package ar.com.seminario.corralon.dao.hibernate.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import ar.com.seminario.corralon.common.dao.hibernate.impl.SpringHibernateDAOImpl;
import ar.com.seminario.corralon.dao.PresupuestoDAO;
import ar.com.seminario.corralon.domain.Presupuesto;

@Repository("presupuestoDAO")
public class PresupuestoDAOImpl extends SpringHibernateDAOImpl<Presupuesto> implements
		PresupuestoDAO {
	
	@SuppressWarnings("unchecked")
	public List<Presupuesto> findByFechaAndCliente(Date f , String cliente){
		List<Presupuesto> presupuestos= null;
		java.sql.Date sqlDate;
		String fechaNula = "";
		if(f == null){
			sqlDate = null;
			fechaNula = "nula";
		}else{
			sqlDate = new java.sql.Date(f.getTime());
		}
		
		Long dni = new Long(0);
		try{
			dni = Long.parseLong(cliente);
		}catch (NumberFormatException e) {
			//dejo dni con el valor inicial
		}
		
		Query query = getSession().createQuery("SELECT p FROM Presupuesto AS p " +
												"WHERE (p.cliente.nombre like :cliente OR " +
													"p.cliente.apellido like :cliente OR " +
													"p.cliente.dni = :dni OR " +
													":cliente like '') AND " +
													"(:fechaNula like 'nula' OR p.fechaEmision = :fecha)")
						.setParameter("cliente", cliente)
						.setParameter("dni", dni)
						.setParameter("fecha", sqlDate)
						.setParameter("fechaNula", fechaNula);
		
		presupuestos = (List<Presupuesto>) query.list();
		return presupuestos;
	}

	@Override
	public Presupuesto findUltimoPresupuesto() {
		Presupuesto presupuesto = null;
		Query query = getSession().createQuery("SELECT p FROM Presupuesto AS p " +
												"WHERE p.fechaEmision >= all(" +
															"SELECT p2.fechaEmision FROM Presupuesto AS p2)");
		presupuesto = (Presupuesto) query.list().get(query.list().size() - 1); //Obtengo el ultimo elemento (el mas nuevo en la BD)
		return presupuesto;
	}
}
