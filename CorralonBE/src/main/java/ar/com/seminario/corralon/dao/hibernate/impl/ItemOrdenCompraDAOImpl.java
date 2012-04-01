package ar.com.seminario.corralon.dao.hibernate.impl;

import org.springframework.stereotype.Repository;

import ar.com.seminario.corralon.common.dao.hibernate.impl.SpringHibernateDAOImpl;
import ar.com.seminario.corralon.dao.ItemOrdenCompraDAO;
import ar.com.seminario.corralon.domain.ItemOrdenCompra;

@Repository("itemOrdenCompraDAO")
public class ItemOrdenCompraDAOImpl extends
		SpringHibernateDAOImpl<ItemOrdenCompra> implements ItemOrdenCompraDAO {

}
