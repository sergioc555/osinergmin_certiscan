package com.certicom.certiscan.domain;

import java.util.Date;

public class LogSBS {
	
	  private int idlog;
	  private Date fecha_consulta;
	  private int idusuario;
	  private String tipdoc;
	  private String numdoc;
	  private String usuarioAsignado;
	  
	 //test
	public LogSBS(){
	}

	public int getIdlog() {
		return idlog;
	}

	public void setIdlog(int idlog) {
		this.idlog = idlog;
	}

	public Date getFecha_consulta() {
		return fecha_consulta;
	}

	public void setFecha_consulta(Date fecha_consulta) {
		this.fecha_consulta = fecha_consulta;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getTipdoc() {
		return tipdoc;
	}

	public void setTipdoc(String tipdoc) {
		this.tipdoc = tipdoc;
	}

	public String getNumdoc() {
		return numdoc;
	}

	public void setNumdoc(String numdoc) {
		this.numdoc = numdoc;
	}

	public String getUsuarioAsignado() {
		return usuarioAsignado;
	}

	public void setUsuarioAsignado(String usuarioAsignado) {
		this.usuarioAsignado = usuarioAsignado;
	}	
	
}
