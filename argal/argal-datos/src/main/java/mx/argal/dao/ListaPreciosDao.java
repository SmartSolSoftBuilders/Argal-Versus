package mx.argal.dao;

import java.util.List;

import mx.argal.modelo.ListaPrecios;


public interface ListaPreciosDao {
	
	public List<ListaPrecios> obtenerListasPrecios();
	public List<ListaPrecios> obtenerListasPreciosByParams(ListaPrecios listaPrecios);
	public int agregarListaPrecios(ListaPrecios listaPrecios);
    public int agregarFileLista(ListaPrecios listaPrecios);
	public ListaPrecios obtenerListasPreciosById(ListaPrecios listaPrecios);
	public int eliminarListaPrecios(ListaPrecios listaPrecios);
	public int obtenerListasPreciosByIdHospIdCliente(ListaPrecios listaPrecios);
    
}
