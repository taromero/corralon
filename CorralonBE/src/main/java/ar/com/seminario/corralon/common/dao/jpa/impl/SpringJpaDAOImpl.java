package ar.com.seminario.corralon.common.dao.jpa.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.seminario.corralon.common.dao.GenericDAO;

@Repository("genericDAO")
public class SpringJpaDAOImpl<T> implements GenericDAO<T> {

	@PersistenceContext
	protected EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public Class<T> getPersistentClass() {
		return (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	@Override
	public void delete(Long id) {
		T obj = this.findByID(id);
		entityManager.remove(obj);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		Query query = entityManager.createQuery("select from" + getPersistentClass().getName());
		List<T> list = query.getResultList();
		return list;
	}

	@Override
	public T findByID(Long id) {
		T t = entityManager.find(this.getPersistentClass(), id);
		return t;
	}

	@Override
	@Transactional
	public void persist(T entity) {
		entityManager.persist(entity);
	}

	@Override
	public T merge(T entity) {
		return entityManager.merge(entity);
	}

}
