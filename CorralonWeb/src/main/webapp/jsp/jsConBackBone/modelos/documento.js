Documento = Backbone.Model.extend({
	initialize: function(){
		$(this.get('divID')).hide();
		$(this.get('errorDivID')).hide();
		var doc = this;
		this.set({items: new Items()});
		this.attributes.items.bind('all', function(){
			doc.attributes.precio = doc.calcularPrecio();
			doc.attributes.precioConDescuento = doc.calcularPrecioConDescuento();
			doc.render();
		});
	},
	defaults: {
		precio: 0
	},
	agregarItem: function(material, cantidadMat){
    	if(!material.get('descripcion')){
    		alert('ingrese un producto por favor');
    	}else if(!cantidadMat){
    		alert('por favor ingrese una cantidad');	
    	}else{
    		var indiceMat = this.indiceMaterialEnColeccion(material.get('descripcion'), material.get('largo'));
    		if(indiceMat != -1){
    			this.sumarCantAItemExistente(indiceMat, cantidadMat, material.get('precio'));
    		}else{
    			this.agregarItemNuevo(material, cantidadMat);
    		}    		
    		idMadMadre = null;
    		$('#unidad1').html('');
    		$('#unidad1').val('');
    	}
	},
	sumarCantAItemExistente: function(indiceMat, cantidadMat, precio){
		var item = this.get('items').at(indiceMat);
		item.sumarCantidad(cantidadMat, precio);
	},
	calcularPrecio: function(){
		var precio = 0;
		for(var i = 0; i < this.get('items').length; i++){
			precio += parseFloat(this.get('items').at(i).get('precio'));
		}
		return precio.toFixed(2);
	},
	calcularPrecioConDescuento: function(){
		return (parseFloat(this.get('precio')) - (parseFloat(this.get('precio')) * parseFloat(this.get('descuento'))/100)).toFixed(2);
	},
	indiceMaterialEnColeccion: function(descripcion, largo){
		var material;
    	for(var i = 0; i < this.get('items').length; i++){
    		material = this.get('items').at(i).get('material');
    		if(material.get('descripcion') == descripcion){
    			if(material.get('idsMadres')){
    				if(material.get('unidad') == unidadMat && material.get('largo') == largo){
    					return i;
    				}
    			}else{
    				return i;
    			}
    		}
    	}
    	return -1;
	},
	sumarUnoAItem: function(cid){
		var item = this.get('items').getByCid(cid);
		item.set({cantidad: parseInt(item.get('cantidad'), 10) + 1});
	},
	restarUnoAItem: function(cid){
		var item = this.get('items').getByCid(cid);
		item.set({cantidad: parseInt(item.get('cantidad'), 10) - 1});
	},
	generateJSON: function(){
		var jsonString = '[';
		var stringJSONItem;
		var cantItemsEnArray = this.get('items').length;
		for(var i = 0; i < cantItemsEnArray; i++){
			stringJSONItem = this.get('items').at(i).generateJSON();
			if(stringJSONItem != ''){
				jsonString += stringJSONItem;
				if((parseInt(i, 10)+1) < cantItemsEnArray){
					jsonString += ', ';
				}
			}
			
		}
		jsonString += ']';
		return jsonString;
	},
	isValid: function(){
	   var todoOk = true;
	   var doc = this;
	   $(this.get('tablaInputsID') + ' input.notEmpty').each(function() {
	   	   if(isEmpty($(this).val())){
	   		  todoOk = false;
	   		  doc.mostrarError("Complete todos los campos por favor");
	   		  return false;
	   	   }
	   });
	   $(this.get('tablaInputsID') + ' input.checkNumeric').each(function() {
		   if(isEmpty($(this).val())){
	   		  todoOk = false;
	   		  doc.mostrarError("Complete todos los campos por favor");
	   		  return false;
		   }else if(!isNumeric($(this).val())){
	   		  todoOk = false;
	   		  doc.mostrarError("Por favor ingrese un valor numerico para las cantidades");
	   		  return false;
	   	   }else if($(this).val()<1){
	   		  todoOk = false;
	   		  doc.mostrarError("Por favor ingrese una cantidad mayor a cero");
	   		  return false;
	   	   }
	   });
	   
	   if(todoOk) $(this.get('errorDivID')).hide();
	   
	   return (todoOk);
	},
	mostrarError: function(errorMsg){
		$(this.get('errorLabelID')).html(errorMsg);
 		$(this.get('errorDivID')).show();
	},
	render: function(){
		$(this.get('totalTdID')).html(this.get('precio'));
		$(this.get('descuentoTdID')).html(this.get('descuento'));
		$(this.get('totalConDescuentoTdID')).html(this.get('precioConDescuento'));
		$(this.get('tablaID')).empty();
		for(var i = 0; i < this.get('items').length; i++){
		    $(this.get('tablaID')).append(this.get('items').at(i).render());
        }
		$(this.get('divID')).show();
	}
});