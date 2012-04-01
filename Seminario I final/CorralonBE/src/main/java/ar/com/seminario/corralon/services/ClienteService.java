package ar.com.seminario.corralon.services;

import java.util.List;

import ar.com.seminario.corralon.common.service.GenericService;
import ar.com.seminario.corralon.domain.Cliente;

public interface ClienteService extends GenericService<Cliente> {

	public Cliente findByDNI(Long dni);
	
	/**
	 * Devuelve el listado de clientes, en una lista de Strings. 
	 * En cada String van el nombre, apellido y DNI del cliente
	 * 
	 * @return Listado clientes
	 */
	public List<String>  getListadoClientes();

}