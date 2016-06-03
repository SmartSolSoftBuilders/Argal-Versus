package mx.argal.modelo;

import java.util.Date;

public class Factura extends BaseModelo {
	
	private Integer idFactura;	
	private String numeroFactura;
	private String aprobada;
	private String detalle;
	private String observaciones;
	private String rutaFactura;
	private Float monto;
	private Float ajusteFactura;
	private String tipoComprobanteFiscalCorregido;
	private Float montoComprobanteFiscalCorregido;
	private String folioComprobanteFiscalCorregido;
	private byte[] archivo;

	
	public byte[] getArchivo() {
		return archivo;
	}
	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}
	public Float getAjusteFactura() {
		return ajusteFactura;
	}
	public void setAjusteFactura(Float ajusteFactura) {
		this.ajusteFactura = ajusteFactura;
	}
	public String getTipoComprobanteFiscalCorregido() {
		return tipoComprobanteFiscalCorregido;
	}
	public void setTipoComprobanteFiscalCorregido(
			String tipoComprobanteFiscalCorregido) {
		this.tipoComprobanteFiscalCorregido = tipoComprobanteFiscalCorregido;
	}
	public Float getMontoComprobanteFiscalCorregido() {
		return montoComprobanteFiscalCorregido;
	}
	public void setMontoComprobanteFiscalCorregido(
			Float montoComprobanteFiscalCorregido) {
		this.montoComprobanteFiscalCorregido = montoComprobanteFiscalCorregido;
	}
	public String getFolioComprobanteFiscalCorregido() {
		return folioComprobanteFiscalCorregido;
	}
	public void setFolioComprobanteFiscalCorregido(
			String folioComprobanteFiscalCorregido) {
		this.folioComprobanteFiscalCorregido = folioComprobanteFiscalCorregido;
	}
	public Float getMonto() {
		return monto;
	}
	public void setMonto(Float monto) {
		this.monto = monto;
	}
	public String getAprobada() {
		return aprobada;
	}
	public void setAprobada(String aprobada) {
		this.aprobada = aprobada;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}
	public Integer getIdFactura() {
		return idFactura;
	}
	public void setIdFactura(Integer idFactura) {
		this.idFactura = idFactura;
	}
	public String getNumeroFactura() {
		return numeroFactura;
	}
	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getRutaFactura() {
		return rutaFactura;
	}
	public void setRutaFactura(String rutaFactura) {
		this.rutaFactura = rutaFactura;
	}
	
	public Factura(){
		
	}
	
	public Factura(Integer idFactura, String numeroFactura, String aprobada,
			String detalle, String observaciones, String rutaFactura,Float monto, Float ajusteFactura,
			String tipoComprobanteFiscalCorregido,
			Float montoComprobanteFiscalCorregido,
			String folioComprobanteFiscalCorregido,byte[] archivo) {
		super();
		this.idFactura = idFactura;
		this.numeroFactura = numeroFactura;
		this.aprobada = aprobada;
		this.detalle = detalle;
		this.observaciones = observaciones;
		this.rutaFactura = rutaFactura;
		this.monto=monto;
		this.ajusteFactura=ajusteFactura;
		this.tipoComprobanteFiscalCorregido=tipoComprobanteFiscalCorregido;
		this.montoComprobanteFiscalCorregido=montoComprobanteFiscalCorregido;
		this.folioComprobanteFiscalCorregido=folioComprobanteFiscalCorregido;
		this.archivo=archivo;
	}

}
