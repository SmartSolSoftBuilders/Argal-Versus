package mx.argal.dao;

import java.util.List;

import mx.argal.modelo.Hospital;
import mx.argal.modelo.Implant;
import mx.argal.modelo.Usuario;

public interface HospitalDao {
	
	public List<Hospital> obtenerHospitales();
	public List<Hospital> obtenerHospitalesActivos();
	void agregarHospital(Hospital hospital);
	public void actualizarHospital(Hospital hospital);
	public boolean eliminarHospital(Hospital hospital);
	public List<Hospital> obtenerHospitalesByIdImplant(Integer idImplant);
	public List<Hospital> obtenerHospitalesSinPermisoByImplant(Integer idImplant);
	public Hospital obtenerHospitalById(Hospital hospital);
}
