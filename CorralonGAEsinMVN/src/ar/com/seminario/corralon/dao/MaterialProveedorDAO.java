package ar.com.seminario.corralon.dao;

import java.util.List;

import ar.com.seminario.corralon.common.dao.GenericDAO;
import ar.com.seminario.corralon.domain.MaterialProveedor;

public interface MaterialProveedorDAO extends GenericDAO<MaterialProveedor>{
	
	public List<MaterialProveedor> findByProveedor(String prov);
	
	public Double findPrecioMatProveedor(String prov, String desc);
	
	public List<String> findDescriptionsByProveedor(String prov);
	
	public MaterialProveedor findByDescripcionYProveedor(String desc, String prov);
	
	public List<MaterialProveedor> findAll();
}
