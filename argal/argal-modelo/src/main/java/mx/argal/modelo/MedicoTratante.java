package mx.argal.modelo;

import java.util.Date;
import java.util.List;

public class MedicoTratante extends BaseModelo {
	
	private Integer idMedicoTratante;
	private String nombre;
	private List<Especialidad> especialidades;
	private Integer tipo;
	
	public Integer getTipo() {
		return tipo;
	}
	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}
	public List<Especialidad> getEspecialidades() {
		return especialidades;
	}	
	public void setEspecialidades(
			List<Especialidad> especialidades) {
		this.especialidades = especialidades;
	}
	public Integer getIdMedicoTratante() {
		return idMedicoTratante;
	}
	public void setIdMedicoTratante(Integer idMedicoTratante) {
		this.idMedicoTratante = idMedicoTratante;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public MedicoTratante(){
		
	}
	
	public MedicoTratante(Integer idMedicoTratante, String nombre,List<Especialidad> especialidades, Integer tipo) {
		super();
		this.idMedicoTratante = idMedicoTratante;
		this.nombre = nombre;
		this.especialidades=especialidades;
		this.tipo=tipo;
	}

}
