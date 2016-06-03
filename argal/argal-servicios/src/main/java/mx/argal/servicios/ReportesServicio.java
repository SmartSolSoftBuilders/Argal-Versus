package mx.argal.servicios;

import java.io.File;
import java.util.List;

import mx.argal.modelo.Implant;
import mx.argal.modelo.Usuario;
//import mx.sep.sajja.datos.vo.FiltroBusquedaVO;
 
public interface ReportesServicio {
	File generarLayoutBanco(int id1, String id2, String fechaInicio, String fechaFin);
	File generarLayoutAseguradora(int id1, String id2, String fechaInicio, String fechaFin);
	File generarLayoutDiarioBanco(int id1, String id2, String fechaInicio,
			String fechaFin) throws Exception;
}
