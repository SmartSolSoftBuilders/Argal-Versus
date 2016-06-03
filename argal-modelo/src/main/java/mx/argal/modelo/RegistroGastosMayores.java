package mx.argal.modelo;

import java.util.Date;

import java.util.List;

public class RegistroGastosMayores extends BaseModelo {
	
	private Integer idRegistroGastosMayores;
	private String numeroPoliza;
	private String deduciblePoliza;
	private String coaseguroMedico;
	private String sumaAsegurada;
	private String montoCartaAutInicial;
	private String tablaHonorariosMedicos;	
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
	private List <GastoRegistroGastosMayores> gastosRegistroGastosMayores;
	
	//Registro de Nacimiento
	private Date nacimientoFechaNacimiento;
	private String nacimientoHoraNacimiento;
	private String nacimientoTipoParto;
	private String nacimientoTalla;
	private String nacimientoPeso;
	private String nacimientoApgar;
	private String nacimientoMedicoTratante;
	
		
	public List<GastoRegistroGastosMayores> getGastosRegistroGastosMayores() {
		return gastosRegistroGastosMayores;
	}
	public void setGastosRegistroGastosMayores(
			List<GastoRegistroGastosMayores> gastosRegistroGastosMayores) {
		this.gastosRegistroGastosMayores = gastosRegistroGastosMayores;
	}
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
	public List<GastoRegistroGastosMayores> getGastoRegistroGastosMayores() {
		return gastosRegistroGastosMayores;
	}
	public void setGastosRegistroSeguroPersonal(
			List<GastoRegistroGastosMayores> gastosRegistroGastosMayores) {
		this.gastosRegistroGastosMayores = gastosRegistroGastosMayores;
	}
	public String getDeduciblePoliza() {
		return deduciblePoliza;
	}
	public void setDeduciblePoliza(String deduciblePoliza) {
		this.deduciblePoliza = deduciblePoliza;
	}
	public Integer getIdRegistroGastosMayores() {
		return idRegistroGastosMayores;
	}
	public void setIdRegistroGastosMayores(Integer idRegistroGastosMayores) {
		this.idRegistroGastosMayores = idRegistroGastosMayores;
	}
	public String getNumeroPoliza() {
		return numeroPoliza;
	}
	public void setNumeroPoliza(String numeroPoliza) {
		this.numeroPoliza = numeroPoliza;
	}
	public String getCoaseguroMedico() {
		return coaseguroMedico;
	}
	public void setCoaseguroMedico(String coaseguroMedico) {
		this.coaseguroMedico = coaseguroMedico;
	}
	public String getSumaAsegurada() {
		return sumaAsegurada;
	}
	public void setSumaAsegurada(String sumaAsegurada) {
		this.sumaAsegurada = sumaAsegurada;
	}
	public String getMontoCartaAutInicial() {
		return montoCartaAutInicial;
	}
	public void setMontoCartaAutInicial(String montoCartaAutInicial) {
		this.montoCartaAutInicial = montoCartaAutInicial;
	}
	public String getTablaHonorariosMedicos() {
		return tablaHonorariosMedicos;
	}
	public void setTablaHonorariosMedicos(String tablaHonorariosMedicos) {
		this.tablaHonorariosMedicos = tablaHonorariosMedicos;
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

	public RegistroGastosMayores(){
		
	}
	
	public RegistroGastosMayores(Integer idRegistroGastosMayores,
			String numeroPoliza, String coaseguroMedico, String sumaAsegurada,
			String montoCartaAutInicial, String tablaHonorariosMedicos,
			String nombreTitular, String appTitular, String apmTitular,
			String nombrePaciente, String appPaciente, String apmPaciente,
			Integer edadPaciente, String unidadEdadPaciente,
			String sexoPaciente, String condicionPaciente,
			String relacionPaciente, String deduciblePoliza,
			List <GastoRegistroGastosMayores> gastosRegistroGastosMayores,
			Date nacimientoFechaNacimiento,String nacimientoHoraNacimiento,
			String nacimientoTipoParto,String nacimientoTalla,String nacimientoPeso,
			String nacimientoApgar,String nacimientoMedicoTratante) {
		super();
		this.idRegistroGastosMayores = idRegistroGastosMayores;
		this.numeroPoliza = numeroPoliza;
		this.coaseguroMedico = coaseguroMedico;
		this.sumaAsegurada = sumaAsegurada;
		this.montoCartaAutInicial = montoCartaAutInicial;
		this.tablaHonorariosMedicos = tablaHonorariosMedicos;
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
		this.deduciblePoliza=deduciblePoliza;
		this.gastosRegistroGastosMayores=gastosRegistroGastosMayores;
		this.nacimientoFechaNacimiento=nacimientoFechaNacimiento;
		this.nacimientoHoraNacimiento=nacimientoHoraNacimiento;
		this.nacimientoTipoParto=nacimientoTipoParto;
		this.nacimientoTalla=nacimientoTalla;
		this.nacimientoPeso=nacimientoPeso;
		this.nacimientoApgar=nacimientoApgar;
		this.nacimientoMedicoTratante=nacimientoMedicoTratante;
	}
		
}