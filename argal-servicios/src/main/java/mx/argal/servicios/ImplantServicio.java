package mx.argal.servicios;

import java.util.List;

import mx.argal.modelo.Implant;
import mx.argal.modelo.Usuario;
//import mx.sep.sajja.datos.vo.FiltroBusquedaVO;
 
public interface ImplantServicio {
	List<Implant> obtenerImplants();
	List<Implant> obtenerImplantsActivos();
	boolean agregarImplant(Implant implant);
	boolean actualizarImplant(Implant implant);
	boolean actualizarNickImplant(Implant implant);
	boolean eliminarImplant(Implant implant);
	Implant obtenerImplantById(Integer idImplant);
	Implant obtenerImplantByNick(Implant implant);
	boolean agregarPermisoHospImplant(Implant implant);
	boolean eliminarPermisoHospImplant(Implant implant);
}
