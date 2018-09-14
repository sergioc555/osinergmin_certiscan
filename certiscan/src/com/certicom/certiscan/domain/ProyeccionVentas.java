package com.certicom.certiscan.domain;

import java.util.Date;
import java.util.List;

public class ProyeccionVentas {

	private Integer id_proyeccion_venta;
	private Integer id_negocio;
	private Integer id_producto;
	private Integer id_ejecutivo;
	private Integer id_ejecutivo_padre;
	private String nombre;
	private String nombre_padre;
	private double meta;
	private Date periodo;
	private List<ProyeccionVentas> listaProyVen;
	
	private double monto_desembolsado;
	private double monto_efectivo;
	private double monto_enproceso;
	private double monto_enEveris;
	private double monto_tipoBase;
	private double monto_enbolsa;
	private Integer cantidadDesembolsado;
	private Integer cantidadEfectivo;
	private Integer cantidadEnProceso;
	private Integer cantidadEnEveris;
	private Integer cantidadTipoBase;
	private Integer cantidadBolsa;
	private Boolean ingresar;
	private Integer tipo_nivel;
	private Integer nro_hijos;
	
	/*Transitorias*/
	private String nomUsuario;
	
	// PAGO DE HABERES;
	private Integer cant_empresas; // PH-PYMES
	private Integer nro_trabajadores;
	private Integer avance_empresas;
	private Integer empresas_aperturadas; // penetracion (cantidad de empresas que se han aperturado cuentas)
	private Integer empresas_sintrabajar;
	private Integer empresas_abandono;
	private Integer avan_ctas_cts;
	private Integer avan_ctas_ph;
	private Integer aper_ctas_cts;
	private Integer aper_ctas_ph;
	private Integer empresas_gestion;
	private Integer gestion_nrocuentas;
	
	private Integer cantidad_campo;
	private Integer cantidad_banco;
	private Integer avance_empresas_campo;
	private Integer avance_empresas_banco;
	private Integer empresas_apert_campo;
	private Integer empresas_apert_banco;
	
	private Integer cantidad_visitas;
	private Integer cantidad_visitas_campo;
	private Integer cantidad_visitas_banco;
	
	private Integer empresas_sintrabajar_campo;
	private Integer empresas_sintrabajar_banco;
	
	private Integer pendxvisitar;
	private Integer pendxvisitar_campo;
	private Integer pendxvisitar_banco;
	
	
	
	//PYMES
	private double monto_riesgos; 
	private Integer cantidad_riesgos;
	private double monto_aprobado; 
	private Integer cantidad_aprobado;
	private double monto_bolsa; 
	private Integer cantidad_bolsa;
	
	private double monto_prospecto; 
	private Integer cantidad_prospecto;
	private double monto_abandono; 
	private Integer cantidad_abandono;
	private double monto_denegado; 
	private Integer cantidad_denegado;
	
	
	
	private Integer nro_ejecutivos_sindesembolso;
	private Integer nro_ejecutivos_sinregistros; // general

	private Integer orden = 0;
	
	private Integer cant_empr_masiva = 0;
	private Integer cant_empr_inst = 0;
	
	private Integer cantidad_mes;
	private Integer cantidad_dia;
	
	public ProyeccionVentas() {

	}

	public Integer getId_proyeccion_venta() {
		return id_proyeccion_venta;
	}

	public void setId_proyeccion_venta(Integer id_proyeccion_venta) {
		this.id_proyeccion_venta = id_proyeccion_venta;
	}

	public Integer getId_negocio() {
		return id_negocio;
	}

	public void setId_negocio(Integer id_negocio) {
		this.id_negocio = id_negocio;
	}

	public Integer getId_producto() {
		return id_producto;
	}

	public void setId_producto(Integer id_producto) {
		this.id_producto = id_producto;
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

	public String getNomUsuario() {
		return nomUsuario;
	}

	public void setNomUsuario(String nomUsuario) {
		this.nomUsuario = nomUsuario;
	}

	public Integer getId_ejecutivo_padre() {
		return id_ejecutivo_padre;
	}

	public void setId_ejecutivo_padre(Integer id_ejecutivo_padre) {
		this.id_ejecutivo_padre = id_ejecutivo_padre;
	}

	public List<ProyeccionVentas> getListaProyVen() {
		return listaProyVen;
	}

	public void setListaProyVen(List<ProyeccionVentas> listaProyVen) {
		this.listaProyVen = listaProyVen;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre_padre() {
		return nombre_padre;
	}

	public void setNombre_padre(String nombre_padre) {
		this.nombre_padre = nombre_padre;
	}

	public double getMonto_desembolsado() {
		return monto_desembolsado;
	}

	public void setMonto_desembolsado(double monto_desembolsado) {
		this.monto_desembolsado = monto_desembolsado;
	}

	public double getMonto_efectivo() {
		return monto_efectivo;
	}

	public void setMonto_efectivo(double monto_efectivo) {
		this.monto_efectivo = monto_efectivo;
	}

	public double getMonto_enproceso() {
		return monto_enproceso;
	}

	public void setMonto_enproceso(double monto_enproceso) {
		this.monto_enproceso = monto_enproceso;
	}

	public double getMonto_enEveris() {
		return monto_enEveris;
	}

	public void setMonto_enEveris(double monto_enEveris) {
		this.monto_enEveris = monto_enEveris;
	}

	public double getMonto_tipoBase() {
		return monto_tipoBase;
	}

	public void setMonto_tipoBase(double monto_tipoBase) {
		this.monto_tipoBase = monto_tipoBase;
	}

	public Integer getCantidadDesembolsado() {
		return cantidadDesembolsado;
	}

	public void setCantidadDesembolsado(Integer cantidadDesembolsado) {
		this.cantidadDesembolsado = cantidadDesembolsado;
	}

	public Integer getCantidadEfectivo() {
		return cantidadEfectivo;
	}

	public void setCantidadEfectivo(Integer cantidadEfectivo) {
		this.cantidadEfectivo = cantidadEfectivo;
	}

	public Integer getCantidadEnProceso() {
		return cantidadEnProceso;
	}

	public void setCantidadEnProceso(Integer cantidadEnProceso) {
		this.cantidadEnProceso = cantidadEnProceso;
	}

	public Integer getCantidadEnEveris() {
		return cantidadEnEveris;
	}

	public void setCantidadEnEveris(Integer cantidadEnEveris) {
		this.cantidadEnEveris = cantidadEnEveris;
	}

	public Integer getCantidadTipoBase() {
		return cantidadTipoBase;
	}

	public void setCantidadTipoBase(Integer cantidadTipoBase) {
		this.cantidadTipoBase = cantidadTipoBase;
	}

	public Boolean getIngresar() {
		return ingresar;
	}

	public void setIngresar(Boolean ingresar) {
		this.ingresar = ingresar;
	}

	public Integer getTipo_nivel() {
		return tipo_nivel;
	}

	public void setTipo_nivel(Integer tipo_nivel) {
		this.tipo_nivel = tipo_nivel;
	}

	public Integer getCant_empresas() {
		return cant_empresas;
	}

	public void setCant_empresas(Integer cant_empresas) {
		this.cant_empresas = cant_empresas;
	}

	public Integer getNro_trabajadores() {
		return nro_trabajadores;
	}

	public void setNro_trabajadores(Integer nro_trabajadores) {
		this.nro_trabajadores = nro_trabajadores;
	}

	public Integer getAvance_empresas() {
		return avance_empresas;
	}

	public void setAvance_empresas(Integer avance_empresas) {
		this.avance_empresas = avance_empresas;
	}

	public Integer getEmpresas_aperturadas() {
		return empresas_aperturadas;
	}

	public void setEmpresas_aperturadas(Integer empresas_aperturadas) {
		this.empresas_aperturadas = empresas_aperturadas;
	}

	public Integer getEmpresas_sintrabajar() {
		return empresas_sintrabajar;
	}

	public void setEmpresas_sintrabajar(Integer empresas_sintrabajar) {
		this.empresas_sintrabajar = empresas_sintrabajar;
	}

	public double getMonto_riesgos() {
		return monto_riesgos;
	}

	public void setMonto_riesgos(double monto_riesgos) {
		this.monto_riesgos = monto_riesgos;
	}

	public Integer getCantidad_riesgos() {
		return cantidad_riesgos;
	}

	public void setCantidad_riesgos(Integer cantidad_riesgos) {
		this.cantidad_riesgos = cantidad_riesgos;
	}

	public double getMonto_aprobado() {
		return monto_aprobado;
	}

	public void setMonto_aprobado(double monto_aprobado) {
		this.monto_aprobado = monto_aprobado;
	}

	public Integer getCantidad_aprobado() {
		return cantidad_aprobado;
	}

	public void setCantidad_aprobado(Integer cantidad_aprobado) {
		this.cantidad_aprobado = cantidad_aprobado;
	}

	public Integer getEmpresas_abandono() {
		return empresas_abandono;
	}

	public void setEmpresas_abandono(Integer empresas_abandono) {
		this.empresas_abandono = empresas_abandono;
	}

	public Integer getAvan_ctas_cts() {
		return avan_ctas_cts;
	}

	public void setAvan_ctas_cts(Integer avan_ctas_cts) {
		this.avan_ctas_cts = avan_ctas_cts;
	}

	public Integer getAvan_ctas_ph() {
		return avan_ctas_ph;
	}

	public void setAvan_ctas_ph(Integer avan_ctas_ph) {
		this.avan_ctas_ph = avan_ctas_ph;
	}

	public Integer getAper_ctas_cts() {
		return aper_ctas_cts;
	}

	public void setAper_ctas_cts(Integer aper_ctas_cts) {
		this.aper_ctas_cts = aper_ctas_cts;
	}

	public Integer getAper_ctas_ph() {
		return aper_ctas_ph;
	}

	public void setAper_ctas_ph(Integer aper_ctas_ph) {
		this.aper_ctas_ph = aper_ctas_ph;
	}

	public Integer getEmpresas_gestion() {
		return empresas_gestion;
	}

	public void setEmpresas_gestion(Integer empresas_gestion) {
		this.empresas_gestion = empresas_gestion;
	}

	public Integer getGestion_nrocuentas() {
		return gestion_nrocuentas;
	}

	public void setGestion_nrocuentas(Integer gestion_nrocuentas) {
		this.gestion_nrocuentas = gestion_nrocuentas;
	}

	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	public Integer getNro_hijos() {
		return nro_hijos;
	}

	public void setNro_hijos(Integer nro_hijos) {
		this.nro_hijos = nro_hijos;
	}

	public double getMonto_bolsa() {
		return monto_bolsa;
	}

	public void setMonto_bolsa(double monto_bolsa) {
		this.monto_bolsa = monto_bolsa;
	}

	public Integer getNro_ejecutivos_sindesembolso() {
		return nro_ejecutivos_sindesembolso;
	}

	public void setNro_ejecutivos_sindesembolso(Integer nro_ejecutivos_sindesembolso) {
		this.nro_ejecutivos_sindesembolso = nro_ejecutivos_sindesembolso;
	}

	public Integer getNro_ejecutivos_sinregistros() {
		return nro_ejecutivos_sinregistros;
	}

	public void setNro_ejecutivos_sinregistros(Integer nro_ejecutivos_sinregistros) {
		this.nro_ejecutivos_sinregistros = nro_ejecutivos_sinregistros;
	}

	public Integer getCantidad_bolsa() {
		return cantidad_bolsa;
	}

	public void setCantidad_bolsa(Integer cantidad_bolsa) {
		this.cantidad_bolsa = cantidad_bolsa;
	}

	public double getMonto_enbolsa() {
		return monto_enbolsa;
	}

	public void setMonto_enbolsa(double monto_enbolsa) {
		this.monto_enbolsa = monto_enbolsa;
	}

	public Integer getCantidadBolsa() {
		return cantidadBolsa;
	}

	public void setCantidadBolsa(Integer cantidadBolsa) {
		this.cantidadBolsa = cantidadBolsa;
	}

	public Integer getCant_empr_masiva() {
		return cant_empr_masiva;
	}

	public void setCant_empr_masiva(Integer cant_empr_masiva) {
		this.cant_empr_masiva = cant_empr_masiva;
	}

	public Integer getCant_empr_inst() {
		return cant_empr_inst;
	}

	public void setCant_empr_inst(Integer cant_empr_inst) {
		this.cant_empr_inst = cant_empr_inst;
	}

	public Integer getCantidad_campo() {
		return cantidad_campo;
	}

	public void setCantidad_campo(Integer cantidad_campo) {
		this.cantidad_campo = cantidad_campo;
	}

	public Integer getCantidad_banco() {
		return cantidad_banco;
	}

	public void setCantidad_banco(Integer cantidad_banco) {
		this.cantidad_banco = cantidad_banco;
	}

	public Integer getAvance_empresas_campo() {
		return avance_empresas_campo;
	}

	public void setAvance_empresas_campo(Integer avance_empresas_campo) {
		this.avance_empresas_campo = avance_empresas_campo;
	}

	public Integer getAvance_empresas_banco() {
		return avance_empresas_banco;
	}

	public void setAvance_empresas_banco(Integer avance_empresas_banco) {
		this.avance_empresas_banco = avance_empresas_banco;
	}

	public Integer getEmpresas_apert_campo() {
		return empresas_apert_campo;
	}

	public void setEmpresas_apert_campo(Integer empresas_apert_campo) {
		this.empresas_apert_campo = empresas_apert_campo;
	}

	public Integer getEmpresas_apert_banco() {
		return empresas_apert_banco;
	}

	public void setEmpresas_apert_banco(Integer empresas_apert_banco) {
		this.empresas_apert_banco = empresas_apert_banco;
	}

	public Integer getCantidad_visitas() {
		return cantidad_visitas;
	}

	public void setCantidad_visitas(Integer cantidad_visitas) {
		this.cantidad_visitas = cantidad_visitas;
	}

	public Integer getCantidad_visitas_campo() {
		return cantidad_visitas_campo;
	}

	public void setCantidad_visitas_campo(Integer cantidad_visitas_campo) {
		this.cantidad_visitas_campo = cantidad_visitas_campo;
	}

	public Integer getCantidad_visitas_banco() {
		return cantidad_visitas_banco;
	}

	public void setCantidad_visitas_banco(Integer cantidad_visitas_banco) {
		this.cantidad_visitas_banco = cantidad_visitas_banco;
	}

	public Integer getEmpresas_sintrabajar_campo() {
		return empresas_sintrabajar_campo;
	}

	public void setEmpresas_sintrabajar_campo(Integer empresas_sintrabajar_campo) {
		this.empresas_sintrabajar_campo = empresas_sintrabajar_campo;
	}

	public Integer getEmpresas_sintrabajar_banco() {
		return empresas_sintrabajar_banco;
	}

	public void setEmpresas_sintrabajar_banco(Integer empresas_sintrabajar_banco) {
		this.empresas_sintrabajar_banco = empresas_sintrabajar_banco;
	}

	public Integer getPendxvisitar() {
		return pendxvisitar;
	}

	public void setPendxvisitar(Integer pendxvisitar) {
		this.pendxvisitar = pendxvisitar;
	}

	public Integer getPendxvisitar_campo() {
		return pendxvisitar_campo;
	}

	public void setPendxvisitar_campo(Integer pendxvisitar_campo) {
		this.pendxvisitar_campo = pendxvisitar_campo;
	}

	public Integer getPendxvisitar_banco() {
		return pendxvisitar_banco;
	}

	public void setPendxvisitar_banco(Integer pendxvisitar_banco) {
		this.pendxvisitar_banco = pendxvisitar_banco;
	}

	public double getMonto_prospecto() {
		return monto_prospecto;
	}

	public void setMonto_prospecto(double monto_prospecto) {
		this.monto_prospecto = monto_prospecto;
	}

	public Integer getCantidad_prospecto() {
		return cantidad_prospecto;
	}

	public void setCantidad_prospecto(Integer cantidad_prospecto) {
		this.cantidad_prospecto = cantidad_prospecto;
	}

	public double getMonto_abandono() {
		return monto_abandono;
	}

	public void setMonto_abandono(double monto_abandono) {
		this.monto_abandono = monto_abandono;
	}

	public Integer getCantidad_abandono() {
		return cantidad_abandono;
	}

	public void setCantidad_abandono(Integer cantidad_abandono) {
		this.cantidad_abandono = cantidad_abandono;
	}

	public double getMonto_denegado() {
		return monto_denegado;
	}

	public void setMonto_denegado(double monto_denegado) {
		this.monto_denegado = monto_denegado;
	}

	public Integer getCantidad_denegado() {
		return cantidad_denegado;
	}

	public void setCantidad_denegado(Integer cantidad_denegado) {
		this.cantidad_denegado = cantidad_denegado;
	}


	public Integer getCantidad_dia() {
		return cantidad_dia;
	}

	public void setCantidad_dia(Integer cantidad_dia) {
		this.cantidad_dia = cantidad_dia;
	}

	public Integer getCantidad_mes() {
		return cantidad_mes;
	}

	public void setCantidad_mes(Integer cantidad_mes) {
		this.cantidad_mes = cantidad_mes;
	}
	
	
	
	
}
