package mx.argal.dao;

import java.util.List;

import mx.argal.modelo.Cliente;
import mx.argal.modelo.Implant;

public interface ClienteDao {
	
	public List<Cliente> obtenerClientes();
	public Cliente obtenerClienteByNick(Cliente cliente);
	public List<Cliente> obtenerClientesActivos();
	void agregarCliente(Cliente Cliente);
	public void actualizarCliente(Cliente Cliente);
	public boolean eliminarCliente(Cliente Cliente);
	//Borrar
	public Cliente obtenerClienteByIdCliente(Cliente cliente);
}
