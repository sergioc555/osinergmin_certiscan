package com.certicom.certiscan.domain;

import java.util.Date;

public class Tarifa {
	
	private Integer id_tarifa;
	private String descripcion;
	private double costo;
	private Integer cod_ciclo;
	private Integer cod_tipo_ciclo;
	private double costo_back;
	private double costo_digitalizador;
	private double costo_supervisor;
	
	public Tarifa() {
	}

	public Integer getId_tarifa() {
		return id_tarifa;
	}

	public void setId_tarifa(Integer id_tarifa) {
		this.id_tarifa = id_tarifa;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public Integer getCod_ciclo() {
		return cod_ciclo;
	}

	public void setCod_ciclo(Integer cod_ciclo) {
		this.cod_ciclo = cod_ciclo;
	}

	public Integer getCod_tipo_ciclo() {
		return cod_tipo_ciclo;
	}

	public void setCod_tipo_ciclo(Integer cod_tipo_ciclo) {
		this.cod_tipo_ciclo = cod_tipo_ciclo;
	}

	public double getCosto_back() {
		return costo_back;
	}

	public void setCosto_back(double costo_back) {
		this.costo_back = costo_back;
	}

	public double getCosto_digitalizador() {
		return costo_digitalizador;
	}

	public void setCosto_digitalizador(double costo_digitalizador) {
		this.costo_digitalizador = costo_digitalizador;
	}

	public double getCosto_supervisor() {
		return costo_supervisor;
	}

	public void setCosto_supervisor(double costo_supervisor) {
		this.costo_supervisor = costo_supervisor;
	}

	
	 
	 
}
