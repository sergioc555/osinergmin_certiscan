package com.certicom.certiscan.domain;

import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class CategoriaIndicadoresCall {
	
	private Integer id_categoria_call;
	private String descripcion;
	private Integer orden;
	private boolean estado;
	private Integer id_producto;
	private List<IndicadorCall> listIndicadorCall;
	
	public CategoriaIndicadoresCall(){
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

	public Integer getId_categoria_call() {
		return id_categoria_call;
	}

	public void setId_categoria_call(Integer id_categoria_call) {
		this.id_categoria_call = id_categoria_call;
	}

	public List<IndicadorCall> getListIndicadorCall() {
		return listIndicadorCall;
	}

	public void setListIndicadorCall(List<IndicadorCall> listIndicadorCall) {
		this.listIndicadorCall = listIndicadorCall;
	}

	public Integer getId_producto() {
		return id_producto;
	}

	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}




}
