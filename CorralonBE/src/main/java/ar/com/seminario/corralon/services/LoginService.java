package ar.com.seminario.corralon.services;

import ar.com.seminario.corralon.dtos.LoginDTO;
import ar.com.seminario.corralon.dtos.UsuarioDTO;
import ar.com.seminario.corralon.exceptions.UsuarioInexistenteException;

public interface LoginService {
	
	public UsuarioDTO validateUser(LoginDTO loginDto) throws UsuarioInexistenteException;
}
