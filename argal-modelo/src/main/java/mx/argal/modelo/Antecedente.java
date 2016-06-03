package mx.argal.modelo;

import java.util.Date;

public class Antecedente extends BaseModelo {
	
	private Integer idAntecedente;
	private String descripcion;
	
	public Integer getIdAntecedente() {
		return idAntecedente;
	}
	
	public void setIdAntecedente(Integer idAntecedente) {
		this.idAntecedente = idAntecedente;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Antecedente(){
		
	}
	
	public Antecedente(Integer idAntecedente, String descripcion) {
		super();
		this.idAntecedente = idAntecedente;
		this.descripcion = descripcion;
	}

}
