package mx.argal.dao;

import java.util.List;

import mx.argal.modelo.Implant;
import mx.argal.modelo.Usuario;

public interface ImplantDao {
	
	public List<Implant> obtenerImplants();
	public List<Implant> obtenerImplantsActivos();
	public void agregarImplant(Implant implant);
	public void actualizarImplant(Implant implant);
	public void actualizarNickImplant(Implant implant);
	public boolean eliminarImplant(Implant implant);
	public Implant obtenerImplantById(Integer idImplant);
	public Implant obtenerImplantByNick(Implant implant);
	public int agregarPermisoHospImplant(Implant implant);
	public int eliminarPermisoHospImplant(Implant implant);
}
