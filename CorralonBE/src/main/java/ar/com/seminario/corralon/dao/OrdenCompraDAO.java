package ar.com.seminario.corralon.dao;

import java.util.Date;
import java.util.List;

import ar.com.seminario.corralon.common.dao.GenericDAO;
import ar.com.seminario.corralon.domain.OrdenCompra;

public interface OrdenCompraDAO extends GenericDAO<OrdenCompra>{
	public List<OrdenCompra> findByFechaAndProveedor(Date f , String proveedor);
	
	public List<OrdenCompra> findAll();
}
