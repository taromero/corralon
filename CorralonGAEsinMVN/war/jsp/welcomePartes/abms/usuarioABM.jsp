<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form id="usuarioABMForm">
		<div id="errorABMUsuarioDiv" align="center" class="ui-widget-header errorDiv">
  			<label id="errorABMUsuario"></label>
		</div>
		<table cellpadding="2" cellspacing="2" border="0" align="center"
			style="padding-top: 20px;">
			
			<tr>
				<td><label>Nombre:</label></td>
				<td><input id="nombreABMU"
						class="ui-widget ui-state-hover" size="25" /></td>
			</tr>
			<tr>
				<td><label>Apellido:</label></td>
				<td><input id="apellidoABMU"
						class="ui-widget ui-state-hover" size="25" /></td>
			</tr>
			<tr>
				<td><label>DNI:</label></td>
				<td><input id="dniABMU"
						class="ui-widget ui-state-hover" size="25" /></td>
			</tr>
			<tr>
				<td><label>Clave:</label></td>
				<td><input id="claveABMU"
						class="ui-widget ui-state-hover" size="25" /></td>
			</tr>
			<tr>
				<td><label>Rol:</label></td>
				<td>
					<select id="rolABMU">
						<option value="Desarrollador"> Desarrollador </option>
						<option value="Jefe"> Jefe </option>
					</select>
				</td>
			</tr>
		</table>
		<div align="center">
				<input type="button" value="guardar" onclick="saveOrUpdateUsuario()" class="ui-button ui-state-default ui-corner-all"/>
		</div>
	</form>
</body>
</html>