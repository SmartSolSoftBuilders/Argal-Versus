package mx.argal.modelo;

import java.util.Date;

public class TipoEvento extends BaseModelo {
	
	private int idTipoEvento;
	private String descripcion;
	
	public int getIdTipoEvento() {
		return idTipoEvento;
	}
	public void setIdTipoEvento(int idTipoEvento) {
		this.idTipoEvento = idTipoEvento;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public TipoEvento(){
		
	}
	public TipoEvento(int idTipoEvento, String descripcion) {
		super();
		this.idTipoEvento = idTipoEvento;
		this.descripcion = descripcion;
	}
		
}
