package ar.com.seminario.corralon.controllers.api;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@RequestMapping(value="/usuarios", method={RequestMethod.POST, RequestMethod.PUT})
	public @ResponseBody String saveOrUpdateUsuario(@RequestParam("usuarioDto") String usuarioJson){
		JSONObject usuarioJsonAux = (JSONObject) JSONSerializer.toJSON(usuarioJson);
		Usuario usuarioDto = new Usuario();
		usuarioDto.setNombre(usuarioJsonAux.getString("nombre"));
		usuarioDto.setApellido(usuarioJsonAux.getString("apellido"));
		usuarioDto.setClave(usuarioJsonAux.getString("clave"));
		usuarioDto.setDni(usuarioJsonAux.getLong("dni"));
		usuarioDto.setRol(usuarioJsonAux.getString("rol"));
		
		try {
			Usuario usuario = usuarioService.findByDNI(usuarioDto.getDni());
			usuario = usuarioDto;
			usuarioService.merge(usuario);
			return "Datos del usuario actualizados";
		} catch (UsuarioInexistenteException e) {
			usuarioService.persist(usuarioDto);
			return "Usuario guardado";
		}
	}
}
