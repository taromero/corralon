package ar.com.seminario.corralon;

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

import ar.com.seminario.corralon.domain.Usuario;
import ar.com.seminario.corralon.dtos.LoginDTO;
import ar.com.seminario.corralon.exceptions.UsuarioInexistenteException;
import ar.com.seminario.corralon.services.ClienteService;
import ar.com.seminario.corralon.services.LoginService;
import ar.com.seminario.corralon.services.MaterialService;
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
	
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	
	public void setMaterialService(MaterialService materialService) {
		this.materialService = materialService;
	}
	
	public void setClienteService(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@RequestMapping("/login.htm")
	public ModelAndView login(HttpServletRequest req, HttpSession session){
		ModelAndView mav = new ModelAndView("login");
		mav.addObject("loginDto", new LoginDTO());
		
		return mav;
		
	}
	
	@RequestMapping("/endLogin.htm")
	public ModelAndView endLogin(@ModelAttribute("loginDto") LoginDTO loginDto, ModelMap modelMap){
		ModelAndView mav = new ModelAndView("welcome");
		String error = "Error de logueo";
		try {
			Usuario usuario = loginService.validateUser(loginDto);
			modelMap.addAttribute("usuario", usuario);
		} catch (UsuarioInexistenteException e) {
			error =  e.getErrorMessage();
		}
		//mav.addObject("presupuestoDTO", new PresupuestoDTO());
		
		List<String> listM = materialService.findAllNonRepeatedDescriptions();
		
		List<String> listC = clienteService.getListadoClientes();
		
		List<String> listP = proveedorService.getListadoProveedores();
		
		mav.addObject("proveedores", serializarParaVista(listP));
		mav.addObject("materiales", serializarParaVista(listM));
		mav.addObject("clientes", serializarParaVista(listC));
		//mav.addObject("generarPresupuestoDTO", new GenerarPresupuestoDTO()); //No se usa, ver si lo borro
		mav.addObject("dniUsuario", loginDto.getAlias());
		
		if(!loginDto.getValidado()){
			mav.setViewName("login");
			mav.addObject("error", error);
		}
		
		return mav;
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
}