package ar.com.seminario.corralon.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import ar.com.seminario.corralon.domain.Usuario;
import ar.com.seminario.corralon.dtos.UsuarioDTO;
import ar.com.seminario.corralon.exceptions.UsuarioInexistenteException;
import ar.com.seminario.corralon.services.UsuarioService;

@Controller
@SessionAttributes({"usuario"})
public class UsuarioControllerApi {

	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping(value="/usuarios/{dni}", method=RequestMethod.GET)
	public @ResponseBody UsuarioDTO getUsuarioByDni(@PathVariable String dni) throws NumberFormatException, UsuarioInexistenteException{
		Usuario usuario = usuarioService.findByDNI(Long.parseLong(dni));
		UsuarioDTO usuarioDTO = new UsuarioDTO(usuario);
		return usuarioDTO;
	}
	
}
