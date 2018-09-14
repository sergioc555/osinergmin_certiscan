package com.certicom.certiscan.managedBeans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import antlr.Utils;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.ExportarArchivo;
import com.certicom.certiscan.commons.FacesUtils;
import com.certicom.certiscan.commons.GenericBeans;
import com.certicom.certiscan.domain.CalificacionPonderada;
import com.certicom.certiscan.domain.DetalleEntidad;
import com.certicom.certiscan.domain.DireccionesReportadas;
import com.certicom.certiscan.domain.LogSBS;
import com.certicom.certiscan.domain.PersonaSBS;
import com.certicom.certiscan.domain.ReporteCrediticio;
import com.certicom.certiscan.domain.Usuario;
import com.certicom.certiscan.jdbc.dao.CentralRiesgoLogic;
import com.certicom.certiscan.services.LogSBSServices;

@ManagedBean(name="centralRiesgoMB")
@ViewScoped
public class CentralRiesgoMB extends GenericBeans implements Serializable{
	
	private PersonaSBS personaSBS;
	private List<CalificacionPonderada> listCalificacionPonderada;
	private List<ReporteCrediticio> listReporteCrediticioSoles;
	private List<ReporteCrediticio> listReporteCrediticioDolares;
	private List<DetalleEntidad> listDetalleEntidadSoles;
	private List<DetalleEntidad> listDetalleEntidadDolares;
	private List<DireccionesReportadas> listDireccionesReportadas;

	private String anio;
	private String mes;
	private String tipoDocumento;
	private String numeroDocumento;
	
	private Date mes1;
	private Date mes2;
	private Date mes3;
	private Date mes4;
	private Date mes5;
	private Date mes6;
	private Date mes7;
	private Date mes8;
	private Date mes9;
	private Date mes10;
	private Date mes11;
	private Date mes12;
	private LogSBSServices logSBSServices;
	
	private LogSBS lsbs;
	@ManagedProperty(value = "#{loginMB}")
	private LoginMB login;

	@ManagedProperty(value = "#{loginMB.usuario}")
	private Usuario usuarioLogin;
	
	
	private Boolean renderedResultado;
	private Boolean renderedResultadoPersona;
	
	SimpleDateFormat sdfMes = new SimpleDateFormat("MM");
	SimpleDateFormat sdfAnio = new SimpleDateFormat("yyyy");
	SimpleDateFormat sdfFormatoSBS = new SimpleDateFormat("yyyyMMdd");
	
	@PostConstruct
	public void inicia()
	{
		
		this.tipoDocumento="0";
		numeroDocumento="";
		renderedResultado = Boolean.FALSE;
		this.logSBSServices = new LogSBSServices();
		
	}
	
	public String rHola(){
		return "hola";
	}
	
	public void setearValores(String tDocumento, String nDocumento){
		
	}
	
	public void buscarEstadoMorosidad() throws Exception{
		if(numeroDocumento.trim().equals("")){
			FacesUtils.showFacesMessage("Debe ingresar un número de documento.",Constante.ERROR);
			renderedResultado = Boolean.FALSE;
			return;
		}
		
		List<PersonaSBS> listP = CentralRiesgoLogic.reporteDirectorioReniec(tipoDocumento, numeroDocumento);
		/*if(listP.isEmpty()){
			FacesUtils.showFacesMessage("No se encontraron datos para el documento ingresado.",Constante.ERROR);
			renderedResultado = Boolean.FALSE;
			return;
		}*/
		if(!listP.isEmpty()){
		personaSBS = listP.get(0);
		renderedResultadoPersona = Boolean.TRUE;
		}else{
		renderedResultadoPersona = Boolean.FALSE;
		}
		
		renderedResultado = Boolean.TRUE;
		//llenar estos datos:
		
		String ultimoPeriodoCargado=CentralRiesgoLogic.getPeriodoUltimo();
		System.out.println("Periodo: "+ultimoPeriodoCargado);
		Date periodoCargado = sdfFormatoSBS.parse(ultimoPeriodoCargado);
		setMes1(periodoCargado);
		setMes2(sumarMesFecha(periodoCargado, -1));
		setMes3(sumarMesFecha(periodoCargado, -2)); 
		setMes4(sumarMesFecha(periodoCargado, -3)); 
		setMes5(sumarMesFecha(periodoCargado, -4)); 
		setMes6(sumarMesFecha(periodoCargado, -5)); 
		setMes7(sumarMesFecha(periodoCargado, -6)); 
		setMes8(sumarMesFecha(periodoCargado, -7)); 
		setMes9(sumarMesFecha(periodoCargado, -8)); 
		setMes10(sumarMesFecha(periodoCargado, -9)); 
		setMes11(sumarMesFecha(periodoCargado, -10)); 
		setMes12(sumarMesFecha(periodoCargado, -11)); 
		
		
		//LLenando listas
		listCalificacionPonderada = CentralRiesgoLogic.listarRCCCali(tipoDocumento, numeroDocumento);
		System.out.println("LISTA PONDERADA: "+this.listCalificacionPonderada.size());
		listReporteCrediticioSoles = CentralRiesgoLogic.listarRCCSoles(tipoDocumento, numeroDocumento);
		listReporteCrediticioDolares = CentralRiesgoLogic.listarRCCDolares(tipoDocumento, numeroDocumento);
		sumalizarTotalesReporteCrediticio(listReporteCrediticioSoles);
		sumalizarTotalesReporteCrediticio(listReporteCrediticioDolares);
		listDetalleEntidadSoles = CentralRiesgoLogic.reporteDetallePorEntidadesSoles(tipoDocumento, numeroDocumento);
		listDetalleEntidadDolares = CentralRiesgoLogic.reporteDetallePorEntidadesDolares(tipoDocumento, numeroDocumento);
		listDireccionesReportadas = CentralRiesgoLogic.reporteDirectorioEssalud(tipoDocumento, numeroDocumento);
		
		this.lsbs = new LogSBS();
		this.lsbs.setIdusuario(this.usuarioLogin.getIdusuario());
		this.lsbs.setFecha_consulta(new Date());
		if(tipoDocumento.equals("0")){
			this.lsbs.setTipdoc("RUC");
		}else if(tipoDocumento.equals("1")){
			this.lsbs.setTipdoc("DNI");
		}else if(tipoDocumento.equals("2")){
			this.lsbs.setTipdoc("CARNET EXTRANJERIA / CARTA INDENTIDAD");
		}else if(tipoDocumento.equals("2")){
			this.lsbs.setTipdoc("PASAPORTE");
		}
		this.lsbs.setNumdoc(numeroDocumento);
		
		this.logSBSServices.crearLogSBS(this.lsbs);
		
		
	}
	
	public void sumalizarTotalesReporteCrediticio(List<ReporteCrediticio> lista){
		//Monto1
		//PARA E
		for(int i=0; i<lista.size(); i++){
			Boolean encontroValor=Boolean.FALSE;
			BigDecimal monto1=BigDecimal.ZERO;
			if(lista.get(i).getTipoCuenta().trim().equals("E") && lista.get(i).getMonto1().compareTo(BigDecimal.ZERO)==0){
				for(int j=i; j<lista.size(); j++){
					if((lista.get(j).getTipoCuenta().equals("E") || lista.get(j).getTipoCuenta().equals("D")  || lista.get(j).getTipoCuenta().equals("C")  || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
						encontroValor= Boolean.TRUE;
					}
					if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("F")){
						monto1= monto1.add(lista.get(j).getMonto1());
					}
					
					
				}
				lista.get(i).setMonto1(monto1);
			}
		}
		
		//PARA D
		for(int i=0; i<lista.size(); i++){
			Boolean encontroValor=Boolean.FALSE;
			BigDecimal monto1=BigDecimal.ZERO;
			if(lista.get(i).getTipoCuenta().trim().equals("D") && lista.get(i).getMonto1().compareTo(BigDecimal.ZERO)==0){
				for(int j=i; j<lista.size(); j++){
					if((lista.get(j).getTipoCuenta().equals("D") || lista.get(j).getTipoCuenta().equals("C")  || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
						encontroValor= Boolean.TRUE;
					}
					if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("E")){
						monto1= monto1.add(lista.get(j).getMonto1());
					}
					
					
				}
				lista.get(i).setMonto1(monto1);
			}
		}
		
		//PARA C
		for(int i=0; i<lista.size(); i++){
			Boolean encontroValor=Boolean.FALSE;
			BigDecimal monto1=BigDecimal.ZERO;
			if(lista.get(i).getTipoCuenta().trim().equals("C") && lista.get(i).getMonto1().compareTo(BigDecimal.ZERO)==0){
				for(int j=i; j<lista.size(); j++){
					if((lista.get(j).getTipoCuenta().equals("C") || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
						encontroValor= Boolean.TRUE;
					}
					if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("D")){
						monto1= monto1.add(lista.get(j).getMonto1());
					}
					
					
				}
				lista.get(i).setMonto1(monto1);
			}
		}
		
		//PARA B
		for(int i=0; i<lista.size(); i++){
			Boolean encontroValor=Boolean.FALSE;
			BigDecimal monto1=BigDecimal.ZERO;
			if(lista.get(i).getTipoCuenta().trim().equals("B") && lista.get(i).getMonto1().compareTo(BigDecimal.ZERO)==0){
				for(int j=i; j<lista.size(); j++){
					if((lista.get(j).getTipoCuenta().equals("B") )&& j>i){
						encontroValor= Boolean.TRUE;
					}
					if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("C")){
						monto1= monto1.add(lista.get(j).getMonto1());
					}
					
					
				}
				lista.get(i).setMonto1(monto1);
			}
		}
		
		
		//Monto2
				//PARA E
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto2=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("E") && lista.get(i).getMonto2().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("E") || lista.get(j).getTipoCuenta().equals("D")  || lista.get(j).getTipoCuenta().equals("C")  || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("F")){
								Monto2= Monto2.add(lista.get(j).getMonto2());
							}
							
							
						}
						lista.get(i).setMonto2(Monto2);
					}
				}
				
				//PARA D
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto2=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("D") && lista.get(i).getMonto2().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("D") || lista.get(j).getTipoCuenta().equals("C")  || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("E")){
								Monto2= Monto2.add(lista.get(j).getMonto2());
							}
							
							
						}
						lista.get(i).setMonto2(Monto2);
					}
				}
				
				//PARA C
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto2=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("C") && lista.get(i).getMonto2().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("C") || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("D")){
								Monto2= Monto2.add(lista.get(j).getMonto2());
							}
							
							
						}
						lista.get(i).setMonto2(Monto2);
					}
				}
				
				//PARA B
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto2=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("B") && lista.get(i).getMonto2().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("B") )&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("C")){
								Monto2= Monto2.add(lista.get(j).getMonto2());
							}
							
							
						}
						lista.get(i).setMonto2(Monto2);
					}
				}
				
				//Monto3
				//PARA E
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto3=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("E") && lista.get(i).getMonto3().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("E") || lista.get(j).getTipoCuenta().equals("D")  || lista.get(j).getTipoCuenta().equals("C")  || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("F")){
								Monto3= Monto3.add(lista.get(j).getMonto3());
							}
							
							
						}
						lista.get(i).setMonto3(Monto3);
					}
				}
				
				//PARA D
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto3=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("D") && lista.get(i).getMonto3().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("D") || lista.get(j).getTipoCuenta().equals("C")  || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("E")){
								Monto3= Monto3.add(lista.get(j).getMonto3());
							}
							
							
						}
						lista.get(i).setMonto3(Monto3);
					}
				}
				
				//PARA C
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto3=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("C") && lista.get(i).getMonto3().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("C") || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("D")){
								Monto3= Monto3.add(lista.get(j).getMonto3());
							}
							
							
						}
						lista.get(i).setMonto3(Monto3);
					}
				}
				
				//PARA B
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto3=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("B") && lista.get(i).getMonto3().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("B") )&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("C")){
								Monto3= Monto3.add(lista.get(j).getMonto3());
							}
							
							
						}
						lista.get(i).setMonto3(Monto3);
					}
				}
				//Monto4
				//PARA E
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto4=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("E") && lista.get(i).getMonto4().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("E") || lista.get(j).getTipoCuenta().equals("D")  || lista.get(j).getTipoCuenta().equals("C")  || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("F")){
								Monto4= Monto4.add(lista.get(j).getMonto4());
							}
							
							
						}
						lista.get(i).setMonto4(Monto4);
					}
				}
				
				//PARA D
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto4=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("D") && lista.get(i).getMonto4().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("D") || lista.get(j).getTipoCuenta().equals("C")  || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("E")){
								Monto4= Monto4.add(lista.get(j).getMonto4());
							}
							
							
						}
						lista.get(i).setMonto4(Monto4);
					}
				}
				
				//PARA C
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto4=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("C") && lista.get(i).getMonto4().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("C") || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("D")){
								Monto4= Monto4.add(lista.get(j).getMonto4());
							}
							
							
						}
						lista.get(i).setMonto4(Monto4);
					}
				}
				
				//PARA B
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto4=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("B") && lista.get(i).getMonto4().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("B") )&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("C")){
								Monto4= Monto4.add(lista.get(j).getMonto4());
							}
							
							
						}
						lista.get(i).setMonto4(Monto4);
					}
				}
				
				//Monto5
				//PARA E
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto5=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("E") && lista.get(i).getMonto5().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("E") || lista.get(j).getTipoCuenta().equals("D")  || lista.get(j).getTipoCuenta().equals("C")  || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("F")){
								Monto5= Monto5.add(lista.get(j).getMonto5());
							}
							
							
						}
						lista.get(i).setMonto5(Monto5);
					}
				}
				
				//PARA D
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto5=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("D") && lista.get(i).getMonto5().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("D") || lista.get(j).getTipoCuenta().equals("C")  || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("E")){
								Monto5= Monto5.add(lista.get(j).getMonto5());
							}
							
							
						}
						lista.get(i).setMonto5(Monto5);
					}
				}
				
				//PARA C
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto5=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("C") && lista.get(i).getMonto5().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("C") || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("D")){
								Monto5= Monto5.add(lista.get(j).getMonto5());
							}
							
							
						}
						lista.get(i).setMonto5(Monto5);
					}
				}
				
				//PARA B
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto5=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("B") && lista.get(i).getMonto5().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("B") )&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("C")){
								Monto5= Monto5.add(lista.get(j).getMonto5());
							}
							
							
						}
						lista.get(i).setMonto5(Monto5);
					}
				}
				//Monto6
				//PARA E
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto6=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("E") && lista.get(i).getMonto6().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("E") || lista.get(j).getTipoCuenta().equals("D")  || lista.get(j).getTipoCuenta().equals("C")  || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("F")){
								Monto6= Monto6.add(lista.get(j).getMonto6());
							}
							
							
						}
						lista.get(i).setMonto6(Monto6);
					}
				}
				
				//PARA D
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto6=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("D") && lista.get(i).getMonto6().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("D") || lista.get(j).getTipoCuenta().equals("C")  || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("E")){
								Monto6= Monto6.add(lista.get(j).getMonto6());
							}
							
							
						}
						lista.get(i).setMonto6(Monto6);
					}
				}
				
				//PARA C
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto6=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("C") && lista.get(i).getMonto6().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("C") || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("D")){
								Monto6= Monto6.add(lista.get(j).getMonto6());
							}
							
							
						}
						lista.get(i).setMonto6(Monto6);
					}
				}
				
				//PARA B
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto6=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("B") && lista.get(i).getMonto6().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("B") )&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("C")){
								Monto6= Monto6.add(lista.get(j).getMonto6());
							}
							
							
						}
						lista.get(i).setMonto6(Monto6);
					}
				}
				
				//Monto7
				//PARA E
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto7=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("E") && lista.get(i).getMonto7().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("E") || lista.get(j).getTipoCuenta().equals("D")  || lista.get(j).getTipoCuenta().equals("C")  || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("F")){
								Monto7= Monto7.add(lista.get(j).getMonto7());
							}
							
							
						}
						lista.get(i).setMonto7(Monto7);
					}
				}
				
				//PARA D
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto7=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("D") && lista.get(i).getMonto7().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("D") || lista.get(j).getTipoCuenta().equals("C")  || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("E")){
								Monto7= Monto7.add(lista.get(j).getMonto7());
							}
							
							
						}
						lista.get(i).setMonto7(Monto7);
					}
				}
				
				//PARA C
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto7=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("C") && lista.get(i).getMonto7().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("C") || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("D")){
								Monto7= Monto7.add(lista.get(j).getMonto6());
							}
							
							
						}
						lista.get(i).setMonto7(Monto7);
					}
				}
				
				//PARA B
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto7=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("B") && lista.get(i).getMonto7().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("B") )&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("C")){
								Monto7= Monto7.add(lista.get(j).getMonto6());
							}
							
							
						}
						lista.get(i).setMonto7(Monto7);
					}
				}
				
				//Monto8
				//PARA E
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto8=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("E") && lista.get(i).getMonto8().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("E") || lista.get(j).getTipoCuenta().equals("D")  || lista.get(j).getTipoCuenta().equals("C")  || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("F")){
								Monto8= Monto8.add(lista.get(j).getMonto8());
							}
							
							
						}
						lista.get(i).setMonto8(Monto8);
					}
				}
				
				//PARA D
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto8=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("D") && lista.get(i).getMonto8().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("D") || lista.get(j).getTipoCuenta().equals("C")  || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("E")){
								Monto8= Monto8.add(lista.get(j).getMonto8());
							}
							
							
						}
						lista.get(i).setMonto8(Monto8);
					}
				}
				
				//PARA C
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto8=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("C") && lista.get(i).getMonto8().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("C") || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("D")){
								Monto8= Monto8.add(lista.get(j).getMonto8());
							}
							
							
						}
						lista.get(i).setMonto8(Monto8);
					}
				}
				
				//PARA B
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto8=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("B") && lista.get(i).getMonto8().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("B") )&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("C")){
								Monto8= Monto8.add(lista.get(j).getMonto8());
							}
							
							
						}
						lista.get(i).setMonto8(Monto8);
					}
				}
				
				//Monto9
				//PARA E
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto9=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("E") && lista.get(i).getMonto9().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("E") || lista.get(j).getTipoCuenta().equals("D")  || lista.get(j).getTipoCuenta().equals("C")  || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("F")){
								Monto9= Monto9.add(lista.get(j).getMonto9());
							}
							
							
						}
						lista.get(i).setMonto9(Monto9);
					}
				}
				
				//PARA D
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto9=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("D") && lista.get(i).getMonto9().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("D") || lista.get(j).getTipoCuenta().equals("C")  || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("E")){
								Monto9= Monto9.add(lista.get(j).getMonto9());
							}
							
							
						}
						lista.get(i).setMonto9(Monto9);
					}
				}
				
				//PARA C
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto9=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("C") && lista.get(i).getMonto9().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("C") || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("D")){
								Monto9= Monto9.add(lista.get(j).getMonto9());
							}
							
							
						}
						lista.get(i).setMonto9(Monto9);
					}
				}
				
				//PARA B
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto9=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("B") && lista.get(i).getMonto9().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("B") )&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("C")){
								Monto9= Monto9.add(lista.get(j).getMonto9());
							}
							
							
						}
						lista.get(i).setMonto9(Monto9);
					}
				}
				
				//Monto10
				//PARA E
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto10=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("E") && lista.get(i).getMonto10().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("E") || lista.get(j).getTipoCuenta().equals("D")  || lista.get(j).getTipoCuenta().equals("C")  || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("F")){
								Monto10= Monto10.add(lista.get(j).getMonto10());
							}
							
							
						}
						lista.get(i).setMonto10(Monto10);
					}
				}
				
				//PARA D
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto10=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("D") && lista.get(i).getMonto10().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("D") || lista.get(j).getTipoCuenta().equals("C")  || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("E")){
								Monto10= Monto10.add(lista.get(j).getMonto10());
							}
							
							
						}
						lista.get(i).setMonto10(Monto10);
					}
				}
				
				//PARA C
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto10=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("C") && lista.get(i).getMonto10().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("C") || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("D")){
								Monto10= Monto10.add(lista.get(j).getMonto10());
							}
							
							
						}
						lista.get(i).setMonto10(Monto10);
					}
				}
				
				//PARA B
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto10=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("B") && lista.get(i).getMonto10().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("B") )&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("C")){
								Monto10= Monto10.add(lista.get(j).getMonto10());
							}
							
							
						}
						lista.get(i).setMonto10(Monto10);
					}
				}
				
				//Monto11
				//PARA E
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto11=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("E") && lista.get(i).getMonto11().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("E") || lista.get(j).getTipoCuenta().equals("D")  || lista.get(j).getTipoCuenta().equals("C")  || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("F")){
								Monto11= Monto11.add(lista.get(j).getMonto11());
							}
							
							
						}
						lista.get(i).setMonto11(Monto11);
					}
				}
				
				//PARA D
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto11=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("D") && lista.get(i).getMonto11().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("D") || lista.get(j).getTipoCuenta().equals("C")  || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("E")){
								Monto11= Monto11.add(lista.get(j).getMonto11());
							}
							
							
						}
						lista.get(i).setMonto11(Monto11);
					}
				}
				
				//PARA C
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto11=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("C") && lista.get(i).getMonto11().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("C") || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("D")){
								Monto11= Monto11.add(lista.get(j).getMonto11());
							}
							
							
						}
						lista.get(i).setMonto11(Monto11);
					}
				}
				
				//PARA B
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto11=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("B") && lista.get(i).getMonto11().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("B") )&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("C")){
								Monto11= Monto11.add(lista.get(j).getMonto11());
							}
							
							
						}
						lista.get(i).setMonto11(Monto11);
					}
				}
			
				//Monto12
				//PARA E
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto12=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("E") && lista.get(i).getMonto12().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("E") || lista.get(j).getTipoCuenta().equals("D")  || lista.get(j).getTipoCuenta().equals("C")  || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("F")){
								Monto12= Monto12.add(lista.get(j).getMonto12());
							}
							
							
						}
						lista.get(i).setMonto12(Monto12);
					}
				}
				
				//PARA D
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto12=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("D") && lista.get(i).getMonto12().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("D") || lista.get(j).getTipoCuenta().equals("C")  || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("E")){
								Monto12= Monto12.add(lista.get(j).getMonto12());
							}
							
							
						}
						lista.get(i).setMonto12(Monto12);
					}
				}
				
				//PARA C
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto12=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("C") && lista.get(i).getMonto12().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("C") || lista.get(j).getTipoCuenta().equals("B"))&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("D")){
								Monto12= Monto12.add(lista.get(j).getMonto12());
							}
							
							
						}
						lista.get(i).setMonto12(Monto12);
					}
				}
				
				//PARA B
				for(int i=0; i<lista.size(); i++){
					Boolean encontroValor=Boolean.FALSE;
					BigDecimal Monto12=BigDecimal.ZERO;
					if(lista.get(i).getTipoCuenta().trim().equals("B") && lista.get(i).getMonto12().compareTo(BigDecimal.ZERO)==0){
						for(int j=i; j<lista.size(); j++){
							if((lista.get(j).getTipoCuenta().equals("B") )&& j>i){
								encontroValor= Boolean.TRUE;
							}
							if(!encontroValor && lista.get(j).getTipoCuenta().trim().equals("C")){
								Monto12= Monto12.add(lista.get(j).getMonto12());
							}
							
							
						}
						lista.get(i).setMonto12(Monto12);
					}
				}
				
	}
	
	public Date sumarMesFecha(Date fecha, Integer numero){
		Calendar calendar = Calendar.getInstance();
	    calendar.setTime(fecha); // Configuramos la fecha que se recibe
	    calendar.add(Calendar.MONTH, numero);  // numero de días a añadir, o restar en caso de días<0
        return calendar.getTime();
	}
	

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public Boolean getRenderedResultado() {
		return renderedResultado;
	}

	public void setRenderedResultado(Boolean renderedResultado) {
		this.renderedResultado = renderedResultado;
	}

	public PersonaSBS getPersonaSBS() {
		return personaSBS;
	}

	public void setPersonaSBS(PersonaSBS personaSBS) {
		this.personaSBS = personaSBS;
	}

	public List<CalificacionPonderada> getListCalificacionPonderada() {
		return listCalificacionPonderada;
	}

	public void setListCalificacionPonderada(
			List<CalificacionPonderada> listCalificacionPonderada) {
		this.listCalificacionPonderada = listCalificacionPonderada;
	}

	public List<ReporteCrediticio> getListReporteCrediticioSoles() {
		return listReporteCrediticioSoles;
	}

	public void setListReporteCrediticioSoles(
			List<ReporteCrediticio> listReporteCrediticioSoles) {
		this.listReporteCrediticioSoles = listReporteCrediticioSoles;
	}

	public List<ReporteCrediticio> getListReporteCrediticioDolares() {
		return listReporteCrediticioDolares;
	}

	public void setListReporteCrediticioDolares(
			List<ReporteCrediticio> listReporteCrediticioDolares) {
		this.listReporteCrediticioDolares = listReporteCrediticioDolares;
	}

	public List<DetalleEntidad> getListDetalleEntidadSoles() {
		return listDetalleEntidadSoles;
	}

	public void setListDetalleEntidadSoles(
			List<DetalleEntidad> listDetalleEntidadSoles) {
		this.listDetalleEntidadSoles = listDetalleEntidadSoles;
	}

	public List<DetalleEntidad> getListDetalleEntidadDolares() {
		return listDetalleEntidadDolares;
	}

	public void setListDetalleEntidadDolares(
			List<DetalleEntidad> listDetalleEntidadDolares) {
		this.listDetalleEntidadDolares = listDetalleEntidadDolares;
	}


	public Date getMes1() {
		return mes1;
	}

	public void setMes1(Date mes1) {
		this.mes1 = mes1;
	}

	public Date getMes2() {
		return mes2;
	}

	public void setMes2(Date mes2) {
		this.mes2 = mes2;
	}

	public Date getMes3() {
		return mes3;
	}

	public void setMes3(Date mes3) {
		this.mes3 = mes3;
	}

	public Date getMes4() {
		return mes4;
	}

	public void setMes4(Date mes4) {
		this.mes4 = mes4;
	}

	public Date getMes5() {
		return mes5;
	}

	public void setMes5(Date mes5) {
		this.mes5 = mes5;
	}

	public Date getMes6() {
		return mes6;
	}

	public void setMes6(Date mes6) {
		this.mes6 = mes6;
	}

	public Date getMes7() {
		return mes7;
	}

	public void setMes7(Date mes7) {
		this.mes7 = mes7;
	}

	public Date getMes8() {
		return mes8;
	}

	public void setMes8(Date mes8) {
		this.mes8 = mes8;
	}

	public Date getMes9() {
		return mes9;
	}

	public void setMes9(Date mes9) {
		this.mes9 = mes9;
	}

	public Date getMes10() {
		return mes10;
	}

	public void setMes10(Date mes10) {
		this.mes10 = mes10;
	}

	public Date getMes11() {
		return mes11;
	}

	public void setMes11(Date mes11) {
		this.mes11 = mes11;
	}

	public Date getMes12() {
		return mes12;
	}

	public void setMes12(Date mes12) {
		this.mes12 = mes12;
	}

	public Boolean getRenderedResultadoPersona() {
		return renderedResultadoPersona;
	}

	public void setRenderedResultadoPersona(Boolean renderedResultadoPersona) {
		this.renderedResultadoPersona = renderedResultadoPersona;
	}

	public List<DireccionesReportadas> getListDireccionesReportadas() {
		return listDireccionesReportadas;
	}

	public void setListDireccionesReportadas(
			List<DireccionesReportadas> listDireccionesReportadas) {
		this.listDireccionesReportadas = listDireccionesReportadas;
	}
		
	public LogSBS getLsbs() {
		return lsbs;
	}

	public void setLsbs(LogSBS lsbs) {
		this.lsbs = lsbs;
	}	
	
	public LoginMB getLogin() {
		return login;
	}

	public void setLogin(LoginMB login) {
		this.login = login;
	}

	public Usuario getUsuarioLogin() {
		return usuarioLogin;
	}

	public void setUsuarioLogin(Usuario usuarioLogin) {
		this.usuarioLogin = usuarioLogin;
	}

	//	metodo para generar pdf
	public void imprimirPDF(){
		System.out.println("IMPRIMIENDO PDF");
	

		try {
			ServletContext servletContext = (ServletContext) (FacesContext.getCurrentInstance().getExternalContext().getContext());
			
			
			Map<String, Object> input =new  HashMap<String,Object>();
			
			input.put("P_USUARIO", "Edgar Medina Vargas");
			//input.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE); // no parte la pagina todo lo mete en un A4
			
			String path = ExportarArchivo.getPath("/resources/reports/jxrml/rptCentralRiesgoPDF.jasper");
			HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
			
			byte[] bytes;
//				bytes = ExportarArchivo.exportPdf(path, input);
//			ExportarArchivo.executePdf(bytes, "CentralRiesgoPDF.pdf");
			FacesContext.getCurrentInstance().responseComplete();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
	
	
	public String imprimirPDF(String nombre){
		System.out.println("IMPRIMIENDO PDF "+nombre);
		return "SUCCESS";
	}
	
	
}