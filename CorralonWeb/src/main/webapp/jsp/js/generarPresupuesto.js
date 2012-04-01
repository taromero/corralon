	function savePresupuesto() {
		$.ajax({  
			url: 'savePresupuestoAjax.htm',
			data: ({items : generarStringDeItemsParaJson(), cliente : obtenerDNI($('#cliente').val()), usuario : dniUsuario}),
			success: function(data) {   
				$('#errorPresupuesto').html(data);
				$('#errorPresupuestoDiv').show();
	         }
		});
	}
	
	function generarStringDeItemsParaJson(){
		var strFinal = '[';
		var strAux;
		for(var i = 0 ; i < cantMat ; i++){
			if($('#mat' + i).length > 0){
				if(!esMadera($('#codMat' + i).val())){
					strAux = '{"mat" : "' + $('#mat' + i).html() + ' - ' + $('#codMat' + i).val()  + '", "cant" : "' + $('#cant' + i).html() + '"}';
					if(i+1 < cantMat){
						strAux += ',';
					}
					strFinal += strAux;
				}
			}
		}
		strFinal += ']';
		return strFinal;
	}
	
	function encontrarMaderas(i){
		if(i <= $('#cantidad1').val()){
			$.ajax({  
				url: 'encontrarMaderasAjax.htm',
				data: ({desc : $('#material').val(), largo : $('#unidad1').val(), cantidad : $('#cantidad1').val()}),
				success: function(data) {  
				if(data.length == 0){
					alert('No se encontro una madera del tipo especificado que tuviese un largo suficiente');
				}else{
					$('#tablaModalMaderas').empty(); 
						for (var index in data) {
							$('#tablaModalMaderas').append(
								'<tr>' + 
									'<td>' + data[index].descripcion + '</td>' + 
									'<td>' + data[index].largo + '</td>' + 
									'<td>' + data[index].stock + ' unidades</td>' + 
									'<td>' + data[index].sobrante + '</td>' + 
									'<td><input type="radio" name="r1" id="l' + index + '" value="' + data[index].largo + '" class="ui-state-default ui-corner-all"/></td>' + 
								'</tr>'
							);
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
				precio = data.precio;
				idMadMadre = data.idMadMadre;
				var cantidadPedida = $('#cantidad1').val();
				$('#cantidad1').val(data.cantidadCortadas);
				agregarItem();
				$('#cantidad1').val(cantidadPedida - data.cantidadCortadas);
				encontrarMaderas(i + 1);
             }
		});
	}

	function cancelarPresupuestoAjax(){
		cantMat = 0;
		$('#contenidoTablaGenerarP').empty();
		$('#divListado1').hide();
		$('#errorPresupuestoDiv').hide();
		calcularTotal1();
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

	function busquedaDescuentoAjax() { //TODO, cambiar funcionalidad
		$.ajax({
			url: 'buscarDescuentoAjax.htm',
			data: ({dniCliente : obtenerDNI($('#cliente').val())}),
			success: function(data) {   
				$('#descuento1').html(data);
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
	});
	
    
    
    
    function verificarMaterial(){
		if(validatePresupuesto()){
			$( "#dialog:ui-dialog" ).dialog( "destroy" );
			if(esMadera($('#material').val())){
				encontrarMaderas(0);
			}else{
				agregarItem();
			}
		}
	}
    
    function getPrecioYUnidadAjax(descripcion){
		$.ajax({  
			url: 'getPrecioYUnidadAjax.htm',
			data: ({desc : descripcion}),
			success: function(data) {  
				precio = data.precio;
				unidadMat = data.unidad;
				manejarUnidades(descripcion);
             }
		});
	}
    
    function manejarUnidades(desc){
		if(esMadera(desc)){
			$('#metrosLineales1').show();
			$('#unidad1').addClass('checkNumeric');
			$('#unidadMadera').val(unidadMat);
		}else{
			$('#metrosLineales1').hide();
			$('#unidad1').removeClass('checkNumeric');
			$('#unidadMat').val(unidadMat);
			$('#unidadMat').html(unidadMat);
		}
	}
    
    var cantMat = 0;
    var precio = -1;
    var unidadMat = false; //Me indica la unidad del material
    var filasCorridas = false;
    var idMadMadre = false;
    /**
     * Agrega un item a la tabla
     */
    function agregarItem(){
    	$('#divListado1').show();
    	if($('#material').val() == ''){
    		alert('ingrese un producto por favor');
    	}else if($('#cant').val() == ''){
    		alert('por favor ingrese una cantidad');	
    	}else{
    		if(existeMaterialEnTabla()){
    			sumarCantidadAMaterialExistente();
    		}else{
    			generarContenidoItemTabla();
    			agregarColumnaUnidadYCorrerFilas();
    			$('#total1').html((parseFloat(parseFloat($('#total1').html())) + parseFloat($('#precio' + (cantMat)).html())).toFixed(2));
        		$('#totalConDescuento1').html((parseFloat($('#total1').html()) - parseFloat($('#total1').html()) * parseFloat($('#descuento1').html())/100).toFixed(2));
    			cantMat++;
    		}    		
    		idMadMadre = null;
    		$('#unidad1').html('');
    		$('#unidad1').val('');
    	}
    }

    function existeMaterialEnTabla(){
    	var nombreCodigoMaterial = $('#material').val();
    	var indiceItem = indiceItemConEseMaterial(cortarNombreMaterialParaLista(nombreCodigoMaterial));
    	if(esMadera(nombreCodigoMaterial)){
    		var matN = $('#unidad1').val() + ' ' + $('#unidadMadera').html();
    	}else{
    		var matN = ' ' + unidadMat;
    	}
		var matV = $('#uni' + indiceItem).html();
		if(matV != matN){
			return false;
		}
    	return indiceItem != -1;
    }
    /**
     * Genera el html del item que se va a agregar a la tabla
     */
    function generarContenidoItemTabla(){
    	//Si es madera
    	if(idMadMadre){
    		$('#contenidoTablaGenerarP').append(
    				'<tr id="item' + cantMat + '">' + 
    					'<td id="mat' + cantMat + '">' + cortarNombreMaterialParaLista($('#material').val()) + '</td>' + 
    					'<td id="cant' + cantMat + '">' + $('#cantidad1').val() + '</td>' +   
    					'<td id="precio' + cantMat + '">' + (precio * $('#cantidad1').val() * $('#unidad1').val()).toFixed(2) + '</td>' + '<td>' + '<a id="quitar'+cantMat+'" href="javascript:quitarItem(' + cantMat + ')" class="hideOnPrint"><em>(quitar)</em></a></td>' +
    					'<input type="hidden" id="codMat' + cantMat + '" value = "' + cortarCodigoParaLista($("#material").val()) + '" />' +
    					'<input type="hidden" id="preciounit' + cantMat + '" value = "' + (precio * 1).toFixed(2) + '" />' +
    					'<input type="hidden" id="idMadMadre' + cantMat + '" class="idMadre" value = "' + idMadMadre + '" />' +
    				'</tr>'
    		);
    	}else{
    		$('#contenidoTablaGenerarP').append(
    			'<tr id="item' + cantMat + '">' + 
    				'<td id="mat' + cantMat + '">' + cortarNombreMaterialParaLista($('#material').val()) + '</td>' + 
    				'<td id="cant' + cantMat + '">' + $('#cantidad1').val() + '</td>' +   
    				'<td id="precio' + cantMat + '">' + (precio * $('#cantidad1').val()).toFixed(2) + '</td>' + '<td>' + '<a id="sumar'+cantMat+'" href="javascript:sumarCant(' + "'cant" + cantMat + "','precio" + cantMat + "', 'preciounit" + cantMat + "','1" + "'" + ')" class="hideOnPrint"> +</a> <a id="restar'+cantMat+'" href="javascript:restarCant(' + "'cant" + cantMat + "','precio" + cantMat + "', 'preciounit" + cantMat + "','1" + "'" + ')" class="hideOnPrint"> -</a> <a id="quitar'+cantMat+'" href="javascript:quitarItem(' + cantMat + ')" class="hideOnPrint"><em>(quitar)</em></a></td>' +
    				'<input type="hidden" id="codMat' + cantMat + '" value = "' + cortarCodigoParaLista($("#material").val()) + '" />' +
    				'<input type="hidden" id="preciounit' + cantMat + '" value = "' + (precio * 1).toFixed(2) + '" />' +
    			'</tr>'
    		);
    	}
    }
    
    /**
     * Si se agrega un material con y todavia no se agrego la columna unidad, se agrega 
     * y se corren todas las filas un espacio (para que queden alineadas con los titulos de las columnas.
     */
    function agregarColumnaUnidadYCorrerFilas(){
    	//Si el material a agregar tiene unidad
    	if(unidadMat){
			$('#item' + cantMat).prepend(
				'<td id="uni' + cantMat + '">' + $('#unidad1').val() + ' ' + unidadMat + '</td>'
			);
			//Si no corri las filas todavia
			if(!filasCorridas){
				filasCorridas = true;
				$('#headerMaterial1').removeClass('rounded-left');
				$('#headers1').prepend(
					'<th class="rounded-left" id="headerunidad1">Unidad </th>'
				);
				for(var i = 0; i<cantMat; i++){
					$('#item' + i).prepend('<td></td>');
				}
			}
		}else if(filasCorridas){
			$('#item' + cantMat).prepend('<td></td>');
		}
    }

    function quitarItem(nro){
    	if(esMadera($('#codMat' + nro).val())){
//    		cancelarCorteAjax($('#mat' + nro).html() + ' - ' + $('#codMat' + nro).val(), $('#uni' + nro).html(), $('#idMadMadre' + nro).val());
    		var NombreYcodigoMaderaARearmar = $('#mat' + nro).html() + ' - ' + $('#codMat' + nro).val();
    		var largoMaderaARearmar = $('#uni' + nro).html().split(' ')[0]; //Obtengo el numero (sin la unidad)
    		$('#item' + nro + ' .idMadre').each(function(){
    			cancelarCorteAjax(NombreYcodigoMaderaARearmar, largoMaderaARearmar, $(this).val());
    		});
    	}
    	$('#item' + nro).remove();
    	for(var i = (cantMat -1); i > nro; i--){
    		$('#item' + i).attr('id', 'item' + (i-1));
    		$('#mat' + i).attr('id', 'mat' + (i-1));
    		$('#cant' + i).attr('id', 'cant' + (i-1));
            $('#precio' + i).attr('id', 'precio' + (i-1));
    		$('#codMat' + i).attr('id', 'codMat' + (i-1));
    		$('#preciounit' + i).attr('id', 'preciounit' + (i-1));
    		$('#quitar' + i).attr('href', 'javascript:quitarItem(' + (i-1) + ')');
    		$('#quitar' + i).attr('id', 'quitar' + (i-1));
    		$('#sumar' + i).attr('href', 'javascript:sumarCant(' + "'cant" + (i-1) + "','precio" + (i-1) + "', 'preciounit" + (i-1) + "','1" + "'" + ')');
    		$('#sumar' + i).attr('id', 'sumar' + (i-1));
    		$('#restar' + i).attr('href', 'javascript:restarCant(' + "'cant" + (i-1) + "','precio" + (i-1) + "', 'preciounit" + (i-1) + "','1" + "'" + ')');
    		$('#restar' + i).attr('id', 'restar' + (i-1));
    	}
    	cantMat--;
    	calcularTotal1();
    }

    function calcularTotal1(){
    	var totalRecalculado = 0;
    	for(var i = 0; i<cantMat; i++){
    		totalRecalculado += parseFloat($('#preciounit' + i).val()) * parseInt($('#cant' + i).html());
    	}
    	$('#total1').html(totalRecalculado.toFixed(2));
    	$('#totalConDescuento1').html((totalRecalculado - totalRecalculado * parseInt($('#descuento1').html())/100).toFixed(2));
    }
    
    function indiceItemConEseMaterial(nombreMaterial){
    	for(var i=0; i<cantMat ; i++){
    		if($('#mat' + i).html() == nombreMaterial){
    			return i;
    		}
    	}
    	return -1;
    }
    
    function sumarCantidadAMaterialExistente(){
    	var nombreCodigoMaterial = $('#material').val();
    	var indiceItem = indiceItemConEseMaterial(cortarNombreMaterialParaLista(nombreCodigoMaterial));
    	var cantidadMaterialExistente = $('#cant' + indiceItem).html();
    	$('#total1').html((parseFloat(parseFloat($('#total1').html())) - parseFloat($('#precio' + (indiceItem)).html())).toFixed(2));
		$('#cant' + indiceItem).html(parseFloat(cantidadMaterialExistente) + parseInt($('#cantidad1').val()));
		$('#precio' + indiceItem).html((parseFloat($('#precio' + indiceItem).html()) + parseFloat((precio * $('#cant' + indiceItem).html()))).toFixed(2));
		$('#total1').html((parseFloat(parseFloat($('#total1').html())) + parseFloat($('#precio' + (indiceItem)).html())).toFixed(2));
		if(esMadera(nombreCodigoMaterial)){
			$('#item' + indiceItem).append(
				'<input type="hidden" id="idMadMadre' + indiceItem + '" class="idMadre" value = "' + idMadMadre + '" />'
			);
		}
    }