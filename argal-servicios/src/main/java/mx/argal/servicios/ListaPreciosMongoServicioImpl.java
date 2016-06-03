package mx.argal.servicios;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.argal.modelo.ListaPrecios;
import mx.argal.mongo.ListaPreciosMongo;
import mx.argal.mongo.ListaPreciosMongoImpl;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

@Service
public class ListaPreciosMongoServicioImpl implements ListaPreciosMongoServicio{
	
	private ListaPreciosMongo listaPreciosMongo = new ListaPreciosMongoImpl();
	
	@Override 
	public List <String> guardarDocumento(ListaPrecios listaPrecios){
		MongoClient mongo = new MongoClient("localhost", 27017);
		List <String> datos=new ArrayList<String>();
		DB db = mongo.getDB("argal-listaprecios");
	    DBCollection collection = db.getCollection("listaprecios");
	    HashMap map = new HashMap();
	    //File archivoXLS = new File(nombreArchivo);	    	    
	    //Se crea el acumuladoTemporal
        try{
          	File archivoXLS = File.createTempFile("pattern", ".xlsx");	    
			archivoXLS.deleteOnExit();

			System.out.println("RUTA TMP:"+archivoXLS.getAbsolutePath());
     		FileOutputStream fos = new FileOutputStream(archivoXLS.getAbsolutePath());
     	    fos.write(listaPrecios.getArchivo());
     	    fos.close();     	    
        
        //Se genera un hashMap con el valor columna-valor
        	System.out.println("se carga el archivo:"+archivoXLS.getAbsolutePath());    		    		
    		FileInputStream file = new FileInputStream(archivoXLS.getAbsolutePath());
    		HSSFWorkbook workbook = null;
    		Workbook workbookxlsx = null; 
    		Sheet hoja = null;
    		if (listaPrecios.getRutaLista().equals("xls")){
    			workbook = new HSSFWorkbook(file);
    			hoja = workbook.getSheetAt(0);
    		}    			
    		else {
    			workbookxlsx = new XSSFWorkbook(file); //or new HSSFWorkbook();
    			hoja = workbookxlsx.getSheetAt(0);
    		}
        	//Se llena el encabezado        			        	    		
    		String data; 
    		String titulo;
    		//System.out.println("Nombre de la Hoja\t" + archivoExcel.getSheet(sheetNo).getName());
    		int filaNum=1;
    		int colNum=1;
    		String mesArchivo="";
    		String anioArchivo="";    		
    		String[] titulos={"codigo_servicio","descripcion","costo","porc_desc","descuento","subtotal","iva","total","","",""};
    		Row fila = hoja.getRow(colNum);
    		System.out.println("LINEAS:"+hoja.getLastRowNum());
    		for (; filaNum <= hoja.getLastRowNum()-1; filaNum++) { // Recorre cada fila de la hoja     			
    			fila = hoja.getRow(filaNum);
    			map = new HashMap();
    			map.put("id_listaprecios",listaPrecios.getIdListaPrecios());    			    			    		
    			if (fila==null)
    				break;
        		System.out.println("columnas:"+fila.getLastCellNum());
    			//for (int columna = 0; columna < (fila.getLastCellNum()); columna++) {    				
        		for (int columna = 0; columna < 8; columna++) {
    				// Recorre cada columna de la fila
    				if (fila.getCell(columna)!=null){    					    					    				
    					fila.getCell(columna).setCellType(Cell.CELL_TYPE_STRING);
    					data = fila.getCell(columna).getStringCellValue();    					    					
    					System.out.print("col:"+columna+"->Valor:"+data);
    					map.put(titulos[columna],data);
    				}
    			}
        		System.out.println("columnas:"+fila.getLastCellNum());
    			this.listaPreciosMongo.saveDocument(collection, map);
    		}			        			
    		file.close();    		
    		datos.add(mesArchivo);
    		datos.add(anioArchivo);
    		return datos;
        }
    	catch (Exception ioe) { 
    		System.out.println("Error:"+ioe.getMessage());
    		ioe.printStackTrace();    		
    	} 
        return datos;
	}
	
	
	public static boolean isNumber(String string){
		try{
			Integer.parseInt(string);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	@Override 
	public List obtenerRenglonMongo(String codigo, String fecha, String producto, String costoEdoCta, double cantidad, double descuento, String idListaPrecios,int renglon){
		List listaPrincipal  = new ArrayList();		
		MongoClient mongo = new MongoClient("localhost", 27017);
		String[] titulos={"codigo_servicio","descripcion","costo","porc_desc","descuento","subtotal","iva","total",""};
		DB db = mongo.getDB("argal-listaprecios");
	    DBCollection collection = db.getCollection("listaprecios");
	    //System.out.println("Collection mycol selected successfully");
		BasicDBObject whereQuery = new BasicDBObject();		
		//if (!codigo.equals("")){
		whereQuery.put("id_listaprecios", Integer.parseInt(idListaPrecios));
		whereQuery.put("codigo_servicio", codigo);		
        DBCursor cursor = collection.find(whereQuery);
        int i=1;
        //System.out.println("Se buscará el registro:'"+codigo+"'");
        List<String> listaMongo  = new ArrayList<String>();
        listaMongo.add(""+renglon);
        int found=0;
        //System.out.println(descuento);
        double descuentoEdoCta = descuento/100;
        //System.out.println("ahora"+descuentoEdoCta);
        while (cursor.hasNext()) {           
           found++;           
           listaMongo  = new ArrayList<String>();
           listaMongo.add(""+renglon);
           System.out.println("Encontrado!");            
           DBObject obj=cursor.next();
           float descuent=(float) (Float.parseFloat(""+obj.get("porc_desc")));           
           listaMongo.add(""+obj.get("codigo_servicio"));
           listaMongo.add(fecha);
           listaMongo.add(""+obj.get("descripcion"));
           float costoLP = round((float) (Float.parseFloat(""+obj.get("costo"))* cantidad),2).floatValue();
           System.out.println("cantidad"+cantidad);
           System.out.println(Float.parseFloat(""+obj.get("costo")));
           listaMongo.add(""+costoLP);           
           //Validando si es correcto el costo 
           float costoEdoCtaf = ( round((float) (Float.parseFloat(costoEdoCta)),2)).floatValue();          
           listaMongo.add(""+costoEdoCtaf);
           System.out.println("costoEdoCta:"+costoEdoCtaf);                      
           System.out.println("costoLP:"+costoLP); 
           if (costoEdoCtaf>costoLP){
        	   listaMongo.add(""+round((costoEdoCtaf-costoLP),2).floatValue());
        	   listaMongo.add(this.setCheckbox(""+renglon));
        	   listaMongo.add(this.setComboArea(""+renglon));
               listaMongo.add(this.setComboRubro(""+renglon));
           }
           else{
        	   listaMongo.add("0.00");
        	   listaMongo.add("");
        	   listaMongo.add("N/A");
        	   listaMongo.add("N/A");
           }                      
           //listaMongo.add(""+(Float.parseFloat(costoEdoCta) * descuento));
           /*listaMongo.add(""+obj.get("porc_desc"));
           listaMongo.add(""+obj.get("descuento"));
           listaMongo.add(""+obj.get("subtotal"));
           listaMongo.add(""+obj.get("iva"));
           listaMongo.add(""+obj.get("total"));*/                     
           listaPrincipal.add(listaMongo);
           i++;
        }
        if (found==0){
        	 listaMongo.add(codigo);
        	 listaMongo.add(fecha);
        	 listaMongo.add(producto);
        	 listaMongo.add("N/E");
        	 listaMongo.add(costoEdoCta);
        	 listaMongo.add("");
        	 listaMongo.add("");
        	 listaMongo.add("");
        	 listaMongo.add("");
        	 listaPrincipal.add(listaMongo);
             //listaMongo.add(""+(Float.parseFloat(costoEdoCta) * descuento));
        }        
        mongo.close();
	    return listaMongo;
	}
	
	@Override 
	public Double obtenerDescuentoMongo(String idListaPrecios){
		Double descuento;		
		MongoClient mongo = new MongoClient("localhost", 27017);		
		DB db = mongo.getDB("argal-listaprecios");
	    DBCollection collection = db.getCollection("listaprecios");
	    System.out.println("Collection mycol selected successfully");
		BasicDBObject whereQuery = new BasicDBObject();		
		//if (!codigo.equals("")){
		whereQuery.put("id_listaprecios", Integer.parseInt(idListaPrecios));
		DBCursor cursor = collection.find(whereQuery);
        while (cursor.hasNext()) {    
           DBObject obj=cursor.next();
           if (obj.get("porc_desc")!=null){
        	   if (isNumeric((""+obj.get("porc_desc")))){
        		   System.out.println("descuento:"+obj.get("porc_desc"));
        		   descuento = Double.parseDouble(""+obj.get("porc_desc"));
        		   mongo.close();
        		   return descuento;
        	   }
           }           
        }
        mongo.close();
	    return 0.0;
	}
	
	@Override
	public void testMongo() {
		// TODO Auto-generated method stub
		 MongoClient mongo = new MongoClient("localhost", 27017);
		 DB db = mongo.getDB("testDB");
	     DBCollection collection = db.getCollection("empleados");
	         
	        ///Delete All documents before running example again
	       WriteResult result = collection.remove(new BasicDBObject());
	       System.out.println("Test!!");
	       System.out.println(result);
	        try{
		        this.listaPreciosMongo.basicDBObject_Example(collection);
		         
		        this.listaPreciosMongo.basicDBObjectBuilder_Example(collection);
		         
		        this.listaPreciosMongo.hashMap_Example(collection);
		         
		        this.listaPreciosMongo.parseJSON_Example(collection);
		         
		        DBCursor cursor = collection.find();
		        while(cursor.hasNext()) {
		            System.out.println(cursor.next());
		        }
	        }
	        catch (Exception e){
	        	System.out.println("Error:"+e.getMessage());
	        }		
	}

	@Override
	public void eliminarDocumento(ListaPrecios listaPrecios) {
		// TODO Auto-generated method stub
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("argal-listaprecios");
	    DBCollection collection = db.getCollection("listaprecios");	
		System.out.println("Eliminando.mongo:"+listaPrecios.getIdListaPrecios());	
	    BasicDBObject query = new BasicDBObject();
	    query.append("id_listaprecios", listaPrecios.getIdListaPrecios());
	    collection.remove(query);
	}
		
	
	public String setComboArea(String renglon){
		String cadena=
				"<select id='area"+renglon+"'name='area"+renglon+"'data-validation='gastobsval'>"+						
				"<option value='1'>PISO</option>"+
				"<option value='2'>QUIROFANO</option>"+
				"<option value='3'>TERAPIA INTENSIVA, INTERMEDIA, NEONATAL</option>"+
				"<option value='4'>URGENCIAS</option>"+
				"<option value='5'>GASTOS PERSONALES</option>"+		    		
				"</select>";
		return cadena;
	}
	
	public String setComboRubro(String renglon){
		String cadena=
				"<select id='rubro"+renglon+"'name='rubro"+renglon+"'data-validation='gastobsval'>"+				
				"<option value=1>MEDICAMENTOS</option>"+
				"<option value=2>MATERIAL</option>"+
				"<option value=3>LABORATORIO Y RX</option>"+
				"<option value=4>TERAPIA RESPIRATORIA</option>"+
				"<option value=5>HABITACIONES</option>"+
				"<option value=6>ANESTESIA (MAQ Y GAS)</option>"+
				"<option value=7>RENTAS DE EQUIPOS</option>"+
				"<option value=8>INSUMOS PROVEEDOR EXTERNO</option>"+
				"<option value=9>CUBICULOS</option>"+
				"<option value=10>TERAPIA INTENSIVA</option>"+
				"<option value=11>BANCO DE SANGRE</option>"+	    		
				"<option value=12>QUIROFANO</option>"+
				"<option value=13>TIEMPOS DE SALA</option>"+			    		
				"</select>";
		return cadena;
	}
	
	public String setCheckbox(String renglon){
		String cadena="";
			cadena="<input type='checkbox' id='check"+renglon+"'name='check"+renglon+"' checked />";
		return cadena;
	}
	
	public static boolean isNumeric( String str )
	{	 
	 if (str.equals("") || str.equals("0.0"))
		 return false;
     try
        {
                /*
                 * To check if the number is valid decimal number, use
                 * double parseDouble(String str) method of
                 * Double wrapper class.
                 *
                 * This method throws NumberFormatException if the
                 * argument string is not a valid decimal number.
                 */
                Double.parseDouble(str);
                if (Double.parseDouble(str)>=0.8)
                	return false;
                return true;
        }
        catch(NumberFormatException nme) {
            return false;
        }   
	}
	
	public static BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);       
        return bd;
    }
}
