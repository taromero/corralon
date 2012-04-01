package ar.com.seminario.corralon.common.service;

import org.springframework.beans.factory.annotation.Autowired;

import ar.com.seminario.corralon.common.dao.GenericDAO;

public abstract class GenericServiceImpl<T> implements GenericService<T>{

	@Autowired
	GenericDAO<T> genericDAO;
	
	@Override
	public void delete(Long id) {
		genericDAO.delete(id);
	}

//	@Override
//	public List<T> findAll() {
//		return genericDAO.findAll();
//		return null;
//	}

	@Override
	public T findByID(Long id) {
		return (T) genericDAO.findByID(id);
	}

	@Override
	public void persist(T entity) {
		genericDAO.persist(entity);
	}

	@Override
	public T merge(T entity) {
		return genericDAO.merge(entity);
	}
	
}
