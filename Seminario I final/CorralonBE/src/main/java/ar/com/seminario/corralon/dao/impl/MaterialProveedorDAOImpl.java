package ar.com.seminario.corralon.dao.impl;

import java.util.List;

import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.seminario.corralon.common.dao.impl.SpringHibernateDAOImpl;
import ar.com.seminario.corralon.dao.MaterialProveedorDAO;
import ar.com.seminario.corralon.domain.Material;
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

	@Override
	public MaterialProveedor findByDescripcion(String descripcion) {
		DetachedCriteria criteria = DetachedCriteria.forClass(MaterialProveedor.class);
		criteria.createAlias("material", "m")
				.add(Restrictions.eq("m.descripcion", descripcion));
		//Obtengo el primero (0) de la lista
		
		criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY); //Si no pongo esto,duplica resultados
		
		return (MaterialProveedor)super.getHibernateTemplate().findByCriteria(criteria).get(0);
	}
}
