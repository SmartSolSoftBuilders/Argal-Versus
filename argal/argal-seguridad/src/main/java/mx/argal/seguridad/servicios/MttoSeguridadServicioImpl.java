package mx.argal.seguridad.servicios;

import java.util.List;


import mx.argal.seguridad.dao.SeguridadDao;
import mx.argal.seguridad.modelo.UsuarioSeguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MttoSeguridadServicioImpl implements MttoSeguridadServicio{

	@Autowired
	private SeguridadDao seguridadDao;
	
	public List<UsuarioSeguridad> consultarUsuarios() {
		return seguridadDao.consultarUsuarios();
	}

	public List<UsuarioSeguridad> consultarUsuariosConRoles() {
		return seguridadDao.consultarUsuariosConRoles();
	}
	
	@Override
	public UsuarioSeguridad consultarUsuarioConRoles(String username){
		return seguridadDao.consultarUsuarioConRoles(username);
	}

	@Override
	public void agregarUsuarioSeguridad(UsuarioSeguridad usuario) {
		// TODO Auto-generated method stub
		seguridadDao.agregarUsuarioSeguridad(usuario);
		
	}

	@Override
	public List<UsuarioSeguridad> consultarUsuariosByUser(
			UsuarioSeguridad usuario) {
		// TODO Auto-generated method stub
		return seguridadDao.consultarUsuariosByUser(usuario);
	}
	
	
	@Override
	public void actualizarPassword(UsuarioSeguridad usuario) {
		// TODO Auto-generated method stub
		this.seguridadDao.actualizarPassword(usuario);
	}

	@Override
	public void actualizarUsuario(UsuarioSeguridad usuarioSeguridad) {
		// TODO Auto-generated method stub
		this.seguridadDao.actualizarUsuario(usuarioSeguridad);		
	}

	@Override
	public void actualizarUsuarioSeguridadUno(UsuarioSeguridad usuario) {
		// TODO Auto-generated method stub
		this.seguridadDao.actualizarUsuarioSeguridadUno(usuario);
	}

	@Override
	public void actualizarUsuarioSeguridadDos(UsuarioSeguridad usuario) {
		// TODO Auto-generated method stub
		this.seguridadDao.actualizarUsuarioSeguridadDos(usuario);
	}
}
