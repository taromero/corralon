package ar.com.seminario.corralon.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.seminario.corralon.domain.OrdenCompra;
import ar.com.seminario.corralon.domain.Presupuesto;
import ar.com.seminario.corralon.dtos.PresupuestoDTO;
import ar.com.seminario.corralon.exceptions.PresupuestosNoEncontradosException;
import ar.com.seminario.corralon.exceptions.UsuarioInexistenteException;
import ar.com.seminario.corralon.services.FechaService;
import ar.com.seminario.corralon.services.OrdenCompraService;
import ar.com.seminario.corralon.services.PresupuestoService;

@Controller
public class SearchController {
	@Autowired
	private PresupuestoService presupuestoService;
	
	@Autowired
	private OrdenCompraService ordenCompraService;
	
	@Autowired
	private FechaService fechaService;
	
	//Ajax
	@RequestMapping(value = "/buscarPresupuestoAjax.htm")
	public @ResponseBody PresupuestoDTO buscarPresupuesto(@RequestParam String idPresupuesto) throws UsuarioInexistenteException{
		Presupuesto presupuesto = presupuestoService.findByID(Long.parseLong(idPresupuesto));
		
		if(presupuesto == null){
			throw new UsuarioInexistenteException();
		}
		
		PresupuestoDTO presupuestoDTO = new PresupuestoDTO(presupuesto);
		return presupuestoDTO;
	}
	
	@RequestMapping(value = "/buscarPresupuestosAjax.htm")
	public @ResponseBody List<Presupuesto> buscarPresupuestos(@RequestParam String fecha, @RequestParam String cliente, HttpServletResponse response) throws PresupuestosNoEncontradosException{
		Date f = null;
		if(fecha != ""){
			f = fechaService.generarFecha(fecha);
		}
		return presupuestoService.findByFechaAndCliente(f, cliente);
	}
	
	@RequestMapping(value = "/buscarOrdenCompraAjax.htm")
	public @ResponseBody OrdenCompra buscarOrdenCompra(@RequestParam String idOC) {
		return ordenCompraService.findByID(Long.parseLong(idOC));
	}
	
	@RequestMapping(value = "/buscarOrdenesCompraAjax.htm")
	public @ResponseBody List<OrdenCompra> buscarOrdenesCompraAjax(@RequestParam String fecha, @RequestParam String proveedor){
		Date f = null;
		if(fecha != ""){
			f = fechaService.generarFecha(fecha);
		}
		return ordenCompraService.findByFechaAndProveedor(f, proveedor);
	}
	
	@RequestMapping(value = "/confirmarPresupuestoAjax.htm")
	public @ResponseBody void confirmarPresupuesto(@RequestParam String idPresupuesto) {
		presupuestoService.confirmarPresupuesto(Long.parseLong(idPresupuesto));
	}
	
	@RequestMapping(value = "/rechazarPresupuestoAjax.htm")
	public @ResponseBody void rechazarPresupuesto(@RequestParam String idPresupuesto) {
		presupuestoService.eliminarPresupuesto(Long.parseLong(idPresupuesto));
	}
	
//	@ExceptionHandler(Exception.class)
//	public @ResponseBody String handleException(Exception e, HttpServletResponse response) {
//	    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//	    return e.getMessage();
//	}
}
