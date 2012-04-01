package ar.com.seminario.corralon.tests;

import org.junit.Before;
import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import com.thoughtworks.selenium.DefaultSelenium;

public class GuardarPresupuestoSelTestGoogleChrome extends GuardarPresupuestoSelTest{

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws Exception {
		RemoteControlConfiguration rc;
		rc = new RemoteControlConfiguration();
		rc.setPort(4444);
		SeleniumServer seleniumserver=new SeleniumServer(rc);
		seleniumserver.boot();
		seleniumserver.start();
		seleniumserver.getPort();
		selenium = new DefaultSelenium("localhost", 4444, "*googlechrome C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe", "http://localhost:8080/");
		selenium.setSpeed("1000");
		selenium.start();
	}
}
