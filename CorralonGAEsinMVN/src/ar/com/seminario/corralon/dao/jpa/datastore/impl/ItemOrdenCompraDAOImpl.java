package ar.com.seminario.corralon.dao.jpa.datastore.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import ar.com.seminario.corralon.common.dao.jpa.impl.SpringJpaDAOImpl;
import ar.com.seminario.corralon.dao.ItemOrdenCompraDAO;
import ar.com.seminario.corralon.domain.ItemOrdenCompra;

@Repository("itemOrdenCompraDAO")
public class ItemOrdenCompraDAOImpl extends
		SpringJpaDAOImpl<ItemOrdenCompra> implements ItemOrdenCompraDAO {

	@Override
	public List<ItemOrdenCompra> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
