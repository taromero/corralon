<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<br>	
		<div id="errorPresupuestoDiv" align="center" class="ui-widget-header errorDiv">
  			<label id="errorPresupuesto" style="color: red;"></label>
		</div>
		<table align="center" id="tablaInputsPresupuesto" style="width:80%">
			<tr>
				<td>Cliente: </td>
				<td>
					<input id="cliente" size="50" class="ui-widget ui-state-hover autocompleteClientes notEmpty"/>
				</td>
				<td><input type="button" id="seleccionarCliente1" value="Seleccionar" onclick="busquedaDescuentoAjax()" class="ui-button ui-state-default ui-corner-all"/></td>
			</tr>
			<tr>
				<td>Material: </td>
				<td>
					<input id="material" size="90" class="autocompleteMateriales notEmpty"/>
				</td>
			</tr>
			<tr>
				<td>Cantidad: </td>
				<td colspan="1">
					<input id="cantidad1" class="checkNumeric"/>
				</td>
				<td id="unidadMat" colspan="3"></td>
			</tr>
			<tr id="metrosLineales1" style='display:none'>
				<td><input id="unidad1" size="3"/></td>
				<td id="unidadMadera">Metros lineales</td>
			</tr>			
		</table>
		<div align="center">
			<input type="button" id="agregar1" value="Agregar" onclick="verificarMaterial()" class="ui-button ui-state-default ui-corner-all"/>
		</div>
		<table align="center" class="hideOnPrint" >
			<tr>
				<td>Tabla de pedidos actuales</td>
			</tr>
		</table>
		<div style="width: 100%; margin-left: 15%; display:none" id="divListado1">
			<div id="totales1" class="horizontal" style="margin-top: 0px">
				<table align="center" class="rounded-corner">
						<tr>
							<td colspan="2" align="center"><b>Total</b></td>
							<td id="total1">0</td>
						</tr>
						<tr>
							<td colspan="2" align="center"><em>descuento%</em></td>
							<td colspan="3" id="descuento1">0</td>
						</tr>
						<tr>
							<td colspan="2" align="center"><b>Total</b><em>(con descuento)</em></td>
							<td id="totalConDescuento1"></td>
						</tr>
				</table>
			</div>
			<div id="lista1" class="horizontal">
				<table align="center" class="rounded-corner">
					<thead>
						<tr id='headers1'>
							<th class="rounded-left" id='headerMaterial1'>Material </th>
							<th>Cantidad</th>
							<th class="rounded-right">Subtotal</th>
						</tr>
					</thead>
					<tbody id="contenidoTablaGenerarP">
					</tbody>
				</table>
			</div>
		</div>
		<div align="center">
			<input type="button" id="guardar1" value="GuardarPresupuesto" onclick="savePresupuesto()" class="ui-button ui-state-default ui-corner-all"/>
			<input type="button" id="cancelar1" value="Cancelar" onclick="cancelarPresupuestoAjax()" class="ui-button ui-state-default ui-corner-all">
		</div>
		<div id="dialog-confirm" title="Desea conservar el sobrante de madera?" style="display:none">
			<p><span class="ui-icon ui-icon-alert" style="float:left;"></span>Si no conserva la madera, se le cobrara el importe por el total de la tabla al cliente</p>
			<table align="center" id="tablaDialog1" class="rounded-corner">
			<thead>
				<tr>
					<th class="rounded-left">Madera </th>
					<th>Largo</th>
					<th>Stock</th>
					<th class="rounded-right">Sobrante</th>
				</tr>
			</thead>
			<tbody id='tablaModalMaderas'>
			</tbody>
			</table>
			<div id="radioCepillar" align="center">
				<input type="radio" id="radioCepillar1" name="radio1" value="true"/><label for="radioCepillar1">Cepillar</label>
				<input type="radio" id="radioCepillar2" name="radio1" class="marcarDeEntrada" value="false"/><label for="radioCepillar2">No cepillar</label>
			</div>
		</div>