package com.certicom.certiscan.domain;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.lowagie.text.pdf.PdfReader;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

public class ExpedienteDocumento {

	private Integer id_expediente_documento;
	private Integer expediente_id;
	private String nombre_archivo;
	private String ruta;
	private Date fecha_subida;
	private Integer id_usuario_creacion;

	private String descripcion_archivo;
	private Long peso;
	private String descripcion_peso;
	private String grupo_formato;
	private Integer nro_paginas;
	private Integer nro_archivo;
	private Integer id_medio;
	private boolean medio;

	private String desNombreArchiOrig;

	private String estado_accion;
	private boolean estado;
	private Integer orden_expediente;
	private Integer id_padre_group;
	private ByteArrayOutputStream baoss;

	private File file;
	private InputStream inputStreamFile;
	private PdfReader pdfReader;
	private com.itextpdf.text.pdf.PdfReader pdfReader2;
	private List<DocumentoPagina> listaPaginas = new ArrayList<>();

	// alterno para expediente
	private String nro_expediente;
	private Date fec_recep;
	private String desOficina;
	private String desEstado;
	private String vfiltradopor;
	private Integer filtradopor;
	private Date fec_ini;
	private Date fec_fin;
	private String entregable;
	/*-------------------*/
	private Integer ordenTexto;
	private String texto;
	private String tipo_archivo;
	private ByteArrayOutputStream baos;
	private String carpeta;
	private String ubicacion;
	private String sede_oficina;
	private String zona;
	private Date fecha_reg;
	private String tipo_documento;
	private String numero_documento;
	private String fecha_del_documento;
	private Integer numero_de_imagenes;
	private String asunto;
	private String datos_del_cliente;

	private String firma_del_fedatario;
	private String fecha_aprobacion_fedatario;
	private String observacion;
	
	private String etiqueta;
	private String path;

	public String getTipo_documento() {
		return tipo_documento;
	}

	public void setTipo_documento(String tipo_documento) {
		this.tipo_documento = tipo_documento;
	}

	public String getNumero_documento() {
		return numero_documento;
	}

	public void setNumero_documento(String nombre_documento) {
		this.numero_documento = nombre_documento;
	}

	public String getFecha_del_documento() {
		return fecha_del_documento;
	}

	public void setFecha_del_documento(String fecha_del_documento) {
		this.fecha_del_documento = fecha_del_documento;
	}

	public Integer getNumero_de_imagenes() {
		return numero_de_imagenes;
	}

	public void setNumero_de_imagenes(Integer numero_de_imagenes) {
		this.numero_de_imagenes = numero_de_imagenes;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getDatos_del_cliente() {
		return datos_del_cliente;
	}

	public void setDatos_del_cliente(String datos_del_cliente) {
		this.datos_del_cliente = datos_del_cliente;
	}

	public String getFirma_del_fedatario() {
		return firma_del_fedatario;
	}

	public void setFirma_del_fedatario(String firma_del_fedatario) {
		this.firma_del_fedatario = firma_del_fedatario;
	}

	public String getFecha_aprobacion_fedatario() {
		return fecha_aprobacion_fedatario;
	}

	public void setFecha_aprobacion_fedatario(String fecha_aprobacion_fedatario) {
		this.fecha_aprobacion_fedatario = fecha_aprobacion_fedatario;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getCarpeta() {
		return carpeta;
	}

	public void setCarpeta(String carpeta) {
		this.carpeta = carpeta;
	}

	public Integer getId_expediente_documento() {
		return id_expediente_documento;
	}

	public void setId_expediente_documento(Integer id_expediente_documento) {
		this.id_expediente_documento = id_expediente_documento;
	}

	public String getDescripcion_archivo() {
		return descripcion_archivo;
	}

	public void setDescripcion_archivo(String descripcion_archivo) {
		this.descripcion_archivo = descripcion_archivo;
	}

	public Date getFecha_subida() {
		return fecha_subida;
	}

	public void setFecha_subida(Date fecha_subida) {
		this.fecha_subida = fecha_subida;
	}

	public Integer getId_usuario_creacion() {
		return id_usuario_creacion;
	}

	public void setId_usuario_creacion(Integer id_usuario_creacion) {
		this.id_usuario_creacion = id_usuario_creacion;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public Integer getExpediente_id() {
		return expediente_id;
	}

	public void setExpediente_id(Integer expediente_id) {
		this.expediente_id = expediente_id;
	}

	public String getNombre_archivo() {
		return nombre_archivo;
	}

	public void setNombre_archivo(String nombre_archivo) {
		this.nombre_archivo = nombre_archivo;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Long getPeso() {
		return peso;
	}

	public void setPeso(Long peso) {
		this.peso = peso;
	}

	public String getDescripcion_peso() {
		return descripcion_peso;
	}

	public void setDescripcion_peso(String descripcion_peso) {
		this.descripcion_peso = descripcion_peso;
	}

	public Integer getNro_paginas() {
		return nro_paginas;
	}

	public void setNro_paginas(Integer nro_paginas) {
		this.nro_paginas = nro_paginas;
	}

	public Integer getNro_archivo() {
		return nro_archivo;
	}

	public void setNro_archivo(Integer nro_archivo) {
		this.nro_archivo = nro_archivo;
	}

	public List<DocumentoPagina> getListaPaginas() {
		return listaPaginas;
	}

	public void setListaPaginas(List<DocumentoPagina> listaPaginas) {
		this.listaPaginas = listaPaginas;
	}

	public String getEstado_accion() {
		return estado_accion;
	}

	public void setEstado_accion(String estado_accion) {
		this.estado_accion = estado_accion;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public InputStream getInputStreamFile() {
		return inputStreamFile;
	}

	public void setInputStreamFile(InputStream inputStreamFile) {
		this.inputStreamFile = inputStreamFile;
	}

	public PdfReader getPdfReader() {
		return pdfReader;
	}

	public void setPdfReader(PdfReader pdfReader) {
		this.pdfReader = pdfReader;
	}

	public com.itextpdf.text.pdf.PdfReader getPdfReader2() {
		return pdfReader2;
	}

	public void setPdfReader2(com.itextpdf.text.pdf.PdfReader pdfReader2) {
		this.pdfReader2 = pdfReader2;
	}

	public Integer getOrden_expediente() {
		return orden_expediente;
	}

	public void setOrden_expediente(Integer orden_expediente) {
		this.orden_expediente = orden_expediente;
	}

	public Integer getId_padre_group() {
		return id_padre_group;
	}

	public void setId_padre_group(Integer id_padre_group) {
		this.id_padre_group = id_padre_group;
	}

	public Integer getOrdenTexto() {
		return ordenTexto;
	}

	public void setOrdenTexto(Integer ordenTexto) {
		this.ordenTexto = ordenTexto;
	}

	public String getNro_expediente() {
		return nro_expediente;
	}

	public void setNro_expediente(String nro_expediente) {
		this.nro_expediente = nro_expediente;
	}

	public Date getFec_recep() {
		return fec_recep;
	}

	public void setFec_recep(Date fec_recep) {
		this.fec_recep = fec_recep;
	}

	public String getDesOficina() {
		return desOficina;
	}

	public void setDesOficina(String desOficina) {
		this.desOficina = desOficina;
	}

	public String getDesEstado() {
		return desEstado;
	}

	public void setDesEstado(String desEstado) {
		this.desEstado = desEstado;
	}

	public ByteArrayOutputStream getBaoss() {
		return baoss;
	}

	public void setBaoss(ByteArrayOutputStream baoss) {
		this.baoss = baoss;
	}

	public String getDesNombreArchiOrig() {
		return desNombreArchiOrig;
	}

	public void setDesNombreArchiOrig(String desNombreArchiOrig) {
		this.desNombreArchiOrig = desNombreArchiOrig;
	}

	public ByteArrayOutputStream getBaos() {
		return baos;
	}

	public void setBaos(ByteArrayOutputStream baos) {
		this.baos = baos;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTipo_archivo() {
		return tipo_archivo;
	}

	public void setTipo_archivo(String tipo_archivo) {
		this.tipo_archivo = tipo_archivo;
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

	public String getGrupo_formato() {
		return grupo_formato;
	}

	public void setGrupo_formato(String grupo_formato) {
		this.grupo_formato = grupo_formato;
	}

	public Integer getId_medio() {
		return id_medio;
	}

	public void setId_medio(Integer id_medio) {
		this.id_medio = id_medio;
	}

	public boolean isMedio() {
		return medio;
	}

	public void setMedio(boolean medio) {
		this.medio = medio;
	}

	public String getEntregable() {
		return entregable;
	}

	public void setEntregable(String entregable) {
		this.entregable = entregable;
	}

	public String getSede_oficina() {
		return sede_oficina;
	}

	public void setSede_oficina(String sede_oficina) {
		this.sede_oficina = sede_oficina;
	}

	public String getZona() {
		return zona;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}

	public Date getFecha_reg() {
		return fecha_reg;
	}

	public void setFecha_reg(Date fecha_reg) {
		this.fecha_reg = fecha_reg;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
