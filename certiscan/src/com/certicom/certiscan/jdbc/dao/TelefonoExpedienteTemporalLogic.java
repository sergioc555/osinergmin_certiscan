package com.certicom.certiscan.jdbc.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.certicom.certiscan.domain.TelefonoExpedienteTemporal;

public class TelefonoExpedienteTemporalLogic {
	public static List<TelefonoExpedienteTemporal> listarExcluidos(Integer idproducto, Date periodo, Integer idUsuario, Integer idCabeceraIndecopy) throws Exception{
		
		Connection conn = null;
		List<TelefonoExpedienteTemporal> lista = new ArrayList<TelefonoExpedienteTemporal>();
		try{
			conn = Conexion.getConnection();
			TelefonoExpedienteTemporalDao dao = new TelefonoExpedienteTemporalDao(conn);
			lista = dao.listarExcluidos(idproducto, periodo, idUsuario, idCabeceraIndecopy);
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
