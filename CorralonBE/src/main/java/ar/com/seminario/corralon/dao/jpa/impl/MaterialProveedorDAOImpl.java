package ar.com.seminario.corralon.dao.jpa.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.com.seminario.corralon.common.dao.jpa.impl.SpringJpaDAOImpl;
import ar.com.seminario.corralon.dao.MaterialDAO;
import ar.com.seminario.corralon.dao.MaterialProveedorDAO;
import ar.com.seminario.corralon.dao.ProveedorDAO;
import ar.com.seminario.corralon.domain.Material;
import ar.com.seminario.corralon.domain.MaterialProveedor;
import ar.com.seminario.corralon.domain.Proveedor;

@Repository("materialProveedorDAO")
public class MaterialProveedorDAOImpl extends SpringJpaDAOImpl<MaterialProveedor> 
				implements MaterialProveedorDAO{
	
	@Autowired
	private ProveedorDAO proveedorDAO;
	@Autowired
	private MaterialDAO materialDAO;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MaterialProveedor> findByProveedor(String prov) {
		Proveedor proveedor = proveedorDAO.findByRazonSocial(prov);
		String queryString = "SELECT mp FROM materialProveedor mp" + 
						" WHERE mp.idPoveedor = "+proveedor.getId_proveedor();
		Query query = entityManager.createQuery(queryString);
		List<MaterialProveedor> materiales = (List<MaterialProveedor>) query.getResultList();
		return materiales;
	}
	
	@Override
	public Double findPrecioMatProveedor(String prov, String desc) {
		Proveedor proveedor = proveedorDAO.findByRazonSocial(prov);
		Material material = materialDAO.findMaterialByDescripcion(desc);
		String queryString = "SELECT mp.precio FROM materialProveedor mp " + 
							" WHERE mp.idProveedor = "+proveedor.getId_proveedor()+" AND " +
									" mp.idMaterial = "+material.getId_material();
		Query query = entityManager.createQuery(queryString);
		Double materiales = (Double) query.getSingleResult();
		return materiales;
	}
	
	public List<String> findDescriptionsByProveedor(String prov) {
		List<String> descripciones = new ArrayList<String>();
		List<MaterialProveedor> matsProveedor = this.findByProveedor(prov);
		for(MaterialProveedor matProveedor : matsProveedor){
			descripciones.add(matProveedor.getMaterial().getDescripcion());
		}
		return descripciones;
	}

	@Override
	public MaterialProveedor findByDescripcionYProveedor(String desc,
			String prov) {
		List<MaterialProveedor> matsProveedor = this.findByProveedor(prov);
		for(MaterialProveedor mp : matsProveedor){
			if(mp.getMaterial().getDescripcion().equals(desc)){
				return mp;
			}
		}
		return null;
	}
}
