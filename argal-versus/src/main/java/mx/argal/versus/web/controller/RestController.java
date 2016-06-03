package mx.argal.versus.web.controller;
import java.io.IOException;
import java.sql.Blob;
import java.util.Calendar;
import java.util.Iterator;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.postgresql.largeobject.LargeObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
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
import mx.argal.web.util.UploadFile;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/cont")
public class RestController {
	@Autowired
	private EventoServicio eventoServicio;
	@Autowired
	private ListaPreciosServicio listaPreciosServicio;
	
	UploadFile ufile;	
	public RestController(){
		System.out.println("init RestController");
		ufile = new UploadFile();
	}
	
@RequestMapping(value = "/get/{value}", method = RequestMethod.GET)
public void get(HttpServletResponse response,@PathVariable String value){
	try {
	response.setContentType(ufile.type);
	response.setContentLength(ufile.length);
	FileCopyUtils.copy(ufile.bytes, response.getOutputStream());
	} catch (IOException e) {
		System.out.println(e.getMessage());
	}
}

@RequestMapping(value = "/upload", method = RequestMethod.POST)
public @ResponseBody String upload(MultipartHttpServletRequest request, HttpServletResponse response) { 
	Iterator<String> itr =request.getFileNames();
	MultipartFile mpf = request.getFile(itr.next());
	System.out.println(mpf.getOriginalFilename() +" uploaded!");	
	System.out.println(request.getParameter("idEventoHidden"));
	String subCarpeta= request.getParameter("idEventoHidden");

	try {
		//Verificar si existe el folder
		File dirFolder=new File("c:\\logs\\facturas\\"+subCarpeta); 
		//Creacion de Directorios y Archivos.
		if(dirFolder.exists()) {
			System.out.println("ya existe el directorio!!");
		}
		else{
			dirFolder.mkdir();
			System.out.println("No existia el directorio, Pero ahora a sido creado!!");
		}
		ufile.idEvento = mpf.getBytes().length;
		ufile.length = mpf.getBytes().length;
		ufile.bytes= mpf.getBytes();
		ufile.type = mpf.getContentType();
		ufile.name = mpf.getOriginalFilename();		
	//Saving file
		String content = "This is the content to write into file";		 		
		String filename= "c:\\logs\\facturas\\"+subCarpeta+"\\"+ ufile.name;
		String s = "Java Code Geeks - Java Examples";
		File file = new File(filename);		
		FileOutputStream fos = null;
		fos = new FileOutputStream(file);
		fos.write(ufile.bytes);		
		fos.close();
	System.out.println("Done"+ufile.length);
	}
	catch (IOException e) {
		e.printStackTrace();
	}
		
	/*try {
	    Thread.sleep(4000);                 //1000 milliseconds is one second.
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}*/
	//return "<img src='http://localhost:8080/argal-versus/mvc/cont/get/"+Calendar.getInstance().getTimeInMillis()+"'/>";
	return "ok";
}
//PARA FACTURAS
@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
public @ResponseBody String uploadFile(MultipartHttpServletRequest request, HttpServletResponse response) { 
	Iterator<String> itr =request.getFileNames();
	MultipartFile mpf = request.getFile(itr.next());
	System.out.println(mpf.getOriginalFilename() +" uploaded factura!");	
	System.out.println(request.getParameter("idEventoHidden4"));
	String idEvento= request.getParameter("idEventoHidden4");
	
	Factura factura = new Factura();
	Evento eventoTmp = this.eventoServicio.obtenerEventoById(Integer.parseInt(idEvento));
	int idFactura=0;
	if (eventoTmp.getFacturas()!=null){
		for (int i=0;i<eventoTmp.getFacturas().size();i++){
			idFactura=eventoTmp.getFacturas().get(i).getIdFactura();
			System.out.println(idFactura);
		}    		
	}
	factura.setIdFactura(idFactura);
	try {
		ufile.idEvento = mpf.getBytes().length;
		ufile.length = mpf.getBytes().length;
		ufile.bytes= mpf.getBytes();
		ufile.type = mpf.getContentType();
		ufile.name = mpf.getOriginalFilename();		
		//Saving file
		System.out.println("TIPO DE ARCHIVO:"+ufile.type);
		factura.setArchivo(mpf.getBytes());
		factura.setRutaFactura("xlsx");
		if (ufile.type.equals("image/gif"))
			factura.setRutaFactura("gif");
		if (ufile.type.equals("image/jpg"))
			factura.setRutaFactura("jpg");
		if (ufile.type.equals("image/jpeg"))
			factura.setRutaFactura("jpeg");
		if (ufile.type.equals("application/pdf"))
			factura.setRutaFactura("pdf");
		if (ufile.type.equals("application/word"))
			factura.setRutaFactura("doc");
		if (ufile.type.equals("application/vnd.ms-excel"))			
			factura.setRutaFactura("xls");
		if (ufile.type.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))							   
			factura.setRutaFactura("xlsx");
		if (ufile.type.equals("application/excel"))
			factura.setRutaFactura("xls");
		if (ufile.type.equals("application/vnd.ms-word"))			
			factura.setRutaFactura("doc");
		if (ufile.type.equals("text/plain"))
			factura.setRutaFactura("txt");
		System.out.println("TIPO"+factura.getRutaFactura());
		this.eventoServicio.agregarFileFactura(factura);		
		System.out.println("Done"+ufile.length);
	}
	catch (IOException e) {
		e.printStackTrace();
	}
		
	/*try {
	    Thread.sleep(4000);                 //1000 milliseconds is one second.
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}*/
	//return "<img src='http://localhost:8080/argal-versus/mvc/cont/get/"+Calendar.getInstance().getTimeInMillis()+"'/>";
	return "ok";
}

@RequestMapping(value = "/uploadevidencia", method = RequestMethod.POST)
public @ResponseBody String uploadEvidencia(MultipartHttpServletRequest request, HttpServletResponse response) { 
	Iterator<String> itr =request.getFileNames();
	MultipartFile mpf = request.getFile(itr.next());
	System.out.println(mpf.getOriginalFilename() +" uploaded!");	
	System.out.println(request.getParameter("idEventoHidden"));
	String subCarpeta= request.getParameter("idEventoHidden");
	Gasto gasto= new Gasto();
	gasto.setIdGasto(5);
	try {
		//Verificar si existe el folder
		File dirFolder=new File("c:\\logs\\evidencias\\"+subCarpeta); 
		//Creacion de Directorios y Archivos.
		if(dirFolder.exists()) {
			System.out.println("ya exite el directorio!!");
		}
		else{
			dirFolder.mkdir();
			System.out.println("No existia el directorio, Pero ahora a sido creado!!");
		}
		ufile.idEvento = mpf.getBytes().length;
		ufile.length = mpf.getBytes().length;
		ufile.bytes= mpf.getBytes();
		ufile.type = mpf.getContentType();
		ufile.name = mpf.getOriginalFilename();
		System.out.println("TIPO DE ARCHIVO:"+ufile.type);
		gasto.setArchivo(mpf.getBytes());
		if (ufile.type.equals("image/gif"))
			gasto.setRutaEvidencia("gif");
		if (ufile.type.equals("image/jpg"))
			gasto.setRutaEvidencia("jpg");
		if (ufile.type.equals("image/jpeg"))
			gasto.setRutaEvidencia("jpeg");
		if (ufile.type.equals("application/pdf"))
			gasto.setRutaEvidencia("pdf");
		if (ufile.type.equals("application/word"))
			gasto.setRutaEvidencia("doc");		
		if (ufile.type.equals("application/vnd.ms-excel"))			
			gasto.setRutaEvidencia("xls");
		if (ufile.type.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))							   
			gasto.setRutaEvidencia("xlsx");
		System.out.println(gasto.getRutaEvidencia());
		this.eventoServicio.agregarFileGasto(gasto);

	//Saving file
		String content = "This is the content to write into file";		 		
		String filename= "c:\\logs\\evidencias\\"+subCarpeta+"\\"+ ufile.name;
		String s = "Java Code Geeks - Java Examples";
		File file = new File(filename);		
		FileOutputStream fos = null;
		fos = new FileOutputStream(file);
		fos.write(ufile.bytes);		
		fos.close();
		System.out.println("DoneGasto"+ufile.length);
	}
	catch (Exception e) {
		e.printStackTrace();
	}
		
	try {
	    Thread.sleep(4000);                 //1000 milliseconds is one second.
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}	//return "<img src='http://localhost:8080/argal-versus/mvc/cont/get/"+Calendar.getInstance().getTimeInMillis()+"'/>";
	return "ok";
}

@RequestMapping(value = "/uploadevidenciaFile", method = RequestMethod.POST)
public @ResponseBody String uploadEvidenciaFile(MultipartHttpServletRequest request, HttpServletResponse response) { 
	Iterator<String> itr =request.getFileNames();
	MultipartFile mpf = request.getFile(itr.next());
	System.out.println(mpf.getOriginalFilename() +" uploaded!");	
	System.out.println(request.getParameter("idEventoHidden"));
	System.out.println(request.getParameter("idEventoHidden2"));
	String idEventoParam=request.getParameter("idEventoHidden");
	String idEvento= "";
	if (idEventoParam==null || idEventoParam==""){
		if (request.getParameter("idEventoHidden2")!=null && request.getParameter("idEventoHidden2")!="")
			idEventoParam=request.getParameter("idEventoHidden2");
		else
			idEventoParam=request.getParameter("idEventoHidden3");
	}
	idEvento=idEventoParam;
	Gasto gasto= new Gasto();
	gasto.setIdGasto(5);
	Evento eventoTmp=this.eventoServicio.obtenerEventoById(Integer.parseInt(idEvento));
	int idGasto=0;
	if (eventoTmp.getGastos()!=null){
		for (int i=0;i<eventoTmp.getGastos().size();i++){
			idGasto=eventoTmp.getGastos().get(i).getIdGasto();
			//System.out.println(idGasto);
		}    		
	}
	System.out.println("gasto guardado:"+idGasto);
	gasto.setIdGasto(idGasto);
	try {
		//Verificar si existe el folder
		ufile.idEvento = mpf.getBytes().length;
		ufile.length = mpf.getBytes().length;
		ufile.bytes= mpf.getBytes();
		ufile.type = mpf.getContentType();
		ufile.name = mpf.getOriginalFilename();
		System.out.println("TIPO DE ARCHIVO:"+ufile.type);
		gasto.setArchivo(mpf.getBytes());
		if (ufile.type.equals("image/gif"))
			gasto.setRutaEvidencia("gif");
		if (ufile.type.equals("image/jpg"))
			gasto.setRutaEvidencia("jpg");
		if (ufile.type.equals("image/jpeg"))
			gasto.setRutaEvidencia("jpeg");
		if (ufile.type.equals("application/pdf"))
			gasto.setRutaEvidencia("pdf");
		if (ufile.type.equals("application/word"))
			gasto.setRutaEvidencia("doc");
		if (ufile.type.equals("application/vnd.ms-excel"))			
			gasto.setRutaEvidencia("xls");
		if (ufile.type.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))							   
			gasto.setRutaEvidencia("xlsx");
		this.eventoServicio.agregarFileGasto(gasto);

		System.out.println("Done"+ufile.length);
	}
	catch (Exception e) {
		System.out.println("error;"+e.getMessage());
		e.printStackTrace();
	}
		
	try {
	    Thread.sleep(4000);                 //1000 milliseconds is one second.
	} catch(InterruptedException ex) {
	    Thread.currentThread().interrupt();
	}
	//return "<img src='http://localhost:8080/argal-versus/mvc/cont/get/"+Calendar.getInstance().getTimeInMillis()+"'/>";
	return ""+gasto.getIdGasto();
}


@RequestMapping(value = "/uploadgasto", method = RequestMethod.POST)
public @ResponseBody String uploadGasto(MultipartHttpServletRequest request, HttpServletResponse response) { 
	Iterator<String> itr =request.getFileNames();
	MultipartFile mpf = request.getFile(itr.next());
	System.out.println(mpf.getOriginalFilename() +" uploaded!");	
	System.out.println(request.getParameter("idEventoHidden"));
	String subCarpeta= request.getParameter("idEventoHidden");
	try {
		ufile.idEvento = mpf.getBytes().length;
		ufile.length = mpf.getBytes().length;
		ufile.bytes= mpf.getBytes();
		ufile.type = mpf.getContentType();
		ufile.name = mpf.getOriginalFilename();		
		//Saving file
		String content = "This is the content to write into file";		 		
		String filename= "c:\\logs\\gastos\\"+subCarpeta+"\\"+ ufile.name;
		String s = "Java Code Geeks - Java Examples";
		File file = new File(filename);		
		FileOutputStream fos = null;
		fos = new FileOutputStream(file);
		fos.write(ufile.bytes);		
		fos.close();
		System.out.println("Done"+ufile.length);
	}
	catch (IOException e) {
		e.printStackTrace();
}
return "<img src='http://localhost:8080/argal-versus/mvc/cont/get/"+Calendar.getInstance().getTimeInMillis()+"'/>";
	//return null;
}

	@RequestMapping(value = "/uploadlistaFile", method = RequestMethod.POST)
	public @ResponseBody String uploadListaFile(MultipartHttpServletRequest request, HttpServletResponse response) { 
		Iterator<String> itr =request.getFileNames();
		MultipartFile mpf = request.getFile(itr.next());
		System.out.println(mpf.getOriginalFilename() +" uploaded!");	
		System.out.println(request.getParameter(""));		
		String idLista=request.getParameter("idArchivoLista");		
		System.out.println("lista guardada:"+idLista);		
		ListaPrecios lista = new ListaPrecios();
		lista.setIdListaPrecios(Integer.parseInt(idLista));
		try {
			//Verificar si existe el folder
			ufile.idEvento = mpf.getBytes().length;
			ufile.length = mpf.getBytes().length;
			ufile.bytes= mpf.getBytes();
			ufile.type = mpf.getContentType();
			ufile.name = mpf.getOriginalFilename();
			System.out.println("TIPO DE ARCHIVO:"+ufile.type);
			lista.setArchivo(mpf.getBytes());
			if (ufile.type.equals("image/gif"))
				lista.setRutaLista("gif");
			if (ufile.type.equals("image/jpg"))
				lista.setRutaLista("jpg");
			if (ufile.type.equals("image/jpeg"))
				lista.setRutaLista("jpeg");
			if (ufile.type.equals("application/pdf"))
				lista.setRutaLista("pdf");
			if (ufile.type.equals("application/word"))
				lista.setRutaLista("doc");
			if (ufile.type.equals("application/vnd.ms-excel"))			
				lista.setRutaLista("xls");
			if (ufile.type.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))							   
				lista.setRutaLista("xlsx");			
			this.listaPreciosServicio.agregarFileLista(lista);	
			System.out.println("Done"+ufile.length);
		}
		catch (Exception e) {
			System.out.println("error;"+e.getMessage());
			e.printStackTrace();
		}
			
		try {
		    Thread.sleep(4000);                 //1000 milliseconds is one second.
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		//return "<img src='http://localhost:8080/argal-versus/mvc/cont/get/"+Calendar.getInstance().getTimeInMillis()+"'/>";
		return "ok";
	}

}