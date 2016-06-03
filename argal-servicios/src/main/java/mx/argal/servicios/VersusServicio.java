package mx.argal.servicios;

import java.util.List;

import mx.argal.modelo.Cliente;
import mx.argal.modelo.Gasto;
import mx.argal.modelo.ListaPrecios;

//import mx.sep.sajja.datos.vo.FiltroBusquedaVO;
 
public interface VersusServicio {
	List doVersus(Gasto gasto, String idListaPrecios);
}
