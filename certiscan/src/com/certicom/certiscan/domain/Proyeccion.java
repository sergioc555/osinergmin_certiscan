package com.certicom.certiscan.domain;

import java.util.Date;

public class Proyeccion {

	private Integer id_proyeccion;
	private Integer id_expediente_producto;
	private Integer expediente_id;
	private Date periodo;
	private Integer cant_cuentas_ph;
	private Integer cant_cuentas_cts;
	private Integer usuario_registro;
	private Date fecha_registro;
	
	
	public Integer getId_proyeccion() {
		return id_proyeccion;
	}
	public Integer getId_expediente_producto() {
		return id_expediente_producto;
	}
	public Integer getExpediente_id() {
		return expediente_id;
	}
	public Date getPeriodo() {
		return periodo;
	}
	public Integer getCant_cuentas_ph() {
		return cant_cuentas_ph;
	}
	public Integer getCant_cuentas_cts() {
		return cant_cuentas_cts;
	}
	public Integer getUsuario_registro() {
		return usuario_registro;
	}
	public Date getFecha_registro() {
		return fecha_registro;
	}
	public void setId_proyeccion(Integer id_proyeccion) {
		this.id_proyeccion = id_proyeccion;
	}
	public void setId_expediente_producto(Integer id_expediente_producto) {
		this.id_expediente_producto = id_expediente_producto;
	}
	public void setExpediente_id(Integer expediente_id) {
		this.expediente_id = expediente_id;
	}
	public void setPeriodo(Date periodo) {
		this.periodo = periodo;
	}
	public void setCant_cuentas_ph(Integer cant_cuentas_ph) {
		this.cant_cuentas_ph = cant_cuentas_ph;
	}
	public void setCant_cuentas_cts(Integer cant_cuentas_cts) {
		this.cant_cuentas_cts = cant_cuentas_cts;
	}
	public void setUsuario_registro(Integer usuario_registro) {
		this.usuario_registro = usuario_registro;
	}
	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	
	
	
}
