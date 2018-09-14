package com.certicom.certiscan.domain;

public class Requisito {

	private Integer cod_requisito;
	private String descripcion;
	private Boolean estado;
	private boolean defecto;
	
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
	public Integer getCod_requisito() {
		return cod_requisito;
	}
	public void setCod_requisito(Integer cod_requisito) {
		this.cod_requisito = cod_requisito;
	}
	public boolean isDefecto() {
		return defecto;
	}
	public void setDefecto(boolean defecto) {
		this.defecto = defecto;
	}
	
	
}
