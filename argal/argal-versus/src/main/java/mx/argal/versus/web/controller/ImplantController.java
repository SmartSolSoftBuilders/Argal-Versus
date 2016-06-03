package mx.argal.versus.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import mx.argal.modelo.Hospital;
import mx.argal.modelo.Implant;
import mx.argal.seguridad.modelo.UsuarioSeguridad;
import mx.argal.seguridad.servicios.MttoSeguridadServicio;
import mx.argal.seguridad.util.SeguridadUtil;
import mx.argal.servicios.EventoServicio;
import mx.argal.servicios.ImplantServicio;
import mx.argal.servicios.UsuarioServicio;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller encargado de devolver la vista principal o index de la aplicación.
 * 
 * El path colocado en la anotación @RequestMappig corresponde a la cofiguración dentro
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
@RequestMapping("/implant")
public class ImplantController {
	
	public final String ROLE_ADMINISTRADOR="ROLE_ADMINISTRADOR";
	public final String ROLE_USUARIO="ROLE_USUARIO";
	public final String ROLE_CAPTURISTA="ROLE_CAPTURISTA";
	
	@Autowired
	//private UsuarioServicio usuarioServicio;
	private ImplantServicio implantServicio;	
	@Autowired
	private MttoSeguridadServicio mttoSeguridadServicio;
	
	
	@RequestMapping(value="/guardarimplant",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public boolean guardarImplant(@RequestBody Implant implant){ 	
    	System.out.println("<OTIKA>Guardando!!!"+implant.getSuperMedico());
    	String nickTmp=implant.getNombreImplant().substring(0,1)+implant.getAppImplant();
    	
    	//Create nick Implant for secure    	
    	implant.setNickImplant(nickTmp.toUpperCase()); 	
    	UsuarioSeguridad usuarioSeguridad = new UsuarioSeguridad();
    	usuarioSeguridad.setNombre(implant.getNombreImplant()+" "+implant.getAppImplant());
		usuarioSeguridad.setUsername(nickTmp.toUpperCase());
		usuarioSeguridad.setPassword(nickTmp.toUpperCase());
		System.out.println("<OTIKA>NICK GENERADO!!!"+implant.getNickImplant());
    	
    	Integer checkForExistingName = mttoSeguridadServicio.consultarUsuariosByUser(usuarioSeguridad).size();   	
    	System.out.println("EXISTE EL USUARIO:"+checkForExistingName); 	
    	if (checkForExistingName>0){
            Random rnd = new Random();
    		Integer idTmp2=rnd.nextInt();
    		implant.setNickImplant(nickTmp.toUpperCase()+idTmp2); 	
    		usuarioSeguridad.setUsername(nickTmp.toUpperCase()+idTmp2);
    		usuarioSeguridad.setPassword(nickTmp.toUpperCase()+idTmp2);	
    	}
    	if (implant.getSuperMedico())
    		usuarioSeguridad.setTipoUsuario(3);
    	else
    		usuarioSeguridad.setTipoUsuario(1);
    	boolean result=this.implantServicio.agregarImplant(implant);
    	mttoSeguridadServicio.agregarUsuarioSeguridad(usuarioSeguridad);    		
    	return result;
    	  
	}
	
	@RequestMapping(value="/actualizarimplantv2",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public boolean actualizarImplantV2(@ModelAttribute(value="implant") Implant implant, BindingResult result){    
    	System.out.println("<OTIKA>Editando!!!"+implant.getIdImplant());
    	UsuarioSeguridad usuarioSeguridad = new UsuarioSeguridad();
    	usuarioSeguridad.setUsername(implant.getNickImplant());
    	List<UsuarioSeguridad> usTmp=mttoSeguridadServicio.consultarUsuariosByUser(usuarioSeguridad);
		if (usTmp.size()>0){
			usuarioSeguridad=usTmp.get(0);
			usuarioSeguridad.setActivo(implant.getActivoImplant());
			System.out.println("ID::"+usuarioSeguridad.getId());
			if (implant.getSuperMedico()==false)
				mttoSeguridadServicio.actualizarUsuarioSeguridadDos(usuarioSeguridad);
			if (implant.getSuperMedico()==true)
				mttoSeguridadServicio.actualizarUsuarioSeguridadUno(usuarioSeguridad);
		}
		
    	return this.implantServicio.actualizarImplant(implant); 
    	
	}
	@RequestMapping(value="/eliminarimplantv2",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public boolean eliminarImplantV2(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    	System.out.println("<OTIKA>Eliminando!!!"+implant.getIdImplant());
    	System.out.println("<OTIKA>Eliminado!!!"+implant.getSuperMedico());
    	UsuarioSeguridad usuarioSeguridad = new UsuarioSeguridad();	
    	usuarioSeguridad.setUsername(implant.getNickImplant());
    	List<UsuarioSeguridad> usuarioTmp = mttoSeguridadServicio.consultarUsuariosByUser(usuarioSeguridad);
    	if (usuarioTmp.size()>0){
    		usuarioTmp.get(0).setActivo(false);
    		mttoSeguridadServicio.actualizarUsuario(usuarioTmp.get(0));
    	}
    	return this.implantServicio.eliminarImplant(implant); 
	}
	
	@RequestMapping(value="/guardarimplantv2",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public boolean guardarImplantV2(@ModelAttribute(value="implant") Implant implant, BindingResult result){ 	
    	System.out.println("<OTIKA>Guardando!!!"+implant.getSuperMedico());
    	String nickTmp=implant.getNombreImplant().substring(0,1)+implant.getAppImplant();    	
    	//Create nick Implant for secure    	
    	implant.setNickImplant(nickTmp.toUpperCase()); 	
    	UsuarioSeguridad usuarioSeguridad = new UsuarioSeguridad();
    	usuarioSeguridad.setNombre(implant.getNombreImplant()+" "+implant.getAppImplant());
		usuarioSeguridad.setUsername(nickTmp.toUpperCase());
		usuarioSeguridad.setPassword(nickTmp.toUpperCase());
		System.out.println("<OTIKA>NICK GENERADO!!!"+implant.getNickImplant());
    	
    	Integer checkForExistingName = mttoSeguridadServicio.consultarUsuariosByUser(usuarioSeguridad).size();   	
    	System.out.println("EXISTE EL USUARIO:"+checkForExistingName); 	
    	if (checkForExistingName>0){
            Random rnd = new Random();
    		Integer idTmp2=rnd.nextInt();
    		implant.setNickImplant(nickTmp.toUpperCase()+idTmp2); 	
    		usuarioSeguridad.setUsername(nickTmp.toUpperCase()+idTmp2);
    		usuarioSeguridad.setPassword(nickTmp.toUpperCase()+idTmp2);	
    	}
    	if (implant.getSuperMedico())
    		usuarioSeguridad.setTipoUsuario(3);
    	else
    		usuarioSeguridad.setTipoUsuario(1);    	
    	this.implantServicio.agregarImplant(implant);
    	mttoSeguridadServicio.agregarUsuarioSeguridad(usuarioSeguridad);    		
    	return true;    	 
	}
	
	@RequestMapping(value="/actualizarimplant",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public boolean actualizarImplant(@RequestBody Implant implant){
    	System.out.println("<OTIKA>Guardando!!!"+implant.getIdImplant());
    	System.out.println("<OTIKA>Guardando!!!"+implant.getSuperMedico());
    	System.out.println("<OTIKA>Guardando!!!"+implant.getNickImplant());
    	UsuarioSeguridad usuarioSeguridad = new UsuarioSeguridad();
    	usuarioSeguridad.setUsername(implant.getNickImplant());
    	List<UsuarioSeguridad> usTmp=mttoSeguridadServicio.consultarUsuariosByUser(usuarioSeguridad);
		if (usTmp.size()>0){
			usuarioSeguridad=usTmp.get(0);
			usuarioSeguridad.setActivo(implant.getActivoImplant());
			System.out.println("ID::"+usuarioSeguridad.getId());
			if (implant.getSuperMedico()==false)
				mttoSeguridadServicio.actualizarUsuarioSeguridadDos(usuarioSeguridad);
			if (implant.getSuperMedico()==true)
				mttoSeguridadServicio.actualizarUsuarioSeguridadUno(usuarioSeguridad);
		}
		
    	return this.implantServicio.actualizarImplant(implant); 
    	
	}
	
	@RequestMapping(value="/eliminarimplant",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public boolean eliminarImplant(@RequestBody Implant implant){
    	System.out.println("<OTIKA>Eliminando!!!"+implant.getIdImplant());
    	System.out.println("<OTIKA>Eliminado!!!"+implant.getSuperMedico());
    	UsuarioSeguridad usuarioSeguridad = new UsuarioSeguridad();	
    	usuarioSeguridad.setUsername(implant.getNickImplant());
    	List<UsuarioSeguridad> usuarioTmp = mttoSeguridadServicio.consultarUsuariosByUser(usuarioSeguridad);
    	if (usuarioTmp.size()>0){
    		usuarioTmp.get(0).setActivo(false);
    		mttoSeguridadServicio.actualizarUsuario(usuarioTmp.get(0));
    	}
    	return this.implantServicio.eliminarImplant(implant); 
	}
	
	@RequestMapping(value="/getimplants",method = RequestMethod.POST)
    @ResponseBody
    public List<Implant> obtenerImplants(){
		//System.out.println("<OTIKA>TEST:"+this.eventoServicio.obtenerEventos());
		//System.out.println("<OTIKA>TEST:"+this.eventoServicio.obtenerEventos().get(0).getImplant().getNombreImplant());
		return this.implantServicio.obtenerImplantsActivos();
	}
	
	@RequestMapping(value="/getimplantbyid",method = RequestMethod.GET)
    @ResponseBody
    public Implant obtenerImplantsById(){
		Implant implant = this.implantServicio.obtenerImplantById(100);
		System.out.println(implant.getHospitalesConPermisos().get(0).getIdHospital());
		return implant;
	}
	@RequestMapping(value="/getimplantbyidv2",method = RequestMethod.POST)
    @ResponseBody
    public Implant obtenerImplantsByIdV2(@ModelAttribute(value="implant") Implant implant, BindingResult result){
		implant = this.implantServicio.obtenerImplantById(implant.getIdImplant());
		return implant;
	}
	 
	@RequestMapping(value="/agregarpermisohospimp",method = RequestMethod.POST)  
    @ResponseBody 
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public boolean agregarPermisoHospImplant(@RequestBody Implant implant){
    	System.out.println("<OTIKA>Guardando!!!"+implant.getIdImplant());
    	System.out.println("<OTIKA>Guardando!!!"+implant.getHospitalesConPermisos().get(0).getIdHospital());
    	return this.implantServicio.agregarPermisoHospImplant(implant); 
	}
	
	@RequestMapping(value="/agregarpermisohospimpV2",method = RequestMethod.POST)  
    @ResponseBody 
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public boolean agregarPermisoHospImplantV2(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    	System.out.println("<OTIKA>Guardando!!!"+implant.getIdImplant());
    	System.out.println("<OTIKA>Guardando!!!"+implant.getId());
    	List<Hospital> hosps= new ArrayList<Hospital>();
    	Hospital hosp = new Hospital();
    	hosp.setIdHospital(Integer.parseInt(""+implant.getId()));
    	hosps.add(hosp);
    	implant.setHospitalesConPermisos(hosps);
    	return this.implantServicio.agregarPermisoHospImplant(implant); 
	}
	
	
	@RequestMapping(value="/eliminarpermisohospimpv2",method = RequestMethod.POST)  
    @ResponseBody 
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public boolean eliminarPermisoHospImplantV2(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    	System.out.println("<OTIKA>Eliminando!!!"+implant.getIdImplant());
    	System.out.println("<OTIKA>Eliminando!!!"+implant.getId());
    	List<Hospital> hosps= new ArrayList<Hospital>();
    	Hospital hosp = new Hospital();
    	hosp.setIdHospital(Integer.parseInt(""+implant.getId()));
    	hosps.add(hosp);
    	implant.setHospitalesConPermisos(hosps);
    	System.out.println("<OTIKA>Eliminando!!!"+implant.getHospitalesConPermisos().get(0).getIdHospital());
    	return this.implantServicio.eliminarPermisoHospImplant(implant); 
	}
	
	@RequestMapping(value="/eliminarpermisohospimp",method = RequestMethod.POST)  
    @ResponseBody 
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public boolean eliminarPermisoHospImplant(@RequestBody Implant implant){
    	System.out.println("<OTIKA>Eliminando!!!"+implant.getIdImplant());
    	System.out.println("<OTIKA>Eliminando!!!"+implant.getHospitalesConPermisos().get(0).getIdHospital());
    	return this.implantServicio.eliminarPermisoHospImplant(implant); 
	}
}
