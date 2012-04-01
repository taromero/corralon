package ar.com.seminario.corralon.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.seminario.corralon.domain.Usuario;
import ar.com.seminario.corralon.exceptions.UsuarioInexistenteException;
import ar.com.seminario.corralon.services.UsuarioService;

@Controller
public class ABMController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@RequestMapping("/saveOrUpdateUsuario.htm")
	public @ResponseBody String saveOrUpdateUsuario(@RequestParam("usuarioDto") String usuarioJson, HttpServletRequest req, HttpSession session){
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
