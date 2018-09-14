package com.certicom.certiscan.domain;

import java.util.Date;
import java.util.List;

public class FacturacionPlanillaDetalle {
	
	private Integer id_facturacion_planilla_detalle;
	private Integer id_facturacion_planilla_cabecera;
	private Integer cod_expediente;
	private Integer cod_exp_tracking;
	private double costo_planilla_facturacion;
	private Integer idusuario;
	
	/* campos para el consolidado planilla*/
	private String cod_quality;
	private String usuarioAsignado;
	private String dni;
	private double montoComision;
	private double montoComisionOperativo;
	private double montoComisionXRegularizar;
	private double montoBonoXCumplimiento;
	private double montoPenalidadXMalaRevision;
	private double montoPenalidadXFueraPlazo;
	private double montoPenalidadXCarpetaPerdida;
	private double montoPenalidadXMalaDeteccionFraude;
	private double montoDeteccionError;
	private double montoDescuento;
	/*private double montoBonoSimple;*/
	private double montoBonoXCapacitacion;
	private double montoBonoPosibleFraude;
	private double montoBono1;
	private double montoBono2;
	private double montoBono3;
	private double montoTotalComisiones;
	private Integer cantidadPag;
	private Integer id_tarifa;
	
	/* campos para el consolidado facturacion */
	private Integer cantidadEmpresa;
	private Integer cantidadPersona;
	private Integer cantidadReniec;
	private String  desConcepto;
	private Integer codConcepto;
	private Integer cantidad;
	private double totalFacturar;
	private double totalFacturarOtros;
	private Integer cod_tab_com;
	private Integer codTabCom;
	private String desCargo;
	private String desTipo_Planilla;
	private Boolean flgcomisionado;
	private List<Integer> resultados;
	
	public FacturacionPlanillaDetalle(){
	}

	public Integer getId_facturacion_planilla_cabecera() {
		return id_facturacion_planilla_cabecera;
	}

	public void setId_facturacion_planilla_cabecera(
			Integer id_facturacion_planilla_cabecera) {
		this.id_facturacion_planilla_cabecera = id_facturacion_planilla_cabecera;
	}

	public Integer getIdusuario() {
		return idusuario;
	}

	public void setIdusuario(Integer idusuario) {
		this.idusuario = idusuario;
	}

	public Integer getId_facturacion_planilla_detalle() {
		return id_facturacion_planilla_detalle;
	}

	public void setId_facturacion_planilla_detalle(
			Integer id_facturacion_planilla_detalle) {
		this.id_facturacion_planilla_detalle = id_facturacion_planilla_detalle;
	}

	public Integer getCod_expediente() {
		return cod_expediente;
	}

	public void setCod_expediente(Integer cod_expediente) {
		this.cod_expediente = cod_expediente;
	}

	public Integer getCod_exp_tracking() {
		return cod_exp_tracking;
	}

	public void setCod_exp_tracking(Integer cod_exp_tracking) {
		this.cod_exp_tracking = cod_exp_tracking;
	}

	public double getCosto_planilla_facturacion() {
		return costo_planilla_facturacion;
	}

	public void setCosto_planilla_facturacion(double costo_planilla_facturacion) {
		this.costo_planilla_facturacion = costo_planilla_facturacion;
	}

	public String getCod_quality() {
		return cod_quality;
	}

	public void setCod_quality(String cod_quality) {
		this.cod_quality = cod_quality;
	}

	public String getUsuarioAsignado() {
		return usuarioAsignado;
	}

	public void setUsuarioAsignado(String usuarioAsignado) {
		this.usuarioAsignado = usuarioAsignado;
	}

	public double getMontoComision() {
		return montoComision;
	}

	public void setMontoComision(double montoComision) {
		this.montoComision = montoComision;
	}

	public double getMontoComisionOperativo() {
		return montoComisionOperativo;
	}

	public void setMontoComisionOperativo(double montoComisionOperativo) {
		this.montoComisionOperativo = montoComisionOperativo;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public double getMontoComisionXRegularizar() {
		return montoComisionXRegularizar;
	}

	public void setMontoComisionXRegularizar(double montoComisionXRegularizar) {
		this.montoComisionXRegularizar = montoComisionXRegularizar;
	}

	public double getMontoDescuento() {
		return montoDescuento;
	}

	public void setMontoDescuento(double montoDescuento) {
		this.montoDescuento = montoDescuento;
	}

	public double getMontoTotalComisiones() {
		return montoTotalComisiones;
	}

	public void setMontoTotalComisiones(double montoTotalComisiones) {
		this.montoTotalComisiones = montoTotalComisiones;
	}

	public Integer getCantidadEmpresa() {
		return cantidadEmpresa;
	}

	public void setCantidadEmpresa(Integer cantidadEmpresa) {
		this.cantidadEmpresa = cantidadEmpresa;
	}

	public Integer getCantidadPersona() {
		return cantidadPersona;
	}

	public void setCantidadPersona(Integer cantidadPersona) {
		this.cantidadPersona = cantidadPersona;
	}

	public Integer getCantidadReniec() {
		return cantidadReniec;
	}

	public void setCantidadReniec(Integer cantidadReniec) {
		this.cantidadReniec = cantidadReniec;
	}

	public String getDesConcepto() {
		return desConcepto;
	}

	public void setDesConcepto(String desConcepto) {
		this.desConcepto = desConcepto;
	}

	public Integer getCodConcepto() {
		return codConcepto;
	}

	public void setCodConcepto(Integer codConcepto) {
		this.codConcepto = codConcepto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public double getTotalFacturar() {
		return totalFacturar;
	}

	public void setTotalFacturar(double totalFacturar) {
		this.totalFacturar = totalFacturar;
	}

	public double getTotalFacturarOtros() {
		return totalFacturarOtros;
	}

	public void setTotalFacturarOtros(double totalFacturarOtros) {
		this.totalFacturarOtros = totalFacturarOtros;
	}

	public Integer getCod_tab_com() {
		return cod_tab_com;
	}

	public void setCod_tab_com(Integer cod_tab_com) {
		this.cod_tab_com = cod_tab_com;
	}

	public Integer getCodTabCom() {
		return codTabCom;
	}

	public void setCodTabCom(Integer codTabCom) {
		this.codTabCom = codTabCom;
	}

	public String getDesCargo() {
		return desCargo;
	}

	public void setDesCargo(String desCargo) {
		this.desCargo = desCargo;
	}

	public String getDesTipo_Planilla() {
		return desTipo_Planilla;
	}

	public void setDesTipo_Planilla(String desTipo_Planilla) {
		this.desTipo_Planilla = desTipo_Planilla;
	}

	public double getMontoBonoXCumplimiento() {
		return montoBonoXCumplimiento;
	}

	public void setMontoBonoXCumplimiento(double montoBonoXCumplimiento) {
		this.montoBonoXCumplimiento = montoBonoXCumplimiento;
	}

	public double getMontoPenalidadXMalaRevision() {
		return montoPenalidadXMalaRevision;
	}

	public void setMontoPenalidadXMalaRevision(double montoPenalidadXMalaRevision) {
		this.montoPenalidadXMalaRevision = montoPenalidadXMalaRevision;
	}

	public double getMontoPenalidadXFueraPlazo() {
		return montoPenalidadXFueraPlazo;
	}

	public void setMontoPenalidadXFueraPlazo(double montoPenalidadXFueraPlazo) {
		this.montoPenalidadXFueraPlazo = montoPenalidadXFueraPlazo;
	}

	public double getMontoPenalidadXCarpetaPerdida() {
		return montoPenalidadXCarpetaPerdida;
	}

	public void setMontoPenalidadXCarpetaPerdida(
			double montoPenalidadXCarpetaPerdida) {
		this.montoPenalidadXCarpetaPerdida = montoPenalidadXCarpetaPerdida;
	}

	public double getMontoPenalidadXMalaDeteccionFraude() {
		return montoPenalidadXMalaDeteccionFraude;
	}

	public void setMontoPenalidadXMalaDeteccionFraude(
			double montoPenalidadXMalaDeteccionFraude) {
		this.montoPenalidadXMalaDeteccionFraude = montoPenalidadXMalaDeteccionFraude;
	}

	public double getMontoDeteccionError() {
		return montoDeteccionError;
	}

	public void setMontoDeteccionError(double montoDeteccionError) {
		this.montoDeteccionError = montoDeteccionError;
	}

	public double getMontoBonoXCapacitacion() {
		return montoBonoXCapacitacion;
	}

	public void setMontoBonoXCapacitacion(double montoBonoXCapacitacion) {
		this.montoBonoXCapacitacion = montoBonoXCapacitacion;
	}

	public Boolean getFlgcomisionado() {
		return flgcomisionado;
	}

	public void setFlgcomisionado(Boolean flgcomisionado) {
		this.flgcomisionado = flgcomisionado;
	}

	public double getMontoBonoPosibleFraude() {
		return montoBonoPosibleFraude;
	}

	public void setMontoBonoPosibleFraude(double montoBonoPosibleFraude) {
		this.montoBonoPosibleFraude = montoBonoPosibleFraude;
	}

	public double getMontoBono1() {
		return montoBono1;
	}

	public void setMontoBono1(double montoBono1) {
		this.montoBono1 = montoBono1;
	}

	public double getMontoBono2() {
		return montoBono2;
	}

	public void setMontoBono2(double montoBono2) {
		this.montoBono2 = montoBono2;
	}

	public double getMontoBono3() {
		return montoBono3;
	}

	public void setMontoBono3(double montoBono3) {
		this.montoBono3 = montoBono3;
	}

	public List<Integer> getResultados() {
		return resultados;
	}

	public void setResultados(List<Integer> resultados) {
		this.resultados = resultados;
	}

	public Integer getCantidadPag() {
		return cantidadPag;
	}

	public void setCantidadPag(Integer cantidadPag) {
		this.cantidadPag = cantidadPag;
	}

	public Integer getId_tarifa() {
		return id_tarifa;
	}

	public void setId_tarifa(Integer id_tarifa) {
		this.id_tarifa = id_tarifa;
	}
	
}
