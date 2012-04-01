	ItemConUnidad = Item.extend({
		render: function(){
			var template = '<tr>' +
								'<td>' + this.get('material').get('unidad') + '</td>' +
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