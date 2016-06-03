package mx.argal.servicios;

import java.util.List;
import java.util.ArrayList;

import mx.argal.dao.EspecialidadDao;
import mx.argal.modelo.Especialidad;
import mx.argal.modelo.MedicoTratante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import mx.sep.sajja.datos.vo.FiltroBusquedaVO;


@Service
public class EspecialidadServicioImpl implements EspecialidadServicio {

	@Autowired
	private EspecialidadDao especialidadDao;
	@Override
	public List<Especialidad> obtenerEspecialidades() {
		// TODO Auto-generated method stub
		try{
			return this.especialidadDao.obtenerEspecialidades();
		}
		catch (Exception e){			
			e.printStackTrace();
			return null;
		}
	}
	
}
