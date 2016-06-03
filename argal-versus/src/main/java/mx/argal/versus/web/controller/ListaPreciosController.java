package mx.argal.versus.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;



import java.util.Date;
import java.util.List;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import mx.argal.modelo.Cliente;
import mx.argal.modelo.Evento;
import mx.argal.modelo.Gasto;
import mx.argal.modelo.Implant;
import mx.argal.modelo.ListaPrecios;
import mx.argal.seguridad.modelo.UsuarioSeguridad;
import mx.argal.seguridad.util.SeguridadUtil;
import mx.argal.servicios.ImplantServicio;
import mx.argal.servicios.ListaPreciosServicio;

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
@RequestMapping("/listaprecios")
public class ListaPreciosController {
	
	public final String ROLE_ADMINISTRADOR="ROLE_ADMINISTRADOR";
	public final String ROLE_USUARIO="ROLE_USUARIO";
	public final String ROLE_CAPTURISTA="ROLE_CAPTURISTA";
	
	@Autowired
	//private UsuarioServicio usuarioServicio;
	private ListaPreciosServicio listaPreciosServicio;
	
	@RequestMapping(value="/getlistas",method = RequestMethod.POST)
    @ResponseBody
    public List<ListaPrecios> obtenerListas(){    	
		return this.listaPreciosServicio.obtenerListasPrecios();
	}
	
	@RequestMapping(value="/getlistaspreciosparams",method = RequestMethod.POST)
    @ResponseBody    
	public List<ListaPrecios> obtenerClientes(@ModelAttribute(value="ListaPrecios") ListaPrecios listaPrecios,HttpServletRequest request){    	
		try{
			System.out.println("lista precios:");
			List<ListaPrecios> lista=new ArrayList<ListaPrecios>();
			lista=this.listaPreciosServicio.obtenerListasPreciosByParams(listaPrecios);
			System.out.println(lista.size());			
			return lista;
		}
		catch(Exception e){
			System.out.println("error!"+e.getMessage());
			e.printStackTrace();
		}
		return new ArrayList<ListaPrecios>();
	}
	
	@RequestMapping(value="/eliminarlistapreciosv2",method = RequestMethod.POST)
    @ResponseBody
    //public ListaPrecios guardarListaPreciosV2(@RequestBody ListaPrecios listaPrecios,HttpServletRequest request){
    public ListaPrecios eliminarListaPreciosV2(@ModelAttribute(value="ListaPrecios") ListaPrecios listaPrecios, BindingResult result){    		
		System.out.println("<OTIKA>Eliminando ListaPrecios!!"+listaPrecios.getIdListaPrecios());    	
		//System.out.println("<OTIKA>Guardando ListaPrecios!!"+listaPrecios.getJubilados());    	
		this.listaPreciosServicio.eliminarListaPrecios(listaPrecios);
		System.out.println("lista precios:"+listaPrecios.getIdListaPrecios());
		System.out.println("lista precios:"+listaPrecios.getId());
		return listaPrecios;
	}
	
	@RequestMapping(value="/guardarlistapreciosv2",method = RequestMethod.POST)
    @ResponseBody
    //public ListaPrecios guardarListaPreciosV2(@RequestBody ListaPrecios listaPrecios,HttpServletRequest request){
    public ListaPrecios guardarListaPreciosV2(@ModelAttribute(value="ListaPrecios") ListaPrecios listaPrecios, BindingResult result){    		
		System.out.println("<OTIKA>Guardando ListaPrecios!!"+listaPrecios.getHospital().getIdHospital());    	
		//System.out.println("<OTIKA>Guardando ListaPrecios!!"+listaPrecios.getJubilados());    	
		this.listaPreciosServicio.agregarListaPrecios(listaPrecios);
		System.out.println("lista precios:"+listaPrecios.getIdListaPrecios());
		System.out.println("lista precios:"+listaPrecios.getId());
		return listaPrecios;
	}
	
}
