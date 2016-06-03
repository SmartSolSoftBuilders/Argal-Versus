package mx.argal.seguridad.dao;

import java.util.List;

import mx.argal.seguridad.modelo.RolSeguridad;
import mx.argal.seguridad.modelo.UsuarioSeguridad;

/**
 * Servicios de acceso a datos para roles y usuario de seguridad.
 * 
 * @author SmartSolutions
 *
 */
public interface SeguridadDao {

	List<UsuarioSeguridad> consultarUsuarios();
	
	List<RolSeguridad> consultarRoles();
	
	UsuarioSeguridad consultarUsuarioConRoles(String username);
	
	List<UsuarioSeguridad> consultarUsuariosConRoles();
	
	void agregarUsuarioSeguridad(UsuarioSeguridad usuario);
	
	void actualizarUsuarioSeguridadUno(UsuarioSeguridad usuario);
	
	void actualizarUsuarioSeguridadDos(UsuarioSeguridad usuario);
	
	List<UsuarioSeguridad> consultarUsuariosByUser(UsuarioSeguridad usuario);

	void actualizarPassword(UsuarioSeguridad usuario);

	void actualizarUsuario(UsuarioSeguridad usuarioSeguridad);
}
