package com.certicom.certiscan.domain;

import java.util.Date;

public class PlanillaPersonal {
	
	public int id_planilla_personal ;
	public int idusuario;
	private Integer id_planilla;
	private Integer id_cargo;
	private Integer id_negocio;
	private Integer id_supervisor;
	private Integer id_coordinador;
	private Date fecha_registro; 
	private Date fecha_ingreso;
	private boolean estado;
	
	
	//campos alternos
	private String desTipoPlanilla;
	private String desCargo;
	private String desNegocio;
	private String supervisor;
	private String coordinador;
	private String codigo_banco;
	private String codigo_banco_supervisor;
	
	
	public int getId_planilla_personal() {
		return id_planilla_personal;
	}
	public void setId_planilla_personal(int id_planilla_personal) {
		this.id_planilla_personal = id_planilla_personal;
	}
	public int getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}
	public Integer getId_planilla() {
		return id_planilla;
	}
	public void setId_planilla(Integer id_planilla) {
		this.id_planilla = id_planilla;
	}
	public Integer getId_cargo() {
		return id_cargo;
	}
	public void setId_cargo(Integer id_cargo) {
		this.id_cargo = id_cargo;
	}
	public Integer getId_negocio() {
		return id_negocio;
	}
	public void setId_negocio(Integer id_negocio) {
		this.id_negocio = id_negocio;
	}
	public Date getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}
	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public String getDesTipoPlanilla() {
		return desTipoPlanilla;
	}
	public void setDesTipoPlanilla(String desTipoPlanilla) {
		this.desTipoPlanilla = desTipoPlanilla;
	}
	public String getDesCargo() {
		return desCargo;
	}
	public void setDesCargo(String desCargo) {
		this.desCargo = desCargo;
	}
	public String getDesNegocio() {
		return desNegocio;
	}
	public void setDesNegocio(String desNegocio) {
		this.desNegocio = desNegocio;
	}
	public Integer getId_supervisor() {
		return id_supervisor;
	}
	public void setId_supervisor(Integer id_supervisor) {
		this.id_supervisor = id_supervisor;
	}
	public Integer getId_coordinador() {
		return id_coordinador;
	}
	public void setId_coordinador(Integer id_coordinador) {
		this.id_coordinador = id_coordinador;
	}
	public String getSupervisor() {
		return supervisor;
	}
	public void setSupervisor(String supervisor) {
		this.supervisor = supervisor;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	public String getCoordinador() {
		return coordinador;
	}
	public void setCoordinador(String coordinador) {
		this.coordinador = coordinador;
	}
	public String getCodigo_banco() {
		return codigo_banco;
	}
	public void setCodigo_banco(String codigo_banco) {
		this.codigo_banco = codigo_banco;
	}
	public String getCodigo_banco_supervisor() {
		return codigo_banco_supervisor;
	}
	public void setCodigo_banco_supervisor(String codigo_banco_supervisor) {
		this.codigo_banco_supervisor = codigo_banco_supervisor;
	}

}
