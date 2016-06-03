package mx.argal.modelo;

import java.util.Date;

public class Cpt extends BaseModelo {
	
	private Integer idCpt;
	private String descripcion;
	private String cveCpt;
	
	public String getCveCpt() {
		return cveCpt;
	}
	public void setCveCpt(String cveCpt) {
		this.cveCpt = cveCpt;
	}
	public Integer getIdCpt() {
		return idCpt;
	}
	public void setIdCpt(Integer idCpt) {
		this.idCpt = idCpt;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Cpt(){
		
	}
	public Cpt(Integer idCpt, String descripcion,String cveCpt) {
		super();
		this.idCpt = idCpt;
		this.descripcion = descripcion;
		this.cveCpt=cveCpt;
	}

}
