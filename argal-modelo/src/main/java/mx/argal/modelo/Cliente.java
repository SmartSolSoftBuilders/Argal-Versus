package mx.argal.modelo;

import java.util.Date;

public class Cliente extends BaseModelo {
	
	private Integer idCliente;
	private String razonSocialCliente;
	private String domicilioCliente;
	private String conmutadorCliente;
	private String directorMedicoCliente;
	private String nombreCliente;
	private String telOfiCliente;
	private String telCelCliente;
	private String emailCliente;
	private boolean activoCliente;
	private String nickCliente;
	private String nombreCorto;
	
			
	public String getNombreCorto() {
		return nombreCorto;
	}
	public void setNombreCorto(String nombreCorto) {
		this.nombreCorto = nombreCorto;
	}
	public String getNickCliente() {
		return nickCliente;
	}
	public void setNickCliente(String nickCliente) {
		this.nickCliente = nickCliente;
	}
	public Integer getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}
	public String getRazonSocialCliente() {
		return razonSocialCliente;
	}
	public void setRazonSocialCliente(String razonSocialCliente) {
		this.razonSocialCliente = razonSocialCliente;
	}
	public String getDomicilioCliente() {
		return domicilioCliente;
	}
	public void setDomicilioCliente(String domicilioCliente) {
		this.domicilioCliente = domicilioCliente;
	}
	public String getConmutadorCliente() {
		return conmutadorCliente;
	}
	public void setConmutadorCliente(String conmutadorCliente) {
		this.conmutadorCliente = conmutadorCliente;
	}
	public String getDirectorMedicoCliente() {
		return directorMedicoCliente;
	}
	public void setDirectorMedicoCliente(String directorMedicoCliente) {
		this.directorMedicoCliente = directorMedicoCliente;
	}
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getTelOfiCliente() {
		return telOfiCliente;
	}	
	public void setTelOfiCliente(String telOfiCliente) {
		this.telOfiCliente = telOfiCliente;
	}
	public String getTelCelCliente() {
		return telCelCliente;
	}
	public void setTelCelCliente(String telCelCliente) {
		this.telCelCliente = telCelCliente;
	}
	public String getEmailCliente() {
		return emailCliente;
	}
	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}
	public boolean isActivoCliente() {
		return activoCliente;
	}
	public void setActivoCliente(boolean activoCliente) {
		this.activoCliente = activoCliente;
	}
	public Cliente(){
		
	}
	
	public Cliente(Integer idCliente, String razonSocialCliente,
			String domicilioCliente, String conmutadorCliente,
			String directorMedicoCliente, String nombreCliente,
			String telOfiCliente, String telCelCliente, String emailCliente,
			boolean activoCliente, String nickCliente, String nombreCorto) {
		super();
		this.idCliente = idCliente;
		this.razonSocialCliente = razonSocialCliente;
		this.domicilioCliente = domicilioCliente;
		this.conmutadorCliente = conmutadorCliente;
		this.directorMedicoCliente = directorMedicoCliente;
		this.nombreCliente = nombreCliente;
		this.telOfiCliente = telOfiCliente;
		this.telCelCliente = telCelCliente;
		this.emailCliente = emailCliente;
		this.activoCliente = activoCliente;
		this.nickCliente = nickCliente;
		this.nombreCorto=nombreCorto;
	}
		
}
