package ar.com.seminario.corralon;

import org.junit.Before;

import com.thoughtworks.selenium.DefaultSelenium;

public class GuardarPresupuestoSelTestGoogleChrome extends GuardarPresupuestoSelTest{

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws Exception {
		selenium = new DefaultSelenium("localhost", 4444, "*googlechrome", "http://localhost:8080/");
		selenium.setSpeed("100");
		selenium.start();
	}
}
