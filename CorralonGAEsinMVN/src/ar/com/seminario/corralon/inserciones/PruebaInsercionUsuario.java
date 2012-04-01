package ar.com.seminario.corralon.inserciones;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.seminario.corralon.dao.ClienteDAO;
import ar.com.seminario.corralon.dao.MaterialDAO;
import ar.com.seminario.corralon.dao.PresupuestoDAO;
import ar.com.seminario.corralon.dao.UsuarioDAO;
import ar.com.seminario.corralon.domain.Cliente;
import ar.com.seminario.corralon.domain.ItemPresupuesto;
import ar.com.seminario.corralon.domain.Material;
import ar.com.seminario.corralon.domain.MaterialConUnidad;
import ar.com.seminario.corralon.domain.Presupuesto;
import ar.com.seminario.corralon.domain.Usuario;


public class PruebaInsercionUsuario {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Inicializo el logger
		
		ApplicationContext appContext = 
	    	  new ClassPathXmlApplicationContext("applicationContextHibernate.xml");
		
		ClienteDAO clienteDAO = (ClienteDAO)appContext.getBean("clienteDAO");
		PresupuestoDAO presupuestoDAO = (PresupuestoDAO)appContext.getBean("presupuestoDAO");
		MaterialDAO materialDAO = (MaterialDAO)appContext.getBean("materialDAO");
		
		Cliente cliente = new Cliente();
		cliente.setApellido("Ponce");
		cliente.setNombre("Mauro");
		cliente.setDescuento(0.2);
		cliente.setDireccion("Nazca 1349, Claypole");
		cliente.setEmail("mgp1989@hotmail.com");
		cliente.setIsRegular(false);
		cliente.setTelefono("42910575");
		clienteDAO.persist(cliente);
		
		Presupuesto presupuesto1 = new Presupuesto();
		presupuesto1.setCliente(cliente);
		presupuesto1.setIsConfirmado(false);
		presupuesto1.setFechaEmision(new Date());
		
		Material bolsaArena = new MaterialConUnidad();
		bolsaArena.setDescripcion("Bolsa de arena");
		bolsaArena.setPrecioVenta(new Double(18.5));
		
		materialDAO.persist(bolsaArena);
		
		Material bolsaCal = new MaterialConUnidad();
		bolsaCal.setDescripcion("Bolsa de cal");
		bolsaCal.setPrecioVenta(new Double(17.05));
		
		materialDAO.persist(bolsaCal);
		
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
		presupuestoDAO.persist(presupuesto1);
		
		List<Cliente> clientesList = clienteDAO.findAll();
		
		for(Cliente c : clientesList){
			
			Set<Presupuesto> presupuestosSet = c.getPresupuestos();
			for(Presupuesto p : presupuestosSet){
				System.out.println("cant presupuestos: " + presupuestosSet.size());
				System.out.println("Cliente: " + c.getNombre());
				System.out.println("\tFecha de presupuesto: " + p.getFechaEmision() 
						+ "\n\tTotal: " + p.getTotal());
			}
		}
		
		Usuario user = new Usuario();
		user.setApellido("Romero");
		user.setClave("sm");
		user.setDni(Long.parseLong("35222444"));
		user.setNombre("Tomas");
		user.setRol("Desarrollador");
		UsuarioDAO uDAO = (UsuarioDAO)appContext.getBean("usuarioDAO");
		uDAO.persist(user);
	}
}
