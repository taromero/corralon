package ar.com.seminario.corralon.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.seminario.corralon.dao.ClienteDAO;
import ar.com.seminario.corralon.domain.Cliente;
import ar.com.seminario.corralon.services.ClienteService;

@Service("clienteService")
public class ClienteServiceImpl implements ClienteService{
	
	@Autowired
	private ClienteDAO clienteDAO;
	
	
	@Override
	public Cliente findByDNI(Long dni) {
		return clienteDAO.findClienteByDNI(dni);
	}


	@Override
	public void delete(Long id) {
		clienteDAO.delete(id);
		
	}


	@Override
	public List<Cliente> findAll() {
		return clienteDAO.findAll();
	}


	@Override
	public Cliente findByID(Long id) {
		return clienteDAO.findByID(id);
	}


	@Override
	public void persist(Cliente entity) {
		clienteDAO.persist(entity);
	}


	@Override
	public void merge(Cliente entity) {
		clienteDAO.merge(entity);
	}


	@Override
	public List<String> getListadoClientes() {
		List<Cliente> clientes = clienteDAO.findAll();
		List<String> listadoClientes = new ArrayList<String>();
		for(Cliente cliente : clientes){
			listadoClientes.add(cliente.getApellido() + ", " + cliente.getNombre() + " - " + cliente.getDni());
		}
		return listadoClientes;
	}
	
}
