package ar.com.seminario.corralon.dao;

import java.util.List;

import ar.com.seminario.corralon.common.dao.GenericDAO;
import ar.com.seminario.corralon.domain.Cliente;

public interface ClienteDAO extends GenericDAO<Cliente>{

	public Cliente findClienteByNombreYApellido(String nombre, String apellido);
	public Cliente findClienteByDNI(Long dni);
	public List<Cliente> findAll();

}
