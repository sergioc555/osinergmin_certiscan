package com.certicom.certiscan.domain;

import java.util.Date;

public class TelefonoExpedienteTemporal {
	
	private Integer id;
	private String valor;
	private Integer idProducto;
	private Date periodo;
	private Integer idUsuario;
	
	public TelefonoExpedienteTemporal(){
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public Date getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Date periodo) {
		this.periodo = periodo;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	
	
	
	
	
}
