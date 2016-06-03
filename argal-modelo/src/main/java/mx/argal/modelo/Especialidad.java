package mx.argal.modelo;

import java.util.Date;

public class Especialidad extends BaseModelo {
	
	private Integer idEspecialidad;
	private String descripcion;
	
	public Integer getIdEspecialidad() {
		return idEspecialidad;
	}
	public void setIdEspecialidad(Integer idEspecialidad) {
		this.idEspecialidad = idEspecialidad;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
		
	public Especialidad(){
		
	}
	
	public Especialidad(Integer idEspecialidad, String descripcion) {
		super();
		this.idEspecialidad = idEspecialidad;
		this.descripcion = descripcion;
	}
 	
}
