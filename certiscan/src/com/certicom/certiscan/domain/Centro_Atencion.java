package com.certicom.certiscan.domain;

import java.io.Serializable;

public class Centro_Atencion implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int id_centro_atencion;
	private String nombre;
	private String descripcion;
	private String responsable;
	private String des_razon_social;
	private Integer id_empresa;

	public Centro_Atencion(){	
	}

	public Integer getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(Integer id_empresa) {
		this.id_empresa = id_empresa;
	}



	public int getId_centro_atencion() {
		return id_centro_atencion;
	}


	public void setId_centro_atencion(int id_centro_atencion) {
		this.id_centro_atencion = id_centro_atencion;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}


	public String getDes_razon_social() {
		return des_razon_social;
	}


	public void setDes_razon_social(String des_razon_social) {
		this.des_razon_social = des_razon_social;
	}
	
	

}
