package ar.com.seminario.corralon.services;

import ar.com.seminario.corralon.domain.Usuario;
import ar.com.seminario.corralon.dtos.LoginDTO;
import ar.com.seminario.corralon.exceptions.UsuarioInexistenteException;

public interface LoginService {
	
	public Usuario validateUser(LoginDTO loginDto) throws UsuarioInexistenteException;
}
