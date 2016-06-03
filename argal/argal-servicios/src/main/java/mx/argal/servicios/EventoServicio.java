package mx.argal.servicios;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import mx.argal.modelo.Bitacora;
import mx.argal.modelo.Cliente;
import mx.argal.modelo.Evento;
import mx.argal.modelo.Factura;
import mx.argal.modelo.Gasto;
import mx.argal.modelo.Icd;
import mx.argal.modelo.Cpt;
import mx.argal.modelo.Implant;
import mx.argal.modelo.MedicoTratante;

//import mx.sep.sajja.datos.vo.FiltroBusquedaVO;
 
public interface EventoServicio {
	List<Evento> obtenerEventos();
	List<Evento> obtenerEventosLt();
	List<Evento> obtenerEventosByImplant(Implant implant);
	List<Evento> obtenerEventosByCliente(Cliente cliente);
	public boolean agregarEvento(Evento evento);
	public Integer agregarGasto(Evento evento);
	public List obtenerDatosCombo();	
	public List<Icd> obtenerIcds();
	public List<MedicoTratante> obtenerMedicosTratantes();
	public boolean guardarEditarGasto(Evento evento);
	public Evento obtenerEvento(Evento evento);
	public boolean agregarBitacora(Evento evento);
	public boolean editarBitacora(Evento evento);
	public boolean registrarAltaPaciente(Evento evento);
	public boolean finalizarEvento(Evento evento);
	public List<Cpt> obtenerCpts();
	public boolean agregarFactura(Evento evento);
	public boolean finalizarMontosEvento(Evento evento);
	public boolean eliminarGasto(Evento evento);
	public boolean agregarMedicoTratante(MedicoTratante medicoTratante);
	public boolean eliminarFactura(Evento evento);
	public boolean actualizarSaldosGastos(Evento evento);
	public boolean editarEvento(Evento evento);
	public void agregarFileGasto(Gasto gasto);
	public Gasto obtenerGastoById(int i);
	public Evento obtenerEventoById(Integer idEvento);
	public void agregarFileFactura(Factura factura);
	void eliminarBitacora(Integer idBitacora);
	public void desactivarMedicoTratante(Integer idMedicoTratante);
	public Bitacora obtenerBitacoraById(Integer idBitacora);
	List<Evento> obtenerEventosLtV2(String fecha1, String fecha2);
	boolean eliminarEvento(Evento evento);
	Integer agregarGastoEvitado(Evento evento);
	public Integer tieneEdosCta(Integer idEvent,Integer tipo);
}
