package ar.com.seminario.corralon.dao;

import java.util.Date;
import java.util.List;

import ar.com.seminario.corralon.common.dao.SpringHibernateDAO;
import ar.com.seminario.corralon.domain.Presupuesto;

public interface PresupuestoDAO extends SpringHibernateDAO<Presupuesto>{
	
	public List<Presupuesto> findByFechaAndCliente(Date f , String cliente);
	
	public Presupuesto findUltimoPresupuesto();
}
