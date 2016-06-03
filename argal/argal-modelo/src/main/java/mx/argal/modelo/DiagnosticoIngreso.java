package mx.argal.modelo;

import java.util.Date;

public class DiagnosticoIngreso extends BaseModelo {
	
	private Integer idDiagnosticoIngreso;
	private String descripcion;
		
	public Integer getIdDiagnosticoIngreso() {
		return idDiagnosticoIngreso;
	}
	public void setIdDiagnosticoIngreso(int idDiagnosticoIngreso) {
		this.idDiagnosticoIngreso = idDiagnosticoIngreso;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public DiagnosticoIngreso(){
		
	}
	
	public DiagnosticoIngreso(Integer idDiagnosticoIngreso, String descripcion) {
		super();
		this.idDiagnosticoIngreso = idDiagnosticoIngreso;
		this.descripcion = descripcion;
	}
 	
}
