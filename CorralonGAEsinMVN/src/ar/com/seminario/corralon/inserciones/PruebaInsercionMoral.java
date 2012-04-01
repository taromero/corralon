package ar.com.seminario.corralon.inserciones;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ar.com.seminario.corralon.dao.ClienteDAO;
import ar.com.seminario.corralon.dao.ProveedorDAO;
import ar.com.seminario.corralon.dao.UsuarioDAO;
import ar.com.seminario.corralon.domain.Cliente;
import ar.com.seminario.corralon.domain.Proveedor;
import ar.com.seminario.corralon.domain.Usuario;

public class PruebaInsercionMoral {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//Inicializo el logger
		
		ApplicationContext appContext = 
	    	  new ClassPathXmlApplicationContext("applicationContextSpring.xml");
		
//		Madera m = new Madera();
//		m.setDescripcion("Algarrobo 3*2 - 123415550145");
//		m.setEnPresupuesto(false);
//		m.setLargo(15);
//		m.setPrecioVenta((double)15);
//		
//		Madera ma = new Madera();
//		ma.setDescripcion("Algarrobo 3*2 - 123415550145");
//		ma.setEnPresupuesto(false);
//		ma.setLargo(18);
//		ma.setMaderaMadre(null);
//		ma.setMaderasHijas(null);
//		ma.setPrecioVenta((double)15);
//		
//		Madera m2 = new Madera();
//		m2.setDescripcion("Algarrobo 4*5 - 123415551057");
//		m2.setEnPresupuesto(false);
//		m2.setLargo(10);
//		m2.setPrecioVenta((double)19);
//		
//		Madera m3 = new Madera();
//		m3.setDescripcion("Curupay 5*3 - 123415781235");
//		m3.setEnPresupuesto(false);
//		m3.setLargo(5);
//		m3.setPrecioVenta((double)14);
//		
//		Set<Madera> hijas = new HashSet<Madera>();
//		hijas.add(m2);
//		hijas.add(m3);
//		m.setMaderasHijas(hijas);
//		
//		MaterialDAO materialDAO = (MaterialDAO)appContext.getBean("materialDAO");
		ProveedorDAO proveedorDAO = (ProveedorDAO)appContext.getBean("proveedorDAO");
//		MaterialProveedorDAO matProveedorDAO = (MaterialProveedorDAO)appContext.getBean("materialProveedorDAO");
//		
//		materialDAO.persist(m);
//		materialDAO.persist(ma);
//		materialDAO.persist(m2);
//		materialDAO.persist(m3);
//		
//		
//		MaterialConUnidad bolsaArena = new MaterialConUnidad();
//		bolsaArena.setDescripcion("Bolsa de arena - 142312345436");
//		bolsaArena.setCantidad(5);
//		bolsaArena.setUnidad("KG");
//		bolsaArena.setStock(10);
//		bolsaArena.setPrecioVenta(18.5);
//		
//		MaterialSinUnidad tornillo = new MaterialSinUnidad();
//		tornillo.setDescripcion("Tornillo - 432154787548");
//		tornillo.setPrecioVenta(17.05);
//		tornillo.setStock(15);
//		
//		materialDAO.persist(bolsaArena);
//		
//		materialDAO.persist(tornillo);
		
		ClienteDAO clienteDAO = (ClienteDAO)appContext.getBean("clienteDAO");
//		PresupuestoDAO presupuestoDAO = (PresupuestoDAO)appContext.getBean("presupuestoDAO");
//		OrdenCompraDAO OcDAO = (OrdenCompraDAO)appContext.getBean("ordenCompraDAO");
		UsuarioDAO usuarioDAO = (UsuarioDAO)appContext.getBean("usuarioDAO");

		
		Cliente cliente = new Cliente();
		cliente.setApellido("Ponce");
		cliente.setNombre("Mauro");
		cliente.setDireccion("Nazca 1349, Claypole");
		cliente.setEmail("mgp1989@hotmail.com");
		cliente.setIsRegular(false);
		cliente.setTelefono("42910575");
		cliente.setDni(Long.parseLong("24392837"));
		cliente.setDescuento(10.0);
		clienteDAO.persist(cliente);
		
		Cliente cliente2 = new Cliente();
		cliente2.setApellido("Jordan");
		cliente2.setNombre("Michael");
		cliente2.setDireccion("Las Heras 1234, Avellaneda");
		cliente2.setEmail("mj1960@hotmail.com");
		cliente2.setIsRegular(false);
		cliente2.setTelefono("42910579");
		cliente2.setDni(Long.parseLong("18392837"));
		cliente2.setDescuento(20.0);
		clienteDAO.persist(cliente2);
		
		Usuario usuario = new Usuario();
		usuario.setApellido("Romero");
		usuario.setNombre("Tomas");
		usuario.setDni(Long.parseLong("35222444"));
		usuario.setClave("sm");
		usuario.setRol("Desarrollador");
		
		usuarioDAO.persist(usuario);
		
		//Creo presupuestos
//		Presupuesto presupuesto1 = new Presupuesto();
//		presupuesto1.setCliente(cliente);
//		presupuesto1.setIsConfirmado(false);
//		presupuesto1.setFechaEmision(new Date());
//		presupuesto1.setUsuario(usuario);
//		
//
//		
//		ItemPresupuesto itemPresupuesto1 = new ItemPresupuesto(bolsaArena, 1);
//		
//		ItemPresupuesto itemPresupuesto2 = new ItemPresupuesto(tornillo, 2);
//		
//		ItemPresupuesto itemPresupuesto3 = new ItemPresupuesto(m2, 2);
//		
//		presupuesto1.getItemsPresupuesto().add(itemPresupuesto1);
//		presupuesto1.getItemsPresupuesto().add(itemPresupuesto2);
//		presupuesto1.getItemsPresupuesto().add(itemPresupuesto3);
//		
//		//calculo el total del presupuesto
//		Double total = (double) 0;
//		for(ItemPresupuesto ip : presupuesto1.getItemsPresupuesto()){
//			Double subtotal = ip.getCantidad() * ip.getMaterial().getPrecioVenta();
//			total += subtotal;
//		}
//		presupuesto1.setTotal(total);
//		
//		
//		
//		cliente.getPresupuestos().add(presupuesto1);
//		presupuestoDAO.persist(presupuesto1);
//		
//		Presupuesto presupuesto2 = new Presupuesto();
//		presupuesto2.setCliente(cliente);
//		presupuesto2.setIsConfirmado(false);
//		presupuesto2.setFechaEmision(new Date());
//		presupuesto2.setUsuario(usuario);
//		
//		ItemPresupuesto itemPresupuesto21 = new ItemPresupuesto(bolsaArena, 15);
//		
//		ItemPresupuesto itemPresupuesto22 = new ItemPresupuesto(tornillo, 50);
//		
//		ItemPresupuesto itemPresupuesto23 = new ItemPresupuesto(m3, 1);
//		
//		presupuesto2.getItemsPresupuesto().add(itemPresupuesto21);
//		presupuesto2.getItemsPresupuesto().add(itemPresupuesto22);
//		presupuesto2.getItemsPresupuesto().add(itemPresupuesto23);
//		
//		//calculo el total del presupuesto
//		Double total2 = (double) 0;
//		for(ItemPresupuesto ip : presupuesto2.getItemsPresupuesto()){
//			Double subtotal2 = ip.getCantidad() * ip.getMaterial().getPrecioVenta();
//			total2 += subtotal2;
//		}
//		presupuesto2.setTotal(total2);
//		
//		cliente.getPresupuestos().add(presupuesto2);
//		presupuestoDAO.persist(presupuesto2);
//		
//		//Termino de crear presupuestos
//		
//		Madera algarrobo = (Madera)materialDAO.findMaterialByDescripcion("Algarrobo 3*2 - 123415550145");
//		List<Cliente> clientesList = clienteDAO.findAll();
//		List<Material> materiales = materialDAO.findAll();
		
		Proveedor prov = new Proveedor("Raipor", "1243-5438", "Belgrano 123", "pedidos@raipor.com");		
		proveedorDAO.persist(prov);
		
		prov = new Proveedor("MaderNor", "4774-7711", "Caceres 332", "pedidos@madernor.com");
		proveedorDAO.persist(prov);
		
		prov = new Proveedor("Princer", "5555-2422", "Esmeralda 114", "pedidos@princer.com");
		proveedorDAO.persist(prov);
		
//		MaterialProveedor matProv1 = new MaterialProveedor(bolsaArena, raipor, 15.50);
//		
//		MaterialProveedor matProv2 = new MaterialProveedor(tornillo, raipor, 2.75);
//		
//		matProveedorDAO.persist(matProv1);
//		matProveedorDAO.persist(matProv2);
//		
//		@SuppressWarnings("deprecation")
//		OrdenCompra ordenCompra = new OrdenCompra(raipor, new Date(), new Date(1011, 9, 05), "Cheque", matProv1.getPrecio() + matProv2.getPrecio(), usuario);
//		
//		ItemOrdenCompra itemOC1 = new ItemOrdenCompra(5, ordenCompra, matProv1);
//
//		ItemOrdenCompra itemOC2 = new ItemOrdenCompra(20, ordenCompra, matProv2);
//		
//		List<ItemOrdenCompra> itemsOC = new ArrayList<ItemOrdenCompra>();
//		
//		itemsOC.add(itemOC1);
//		itemsOC.add(itemOC2);
//		
//		ordenCompra.setItemsOrdenCompra(itemsOC);
//		
//		OcDAO.persist(ordenCompra);
//		
//		System.out.println("Madera Algarroo y sus Hijas:");
//		System.out.println(algarrobo.getDescripcion());
//		Set <Madera> hijitas= algarrobo.getMaderasHijas();
//		for(Material c : hijitas){
//			System.out.println(c.toString());
//		}		
//		
//		System.out.println("\nTodas Los Materiales:");
//		for(Material c : materiales){
//				System.out.println(c.toString());
//		}		
//		
//		System.out.println("\nTodos los clientes y sus presupuestos:");
//		for(Cliente c : clientesList){			
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
