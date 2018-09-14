package com.certicom.certiscan.commons;

import java.util.List;

import com.certicom.certiscan.domain.Expediente;
import com.certicom.certiscan.domain.IndicadorCall;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ReportExpediente {
	
	private List<Expediente> listaBaseEntregada;
	private List<Expediente> listaBaseNeta;
	
	private List<Expediente> listaCargados;
	private List<Expediente> listaMontos;
	private List<Expediente> listaRepetidos;
	
	private List<IndicadorCall> listaIndicadores;
	
	private List<Expediente> listaBaseExcluida;
	
	
	public List<Expediente> getListaBaseEntregada() {
		return listaBaseEntregada;
	}
	public void setListaBaseEntregada(List<Expediente> listaBaseEntregada) {
		this.listaBaseEntregada = listaBaseEntregada;
	}
	public List<Expediente> getListaBaseNeta() {
		return listaBaseNeta;
	}
	public void setListaBaseNeta(List<Expediente> listaBaseNeta) {
		this.listaBaseNeta = listaBaseNeta;
	}
	
	public List<Expediente> getListaCargados() {
		return listaCargados;
	}
	public void setListaCargados(List<Expediente> listaCargados) {
		this.listaCargados = listaCargados;
	}
	public List<Expediente> getListaMontos() {
		return listaMontos;
	}
	public void setListaMontos(List<Expediente> listaMontos) {
		this.listaMontos = listaMontos;
	}
	public List<Expediente> getListaRepetidos() {
		return listaRepetidos;
	}
	public void setListaRepetidos(List<Expediente> listaRepetidos) {
		this.listaRepetidos = listaRepetidos;
	}
	
	public JRBeanCollectionDataSource getBaseEntregadaDS() {
		return new JRBeanCollectionDataSource(this.listaBaseEntregada, false);
	}
	
	public List<IndicadorCall> getListaIndicadores() {
		return listaIndicadores;
	}
	public void setListaIndicadores(List<IndicadorCall> listaIndicadores) {
		this.listaIndicadores = listaIndicadores;
	}
	
	public JRBeanCollectionDataSource getBaseNetaDS() {
		return new JRBeanCollectionDataSource(this.listaBaseNeta, false);
	}
	
	
	public JRBeanCollectionDataSource getCargadosDS() {
		return new JRBeanCollectionDataSource(this.listaCargados, false);
	}
	
	public JRBeanCollectionDataSource getMontosDS() {
		return new JRBeanCollectionDataSource(this.listaMontos, false);
	}
	
	public JRBeanCollectionDataSource getRepetidosDS() {
		return new JRBeanCollectionDataSource(this.listaRepetidos, false);
	}

	public JRBeanCollectionDataSource getIndicadoresDS() {
		return new JRBeanCollectionDataSource(this.listaIndicadores, false);
	}
	public List<Expediente> getListaBaseExcluida() {
		return listaBaseExcluida;
	}
	public void setListaBaseExcluida(List<Expediente> listaBaseExcluida) {
		this.listaBaseExcluida = listaBaseExcluida;
	}
	
	
	public JRBeanCollectionDataSource getBaseExcluidaDS() {
		return new JRBeanCollectionDataSource(this.listaBaseExcluida, false);
	}

}
