	ItemMadera = Backbone.Model.extend({
		defaults: {
			idsMadres: new Array()
		},
		sumarCantidad: function(cant, precio){
			this.set({cantidad: this.get('cantidad') + cant});
			var idsMadres = this.get('idsMadres');
			var agregarIdMadre = true;
			for(var i in idsMadres){
				if(this.idsMadres[i] == idMadMadre){
					agregarIdMadre = false;
				}
			}
			if(agregarIdMadre){
				this.get('idsMadres').push(idMadMadre);
	    		this.set({precio: (this.get('precio') + precio).toFixed(2)});
			}
		},
		//No devuelve nada porque no quiero que le llegue este pedido al servidor, porque ya los tiene registrados
		generateJSON: function(){
			return '';
		},
		render: function(){
			var template = '<tr>' +
								'<td>' + this.get('material').get('largo') + ' ' + this.get('material').get('unidad') + '</td>' +
								'<td>' + this.get('material').get('nombre') + '</td>' + 
								'<td>' + this.get('cantidad') + '</td>' +   
								'<td>' + this.get('precio') + '</td>' + 
								'<td>' + 
									'<a href="javascript:' + this.get('documento') + '.quitarItem(' + "'" + this.cid + "'" +')" class="hideOnPrint"><em>(quitar)</em></a>' + 
								'</td>' + 
							'</tr>';
			return template;
		}
	});