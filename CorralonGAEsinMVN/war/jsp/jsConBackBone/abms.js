function saveOrUpdateUsuario(){
	$.ajax({  
		url: 'saveOrUpdateUsuario.htm',
		data: ({usuarioDto : generarUsuarioJSON()}),
		success: function(data){
			alert(data);
		},
		error: function (XMLHttpRequest, textStatus, errorThrown, data) {
			$('#errorABMUsuario').html(XMLHttpRequest.responseText);
			$('#errorABMUsuarioDiv').show();
            if(data){
            	alert("Internal Server Error: " + data);
            }
		}
	});
}

function generarUsuarioJSON(){
	json = '{nombre: "' + $('#nombreABMU').val() + '", apellido: "' + $('#apellidoABMU').val() +
	'" , dni: "' + $('#dniABMU').val() + '", clave: "' + $('#claveABMU').val() + 
	'",rol: "' + $('#rolABMU').val() + '"}';
	return json;
}