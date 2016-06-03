package mx.argal.modelo;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Evento extends BaseModelo {
	
	private int idEvento;
	private int statusEvento;
	private Implant implant;
	private Hospital hospital;
	private Cliente cliente;
	private TipoEvento tipoEvento;
	private TipoSeguro tipoSeguro;
	private Date fechaIngreso;
	private Date fechaCaptura;
	private String numHabitacion;	
	private boolean red;
	private RegistroSeguroPersonal registroSeguroPersonal;
	private RegistroGastosMayores registroGastosMayores;
	private Icd diagnosticoIngreso1;
	private Icd diagnosticoIngreso2;
	private Antecedente antecedente;
	private MedicoTratante medicoTratante;
	private List<Bitacora> bitacoras;
	private List<Gasto> gastos;
	private String folioArgal;
	private String folioHospital;
	private String horaIngreso;
	private String medicoDictaminador;
	
	//Egreso Paciente
	private Date fechaEgreso;
	private Icd diagnosticoEgreso1;
	private Cpt procedimiento1;
	private Cpt procedimiento2;
	private String eventosNoDeseablesEntornoHosp;
	private String motivoEgreso;
	private String horaEgreso;
	private String horaEgresoCapt;
	private Date fechaEgresoCapt;
	
	private Date fechaDef;
	private String horaDef;
	private String causaDirectaDef;
	private Integer diasIncapacidad;
		
	private List<Factura> facturas;
	//Finalizar Evento
	private Float montoAntesDesvios;
	private Float totalDesvios;//Gastos Observados
	private Float totalDesviosComprobados;
	private Float montoDespuesDesvios;//-> No bd
	private Float ivaFinalizarEvento;
	private Float coaseguroFinalizarEvento;
	private Float deducibleFinalizarEvento;
	private Float descuentoHospFinalizarEvento;
	private Integer numFacturasAprobadas;
	private Integer numFacturasRechazadas;
	private Float montoDesviosFacturacion;
	private Float montoAjusteFacturacion;
	private Float montoFacturacionCorregido;	
	private Float montoFinalFacturacion;
	
	//BORRAR
	private Float descuentoHospital;
	private Float difFacturacionMontMenosDesvios;
	private String comentariosDiferenciaFacturacion;
	private Float descuentoNoAplicado;
	private Float cargosPersonales;	
	private String ajusteFactura;	
	private String tipoComprobanteFiscalCorregido;
	private String folioComprobanteFiscalCorregido;
	private Float montoComprobanteFiscalCorregido;
	
	/*Justificación Gastos
	private Float justificacionTerapiaIntensiva;
	private Float justificacionMedicamentos;
	private Float justificacionMaterialesEquipos;
	private Float justificacionBancoSangre;
	private Float justificacionQuirofano;
	private Float justificacionOtros;
	private String justificacionObservaciones;*/
	
	
	
	public Float getTotalDesviosComprobados() {
		return totalDesviosComprobados;
	}
	public Float getMontoFinalFacturacion() {
		return montoFinalFacturacion;
	}
	public void setMontoFinalFacturacion(Float montoFinalFacturacion) {
		this.montoFinalFacturacion = montoFinalFacturacion;
	}
	public void setTotalDesvios(Float totalDesvios) {
		this.totalDesvios = totalDesvios;
	}
	public void setTotalDesviosComprobados(Float totalDesviosComprobados) {
		this.totalDesviosComprobados = totalDesviosComprobados;
	}
	public Integer getNumFacturasAprobadas() {
		return numFacturasAprobadas;
	}
	public void setNumFacturasAprobadas(Integer numFacturasAprobadas) {
		this.numFacturasAprobadas = numFacturasAprobadas;
	}
	public Integer getNumFacturasRechazadas() {
		return numFacturasRechazadas;
	}
	public void setNumFacturasRechazadas(Integer numFacturasRechazadas) {
		this.numFacturasRechazadas = numFacturasRechazadas;
	}
	public Float getMontoDesviosFacturacion() {
		return montoDesviosFacturacion;
	}
	public void setMontoDesviosFacturacion(Float montoDesviosFacturacion) {
		this.montoDesviosFacturacion = montoDesviosFacturacion;
	}
	public Float getMontoAjusteFacturacion() {
		return montoAjusteFacturacion;
	}
	public void setMontoAjusteFacturacion(Float montoAjusteFacturacion) {
		this.montoAjusteFacturacion = montoAjusteFacturacion;
	}
	public Float getMontoFacturacionCorregido() {
		return montoFacturacionCorregido;
	}
	public void setMontoFacturacionCorregido(Float montoFacturacionCorregido) {
		this.montoFacturacionCorregido = montoFacturacionCorregido;
	}
	public Date getFechaEgresoCapt() {
		return fechaEgresoCapt;
	}
	public void setFechaEgresoCapt(Date fechaEgresoCapt) {
		this.fechaEgresoCapt = fechaEgresoCapt;
	}
	public String getHoraEgresoCapt() {
		return horaEgresoCapt;
	}
	public void setHoraEgresoCapt(String horaEgresoCapt) {
		this.horaEgresoCapt = horaEgresoCapt;
	}
	public String getHoraEgreso() {
		return horaEgreso;
	}
	public void setHoraEgreso(String horaEgreso) {
		this.horaEgreso = horaEgreso;
	}
	public String getCausaDirectaDef() {
		return causaDirectaDef;
	}
	public void setCausaDirectaDef(String causaDirectaDef) {
		this.causaDirectaDef = causaDirectaDef;
	}
	public Integer getDiasIncapacidad() {
		return diasIncapacidad;
	}
	public void setDiasIncapacidad(Integer diasIncapacidad) {
		this.diasIncapacidad = diasIncapacidad;
	}
	public Date getFechaDef() {
		return fechaDef;
	}
	public void setFechaDef(Date fechaDef) {
		this.fechaDef = fechaDef;
	}
	public String getHoraDef() {
		return horaDef;
	}
	public void setHoraDef(String horaDef) {
		this.horaDef = horaDef;
	}

	public String getMedicoDictaminador() {
		return medicoDictaminador;
	}
	public void setMedicoDictaminador(String medicoDictaminador) {
		this.medicoDictaminador = medicoDictaminador;
	}

	public String getHoraIngreso() {
		return horaIngreso;
	}
	public void setHoraIngreso(String horaIngreso) {
		this.horaIngreso = horaIngreso;
	}
	
	public String getFolioArgal() {
		return folioArgal;
	}
	
	public void setFolioArgal(String folioArgal) {
		this.folioArgal = folioArgal;
	}
	public String getFolioHospital() {
		return folioHospital;
	}
	public void setFolioHospital(String folioHospital) {
		this.folioHospital = folioHospital;
	}
	public Float getIvaFinalizarEvento() {
		return ivaFinalizarEvento;
	}
	public void setIvaFinalizarEvento(Float ivaFinalizarEvento) {
		this.ivaFinalizarEvento = ivaFinalizarEvento;
	}
	public Float getCoaseguroFinalizarEvento() {
		return coaseguroFinalizarEvento;
	}
	public void setCoaseguroFinalizarEvento(Float coaseguroFinalizarEvento) {
		this.coaseguroFinalizarEvento = coaseguroFinalizarEvento;
	}
	public Float getDeducibleFinalizarEvento() {
		return deducibleFinalizarEvento;
	}
	public void setDeducibleFinalizarEvento(Float deducibleFinalizarEvento) {
		this.deducibleFinalizarEvento = deducibleFinalizarEvento;
	}
	public Float getDescuentoHospFinalizarEvento() {
		return descuentoHospFinalizarEvento;
	}
	public void setDescuentoHospFinalizarEvento(Float descuentoHospFinalizarEvento) {
		this.descuentoHospFinalizarEvento = descuentoHospFinalizarEvento;
	}
	public Date getFechaEgreso() {
		return fechaEgreso;
	}
	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}
	public Icd getDiagnosticoEgreso1() {
		return diagnosticoEgreso1;
	}
	public void setDiagnosticoEgreso1(Icd diagnosticoEgreso1) {
		this.diagnosticoEgreso1 = diagnosticoEgreso1;
	}
	public Cpt getProcedimiento1() {
		return procedimiento1;
	}
	public void setProcedimiento1(Cpt procedimiento1) {
		this.procedimiento1 = procedimiento1;
	}
	public Cpt getProcedimiento2() {
		return procedimiento2;
	}
	public void setProcedimiento2(Cpt procedimiento2) {
		this.procedimiento2 = procedimiento2;
	}
	public String getEventosNoDeseablesEntornoHosp() {
		return eventosNoDeseablesEntornoHosp;
	}
	public void setEventosNoDeseablesEntornoHosp(
			String eventosNoDeseablesEntornoHosp) {
		this.eventosNoDeseablesEntornoHosp = eventosNoDeseablesEntornoHosp;
	}
	public String getMotivoEgreso() {
		return motivoEgreso;
	}
	public void setMotivoEgreso(String motivoEgreso) {
		this.motivoEgreso = motivoEgreso;
	}
	public List<Factura> getFacturas() {
		return facturas;
	}
	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}
	public Float getMontoAntesDesvios() {
		return montoAntesDesvios;
	}
	public void setMontoAntesDesvios(Float montoAntesDesvios) {
		this.montoAntesDesvios = montoAntesDesvios;
	}
	public Float getMontoDespuesDesvios() {
		return montoDespuesDesvios;
	}
	public void setMontoDespuesDesvios(Float montoDespuesDesvios) {
		this.montoDespuesDesvios = montoDespuesDesvios;
	}
	public Float getDescuentoHospital() {
		return descuentoHospital;
	}
	public void setDescuentoHospital(Float descuentoHospital) {
		this.descuentoHospital = descuentoHospital;
	}
	public Float getDifFacturacionMontMenosDesvios() {
		return difFacturacionMontMenosDesvios;
	}
	public void setDifFacturacionMontMenosDesvios(
			Float difFacturacionMontMenosDesvios) {
		this.difFacturacionMontMenosDesvios = difFacturacionMontMenosDesvios;
	}
	public String getComentariosDiferenciaFacturacion() {
		return comentariosDiferenciaFacturacion;
	}
	public void setComentariosDiferenciaFacturacion(
			String comentariosDiferenciaFacturacion) {
		this.comentariosDiferenciaFacturacion = comentariosDiferenciaFacturacion;
	}
	public Float getDescuentoNoAplicado() {
		return descuentoNoAplicado;
	}
	public void setDescuentoNoAplicado(Float descuentoNoAplicado) {
		this.descuentoNoAplicado = descuentoNoAplicado;
	}
	public Float getCargosPersonales() {
		return cargosPersonales;
	}
	public void setCargosPersonales(Float cargosPersonales) {
		this.cargosPersonales = cargosPersonales;
	}
	public Float getTotalDesvios() {
		return totalDesvios;
	}
	public void setTotalDesvíos(Float totalDesvios) {
		this.totalDesvios = totalDesvios;
	}
	public String getAjusteFactura() {
		return ajusteFactura;
	}
	public void setAjusteFactura(String ajusteFactura) {
		this.ajusteFactura = ajusteFactura;
	}
	public String getTipoComprobanteFiscalCorregido() {
		return tipoComprobanteFiscalCorregido;
	}
	public void setTipoComprobanteFiscalCorregido(
			String tipoComprobanteFiscalCorregido) {
		this.tipoComprobanteFiscalCorregido = tipoComprobanteFiscalCorregido;
	}
	public String getFolioComprobanteFiscalCorregido() {
		return folioComprobanteFiscalCorregido;
	}
	public void setFolioComprobanteFiscalCorregido(
			String folioComprobanteFiscalCorregido) {
		this.folioComprobanteFiscalCorregido = folioComprobanteFiscalCorregido;
	}
	public Float getMontoComprobanteFiscalCorregido() {
		return montoComprobanteFiscalCorregido;
	}
	public void setMontoComprobanteFiscalCorregido(
			Float montoComprobanteFiscalCorregido) {
		this.montoComprobanteFiscalCorregido = montoComprobanteFiscalCorregido;
	}
	
	/*public Float getJustificacionTerapiaIntensiva() {
		return justificacionTerapiaIntensiva;
	}
	public void setJustificacionTerapiaIntensiva(Float justificacionTerapiaIntensiva) {
		this.justificacionTerapiaIntensiva = justificacionTerapiaIntensiva;
	}
	public Float getJustificacionMedicamentos() {
		return justificacionMedicamentos;
	}
	public void setJustificacionMedicamentos(Float justificacionMedicamentos) {
		this.justificacionMedicamentos = justificacionMedicamentos;
	}
	public Float getJustificacionMaterialesEquipos() {
		return justificacionMaterialesEquipos;
	}
	public void setJustificacionMaterialesEquipos(
			Float justificacionMaterialesEquipos) {
		this.justificacionMaterialesEquipos = justificacionMaterialesEquipos;
	}
	public Float getJustificacionBancoSangre() {
		return justificacionBancoSangre;
	}
	public void setJustificacionBancoSangre(Float justificacionBancoSangre) {
		this.justificacionBancoSangre = justificacionBancoSangre;
	}
	public Float getJustificacionQuirofano() {
		return justificacionQuirofano;
	}
	public void setJustificacionQuirofano(Float justificacionQuirofano) {
		this.justificacionQuirofano = justificacionQuirofano;
	}
	public Float getJustificacionOtros() {
		return justificacionOtros;
	}
	public void setJustificacionOtros(Float justificacionOtros) {
		this.justificacionOtros = justificacionOtros;
	}
	public String getJustificacionObservaciones() {
		return justificacionObservaciones;
	}
	public void setJustificacionObservaciones(String justificacionObservaciones) {
		this.justificacionObservaciones = justificacionObservaciones;
	}
	*/
	public List<Gasto> getGastos() {
		return gastos;
	}
	public void setGastos(List<Gasto> gastos) {
		this.gastos = gastos;
	}
	public List<Bitacora> getBitacoras() {
		return bitacoras;
	}
	public void setBitacoras(List<Bitacora> bitacoras) {
		this.bitacoras = bitacoras;
	}
	public Antecedente getAntecedente() {
		return antecedente;
	}
	public void setAntecedente(Antecedente antecedente) {
		this.antecedente = antecedente;
	}
	public MedicoTratante getMedicoTratante() {
		return medicoTratante;
	}
	public void setMedicoTratante(MedicoTratante medicoTratante) {
		this.medicoTratante = medicoTratante;
	}
	public Icd getDiagnosticoIngreso1() {
		return diagnosticoIngreso1;
	}
	public void setDiagnosticoIngreso1(Icd diagnosticoIngresoUno) {
		this.diagnosticoIngreso1 = diagnosticoIngresoUno;
	}
	public Icd getDiagnosticoIngreso2() {
		return diagnosticoIngreso2;
	}
	public void setDiagnosticoIngreso2(Icd diagnosticoIngreso2) {
		this.diagnosticoIngreso2 = diagnosticoIngreso2;
	}
	public RegistroGastosMayores getRegistroGastosMayores() {
		return registroGastosMayores;
	}
	public void setRegistroGastosMayores(RegistroGastosMayores registroGastosMayores) {
		this.registroGastosMayores = registroGastosMayores;
	}
	public RegistroSeguroPersonal getRegistroSeguroPersonal() {
		return registroSeguroPersonal;
	}
	public void setRegistroSeguroPersonal(
			RegistroSeguroPersonal registroSeguroPersonal) {
		this.registroSeguroPersonal = registroSeguroPersonal;
	}
	public int getIdEvento() {
		return idEvento;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;
	}
	public int getStatusEvento() {
		return statusEvento;
	}
	public void setStatusEvento(int statusEvento) {
		this.statusEvento = statusEvento;
	}
	public Implant getImplant() {
		return implant;
	}
	public void setImplant(Implant implant) {
		this.implant = implant;
	}
	public Hospital getHospital() {
		return hospital;
	}
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public TipoEvento getTipoEvento() {
		return tipoEvento;
	}
	public void setTipoEvento(TipoEvento tipoEvento) {
		this.tipoEvento = tipoEvento;
	}
	public TipoSeguro getTipoSeguro() {
		return tipoSeguro;
	}
	public void setTipoSeguro(TipoSeguro tipoSeguro) {
		this.tipoSeguro = tipoSeguro;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public Date getFechaCaptura() {
		return fechaCaptura;
	}
	public void setFechaCaptura(Date fechaCaptura) {
		this.fechaCaptura = fechaCaptura;
	}
	public String getNumHabitacion() {
		return numHabitacion;
	}
	public void setNumHabitacion(String numHabitacion) {
		this.numHabitacion = numHabitacion;
	}
	public boolean isRed() {
		return red;
	}
	public void setRed(boolean red) {
		this.red = red;
	}	
	
	public Evento(){		
	}
	
	public Evento(int idEvento, int statusEvento, Implant implant, Hospital hospital,
			Cliente cliente, TipoEvento tipoEvento, TipoSeguro tipoSeguro,
			Date fechaIngreso, Date fechaCaptura, String numHabitacion,
			boolean red, RegistroSeguroPersonal registroSeguroPersonal,
			RegistroGastosMayores registroGastosMayores,
			Icd diagnosticoIngresoUno, Icd diagnosticoIngreso2,
			Antecedente antecedente, MedicoTratante medicoTratante,
			List<Bitacora> bitacoras, List<Gasto> gastos, Date fechaEgreso,
			Icd diagnosticoEgreso1, Cpt procedimiento1, Cpt procedimiento2,
			String eventosNoDeseablesEntornoHosp, String motivoEgreso,
			List<Factura> facturas, Float montoAntesDesvios,
			Float montoDespuesDesvios, Float descuentoHospital,
			Float difFacturacionMontMenosDesvios,
			String comentariosDiferenciaFacturacion, Float descuentoNoAplicado,
			Float cargosPersonales, Float totalDesvios, String ajusteFactura,
			String tipoComprobanteFiscalCorregido,
			String folioComprobanteFiscalCorregido,
			Float montoComprobanteFiscalCorregido,
			/*Float justificacionTerapiaIntensiva,
			Float justificacionMedicamentos,
			Float justificacionMaterialesEquipos,
			Float justificacionBancoSangre, Float justificacionQuirofano,
			Float justificacionOtros, String justificacionObservaciones,*/
			Float ivaFinalizarEvento,
			Float coaseguroFinalizarEvento,
			Float deducibleFinalizarEvento,
			Float descuentoHospFinalizarEvento,
			String folioArgal, String folioHospital,String horaIngreso,String medicoDictaminador,Date fechaDef, String horaDef,
			String causaDirectaDef, Integer diasIncapacidad, String horaEgreso,String horaEgresoCapt,Date fechaEgresoCapt,
			Float totalDesviosComprobados,Integer numFacturasAprobadas,Integer numFacturasRechazadas,
			Float montoDesviosFacturacion,Float montoAjusteFacturacion,Float montoFacturacionCorregido,Float montoFinalFacturacion) {
		super();
		this.idEvento = idEvento;
		this.statusEvento=statusEvento;
		this.implant = implant;
		this.hospital = hospital;
		this.cliente = cliente;
		this.tipoEvento = tipoEvento;
		this.tipoSeguro = tipoSeguro;
		this.fechaIngreso = fechaIngreso;
		this.fechaCaptura = fechaCaptura;
		this.numHabitacion = numHabitacion;
		this.red = red;
		this.registroSeguroPersonal = registroSeguroPersonal;
		this.registroGastosMayores = registroGastosMayores;
		this.diagnosticoIngreso1 = diagnosticoIngresoUno;
		this.diagnosticoIngreso2 = diagnosticoIngreso2;
		this.antecedente = antecedente;
		this.medicoTratante = medicoTratante;
		this.bitacoras = bitacoras;
		this.gastos = gastos;
		this.fechaEgreso = fechaEgreso;
		this.diagnosticoEgreso1 = diagnosticoEgreso1;
		this.procedimiento1 = procedimiento1;
		this.procedimiento2 = procedimiento2;
		this.eventosNoDeseablesEntornoHosp = eventosNoDeseablesEntornoHosp;
		this.motivoEgreso = motivoEgreso;
		this.facturas = facturas;
		this.montoAntesDesvios = montoAntesDesvios;
		this.montoDespuesDesvios = montoDespuesDesvios;
		this.descuentoHospital = descuentoHospital;
		this.difFacturacionMontMenosDesvios = difFacturacionMontMenosDesvios;
		this.comentariosDiferenciaFacturacion = comentariosDiferenciaFacturacion;
		this.descuentoNoAplicado = descuentoNoAplicado;
		this.cargosPersonales = cargosPersonales;
		this.totalDesvios = totalDesvios;
		this.ajusteFactura = ajusteFactura;
		this.tipoComprobanteFiscalCorregido = tipoComprobanteFiscalCorregido;
		this.folioComprobanteFiscalCorregido = folioComprobanteFiscalCorregido;
		this.montoComprobanteFiscalCorregido = montoComprobanteFiscalCorregido;
		/*this.justificacionTerapiaIntensiva = justificacionTerapiaIntensiva;
		this.justificacionMedicamentos = justificacionMedicamentos;
		this.justificacionMaterialesEquipos = justificacionMaterialesEquipos;
		this.justificacionBancoSangre = justificacionBancoSangre;
		this.justificacionQuirofano = justificacionQuirofano;
		this.justificacionOtros = justificacionOtros;
		this.justificacionObservaciones = justificacionObservaciones;*/
		this.ivaFinalizarEvento=ivaFinalizarEvento;
		this.coaseguroFinalizarEvento=coaseguroFinalizarEvento;
		this.deducibleFinalizarEvento=deducibleFinalizarEvento;
		this.descuentoHospFinalizarEvento=descuentoHospFinalizarEvento;
		this.folioArgal=folioArgal;
		this.folioHospital=folioHospital;
		this.horaIngreso=horaIngreso;
		this.medicoDictaminador=medicoDictaminador;
		this.fechaDef=fechaDef;
		this.horaDef=horaDef;
		this.causaDirectaDef=causaDirectaDef;
		this.diasIncapacidad=diasIncapacidad;
		this.horaEgreso=horaEgreso;
		this.horaEgresoCapt=horaEgresoCapt;
		this.fechaEgresoCapt=fechaEgresoCapt;
		this.montoFinalFacturacion=montoFinalFacturacion;
	}
		
}
