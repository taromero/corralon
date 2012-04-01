package ar.com.seminario.corralon.dao.hibernate.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.seminario.corralon.common.dao.hibernate.impl.SpringHibernateDAOImpl;
import ar.com.seminario.corralon.dao.MaterialProveedorDAO;
import ar.com.seminario.corralon.domain.MaterialProveedor;

@Repository("materialProveedorDAO")
public class MaterialProveedorDAOImpl extends SpringHibernateDAOImpl<MaterialProveedor> 
				implements MaterialProveedorDAO{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MaterialProveedor> findByProveedor(String prov) {
		DetachedCriteria criteria = DetachedCriteria.forClass(MaterialProveedor.class);
		criteria.createCriteria("proveedor").add( Restrictions.eq("razonSocial", prov));
		
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY); //Si no pongo esto,duplica resultados
		
		return (List<MaterialProveedor>)super.getHibernateTemplate().findByCriteria(criteria);
	}
	
	@Override
	public Double findPrecioMatProveedor(String prov, String desc) {
		DetachedCriteria criteria = DetachedCriteria.forClass(MaterialProveedor.class);
		criteria.createAlias("material", "m")
				.createAlias("proveedor", "p")
				.add( Restrictions.eq("p.razonSocial", prov))
			    .add( Restrictions.eq("m.descripcion", desc));
		
		MaterialProveedor mp = (MaterialProveedor) super.getHibernateTemplate().findByCriteria(criteria).get(0);
		return mp.getPrecio();
	}

	@SuppressWarnings("unchecked")
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
