package ar.com.seminario.corralon.common.service;

import java.util.List;


public interface GenericService<T> {

	public abstract void delete(Long id);

	public abstract List<T> findAll();

	public abstract T findByID(Long id);

	public abstract void persist(T entity);
	
	public abstract T merge(T entity);
}
