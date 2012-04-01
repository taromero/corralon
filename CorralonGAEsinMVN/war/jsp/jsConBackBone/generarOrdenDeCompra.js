var ordenDeCompra = new OrdenDeCompra({tablaID: '#contenidoTablaGenerarOC',
										totalTdID: '#total2', 
										descuentoTdID: '#descuento2',
										totalConDescuentoTdID: '#totalConDescuento2',
										errorDivID: '#errorOCDiv',
										errorLabelID: '#errorOC',
										tablaInputsID: 'tablaInputsOC',
										divID: '#ordenDeCompraDiv'});
var materialObtenidoOC;
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
		data: ({items : ordenDeCompra.generateJSON(), 
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
			    	getPrecioOCYUnidadAjax(ui.item.value);
				}
			});
        	
         }
	});
}

function verificarMaterialOC(){
	if(esMadera($('#materialesProveedor').val())){
		agregarMaderaASesion();
	}else{
		ordenDeCompra.agregarItem(materialObtenidoOC, $('#cantidad2').val());
	}
}

function agregarMaderaASesion(){
	$.ajax({  
		url: 'agregarMaderaASesion.htm',
		data: ({desc : $('#materialesProveedor').val(), cant : $('#cantidad2').val(), largo : $('#unidad2').val()}),
		success: function(data) {  
			materialObtenidoOC.set({largo: $('#unidad2').val()});
			ordenDeCompra.agregarItem(materialObtenidoOC, $('#cantidad2').val());
         }
	});
}

function quitarMaderaDeSesion(descripcion){
	$.ajax({  
		url: 'quitarMaderaDeSesion.htm',
		data: ({desc : descripcion})
	});
}

function getPrecioOCYUnidadAjax(descripcion){
	$.ajax({  
		url: 'getPrecioYUnidadOCAjax.htm',
		data: ({prov: $('#proveedor').val(), desc : descripcion}),
		success: function(data) {
			materialObtenidoOC = new Material({descripcion: descripcion, nombre: cortarNombreMaterialParaLista(descripcion), 
												precio: data.precio, unidad: data.unidad});
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
