package ar.com.seminario.corralon.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.seminario.corralon.services.MaterialService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { ConfiguracionTests.APP_CONTEXT })
public class MaterialServiceTest {
	
	@Autowired
	private MaterialService materialService;
	
	@Test
	public void testCalcularPrecio(){
		assertTrue(materialService.calcularPrecio(Double.parseDouble("2"), Double.parseDouble("10"),
				Double.parseDouble("8"), Boolean.TRUE, Boolean.TRUE) == 17.6);
		
		assertTrue(materialService.calcularPrecio(Double.parseDouble("2"), Double.parseDouble("10"),
				Double.parseDouble("8"), Boolean.FALSE, Boolean.TRUE) == 22);
		
		assertTrue(materialService.calcularPrecio(Double.parseDouble("2"), Double.parseDouble("10"),
				Double.parseDouble("8"), Boolean.TRUE, Boolean.FALSE) == 16);		
	
		assertTrue(materialService.calcularPrecio(Double.parseDouble("2"), Double.parseDouble("10"),
				Double.parseDouble("8"), Boolean.FALSE, Boolean.FALSE) == 20);
	}

}
