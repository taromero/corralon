<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<div id="errorBusquedaDiv" align="center" class="ui-widget-header errorDiv">
		<label id="errorBusqueda"></label>
	</div>
	<div id="radioBuscar" align="center" class="hideOnPrint">
		<input type="radio" id="radioPresupuesto" name="radio3" value="presupuesto" class="marcarDeEntrada"/><label for="radioPresupuesto">Presupuesto</label>
		<input type="radio" id="radioOC" name="radio3" value="ordenDeCompra"/><label for="radioOC">Orden de Compra</label>
	</div>
	<table align="center" id="tablaInputsBusqueda" class="hideOnPrint">
		<tr>
			<td>Codigo</td>
			<td><input id="codigo3" class="checkNumeric""></td>
		</tr>
		<tr>
			<td>Fecha generacion</td>
			<td><input id="fecha3" class="datepicker notEmpty"></td>
		</tr>
		<tr id="proveedorBuscar" style="display:none">
			<td>Proveedor</td>
			<td><input id="proveedor3" class="autocompleteProveedores notEmpty"></td>
		</tr>
		<tr id="clienteBuscar">
			<td>Cliente</td>
			<td><input id="cliente3" class="autocompleteClientes notEmpty"></td>
		</tr>
	</table>
	<div align="center" class="hideOnPrint">
		<input type="button" id="buscar3" value="Buscar" onclick="buscar()" class="ui-button ui-state-default ui-corner-all"/>
	</div>
	<div id='resultado' style="display:none">
		<div style="width: 100%; margin-left: 15%">
			<div class="horizontal">
					<table align="center" class="rounded-corner" style="margin-top: 0px">
						<thead>
							<tr id="headers3">
								<th class="rounded-left" id='headerMaterial3'>Material </th>
								<th>Cantidad</th>
								<th class="rounded-right">Subtotal</th>
							</tr>
						</thead>
						<tbody id="contenidoTablaBuscar">
							<tr>
								<td colspan="2" align="center"><b>Total</b></td>
								<td id="totalSdBuscar"></td>
							</tr>
							<tr class="presupuesto">
								<td colspan="2" align="center"><em>descuento%</em></td>
								<td id="descuentoPBuscar"><em></em></td>
							</tr>
							<tr class="presupuesto">
								<td colspan="2" align="center"><b>Total</b><em>(con descuento)</em></td>
								<td id="totalCdPBuscar" style="background-color: FFF000"></td>
							</tr>
						</tbody>
					</table>
			</div>
			<div class="horizontal presupuesto">
				<table align="center" class="rounded-corner" style="margin-top: 0px">
					<tr><td>Confirmado:</td><td id="confirmadoP"></td></tr>
					<tr><td>Nro. Presupuesto:</td><td id="nroPBuscar"></td></tr>
					<tr><td>Cliente:</td><td id="clientePBuscar"></td></tr>
					<tr><td>Fecha de Emisión:</td><td id="emisionPBuscar"></td></tr>
					<tr><td>Validez:</td><td>7 días</td></tr>
					<tr><td>Creador:</td><td id="creadorPBuscar"></td></tr>		
					<tr><td>Total:</td><td id="totalPBuscar"></td></tr>
				</table>
			</div>
			<div class="horizontal ordenCompra">
				<table align="center" class="rounded-corner" style="margin-top: 0px">
					<tr><td>Nro. Orden De Compra:</td><td id="nroOCBuscar"></td></tr>
					<tr><td>Proveedor:</td><td id="proveedorOCBuscar"></td></tr>
					<tr><td>Fecha de Emisión:</td><td id="emisionOCBuscar"></td></tr>
					<tr><td>Fecha de Entrega:</td><td id="entregaOCBuscar"></td></tr>
					<tr><td>Forma Pago:</td><td id="formaPagoOCBuscar"></td></tr>
					<tr><td>Creador:</td><td id="creadorOCBuscar"></td></tr>		
					<tr><td>Total:</td><td id="totalOCBuscar"></td></tr>
				</table>
			</div>
		</div>
		<div align="center" class="hideOnPrint">
			<input type="button" id="confirmar3" value="Confirmar" onclick="confirmarPresupuestoAjax()" class="presupuesto ui-button ui-state-default ui-corner-all">
			<input type="button" id="rechazar3" value="Rechazar" onclick="rechazarPresupuestoAjax()" class="presupuesto ui-button ui-state-default ui-corner-all">
			<input type="button" id="imprimir1" value="Imprimir" onclick="imprimir()" class="ui-button ui-state-default ui-corner-all">
		</div>
	</div>
	<div id="modalPresupuestosOC" title="Seleccione el presupuesto" style="display:none">
	<table align="center" id="tablaDialog3" class="rounded-corner">
		<thead>
			<tr>
				<th class="rounded-left">Fecha </th>
				<th class="presupuesto">Cliente</th>
				<th class="ordenCompra">Proveedor</th>
				<th class="rounded-right">Monto</th>
			</tr>
		</thead>
		<tbody id='tablaModalPresupuestosOC'>
		</tbody>
		</table>
	</div>