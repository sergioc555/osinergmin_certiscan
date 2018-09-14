package com.certicom.certiscan.domain;

public class CategoriaEstados {

	private Integer id_categoria_estado;
	private String descripcion;
	private String observacion;
	private boolean estado;

	private String codigo;

	public CategoriaEstados() {
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

	public Integer getId_categoria_estado() {
		return id_categoria_estado;
	}

	public void setId_categoria_estado(Integer id_categoria_estado) {
		this.id_categoria_estado = id_categoria_estado;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

}
