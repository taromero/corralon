package ar.com.seminario.corralon.dao.jpa.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ar.com.seminario.corralon.common.dao.jpa.impl.SpringJpaDAOImpl;
import ar.com.seminario.corralon.dao.PresupuestoDAO;
import ar.com.seminario.corralon.domain.Cliente;
import ar.com.seminario.corralon.domain.Presupuesto;

@Repository("presupuestoDAO")
public class PresupuestoDAOImpl extends SpringJpaDAOImpl<Presupuesto> implements
		PresupuestoDAO {
	
	@SuppressWarnings("unchecked")
	public List<Presupuesto> findByFechaAndCliente(Date f , String cliente){
		List<Cliente> clientes;
		List<Presupuesto> presupuestos= new ArrayList<Presupuesto>();
		String queryCliente = "SELECT c Cliente c WHERE c.apellido = '" + cliente + "'" +
								" OR c.nombre = '" + cliente + "' OR c.dni = " + cliente;
		Query query = entityManager.createQuery(queryCliente);
		clientes = (List<Cliente>) query.getResultList();
		if(f != null){
			for(Cliente c : clientes){
				Set<Presupuesto> pAux = c.getPresupuestos();
				for(Presupuesto p : pAux){
					if(p.getFechaEmision().equals(f)){
						presupuestos.add(p);
					}
				}
			}
		}else{
			for(Cliente c : clientes){
				Set<Presupuesto> pAux = c.getPresupuestos();
				for(Presupuesto p : pAux){
					presupuestos.add(p);
				}
			}
		}
		return presupuestos;
	}

	@Override
	public Presupuesto findUltimoPresupuesto() {
		String queryString = "SELECT p FROM Presupuesto p" +
							" WHERE p.fechaEmision >= all(" +
							" SELECT p2.fechaEmision FROM Presupuesto p2)";
		Query query = entityManager.createQuery(queryString);
		Presupuesto presupuesto = (Presupuesto) query.getSingleResult();
		return presupuesto;
	}
}
