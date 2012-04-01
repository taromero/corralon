package ar.com.seminario.corralon.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.seminario.corralon.dao.ItemPresupuestoDAO;
import ar.com.seminario.corralon.domain.ItemPresupuesto;
import ar.com.seminario.corralon.services.ItemPresupuestoService;

@Service("itemPresupuestoService")
public class ItemPresupuestoServiceImpl implements ItemPresupuestoService{
	
	@Autowired
	private ItemPresupuestoDAO itemPresupuestoDAO;
	
	public void setItemPresupuestoDAO(ItemPresupuestoDAO itemPresupuestoDAO) {
		this.itemPresupuestoDAO = itemPresupuestoDAO;
	}

	@Override
	public void delete(Long id) {
		itemPresupuestoDAO.delete(id);
	}

	@Override
	public List<ItemPresupuesto> findAll() {
		return itemPresupuestoDAO.findAll();
	}

	@Override
	public ItemPresupuesto findByID(Long id) {
		return itemPresupuestoDAO.findByID(id);
	}

	@Override
	public void persist(ItemPresupuesto entity) {
		itemPresupuestoDAO.persist(entity);
		
	}

	@Override
	public void merge(ItemPresupuesto entity) {
		itemPresupuestoDAO.merge(entity);
	}

	

}
