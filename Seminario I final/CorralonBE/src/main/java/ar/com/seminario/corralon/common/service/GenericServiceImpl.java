package ar.com.seminario.corralon.common.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.seminario.corralon.common.dao.SpringHibernateDAO;

public class GenericServiceImpl<T> implements GenericService<T>{

	@Autowired
	SpringHibernateDAO<T> springHibernateDAOImpl;
	
	@Override
	public void delete(Long id) {
		springHibernateDAOImpl.delete(id);
	}

	@Override
	public List<T> findAll() {
		return springHibernateDAOImpl.findAll();
	}

	@Override
	public T findByID(Long id) {
		return (T) springHibernateDAOImpl.findByID(id);
	}

	@Override
	public void persist(T entity) {
		springHibernateDAOImpl.persist(entity);
	}

	@Override
	public void merge(T entity) {
		springHibernateDAOImpl.merge(entity);
	}
	
}
