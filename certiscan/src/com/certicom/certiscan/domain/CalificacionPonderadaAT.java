package com.certicom.certiscan.domain;

import java.math.BigDecimal;

public class CalificacionPonderadaAT {

	private String mes;
	private BigDecimal normal;
	private BigDecimal problemaPotencial;
	private BigDecimal deficiente;
	private BigDecimal dudoso;
	private BigDecimal perdida;
	
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public BigDecimal getNormal() {
		return normal;
	}
	public void setNormal(BigDecimal normal) {
		this.normal = normal;
	}
	public BigDecimal getProblemaPotencial() {
		return problemaPotencial;
	}
	public void setProblemaPotencial(BigDecimal problemaPotencial) {
		this.problemaPotencial = problemaPotencial;
	}
	public BigDecimal getDeficiente() {
		return deficiente;
	}
	public void setDeficiente(BigDecimal deficiente) {
		this.deficiente = deficiente;
	}
	public BigDecimal getDudoso() {
		return dudoso;
	}
	public void setDudoso(BigDecimal dudoso) {
		this.dudoso = dudoso;
	}
	public BigDecimal getPerdida() {
		return perdida;
	}
	public void setPerdida(BigDecimal perdida) {
		this.perdida = perdida;
	}
	
	
}
