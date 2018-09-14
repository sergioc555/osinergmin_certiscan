package com.certicom.certiscan.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Facturacion {

	private Integer idFacturacion;
	private Integer id_facturacion_cabecera;
	private double 	meta;

	public double getMeta() {
		return meta;
	}
	public void setMeta(double meta) {
		this.meta = meta;
	}
	public Integer getId_facturacion_cabecera() {
		return id_facturacion_cabecera;
	}
	public void setId_facturacion_cabecera(Integer id_facturacion_cabecera) {
		this.id_facturacion_cabecera = id_facturacion_cabecera;
	}
	private Date periodo;
	private Integer idProducto; // guardar;
	private Integer idnegocio; // guardar;
  
	public String cliente_negativo;
	public String cr;
	public String vd;
	public String vl;
	public String getSituacionaltamira() {
		return situacionaltamira;
	}
	public void setSituacionaltamira(String situacionaltamira) {
		this.situacionaltamira = situacionaltamira;
	}
	public String fuerza_venta;
	public String modalidad_venta;
	public String numero_solicitud;
	public String apellido_paterno_cliente;
	public String apellido_materno_cliente;
	public String nombre_cliente;
	public String moneda_solicitada;
	public String moneda_aprobada;
	public BigDecimal monto_solicitado;
	public BigDecimal monto_aprobado;
	
	public String codigo_producto;
	public String nombre_producto;
	public String codigo_sub_producto;
	public String sub_producto;
	public String adicional;
	public String doi; // documento
	public String direccion_cliente;
	public String telefono_cliente;
	public String celular_cliente;
	public String origen;
	public String fecha_rvgl;
	public String fecha_registro;
	public String fecha_estado;
	public String fecha_actual;

	public String fecha_formalizacion_operacion;
	public String fecha_estado_comision;
	public String estado_comision;
	public String fecha_estado_altamira;
	public String estado_altamira;
	public String propiedad_producto;
	public String numero_contrato;
	public String codigo_jefe;
	public String jefe;
	public String codigo_ejecutivo;
	public String ejecutivo;
	public String codigo_oficina_asignada;
	public String observacion;
	public String ultima_persona_atencion;
	public String responsable_actual;
	public String cliente_vip;
	public String pago_haberes;
	public String tipo_captacion;
	public String canal_venta;
	public String colectivo;
	public String observacion_riesgos;
	public String fondo_aperturado;
	public String origen_fondo;
	public String procedencia_fondos;
	public String segmento_promotor;
	public String cliente;
	public String razon_social;
	public String ruc;
	public String precio_cts;
	public BigDecimal monto_soles;
	public String departamento;
	public String modalidad;
	public String ruc_empresa;
	public String empresa_ph;
	
	/*NUEVOS*/
	public String tipodocumento;
	public String contrato;
	public String fechaalta;
	public String codigocliente;
	public String nrocontrato;
	public String nrotarjeta;
	public String situaciontarjeta;
	public String situacionfacturacion;
	public String situacionaltamira;
	public String fechacancelaciontarj;
	public String fechabloqueo;
	public String fechaactivacion;
	public String fechacancelacionprest;
	public String fechaliquidacion;
	public String comision;
	public BigDecimal comisionjefe;
	public String reglanegocio;
	public String solicitado;
	public String modoventa;
	public String canalventa;
	public String fechaformalizacion;
	public String moneda;
	public String monto;
	public String liquidacion;
	public String caalc_cnro_solicitud; 
	public String tc;
	public BigDecimal bono_mi_vivienda;
	public BigDecimal monto_total_soles;
	public String validacion_colocacion;
	public Integer expediente_id;
	public String ubicacion;
	public String codigo_oficina;
	
	/*FIN NUEVOS*/
	private Integer idusuario_ejecutivo_original;
	private Integer idusuario_jefe_original;
	private String descripcion_encontrado;
	private Integer idusuario_ejecutivo;
	private Integer idusuario_jefe; // persistir idusuario_supervisor
	
	
	private Integer idusuario_jefe_campo;
	private Integer id_indicadores_call;
	private Integer id_categoria_call;
	private Integer id_cargo;
	private String des_tipo_planilla;
	private BigDecimal por_comision_certicom;
	private BigDecimal comision_certicom;
	private BigDecimal igv;
	private BigDecimal por_comision_ejecutivo; //esto es para sacar el porcentaje 
	private BigDecimal comision_ejecutivo;
	
	private BigDecimal comision_supervisor;
	private BigDecimal comision_coordinador;
	private BigDecimal comision_back;
	
	
	private Integer idusuario_coordinador;
	private Integer idusuario_back;
	
	private Integer idusuario_ejecutivo_campo;
	private Integer idusuario_supervisor_campo;
	private BigDecimal comision_ejecutivo_campo;
	private BigDecimal comision_supervisor_campo;
	private boolean estado_validacion;
	
	
	private BigDecimal por_comision_supervisor;
	private BigDecimal por_comision_ejecutivo_campo;
	private BigDecimal por_comision_supervisor_campo;
	private BigDecimal por_comision_coordinador;
	private BigDecimal por_comision_back;
	
	private Integer idplanilla_ejecutivo;
	private Integer idplanilla_supervisor;
	private Integer idplanilla_ejecutivo_campo;
	private Integer idplanilla_supervisor_campo;
	private Integer idplanilla_coordinador;
	private Integer idplanilla_back;
	public String id_producto_factura;
	
	// pago de haberes
	public String mes;
	public String rango;
	//private String producto;
	
	//pymes
	public String segmento;
	public String tipooperacion;
	public String rango_dias;
	
	
	// transient
	private double  meta_supervisor;
	private double  meta_coordinador;
	
	//Transitorias
	private Boolean estadoMatch;
	private List<ExpedienteProducto> listaExpedienteProducto;
	private ExpedienteProducto expedienteProducto;
	private Usuario usuarioEjecutivo;
	private Usuario usuarioJefe;
	private Usuario usuarioEjecutivoOriginal;
	private Usuario usuarioJefeOriginal;
	private Usuario usuarioSCPFTrabajo;
	private Integer id_sub_producto;
	private Date fecha_sistema;
	private Integer usuario_sistema;
	private BigDecimal total_facturar;
	private BigDecimal monto_descuento_ejecutivo;
	private BigDecimal monto_descuento_supervisor;
	private Integer mes_descuento;
	private String mes_descuento_DESC;
	private String observacion_descuento;
	
	private Usuario usuarioCoordinador;
	private Usuario usuarioEjecutivoCampo;
	private Usuario usuarioSupervisorCampo;
	private Usuario usuarioBackOffice;
	
	
	private  Integer nro_ope_eje = 0;
	private double monto_consolidado_eje = 0;
	
	private  Integer nro_ope_sp = 0;
	private double monto_consolidado_sp = 0;
	private String usuarioAsignado;
	
	private String des_negocio ;
	private String des_producto;
	private double monto_facturado;
	private double monto_pagado;
	private double monto_desembolsado;
	
	private Integer cant_operaciones;

	public Integer getIdusuario_ejecutivo_original() {
		return idusuario_ejecutivo_original;
	}
	public void setIdusuario_ejecutivo_original(Integer idusuario_ejecutivo_original) {
		this.idusuario_ejecutivo_original = idusuario_ejecutivo_original;
	}
	public Integer getIdusuario_jefe_original() {
		return idusuario_jefe_original;
	}
	public void setIdusuario_jefe_original(Integer idusuario_jefe_original) {
		this.idusuario_jefe_original = idusuario_jefe_original;
	}
	public String getMes_descuento_DESC() {
		mes_descuento_DESC = Globales.MESES[mes_descuento];
		return mes_descuento_DESC;
	}
	public void setMes_descuento_DESC(String mes_descuento_DESC) {
		this.mes_descuento_DESC = mes_descuento_DESC;
	}
	public Integer getIdFacturacion() {
		return idFacturacion;
	}
	public void setIdFacturacion(Integer idFacturacion) {
		this.idFacturacion = idFacturacion;
	}
	public Date getPeriodo() {
		return periodo;
	}
	public void setPeriodo(Date periodo) {
		this.periodo = periodo;
	}
	public Integer getIdProducto() {
		return idProducto;
	}
	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}
	public String getCliente_negativo() {
		return cliente_negativo;
	}
	public void setCliente_negativo(String cliente_negativo) {
		this.cliente_negativo = cliente_negativo;
	}
	public String getCr() {
		return cr;
	}
	public void setCr(String cr) {
		this.cr = cr;
	}
	public String getVd() {
		return vd;
	}
	public void setVd(String vd) {
		this.vd = vd;
	}
	public String getVl() {
		return vl;
	}
	public void setVl(String vl) {
		this.vl = vl;
	}
	public String getFuerza_venta() {
		return fuerza_venta;
	}
	public void setFuerza_venta(String fuerza_venta) {
		this.fuerza_venta = fuerza_venta;
	}
	public String getModalidad_venta() {
		return modalidad_venta;
	}
	public void setModalidad_venta(String modalidad_venta) {
		this.modalidad_venta = modalidad_venta;
	}
	public String getCodigo_producto() {
		return codigo_producto;
	}
	public void setCodigo_producto(String codigo_producto) {
		this.codigo_producto = codigo_producto;
	}
	
	public String getCodigo_sub_producto() {
		return codigo_sub_producto;
	}
	public void setCodigo_sub_producto(String codigo_sub_producto) {
		this.codigo_sub_producto = codigo_sub_producto;
	}
	public String getSub_producto() {
		return sub_producto;
	}
	public void setSub_producto(String sub_producto) {
		this.sub_producto = sub_producto;
	}
	public String getAdicional() {
		return adicional;
	}
	public void setAdicional(String adicional) {
		this.adicional = adicional;
	}
	public String getNumero_solicitud() {
		return numero_solicitud;
	}
	public void setNumero_solicitud(String numero_solicitud) {
		this.numero_solicitud = numero_solicitud;
	}
	public String getDoi() {
		return doi;
	}
	public void setDoi(String doi) {
		this.doi = doi;
	}
	public String getApellido_paterno_cliente() {
		return apellido_paterno_cliente;
	}
	public void setApellido_paterno_cliente(String apellido_paterno_cliente) {
		this.apellido_paterno_cliente = apellido_paterno_cliente;
	}
	public String getApellido_materno_cliente() {
		return apellido_materno_cliente;
	}
	public void setApellido_materno_cliente(String apellido_materno_cliente) {
		this.apellido_materno_cliente = apellido_materno_cliente;
	}
	public String getNombre_cliente() {
		return nombre_cliente;
	}
	public void setNombre_cliente(String nombre_cliente) {
		this.nombre_cliente = nombre_cliente;
	}
	public String getDireccion_cliente() {
		return direccion_cliente;
	}
	public void setDireccion_cliente(String direccion_cliente) {
		this.direccion_cliente = direccion_cliente;
	}
	public String getTelefono_cliente() {
		return telefono_cliente;
	}
	public void setTelefono_cliente(String telefono_cliente) {
		this.telefono_cliente = telefono_cliente;
	}
	public String getCelular_cliente() {
		return celular_cliente;
	}
	public void setCelular_cliente(String celular_cliente) {
		this.celular_cliente = celular_cliente;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getFecha_rvgl() {
		return fecha_rvgl;
	}
	public void setFecha_rvgl(String fecha_rvgl) {
		this.fecha_rvgl = fecha_rvgl;
	}
	public String getFecha_registro() {
		return fecha_registro;
	}
	public void setFecha_registro(String fecha_registro) {
		this.fecha_registro = fecha_registro;
	}
	public String getFecha_estado() {
		return fecha_estado;
	}
	public void setFecha_estado(String fecha_estado) {
		this.fecha_estado = fecha_estado;
	}
	public String getFecha_actual() {
		return fecha_actual;
	}
	public void setFecha_actual(String fecha_actual) {
		this.fecha_actual = fecha_actual;
	}
	public String getMoneda_solicitada() {
		return moneda_solicitada;
	}
	public void setMoneda_solicitada(String moneda_solicitada) {
		this.moneda_solicitada = moneda_solicitada;
	}
	public BigDecimal getMonto_solicitado() {
		return monto_solicitado;
	}
	public void setMonto_solicitado(BigDecimal monto_solicitado) {
		this.monto_solicitado = monto_solicitado;
	}
	public String getMoneda_aprobada() {
		return moneda_aprobada;
	}
	public void setMoneda_aprobada(String moneda_aprobada) {
		this.moneda_aprobada = moneda_aprobada;
	}
	public BigDecimal getMonto_aprobado() {
		return monto_aprobado;
	}
	public void setMonto_aprobado(BigDecimal monto_aprobado) {
		this.monto_aprobado = monto_aprobado;
	}
	public String getFecha_formalizacion_operacion() {
		return fecha_formalizacion_operacion;
	}
	public void setFecha_formalizacion_operacion(
			String fecha_formalizacion_operacion) {
		this.fecha_formalizacion_operacion = fecha_formalizacion_operacion;
	}
	public String getFecha_estado_comision() {
		return fecha_estado_comision;
	}
	public void setFecha_estado_comision(String fecha_estado_comision) {
		this.fecha_estado_comision = fecha_estado_comision;
	}
	public String getEstado_comision() {
		return estado_comision;
	}
	public void setEstado_comision(String estado_comision) {
		this.estado_comision = estado_comision;
	}
	public String getFecha_estado_altamira() {
		return fecha_estado_altamira;
	}
	public void setFecha_estado_altamira(String fecha_estado_altamira) {
		this.fecha_estado_altamira = fecha_estado_altamira;
	}
	public String getEstado_altamira() {
		return estado_altamira;
	}
	public void setEstado_altamira(String estado_altamira) {
		this.estado_altamira = estado_altamira;
	}
	public String getPropiedad_producto() {
		return propiedad_producto;
	}
	public void setPropiedad_producto(String propiedad_producto) {
		this.propiedad_producto = propiedad_producto;
	}
	public String getNumero_contrato() {
		return numero_contrato;
	}
	public void setNumero_contrato(String numero_contrato) {
		this.numero_contrato = numero_contrato;
	}
	public String getCodigo_jefe() {
		return codigo_jefe;
	}
	public void setCodigo_jefe(String codigo_jefe) {
		this.codigo_jefe = codigo_jefe;
	}
	public String getJefe() {
		return jefe;
	}
	public void setJefe(String jefe) {
		this.jefe = jefe;
	}
	public String getCodigo_ejecutivo() {
		return codigo_ejecutivo;
	}
	public void setCodigo_ejecutivo(String codigo_ejecutivo) {
		this.codigo_ejecutivo = codigo_ejecutivo;
	}
	public String getEjecutivo() {
		return ejecutivo;
	}
	public void setEjecutivo(String ejecutivo) {
		this.ejecutivo = ejecutivo;
	}
	public String getCodigo_oficina_asignada() {
		return codigo_oficina_asignada;
	}
	public void setCodigo_oficina_asignada(String codigo_oficina_asignada) {
		this.codigo_oficina_asignada = codigo_oficina_asignada;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getUltima_persona_atencion() {
		return ultima_persona_atencion;
	}
	public void setUltima_persona_atencion(String ultima_persona_atencion) {
		this.ultima_persona_atencion = ultima_persona_atencion;
	}
	public String getResponsable_actual() {
		return responsable_actual;
	}
	public void setResponsable_actual(String responsable_actual) {
		this.responsable_actual = responsable_actual;
	}
	public String getCliente_vip() {
		return cliente_vip;
	}
	public void setCliente_vip(String cliente_vip) {
		this.cliente_vip = cliente_vip;
	}
	public String getPago_haberes() {
		return pago_haberes;
	}
	public void setPago_haberes(String pago_haberes) {
		this.pago_haberes = pago_haberes;
	}
	public String getTipo_captacion() {
		return tipo_captacion;
	}
	public void setTipo_captacion(String tipo_captacion) {
		this.tipo_captacion = tipo_captacion;
	}
	public String getCanal_venta() {
		return canal_venta;
	}
	public void setCanal_venta(String canal_venta) {
		this.canal_venta = canal_venta;
	}
	public String getColectivo() {
		return colectivo;
	}
	public void setColectivo(String colectivo) {
		this.colectivo = colectivo;
	}
	public String getObservacion_riesgos() {
		return observacion_riesgos;
	}
	public void setObservacion_riesgos(String observacion_riesgos) {
		this.observacion_riesgos = observacion_riesgos;
	}
	public String getFondo_aperturado() {
		return fondo_aperturado;
	}
	public void setFondo_aperturado(String fondo_aperturado) {
		this.fondo_aperturado = fondo_aperturado;
	}
	public String getOrigen_fondo() {
		return origen_fondo;
	}
	public void setOrigen_fondo(String origen_fondo) {
		this.origen_fondo = origen_fondo;
	}
	public String getProcedencia_fondos() {
		return procedencia_fondos;
	}
	public void setProcedencia_fondos(String procedencia_fondos) {
		this.procedencia_fondos = procedencia_fondos;
	}
	public String getSegmento_promotor() {
		return segmento_promotor;
	}
	public void setSegmento_promotor(String segmento_promotor) {
		this.segmento_promotor = segmento_promotor;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getRazon_social() {
		return razon_social;
	}
	public void setRazon_social(String razon_social) {
		this.razon_social = razon_social;
	}
	public String getRuc() {
		return ruc;
	}
	public void setRuc(String ruc) {
		this.ruc = ruc;
	}
	public String getPrecio_cts() {
		return precio_cts;
	}
	public void setPrecio_cts(String precio_cts) {
		this.precio_cts = precio_cts;
	}
	public String getNombre_producto() {
		return nombre_producto;
	}
	public void setNombre_producto(String nombre_producto) {
		this.nombre_producto = nombre_producto;
	}
	public Boolean getEstadoMatch() {
		return estadoMatch;
	}
	public void setEstadoMatch(Boolean estadoMatch) {
		this.estadoMatch = estadoMatch;
	}
	public List<ExpedienteProducto> getListaExpedienteProducto() {
		return listaExpedienteProducto;
	}
	public void setListaExpedienteProducto(
			List<ExpedienteProducto> listaExpedienteProducto) {
		this.listaExpedienteProducto = listaExpedienteProducto;
	}
	public ExpedienteProducto getExpedienteProducto() {
		return expedienteProducto;
	}
	public void setExpedienteProducto(ExpedienteProducto expedienteProducto) {
		this.expedienteProducto = expedienteProducto;
	}
	public Integer getIdusuario_ejecutivo() {
		return idusuario_ejecutivo;
	}
	public void setIdusuario_ejecutivo(Integer idusuario_ejecutivo) {
		this.idusuario_ejecutivo = idusuario_ejecutivo;
	}
	public Integer getIdusuario_jefe() {
		return idusuario_jefe;
	}
	public void setIdusuario_jefe(Integer idusuario_jefe) {
		this.idusuario_jefe = idusuario_jefe;
	}
	public Usuario getUsuarioEjecutivo() {
		return usuarioEjecutivo;
	}
	public void setUsuarioEjecutivo(Usuario usuarioEjecutivo) {
		this.usuarioEjecutivo = usuarioEjecutivo;
	}
	public Usuario getUsuarioJefe() {
		return usuarioJefe;
	}
	public void setUsuarioJefe(Usuario usuarioJefe) {
		this.usuarioJefe = usuarioJefe;
	}
	
	public String getDescripcion_encontrado() {
		return descripcion_encontrado;
	}

	public void setDescripcion_encontrado(String descripcion_encontrado) {
		this.descripcion_encontrado = descripcion_encontrado;
	}
	public Integer getId_sub_producto() {
		return id_sub_producto;
	}
	public void setId_sub_producto(Integer id_sub_producto) {
		this.id_sub_producto = id_sub_producto;
	}
	public Integer getIdusuario_ejecutivo_campo() {
		return idusuario_ejecutivo_campo;
	}
	public void setIdusuario_ejecutivo_campo(Integer idusuario_ejecutivo_campo) {
		this.idusuario_ejecutivo_campo = idusuario_ejecutivo_campo;
	}
	public Integer getIdusuario_jefe_campo() {
		return idusuario_jefe_campo;
	}
	public void setIdusuario_jefe_campo(Integer idusuario_jefe_campo) {
		this.idusuario_jefe_campo = idusuario_jefe_campo;
	}
	public Integer getId_indicadores_call() {
		return id_indicadores_call;
	}
	public void setId_indicadores_call(Integer id_indicadores_call) {
		this.id_indicadores_call = id_indicadores_call;
	}
	public Integer getId_categoria_call() {
		return id_categoria_call;
	}
	public void setId_categoria_call(Integer id_categoria_call) {
		this.id_categoria_call = id_categoria_call;
	}
	public String getDes_tipo_planilla() {
		return des_tipo_planilla;
	}
	public void setDes_tipo_planilla(String des_tipo_planilla) {
		this.des_tipo_planilla = des_tipo_planilla;
	}
	public BigDecimal getMonto_soles() {
		return monto_soles;
	}
	public void setMonto_soles(BigDecimal monto_soles) {
		this.monto_soles = monto_soles;
	}
	public String getDepartamento() {
		return departamento;
	}
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	public String getModalidad() {
		return modalidad;
	}
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}
	public String getTipodocumento() {
		return tipodocumento;
	}
	public void setTipodocumento(String tipodocumento) {
		this.tipodocumento = tipodocumento;
	}
	public String getContrato() {
		return contrato;
	}
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
	public String getFechaalta() {
		return fechaalta;
	}
	public void setFechaalta(String fechaalta) {
		this.fechaalta = fechaalta;
	}
	public String getCodigocliente() {
		return codigocliente;
	}
	public void setCodigocliente(String codigocliente) {
		this.codigocliente = codigocliente;
	}
	public String getNrocontrato() {
		return nrocontrato;
	}
	public void setNrocontrato(String nrocontrato) {
		this.nrocontrato = nrocontrato;
	}
	public String getNrotarjeta() {
		return nrotarjeta;
	}
	public void setNrotarjeta(String nrotarjeta) {
		this.nrotarjeta = nrotarjeta;
	}
	public String getSituaciontarjeta() {
		return situaciontarjeta;
	}
	public void setSituaciontarjeta(String situaciontarjeta) {
		this.situaciontarjeta = situaciontarjeta;
	}
	public String getSituacionfacturacion() {
		return situacionfacturacion;
	}
	public void setSituacionfacturacion(String situacionfacturacion) {
		this.situacionfacturacion = situacionfacturacion;
	}
	public String getFechacancelaciontarj() {
		return fechacancelaciontarj;
	}
	public void setFechacancelaciontarj(String fechacancelaciontarj) {
		this.fechacancelaciontarj = fechacancelaciontarj;
	}
	public String getFechabloqueo() {
		return fechabloqueo;
	}
	public void setFechabloqueo(String fechabloqueo) {
		this.fechabloqueo = fechabloqueo;
	}
	public String getFechaactivacion() {
		return fechaactivacion;
	}
	public void setFechaactivacion(String fechaactivacion) {
		this.fechaactivacion = fechaactivacion;
	}
	public String getFechacancelacionprest() {
		return fechacancelacionprest;
	}
	public void setFechacancelacionprest(String fechacancelacionprest) {
		this.fechacancelacionprest = fechacancelacionprest;
	}
	public String getFechaliquidacion() {
		return fechaliquidacion;
	}
	public void setFechaliquidacion(String fechaliquidacion) {
		this.fechaliquidacion = fechaliquidacion;
	}
	public String getComision() {
		return comision;
	}
	public void setComision(String comision) {
		this.comision = comision;
	}
	public String getReglanegocio() {
		return reglanegocio;
	}
	public void setReglanegocio(String reglanegocio) {
		this.reglanegocio = reglanegocio;
	}
	public String getSolicitado() {
		return solicitado;
	}
	public void setSolicitado(String solicitado) {
		this.solicitado = solicitado;
	}
	public String getModoventa() {
		return modoventa;
	}
	public void setModoventa(String modoventa) {
		this.modoventa = modoventa;
	}
	public String getCanalventa() {
		return canalventa;
	}
	public void setCanalventa(String canalventa) {
		this.canalventa = canalventa;
	}
	public String getFechaformalizacion() {
		return fechaformalizacion;
	}
	public void setFechaformalizacion(String fechaformalizacion) {
		this.fechaformalizacion = fechaformalizacion;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getLiquidacion() {
		return liquidacion;
	}
	public void setLiquidacion(String liquidacion) {
		this.liquidacion = liquidacion;
	}
	public String getCaalc_cnro_solicitud() {
		return caalc_cnro_solicitud;
	}
	public void setCaalc_cnro_solicitud(String caalc_cnro_solicitud) {
		this.caalc_cnro_solicitud = caalc_cnro_solicitud;
	}
	public String getTc() {
		return tc;
	}
	public void setTc(String tc) {
		this.tc = tc;
	}
	public BigDecimal getBono_mi_vivienda() {
		return bono_mi_vivienda;
	}
	public void setBono_mi_vivienda(BigDecimal bono_mi_vivienda) {
		this.bono_mi_vivienda = bono_mi_vivienda;
	}
	public BigDecimal getMonto_total_soles() {
		return monto_total_soles;
	}
	public void setMonto_total_soles(BigDecimal monto_total_soles) {
		this.monto_total_soles = monto_total_soles;
	}
	public String getValidacion_colocacion() {
		return validacion_colocacion;
	}
	public void setValidacion_colocacion(String validacion_colocacion) {
		this.validacion_colocacion = validacion_colocacion;
	}
	public Date getFecha_sistema() {
		return fecha_sistema;
	}
	public void setFecha_sistema(Date fecha_sistema) {
		this.fecha_sistema = fecha_sistema;
	}
	public Integer getUsuario_sistema() {
		return usuario_sistema;
	}
	public void setUsuario_sistema(Integer usuario_sistema) {
		this.usuario_sistema = usuario_sistema;
	}
	public Integer getIdnegocio() {
		return idnegocio;
	}
	public void setIdnegocio(Integer idnegocio) {
		this.idnegocio = idnegocio;
	}
	public BigDecimal getComision_certicom() {
		return comision_certicom;
	}
	public void setComision_certicom(BigDecimal comision_certicom) {
		this.comision_certicom = comision_certicom.setScale(4, BigDecimal.ROUND_HALF_UP);
	}
	public BigDecimal getIgv() {
		return igv;
	}
	public void setIgv(BigDecimal igv) {
		this.igv = igv.setScale(4, BigDecimal.ROUND_HALF_UP);;
	}
	public BigDecimal getPor_comision_ejecutivo() {
		return por_comision_ejecutivo;
	}
	public void setPor_comision_ejecutivo(BigDecimal por_comision_ejecutivo) {
		this.por_comision_ejecutivo = por_comision_ejecutivo.setScale(4, BigDecimal.ROUND_HALF_UP);;
	}
	public BigDecimal getComision_ejecutivo() {
		return comision_ejecutivo;
	}
	public void setComision_ejecutivo(BigDecimal comision_ejecutivo) {
		this.comision_ejecutivo = comision_ejecutivo.setScale(4, BigDecimal.ROUND_HALF_UP);
	}
	public BigDecimal getPor_comision_certicom() {
		return por_comision_certicom;
	}
	public void setPor_comision_certicom(BigDecimal por_comision_certicom) {
		this.por_comision_certicom = por_comision_certicom.setScale(3, BigDecimal.ROUND_HALF_UP);
	}
	public BigDecimal getTotal_facturar() {
		return total_facturar;
	}
	public void setTotal_facturar(BigDecimal total_facturar) {
		this.total_facturar = total_facturar;
	}
	public Integer getExpediente_id() {
		return expediente_id;
	}
	public void setExpediente_id(Integer expediente_id) {
		this.expediente_id = expediente_id;
	}
	public BigDecimal getComisionjefe() {
		return comisionjefe;
	}
	public void setComisionjefe(BigDecimal comisionjefe) {
		this.comisionjefe = comisionjefe.setScale(4, BigDecimal.ROUND_HALF_UP);
	}
	public Usuario getUsuarioSCPFTrabajo() {
		return usuarioSCPFTrabajo;
	}
	public void setUsuarioSCPFTrabajo(Usuario usuarioSCPFTrabajo) {
		this.usuarioSCPFTrabajo = usuarioSCPFTrabajo;
	}
	public BigDecimal getMonto_descuento_ejecutivo() {
		return monto_descuento_ejecutivo;
	}
	public void setMonto_descuento_ejecutivo(BigDecimal monto_descuento_ejecutivo) {
		this.monto_descuento_ejecutivo = monto_descuento_ejecutivo;
	}
	public BigDecimal getMonto_descuento_supervisor() {
		return monto_descuento_supervisor;
	}
	public void setMonto_descuento_supervisor(BigDecimal monto_descuento_supervisor) {
		this.monto_descuento_supervisor = monto_descuento_supervisor;
	}
	public Integer getMes_descuento() {
		return mes_descuento;
	}
	public void setMes_descuento(Integer mes_descuento) {
		this.mes_descuento = mes_descuento;
	}
	public String getObservacion_descuento() {
		return observacion_descuento;
	}
	public void setObservacion_descuento(String observacion_descuento) {
		this.observacion_descuento = observacion_descuento;
	}
	public Integer getId_cargo() {
		return id_cargo;
	}
	public void setId_cargo(Integer id_cargo) {
		this.id_cargo = id_cargo;
	}
	public Usuario getUsuarioEjecutivoOriginal() {
		return usuarioEjecutivoOriginal;
	}
	public void setUsuarioEjecutivoOriginal(Usuario usuarioEjecutivoOriginal) {
		this.usuarioEjecutivoOriginal = usuarioEjecutivoOriginal;
	}
	public Usuario getUsuarioJefeOriginal() {
		return usuarioJefeOriginal;
	}
	public void setUsuarioJefeOriginal(Usuario usuarioJefeOriginal) {
		this.usuarioJefeOriginal = usuarioJefeOriginal;
	}
	public BigDecimal getComision_supervisor() {
		return comision_supervisor;
	}
	public void setComision_supervisor(BigDecimal comision_supervisor) {
		this.comision_supervisor = comision_supervisor.setScale(4, BigDecimal.ROUND_HALF_UP);
	}
	public BigDecimal getComision_coordinador() {
		return comision_coordinador;
	}
	public void setComision_coordinador(BigDecimal comision_coordinador) {
		this.comision_coordinador = comision_coordinador.setScale(4, BigDecimal.ROUND_HALF_UP);
	}
	public BigDecimal getComision_back() {
		return comision_back;
	}
	public void setComision_back(BigDecimal comision_back) {
		this.comision_back = comision_back.setScale(4, BigDecimal.ROUND_HALF_UP);
	}
	public Integer getIdusuario_coordinador() {
		return idusuario_coordinador;
	}
	public void setIdusuario_coordinador(Integer idusuario_coordinador) {
		this.idusuario_coordinador = idusuario_coordinador;
	}
	public Integer getIdusuario_back() {
		return idusuario_back;
	}
	public void setIdusuario_back(Integer idusuario_back) {
		this.idusuario_back = idusuario_back;
	}
	public BigDecimal getPor_comision_supervisor() {
		return por_comision_supervisor;
	}
	public void setPor_comision_supervisor(BigDecimal por_comision_supervisor) {
		this.por_comision_supervisor = por_comision_supervisor;
	}
	public Integer getIdusuario_supervisor_campo() {
		return idusuario_supervisor_campo;
	}
	public void setIdusuario_supervisor_campo(Integer idusuario_supervisor_campo) {
		this.idusuario_supervisor_campo = idusuario_supervisor_campo;
	}
	public BigDecimal getComision_ejecutivo_campo() {
		return comision_ejecutivo_campo;
	}
	public void setComision_ejecutivo_campo(BigDecimal comision_ejecutivo_campo) {
		this.comision_ejecutivo_campo = comision_ejecutivo_campo;
	}
	public BigDecimal getComision_supervisor_campo() {
		return comision_supervisor_campo;
	}
	public void setComision_supervisor_campo(BigDecimal comision_supervisor_campo) {
		this.comision_supervisor_campo = comision_supervisor_campo;
	}
	public BigDecimal getPor_comision_coordinador() {
		return por_comision_coordinador;
	}
	public void setPor_comision_coordinador(BigDecimal por_comision_coordinador) {
		this.por_comision_coordinador = por_comision_coordinador;
	}
	public double getMeta_supervisor() {
		return meta_supervisor;
	}
	public void setMeta_supervisor(double meta_supervisor) {
		this.meta_supervisor = meta_supervisor;
	}
	public double getMeta_coordinador() {
		return meta_coordinador;
	}
	public void setMeta_coordinador(double meta_coordinador) {
		this.meta_coordinador = meta_coordinador;
	}
	public Usuario getUsuarioCoordinador() {
		return usuarioCoordinador;
	}
	public void setUsuarioCoordinador(Usuario usuarioCoordinador) {
		this.usuarioCoordinador = usuarioCoordinador;
	}
	public Usuario getUsuarioEjecutivoCampo() {
		return usuarioEjecutivoCampo;
	}
	public void setUsuarioEjecutivoCampo(Usuario usuarioEjecutivoCampo) {
		this.usuarioEjecutivoCampo = usuarioEjecutivoCampo;
	}
	public Usuario getUsuarioSupervisorCampo() {
		return usuarioSupervisorCampo;
	}
	public void setUsuarioSupervisorCampo(Usuario usuarioSupervisorCampo) {
		this.usuarioSupervisorCampo = usuarioSupervisorCampo;
	}
	public boolean isEstado_validacion() {
		return estado_validacion;
	}
	public void setEstado_validacion(boolean estado_validacion) {
		this.estado_validacion = estado_validacion;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getCodigo_oficina() {
		return codigo_oficina;
	}
	public void setCodigo_oficina(String codigo_oficina) {
		this.codigo_oficina = codigo_oficina;
	}
	public BigDecimal getPor_comision_ejecutivo_campo() {
		return por_comision_ejecutivo_campo;
	}
	public void setPor_comision_ejecutivo_campo(
			BigDecimal por_comision_ejecutivo_campo) {
		this.por_comision_ejecutivo_campo = por_comision_ejecutivo_campo;
	}
	public BigDecimal getPor_comision_supervisor_campo() {
		return por_comision_supervisor_campo;
	}
	public void setPor_comision_supervisor_campo(
			BigDecimal por_comision_supervisor_campo) {
		this.por_comision_supervisor_campo = por_comision_supervisor_campo;
	}
	public BigDecimal getPor_comision_back() {
		return por_comision_back;
	}
	public void setPor_comision_back(BigDecimal por_comision_back) {
		this.por_comision_back = por_comision_back;
	}
	public Integer getIdplanilla_ejecutivo() {
		return idplanilla_ejecutivo;
	}
	public void setIdplanilla_ejecutivo(Integer idplanilla_ejecutivo) {
		this.idplanilla_ejecutivo = idplanilla_ejecutivo;
	}
	public Integer getIdplanilla_supervisor() {
		return idplanilla_supervisor;
	}
	public void setIdplanilla_supervisor(Integer idplanilla_supervisor) {
		this.idplanilla_supervisor = idplanilla_supervisor;
	}
	public Integer getIdplanilla_ejecutivo_campo() {
		return idplanilla_ejecutivo_campo;
	}
	public void setIdplanilla_ejecutivo_campo(Integer idplanilla_ejecutivo_campo) {
		this.idplanilla_ejecutivo_campo = idplanilla_ejecutivo_campo;
	}
	public Integer getIdplanilla_supervisor_campo() {
		return idplanilla_supervisor_campo;
	}
	public void setIdplanilla_supervisor_campo(Integer idplanilla_supervisor_campo) {
		this.idplanilla_supervisor_campo = idplanilla_supervisor_campo;
	}
	public Integer getIdplanilla_coordinador() {
		return idplanilla_coordinador;
	}
	public void setIdplanilla_coordinador(Integer idplanilla_coordinador) {
		this.idplanilla_coordinador = idplanilla_coordinador;
	}
	public Integer getIdplanilla_back() {
		return idplanilla_back;
	}
	public void setIdplanilla_back(Integer idplanilla_back) {
		this.idplanilla_back = idplanilla_back;
	}
	

	public Usuario getUsuarioBackOffice() {
		return usuarioBackOffice;
	}
	public void setUsuarioBackOffice(Usuario usuarioBackOffice) {
		this.usuarioBackOffice = usuarioBackOffice;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public String getRango() {
		return rango;
	}
	public void setRango(String rango) {
		this.rango = rango;
	}
	public String getId_producto_factura() {
		return id_producto_factura;
	}
	public void setId_producto_factura(String id_producto_factura) {
		this.id_producto_factura = id_producto_factura;
	}
	public String getSegmento() {
		return segmento;
	}
	public void setSegmento(String segmento) {
		this.segmento = segmento;
	}
	public String getTipooperacion() {
		return tipooperacion;
	}
	public void setTipooperacion(String tipooperacion) {
		this.tipooperacion = tipooperacion;
	}
	public String getRango_dias() {
		return rango_dias;
	}
	public void setRango_dias(String rango_dias) {
		this.rango_dias = rango_dias;
	}
	public Integer getNro_ope_eje() {
		return nro_ope_eje;
	}
	public void setNro_ope_eje(Integer nro_ope_eje) {
		this.nro_ope_eje = nro_ope_eje;
	}
	public double getMonto_consolidado_eje() {
		return monto_consolidado_eje;
	}
	public void setMonto_consolidado_eje(double monto_consolidado_eje) {
		this.monto_consolidado_eje = monto_consolidado_eje;
	}
	public Integer getNro_ope_sp() {
		return nro_ope_sp;
	}
	public void setNro_ope_sp(Integer nro_ope_sp) {
		this.nro_ope_sp = nro_ope_sp;
	}
	public double getMonto_consolidado_sp() {
		return monto_consolidado_sp;
	}
	public void setMonto_consolidado_sp(double monto_consolidado_sp) {
		this.monto_consolidado_sp = monto_consolidado_sp;
	}
	public String getDes_negocio() {
		return des_negocio;
	}
	public void setDes_negocio(String des_negocio) {
		this.des_negocio = des_negocio;
	}
	public String getDes_producto() {
		return des_producto;
	}
	public void setDes_producto(String des_producto) {
		this.des_producto = des_producto;
	}
	public double getMonto_facturado() {
		return monto_facturado;
	}
	public void setMonto_facturado(double monto_facturado) {
		this.monto_facturado = monto_facturado;
	}
	public double getMonto_pagado() {
		return monto_pagado;
	}
	public void setMonto_pagado(double monto_pagado) {
		this.monto_pagado = monto_pagado;
	}
	public Integer getCant_operaciones() {
		return cant_operaciones;
	}
	public void setCant_operaciones(Integer cant_operaciones) {
		this.cant_operaciones = cant_operaciones;
	}
	public double getMonto_desembolsado() {
		return monto_desembolsado;
	}
	public void setMonto_desembolsado(double monto_desembolsado) {
		this.monto_desembolsado = monto_desembolsado;
	}
	public String getUsuarioAsignado() {
		return usuarioAsignado;
	}
	public void setUsuarioAsignado(String usuarioAsignado) {
		this.usuarioAsignado = usuarioAsignado;
	}
	public String getRuc_empresa() {
		return ruc_empresa;
	}
	public void setRuc_empresa(String ruc_empresa) {
		this.ruc_empresa = ruc_empresa;
	}
	public String getEmpresa_ph() {
		return empresa_ph;
	}
	public void setEmpresa_ph(String empresa_ph) {
		this.empresa_ph = empresa_ph;
	}


	
	
	
	
}
