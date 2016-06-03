package mx.argal.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mx.argal.modelo.Antecedente;
import mx.argal.modelo.Bitacora;
import mx.argal.modelo.Cliente;
import mx.argal.modelo.Especialidad;
import mx.argal.modelo.Evento;
import mx.argal.modelo.Factura;
import mx.argal.modelo.Gasto;
import mx.argal.modelo.Icd;
import mx.argal.modelo.Cpt;
import mx.argal.modelo.Implant;
import mx.argal.modelo.MedicoTratante;

public interface EventoDao {
	
	public List<Evento> obtenerEventos();
	public List<Evento> obtenerEventosLt();
	public List<Evento> obtenerEventosByImplant(Implant implant);
	public List<Evento> obtenerEventosByCliente(Cliente cliente);
	public void agregarEvento(Evento evento);
	public Integer agregarGasto(Evento evento);
	public List<Icd> obtenerIcds();
	public List<MedicoTratante> obtenerMedicosTratantes();
	public void guardarEditarGasto(Evento evento);
	public Evento obtenerEvento(Evento evento);
	public void agregarBitacora(Evento evento);
	public void editarBitacora(Evento evento);
	public List<Evento> obtenerEventosBanco();
	public List<Evento> obtenerEventosBancoByParams(Evento evento);

	public void registrarAltaPaciente(Evento evento);
	public void finalizarEvento(Evento evento);
	public List<Cpt> obtenerCpts();
	public List<Especialidad> obtenerEspecialidades();
	public void agregarFactura(Evento evento);
	public void finalizarMontosEvento(Evento evento);
	public void eliminarGastoEvento(Evento evento);
	public List<Antecedente> obtenerAntecedentes();
	public void agregarMedicoTratante(MedicoTratante medicoTratante);
	public void eliminarFactura(Evento evento);
	public void eliminarBitacora(Integer idBitacora);
	public Integer actualizarTotalMontoAntesDesvios(Evento evento);
	public Integer actualizarTotalDesvios(Evento evento);
	public List<Evento> obtenerEventosAseguradora(Evento eventoTmp);
	public List<Evento> obtenerEventosAseguradoraByParams(Evento eventoTmp);
	public void editarEvento(Evento evento);
	public void agregarFileGasto(Gasto gasto);
	public void agregarFileFactura(Factura factura);
	public Gasto obtenerGastoById(int idGasto);
	public Evento obtenerEventoById(Integer idEvento);
  	public void actualizarMontosFinalesFacturacion(Evento evento);
	public void desactivarMedicoTratante(Integer idMedicoTratante);
	public Bitacora obtenerBitacoraById(Integer idBitacora);
	public List<Evento> obtenerEventosByNumNomina(@Param("pNomina")String pNomina);
	public List<Evento> obtenerBitacorasByIdEvento(@Param("idEvento")Integer idEvento);
	public List<Evento> obtenerEventosLtByParam(@Param("fecha1")String fecha1, @Param("fecha2")String fecha2);
	public Integer eliminarBitacorasByIdEvento(Evento evento);
	public Integer eliminarGastosByIdEvento(Evento evento);
	public Integer eliminarRegSegPersonalByIdEvento(Evento evento);
	public Integer eliminarEventoByIdEvento(Evento evento);
	public List<Evento> obtenerEventosByNumNominaAxa(String pNomina);
	public List<Evento> obtenerEventosBancoLayoutDiarioByParams(Evento eventoTmp);
	public List<Evento> obtenerEventosBancoLayoutMensualByParams(Evento eventoTmp);
	public void agregarGastoEvitado(Evento evento);
	public Integer tieneEdosCta(@Param("idEvent")Integer idEvent,@Param("tipo")Integer tipo);
}
