package com.certicom.certiscan.domain;

public class Cliente {
	private String ruc_cliente;
	private String razonsocial_cliente;
	private String direccion_cliente;
	private String contacto_cliente;
	private String telefono_cliente;
	
	public Cliente(){
		
	}
	
	
	public String getRuc_cliente() {
		return ruc_cliente;
	}
	public void setRuc_cliente(String ruc_cliente) {
		this.ruc_cliente = ruc_cliente;
	}
	public String getRazonsocial_cliente() {
		return razonsocial_cliente;
	}
	public void setRazonsocial_cliente(String razonsocial_cliente) {
		this.razonsocial_cliente = razonsocial_cliente;
	}
	public String getDireccion_cliente() {
		return direccion_cliente;
	}
	public String getContacto_cliente() {
		return contacto_cliente;
	}
	public String getTelefono_cliente() {
		return telefono_cliente;
	}
	public void setDireccion_cliente(String direccion_cliente) {
		this.direccion_cliente = direccion_cliente;
	}
	public void setContacto_cliente(String contacto_cliente) {
		this.contacto_cliente = contacto_cliente;
	}
	public void setTelefono_cliente(String telefono_cliente) {
		this.telefono_cliente = telefono_cliente;
	}
	
	

}
