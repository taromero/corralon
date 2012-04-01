package ar.com.seminario.corralon.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.seminario.corralon.common.dao.impl.SpringHibernateDAOImpl;
import ar.com.seminario.corralon.dao.ClienteDAO;
import ar.com.seminario.corralon.domain.Cliente;

@Repository("clienteDAO")
public class ClienteDAOImpl extends SpringHibernateDAOImpl<Cliente> implements
		ClienteDAO {
	
	@Override
	public List<Cliente> findAll() {
		return super.findAll();
	}

	@Override
	public Cliente findClienteByNombreYApellido(String nombre, String apellido) {
		DetachedCriteria criteria = DetachedCriteria.forClass(getPersistentClass());
		criteria.add(Restrictions.eq("nombre", nombre).ignoreCase());
		criteria.add(Restrictions.eq("apellido", apellido).ignoreCase());
		
		return (Cliente)getHibernateTemplate().findByCriteria(criteria).get(0);
	}

	@Override
	public Cliente findClienteByDNI(Long dni) {
		DetachedCriteria criteria = DetachedCriteria.forClass(getPersistentClass());
		criteria.add(Restrictions.eq("dni", dni));
		
		return (Cliente)getHibernateTemplate().findByCriteria(criteria).get(0);
	}
	
}
