package ar.com.seminario.corralon.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import ar.com.seminario.corralon.domain.Cliente;
import ar.com.seminario.corralon.services.ClienteService;
import ar.com.seminario.corralon.tests.utils.impl.DomainFactory;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { ConfiguracionTests.APP_CONTEXT })
@TransactionConfiguration(defaultRollback = true)
//@Transactional()
public class ClienteServiceTest {
	
	@Autowired
	private ClienteService clienteService;
	
	@Test
//	@Rollback(true)
	public void testBusquedas() throws Exception{
		Cliente c1 = DomainFactory.buildCliente();
		clienteService.persist(c1);
		
		
		Cliente c2 = clienteService.findClienteByNombreYApellido(c1.getNombre(), c1.getApellido());
		assertTrue(c2.getApellido().equals(c1.getApellido()));
	}

}
