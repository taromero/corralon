package ar.com.seminario.corralon.dao.hibernate.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import ar.com.seminario.corralon.common.dao.hibernate.impl.SpringHibernateDAOImpl;
import ar.com.seminario.corralon.dao.OrdenCompraDAO;
import ar.com.seminario.corralon.domain.ItemOrdenCompra;
import ar.com.seminario.corralon.domain.OrdenCompra;

@Repository("ordenCompraDAO")
public class OrdenCompraDAOImpl extends SpringHibernateDAOImpl<OrdenCompra>
		implements OrdenCompraDAO {
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
	public List<OrdenCompra> findByFechaAndProveedor(Date f , String proveedor){
		List<OrdenCompra> ordenesCompra= null;
		java.sql.Date sqlDate;
		String fechaNula = "";
		if(f == null){
			sqlDate = null;
			fechaNula = "nula";
		}else{
			sqlDate = new java.sql.Date(f.getTime());
		}
		try{
		}catch (NumberFormatException e) {
			//dejo dni con el valor inicial
		}
		
		Query query = getSession().createQuery("SELECT oc FROM OrdenCompra AS oc " +
												"WHERE (oc.proveedor.razonSocial like :proveedor OR " +
													"oc.proveedor.direccion like :proveedor OR " +
													"oc.proveedor.email like :proveedor OR " +
													":proveedor like '') AND " +
													"(:fechaNula like 'nula' OR oc.fechaEmision = :fecha)")
						.setParameter("proveedor", proveedor)
						.setParameter("fecha", sqlDate)
						.setParameter("fechaNula", fechaNula);
		
		ordenesCompra = (List<OrdenCompra>) query.list();
		return ordenesCompra;
	}
}
