package ar.com.seminario.corralon;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.seminario.corralon.services.PresupuestoService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContextSpring.xml" })
public class PresupuestoServiceTest {
	
	@Autowired
	private PresupuestoService presupuestoService;
	
	@Test
	public void testObtenerUltimoPresupuestoGenerado(){
		assertTrue(presupuestoService.findUltimoGenerado() != null);
	}

}
