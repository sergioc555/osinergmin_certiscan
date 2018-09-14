package com.certicom.certiscan.jdbc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.certicom.certiscan.domain.Expediente;
import com.certicom.certiscan.domain.TelefonoExpedienteTemporal;

public class ExpedienteDao {

	private Connection conn = null;
	private CallableStatement sp;
	private PreparedStatement pst;
	
	public ExpedienteDao(Connection conn){
		this.conn = conn;
	}
	
	public boolean actualizarExpedientesRepetidos(Integer idproducto, Date periodo, Date periodoAnterior) throws SQLException{
		java.sql.Date d = new java.sql.Date(periodo.getTime());
		java.sql.Date d2 = new java.sql.Date(periodoAnterior.getTime());
		String sql = "UPDATE t_expediente e1 "+
				" SET estado_nuevo=false "+
				" FROM t_expediente e2 "+
				" WHERE "+
				 "   e1.num_doc = e2.num_doc AND"+
				 "   e1.periodo= ? AND"+
				 "   e2.periodo= ? AND"+
				 "   e1.id_producto= ? AND"+
				 "   e2.id_producto= ? ";
		 //System.out.println("SQL: "+sql);
		 pst = conn.prepareStatement(sql);
		 pst.setDate(1, d);
		 pst.setDate(2, d2); 
		 pst.setInt(3,idproducto);
		 pst.setInt(4,idproducto);
		
		 pst.executeUpdate();
		
		return true;
	}
	
	
	public List<Expediente> listarExpedienteRepetidos(Integer idproducto, Date periodo, Date periodoAnterior) throws SQLException{
		List<Expediente> lista = new ArrayList<Expediente>();
//		sp = conn.prepareCall("{call pa_listarexpedienterepetidos(?, ?, ?)}");
//		//System.out.println("Prod "+idproducto);
//		//System.out.println("periodo "+periodo);
//		//System.out.println("periodoAnterior "+periodoAnterior);
//		sp.setInt(1,idproducto);
//		sp.setDate(2, new java.sql.Date(periodo.getTime()));
//		sp.setDate(3, new java.sql.Date(periodoAnterior.getTime()));
//	
//		ResultSet rs = sp.executeQuery();
//		Expediente e;
//		while(rs.next()){
//			e = new Expediente();
//			e.setExpediente_id(rs.getInt(1));
//			e.setNum_doc(rs.getString(2));
//			e.setPeriodo(rs.getDate(3));
//			e.setId_producto(rs.getInt(4));
//			lista.add(e);
//		}
		
		return lista;
	}
	
	public List<String> listarExpediente(Integer idproducto, Date periodo) throws SQLException{
		List<String> lista = new ArrayList<String>();
		sp = conn.prepareCall("{call pa_listarexpediente(?, ?)}");
		sp.setInt(1,idproducto);
		sp.setDate(2, new java.sql.Date(periodo.getTime()));
		
		ResultSet rs = sp.executeQuery();
		Expediente e;
		while(rs.next()){
			//e = new Expediente();
			//e.setExpediente_id(rs.getInt(1));
			//e.setNum_doc(rs.getString(2));
			//e.setPeriodo(rs.getDate(3));
			//e.setId_producto(rs.getInt(4));
			lista.add(rs.getString(2));
		}
		
		return lista;
	}
	
	
	public List<String> listarExpedienterucfecha(Integer idproducto, Date periodo) throws SQLException{
		List<String> lista = new ArrayList<String>();
		sp = conn.prepareCall("{call pa_listarexpedienterucfecha(?, ?)}");
		sp.setInt(1,idproducto);
		sp.setDate(2, new java.sql.Date(periodo.getTime()));
		
		ResultSet rs = sp.executeQuery();
		while(rs.next()){
			lista.add(rs.getString(1));
		}
		
		return lista;
	}
}
