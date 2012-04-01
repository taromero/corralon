package ar.com.seminario.corralon.common.dao;

import java.util.List;


public interface GenericDAO<T> {

	public abstract void delete(Long id);

	public abstract List<T> findAll();

	public abstract T findByID(Long id);

	public abstract void persist(T entity);
	
	public abstract T merge(T entity);

}