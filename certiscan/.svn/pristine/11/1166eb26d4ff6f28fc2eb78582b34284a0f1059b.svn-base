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
import com.certicom.certiscan.domain.ConsultaSBS;
import com.certicom.certiscan.domain.DetalleEntidad;
import com.certicom.certiscan.domain.DireccionesReportadas;
import com.certicom.certiscan.domain.PersonaSBS;
import com.certicom.certiscan.domain.ReporteCrediticio;

import java.sql.Statement;

public class ConsultaSBSDao {

	private Connection conn = null;
	private CallableStatement sp;
	private PreparedStatement pst;
	
	public ConsultaSBSDao(Connection conn){
		this.conn = conn;
	}
	
	public List<ConsultaSBS> consultaPlanCuentas(Integer valor,String co_cuenta) throws SQLException {
		Statement st = null;
		st = conn.createStatement();
		List<ConsultaSBS> lista = new ArrayList<ConsultaSBS>();
	  String cons= "select * from cert.tbcuenta_contable ";
	  
	  if(valor==1){
		  cons+="order by co_cuenta asc";
	  }else{
		  cons+="where co_cuenta is not null and trim(co_cuenta) != '' " +
				  "and co_cuenta like '%"+co_cuenta+"%' order by co_cuenta asc"; 
	  }
	  		
	  		
	  ResultSet rs = st.executeQuery(cons);  
	 while (rs.next()) {
		 ConsultaSBS consultaSBS = new ConsultaSBS();
		 consultaSBS.setCo_cuenta(rs.getString(1));
		 consultaSBS.setTi_cuenta(rs.getString(2));
		 consultaSBS.setNo_cuenta(rs.getString(3));
		 lista.add(consultaSBS);
	 } 
	 
	 /*for (ConsultaSBS consultaSBS : lista) {
		System.out.println("codigo de cuenta "+consultaSBS.getCo_cuenta());
		System.out.println("tipo de cuenta "+consultaSBS.getTi_cuenta());
		System.out.println("nombre de cuenta "+consultaSBS.getNo_cuenta());
		 
	}*/

	 return lista;	 
}
	
}
