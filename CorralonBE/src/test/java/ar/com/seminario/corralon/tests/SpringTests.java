package ar.com.seminario.corralon.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses( { ClienteServiceTest.class, 
					MaterialServiceTest.class,
						PresupuestoServiceTest.class, 
							LoginServiceTest.class})
public class SpringTests {}