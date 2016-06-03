package mx.argal.modelo;

import java.util.Date;

public class Icd extends BaseModelo {
	
	private Integer idIcd;
	private String descripcion;
	private String clave;
		
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public Integer getIdIcd() {
		return idIcd;
	}
	public void setIdIcd(int idIcd) {
		this.idIcd = idIcd;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Icd(){
		
	}
	
	public Icd(Integer idIcd, String descripcion,String clave) {
		super();
		this.idIcd = idIcd;
		this.descripcion = descripcion;
		this.clave=clave;
	}
 	
}
