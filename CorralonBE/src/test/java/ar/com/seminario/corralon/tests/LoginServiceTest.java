package ar.com.seminario.corralon.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.seminario.corralon.dtos.LoginDTO;
import ar.com.seminario.corralon.exceptions.UsuarioInexistenteException;
import ar.com.seminario.corralon.services.LoginService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { ConfiguracionTests.APP_CONTEXT })
public class LoginServiceTest {
	
	@Autowired
	private LoginService loginService;
	
	@Test
	public void testlogin() throws UsuarioInexistenteException{
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setAlias(new Long(35222444));
		loginDTO.setPassword("sm");
		assertTrue(loginService.validateUser(loginDTO).getApellido().equalsIgnoreCase("Romero"));
	}
	
}
