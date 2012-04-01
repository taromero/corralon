package ar.com.seminario.corralon.dao.jpa.impl;

import org.springframework.stereotype.Repository;

import ar.com.seminario.corralon.common.dao.jpa.impl.SpringJpaDAOImpl;
import ar.com.seminario.corralon.dao.ItemOrdenCompraDAO;
import ar.com.seminario.corralon.domain.ItemOrdenCompra;

@Repository("itemOrdenCompraDAO")
public class ItemOrdenCompraDAOImpl extends
		SpringJpaDAOImpl<ItemOrdenCompra> implements ItemOrdenCompraDAO {

}
