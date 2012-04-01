package ar.com.seminario.corralon.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import ar.com.seminario.corralon.domain.Madera;
import ar.com.seminario.corralon.domain.Usuario;
import ar.com.seminario.corralon.dtos.PrecioUnidadDTO;
import ar.com.seminario.corralon.exceptions.UsuarioInexistenteException;
import ar.com.seminario.corralon.services.FechaService;
import ar.com.seminario.corralon.services.MaterialService;
import ar.com.seminario.corralon.services.OrdenCompraService;

@Controller
@SessionAttributes({"usuario"})
public class GenerarOrdenCompraController {
	
	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private OrdenCompraService ordenCompraService;
	
	@Autowired
	private FechaService fechaService;
	
	
	@RequestMapping(value = "/getMaterialesProveedorAjax.htm")
	public @ResponseBody List<String> getMaterialesProveedorAjax(@RequestParam("prov") String prov) {
		return materialService.findDescriptionsByProveedor(prov);
	}
	
	@RequestMapping(value = "/getPrecioYUnidadOCAjax.htm")
	public @ResponseBody PrecioUnidadDTO getPrecioYUnidadOCAjax(@RequestParam("prov") String prov, @RequestParam("desc") String desc){
		Double precio = materialService.findPrecioMatProveedor(prov, desc);
		String unidad = materialService.obtenerUnidadMatProv(desc, prov);
		PrecioUnidadDTO puDTO = new PrecioUnidadDTO(precio, unidad);
		return puDTO;
	}
	
	@RequestMapping(value = "/saveOrdenDeCompraAjax.htm")
	public @ResponseBody String saveOrdenDeCompraAjax(@RequestParam("items") String items,@RequestParam("proveedor") String razonSocialProveedor, 
									@RequestParam("usuario") String dniUsuario, @RequestParam("fechaEntrega") String fechaEntrega,
										@RequestParam("formaPago") String formaPago, @ModelAttribute Usuario user) {
		JSONArray jsonItems = (JSONArray) JSONSerializer.toJSON(items);
		List<String> materiales = new ArrayList<String>();
		List<Integer> cantidades = new ArrayList<Integer>();
		for (int i = 0; i < jsonItems.size(); i++) {
			JSONObject jobj = (JSONObject) jsonItems.get(i);
			materiales.add(jobj.getJSONObject("material").getString("descripcion"));
			cantidades.add(jobj.getInt("cantidad"));
		}	
		//Paso la fecha de String a Date
		Date date = fechaService.generarFecha(fechaEntrega);
		try {
			ordenCompraService.generarOrdenCompra(Long.parseLong(dniUsuario), razonSocialProveedor, materiales, cantidades, date, formaPago, user.getMaderasParaOC());
			user.getMaderasParaOC().clear();
		}	catch (UsuarioInexistenteException e) {
			return "No se encontro al cliente en la base de datos";
		}
		return "OC guardada";
	}
	
	@RequestMapping(value = "/agregarMaderaASesion.htm")
	public @ResponseBody void agregarMaderaASesion(@ModelAttribute Usuario user,@RequestParam("desc") String desc, 
					@RequestParam("cant") String cantidad,@RequestParam("largo") String largo){
		for(int i = 0; i<Integer.parseInt(cantidad); i++){
			Madera mad = new Madera();
			mad.setDescripcion(desc);
			mad.setLargo(Integer.parseInt(largo));
			user.getMaderasParaOC().add(mad);
		}
	}
	
	@RequestMapping(value = "/quitarMaderaDeSesion.htm")
	public @ResponseBody void quitarMaderaDeSesion(@ModelAttribute Usuario user,@RequestParam("desc") String desc){
		Madera maderaAQuitar = null;
		for(Madera m : user.getMaderasParaOC()){
			if(m.getDescripcion().equals(desc)){
				maderaAQuitar = m;
			}
		}
		if(maderaAQuitar != null){
			user.getMaderasParaOC().remove(maderaAQuitar);
		}
	}
}