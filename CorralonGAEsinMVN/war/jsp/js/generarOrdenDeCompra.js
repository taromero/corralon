	
	//Hace que cuando se cambie el proveedor seleccionado se reinicien los atributos de la pantalla de generacion de ordenes de compra
	$(function(){
		$('#seleccionarProveedor2').toggle(function(){
			$('#proveedor').attr('disabled', 'disabled');
			},function(){
				var inputs = document.getElementsByTagName('input');
				for (var i_tem = 0; i_tem < inputs.length; i_tem++){
					if (inputs[i_tem].type=='text'){
						inputs[i_tem].value='';
					}
				}
				$('#proveedor').attr('disabled', '');
				getMaterialesProveedorAjax();
			}
		);
	});

	function saveOC() {
		$.ajax({  
			url: 'saveOrdenDeCompraAjax.htm',
			data: ({items : generarStringDeItemsParaJsonOC(), 
						proveedor : $('#proveedor').val(), 
							usuario : dniUsuario, 
								fechaEntrega : $('#fechaEntrega').val(), 
									formaPago : $('#formaPago').val()}),
			success: function(data) {
				$('#errorOC').html(data);
				$('#errorOCDiv').show();
	         }
		});
	}
	
	function getMaterialesProveedorAjax() {
		$.ajax({
			url: 'getMaterialesProveedorAjax.htm',
			data: ({prov : $('#proveedor').val()}),
			success: function(data) {
				var str = new Array();
				   
	            for (var index in data) {
					str[index] = data[index];
	            }
			 	$("#materialesProveedor").autocomplete({
				    source: str,
				    select: function(event, ui) { //Esto hace que cuando cambie el valor del input "material" se fije si es madera, y si es que agregue el campo para poner los metros lineales. No podia usar jquery $('#material').change() porque me tomaba el valor que estaba en material antes de la asignacion del valor del item del autocomplete.
				    	getPrecioOCYUnidadYAgregarItemAjax(ui.item.value);
					}
				});
            	
             }
		});
	}
	
	function verificarMaterialOC(){
		if(esMadera($('#materialesProveedor').val())){
			agregarMaderaASesion();
		}else{
			agregarItemOC();
		}
	}
	
	function agregarMaderaASesion(){
		$.ajax({  
			url: 'agregarMaderaASesion.htm',
			data: ({desc : $('#materialesProveedor').val(), cant : $('#cantidad2').val(), largo : $('#unidad2').val()}),
			success: function(data) {  
				agregarItemOC();
             }
		});
	}
	
	function getPrecioOCYUnidadYAgregarItemAjax(descripcion){
		$.ajax({  
			url: 'getPrecioYUnidadOCAjax.htm',
			data: ({prov: $('#proveedor').val(), desc : descripcion}),
			success: function(data) {
				precio2 = data.precio;
				unidadMat2 = data.unidad;
				manejarUnidadesOC(descripcion);
             }
		});
	}
	
	function manejarUnidadesOC(desc){
		if(esMadera(desc)){
			$('#metrosLineales2').show();
			$('#unidad2').addClass('checkNumeric');
			$('#unidadMadera2').val(unidadMat);
		}else{
			$('#metrosLineales2').hide();
			$('#unidad2').removeClass('checkNumeric');
			$('#unidadMat2').val(unidadMat);
			$('#unidadMat2').html(unidadMat);
		}
	}
	
    var unidadMat2 = false; //Me indica la unidad del material
	var filasCorridas2 = false;
	var cantMat2 = 0;
    var precio2 = -1;
    function agregarItemOC(){ 
    	$('#divListado2').show();
		
    	if(existeMaterialEnTablaOC()){
			sumarCantidadAMaterialExistenteOC();
		}else{
			generarContenidoItemTablaOC();
			agregarColumnaUnidadYCorrerFilasOC();
			cantMat2++;
			$('#total2').html((parseFloat(parseFloat($('#total2').html())) + parseFloat($('#precio2' + (cantMat2-1)).html())).toFixed(2));
		}
    	$('#unidad1').html('');
		$('#unidad1').val('');
    }
    
    function generarContenidoItemTablaOC(){
    	if(esMadera($("#materialesProveedor").val())){
    		$('#contenidoTablaGenerarOC').append(
				'<tr id="item2' + cantMat2 + '">' + 
					'<td id="mat2' + cantMat2 + '">' + cortarNombreMaterialParaLista($('#materialesProveedor').val()) + '</td>' + 
					'<td id="cant2' + cantMat2 + '">' + $('#cantidad2').val() + '</td>' +   
					'<td id="precio2' + cantMat2 + '">' + (precio2 * $('#cantidad2').val() * $('#unidad2').val()).toFixed(2) + '</td>' + '<td>' + '<a id="quitar'+cantMat2+'" href="javascript:quitarItem(' + cantMat2 + ')" class="hideOnPrint"><em>(quitar)</em></a></td>' +
					'<input type="hidden" id="codMat2' + cantMat2 + '" value = "' + cortarCodigoParaLista($("#materialesProveedor").val()) + '" />' +
					'<input type="hidden" id="preciounit2' + cantMat2 + '" value = "' + (precio2 * 1).toFixed(2) + '" />' +
				'</tr>'
    		);
    	}else{
			$('#contenidoTablaGenerarOC').append(
				'<tr id="item2' + cantMat2 + '">' + 
					'<td id="mat2' + cantMat2 + '">' + cortarNombreMaterialParaLista($('#materialesProveedor').val()) + '</td>' + 
					'<td id="cant2' + cantMat2 + '">' + $('#cantidad2').val() + '</td>' +   
					'<td id="precio2' + cantMat2 + '">' + precio2 * $('#cantidad2').val() + '</td>' + '<td>' + '<a id="sumar2'+cantMat2+'" href="javascript:sumarCant(' + "'cant2" + cantMat2 + "', 'precio2" + cantMat2 + "', 'preciounit2" + cantMat2 + "', '2" + "'" + ')" class="hideOnPrint"> +</a> <a id="restar2'+cantMat2+'" href="javascript:restarCant(' + "'cant2" + cantMat2 + "', 'precio2" + cantMat2 + "', 'preciounit2" + cantMat2 + "', '2" + "'" + ')" class="hideOnPrint"> -</a> <a id="quitar2'+cantMat2+'" href="javascript:quitarItem2(' + cantMat2 + ')" class="hideOnPrint"><em>(quitar)</em></a></td>' +
					'<input type="hidden" id="codMat2' + cantMat2 + '" value = "' + cortarCodigoParaLista($("#materialesProveedor").val()) + '" />' +
					'<input type="hidden" id="preciounit2' + cantMat2 + '" value = "' + precio2 + '" />' +
				'</tr>'
			);
    	}
    }

	 /**
     * Si se agrega un material con y todavia no se agrego la columna unidad, se agrega 
     * y se corren todas las filas un espacio (para que queden alineadas con los titulos de las columnas.
     */
    function agregarColumnaUnidadYCorrerFilasOC(){
    	//Si el material a agregar tiene unidad
    	if(unidadMat2){
			$('#item2' + cantMat2).prepend(
				'<td id="uni2' + cantMat2 + '">' + $('#unidad2').val() + ' ' + unidadMat2 + '</td>'
			);
			//Si no corri las filas todavia
			if(!filasCorridas2){
				filasCorridas2 = true;
				$('#headerMaterial2').removeClass('rounded-left');
				$('#headers2').prepend(
					'<th class="rounded-left" id="headerunidad2">Unidad </th>'
				);
				for(var i = 0; i<cantMat2; i++){
					$('#item2' + i).prepend('<td></td>');
				}
			}
		}else if(filasCorridas2){
			$('#item2' + cantMat2).prepend('<td></td>');
		}
    }
    
    function existeMaterialEnTablaOC(){
    	var nombreCodigoMaterial = $('#materialesProveedor').val();
    	var indiceItem = indiceItemConEseMaterialOC(cortarNombreMaterialParaLista(nombreCodigoMaterial));
    	return indiceItem != -1;
    }
    	
    function indiceItemConEseMaterialOC(nombreMaterial){
    	for(var i=0; i<cantMat2 ; i++){
    		if($('#mat2' + i).html() == nombreMaterial){
    			return i;
    		}
    	}
    	return -1;
    }
    
    function sumarCantidadAMaterialExistenteOC(){
    	var nombreCodigoMaterial = $('#materialesProveedor').val();
    	var indiceItem = indiceItemConEseMaterialOC(cortarNombreMaterialParaLista(nombreCodigoMaterial));
    	var cantidadMaterialExistente = $('#cant2' + indiceItem).html();
    	$('#total2').html((parseFloat(parseFloat($('#total2').html())) - parseFloat($('#precio2' + (indiceItem)).html())).toFixed(2));
		$('#cant2' + indiceItem).html(parseFloat(cantidadMaterialExistente) + parseInt($('#cantidad2').val()));
		$('#precio2' + indiceItem).html((parseFloat($('#precio2' + indiceItem).html()) + parseFloat((precio2 * $('#cant2' + indiceItem).html()))).toFixed(2));
		$('#total2').html((parseFloat(parseFloat($('#total2').html())) + parseFloat($('#precio2' + (indiceItem)).html())).toFixed(2));
    }
    
    function quitarItem2(nro){
    	$('#item2' + nro).remove();
    	for(var i = (cantMat2 -1); i > nro; i--){
    		$('#item2' + i).attr('id', 'item2' + (i-1));
    		$('#mat2' + i).attr('id', 'mat2' + (i-1));
    		$('#cant2' + i).attr('id', 'cant2' + (i-1));
            $('#precio2' + i).attr('id', 'precio2' + (i-1));
    		$('#codMat2' + i).attr('id', 'codMat2' + (i-1));
    		$('#preciounit2' + i).attr('id', 'preciounit2' + (i-1));
    		$('#quitar2' + i).attr('href', 'javascript:quitarItem2(' + (i-1) + ')');
    		$('#quitar2' + i).attr('id', 'quitar2' + (i-1));
    		$('#sumar2' + i).attr('href', 'javascript:sumarCant(' + "'cant2" + (i-1) + "','precio2" + (i-1) + "', 'preciounit2" + (i-1) + "','2" + "'" + ')');
    		$('#sumar2' + i).attr('id', 'sumar2' + (i-1));
    		$('#restar2' + i).attr('href', 'javascript:restarCant(' + "'cant2" + (i-1) + "','precio2" + (i-1) + "', 'preciounit2" + (i-1) + "','2" + "'" + ')');
    		$('#restar2' + i).attr('id', 'restar2' + (i-1));
    	}
    	cantMat2--;
    	calcularTotal2();
    }

    function calcularTotal2(){
    	var totalRecalculado = 0;
    	for(var i = 0; i<cantMat2; i++){
    		totalRecalculado += parseFloat($('#preciounit2' + i).val()) * parseInt($('#cant2' + i).html());
    	}
    	$('#total2').html(totalRecalculado);
    }
    
    function generarStringDeItemsParaJsonOC(){
		var strFinal = '[';
		var strAux;
		for(var i = 0 ; i < cantMat2 ; i++){
			if(!esMadera($('#codMat2' + i).val())){
				if($('#mat2' + i).length > 0){
						strAux = '{"mat" : "' + $('#mat2' + i).html() + ' - ' + $('#codMat2' + i).val()  + 
										'", "cant" : "' + $('#cant2' + i).html() + '"}';
						if(i+1 < cantMat2){
							strAux += ',';
						}
						strFinal += strAux;
				}
			}
		}
		strFinal += ']';
		return strFinal;
	}