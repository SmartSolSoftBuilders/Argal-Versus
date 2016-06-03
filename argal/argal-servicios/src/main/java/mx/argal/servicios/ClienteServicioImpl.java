package mx.argal.servicios;

import java.util.List;

import mx.argal.dao.ClienteDao;
import mx.argal.modelo.Cliente;
import mx.argal.modelo.Implant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import mx.sep.sajja.datos.vo.FiltroBusquedaVO;


@Service
public class ClienteServicioImpl implements ClienteServicio {

	@Autowired
	private ClienteDao clienteDao;
	
	@Override
	public List<Cliente> obtenerClientes() {
		// TODO Auto-generated method stub
		return this.clienteDao.obtenerClientes();
	}

	@Override
	public List<Cliente> obtenerClientesActivos() {
		// TODO Auto-generated method stub
		return this.clienteDao.obtenerClientesActivos();
	}

	@Override
	public boolean agregarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		try{
			cliente = this.changeToUpperCase(cliente);
			cliente.setActivoCliente(true);
			clienteDao.agregarCliente(cliente);
			return true;
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("<OTIKA>Error!"+e.getMessage());
			return false;
		}
		
	}
	
	@Override
	public Cliente obtenerClienteByNick(Cliente cliente) {
		// TODO Auto-generated method stub
		try{			
			return clienteDao.obtenerClienteByNick(cliente);			
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("<OTIKA>Error!"+e.getMessage());
			return null;
		}
		
	}
	
	@Override
	public Cliente obtenerClienteByIdCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		try{			
			return clienteDao.obtenerClienteByIdCliente(cliente);			
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("<OTIKA>Error!"+e.getMessage());
			return null;
		}
		
	}

	private Cliente changeToUpperCase(Cliente cliente) {
		// TODO Auto-generated method stub
		cliente.setNombreCliente(cliente.getNombreCliente().toUpperCase());				
		return cliente;
	}

	@Override
	public boolean eliminarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		try{
			cliente.setActivoCliente(false);
			this.clienteDao.eliminarCliente(cliente);
			return true;
		}
		catch(Exception e){
			return false;		
		}
	}
	
	public boolean actualizarCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		try{
			cliente = this.changeToUpperCase(cliente);
			cliente.setActivoCliente(true);
			clienteDao.actualizarCliente(cliente);
			return true;
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("<OTIKA>Error!"+e.getMessage());
			return false;
		}
		
	}
}
