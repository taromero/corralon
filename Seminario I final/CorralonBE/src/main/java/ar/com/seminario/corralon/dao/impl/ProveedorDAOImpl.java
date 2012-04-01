package ar.com.seminario.corralon.dao.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.seminario.corralon.common.dao.impl.SpringHibernateDAOImpl;
import ar.com.seminario.corralon.dao.ProveedorDAO;
import ar.com.seminario.corralon.domain.Proveedor;

@Repository("proveedorDAO")
public class ProveedorDAOImpl extends SpringHibernateDAOImpl<Proveedor>
		implements ProveedorDAO {

	@Override
	public Proveedor findByRazonSocial(String razonSocial) {
		DetachedCriteria criteria = DetachedCriteria.forClass(getPersistentClass());
		criteria.add(Restrictions.eq("razonSocial", razonSocial).ignoreCase());
		
		return (Proveedor)getHibernateTemplate().findByCriteria(criteria).get(0);
	}

}
