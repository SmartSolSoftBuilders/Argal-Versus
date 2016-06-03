package mx.argal.versus.web.controller;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.Iterator;

import org.apache.ibatis.type.ByteTypeHandler;

import javax.servlet.http.HttpServletRequest;

import mx.argal.modelo.Especialidad;
import mx.argal.modelo.MedicoTratante;
import mx.argal.seguridad.modelo.UsuarioSeguridad;
import mx.argal.seguridad.util.SeguridadUtil;
import mx.argal.servicios.EspecialidadServicio;

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
@RequestMapping("/especialidad")
public class EspecialidadController {
	
	public final String ROLE_ADMINISTRADOR="ROLE_ADMINISTRADOR";
	public final String ROLE_IMPLANT="ROLE_IMPLANT";
	public final String ROLE_CLIENTE="ROLE_CLIENTE";
	
	@Autowired
	private EspecialidadServicio especialidadServicio;	
	
	
	@RequestMapping(value="/obtenerespecialidades",method = RequestMethod.POST)
    @ResponseBody
    public List<Especialidad> obtenerEspecialidades(){
    	System.out.println("<OTIKA>Obteniendo especialidades!!!");    	    	
    	return this.especialidadServicio.obtenerEspecialidades(); 
	}
	
				
}
