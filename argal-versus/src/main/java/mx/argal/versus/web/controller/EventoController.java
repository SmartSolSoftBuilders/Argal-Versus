//smartcommit ok
package mx.argal.versus.web.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.List;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.codec.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.type.ByteTypeHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import mx.argal.seguridad.modelo.UsuarioSeguridad;
import mx.argal.seguridad.util.SeguridadUtil;
import mx.argal.servicios.ClienteServicio;
import mx.argal.servicios.EventoServicio;
import mx.argal.servicios.EventoServicio;
import mx.argal.servicios.ImplantServicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.InputStream;

/**
 * Controller encargado de devolver la vista principal o index de la aplicaciÃ³n.
 * 
 * El path colocado en la anotaciÃ³n @RequestMappig corresponde a la cofiguraciÃ³n dentro
 * del archivo web.xml
 * 
 * <pre>
 * {@code
 *   <welcome-file-list>
 *       <welcome-file>mvc/index</welcome-file>
 *   </welcome-file-list>   
 * }
 * </pre>
 * 
 * @author SmartSolutions
 *
 */
@Controller
@RequestMapping("/evento")
public class EventoController {
	
	public final String ROLE_ADMINISTRADOR="ROLE_ADMINISTRADOR";
	public final String ROLE_IMPLANT="ROLE_IMPLANT";
	public final String ROLE_CLIENTE="ROLE_CLIENTE";
	
	@Autowired
	private EventoServicio eventoServicio;	
	
	@Autowired
	private ImplantServicio implantServicio;
	
	@Autowired
	private ClienteServicio clienteServicio;
	
	@RequestMapping(value="/guardareventoeditar",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public boolean guardarEventoEditar(@RequestBody Evento evento,HttpServletRequest request){
		if (request.getSession().getAttribute("idImplantLogin")!=null){
			evento.getImplant().setIdImplant(Integer.parseInt(request.getSession().getAttribute("idImplantLogin").toString()));
		}
			
    	System.out.println("<OTIKA>Guardando!!!"+evento.getImplant().getIdImplant());
    	System.out.println("<OTIKA>Guardando Aseguradora!!!"+evento.getRegistroGastosMayores());
    	System.out.println("<OTIKA>Guardando Banco!!"+evento.getRegistroSeguroPersonal());
    	this.eventoServicio.editarEvento(evento);
    	/*Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    	evento.setFechaCaptura(date);
    	evento.setFechaIngreso(date);*/
    	return true;
    	//return this.eventoServicio.agregarEvento(evento); 
	}
	
	@RequestMapping(value="/guardareventoeditarv2",method = RequestMethod.POST)
    @ResponseBody    
    public boolean guardarEventoEditarV2(@ModelAttribute(value="evento") Evento evento,HttpServletRequest request){
		try{
		if (request.getSession().getAttribute("idImplantLogin")!=null){
			evento.getImplant().setIdImplant(Integer.parseInt(request.getSession().getAttribute("idImplantLogin").toString()));
		}
		/*
		Enumeration<String> parameterNames = request.getParameterNames();		  
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();

			System.out.println(paramName);
            String[] paramValues = request.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                System.out.println("t" + paramValue);
					System.out.println("n");
            }
        }		
        */
		if (request.getParameter("diagnosticoIngreso1.idIcd")!=null)
			evento.getDiagnosticoIngreso1().setIdIcd(Integer.parseInt(request.getParameter("diagnosticoIngreso1.idIcd")));
		if (request.getParameter("diagnosticoIngreso2.idIcd")!=null){
			if (!request.getParameter("diagnosticoIngreso2.idIcd").equals("")){
				System.out.println("ICD2:"+request.getParameter("diagnosticoIngreso2.idIcd"));
				evento.getDiagnosticoIngreso2().setIdIcd(Integer.parseInt(request.getParameter("diagnosticoIngreso2.idIcd")));
			}
		}		
    	System.out.println("<OTIKA>Guardando!!!"+evento.getImplant().getIdImplant());
    	System.out.println("<OTIKA>Guardando Aseguradora!!!"+evento.getRegistroGastosMayores());
    	System.out.println("<OTIKA>Guardando Banco!!"+evento.getRegistroSeguroPersonal());    	
    	if (evento.getTipoEvento().getIdTipoEvento()!=4){
    		evento.getRegistroSeguroPersonal().setNacimientoHoraNacimiento("01:00");
    	}
    	this.eventoServicio.editarEvento(evento);
		}
		catch(Exception e){
			e.printStackTrace();
		}
    	/*Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    	evento.setFechaCaptura(date);
    	evento.setFechaIngreso(date);*/
    	return true;
    	//return this.eventoServicio.agregarEvento(evento); 
	}
	
	
	@RequestMapping(value="/guardareventov2",method = RequestMethod.POST)
    @ResponseBody
    public boolean guardarEventoV20(@ModelAttribute(value="Evento") Evento evento,HttpServletRequest request){
    //public boolean guardarEvento(@RequestBody Evento evento,HttpServletRequest request){
		if (request.getSession().getAttribute("idImplantLogin")!=null){
			evento.getImplant().setIdImplant(Integer.parseInt(request.getSession().getAttribute("idImplantLogin").toString()));
		}
		int idImplant = Integer.parseInt(""+request.getSession().getAttribute("idImplantLogin"));
		evento.getImplant().setIdImplant(idImplant);
		/*   
		Enumeration<String> parameterNames = request.getParameterNames();
		   
		           while (parameterNames.hasMoreElements()) {
		   
		               String paramName = parameterNames.nextElement();
		   
		   			System.out.println(paramName);		   
		   			System.out.println("n");		
		               String[] paramValues = request.getParameterValues(paramName);		   
		               for (int i = 0; i < paramValues.length; i++) {		   
		                   String paramValue = paramValues[i];		   
		                   System.out.println("t" + paramValue);		   
		   				   System.out.println("n");
		               }		    
		           }
		           */
		if (request.getParameter("diagnosticoIngreso1.idIcd")!=null)
			evento.getDiagnosticoIngreso1().setIdIcd(Integer.parseInt(request.getParameter("diagnosticoIngreso1.idIcd")));
		if (request.getParameter("diagnosticoIngreso2.idIcd")!=null){
			if (!request.getParameter("diagnosticoIngreso2.idIcd").equals("")){
				System.out.println("ICD2:"+request.getParameter("diagnosticoIngreso2.idIcd"));
				evento.getDiagnosticoIngreso2().setIdIcd(Integer.parseInt(request.getParameter("diagnosticoIngreso2.idIcd")));
			}
		}			
		System.out.println("<OTIKA>Guardando!!!"+evento.getImplant().getIdImplant());		
		System.out.println("<OTIKA>Guardando!!!"+evento.getHoraIngreso());    	
    	System.out.println("<OTIKA>Guardando Banco!!"+evento.getRegistroSeguroPersonal());
    	System.out.println("<OTIKA>Guardando Banco!!"+evento.getDiagnosticoIngreso1().getIdIcd());
    	System.out.println("<OTIKA>Guardando Banco!!"+evento.getDiagnosticoIngreso2().getIdIcd());
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    	evento.setFechaCaptura(date);
    	if (evento.getTipoEvento().getIdTipoEvento()!=4){
    		evento.getRegistroSeguroPersonal().setNacimientoHoraNacimiento("01:00");
    	}    	
    		
    	//evento.setFechaIngreso(date);    	
    	return this.eventoServicio.agregarEvento(evento); 
	}
	
	@RequestMapping(value="/guardarevento",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public boolean guardarEvento(@RequestBody Evento evento,HttpServletRequest request){
		if (request.getSession().getAttribute("idImplantLogin")!=null){
			evento.getImplant().setIdImplant(Integer.parseInt(request.getSession().getAttribute("idImplantLogin").toString()));
		}
		
		System.out.println("<OTIKA>Guardando!!!"+evento.getImplant().getIdImplant());
		System.out.println("<OTIKA>Guardando!!!"+evento.getHoraIngreso());    	
		System.out.println("<OTIKA>Guardando Aseguradora!!!"+evento.getRegistroGastosMayores());
    	System.out.println("<OTIKA>Guardando Banco!!"+evento.getRegistroSeguroPersonal());
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    	evento.setFechaCaptura(date);
    	//evento.setFechaIngreso(date);    	
    	return this.eventoServicio.agregarEvento(evento); 
	}
	
	@RequestMapping(value="/guardarmedtrat",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public boolean guardarMedicoTratante(@RequestBody MedicoTratante medicoTratante,HttpServletRequest request){
		System.out.println("<OTIKA>Guardando!!!"+medicoTratante);
    	//return this.eventoServicio.agregarEvento(evento); 
		this.eventoServicio.agregarMedicoTratante(medicoTratante);
		return true;
	}
	
	@RequestMapping(value="/guardarmedtratv2",method = RequestMethod.POST)
    @ResponseBody
    public boolean guardarMedicoTratanteV2(    		    		
    		@RequestParam (value = "valores[]") String[] valores){
		try{
			System.out.println("<OTIKA>Guardando!!!"+valores);
			for (int i=0; i<valores.length; i++){
				System.out.println(valores[i]);
			}
			MedicoTratante medico = new MedicoTratante();
			medico.setNombre(valores[0]);
			medico.setTipo(Integer.parseInt(valores[1]));
			List<Especialidad> especialidades = new ArrayList<Especialidad>();
			Especialidad especialidad = new Especialidad();
			for (int i=2; i<valores.length; i++){
				especialidad = new Especialidad();
				especialidad.setIdEspecialidad(Integer.parseInt(valores[i]));
				especialidades.add(especialidad);
			}
			medico.setEspecialidades(especialidades);	
			this.eventoServicio.agregarMedicoTratante(medico);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("GUARDADO CORRECTAMENTE");
		return true;
	}

	
	@RequestMapping(value="/eliminarmedtrat",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public List<MedicoTratante> desactivarMedicoTratante(@ModelAttribute(value="MedicoTratante") MedicoTratante medicoTratante,HttpServletRequest request){
		System.out.println("<OTIKA>Eliminando!!!"+medicoTratante.getIdMedicoTratante());
    	//return this.eventoServicio.agregarEvento(evento); 
		this.eventoServicio.desactivarMedicoTratante(medicoTratante.getIdMedicoTratante());
		return this.eventoServicio.obtenerMedicosTratantes();
	}

	@RequestMapping(value="/obtenereventobyid",method = RequestMethod.POST)
    @ResponseBody
    public Evento obtenerEvento(@RequestBody Evento evento){
    	System.out.println("<OTIKA>Obteniendo evento!!!"+evento.getIdEvento());    	    	
    	return this.eventoServicio.obtenerEvento(evento); 
	}
	
	@RequestMapping(value="/obtenereventobyidv2",method = RequestMethod.POST)
    @ResponseBody
    public Evento obtenerEventoV2(@ModelAttribute(value="evento") Evento evento){
    	System.out.println("<OTIKA>Obteniendo evento!!!"+evento.getIdEvento());    	
    	return this.eventoServicio.obtenerEventoById(evento.getIdEvento());
    	//return this.eventoServicio.obtenerEvento(evento); 
	}
	
	@RequestMapping(value="/gettipousuario",method = RequestMethod.POST)
    @ResponseBody
    public int tipoUsuario(HttpServletRequest request){
    	System.out.println("<OTIKA>Obteniendo tipo usuario!!!");    	    	
    	int tipoUsuario=0;
    	String rol = ""+request.getSession().getAttribute("rolUser");
    	if (rol.equals(ROLE_ADMINISTRADOR))
    		return 3;
    	if (rol.equals(ROLE_IMPLANT))
    		return 1;
    	if (rol.equals(ROLE_CLIENTE))
    		return 2;
    	return 0;
	}
	
	@RequestMapping(value="/geteventos",method = RequestMethod.POST)
    @ResponseBody
    public List<Evento> obtenerEventos(HttpServletRequest request){
		System.out.println("<OTIKA>TEST:"+this.eventoServicio.obtenerEventos());
		String nickImplant=""+request.getSession().getAttribute("userSession");
		System.out.println("<OTIKA>TEST GET EVENTOS FOR:"+nickImplant);		
		String rol="";
		rol=""+request.getSession().getAttribute("rolUser");
		System.out.println("<OTIKA>TEST GET EVENTOS WITH ROLE:"+rol);
		if (rol!=null){
			if (rol.equals(ROLE_IMPLANT)){
				Implant implant = new Implant();
				implant.setNickImplant(nickImplant);
				implant=this.implantServicio.obtenerImplantByNick(implant);
				return this.eventoServicio.obtenerEventosByImplant(implant);
			}
			if (rol.equals(ROLE_CLIENTE)){
				Cliente cliente = new Cliente();
				cliente.setNickCliente(nickImplant);				
				cliente = this.clienteServicio.obtenerClienteByNick(cliente);
				return this.eventoServicio.obtenerEventosByCliente(cliente);
			}
		}
		System.out.println("<OTIKA>Regreso:"+rol);
		try{
			return this.eventoServicio.obtenerEventos();
		}
		catch(Exception e){
			System.out.println("ERROR!");
			e.printStackTrace();
		}
		return null;
	}
	//Tests
	@RequestMapping(value="/geteventosltv2",method = RequestMethod.GET)
    @ResponseBody
    public List obtenerEventosLtv2(@RequestParam String mesLimite,HttpServletRequest request){
		List<Evento> eventos = new ArrayList<Evento>();
		String nickImplant=""+request.getSession().getAttribute("userSession");
		System.out.println("<OTIKA>TEST GET EVENTOS FOR:"+nickImplant);	
		String rol="";
		rol=""+request.getSession().getAttribute("rolUser");
		System.out.println("<OTIKA>TEST GET EVENTOS WITH ROLE:"+rol+"->"+mesLimite.length());
		/* SE ESTABLECE EL RANGO DE FECHAS*/
		int dias=0;
		if (mesLimite.equals("1")){
			dias=-31;
		}
		if (mesLimite.equals("2")){
			dias=-62;
		}
		if (mesLimite.equals("3")){
			dias=-124;
		}
		if (mesLimite.equals("4")){
			dias=-200;
		}
		if (mesLimite.length()>1){
			dias=-62;
		}
		String fecha1="";
		String fecha2="";		
		if (!mesLimite.equals("5")){
			Date date = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");		
			System.out.println("Fecha: "+dateFormat.format(date));
			fecha1=dateFormat.format(date);
			System.out.println("Buscar eventos desde:"+fecha1);
			fecha2=dateFormat.format(sumarRestarDiasFecha(new Date(), dias));		
			System.out.println("Buscar eventos hasta:"+fecha2);
		}
		else{
			fecha1=null;
		}
		/* */
		try{
			List eventosTmp = new ArrayList();
			if (rol!=null){
				if (rol.equals(ROLE_IMPLANT)){
					Implant implant = new Implant();
					implant.setNickImplant(nickImplant);
					implant=this.implantServicio.obtenerImplantByNick(implant);
					eventos = this.eventoServicio.obtenerEventosByImplant(implant);
				}
				if (rol.equals(ROLE_CLIENTE)){
					Cliente cliente = new Cliente();
					cliente.setNickCliente(nickImplant);				
					cliente = this.clienteServicio.obtenerClienteByNick(cliente);
					eventos = this.eventoServicio.obtenerEventosByCliente(cliente);
				}				
				if (rol.equals(ROLE_ADMINISTRADOR)){
					eventos= this.eventoServicio.obtenerEventosLtV2(fecha1,fecha2);
				}
				List eventoTmp=new ArrayList<Evento>();
				String[] statusEvento={"N/A","EN CURSO","EGRESADO","FINALIZADO"};
				for (int i=0;i<eventos.size();i++){
					eventoTmp = new ArrayList<Evento>();
					eventoTmp.add(eventos.get(i).getIdEvento());
					eventoTmp.add(eventos.get(i).getFolioHospital());
					eventoTmp.add(eventos.get(i).getFolioArgal());
					eventoTmp.add(new SimpleDateFormat("yyyy/MM/dd").format(eventos.get(i).getFechaIngreso()));
					eventoTmp.add(eventos.get(i).getCliente().getNombreCorto());
					eventoTmp.add(eventos.get(i).getHospital().getNombreHospital());
					if (eventos.get(i).getRegistroSeguroPersonal()!=null){
						eventoTmp.add(eventos.get(i).getRegistroSeguroPersonal().getNombrePaciente()+" "+eventos.get(i).getRegistroSeguroPersonal().getAppPaciente()+" "+eventos.get(i).getRegistroSeguroPersonal().getApmPaciente());
					}
					else
						eventoTmp.add("N/D");
					eventoTmp.add(statusEvento[eventos.get(i).getStatusEvento()]);					
					eventoTmp.add("<a href='#' onclick='verEditar(\""+eventos.get(i).getIdEvento()+"\")'><img width='20' height='20' src='static/img/editar.jpg'/></a>");
					eventoTmp.add("<a href='#' onclick='verGastos(\""+eventos.get(i).getIdEvento()+"\")'><img width='20' height='20' src='static/img/gastos1.jpg'/></a>");
					eventoTmp.add("<a href='#' onclick='verBitacoras(\""+eventos.get(i).getIdEvento()+"\")'><img width='20' height='20' src='static/img/segmedico1.jpg'/></a>");
					//Candados para Egresos, el idArea para edos de cta de egreso es 3
					if (this.eventoServicio.tieneEdosCta(eventos.get(i).getIdEvento(),3)>0)
						eventoTmp.add("<a href='#' onclick='verEgreso(\""+eventos.get(i).getIdEvento()+"\")'><img width='20' height='20' src='static/img/egresopaciente1.jpg'/></a>");
					else
						eventoTmp.add("<a href='#' onclick='showMsjCandado(2)'><img width='20' height='20' src='static/img/egresopaciente2.jpg'/></a>");
					//Candados para Finalizar, el idArea para edos de cta finales es 2
					if (eventos.get(i).getStatusEvento()==1){
						eventoTmp.add("<a href='#' onclick='showMsjCandado(3)'><img width='20' height='20' src='static/img/finalizarevento2.jpg'/></a>");
					}
					else{
						if (this.eventoServicio.tieneEdosCta(eventos.get(i).getIdEvento(),2)>0)
							eventoTmp.add("<a href='#' onclick='verBalance(\""+eventos.get(i).getIdEvento()+"\")'><img width='20' height='20' src='static/img/finalizarevento1.jpg'/></a>");
						else
							eventoTmp.add("<a href='#' onclick='showMsjCandado(4)'><img width='20' height='20' src='static/img/finalizarevento2.jpg'/></a>");
							
					}
					eventoTmp.add(eventos.get(i).getImplant().getNickImplant());
					if (rol.equals(ROLE_ADMINISTRADOR)){
						eventoTmp.add("<a href='#' onclick='eliminarEvento(\""+eventos.get(i).getIdEvento()+"\")'><img width='20' height='20' src='static/img/borrar.jpg'/></a>");
						//eventoTmp.add("<a href='#' onclick='alert(\"Próximamente...\");'><img width='20' height='20' src='static/img/borrar.jpg'/></a>");
					}
					eventosTmp.add(eventoTmp);
				}
				return eventosTmp;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());			
		}
		return null;
	}
	
	@RequestMapping(value="/geteventoslt",method = RequestMethod.GET)
    @ResponseBody
    public List<Evento> obtenerEventosLt(HttpServletRequest request){
		String nickImplant=""+request.getSession().getAttribute("userSession");
		System.out.println("<OTIKA>TEST GET EVENTOS FOR:"+nickImplant);	
		String rol="";
		rol=""+request.getSession().getAttribute("rolUser");
		System.out.println("<OTIKA>TEST GET EVENTOS WITH ROLE:"+rol);
		try{
			if (rol!=null){
				if (rol.equals(ROLE_IMPLANT)){
					Implant implant = new Implant();
					implant.setNickImplant(nickImplant);
					implant=this.implantServicio.obtenerImplantByNick(implant);
					return this.eventoServicio.obtenerEventosByImplant(implant);
				}
				if (rol.equals(ROLE_CLIENTE)){
					Cliente cliente = new Cliente();
					cliente.setNickCliente(nickImplant);				
					cliente = this.clienteServicio.obtenerClienteByNick(cliente);
					return this.eventoServicio.obtenerEventosByCliente(cliente);
				}
				return this.eventoServicio.obtenerEventosLt();
			}
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());			
		}
		return null;
	}

	@RequestMapping(value="/getcombovalues",method = RequestMethod.POST)
    @ResponseBody
    public List obtenerComboValues(){
		System.out.println("<OTIKA>Getcombovalues");
		return this.eventoServicio.obtenerDatosCombo();
	}
	
	@RequestMapping(value="/geticds",method = RequestMethod.POST)
    @ResponseBody
    public List<Icd> obtenerIcds(){
		System.out.println("<OTIKA>GetIcds");
		return this.eventoServicio.obtenerIcds();
	}
	@RequestMapping(value="/geticdslt",method = RequestMethod.POST)
    @ResponseBody
    public List obtenerIcdsLt(@RequestParam String tipo){
		System.out.println("<OTIKA>GetIcds"+tipo);
		List<Icd> icds = this.eventoServicio.obtenerIcds();
		List icdList  = new ArrayList();
		List tmp  = new ArrayList();				
		for (int i=0; i<icds.size();i++){
			tmp  = new ArrayList();
			tmp.add(icds.get(i).getIdIcd());
			tmp.add(icds.get(i).getDescripcion());
			tmp.add(icds.get(i).getClave());
			if (tipo.equals("3")){
				tmp.add("<a href='#' onclick='seleccionarIcdEgreso("+icds.get(i).getIdIcd()+","+"\""+icds.get(i).getDescripcion()+"\""+","+tipo+")'>Seleccionar</a>");
			}
			else
				tmp.add("<a href='#' onclick='seleccionarIcd("+icds.get(i).getIdIcd()+","+"\""+icds.get(i).getDescripcion()+"\""+","+tipo+")'><img src='../../static/img/v2.0/img/checked.png' width=30 height=30/></a>");				
			icdList.add(tmp);
		}
		return icdList;
	}
	
	@RequestMapping(value="/getcptslt",method = RequestMethod.POST)
    @ResponseBody
    public List obtenerCptsLt(@RequestParam String tipo){
		System.out.println("<OTIKA>GetCpts"+tipo);
		List<Cpt> cpts = this.eventoServicio.obtenerCpts();
		List icdList  = new ArrayList();
		List tmp  = new ArrayList();				
		for (int i=0; i<cpts.size();i++){
			tmp  = new ArrayList();
			tmp.add(cpts.get(i).getIdCpt());
			tmp.add(cpts.get(i).getDescripcion());
			tmp.add(cpts.get(i).getCveCpt());			
			tmp.add("<a href='#' onclick='seleccionarCpt("+cpts.get(i).getIdCpt()+","+"\""+cpts.get(i).getDescripcion()+"\""+","+tipo+")'>Seleccionar</a>");							
			icdList.add(tmp);
		}
		return icdList;
	}
	
	@RequestMapping(value="/getcpts",method = RequestMethod.POST)
    @ResponseBody
    public List<Cpt> obtenerCpts(){
		System.out.println("<OTIKA>GetCpts");
		return this.eventoServicio.obtenerCpts();
	}
	
	@RequestMapping(value="/getmedicostratantesv3",method = RequestMethod.POST)
    @ResponseBody
    public List obtenerMedicosTratantesV3(){
		System.out.println("<OTIKA>GetcombovaluesMT");
		List<MedicoTratante>  medicosTratantes  = eventoServicio.obtenerMedicosTratantes();
		String[] tipoMedicos={"","RED","STAFF","INTERINO"};
		List medicosTmp = new ArrayList();		
		try{
			for (int i=0; i<medicosTratantes.size();i++){
				List medTrat = new ArrayList();
				medTrat.add(medicosTratantes.get(i).getIdMedicoTratante());
				medTrat.add(medicosTratantes.get(i).getNombre());
				medTrat.add(tipoMedicos[medicosTratantes.get(i).getTipo()]);
				String especialidades="";
				for (int j=0; j<medicosTratantes.get(i).getEspecialidades().size();j++){
					if (j>1){
						especialidades+=",";
					}
					especialidades+=medicosTratantes.get(i).getEspecialidades().get(j).getDescripcion();
				}
				medTrat.add(especialidades);
				medTrat.add("<a href='#' onclick='eliminarMed("+medicosTratantes.get(i).getIdMedicoTratante()+")'>Eliminar</a>");
				medicosTmp.add(medTrat);
			}
			System.out.println("<OTIKA>"+medicosTratantes.size());
		}
		catch(Exception e){
			System.out.println("ERROR:"+e.getMessage());
		}
		return medicosTmp;
	}
	
	@RequestMapping(value="/getmedicostratantes",method = RequestMethod.POST)
    @ResponseBody
    public List<MedicoTratante> obtenerMedicosTratantes(){
		System.out.println("<OTIKA>GetcombovaluesMT");
		List<MedicoTratante>  medicosTratantes  = new ArrayList<MedicoTratante>();
		try{
			medicosTratantes=eventoServicio.obtenerMedicosTratantes();
			System.out.println("<OTIKA>"+medicosTratantes.size());
			System.out.println(medicosTratantes.get(0));
			System.out.println(medicosTratantes.get(1));

		}
		catch(Exception e){
			System.out.println("ERROR:"+e.getMessage());
		}
		return medicosTratantes;
	}
	
	
	@RequestMapping(value="/getmedicostratantesv2",method = RequestMethod.POST)
    @ResponseBody
    public List obtenerMedicosTratantesV2(@RequestParam String tipo){
		System.out.println("<OTIKA>getMedTrat");
		List medicosTratantesTmp = new ArrayList();
		List tmp =  new ArrayList();
		String[] tipoMedicos={"","RED","STAFF","INTERINO"};
		List<MedicoTratante>  medicosTratantes  = new ArrayList<MedicoTratante>();
		try{
			medicosTratantes=eventoServicio.obtenerMedicosTratantes();
			for (int i=0;i<medicosTratantes.size();i++){				
				tmp =  new ArrayList();
				tmp.add(medicosTratantes.get(i).getNombre());
				tmp.add(tipoMedicos[medicosTratantes.get(i).getTipo()]);
				String especialidades="";
				String coma=",";
				for (int j=0;j<medicosTratantes.get(i).getEspecialidades().size();j++){
					if (j>0)
						especialidades+=coma;
					especialidades+=medicosTratantes.get(i).getEspecialidades().get(j).getDescripcion();										
				}
				tmp.add(especialidades);
				tmp.add("<a href='#' onclick='seleccionarMedicoTratante("+medicosTratantes.get(i).getIdMedicoTratante()+","+"\""+medicosTratantes.get(i).getNombre()+"\""+","+medicosTratantes.get(i).getTipo()+","+"\""+especialidades+"\","+tipo+")'>Seleccionar</a>");				
				medicosTratantesTmp.add(tmp);	
			}

		}
		catch(Exception e){
			System.out.println("ERROR:"+e.getMessage());
		}		
		return medicosTratantesTmp;
	}	
	
	@RequestMapping(value="/guardargasto",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public Evento guardarGasto(@RequestBody Evento evento){    	
    	System.out.println("<OTIKA>Guardando GASTO!!"+evento.getGastos());    	
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");    	
    	//evento.getGastos().get(1).setFechaIngreso(date);
    	this.eventoServicio.agregarGasto(evento);
    	return this.eventoServicio.obtenerEvento(evento);
	}
	
	@RequestMapping(value="/guardargastov2",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public Evento guardarGastoV2(@ModelAttribute(value="gasto") Gasto gasto, HttpServletRequest request, BindingResult result){    	
		Evento evento = new Evento();
		evento.setIdEvento(gasto.getIdGasto());
		List<Gasto> gastos = new ArrayList<Gasto>();
		gastos.add(gasto);
		evento.setGastos(gastos);
		System.out.println("<OTIKA>Guardando GASTOv2!!"+evento.getGastos());    	
    	System.out.println("<OTIKA>Guardando GASTOv2!!"+evento.getIdEvento());
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");    	
    	//evento.getGastos().get(1).setFechaIngreso(date);    	
    	try{
	    	if (gasto.getIdTipoCargo()==4){	    			    			    		              
	    		gasto.setArchivo(gasto.getRutaEvidencia().getBytes(Charsets.UTF_8));
	            gasto.setRutaEvidencia("txt");
	    	}
    	}
    	catch (Exception e){
    		System.out.println("ERROR AL GUARDAR!"+ e.getMessage());
    	}
    	this.eventoServicio.agregarGastoEvitado(evento);
    	return this.eventoServicio.obtenerEvento(evento);
	}
	
	@RequestMapping(value="/eliminargasto",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public Evento EliminarGasto(@RequestBody Evento evento){    	
    	System.out.println("<OTIKA>Eliminando GASTO!!"+evento.getGastos());    	    	
    	this.eventoServicio.eliminarGasto(evento);
    	return this.eventoServicio.obtenerEvento(evento);
	}
	
	@RequestMapping(value="/eliminargastov2",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){    
	public Evento eliminarGastoV2(@ModelAttribute(value="gasto") Gasto gasto, BindingResult result){
		System.out.println("<OTIKA>Eliminando GASTO!!"+gasto.getId());
		System.out.println("<OTIKA>Eliminando GASTO!!"+gasto.getIdGasto());
		Evento evento = new Evento();
		evento.setIdEvento(Integer.parseInt(""+gasto.getId()));
		List<Gasto> gastos = new ArrayList<Gasto>();		
		gastos.add(gasto);
		evento.setGastos(gastos);    	    	    
    	this.eventoServicio.eliminarGasto(evento);
    	return this.eventoServicio.obtenerEvento(evento);
	}

	
	
	
	@RequestMapping(value="/guardareditargasto",method = RequestMethod.POST)
    @ResponseBody
	public Evento guardarEditarGasto(@RequestBody Evento evento){    	
    	System.out.println("<OTIKA>Actualizando Gasto evento!!"+evento.getGastos());    	
    	Date date = new Date();
    	
    	//Actualizando el total
    	System.out.println("EDITANDO EL GASTO");   
    	this.eventoServicio.guardarEditarGasto(evento);
    	System.out.println("ACTUALIZANDO SALDO ANTES");
    	evento=this.eventoServicio.obtenerEvento(evento);
     	this.eventoServicio.actualizarSaldosGastos(evento); 
    	System.out.println(evento);
    	return evento;    			
	} 	
	
	@RequestMapping(value="/guardarbitacora",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public Evento guardarBitacora(@RequestBody Evento evento){    	
    	System.out.println("<OTIKA>Guardando Bitácora!!"+evento.getBitacoras());    	
    	this.eventoServicio.agregarBitacora(evento);
    	evento=this.eventoServicio.obtenerEvento(evento);
    	System.out.println("BITACORAS AHORA"+evento.getBitacoras().size());
    	return evento;
	}
	
	@RequestMapping(value="/getBitacoraByIdV2",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public Bitacora getBitacoraV2(@ModelAttribute(value="bitacora") Bitacora bitacora, BindingResult result){    	
    	System.out.println("<OTIKA>Obtener Bitácora!!"+bitacora.getIdBitacora());    	
    	bitacora=this.eventoServicio.obtenerBitacoraById(bitacora.getIdBitacora());    	    	
    	return bitacora;
	}
	
	@RequestMapping(value="/guardareditarbitacorav2",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public Evento guardarEditarBitacoraV2(@ModelAttribute(value="Bitacora") Bitacora bitacora,HttpServletRequest request){
		System.out.println("<OTIKA>Guardando-actualizar Bitácora!!"+bitacora.getIdBitacora());
    	System.out.println("<OTIKA>Guardando-actualizar Bitácora!!"+bitacora.getId());
    	Evento evento = new Evento();
		evento.setIdEvento(Integer.parseInt(""+bitacora.getId()));
		List<Bitacora> bitacoras = new ArrayList<Bitacora>();		
		bitacoras.add(bitacora);
		evento.setBitacoras(bitacoras);
    	this.eventoServicio.editarBitacora(evento);
    	evento=this.eventoServicio.obtenerEvento(evento);
    	System.out.println("BITACORAS AHORA"+evento.getBitacoras().size());
    	return evento;
	}

	@RequestMapping(value="/guardarbitacorav2",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public Evento guardarBitacoraV2(@ModelAttribute(value="bitacora") Bitacora bitacora, BindingResult result){    	
    	System.out.println("<OTIKA>Guardando Bitácora!!"+bitacora.getIdBitacora());
    	Evento evento = new Evento();
		evento.setIdEvento(Integer.parseInt(""+bitacora.getId()));
		List<Bitacora> bitacoras = new ArrayList<Bitacora>();		
		bitacoras.add(bitacora);
		evento.setBitacoras(bitacoras);
    	this.eventoServicio.agregarBitacora(evento);
    	evento=this.eventoServicio.obtenerEvento(evento);
    	System.out.println("BITACORAS AHORA"+evento.getBitacoras().size());
    	return evento;
	}
	
	@RequestMapping(value="/eliminarbitacorav2",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public Evento eliminarBitacoraV2(@ModelAttribute(value="bitacora") Bitacora bitacora, BindingResult result){    	
    	System.out.println("<OTIKA>Eliminando Bitácora!!"+bitacora.getIdBitacora());
    	Evento evento = new Evento();
		evento.setIdEvento(Integer.parseInt(""+bitacora.getId()));
		List<Bitacora> bitacoras = new ArrayList<Bitacora>();		
		bitacoras.add(bitacora);
		evento.setBitacoras(bitacoras);
    	this.eventoServicio.eliminarBitacora(bitacora.getIdBitacora());    	    	
    	evento=this.eventoServicio.obtenerEvento(evento);
    	System.out.println("BITACORAS AHORA"+evento.getBitacoras().size());
    	return evento;
	}
	@RequestMapping(value="/eliminarbitacora",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public Evento eliminarBitacora(@RequestBody Bitacora bitacora){    	
    	System.out.println("<OTIKA>Elminando Bitácora!!"+bitacora.getIdBitacora());
    	System.out.println("<OTIKA>Elminando Evento!!"+bitacora.getIdRegistroMedico());
    	this.eventoServicio.eliminarBitacora(bitacora.getIdBitacora());
    	Evento evento= new Evento();
    	evento.setIdEvento(bitacora.getIdRegistroMedico());
    	evento=this.eventoServicio.obtenerEvento(evento);
    	System.out.println("BITACORAS AHORA"+evento.getBitacoras().size());
    	return evento;
	}
	
	@RequestMapping(value="/getBitacoraById",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public Bitacora getBitacoraById(@RequestBody Bitacora bitacora){    	
    	System.out.println("<OTIKA>Obtener Bitácora!!"+bitacora.getIdBitacora());    	
    	bitacora=this.eventoServicio.obtenerBitacoraById(bitacora.getIdBitacora());    	    	
    	return bitacora;
	}

	@RequestMapping(value="/guardarfactura",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public Evento guardarFactura(@RequestBody Evento evento){    	
    	System.out.println("<OTIKA>Guardando Facturas!!"+evento.getFacturas());    	
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    	/*if (evento.getBitacoras().get(0)!=null)
    		evento.getBitacoras().get(0).setFechaBitacora(date);    	
    	else {if (evento.getBitacoras().get(1)!=null)
    		evento.getBitacoras().get(1).setFechaBitacora(date);
    	}*/
    	this.eventoServicio.agregarFactura(evento);
    	evento=this.eventoServicio.obtenerEvento(evento);
    	return evento;
	}
	
	@RequestMapping(value="/guardarfacturav2",method = RequestMethod.POST)
    @ResponseBody
    public Evento guardarFacturaV2(@ModelAttribute(value="factura") Factura factura, BindingResult result){    	
    	//System.out.println("<OTIKA>Guardando Facturas!!"+evento.getFacturas());
		Evento evento = new Evento();
		evento.setIdEvento(factura.getIdFactura());
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    	List<Factura> facturas = new ArrayList<Factura>();
    	facturas.add(factura);    	
    	evento.setFacturas(facturas);
    	this.eventoServicio.agregarFactura(evento);
    	evento=this.eventoServicio.obtenerEvento(evento);
    	return evento;
	}
	
	@RequestMapping(value="/eliminarfactura",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public Evento eliminarFactura(@RequestBody Evento evento){    	
    	System.out.println("<OTIKA>Eliminando Facturas!!"+evento.getFacturas());    	
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    	/*if (evento.getBitacoras().get(0)!=null)
    		evento.getBitacoras().get(0).setFechaBitacora(date);    	
    	else {if (evento.getBitacoras().get(1)!=null)
    		evento.getBitacoras().get(1).setFechaBitacora(date);
    	}*/
    	this.eventoServicio.eliminarFactura(evento);
    	evento=this.eventoServicio.obtenerEvento(evento);
    	return evento;
	}
	
	@RequestMapping(value="/eliminarfacturav2",method = RequestMethod.POST)
    @ResponseBody
    public Evento eliminarFacturaV2(@ModelAttribute(value="factura") Factura factura, BindingResult result){		    	    
    	Evento evento = new Evento();
		evento.setIdEvento(Integer.parseInt(""+factura.getId()));
    	Date date = new Date();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    	List<Factura> facturas = new ArrayList<Factura>();
    	facturas.add(factura);    	
    	evento.setFacturas(facturas);
    	System.out.println("<OTIKA>Eliminando Facturas!!"+evento.getFacturas());
    	this.eventoServicio.eliminarFactura(evento);
    	evento=this.eventoServicio.obtenerEvento(evento);
    	return evento;
	}
	
	@RequestMapping(value="/finalizarmontosevento",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public Evento finalizarMontosEvento(@RequestBody Evento evento){    	
    	System.out.println("<OTIKA>Finalizando Evento!!"+evento.getIdEvento());    	
    	/*if (evento.getBitacoras().get(0)!=null)
    		evento.getBitacoras().get(0).setFechaBitacora(date);    	
    	else {if (evento.getBitacoras().get(1)!=null)
    		evento.getBitacoras().get(1).setFechaBitacora(date);
    	}*/
    	this.eventoServicio.finalizarMontosEvento(evento);
    	evento=this.eventoServicio.obtenerEvento(evento);
    	return evento;
	}
	
	@RequestMapping(value="/finalizareventov2",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public Evento finalizarMontosEventoV2(@ModelAttribute(value="evento") Evento evento, BindingResult result){    	
    	System.out.println("<OTIKA>Finalizando Evento!!"+evento.getIdEvento());    	    
    	this.eventoServicio.finalizarMontosEvento(evento);
    	evento=this.eventoServicio.obtenerEvento(evento);
    	return evento;
	}

	
	@RequestMapping(value="/guardareditarbitacora",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public Evento guardarEditarBitacora(@RequestBody Evento evento){    	
    	System.out.println("<OTIKA>Guardando Bitácora!!"+evento.getBitacoras());    	    	
    	this.eventoServicio.editarBitacora(evento);
    	evento=this.eventoServicio.obtenerEvento(evento);
    	return evento;
	}
	
	@RequestMapping(value="/registraraltapaciente",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public Evento altaPaciente(@RequestBody Evento evento){    	
    	System.out.println("<OTIKA>Guardando AltaPaciente!!->"+evento);    	
    	System.out.println("<OTIKA>Guardando AltaPaciente!!->"+evento.getFechaEgreso());
    	//Date date = new Date();
    	//SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    	//evento.setFechaEgreso(date);
    	this.eventoServicio.registrarAltaPaciente(evento);
    	evento=this.eventoServicio.obtenerEvento(evento);
    	return evento;
	}
	
	@RequestMapping(value="/registraraltapacientev2",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public Evento altaPacienteV2(@ModelAttribute(value="evento") Evento evento,HttpServletRequest request){    	
    	System.out.println("<OTIKA>Guardando AltaPaciente!!->"+evento);    	
    	if (request.getParameter("diagnosticoEgreso1.idIcd")!=null)
			evento.getDiagnosticoEgreso1().setIdIcd(Integer.parseInt(request.getParameter("diagnosticoEgreso1.idIcd")));
    	
    	System.out.println("<OTIKA>Guardando AltaPaciente!!->"+evento.getFechaEgreso());    	
    	System.out.println("<OTIKA>Guardando AltaPaciente!!->"+evento.getProcedimiento1().getIdCpt());
    	System.out.println("<OTIKA>Guardando AltaPaciente!!->"+evento.getProcedimiento2().getIdCpt());    	
    	System.out.println("<OTIKA>Guardando AltaPaciente!!->"+evento.getDiagnosticoEgreso1().getIdIcd());
    	System.out.println("<OTIKA>Guardando AltaPaciente!!->"+evento.getFechaEgreso());
    	this.eventoServicio.registrarAltaPaciente(evento);
    	evento=this.eventoServicio.obtenerEvento(evento);
    	return evento;
	}
	
	@RequestMapping(value="/finalizarevento",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public Evento finalizarEvento(@RequestBody Evento evento){    	
    	System.out.println("<OTIKA>Guardando Bitácora!!"+evento);    	    	
    	this.eventoServicio.finalizarEvento(evento);
    	evento=this.eventoServicio.obtenerEvento(evento);
    	return evento;
	}
	
	@RequestMapping(value="/eliminareventov2",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public boolean eliminarEvento(@ModelAttribute(value="evento") Evento evento){    	
    	System.out.println("<OTIKA>Eliminando Bitácora!!"+evento);
    	try{
    		this.eventoServicio.eliminarEvento(evento);
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    	return true;
	}
	
	public Date sumarRestarDiasFecha(Date fecha, int dias){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha); // Configuramos la fecha que se recibe
		calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
		return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos		
	}
	
	@RequestMapping(value="/getgastos",method = RequestMethod.POST)
    @ResponseBody
    public List obtenerGastosByIdEvento(@RequestParam Integer idEvento,HttpServletRequest request){		
		System.out.println("Buscar Gastos de:"+idEvento);
		List respuesta = new ArrayList();
		try{
			Evento evento = this.eventoServicio.obtenerEventoById(idEvento);
			respuesta.add(evento);
			List<Gasto> gastos = evento.getGastos();
			System.out.println(evento.getGastos().size());
			List<Gasto> estadosCuenta = new ArrayList<Gasto>();
			List<Gasto> gastosObservados = new ArrayList<Gasto>();
			List<Gasto> gastosRelevantes = new ArrayList<Gasto>();
			for (int i=0; i<gastos.size();i++){
				//System.out.println(gastos.get(i).getIdTipoCargo());
				if (gastos.get(i).getIdTipoCargo()==1){					
					estadosCuenta.add(gastos.get(i));
				}
				if (gastos.get(i).getIdTipoCargo()==2){
					gastosObservados.add(gastos.get(i));
				}
				if (gastos.get(i).getIdTipoCargo()==3){
					gastosRelevantes.add(gastos.get(i));
				}
			}
			respuesta.add(estadosCuenta);
			respuesta.add(gastosObservados);
			respuesta.add(gastosRelevantes);
			
			return respuesta;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return respuesta;
	}
	
	@RequestMapping(value="/getgastosv2",method = RequestMethod.POST)
    @ResponseBody
    public List obtenerGastosByIdEventoV2(@RequestParam Integer idEvento,HttpServletRequest request){		
		System.out.println("Buscar Gastos de:"+idEvento);
		List respuesta = new ArrayList();
		try{
			Evento evento = this.eventoServicio.obtenerEventoById(idEvento);
			respuesta.add(evento);
			List <Gasto>gastos = evento.getGastos();
			System.out.println(evento.getGastos().size());
			List estadosCuenta = new ArrayList();
			List gastosObservados = new ArrayList();
			List gastosRelevantes = new ArrayList();
			List gastosEvitados = new ArrayList(); 
			String[] tipoEdoCta={"","ESTADO DE CUENTA PARCIAL","ESTADO DE CUENTA FINAL","ESTADO DE CUENTA DE EGRESO"};
			String[] areas={"","PISO","QUIROFANO","TERAPIA INTENSIVA, INTERMEDIA, NEONATAL","URGENCIAS","GASTOS PERSONALES"};
			String[] rubros={"","MEDICAMENTOS","MATERIAL","LABORATORIO Y RX","TERAPIA RESPIRATORIA","HABITACIONES","ANESTESIA (MAQ Y GAS)","RENTAS DE EQUIPOS","INSUMOS PROVEEDOR EXTERNO","CUBICULOS","TERAPIA INTENSIVA","BANCO DE SANGRE","QUIROFANO","TIEMPOS DE SALA"};
			String[] razones={"","NO UTILIZADO","DUPLICADO","NO INDICADO POR DIAGNÓSTICO","NO APARECE EN INDICACIONES MÉDICAS","COSTO INDEBIDO"};
			for (int i=0; i<gastos.size();i++){
				//System.out.println(gastos.get(i).getIdTipoCargo());
				if (gastos.get(i).getIdTipoCargo()==1){
					List edoCta = new ArrayList();
					edoCta.add(gastos.get(i).getIdGasto());
					edoCta.add(tipoEdoCta[gastos.get(i).getIdArea()]);
					edoCta.add((new SimpleDateFormat("yyyy/MM/dd").format(gastos.get(i).getFechaIngreso())));
					edoCta.add("$"+gastos.get(i).getMontoUnitario());					
					//edoCta.add("<a href='#' onclick=\'ajax_download2("+gastos.get(i).getIdGasto()+",\""+gastos.get(i).getRutaEvidencia()+"\")'>ver archivo</a>");
					edoCta.add("<a href='#' onclick=\'ajax_download2("+gastos.get(i).getIdGasto()+",\""+idEvento+"\")'>ver archivo</a>");
					edoCta.add("<a href='#' onclick=\'eliminarGasto("+gastos.get(i).getIdGasto()+")'>Eliminar</a>");																			
					estadosCuenta.add(edoCta);
				}
				if (gastos.get(i).getIdTipoCargo()==2){
					List edoCta = new ArrayList();
					edoCta.add(gastos.get(i).getIdGasto());
					edoCta.add((new SimpleDateFormat("yyyy/MM/dd").format(gastos.get(i).getFechaIngreso())));
					edoCta.add(gastos.get(i).getNombre());
					edoCta.add(gastos.get(i).getMontoUnitario());
					edoCta.add(gastos.get(i).getCantidad());
					edoCta.add(areas[gastos.get(i).getIdArea()]);
					edoCta.add(rubros[gastos.get(i).getIdRubro()]);
					edoCta.add(razones[gastos.get(i).getIdRazon()]);															
					edoCta.add("<a href='#' onclick=\'ajax_download2("+gastos.get(i).getIdGasto()+",\""+gastos.get(i).getRutaEvidencia()+"\")'>ver archivo</a>");
					edoCta.add("<a href='#' onclick=\'eliminarGasto("+gastos.get(i).getIdGasto()+")'>Eliminar</a>");										
					gastosObservados.add(edoCta);
				}
				if (gastos.get(i).getIdTipoCargo()==3){
					List edoCta = new ArrayList();
					edoCta.add(gastos.get(i).getIdGasto());
					edoCta.add((new SimpleDateFormat("yyyy/MM/dd").format(gastos.get(i).getFechaIngreso())));
					edoCta.add(gastos.get(i).getIdTipoCargo());
					edoCta.add(gastos.get(i).getMontoUnitario());
					edoCta.add(areas[gastos.get(i).getIdArea()]);									
					edoCta.add("<a href='#' onclick=\'ajax_download2("+gastos.get(i).getIdGasto()+",\""+gastos.get(i).getRutaEvidencia()+"\")'>ver archivo</a>");
					edoCta.add("<a href='#' onclick=\'eliminarGasto("+gastos.get(i).getIdGasto()+")'>Eliminar</a>");																			
					gastosRelevantes.add(edoCta);
				}
				if (gastos.get(i).getIdTipoCargo()==4){
					List edoCta = new ArrayList();
					edoCta.add(gastos.get(i).getIdGasto());
					edoCta.add((new SimpleDateFormat("yyyy/MM/dd").format(gastos.get(i).getFechaIngreso())));					
					edoCta.add(gastos.get(i).getMontoUnitario());
					edoCta.add(new String(gastos.get(i).getArchivo(), "UTF-8"));																				
					edoCta.add("<a href='#' onclick=\'eliminarGasto("+gastos.get(i).getIdGasto()+")'>Eliminar</a>");																			
					gastosEvitados.add(edoCta);
				}
			}
			respuesta.add(estadosCuenta);
			respuesta.add(gastosObservados);
			respuesta.add(gastosRelevantes);
			respuesta.add(gastosEvitados);
			
			return respuesta;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return respuesta;
	}
	
	@RequestMapping(value="/getbitacorasv2",method = RequestMethod.POST)
    @ResponseBody
    public List obtenerBitacorasV2(@RequestParam Integer idEvento,HttpServletRequest request){		
		System.out.println("Buscar bitacoras de:"+idEvento);
		List respuesta = new ArrayList();
		try{
			Evento evento = this.eventoServicio.obtenerEventoById(idEvento);
			respuesta.add(evento);
			List <Bitacora>bitacoras = evento.getBitacoras();
			System.out.println(evento.getBitacoras().size());
			String[] estadoPaciente={"","PACIENTE ESTABLE","EN ESPERA DE RESULTADOS","PACIENTE EN ESPERA DE ALTA","PACIENTE GRAVE","PACIENTE TERMINAL","PACIENTE EN ESPERA DE DIAGNOSTICO"};						
			List respuesta2 = new ArrayList();
			for (int i=0; i<bitacoras.size();i++){								
					List bitacora = new ArrayList();
					bitacora.add(bitacoras.get(i).getIdBitacora());
					if (bitacoras.get(i).getFechaBitacora()!=null)
						bitacora.add((new SimpleDateFormat("yyyy/MM/dd").format(bitacoras.get(i).getFechaBitacora())));
					else
						bitacora.add("");
					bitacora.add(estadoPaciente[bitacoras.get(i).getIdRegistroMedico()]);
					bitacora.add(bitacoras.get(i).getObservaciones());
					bitacora.add(bitacoras.get(i).getInterconsulta());										
					bitacora.add("<a href='#' onclick=\'showDivBitacora(3,"+bitacoras.get(i).getIdBitacora()+")'>Editar</a>");
					bitacora.add("<a href='#' onclick=\'eliminarBitacora("+bitacoras.get(i).getIdBitacora()+")'>Eliminar</a>");																			
					respuesta2.add(bitacora);				
			}			
			respuesta.add(respuesta2);
			return respuesta;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return respuesta;
	}
	
	@RequestMapping(value="/guardargastosversusv2",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public Evento guardarGastosDesviadoV2(
    		@RequestParam (value = "valores[]") String[] valores){
    				
		System.out.println("GUARDANDO VERSUS");
		System.out.println(valores);
		Evento evento = new Evento();
		List<Gasto>gastos = new ArrayList<Gasto>();
		Gasto gasto = new Gasto();
		//valor[0] es idEvento 
		evento.setIdEvento(Integer.parseInt(valores[0]));
		//valor[1] es num gastos a desviar
		try{
			int numGastosADesviar= Integer.parseInt(valores[1]);
			int inicial=2;
			for (int i=0;i<numGastosADesviar;i++){
				gastos = new ArrayList<Gasto>();
				gasto = new Gasto();
				gasto.setFechaIngreso(new SimpleDateFormat("dd-MM-yyyy").parse(valores[inicial]));
				gasto.setIdArea(Integer.parseInt(valores[inicial+1]));
				gasto.setIdRubro(Integer.parseInt(valores[inicial+2]));
				gasto.setMontoUnitario(Float.parseFloat(valores[inicial+3]));
				gasto.setNombre(valores[inicial+4]);
				gasto.setIdTipoCargo(2);				
				gasto.setIdRazon(5);
				gasto.setCantidad(new Float(1));
				gastos.add(gasto);
				inicial=inicial+5;
				evento.setGastos(gastos);	
				this.eventoServicio.agregarGasto(evento);
			}						
			System.out.println("<OTIKA>Guardando GASTOv2!!"+evento.getGastos());    	
	    	System.out.println("<OTIKA>Guardando GASTOv2!!"+evento.getIdEvento());
	    	Date date = new Date();
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");    	
	    	//evento.getGastos().get(1).setFechaIngreso(date);	    	
	    	return this.eventoServicio.obtenerEvento(evento);
		}
		catch(Exception e){
			e.printStackTrace();
		}		
		return this.eventoServicio.obtenerEvento(evento);
	}

	 @RequestMapping(value="/htmltopdf",method = RequestMethod.POST)
     public void htmlToPdf ( 
	    		HttpServletRequest request,HttpServletResponse response){	  			
		try{
			String html=request.getParameter("txt").toString();
			String total=request.getParameter("txt2").toString();
			String descuento=request.getParameter("txt3").toString();
			String totalMenosDescuento=request.getParameter("txt4").toString();
		    System.out.println("ID a buscar:"+html);
			Document document = new Document(PageSize._11X17);
	        File fileToDownloadTmp=File.createTempFile("tmp",".pdf");
	        PdfWriter.getInstance(document, new FileOutputStream(fileToDownloadTmp));	         
	        HTMLWorker htmlWorker = new HTMLWorker(document);
	        document.open();
	        String formulario="";
	        formulario=html;
	        String str = "<html><head></head><body>"+html+"<br>"+total+"<br>"+descuento+"<br>"+totalMenosDescuento+        
	          "</body></html>";
	        
	        htmlWorker.parse(new StringReader(str));
	        document.close();
	        FileInputStream inputStream = null;
	        inputStream=new FileInputStream(fileToDownloadTmp);
	        response.setContentType("text/html");
	        response.setHeader("Content-Disposition", "attachment; filename="+"AcuseVersus-.pdf"); 
	        IOUtils.copy(inputStream, response.getOutputStream());
	        response.flushBuffer();
	        System.out.println("Done");
	    	
		}
		catch(Exception e){
			e.printStackTrace();
		}		
	  }
}
