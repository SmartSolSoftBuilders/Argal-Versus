package mx.argal.servicios;

import java.io.File;

import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import mx.argal.dao.EventoDao;
import mx.argal.modelo.Evento;
import mx.argal.modelo.Gasto;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
/*Librerías para trabajar con archivos excel*/
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportesServicioImpl implements ReportesServicio {
	
	@Autowired
	private EventoDao eventoDao;
	private List<String> encabezados;
	private List<String> contenidoCeldas;
	
	//String rutaArchivos="C:\\tmp\\";
	String rutaArchivos="/home/";
	 
	@Override
	public File generarLayoutBanco(int id1, String id2, String fechaInicio, String fechaFin) {
		/*La ruta donde se creará el archivo*/
        //String rutaArchivo = System.getProperty("user.home")+"/ejemploExcelJava.xls";
		String rutaArchivo = rutaArchivos+"LAYOUT_BITACORA_BANCOS.xlsx";
		 
        /*Se crea el objeto de tipo File con la ruta del archivo*/
		File archivoXLS = new File(rutaArchivo); 
		Evento eventoTmp=new Evento();
		if (fechaInicio!="")
			eventoTmp.setFechaIngreso(new Date(fechaInicio));
		if (fechaFin!="")
			eventoTmp.setFechaEgreso(new Date(fechaFin));
		//Verificar si el evento es finalizado
		List<Evento> eventos = new ArrayList<Evento>();
		//Para eventos finalizados
		if (id2.equals("true")){
			eventoTmp.setStatusEvento(3);
			//System.out.println("Finalizados");
			}
		else{
			eventoTmp.setStatusEvento(1);
		}
		eventos = eventoDao.obtenerEventosBancoByParams(eventoTmp);
		System.out.println("EVENTOS ENCONTRADOS:"+eventos.size());
        /*Se crea el archivo*/
        int filaInicial=9;
        int columnaInicial=1;
        int totalDatos=97;
        try{
        	FileInputStream file = new FileInputStream(archivoXLS);
        	Workbook wb = new XSSFWorkbook(file); //or new HSSFWorkbook();
        	//HSSFWorkbook workbook = new HSSFWorkbook(file);
        	//HSSFSheet hoja = workbook.getSheetA	t(0);
            Sheet hoja = wb.getSheetAt(0);
        	//Total eventos 
        	Row fila = hoja.getRow(3);     	  
        	fila.createCell(2).setCellValue(eventos.size());
        	//Se guardarán todos los eventos
        	if (eventos.size()>0){
        	for (int j=filaInicial,indexEventos=0; j<(eventos.size()+filaInicial); j++,indexEventos++){
        		//System.out.println("PRIMER EVENTO!");
        		fila=hoja.getRow(j);
        		//for (int k=columnaInicial,recorrerCeldas=1;k<totalDatos; k++){
        		//System.out.println("j+j"+eventos.get(indexEventos).getIdEvento());
        		int recorrerCeldas=1;
        		fila.createCell(recorrerCeldas++).setCellValue(this.getMes(eventos.get(indexEventos)));
        		fila.createCell(recorrerCeldas++).setCellValue(this.getSemanas(eventos.get(indexEventos)));
        		fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getHospital().getNombreHospital());
        		fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getNumHabitacion());
        		fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getHospital().getCiudadHospital());
        		fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getHospital().getMunicipioDelHospital());
        		if (eventos.get(indexEventos).getRegistroSeguroPersonal()!=null){
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getCliente().getNombreCorto());
        		}
        		else{
        			fila.createCell(recorrerCeldas++).setCellValue("");
        		}
        		
        	    CellStyle cellStyle = wb.createCellStyle();
        	    CreationHelper createHelper = wb.getCreationHelper();
        	    short dateFormat = createHelper.createDataFormat().getFormat("dd/MM/yyyy");
        	    cellStyle.setDataFormat(dateFormat);        	    
        		fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getFechaCaptura());
        	    fila.getCell(recorrerCeldas-1).setCellStyle(cellStyle);
        		fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getFechaIngreso());
        		fila.getCell(recorrerCeldas-1).setCellStyle(cellStyle);
        		if (eventos.get(indexEventos).getFechaEgreso()!=null){
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getFechaEgreso());
        			fila.getCell(recorrerCeldas-1).setCellStyle(cellStyle);
        		}
        		else
        			fila.createCell(recorrerCeldas++).setCellValue("");
        		if (eventos.get(indexEventos).getFechaEgresoCapt()!=null){
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getFechaEgresoCapt());
        			fila.getCell(recorrerCeldas-1).setCellStyle(cellStyle);
        		}
        		else
        			fila.createCell(recorrerCeldas++).setCellValue("");
        		//Días estancia hosp        		
        		String dias =this.getDiasEstHosp(eventos.get(indexEventos));        		
        		int diasRep = 0;
        		if (dias!="" && dias != null){
        			if (Integer.parseInt(dias)>0){
        				diasRep = Integer.parseInt(dias);
            			diasRep = diasRep - 0;
        			}
        			
        		}        		
        		fila.createCell(recorrerCeldas++).setCellValue(diasRep);        		        		
        		if (eventos.get(indexEventos).getDiasIncapacidad()!=null)
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getDiasIncapacidad());
        		else 
        			fila.createCell(recorrerCeldas++).setCellValue(0);
        		if (eventos.get(indexEventos).getRegistroSeguroPersonal()!=null){
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getRegistroSeguroPersonal().getNombreTitular() +" "+ eventos.get(indexEventos).getRegistroSeguroPersonal().getAppTitular() +" "+ eventos.get(indexEventos).getRegistroSeguroPersonal().getApmTitular());
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getRegistroSeguroPersonal().getNombrePaciente()+" " + eventos.get(indexEventos).getRegistroSeguroPersonal().getAppPaciente() +" "+ eventos.get(indexEventos).getRegistroSeguroPersonal().getApmPaciente());
        		}
        		else{
            		fila.createCell(recorrerCeldas++).setCellValue("");
            		fila.createCell(recorrerCeldas++).setCellValue("");   				
        		}
        		if (eventos.get(indexEventos).getRegistroSeguroPersonal()!=null){
        			//STATUS PACIENTE
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getTipoEvento().getDescripcion());
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroSeguroPersonal().getSexoPaciente());
       				if (eventos.get(indexEventos).getTipoEvento().getIdTipoEvento()!=4){       					
       					fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroSeguroPersonal().getEdadPaciente());
       					fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroSeguroPersonal().getUnidadEdadPaciente());
       				}
       				else{
       					//fila.createCell(recorrerCeldas++).setCellValue("");
       					fila.createCell(recorrerCeldas++).setCellValue("");
       					fila.createCell(recorrerCeldas++).setCellValue("");
       				}
       				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroSeguroPersonal().getCondicionPaciente());
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroSeguroPersonal().getCenso());
        			if (eventos.get(indexEventos).getRegistroSeguroPersonal().getCapita()!=null)
        				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroSeguroPersonal().getCapita());
        			else
        				fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroSeguroPersonal().getRelacionPaciente());
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroSeguroPersonal().getNumAutorizacion());
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroSeguroPersonal().getNumeroNomina());       				
       			}
       			else{
       				fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");
        		}
        		if (eventos.get(indexEventos).getMedicoTratante()!=null){
        			if (eventos.get(indexEventos).getMedicoTratante().getEspecialidades()!=null){
        				fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getMedicoTratante().getNombre());
        				if (eventos.get(indexEventos).getMedicoTratante().getEspecialidades().size()>0)
        						fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getMedicoTratante().getEspecialidades().get(0).getDescripcion());
            			else
                			fila.createCell(recorrerCeldas++).setCellValue("");
    					String tipo="";
        				if (eventos.get(indexEventos).getMedicoTratante()!=null){
        					if (eventos.get(indexEventos).getMedicoTratante().getTipo()==1)
        						tipo="RED";
        					if (eventos.get(indexEventos).getMedicoTratante().getTipo()==2)
        						tipo="STAFF";
        					if (eventos.get(indexEventos).getMedicoTratante().getTipo()==3)
        						tipo="INTERINO";
        				}
        				fila.createCell(recorrerCeldas++).setCellValue(tipo);
        			}
        			else{
            			fila.createCell(recorrerCeldas++).setCellValue("");
            			fila.createCell(recorrerCeldas++).setCellValue("");
            			fila.createCell(recorrerCeldas++).setCellValue("");        					
        			}
        		}
        		else{
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");
        		}
        		//Obtener interconsultas
    			if (eventos.get(indexEventos).getBitacoras()!=null && eventos.get(indexEventos).getBitacoras().size()>0){
    				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getBitacoras().get(eventos.get(indexEventos).getBitacoras().size()-1).getInterconsulta());
    			}
    			else
    				fila.createCell(recorrerCeldas++).setCellValue("");
        		
        		if (eventos.get(indexEventos).getDiagnosticoIngreso1()!=null){
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getDiagnosticoIngreso1().getDescripcion());
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getDiagnosticoIngreso1().getClave());
        		}
        		else{
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");        				
        		}
        		if (eventos.get(indexEventos).getDiagnosticoIngreso2()!=null){
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getDiagnosticoIngreso2().getDescripcion());
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getDiagnosticoIngreso2().getClave());
        		}
        		else{
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");        				
        		}
        		//
        		if (eventos.get(indexEventos).getDiagnosticoEgreso1()!=null){
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getDiagnosticoEgreso1().getDescripcion());
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getDiagnosticoEgreso1().getClave());
        		}
        		else{
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");        				
        		}
        		if (eventos.get(indexEventos).getProcedimiento1()!=null){
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getProcedimiento1().getDescripcion());
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getProcedimiento1().getCveCpt());
        		}
        		else{
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");        				
        		}
        		if (eventos.get(indexEventos).getProcedimiento2()!=null){
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getProcedimiento2().getDescripcion());
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getProcedimiento2().getCveCpt());
        		}
        		else{
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");        				
        		}
        		if (eventos.get(indexEventos).getRegistroSeguroPersonal()!=null && eventos.get(indexEventos).getBitacoras().size()>0){
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getBitacoras().get(eventos.get(indexEventos).getBitacoras().size()-1).getDescripcion());
        		}
        		else{
        			fila.createCell(recorrerCeldas++).setCellValue("");
        		}
        		//Seguimiento diario
        		//Obtener montos diarios
        		float montoAnt=0;
        		float montoAct=0;
        		System.out.println(eventos.get(indexEventos).getGastos().size());
        		if (eventos.get(indexEventos).getGastos().size()>0){
        			for (int indx=0;indx<eventos.get(indexEventos).getGastos().size();indx++){
        				montoAct=eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario();
        				if (indx>0)
        					montoAnt=eventos.get(indexEventos).getGastos().get(indx-1).getMontoUnitario();
        			}
        		}
        		System.out.println(montoAnt);
        		System.out.println(montoAct);
        		fila.createCell(recorrerCeldas++).setCellValue(montoAnt);
    			fila.createCell(recorrerCeldas++).setCellValue(montoAct);	
    			//Obtener bitácoras médicas
    				if (eventos.get(indexEventos).getBitacoras().size()>0){
    	       			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getBitacoras().get(0).getObservaciones());
    				}
    				else
    					fila.createCell(recorrerCeldas++).setCellValue("");
        			//Datos para nacimientos
        			if (eventos.get(indexEventos).getTipoEvento().getIdTipoEvento()==4){
        				if (eventos.get(indexEventos).getRegistroSeguroPersonal()!=null){
        					fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getRegistroSeguroPersonal().getNacimientoTipoParto());
        					fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getRegistroSeguroPersonal().getNacimientoPeso());
        					fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getRegistroSeguroPersonal().getNacimientoTalla());
        					fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getRegistroSeguroPersonal().getNacimientoApgar());
        				}
        				else{
            				fila.createCell(recorrerCeldas++).setCellValue("");
            				fila.createCell(recorrerCeldas++).setCellValue("");
            				fila.createCell(recorrerCeldas++).setCellValue("");
            				fila.createCell(recorrerCeldas++).setCellValue("");        					
        				}
        			}
        			else{
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");
        			}
        			//Desvíos cargos en piso
        			float montoDesvPisoMaterial=0;
        			float montoDesvPisoMedicamentos=0;
        			float montoDesvPisoLab=0;
        			float montoDesvPisoTerapiaResp=0;
        			float montoDesvPisoHabitaciones=0;
        			//Desvíos cargos en quirófano
        			float montoDesvQuirofMaterial=0;
        			float montoDesvQuirofMedicamentos=0;
        			float montoDesvQuirofLab=0;
        			float montoDesvQuirofTerapiaResp=0;
        			float montoDesvQuirofHabitaciones=0;
        			//Desvíos cargos en terapia
        			float montoDesvTerapiaMaterial=0;
        			float montoDesvTerapiaMedicamentos=0;
        			float montoDesvTerapiaLab=0;
        			float montoDesvTerapiaTerapiaResp=0;
        			float montoDesvTerapiaHabitaciones=0;
        			//Desvíos cargos en urgencias
        			float montoDesvUrgencMaterial=0;
        			float montoDesvUrgencMedicamentos=0;
        			float montoDesvUrgencLab=0;
        			float montoDesvUrgencTerapiaResp=0;
        			float montoDesvUrgencHabitaciones=0;
        			float montoDesvGastosPersonales=0;
        			if (eventos.get(indexEventos).getGastos().size()>0){
        				for (int indx=0;indx<eventos.get(indexEventos).getGastos().size();indx++){
        					//Se obtienen sólo los desvíos
        					if (eventos.get(indexEventos).getGastos().get(indx).getIdTipoCargo()==2){
        						//PISO
        						if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==1){
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==2)
        								montoDesvPisoMaterial=montoDesvPisoMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==1)
        								montoDesvPisoMaterial=montoDesvPisoMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==3)
        								montoDesvPisoMaterial=montoDesvUrgencMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==4)
        								montoDesvPisoMaterial=montoDesvUrgencMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==5)
        								montoDesvPisoMaterial=montoDesvPisoMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());       							
        						}
        						//QUIROFANO 
        						if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==2){
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==2)
        								montoDesvQuirofMaterial=montoDesvQuirofMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==1)
        								montoDesvQuirofMaterial=montoDesvQuirofMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==3)
        								montoDesvQuirofMaterial=montoDesvQuirofMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==4)
        								montoDesvQuirofMaterial=montoDesvQuirofMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==5)
        								montoDesvQuirofMaterial=montoDesvQuirofMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());       							
        						}
           						//Terapia 
        						if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==3){
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==2)
        								montoDesvTerapiaMaterial=montoDesvTerapiaMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==1)
        								montoDesvTerapiaMaterial=montoDesvTerapiaMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==3)
        								montoDesvTerapiaMaterial=montoDesvTerapiaMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==4)
        								montoDesvTerapiaMaterial=montoDesvTerapiaMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==5)
        								montoDesvTerapiaMaterial=montoDesvTerapiaMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());       							
        						}
           						//Urgencias
        						if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==4){
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==2)
        								montoDesvUrgencMaterial=montoDesvUrgencMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==1)
        								montoDesvUrgencMaterial=montoDesvUrgencMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==3)
        								montoDesvUrgencMaterial=montoDesvUrgencMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==4)
        								montoDesvUrgencMaterial=montoDesvUrgencMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==5)
        								montoDesvUrgencMaterial=montoDesvUrgencMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());       							
        						}
        						//Gastos Personales
        						if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==5){        							
        							montoDesvGastosPersonales=montoDesvGastosPersonales+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());        							       						
        						}
        					}
        				}
        			}
        			//PISO
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvPisoMaterial);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvPisoMedicamentos);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvPisoLab);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvPisoTerapiaResp);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvPisoHabitaciones);
        			//QUIROFANO
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvQuirofMaterial);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvQuirofMedicamentos);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvQuirofLab);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvQuirofTerapiaResp);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvQuirofHabitaciones);
        			fila.createCell(recorrerCeldas++).setCellValue(0);
        			//TERAPIA
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvTerapiaMaterial);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvTerapiaMedicamentos);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvTerapiaLab);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvTerapiaTerapiaResp);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvTerapiaHabitaciones);
        			fila.createCell(recorrerCeldas++).setCellValue(0);
        			//URGENCIAS
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvUrgencMaterial);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvUrgencMedicamentos);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvUrgencLab);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvUrgencTerapiaResp);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvUrgencHabitaciones);
        			
        			//Jubilados
        			if (eventos.get(indexEventos).getRegistroSeguroPersonal().getCantidadCubiertaConvenio()!=null){
        				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroSeguroPersonal().getCantidadCubiertaConvenio());
        				//fila.createCell(recorrerCeldas++).setCellValue(0);
        				float tmpMDD=0;
        				/*if (eventos.get(indexEventos).getMontoDespuesDesvios()!=null && eventos.get(indexEventos).getMontoDespuesDesvios()!=0) {
        					tmpMDD=eventos.get(indexEventos).getTotalDesvios();
        				}*/
        				if (eventos.get(indexEventos).getMontoFinalFacturacion()!=null && eventos.get(indexEventos).getMontoFinalFacturacion()!=0) {
        					tmpMDD=eventos.get(indexEventos).getMontoFinalFacturacion();
        				}
        				fila.createCell(recorrerCeldas++).setCellValue(""+tmpMDD);       			 
        			}else{
        				fila.createCell(recorrerCeldas++).setCellValue(0);
           				fila.createCell(recorrerCeldas++).setCellValue(0);
        			}
        			//Obteniendo el total de cargos observados 
        			float cargosObservados=montoDesvPisoMaterial+montoDesvPisoMedicamentos+montoDesvPisoLab+montoDesvPisoTerapiaResp;
        			cargosObservados=cargosObservados+montoDesvPisoHabitaciones+montoDesvQuirofMaterial;
        			cargosObservados=cargosObservados+montoDesvQuirofMedicamentos+montoDesvQuirofLab+montoDesvQuirofTerapiaResp;
        			cargosObservados=cargosObservados+montoDesvQuirofHabitaciones;
        			cargosObservados=cargosObservados+montoDesvTerapiaMaterial+montoDesvTerapiaMedicamentos+montoDesvTerapiaLab;
        			cargosObservados=cargosObservados+montoDesvTerapiaTerapiaResp+montoDesvTerapiaHabitaciones+montoDesvUrgencMaterial;
        			cargosObservados=cargosObservados+montoDesvUrgencMedicamentos+montoDesvUrgencLab+montoDesvUrgencTerapiaResp;
        			cargosObservados=cargosObservados+montoDesvUrgencHabitaciones+montoDesvGastosPersonales;
        			
        			//Montos Finales Evento
        			if (eventos.get(indexEventos).getMontoAntesDesvios()!=null)
        				fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getMontoAntesDesvios());
        			else
        				fila.createCell(recorrerCeldas++).setCellValue(0);
        			float montTmAntD=0;
        			if (eventos.get(indexEventos).getMontoAntesDesvios()!=null){
        				montTmAntD=Float.parseFloat(""+eventos.get(indexEventos).getMontoAntesDesvios());
        				if (eventos.get(indexEventos).getTotalDesvios()!=null && eventos.get(indexEventos).getTotalDesvios()!=0)
        					cargosObservados=Float.parseFloat(""+eventos.get(indexEventos).getTotalDesvios());
        				else
        					cargosObservados=0;
        				fila.createCell(recorrerCeldas++).setCellValue((montTmAntD-cargosObservados));
        			}
        			else
        				fila.createCell(recorrerCeldas++).setCellValue(0);
        			if (eventos.get(indexEventos).getDescuentoHospital()!=null)
        				fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getDescuentoHospital());
        			else
        				fila.createCell(recorrerCeldas++).setCellValue(0);
        			if (eventos.get(indexEventos).getMontoFinalFacturacion()!=null)
        				fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getMontoFinalFacturacion());
        			else
        				fila.createCell(recorrerCeldas++).setCellValue(0);
        			//Diferencia facturación - monto después desvíos
        			if (eventos.get(indexEventos).getMontoFinalFacturacion()!=null){
        				float tmp1=eventos.get(indexEventos).getMontoFinalFacturacion();
        				float tmp2=montTmAntD-cargosObservados;
        				fila.createCell(recorrerCeldas++).setCellValue((tmp1-tmp2));
        			}
        			else
        				fila.createCell(recorrerCeldas++).setCellValue(0);
        			if(eventos.get(indexEventos).getComentariosDiferenciaFacturacion()!=null) 
        				fila.createCell(recorrerCeldas++).setCellValue(""+(eventos.get(indexEventos).getComentariosDiferenciaFacturacion()));
        			else
        				fila.createCell(recorrerCeldas++).setCellValue("");
        			//System.out.println("FACTURAS:"+eventos.get(indexEventos).getFacturas().size());
        			int numFacturas=eventos.get(indexEventos).getFacturas().size();
        			if (numFacturas>0){
        				//num de factura
	        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getFacturas().get(numFacturas-1).getNumeroFactura());
	        			//Aprobada / Rechazada
	    				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getFacturas().get(numFacturas-1).getAprobada());
	    				//Detalle
	    				if (eventos.get(indexEventos).getFacturas().get(numFacturas-1).getDetalle()!=null)
	    					fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getFacturas().get(numFacturas-1).getDetalle());
	    				else
	    					fila.createCell(recorrerCeldas++).setCellValue("");
	       				//Ajuste Factura
	    				if (eventos.get(indexEventos).getFacturas().get(numFacturas-1).getAjusteFactura()!=null)
	    					fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getFacturas().get(numFacturas-1).getAjusteFactura());
	    				else
	    					fila.createCell(recorrerCeldas++).setCellValue("");
	       				//Tipo Comprobante Fiscal Corregido
	    				if (eventos.get(indexEventos).getFacturas().get(numFacturas-1).getTipoComprobanteFiscalCorregido()!=null)
	    					fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getFacturas().get(numFacturas-1).getTipoComprobanteFiscalCorregido());
	       				else
	    					fila.createCell(recorrerCeldas++).setCellValue("");
	       				//Folio Comp
	    				if (eventos.get(indexEventos).getFacturas().get(numFacturas-1).getFolioComprobanteFiscalCorregido()!=null)
	    					fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getFacturas().get(numFacturas-1).getFolioComprobanteFiscalCorregido());
	       				else
	    					fila.createCell(recorrerCeldas++).setCellValue("");
	       				//Monto Comp
	    				if (eventos.get(indexEventos).getFacturas().get(numFacturas-1).getMontoComprobanteFiscalCorregido()!=null)
	    					fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getFacturas().get(numFacturas-1).getMontoComprobanteFiscalCorregido());
	       				else
	    					fila.createCell(recorrerCeldas++).setCellValue("");
        			}
        			else{
	        			//num de factura
	        			fila.createCell(recorrerCeldas++).setCellValue("");
	        			//Aprobada / Rechazada
	    				fila.createCell(recorrerCeldas++).setCellValue("");
	    				//Detalle
	       				fila.createCell(recorrerCeldas++).setCellValue("");
	       				//Ajuste Factura
	       				fila.createCell(recorrerCeldas++).setCellValue("");
	       				//Tipo Comprobante Fiscal Corregido
	       				fila.createCell(recorrerCeldas++).setCellValue("");
	       				//Folio Comp
	       				fila.createCell(recorrerCeldas++).setCellValue("");
	       				//Monto Comp
	       				fila.createCell(recorrerCeldas++).setCellValue("");
        			}
        			if (eventos.get(indexEventos).getDescuentoNoAplicado()!=null)
        				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getDescuentoNoAplicado());
        			else
        				fila.createCell(recorrerCeldas++).setCellValue("");
        			//fila.createCell(recorrerCeldas++).setCellValue(""+montoDesvGastosPersonales);
        			fila.createCell(recorrerCeldas++).setCellValue(0);
        			fila.createCell(recorrerCeldas++).setCellValue(""+cargosObservados);
        			// TOTALES DESGLOCE GASTOS RELEVANTES
        			float desgGastRelevTerapia=0;
        			float desgGastRelevMedicamentos=0;
        			float desgGastRelevMateriales=0;
        			float desgGastRelevBancoSang=0;
        			float desgGastRelevQuirofano=0;
        			float desgGastRelevOtros=0;        			
        			if (eventos.get(indexEventos).getGastos().size()>0){
        				for (int indx=0;indx<eventos.get(indexEventos).getGastos().size();indx++){
        					//Se obtienen sólo los desvíos
        					if (eventos.get(indexEventos).getGastos().get(indx).getIdTipoCargo()==3){
        						if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==4)
        							desgGastRelevTerapia=desgGastRelevTerapia+ eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario();
    							if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==1)
    								desgGastRelevMedicamentos=desgGastRelevMedicamentos+ eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario();
    							if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==2)
    								desgGastRelevMateriales=desgGastRelevMateriales+ eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario();
    							if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==11)
    								desgGastRelevBancoSang=desgGastRelevBancoSang+ eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario();
    							if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()!=4 &&
    									eventos.get(indexEventos).getGastos().get(indx).getIdArea()!=11 &&
    									eventos.get(indexEventos).getGastos().get(indx).getIdArea()!=1 &&
    									eventos.get(indexEventos).getGastos().get(indx).getIdArea()!=2)
    								desgGastRelevOtros=desgGastRelevOtros+ eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario();
        					}
        				}
        			}
        			fila.createCell(recorrerCeldas++).setCellValue(desgGastRelevTerapia);
        			fila.createCell(recorrerCeldas++).setCellValue(desgGastRelevMedicamentos);
        			fila.createCell(recorrerCeldas++).setCellValue(desgGastRelevMateriales);
        			fila.createCell(recorrerCeldas++).setCellValue(desgGastRelevBancoSang);
        			fila.createCell(recorrerCeldas++).setCellValue(desgGastRelevQuirofano);
        			fila.createCell(recorrerCeldas++).setCellValue(desgGastRelevOtros);
        			
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			//Def
        			if (eventos.get(indexEventos).getHoraDef()!=null){
        				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getHoraDef());
        				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getCausaDirectaDef());
        			}
        			else{
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");
        			}
        			//Nom Recien Nacido
        			if (eventos.get(indexEventos).getTipoEvento().getIdTipoEvento()==4){
        				fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getRegistroSeguroPersonal().getAppPaciente()+" "+eventos.get(indexEventos).getRegistroSeguroPersonal().getApmPaciente() +" " +eventos.get(indexEventos).getRegistroSeguroPersonal().getNombrePaciente());
        			}
        			else{
        				fila.createCell(recorrerCeldas++).setCellValue("");
        			}
        			//Gastos Evitados
        			float gastosEvitados=0;
        			if (eventos.get(indexEventos).getGastos()!=null){        				
        				for (int i=0;i<eventos.get(indexEventos).getGastos().size();i++){
        					if (eventos.get(indexEventos).getGastos().get(i).getIdTipoCargo()==4)
        						gastosEvitados+=eventos.get(indexEventos).getGastos().get(i).getMontoUnitario();
        				}
        			}
        			fila.createCell(recorrerCeldas++).setCellValue(gastosEvitados);
        		}
        	}
        	//}  	 	
            file.close();
            //FileOutputStream outFile =new FileOutputStream(new File("/home/update.xlsx"));
            FileOutputStream outFile =new FileOutputStream(new File(rutaArchivos+"update.xlsx"));
            
            wb.write(outFile);
            outFile.flush();
            outFile.close();
            archivoXLS = new File(rutaArchivos+"update.xlsx");
            //archivoXLS = new File("/home/update.xlsx");
            
		} 
        catch (IOException e) {
			// TODO Auto-generated catch block
        	System.out.println("<OTIKA>ERROR!"+e.getMessage());
			e.printStackTrace();
		}
        /*Cerramos el flujo de datos*/        
        return archivoXLS;
	}
	
	@Override
	public File generarLayoutDiarioBanco(int id1, String id2, String fechaInicio, String fechaFin) throws Exception{
		/*La ruta donde se creará el archivo*/
        //String rutaArchivo = System.getProperty("user.home")+"/ejemploExcelJava.xls";
		String rutaArchivo = rutaArchivos+"LAYOUT_BITACORA_BANCOS.xlsx";
		//String rutaArchivo = "/home/LAYOUT_BITACORA_BANCOS.xlsx";		 
        /*Se crea el objeto de tipo File con la ruta del archivo*/
		File archivoXLS = new File(rutaArchivo); 
		Evento eventoTmp=new Evento();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");		
		if (fechaInicio!="")
			eventoTmp.setFechaIngreso(sdf.parse(fechaInicio));
		if (fechaFin!="")
			eventoTmp.setFechaEgreso(sdf.parse(fechaFin));
		//Verificar si el evento es finalizado
		List<Evento> eventos = new ArrayList<Evento>();
		//Para eventos finalizados
		if (id2.equals("1")){
			eventoTmp.setStatusEvento(3);
			eventos = eventoDao.obtenerEventosBancoLayoutDiarioByParams(eventoTmp);
			//System.out.println("Layout diario");
			}
		else{
			eventoTmp.setStatusEvento(1);
			eventos = eventoDao.obtenerEventosBancoLayoutMensualByParams(eventoTmp);			
		}		
		//System.out.println("EVENTOS ENCONTRADOS:"+eventos.size());
        /*Se crea el archivo*/
        int filaInicial=9;
        int columnaInicial=1;
        int totalDatos=97;
        try{
        	FileInputStream file = new FileInputStream(archivoXLS);
        	Workbook wb = new XSSFWorkbook(file); //or new HSSFWorkbook();
        	//HSSFWorkbook workbook = new HSSFWorkbook(file);
        	//HSSFSheet hoja = workbook.getSheetA	t(0);
            Sheet hoja = wb.getSheetAt(0);
        	//Total eventos 
        	Row fila = hoja.getRow(3);     	  
        	fila.createCell(2).setCellValue(eventos.size());
        	//Se guardarán todos los eventos
        	if (eventos.size()>0){
        	for (int j=filaInicial,indexEventos=0; j<(eventos.size()+filaInicial); j++,indexEventos++){
        		//System.out.println("PRIMER EVENTO!");
        		fila=hoja.getRow(j);
        		//for (int k=columnaInicial,recorrerCeldas=1;k<totalDatos; k++){
        		//System.out.println("j+j"+eventos.get(indexEventos).getIdEvento());
        		int recorrerCeldas=1;
        		fila.createCell(recorrerCeldas++).setCellValue(this.getMes(eventos.get(indexEventos)));
        		fila.createCell(recorrerCeldas++).setCellValue(this.getSemanas(eventos.get(indexEventos)));
        		fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getHospital().getNombreHospital());
        		fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getNumHabitacion());
        		fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getHospital().getCiudadHospital());
        		fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getHospital().getMunicipioDelHospital());
        		if (eventos.get(indexEventos).getRegistroSeguroPersonal()!=null){
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getCliente().getNombreCorto());
        		}
        		else{
        			fila.createCell(recorrerCeldas++).setCellValue("");
        		}
        		
        	    CellStyle cellStyle = wb.createCellStyle();
        	    CreationHelper createHelper = wb.getCreationHelper();
        	    short dateFormat = createHelper.createDataFormat().getFormat("dd/MM/yyyy");
        	    cellStyle.setDataFormat(dateFormat);        	    
        		fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getFechaCaptura());
        	    fila.getCell(recorrerCeldas-1).setCellStyle(cellStyle);
        		fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getFechaIngreso());
        		fila.getCell(recorrerCeldas-1).setCellStyle(cellStyle);
        		if (eventos.get(indexEventos).getFechaEgreso()!=null){
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getFechaEgreso());
        			fila.getCell(recorrerCeldas-1).setCellStyle(cellStyle);
        		}
        		else
        			fila.createCell(recorrerCeldas++).setCellValue("");
        		if (eventos.get(indexEventos).getFechaEgresoCapt()!=null){
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getFechaEgresoCapt());
        			fila.getCell(recorrerCeldas-1).setCellStyle(cellStyle);
        		}
        		else
        			fila.createCell(recorrerCeldas++).setCellValue("");
        		//Días estancia hosp        		
        		String dias =this.getDiasEstHosp(eventos.get(indexEventos));        		
        		int diasRep = 0;
        		if (dias!="" && dias != null){
        			if (Integer.parseInt(dias)>0){
        				diasRep = Integer.parseInt(dias);
            			diasRep = diasRep - 0;
        			}
        			
        		}        		
        		fila.createCell(recorrerCeldas++).setCellValue(diasRep);        		        		
        		if (eventos.get(indexEventos).getDiasIncapacidad()!=null)
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getDiasIncapacidad());
        		else 
        			fila.createCell(recorrerCeldas++).setCellValue(0);
        		if (eventos.get(indexEventos).getRegistroSeguroPersonal()!=null){
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getRegistroSeguroPersonal().getNombreTitular() +" "+ eventos.get(indexEventos).getRegistroSeguroPersonal().getAppTitular() +" "+ eventos.get(indexEventos).getRegistroSeguroPersonal().getApmTitular());
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getRegistroSeguroPersonal().getNombrePaciente()+" " + eventos.get(indexEventos).getRegistroSeguroPersonal().getAppPaciente() +" "+ eventos.get(indexEventos).getRegistroSeguroPersonal().getApmPaciente());
        		}
        		else{
            		fila.createCell(recorrerCeldas++).setCellValue("");
            		fila.createCell(recorrerCeldas++).setCellValue("");   				
        		}
        		if (eventos.get(indexEventos).getRegistroSeguroPersonal()!=null){
        			//STATUS PACIENTE
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getTipoEvento().getDescripcion());
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroSeguroPersonal().getSexoPaciente());
       				if (eventos.get(indexEventos).getTipoEvento().getIdTipoEvento()!=4){       					
       					fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroSeguroPersonal().getEdadPaciente());
       					fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroSeguroPersonal().getUnidadEdadPaciente());
       				}
       				else{
       					//fila.createCell(recorrerCeldas++).setCellValue("");
       					fila.createCell(recorrerCeldas++).setCellValue("");
       					fila.createCell(recorrerCeldas++).setCellValue("");
       				}
       				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroSeguroPersonal().getCondicionPaciente());
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroSeguroPersonal().getCenso());
        			if (eventos.get(indexEventos).getRegistroSeguroPersonal().getCapita()!=null)
        				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroSeguroPersonal().getCapita());
        			else
        				fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroSeguroPersonal().getRelacionPaciente());
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroSeguroPersonal().getNumAutorizacion());
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroSeguroPersonal().getNumeroNomina());       				
       			}
       			else{
       				fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");
        		}
        		if (eventos.get(indexEventos).getMedicoTratante()!=null){
        			if (eventos.get(indexEventos).getMedicoTratante().getEspecialidades()!=null){
        				fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getMedicoTratante().getNombre());
        				if (eventos.get(indexEventos).getMedicoTratante().getEspecialidades().size()>0)
        						fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getMedicoTratante().getEspecialidades().get(0).getDescripcion());
            			else
                			fila.createCell(recorrerCeldas++).setCellValue("");
    					String tipo="";
        				if (eventos.get(indexEventos).getMedicoTratante()!=null){
        					if (eventos.get(indexEventos).getMedicoTratante().getTipo()==1)
        						tipo="RED";
        					if (eventos.get(indexEventos).getMedicoTratante().getTipo()==2)
        						tipo="STAFF";
        					if (eventos.get(indexEventos).getMedicoTratante().getTipo()==3)
        						tipo="INTERINO";
        				}
        				fila.createCell(recorrerCeldas++).setCellValue(tipo);
        			}
        			else{
            			fila.createCell(recorrerCeldas++).setCellValue("");
            			fila.createCell(recorrerCeldas++).setCellValue("");
            			fila.createCell(recorrerCeldas++).setCellValue("");        					
        			}
        		}
        		else{
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");
        		}
        		//Obtener interconsultas
    			if (eventos.get(indexEventos).getBitacoras()!=null && eventos.get(indexEventos).getBitacoras().size()>0){
    				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getBitacoras().get(eventos.get(indexEventos).getBitacoras().size()-1).getInterconsulta());
    			}
    			else
    				fila.createCell(recorrerCeldas++).setCellValue("");
        		
        		if (eventos.get(indexEventos).getDiagnosticoIngreso1()!=null){
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getDiagnosticoIngreso1().getDescripcion());
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getDiagnosticoIngreso1().getClave());
        		}
        		else{
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");        				
        		}
        		if (eventos.get(indexEventos).getDiagnosticoIngreso2()!=null){
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getDiagnosticoIngreso2().getDescripcion());
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getDiagnosticoIngreso2().getClave());
        		}
        		else{
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");        				
        		}
        		//
        		if (eventos.get(indexEventos).getDiagnosticoEgreso1()!=null){
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getDiagnosticoEgreso1().getDescripcion());
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getDiagnosticoEgreso1().getClave());
        		}
        		else{
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");        				
        		}
        		if (eventos.get(indexEventos).getProcedimiento1()!=null){
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getProcedimiento1().getDescripcion());
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getProcedimiento1().getCveCpt());
        		}
        		else{
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");        				
        		}
        		if (eventos.get(indexEventos).getProcedimiento2()!=null){
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getProcedimiento2().getDescripcion());
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getProcedimiento2().getCveCpt());
        		}
        		else{
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue("");        				
        		}
        		if (eventos.get(indexEventos).getRegistroSeguroPersonal()!=null && eventos.get(indexEventos).getBitacoras().size()>0){
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getBitacoras().get(eventos.get(indexEventos).getBitacoras().size()-1).getDescripcion());
        		}
        		else{
        			fila.createCell(recorrerCeldas++).setCellValue("");
        		}
        		//Seguimiento diario
        		//Obtener montos diarios
        		float montoAnt=0;
        		float montoAct=0;
        		if (eventos.get(indexEventos).getGastos().size()>0){
        			for (int indx=0;indx<eventos.get(indexEventos).getGastos().size();indx++){
        				montoAct=eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario();
        				if (indx>0)
        					montoAnt=eventos.get(indexEventos).getGastos().get(indx-1).getMontoUnitario();
        			}
        		}
        		fila.createCell(recorrerCeldas++).setCellValue(montoAnt);
    			fila.createCell(recorrerCeldas++).setCellValue(montoAct);	
    			//Obtener bitácoras médicas
    				if (eventos.get(indexEventos).getBitacoras().size()>0){
    	       			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getBitacoras().get(0).getObservaciones());
    				}
    				else
    					fila.createCell(recorrerCeldas++).setCellValue("");
        			//Datos para nacimientos
        			if (eventos.get(indexEventos).getTipoEvento().getIdTipoEvento()==4){
        				if (eventos.get(indexEventos).getRegistroSeguroPersonal()!=null){
        					fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getRegistroSeguroPersonal().getNacimientoTipoParto());
        					fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getRegistroSeguroPersonal().getNacimientoPeso());
        					fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getRegistroSeguroPersonal().getNacimientoTalla());
        					fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getRegistroSeguroPersonal().getNacimientoApgar());
        				}
        				else{
            				fila.createCell(recorrerCeldas++).setCellValue("");
            				fila.createCell(recorrerCeldas++).setCellValue("");
            				fila.createCell(recorrerCeldas++).setCellValue("");
            				fila.createCell(recorrerCeldas++).setCellValue("");        					
        				}
        			}
        			else{
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");
        			}
        			//Desvíos cargos en piso
        			float montoDesvPisoMaterial=0;
        			float montoDesvPisoMedicamentos=0;
        			float montoDesvPisoLab=0;
        			float montoDesvPisoTerapiaResp=0;
        			float montoDesvPisoHabitaciones=0;
        			//Desvíos cargos en quirófano
        			float montoDesvQuirofMaterial=0;
        			float montoDesvQuirofMedicamentos=0;
        			float montoDesvQuirofLab=0;
        			float montoDesvQuirofTerapiaResp=0;
        			float montoDesvQuirofHabitaciones=0;
        			//Desvíos cargos en terapia
        			float montoDesvTerapiaMaterial=0;
        			float montoDesvTerapiaMedicamentos=0;
        			float montoDesvTerapiaLab=0;
        			float montoDesvTerapiaTerapiaResp=0;
        			float montoDesvTerapiaHabitaciones=0;
        			//Desvíos cargos en urgencias
        			float montoDesvUrgencMaterial=0;
        			float montoDesvUrgencMedicamentos=0;
        			float montoDesvUrgencLab=0;
        			float montoDesvUrgencTerapiaResp=0;
        			float montoDesvUrgencHabitaciones=0;
        			float montoDesvGastosPersonales=0;
        			if (eventos.get(indexEventos).getGastos().size()>0){
        				for (int indx=0;indx<eventos.get(indexEventos).getGastos().size();indx++){
        					//Se obtienen sólo los desvíos
        					if (eventos.get(indexEventos).getGastos().get(indx).getIdTipoCargo()==2){
        						//PISO
        						if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==1){
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==2)
        								montoDesvPisoMaterial=montoDesvPisoMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==1)
        								montoDesvPisoMaterial=montoDesvPisoMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==3)
        								montoDesvPisoMaterial=montoDesvUrgencMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==4)
        								montoDesvPisoMaterial=montoDesvUrgencMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==5)
        								montoDesvPisoMaterial=montoDesvPisoMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());       							
        						}
        						//QUIROFANO 
        						if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==2){
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==2)
        								montoDesvQuirofMaterial=montoDesvQuirofMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==1)
        								montoDesvQuirofMaterial=montoDesvQuirofMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==3)
        								montoDesvQuirofMaterial=montoDesvQuirofMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==4)
        								montoDesvQuirofMaterial=montoDesvQuirofMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==5)
        								montoDesvQuirofMaterial=montoDesvQuirofMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());       							
        						}
           						//Terapia 
        						if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==3){
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==2)
        								montoDesvTerapiaMaterial=montoDesvTerapiaMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==1)
        								montoDesvTerapiaMaterial=montoDesvTerapiaMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==3)
        								montoDesvTerapiaMaterial=montoDesvTerapiaMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==4)
        								montoDesvTerapiaMaterial=montoDesvTerapiaMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==5)
        								montoDesvTerapiaMaterial=montoDesvTerapiaMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());       							
        						}
           						//Urgencias
        						if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==4){
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==2)
        								montoDesvUrgencMaterial=montoDesvUrgencMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==1)
        								montoDesvUrgencMaterial=montoDesvUrgencMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==3)
        								montoDesvUrgencMaterial=montoDesvUrgencMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==4)
        								montoDesvUrgencMaterial=montoDesvUrgencMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==5)
        								montoDesvUrgencMaterial=montoDesvUrgencMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());       							
        						}
        						//Gastos Personales
        						if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==5){        							
        							montoDesvGastosPersonales=montoDesvGastosPersonales+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());        							       						
        						}
        					}
        				}
        			}
        			//PISO
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvPisoMaterial);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvPisoMedicamentos);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvPisoLab);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvPisoTerapiaResp);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvPisoHabitaciones);
        			//QUIROFANO
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvQuirofMaterial);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvQuirofMedicamentos);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvQuirofLab);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvQuirofTerapiaResp);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvQuirofHabitaciones);
        			fila.createCell(recorrerCeldas++).setCellValue(0);
        			//TERAPIA
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvTerapiaMaterial);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvTerapiaMedicamentos);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvTerapiaLab);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvTerapiaTerapiaResp);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvTerapiaHabitaciones);
        			fila.createCell(recorrerCeldas++).setCellValue(0);
        			//URGENCIAS
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvUrgencMaterial);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvUrgencMedicamentos);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvUrgencLab);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvUrgencTerapiaResp);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvUrgencHabitaciones);
        			
        			//Jubilados
        			if (eventos.get(indexEventos).getRegistroSeguroPersonal().getCantidadCubiertaConvenio()!=null){
        				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroSeguroPersonal().getCantidadCubiertaConvenio());
        				//fila.createCell(recorrerCeldas++).setCellValue(0);
        				float tmpMDD=0;
        				/*if (eventos.get(indexEventos).getMontoDespuesDesvios()!=null && eventos.get(indexEventos).getMontoDespuesDesvios()!=0) {
    					tmpMDD=eventos.get(indexEventos).getTotalDesvios();
    					}*/
        				if (eventos.get(indexEventos).getMontoFinalFacturacion()!=null && eventos.get(indexEventos).getMontoFinalFacturacion()!=0) {
    						tmpMDD=eventos.get(indexEventos).getMontoFinalFacturacion();
    					}
    					fila.createCell(recorrerCeldas++).setCellValue(""+tmpMDD);
        				//fila.createCell(recorrerCeldas++).setCellValue(""+(tmpMDD-eventos.get(indexEventos).getRegistroSeguroPersonal().getCantidadCubiertaConvenio()));       			 
        			}else{
        				fila.createCell(recorrerCeldas++).setCellValue(0);
           				fila.createCell(recorrerCeldas++).setCellValue(0);
        			}
        			//Obteniendo el total de cargos observados 
        			float cargosObservados=montoDesvPisoMaterial+montoDesvPisoMedicamentos+montoDesvPisoLab+montoDesvPisoTerapiaResp;
        			cargosObservados=cargosObservados+montoDesvPisoHabitaciones+montoDesvQuirofMaterial;
        			cargosObservados=cargosObservados+montoDesvQuirofMedicamentos+montoDesvQuirofLab+montoDesvQuirofTerapiaResp;
        			cargosObservados=cargosObservados+montoDesvQuirofHabitaciones;
        			cargosObservados=cargosObservados+montoDesvTerapiaMaterial+montoDesvTerapiaMedicamentos+montoDesvTerapiaLab;
        			cargosObservados=cargosObservados+montoDesvTerapiaTerapiaResp+montoDesvTerapiaHabitaciones+montoDesvUrgencMaterial;
        			cargosObservados=cargosObservados+montoDesvUrgencMedicamentos+montoDesvUrgencLab+montoDesvUrgencTerapiaResp;
        			cargosObservados=cargosObservados+montoDesvUrgencHabitaciones+montoDesvGastosPersonales;
        			
        			//Montos Finales Evento
        			if (eventos.get(indexEventos).getMontoAntesDesvios()!=null)
        				fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getMontoAntesDesvios());
        			else
        				fila.createCell(recorrerCeldas++).setCellValue(0);
        			float montTmAntD=0;
        			if (eventos.get(indexEventos).getMontoAntesDesvios()!=null){
        				montTmAntD=Float.parseFloat(""+eventos.get(indexEventos).getMontoAntesDesvios());
        				if (eventos.get(indexEventos).getTotalDesvios()!=null && eventos.get(indexEventos).getTotalDesvios()!=0)
        					cargosObservados=Float.parseFloat(""+eventos.get(indexEventos).getTotalDesvios());
        				else
        					cargosObservados=0;
        				fila.createCell(recorrerCeldas++).setCellValue((montTmAntD-cargosObservados));
        			}
        			else
        				fila.createCell(recorrerCeldas++).setCellValue(0);
        			if (eventos.get(indexEventos).getDescuentoHospital()!=null)
        				fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getDescuentoHospital());
        			else
        				fila.createCell(recorrerCeldas++).setCellValue(0);
        			if (eventos.get(indexEventos).getMontoFinalFacturacion()!=null)
        				fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getMontoFinalFacturacion());
        			else
        				fila.createCell(recorrerCeldas++).setCellValue(0);
        			//Diferencia facturación - monto después desvíos
        			if (eventos.get(indexEventos).getMontoFinalFacturacion()!=null){
        				float tmp1=eventos.get(indexEventos).getMontoFinalFacturacion();
        				float tmp2=montTmAntD-cargosObservados;
        				fila.createCell(recorrerCeldas++).setCellValue((tmp1-tmp2));
        			}
        			else
        				fila.createCell(recorrerCeldas++).setCellValue(0);
        			if(eventos.get(indexEventos).getComentariosDiferenciaFacturacion()!=null) 
        				fila.createCell(recorrerCeldas++).setCellValue(""+(eventos.get(indexEventos).getComentariosDiferenciaFacturacion()));
        			else
        				fila.createCell(recorrerCeldas++).setCellValue("");
        			//System.out.println("FACTURAS:"+eventos.get(indexEventos).getFacturas().size());
        			int numFacturas=eventos.get(indexEventos).getFacturas().size();
        			if (numFacturas>0){
        				//num de factura
	        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getFacturas().get(numFacturas-1).getNumeroFactura());
	        			//Aprobada / Rechazada
	    				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getFacturas().get(numFacturas-1).getAprobada());
	    				//Detalle
	    				if (eventos.get(indexEventos).getFacturas().get(numFacturas-1).getDetalle()!=null)
	    					fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getFacturas().get(numFacturas-1).getDetalle());
	    				else
	    					fila.createCell(recorrerCeldas++).setCellValue("");
	       				//Ajuste Factura
	    				if (eventos.get(indexEventos).getFacturas().get(numFacturas-1).getAjusteFactura()!=null)
	    					fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getFacturas().get(numFacturas-1).getAjusteFactura());
	    				else
	    					fila.createCell(recorrerCeldas++).setCellValue("");
	       				//Tipo Comprobante Fiscal Corregido
	    				if (eventos.get(indexEventos).getFacturas().get(numFacturas-1).getTipoComprobanteFiscalCorregido()!=null)
	    					fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getFacturas().get(numFacturas-1).getTipoComprobanteFiscalCorregido());
	       				else
	    					fila.createCell(recorrerCeldas++).setCellValue("");
	       				//Folio Comp
	    				if (eventos.get(indexEventos).getFacturas().get(numFacturas-1).getFolioComprobanteFiscalCorregido()!=null)
	    					fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getFacturas().get(numFacturas-1).getFolioComprobanteFiscalCorregido());
	       				else
	    					fila.createCell(recorrerCeldas++).setCellValue("");
	       				//Monto Comp
	    				if (eventos.get(indexEventos).getFacturas().get(numFacturas-1).getMontoComprobanteFiscalCorregido()!=null)
	    					fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getFacturas().get(numFacturas-1).getMontoComprobanteFiscalCorregido());
	       				else
	    					fila.createCell(recorrerCeldas++).setCellValue("");
        			}
        			else{
	        			//num de factura
	        			fila.createCell(recorrerCeldas++).setCellValue("");
	        			//Aprobada / Rechazada
	    				fila.createCell(recorrerCeldas++).setCellValue("");
	    				//Detalle
	       				fila.createCell(recorrerCeldas++).setCellValue("");
	       				//Ajuste Factura
	       				fila.createCell(recorrerCeldas++).setCellValue("");
	       				//Tipo Comprobante Fiscal Corregido
	       				fila.createCell(recorrerCeldas++).setCellValue("");
	       				//Folio Comp
	       				fila.createCell(recorrerCeldas++).setCellValue("");
	       				//Monto Comp
	       				fila.createCell(recorrerCeldas++).setCellValue("");
        			}
        			if (eventos.get(indexEventos).getDescuentoNoAplicado()!=null)
        				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getDescuentoNoAplicado());
        			else
        				fila.createCell(recorrerCeldas++).setCellValue("");
        			//fila.createCell(recorrerCeldas++).setCellValue(""+montoDesvGastosPersonales);
        			fila.createCell(recorrerCeldas++).setCellValue(0);
        			fila.createCell(recorrerCeldas++).setCellValue(""+cargosObservados);
        			// TOTALES DESGLOCE GASTOS RELEVANTES
        			float desgGastRelevTerapia=0;
        			float desgGastRelevMedicamentos=0;
        			float desgGastRelevMateriales=0;
        			float desgGastRelevBancoSang=0;
        			float desgGastRelevQuirofano=0;
        			float desgGastRelevOtros=0;        			
        			if (eventos.get(indexEventos).getGastos().size()>0){
        				for (int indx=0;indx<eventos.get(indexEventos).getGastos().size();indx++){
        					//Se obtienen sólo los desvíos
        					if (eventos.get(indexEventos).getGastos().get(indx).getIdTipoCargo()==3){
        						if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==4)
        							desgGastRelevTerapia=desgGastRelevTerapia+ eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario();
    							if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==1)
    								desgGastRelevMedicamentos=desgGastRelevMedicamentos+ eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario();
    							if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==2)
    								desgGastRelevMateriales=desgGastRelevMateriales+ eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario();
    							if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==11)
    								desgGastRelevBancoSang=desgGastRelevBancoSang+ eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario();
    							if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()!=4 &&
    									eventos.get(indexEventos).getGastos().get(indx).getIdArea()!=11 &&
    									eventos.get(indexEventos).getGastos().get(indx).getIdArea()!=1 &&
    									eventos.get(indexEventos).getGastos().get(indx).getIdArea()!=2)
    								desgGastRelevOtros=desgGastRelevOtros+ eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario();
        					}
        				}
        			}
        			fila.createCell(recorrerCeldas++).setCellValue(desgGastRelevTerapia);
        			fila.createCell(recorrerCeldas++).setCellValue(desgGastRelevMedicamentos);
        			fila.createCell(recorrerCeldas++).setCellValue(desgGastRelevMateriales);
        			fila.createCell(recorrerCeldas++).setCellValue(desgGastRelevBancoSang);
        			fila.createCell(recorrerCeldas++).setCellValue(desgGastRelevQuirofano);
        			fila.createCell(recorrerCeldas++).setCellValue(desgGastRelevOtros);
        			
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			//Def
        			if (eventos.get(indexEventos).getHoraDef()!=null){
        				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getHoraDef());
        				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getCausaDirectaDef());
        			}
        			else{
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");
        			}
        			//Nom Recien Nacido
        			if (eventos.get(indexEventos).getTipoEvento().getIdTipoEvento()==4){
        				fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getRegistroSeguroPersonal().getAppPaciente()+" "+eventos.get(indexEventos).getRegistroSeguroPersonal().getApmPaciente() +" " +eventos.get(indexEventos).getRegistroSeguroPersonal().getNombrePaciente());
        			}
        			else{
        				fila.createCell(recorrerCeldas++).setCellValue("");
        			}
        			//Gastos Evitados
        			float gastosEvitados=0;
        			if (eventos.get(indexEventos).getGastos()!=null){        				
        				for (int i=0;i<eventos.get(indexEventos).getGastos().size();i++){
        					if (eventos.get(indexEventos).getGastos().get(i).getIdTipoCargo()==4)
        						gastosEvitados+=eventos.get(indexEventos).getGastos().get(i).getMontoUnitario();
        				}
        			}
        			fila.createCell(recorrerCeldas++).setCellValue(gastosEvitados);
        		}
        	}
        	//}  	 	
            file.close();
            //FileOutputStream outFile =new FileOutputStream(new File("/home/update.xlsx"));
            FileOutputStream outFile =new FileOutputStream(new File(rutaArchivos+"update.xlsx"));
            
            wb.write(outFile);
            outFile.flush();
            outFile.close();
            archivoXLS = new File(rutaArchivos+"update.xlsx");
            //archivoXLS = new File("/home/update.xlsx");
            
		} 
        catch (IOException e) {
			// TODO Auto-generated catch block
        	System.out.println("<OTIKA>ERROR!"+e.getMessage());
			e.printStackTrace();
		}
        /*Cerramos el flujo de datos*/        
        return archivoXLS;
	}
	
	private String getDias(Evento evento) {
		// TODO Auto-generated method stub
		return "";
	}

	private String getDiasEstHosp(Evento evento) {
		// TODO Auto-generated method stub
		Date fecha1=evento.getFechaIngreso();
		Date fecha2=evento.getFechaEgreso();
		return getDifDias(fecha1,fecha2);
	}
	
	private Integer getMes(Evento evento){
		int mes=0; 
		Calendar date1 = Calendar.getInstance();
		//System.out.println("FECHA"+evento.getFechaIngreso());
		date1.setTime(evento.getFechaIngreso());
		int year = date1.get(Calendar.YEAR);
	    int month = date1.get(Calendar.MONTH);
	    int day = date1.get(Calendar.DAY_OF_MONTH);		
		return (month+1); 
		
	}
	
	private Integer getSemanas(Evento evento){
		Calendar date1 = Calendar.getInstance();
		date1.setTime(evento.getFechaIngreso());
		int year = date1.get(Calendar.YEAR);
	    int month = date1.get(Calendar.MONTH);
	    int day = date1.get(Calendar.DAY_OF_MONTH);
	    int weak = date1.get(Calendar.WEEK_OF_MONTH);
		return (weak);
	}
	
	public String getDifDias(Date fecha1, Date fecha2){
		//System.out.println("Fecha 1"+fecha1);
		//System.out.println("Fecha 2"+fecha2);
		if (fecha2==null){
			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH); // Note: zero based!
			int day = now.get(Calendar.DAY_OF_MONTH);			
		}
		Calendar c = Calendar.getInstance();
		Calendar fechaInicio = new GregorianCalendar();		 
		fechaInicio.setTime(fecha1);
		Calendar fechaFin = new GregorianCalendar();
		if (fecha2!=null){
			fechaFin.setTime(fecha2);
		}		
		if ( fechaInicio.before(fechaFin) ){
		
		}
		else{
		     if ( fechaFin.before(fechaInicio) ){		     
		     }else{
		      return "0";
		     } 
		}
		c.setTimeInMillis(fechaFin.getTime().getTime() - fechaInicio.getTime().getTime());
		//System.out.println("DIFERENCIA OBTENIDA"+c.get(Calendar.DAY_OF_YEAR));
		if (fecha2==null)
			return ""+((c.get(Calendar.DAY_OF_YEAR))-1);
		return ""+c.get(Calendar.DAY_OF_YEAR);
	}
	
	@Override
	public File generarLayoutAseguradora(int id1, String id2, String fechaInicio, String fechaFin) {
		/*La ruta donde se creará el archivo*/
        //String rutaArchivo = System.getProperty("user.home")+"/ejemploExcelJava.xls";
		String rutaArchivo = rutaArchivos+"LAYOUT BITACORA ASEGURADORAS.xlsx";
        /*Se crea el objeto de tipo File con la ruta del archivo*/
		File archivoXLS = new File(rutaArchivo); 
		Evento eventoTmp=new Evento();
		if (fechaInicio!="")
			eventoTmp.setFechaIngreso(new Date(fechaInicio));
		if (fechaFin!="")
			eventoTmp.setFechaEgreso(new Date(fechaFin));
		//System.out.println("Los datos que se buscan:"+ eventoTmp.getFechaIngreso()+"->"+eventoTmp.getFechaEgreso());
		//Verificar si el evento es finalizado
		List<Evento> eventos = new ArrayList<Evento>();
		//Para eventos finalizados
		if (id2.equals("true"))
			eventoTmp.setStatusEvento(3);
		else
			eventoTmp.setStatusEvento(1);		
		eventos = eventoDao.obtenerEventosAseguradoraByParams(eventoTmp);
		//System.out.println("EVENTOS ENCONTRADOS:");
		//System.out.println(eventos.size());
        /*Se crea el archivo*/
        int filaInicial=9;
        int columnaInicial=1;
        int totalDatos=97;
        try{
        	FileInputStream file = new FileInputStream(archivoXLS);
        	HSSFWorkbook workbook = new HSSFWorkbook(file);
        	HSSFSheet hoja = workbook.getSheetAt(0);
        	//Total eventos 
        	Row fila = hoja.getRow(3);     	  
        	fila.createCell(2).setCellValue(eventos.size());
        	//Se guardarán todos los eventos
        	for (int j=filaInicial,indexEventos=0; j<(eventos.size()+filaInicial); j++,indexEventos++){
        		fila=hoja.getRow(j);
        		for (int k=columnaInicial,recorrerCeldas=1;k<totalDatos; k++){
        			recorrerCeldas=1;
        			fila.createCell(recorrerCeldas++).setCellValue(this.getMes(eventos.get(indexEventos)));
        			fila.createCell(recorrerCeldas++).setCellValue(this.getSemanas(eventos.get(indexEventos)));
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getHospital().getNombreHospital());
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getNumHabitacion());
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getHospital().getEstadoHospital());
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getHospital().getCiudadHospital());
        			if (eventos.get(indexEventos).getRegistroGastosMayores()!=null){
        				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroGastosMayores().getNumeroPoliza());
        			}
        			else{
        				fila.createCell(recorrerCeldas++).setCellValue("");
        			}
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getFechaCaptura());
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getFechaIngreso());
        			if (eventos.get(indexEventos).getFechaEgreso()!=null)
        				fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getFechaEgreso());
        			else
        				fila.createCell(recorrerCeldas++).setCellValue("");
        			if (eventos.get(indexEventos).getFechaEgresoCapt()!=null)
        				fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getFechaEgresoCapt());
        			else
        				fila.createCell(recorrerCeldas++).setCellValue("");
        			//Días estancia hosp
        			fila.createCell(recorrerCeldas++).setCellValue(this.getDias(eventos.get(indexEventos)));
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getDiasIncapacidad());
        			if (eventos.get(indexEventos).getRegistroGastosMayores()!=null){
        				fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getRegistroGastosMayores().getNombreTitular() + eventos.get(indexEventos).getRegistroGastosMayores().getAppTitular() + eventos.get(indexEventos).getRegistroGastosMayores().getApmTitular());
        				fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getRegistroGastosMayores().getNombrePaciente() + eventos.get(indexEventos).getRegistroGastosMayores().getAppPaciente() + eventos.get(indexEventos).getRegistroGastosMayores().getApmPaciente());
        			}
        			else{
            			fila.createCell(recorrerCeldas++).setCellValue("");
            			fila.createCell(recorrerCeldas++).setCellValue("");   				
        			}
        			//STATUS PACIENTE
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			if (eventos.get(indexEventos).getRegistroGastosMayores()!=null){
        				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroGastosMayores().getSexoPaciente());
        				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroGastosMayores().getEdadPaciente());
        				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroGastosMayores().getUnidadEdadPaciente());
        				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroGastosMayores().getRelacionPaciente());
        				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroGastosMayores().getCondicionPaciente());
        				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroGastosMayores().getDeduciblePoliza());
        				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroGastosMayores().getCoaseguroMedico());
        				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroGastosMayores().getSumaAsegurada());
        				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getRegistroGastosMayores().getTablaHonorariosMedicos());
        				
        			}
        			else{
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");
        			}
        			if (eventos.get(indexEventos).getMedicoTratante()!=null){
        				if (eventos.get(indexEventos).getMedicoTratante().getEspecialidades()!=null){
        					fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getMedicoTratante().getNombre());
        					if (eventos.get(indexEventos).getMedicoTratante().getEspecialidades().size()>0)
        							fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getMedicoTratante().getEspecialidades().get(0).getDescripcion());
            				else
                				fila.createCell(recorrerCeldas++).setCellValue("");
        					
        					fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getMedicoTratante().getTipo());
        				}
        				else{
            				fila.createCell(recorrerCeldas++).setCellValue("");
            				fila.createCell(recorrerCeldas++).setCellValue("");
            				fila.createCell(recorrerCeldas++).setCellValue("");        					
        				}
        			}
        			else{
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");
        			}
        			//Obtener interconsultas
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			if (eventos.get(indexEventos).getDiagnosticoIngreso1()!=null){
        				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getDiagnosticoIngreso1().getDescripcion());
        				fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getDiagnosticoIngreso1().getClave());
        			}
        			else{
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");        				
        			}
        			if (eventos.get(indexEventos).getDiagnosticoIngreso2()!=null){
        				fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getDiagnosticoIngreso2().getDescripcion());
        				fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getDiagnosticoIngreso2().getClave());
        			}
        			else{
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");        				
        			}
        			if (eventos.get(indexEventos).getDiagnosticoEgreso1()!=null){
        				fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getDiagnosticoEgreso1().getDescripcion());
        				fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getDiagnosticoEgreso1().getClave());
        			}else{
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");        				
        			} 
        			if (eventos.get(indexEventos).getProcedimiento1()!=null){
        				fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getProcedimiento1().getDescripcion());
        				fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getProcedimiento1().getCveCpt());
        			}else{
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");        				
        			}
        			if (eventos.get(indexEventos).getProcedimiento2()!=null){
        				fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getProcedimiento2().getDescripcion());
        				fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getProcedimiento2().getCveCpt());
        			}else{
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");        				
        			}
        			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getTipoEvento().getDescripcion());
        			//Seguimiento diario
        			//Obtener montos diarios
        			float montoAnt=0;
        			float montoAct=0;
        			if (eventos.get(indexEventos).getGastos().size()>0){
        				for (int indx=0;indx<eventos.get(indexEventos).getGastos().size();indx++){
        					montoAct=eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario();
        					if (indx>0)
        						montoAnt=eventos.get(indexEventos).getGastos().get(indx-1).getMontoUnitario();
        				}
        			}
        			fila.createCell(recorrerCeldas++).setCellValue(montoAnt);
    				fila.createCell(recorrerCeldas++).setCellValue(montoAct);	
    				//Obtener bitácoras médicas
    				if (eventos.get(indexEventos).getBitacoras().size()>0){
    	       			fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getBitacoras().get(0).getObservaciones());
    				}   			
        			//Datos para nacimientos
        			if (eventos.get(indexEventos).getTipoEvento().getIdTipoEvento()==4){
        				if (eventos.get(indexEventos).getRegistroGastosMayores()!=null){
        					fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getRegistroGastosMayores().getNacimientoTipoParto());
        					fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getRegistroGastosMayores().getNacimientoPeso());
        					fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getRegistroGastosMayores().getNacimientoTalla());
        					fila.createCell(recorrerCeldas++).setCellValue(eventos.get(indexEventos).getRegistroGastosMayores().getNacimientoApgar());
        				}
        				else{
            				fila.createCell(recorrerCeldas++).setCellValue("");
            				fila.createCell(recorrerCeldas++).setCellValue("");
            				fila.createCell(recorrerCeldas++).setCellValue("");
            				fila.createCell(recorrerCeldas++).setCellValue("");        					
        				}
        			}
        			else{
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");
        				fila.createCell(recorrerCeldas++).setCellValue("");
        			}
        			//Desvíos cargos en piso
        			float montoDesvPisoMaterial=0;
        			float montoDesvPisoMedicamentos=0;
        			float montoDesvPisoLab=0;
        			float montoDesvPisoTerapiaResp=0;
        			float montoDesvPisoHabitaciones=0;
        			//Desvíos cargos en quirófano
        			float montoDesvQuirofMaterial=0;
        			float montoDesvQuirofMedicamentos=0;
        			float montoDesvQuirofLab=0;
        			float montoDesvQuirofTerapiaResp=0;
        			float montoDesvQuirofHabitaciones=0;
        			//Desvíos cargos en terapia
        			float montoDesvTerapiaMaterial=0;
        			float montoDesvTerapiaMedicamentos=0;
        			float montoDesvTerapiaLab=0;
        			float montoDesvTerapiaTerapiaResp=0;
        			float montoDesvTerapiaHabitaciones=0;
        			//Desvíos cargos en urgencias
        			float montoDesvUrgencMaterial=0;
        			float montoDesvUrgencMedicamentos=0;
        			float montoDesvUrgencLab=0;
        			float montoDesvUrgencTerapiaResp=0;
        			float montoDesvUrgencHabitaciones=0;
        			if (eventos.get(indexEventos).getGastos().size()>0){
        				for (int indx=0;indx<eventos.get(indexEventos).getGastos().size();indx++){
        					//Se obtienen sólo los desvíos
        					if (eventos.get(indexEventos).getGastos().get(indx).getIdTipoCargo()==2){
        						//PISO
        						if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==1){
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==2)
        								montoDesvPisoMaterial=montoDesvPisoMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==1)
        								montoDesvPisoMaterial=montoDesvPisoMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==3)
        								montoDesvPisoMaterial=montoDesvUrgencMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==4)
        								montoDesvPisoMaterial=montoDesvUrgencMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==5)
        								montoDesvPisoMaterial=montoDesvPisoMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());       							
        						}
        						//QUIROFANO 
        						if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==2){
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==2)
        								montoDesvQuirofMaterial=montoDesvQuirofMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==1)
        								montoDesvQuirofMaterial=montoDesvQuirofMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==3)
        								montoDesvQuirofMaterial=montoDesvQuirofMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==4)
        								montoDesvQuirofMaterial=montoDesvQuirofMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==5)
        								montoDesvQuirofMaterial=montoDesvQuirofMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());       							
        						}
           						//Terapia 
        						if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==3){
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==2)
        								montoDesvTerapiaMaterial=montoDesvTerapiaMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==1)
        								montoDesvTerapiaMaterial=montoDesvTerapiaMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==3)
        								montoDesvTerapiaMaterial=montoDesvTerapiaMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==4)
        								montoDesvTerapiaMaterial=montoDesvTerapiaMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==5)
        								montoDesvTerapiaMaterial=montoDesvTerapiaMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());       							
        						}
           						//Urgencias
        						if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==4){
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==2)
        								montoDesvUrgencMaterial=montoDesvUrgencMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==1)
        								montoDesvUrgencMaterial=montoDesvUrgencMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==3)
        								montoDesvUrgencMaterial=montoDesvUrgencMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==4)
        								montoDesvUrgencMaterial=montoDesvUrgencMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());
        							if (eventos.get(indexEventos).getGastos().get(indx).getIdRubro()==5)
        								montoDesvUrgencMaterial=montoDesvUrgencMaterial+(eventos.get(indexEventos).getGastos().get(indx).getCantidad()*eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario());       							
        						}     						    						
        					}
        				}
        			}
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvPisoMaterial);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvPisoMedicamentos);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvPisoLab);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvPisoTerapiaResp);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvPisoHabitaciones);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvQuirofMaterial);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvQuirofMedicamentos);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvQuirofLab);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvQuirofTerapiaResp);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvQuirofHabitaciones);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvTerapiaMaterial);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvTerapiaMedicamentos);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvTerapiaLab);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvTerapiaTerapiaResp);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvTerapiaHabitaciones);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvUrgencMaterial);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvUrgencMedicamentos);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvUrgencLab);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvUrgencTerapiaResp);
        			fila.createCell(recorrerCeldas++).setCellValue(montoDesvUrgencHabitaciones);
        			
        			//Montos Finales Evento
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getMontoAntesDesvios());
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getMontoDespuesDesvios());
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getDescuentoHospital());
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getMontoFinalFacturacion());
        			//Diferencia facturación - monto después desvíos
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			fila.createCell(recorrerCeldas++).setCellValue(""+(eventos.get(indexEventos).getComentariosDiferenciaFacturacion()));
        			//num de factura
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			//Aprobada / Rechazada
    				fila.createCell(recorrerCeldas++).setCellValue("");
    				//Detalle
       				fila.createCell(recorrerCeldas++).setCellValue("");
       				//Ajuste Factura
       				fila.createCell(recorrerCeldas++).setCellValue("");
       				//Tipo Comprobante Fiscal Corregido
       				fila.createCell(recorrerCeldas++).setCellValue("");
       				//Folio Comp
       				fila.createCell(recorrerCeldas++).setCellValue("");
       				//Monto Comp
       				fila.createCell(recorrerCeldas++).setCellValue("");
       				
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getDescuentoNoAplicado());
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getCargosPersonales());
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getComentariosDiferenciaFacturacion());
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getTotalDesviosComprobados());
        			// TOTALES DESGLOCE GASTOS RELEVANTES
        			float desgGastRelevTerapia=0;
        			float desgGastRelevMedicamentos=0;
        			float desgGastRelevMateriales=0;
        			float desgGastRelevBancoSang=0;
        			float desgGastRelevQuirofano=0;
        			float desgGastRelevOtros=0;        			
        			if (eventos.get(indexEventos).getGastos().size()>0){
        				for (int indx=0;indx<eventos.get(indexEventos).getGastos().size();indx++){
        					//Se obtienen sólo los desvíos
        					if (eventos.get(indexEventos).getGastos().get(indx).getIdTipoCargo()==3){
        						if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==4)
        							desgGastRelevTerapia=desgGastRelevTerapia+ eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario();
    							if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==1)
    								desgGastRelevMedicamentos=desgGastRelevMedicamentos+ eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario();
    							if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==2)
    								desgGastRelevMateriales=desgGastRelevMateriales+ eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario();
    							if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()==11)
    								desgGastRelevBancoSang=desgGastRelevBancoSang+ eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario();
    							if (eventos.get(indexEventos).getGastos().get(indx).getIdArea()!=4 &&
    								eventos.get(indexEventos).getGastos().get(indx).getIdArea()!=11 &&
    								eventos.get(indexEventos).getGastos().get(indx).getIdArea()!=1 &&
    								eventos.get(indexEventos).getGastos().get(indx).getIdArea()!=2)
    								desgGastRelevOtros=desgGastRelevOtros+ eventos.get(indexEventos).getGastos().get(indx).getMontoUnitario();
        					}
        				}
        			}
        			fila.createCell(recorrerCeldas++).setCellValue(desgGastRelevTerapia);
        			fila.createCell(recorrerCeldas++).setCellValue(desgGastRelevMedicamentos);
        			fila.createCell(recorrerCeldas++).setCellValue(desgGastRelevMateriales);
        			fila.createCell(recorrerCeldas++).setCellValue(desgGastRelevBancoSang);
        			fila.createCell(recorrerCeldas++).setCellValue(desgGastRelevQuirofano);
        			fila.createCell(recorrerCeldas++).setCellValue(desgGastRelevOtros);
        			
        			fila.createCell(recorrerCeldas++).setCellValue("");
        			//Def
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getHoraDef());
        			fila.createCell(recorrerCeldas++).setCellValue(""+eventos.get(indexEventos).getComentariosDiferenciaFacturacion());

        		}
        	}  	 	
            file.close();
            FileOutputStream outFile =new FileOutputStream(new File(rutaArchivos+"update.xls"));
            workbook.write(outFile);
            workbook.close();
            outFile.close();
            workbook.close();
            archivoXLS = new File(rutaArchivos+"update.xlsx"); 	
         	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//System.out.println("<OTIKA>ERROR!"+e.getMessage());
			e.printStackTrace();
		}           
        return archivoXLS;
	}
	

}
