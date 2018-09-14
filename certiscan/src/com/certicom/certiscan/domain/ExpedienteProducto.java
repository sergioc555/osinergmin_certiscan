package com.certicom.certiscan.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class ExpedienteProducto {

	private Integer id_expediente_producto;
	private Integer expedienteid;
	private Integer productoid;
	private Integer usuarioid;
	private Date periodo;
	private Estados estado;
	private Date fecha_asignacion;
	private Integer idEstado;
	private Integer idCategoriaCall;
	private Integer id_indicadores_call;
	private Expediente expediente = new Expediente();
	private Usuario usuario;
	private Boolean flgcklist;
	private Integer totPendiente;
	private Integer totEnProceso;
	private Integer totConcluido;

	private Integer idusuario;
	private Integer cant_exp;
	private String nom_promotor;
	private String nom_oficina;
	private String estado_envioph;
	private String ruc;

	private boolean estadoExpediente;
	private Integer idEstadoExpediente;
	private Integer idEstadoFacturacion;

	// campos para filtros
	private Integer negocio;
	private Integer product;
	private Integer anio;
	private Integer mes;

	// Transitorias
	private String nombreSupervisor;
	private String nombreCoordinador;
	private String estadoBase;
	private Boolean estadoNuevo;
	private String nombreOficina;
	private String nombre_ejecutivo;

	// para reporte vip
	private Integer cantidad_lima;
	private BigDecimal total_lima;
	private Integer cantidad_provincia;
	private BigDecimal total_provincia;
	private String rango_cantidad;
	private Integer cantidad_nacional;
	private BigDecimal total_nacional;

	// para semaforo
	private Integer cant_pendiente;
	private BigDecimal mont_pendiente;
	private String per_pendiente;
	private BigDecimal bd_porpediente;
	private Integer cant_proceso;
	private BigDecimal mont_proceso;
	private String per_proceso;
	private BigDecimal bd_porproceso;
	private Integer cant_concluido;
	private BigDecimal mont_concluido;
	private String per_concluido;
	private BigDecimal bd_porconcluido;
	private Integer cant_asignado;
	private BigDecimal mont_asignado;
	private String per_asignado;
	private BigDecimal bd_porasignado;
	private Integer cant_presentados;

	// para controlcalidad
	private String des_productos;
	private String des_empresa;
	private String des_negocio;
	private String des_sub_productos;

	// para rpt gestion de llamadas;
	private String cod_banco;
	private String num_doc;
	private String nom_cliente;
	private String territorio_ofc;
	private BigDecimal prestamo_soles;
	private String des_estado;
	private String nom_supervisor;
	private String destipo;
	private String estado_rescont;
	private String rc_telefono;
	private Date rc_fecha;
	private String rc_comentario;
	private String descomentario;
	private String rc_estado_rescont;

	// Transitorias:
	private Integer idUsuarioSupervisor;
	private String cod_oficina;
	private String descripcionIndicador;
	private String ultima_cita;
	private String telefonos_contacto;
	private List<Expediente> listExpedPresentados;

	private Integer id_estado_padre;

	private String modalidad_empresa; // refleja el campo de la tabla expediente
	private String moneda_empresa; // refleja el campo de la tabla expediente
	private String tipo_empresa; // refleja el campo de tipo empresa
	private Date fecha_envio_calidad; // refleja el campo de fecha envio calidad
	private Date fecha_envio_banco; // refleja el campo de fecha envio banco
	private Integer cant_aperturado; // transient
	private Integer cant_rechazado; // transient

	private Integer id_sub_producto; // transient
	private String sub_productoDesc; // transient

	private Integer id_expediente_cuentas_envios;
	
	
//	para exoediente_cuentas_envio
	
	private Integer ece_nacional;
	private String ece_nacionalidad;
	private String ece_pais;
	private String ece_nombres;
	private String ece_ape_paterno;
	private String ece_ape_materno;
	private Date ece_fec_nacimiento;
	private String ece_carnet_extranjeria;
	private String ece_direccion;
	private Date ece_fec_caducidad;
	
	private String  usuario_campo;
	private String supervisor_campo;
	
	private String provincia;
	
	
	private String descripcion_archivo;
	private String conces;
	
	private String com_t1;
	private String com_t2;
	
	private Integer id_vendedor;

	public ExpedienteProducto() {
	}

	public String getTelefonos_contacto() {
		return telefonos_contacto;
	}

	public void setTelefonos_contacto(String telefonos_contacto) {
		this.telefonos_contacto = telefonos_contacto;
	}

	public Integer getExpedienteid() {
		return expedienteid;
	}

	public void setExpedienteid(Integer expedienteid) {
		this.expedienteid = expedienteid;
	}

	public Integer getProductoid() {
		return productoid;
	}

	public void setProductoid(Integer productoid) {
		this.productoid = productoid;
	}

	public Integer getUsuarioid() {
		return usuarioid;
	}

	public void setUsuarioid(Integer usuarioid) {
		this.usuarioid = usuarioid;
	}

	public Date getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Date periodo) {
		this.periodo = periodo;
	}

	public Estados getEstado() {
		return estado;
	}

	public void setEstado(Estados estado) {
		this.estado = estado;
	}

	public Date getFecha_asignacion() {
		return fecha_asignacion;
	}

	public void setFecha_asignacion(Date fecha_asignacion) {
		this.fecha_asignacion = fecha_asignacion;
	}

	public Expediente getExpediente() {
		return expediente;
	}

	public void setExpediente(Expediente expediente) {
		this.expediente = expediente;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Integer getId_expediente_producto() {
		return id_expediente_producto;
	}

	public void setId_expediente_producto(Integer id_expediente_producto) {
		this.id_expediente_producto = id_expediente_producto;
	}

	public Integer getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Integer idEstado) {
		this.idEstado = idEstado;
	}

	public Integer getIdCategoriaCall() {
		return idCategoriaCall;
	}

	public void setIdCategoriaCall(Integer idCategoriaCall) {
		this.idCategoriaCall = idCategoriaCall;
	}

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public Integer getCant_exp() {
		return cant_exp;
	}

	public void setCant_exp(Integer cant_exp) {
		this.cant_exp = cant_exp;
	}

	public String getNom_promotor() {
		return nom_promotor;
	}

	public void setNom_promotor(String nom_promotor) {
		this.nom_promotor = nom_promotor;
	}

	public Integer getNegocio() {
		return negocio;
	}

	public void setNegocio(Integer negocio) {
		this.negocio = negocio;
	}

	public Integer getProduct() {
		return product;
	}

	public void setProduct(Integer product) {
		this.product = product;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public String getNombreSupervisor() {
		return nombreSupervisor;
	}

	public void setNombreSupervisor(String nombreSupervisor) {
		this.nombreSupervisor = nombreSupervisor;
	}

	public String getNombreCoordinador() {
		return nombreCoordinador;
	}

	public void setNombreCoordinador(String nombreCoordinador) {
		this.nombreCoordinador = nombreCoordinador;
	}

	public String getEstadoBase() {
		return estadoBase;
	}

	public void setEstadoBase(String estadoBase) {
		this.estadoBase = estadoBase;
	}

	public Boolean getEstadoNuevo() {
		return estadoNuevo;
	}

	public void setEstadoNuevo(Boolean estadoNuevo) {
		this.estadoNuevo = estadoNuevo;
	}

	public boolean isEstadoExpediente() {
		return estadoExpediente;
	}

	public void setEstadoExpediente(boolean estadoExpediente) {
		this.estadoExpediente = estadoExpediente;
	}

	public Integer getIdEstadoExpediente() {
		return idEstadoExpediente;
	}

	public void setIdEstadoExpediente(Integer idEstadoExpediente) {
		this.idEstadoExpediente = idEstadoExpediente;
	}

	public Integer getIdEstadoFacturacion() {
		return idEstadoFacturacion;
	}

	public void setIdEstadoFacturacion(Integer idEstadoFacturacion) {
		this.idEstadoFacturacion = idEstadoFacturacion;
	}

	public Integer getId_indicadores_call() {
		return id_indicadores_call;
	}

	public void setId_indicadores_call(Integer id_indicadores_call) {
		this.id_indicadores_call = id_indicadores_call;
	}

	public String getNombreOficina() {
		return nombreOficina;
	}

	public void setNombreOficina(String nombreOficina) {
		this.nombreOficina = nombreOficina;
	}

	public Integer getCantidad_lima() {
		return cantidad_lima;
	}

	public void setCantidad_lima(Integer cantidad_lima) {
		this.cantidad_lima = cantidad_lima;
	}

	public BigDecimal getTotal_lima() {
		return total_lima;
	}

	public void setTotal_lima(BigDecimal total_lima) {
		this.total_lima = total_lima;
	}

	public Integer getCantidad_provincia() {
		return cantidad_provincia;
	}

	public void setCantidad_provincia(Integer cantidad_provincia) {
		this.cantidad_provincia = cantidad_provincia;
	}

	public BigDecimal getTotal_provincia() {
		return total_provincia;
	}

	public void setTotal_provincia(BigDecimal total_provincia) {
		this.total_provincia = total_provincia;
	}

	public String getRango_cantidad() {
		return rango_cantidad;
	}

	public void setRango_cantidad(String rango_cantidad) {
		this.rango_cantidad = rango_cantidad;
	}

	public Integer getCantidad_nacional() {
		return cantidad_nacional;
	}

	public void setCantidad_nacional(Integer cantidad_nacional) {
		this.cantidad_nacional = cantidad_nacional;
	}

	public BigDecimal getTotal_nacional() {
		return total_nacional;
	}

	public void setTotal_nacional(BigDecimal total_nacional) {
		this.total_nacional = total_nacional;
	}

	public String getNombre_ejecutivo() {
		return nombre_ejecutivo;
	}

	public void setNombre_ejecutivo(String nombre_ejecutivo) {
		this.nombre_ejecutivo = nombre_ejecutivo;
	}

	public Integer getCant_pendiente() {
		return cant_pendiente;
	}

	public void setCant_pendiente(Integer cant_pendiente) {
		this.cant_pendiente = cant_pendiente;
	}

	public BigDecimal getMont_pendiente() {
		return mont_pendiente;
	}

	public void setMont_pendiente(BigDecimal mont_pendiente) {
		this.mont_pendiente = mont_pendiente;
	}

	public Integer getCant_proceso() {
		return cant_proceso;
	}

	public void setCant_proceso(Integer cant_proceso) {
		this.cant_proceso = cant_proceso;
	}

	public BigDecimal getMont_proceso() {
		return mont_proceso;
	}

	public void setMont_proceso(BigDecimal mont_proceso) {
		this.mont_proceso = mont_proceso;
	}

	public Integer getCant_concluido() {
		return cant_concluido;
	}

	public void setCant_concluido(Integer cant_concluido) {
		this.cant_concluido = cant_concluido;
	}

	public BigDecimal getMont_concluido() {
		return mont_concluido;
	}

	public void setMont_concluido(BigDecimal mont_concluido) {
		this.mont_concluido = mont_concluido;
	}

	public Integer getCant_asignado() {
		return cant_asignado;
	}

	public void setCant_asignado(Integer cant_asignado) {
		this.cant_asignado = cant_asignado;
	}

	public BigDecimal getMont_asignado() {
		return mont_asignado;
	}

	public void setMont_asignado(BigDecimal mont_asignado) {
		this.mont_asignado = mont_asignado;
	}

	public String getPer_pendiente() {
		return per_pendiente;
	}

	public void setPer_pendiente(String per_pendiente) {
		this.per_pendiente = per_pendiente;
	}

	public String getPer_proceso() {
		return per_proceso;
	}

	public void setPer_proceso(String per_proceso) {
		this.per_proceso = per_proceso;
	}

	public String getPer_concluido() {
		return per_concluido;
	}

	public void setPer_concluido(String per_concluido) {
		this.per_concluido = per_concluido;
	}

	public String getPer_asignado() {
		return per_asignado;
	}

	public void setPer_asignado(String per_asignado) {
		this.per_asignado = per_asignado;
	}

	public String getDes_productos() {
		return des_productos;
	}

	public void setDes_productos(String des_productos) {
		this.des_productos = des_productos;
	}

	public String getDes_empresa() {
		return des_empresa;
	}

	public void setDes_empresa(String des_empresa) {
		this.des_empresa = des_empresa;
	}


	public String getCod_banco() {
		return cod_banco;
	}

	public void setCod_banco(String cod_banco) {
		this.cod_banco = cod_banco;
	}

	public String getNum_doc() {
		return num_doc;
	}

	public void setNum_doc(String num_doc) {
		this.num_doc = num_doc;
	}

	public String getNom_cliente() {
		return nom_cliente;
	}

	public void setNom_cliente(String nom_cliente) {
		this.nom_cliente = nom_cliente;
	}

	public String getTerritorio_ofc() {
		return territorio_ofc;
	}

	public void setTerritorio_ofc(String territorio_ofc) {
		this.territorio_ofc = territorio_ofc;
	}

	public BigDecimal getPrestamo_soles() {
		return prestamo_soles;
	}

	public void setPrestamo_soles(BigDecimal prestamo_soles) {
		this.prestamo_soles = prestamo_soles;
	}

	public String getDes_estado() {
		return des_estado;
	}

	public void setDes_estado(String des_estado) {
		this.des_estado = des_estado;
	}

	public String getNom_supervisor() {
		return nom_supervisor;
	}

	public void setNom_supervisor(String nom_supervisor) {
		this.nom_supervisor = nom_supervisor;
	}

	public String getDestipo() {
		return destipo;
	}

	public String getEstado_rescont() {
		return estado_rescont;
	}

	public void setEstado_rescont(String estado_rescont) {
		this.estado_rescont = estado_rescont;
	}

	public Integer getIdUsuarioSupervisor() {
		return idUsuarioSupervisor;
	}

	public void setIdUsuarioSupervisor(Integer idUsuarioSupervisor) {
		this.idUsuarioSupervisor = idUsuarioSupervisor;
	}

	public void setDestipo(String destipo) {
		this.destipo = destipo;
	}

	public String getRc_telefono() {
		return rc_telefono;
	}

	public void setRc_telefono(String rc_telefono) {
		this.rc_telefono = rc_telefono;
	}

	public String getRc_comentario() {
		return rc_comentario;
	}

	public void setRc_comentario(String rc_comentario) {
		this.rc_comentario = rc_comentario;
	}

	public String getRc_estado_rescont() {
		return rc_estado_rescont;
	}

	public void setRc_estado_rescont(String rc_estado_rescont) {
		this.rc_estado_rescont = rc_estado_rescont;
	}

	public Date getRc_fecha() {
		return rc_fecha;
	}

	public void setRc_fecha(Date rc_fecha) {
		this.rc_fecha = rc_fecha;
	}

	public Integer getCant_presentados() {
		return cant_presentados;
	}

	public void setCant_presentados(Integer cant_presentados) {
		this.cant_presentados = cant_presentados;
	}

	public List<Expediente> getListExpedPresentados() {
		return listExpedPresentados;
	}

	public void setListExpedPresentados(List<Expediente> listExpedPresentados) {
		this.listExpedPresentados = listExpedPresentados;
	}

	public String getDescripcionIndicador() {
		return descripcionIndicador;
	}

	public void setDescripcionIndicador(String descripcionIndicador) {
		this.descripcionIndicador = descripcionIndicador;
	}

	public JRBeanCollectionDataSource getExpPresentadosDS() {
		return new JRBeanCollectionDataSource(this.listExpedPresentados, false);
	}

	public String getDes_negocio() {
		return des_negocio;
	}

	public void setDes_negocio(String des_negocio) {
		this.des_negocio = des_negocio;
	}

	public BigDecimal getBd_porpediente() {
		return bd_porpediente;
	}

	public void setBd_porpediente(BigDecimal bd_porpediente) {
		this.bd_porpediente = bd_porpediente;
	}

	public BigDecimal getBd_porproceso() {
		return bd_porproceso;
	}

	public void setBd_porproceso(BigDecimal bd_porproceso) {
		this.bd_porproceso = bd_porproceso;
	}

	public BigDecimal getBd_porconcluido() {
		return bd_porconcluido;
	}

	public void setBd_porconcluido(BigDecimal bd_porconcluido) {
		this.bd_porconcluido = bd_porconcluido;
	}

	public BigDecimal getBd_porasignado() {
		return bd_porasignado;
	}

	public void setBd_porasignado(BigDecimal bd_porasignado) {
		this.bd_porasignado = bd_porasignado;
	}

	public String getUltima_cita() {
		return ultima_cita;
	}

	public void setUltima_cita(String ultima_cita) {
		this.ultima_cita = ultima_cita;
	}

	public String getCod_oficina() {
		return cod_oficina;
	}

	public void setCod_oficina(String cod_oficina) {
		this.cod_oficina = cod_oficina;
	}

	public String getNom_oficina() {
		return nom_oficina;
	}

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc;
	}

	public void setNom_oficina(String nom_oficina) {
		this.nom_oficina = nom_oficina;
	}

	public Integer getId_estado_padre() {
		return id_estado_padre;
	}

	public void setId_estado_padre(Integer id_estado_padre) {
		this.id_estado_padre = id_estado_padre;
	}

	public String getEstado_envioph() {
		return estado_envioph;
	}

	public void setEstado_envioph(String estado_envioph) {
		this.estado_envioph = estado_envioph;
	}

	public String getModalidad_empresa() {
		return modalidad_empresa;
	}

	public void setModalidad_empresa(String modalidad_empresa) {
		this.modalidad_empresa = modalidad_empresa;
	}

	public String getMoneda_empresa() {
		return moneda_empresa;
	}

	public void setMoneda_empresa(String moneda_empresa) {
		this.moneda_empresa = moneda_empresa;
	}

	public String getTipo_empresa() {
		return tipo_empresa;
	}

	public void setTipo_empresa(String tipo_empresa) {
		this.tipo_empresa = tipo_empresa;
	}

	public Date getFecha_envio_calidad() {
		return fecha_envio_calidad;
	}

	public void setFecha_envio_calidad(Date fecha_envio_calidad) {
		this.fecha_envio_calidad = fecha_envio_calidad;
	}

	public Date getFecha_envio_banco() {
		return fecha_envio_banco;
	}

	public void setFecha_envio_banco(Date fecha_envio_banco) {
		this.fecha_envio_banco = fecha_envio_banco;
	}

	public Integer getCant_aperturado() {
		return cant_aperturado;
	}

	public void setCant_aperturado(Integer cant_aperturado) {
		this.cant_aperturado = cant_aperturado;
	}

	public Integer getId_expediente_cuentas_envios() {
		return id_expediente_cuentas_envios;
	}

	public void setId_expediente_cuentas_envios(Integer id_expediente_cuentas_envios) {
		this.id_expediente_cuentas_envios = id_expediente_cuentas_envios;
	}

	public Integer getCant_rechazado() {
		return cant_rechazado;
	}

	public void setCant_rechazado(Integer cant_rechazado) {
		this.cant_rechazado = cant_rechazado;
	}

	public Integer getId_sub_producto() {
		return id_sub_producto;
	}

	public void setId_sub_producto(Integer id_sub_producto) {
		this.id_sub_producto = id_sub_producto;
	}

	public String getSub_productoDesc() {
		return sub_productoDesc;
	}

	public void setSub_productoDesc(String sub_productoDesc) {
		this.sub_productoDesc = sub_productoDesc;
	}

	public String getDes_sub_productos() {
		return des_sub_productos;
	}

	public void setDes_sub_productos(String des_sub_productos) {
		this.des_sub_productos = des_sub_productos;
	}

	public Integer getEce_nacional() {
		return ece_nacional;
	}

	public void setEce_nacional(Integer ece_nacional) {
		this.ece_nacional = ece_nacional;
	}

	public String getEce_nacionalidad() {
		return ece_nacionalidad;
	}

	public void setEce_nacionalidad(String ece_nacionalidad) {
		this.ece_nacionalidad = ece_nacionalidad;
	}

	public String getEce_pais() {
		return ece_pais;
	}

	public void setEce_pais(String ece_pais) {
		this.ece_pais = ece_pais;
	}

	public String getEce_nombres() {
		return ece_nombres;
	}

	public void setEce_nombres(String ece_nombres) {
		this.ece_nombres = ece_nombres;
	}

	public String getEce_ape_paterno() {
		return ece_ape_paterno;
	}

	public void setEce_ape_paterno(String ece_ape_paterno) {
		this.ece_ape_paterno = ece_ape_paterno;
	}

	public String getEce_ape_materno() {
		return ece_ape_materno;
	}

	public void setEce_ape_materno(String ece_ape_materno) {
		this.ece_ape_materno = ece_ape_materno;
	}

	public Date getEce_fec_nacimiento() {
		return ece_fec_nacimiento;
	}

	public void setEce_fec_nacimiento(Date ece_fec_nacimiento) {
		this.ece_fec_nacimiento = ece_fec_nacimiento;
	}

	public String getEce_carnet_extranjeria() {
		return ece_carnet_extranjeria;
	}

	public void setEce_carnet_extranjeria(String ece_carnet_extranjeria) {
		this.ece_carnet_extranjeria = ece_carnet_extranjeria;
	}

	public String getEce_direccion() {
		return ece_direccion;
	}

	public void setEce_direccion(String ece_direccion) {
		this.ece_direccion = ece_direccion;
	}

	public Date getEce_fec_caducidad() {
		return ece_fec_caducidad;
	}

	public void setEce_fec_caducidad(Date ece_fec_caducidad) {
		this.ece_fec_caducidad = ece_fec_caducidad;
	}

	public String getUsuario_campo() {
		return usuario_campo;
	}

	public void setUsuario_campo(String usuario_campo) {
		this.usuario_campo = usuario_campo;
	}

	public String getSupervisor_campo() {
		return supervisor_campo;
	}

	public void setSupervisor_campo(String supervisor_campo) {
		this.supervisor_campo = supervisor_campo;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getDescripcion_archivo() {
		return descripcion_archivo;
	}

	public void setDescripcion_archivo(String descripcion_archivo) {
		this.descripcion_archivo = descripcion_archivo;
	}

	public Boolean getFlgcklist() {
		return flgcklist;
	}

	public void setFlgcklist(Boolean flgcklist) {
		this.flgcklist = flgcklist;
	}

	public String getConces() {
		return conces;
	}

	public void setConces(String conces) {
		this.conces = conces;
	}

	public String getDescomentario() {
		return descomentario;
	}

	public void setDescomentario(String descomentario) {
		this.descomentario = descomentario;
	}

	public Integer getTotPendiente() {
		return totPendiente;
	}

	public void setTotPendiente(Integer totPendiente) {
		this.totPendiente = totPendiente;
	}

	public Integer getTotEnProceso() {
		return totEnProceso;
	}

	public void setTotEnProceso(Integer totEnProceso) {
		this.totEnProceso = totEnProceso;
	}

	public Integer getTotConcluido() {
		return totConcluido;
	}

	public void setTotConcluido(Integer totConcluido) {
		this.totConcluido = totConcluido;
	}

	public String getCom_t1() {
		return com_t1;
	}

	public void setCom_t1(String com_t1) {
		this.com_t1 = com_t1;
	}

	public String getCom_t2() {
		return com_t2;
	}

	public void setCom_t2(String com_t2) {
		this.com_t2 = com_t2;
	}

	public Integer getId_vendedor() {
		return id_vendedor;
	}

	public void setId_vendedor(Integer id_vendedor) {
		this.id_vendedor = id_vendedor;
	}

	

	
}
