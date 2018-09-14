package com.certicom.certiscan.domain;

import java.util.List;

public class Indice {
	
	private Integer id_indice;
	private Integer id_tipo_documento;
	private Integer id_tipo_dato;
	private String  descripcion;
	private boolean estado;
	private String  Des_ctgCall;
	
	//Transitorias // no borrar es reusado
	private List<SubIndicadorCall> listSubIndicadorCall;
	private Integer cantidad;
	private String tipo_planilla;
	private String cod_territorio;
	private String territorio_ofc;
	private String desc_prioridad;
	public Indice(){
	}	


	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}	

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public List<SubIndicadorCall> getListSubIndicadorCall() {
		return listSubIndicadorCall;
	}


	public void setListSubIndicadorCall(List<SubIndicadorCall> listSubIndicadorCall) {
		this.listSubIndicadorCall = listSubIndicadorCall;
	}


	public Integer getCantidad() {
		return cantidad;
	}


	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}


	public String getTipo_planilla() {
		return tipo_planilla;
	}


	public void setTipo_planilla(String tipo_planilla) {
		this.tipo_planilla = tipo_planilla;
	}

	public String getCod_territorio() {
		return cod_territorio;
	}


	public void setCod_territorio(String cod_territorio) {
		this.cod_territorio = cod_territorio;
	}


	public String getTerritorio_ofc() {
		return territorio_ofc;
	}


	public void setTerritorio_ofc(String territorio_ofc) {
		this.territorio_ofc = territorio_ofc;
	}


	public String getDesc_prioridad() {
		return desc_prioridad;
	}


	public void setDesc_prioridad(String desc_prioridad) {
		this.desc_prioridad = desc_prioridad;
	}


	public Integer getId_indice() {
		return id_indice;
	}


	public void setId_indice(Integer id_indice) {
		this.id_indice = id_indice;
	}


	public Integer getId_tipo_documento() {
		return id_tipo_documento;
	}

	public void setId_tipo_documento(Integer id_tipo_documento) {
		this.id_tipo_documento = id_tipo_documento;
	}

	public Integer getId_tipo_dato() {
		return id_tipo_dato;
	}

	public void setId_tipo_dato(Integer id_tipo_dato) {
		this.id_tipo_dato = id_tipo_dato;
	}


	public String getDes_ctgCall() {
		return Des_ctgCall;
	}


	public void setDes_ctgCall(String des_ctgCall) {
		Des_ctgCall = des_ctgCall;
	}

	
}

