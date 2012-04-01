package ar.com.seminario.corralon.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import ar.com.seminario.corralon.domain.Cliente;
import ar.com.seminario.corralon.domain.ItemPresupuesto;
import ar.com.seminario.corralon.domain.MaterialConUnidad;
import ar.com.seminario.corralon.domain.Presupuesto;
import ar.com.seminario.corralon.domain.Usuario;
import ar.com.seminario.corralon.dtos.LoginDTO;
import ar.com.seminario.corralon.exceptions.UsuarioInexistenteException;
import ar.com.seminario.corralon.services.ClienteService;
import ar.com.seminario.corralon.services.LoginService;
import ar.com.seminario.corralon.services.MaterialService;
import ar.com.seminario.corralon.services.PresupuestoService;
import ar.com.seminario.corralon.services.ProveedorService;

@Controller
@SessionAttributes({"usuario"})
public class LoginController{
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ProveedorService proveedorService;
	
	@Autowired
	private PresupuestoService presupuestoService;
	
	@RequestMapping("/login.htm")
	public ModelAndView login(HttpServletRequest req, HttpSession session){
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("loginDto", new LoginDTO());
		
//		this.insertarDatos();
		
		return mav;
		
	}
	
	@RequestMapping("/endLogin.htm")
	public ModelAndView endLogin(@ModelAttribute("loginDto") LoginDTO loginDto, ModelMap modelMap){
		ModelAndView mav = new ModelAndView("welcome");
		String error = "Error de logueo";
		
		this.cargarDatos(mav);
		
		mav.addObject("dniUsuario", loginDto.getAlias());
		
		try {
			Usuario usuario = loginService.validateUser(loginDto);
			modelMap.addAttribute("usuario", usuario);
		} catch (UsuarioInexistenteException e) {
			error =  e.getErrorMessage();
		}
		if(!loginDto.getValidado()){
			mav.setViewName("login");
			mav.addObject("error", error);
		}
		
		return mav;
	}
	
	private void cargarDatos(ModelAndView mav){
		List<String> listM = materialService.findAllNonRepeatedDescriptions();
		
		List<String> listC = clienteService.getListadoClientes();
		
		List<String> listP = proveedorService.getListadoProveedores();
		
		mav.addObject("proveedores", serializarParaVista(listP));
		mav.addObject("materiales", serializarParaVista(listM));
		mav.addObject("clientes", serializarParaVista(listC));
	}
	
	private String serializarParaVista(List<String> list){
		StringBuffer values = new StringBuffer();
		for (int i = 0; i < list.size(); ++i) {
			if (values.length() > 0) {
	        	values.append(',');
	        }
	        values.append('"').append(list.get(i)).append('"');
	     }
		return values.toString();
	}
	
	@SuppressWarnings("unused")
	private void insertarDatos(){
		Usuario user = new Usuario();
		user.setApellido("Romero");
		user.setNombre("Tomas");
		user.setDni(new Long(33444564));
		user.setClave("1578tomas3952");
		
		Cliente cliente = new Cliente();
		cliente.setApellido("Ponce");
		cliente.setNombre("Mauro");
		cliente.setDescuento(0.3);
		cliente.setDireccion("Nazca 1349, Claypole");
		cliente.setEmail("mgp1989@hotmail.com");
		cliente.setIsRegular(false);
		cliente.setTelefono("42910575");
		cliente.setDni(new Long(22333555));
//		clienteService.persist(cliente);
		
//		Cliente cliente = clienteService.findClienteByNombreYApellido("Mauro", "Ponce");
		
		Presupuesto presupuesto1 = new Presupuesto();
		presupuesto1.setCliente(cliente);
		presupuesto1.setUsuario(user);
		presupuesto1.setIsConfirmado(false);
		presupuesto1.setFechaEmision(new Date());
		
		MaterialConUnidad bolsaArena = new MaterialConUnidad();
		bolsaArena.setDescripcion("BOLSON DE ARENA - 510010000011");
		bolsaArena.setUnidad("40 KG");
		bolsaArena.setPrecioVenta(new Double(18.5));
		bolsaArena.setStock(23);
		
//		materialService.persist(bolsaArena);
		
//		Material bolsaArena = materialService.findByDescripcion("Bolsa de arena");
		
		MaterialConUnidad bolsaCal = new MaterialConUnidad();
		bolsaCal.setDescripcion("BOLSON DE PIEDRA PARTIDA - 510020000011");
		bolsaCal.setUnidad("40 KG");
		bolsaCal.setPrecioVenta(new Double(17.05));
		bolsaCal.setStock(50);
		
//		materialService.persist(bolsaCal);
		
		ItemPresupuesto itemPresupuesto1 = new ItemPresupuesto(bolsaArena, 1);
		
		ItemPresupuesto itemPresupuesto2 = new ItemPresupuesto(bolsaCal, 2);
		
		presupuesto1.getItemsPresupuesto().add(itemPresupuesto1);
		presupuesto1.getItemsPresupuesto().add(itemPresupuesto2);
		
		//calculo el total del presupuesto
		double total = 0;
		for(ItemPresupuesto ip : presupuesto1.getItemsPresupuesto()){
			double subtotal =  (ip.getCantidad() * ip.getMaterial().getPrecioVenta());
			total += subtotal;
		}
		presupuesto1.setTotal(total);
		
		
		
		cliente.getPresupuestos().add(presupuesto1);
		presupuestoService.persist(presupuesto1);
//		
//		List<Cliente> clientesList = clienteService.findAll();
//		
//		for(Cliente c : clientesList){
//			
//			Set<Presupuesto> presupuestosSet = c.getPresupuestos();
//			for(Presupuesto p : presupuestosSet){
//				System.out.println("cant presupuestos: " + presupuestosSet.size());
//				System.out.println("Cliente: " + c.getNombre());
//				System.out.println("\tFecha de presupuesto: " + p.getFechaEmision() 
//						+ "\n\tTotal: " + p.getTotal());
//			}
//		}
	}
}