package ar.com.seminario.corralon.dao;

import java.util.List;

import ar.com.seminario.corralon.common.dao.GenericDAO;
import ar.com.seminario.corralon.domain.Usuario;
import ar.com.seminario.corralon.exceptions.UsuarioInexistenteException;

public interface UsuarioDAO extends GenericDAO<Usuario>{
	public Usuario findByDNI(Long dni) throws UsuarioInexistenteException;
	
	public List<Usuario> findAll();
	
	public Usuario findByName(String name) throws UsuarioInexistenteException;
	
	public Usuario findByEmail(String email) throws UsuarioInexistenteException;
	
	public Usuario findByExternalId(String externalId) throws UsuarioInexistenteException;
}
