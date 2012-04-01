package ar.com.seminario.corralon;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.seminario.corralon.domain.Material;
import ar.com.seminario.corralon.domain.Presupuesto;
import ar.com.seminario.corralon.exceptions.StockInsuficienteException;
import ar.com.seminario.corralon.services.MaterialService;
import ar.com.seminario.corralon.services.PresupuestoService;

import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.SeleneseTestCase;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContextSpring.xml" })
//@TransactionConfiguration (transactionManager = "txManager", defaultRollback = true)
//@Transactional
public class GuardarPresupuestoSelTest extends SeleneseTestCase {
	
	private final Integer CANTIDAD_BOLSAS = 8;
	private final String DESCRIPCION_MATERIAL = "Bolsa de arena - 142312345436";
	
	@Autowired
	PresupuestoService presupuestoService;
	
	@Autowired
	MaterialService materialService;
	
	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/");
		selenium.setSpeed("100");
		selenium.start();
	}

	@Test
//	@Transactional
//	@Rollback(true)
	public void testGuardarPresupuestoSel() throws StockInsuficienteException, InterruptedException{
		Material material = materialService.findByDescripcion(DESCRIPCION_MATERIAL);
		if(materialService.hasSuficienteStock(material, CANTIDAD_BOLSAS)){
			selenium.open("/endLogin.htm?alias=35222444&password=sm");
			selenium.type("cliente", "Ponce, Mauro - 24392837");
			selenium.click("seleccionarCliente1");
			selenium.type("material", DESCRIPCION_MATERIAL);
			selenium.type("cantidad1", CANTIDAD_BOLSAS.toString());
			selenium.click("agregar1");
			selenium.click("guardar1");
			
			this.waiting(1000); //Le doy tiempo a que guarde el presupuesto en la BD antes de seguir (con firefox andaba bien sin esto, con googlechrome no)
			
			Presupuesto presupuestoGenerado = presupuestoService.findUltimoGenerado();
			assertTrue(presupuestoGenerado.getItemsPresupuesto().get(0).getCantidad().equals(CANTIDAD_BOLSAS));
			presupuestoService.eliminarPresupuesto(presupuestoGenerado.getId_presupuesto());
		}
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
	
	public void waiting (int n){        
		 long t0, t1;
		 t0 =  System.currentTimeMillis();
		 do{t1 = System.currentTimeMillis();
		 }
		 while (t1 - t0 < n);
	}
}