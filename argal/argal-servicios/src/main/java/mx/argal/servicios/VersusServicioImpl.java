package mx.argal.servicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import mx.argal.dao.ListaPreciosDao;
import mx.argal.modelo.Gasto;
import mx.argal.modelo.ListaPrecios;
import mx.argal.modelo.Implant;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import mx.sep.sajja.datos.vo.FiltroBusquedaVO;


@Service
public class VersusServicioImpl implements VersusServicio {

	@Autowired
	private ListaPreciosDao listaPreciosDao;
	
	@Autowired
	private ListaPreciosMongoServicio listaPreciosMongoServicio;
		
	@Override
	public List doVersus(Gasto gasto, String idListaPrecios) {
		// TODO Auto-generated method stub
		List resultadoVersus = new ArrayList();
		List renglones = new ArrayList();
		try{
			File archivoXLS = File.createTempFile("pattern", ".xlsx");	    
			archivoXLS.deleteOnExit();
	
			System.out.println("RUTA TMP:"+archivoXLS.getAbsolutePath());
	 		FileOutputStream fos = new FileOutputStream(archivoXLS.getAbsolutePath());
	 	    fos.write(gasto.getArchivo());
	 	    fos.close();     	    
	    
	    //Se genera un hashMap con el valor columna-valor
	    	System.out.println("se carga el archivo:"+archivoXLS.getAbsolutePath());    		    		
			FileInputStream file = new FileInputStream(archivoXLS.getAbsolutePath());
			HSSFWorkbook workbook = null;
			Workbook workbookxlsx = null; 
			Sheet hoja = null;
			if (gasto.getRutaEvidencia().equals("xls")){
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
			String[] titulos={"codigo_servicio","descripcion","costo","porc_desc","descuento","subtotal","iva","total",""};
			Row fila = hoja.getRow(colNum);
			System.out.println("LINEAS:"+hoja.getLastRowNum());
			System.out.println("ListaPrecios:"+idListaPrecios);
			boolean leerProductos=false;
			float subTotalEdoCta=0;
			float subTotalDescuento=0;
			float diferenciaSubTotalMenosDescuento=0;
			Double descuento = this.listaPreciosMongoServicio.obtenerDescuentoMongo(idListaPrecios);
			System.out.println("DESCUENTO ENCONTRADO:"+descuento);
			for (; filaNum <= hoja.getLastRowNum()-1; filaNum++) { // Recorre cada fila de la hoja     			
				fila = hoja.getRow(filaNum);
				if (fila==null)
					break;
	    		//System.out.println("columnas:"+fila.getLastCellNum());
	    		if (!leerProductos){
					for (int columna = 0; columna < (fila.getLastCellNum()); columna++) {						
						// Recorre cada columna de la fila
						if (fila.getCell(columna)!=null){    					    					    				
							fila.getCell(columna).setCellType(Cell.CELL_TYPE_STRING);
							data = fila.getCell(columna).getStringCellValue();
							if (data.equals("CODIGO") || data.equals("CÓDIGO")){
								leerProductos=true;
								System.out.print("col:"+columna+"->Valor:"+data);	
							}										
						}
					}
	    		}
	    		else{	    	
	    			//PRODUCTOS ENCONTRADOS	    			
	    			if (fila.getCell(0)!=null){							
						fila.getCell(0).setCellType(Cell.CELL_TYPE_STRING);
						data = fila.getCell(0).getStringCellValue();
						List renglonLP = new ArrayList();							
						if (!data.equals("Suma:") && !data.equals("Suma") && !data.equals("SUMA") && !data.equals("SUMA:") ){
							if (fila.getCell(0).getStringCellValue()!="" ){									
								if (fila.getCell(1)!=null && fila.getCell(3)!=null && fila.getCell(7)!=null && fila.getCell(8)!=null &&fila.getCell(9)!=null){
									if (!fila.getCell(1).getStringCellValue().equals("") && !fila.getCell(3).getStringCellValue().equals("")){
									//System.out.println("valor antes:"+""+fila.getCell(9).getNumericCellValue());		
									renglonLP=this.listaPreciosMongoServicio.obtenerRenglonMongo(data,fila.getCell(1).getStringCellValue(),fila.getCell(3).getStringCellValue(),
											""+fila.getCell(9).getNumericCellValue(),fila.getCell(8).getNumericCellValue(), fila.getCell(7).getNumericCellValue(),idListaPrecios,filaNum+1);
									//if (renglonLP.get(4)!="N/E")
										subTotalEdoCta+=Float.parseFloat(""+renglonLP.get(5));										
									if (renglonLP.get(5)!=""){											
										subTotalDescuento+=Float.parseFloat(""+renglonLP.get(5))-Float.parseFloat(""+renglonLP.get(5))*descuento;
									}																					
									renglones.add(renglonLP);
									}
								}									
							}
						}													
					}					
	    		}	    		
	    		//System.out.println("columnas:"+fila.getLastCellNum());
			}			
			resultadoVersus.add(""+ round(0+subTotalEdoCta,2));
    		resultadoVersus.add(""+ round(0+subTotalDescuento,2));
    		resultadoVersus.add(""+ round((subTotalEdoCta-subTotalDescuento),2));
    		resultadoVersus.add(renglones);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return resultadoVersus;		
	}
	
	public static BigDecimal round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);       
        return bd;
    }
}
