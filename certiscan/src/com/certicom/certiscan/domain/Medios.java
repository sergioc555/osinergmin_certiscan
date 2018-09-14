package com.certicom.certiscan.domain;

import java.io.ByteArrayOutputStream;
import java.util.Date;
import java.util.List;

import org.primefaces.model.UploadedFile;

public class Medios {
	
	private Integer id_medio;
	private String descripcion;
	private Integer total_archivos;
	private String descripcion_peso;
	private Long peso;
	private Date fecha_registro;
	private ByteArrayOutputStream baos;
	private List<ExpedienteDocumento> listExpedienteDocumentos;
	private String ruta;
	private Integer id_estado;
	private String desEstado;
	private Integer id_oficina;
	private String tipo_medio;
	private UploadedFile upFile;
	private boolean creado;
	
	private Date fecha_ini;
	private Date fecha_fin;
	
	private Date fec_ini;
	private Date fec_fin;
	
	private String vfiltradopor;
	private Integer filtradopor;
	
	private Integer idusuario;
	
	public Medios(){
		
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Integer getId_medio() {
		return id_medio;
	}

	public void setId_medio(Integer id_medio) {
		this.id_medio = id_medio;
	}

	public Integer getTotal_archivos() {
		return total_archivos;
	}

	public void setTotal_archivos(Integer total_archivos) {
		this.total_archivos = total_archivos;
	}

	public Date getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public String getDescripcion_peso() {
		return descripcion_peso;
	}

	public void setDescripcion_peso(String descripcion_peso) {
		this.descripcion_peso = descripcion_peso;
	}

	public Long getPeso() {
		return peso;
	}

	public void setPeso(Long peso) {
		this.peso = peso;
	}

	public List<ExpedienteDocumento> getListExpedienteDocumentos() {
		return listExpedienteDocumentos;
	}

	public void setListExpedienteDocumentos(
			List<ExpedienteDocumento> listExpedienteDocumentos) {
		this.listExpedienteDocumentos = listExpedienteDocumentos;
	}

	public ByteArrayOutputStream getBaos() {
		return baos;
	}

	public void setBaos(ByteArrayOutputStream baos) {
		this.baos = baos;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public Integer getId_estado() {
		return id_estado;
	}

	public void setId_estado(Integer id_estado) {
		this.id_estado = id_estado;
	}

	public String getDesEstado() {
		return desEstado;
	}

	public void setDesEstado(String desEstado) {
		this.desEstado = desEstado;
	}

	public Date getFec_ini() {
		return fec_ini;
	}

	public void setFec_ini(Date fec_ini) {
		this.fec_ini = fec_ini;
	}

	public Date getFec_fin() {
		return fec_fin;
	}

	public void setFec_fin(Date fec_fin) {
		this.fec_fin = fec_fin;
	}

	public String getVfiltradopor() {
		return vfiltradopor;
	}

	public void setVfiltradopor(String vfiltradopor) {
		this.vfiltradopor = vfiltradopor;
	}

	public Integer getFiltradopor() {
		return filtradopor;
	}

	public void setFiltradopor(Integer filtradopor) {
		this.filtradopor = filtradopor;
	}

	public Integer getId_oficina() {
		return id_oficina;
	}

	public void setId_oficina(Integer id_oficina) {
		this.id_oficina = id_oficina;
	}

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public String getTipo_medio() {
		return tipo_medio;
	}

	public void setTipo_medio(String tipo_medio) {
		this.tipo_medio = tipo_medio;
	}

	public UploadedFile getUpFile() {
		return upFile;
	}

	public void setUpFile(UploadedFile upFile) {
		this.upFile = upFile;
	}

	public boolean isCreado() {
		return creado;
	}

	public void setCreado(boolean creado) {
		this.creado = creado;
	}

	public Date getFecha_ini() {
		return fecha_ini;
	}

	public void setFecha_ini(Date fecha_ini) {
		this.fecha_ini = fecha_ini;
	}

	public Date getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Date fecha_fin) {
		this.fecha_fin = fecha_fin;
	}

	
}
