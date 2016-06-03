package mx.argal.servicios;

import java.util.List;

import mx.argal.dao.HospitalDao;
import mx.argal.dao.ImplantDao;
import mx.argal.dao.UsuarioDao;
import mx.argal.modelo.Hospital;
import mx.argal.modelo.Implant;
import mx.argal.modelo.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import mx.sep.sajja.datos.vo.FiltroBusquedaVO;


@Service
public class HospitalServicioImpl implements HospitalServicio {

	@Autowired
	private HospitalDao hospitalDao;

	@Override
	public List<Hospital> obtenerHospitales() {
		// TODO Auto-generated method stub
		return this.hospitalDao.obtenerHospitales();
	}
	
	@Override
	public List<Hospital> obtenerHospitalesActivos() {
		// TODO Auto-generated method stub
		return this.hospitalDao.obtenerHospitalesActivos();
	}

	@Override
	public boolean agregarHospital(Hospital hospital) {
		// TODO Auto-generated method stub
		try{
			hospital=this.changeToUpperCase(hospital);
			hospital.setActivoHospital(true);
			this.hospitalDao.agregarHospital(hospital);
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private Hospital changeToUpperCase(Hospital hospital) {
		// TODO Auto-generated method stub		
		hospital.setNombreHospital(hospital.getNombreHospital().toUpperCase());
		hospital.setDireccionHospital(hospital.getDireccionHospital());
		if (hospital.getEstadoHospital()!=null)
			hospital.setEstadoHospital(hospital.getEstadoHospital().toUpperCase());
		hospital.setMunicipioDelHospital(hospital.getMunicipioDelHospital());
		return hospital;
	}
	
	@Override
	public boolean eliminarHospital(Hospital hospital) {
		// TODO Auto-generated method stub
		try{
			hospital.setActivoHospital(false);
			this.hospitalDao.eliminarHospital(hospital);
			return true;
		}
		catch(Exception e){
			return false;		
		}
	}
	
	public boolean actualizarHospital(Hospital hospital) {
		// TODO Auto-generated method stub
		try{
			hospital = this.changeToUpperCase(hospital);
			hospital.setActivoHospital(true);
			hospitalDao.actualizarHospital(hospital);
			return true;
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("<OTIKA>Error!"+e.getMessage());
			return false;
		}
		
	}

	@Override
	public List<Hospital> obtenerHospitalesByImplant(Implant implant) {
		// TODO Auto-generated method stub
		return this.hospitalDao.obtenerHospitalesByIdImplant(implant.getIdImplant());
	}

	@Override
	public List<Hospital> obtenerHospitalesSinPermisoByImplant(Implant implant) {
		// TODO Auto-generated method stub
		return this.hospitalDao.obtenerHospitalesSinPermisoByImplant(implant.getIdImplant());
	}

	@Override
	public Hospital obtenerHospitalById(Hospital hospital) {
		// TODO Auto-generated method stub
		return this.hospitalDao.obtenerHospitalById(hospital);
	}

}
