package ar.com.seminario.corralon.dao;

import java.util.List;

import ar.com.seminario.corralon.common.dao.GenericDAO;
import ar.com.seminario.corralon.domain.Proveedor;

public interface ProveedorDAO extends GenericDAO<Proveedor> {

	public Proveedor findByRazonSocial(String razonSocial);
	public List<Proveedor> findAll();
}
