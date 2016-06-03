package mx.argal.modelo;

import java.util.Date;

public class GastoRegistroSeguroPersonal extends BaseModelo {
	
	private Integer idGastoRegistroSeguroPersonal;
	private Integer idTipoCargo;
	private Integer idArea;
	private Integer idRubro;
	private Integer idRazon;
	private Date fechaIngreso;
	private String nombre;
	private Float montoUnitario;
	private Float cantidad;
	private String rutaEvidencia;
	
	public Integer getIdGastoRegistroSeguroPersonal() {
		return idGastoRegistroSeguroPersonal;
	}
	public void setIdGastoRegistroSeguroPersonal(
			Integer idGastoRegistroSeguroPersonal) {
		this.idGastoRegistroSeguroPersonal = idGastoRegistroSeguroPersonal;
	}
	public Integer getIdTipoCargo() {
		return idTipoCargo;
	}
	public void setIdTipoCargo(Integer idTipoCargo) {
		this.idTipoCargo = idTipoCargo;
	}
	public Integer getIdArea() {
		return idArea;
	}
	public void setIdArea(Integer idArea) {
		this.idArea = idArea;
	}
	public Integer getIdRubro() {
		return idRubro;
	}
	public void setIdRubro(Integer idRubro) {
		this.idRubro = idRubro;
	}
	public Integer getIdRazon() {
		return idRazon;
	}
	public void setIdRazon(Integer idRazon) {
		this.idRazon = idRazon;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Float getMontoUnitario() {
		return montoUnitario;
	}
	public void setMontoUnitario(Float montoUnitario) {
		this.montoUnitario = montoUnitario;
	}
	public Float getCantidad() {
		return cantidad;
	}
	public void setCantidad(Float cantidad) {
		this.cantidad = cantidad;
	}
	public String getRutaEvidencia() {
		return rutaEvidencia;
	}
	public void setRutaEvidencia(String rutaEvidencia) {
		this.rutaEvidencia = rutaEvidencia;
	}
	
	public GastoRegistroSeguroPersonal(){
		
	}
	
	public GastoRegistroSeguroPersonal(Integer idGastoRegistroSeguroPersonal,
			Integer idTipoCargo, Integer idArea, Integer idRubro,
			Integer idRazon, Date fechaIngreso, String nombre,
			Float montoUnitario, Float cantidad, String rutaEvidencia) {
		super();
		this.idGastoRegistroSeguroPersonal = idGastoRegistroSeguroPersonal;
		this.idTipoCargo = idTipoCargo;
		this.idArea = idArea;
		this.idRubro = idRubro;
		this.idRazon = idRazon;
		this.fechaIngreso = fechaIngreso;
		this.nombre = nombre;
		this.montoUnitario = montoUnitario;
		this.cantidad = cantidad;
		this.rutaEvidencia = rutaEvidencia;
	}
		
}
