package com.certicom.certiscan.jdbc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.certicom.certiscan.domain.CalificacionPonderadaAT;
import com.certicom.certiscan.domain.DetalleEntidad;
import com.certicom.certiscan.domain.DireccionesReportadas;
import com.certicom.certiscan.domain.PersonaSBS;
import com.certicom.certiscan.domain.ReporteCrediticio;

import java.sql.Statement;

public class CentralRiesgoDao {

	private Connection conn = null;
	private CallableStatement sp;
	private PreparedStatement pst;
	
	public CentralRiesgoDao(Connection conn){
		this.conn = conn;
	}
	
	public List<PersonaSBS> reporteDirectorioReniec(String  doc) throws SQLException {
		Statement st = null;
		st = conn.createStatement();
		List<PersonaSBS> lista = new ArrayList<PersonaSBS>();
	  String cons= "  select distinct coalesce(re.no_nombre|| ', '||re.no_apepat|| ' '|| re.no_apemat,'Sin Informacion') as NombreCompleto, "+ 
	  	  "coalesce(substring(re.fe_nac,7,2) || '/'||substring(re.fe_nac,5,2)|| '/'|| substring(re.fe_nac,1,4),'Sin Informacion') as fecha, "+
	  	 "'PERU' as peru,  "+
	  	 " coalesce(re.co_gradoinst,'Sin Informacion') as grado,"+
	  	"coalesce(de.no_departamento || ' / '|| pro.no_provincia || ' / '|| dis.no_distrito,'Ubigeo no encontrado')  as ubigeo "+
	  	"from   cert.reniec re "+
	  	"  left join cert.tbdepartamento de on( substring(re.co_ubigeo,1,2)=de.co_departamento)"+
	  	"  left join  cert.tbprovincia pro on( substring(re.co_ubigeo,3,2)=pro.co_provincia and substring(re.co_ubigeo,1,2)=pro.co_departamento )"+
	  	"  left join cert.tbdistrito dis on(substring(re.co_ubigeo,3,2)=dis.co_provincia and substring(re.co_ubigeo,1,2)=dis.co_departamento and substring(re.co_ubigeo,5,2)=dis.co_distrito )"+
	  	" where re.co_docide='"+doc+"'";
	  		
	  ResultSet rs = st.executeQuery(cons);  
	 while (rs.next()) {
		 PersonaSBS personaSBS = new PersonaSBS();
		 personaSBS.setNombreCompleto(rs.getString(1));
		 personaSBS.setFechaNac(rs.getString(2));
		 personaSBS.setOrigen(rs.getString(3));
		 personaSBS.setNivelEducacion(rs.getString(4));
		 personaSBS.setUbigeo(rs.getString(5));
		 lista.add(personaSBS);
	 }
	 return lista;	 
	}
	
	public List<PersonaSBS> reporteDirectorioReniecRuc(String  doc) throws SQLException {
		Statement st = null;
		st = conn.createStatement();
		List<PersonaSBS> lista = new ArrayList<PersonaSBS>();
	  String cons= " select distinct coalesce(re.no_nombre|| ', '||re.no_apepat|| ' '|| re.no_apemat,'Sin Informacion') as NombreCompleto,"+  
	  	 " coalesce(substring(re.fe_nac,7,2) || '/'||substring(re.fe_nac,5,2)|| '/'|| substring(re.fe_nac,1,4),'Sin Informacion') as fecha,"+
	  	" 'PERU' as peru,   coalesce(re.co_gradoinst,'Sin Informacion') as grado,"+
	  	" coalesce(de.no_departamento || ' / '|| pro.no_provincia || ' / '|| dis.no_distrito,'Sin Informacion')  as ubigeo "+
	  	" from cert.tbiden_clie iden"+
         "  left join   cert.reniec re on(re.co_docide=iden.nu_docuide)"+
         "  left join cert.tbdepartamento de on( substring(re.co_ubigeo,1,2)=de.co_departamento)"+
         "  left join  cert.tbprovincia pro on( substring(re.co_ubigeo,3,2)=pro.co_provincia and substring(re.co_ubigeo,1,2)=pro.co_departamento )"+
         "  left join cert.tbdistrito dis on(substring(re.co_ubigeo,3,2)=dis.co_provincia and substring(re.co_ubigeo,1,2)=dis.co_departamento and substring(re.co_ubigeo,5,2)=dis.co_distrito )"+
        " where iden.nu_ruc='"+doc+"'";
	  		
	  ResultSet rs = st.executeQuery(cons); 	  
	 while (rs.next()) {
		 PersonaSBS personaSBS = new PersonaSBS();
		 personaSBS.setNombreCompleto(rs.getString(1));
		 personaSBS.setFechaNac(rs.getString(2));
		 personaSBS.setOrigen(rs.getString(3));
		 personaSBS.setNivelEducacion(rs.getString(4));
		 personaSBS.setUbigeo(rs.getString(5));
		 lista.add(personaSBS);
	 }
	 return lista;	 
	}
	
	public List<DireccionesReportadas> reporteDirectorioEssaludcRuc(String  doc) throws SQLException {
		Statement st = null;
		st = conn.createStatement();
		List<DireccionesReportadas> lista = new ArrayList<DireccionesReportadas>();
	  String cons= "select distinct to_date((es.anno||'/'||es.mes||'/'||es.dia),'YYYY MM DD') as fecha,"+ 
	          "coalesce(es.direccion,'Sin Informacion') as direccion ,coalesce(es.distrito,'Sin Informacion') as distrito, coalesce(es.departa,'Sin Informacion') as dpto,"+
		  	  "coalesce(es.telefono,'Sin Informacion') as telfono, 'ESSALUD' as fuente, 'OFICINA' as tipo "+ 
		  	 "from cert.tbiden_clie iden "+
		  	 "left join cert.essalud es on(es.dnigeo=iden.nu_docuide) "+
		  	 "where    iden.nu_ruc = '"+doc+"'";
	  		
	  ResultSet rs = st.executeQuery(cons); 	  
	 while (rs.next()) {
		 DireccionesReportadas direccrepSBS = new DireccionesReportadas();
		 
		 direccrepSBS.setFecha(rs.getString("fecha"));
		 direccrepSBS.setDireccion(rs.getString("direccion"));
		 direccrepSBS.setDistrito(rs.getString("distrito"));
		 direccrepSBS.setDepartamento(rs.getString("dpto"));
		 direccrepSBS.setTelefono(rs.getString("telfono"));
		 direccrepSBS.setFuente(rs.getString("fuente"));
		 direccrepSBS.setTipo(rs.getString("tipo"));
		 lista.add(direccrepSBS);
	 }
	 return lista;	 
	}
	
	public List<DireccionesReportadas> reporteDirectorioEssaludcDNI(String  doc) throws SQLException {
		Statement st = null;
		st = conn.createStatement();
		List<DireccionesReportadas> lista = new ArrayList<DireccionesReportadas>();
	  String cons= "select distinct to_date((es.anno||'/'||es.mes||'/'||es.dia),'YYYY MM DD') as fecha,"+ 
	          "coalesce(es.direccion,'Sin Informacion') as direccion ,coalesce(es.distrito,'Sin Informacion') as distrito, coalesce(es.departa,'Sin Informacion') as dpto,"+ 
		  	  "coalesce(es.telefono,'Sin Informacion') as telfono, 'ESSALUD' as fuente, 'OFICINA' as tipo "+  
		  	 "from  cert.essalud es "+ 
		  	 "where    es.dnigeo = '"+doc+"'";
	  		
	  ResultSet rs = st.executeQuery(cons); 	  
	 while (rs.next()) {
		 DireccionesReportadas direccrepSBS = new DireccionesReportadas();
		 direccrepSBS.setFecha(rs.getString("fecha"));
		 direccrepSBS.setDireccion(rs.getString("direccion"));
		 direccrepSBS.setDistrito(rs.getString("distrito"));
		 direccrepSBS.setDepartamento(rs.getString("dpto"));
		 direccrepSBS.setTelefono(rs.getString("telfono"));
		 direccrepSBS.setFuente(rs.getString("fuente"));
		 direccrepSBS.setTipo(rs.getString("tipo"));
		 lista.add(direccrepSBS);
	 }
	 return lista;	 
	}
	
	public List<CalificacionPonderadaAT> listarRCCRUCCali(String tipoDoc, String numdoc) throws SQLException{
		List<CalificacionPonderadaAT> lista = new ArrayList<CalificacionPonderadaAT>();
		sp = conn.prepareCall("{call cert.SP_LISTAR_CALI_RUC(?, ?)}");
		sp.setString(1,tipoDoc);
		sp.setString(2, numdoc);
		
		ResultSet rs = sp.executeQuery();
		CalificacionPonderadaAT cp;
		while(rs.next()){
			cp = new CalificacionPonderadaAT();
			cp.setMes(rs.getString(1));
			cp.setNormal(rs.getBigDecimal(2));
			cp.setProblemaPotencial(rs.getBigDecimal(3));
			cp.setDeficiente(rs.getBigDecimal(4));
			cp.setDudoso(rs.getBigDecimal(5));
			cp.setPerdida(rs.getBigDecimal(6));
			lista.add(cp);
		}
		
		return lista;
	}
	
	public List<CalificacionPonderadaAT> listarRCCDNICali(String tipoDoc, String numdoc) throws SQLException{
		List<CalificacionPonderadaAT> lista = new ArrayList<CalificacionPonderadaAT>();
		sp = conn.prepareCall("{call cert.SP_LISTAR_CALI_DNI(?, ?)}");
		sp.setString(1,tipoDoc);
		sp.setString(2, numdoc);
		
		ResultSet rs = sp.executeQuery();
		CalificacionPonderadaAT cp;
		while(rs.next()){
			cp = new CalificacionPonderadaAT();
			cp.setMes(rs.getString(1));
			cp.setNormal(rs.getBigDecimal(2));
			cp.setProblemaPotencial(rs.getBigDecimal(3));
			cp.setDeficiente(rs.getBigDecimal(4));
			cp.setDudoso(rs.getBigDecimal(5));
			cp.setPerdida(rs.getBigDecimal(6));
			lista.add(cp);
		}
		
		return lista;
	}
	
	
	
	public List<ReporteCrediticio> listarRCCRUC(String tipoDoc, String numdoc) throws SQLException{
		List<ReporteCrediticio> lista = new ArrayList<ReporteCrediticio>();
		sp = conn.prepareCall("{call cert.SP_LISTAR_RRCC_RUC(?, ?)}");
		sp.setString(1,tipoDoc);
		sp.setString(2, numdoc);
		
		ResultSet rs = sp.executeQuery();
		ReporteCrediticio rc;
		while(rs.next()){
			rc = new ReporteCrediticio();
			rc.setCodigoCuenta(rs.getString(2));
			rc.setNombreCuenta(rs.getString(3));
			rc.setTipoCuenta(rs.getString(4));
			rc.setMonto1(rs.getBigDecimal(5));
			rc.setMonto2(rs.getBigDecimal(6));
			rc.setMonto3(rs.getBigDecimal(7));
			rc.setMonto4(rs.getBigDecimal(8));
			rc.setMonto5(rs.getBigDecimal(9));
			rc.setMonto6(rs.getBigDecimal(10));
			rc.setMonto7(rs.getBigDecimal(11));
			rc.setMonto8(rs.getBigDecimal(12));
			rc.setMonto9(rs.getBigDecimal(13));
			rc.setMonto10(rs.getBigDecimal(14));
			rc.setMonto11(rs.getBigDecimal(15));
			rc.setMonto12(rs.getBigDecimal(16));
			lista.add(rc);
		}
		
		return lista;
	}
	
	public List<ReporteCrediticio> listarRCCDNI(String tipoDoc, String numdoc) throws SQLException{
		List<ReporteCrediticio> lista = new ArrayList<ReporteCrediticio>();
		sp = conn.prepareCall("{call cert.SP_LISTAR_RRCC_DNI(?, ?)}");
		sp.setString(1,tipoDoc);
		sp.setString(2, numdoc);
		
		ResultSet rs = sp.executeQuery();
		ReporteCrediticio rc;
		while(rs.next()){
			rc = new ReporteCrediticio();
			rc.setCodigoCuenta(rs.getString(2));
			rc.setNombreCuenta(rs.getString(3));
			rc.setTipoCuenta(rs.getString(4));
			rc.setMonto1(rs.getBigDecimal(5));
			rc.setMonto2(rs.getBigDecimal(6));
			rc.setMonto3(rs.getBigDecimal(7));
			rc.setMonto4(rs.getBigDecimal(8));
			rc.setMonto5(rs.getBigDecimal(9));
			rc.setMonto6(rs.getBigDecimal(10));
			rc.setMonto7(rs.getBigDecimal(11));
			rc.setMonto8(rs.getBigDecimal(12));
			rc.setMonto9(rs.getBigDecimal(13));
			rc.setMonto10(rs.getBigDecimal(14));
			rc.setMonto11(rs.getBigDecimal(15));
			rc.setMonto12(rs.getBigDecimal(16));
			lista.add(rc);
		}
		
		return lista;
	}
	
	public List<ReporteCrediticio> listarRCCRUCdo(String tipoDoc, String numdoc) throws SQLException{
		List<ReporteCrediticio> lista = new ArrayList<ReporteCrediticio>();
		sp = conn.prepareCall("{call cert.sp_listar_rrcc_ruc_do(?, ?)}");
		sp.setString(1,tipoDoc);
		sp.setString(2, numdoc);
		
		ResultSet rs = sp.executeQuery();
		ReporteCrediticio rc;
		while(rs.next()){
			rc = new ReporteCrediticio();
			rc.setCodigoCuenta(rs.getString(2));
			rc.setNombreCuenta(rs.getString(3));
			rc.setTipoCuenta(rs.getString(4));
			rc.setMonto1(rs.getBigDecimal(5));
			rc.setMonto2(rs.getBigDecimal(6));
			rc.setMonto3(rs.getBigDecimal(7));
			rc.setMonto4(rs.getBigDecimal(8));
			rc.setMonto5(rs.getBigDecimal(9));
			rc.setMonto6(rs.getBigDecimal(10));
			rc.setMonto7(rs.getBigDecimal(11));
			rc.setMonto8(rs.getBigDecimal(12));
			rc.setMonto9(rs.getBigDecimal(13));
			rc.setMonto10(rs.getBigDecimal(14));
			rc.setMonto11(rs.getBigDecimal(15));
			rc.setMonto12(rs.getBigDecimal(16));
			lista.add(rc);
		}
		
		return lista;
	}
	
	public List<ReporteCrediticio> listarRCCDNIdo(String tipoDoc, String numdoc) throws SQLException{
		List<ReporteCrediticio> lista = new ArrayList<ReporteCrediticio>();
		sp = conn.prepareCall("{call cert.sp_listar_rrcc_dni_do(?, ?)}");
		sp.setString(1,tipoDoc);
		sp.setString(2, numdoc);
		
		ResultSet rs = sp.executeQuery();
		ReporteCrediticio rc;
		while(rs.next()){
			rc = new ReporteCrediticio();
			rc.setCodigoCuenta(rs.getString(2));
			rc.setNombreCuenta(rs.getString(3));
			rc.setTipoCuenta(rs.getString(4));
			rc.setMonto1(rs.getBigDecimal(5));
			rc.setMonto2(rs.getBigDecimal(6));
			rc.setMonto3(rs.getBigDecimal(7));
			rc.setMonto4(rs.getBigDecimal(8));
			rc.setMonto5(rs.getBigDecimal(9));
			rc.setMonto6(rs.getBigDecimal(10));
			rc.setMonto7(rs.getBigDecimal(11));
			rc.setMonto8(rs.getBigDecimal(12));
			rc.setMonto9(rs.getBigDecimal(13));
			rc.setMonto10(rs.getBigDecimal(14));
			rc.setMonto11(rs.getBigDecimal(15));
			rc.setMonto12(rs.getBigDecimal(16));
			lista.add(rc);
		}
		
		return lista;
	}
	
	public List<DetalleEntidad> reporteDetallePorEntidadesRUCSoles(String tipoDoc, String numdoc) throws SQLException{
		List<DetalleEntidad> lista = new ArrayList<DetalleEntidad>();
		sp = conn.prepareCall("{call cert.SP_LISTAR_RRCC_DETALLE_RUC(?, ?)}");
		sp.setString(1,tipoDoc);
		sp.setString(2, numdoc);
		
		ResultSet rs = sp.executeQuery();
		DetalleEntidad de;
		while(rs.next()){
			de = new DetalleEntidad();
			de.setCodigoEmpresa(rs.getString(1));
			de.setCreditoVigente(rs.getBigDecimal(2));
			de.setCreditoRefinanciado(rs.getBigDecimal(3));
			de.setCreditoVencido(rs.getBigDecimal(4));
			de.setCreditoCobroJudicial(rs.getBigDecimal(5));
			de.setNombreEntidad(rs.getString(6));
			de.setCalificacion(rs.getString(7));
			lista.add(de);
		}
		
		return lista;
	}
	
	public List<DetalleEntidad> reporteDetallePorEntidadesDNISoles(String tipoDoc, String numdoc) throws SQLException{
		List<DetalleEntidad> lista = new ArrayList<DetalleEntidad>();
		sp = conn.prepareCall("{call cert.SP_LISTAR_RRCC_DETALLE_DNI(?, ?)}");
		sp.setString(1,tipoDoc);
		sp.setString(2, numdoc);
		
		ResultSet rs = sp.executeQuery();
		DetalleEntidad de;
		while(rs.next()){
			de = new DetalleEntidad();
			de.setCodigoEmpresa(rs.getString(1));
			de.setCreditoVigente(rs.getBigDecimal(2));
			de.setCreditoRefinanciado(rs.getBigDecimal(3));
			de.setCreditoVencido(rs.getBigDecimal(4));
			de.setCreditoCobroJudicial(rs.getBigDecimal(5));
			de.setNombreEntidad(rs.getString(6));
			de.setCalificacion(rs.getString(7));
			lista.add(de);
		}
		
		return lista;
	}
	
	public List<DetalleEntidad> reporteDetallePorEntidadesRUCDolares(String tipoDoc, String numdoc) throws SQLException{
		List<DetalleEntidad> lista = new ArrayList<DetalleEntidad>();
		sp = conn.prepareCall("{call cert.SP_LISTAR_RRCC_DETALLE_RUC_DO(?, ?)}");
		sp.setString(1,tipoDoc);
		sp.setString(2, numdoc);
		
		ResultSet rs = sp.executeQuery();
		DetalleEntidad de;
		while(rs.next()){
			de = new DetalleEntidad();
			de.setCodigoEmpresa(rs.getString(1));
			de.setCreditoVigente(rs.getBigDecimal(2));
			de.setCreditoRefinanciado(rs.getBigDecimal(3));
			de.setCreditoVencido(rs.getBigDecimal(4));
			de.setCreditoCobroJudicial(rs.getBigDecimal(5));
			de.setNombreEntidad(rs.getString(6));
			de.setCalificacion(rs.getString(7));
			lista.add(de);
		}
		
		return lista;
	}
	
	public List<DetalleEntidad> reporteDetallePorEntidadesDNIDolares(String tipoDoc, String numdoc) throws SQLException{
		List<DetalleEntidad> lista = new ArrayList<DetalleEntidad>();
		sp = conn.prepareCall("{call cert.SP_LISTAR_RRCC_DETALLE_DNI_DO(?, ?)}");
		sp.setString(1,tipoDoc);
		sp.setString(2, numdoc);
		
		ResultSet rs = sp.executeQuery();
		DetalleEntidad de;
		while(rs.next()){
			de = new DetalleEntidad();
			de.setCodigoEmpresa(rs.getString(1));
			de.setCreditoVigente(rs.getBigDecimal(2));
			de.setCreditoRefinanciado(rs.getBigDecimal(3));
			de.setCreditoVencido(rs.getBigDecimal(4));
			de.setCreditoCobroJudicial(rs.getBigDecimal(5));
			de.setNombreEntidad(rs.getString(6));
			de.setCalificacion(rs.getString(7));
			lista.add(de);
		}
		
		return lista;
	}
	
	
	public String getPeriodoUltimo() throws SQLException {
		Statement st = null;
		st = conn.createStatement();
		String cons= " select fe_reporte from cert.tbiden_clie limit 1";
	  	String resultado="";	
	  	ResultSet rs = st.executeQuery(cons); 	  
		 while (rs.next()) {
			 resultado= rs.getString(1);
			 
		 }
	 return resultado;	 
	}
}
