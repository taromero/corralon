package ar.com.seminario.corralon.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.seminario.corralon.dao.UsuarioDAO;
import ar.com.seminario.corralon.domain.Usuario;
import ar.com.seminario.corralon.exceptions.UsuarioInexistenteException;
import ar.com.seminario.corralon.services.UsuarioService;

@Service("usuarioService")
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	@Override
	public Usuario findByDNI(Long dni) throws UsuarioInexistenteException {
		return usuarioDAO.findByDNI(dni);
	}
	
	@Override
	public void delete(Long id) {
		usuarioDAO.delete(id);
	}

	@Override
	public List<Usuario> findAll() {
		return usuarioDAO.findAll();
	}

	@Override
	public Usuario findByID(Long id) {
		return usuarioDAO.findByID(id);
	}

	@Override
	public void persist(Usuario entity) {
		usuarioDAO.persist(entity);
	}

	@Override
	public void merge(Usuario entity) {
		usuarioDAO.merge(entity);
	}

}
