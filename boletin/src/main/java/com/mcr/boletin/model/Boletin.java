package com.mcr.boletin.model;

import java.io.Serializable;
import java.util.Date;

public class Boletin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int idBoletin;
	private String numeroDeBoletin;
	private Date fechaDeBoletin;
	private String resolucionesDesde;
	private String resolucionesHasta;
	private String ordenanzasIncluidas;
	private String otrosDocumentos;
	private String direccionArchivo;
	private String tamanioArchivo;
	private String resolucionDesdeHasta;
	private boolean estadoBoletin;
	
	
	
	public Boletin() {
		
		
	
	}









	public int getIdBoletin() {
		return idBoletin;
	}









	public void setIdBoletin(int idBoletin) {
		this.idBoletin = idBoletin;
	}









	public String getNumeroDeBoletin() {
		return numeroDeBoletin;
	}









	public void setNumeroDeBoletin(String numeroDeBoletin) {
		this.numeroDeBoletin = numeroDeBoletin;
	}









	public Date getFechaDeBoletin() {
		return fechaDeBoletin;
	}









	public void setFechaDeBoletin(Date fechaDeBoletin) {
		this.fechaDeBoletin = fechaDeBoletin;
	}



	public String getOrdenanzasIncluidas() {
		return ordenanzasIncluidas;
	}









	public void setOrdenanzasIncluidas(String ordenanzasIncluidas) {
		this.ordenanzasIncluidas = ordenanzasIncluidas;
	}









	public String getOtrosDocumentos() {
		return otrosDocumentos;
	}









	public void setOtrosDocumentos(String otrosDocumentos) {
		this.otrosDocumentos = otrosDocumentos;
	}









	public String getDireccionArchivo() {
		return direccionArchivo;
	}









	public void setDireccionArchivo(String direccionArchivo) {
		this.direccionArchivo = direccionArchivo;
	}









	public boolean isEstadoBoletin() {
		return estadoBoletin;
	}









	public void setEstadoBoletin(boolean estadoBoletin) {
		this.estadoBoletin = estadoBoletin;
	}









	public String getResolucionesDesde() {
		return resolucionesDesde;
	}









	public void setResolucionesDesde(String resolucionesDesde) {
		this.resolucionesDesde = resolucionesDesde;
	}









	public String getResolucionesHasta() {
		return resolucionesHasta;
	}









	public void setResolucionesHasta(String resolucionesHasta) {
		this.resolucionesHasta = resolucionesHasta;
	}









	public String getTamanioArchivo() {
		return tamanioArchivo;
	}









	public void setTamanioArchivo(String tamanioArchivo) {
		this.tamanioArchivo = tamanioArchivo;
	}









	public String getResolucionDesdeHasta() {
		return resolucionDesdeHasta;
	}









	public void setResolucionDesdeHasta(String resolucionDesdeHasta) {
		this.resolucionDesdeHasta = resolucionDesdeHasta;
	}
	
	
	

}
