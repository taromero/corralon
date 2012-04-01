package ar.com.seminario.corralon.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.seminario.corralon.dao.UsuarioDAO;
import ar.com.seminario.corralon.domain.Usuario;
import ar.com.seminario.corralon.dtos.LoginDTO;
import ar.com.seminario.corralon.exceptions.UsuarioInexistenteException;
import ar.com.seminario.corralon.services.LoginService;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UsuarioDAO usuarioDAO;

	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	@Override
	public Usuario validateUser(LoginDTO loginDTO) throws UsuarioInexistenteException {
		Usuario usuario;
		loginDTO.setValidado(false);
		usuario = usuarioDAO.findByDNI(loginDTO.getAlias()); // El alias del login es el dni
		if (usuario != null) {
			if (usuario.getClave().equals(loginDTO.getPassword())) {
				loginDTO.setValidado(true);
			}
		}

		return usuario;
	}

}
