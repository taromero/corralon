//	window.onbeforeunload = function() { 
//		return "Estas seguro que quieres salir de la pagina?? se perderan todos los cambios."; 
//	}; 

	$(function(){
		//Genera la estructura de tabs
		$('#tabs').tabs();
		
		//Llena los autocompletes
		$('.autocompleteClientes').autocomplete({
			source: clientesList
		});
		
		$('.autocompleteMateriales').autocomplete({
			source: materialesList,
			select: function(event, ui) { //Esto hace que cuando cambie el valor del input "material" se fije si es madera, y si es que agregue el campo para poner los metros lineales. No podia usar jquery $('#material').change() porque me tomaba el valor que estaba en material antes de la asignacion del valor del item del autocomplete.
				getPrecioYUnidadAjax(ui.item.value);
			}
		});
		
		$('.autocompleteProveedores').autocomplete({
			source: proveedoresList
		});
		
		//Genera los calendarios para los campos que lo requieran
		$(".datepicker").datepicker( {
			dateFormat : 'dd-mm-yy',
			monthNames: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 
		                     'Junio', 'Julio', 'Agosto', 'Septiembre', 
		                     'Octubre', 'Noviembre', 'Diciembre'],
		    dayNamesMin: ['Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa']
		});
		
		//Setea los radios a marcar de entrada
		$('.marcarDeEntrada').attr('checked', true);
		
		//Hace que sean resizable los listados
		$( ".rounded-corner" ).resizable();
		
	});
	
	//Cuando se cierre la ventana se van a cancelar todos los cortes que esten guardados en sesion (cuando se guarda el presupuesto, se eliminan sus cortes de la sesion)
	$(window).unload(function() {
		cancelarCortesAjax();
	});
	
	function cortarNombreMaterialParaLista(descripcion){
		return descripcion.slice(0,-15);
	}

	function cortarCodigoParaLista(descripcion){
		return descripcion.slice(-12);
	}
	
	//Corta el dni del nombre para hacer busqudas o guardar datos
	function obtenerDNI(nombre){
		return nombre.slice(-8);
	}

	function esMadera(descripcion){
		return (parseInt(descripcion.slice(-12, -8)) >= parseInt('6200') && parseInt(descripcion.slice(-12, -8)) < parseInt('6300'));
	}
	
	function sumarCant(idCant, idPrecio, idPreciounit, identificadorDeOperacion){
    	$('#' + idCant).html(parseInt($('#' + idCant).html()) + 1);
        var preciounit = parseInt($('#' + idPreciounit).val()).toFixed(2);
        var cantdelitem = parseFloat($('#' + idCant).html());
    	$('#' + idPrecio).html((parseFloat($('#' + idPreciounit).val()) * parseInt($('#' + idCant).html())).toFixed(2));
    	eval("calcularTotal" + identificadorDeOperacion + "()");
    }

    function restarCant(idCant, idPrecio, idPreciounit, identificadorDeOperacion){
    	$('#' + idCant).html(parseInt($('#' + idCant).html()) - 1);
        var preciounit = parseInt($('#' + idPreciounit).val()).toFixed(2);
        var cantdelitem = parseFloat($('#' + idCant).html());
    	$('#' + idPrecio).html((parseFloat($('#' + idPreciounit).val()) * parseInt($('#' + idCant).html())).toFixed(2));
    	eval("calcularTotal" + identificadorDeOperacion + "()");
    }