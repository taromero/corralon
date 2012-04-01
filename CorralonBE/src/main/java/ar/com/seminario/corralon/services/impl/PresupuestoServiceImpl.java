package ar.com.seminario.corralon.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.seminario.corralon.dao.PresupuestoDAO;
import ar.com.seminario.corralon.domain.Cliente;
import ar.com.seminario.corralon.domain.ItemPresupuesto;
import ar.com.seminario.corralon.domain.Madera;
import ar.com.seminario.corralon.domain.Material;
import ar.com.seminario.corralon.domain.MaterialSinUnidad;
import ar.com.seminario.corralon.domain.Presupuesto;
import ar.com.seminario.corralon.domain.Usuario;
import ar.com.seminario.corralon.exceptions.PresupuestoSinItemsException;
import ar.com.seminario.corralon.exceptions.PresupuestosNoEncontradosException;
import ar.com.seminario.corralon.exceptions.StockInsuficienteException;
import ar.com.seminario.corralon.exceptions.UsuarioInexistenteException;
import ar.com.seminario.corralon.services.ClienteService;
import ar.com.seminario.corralon.services.ItemPresupuestoService;
import ar.com.seminario.corralon.services.MaterialService;
import ar.com.seminario.corralon.services.PresupuestoService;
import ar.com.seminario.corralon.services.UsuarioService;

@Service("presupuestoService")
public class PresupuestoServiceImpl	implements PresupuestoService {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private MaterialService materialService;
	
	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ItemPresupuestoService itemPresupuestoService;

	@Autowired
	private PresupuestoDAO presupuestoDAO;

	@Override
	@Transactional(rollbackFor = {UsuarioInexistenteException.class, StockInsuficienteException.class, PresupuestoSinItemsException.class})
	public Presupuesto generarPresupuesto(Long idUsuario, Long idCliente,
			List<String> descripciones, List<Integer> cantidades, List<Madera> maderas) throws UsuarioInexistenteException, StockInsuficienteException, PresupuestoSinItemsException {
		Cliente cliente = clienteService.findByDNI(idCliente);
		Usuario usuario = usuarioService.findByDNI(idUsuario);
		
		// Probar usar load para obtener las referencias a cliente y usuario
		Presupuesto presupuestoActual = new Presupuesto(cliente, usuario, false);
		List<ItemPresupuesto> items = new ArrayList<ItemPresupuesto>();
		Double precioTotal = new Double(0);
		String descripcionMaterial;
		Integer cantidadRequerida;
		Integer cantItems = descripciones.size();
		if(cantItems < 1 && maderas.isEmpty()){
			throw new PresupuestoSinItemsException();
		}
		for (int i = 0; i < cantItems; i++) {
			descripcionMaterial = descripciones.get(i);
			cantidadRequerida = cantidades.get(i);
			Material material = materialService.findByDescripcion(descripcionMaterial);
			if(materialService.hasSuficienteStock(material, cantidadRequerida)){
				Integer stock = ((MaterialSinUnidad)material).getStock();
				((MaterialSinUnidad)material).setStock(stock - cantidadRequerida);
//				materialService.merge(material);
				ItemPresupuesto item = new ItemPresupuesto(material, cantidadRequerida);
				precioTotal += item.getMaterial().getPrecioVenta() * item.getCantidad();
				items.add(item);
			}
		}
		for(Madera madera : maderas){
			ItemPresupuesto item = new ItemPresupuesto(madera, 1);
			items.add(item);
			precioTotal += madera.getPrecioVenta() * madera.getLargo();
		}
		presupuestoActual.setTotal(precioTotal);
		presupuestoActual.setFechaEmision(new Date());
		
		presupuestoActual.setItemsPresupuesto(items);
		
		cliente.getPresupuestos().add(presupuestoActual);
		
//		presupuestoDAO.persist(presupuestoActual);
		
		return presupuestoDAO.merge(presupuestoActual);
	}

	@Override
	public Presupuesto confirmarPresupuesto(Long idPresupuesto) {
		Presupuesto p = presupuestoDAO.findByID(idPresupuesto);
		p.setIsConfirmado(true);
		return presupuestoDAO.merge(p);
	}

	@Override
	@Transactional
	public void eliminarPresupuesto(Long idPresupuesto) {
		Presupuesto p = presupuestoDAO.findByID(idPresupuesto);
		Material mat;
		Integer cant;
		for(ItemPresupuesto item : p.getItemsPresupuesto()){
			mat = item.getMaterial();
			if(mat.getClass() == Madera.class){
				item.setMaterial(null);
				itemPresupuestoService.merge(item);
				materialService.rearmarMadera((Madera)mat);
			}
//			if(mat instanceof MaterialSinUnidad){
			else{
				cant = item.getCantidad();
				Integer stock = ((MaterialSinUnidad)mat).getStock();
				((MaterialSinUnidad)mat).setStock(stock + cant);
				materialService.merge(mat);
			}
		}
		p.getItemsPresupuesto().clear();
		presupuestoDAO.delete(idPresupuesto);
		
	}

	@Override
	public Presupuesto findUltimoGenerado() {
		return presupuestoDAO.findUltimoPresupuesto();
	}
	
	@Override
	public List<Presupuesto> findByFechaAndCliente(Date fecha, String cliente) throws PresupuestosNoEncontradosException{
		List<Presupuesto> presupuestos = presupuestoDAO.findByFechaAndCliente(fecha, cliente);
		if(presupuestos.isEmpty()){
			throw new PresupuestosNoEncontradosException();
		}
		return presupuestos;
	}

	@Override
	public Presupuesto findByID(Long id) {
		return presupuestoDAO.findByID(id);
	}

	@Override
	public void persist(Presupuesto entity) {
		presupuestoDAO.persist(entity);

	}
	
	@Override
	public Presupuesto merge(Presupuesto entity) {
		return presupuestoDAO.merge(entity);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Presupuesto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
