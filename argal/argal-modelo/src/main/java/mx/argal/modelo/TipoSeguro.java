package mx.argal.modelo;

import java.util.Date;

public class TipoSeguro extends BaseModelo {
	
	private int idTipoSeguro;
	private String descripcion;
	
	public int getIdTipoSeguro() {
		return idTipoSeguro;
	}
	public void setIdTipoSeguro(int idTipoSeguro) {
		this.idTipoSeguro = idTipoSeguro;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public TipoSeguro(){
		
	}
	public TipoSeguro(int idTipoSeguro, String descripcion) {
		super();
		this.idTipoSeguro = idTipoSeguro;
		this.descripcion = descripcion;
	}
			
}
