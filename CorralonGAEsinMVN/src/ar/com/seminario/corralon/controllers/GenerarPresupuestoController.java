package ar.com.seminario.corralon.controllers;

import java.util.ArrayList;
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
import ar.com.seminario.corralon.dtos.CorteMaderaDTO;
import ar.com.seminario.corralon.dtos.MaderaDTO;
import ar.com.seminario.corralon.dtos.PrecioUnidadDTO;
import ar.com.seminario.corralon.exceptions.MaderasNoEncontradasException;
import ar.com.seminario.corralon.exceptions.PresupuestoSinItemsException;
import ar.com.seminario.corralon.exceptions.StockInsuficienteException;
import ar.com.seminario.corralon.exceptions.UsuarioInexistenteException;
import ar.com.seminario.corralon.services.ClienteService;
import ar.com.seminario.corralon.services.MaterialService;
import ar.com.seminario.corralon.services.PresupuestoService;

@Controller
@SessionAttributes({"usuario"})
public class GenerarPresupuestoController {
	@Autowired
	private PresupuestoService presupuestoService;

	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private ClienteService clienteService;
	
	/**
	 * Guarda el presupuesto y elimina las maderas de la sesion. 
	 * Si ocurre algun error devuelve el mensaje correspondiente.
	 * @param items
	 * @param dniCliente
	 * @param dniUsuario
	 * @param user
	 * @return
	 * @throws PresupuestoSinItemsException 
	 * @throws StockInsuficienteException 
	 * @throws UsuarioInexistenteException 
	 * @throws NumberFormatException 
	 */
	@RequestMapping(value = "/savePresupuestoAjax.htm")
	public @ResponseBody String savePresupuesto(@RequestParam("items") String items, @RequestParam("cliente") String dniCliente, 
													@RequestParam("usuario") String dniUsuario, @ModelAttribute Usuario user) 
															throws NumberFormatException, UsuarioInexistenteException, StockInsuficienteException, PresupuestoSinItemsException {
		JSONArray jsonItems = (JSONArray) JSONSerializer.toJSON(items);
		List<String> materiales = new ArrayList<String>();
		List<Integer> cantidades = new ArrayList<Integer>();
		List<Madera> maderas = materialService.obtenerMaderasDeUsuarioEnSesion(user);
		for (int i = 0; i < jsonItems.size(); i++) {
			JSONObject jobj = (JSONObject) jsonItems.get(i);
			materiales.add(jobj.getJSONObject("material").getString("descripcion"));
			cantidades.add(jobj.getInt("cantidad"));
		}	
		presupuestoService.generarPresupuesto(Long.parseLong(dniUsuario), Long.parseLong(dniCliente), materiales, cantidades, maderas);
		user.getIdsMaderasPresupuestandose().clear();
		return "Presupuesto guardado";
	}
	
	@RequestMapping(value = "/encontrarMaderasAjax.htm")
	public @ResponseBody List<MaderaDTO> encontrarMaderasAjax(@RequestParam("desc") String desc, @RequestParam("largo") String largo, @RequestParam("cantidad") String cantidad) 
																	throws MaderasNoEncontradasException{
		List<MaderaDTO> lMadDTO = new ArrayList<MaderaDTO>();
		List<Madera> lMad = materialService.obtenerMaderasAdecuadas(desc, Float.parseFloat(largo));
		Integer stock;
		for(Madera m : lMad){
			stock = materialService.obtenerStockMadera(desc, m.getLargo());
			float sobrante = m.getLargo() - Float.parseFloat(largo)*Float.parseFloat(cantidad);
			if (sobrante >= 0){
				MaderaDTO mDTO= new MaderaDTO(m.getDescripcion(), m.getLargo(), stock, sobrante);
				lMadDTO.add(mDTO);
			}
		}
		return lMadDTO;
	}
	
	@RequestMapping(value = "/cortarMaderaAjax.htm")
	public @ResponseBody CorteMaderaDTO cortarMaderaAjax(@RequestParam("desc") String desc, 
										@RequestParam("cant") String cantidadCortesPedidos,@RequestParam("largoMadera") String largoMadera, 
										@RequestParam("largoACortar") String largoACortar, @RequestParam("guardarSobrante") String guardarSobrante, 
										@RequestParam("cepillar") String cepillar, @ModelAttribute Usuario user){
		Madera mad = (Madera) materialService.findByDescripcionYLargo(desc, Float.parseFloat(largoMadera));
		Integer cantidadCortesPosibles = (int) Math.floor(mad.getLargo()/Float.parseFloat(largoACortar));
		Integer cantidadCortesRealizados = 0;
		for(int i = 0; i< cantidadCortesPosibles && i < Integer.parseInt(cantidadCortesPedidos); i++){
			Long idMaderaCortada = materialService.cortarMadera(mad, Float.parseFloat(largoACortar));
			user.getIdsMaderasPresupuestandose().add(idMaderaCortada);
			cantidadCortesRealizados++;
		}
		mad.setEnPresupuesto(!Boolean.parseBoolean(guardarSobrante));
		materialService.merge(mad);
		Double precio = materialService.calcularPrecio(mad.getPrecioVenta(), Double.parseDouble(largoMadera), Double.parseDouble(largoACortar), Boolean.parseBoolean(guardarSobrante), Boolean.parseBoolean(cepillar));
		Long idMadMadre = mad.getId_material().getId();
		
		return new CorteMaderaDTO(idMadMadre, precio, cantidadCortesRealizados);
	}
	
	@RequestMapping(value = "/cancelarCortesAjax.htm")
	public @ResponseBody void cancelarCortesAjax(@ModelAttribute Usuario user){
		for(Long madId : user.getIdsMaderasPresupuestandose()){
			Madera mad = (Madera) materialService.findByID(madId);
			materialService.rearmarMadera(mad);
		}
		user.getIdsMaderasPresupuestandose().clear();
	}
	@RequestMapping(value = "/getPrecioYUnidadAjax.htm")
	public @ResponseBody PrecioUnidadDTO getPrecioYUnidadAjax(@RequestParam("desc") String desc){
		Double precio = materialService.calcularPrecio(desc);
		String unidad = materialService.obtenerUnidad(desc);
		PrecioUnidadDTO puDTO = new PrecioUnidadDTO(precio, unidad);
		return puDTO;
	}
	
	@RequestMapping(value = "/buscarDescuentoAjax.htm")
	public @ResponseBody Double busquedaDescuentoAjax(@RequestParam("dniCliente") String dniCliente){
		return clienteService.findByDNI(Long.parseLong(dniCliente)).getDescuento();
	}
	
	
	@RequestMapping(value = "/cancelarCorteAjax.htm")
	public @ResponseBody void cancelarCorteAjax(@ModelAttribute Usuario user, @RequestParam("desc") String desc, 
													@RequestParam("largo") String largo, @RequestParam("idMadre") String idMadre){
		List<Madera> maderasPresupuestandose = materialService.obtenerMaderasDeUsuarioEnSesion(user);
		for(Madera m : maderasPresupuestandose){
			if(m.getDescripcion().equals(desc) && m.getLargo() == Float.parseFloat(largo) && m.getMaderaMadre().getId_material().getId() == Long.parseLong(idMadre)){
				user.getIdsMaderasPresupuestandose().remove(m.getId_material());//revisar
				materialService.rearmarMadera(m);
				return; //Corto la iteracion
			}
		}
	}
	
//	@ExceptionHandler(Exception.class)
//	public @ResponseBody String handleException(Exception e, HttpServletResponse response) {
//	    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//	    return e.getMessage();
//	}
}
