package mx.argal.servicios;

import java.util.List;

import mx.argal.modelo.Hospital;
import mx.argal.modelo.Implant;
import mx.argal.modelo.Usuario;
//import mx.sep.sajja.datos.vo.FiltroBusquedaVO;
 
public interface HospitalServicio {
	List<Hospital> obtenerHospitales();
	List<Hospital> obtenerHospitalesActivos();
	boolean agregarHospital(Hospital hospital);
	boolean actualizarHospital(Hospital hospital);
	boolean eliminarHospital(Hospital hospital);
	List<Hospital> obtenerHospitalesByImplant(Implant implant);
	List<Hospital> obtenerHospitalesSinPermisoByImplant(Implant implant);
	Hospital obtenerHospitalById(Hospital hospital);
}
