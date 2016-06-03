package mx.argal.seguridad.util;

import mx.argal.seguridad.modelo.UsuarioSeguridad;

import mx.argal.seguridad.servicios.CustomUserDetails;
import mx.argal.seguridad.servicios.MenuSeguridadServicio;
import mx.argal.seguridad.servicios.MttoSeguridadServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Métodos de utilería para interactuar con el módulo de seguridad.
 * 
 * @author SmartSolutions
 *
 */
public class SeguridadUtil {
	
	public SeguridadUtil(){
		
	}
	
	@Autowired
	MttoSeguridadServicio mttoSeguridad;
	MenuSeguridadServicio mttoMenuSeguridad;
	
	public UsuarioSeguridad obtenerRoles(String username){
		
		return this.mttoSeguridad.consultarUsuarioConRoles(username);	
	}
	

	/**
	 * Trata de obtener la información del usuario autenticado para el hilo
	 * de ejecución actual.
	 * 
	 * Si no le es posible obtenerlo lanza un {@link RuntimeException}
	 * 
	 * @return Un objeto tipo {@link UsuarioSeguridad}
	 */
	public void agregarUsuarioSeguridad(UsuarioSeguridad usuario){
		this.mttoSeguridad.agregarUsuarioSeguridad(usuario);
	}
    public static UsuarioSeguridad getUsuarioActual() {
        if(SecurityContextHolder.getContext().
                getAuthentication() == null
           || !SecurityContextHolder.getContext().
                getAuthentication().
                getPrincipal().
                getClass().
                equals(CustomUserDetails.class)) {
            throw new RuntimeException("La sesión actual no ha sido autenticada", null);
        }

        CustomUserDetails cud = (CustomUserDetails) SecurityContextHolder.getContext().
                getAuthentication().
                getPrincipal();
        
        UsuarioSeguridad us = new UsuarioSeguridad(cud.getUsername(), "", cud.getNombre(), 1,cud.isEnabled());
        
        return us;
    }
}
