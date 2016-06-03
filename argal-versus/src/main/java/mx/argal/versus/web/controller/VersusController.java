package mx.argal.versus.web.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;
import java.util.Iterator;

import org.apache.ibatis.type.ByteTypeHandler;

import javax.servlet.http.HttpServletRequest;

import mx.argal.modelo.Bitacora;
import mx.argal.modelo.Cliente;
import mx.argal.modelo.Especialidad;
import mx.argal.modelo.Evento;
import mx.argal.modelo.Factura;
import mx.argal.modelo.Gasto;
import mx.argal.modelo.Hospital;
import mx.argal.modelo.Icd;
import mx.argal.modelo.Cpt;
import mx.argal.modelo.Implant;
import mx.argal.modelo.ListaPrecios;
import mx.argal.modelo.MedicoTratante;
import mx.argal.seguridad.modelo.UsuarioSeguridad;
import mx.argal.seguridad.util.SeguridadUtil;
import mx.argal.servicios.ClienteServicio;
import mx.argal.servicios.EventoServicio;
import mx.argal.servicios.EventoServicio;
import mx.argal.servicios.ImplantServicio;
import mx.argal.servicios.ListaPreciosServicio;
import mx.argal.servicios.VersusServicio;

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
@RequestMapping("/versus")
public class VersusController {
	
	public final String ROLE_ADMINISTRADOR="ROLE_ADMINISTRADOR";
	public final String ROLE_IMPLANT="ROLE_IMPLANT";
	public final String ROLE_CLIENTE="ROLE_CLIENTE";
	
	@Autowired
	private EventoServicio eventoServicio;	
	
	@Autowired
	private VersusServicio versusServicio;
	
	@Autowired
	private ListaPreciosServicio listaPreciosServicio;
	
	@Autowired
	private ClienteServicio clienteServicio;
	
			
	@RequestMapping(value="/doversus",method = RequestMethod.POST)
    @ResponseBody
    public List doVersus(@RequestParam (value = "idGasto") String idGasto,
    			@RequestParam (value = "idHospital") String idHospital,
    			@RequestParam (value = "idCliente") String idCliente,
    			@RequestParam (value = "jubilado") boolean jubilado
    		){
		List renglones = new ArrayList();
		try{			
			if (Integer.parseInt(idCliente)!=11 && Integer.parseInt(idCliente)!=22 ){
				jubilado=false;
			}
			System.out.println("Obteniendo el gasto para versus:"+idGasto);
			System.out.println("Obteniendo el gasto para versus:"+idHospital);
			System.out.println("Obteniendo el gasto para versus:"+idCliente);			
			System.out.println("Obteniendo el gasto para versus:"+jubilado);
			Gasto gasto = this.eventoServicio.obtenerGastoById(Integer.parseInt(idGasto));
			ListaPrecios listaPrecios = new ListaPrecios();
			Hospital hospital =  new Hospital();
			hospital.setIdHospital(Integer.parseInt(idHospital));
			Cliente cliente = new Cliente();
			cliente.setIdCliente(Integer.parseInt(idCliente));
			listaPrecios.setHospital(hospital);
			listaPrecios.setCliente(cliente);
			listaPrecios.setJubilados(jubilado);
			String idListaPrecios = ""+this.listaPreciosServicio.obtenerListasPreciosByIdHospIdCliente(listaPrecios);
			System.out.println("LP:"+idListaPrecios);
			renglones = this.versusServicio.doVersus(gasto,idListaPrecios);
			System.out.println("--------------RENGLONES-----------------");
			//Desactivar para ver los renglones
			/*for (int i=0;i<renglones.size();i++){
				System.out.println(renglones.get(i));
			}*/
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return renglones;
	}		
}
