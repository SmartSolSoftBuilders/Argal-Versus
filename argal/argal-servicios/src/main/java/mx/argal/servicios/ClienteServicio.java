package mx.argal.servicios;

import java.util.List;
import mx.argal.modelo.Cliente;
import mx.argal.modelo.Hospital;

//import mx.sep.sajja.datos.vo.FiltroBusquedaVO;
 
public interface ClienteServicio {
	List<Cliente> obtenerClientes();
	List<Cliente> obtenerClientesActivos();
	Cliente obtenerClienteByNick(Cliente cliente);
	boolean agregarCliente(Cliente cliente);
	boolean actualizarCliente(Cliente cliente);
	boolean eliminarCliente(Cliente cliente);
	Cliente obtenerClienteByIdCliente(Cliente cliente);
}
