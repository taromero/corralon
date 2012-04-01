package ar.com.seminario.corralon.common.dao;

import java.io.Serializable;
import java.util.List;

public interface SpringHibernateDAO<T> {

	public abstract void delete(Long id);

	public abstract List<T> findAll();

	public abstract T findByID(Long id);

	public abstract void persist(T entity);
	
	public abstract Serializable save(T entity);
	
	public abstract void merge(T entity);

	public T load(Long id);

}