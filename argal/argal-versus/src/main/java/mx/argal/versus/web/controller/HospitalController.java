package mx.argal.versus.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.argal.modelo.Hospital;
import mx.argal.modelo.Implant;
import mx.argal.seguridad.modelo.UsuarioSeguridad;
import mx.argal.seguridad.util.SeguridadUtil;
import mx.argal.servicios.HospitalServicio;
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
@RequestMapping("/hospital")
public class HospitalController {
	
	public final String ROLE_ADMINISTRADOR="ROLE_ADMINISTRADOR";
	public final String ROLE_USUARIO="ROLE_USUARIO";
	public final String ROLE_CAPTURISTA="ROLE_CAPTURISTA";
	
	@Autowired
	//private UsuarioServicio usuarioServicio;
	private HospitalServicio hospitalServicio;
	
	@RequestMapping(value="/guardarhospital",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public boolean guardarImplant(@RequestBody Hospital hospital){
    	System.out.println("<OTIKA>Guardando!!!"+hospital.getNombreHospital());
    	System.out.println("<OTIKA>Guardando!!!"+hospital.getDireccionHospital());
    	return this.hospitalServicio.agregarHospital(hospital); 
	}
	
	@RequestMapping(value="/guardarhospitalv2",method = RequestMethod.POST)
    @ResponseBody
    public boolean guardarImplantV2(@ModelAttribute(value="Hospital") Hospital hospital, BindingResult result){
    	System.out.println("<OTIKA>Guardando!!!"+hospital.getNombreHospital());
    	System.out.println("<OTIKA>Guardando!!!"+hospital.getDireccionHospital());
    	return this.hospitalServicio.agregarHospital(hospital); 
	}
	
	@RequestMapping(value="/gethospitalesbyimplant",method = RequestMethod.POST)
    @ResponseBody
    public List<Hospital> obtenerHospitalesByImplant(@RequestBody Implant implant){    	
		return this.hospitalServicio.obtenerHospitalesByImplant(implant);
	}
	
	@RequestMapping(value="/gethospitalbyidv2",method = RequestMethod.POST)
    @ResponseBody
    public Hospital obtenerHospitalById(@ModelAttribute(value="Hospital") Hospital hospital, BindingResult result){    	
		return this.hospitalServicio.obtenerHospitalById(hospital);		
	}
	
	@RequestMapping(value="/gethospitalessinpermisoimplant",method = RequestMethod.POST)
    @ResponseBody
    public List<Hospital> obtenerHospitalesSinPermisoByImplant(@RequestBody Implant implant){    	
		return this.hospitalServicio.obtenerHospitalesSinPermisoByImplant(implant);
	}
	
	@RequestMapping(value="/gethospitalessinpermisoimplantv2",method = RequestMethod.POST)
    @ResponseBody
    public List<Hospital> obtenerHospitalesSinPermisoByImplantV2(@ModelAttribute(value="implant") Implant implant, BindingResult result){    	
		return this.hospitalServicio.obtenerHospitalesSinPermisoByImplant(implant);
	}
	
	@RequestMapping(value="/deletepermisoimplant",method = RequestMethod.POST)
    @ResponseBody
    public boolean deletepermisoimplant(@RequestBody Implant implant){    	
		//return this.hospitalServicio.borrarPermisoImplant(implant);
		return false;
		
	}
	
	@RequestMapping(value="/gethospitales",method = RequestMethod.POST)
    @ResponseBody
    public List<Hospital> obtenerHospitales(HttpSession session,HttpServletResponse response){
		System.out.println("Obteniendo la lista de hospitales!");
		int tipoUsuario=Integer.parseInt(""+session.getAttribute("tipoUsuarioLogin"));
		System.out.println(tipoUsuario);
		if (tipoUsuario==3){
			return this.hospitalServicio.obtenerHospitalesActivos();
		}
		else{
			int idImpl=Integer.parseInt(""+session.getAttribute("idImplantLogin"));
			Implant implant= new Implant();
			implant.setIdImplant(idImpl);
			return this.hospitalServicio.obtenerHospitalesByImplant(implant);
		}
	}
	
	@RequestMapping(value="/actualizarhospital",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public boolean actualizarHospital(@RequestBody Hospital hospital){    	
    	System.out.println("<OTIKA>Guardando!!!"+hospital.getIdHospital());
    	return this.hospitalServicio.actualizarHospital(hospital); 
	}
	
	@RequestMapping(value="/eliminarhospital",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public boolean eliminarImplant(@RequestBody Hospital hospital){
		System.out.println("<OTIKA>Eliminando!!!"+hospital.getIdHospital());
    	return this.hospitalServicio.eliminarHospital(hospital); 
	}
	
	@RequestMapping(value="/actualizarhospitalv2",method = RequestMethod.POST)
    @ResponseBody
    public boolean actualizarHospitalV2(@ModelAttribute(value="Hospital") Hospital hospital, BindingResult result){
    	System.out.println("<OTIKA>Guardando!!!"+hospital.getIdHospital());
    	return this.hospitalServicio.actualizarHospital(hospital); 
	}
	
	@RequestMapping(value="/eliminarhospitalv2",method = RequestMethod.POST)
    @ResponseBody
    public boolean eliminarImplantV2(@ModelAttribute(value="Hospital") Hospital hospital, BindingResult result){
		System.out.println("<OTIKA>Eliminando!!!"+hospital.getIdHospital());
    	return this.hospitalServicio.eliminarHospital(hospital); 
	}
}
