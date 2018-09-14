package com.certicom.certiscan.domain;

public class CabeceraFacturacionNombre {
	private Integer idCabeceraFacturacionNombre;
	private Integer idCabeceraFacturacion;
	private String descripcion;
	private Boolean estado;
	
	public Integer getIdCabeceraFacturacionNombre() {
		return idCabeceraFacturacionNombre;
	}
	public void setIdCabeceraFacturacionNombre(Integer idCabeceraFacturacionNombre) {
		this.idCabeceraFacturacionNombre = idCabeceraFacturacionNombre;
	}
	public Integer getIdCabeceraFacturacion() {
		return idCabeceraFacturacion;
	}
	public void setIdCabeceraFacturacion(Integer idCabeceraFacturacion) {
		this.idCabeceraFacturacion = idCabeceraFacturacion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getEstado() {
		return estado;
	}
	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	
}
