package com.certicom.certiscan.commons;

import java.io.BufferedReader;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.lowagie.text.pdf.PdfReader;

import java.util.Properties;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.io.FileInputStream;

import org.apache.commons.io.IOUtils;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.domain.ExpedienteDocumento;
import com.certicom.certiscan.domain.Medios;

public class Utiles {
	
	public static  String enviarArchivoViaSFTP (File archImg, String nombreArchivo,String _rutaExp){
		String HOST = Constante.HOST_SCPF;
	    Integer PUERTO = Constante.PUERTO_SCPF;
	    String USUARIO = Constante.USUARIO_SCPF;
	    String PASSWORD = Constante.PASSWORD_SCPF;
	    
	    
	    //String RUTA = "/var/www/html/tipodocumental/";
	    //String RUTA = "/opt/tipodocumental/";
	    //String RUTA = Constante.RUTA_ARCHIVO;
	    String RUTA = _rutaExp;
	    
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
            canalSFTP.cd(RUTA);//exploroe el directorio remoto
            //this.rutaImagen=RUTA+archImg.getName();
            //archImg.setReadOnly();
            canalSFTP.put(new FileInputStream(archImg),nombreArchivo);
            
           
        }catch(Exception g){
            System.out.println("Transferencia Fallida");
            g.printStackTrace();
        }finally {
        	
            // Cerramos el canal y session
            if (canalSFTP.isConnected())
                    canalSFTP.disconnect();
            if (miSesion.isConnected())
                    miSesion.disconnect();
        }
        
        return RUTA+archImg.getName();
	}
	
	public static void copiarArchivo (File archImg,  String nombreArchivo, String _rutaExp, ChannelSftp canalSFTP){
	    String RUTA = _rutaExp;
	    try
        {
            System.out.println("entramos a la ruta : "+RUTA);
		canalSFTP.cd(RUTA);//exploroe el directorio remoto
		canalSFTP.put(new FileInputStream(archImg),nombreArchivo);
        }catch(Exception g){
            System.out.println("Transferencia Fallida");
            g.printStackTrace();
        }
	}
		
		
	public static  String enviarArchivoViaSFTP2 (File archImg, String nombreArchivo,String _rutaExp){
		System.out.println("Inicio enviar archivo");
		String HOST = Constante.HOST_SCPF;
	    Integer PUERTO = Constante.PUERTO_SCPF;
	    String USUARIO = Constante.USUARIO_SCPF;
	    String PASSWORD = Constante.PASSWORD_SCPF;
	    
	    
	    //String RUTA = "/var/www/html/tipodocumental/";
	    //String RUTA = "/opt/tipodocumental/";
	    //String RUTA = Constante.RUTA_ARCHIVO;
	    //String RUTA = nombreArchivo;
	    String RUTA = _rutaExp;
	    
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
            System.out.println("entramos a la ruta : "+RUTA);
            canalSFTP.cd(RUTA);//exploroe el directorio remoto
            //this.rutaImagen=RUTA+archImg.getName();
            //archImg.setReadOnly();
            canalSFTP.put(new FileInputStream(archImg),nombreArchivo);
            
           
        }catch(Exception g){
            System.out.println("Transferencia Fallida");
            g.printStackTrace();
        }finally {
        	
            // Cerramos el canal y session
            if (canalSFTP.isConnected())
                    canalSFTP.disconnect();
            if (miSesion.isConnected())
                    miSesion.disconnect();
        }
        System.out.println("Fin enviar archivo");
        
        return RUTA+archImg.getName();
	}
	
	public void ejecutarBashOLD (Medios m){
		String HOST = Constante.HOST_certiscan;
	    Integer PUERTO = Constante.PUERTO_certiscan;
	    String USUARIO = Constante.USUARIO_certiscan;
	    String PASSWORD = Constante.PASSWORD_certiscan;
	    
	    
	    //String RUTA = "/var/www/html/tipodocumental/";
	    //String RUTA = "/opt/tipodocumental/";
	    //String RUTA = Constante.RUTA_ARCHIVO;
	    //String RUTA = nombreArchivo;
	    //String RUTA = _rutaExp;
	    
	    Session miSesion = null;
	    Channel miCanal = null;
	    ChannelExec canalExec = null;
		
        try
        {
        	System.out.println("ID MEDIO: "+m.getId_medio());
            System.out.println("DESCRIPCION MEDIO: "+m.getDescripcion());
            String nombre = m.getDescripcion().replace(" ", "");
        	
            JSch jsch = new JSch(); //instancia de jsch
            miSesion= jsch.getSession(USUARIO,HOST,PUERTO);//establezco la sesion
            miSesion.setPassword(PASSWORD); //le doy la clave
            Properties configuracion = new Properties(); //nueva instancia de arch de config.
            configuracion.put("StrictHostKeyChecking","no"); //seteo una propiedad
            miSesion.setConfig(configuracion);//se la paso a la sesion
            System.out.println("iniciando conexion...");
            miSesion.connect();//intento la conexion
            miCanal =  miSesion.openChannel("exec"); //abro un canal sftp
            miCanal.connect();//me conecto
            //this.rutaImagen=RUTA+archImg.getName();
            //archImg.setReadOnly();
            canalExec = (ChannelExec)miCanal;
            
            System.out.println("cd /opt/prueba ; ./prueba.sh "+m.getId_medio()+" "+nombre+".zip");
            //canalExec.setCommand("cd /opt/prueba ; ./prueba.sh "+m.getId_medio()+" "+m.getDescripcion().replace(" ", "")+".zip");
            //canalExec.setCommand("cd /opt/prueba ; ./prueba.sh 127 Medio127.zip");
            canalExec.setCommand("cd /opt/prueba ; ./prueba3.sh ");
            canalExec.connect();
            //text.replace(" ","")
            
            System.out.println("RESULTADO: "+canalExec.getExitStatus());
            
            InputStream outputstream_from_the_channel = canalExec.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(outputstream_from_the_channel));
            String line = null;
            StringBuilder sb = new StringBuilder();
            boolean isloginStringPassed = false ;

            while ((line = br.readLine()) != null) {
                    sb.append(line.trim());
            }
            System.out.println("Result ="+sb.toString());
           
        }catch(Exception g){
            System.out.println("Transferencia Fallida");
            g.printStackTrace();
        }finally {
        	
            // Cerramos el canal y session
            if (canalExec.isConnected())
            	canalExec.disconnect();
            if (miSesion.isConnected())
                    miSesion.disconnect();
        }
	}
	
	public static void ejecutarBash (Medios m){
		/*
		String HOST1 = Constante.HOST_certiscan;
	    Integer PUERTO1 = Constante.PUERTO_certiscan;
	    String USUARIO1 = Constante.USUARIO_certiscan;
	    String PASSWORD1 = Constante.PASSWORD_certiscan;
*/
		String HOST1 = Constante.HOST_SCPF;
	    Integer PUERTO1 = Constante.PUERTO_SCPF;
	    String USUARIO1 = Constante.USUARIO_SCPF;
	    String PASSWORD1 = Constante.PASSWORD_SCPF;
	    
	    
	    //String RUTA1 = Constante.RUTA_ARCHIVO_TICKET;
		Session miSesion1 = null;
		Channel miCanal1 = null;
		Channel miCanal12 = null;
		//Channel miCanal11 = null;
		ChannelSftp canalSFTP1 = null;
		ChannelExec canalExec = null;
		
		InputStream is1 = null;
		ByteArrayOutputStream osx=new ByteArrayOutputStream(); 
				
		 try
	        {
			 
	            JSch jsch1 = new JSch(); //instancia de jsch
	            miSesion1= jsch1.getSession(USUARIO1,HOST1,PUERTO1);//establezco la sesion
	            miSesion1.setPassword(PASSWORD1); //le doy la clave
	            Properties configuracion1 = new Properties(); //nueva instancia de arch de config.
	            configuracion1.put("StrictHostKeyChecking","no"); //seteo una propiedad
	            miSesion1.setConfig(configuracion1);//se la paso a la sesion
	            System.out.println("iniciando conexion...");
	            miSesion1.connect();//intento la conexion
	            miCanal1 =  miSesion1.openChannel("sftp"); //abro un canal sftp
	            miCanal12 =  miSesion1.openChannel("exec"); //abro un canal sftp
	            //miCanal11 =  miSesion1.openChannel("shell"); //abro un canal sftp
	            miCanal1.connect();//me conecto
	            canalSFTP1 = (ChannelSftp)miCanal1;//casteo
	            canalExec = (ChannelExec)miCanal12;
	            
	            System.out.println("cd /home/rbullon/Sergio/proyectos/osinergmin/basePruebas ; ./prueba.sh "+m.getId_medio()+" "+m.getDescripcion().replace(" ", "")+".gz");
	            //canalExec.setCommand("cd /opt/prueba ; mkdir /var/www/html/osinergmin/israel");
	            canalExec.setCommand("cd /home/rbullon/Sergio/proyectos/osinergmin/basePruebas ; ./prueba.sh "+m.getId_medio()+" "+m.getDescripcion().replace(" ", "")+".gz");
	            canalExec.connect();
	            
	            System.out.println("RESULTADO: "+canalExec.getExitStatus());
	            
	            InputStream outputstream_from_the_channel = canalExec.getInputStream();
	            BufferedReader br = new BufferedReader(new InputStreamReader(outputstream_from_the_channel));
	            String line = null;
	            StringBuilder sb = new StringBuilder();
	            boolean isloginStringPassed = false ;

	            while ((line = br.readLine()) != null) {
	                    sb.append(line.trim());
	            }
	            System.out.println("Result ="+sb.toString());
	            /*is1=canalSFTP1.get("/opt/prueba/prueba.pdf");
	            try {
	                byte[] buffer = new byte[1024];
	                int count;
	                while (-1 != (count = is1.read(buffer))) {
	                	osx.write(buffer, 0, count);
	                }
	            } finally {
	            	data = osx.toByteArray();
	            	
	            	is1.read(data);
	                is1.close();
	            }*/
	            
	        }catch(Exception g){
	            System.out.println("Transferencia Fallida");
	            g.printStackTrace();
	        }finally {
	            System.out.println("FIN");
	        	
	            // Cerramos el canal y session
	            if (canalSFTP1.isConnected())
	                    canalSFTP1.disconnect();
	            if (miSesion1.isConnected())
	                    miSesion1.disconnect();
	        }
	}
	
	public static void cambiarNombreViaSFTP (String nuevoNombre,String antiguoNombre, String ruta){
		String HOST = Constante.HOST_SCPF;
	    Integer PUERTO = Constante.PUERTO_SCPF;
	    String USUARIO = Constante.USUARIO_SCPF;
	    String PASSWORD = Constante.PASSWORD_SCPF;
	    
	    
	    //String RUTA = "/var/www/html/tipodocumental/";
	    //String RUTA = "/opt/tipodocumental/";
	    //String RUTA = Constante.RUTA_ARCHIVO;
	    String RUTA = ruta;
	    
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
            canalSFTP.cd(RUTA);//exploroe el directorio remoto
            //this.rutaImagen=RUTA+archImg.getName();
            //archImg.setReadOnly();
            //canalSFTP.put(new FileInputStream(archImg),nombreArchivo);
            System.out.println("Nuevo Nombre"+nuevoNombre);
            System.out.println("Antiguo Nombre"+antiguoNombre);
            canalSFTP.rename(antiguoNombre , nuevoNombre);
            
           
        }catch(Exception g){
            System.out.println("Transferencia Fallida");
            g.printStackTrace();
        }finally {
        	
            // Cerramos el canal y session
            if (canalSFTP.isConnected())
                    canalSFTP.disconnect();
            if (miSesion.isConnected())
                    miSesion.disconnect();
        }
        
        //return RUTA+archImg.getName();
	}
		
	public static  void enviarArchivoViaSFTPMasivo(List<ExpedienteDocumento> docs, String rutaExpediente){
		String HOST = Constante.HOST_SCPF;
	    Integer PUERTO = Constante.PUERTO_SCPF;
	    String USUARIO = Constante.USUARIO_SCPF;
	    String PASSWORD = Constante.PASSWORD_SCPF;
	    
	    
	    //String RUTA = "/var/www/html/tipodocumental/";
	    //String RUTA = "/opt/tipodocumental/";
	    //String RUTA = Constante.RUTA_ARCHIVO;
	    String RUTA = rutaExpediente;
	    
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
            canalSFTP.cd(RUTA);//exploroe el directorio remoto
            //this.rutaImagen=RUTA+archImg.getName();
            //archImg.setReadOnly();
            
            for (ExpedienteDocumento ed : docs) {
            	canalSFTP.put(new FileInputStream(ed.getFile()),ed.getNombre_archivo());
			}
            
           
        }catch(Exception g){
            System.out.println("Transferencia Fallida");
            g.printStackTrace();
        }finally {
        	
            // Cerramos el canal y session
            if (canalSFTP.isConnected())
                    canalSFTP.disconnect();
            if (miSesion.isConnected())
                    miSesion.disconnect();
        }
        
    //    return RUTA+archImg.getName();
	}
	
	public static  void enviarArchivoViaSFTP4(File f, String rutaExpediente, String nom_arch){
		String HOST = Constante.HOST_SCPF;
	    Integer PUERTO = Constante.PUERTO_SCPF;
	    String USUARIO = Constante.USUARIO_SCPF;
	    String PASSWORD = Constante.PASSWORD_SCPF;
	    
	    
	    //String RUTA = "/var/www/html/tipodocumental/";
	    //String RUTA = "/opt/tipodocumental/";
	    //String RUTA = Constante.RUTA_ARCHIVO;
	    String RUTA = rutaExpediente;
	    
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
            canalSFTP.cd(RUTA);//exploroe el directorio remoto
            //this.rutaImagen=RUTA+archImg.getName();
            //archImg.setReadOnly();
            
            	canalSFTP.put(new FileInputStream(f),nom_arch);
			
            
           
        }catch(Exception g){
            System.out.println("Transferencia Fallida");
            g.printStackTrace();
        }finally {
        	
            // Cerramos el canal y session
            if (canalSFTP.isConnected())
                    canalSFTP.disconnect();
            if (miSesion.isConnected())
                    miSesion.disconnect();
        }
        
    //    return RUTA+archImg.getName();
	}

	public static void eliminarArchivoViaSFTP(String ruta_imagen, String rutaExpediente){
		
		System.out.println("enviando el archivo de imagen");
				
		String HOST = Constante.HOST_SCPF;
	    Integer PUERTO = Constante.PUERTO_SCPF;
	    String USUARIO = Constante.USUARIO_SCPF;
	    String PASSWORD = Constante.PASSWORD_SCPF;
	    
	    //String RUTA = Constante.RUTA_ARCHIVO;
	    String RUTA = rutaExpediente;
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
	        canalSFTP.cd(RUTA);//exploroe el directorio remoto
	        canalSFTP.rm(ruta_imagen);
	       
	        
	    }catch(Exception g){
	        System.out.println("Transferencia Fallida");
	        g.printStackTrace();
	    }finally {
	    	
	        // Cerramos el canal y session
	        if (canalSFTP.isConnected())
	                canalSFTP.disconnect();
	        if (miSesion.isConnected())
	                miSesion.disconnect();
	    }
	}
		
	public static byte[] descargarArchivoViaSFTP(String _ruta_imagen,String _file_name){
		byte[] _data = null;
		
		String HOST = Constante.HOST_SCPF;
	    Integer PUERTO = Constante.PUERTO_SCPF;
	    String USUARIO = Constante.USUARIO_SCPF;
	    String PASSWORD = Constante.PASSWORD_SCPF;
	    
	    String RUTA = Constante.RUTA_ARCHIVO;
		Session miSesion = null;
		Channel miCanal = null;
		ChannelSftp canalSFTP = null;
		 
		try{
		InputStream is = null;
		byte[] data = null;
		ByteArrayOutputStream osx=new ByteArrayOutputStream(); 
		
		System.out.println("RUTA: "+_ruta_imagen);
		System.out.println("FILE: "+_file_name);
		
		String completo = _ruta_imagen + _file_name;
		
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
	            
	            is=canalSFTP.get(completo);
	            try {
	                byte[] buffer = new byte[1024];
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
	            g.printStackTrace();
	        }finally {
	        	
	            // Cerramos el canal y session
	            if (canalSFTP.isConnected())
	                    canalSFTP.disconnect();
	            if (miSesion.isConnected())
	                    miSesion.disconnect();
	        }
		   

		   is.read(data);
		   is.close();
		   
		   _data = data;
		   

		}catch (IOException e) {
			e.printStackTrace();
		}
		return _data;
		
	}
	
	public static byte[] descargarArchivoViaSFTP3(String _ruta_imagen){
		byte[] _data = null;
		
		String HOST = Constante.HOST_SCPF;
	    Integer PUERTO = Constante.PUERTO_SCPF;
	    String USUARIO = Constante.USUARIO_SCPF;
	    String PASSWORD = Constante.PASSWORD_SCPF;
	    
	    String RUTA = Constante.RUTA_MEDIOS;
		Session miSesion = null;
		Channel miCanal = null;
		ChannelSftp canalSFTP = null;
		 
		try{
		InputStream is = null;
		byte[] data = null;
		ByteArrayOutputStream osx=new ByteArrayOutputStream(); 
		
		System.out.println("RUTA: "+_ruta_imagen);
		
		String completo = _ruta_imagen;
		
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
	            
	            is=canalSFTP.get(completo);
	            try {
	                byte[] buffer = new byte[1024];
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
	            g.printStackTrace();
	        }finally {
	        	
	            // Cerramos el canal y session
	            if (canalSFTP.isConnected())
	                    canalSFTP.disconnect();
	            if (miSesion.isConnected())
	                    miSesion.disconnect();
	        }
		   

		   is.read(data);
		   is.close();
		   
		   _data = data;
		   

		}catch (IOException e) {
			e.printStackTrace();
		}
		 
		
		System.out.println("completado retornando valor");
		return _data;
		
	}
	
	public static byte[] descargarArchivoViaSFTP2(String _ruta_imagen){
		System.out.println("Inicio Descarga");
		byte[] _data = null;
		
		String HOST = Constante.HOST_SCPF;
	    Integer PUERTO = Constante.PUERTO_SCPF;
	    String USUARIO = Constante.USUARIO_SCPF;
	    String PASSWORD = Constante.PASSWORD_SCPF;
	    
	    String RUTA = Constante.RUTA_ARCHIVO;
		Session miSesion = null;
		Channel miCanal = null;
		ChannelSftp canalSFTP = null;
		 
		try{
		InputStream is = null;
		byte[] data = null;
		ByteArrayOutputStream osx=new ByteArrayOutputStream(); 
		
		System.out.println("RUTA: "+_ruta_imagen);
		//System.out.println("FILE: "+_file_name);
		
		String completo = _ruta_imagen;
		
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
	            
	            is=canalSFTP.get(completo);
	            
	           // File tempFile = new File()
	            try {
	            	System.out.println("Entramos al Try-catch");
	                byte[] buffer = new byte[1024*1024];
	                int count;
	                while (-1 != (count = is.read(buffer))) {
	                	osx.write(buffer, 0, count);
	                }
	                
	            }catch(Exception ex){
	                	ex.printStackTrace();
	                	System.out.println(ex.getMessage());       
	            } finally {
	            	data = osx.toByteArray();
	            	
	            	is.read(data);
	                is.close();
	                System.out.println("Salimos del Finally");
	            }
	            
	            
	            
	            
	            
	        }catch(Exception g){
	            System.out.println("Transferencia Fallida");
	            g.printStackTrace();
	        }finally {
	        	System.out.println("cerramos conexiones");
	            // Cerramos el canal y session
	            if (canalSFTP.isConnected())
	                    canalSFTP.disconnect();
	            if (miSesion.isConnected())
	                    miSesion.disconnect();
	        }
		   

		   is.read(data);
		   is.close();
		   
		   _data = data;
		   

		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Terminado Descargar");
		return _data;
		
	}
	
	public static BufferedOutputStream descargarFile(String ruta_archivo){
		
		String HOST = Constante.HOST_SCPF;
	    Integer PUERTO = Constante.PUERTO_SCPF;
	    String USUARIO = Constante.USUARIO_SCPF;
	    String PASSWORD = Constante.PASSWORD_SCPF;
	    
	    Session miSesion = null;
		Channel miCanal = null;
		ChannelSftp canalSFTP = null;
		File newFile = null;
		BufferedOutputStream bos = null;
		
		try {
			JSch jsch = new JSch();
			miSesion = jsch.getSession(USUARIO, HOST, PUERTO);
			miSesion.setPassword(PASSWORD);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			miSesion.setConfig(config);
			miSesion.connect();
			miCanal = miSesion.openChannel("sftp");
			miCanal.connect();
			canalSFTP = (ChannelSftp) miCanal;
			canalSFTP.cd(ruta_archivo);
			byte[] buffer = new byte[32*1024];
			BufferedInputStream bis = new BufferedInputStream(canalSFTP.get(ruta_archivo));
			newFile = new File(ruta_archivo);
			OutputStream os = new FileOutputStream(newFile);
			bos = new BufferedOutputStream(os);
			int readCount;
			while ((readCount = bis.read(buffer)) > 0) {
				System.out.println("Writing: ");
				bos.write(buffer, 0, readCount);
			}
			bis.close();
			bos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		
		return bos;
		
		
	}
	
	public static void moveFileToDeleted(ExpedienteDocumento _ed){
	byte[] _data = null;
		
		String HOST = Constante.HOST_SCPF;
	    Integer PUERTO = Constante.PUERTO_SCPF;
	    String USUARIO = Constante.USUARIO_SCPF;
	    String PASSWORD = Constante.PASSWORD_SCPF;
	    
	    String RUTA = Constante.RUTA_ARCHIVO;
		Session miSesion = null;
		
		Channel miCanal = null;
		ChannelSftp canalSFTP = null;
		
		Channel TochDeleted = null;
		ChannelSftp TochDeletedSFTP = null;
		 
		try{
		InputStream is = null;
		byte[] data = null;
		ByteArrayOutputStream osx=new ByteArrayOutputStream(); 
		
			 
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
	            
	            TochDeleted =  miSesion.openChannel("sftp"); //abro un canal sftp
	            TochDeleted.connect();//me conecto
	            
	            
	            
	            canalSFTP = (ChannelSftp)miCanal;//casteo
	            TochDeletedSFTP = (ChannelSftp)TochDeleted;//casteo
	            
	            is= canalSFTP.get(_ed.getRuta());
	            
	            TochDeletedSFTP.put(is, Constante.RUTA_ARCHIVO_ELIMINADO+_ed.getNombre_archivo());
	            
	 		   canalSFTP.cd(RUTA);//exploroe el directorio remoto
		       canalSFTP.rm(_ed.getRuta());
	 		   
	            
	        }catch(Exception g){
	            System.out.println("Transferencia Fallida");
	            g.printStackTrace();
	        }finally {
	        	
	            // Cerramos el canal y session
	            if (canalSFTP.isConnected())
	                    canalSFTP.disconnect();
	            if (TochDeletedSFTP.isConnected())
	            		TochDeletedSFTP.disconnect();
	            if (miSesion.isConnected())
	                    miSesion.disconnect();
	        }
		   
	
	}
		
	public static void moveFileToReplaced(File _newFile, ExpedienteDocumento _ed, String _ruta){
	byte[] _data = null;
		
		String HOST = Constante.HOST_SCPF;
	    Integer PUERTO = Constante.PUERTO_SCPF;
	    String USUARIO = Constante.USUARIO_SCPF;
	    String PASSWORD = Constante.PASSWORD_SCPF;
	    
	    String RUTA = _ruta;
		Session miSesion = null;
		
		Channel miCanal = null;
		ChannelSftp canalSFTP = null;
		
		Channel TochDeleted = null;
		ChannelSftp TochDeletedSFTP = null;
		 
		try{
		InputStream is = null;
		byte[] data = null;
		ByteArrayOutputStream osx=new ByteArrayOutputStream(); 
		
			 
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
	            
	            TochDeleted =  miSesion.openChannel("sftp"); //abro un canal sftp
	            TochDeleted.connect();//me conecto
	            
	            
	            
	            canalSFTP = (ChannelSftp)miCanal;//casteo
	            TochDeletedSFTP = (ChannelSftp)TochDeleted;//casteo
	            
	            is= canalSFTP.get(_ed.getRuta());
	            
	            TochDeletedSFTP.put(is, Constante.RUTA_ARCHIVO_ELIMINADO+_ed.getNombre_archivo());
	         
	            canalSFTP.cd(RUTA);
	            canalSFTP.put(new FileInputStream(_newFile),_ed.getNombre_archivo());
	            
	 		  
	 		   
	            
	        }catch(Exception g){
	            System.out.println("Transferencia Fallida");
	            g.printStackTrace();
	        }finally {
	        	
	            // Cerramos el canal y session
	            if (canalSFTP.isConnected())
	                    canalSFTP.disconnect();
	            if (TochDeletedSFTP.isConnected())
	            		TochDeletedSFTP.disconnect();
	            if (miSesion.isConnected())
	                    miSesion.disconnect();
	        }
		   
	
	}
			
	public static Date stringToDate(String dia, String mes, String anio)
	{
        Date fecha = null;
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        String strFecha = anio+"-"+mes+"-"+dia;
        try {

            fecha = formatoDelTexto.parse(strFecha);

        } catch (ParseException ex) {

            ex.printStackTrace();

        }
        
        return fecha;
		
	}
		
	public static List<String> findDuplicates(List<String> listContainingDuplicates) {
		 
		 Set<String> set = new HashSet<String>();
		 List<String> repeated = new ArrayList<String>();
 
		for (String item : listContainingDuplicates) {
				if (!set.add(item)) {
					repeated.add(item);
				}
		}			
		return repeated;
	}
		
	public static byte[] getFileViaSFTP(String _ruta_imagen,String _file_name){
		byte[] _data = null;
		
		String HOST = Constante.HOST_RENIEC;
	    Integer PUERTO = Constante.PUERTO_RENIEC;
	    String USUARIO = Constante.USUARIO_RENIEC;
	    String PASSWORD = Constante.PASSWORD_RENIEC;
	    
	    String RUTA = Constante.RUTA_ARCHIVO_RENIEC;
		
	    Session miSesion = null;
		Channel miCanal = null;
		ChannelSftp canalSFTP = null;
		 
		try{
		InputStream is = null;
		byte[] data = null;
		ByteArrayOutputStream osx=new ByteArrayOutputStream(); 
		
		 try
	        {
			 
	            JSch jsch = new JSch(); 
	            miSesion= jsch.getSession(USUARIO,HOST,PUERTO);
	            miSesion.setPassword(PASSWORD); 
	            //le doy la clave
	            Properties configuracion = new Properties(); //nueva instancia de arch de config.
	            configuracion.put("StrictHostKeyChecking","no"); //seteo una propiedad
	            
	            miSesion.setConfig(configuracion);//se la paso a la sesion
	            System.out.println("iniciando conexion...");
	            miSesion.connect();//intento la conexion
	            miCanal =  miSesion.openChannel("sftp"); //abro un canal sftp
	            miCanal.connect();//me conecto
	            canalSFTP = (ChannelSftp)miCanal;//casteo

	            is=canalSFTP.get(_ruta_imagen);
	            try {
	                byte[] buffer = new byte[1024];
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
	            g.printStackTrace();
	            return null;
	        }finally {
	            if (canalSFTP.isConnected())
	                    canalSFTP.disconnect();
	            if (miSesion.isConnected())
	                    miSesion.disconnect();
	        }

		   is.read(data);
		   is.close();
		   
		   _data = data;

		}catch (IOException e) {
			e.printStackTrace();
		}
		return _data;
		
	}
		
	public static List<ExpedienteDocumento> obtenerArchivosFisico(List<ExpedienteDocumento> listData){
		byte[] _data = null;
		
		String HOST = Constante.HOST_SCPF;
	    Integer PUERTO = Constante.PUERTO_SCPF;
	    String USUARIO = Constante.USUARIO_SCPF;
	    String PASSWORD = Constante.PASSWORD_SCPF;
	    
	    String RUTA = Constante.RUTA_ARCHIVO;
		Session miSesion = null;
		Channel miCanal = null;
		ChannelSftp canalSFTP = null;
		 
		InputStream is = null;
		byte[] data = null;
		ByteArrayOutputStream osx=new ByteArrayOutputStream(); 
		
		 try {
			 
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
	            
	            
	            for (ExpedienteDocumento ed : listData) {
					
				
	            
	            is=canalSFTP.get(ed.getRuta());
	            ed.setInputStreamFile(is);
	            
	            
//	            try {
//	                byte[] buffer = new byte[1024];
//	                int count;
//	                while (-1 != (count = is.read(buffer))) {
//	                	osx.write(buffer, 0, count);
//	                }
//	            } finally {
//	            	data = osx.toByteArray();
//	            	is.read(data);
//	                is.close();
//	            }
	            
//	            File tempFile = File.createTempFile("prefix", ".pdf");
//	            tempFile.deleteOnExit();
//	            FileOutputStream out = new FileOutputStream(tempFile);
//	            IOUtils.copy(is, out);
	            
	            File _archivo = File.createTempFile("prefix", ".pdf");
				System.out.println("before " + _archivo.length());
				  InputStream in = is;
				  OutputStream out = new FileOutputStream(_archivo);
				  byte[] buf = new byte[1024];
		            int len;
		             
		            while ((len = in.read(buf)) > 0) {
		              out.write(buf, 0, len);
		            }
		            
		            in.close();
		            out.close();
	            
		            System.out.println("after "+_archivo.length());
	            
	            ed.setFile(_archivo);
	            
	            PdfReader pf = new PdfReader(new FileInputStream(_archivo));
	            ed.setPdfReader(pf);
	            
	            
	            
	            } 
	            
	            
	        }catch(Exception g){
	            System.out.println("Transferencia Fallida");
	            g.printStackTrace();
	        }finally {
	            if (canalSFTP.isConnected())
	                    canalSFTP.disconnect();
	            if (miSesion.isConnected())
	                    miSesion.disconnect();
	        }

		
		
		return listData;
		
	}
	
	
	public static void writeZipFile(File directoryToZip, List<File> fileList) {

		try {
			FileOutputStream fos = new FileOutputStream(directoryToZip.getName() + ".zip");
			ZipOutputStream zos = new ZipOutputStream(fos);

			for (File file : fileList) {
				if (!file.isDirectory()) { // we only zip files, not directories
					addToZip(directoryToZip, file, zos);
				}
			}

			zos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addToZip(File directoryToZip, File file, ZipOutputStream zos) throws FileNotFoundException,
	IOException {

		FileInputStream fis = new FileInputStream(file);
		
		// we want the zipEntry's path to be a relative path that is relative
		// to the directory being zipped, so chop off the rest of the path
		String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1,
				file.getCanonicalPath().length());
		System.out.println("Writing '" + zipFilePath + "' to zip file");
		ZipEntry zipEntry = new ZipEntry(zipFilePath);
		zos.putNextEntry(zipEntry);
		
		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zos.write(bytes, 0, length);
		}
		
		zos.closeEntry();
		fis.close();
	}
	
	public static void addToZipFile(byte[] zipFile,  String fileName, ZipOutputStream zos) throws FileNotFoundException, IOException {

		System.out.println("Writing '" + fileName + "' to zip file");
		
		InputStream in = new ByteArrayInputStream(zipFile);
		
		ZipEntry zipEntry = new ZipEntry(fileName);
		zipEntry.setSize(zipFile.length);
		
		zos.putNextEntry(zipEntry);
		
		 byte[] bytes = new byte[1024];
	     int length;
	      while ((length = in.read(bytes)) >= 0) {
	          zos.write(bytes, 0, length);
	      }
		
		zos.closeEntry();
		in.close();
		

	}
	
	
	public static void addToZipFile(File file, ZipOutputStream zos) throws FileNotFoundException, IOException {
		
		 System.out.println("Writing '" + file.getName() + "' to zip file");	
		
		  FileInputStream fis = new FileInputStream(file);
	      ZipEntry zipEntry = new ZipEntry(file.getName());
	      zos.putNextEntry(zipEntry);

	      byte[] bytes = new byte[1024];
	      int length;
	      while ((length = fis.read(bytes)) >= 0) {
	          zos.write(bytes, 0, length);
	      }

	      zos.closeEntry();
	      fis.close();
			
	}
	
	
	
	

}
