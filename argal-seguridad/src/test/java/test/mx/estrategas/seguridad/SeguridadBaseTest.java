package test.mx.estrategas.seguridad;

import java.util.ArrayList;
import java.util.List;

import mx.argal.seguridad.modelo.RolSeguridad;
import mx.argal.seguridad.modelo.UsuarioSeguridad;
import mx.argal.seguridad.servicios.CustomGrantedAuthority;
import mx.argal.seguridad.servicios.CustomUserDetails;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Configuración base para las pruebas del componente de seguridad.
 * 
 * @author Alex Pi
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/mx/estrategas/seguridad/seguridad-config.xml")
@ActiveProfiles("testing")
@TransactionConfiguration(transactionManager="txManagerSeguridad", defaultRollback=true)
@Transactional
public abstract class SeguridadBaseTest {

	@Before
	public void init(){
		this.crearTestAuthenticationDefault();
	}
	
	/**
	 * Crea el objeto Authentication que por default será usado para los
	 * test. Se puede cambiar usando {@link SeguridadBaseTest#crearTestAuthentication(String, String, List)}
	 * 
	 */
	protected void crearTestAuthenticationDefault(){
		RolSeguridad rol1 = new RolSeguridad("ROLE_USUARIO", true);
		RolSeguridad rol2 = new RolSeguridad("ROLE_USUARIO2", true);
		List<RolSeguridad> roles = new ArrayList<RolSeguridad>();
		roles.add(rol1);
		roles.add(rol2);
		
		this.crearTestAuthentication("brian", "b", roles);
	}
	
	/**
	 * Crea un objeto {@link Authentication} para fines de prueba y lo coloca dentro del
	 * {@link SecurityContextHolder} para que pueda ser usado durante la ejecución de los tests.
	 * 
	 * @param username
	 * @param password
	 * @param permisos
	 */
	protected void crearTestAuthentication(String username, String password, List<RolSeguridad> roles){
		UsuarioSeguridad us = new UsuarioSeguridad(username, password, "b", 1, true);
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for (RolSeguridad rol : roles) {
			if(rol.isActivo())
				authorities.add(new CustomGrantedAuthority(rol));
		}		
		CustomUserDetails userDetails = new CustomUserDetails(us, authorities);
		Authentication auth = new TestingAuthenticationToken(userDetails, username, authorities);
		auth.setAuthenticated(true);
        SecurityContextHolder.getContext().setAuthentication(auth);		
	}
}
