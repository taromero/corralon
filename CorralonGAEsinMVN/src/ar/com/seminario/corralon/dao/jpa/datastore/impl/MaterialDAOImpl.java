package ar.com.seminario.corralon.dao.jpa.datastore.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.com.seminario.corralon.common.dao.jpa.impl.SpringJpaDAOImpl;
import ar.com.seminario.corralon.dao.MaterialDAO;
import ar.com.seminario.corralon.dao.MaterialProveedorDAO;
import ar.com.seminario.corralon.domain.Madera;
import ar.com.seminario.corralon.domain.Material;
import ar.com.seminario.corralon.domain.MaterialProveedor;

@Repository("materialDAO")
public class MaterialDAOImpl extends SpringJpaDAOImpl<Material> implements
		MaterialDAO {
	
	@Autowired
	private MaterialProveedorDAO matProveedorDAO;
	
	//No devuelve una lista porque no va a haber materiales con el mismo nombre
	@Override
	public Material findMaterialByDescripcion(String descripcionMaterial) {
		String queryString = "SELECT m FROM Material m" + 
							" WHERE m.descripcion = '"+descripcionMaterial+"'";
		Query query = entityManager.createQuery(queryString);
		Material material = (Material) query.getSingleResult();
		return material;
	}
	
	public Madera findMaterialByDescripcionYLargo(String descripcionMaterial, Float largo) {
		String queryString = "SELECT m FROM material m" + 
							" WHERE m.descripcion = '"+descripcionMaterial+"' AND " +
									" m.largo = "+largo;
		Query query = entityManager.createQuery(queryString);
		Madera material = (Madera) query.getSingleResult();
		return material;
	}
	
	public Madera findMaterialByDescripcionLargoYMadre(String descripcionMaterial, Float largo, Long idMadre) {
		String queryString = "SELECT m FROM material m" + 
							" WHERE m.descripcion = '"+descripcionMaterial+"' AND " +
								" m.largo = "+largo+" AND " +
								" m.maderamadre_id_material = "+idMadre;
		Query query = entityManager.createQuery(queryString);
		Madera material = (Madera) query.getSingleResult();
		return material;
	}
	
	@SuppressWarnings("unchecked")
	public List<Madera> obtenerMaderasAdecuadas(String desc , float largo){
		String queryString = "SELECT m FROM material m" + 
							" WHERE m.descripcion = '"+desc+"' AND " +
									" m.largo >= "+largo+" AND " +
									" m.enPresupuesto = false";
		Query query = entityManager.createQuery(queryString);
		List<Madera> maderas = (List<Madera>) query.getResultList();
		return maderas;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int obtenerStockMadera(String desc, float largo) {
		String queryString = "SELECT m FROM material m" + 
								" WHERE m.descripcion = '"+desc+"' AND " +
										" m.largo >= "+largo+" AND " +
										" m.enPresupuesto = false";
		Query query = entityManager.createQuery(queryString);
		List<Madera> maderas = (List<Madera>) query.getResultList();
		return maderas.size();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> findAllNonRepeatedDescriptions(){
		String queryString = "SELECT distinct m.descripcion FROM Material m";
		Query query = entityManager.createQuery(queryString);
		List<String> descripciones = (List<String>) query.getResultList();
		descripciones.size();
		return descripciones;
	}

	@Override
	public List<Material> findByProveedor(String prov) {
		List<MaterialProveedor> mps = matProveedorDAO.findByProveedor(prov);
		List<Material> mats = new ArrayList<Material>();
		for(MaterialProveedor mp : mps){
			Material m = mp.getMaterial();
			m.setPrecioVenta(mp.getPrecio());
			mats.add(m);
		}
		return mats;
	}

	@Override
	public List<Material> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
