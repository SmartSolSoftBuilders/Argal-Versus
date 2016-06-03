package mx.argal.modelo;

import java.util.Date;
import java.util.List;

public class Implant extends BaseModelo {
	
	private Integer idImplant;	
	private String nombreImplant;	
	private String appImplant;
	private String apmImplant;
	private String nextelImplant;
	private String idNextelImplant;
	private String celularImplant;
	private String telOfiImplant;
	private String puestoImplant;	
	private String emailInstImplant;
	private String emailPersImplant;
	private boolean superMedico;
	private boolean activoImplant;
	private List<Hospital> hospitalesConPermisos;
	private String nickImplant;
	
	public String getNickImplant() {
		return nickImplant;
	}

	public void setNickImplant(String nickImplant) {
		this.nickImplant = nickImplant;
	}

	public boolean getActivoImplant() {
		return activoImplant;
	}
	
	public void setActivoImplant(boolean activoImplant) {
		this.activoImplant = activoImplant;
	}


	public List<Hospital> getHospitalesConPermisos() {
		return hospitalesConPermisos;
	}

	public void setHospitalesConPermisos(List<Hospital> hospitalesConPermisos) {
		this.hospitalesConPermisos = hospitalesConPermisos;
	}
	
	public String getPuestoImplant() {
		return puestoImplant;
	}

	public void setPuestoImplant(String puestoImplant) {
		this.puestoImplant = puestoImplant;
	}

    public Integer getIdImplant() {
		return idImplant;
	}

	public void setIdImplant(Integer idImplant) {
		this.idImplant = idImplant;
	}

	public String getNombreImplant() {
		return nombreImplant;
	}

	public void setNombreImplant(String nombreImplant) {
		this.nombreImplant = nombreImplant;
	}

	public String getAppImplant() {
		return appImplant;
	}

	public void setAppImplant(String appImplant) {
		this.appImplant = appImplant;
	}

	public String getApmImplant() {
		return apmImplant;
	}

	public void setApmImplant(String apmImplant) {
		this.apmImplant = apmImplant;
	}

	public String getNextelImplant() {
		return nextelImplant;
	}

	public void setNextelImplant(String nextelImplant) {
		this.nextelImplant = nextelImplant;
	}

	public String getIdNextelImplant() {
		return idNextelImplant;
	}

	public void setIdNextelImplant(String idNextelImplant) {
		this.idNextelImplant = idNextelImplant;
	}

	public String getCelularImplant() {
		return celularImplant;
	}

	public void setCelularImplant(String celularImplant) {
		this.celularImplant = celularImplant;
	}

	public String getTelOfiImplant() {
		return telOfiImplant;
	}

	public void setTelOfiImplant(String telOfiImplant) {
		this.telOfiImplant = telOfiImplant;
	}

	public String getEmailInstImplant() {
		return emailInstImplant;
	}

	public void setEmailInstImplant(String emailInstImplant) {
		this.emailInstImplant = emailInstImplant;
	}

	public String getEmailPersImplant() {
		return emailPersImplant;
	}

	public void setEmailPersImplant(String emailPersImplant) {
		this.emailPersImplant = emailPersImplant;
	}

	public boolean getSuperMedico() {
		return superMedico;
	}

	public void setSuperMedico(boolean superMedico) {
		this.superMedico = superMedico;
	}

	public Implant() {
    }

	public Implant(Integer idImplant, String nombreImplant, String appImplant,
			String apmImplant, String nextelImplant, String idNextelImplant,
			String celularImplant, String telOfiImplant, String puestoImplant,
			String emailInstImplant, String emailPersImplant,
			boolean superMedico, boolean activoImplant, List<Hospital> hospitalesConPermisos, String nickImplant) {
		super();
		this.idImplant = idImplant;
		this.nombreImplant = nombreImplant;
		this.appImplant = appImplant;
		this.apmImplant = apmImplant;
		this.nextelImplant = nextelImplant;
		this.idNextelImplant = idNextelImplant;
		this.celularImplant = celularImplant;
		this.telOfiImplant = telOfiImplant;
		this.puestoImplant = puestoImplant;
		this.emailInstImplant = emailInstImplant;
		this.emailPersImplant = emailPersImplant;
		this.superMedico = superMedico;
		this.activoImplant= activoImplant;
		this.hospitalesConPermisos=hospitalesConPermisos;
		this.nickImplant=nickImplant;
	}
}
