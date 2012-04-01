	Item = Backbone.Model.extend({
		initialize: function(){
			this.attributes.precio =  this.calcularPrecio();
			this.bind('change:cantidad', function(){
				this.attributes.precio =  this.calcularPrecio();
				this.render();
			});
		},
		calcularPrecio: function(){
			var precio = (parseInt(this.get('cantidad'), 10) * parseFloat(this.get('material').get('precio'))).toFixed(2);
			return precio;
		},
		sumarCantidad: function(cant){
			this.set({cantidad: parseInt(this.get('cantidad'), 10) + parseInt(cant, 10)});
		},
		generateJSON: function(){
			return JSON.stringify(this);
		},
		render: function(){
			var template = '<tr>' +
								'<td></td>' +
								'<td>' + this.get('material').get('nombre') + '</td>' + 
								'<td>' + this.get('cantidad') + '</td>' +   
								'<td>' + this.get('precio') + '</td>' + 
								'<td>' + 
									'<a href="javascript:' + this.get('documento') + '.sumarUnoAItem(' + "'" + this.cid + "'" +')" class="hideOnPrint"> +</a>' +
									'<a href="javascript:' + this.get('documento') + '.restarUnoAItem(' + "'" + this.cid + "'" +')" class="hideOnPrint"> - </a>' + 
									'<a href="javascript:' + this.get('documento') + '.quitarItem(' + "'" + this.cid + "'" +')" class="hideOnPrint"><em>(quitar)</em></a>' + 
								'</td>' + 
							'</tr>';
			return template;
		}
	});