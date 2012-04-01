package ar.com.seminario.corralon.dao;

import java.util.List;

import ar.com.seminario.corralon.common.dao.GenericDAO;
import ar.com.seminario.corralon.domain.ItemOrdenCompra;

public interface ItemOrdenCompraDAO extends GenericDAO<ItemOrdenCompra> {
	public List<ItemOrdenCompra> findAll();
}
