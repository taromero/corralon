
<br>
	<div id="errorOCDiv" align="center" class="ui-widget-header errorDiv">
  			<label id="errorOC"></label>
	</div>
	<table align="center" id="tablaInputsOC">
		<tr>
			<td>Proveedor :</td>
			<td><input id="proveedor" class="autocompleteProveedores notEmpty"></td>
			<td><input type="button" value="Seleccionar" id="seleccionarProveedor2" onclick="getMaterialesProveedorAjax()" class="ui-button ui-state-default ui-corner-all"/></td>
		</tr>
		<tr>
			<td>Fecha de Emision :</td>
			<td><input id="datepicker" class="datepicker"/></td>
		</tr>
		<tr>
			<td>Material :</td>
			<td><input id="materialesProveedor" class="notEmpty" size="90"></td>
		</tr>
		<tr>
			<td>Cantidad :</td>
			<td> <input id="cantidad2" class="checkNumeric">
		</tr>
		<tr id="metrosLineales2" style='display:none'>
				<td><input id="unidad2" size="3"/></td>
				<td id="unidadMadera2">Metros lineales</td>
		</tr>
		<tr>
			<td>Forma de Pago :</td>
			<td><select id="formaPago" class="ui-state-default">
				<option value="cheque"> Cheque </option>
				<option value="cuentaCorriente"> Cuenta Corriente </option>
			</select></td>
		</tr>
		<tr>
			<td>Fecha de Entrega :</td>
			<td><input id="fechaEntrega" class="datepicker notEmpty"/></td>
		</tr>
	</table>
	<div align="center">
			<input type="button" id="agregar2" value="Agregar" onclick="verificarMaterialOC()" class="ui-button ui-state-default ui-corner-all"/>
	</div>
	
	<div style="width: 100%; margin-left: 15%; display:none" id="ordenDeCompraDiv">
			<div id="totales2" class="horizontal" style="margin-top: 0px">
				<table align="center" class="rounded-corner">
						<tr>
							<td colspan="2" align="center"><b>Total</b></td>
							<td id="total2">0</td>
						</tr>
				</table>
			</div>
			<div id="lista2" class="horizontal">
				<table align="center" class="rounded-corner">
					<thead>
						<tr id='headers2'>
							<th class="rounded-left" id='headerMaterial2'>Unidad </th>
							<th>Material</th>
							<th>Cantidad</th>
							<th class="rounded-right">Subtotal</th>
						</tr>
					</thead>
					<tbody id="contenidoTablaGenerarOC">
					</tbody>
				</table>
			</div>
		</div>
		
		<div align="center">
			<input type="button" id="guardar2" value="Guardar" onclick="saveOC()" class="ui-button ui-state-default ui-corner-all"/>
		</div>