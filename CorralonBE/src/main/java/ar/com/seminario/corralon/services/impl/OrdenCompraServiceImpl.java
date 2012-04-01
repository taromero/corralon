package ar.com.seminario.corralon.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.seminario.corralon.dao.MaterialProveedorDAO;
import ar.com.seminario.corralon.dao.OrdenCompraDAO;
import ar.com.seminario.corralon.domain.ItemOrdenCompra;
import ar.com.seminario.corralon.domain.Madera;
import ar.com.seminario.corralon.domain.MaterialProveedor;
import ar.com.seminario.corralon.domain.OrdenCompra;
import ar.com.seminario.corralon.domain.Proveedor;
import ar.com.seminario.corralon.domain.Usuario;
import ar.com.seminario.corralon.exceptions.UsuarioInexistenteException;
import ar.com.seminario.corralon.services.ItemOrdenCompraService;
import ar.com.seminario.corralon.services.OrdenCompraService;
import ar.com.seminario.corralon.services.ProveedorService;
import ar.com.seminario.corralon.services.UsuarioService;

@Service("ordenCompraService")
public class OrdenCompraServiceImpl implements OrdenCompraService {

	@Autowired
	private OrdenCompraDAO ordenCompraDAO;
	
	@Autowired
	private MaterialProveedorDAO materialProveedorDAO;
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ItemOrdenCompraService itemOrdenCompraService;
	
	@Autowired
	private ProveedorService proveedorService;
	
	@Override
	public void delete(Long id) {
		ordenCompraDAO.delete(id);
	}

	@Override
	public List<OrdenCompra> findAll() {
		return ordenCompraDAO.findAll();
	}

	@Override
	public OrdenCompra findByID(Long id) {
		return ordenCompraDAO.findByID(id);
	}

	@Override
	public void persist(OrdenCompra entity) {
		ordenCompraDAO.persist(entity);
	}

	@Override
	@Transactional
	public void generarOrdenCompra(Long idUsuario, String razonSocialProveedor,
			List<String> descripciones, List<Integer> cantidades,
			Date fechaEntrega, String formaPago, List<Madera> maderas) throws UsuarioInexistenteException { // fechaEntrega es un String de este estilo: 23-04-2011.
		
		Proveedor proveedor = proveedorService.findByRazonSocial(razonSocialProveedor);
		
		Usuario usuario = usuarioService.findByDNI(idUsuario);
		
		OrdenCompra OCActual = new OrdenCompra();
		OCActual.setProveedor(proveedor);
		OCActual.setUsuario(usuario);
		OCActual.setFechaEmision(new Date());
		OCActual.setFormaPago(formaPago);
		OCActual.setFechaEntrega(fechaEntrega);

		List<ItemOrdenCompra> items = new ArrayList<ItemOrdenCompra>();
		Double precioTotal = new Double(0);
		String descripcion;
		Integer cant;
		for (int i = 0; i < descripciones.size(); i++) {
			ItemOrdenCompra item = new ItemOrdenCompra();
			descripcion = descripciones.get(i);
			cant = cantidades.get(i);
			MaterialProveedor material = materialProveedorDAO
					.findByDescripcionYProveedor(descripcion, proveedor.getRazonSocial());
			
			item.setMaterial(material);
			item.setCantidad(cant);
			item.setOrdenCompra(OCActual);
			precioTotal += item.getMaterial().getPrecio()
					* item.getCantidad();
			items.add(item);
		}
		
		for(Madera m : maderas){
			ItemOrdenCompra item = new ItemOrdenCompra();
			MaterialProveedor material = materialProveedorDAO
				.findByDescripcionYProveedor(m.getDescripcion(), proveedor.getRazonSocial());
			item.setMaterial(material);
			item.setCantidad(1);
			item.setOrdenCompra(OCActual);
			precioTotal += item.getMaterial().getPrecio()
			* item.getCantidad();
			items.add(item);
		}
		
		OCActual.setTotal(precioTotal);
		OCActual.setFechaEmision(new Date());
		
		ordenCompraDAO.persist(OCActual);
		
		for (ItemOrdenCompra itemListo : items) {
			itemOrdenCompraService.persist(itemListo);
		}
		
		OCActual.setItemsOrdenCompra(items);
		
		ordenCompraDAO.merge(OCActual);
		
	}

	@Override
	public OrdenCompra merge(OrdenCompra entity) {
		return ordenCompraDAO.merge(entity);
	}

	@Override
	public List<OrdenCompra> findByFechaAndProveedor(Date f, String proveedor) {
		return ordenCompraDAO.findByFechaAndProveedor(f, proveedor);
	}

}
