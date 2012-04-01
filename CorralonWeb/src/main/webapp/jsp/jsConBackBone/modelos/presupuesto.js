Presupuesto = Documento.extend({
	defaults: {
		precio: 0
	},
	agregarItemNuevo: function(material, cantidadMat){
		if(material.get('largo')){
    		var item = new ItemMadera({material: material, cantidad: cantidadMat, precio: material.get('precio'), documento: 'presupuesto'});
    		item.get('idsMadres').push(material.get('idMadMadre'));
    	}else if(material.get('unidad')){
    		var item = new ItemConUnidad({material: material, cantidad: cantidadMat, documento: 'presupuesto'});
    	}else{
    		var item = new Item({material: material, cantidad: cantidadMat, documento: 'presupuesto'});
    	}
    	this.get('items').add(item);
	},
	quitarItem: function(cid){
		var item = this.get('items').getByCid(cid);
    	if(item.get('idsMadres')){
    		for(var i in item.get('idsMadres')){
    			cancelarCorteAjax(item.get('material').get('descripcion'), item.get('material').get('largo'), item.get('idsMadres')[i]);
    		}
    	}
    	this.get('items').remove(item);
	}
});