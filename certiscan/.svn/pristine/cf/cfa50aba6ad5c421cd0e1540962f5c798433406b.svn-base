package com.certicom.certiscan.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.primefaces.model.StreamedContent;

public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int idusuario;
	private String dni;
	private String nombre;
	private String apellido_paterno;
	private String apellido_materno;
	private String login;
	private String password;
	private String email;
	private String direccion;
	private Boolean estado;
	private Date fecha_acceso;
	private Date fecha_registro;
	private Date fecha_mod;
	private int cod_perfil;
	private Boolean es_nuevo;
	private Date fecha_cambio_password;
	private String fecha_cambio_pass;
	private Integer id_empresa;
	private Integer id_proyecto;
	private Integer id_negocio;
	private Integer id_cargo;
	private Integer id_planilla;
	private Integer id_oficina;
	private byte[] imagen;
	private Date fecha_cese;
	private String motivo_cese;
	private Boolean estado_planilla;
	private String funcion;
	private String rpm;
	private String rpc;
	private String telefono;
	private String codigoBanco; //el de ejecutivo
	private String codigoBancoSupervisor; // el de supervisor
	private String codigo_quality; // codigo del sistema quality
	private Integer dias_vacaciones;
	private Integer dias_descanso;
	private Integer idproducto;
	private String usuarioAsignado;
	private Integer totalExpedientes;
	private Integer totalSolicitudes;
	private Integer totalPaginasExpedientes;
	private Integer totalPaginasSolicitudes;
	
	private String producto_principal;
	private String otros_productos;
	private String negocio_asignado;

	/* Para ONP */

	private String nombre_completo;
	private int id_centro_atencion;
	private String nombre_centro_atencion;
	private int nro_impresiones;
	private int nro_consultas;
	private String desEmpresa;
	private String desNegocio;
	private String desOficina;

	private int Totalimpresiones;
	private int Totalconsultas;

	/* campos alternos Para Ejecutivo */
	private String desTipoPlanilla;
	private String desCargo;
	private Integer sid_ubigeo;	
	public Date fecha_ingreso;
	private String departamento;
	private String provincia;
	private String distrito;
	private String des_responsable;
	private Integer id_supervisor;
	private Integer id_coordinador;
	private double costo;
	
	private Integer cantidadExpedientesAsignados;
	private String nombreSupervisor;
	private String nombreCoordinador;
	// para vista
	private StreamedContent archivoFoto;
	private String  des_cargo;
	private String  nomSupervisor;
 	private Boolean selected = Boolean.FALSE;
	
	//Transitorias:
	private Integer numeroExpedienteFactura;
	private BigDecimal montoExpedienteFactura;
	private BigDecimal montoTotal;
	
	private Integer totalRegistros;
	private Integer totalRegistrosEncontrados;
	private Integer totalRegistrosNoEncontrados;
	private BigDecimal montoSolicitado;
	private BigDecimal montoAprobado;
	private Boolean estadoFacturacion;
	private Usuario usuarioJefe;
	
	private BigDecimal montoBono;
	private Double bono_mi_vivienda;
	private Integer cantidadExpedientes;
	private Integer nroPaginas;
	private double totalFacturar;
	
	private String nom_supervisor;
	private Integer sala;
	private Boolean sala_dueno;
	private Integer idpadre;
	private List<Expediente> listaExpedientes;
	private List<Expediente> listaSolicitudes;
	
	// transitorias
	private Integer cant_op = 0;
	private double monto_soles =0;
	
	/* transitorias para proyeccion ventas*/
	private Integer id_proyeccion_venta;
	private Integer id_ejecutivo;
	private double  meta;
	private Date 	periodo;
	private Integer id_ejecutivo_padre;
	
	private String estado_banco; // Visible Banco,No Visible Banco
	//private n
	
	private double direccion_lat;
	private double direccion_long;
	private String lat_ultimo;
	private String long_ultimo;
	private String estado_gps;
	
	private String imei;
	private Date ult_fecha_ubicacion;
	
	private double max_metros_casa;
	private String desc_est_ubic;
	
	private Integer idusuario_referente;
	
	private String concesionario;
	
	private String territorio;
	
	private Integer cantidad = 0;
	
	private boolean dotacion;
	
	private String antiguedad;

	public Usuario() {
	}

	public BigDecimal getMontoBono() {
		return montoBono;
	}

	public void setMontoBono(BigDecimal montoBono) {
		this.montoBono = montoBono;
	}

	public BigDecimal getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(BigDecimal montoTotal) {
		this.montoTotal = montoTotal;
	}

	public Integer getId_cargo() {
		return id_cargo;
	}

	public void setId_cargo(Integer id_cargo) {
		this.id_cargo = id_cargo;
	}

	public Integer getId_planilla() {
		return id_planilla;
	}

	public void setId_planilla(Integer id_planilla) {
		this.id_planilla = id_planilla;
	}

	public String getDesEmpresa() {
		return desEmpresa;
	}

	public void setDesEmpresa(String desEmpresa) {
		this.desEmpresa = desEmpresa;
	}

	public String getDesNegocio() {
		return desNegocio;
	}

	public void setDesNegocio(String desNegocio) {
		this.desNegocio = desNegocio;
	}

	public Integer getId_empresa() {
		return id_empresa;
	}

	public void setId_empresa(Integer id_empresa) {
		this.id_empresa = id_empresa;
	}

	public Integer getId_proyecto() {
		return id_proyecto;
	}

	public void setId_proyecto(Integer id_proyecto) {
		this.id_proyecto = id_proyecto;
	}

	public Integer getId_negocio() {
		return id_negocio;
	}

	public void setId_negocio(Integer id_negocio) {
		this.id_negocio = id_negocio;
	}

	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
	}

	public Usuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public Usuario(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public int getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido_paterno() {
		return apellido_paterno;
	}

	public void setApellido_paterno(String apellido_paterno) {
		this.apellido_paterno = apellido_paterno;
	}

	public String getApellido_materno() {
		return apellido_materno;
	}

	public void setApellido_materno(String apellido_materno) {
		this.apellido_materno = apellido_materno;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public Date getFecha_acceso() {
		return fecha_acceso;
	}

	public void setFecha_acceso(Date fecha_acceso) {
		this.fecha_acceso = fecha_acceso;
	}

	public Date getFecha_registro() {
		return fecha_registro;
	}

	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro = fecha_registro;
	}

	public Date getFecha_mod() {
		return fecha_mod;
	}

	public void setFecha_mod(Date fecha_mod) {
		this.fecha_mod = fecha_mod;
	}

	public int getCod_perfil() {
		return cod_perfil;
	}

	public void setCod_perfil(int cod_perfil) {
		this.cod_perfil = cod_perfil;
	}

	public Boolean getEs_nuevo() {
		return es_nuevo;
	}

	public void setEs_nuevo(Boolean es_nuevo) {
		this.es_nuevo = es_nuevo;
	}

	public int getId_centro_atencion() {
		return id_centro_atencion;
	}

	public void setId_centro_atencion(int id_centro_atencion) {
		this.id_centro_atencion = id_centro_atencion;
	}

	public String getNombre_centro_atencion() {
		return nombre_centro_atencion;
	}

	public void setNombre_centro_atencion(String nombre_centro_atencion) {
		this.nombre_centro_atencion = nombre_centro_atencion;
	}

	public Date getFecha_cambio_password() {
		return fecha_cambio_password;
	}

	public void setFecha_cambio_password(Date fecha_cambio_password) {
		this.fecha_cambio_password = fecha_cambio_password;
	}

	public String getFecha_cambio_pass() {
		return fecha_cambio_pass;
	}

	public void setFecha_cambio_pass(String fecha_cambio_pass) {
		this.fecha_cambio_pass = fecha_cambio_pass;
	}

	public int getNro_impresiones() {
		return nro_impresiones;
	}

	public void setNro_impresiones(int nro_impresiones) {
		this.nro_impresiones = nro_impresiones;
	}

	public int getNro_consultas() {
		return nro_consultas;
	}

	public void setNro_consultas(int nro_consultas) {
		this.nro_consultas = nro_consultas;
	}

	public String getNombre_completo() {
		String apellidos="";
		if (getApellido_paterno() != null) 
			apellidos += " " + getApellido_paterno();
		
		if (getApellido_materno() != null) 
			apellidos += " " + getApellido_materno();
		
		nombre_completo = (apellidos + " " +getNombre()).trim();
		return nombre_completo;
	}

	public int getTotalimpresiones() {
		return Totalimpresiones;
	}

	public void setTotalimpresiones(int totalimpresiones) {
		Totalimpresiones = totalimpresiones;
	}

	public int getTotalconsultas() {
		return Totalconsultas;
	}

	public void setTotalconsultas(int totalconsultas) {
		Totalconsultas = totalconsultas;
	}

	public String getDesTipoPlanilla() {
		return desTipoPlanilla;
	}

	public void setDesTipoPlanilla(String desTipoPlanilla) {
		this.desTipoPlanilla = desTipoPlanilla;
	}

	public String getDesCargo() {
		return desCargo;
	}

	public void setDesCargo(String desCargo) {
		this.desCargo = desCargo;
	}

	public StreamedContent getArchivoFoto() {
		return archivoFoto;
	}

	public void setArchivoFoto(StreamedContent archivoFoto) {
		this.archivoFoto = archivoFoto;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	public Integer getSid_ubigeo() {
		return sid_ubigeo;
	}

	public void setSid_ubigeo(Integer sid_ubigeo) {
		this.sid_ubigeo = sid_ubigeo;
	}

	public Integer getId_oficina() {
		return id_oficina;
	}

	public void setId_oficina(Integer id_oficina) {
		this.id_oficina = id_oficina;
	}

	public Date getFecha_ingreso() {
		return fecha_ingreso;
	}

	public void setFecha_ingreso(Date fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getDistrito() {
		return distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getDes_responsable() {
		return des_responsable;
	}

	public void setDes_responsable(String des_responsable) {
		this.des_responsable = des_responsable;
	}

	public Integer getId_supervisor() {
		return id_supervisor;
	}

	public void setId_supervisor(Integer id_supervisor) {
		this.id_supervisor = id_supervisor;
	}

	public Integer getId_coordinador() {
		return id_coordinador;
	}

	public void setId_coordinador(Integer id_coordinador) {
		this.id_coordinador = id_coordinador;
	}

	public String getDes_cargo() {
		return des_cargo;
	}

	public void setDes_cargo(String des_cargo) {
		this.des_cargo = des_cargo;
	}

	public Integer getCantidadExpedientesAsignados() {
		return cantidadExpedientesAsignados;
	}

	public void setCantidadExpedientesAsignados(Integer cantidadExpedientesAsignados) {
		this.cantidadExpedientesAsignados = cantidadExpedientesAsignados;
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

	public String getNomSupervisor() {
		return nomSupervisor;
	}

	public void setNomSupervisor(String nomSupervisor) {
		this.nomSupervisor = nomSupervisor;
	}

	public Boolean getSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	public Integer getNumeroExpedienteFactura() {
		return numeroExpedienteFactura;
	}

	public void setNumeroExpedienteFactura(Integer numeroExpedienteFactura) {
		this.numeroExpedienteFactura = numeroExpedienteFactura;
	}

	public BigDecimal getMontoExpedienteFactura() {
		return montoExpedienteFactura;
	}

	public void setMontoExpedienteFactura(BigDecimal montoExpedienteFactura) {
		this.montoExpedienteFactura = montoExpedienteFactura;
	}

	public Date getFecha_cese() {
		return fecha_cese;
	}

	public void setFecha_cese(Date fecha_cese) {
		this.fecha_cese = fecha_cese;
	}

	public String getMotivo_cese() {
		return motivo_cese;
	}

	public void setMotivo_cese(String motivo_cese) {
		this.motivo_cese = motivo_cese;
	}

	public Boolean getEstado_planilla() {
		return estado_planilla;
	}

	public void setEstado_planilla(Boolean estado_planilla) {
		this.estado_planilla = estado_planilla;
	}

	public String getFuncion() {
		return funcion;
	}

	public void setFuncion(String funcion) {
		this.funcion = funcion;
	}

	public String getRpm() {
		return rpm;
	}

	public void setRpm(String rpm) {
		this.rpm = rpm;
	}

	public String getRpc() {
		return rpc;
	}

	public void setRpc(String rpc) {
		this.rpc = rpc;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCodigoBanco() {
		return codigoBanco;
	}

	public void setCodigoBanco(String codigoBanco) {
		this.codigoBanco = codigoBanco;
	}

	public String getCodigoBancoSupervisor() {
		return codigoBancoSupervisor;
	}

	public void setCodigoBancoSupervisor(String codigoBancoSupervisor) {
		this.codigoBancoSupervisor = codigoBancoSupervisor;
	}

	public Integer getTotalRegistros() {
		return totalRegistros;
	}

	public void setTotalRegistros(Integer totalRegistros) {
		this.totalRegistros = totalRegistros;
	}

	public BigDecimal getMontoSolicitado() {
		return montoSolicitado;
	}

	public void setMontoSolicitado(BigDecimal montoSolicitado) {
		this.montoSolicitado = montoSolicitado;
	}

	public BigDecimal getMontoAprobado() {
		return montoAprobado;
	}

	public void setMontoAprobado(BigDecimal montoAprobado) {
		this.montoAprobado = montoAprobado;
	}

	public Boolean getEstadoFacturacion() {
		return estadoFacturacion;
	}

	public void setEstadoFacturacion(Boolean estadoFacturacion) {
		this.estadoFacturacion = estadoFacturacion;
	}

	public Usuario getUsuarioJefe() {
		return usuarioJefe;
	}

	public void setUsuarioJefe(Usuario usuarioJefe) {
		this.usuarioJefe = usuarioJefe;
	}

	public Integer getTotalRegistrosEncontrados() {
		return totalRegistrosEncontrados;
	}

	public void setTotalRegistrosEncontrados(Integer totalRegistrosEncontrados) {
		this.totalRegistrosEncontrados = totalRegistrosEncontrados;
	}

	public Integer getTotalRegistrosNoEncontrados() {
		return totalRegistrosNoEncontrados;
	}

	public void setTotalRegistrosNoEncontrados(Integer totalRegistrosNoEncontrados) {
		this.totalRegistrosNoEncontrados = totalRegistrosNoEncontrados;
	}
	public String getCodigo_quality() {
		return codigo_quality;
	}

	public void setCodigo_quality(String codigo_quality) {
		this.codigo_quality = codigo_quality;
	}

	public Double getBono_mi_vivienda() {
		return bono_mi_vivienda;
	}

	public void setBono_mi_vivienda(Double bono_mi_vivienda) {
		this.bono_mi_vivienda = bono_mi_vivienda;
	}

	public String getNom_supervisor() {
		return nom_supervisor;
	}

	public void setNom_supervisor(String nom_supervisor) {
		this.nom_supervisor = nom_supervisor;
	}

	public Integer getCant_op() {
		return cant_op;
	}

	public void setCant_op(Integer cant_op) {
		this.cant_op = cant_op;
	}

	public double getMonto_soles() {
		return monto_soles;
	}

	public void setMonto_soles(double monto_soles) {
		this.monto_soles = monto_soles;
	}

	public Integer getDias_vacaciones() {
		return dias_vacaciones;
	}

	public void setDias_vacaciones(Integer dias_vacaciones) {
		this.dias_vacaciones = dias_vacaciones;
	}

	public Integer getDias_descanso() {
		return dias_descanso;
	}

	public void setDias_descanso(Integer dias_descanso) {
		this.dias_descanso = dias_descanso;
	}

	public Integer getSala() {
		return sala;
	}

	public void setSala(Integer sala) {
		this.sala = sala;
	}

	public Boolean getSala_dueno() {
		return sala_dueno;
	}

	public void setSala_dueno(Boolean sala_dueno) {
		this.sala_dueno = sala_dueno;
	}

	public Integer getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(Integer idproducto) {
		this.idproducto = idproducto;
	}

	public Integer getId_proyeccion_venta() {
		return id_proyeccion_venta;
	}

	public void setId_proyeccion_venta(Integer id_proyeccion_venta) {
		this.id_proyeccion_venta = id_proyeccion_venta;
	}

	public Integer getId_ejecutivo() {
		return id_ejecutivo;
	}

	public void setId_ejecutivo(Integer id_ejecutivo) {
		this.id_ejecutivo = id_ejecutivo;
	}

	public double getMeta() {
		return meta;
	}

	public void setMeta(double meta) {
		this.meta = meta;
	}

	public Date getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Date periodo) {
		this.periodo = periodo;
	}

	public Integer getId_ejecutivo_padre() {
		return id_ejecutivo_padre;
	}

	public void setId_ejecutivo_padre(Integer id_ejecutivo_padre) {
		this.id_ejecutivo_padre = id_ejecutivo_padre;
	}

	public Integer getIdpadre() {
		return idpadre;
	}

	public void setIdpadre(Integer idpadre) {
		this.idpadre = idpadre;
	}

	public String getProducto_principal() {
		return producto_principal;
	}

	public void setProducto_principal(String producto_principal) {
		this.producto_principal = producto_principal;
	}

	public String getOtros_productos() {
		return otros_productos;
	}

	public void setOtros_productos(String otros_productos) {
		this.otros_productos = otros_productos;
	}

	public String getNegocio_asignado() {
		return negocio_asignado;
	}

	public void setNegocio_asignado(String negocio_asignado) {
		this.negocio_asignado = negocio_asignado;
	}

	public String getEstado_banco() {
		return estado_banco;
	}

	public void setEstado_banco(String estado_banco) {
		this.estado_banco = estado_banco;
	}

	public double getDireccion_lat() {
		return direccion_lat;
	}

	public void setDireccion_lat(double direccion_lat) {
		this.direccion_lat = direccion_lat;
	}

	public double getDireccion_long() {
		return direccion_long;
	}

	public void setDireccion_long(double direccion_long) {
		this.direccion_long = direccion_long;
	}

	public String getEstado_gps() {
		return estado_gps;
	}

	public void setEstado_gps(String estado_gps) {
		this.estado_gps = estado_gps;
	}

	public String getLat_ultimo() {
		return lat_ultimo;
	}

	public void setLat_ultimo(String lat_ultimo) {
		this.lat_ultimo = lat_ultimo;
	}

	public String getLong_ultimo() {
		return long_ultimo;
	}

	public void setLong_ultimo(String long_ultimo) {
		this.long_ultimo = long_ultimo;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public Date getUlt_fecha_ubicacion() {
		return ult_fecha_ubicacion;
	}

	public void setUlt_fecha_ubicacion(Date ult_fecha_ubicacion) {
		this.ult_fecha_ubicacion = ult_fecha_ubicacion;
	}

	public double getMax_metros_casa() {
		return max_metros_casa;
	}

	public void setMax_metros_casa(double max_metros_casa) {
		this.max_metros_casa = max_metros_casa;
	}

	public String getDesc_est_ubic() {
		return desc_est_ubic;
	}

	public void setDesc_est_ubic(String desc_est_ubic) {
		this.desc_est_ubic = desc_est_ubic;
	}

	public Integer getIdusuario_referente() {
		return idusuario_referente;
	}

	public void setIdusuario_referente(Integer idusuario_referente) {
		this.idusuario_referente = idusuario_referente;
	}

	public String getConcesionario() {
		return concesionario;
	}

	public void setConcesionario(String concesionario) {
		this.concesionario = concesionario;
	}

	public String getTerritorio() {
		return territorio;
	}

	public void setTerritorio(String territorio) {
		this.territorio = territorio;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public boolean isDotacion() {
		return dotacion;
	}

	public void setDotacion(boolean dotacion) {
		this.dotacion = dotacion;
	}

	public String getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(String antiguedad) {
		this.antiguedad = antiguedad;
	}

	public String getDesOficina() {
		return desOficina;
	}

	public void setDesOficina(String desOficina) {
		this.desOficina = desOficina;
	}

	public List<Expediente> getListaExpedientes() {
		return listaExpedientes;
	}

	public void setListaExpedientes(List<Expediente> listaExpedientes) {
		this.listaExpedientes = listaExpedientes;
	}

	public Integer getCantidadExpedientes() {
		return cantidadExpedientes;
	}

	public void setCantidadExpedientes(Integer cantidadExpedientes) {
		this.cantidadExpedientes = cantidadExpedientes;
	}

	public Integer getNroPaginas() {
		return nroPaginas;
	}

	public void setNroPaginas(Integer nroPaginas) {
		this.nroPaginas = nroPaginas;
	}

	public double getTotalFacturar() {
		return totalFacturar;
	}

	public void setTotalFacturar(double totalFacturar) {
		this.totalFacturar = totalFacturar;
	}

	public String getUsuarioAsignado() {
		return usuarioAsignado;
	}

	public void setUsuarioAsignado(String usuarioAsignado) {
		this.usuarioAsignado = usuarioAsignado;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public List<Expediente> getListaSolicitudes() {
		return listaSolicitudes;
	}

	public void setListaSolicitudes(List<Expediente> listaSolicitudes) {
		this.listaSolicitudes = listaSolicitudes;
	}

	public Integer getTotalExpedientes() {
		return totalExpedientes;
	}

	public void setTotalExpedientes(Integer totalExpedientes) {
		this.totalExpedientes = totalExpedientes;
	}

	public Integer getTotalSolicitudes() {
		return totalSolicitudes;
	}

	public void setTotalSolicitudes(Integer totalSolicitudes) {
		this.totalSolicitudes = totalSolicitudes;
	}

	public Integer getTotalPaginasExpedientes() {
		return totalPaginasExpedientes;
	}

	public void setTotalPaginasExpedientes(Integer totalPaginasExpedientes) {
		this.totalPaginasExpedientes = totalPaginasExpedientes;
	}

	public Integer getTotalPaginasSolicitudes() {
		return totalPaginasSolicitudes;
	}

	public void setTotalPaginasSolicitudes(Integer totalPaginasSolicitudes) {
		this.totalPaginasSolicitudes = totalPaginasSolicitudes;
	}

		
	/*
	 * public String getNombre_completo() { String nomb = getNombre() + " " +
	 * getApellido_paterno() + " "+getApellido_materno(); return nomb; }
	 */
}
