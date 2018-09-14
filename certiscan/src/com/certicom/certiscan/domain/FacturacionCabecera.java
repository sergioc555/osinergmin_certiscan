package com.certicom.certiscan.domain;

import java.util.Date;

public class FacturacionCabecera {
	private Integer idFacturacionCabecera;
	private Integer idUsuario;
	private Integer idProducto;
	private Date fecha;
	private Integer numeroregistro;
	private Date periodo;
	private Integer id_negocio;
	private String nombrearchivo;
	private String nombusuario;
	private String desnegocio;
	private String desproducto;
	
	//alternos
	private String anio;
	private String mes;
	
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public Integer getIdFacturacionCabecera() {
		return idFacturacionCabecera;
	}
	public void setIdFacturacionCabecera(Integer idFacturacionCabecera) {
		this.idFacturacionCabecera = idFacturacionCabecera;
	}
	public Integer getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Integer getNumeroregistro() {
		return numeroregistro;
	}
	public void setNumeroregistro(Integer numeroregistro) {
		this.numeroregistro = numeroregistro;
	}
	public Date getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Date periodo) {
		this.periodo = periodo;
	}
	public Integer getId_negocio() {
		return id_negocio;
	}
	public void setId_negocio(Integer id_negocio) {
		this.id_negocio = id_negocio;
	}
	public String getNombrearchivo() {
		return nombrearchivo;
	}
	public void setNombrearchivo(String nombrearchivo) {
		this.nombrearchivo = nombrearchivo;
	}
	public String getNombusuario() {
		return nombusuario;
	}
	public void setNombusuario(String nombusuario) {
		this.nombusuario = nombusuario;
	}
	public String getDesnegocio() {
		return desnegocio;
	}
	public void setDesnegocio(String desnegocio) {
		this.desnegocio = desnegocio;
	}
	public String getDesproducto() {
		return desproducto;
	}
	public void setDesproducto(String desproducto) {
		this.desproducto = desproducto;
	} 
}
