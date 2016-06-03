package mx.argal.versus.web.controller;
import java.io.IOException;
import java.util.Calendar;
import java.util.Iterator;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.util.FileCopyUtils;

import mx.argal.modelo.Evento;
import mx.argal.modelo.Factura;
import mx.argal.modelo.Gasto;
import mx.argal.modelo.ListaPrecios;
import mx.argal.servicios.EventoServicio;
import mx.argal.servicios.ListaPreciosServicio;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Controller
@RequestMapping("/file")
public class FileController {
	@Autowired
	private EventoServicio eventoServicio;	
	@Autowired
	private ListaPreciosServicio listaPreciosServicio;
	
	@RequestMapping(value="/getFile",method = RequestMethod.POST)
    @ResponseBody
    public void getFile(@ModelAttribute(value="factura") Factura factura,HttpServletRequest request,HttpServletResponse response){
		System.out.println("<OTIKA>Solicitan factura del evento:"+request.getParameter("id1"));
		int id1=Integer.parseInt(request.getParameter("id1").toString());
		String id2=request.getParameter("id2").toString();
	          
		factura.setIdFactura(id1);
		factura.setRutaFactura(id2);
		
		String ruta="c:\\logs\\facturas\\"+factura.getIdFactura()+"\\"+factura.getRutaFactura();
		File fileToDownload = null;
		InputStream inputStream = null;
		try{            
			fileToDownload = new File (ruta);
	        inputStream = new FileInputStream(fileToDownload);
	        response.setContentType("text/html");
            response.setHeader("Content-Disposition", "attachment; filename="+factura.getRutaFactura()); 
            IOUtils.copy(inputStream, response.getOutputStream());
            response.flushBuffer();
            System.out.println("Done");            
	    }catch(Exception e){            
	        e.printStackTrace();
	    }
	}

	@RequestMapping(value="/getFacturaFile",method = RequestMethod.POST)
    @ResponseBody
    public void getFacturaFile(@ModelAttribute(value="factura") Factura factura,HttpServletRequest request,HttpServletResponse response){
		System.out.println("<OTIKA>Solicitan factura del evento:"+request.getParameter("id1"));
		int id1=Integer.parseInt(request.getParameter("id1").toString());
		String id2=request.getParameter("id2").toString();
	          
		factura.setIdFactura(id1);
		factura.setRutaFactura(id2);
		Evento evento = this.eventoServicio.obtenerEventoById(id1);
		
		File fileToDownload = null;
		InputStream inputStream = null;
		Factura dataTmp=new Factura();
		if (evento.getFacturas()!=null){
			for (int i=0; i<evento.getFacturas().size();i++){
				if (evento.getFacturas().get(i).getIdFactura()==Integer.parseInt(id2)){
					dataTmp=evento.getFacturas().get(i);
				}
			}
		}
		try{            
            byte [] data = dataTmp.getArchivo();
            response.reset ();
            response.setHeader("Content-Disposition", "attachment; filename="+dataTmp.getIdFactura()+"."+dataTmp.getRutaFactura()); 
            response.addHeader ("Content-Length", "" + data.length);
            response.setContentType ("application / octet-stream; charset = UTF-8");
            OutputStream outputStream = new BufferedOutputStream (response.getOutputStream ());
            outputStream.write (data);
            outputStream.flush(	);
            outputStream.close();
	    }catch(Exception e){            
	        e.printStackTrace();
	    }
	}

	
	@RequestMapping(value="/getFile2",method = RequestMethod.POST)
    @ResponseBody
    public void getFile2(@ModelAttribute(value="factura") Factura factura,HttpServletRequest request,HttpServletResponse response){		
		System.out.println("<OTIKA>Solicitan evidencia del gasto:"+request.getParameter("id1"));
		int id1=Integer.parseInt(request.getParameter("id1").toString());
		String id2=request.getParameter("id2").toString();
	    int idGasto=5;
	    Gasto gasto = this.eventoServicio.obtenerGastoById(5);
	    System.out.println(gasto.getRutaEvidencia());
		factura.setIdFactura(id1);
		factura.setRutaFactura(id2);
		System.out.println("ARCHIVO OBTENIDO:"+gasto.getArchivo().length+"->"+gasto.getArchivo());
		String ruta="c:\\logs\\tmp\\tmp.jpg";
		File fileToDownload = null;
		InputStream inputStream = null;
		try{            
			///
            byte [] data = gasto.getArchivo();
            response.reset ();
            response.setHeader("Content-Disposition", "attachment; filename="+gasto.getIdGasto()+"."+gasto.getRutaEvidencia()); 
            response.addHeader ("Content-Length", "" + data.length);
            response.setContentType ("application / octet-stream; charset = UTF-8");
            OutputStream outputStream = new BufferedOutputStream (response.getOutputStream ());
            outputStream.write (data);
            outputStream.flush();
            outputStream.close();
	    }
		catch(Exception e){            
	        e.printStackTrace();
	    }
	}
	
	@RequestMapping(value="/getFileGasto",method = RequestMethod.POST)
    @ResponseBody
    public void getFileGasto(@ModelAttribute(value="factura") Factura factura,HttpServletRequest request,HttpServletResponse response){		
		System.out.println("<OTIKA>Solicitan evidencia del gasto:"+request.getParameter("id1"));
		int id1=Integer.parseInt(request.getParameter("id1").toString());
		String id2=request.getParameter("id2").toString();
		System.out.println("->>>"+id2);
	    int idGasto=id1;
	    Gasto gasto = this.eventoServicio.obtenerGastoById(idGasto);
	    System.out.println("->"+gasto.getRutaEvidencia());
		System.out.println("ARCHIVO OBTENIDO:"+gasto.getArchivo());		
		File fileToDownload = null;
		InputStream inputStream = null;
		try{            
            byte [] data = gasto.getArchivo();
            response.reset ();
            response.setHeader("Content-Disposition", "attachment; filename=evento:"+id2+"-"+gasto.getIdGasto()+"."+gasto.getRutaEvidencia()); 
            response.addHeader ("Content-Length", "" + data.length);
            response.setContentType ("application / octet-stream; charset = UTF-8");
            OutputStream outputStream = new BufferedOutputStream (response.getOutputStream ());
            outputStream.write (data);
            outputStream.flush();
            outputStream.close();
	    }catch(Exception e){            
	        e.printStackTrace();
	    }
	}
	
	@RequestMapping(value="/getFileListaPrecios",method = RequestMethod.POST)
    @ResponseBody
    public void getFileListaPrecios(@ModelAttribute(value="ListaPrecios") ListaPrecios listaPrecios,HttpServletRequest request,HttpServletResponse response){		
		System.out.println("<OTIKA>Solicitan evidencia del gasto:"+request.getParameter("id1"));
		int id1=Integer.parseInt(request.getParameter("id1").toString());
		listaPrecios.setIdListaPrecios(Integer.parseInt(""+id1));
		listaPrecios = this.listaPreciosServicio.obtenerListasPreciosById(listaPrecios);	    
	    System.out.println("->"+listaPrecios.getRutaLista());
		System.out.println("ARCHIVO OBTENIDO:"+listaPrecios.getArchivo());		
		File fileToDownload = null;
		InputStream inputStream = null;
		try{            
            byte [] data = listaPrecios.getArchivo();
            response.reset ();
            response.setHeader("Content-Disposition", "attachment; filename=evento"+listaPrecios.getIdListaPrecios()+"."+listaPrecios.getRutaLista()); 
            response.addHeader ("Content-Length", "" + data.length);
            response.setContentType ("application / octet-stream; charset = UTF-8");
            OutputStream outputStream = new BufferedOutputStream (response.getOutputStream ());
            outputStream.write (data);
            outputStream.flush();
            outputStream.close();
	    }catch(Exception e){            
	        e.printStackTrace();
	    }
	}
}