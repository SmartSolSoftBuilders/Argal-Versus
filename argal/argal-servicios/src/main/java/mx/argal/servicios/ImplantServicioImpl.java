package mx.argal.servicios;

import java.util.List;

import mx.argal.dao.ImplantDao;
import mx.argal.dao.UsuarioDao;
import mx.argal.modelo.Implant;
import mx.argal.modelo.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import mx.sep.sajja.datos.vo.FiltroBusquedaVO;


@Service
public class ImplantServicioImpl implements ImplantServicio {

	@Autowired
	private ImplantDao implantDao;
	
	@Override
	public List<Implant> obtenerImplants() {
		// TODO Auto-generated method stub
		return this.implantDao.obtenerImplants();
	}

	@Override
	public List<Implant> obtenerImplantsActivos() {
		// TODO Auto-generated method stub
		try{
			return this.implantDao.obtenerImplantsActivos();
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	} 

	@Override
	public boolean agregarImplant(Implant implant) {
		// TODO Auto-generated method stub
		try{
			implant = this.changeToUpperCase(implant);
			implant.setActivoImplant(true);
			implantDao.agregarImplant(implant);
			return true;
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("<OTIKA>Error!"+e.getMessage());
			return false;
		}
		
	}

	private Implant changeToUpperCase(Implant implant) {
		// TODO Auto-generated method stub
		implant.setNombreImplant(implant.getNombreImplant().toUpperCase());
		implant.setAppImplant(implant.getAppImplant().toUpperCase());
		implant.setApmImplant(implant.getApmImplant().toUpperCase());
		implant.setPuestoImplant(implant.getPuestoImplant().toUpperCase());		
		return implant;
	}

	@Override
	public boolean eliminarImplant(Implant implant) {
		// TODO Auto-generated method stub
		try{
			implant.setActivoImplant(false);
			this.implantDao.eliminarImplant(implant);
			return true;
		}
		catch(Exception e){
			return false;		
		}
	}
	
	public boolean actualizarNickImplant(Implant implant) {
		// TODO Auto-generated method stub
		try{			
			implantDao.actualizarNickImplant(implant);
			return true;
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("<OTIKA>Error!"+e.getMessage());
			return false;
		}
		
	}
	
	public boolean actualizarImplant(Implant implant) {
		// TODO Auto-generated method stub
		try{
			implant = this.changeToUpperCase(implant);
			implant.setActivoImplant(true);
			implantDao.actualizarImplant(implant);
			return true;
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("<OTIKA>Error!"+e.getMessage());
			return false;
		}
		
	}

	@Override
	public Implant obtenerImplantById(Integer idImplant) {
		// TODO Auto-generated method stub
		return this.implantDao.obtenerImplantById(idImplant);
	}
	
	@Override
	public Implant obtenerImplantByNick(Implant implant) {
		// TODO Auto-generated method stub
		return this.implantDao.obtenerImplantByNick(implant);
	}

	@Override
	public boolean agregarPermisoHospImplant(Implant implant) {
		// TODO Auto-generated method stub
		try{
			int returnValue=this.implantDao.agregarPermisoHospImplant(implant);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean eliminarPermisoHospImplant(Implant implant) {
		// TODO Auto-generated method stub
		try{
			int valor=this.implantDao.eliminarPermisoHospImplant(implant);
			if (valor >0)
				return true;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return false;	
	}

}
