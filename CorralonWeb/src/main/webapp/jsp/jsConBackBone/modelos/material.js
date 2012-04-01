	Material = Backbone.Model.extend({
		initialize: function(){
			this.bind('change:descripcion', function(){
				nombre: cortarNombreMaterialParaLista(this.descripcion);
			});
		}
	});