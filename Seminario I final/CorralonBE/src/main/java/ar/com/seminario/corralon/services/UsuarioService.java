package ar.com.seminario.corralon.services;

import ar.com.seminario.corralon.common.service.GenericService;
import ar.com.seminario.corralon.domain.Usuario;
import ar.com.seminario.corralon.exceptions.UsuarioInexistenteException;

public interface UsuarioService extends GenericService<Usuario>{
	public Usuario findByDNI(Long dni) throws UsuarioInexistenteException;
}
