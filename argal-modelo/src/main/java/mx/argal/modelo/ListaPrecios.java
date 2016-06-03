package mx.argal.modelo;

import java.sql.Blob;
import java.util.Date;

public class ListaPrecios extends BaseModelo {

	private Integer idListaPrecios;
	private Hospital hospital;
	private Cliente cliente;
	private boolean jubilados;
	private String rutaLista;
	private byte[]  archivo;	
	
	public String getRutaLista() {
		return rutaLista;
	}
	public void setRutaLista(String rutaLista) {
		this.rutaLista = rutaLista;
	}
	public boolean getJubilados() {
		return this.jubilados;
	}	
	public void setJubilados(boolean jubilados) {
		this.jubilados = jubilados;
	}
	public Integer getIdListaPrecios() {
		return idListaPrecios;
	}
	public void setIdListaPrecios(Integer idListaPrecios) {
		this.idListaPrecios = idListaPrecios;
	}
	public Hospital getHospital() {
		return hospital;
	}
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public byte[]  getArchivo() {
		return archivo;
	}
	public void setArchivo(byte[]  archivo) {
		this.archivo = archivo;
	}
	
	public ListaPrecios(){
		
	}
	
	public ListaPrecios(Integer idListaPrecios, Hospital hospital,
			Cliente cliente, byte[] archivo,boolean jubilados,String rutaLista) {
		super();
		this.idListaPrecios = idListaPrecios;
		this.hospital = hospital;
		this.cliente = cliente;
		this.archivo = archivo;
		this.jubilados = jubilados;
		this.rutaLista = rutaLista;
	}
		
}
