function saveOrUpdateUsuario() {
	$.ajax({
		url : 'saveOrUpdateUsuario.htm',
		data : ({
			usuarioDto : generarUsuarioJSON()
		}),
		success : function(data) {
			alert(data);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown, data) {
			$('#errorABMUsuario').html(XMLHttpRequest.responseText);
			$('#errorABMUsuarioDiv').show();
			if (data) {
				alert("Internal Server Error: " + data);
			}
		}
	});
}

function findUsuariobyDni(){
	$( "#cargandoDialog" ).dialog('open');
	$.ajax({
		  type: 'GET',
		  url: "rest/usuarios/" + $('#dniABMU').val(),
		  data: "",
		  contentType : "application/json",
		  success: function(usuario){
			  $('#nombreABMU').val(usuario.nombre);
			  sessionStorage.setItem("usuario" + usuario.dni, JSON.stringify(usuario));
			  $( "#cargandoDialog" ).dialog('close');
		  },
		});
}

function generarUsuarioJSON() {
	json = '{nombre: "' + $('#nombreABMU').val() + '", apellido: "'
			+ $('#apellidoABMU').val() + '" , dni: "' + $('#dniABMU').val()
			+ '", clave: "' + $('#claveABMU').val() + '",rol: "'
			+ $('#rolABMU').val() + '", resourceOwner: "'
			+ $('#resourceOwnerABMU').val() + '", externalId: "'
			+ $('#externalIdABMU').val() + "\", calendarIFrame: '"
			+ $('#calendarIFrameABMU').val() + "', calendarId: '" 
			+ $('#calendarIdABMU').val()+ "'}";
	return json;
}