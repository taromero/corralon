package ar.com.seminario.corralon.dao;

import java.util.List;

import ar.com.seminario.corralon.common.dao.SpringHibernateDAO;
import ar.com.seminario.corralon.domain.MaterialProveedor;

public interface MaterialProveedorDAO extends SpringHibernateDAO<MaterialProveedor>{
	
	public List<MaterialProveedor> findByProveedor(String prov);
	
	public Double findPrecioMatProveedor(String prov, String desc);
	
	public MaterialProveedor findByDescripcion(String descripcion);
	
}
