package ar.com.seminario.corralon.tests;

import org.openqa.selenium.server.RemoteControlConfiguration;
import org.openqa.selenium.server.SeleniumServer;

import com.thoughtworks.selenium.DefaultSelenium;

public class GuardarPresupuestoSelTestFirefox extends GuardarPresupuestoSelTest{

	@SuppressWarnings("deprecation")
	@Override
	public void setUp() throws Exception {
		RemoteControlConfiguration rc;
		rc = new RemoteControlConfiguration();
		rc.setPort(4444);
		SeleniumServer seleniumserver=new SeleniumServer(rc);
		seleniumserver=new SeleniumServer(rc);
		seleniumserver.boot();
		seleniumserver.start();
		seleniumserver.getPort();
		selenium = new DefaultSelenium("localhost", 4444, "*chrome", "http://localhost:8080/");
		selenium.setSpeed("1000");
		selenium.start();
	}

}
