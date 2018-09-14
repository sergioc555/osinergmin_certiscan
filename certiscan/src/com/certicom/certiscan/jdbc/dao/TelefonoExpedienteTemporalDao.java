package com.certicom.certiscan.jdbc.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.certicom.certiscan.domain.TelefonoExpedienteTemporal;

public class TelefonoExpedienteTemporalDao {

	private Connection conn = null;
	private CallableStatement sp;
	
	public TelefonoExpedienteTemporalDao(Connection conn){
		this.conn = conn;
	}
	
	public List<TelefonoExpedienteTemporal> listarExcluidos(Integer idproducto, Date periodo, Integer idUsuario, Integer idCabeceraIndecopy) throws SQLException{
		List<TelefonoExpedienteTemporal> lista = new ArrayList<TelefonoExpedienteTemporal>();
		//sp = conn.prepareCall("{call pa_listartelefonosexpedientes(idprod integer, period date, idusuar integer, idcabeceraind integer)}")
		sp = conn.prepareCall("{call pa_listartelefonosexpedientes2(?, ?, ?, ?)}");
		sp.setInt(1,idproducto);
		sp.setDate(2, new java.sql.Date(periodo.getTime()));
		sp.setInt(3, idUsuario);
		sp.setInt(4, idCabeceraIndecopy);
		
		ResultSet rs = sp.executeQuery();
		TelefonoExpedienteTemporal t;
		while(rs.next()){
			t = new TelefonoExpedienteTemporal();
			t.setId(rs.getInt(1));
			t.setValor(rs.getString(2));
			t.setIdProducto(rs.getInt(3));
			t.setPeriodo(rs.getDate(4));
			t.setIdUsuario(rs.getInt(5));
			lista.add(t);
		}
		
		return lista;
	}
	
	
	
}
