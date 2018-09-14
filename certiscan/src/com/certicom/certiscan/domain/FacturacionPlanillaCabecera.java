package com.certicom.certiscan.domain;

import java.util.Date;

public class FacturacionPlanillaCabecera {
	
	private Integer id_facturacion_planilla_cabecera;
	private Integer idusuario;
	private Date  fecha;
	private Integer nro_registros;
	private Integer cod_ciclo;
	
	public FacturacionPlanillaCabecera(){
	}

	public Integer getId_facturacion_planilla_cabecera() {
		return id_facturacion_planilla_cabecera;
	}

	public void setId_facturacion_planilla_cabecera(
			Integer id_facturacion_planilla_cabecera) {
		this.id_facturacion_planilla_cabecera = id_facturacion_planilla_cabecera;
	}

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getNro_registros() {
		return nro_registros;
	}

	public void setNro_registros(Integer nro_registros) {
		this.nro_registros = nro_registros;
	}

	public Integer getCod_ciclo() {
		return cod_ciclo;
	}

	public void setCod_ciclo(Integer cod_ciclo) {
		this.cod_ciclo = cod_ciclo;
	}
	

	
	
}
