package com.certicom.certiscan.jdbc.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.certicom.certiscan.domain.Expediente;
import com.certicom.certiscan.domain.TelefonoExpedienteTemporal;

public class ExpedienteLogic {
	public static boolean actualizarExpedientesRepetidos(Integer idproducto, Date periodo, Date periodoAnterior) throws Exception{
		
		Connection conn = null;
		try{
			conn = Conexion.getConnection();
			ExpedienteDao dao = new ExpedienteDao(conn);
			return dao.actualizarExpedientesRepetidos(idproducto, periodo, periodoAnterior);
		}catch(Exception ex){
			ex.getMessage();
			throw ex;
		}finally{
			if(conn!=null)
				conn.close();
		}
	
	}
	
	public static List<Expediente> listarExpedienteRepetidos(Integer idproducto, Date periodo, Date periodoAnterior) throws Exception{
		
		Connection conn = null;
		List<Expediente> lista = new ArrayList<Expediente>();
		try{
			conn = Conexion.getConnection();
			ExpedienteDao dao = new ExpedienteDao(conn);
			lista = dao.listarExpedienteRepetidos(idproducto, periodo, periodoAnterior);
		}catch(Exception ex){
			ex.getMessage();
			throw ex;
		}finally{
			if(conn!=null)
				conn.close();
		}
		return lista;
	}
	
	public static List<String> listarExpediente(Integer idproducto, Date periodo) throws Exception{
		
		Connection conn = null;
		List<String> lista = new ArrayList<String>();
		try{
			conn = Conexion.getConnection();
			ExpedienteDao dao = new ExpedienteDao(conn);
			lista = dao.listarExpediente(idproducto, periodo);
		}catch(Exception ex){
			ex.getMessage();
			throw ex;
		}finally{
			if(conn!=null)
				conn.close();
		}
		return lista;
	}
	
	public static List<String> listarExpedienterucfecha(Integer idproducto, Date periodo) throws Exception{
		
		Connection conn = null;
		List<String> lista = new ArrayList<String>();
		try{
			conn = Conexion.getConnection();
			ExpedienteDao dao = new ExpedienteDao(conn);
			lista = dao.listarExpedienterucfecha(idproducto, periodo);
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
