package ar.com.seminario.corralon.dao.hibernate.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ar.com.seminario.corralon.common.dao.hibernate.impl.SpringHibernateDAOImpl;
import ar.com.seminario.corralon.dao.UsuarioDAO;
import ar.com.seminario.corralon.domain.Usuario;
import ar.com.seminario.corralon.exceptions.UsuarioInexistenteException;

@Repository("usuarioDAO")
public class UsuarioDAOImpl extends SpringHibernateDAOImpl<Usuario> implements UsuarioDAO{

	@Override
	public Usuario findByDNI(Long dni) throws UsuarioInexistenteException {
		DetachedCriteria criteria = DetachedCriteria.forClass(Usuario.class);
		criteria.add(Restrictions.eq("dni", dni));
		
		if(getHibernateTemplate().findByCriteria(criteria).size() == 0){
			throw new UsuarioInexistenteException();
		}
		
		return (Usuario)getHibernateTemplate().findByCriteria(criteria).get(0);
	}

	@Override
	public Usuario findByName(String name) throws UsuarioInexistenteException {
		DetachedCriteria criteria = DetachedCriteria.forClass(Usuario.class);
		criteria.add(Restrictions.eq("nombre", name));
		
		if(getHibernateTemplate().findByCriteria(criteria).size() == 0){
			throw new UsuarioInexistenteException();
		}
		
		return (Usuario)getHibernateTemplate().findByCriteria(criteria).get(0);
	}

	@Override
	public Usuario findByEmail(String email) throws UsuarioInexistenteException {
		DetachedCriteria criteria = DetachedCriteria.forClass(Usuario.class);
		criteria.add(Restrictions.eq("email", email));
		
		if(getHibernateTemplate().findByCriteria(criteria).size() == 0){
			throw new UsuarioInexistenteException();
		}
		
		return (Usuario)getHibernateTemplate().findByCriteria(criteria).get(0);
	}

	@Override
	public Usuario findByExternalId(String externalId)
			throws UsuarioInexistenteException {
		DetachedCriteria criteria = DetachedCriteria.forClass(Usuario.class);
		criteria.add(Restrictions.eq("externalId", externalId));
		
		if(getHibernateTemplate().findByCriteria(criteria).size() == 0){
			throw new UsuarioInexistenteException();
		}
		
		return (Usuario)getHibernateTemplate().findByCriteria(criteria).get(0);
	} 
	
}
