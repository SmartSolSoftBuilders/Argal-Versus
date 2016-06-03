package mx.argal.versus.web.controller;

import java.util.ArrayList;


import java.util.List;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import mx.argal.modelo.Cliente;
import mx.argal.modelo.Implant;
import mx.argal.seguridad.modelo.UsuarioSeguridad;
import mx.argal.seguridad.util.SeguridadUtil;
import mx.argal.servicios.ImplantServicio;
import mx.argal.servicios.ClienteServicio;

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
@RequestMapping("/cliente")
public class ClienteController {
	
	public final String ROLE_ADMINISTRADOR="ROLE_ADMINISTRADOR";
	public final String ROLE_USUARIO="ROLE_USUARIO";
	public final String ROLE_CAPTURISTA="ROLE_CAPTURISTA";
	
	@Autowired
	//private UsuarioServicio usuarioServicio;
	private ClienteServicio clienteServicio;
	
	
	@RequestMapping(value="/guardarclientev2",method = RequestMethod.POST)
    @ResponseBody
    public boolean guardarClienteV2(@ModelAttribute(value="Cliente") Cliente cliente){
    	System.out.println("<OTIKA>Guardando!!!"+cliente.getNombreCliente());    	
    	return this.clienteServicio.agregarCliente(cliente); 
	}
	
	@RequestMapping(value="/eliminarclientev2",method = RequestMethod.POST)
    @ResponseBody
    public boolean eliminarClienteV2(@ModelAttribute(value="Cliente") Cliente cliente){
    	System.out.println("<OTIKA>Eliminando!!!"+cliente.getIdCliente());    	
    	return this.clienteServicio.eliminarCliente(cliente); 
	}
	
	@RequestMapping(value="/actualizarclientev2",method = RequestMethod.POST)
    @ResponseBody
    public boolean actualizarClienteV2(@ModelAttribute(value="Cliente") Cliente cliente){
    	System.out.println("<OTIKA>Guardando!!!"+cliente.getIdCliente());
    	return this.clienteServicio.actualizarCliente(cliente); 
	}
	
	@RequestMapping(value="/guardarcliente",method = RequestMethod.POST)
    @ResponseBody
    //public boolean guardarImplant(@ModelAttribute(value="implant") Implant implant, BindingResult result){
    public boolean guardarCliente(@RequestBody Cliente cliente){
    	System.out.println("<OTIKA>Guardando!!!"+cliente.getNombreCliente());    	
    	return this.clienteServicio.agregarCliente(cliente); 
	} 
	
	@RequestMapping(value="/getclientebyidv2",method = RequestMethod.POST)
    @ResponseBody
    public Cliente obtenerClienteByIdV2(@ModelAttribute(value="Cliente") Cliente cliente){    	
		return this.clienteServicio.obtenerClienteByIdCliente(cliente);
	}
	
	@RequestMapping(value="/getclientes",method = RequestMethod.POST)
    @ResponseBody
    public List<Cliente> obtenerClientes(){    	
		return this.clienteServicio.obtenerClientesActivos();
	}
	
	@RequestMapping(value="/getclientesv2",method = RequestMethod.POST)
    @ResponseBody
    public List<Cliente> obtenerClientesV2(){
		
		return this.clienteServicio.obtenerClientesActivos();
	}
	
	@RequestMapping(value="/actualizarcliente",method = RequestMethod.POST)
    @ResponseBody
    public boolean actualizarCliente(@RequestBody Cliente cliente){    	
    	System.out.println("<OTIKA>Guardando!!!"+cliente.getIdCliente());
    	return this.clienteServicio.actualizarCliente(cliente); 
	}
	
	@RequestMapping(value="/eliminarcliente",method = RequestMethod.POST)
    @ResponseBody
    public boolean eliminarCliente(@RequestBody Cliente cliente){
    	System.out.println("<OTIKA>Eliminando!!!"+cliente.getIdCliente());    	
    	return this.clienteServicio.eliminarCliente(cliente); 
	}

}
