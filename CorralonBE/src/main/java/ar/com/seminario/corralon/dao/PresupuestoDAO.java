package ar.com.seminario.corralon.dao;

import java.util.Date;
import java.util.List;

import ar.com.seminario.corralon.common.dao.GenericDAO;
import ar.com.seminario.corralon.domain.Presupuesto;

public interface PresupuestoDAO extends GenericDAO<Presupuesto>{
	
	public List<Presupuesto> findByFechaAndCliente(Date f , String cliente);
	
	public Presupuesto findUltimoPresupuesto();
	
	public List<Presupuesto> findAll();
}
