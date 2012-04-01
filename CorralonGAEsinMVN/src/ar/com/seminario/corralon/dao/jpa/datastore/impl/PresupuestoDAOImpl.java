package ar.com.seminario.corralon.dao.jpa.datastore.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ar.com.seminario.corralon.dao.PresupuestoDAO;
import ar.com.seminario.corralon.domain.Cliente;
import ar.com.seminario.corralon.domain.ItemPresupuesto;
import ar.com.seminario.corralon.domain.Presupuesto;

@Repository("presupuestoDAO")
public class PresupuestoDAOImpl implements
		PresupuestoDAO {
	
	@PersistenceContext
	protected EntityManager entityManager;
	
	@Override
	public void persist(Presupuesto presupuesto) {
		List<ItemPresupuesto> itemsPresupuesto = presupuesto.getItemsPresupuesto();
		for(ItemPresupuesto itemPresupuesto : itemsPresupuesto){
			itemPresupuesto.setId_item_presupuesto(null);
			itemPresupuesto.getMaterial().setId_material(null);
		}
		presupuesto.getCliente().setId_cliente(null);
		presupuesto.getUsuario().setId_usuario(null);
		entityManager.persist(presupuesto);
	}
	
	@SuppressWarnings("unchecked")
	public List<Presupuesto> findByFechaAndCliente(Date f , String cliente){
		if(cliente.isEmpty()){
			cliente = "-1";
		}
		List<Cliente> clientes;
		List<Presupuesto> presupuestos= new ArrayList<Presupuesto>();
		String queryCliente = "SELECT c FROM Cliente c WHERE c.apellido = '" + cliente + "'";// +
								//" OR c.nombre = '" + cliente + "' OR c.dni = " + cliente;
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

	@Override
	public List<Presupuesto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long id) {
		Presupuesto presupuesto = this.findByID(id);
		entityManager.remove(presupuesto);
	}

	@Override
	public Presupuesto findByID(Long id) {
		return entityManager.find(Presupuesto.class, id);
	}

	@Override
	public Presupuesto merge(Presupuesto entity) {
		return entityManager.merge(entity);
	}
}
