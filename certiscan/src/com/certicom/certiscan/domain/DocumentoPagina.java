package com.certicom.certiscan.domain;

import java.util.Date;

public class DocumentoPagina {
	
	private Integer id_documento_pagina;
	private Integer id_expediente_documento;
	private Integer nro_pagina;
	private Long peso;
	private String descripcion_peso;
	private Date fecha_subida;
	private Integer id_usuario_creacion;
	private boolean flag;
	private String estado_accion;
	private boolean estado;
	
	public Integer getId_expediente_documento() {
		return id_expediente_documento;
	}
	public void setId_expediente_documento(Integer id_expediente_documento) {
		this.id_expediente_documento = id_expediente_documento;
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
	public Integer getId_documento_pagina() {
		return id_documento_pagina;
	}
	public void setId_documento_pagina(Integer id_documento_pagina) {
		this.id_documento_pagina = id_documento_pagina;
	}
	public Integer getNro_pagina() {
		return nro_pagina;
	}
	public void setNro_pagina(Integer nro_pagina) {
		this.nro_pagina = nro_pagina;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
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
	
	
	
	
}
