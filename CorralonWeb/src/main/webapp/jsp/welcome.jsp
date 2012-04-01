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
<link rel="stylesheet" href="jsp/css/dataTables/demo_page.css" type="text/css"></link>
<link rel="stylesheet" href="jsp/css/dataTables/demo_table_jui.css" type="text/css"></link>
<link rel="stylesheet" href="jsp/css/dataTables/demo_table.css" type="text/css"></link>
<script type="text/javascript" src="jsp/jquery/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="jsp/jquery/jquery-ui-1.8.14.custom.min.js"></script>
<script type="text/javascript" src="jsp/jquery/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="jsp/underscore/underscore-1.1.7-min.js"></script>
<script type="text/javascript" src="jsp/backbone/backbone-0.5.1-min.js"></script>
<script type="text/javascript" src="jsp/js/welcome.js"></script>
<script type="text/javascript" src="jsp/jsConBackBone/modelos/material.js"></script>
<script type="text/javascript" src="jsp/jsConBackBone/modelos/item.js"></script>
<script type="text/javascript" src="jsp/jsConBackBone/modelos/itemConUnidad.js"></script>
<script type="text/javascript" src="jsp/jsConBackBone/modelos/itemMadera.js"></script>
<script type="text/javascript" src="jsp/jsConBackBone/modelos/itemMaderaOC.js"></script>
<script type="text/javascript" src="jsp/jsConBackBone/modelos/items.js"></script>
<script type="text/javascript" src="jsp/jsConBackBone/modelos/documento.js"></script>
<script type="text/javascript" src="jsp/jsConBackBone/modelos/presupuesto.js"></script>
<script type="text/javascript" src="jsp/jsConBackBone/modelos/ordenDeCompra.js"></script>
<script type="text/javascript" src="jsp/jsConBackBone/generarPresupuesto.js"></script>
<script type="text/javascript" src="jsp/jsConBackBone/generarOrdenDeCompra.js"></script>
<script type="text/javascript" src="jsp/jsConBackBone/buscar.js"></script>
<!-- <script type="text/javascript" src="jsp/jsConBackBone/abms.js"></script> -->
<script type="text/javascript" src="jsp/jsConBackBone/rest/abms.js"></script>
<script type="text/javascript" src="jsp/js/validaciones.js"></script>


</head>
<body>

	<div id="tabs" style="height:1000px">
		<ul class="hideOnPrint">
			<li><a href="#tabs-1">Generar Presupuesto</a></li>
			<li><a href="#tabs-2">Generar Orden de Compra</a></li>
			<li><a href="#tabs-3">Buscar</a></li>
			<li><a href="#tabs-4">ABM Usuario</a></li>
			<li><a href="#tabs-5">Ver Calendario</a></li>
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
		<div id="tabs-4">
			<c:import url="/jsp/welcomePartes/abms/usuarioABM.jsp"></c:import>
		</div>
		<c:if test="${calendarIFrame != null}">
			<div id="tabs-5">
				${calendarIFrame}
			</div>
		</c:if>
	</div>

</body>
</html>