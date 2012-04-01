package ar.com.seminario.corralon.dao.hibernate.impl;

import org.springframework.stereotype.Repository;

import ar.com.seminario.corralon.common.dao.hibernate.impl.SpringHibernateDAOImpl;
import ar.com.seminario.corralon.dao.ItemPresupuestoDAO;
import ar.com.seminario.corralon.domain.ItemPresupuesto;

@Repository("itemPresupuestoDAO")
public class ItemPresupuestoDAOImpl extends SpringHibernateDAOImpl<ItemPresupuesto> implements ItemPresupuestoDAO{

}
