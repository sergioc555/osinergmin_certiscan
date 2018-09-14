package com.certicom.certiscan.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArchivoExcel {
	// prueba de envio

	private List<String> listaData;
	private List<String> listaCabecera;
	private Integer nroColumnas;
	private Integer nroFilas;
	private List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
	private List<String[]> rows = new ArrayList<String[]>();
	private String[] headers;

	public List<String> getListaData() {
		return listaData;
	}

	public void setListaData(List<String> listaData) {
		this.listaData = listaData;
	}

	public Integer getNroColumnas() {
		return nroColumnas;
	}

	public void setNroColumnas(Integer nroColumnas) {
		this.nroColumnas = nroColumnas;
	}

	public Integer getNroFilas() {
		return nroFilas;
	}

	public void setNroFilas(Integer nroFilas) {
		this.nroFilas = nroFilas;
	}

	public List<String> getListaCabecera() {
		return listaCabecera;
	}

	public void setListaCabecera(List<String> listaCabecera) {
		this.listaCabecera = listaCabecera;
	}

	public List<Map<String, String>> getMapList() {
		return mapList;
	}

	public void setMapList(List<Map<String, String>> mapList) {
		this.mapList = mapList;
	}

	public List<String[]> getRows() {
		return rows;
	}

	public void setRows(List<String[]> rows) {
		this.rows = rows;
	}

	public String[] getHeaders() {
		return headers;
	}

	public void setHeaders(String[] headers) {
		this.headers = headers;
	}

}
