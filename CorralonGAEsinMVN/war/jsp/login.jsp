<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>

<link type="text/css"
	href="jsp/css/JQuery/redmond/jquery-ui-1.8.14.custom.css"
	rel="stylesheet" />

<script type="text/javascript" src="jsp/jquery/jquery-1.5.1.min.js"></script>
<script type="text/javascript"
	src="jsp/jquery/jquery-ui-1.8.14.custom.min.js"></script>

<style type="text/css">
/*demo page css*/
body {
	font: 100% "Trebuchet MS", sans-serif;
	margin: 50px;
	margin-left: 30%;
	background-color: #FCFDFD;
	width: 35%
}

.demoHeaders {
	margin-top: 2em;
}

#dialog_link {
	padding: .4em 1em .4em 20px;
	text-decoration: none;
	position: relative;
}

#dialog_link span.ui-icon {
	margin: 0 5px 0 0;
	position: absolute;
	left: .2em;
	top: 50%;
	margin-top: -8px;
}
}
</style>

<link type="text/css"
	href="jsp/css/JQuery/redmond/jquery-ui-1.8.6.custom.css"
	rel="stylesheet" />
<link rel="stylesheet" href="jsp/css/JQuery/style.css" type="text/css"></link>
<script type="text/javascript" src="jsp/js/validaciones.js"></script>

</head>
<body>
	<div>
		<form:form action="endLogin.htm" commandName="loginDto" id="loginform"
			onsubmit="return validateLogin()">
			<div id="underline" align="center" class="ui-widget-header">
				<h1 class="underline">CorralonWeb</h1>
				<label id="errorLogin">${error}</label>
			</div>
			<div class="ui-widget-content" align="center"
				style="padding-bottom: 20px;">
				<table cellpadding="2" cellspacing="2" border="0" align="center"
					style="padding-top: 20px;">
					<tr>
						<td><form:label path="alias">DNI:</form:label></td>
						<td><form:input path="alias" id="alias1234"
								cssClass="ui-widget ui-state-hover checkNumeric" maxlength="15"
								size="15" /></td>
					</tr>
					<tr>
						<td><form:label path="password">Clave:</form:label></td>
						<td><form:password path="password"
								cssClass="ui-widget ui-state-hover" id="psw" maxlength="15"
								size="15" /></td>
					</tr>
				</table>
				<div align="center">
					<input type="submit" value="login"
						class="ui-button ui-state-default ui-corner-all" />
				</div>
			</div>
			<div>
				<a
					href="https://accounts.google.com/o/oauth2/auth?client_id=209353058543.apps.googleusercontent.com&redirect_uri=https://corralonpresupuestos.appspot.com/endLoginGoogleOauth.htm&scope=https://www.google.com/m8/feeds/&response_type=code">
					Loguearse con cuenta de Google </a>
			</div>
		</form:form>
	</div>
</body>
</html>