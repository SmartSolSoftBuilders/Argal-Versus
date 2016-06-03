package mx.argal.servicios;

import java.util.ArrayList;
import java.util.List;

import mx.argal.dao.ListaPreciosDao;
import mx.argal.modelo.ListaPrecios;
import mx.argal.modelo.Implant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import mx.sep.sajja.datos.vo.FiltroBusquedaVO;


@Service
public class ListaPreciosServicioImpl implements ListaPreciosServicio {

	@Autowired
	private ListaPreciosDao listaPreciosDao;
	
	@Autowired
	private ListaPreciosMongoServicio listaPreciosMongoServicio;
	
	@Override
	public List<ListaPrecios> obtenerListasPrecios() {
		// TODO Auto-generated method stub
		return this.listaPreciosDao.obtenerListasPrecios();
	}

	@Override
	public List<ListaPrecios> obtenerListasPreciosByParams(ListaPrecios listaPrecios) {
		// TODO Auto-generated method stub
		try{
			System.out.println(listaPrecios.getHospital().getIdHospital());
			System.out.println(listaPrecios.getCliente().getIdCliente());
			return this.listaPreciosDao.obtenerListasPreciosByParams(listaPrecios);
		}
		catch(Exception e){
			System.out.println("ERROR");
			e.printStackTrace();
		}
		return new ArrayList<ListaPrecios>();
	}

	@Override
	public int agregarListaPrecios(ListaPrecios listaPrecios) {
		// TODO Auto-generated method stub
		return this.listaPreciosDao.agregarListaPrecios(listaPrecios);
	}
	
	@Override
	public int eliminarListaPrecios(ListaPrecios listaPrecios) {
		// TODO Auto-generated method stub
		int resp=this.listaPreciosDao.eliminarListaPrecios(listaPrecios);;
		this.listaPreciosMongoServicio.eliminarDocumento(listaPrecios);
		return resp;
	}

	@Override
	public int agregarFileLista(ListaPrecios listaPrecios) {
		// TODO Auto-generated method stub
		 int resp=this.listaPreciosDao.agregarFileLista(listaPrecios);
		 listaPreciosMongoServicio.guardarDocumento(listaPrecios);
		return resp;
	}

	@Override
	public ListaPrecios obtenerListasPreciosById(ListaPrecios listaPrecios) {
		// TODO Auto-generated method stub
		return this.listaPreciosDao.obtenerListasPreciosById(listaPrecios);
	}

	@Override
	public int obtenerListasPreciosByIdHospIdCliente(ListaPrecios listaPrecios) {
		// TODO Auto-generated method stub
		return this.listaPreciosDao.obtenerListasPreciosByIdHospIdCliente(listaPrecios);
	}

}
