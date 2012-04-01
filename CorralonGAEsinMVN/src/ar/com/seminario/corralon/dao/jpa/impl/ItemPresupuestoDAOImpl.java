package ar.com.seminario.corralon.dao.jpa.impl;

import org.springframework.stereotype.Repository;

import ar.com.seminario.corralon.common.dao.jpa.impl.SpringJpaDAOImpl;
import ar.com.seminario.corralon.dao.ItemPresupuestoDAO;
import ar.com.seminario.corralon.domain.ItemPresupuesto;

@Repository("itemPresupuestoDAO")
public class ItemPresupuestoDAOImpl extends SpringJpaDAOImpl<ItemPresupuesto> implements ItemPresupuestoDAO{

}
