package ar.com.seminario.corralon.dao.jpa.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ar.com.seminario.corralon.common.dao.jpa.impl.SpringJpaDAOImpl;
import ar.com.seminario.corralon.dao.UsuarioDAO;
import ar.com.seminario.corralon.domain.Usuario;
import ar.com.seminario.corralon.exceptions.UsuarioInexistenteException;

@Repository("usuarioDAO")
public class UsuarioDAOImpl extends SpringJpaDAOImpl<Usuario> implements UsuarioDAO{

	@Override
	public Usuario findByDNI(Long dni) throws UsuarioInexistenteException {
		String queryString = "SELECT u from Usuario u where u.dni = " + dni;
		Query query = entityManager.createQuery(queryString);
		Usuario usuario = (Usuario) query.getSingleResult();
		
		if(usuario == null){
			throw new UsuarioInexistenteException();
		}
		
		return usuario;
	}

	@Override
	public Usuario findByName(String name) throws UsuarioInexistenteException {
		String queryString = "SELECT u from Usuario u where u.nombre = '" + name + "'";
		Query query = entityManager.createQuery(queryString);
		Usuario usuario = (Usuario) query.getSingleResult();
		
		if(usuario == null){
			throw new UsuarioInexistenteException();
		}
		
		return usuario;
	} 
	
}
