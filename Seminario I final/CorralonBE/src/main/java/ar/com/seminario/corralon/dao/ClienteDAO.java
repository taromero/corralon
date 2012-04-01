package ar.com.seminario.corralon.dao;

import ar.com.seminario.corralon.common.dao.SpringHibernateDAO;
import ar.com.seminario.corralon.domain.Cliente;

public interface ClienteDAO extends SpringHibernateDAO<Cliente>{

	public Cliente findClienteByNombreYApellido(String nombre, String apellido);
	public Cliente findClienteByDNI(Long dni);

}
