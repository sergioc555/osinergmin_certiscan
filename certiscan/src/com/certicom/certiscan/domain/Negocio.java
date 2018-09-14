package com.certicom.certiscan.domain;

import java.util.List;

public class Negocio {
	
	private Integer id_negocio;
	private String descripcion;
	private boolean estado;
	private String des_proyecto;
	
	private Integer id_centro_atencion;
	
	
	public Negocio(){
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	

	public Integer getId_negocio() {
		return id_negocio;
	}

	public void setId_negocio(Integer id_negocio) {
		this.id_negocio = id_negocio;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public String getDes_proyecto() {
		return des_proyecto;
	}

	public void setDes_proyecto(String des_proyecto) {
		this.des_proyecto = des_proyecto;
	}

	public Integer getId_centro_atencion() {
		return id_centro_atencion;
	}

	public void setId_centro_atencion(Integer id_centro_atencion) {
		this.id_centro_atencion = id_centro_atencion;
	}


	
	
}
