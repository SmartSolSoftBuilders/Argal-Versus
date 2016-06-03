package mx.argal.servicios;

import java.util.List;
import java.util.ArrayList;

import mx.argal.dao.EventoDao;
import mx.argal.dao.ClienteDao;
import mx.argal.dao.ImplantDao;
import mx.argal.dao.TipoEventoDao;
import mx.argal.dao.TipoSeguroDao;
import mx.argal.modelo.Bitacora;
import mx.argal.modelo.Cliente;
import mx.argal.modelo.Evento;
import mx.argal.modelo.Factura;
import mx.argal.modelo.Gasto;
import mx.argal.modelo.Icd;
import mx.argal.modelo.Cpt;
import mx.argal.modelo.Implant;
import mx.argal.modelo.MedicoTratante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import mx.sep.sajja.datos.vo.FiltroBusquedaVO;


@Service
public class EventoServicioImpl implements EventoServicio {

	@Autowired
	private EventoDao eventoDao;
	@Autowired
	private TipoEventoDao tipoEventoDao;
	@Autowired
	private TipoSeguroDao tipoSeguroDao;
	@Autowired
	private ClienteDao clienteDao;
	@Autowired
	private ImplantDao implantDao;

	@Override
	public List<Evento> obtenerEventos() {
		// TODO Auto-generated method stub
		try{
			System.out.println("OBTENIENDO EVENTOS");
			List<Evento> eventos=this.eventoDao.obtenerEventos();
			System.out.println("OBTENIENDO EVENTOS"+eventos.size());
			return eventos;
		}
		catch (Exception e){			
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Evento> obtenerEventosLt() {
		// TODO Auto-generated method stub
		try{
			return this.eventoDao.obtenerEventosLt();
		}
		catch (Exception e){			
			e.printStackTrace();
			return null;
		}
	}

	
	@Override
	public Evento obtenerEventoById(Integer idEvento) {
		// TODO Auto-generated method stub
		try{
			return this.eventoDao.obtenerEventoById(idEvento);
		}
		catch (Exception e){			
			e.printStackTrace();
			return null;
		}
	}
	

	@Override
	public Bitacora obtenerBitacoraById(Integer idBitacora) {
		// TODO Auto-generated method stub
		try{
			return this.eventoDao.obtenerBitacoraById(idBitacora);
		}
		catch (Exception e){			
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Evento> obtenerEventosByImplant(Implant implant) {
		// TODO Auto-generated method stub
		try{
			return this.eventoDao.obtenerEventosByImplant(implant);
		}
		catch (Exception e){			
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public List<Evento> obtenerEventosByCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		try{
			return this.eventoDao.obtenerEventosByCliente(cliente);
		}
		catch (Exception e){			
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public Evento obtenerEvento(Evento evento) {
		// TODO Auto-generated method stub
		try{
			return this.eventoDao.obtenerEvento(evento);
		}
		catch (Exception e){			
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean agregarEvento(Evento evento) {
		// TODO Auto-generated method stub
		try{
			this.eventoDao.agregarEvento(evento);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean editarEvento(Evento evento) {
		// TODO Auto-generated method stub
		try{
			this.eventoDao.editarEvento(evento);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean eliminarGasto(Evento evento) {
		// TODO Auto-generated method stub
		try{
			this.eventoDao.eliminarGastoEvento(evento);
			actualizarSaldosGastos(evento);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean guardarEditarGasto(Evento evento) {
		// TODO Auto-generated method stub
		try{
			this.eventoDao.guardarEditarGasto(evento);
			actualizarSaldosGastos(evento);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}


	@Override
	@SuppressWarnings(value = { "all" })
	public List obtenerDatosCombo() {
		// TODO Auto-generated method stub
		List datosCombo = new ArrayList();
		try{
			datosCombo.add(this.clienteDao.obtenerClientesActivos());
			datosCombo.add(this.tipoSeguroDao.obtenerTiposSeguro());
			datosCombo.add(this.tipoEventoDao.obtenerTiposEvento());						
			datosCombo.add(this.eventoDao.obtenerAntecedentes());
			datosCombo.add(this.implantDao.obtenerImplants());		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return datosCombo;
	}
	
	@Override
	public List<Icd> obtenerIcds() {
		// TODO Auto-generated method stub
		return this.eventoDao.obtenerIcds();
	}
	
	@Override
	public List<Cpt> obtenerCpts() {
		// TODO Auto-generated method stub
		return this.eventoDao.obtenerCpts();
	}

	@Override
	public List<MedicoTratante> obtenerMedicosTratantes() {
		// TODO Auto-generated method stub
		List<MedicoTratante>  medicosTratantes  = new ArrayList<MedicoTratante>();
		try{
			medicosTratantes=this.eventoDao.obtenerMedicosTratantes();
		}
		catch(Exception e){
			System.out.println("ERROR"+e.getMessage());
			}
		return medicosTratantes;
	}
	
	@Override
	public Integer agregarGasto(Evento evento) {
		// TODO Auto-generated method stub
		try{
			this.eventoDao.agregarGasto(evento);
			actualizarSaldosGastos(evento);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public Integer agregarGastoEvitado(Evento evento) {
		// TODO Auto-generated method stub
		try{
			this.eventoDao.agregarGastoEvitado(evento);			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public boolean agregarBitacora(Evento evento) {
		// TODO Auto-generated method stub
		try{
			this.eventoDao.agregarBitacora(evento);
			return true;
		}
		catch(Exception e){
			System.out.println("ERROR GUARDANDO LA BITACORA:"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean agregarFactura(Evento evento) {
		// TODO Auto-generated method stub
		try{
			this.eventoDao.agregarFactura(evento);
			Evento eventoTmp=eventoDao.obtenerEventoById(evento.getIdEvento());
			System.out.println("Se actualizaran los montos de las facturas del evento:"+eventoTmp.getIdEvento());
			float montoFinalFact=0;
			float montoDesvFact=0;
			float montoAjusteFact=0;
			
			if (eventoTmp.getFacturas()!=null){
				for (int i=0;i<eventoTmp.getFacturas().size();i++){
					if (eventoTmp.getFacturas().get(i).getAprobada().equals("SI")){
						montoFinalFact=montoFinalFact+eventoTmp.getFacturas().get(i).getMonto();
					}
					else{
						montoDesvFact=montoDesvFact+eventoTmp.getFacturas().get(i).getMonto();
						montoAjusteFact=montoAjusteFact+eventoTmp.getFacturas().get(i).getAjusteFactura();
					}
				}
				eventoTmp.setMontoDesviosFacturacion(montoDesvFact);
				eventoTmp.setMontoFinalFacturacion(montoFinalFact);
				eventoTmp.setMontoAjusteFacturacion(montoAjusteFact);
				eventoDao.actualizarMontosFinalesFacturacion(eventoTmp);
				System.out.println("Montos actualizados!");
			}
			
			return true;
		}
		catch(Exception e){
			System.out.println("ERROR GUARDANDO LA FACTURA:"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean finalizarMontosEvento(Evento evento) {
		// TODO Auto-generated method stub
		try{						
			this.eventoDao.finalizarMontosEvento(evento);
			this.actualizarSaldosGastosFinalizar(evento);
			this.eventoDao.finalizarMontosEvento(evento);
			return true;
		}
		catch(Exception e){
			System.out.println("ERROR GUARDANDO LA FACTURA:"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}

	
	@Override
	public boolean editarBitacora(Evento evento) {
		// TODO Auto-generated method stub
		try{
			this.eventoDao.editarBitacora(evento);
			return true;
		}
		catch(Exception e){
			System.out.println("ERROR GUARDANDO LA BITACORA:"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean registrarAltaPaciente(Evento evento) {
		// TODO Auto-generated method stub
		try{
			this.eventoDao.registrarAltaPaciente(evento);
			actualizarSaldosGastos(evento);
			return true;
		}
		catch(Exception e){
			System.out.println("ERROR GUARDANDO LA BITACORA:"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean finalizarEvento(Evento evento) {
		// TODO Auto-generated method stub
		try{
			this.eventoDao.finalizarEvento(evento);
			return true;
		}
		catch(Exception e){
			System.out.println("ERROR GUARDANDO LA BITACORA:"+e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public boolean agregarMedicoTratante(MedicoTratante medicoTratante) {
		// TODO Auto-generated method stub
		try{
			this.eventoDao.agregarMedicoTratante(medicoTratante);
			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean eliminarFactura(Evento evento) {
		// TODO Auto-generated method stub
		try{
			this.eventoDao.eliminarFactura(evento);
			Evento eventoTmp=eventoDao.obtenerEventoById(evento.getIdEvento());
			System.out.println("Se actualizaran los montos de las facturas del evento:"+eventoTmp.getIdEvento());
			float montoFinalFact=0;
			float montoDesvFact=0;
			float montoAjusteFact=0;			
			if (eventoTmp.getFacturas()!=null){
				for (int i=0;i<eventoTmp.getFacturas().size();i++){
					if (eventoTmp.getFacturas().get(i).getAprobada().equals("SI")){
						montoFinalFact=montoFinalFact+eventoTmp.getFacturas().get(i).getMonto();
					}
					else{
						montoDesvFact=montoDesvFact+eventoTmp.getFacturas().get(i).getMonto();
						montoAjusteFact=montoAjusteFact+eventoTmp.getFacturas().get(i).getAjusteFactura();
					}
				}
				eventoTmp.setMontoDesviosFacturacion(montoDesvFact);
				eventoTmp.setMontoFinalFacturacion(montoFinalFact);
				eventoTmp.setMontoAjusteFacturacion(montoAjusteFact);
				eventoDao.actualizarMontosFinalesFacturacion(eventoTmp);
				System.out.println("Montos actualizados!");
			}
			return true;
		}
		catch(Exception e){
			System.out.println("ERROR GUARDANDO LA FACTURA:"+e.getMessage());
			e.printStackTrace();
			return false;
		}		
	}

	@Override
	public boolean actualizarSaldosGastos(Evento evento) {
		// TODO Auto-generated method stub
		float totalAntesDesvios=0;
		float totalDesvios=0;
		evento=this.eventoDao.obtenerEventoById(evento.getIdEvento());
		System.out.println("obteniendo Gastos de:"+evento.getStatusEvento());		
		//Evento es finalizado
		if (evento.getStatusEvento()==3){
			return this.actualizarSaldosGastosOld(evento);
		}
		int edoEventoTmp=1;
		//Evento es egresado
		if (evento.getStatusEvento()==2)
			edoEventoTmp=3;
		//Evento es En curso
		try{
			for (int i=0; i<evento.getGastos().size();i++){
				if (evento.getGastos().get(i)!=null){
					if (evento.getGastos().get(i).getIdTipoCargo()==1 && evento.getGastos().get(i).getIdArea()==edoEventoTmp){		
						totalAntesDesvios=evento.getGastos().get(i).getMontoUnitario();						
					}
					if (evento.getGastos().get(i).getIdTipoCargo()==2){
						totalDesvios=(evento.getGastos().get(i).getMontoUnitario()*evento.getGastos().get(i).getCantidad());
					}
				}			
			} 
			evento.setTotalDesvios(totalDesvios);
			evento.setMontoAntesDesvios(totalAntesDesvios);
			this.eventoDao.actualizarTotalMontoAntesDesvios(evento);
			System.out.println("TOTAL ANTES DESVIOS:"+totalAntesDesvios);
			System.out.println("TOTAL DESVIOS:"+totalDesvios);

			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	//Antigua versión
	public boolean actualizarSaldosGastosFinalizar(Evento evento) {
		// TODO Auto-generated method stub
		float totalAntesDesvios=0;
		float totalDesvios=0;
		evento=this.eventoDao.obtenerEventoById(evento.getIdEvento());
		System.out.println("obteniendo Gastos de:");
		int tipoBusqueda=0;		
		try{
			for (int i=0; i<evento.getGastos().size();i++){
				System.out.println(evento.getGastos().get(i).getIdTipoCargo());
				System.out.println(evento.getGastos().get(i).getIdArea());
				if (evento.getGastos().get(i)!=null){
					if (evento.getGastos().get(i).getIdTipoCargo()==1 && evento.getGastos().get(i).getIdArea()==2){		
						totalAntesDesvios=totalAntesDesvios+evento.getGastos().get(i).getMontoUnitario();						
					}
					if (evento.getGastos().get(i).getIdTipoCargo()==2){
						totalDesvios=totalDesvios+(evento.getGastos().get(i).getMontoUnitario()*evento.getGastos().get(i).getCantidad());
					}
				}			
			} 
			evento.setTotalDesvios(totalDesvios);
			evento.setMontoAntesDesvios(totalAntesDesvios);
			if (evento.getGastos().size()>0){
				this.eventoDao.actualizarTotalMontoAntesDesvios(evento);
			}
			System.out.println("TOTAL ANTES DESVIOS:"+totalAntesDesvios);
			System.out.println("TOTAL DESVIOS:"+totalDesvios);

			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	//Antigua versión
	public boolean actualizarSaldosGastosOld(Evento evento) {
		// TODO Auto-generated method stub
		float totalAntesDesvios=0;
		float totalDesvios=0;
		evento=this.eventoDao.obtenerEventoById(evento.getIdEvento());
		System.out.println("obteniendo Gastos de:");
		int tipoBusqueda=0;		
		try{
			for (int i=0; i<evento.getGastos().size();i++){
				System.out.println(evento.getGastos().get(i).getIdTipoCargo());
				System.out.println(evento.getGastos().get(i).getIdArea());
				if (evento.getGastos().get(i)!=null){
					if (evento.getGastos().get(i).getIdTipoCargo()==1 && evento.getGastos().get(i).getIdArea()==2){		
						totalAntesDesvios=totalAntesDesvios+evento.getGastos().get(i).getMontoUnitario();						
					}
					if (evento.getGastos().get(i).getIdTipoCargo()==2){
						totalDesvios=totalDesvios+(evento.getGastos().get(i).getMontoUnitario()*evento.getGastos().get(i).getCantidad());
					}
				}			
			} 
			evento.setTotalDesvios(totalDesvios);
			evento.setMontoAntesDesvios(totalAntesDesvios);
			this.eventoDao.actualizarTotalMontoAntesDesvios(evento);
			System.out.println("TOTAL ANTES DESVIOS:"+totalAntesDesvios);
			System.out.println("TOTAL DESVIOS:"+totalDesvios);

			return true;
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void agregarFileGasto(Gasto gasto) {
		// TODO Auto-generated method stub
		try{
			this.eventoDao.agregarFileGasto(gasto);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Override
	public void agregarFileFactura(Factura factura) {
		// TODO Auto-generated method stub
		try{
			this.eventoDao.agregarFileFactura(factura);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public Gasto obtenerGastoById(int i) {
		// TODO Auto-generated method stub
		return this.eventoDao.obtenerGastoById(i);
	}

	@Override
	public void eliminarBitacora(Integer idBitacora) {
		// TODO Auto-generated method stub
		this.eventoDao.eliminarBitacora(idBitacora);
	}

	@Override
	public void desactivarMedicoTratante(Integer idMedicoTratante) {
		// TODO Auto-generated method stub
		this.eventoDao.desactivarMedicoTratante(idMedicoTratante);
	}

	@Override
	public List<Evento> obtenerEventosLtV2(String fecha1, String fecha2) {
		// TODO Auto-generated method stub
		try{
			return this.eventoDao.obtenerEventosLtByParam(fecha1,fecha2);
		}
		catch (Exception e){			
			e.printStackTrace();
			return null;
		}		
	}

	@Override
	public boolean eliminarEvento(Evento evento) {
		// TODO Auto-generated method stub
		int check=0;
		try{
			check=this.eventoDao.eliminarBitacorasByIdEvento(evento);
			check=this.eventoDao.eliminarGastosByIdEvento(evento);
			check=this.eventoDao.eliminarRegSegPersonalByIdEvento(evento);
			check=this.eventoDao.eliminarEventoByIdEvento(evento);
			return true;
		}
		catch(Exception e){
			System.out.println("ERROR:"+e.getMessage());
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Integer tieneEdosCta(Integer idEvent, Integer tipo) {
		// TODO Auto-generated method stub
		return this.eventoDao.tieneEdosCta(idEvent, tipo);
	}
	

}
