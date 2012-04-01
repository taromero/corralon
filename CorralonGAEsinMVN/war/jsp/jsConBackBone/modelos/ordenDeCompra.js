OrdenDeCompra = Documento.extend({
	defaults: {
		precio: 0
	},
	agregarItemNuevo: function(material, cantidadMat){
		if(material.get('largo')){
    		var item = new ItemMaderaOC({material: material, cantidad: cantidadMat, documento: 'ordenDeCompra'});
    	}else if(material.get('unidad')){
    		var item = new ItemConUnidad({material: material, cantidad: cantidadMat, documento: 'ordenDeCompra'});
    	}else{
    		var item = new Item({material: material, cantidad: cantidadMat, documento: 'ordenDeCompra'});
    	}
		this.get('items').add(item);
	},
	quitarItem: function(cid){
		var item = this.get('items').getByCid(cid);
    	if(item.get('material').get('largo')){
    		quitarMaderaDeSesion(item.get('material').get('descripcion'));
    	}
    	this.get('items').remove(item);
	}
});