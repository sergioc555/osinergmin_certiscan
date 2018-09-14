package com.certicom.certiscan.domain;

import java.util.Date;

public class CompresionLectura {

	private Integer id_comprension_lectura;
	private Integer idusuario;
	private Integer id_negocio;
	private Date periodo;
	private String texto_pdf;
	private Date fec_registro;
	
	public Integer getId_comprension_lectura() {
		return id_comprension_lectura;
	}
	public void setId_comprension_lectura(Integer id_comprension_lectura) {
		this.id_comprension_lectura = id_comprension_lectura;
	}
	public Integer getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}
	public Integer getId_negocio() {
		return id_negocio;
	}
	public void setId_negocio(Integer id_negocio) {
		this.id_negocio = id_negocio;
	}
	public Date getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Date periodo) {
		this.periodo = periodo;
	}
	public String getTexto_pdf() {
		return texto_pdf;
	}
	public void setTexto_pdf(String texto_pdf) {
		this.texto_pdf = texto_pdf;
	}
	public Date getFec_registro() {
		return fec_registro;
	}
	public void setFec_registro(Date fec_registro) {
		this.fec_registro = fec_registro;
	}

}
