package com.certicom.certiscan.test;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.junit.Test;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.jdbc.dao.Conexion;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;



//clase creada para crear las carpetas desde archivos en  base de datos


public class TestCreateExpedienteFile {
	
//	@Test
//	public void create() throws Exception{
//		
//		String HOST = Constante.HOST_SCPF;
//	    Integer PUERTO = Constante.PUERTO_SCPF;
//	    String USUARIO = Constante.USUARIO_SCPF;
//	    String PASSWORD = Constante.PASSWORD_SCPF;
//	    
//	    String RUTA_PREVIA = Constante.RUTA_DIGITILZACION;
//	    
//	   // File folder = new File(RUTA_PREVIA);
//	    Session miSesion = null;
//	    Channel miCanal = null;
//	    ChannelSftp canalSFTP = null;
//		
//		Connection con = null;
//		Statement st = null;
//		con = Conexion.getConnection();
//		st = con.createStatement();
//		
//		//este query es para recuperar y armar los archivos archivos y carpertas desde base de datos
//		
//		String sql = " select concat(nro_expediente||'_'||to_char(fecha_documento,'ddmmyyyy')||'_'||to_char(hora_documento,'HH24MI')||'_'||oficina_origen) cadena ";
//			   sql +=" from t_expediente ";
//		ResultSet rs = st.executeQuery(sql);
//		Integer i = 0;
//		while (rs.next()){
//			System.out.println("carpeta a Crear : "+rs.getString(1));
//			String RUTA = RUTA_PREVIA+rs.getString(1);
//			 try
//		        {
//				 	
//		            JSch jsch = new JSch(); //instancia de jsch
//		            miSesion= jsch.getSession(USUARIO,HOST,PUERTO);//establezco la sesion
//		            miSesion.setPassword(PASSWORD); //le doy la clave
//		            Properties configuracion = new Properties(); //nueva instancia de arch de config.
//		            configuracion.put("StrictHostKeyChecking","no"); //seteo una propiedad
//		            miSesion.setConfig(configuracion);//se la paso a la sesion
//		            //System.out.println("iniciando conexion...");
//		            miSesion.connect();//intento la conexion
//		            miCanal =  miSesion.openChannel("sftp"); //abro un canal sftp
//		            miCanal.connect();//me conecto
//		            canalSFTP = (ChannelSftp)miCanal;//casteo
//		            canalSFTP.cd(RUTA_PREVIA);//exploroe el directorio remoto
//		            //this.rutaImagen=RUTA+archImg.getName();
//		            //archImg.setReadOnly();
//		            canalSFTP.mkdir(RUTA);
//		           // this.rutaCarpeta = RUTA;
//		            //canalSFTP.put(new FileInputStream(folder),this.nuevoExpediente.getNro_expediente());
//		            i++;
//		           
//		        }catch(Exception g){
//		            System.out.println("Transferencia Fallida");
//		            System.out.println("Carpeta "+rs.getString(1) + " no creada");
//		            g.printStackTrace();
//		        }finally {
//		        	
//		            // Cerramos el canal y session
//		            if (canalSFTP.isConnected())
//		                    canalSFTP.disconnect();
//		            if (miSesion.isConnected())
//		                    miSesion.disconnect();
//		        }
//			
//			
//			
//		}
//		System.out.println("Total de carpetas creadas : "+i);
//		
//
//		
//	}
//	
	

}
