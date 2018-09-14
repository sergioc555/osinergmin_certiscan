package com.certicom.certiscan.domain;

public class Cargo {
	
	private Integer id_cargo;
	private String descripcion;

	
	public Cargo(){
		
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getId_cargo() {
		return id_cargo;
	}

	public void setId_cargo(Integer id_cargo) {
		this.id_cargo = id_cargo;
	}

	

	
	
}
