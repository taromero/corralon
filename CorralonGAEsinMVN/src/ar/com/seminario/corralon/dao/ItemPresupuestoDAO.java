package ar.com.seminario.corralon.dao;

import java.util.List;

import ar.com.seminario.corralon.common.dao.GenericDAO;
import ar.com.seminario.corralon.domain.ItemPresupuesto;


public interface ItemPresupuestoDAO extends GenericDAO<ItemPresupuesto>{
	public List<ItemPresupuesto> findAll();
}
