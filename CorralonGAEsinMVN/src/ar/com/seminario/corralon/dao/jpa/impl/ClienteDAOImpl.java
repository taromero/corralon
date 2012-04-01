package ar.com.seminario.corralon.dao.jpa.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ar.com.seminario.corralon.common.dao.jpa.impl.SpringJpaDAOImpl;
import ar.com.seminario.corralon.dao.ClienteDAO;
import ar.com.seminario.corralon.domain.Cliente;

@Repository("clienteDAO")
public class ClienteDAOImpl extends SpringJpaDAOImpl<Cliente> implements
		ClienteDAO {
	
	@Override
	public List<Cliente> findAll() {
		return super.findAll();
	}

	@Override
	public Cliente findClienteByNombreYApellido(String nombre, String apellido) {
		String queryString = "SELECT c FROM Cliente c" + 
							" WHERE c.nombre = '"+nombre+"' AND c.apellido = '"+apellido+"'";
		Query query = entityManager.createQuery(queryString);
		Cliente cliente = (Cliente) query.getSingleResult();
		return cliente;
	}

	@Override
	public Cliente findClienteByDNI(Long dni) {
		String queryString = "SELECT c FROM Cliente c " + 
							" WHERE c.dni = "+dni;
		Query query = entityManager.createQuery(queryString);
		Cliente cliente = (Cliente) query.getSingleResult();
		return cliente; 
	}
	
}
