package ar.com.seminario.corralon.dao;

import ar.com.seminario.corralon.common.dao.SpringHibernateDAO;
import ar.com.seminario.corralon.domain.Usuario;
import ar.com.seminario.corralon.exceptions.UsuarioInexistenteException;

public interface UsuarioDAO extends SpringHibernateDAO<Usuario>{
	public Usuario findByDNI(Long dni) throws UsuarioInexistenteException;
}
