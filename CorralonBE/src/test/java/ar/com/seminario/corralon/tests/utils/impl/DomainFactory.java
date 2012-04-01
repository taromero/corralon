package ar.com.seminario.corralon.tests.utils.impl;

import ar.com.seminario.corralon.domain.Cliente;

public class DomainFactory{

	public static Cliente buildCliente(){
		Cliente c = new Cliente();
		c.setApellido("Ponce");
		c.setDescuento(10D);
		c.setDireccion("Claypole 123");
		c.setDni(33444777L);
		c.setEmail("mgp1989@gmail.com");
		c.setIsRegular(true);
		c.setNombre("Mauro");
		c.setTelefono("12345678");
		
		return c;
	}
	
}
