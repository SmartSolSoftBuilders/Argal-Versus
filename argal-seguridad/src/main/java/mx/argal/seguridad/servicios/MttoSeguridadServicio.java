package mx.argal.seguridad.servicios;

import java.util.List;

import mx.argal.seguridad.modelo.UsuarioSeguridad;

public interface MttoSeguridadServicio {

	List<UsuarioSeguridad> consultarUsuarios();	
	List<UsuarioSeguridad> consultarUsuariosConRoles();
	List<UsuarioSeguridad> consultarUsuariosByUser(UsuarioSeguridad usuario);
	UsuarioSeguridad consultarUsuarioConRoles(String username);
	void agregarUsuarioSeguridad(UsuarioSeguridad usuario);
	void actualizarUsuarioSeguridadUno(UsuarioSeguridad usuario);
	void actualizarUsuarioSeguridadDos(UsuarioSeguridad usuario);
	void actualizarPassword(UsuarioSeguridad usuario);
	void actualizarUsuario(UsuarioSeguridad usuarioSeguridad);
	
}
