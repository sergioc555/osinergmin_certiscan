package com.certicom.certiscan.domain;

import java.util.Date;

public class Comentario {
	
	private Integer idcomentario;
	private String descripcion;
	private Integer expediente_id;
	private Date fec_hora;
	 
	public Comentario() {
	}

	public Integer getIdcomentario() {
		return idcomentario;
	}

	public void setIdcomentario(Integer idcomentario) {
		this.idcomentario = idcomentario;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getExpediente_id() {
		return expediente_id;
	}

	public void setExpediente_id(Integer expediente_id) {
		this.expediente_id = expediente_id;
	}

	public Date getFec_hora() {
		return fec_hora;
	}

	public void setFec_hora(Date fec_hora) {
		this.fec_hora = fec_hora;
	}


	 
	 
}
