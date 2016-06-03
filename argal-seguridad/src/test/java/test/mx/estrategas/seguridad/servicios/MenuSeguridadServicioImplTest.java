package test.mx.estrategas.seguridad.servicios;

import java.util.List;

import mx.argal.seguridad.modelo.ModuloMenu;
import mx.argal.seguridad.servicios.MenuSeguridadServicio;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.mx.estrategas.seguridad.SeguridadBaseTest;

public class MenuSeguridadServicioImplTest extends SeguridadBaseTest {
	
	@Autowired
	private MenuSeguridadServicio menuSeguridadServicio;

	@Test
	public void dependenciaTest(){
		Assert.assertNotNull(menuSeguridadServicio);
	}
	
	@Test
	public void consultarModulosTest(){
		List<ModuloMenu> modulos = menuSeguridadServicio.consultarModulos();
		
		//Assert.assertFalse(modulos.isEmpty());
		//Assert.assertEquals(2, modulos.size());
	}
}
