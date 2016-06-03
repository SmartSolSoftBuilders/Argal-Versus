package mx.argal.modelo;

import java.util.Date;

public class Hospital extends BaseModelo {
	private Integer idHospital;
	private String nombreHospital;
	private String direccionHospital;
	private String coloniaHospital;
	private String municipioDelHospital;
	private String estadoHospital;
	private String cpHospital;
	private String conmutadorHospital;
	private String telModuloHospital;
	private String ciudadHospital;
	private String paisHospital;
	private boolean activoHospital;
	
	public String getCiudadHospital() {
		return ciudadHospital;
	}
	public void setCiudadHospital(String ciudadHospital) {
		this.ciudadHospital = ciudadHospital;
	}
	public String getPaisHospital() {
		return paisHospital;
	}
	public void setPaisHospital(String paisHospital) {
		this.paisHospital = paisHospital;
	}

	
	
	public String getTelModuloHospital() {
		return telModuloHospital;
	}
	public void setTelModuloHospital(String telModuloHospital) {
		this.telModuloHospital = telModuloHospital;
	}
	public boolean isActivoHospital() {
		return activoHospital;
	}
	public void setActivoHospital(boolean activoHospital) {
		this.activoHospital = activoHospital;
	}
	public Integer getIdHospital() {
		return idHospital;
	}
	public void setIdHospital(Integer idHospital) {
		this.idHospital = idHospital;
	}
	public String getNombreHospital() {
		return nombreHospital;
	}
	public void setNombreHospital(String nombreHospital) {
		this.nombreHospital = nombreHospital;
	}
	public String getDireccionHospital() {
		return direccionHospital;
	}
	public void setDireccionHospital(String direccionHospital) {
		this.direccionHospital = direccionHospital;
	}
	public String getColoniaHospital() {
		return coloniaHospital;
	}
	public void setColoniaHospital(String coloniaHospital) {
		this.coloniaHospital = coloniaHospital;
	}
	public String getMunicipioDelHospital() {
		return municipioDelHospital;
	}
	public void setMunicipioDelHospital(String municipioDelHospital) {
		this.municipioDelHospital = municipioDelHospital;
	}
	public String getEstadoHospital() {
		return estadoHospital;
	}
	public void setEstadoHospital(String estadoHospital) {
		this.estadoHospital = estadoHospital;
	}
	public String getCpHospital() {
		return cpHospital;
	}
	public void setCpHospital(String cpHospital) {
		this.cpHospital = cpHospital;
	}
	public String getConmutadorHospital() {
		return conmutadorHospital;
	}
	public void setConmutadorHospital(String conmutadorHospital) {
		this.conmutadorHospital = conmutadorHospital;
	}
	
	public Hospital(){
		
	}
	
	public Hospital(Integer idHospital, String nombreHospital,
			String direccionHospital, String coloniaHospital,
			String municipioDelHospital, String estadoHospital, String cpHospital,
			String conmutadorHospital, boolean activoHospital, String telModuloHospital,String ciudadHospital, String paisHospital) {
		super();
		this.idHospital = idHospital;
		this.nombreHospital = nombreHospital;
		this.direccionHospital = direccionHospital;
		this.coloniaHospital = coloniaHospital;
		this.municipioDelHospital = municipioDelHospital;
		this.estadoHospital = estadoHospital;
		this.cpHospital = cpHospital;
		this.conmutadorHospital = conmutadorHospital;
		this.telModuloHospital=telModuloHospital;
		this.ciudadHospital=ciudadHospital;
		this.paisHospital=paisHospital;
		this.activoHospital = activoHospital;
	}
	
}
