package mx.argal.modelo;

import java.sql.Blob;
import java.util.Date;

public class Gasto extends BaseModelo {
	
	private Integer idGasto;	
	private Integer idTipoCargo;
	private Integer idArea;
	private Integer idRubro;
	private Integer idRazon;
	private Date fechaIngreso;
	private String nombre;
	private Float montoUnitario;
	private Float cantidad;
	private String rutaEvidencia;
	private byte[]  archivo;
	
	public byte[]  getArchivo() {
		return archivo;
	}
	public void setArchivo(byte[]  archivo) {
		this.archivo = archivo;
	}
	public Integer getIdGasto() {
		return idGasto;
	}
	public void setIdGasto(
			Integer idGasto) {
		this.idGasto=idGasto;
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
	
	public Gasto(){
		
	}
	
	public Gasto(Integer idGasto,
			Integer idTipoCargo, Integer idArea, Integer idRubro,
			Integer idRazon, Date fechaIngreso, String nombre,
			Float montoUnitario, Float cantidad, String rutaEvidencia,byte[]  archivo) {
		super();
		this.idGasto = idGasto;
		this.idTipoCargo = idTipoCargo;
		this.idArea = idArea;
		this.idRubro = idRubro;
		this.idRazon = idRazon;
		this.fechaIngreso = fechaIngreso;
		this.nombre = nombre;
		this.montoUnitario = montoUnitario;
		this.cantidad = cantidad;
		this.rutaEvidencia = rutaEvidencia;
		this.archivo=archivo;
	}
		
}
