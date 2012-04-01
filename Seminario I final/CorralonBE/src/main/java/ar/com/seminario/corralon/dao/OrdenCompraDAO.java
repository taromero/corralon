package ar.com.seminario.corralon.dao;

import java.util.Date;
import java.util.List;

import ar.com.seminario.corralon.common.dao.SpringHibernateDAO;
import ar.com.seminario.corralon.domain.OrdenCompra;

public interface OrdenCompraDAO extends SpringHibernateDAO<OrdenCompra>{
	public List<OrdenCompra> findByFechaAndProveedor(Date f , String proveedor);
}
