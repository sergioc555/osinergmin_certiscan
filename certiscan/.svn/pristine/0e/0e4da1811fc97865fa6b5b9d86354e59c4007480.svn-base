package com.certicom.certiscan.jdbc.dao;

import java.sql.Connection;

public class ReniecLogic {

	
	public static String getRutaImagenByDni(String dni) throws Exception{
		Connection conn =  null;
		try{
		conn = Conexion.getConnectionRENIEC();
		ReniecDAO dao = new ReniecDAO(conn);
		return dao.getRutaImagenByDni(dni);
		}catch(Exception ex){
			ex.getMessage();
			throw ex;
		}finally{
			if(conn!=null)
				conn.close();
		}
		
	}
}
