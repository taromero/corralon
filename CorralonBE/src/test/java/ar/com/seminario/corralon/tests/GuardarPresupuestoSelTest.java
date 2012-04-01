package ar.com.seminario.corralon.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.server.SeleniumServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.seminario.corralon.domain.Material;
import ar.com.seminario.corralon.domain.Presupuesto;
import ar.com.seminario.corralon.exceptions.StockInsuficienteException;
import ar.com.seminario.corralon.services.MaterialService;
import ar.com.seminario.corralon.services.PresupuestoService;

import com.thoughtworks.selenium.SeleneseTestCase;

@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { ConfiguracionTests.APP_CONTEXT })
//@TransactionConfiguration (transactionManager = "txManager", defaultRollback = true)
//@Transactional
public abstract class GuardarPresupuestoSelTest extends SeleneseTestCase {
	
	private final Integer CANTIDAD_MAT = 8;
	private final String DESCRIPCION_CORTA_MATERIAL = "ESM BERMELLON EN AEROSOL 440 - 0100";
	private final String DESCRIPCION_MATERIAL = "ESM BERMELLON EN AEROSOL 440 - 010010000720";
	private SeleniumServer seleniumserver;
	
	@Autowired
	private PresupuestoService presupuestoService;
	
	@Autowired
	private MaterialService materialService;
	
	@Before
	public abstract void setUp() throws Exception;

	@Test
//	@Transactional
//	@Rollback(true)
	public void testGuardarPresupuestoSel() throws StockInsuficienteException, InterruptedException{
		Material material = materialService.findByDescripcion(DESCRIPCION_MATERIAL);
		if(materialService.hasSuficienteStock(material, CANTIDAD_MAT)){
			selenium.open("/endLogin.htm?alias=35222444&password=sm");
			selenium.type("cliente", "Ponce, Mauro - 24392837");
			selenium.click("seleccionarCliente1");
			selenium.typeKeys("material", DESCRIPCION_CORTA_MATERIAL);
			for (int second = 0;; second++) {
				if (second >= 5) fail("timeout");
//				try { if (selenium.isVisible("css=.ui-autocomplete")) break; } catch (Exception e) {}
				try { if (selenium.isTextPresent(DESCRIPCION_MATERIAL)) break; } catch (Exception e) {}
				Thread.sleep(1000);
			}
			selenium.mouseOver("css=a.ui-corner-all.ui-state-hover");
//			selenium.mouseOver("link=" + DESCRIPCION_MATERIAL);
			selenium.click("link=" + DESCRIPCION_MATERIAL);
			selenium.click("agregar1");
			selenium.click("guardar1");
			
			this.waiting(1000); //Le doy tiempo a que guarde el presupuesto en la BD antes de seguir (con firefox andaba bien sin esto, con googlechrome no)
			
			Presupuesto presupuestoGenerado = presupuestoService.findUltimoGenerado();
			assertTrue(presupuestoGenerado.getItemsPresupuesto().get(0).getCantidad().equals(CANTIDAD_MAT));
			presupuestoService.eliminarPresupuesto(presupuestoGenerado.getId_presupuesto());
		}
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
		seleniumserver.stop();
	}
	
	public void waiting (int n){        
		 long t0, t1;
		 t0 =  System.currentTimeMillis();
		 do{t1 = System.currentTimeMillis();
		 }
		 while (t1 - t0 < n);
	}
}