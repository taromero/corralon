package ar.com.seminario.corralon.inserciones;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.seminario.corralon.domain.Cliente;
import ar.com.seminario.corralon.domain.ItemPresupuesto;
import ar.com.seminario.corralon.domain.Material;
import ar.com.seminario.corralon.domain.MaterialConUnidad;
import ar.com.seminario.corralon.domain.Presupuesto;
import ar.com.seminario.corralon.domain.Usuario;
import ar.com.seminario.corralon.services.PresupuestoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContext.xml"})
public class InsercionUsuariooYPresupuestoTest {

	@Autowired
	private PresupuestoService presupuestoService;

	@Test
	public void insertarDatos(){
		Usuario user = new Usuario();
		user.setApellido("Romero");
		user.setNombre("Tomas");
		user.setDni(new Long(33444564));
		user.setClave("1578tomas3952");
		
		Cliente cliente = new Cliente();
		cliente.setApellido("Ponce");
		cliente.setNombre("Mauro");
		cliente.setDescuento(0.3);
		cliente.setDireccion("Nazca 1349, Claypole");
		cliente.setEmail("mgp1989@hotmail.com");
		cliente.setIsRegular(false);
		cliente.setTelefono("42910575");
//		clienteService.persist(cliente);
		
//		Cliente cliente = clienteService.findClienteByNombreYApellido("Mauro", "Ponce");
		
		Presupuesto presupuesto1 = new Presupuesto();
		presupuesto1.setCliente(cliente);
		presupuesto1.setUsuario(user);
		presupuesto1.setIsConfirmado(false);
		presupuesto1.setFechaEmision(new Date());
		
		Material bolsaArena = new MaterialConUnidad();
		bolsaArena.setDescripcion("Bolsa de arena");
		bolsaArena.setPrecioVenta(new Double(18.5));
		
//		materialService.persist(bolsaArena);
		
//		Material bolsaArena = materialService.findByDescripcion("Bolsa de arena");
		
		Material bolsaCal = new MaterialConUnidad();
		bolsaCal.setDescripcion("Bolsa de cal");
		bolsaCal.setPrecioVenta(new Double(17.05));
		
//		materialService.persist(bolsaCal);
		
		ItemPresupuesto itemPresupuesto1 = new ItemPresupuesto(bolsaArena, 1);
		
		ItemPresupuesto itemPresupuesto2 = new ItemPresupuesto(bolsaCal, 2);
		
		presupuesto1.getItemsPresupuesto().add(itemPresupuesto1);
		presupuesto1.getItemsPresupuesto().add(itemPresupuesto2);
		
		//calculo el total del presupuesto
		double total = 0;
		for(ItemPresupuesto ip : presupuesto1.getItemsPresupuesto()){
			double subtotal =  (ip.getCantidad() * ip.getMaterial().getPrecioVenta());
			total += subtotal;
		}
		presupuesto1.setTotal(total);
		
		
		
		cliente.getPresupuestos().add(presupuesto1);
		presupuestoService.persist(presupuesto1);
//		
//		List<Cliente> clientesList = clienteService.findAll();
//		
//		for(Cliente c : clientesList){
//			
//			Set<Presupuesto> presupuestosSet = c.getPresupuestos();
//			for(Presupuesto p : presupuestosSet){
//				System.out.println("cant presupuestos: " + presupuestosSet.size());
//				System.out.println("Cliente: " + c.getNombre());
//				System.out.println("\tFecha de presupuesto: " + p.getFechaEmision() 
//						+ "\n\tTotal: " + p.getTotal());
//			}
//		}
	}
	
}
