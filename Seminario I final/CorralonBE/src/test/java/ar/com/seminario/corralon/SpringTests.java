package ar.com.seminario.corralon;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses( { GuardarPresupuestoSelTest.class, 
					GuardarPresupuestoSelTestGoogleChrome.class , 
						PresupuestoServiceTest.class})
public class SpringTests {}