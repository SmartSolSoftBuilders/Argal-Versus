package mx.argal.servicios;

import mx.argal.dao.UsuarioDao;
import mx.argal.modelo.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import mx.sep.sajja.datos.vo.FiltroBusquedaVO;


@Service
public class UsuarioServicioImpl implements UsuarioServicio {

	@Autowired
	private UsuarioDao usuarioDao;

	@Override
	public Usuario obtenerUsuario() {
		// TODO Auto-generated method stub
		return usuarioDao.obtenerUsuario();
	}
	
	}
