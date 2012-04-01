package ar.com.seminario.corralon.dao.jpa.datastore.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import ar.com.seminario.corralon.common.dao.jpa.impl.SpringJpaDAOImpl;
import ar.com.seminario.corralon.dao.ItemPresupuestoDAO;
import ar.com.seminario.corralon.domain.ItemPresupuesto;

@Repository("itemPresupuestoDAO")
public class ItemPresupuestoDAOImpl extends SpringJpaDAOImpl<ItemPresupuesto> implements ItemPresupuestoDAO{

	@Override
	public List<ItemPresupuesto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
