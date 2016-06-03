package mx.argal.servicios;

import java.util.HashMap;
import java.util.List;

import mx.argal.modelo.ListaPrecios;

public interface ListaPreciosMongoServicio {

	public void testMongo();
	public List<String> guardarDocumento(ListaPrecios listaPrecios);
	public void eliminarDocumento(ListaPrecios listaPrecios);			
	List obtenerRenglonMongo(String codigo, String fecha, String producto,
			String costoEdoCta, double cantidad, double descuento,
			String idListaPrecios, int renglon);
	Double obtenerDescuentoMongo(String idListaPrecios);

}
