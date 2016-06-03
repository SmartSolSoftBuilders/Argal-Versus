package mx.argal.versus.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Iterator;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mx.argal.modelo.Factura;
import mx.argal.modelo.Implant;
import mx.argal.seguridad.modelo.UsuarioSeguridad;
import mx.argal.seguridad.util.SeguridadUtil;
import mx.argal.servicios.EventoServicio;
import mx.argal.servicios.ImplantServicio;
import mx.argal.servicios.ReportesServicio;
import mx.argal.servicios.UsuarioServicio;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
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
@RequestMapping("/reportes")
public class ReportesController {
	
	public final String ROLE_ADMINISTRADOR="ROLE_ADMINISTRADOR";
	public final String ROLE_USUARIO="ROLE_USUARIO";
	public final String ROLE_CAPTURISTA="ROLE_CAPTURISTA";
	
	@Autowired
	//private UsuarioServicio usuarioServicio;
	private ReportesServicio reportesServicio;
	
	
	@RequestMapping(value="/obtenerlayoutbanco",method = RequestMethod.GET)
    @ResponseBody
    public  void getFile(HttpSession session,HttpServletResponse response) {
		File fileToDownload = null;
        InputStream inputStream = null;
		try{            
			fileToDownload = this.reportesServicio.generarLayoutBanco(1,"","","");
            inputStream = new FileInputStream(fileToDownload);
            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename="+"layoutBanco02032014-14032014.xlsx"); 
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
        }catch(Exception e){            
            e.printStackTrace();
        }
 
    }
	
	@RequestMapping(value="/getFile",method = RequestMethod.POST)
    @ResponseBody
    public void getFile(HttpServletRequest request,HttpServletResponse response){
		int id1=Integer.parseInt(request.getParameter("id1").toString());
		String id2=request.getParameter("id2").toString();
		String fechaInicio=      request.getParameter("id3").toString();
	    String fechaFin=      request.getParameter("id4").toString();
		System.out.println("<OTIKA>Solicitan reporte del evento:"+id1+"->"+id2+"->"+fechaInicio+"->"+fechaFin);
		if (id1==1){
			File fileToDownload = null;
			File fileToDownloadErr = null;
			InputStream inputStream = null;
			try{            
				fileToDownload = this.reportesServicio.generarLayoutBanco(id1, id2, fechaInicio, fechaFin);
				inputStream = new FileInputStream(fileToDownload);
				response.setContentType("application/force-download");
				response.setHeader("Content-Disposition", "attachment; filename="+"layoutBanco"+fechaInicio+"-"+fechaFin+".xlsx"); 
				IOUtils.copy(inputStream, response.getOutputStream());
				response.flushBuffer();
				}
			catch(Exception e){
				try{
					fileToDownload = new File("layout-tmp", ".xlsx");
					System.out.println("ERROR:"+ e.getMessage());
					fileToDownload = new File("error-tmp", ".xlsx");
					response.setContentType("text/plain");
					response.setHeader("Content-Disposition","attachment;filename=myFileError.txt");
					ServletOutputStream out = response.getOutputStream();
					out.println(e.getMessage());
					out.flush();
					out.close();					
					e.printStackTrace();
				}
				catch(Exception e2){
					e2.printStackTrace();
				}
			}
		}else{
				File fileToDownload = null;
				InputStream inputStream = null;
				try{            
					fileToDownload = this.reportesServicio.generarLayoutAseguradora(id1, id2, fechaInicio, fechaFin);
					inputStream = new FileInputStream(fileToDownload);
					response.setContentType("application/force-download");
					response.setHeader("Content-Disposition", "attachment; filename="+"layoutAseguradora"+fechaInicio+"-"+fechaFin+".xls"); 
					IOUtils.copy(inputStream, response.getOutputStream());
					response.flushBuffer();
				}catch(Exception e){            
				e.printStackTrace();
			}
		}
 
	}

	@RequestMapping(value="/getFileDiario",method = RequestMethod.POST)
    @ResponseBody
    public void getFileDiario(HttpServletRequest request,HttpServletResponse response){
		int id1=Integer.parseInt(request.getParameter("id1").toString());
		String id2=request.getParameter("id2").toString();
		String fechaInicio=request.getParameter("id3").toString();
	    String fechaFin=request.getParameter("id4").toString();
	    String nombreLayout="layoutMensualBanco"+fechaInicio+"-"+fechaFin;	    
		if (id2.equals("1")){
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		    Date date = new Date();
			nombreLayout="layoutDiarioBanco-"+dateFormat.format(date);
		}
		System.out.println("<OTIKA>Solicitan reporte del evento:"+id1+"->"+id2+"->"+fechaInicio+"->"+fechaFin);		
		if (id1==1){
			File fileToDownload = null;			
			InputStream inputStream = null;
			try{            
				fileToDownload = this.reportesServicio.generarLayoutDiarioBanco(id1, id2, fechaInicio, fechaFin);
				inputStream = new FileInputStream(fileToDownload);
				response.setContentType("application/force-download");
				response.setHeader("Content-Disposition", "attachment; filename="+nombreLayout+".xlsx"); 
				IOUtils.copy(inputStream, response.getOutputStream());
				response.flushBuffer();
				}
			catch(Exception e){
				try{
					fileToDownload = new File("layout-tmp", ".xlsx");
					System.out.println("ERROR:"+ e.getMessage());
					fileToDownload = new File("error-tmp", ".xlsx");
					response.setContentType("text/plain");
					response.setHeader("Content-Disposition","attachment;filename=myFileError.txt");
					ServletOutputStream out = response.getOutputStream();
					out.println(e.getMessage());
					out.flush();
					out.close();					
					e.printStackTrace();
				}
				catch(Exception e2){
					e2.printStackTrace();
				}
			}
		}else{
				File fileToDownload = null;
				InputStream inputStream = null;
				try{            
					fileToDownload = this.reportesServicio.generarLayoutAseguradora(id1, id2, fechaInicio, fechaFin);
					inputStream = new FileInputStream(fileToDownload);
					response.setContentType("application/force-download");
					response.setHeader("Content-Disposition", "attachment; filename="+"layoutAseguradora"+fechaInicio+"-"+fechaFin+".xls"); 
					IOUtils.copy(inputStream, response.getOutputStream());
					response.flushBuffer();
				}catch(Exception e){            
				e.printStackTrace();
			}
		}
 
	}
		
	
}
