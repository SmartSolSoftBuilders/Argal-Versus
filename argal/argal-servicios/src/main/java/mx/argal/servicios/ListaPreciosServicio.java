package mx.argal.servicios;

import java.util.List;

import mx.argal.modelo.Cliente;
import mx.argal.modelo.ListaPrecios;

//import mx.sep.sajja.datos.vo.FiltroBusquedaVO;
 
public interface ListaPreciosServicio {
	List<ListaPrecios> obtenerListasPrecios();
	List<ListaPrecios> obtenerListasPreciosByParams(ListaPrecios listaPrecios);
	int agregarListaPrecios(ListaPrecios listaPrecios);
    int agregarFileLista(ListaPrecios listaPrecios);
	ListaPrecios obtenerListasPreciosById(ListaPrecios listaPrecios);
	int eliminarListaPrecios(ListaPrecios listaPrecios);	
	int obtenerListasPreciosByIdHospIdCliente(ListaPrecios listaPrecios);
}
