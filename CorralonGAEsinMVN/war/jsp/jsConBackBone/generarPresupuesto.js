	var presupuesto = new Presupuesto({tablaID: '#contenidoTablaGenerarP',
										totalTdID: '#total1', 
										descuentoTdID: '#descuento1',
										totalConDescuentoTdID: '#totalConDescuento1',
										errorDivID: '#errorPresupuestoDiv',
										errorLabelID: '#errorPresupuesto',
										tablaInputsID: '#tablaInputsPresupuesto',
										divID: '#presupuestoDiv'});
	var materialObtenidoP;

	 function verificarMaterial(){
			if(presupuesto.isValid()){
				//$( "#dialog:ui-dialog" ).dialog( "destroy" );
				if(esMadera($('#material').val())){
					encontrarMaderas(0);
				}else{
					presupuesto.agregarItem(materialObtenidoP, $('#cantidad1').val());
				}
			}
		}
	
	function savePresupuesto() {
		$.ajax({  
			url: 'savePresupuestoAjax.htm',
			data: ({items : presupuesto.generateJSON(), cliente : obtenerDNI($('#cliente').val()), usuario : dniUsuario}),
			success: function(data){
				alert(data);
			},
			error: function (XMLHttpRequest, textStatus, errorThrown, data) {
				presupuesto.mostrarError(XMLHttpRequest.responseText);
	            if(data){
	            	alert("Internal Server Error: " + data);
	            }
			}
		});
	}
	
	function encontrarMaderas(i){
		if(i <= $('#cantidad1').val()){
			$.ajax({  
				url: 'encontrarMaderasAjax.htm',
				data: ({desc : $('#material').val(), largo : $('#unidad1').val(), cantidad : $('#cantidad1').val()}),
				success: function(data) {
					$('#tablaModalMaderas').empty(); 
					for (var index in data) {
						$('#tablaDialog1').dataTable().fnAddData( [
								data[index].descripcion, 
								data[index].largo,
								data[index].stock, 
								data[index].sobrante,
								'<td><input type="radio" name="r1" id="l' + index + '" value="' + data[index].largo + '" class="ui-state-default ui-corner-all"/></td>' 
						]);
		            }
					$( "#dialog-confirm" ).dialog({
						resizable: true,
						height:550,
						width:500,
						modal: true,
						buttons: {
							"Guardar sobrante": function() {
								cortarMaderaAjax('true', i);
								$( this ).dialog( "close" );
							},
							"Descartar sobrante": function() {
								cortarMaderaAjax('false', i);
								$( this ).dialog( "close" );
							}
						}
					});
	           },
	           error: function (XMLHttpRequest, textStatus, errorThrown, data) {
					presupuesto.mostrarError(XMLHttpRequest.responseText);
		            if(data){
		            	alert("Internal Server Error: " + data);
		            }
				}
			});
		}
	}

	function cortarMaderaAjax(guardarSobrante, i){
		$.ajax({  
			url: 'cortarMaderaAjax.htm',
			data: ({desc : $('#material').val(), cant : $('#cantidad1').val(), largoMadera : $('#tablaModalMaderas input:checked').val(), largoACortar : $('#unidad1').val(), guardarSobrante : guardarSobrante, cepillar : $('#radioCepillar input:checked').val()}),
			success: function(data) {  
				materialObtenidoP.set({precio: data.precio});
				materialObtenidoP.set({largo: $('#unidad1').val()});
				materialObtenidoP.set({idMadMadre: data.idMadMadre});
				var cantidadPedida = $('#cantidad1').val();
				presupuesto.agregarItem(materialObtenidoP, cantidadPedida);
				$('#cantidad1').val(cantidadPedida - data.cantidadCortadas);
				encontrarMaderas(i + 1);
             }
		});
	}

	function cancelarPresupuestoAjax(){
		presupuesto = new Presupuesto();
		cancelarCortesAjax();
	}

	function cancelarCortesAjax(){
		$.ajax({
			url: 'cancelarCortesAjax.htm'
		});
	}

	function cancelarCorteAjax(desc, largo, idMadre){
		$.ajax({
			url: 'cancelarCorteAjax.htm',
			data: ({desc : desc, largo: largo, idMadre: idMadre}),
			success: function(data) {   
				alert("Bien!!");
             }
		});
	}

	function busquedaDescuentoAjax() {
		$.ajax({
			url: 'buscarDescuentoAjax.htm',
			data: ({dniCliente : obtenerDNI($('#cliente').val())}),
			success: function(data) {   
				presupuesto.set({descuento: data});
             }
		});
	}
	
	$(function(){
		$('#seleccionarCliente1').toggle(function(){
			$('#cliente').attr('disabled', 'disabled');
			},function(){
				var inputs = document.getElementsByTagName('input');
				for (var i_tem = 0; i_tem < inputs.length; i_tem++){
					if (inputs[i_tem].type=='text'){
						inputs[i_tem].value='';
					}
				}
				$('#cliente').attr('disabled', '');
			}
		);
	});
	
	$(function() {
		$( "#radioCepillar" ).buttonset();
		$('#tablaDialog1').dataTable();
	});
    
    function getPrecioYUnidadAjax(descripcion){
		$.ajax({  
			url: 'getPrecioYUnidadAjax.htm',
			data: ({desc : descripcion}),
			success: function(data) {  
				materialObtenidoP = new Material({descripcion: $('#material').val(), nombre: cortarNombreMaterialParaLista(descripcion), 
	    										precio: data.precio, unidad: data.unidad});
				manejarUnidades(descripcion);
             }
		});
	}
    
    function manejarUnidades(desc){
		if(esMadera(desc)){
			$('#metrosLineales1').show();
			$('#unidad1').addClass('checkNumeric');
			$('#unidadMadera').val(materialObtenidoP.get('unidad'));
		}else{
			$('#metrosLineales1').hide();
			$('#unidad1').removeClass('checkNumeric');
			$('#unidad1').val(materialObtenidoP.get('unidad'));
			$('#unidad1').html(materialObtenidoP.get('unidad'));
		}
	}