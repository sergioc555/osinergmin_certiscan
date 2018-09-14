package com.certicom.certiscan.jdbc.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.certicom.certiscan.domain.CalificacionPonderada;
import com.certicom.certiscan.domain.CalificacionPonderadaAT;
import com.certicom.certiscan.domain.ConsultaSBS;
import com.certicom.certiscan.domain.DetalleEntidad;
import com.certicom.certiscan.domain.DireccionesReportadas;
import com.certicom.certiscan.domain.Expediente;
import com.certicom.certiscan.domain.PersonaSBS;
import com.certicom.certiscan.domain.ReporteCrediticio;
import com.certicom.certiscan.domain.TelefonoExpedienteTemporal;

public class CentralRiesgoLogic {
	
	public static List<PersonaSBS> reporteDirectorioReniec(String tipodoc, String numdoc) throws Exception{
		
		Connection conn = null;
		List<PersonaSBS> lista = new ArrayList<PersonaSBS>();
		try{
			conn = Conexion.getConnectionSBS();
			CentralRiesgoDao dao = new CentralRiesgoDao(conn);
			if (tipodoc.equals("0")){
			lista = dao.reporteDirectorioReniecRuc(numdoc);
			}else{
				lista = dao.reporteDirectorioReniec(numdoc);	
			}
		}catch(Exception ex){
			ex.getMessage();
			throw ex;
		}finally{
			if(conn!=null)
				conn.close();
		}
		return lista;
	}
	
public static List<DireccionesReportadas> reporteDirectorioEssalud(String tipodoc, String numdoc) throws Exception{
		
		Connection conn = null;
		List<DireccionesReportadas> lista = new ArrayList<DireccionesReportadas>();
		try{
			conn = Conexion.getConnectionSBS();
			CentralRiesgoDao dao = new CentralRiesgoDao(conn);
			if (tipodoc.equals("0")){
			lista = dao.reporteDirectorioEssaludcRuc(numdoc);
			}else{
				lista = dao.reporteDirectorioEssaludcDNI(numdoc);	
			}
		}catch(Exception ex){
			ex.getMessage();
			throw ex;
		}finally{
			if(conn!=null)
				conn.close();
		}
		return lista;
	}
	
	public static List<CalificacionPonderada> listarRCCCali(String tipodoc, String numdoc) throws Exception{
		
		Connection conn = null;
		List<CalificacionPonderadaAT> lista = new ArrayList<CalificacionPonderadaAT>();
		List<CalificacionPonderada> listafinal = new ArrayList<CalificacionPonderada>();
		try{
			conn = Conexion.getConnectionSBS();
			CentralRiesgoDao dao = new CentralRiesgoDao(conn);
			if (tipodoc.equals("0")){
			lista = dao.listarRCCRUCCali(tipodoc, numdoc);
			if (lista.size()>0){
			listafinal = transponer(lista);
			}else {
				listafinal = new ArrayList<CalificacionPonderada>();
			}
			}else{
				lista = dao.listarRCCDNICali(tipodoc, numdoc);	
				if (lista.size()>0){
				listafinal = transponer(lista);
				}else {
					listafinal = new ArrayList<CalificacionPonderada>();
				}
			}
			
			
		}catch(Exception ex){
			ex.getMessage();
			throw ex;
		}finally{
			if(conn!=null)
				conn.close();
		}
		return listafinal;
	}
	
	public static List<ReporteCrediticio> listarRCCSoles(String tipodoc, String numdoc) throws Exception{
		
		Connection conn = null;
		List<ReporteCrediticio> lista = new ArrayList<ReporteCrediticio>();
		try{
			conn = Conexion.getConnectionSBS();
			CentralRiesgoDao dao = new CentralRiesgoDao(conn);
			if (tipodoc.equals("0")){
			lista = dao.listarRCCRUC(tipodoc, numdoc);
			}else{
				lista = dao.listarRCCDNI(tipodoc, numdoc);	
			}
		}catch(Exception ex){
			ex.getMessage();
			throw ex;
		}finally{
			if(conn!=null)
				conn.close();
		}
		return lista;
	}
	
	public static List<ReporteCrediticio> listarRCCDolares(String tipodoc, String numdoc) throws Exception{
	
	Connection conn = null;
	List<ReporteCrediticio> lista = new ArrayList<ReporteCrediticio>();
	try{
		conn = Conexion.getConnectionSBS();
		CentralRiesgoDao dao = new CentralRiesgoDao(conn);
		if (tipodoc.equals("0")){
		lista = dao.listarRCCRUCdo(tipodoc, numdoc);
		}else{
			lista = dao.listarRCCDNIdo(tipodoc, numdoc);	
		}
	}catch(Exception ex){
		ex.getMessage();
		throw ex;
	}finally{
		if(conn!=null)
			conn.close();
	}
	return lista;
}

	public static List<CalificacionPonderada> transponer(List<CalificacionPonderadaAT> caliLista){
		List<CalificacionPonderada> lista=new ArrayList<CalificacionPonderada>();
		List<CalificacionPonderada> listafinal=new ArrayList<CalificacionPonderada>();
		CalificacionPonderada cali=null;
		
		cali=new CalificacionPonderada();
		
		cali.setCalificacion("Normal");
		lista.add(0,cali);
		cali=new CalificacionPonderada();
		cali.setCalificacion("Problema Potencial");
		lista.add(1,cali);
		cali=new CalificacionPonderada();
		cali.setCalificacion("Deficiente");
		lista.add(2,cali);
		cali=new CalificacionPonderada();
		cali.setCalificacion("Dudoso");
		lista.add(3,cali);
		cali=new CalificacionPonderada();
		cali.setCalificacion("Perdida");
		lista.add(4,cali);
		
		cali=new CalificacionPonderada();
		
		for (CalificacionPonderada califP: lista){
			
			if (califP.getCalificacion().equals("Normal")){
						
						
							for (CalificacionPonderadaAT cpat: caliLista){
								
								if(cpat.getMes().equals("1")){
									califP.setMes1(cpat.getNormal());
								}else if(cpat.getMes().equals("2")){
									califP.setMes2(cpat.getNormal());
								}else if(cpat.getMes().equals("3")){
									califP.setMes3(cpat.getNormal());
								}else if(cpat.getMes().equals("4")){
									califP.setMes4(cpat.getNormal());
								}else if(cpat.getMes().equals("5")){
									califP.setMes5(cpat.getNormal());
								}else if(cpat.getMes().equals("6")){
									califP.setMes6(cpat.getNormal());
								}else if(cpat.getMes().equals("7")){
									califP.setMes7(cpat.getNormal());
								}else if(cpat.getMes().equals("8")){
									califP.setMes8(cpat.getNormal());
								}else if(cpat.getMes().equals("9")){
									califP.setMes9(cpat.getNormal());
								}else if(cpat.getMes().equals("10")){
									califP.setMes10(cpat.getNormal());
								}else if(cpat.getMes().equals("11")){
									califP.setMes11(cpat.getNormal());
								}else if(cpat.getMes().equals("12")){
									califP.setMes12(cpat.getNormal());
								}
							}
						
					
			}
			if (califP.getCalificacion().equals("Problema Potencial")){
				
				for (CalificacionPonderadaAT cpat: caliLista){
					
					if(cpat.getMes().equals("1")){
						califP.setMes1(cpat.getProblemaPotencial());
					}else if(cpat.getMes().equals("2")){
						califP.setMes2(cpat.getProblemaPotencial());
					}else if(cpat.getMes().equals("3")){
						califP.setMes3(cpat.getProblemaPotencial());
					}else if(cpat.getMes().equals("4")){
						califP.setMes4(cpat.getProblemaPotencial());
					}else if(cpat.getMes().equals("5")){
						califP.setMes5(cpat.getProblemaPotencial());
					}else if(cpat.getMes().equals("6")){
						califP.setMes6(cpat.getProblemaPotencial());
					}else if(cpat.getMes().equals("7")){
						califP.setMes7(cpat.getProblemaPotencial());
					}else if(cpat.getMes().equals("8")){
						califP.setMes8(cpat.getProblemaPotencial());
					}else if(cpat.getMes().equals("9")){
						califP.setMes9(cpat.getProblemaPotencial());
					}else if(cpat.getMes().equals("10")){
						califP.setMes10(cpat.getProblemaPotencial());
					}else if(cpat.getMes().equals("11")){
						califP.setMes11(cpat.getProblemaPotencial());
					}else if(cpat.getMes().equals("12")){
						califP.setMes12(cpat.getProblemaPotencial());
					}
				}
				
			}
			if (califP.getCalificacion().equals("Deficiente")){
				
				for (CalificacionPonderadaAT cpat: caliLista){
					
					if(cpat.getMes().equals("1")){
						califP.setMes1(cpat.getDeficiente());
					}else if(cpat.getMes().equals("2")){
						califP.setMes2(cpat.getDeficiente());
					}else if(cpat.getMes().equals("3")){
						califP.setMes3(cpat.getDeficiente());
					}else if(cpat.getMes().equals("4")){
						califP.setMes4(cpat.getDeficiente());
					}else if(cpat.getMes().equals("5")){
						califP.setMes5(cpat.getDeficiente());
					}else if(cpat.getMes().equals("6")){
						califP.setMes6(cpat.getDeficiente());
					}else if(cpat.getMes().equals("7")){
						califP.setMes7(cpat.getDeficiente());
					}else if(cpat.getMes().equals("8")){
						califP.setMes8(cpat.getDeficiente());
					}else if(cpat.getMes().equals("9")){
						califP.setMes9(cpat.getDeficiente());
					}else if(cpat.getMes().equals("10")){
						califP.setMes10(cpat.getDeficiente());
					}else if(cpat.getMes().equals("11")){
						califP.setMes11(cpat.getDeficiente());
					}else if(cpat.getMes().equals("12")){
						califP.setMes12(cpat.getDeficiente());
					}
				}
			}
			if (califP.getCalificacion().equals("Dudoso")){
				for (CalificacionPonderadaAT cpat: caliLista){
					
					if(cpat.getMes().equals("1")){
						califP.setMes1(cpat.getDudoso());
					}else if(cpat.getMes().equals("2")){
						califP.setMes2(cpat.getDudoso());
					}else if(cpat.getMes().equals("3")){
						califP.setMes3(cpat.getDudoso());
					}else if(cpat.getMes().equals("4")){
						califP.setMes4(cpat.getDudoso());
					}else if(cpat.getMes().equals("5")){
						califP.setMes5(cpat.getDudoso());
					}else if(cpat.getMes().equals("6")){
						califP.setMes6(cpat.getDudoso());
					}else if(cpat.getMes().equals("7")){
						califP.setMes7(cpat.getDudoso());
					}else if(cpat.getMes().equals("8")){
						califP.setMes8(cpat.getDudoso());
					}else if(cpat.getMes().equals("9")){
						califP.setMes9(cpat.getDudoso());
					}else if(cpat.getMes().equals("10")){
						califP.setMes10(cpat.getDudoso());
					}else if(cpat.getMes().equals("11")){
						califP.setMes11(cpat.getDudoso());
					}else if(cpat.getMes().equals("12")){
						califP.setMes12(cpat.getDudoso());
					}
				}
			}
			if (califP.getCalificacion().equals("Perdida")){
				for (CalificacionPonderadaAT cpat: caliLista){
					
					if(cpat.getMes().equals("1")){
						califP.setMes1(cpat.getPerdida());
					}else if(cpat.getMes().equals("2")){
						califP.setMes2(cpat.getPerdida());
					}else if(cpat.getMes().equals("3")){
						califP.setMes3(cpat.getPerdida());
					}else if(cpat.getMes().equals("4")){
						califP.setMes4(cpat.getPerdida());
					}else if(cpat.getMes().equals("5")){
						califP.setMes5(cpat.getPerdida());
					}else if(cpat.getMes().equals("6")){
						califP.setMes6(cpat.getPerdida());
					}else if(cpat.getMes().equals("7")){
						califP.setMes7(cpat.getPerdida());
					}else if(cpat.getMes().equals("8")){
						califP.setMes8(cpat.getPerdida());
					}else if(cpat.getMes().equals("9")){
						califP.setMes9(cpat.getPerdida());
					}else if(cpat.getMes().equals("10")){
						califP.setMes10(cpat.getPerdida());
					}else if(cpat.getMes().equals("11")){
						califP.setMes11(cpat.getPerdida());
					}else if(cpat.getMes().equals("12")){
						califP.setMes12(cpat.getPerdida());
					}
				}
			}
		}
		
		if(caliLista.size()==0)
		lista=null;
		
		return lista;
	}
	
	
	public static List<DetalleEntidad> reporteDetallePorEntidadesDolares(String tipodoc, String numdoc) throws Exception{
		
		Connection conn = null;
		List<DetalleEntidad> lista = new ArrayList<DetalleEntidad>();
		try{
			conn = Conexion.getConnectionSBS();
			CentralRiesgoDao dao = new CentralRiesgoDao(conn);
			if (tipodoc.equals("0")){
			lista = dao.reporteDetallePorEntidadesRUCDolares(tipodoc, numdoc);
			}else{
				lista = dao.reporteDetallePorEntidadesDNIDolares(tipodoc, numdoc);	
			}
		}catch(Exception ex){
			ex.getMessage();
			throw ex;
		}finally{
			if(conn!=null)
				conn.close();
		}
		return lista;
	}
	
	public static List<DetalleEntidad> reporteDetallePorEntidadesSoles(String tipodoc, String numdoc) throws Exception{
		
		Connection conn = null;
		List<DetalleEntidad> lista = new ArrayList<DetalleEntidad>();
		try{
			conn = Conexion.getConnectionSBS();
			CentralRiesgoDao dao = new CentralRiesgoDao(conn);
			if (tipodoc.equals("0")){
			lista = dao.reporteDetallePorEntidadesRUCSoles(tipodoc, numdoc);
			}else{
				lista = dao.reporteDetallePorEntidadesDNISoles(tipodoc, numdoc);	
			}
		}catch(Exception ex){
			ex.getMessage();
			throw ex;
		}finally{
			if(conn!=null)
				conn.close();
		}
		return lista;
	}
	
	public static String getPeriodoUltimo() throws Exception{
		
		Connection conn = null;
		String resultado="";
		try{
			conn = Conexion.getConnectionSBS();
			CentralRiesgoDao dao = new CentralRiesgoDao(conn);
			resultado = dao.getPeriodoUltimo();	
			
		}catch(Exception ex){
			ex.getMessage();
			throw ex;
		}finally{
			if(conn!=null)
				conn.close();
		}
		return resultado;
		
	}
	
	
	public static List<ConsultaSBS> consultaPlanCuentas(Integer valor,String co_cuenta) throws Exception{
		
		Connection conn = null;
		List<ConsultaSBS> lista = new ArrayList<ConsultaSBS>();
		try{
			conn = Conexion.getConnectionSBS();
			ConsultaSBSDao dao = new ConsultaSBSDao(conn);
			
			lista = dao.consultaPlanCuentas(valor,co_cuenta);
			
		}catch(Exception ex){
			ex.getMessage();
			throw ex;
		}finally{
			if(conn!=null)
				conn.close();
		}
		return lista;
	}
	
	
}
