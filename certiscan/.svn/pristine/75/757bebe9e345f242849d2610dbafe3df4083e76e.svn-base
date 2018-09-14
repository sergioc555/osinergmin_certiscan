package com.certicom.certiscan.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ReniecDAO {

	private Connection conn = null;
	private PreparedStatement pst;
	
	
	public ReniecDAO(Connection conn){
		this.conn = conn;
	}
	
	public String getRutaImagenByDni(String dni) throws Exception{
		String rutaImagen = null;
		System.out.println("DNI: "+dni);
		String sql = "select ruta_imagen from personas where dni = ?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, dni);
		
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			rutaImagen = rs.getString("ruta_imagen");
			break;
		}
		return rutaImagen;
	}
	
}
