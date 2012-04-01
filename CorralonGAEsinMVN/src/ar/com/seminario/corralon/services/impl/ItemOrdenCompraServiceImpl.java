package ar.com.seminario.corralon.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.seminario.corralon.dao.ItemOrdenCompraDAO;
import ar.com.seminario.corralon.domain.ItemOrdenCompra;
import ar.com.seminario.corralon.services.ItemOrdenCompraService;

@Service("itemOrdenCompraService")
public class ItemOrdenCompraServiceImpl implements ItemOrdenCompraService{

	@Autowired
	private ItemOrdenCompraDAO itemOrdenCompraDAO;

	@Override
	public void delete(Long id) {
		itemOrdenCompraDAO.delete(id);
	}

	@Override
	public List<ItemOrdenCompra> findAll() {
		return itemOrdenCompraDAO.findAll();
	}

	@Override
	public ItemOrdenCompra findByID(Long id) {
		return itemOrdenCompraDAO.findByID(id);
	}

	@Override
	public void persist(ItemOrdenCompra entity) {
		itemOrdenCompraDAO.persist(entity);
	}

	@Override
	public ItemOrdenCompra merge(ItemOrdenCompra entity) {
		return itemOrdenCompraDAO.merge(entity);
	}
	

}
