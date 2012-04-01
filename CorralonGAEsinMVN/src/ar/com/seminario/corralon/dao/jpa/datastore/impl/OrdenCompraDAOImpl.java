package ar.com.seminario.corralon.dao.jpa.datastore.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.com.seminario.corralon.common.dao.jpa.impl.SpringJpaDAOImpl;
import ar.com.seminario.corralon.dao.OrdenCompraDAO;
import ar.com.seminario.corralon.dao.ProveedorDAO;
import ar.com.seminario.corralon.domain.ItemOrdenCompra;
import ar.com.seminario.corralon.domain.OrdenCompra;
import ar.com.seminario.corralon.domain.Proveedor;

@Repository("ordenCompraDAO")
public class OrdenCompraDAOImpl extends SpringJpaDAOImpl<OrdenCompra>
		implements OrdenCompraDAO {

	@Autowired
	private ProveedorDAO proveedorDAO;
	
	//Inexplicablemente, tengo que hacer esto. si comento todo de la segunda linea para abajo , el super.findByID de la primera linea me trae los items duplicados, pero sino no
	public OrdenCompra findByID(Long id){
		OrdenCompra oc = super.findByID(id);
		List<ItemOrdenCompra> list = oc.getItemsOrdenCompra();
		ItemOrdenCompra item1;
		ItemOrdenCompra item2;
		for(int i = 0; i < list.size(); i++){
			item1 = list.get(i);
			for(int j = i+1; j < list.size(); j++){
				item2 = list.get(j);
				if(item1.getId_item_orden_compra() == item2.getId_item_orden_compra()){
					list.remove(j);
				}
			}
		}
		oc.setItemsOrdenCompra(list);
		return oc;
	}
	
	@SuppressWarnings("unchecked")
	public List<OrdenCompra> findByFechaAndProveedor(Date f , String proveedorRazonSocial){
		List<OrdenCompra> ordenesCompra = new ArrayList<OrdenCompra>();
		List<OrdenCompra> ordenesCompraAux = null;
		Proveedor proveedor = proveedorDAO.findByRazonSocial(proveedorRazonSocial);
		String queryString = "SELECT oc FROM ordenCompra oc" + 
							" WHERE oc.idProveedor = "+proveedor.getId_proveedor();
		
		Query query = entityManager.createQuery(queryString);
		
		ordenesCompraAux = (List<OrdenCompra>) query.getResultList();
		
		for(OrdenCompra oc : ordenesCompraAux){
			if(oc.getFechaEmision().equals(f)){
				ordenesCompra.add(oc);
			}
		}
		return ordenesCompra;
	}

	@Override
	public List<OrdenCompra> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
