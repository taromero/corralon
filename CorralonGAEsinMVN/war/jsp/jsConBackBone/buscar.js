	function initializePresupuestoBq(){
		return new Presupuesto({tablaID: '#contenidoTablaBuscar',
									totalTdID: '#totalSdBuscar', 
									descuentoTdID: '#descuentoPBuscar',
									totalConDescuentoTdID: '#totalCdPBuscar',
									errorDivID: '#errorBusquedaDiv',
									errorLabelID: '#errorBusqueda',
									tablaInputsID: '#tablaInputsBusqueda',
									divID: '#resultado'});
	}
	
	var presupuestoBq;
	
	$(function() {
		//Agrupo los radios
		$( "#radioBuscar" ).buttonset();
		
		//Le doy el comportamiento a los radios
		$("#radioPresupuesto").click(function(){
		    mostrarCliente();
		 });
		
	    $("#radioOC").click(function(){
		    mostrarProveedor();
	 	 });
	});

	function imprimir(){
		javascript:window.print();
	}
	
	function buscar(){
		if(validateBusqueda()){
			if($('#radioPresupuesto').is(':checked')){
				if($('#codigo3').val() == ""){
					buscarPresupuestos();
				}else{
					buscarPresupuestoAjax();
				}
			}else{
				if($('#codigo3').val() == ""){
					buscarOrdenesCompraAjax();
				}else{
					buscarOrdenCompraAjax();
				}
			}
		}
	}
	
	var htmlFijoBuscar;
	var unidad3 = false;
	var correrFilas3 = false;
	var cantMat3 = 0;
	function buscarPresupuestoAjax(idPresupuesto) {
		presupuestoBq = initializePresupuestoBq();
		var idP;
		if(idPresupuesto == null){
			idP = $('#codigo3').val();
		}else{
			idP = idPresupuesto;
		}
		$.ajax({
			url: 'buscarPresupuestoAjax.htm',
			data: ({idPresupuesto : idP}),
			success: function(data) {
				if(data.isConfirmado == true){
					$('#confirmadoP').html('Si');
				}else{
					$('#confirmadoP').html('No');
				}
				$('#nroPBuscar').html(data.id_presupuesto);
				$('#clientePBuscar').html(data.cliente.apellido + ', ' + data.cliente.nombre);
				$('#emisionPBuscar').html(data.fechaEmision);
				$('#creadorPBuscar').html(data.usuario.apellido + ', ' + data.usuario.nombre);
				$('#totalPBuscar').html((data.total - data.total*data.cliente.descuento/100).toFixed(2)); //Multiplico por 1 porque sino creo que no anda el toFixed(2)
				//armo los items de la tabla
				var l1, l2, l3, lUnidad, htmlItems;
				var bDesc, bCant, bPrecio, bNombre, unidadMaterial, bLargo;
				for (var index in data.itemsPresupuesto) {
					bDesc = data.itemsPresupuesto[index].material.descripcion;
					bCant = data.itemsPresupuesto[index].cantidad;
					bPrecio = data.itemsPresupuesto[index].material.precioVenta;
					bUnidadMat = data.itemsPresupuesto[index].material.unidad;
					bLargo = data.itemsPresupuesto[index].material.largo;
					bIdMadMadre = data.itemsPresupuesto[index].material.idMadMadre;
					
					material = new Material({descripcion: bDesc, nombre: cortarNombreMaterialParaLista(bDesc), precio: bPrecio, unidad: bUnidadMat, 
													largo: bLargo, idMadMadre: bIdMadMadre});
					
					presupuestoBq.agregarItem(material, bCant);
	            }
	            mostrarPresupuesto();
			},
			error: function (XMLHttpRequest, textStatus, errorThrown, data) {
				presupuestoBq.mostrarError(XMLHttpRequest.responseText);
	            if(data){
	            	alert("Internal Server Error: " + data);
	            }
			}
		});
	}
	
	function buscarOrdenCompraAjax(idOrdenCompra) {
		var idOC;
		if(idOrdenCompra == null){
			idOC = $('#codigo3').val();
		}else{
			idOC = idOrdenCompra;
		}
		$.ajax({
			url: 'buscarOrdenCompraAjax.htm',
			data: ({idOC : idOC}),
			success: function(data) {
				$('#headers3').html(
					'<th class="rounded-left" id="headerMaterial3">Material </th>' +
					'<th>Cantidad</th>' +
					'<th class="rounded-right">Subtotal</th>'
				);	
				$('#nroOCBuscar').html(data.id_orden_compra);
				$('#proveedorOCBuscar').html(data.proveedor.razonSocial);
				$('#emisionOCBuscar').html(data.fechaEmision);
				$('#entregaOCBuscar').html(data.fechaEntrega);
				$('#formaPagoOCBuscar').html(data.formaPago);
				$('#creadorOCBuscar').html(data.usuario.apellido + ', ' + data.usuario.nombre);
				$('#totalOCBuscar').html(data.total);
				//armo los items de la tabla
				var l1, l2, l3, lUnidad, htmlItems;
				var bDesc, bCant, bPrecio, bCodigo;
				if(htmlFijoBuscar == null){
					htmlFijoBuscar = $('#contenidoTablaBuscar').html();
				}
				for (var index in data.itemsOrdenCompra) {
					bDesc = data.itemsOrdenCompra[index].material.material.descripcion; //Material proveedor tiene un material, que tiene una descripci
					bCant = data.itemsOrdenCompra[index].cantidad;
					bPrecio = data.itemsOrdenCompra[index].material.precio;
					l1 = '<tr id="item3' + index + '"><td>' + cortarNombreMaterialParaLista(bDesc) + '</td>';
					l2 = '<td>' + bCant + '</td>';
					l3 = '<td>' + (bPrecio * bCant).toFixed(2) + '</td>';
					bCodigo = cortarCodigoParaLista(bDesc);
					l4 = '<input type="hidden" id="codMat3' + index + '" value = "' + bCodigo +'" /></tr>';
					
					var unidadMaterial = data.itemsOrdenCompra[index].material.material.unidad;
					if(unidadMaterial){
						l1 = '<td>' + cortarNombreMaterialParaLista(bDesc) + '</td>';
						lUnidad = '<tr><td>' + unidadMaterial + '</td>';
						if(esMadera(bDesc)){
							var bLargo = data.itemsOrdenCompra[index].material.material.largo;
							l1 = '<td>' + cortarNombreMaterialParaLista(bDesc) + '</td>';
							l2 = '<td>' + '1'+ '</td>';
							l3 = '<td>' + bLargo * bPrecio + '</td>';
							lUnidad = '<tr><td>' + bLargo + ' ' + unidadMaterial + '</td>';	
						}
						if(unidad3 == false){
							unidad3 = true;
							correrFilas3 = true;
							$('#headerMaterial3').removeClass('rounded-left');
							$('#headers3').prepend(
								'<th class="rounded-left" id="headerunidad3">Unidad </th>'
							);
						}
						htmlItems += lUnidad + l1 + l2 + l3 +l4;
					}else{
						htmlItems += l1 + l2 + l3 + l4;
					}
	            }
	            $('#contenidoTablaBuscar').html(htmlItems + htmlFijoBuscar);
	            $('#totalSdBuscar').html(data.total.toFixed(2));
				
	            mostrarOC();
			}
		});
	}
	
	
	
	function buscarPresupuestos(){
		$.ajax({
			url: 'buscarPresupuestosAjax.htm',
			data: ({fecha: $(fecha3).val(), cliente: obtenerDNI($(cliente3).val())}),
			success: function(data) {
				$('.ordenCompra').hide();
				$('#tablaModalPresupuestosOC').empty();
				for(var index in data){
					//Hacer que sean radiobuttons para elegir
					$('#tablaModalPresupuestosOC').append(
						'<tr>' +
							'<td id="fecha3Modal' + index + '">' + data[index].fechaEmision + '</td>' +
							'<td id="cliente3Modal' + index + '">' + data[index].cliente.apellido + '</td>' +
							'<td id="Monto3Modal' + index + '">' + data[index].total.toFixed(2) + '</td>' + 
							'<td id="id3Modal' + index + '">' + data[index].id_presupuesto + '</td>' +
							'<td><input type="radio" name="r3" id="radio3Modal' + index + '" value="' + data[index].id_presupuesto + '" class="ui-state-default ui-corner-all"/></td>' + 
						'</tr>'
					);	
				}
				$('#modalPresupuestosOC').dialog({
					resizable: true,
					height:550,
					width:500,
					modal: true,
					buttons: {
						"Buscar": function() {
							buscarPresupuestoAjax($('#tablaModalPresupuestosOC input:checked').val());
							$( this ).dialog( "close" );
						},
						"Cancelar": function() {
							$( this ).dialog( "close" );
						}
					}
				});
			},
			error: function (XMLHttpRequest, textStatus, errorThrown, data) {
				presupuestoBq.mostrarError(XMLHttpRequest.responseText);
	            if(data){
	            	alert("Internal Server Error: " + data);
	            }
			}
		});
	}
	
	function buscarOrdenesCompraAjax(){
		$.ajax({
			url: 'buscarOrdenesCompraAjax.htm',
			data: ({fecha: $(fecha3).val(), proveedor: $(proveedor3).val()}),
			success: function(data) {
				$('.presupuesto').hide();
				$('#tablaModalPresupuestosOC').empty();
				for(var index in data){
					//Hacer que sean radiobuttons para elegir
					$('#tablaModalPresupuestosOC').append(
						'<tr>' +
							'<td id="fecha3Modal' + index + '">' + data[index].fechaEmision + '</td>' +
							'<td id="proveedor3Modal' + index + '">' + data[index].proveedor.razonSocial + '</td>' +
							'<td id="Monto3Modal' + index + '">' + data[index].total.toFixed(2) + '</td>' + 
							'<td id="id3Modal' + index + '">' + data[index].id_orden_compra + '</td>' +
							'<td><input type="radio" name="r3" id="radio3Modal' + index + '" value="' + data[index].id_orden_compra + '" class="ui-state-default ui-corner-all"/></td>' + 
						'</tr>'
					);	
				}
				$('#modalPresupuestosOC').dialog({
					resizable: true,
					height:550,
					width:500,
					modal: true,
					buttons: {
						"Buscar": function() {
							buscarOrdenCompraAjax($('#tablaModalPresupuestosOC input:checked').val());
							$( this ).dialog( "close" );
						},
						"Cancelar": function() {
							$( this ).dialog( "close" );
						}
					}
				});
			}
		});
	}
	
	function confirmarPresupuestoAjax(){
		$.ajax({
			url: 'confirmarPresupuestoAjax.htm',
			data: ({idPresupuesto : $('#nroPBuscar').html()}),
			success: function(data) {
				$('#confirmadoP').html('Si');
				alert("Presupuesto confirmado");
			}
		});
	}
	
	function rechazarPresupuestoAjax(){
		$.ajax({
			url: 'rechazarPresupuestoAjax.htm',
			data: ({idPresupuesto : $('#nroPBuscar').html()}),
			success: function(data) {
				$('#resultado').hide();
				alert("Presupuesto eliminado");
			}
		});
	}
	
	function mostrarPresupuesto(){
		$('#resultado').show();
		$('.presupuesto').show();
		$('.ordenCompra').hide();
	}
	
	function mostrarOC(){
		$('#resultado').show();
		$('.ordenCompra').show();
		$('.presupuesto').hide();
	}
	
    function mostrarProveedor(){
		$('#clienteBuscar').hide();
		$('#proveedorBuscar').show();
	}
    
	function mostrarCliente(){
		$('#proveedorBuscar').hide();
		$('#clienteBuscar').show();
	}