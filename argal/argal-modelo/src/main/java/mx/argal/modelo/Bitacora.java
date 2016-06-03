package mx.argal.modelo;

import java.util.Date;

public class Bitacora extends BaseModelo {
	
	private Integer idBitacora;
	private Integer idRegistroMedico;
	private String descripcion;
	private String observaciones;
	private String interconsulta;
	
	public String getInterconsulta() {
		return interconsulta;
	}
	public void setInterconsulta(String interconsulta) {
		this.interconsulta = interconsulta;
	}

	private Date fechaBitacora;
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Date getFechaBitacora() {
		return fechaBitacora;
	}
	public void setFechaBitacora(Date fechaBitacora) {
		this.fechaBitacora = fechaBitacora;
	}
	public Integer getIdBitacora() {
		return idBitacora;
	}
	public void setIdBitacora(Integer idBitacora) {
		this.idBitacora = idBitacora;
	}
	public Integer getIdRegistroMedico() {
		return idRegistroMedico;
	}
	public void setIdRegistroMedico(Integer idRegistroMedico) {
		this.idRegistroMedico = idRegistroMedico;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	public Bitacora(){
		
	}
	
	public Bitacora(Integer idBitacora, Integer idRegistroMedico,
			String observaciones, String interconsulta) {
		super();
		this.idBitacora = idBitacora;
		this.idRegistroMedico = idRegistroMedico;
		this.observaciones = observaciones;
		this.interconsulta=interconsulta;
	}
	
}
