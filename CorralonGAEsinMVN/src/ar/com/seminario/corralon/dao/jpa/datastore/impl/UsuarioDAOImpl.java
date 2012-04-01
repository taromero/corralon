package ar.com.seminario.corralon.dao.jpa.datastore.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ar.com.seminario.corralon.common.dao.jpa.impl.SpringJpaDAOImpl;
import ar.com.seminario.corralon.dao.UsuarioDAO;
import ar.com.seminario.corralon.domain.Usuario;
import ar.com.seminario.corralon.exceptions.UsuarioInexistenteException;

@Repository("usuarioDAO")
public class UsuarioDAOImpl extends SpringJpaDAOImpl<Usuario> implements UsuarioDAO{

	@SuppressWarnings("unchecked")
	@Override
	public Usuario findByDNI(Long dni) throws UsuarioInexistenteException {
		String queryString = "SELECT u from Usuario u where u.dni = " + dni;
		Query query = entityManager.createQuery(queryString);
		List<Usuario> usuarios = (List<Usuario>) query.getResultList();
		
		if(usuarios.isEmpty()){
			throw new UsuarioInexistenteException();
		}
		
		return usuarios.get(0);
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Usuario findByName(String name) throws UsuarioInexistenteException {
		String queryString = "SELECT u from Usuario u where u.nombre = '" + name + "'";
		Query query = entityManager.createQuery(queryString);
		List<Usuario> usuarios = (List<Usuario>) query.getResultList();
		
		if(usuarios.isEmpty()){
			throw new UsuarioInexistenteException();
		}
		
		return usuarios.get(0);
	} 
	
}
