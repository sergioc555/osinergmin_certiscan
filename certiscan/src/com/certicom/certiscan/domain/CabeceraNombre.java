package com.certicom.certiscan.domain;

public class CabeceraNombre {
	
	private Integer idcabeceranombre;
	private String descripcion;
	private boolean estado;
	private Integer idcabecera;
	
	public CabeceraNombre(){
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Integer getIdcabecera() {
		return idcabecera;
	}

	public void setIdcabecera(Integer idcabecera) {
		this.idcabecera = idcabecera;
	}

	public Integer getIdcabeceranombre() {
		return idcabeceranombre;
	}

	public void setIdcabeceranombre(Integer idcabeceranombre) {
		this.idcabeceranombre = idcabeceranombre;
	}

	
	
	
}
