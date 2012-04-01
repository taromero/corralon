package ar.com.seminario.corralon.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.com.seminario.corralon.common.dao.impl.SpringHibernateDAOImpl;
import ar.com.seminario.corralon.dao.MaterialDAO;
import ar.com.seminario.corralon.dao.MaterialProveedorDAO;
import ar.com.seminario.corralon.domain.Madera;
import ar.com.seminario.corralon.domain.Material;
import ar.com.seminario.corralon.domain.MaterialProveedor;

@Repository("materialDAO")
public class MaterialDAOImpl extends SpringHibernateDAOImpl<Material> implements
		MaterialDAO {

	@Autowired
	private MaterialProveedorDAO matProveedorDAO;
	
	//No devuelve una lista porque no va a haber materiales con el mismo nombre
	@Override
	public Material findMaterialByDescripcion(String descripcionMaterial) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Material.class);
		criteria.add(Restrictions.eq("descripcion", descripcionMaterial));
		//Obtengo el primero (0) de la lista
		return (Material)super.getHibernateTemplate().findByCriteria(criteria).get(0);
	}
	
	public Madera findMaterialByDescripcionYLargo(String descripcionMaterial, Float largo) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Material.class);
		criteria.add(Restrictions.eq("descripcion", descripcionMaterial));
		criteria.add(Restrictions.eq("largo", largo));
		//Obtengo el primero (0) de la lista
		return (Madera)super.getHibernateTemplate().findByCriteria(criteria).get(0);
	}
	
	public Madera findMaterialByDescripcionLargoYMadre(String descripcionMaterial, Float largo, Long idMadre) {
//		DetachedCriteria criteria = DetachedCriteria.forClass(Material.class);
//		criteria.add(Restrictions.eq("descripcion", descripcionMaterial));
//		criteria.add(Restrictions.eq("largo", largo));
//		criteria.add(Restrictions.eq("maderaMadre", idMadre));
//		//Obtengo el primero (0) de la lista
//		return (Madera)super.getHibernateTemplate().findByCriteria(criteria).get(0);
		Query query = getSession().createQuery("SELECT m FROM Madera m WHERE m.descripcion = :descr AND m.largo >= :largo AND maderamadre_id_material = :idMadre")
					.setParameter("descr", descripcionMaterial)
					.setParameter("largo", largo)
					.setParameter("idMadre", idMadre);
		
		return (Madera) query.list().get(0);
	}
	
	@SuppressWarnings("unchecked")
	public List<Madera> obtenerMaderasAdecuadas(String desc , float largo){
		List<Madera> maderas= null;
		
		Query query = getSession().createQuery("SELECT m FROM Madera AS m WHERE m.descripcion = :descr AND m.largo >= :largo AND m.enPresupuesto = :enPresupuesto")	
					.setParameter("descr", desc)
					.setParameter("largo", largo)
					.setParameter("enPresupuesto", false);
		
		maderas = (List<Madera>) query.list();		
		
		return maderas;
	}

	@Override
	public int obtenerStockMadera(String desc, float largo) {
		int stock = 0; 
		
		Query query = getSession().createQuery("SELECT count(m) FROM Madera AS m WHERE m.descripcion = :descr AND m.largo = :largo AND m.enPresupuesto = :enPresupuesto")	
					.setParameter("descr", desc)
					.setParameter("largo", largo)
					.setParameter("enPresupuesto", false);
		
		stock = ((Long)query.uniqueResult()).intValue();
				
		return stock;
	}
	
	//Lo dejo como ejemplo por si hay que hacer algo parecido
//	@SuppressWarnings("unchecked")
//	public List<MaterialDTO> findAllNonRepeatedDescriptions(){
//		DetachedCriteria criteria = DetachedCriteria.forClass(Material.class);
//		criteria.setProjection( Projections.distinct( Projections.projectionList().add(Projections.property( "descripcion" ).as("descripcion")).add(Projections.property("disc").as("disc"))) );
//		
//		criteria.setResultTransformer(Transformers.aliasToBean(MaterialDTO.class));
//		
//		List<MaterialDTO> algo = (List<MaterialDTO>)super.getHibernateTemplate().findByCriteria(criteria);
//		return algo;
//	}
	
	@SuppressWarnings("unchecked")
	public List<String> findAllNonRepeatedDescriptions(){
		DetachedCriteria criteria = DetachedCriteria.forClass(Material.class);
		criteria.setProjection( Projections.distinct( Projections.property( "descripcion" )) );
		
		return (List<String>)super.getHibernateTemplate().findByCriteria(criteria);
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
	public Double findPrecioMatProveedor(String prov, String desc) {
		return matProveedorDAO.findPrecioMatProveedor(prov, desc);
	}


	@Override
	public MaterialProveedor findMatProvByDescripcion(String descripcion) {
		return matProveedorDAO.findByDescripcion(descripcion);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> findDescriptionsByProveedor(String prov) {
		List<String> descripcionesMateriales;
		Query query = getSession().createQuery("SELECT mp.material.descripcion FROM MaterialProveedor AS mp WHERE mp.proveedor.razonSocial = :prov")	
		.setParameter("prov", prov);
		
		descripcionesMateriales = query.list();
		return descripcionesMateriales;
	}

	@Override
	public MaterialProveedor findByDescripcionYProveedor(String desc,
			String prov) {
		MaterialProveedor mp;
		Query query = getSession().createQuery("SELECT mp FROM MaterialProveedor AS mp WHERE mp.proveedor.razonSocial = :prov AND mp.material.descripcion = :desc")	
		.setParameter("prov", prov)
		.setParameter("desc", desc);
		
		mp = (MaterialProveedor) query.list().get(0);
		return mp;
	}
}
