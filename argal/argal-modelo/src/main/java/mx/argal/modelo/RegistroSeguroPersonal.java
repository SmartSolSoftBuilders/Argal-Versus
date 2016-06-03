package mx.argal.modelo;

import java.util.Date;
import java.util.List;

public class RegistroSeguroPersonal extends BaseModelo {
	
	
	private Integer idRegistroSeguroPersonal;
	private Integer idEvento;
	private String numeroNomina;
	private String institucion;
	private String nombreTitular;		
	private String appTitular;
	private String apmTitular;
	private String nombrePaciente;
	private String appPaciente;
	private String apmPaciente;
	private Integer edadPaciente;
	private String unidadEdadPaciente;
	private String sexoPaciente;
	private String condicionPaciente;
	private String relacionPaciente;
	
	private String numAutorizacion;
	private List <GastoRegistroSeguroPersonal> gastosRegistroSeguroPersonal;
	private String capita;
	private String censo;
	private Float cantidadCubiertaConvenio;
	
	
	public String getCenso() {
		return censo;
	}
	public void setCenso(String censo) {
		this.censo = censo;
	}
	public Float getCantidadCubiertaConvenio() {
		return cantidadCubiertaConvenio;
	}
	public void setCantidadCubiertaConvenio(Float cantidadCubiertaConvenio) {
		this.cantidadCubiertaConvenio = cantidadCubiertaConvenio;
	}

	//Registro de Nacimiento
	private Date nacimientoFechaNacimiento;
	private String nacimientoHoraNacimiento;
	private String nacimientoTipoParto;
	private String nacimientoTalla;
	private String nacimientoPeso;
	private String nacimientoApgar;
	private String nacimientoMedicoTratante;
	
	
	public Date getNacimientoFechaNacimiento() {
		return nacimientoFechaNacimiento;
	}
	public void setNacimientoFechaNacimiento(Date nacimientoFechaNacimiento) {
		this.nacimientoFechaNacimiento = nacimientoFechaNacimiento;
	}
	public String getNacimientoHoraNacimiento() {
		return nacimientoHoraNacimiento;
	}
	public void setNacimientoHoraNacimiento(String nacimientoHoraNacimiento) {
		this.nacimientoHoraNacimiento = nacimientoHoraNacimiento;
	}
	public String getNacimientoTipoParto() {
		return nacimientoTipoParto;
	}
	public void setNacimientoTipoParto(String nacimientoTipoParto) {
		this.nacimientoTipoParto = nacimientoTipoParto;
	}
	public String getNacimientoTalla() {
		return nacimientoTalla;
	}
	public void setNacimientoTalla(String nacimientoTalla) {
		this.nacimientoTalla = nacimientoTalla;
	}
	public String getNacimientoPeso() {
		return nacimientoPeso;
	}
	public void setNacimientoPeso(String nacimientoPeso) {
		this.nacimientoPeso = nacimientoPeso;
	}
	public String getNacimientoApgar() {
		return nacimientoApgar;
	}
	public void setNacimientoApgar(String nacimientoApgar) {
		this.nacimientoApgar = nacimientoApgar;
	}
	public String getNacimientoMedicoTratante() {
		return nacimientoMedicoTratante;
	}
	public void setNacimientoMedicoTratante(String nacimientoMedicoTratante) {
		this.nacimientoMedicoTratante = nacimientoMedicoTratante;
	}
	public String getCapita() {
		return this.capita;
	}		
	public void setCapita(String capita){
		this.capita=capita;
	}
	
	public List<GastoRegistroSeguroPersonal> getGastosRegistroSeguroPersonal() {
		return gastosRegistroSeguroPersonal;
	}
	public void setGastosRegistroSeguroPersonal(
			List<GastoRegistroSeguroPersonal> gastosRegistroSeguroPersonal) {
		this.gastosRegistroSeguroPersonal = gastosRegistroSeguroPersonal;
	}
		
	public Integer getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(Integer idEvento) {
		this.idEvento = idEvento;
	}
	public String getNumAutorizacion() {
		return numAutorizacion;
	}
	public void setNumAutorizacion(String numAutorizacion) {
		this.numAutorizacion = numAutorizacion;
	}		
	public Integer getIdRegistroSeguroPersonal() {
		return idRegistroSeguroPersonal;
	}
	public void setIdRegistroSeguroPersonal(Integer idRegistroSeguroPersonal) {
		this.idRegistroSeguroPersonal = idRegistroSeguroPersonal;
	}
	public String getNumeroNomina() {
		return numeroNomina;
	}
	public void setNumeroNomina(String numeroNomina) {
		this.numeroNomina = numeroNomina;
	}
	public String getInstitucion() {
		return institucion;
	}
	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}
	public String getNombreTitular() {
		return nombreTitular;
	}
	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}
	public String getAppTitular() {
		return appTitular;
	}
	public void setAppTitular(String appTitular) {
		this.appTitular = appTitular;
	}
	public String getApmTitular() {
		return apmTitular;
	}
	public void setApmTitular(String apmTitular) {
		this.apmTitular = apmTitular;
	}
	public String getNombrePaciente() {
		return nombrePaciente;
	}
	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}
	public String getAppPaciente() {
		return appPaciente;
	}
	public void setAppPaciente(String appPaciente) {
		this.appPaciente = appPaciente;
	}
	public String getApmPaciente() {
		return apmPaciente;
	}
	public void setApmPaciente(String apmPaciente) {
		this.apmPaciente = apmPaciente;
	}
	public Integer getEdadPaciente() {
		return edadPaciente;
	}
	public void setEdadPaciente(Integer edadPaciente) {
		this.edadPaciente = edadPaciente;
	}
	public String getUnidadEdadPaciente() {
		return unidadEdadPaciente;
	}
	public void setUnidadEdadPaciente(String unidadEdadPaciente) {
		this.unidadEdadPaciente = unidadEdadPaciente;
	}
	public String getSexoPaciente() {
		return sexoPaciente;
	}
	public void setSexoPaciente(String sexoPaciente) {
		this.sexoPaciente = sexoPaciente;
	}
	public String getCondicionPaciente() {
		return condicionPaciente;
	}
	public void setCondicionPaciente(String condicionPaciente) {
		this.condicionPaciente = condicionPaciente;
	}
	public String getRelacionPaciente() {
		return relacionPaciente;
	}
	public void setRelacionPaciente(String relacionPaciente) {
		this.relacionPaciente = relacionPaciente;
	}
	
	public RegistroSeguroPersonal(){
		
	}
	
	public RegistroSeguroPersonal(Integer idRegistroSeguroPersonal,
			String numeroNomina, String institucion, String nombreTitular,
			String appTitular, String apmTitular, String nombrePaciente,
			String appPaciente, String apmPaciente, Integer edadPaciente,
			String unidadEdadPaciente, String sexoPaciente,
			String condicionPaciente, String relacionPaciente, String numAutorizacion, Integer idEvento,
			List <GastoRegistroSeguroPersonal> gastosRegistroSeguroPersonal,String capita,
			Date nacimientoFechaNacimiento,String nacimientoHoraNacimiento,
			String nacimientoTipoParto,String nacimientoTalla,String nacimientoPeso,
			String nacimientoApgar,String nacimientoMedicoTratante,Float cantidadCubiertaConvenio,String censo) {
		super();
		this.idRegistroSeguroPersonal = idRegistroSeguroPersonal;
		this.numeroNomina = numeroNomina;
		this.institucion = institucion;
		this.nombreTitular = nombreTitular;
		this.appTitular = appTitular;
		this.apmTitular = apmTitular;
		this.nombrePaciente = nombrePaciente;
		this.appPaciente = appPaciente;
		this.apmPaciente = apmPaciente;
		this.edadPaciente = edadPaciente;
		this.unidadEdadPaciente = unidadEdadPaciente;
		this.sexoPaciente = sexoPaciente;
		this.condicionPaciente = condicionPaciente;
		this.relacionPaciente = relacionPaciente; 
		this.numAutorizacion=numAutorizacion;
		this.idEvento=idEvento;
		this.gastosRegistroSeguroPersonal=gastosRegistroSeguroPersonal;
		this.capita=capita;
		this.nacimientoFechaNacimiento=nacimientoFechaNacimiento;
		this.nacimientoHoraNacimiento=nacimientoHoraNacimiento;
		this.nacimientoTipoParto=nacimientoTipoParto;
		this.nacimientoTalla=nacimientoTalla;
		this.nacimientoPeso=nacimientoPeso;
		this.nacimientoApgar=nacimientoApgar;
		this.nacimientoMedicoTratante=nacimientoMedicoTratante;
		this.cantidadCubiertaConvenio=cantidadCubiertaConvenio;
		this.censo=censo;
	}
	
		
}
