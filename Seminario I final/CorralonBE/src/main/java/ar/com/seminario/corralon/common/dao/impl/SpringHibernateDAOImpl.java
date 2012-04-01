package ar.com.seminario.corralon.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import ar.com.seminario.corralon.common.dao.SpringHibernateDAO;

@Repository("springHibernateDAOImpl")
public class SpringHibernateDAOImpl<T> extends HibernateDaoSupport implements
		SpringHibernateDAO<T> {

	/*
	 * Clase parametrizada, para ser extendida. Facilita el uso de Hibernate
	 * porque ofrece un objeto HibernateTemplate que tiene m�todos mas simples
	 * para las operaciones CRUD.
	 */

	@SuppressWarnings("unchecked")
	public Class<T> getPersistentClass() {
		return (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public void delete(Long id) {
		T obj = (T) getHibernateTemplate().load(getPersistentClass(), id);
		getHibernateTemplate().delete(obj);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		// Consulta con HQL. La tabla se tiene que llamar igual q la clase.
		return getHibernateTemplate().find(
				"from " + getPersistentClass().getName());
	}

	@Override
	public T findByID(Long id) {
		return (T) getHibernateTemplate().get(getPersistentClass(), id);
	}

	@Override
	public void persist(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}
	
	@Override
	public Serializable save(T entity) {
		return getHibernateTemplate().save(entity);
	}

	/*
	 * La session factory es un bean m�s de Spring, por lo que se inyecta como
	 * dependencia a las clases que heredan a esta.
	 */
	@Autowired
	public void wireSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}
	
	@Override
	public void merge (T entity){
		getHibernateTemplate().merge(entity);
	}
	
	@Override
	public T load(Long id){
		return (T)getHibernateTemplate().load(getPersistentClass(), id);
	}

}
