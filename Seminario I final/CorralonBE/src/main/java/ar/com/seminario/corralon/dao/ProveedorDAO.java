package ar.com.seminario.corralon.dao;

import ar.com.seminario.corralon.common.dao.SpringHibernateDAO;
import ar.com.seminario.corralon.domain.Proveedor;

public interface ProveedorDAO extends SpringHibernateDAO<Proveedor> {

	public Proveedor findByRazonSocial(String razonSocial);
}
