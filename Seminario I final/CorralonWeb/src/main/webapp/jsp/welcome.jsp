<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<%@page import="ar.com.seminario.corralon.dtos.LoginDTO"%>

<html>
<head>

<script>
//Paso los datos que vienen del servidor a variables js en el .jsp porque no se puede hacer en los .js
var dniUsuario = ${dniUsuario};
var materialesList = [ ${materiales} ];
var clientesList = [ ${clientes} ];
var proveedoresList = [ ${proveedores} ];
</script>

<style type="text/css">
			/*demo page css*/
			body{ font: 100% "Trebuchet MS", sans-serif; margin-left: 50px; margin-right: 50px}
			.demoHeaders { margin-top: 2em; }
			#dialog_link {padding: .4em 1em .4em 20px;text-decoration: none;position: relative;}
			#dialog_link span.ui-icon {margin: 0 5px 0 0;position: absolute;left: .2em;top: 50%;margin-top: -8px;}
			}
		</style>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Corralon</title>

<link type="text/css" href="jsp/css/JQuery/redmond/jquery-ui-1.8.14.custom.css"
	rel="stylesheet" />
<link rel="stylesheet" href="jsp/css/JQuery/style.css" type="text/css"></link>
<link rel="stylesheet" href="jsp/css/print/presupuesto.css" type="text/css" media="print"></link>
<script type="text/javascript" src="jsp/jquery/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="jsp/jquery/jquery-ui-1.8.14.custom.min.js"></script>
<script type="text/javascript" src="jsp/js/welcome.js"></script>
<script type="text/javascript" src="jsp/js/buscar.js"></script>
<script type="text/javascript" src="jsp/js/generarPresupuesto.js"></script>
<script type="text/javascript" src="jsp/js/generarOrdenDeCompra.js"></script>
<script type="text/javascript" src="jsp/js/validaciones.js"></script>

</head>
<body>

	<div id="tabs" style="height:1000px">
		<ul class="hideOnPrint">
			<li><a href="#tabs-1">Generar Presupuesto</a></li>
			<li><a href="#tabs-2">Generar Orden de Compra</a></li>
			<li><a href="#tabs-3">Buscar</a></li>
		</ul>
		<div id="tabs-1">
			<c:import url="/jsp/welcomePartes/generarPresupuesto.jsp"></c:import>
		</div>
		<div id="tabs-2">
			<c:import url="/jsp/welcomePartes/generarOrdenDeCompra.jsp"></c:import>
		</div>
		<div id="tabs-3">
			<c:import url="/jsp/welcomePartes/buscar.jsp"></c:import>
		</div>
	</div>

</body>
</html>