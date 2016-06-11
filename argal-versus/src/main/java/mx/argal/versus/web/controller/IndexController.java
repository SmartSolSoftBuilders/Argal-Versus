package mx.argal.versus.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import mx.argal.modelo.Evento;
import mx.argal.modelo.Implant;
import mx.argal.seguridad.modelo.UsuarioSeguridad;
import mx.argal.seguridad.servicios.MttoSeguridadServicio;
import mx.argal.seguridad.util.SeguridadUtil;
import mx.argal.servicios.ImplantServicio;
import mx.argal.servicios.UsuarioServicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/index")
public class IndexController {
	
	public final String ROLE_ADMINISTRADOR="ROLE_ADMINISTRADOR";
	public final String ROLE_IMPLANT="ROLE_IMPLANT";
	public final String ROLE_CLIENTE="ROLE_CLIENTE";
	
	@Autowired
	//private UsuarioServicio usuarioServicio;
	private ImplantServicio implantServicio;
	
	@Autowired
	private MttoSeguridadServicio mttoSeguridadServicio;

	//Test commit 
	@RequestMapping(method = RequestMethod.GET)
    public ModelAndView mostrarIndex(HttpServletRequest request){	    	    
    	UsuarioSeguridad usuario = SeguridadUtil.getUsuarioActual();    	
    	String rol="";
    	System.out.println("<OTIKA>RFCenLogin:"+usuario.getNombre());
    	usuario.setNombre(usuario.getNombre().toUpperCase());
    	usuario.setPassword(usuario.getPassword().toUpperCase());
    	System.out.println("<OTIKA>Login!"+((SecurityContextImpl)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")).getAuthentication().getPrincipal().toString());    	
    	if ( ((SecurityContextImpl)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")).getAuthentication().getAuthorities().size()>0){
    		Iterator it = ((SecurityContextImpl)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")).getAuthentication().getAuthorities().iterator();
    		while (it.hasNext()){
    			rol=it.next().toString();
    			request.getSession().setAttribute("rolUser",rol);
    			request.getSession().setAttribute("userSession",usuario.getUsername());    			
    			request.getSession().setAttribute("msj","Bienvenido "+usuario.getNombre());
    			break;
    		}    		
    	}     	
    	if ( rol.equals(ROLE_ADMINISTRADOR)) {    		
    		Implant implant=new Implant();
    		implant.setNickImplant(usuario.getUsername());
    		implant=this.implantServicio.obtenerImplantByNick(implant);
    		System.out.println("ID DEL IMPLANT:"+implant.getIdImplant());
			request.getSession().setAttribute("idImplantLogin",implant.getIdImplant());    			
			request.getSession().setAttribute("tipoUsuarioLogin",3);
    		return new ModelAndView("v2.0/versus/versus", "usuario", usuario);
    	}
    	if ( rol.equals(ROLE_IMPLANT)) {
    		Implant implant=new Implant();
    		implant.setNickImplant(usuario.getUsername());
    		implant=this.implantServicio.obtenerImplantByNick(implant);
    		System.out.println("ID DEL IMPLANT:"+implant.getIdImplant());
			request.getSession().setAttribute("idImplantLogin",implant.getIdImplant());
			request.getSession().setAttribute("tipoUsuarioLogin",2);
			return new ModelAndView("v2.0/eventos/eventos", "usuario", usuario);
    		//return new ModelAndView("index/index", "usuario", usuario);
    	}
    	if ( rol.equals(ROLE_CLIENTE)) {        	
    		request.getSession().setAttribute("tipoUsuarioLogin",1);   	    
    		return new ModelAndView("index/index", "usuario", usuario);
    	}    	    	    	   
    	return new ModelAndView("v2.0/eventos/eventos", "usuario", usuario);
    }
	
	@RequestMapping(value="/getpage",method = RequestMethod.GET)  
    public ModelAndView loadPage(@RequestParam String page,HttpServletRequest request){
		System.out.println(page);
		UsuarioSeguridad usuario = SeguridadUtil.getUsuarioActual();    	
    	String rol="";
    	System.out.println("<OTIKA>RFCenLogin:"+usuario.getNombre());
    	usuario.setNombre(usuario.getNombre().toUpperCase());
    	usuario.setPassword(usuario.getPassword().toUpperCase());
    	System.out.println("<OTIKA>Login!"+((SecurityContextImpl)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")).getAuthentication().getPrincipal().toString());    	
    	if ( ((SecurityContextImpl)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")).getAuthentication().getAuthorities().size()>0){
    		Iterator it = ((SecurityContextImpl)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT")).getAuthentication().getAuthorities().iterator();
    		while (it.hasNext()){
    			rol=it.next().toString();
    			request.getSession().setAttribute("rolUser",rol);
    			request.getSession().setAttribute("userSession",usuario.getUsername());    			
    			request.getSession().setAttribute("msj","Bienvenido "+usuario.getNombre());
    			break;
    		}    		
    	}
    	if (rol.equals(ROLE_IMPLANT)){
    		if (page.equals("1")){
				return new ModelAndView("v2.0/eventos/alta_evento");
			}
    		if (page.equals("8")){
				System.out.println("new");
				return new ModelAndView("v2.0/reportes/layout");
			}
    	}
    	if (rol.equals(ROLE_ADMINISTRADOR)){
			if (page.equals("1")){
				return new ModelAndView("v2.0/eventos/alta_evento");
			}
			if (page.equals("2")){
				return new ModelAndView("v2.0/catalogos/implants");
			}
			if (page.equals("3")){
				return new ModelAndView("v2.0/catalogos/hospitales");
			}
			if (page.equals("4")){
				return new ModelAndView("v2.0/catalogos/permisos");
			}
			if (page.equals("5")){			
				return new ModelAndView("v2.0/catalogos/medicos_tratantes");
			}
			if (page.equals("6")){
				System.out.println("new");
				return new ModelAndView("v2.0/catalogos/clientes");
			}
			if (page.equals("7")){
				System.out.println("new");
				return new ModelAndView("v2.0/catalogos/precios");
			}
			if (page.equals("8")){
				System.out.println("new");
				return new ModelAndView("v2.0/reportes/layout");
			}
    	}
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/obteneruserseguridad",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public UsuarioSeguridad obtenerUsuarioSeguridad(HttpServletRequest request, @RequestBody UsuarioSeguridad usuario){		
    	System.out.println("<OTIKA>Obteniendo UsuarioSeguridad!!!"+request.getSession().getAttribute("userSession"));
    	if (request.getSession().getAttribute("userSession")!=null)
    		usuario.setUsername(request.getSession().getAttribute("userSession").toString());
    	List<UsuarioSeguridad> userTmp = new ArrayList<UsuarioSeguridad>();
    	userTmp=this.mttoSeguridadServicio.consultarUsuariosByUser(usuario);
    	System.out.println(userTmp.get(0));
    	System.out.println(request.getSession().getAttribute("rolUser"));
    	if (userTmp!=null){
    		if (request.getSession().getAttribute("rolUser").equals(ROLE_ADMINISTRADOR))
    			userTmp.get(0).setTipoUsuario(3);
    		if (request.getSession().getAttribute("rolUser").equals(ROLE_CLIENTE))
    			userTmp.get(0).setTipoUsuario(2);
    		if (request.getSession().getAttribute("rolUser").equals(ROLE_IMPLANT))
    			userTmp.get(0).setTipoUsuario(1);
    		return userTmp.get(0);
    	}
    	else
    		return null;
	}
	
	@RequestMapping(value="/cambiarpassword",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public UsuarioSeguridad cambiarPasswordSeguridad(HttpServletRequest request, @RequestBody UsuarioSeguridad usuario){		
    	System.out.println("<OTIKA>Obteniendo UsuarioSeguridad cambiar psw!!!"+request.getSession().getAttribute("userSession"));
    	System.out.println(usuario.getPassword());    	
    	if (request.getSession().getAttribute("userSession")!=null)    	
    		usuario.setUsername(request.getSession().getAttribute("userSession").toString());
    	List<UsuarioSeguridad> userTmp = new ArrayList<UsuarioSeguridad>();
    	userTmp=this.mttoSeguridadServicio.consultarUsuariosByUser(usuario);
    	if (userTmp.size()>0){    		
    		usuario.setPassword(usuario.getPassword().toUpperCase());
    		mttoSeguridadServicio.actualizarPassword(usuario);
    	}
    	return null;
	}

}
