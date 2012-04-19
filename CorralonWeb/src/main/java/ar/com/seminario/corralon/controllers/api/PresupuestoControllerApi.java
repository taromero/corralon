package ar.com.seminario.corralon.controllers.api;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import ar.com.seminario.corralon.domain.Madera;
import ar.com.seminario.corralon.dtos.UsuarioDTO;
import ar.com.seminario.corralon.exceptions.PresupuestoSinItemsException;
import ar.com.seminario.corralon.exceptions.StockInsuficienteException;
import ar.com.seminario.corralon.exceptions.UsuarioInexistenteException;
import ar.com.seminario.corralon.services.MaterialService;
import ar.com.seminario.corralon.services.PresupuestoService;

@Controller
@SessionAttributes({"usuario"})
public class PresupuestoControllerApi {
	@Autowired
	private PresupuestoService presupuestoService;

	@Autowired
	private MaterialService materialService;
	
	/**
	 * Guarda el presupuesto y elimina las maderas de la sesion. 
	 * Si ocurre algun error devuelve el mensaje correspondiente.
	 */
	@RequestMapping(value = "/presupuestos", method=RequestMethod.POST)
	public @ResponseBody String savePresupuesto(@RequestParam("items") String items, @RequestParam("cliente") String dniCliente, 
													@RequestParam("usuario") String dniUsuario, @ModelAttribute UsuarioDTO user) 
															throws NumberFormatException, UsuarioInexistenteException, 
																	StockInsuficienteException, PresupuestoSinItemsException {
		JSONArray jsonItems = (JSONArray) JSONSerializer.toJSON(items);
		List<String> materiales = new ArrayList<String>();
		List<Integer> cantidades = new ArrayList<Integer>();
		List<Madera> maderas = materialService.obtenerMaderasDeUsuarioEnSesion(user.getIdsMaderasPresupuestandose());
		for (int i = 0; i < jsonItems.size(); i++) {
			JSONObject jobj = (JSONObject) jsonItems.get(i);
			materiales.add(jobj.getJSONObject("material").getString("descripcion"));
			cantidades.add(jobj.getInt("cantidad"));
		}	
		presupuestoService.generarPresupuesto(Long.parseLong(dniUsuario), Long.parseLong(dniCliente), materiales, cantidades, maderas);
		user.getIdsMaderasPresupuestandose().clear();
		return "Presupuesto guardado";
	}
}
