package com.certicom.certiscan.servlet;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.jdbc.dao.Conexion;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

/**
 * Servlet implementation class DisplayImage
 */
//@WebServlet("/DisplayImage")
public class DisplayImage extends HttpServlet {
	
	private static final long serialVersionUID = 4593558495041379082L;
    
	private String gcodigo;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PreparedStatement pstmt = null;
		ResultSet rs;
		Connection con;
		
		byte[] data = null;
		
		
		InputStream sImage;
		 //prueba
		try {
			  String PATH ="/src/com/certicom/certiscan/propiedades/database.properties"; 
	         Properties properties = new Properties();  
	         properties.load(Conexion.class.getResourceAsStream(PATH)); 
	         String file = null;
			   //obteniendo el archivo de imagen.			
			
//			   String urldb ="jdbc:postgresql://10.162.170.103:5432/SCPF";
//			   String userbd="postgres";
//			   String passbd = "postgres11";
			   
			   String urldb =properties.getProperty("jdbc.url");
			   String userbd=properties.getProperty("jdbc.username");
			   String passbd = properties.getProperty("jdbc.password");
			   con = DriverManager.getConnection(urldb,userbd,passbd);
			   
			   System.out.println("urldb ===> "+urldb);
			   System.out.println("userbd ===> "+userbd);
			   System.out.println("passbd ===> "+passbd);
			   
			  
			   String codigo = request.getParameter("codigo").toString();
			   
			   System.out.println("codigo ===> "+codigo);
				   
				   String idusuario = codigo;//request.getParameter("idPiloto").toString();	
				   System.out.println("Nro: "+codigo);
					   //para meter a bd parametros
					   //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

					   String strSql = "select ruta from t_expediente_documento where id_expediente_documento = ?";
					   pstmt = con.prepareStatement(strSql);
					   /*if(idusuario==null){
						   System.out.println("El código es nulo");
						   this.gcodigo=idusuario;
					   }else{
						   System.out.println("El código no es nulo");
						   if(idusuario.equals("")){
							   System.out.println("El código es: "+idusuario);
							   
							   idusuario="0";
							   this.gcodigo=idusuario;
							   //System.out.println("El código es: "+idusuario);
						   }					  
					   }*/
			           pstmt.setInt(1,Integer.parseInt(idusuario));
					   
			           rs = pstmt.executeQuery();
			          
			           if (rs.next()) {
			        	   file = rs.getString("ruta");
			            }else{
			            	System.out.println("No hay registros");
			            }
			           
			           rs.close();
			           pstmt.close();
			           con.close();
			         //file = file.substring(9);
					   InputStream is = null;
					   data = null;
					   ByteArrayOutputStream osx=new ByteArrayOutputStream();
					   /*byte[] data = null;
				       try {
				    	   //String cadenaURL = file;
				    	   String cadenaURL = rutaUrl+file;
				    	   System.out.println("Cadena del archivo: "+cadenaURL);
				    	   URL url = new URL(cadenaURL);
				           is = url.openStream();
				           //devolviendo como bye[]
				           osx = new ByteArrayOutputStream();			
				           byte[] buf = new byte[4096];
				           int n;			
				           while ((n = is.read(buf)) >= 0)
				           { 
				        	   osx.write(buf, 0, n);
				           }
						
				           osx.close();
				           is.close();			
				           data = osx.toByteArray();
				                
				       } catch (MalformedURLException e) {
				    	   e.printStackTrace();
				       } catch (IOException e) {
				    	   e.printStackTrace();
				       }*/
					   
					   /*String HOST = Constante.HOST_RENIEC;
					    Integer PUERTO = Constante.PUERTO_RENIEC;
					    String USUARIO = Constante.USUARIO_RENIEC;
					    String PASSWORD = Constante.PASSWORD_RENIEC;*/
					    
					   String HOST = Constante.HOST_SCPF;
					    Integer PUERTO = Constante.PUERTO_SCPF;
					    String USUARIO = Constante.USUARIO_SCPF;
					    String PASSWORD = Constante.PASSWORD_SCPF;
					   
					    String RUTA = file;
					    Session miSesion = null;
					    Channel miCanal = null;
					    ChannelSftp canalSFTP = null;
					    
					    
				        try
				        {
				            JSch jsch = new JSch(); //instancia de jsch
				            miSesion= jsch.getSession(USUARIO,HOST,PUERTO);//establezco la sesion
				            miSesion.setPassword(PASSWORD); //le doy la clave
				            Properties configuracion = new Properties(); //nueva instancia de arch de config.
				            configuracion.put("StrictHostKeyChecking","no"); //seteo una propiedad
				            miSesion.setConfig(configuracion);//se la paso a la sesion
				            System.out.println("iniciando conexion...");
				            miSesion.connect();//intento la conexion
				            miCanal =  miSesion.openChannel("sftp"); //abro un canal sftp
				            miCanal.connect();//me conecto
				            canalSFTP = (ChannelSftp)miCanal;//casteo
				            //canalSFTP.cd(RUTA);//exploroe el directorio remoto
				            //this.rutaImagen=RUTA+archImg.getName();
				            //canalSFTP.put(new FileInputStream(archImg),archImg.getName());
				            
				            System.out.println("RUTA: "+RUTA);
				            //System.out.println("RUTA: "+miCanal.);
				            
				            is=canalSFTP.get(RUTA);
				            
				            try {
				                byte[] buffer = new byte[256];
				                int count;
				                while (-1 != (count = is.read(buffer))) {
				                	osx.write(buffer, 0, count);
				                }
				            } finally {
				            	data = osx.toByteArray();
				            	
				            	is.read(data);
				                is.close();
				            }
				            
				        }catch(Exception g){
				            System.out.println("Transferencia Fallida");
				            /*if(this.gcodigo==null){
				            	System.out.println("El código es nulo");
				            }else{
				            	System.out.println("El código no es nulo");
				            	if(this.gcodigo.equals("")){
				            		System.out.println("El código es vacio");
				            	}else{
				            		System.out.println("El código tiene valor");
				            	}
				            }*/
				            g.printStackTrace();
				        }finally {
				        	System.out.println("Termino de leer");
				            // Cerramos el canal y session
				            if (canalSFTP.isConnected())
				                    canalSFTP.disconnect();
				            if (miSesion.isConnected())
				                    miSesion.disconnect();
				        }
					   

					   is.read(data);
					   is.close();
					   // escribiendo el contenido d ela imagen  a la respuesta.
					   response.getOutputStream().write(data);			   			
			  
			} catch (IOException e) {
				e.printStackTrace();
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        
	}

	public String getGcodigo() {
		return gcodigo;
	}

	public void setGcodigo(String gcodigo) {
		this.gcodigo = gcodigo;
	}
	
	

}

