package com.certicom.certiscan.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;




import javax.annotation.PreDestroy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import src.com.certicom.certiscan.utils.Utils;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.ServiceFinder;
import com.certicom.certiscan.commons.Utiles;
import com.certicom.certiscan.domain.DocumentoPagina;
import com.certicom.certiscan.domain.ExpedienteDocumento;
import com.certicom.certiscan.jdbc.dao.Conexion;
import com.certicom.certiscan.services.ExpedienteDocumentoService;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.lowagie.text.pdf.PdfReader;





public class TestUploadFile {
//	
//	final static String dir_files = "D://Data/1erEntregable/firmado_papel/PAP_01";
//	final static String RUTA_PREVIA = Constante.RUTA_DIGITILZACION;
//	final static Integer ID_USURIO = 522;
//	
//	private List<ExpedienteDocumento> listaDocumentos = new ArrayList<>();
//	
//	@Before
//	public void init() {
//		listaDocumentos = new ArrayList<ExpedienteDocumento>();
//	}
//
//
//	//subir los archivos al servidor en las carpetas creadas
//	public void upload() throws Exception{
//		
//		Connection con = null;
//		PreparedStatement pst = null;
//		con = Conexion.getConnection();
//		
//		//Utiles.enviarArchivoViaSFTP2(archImg, nombreArchivo, _rutaExp);
//		
//		 TestUploadFile t = new TestUploadFile();
//		 File [] files =  t.listFilesAndFolders(dir_files);
//		 
//		 System.out.println(files.length + " Files encontrados");
//		 
//		 Integer i = 0;
//		 
//		 for (File file : files){
//			 
//			 String query = " select distinct dir from temp_carga_datos where nombre_pdf = ? ";
//			 pst = con.prepareStatement(query);
//			 pst.setString(1, file.getName());
//			 
//			 ResultSet rs = pst.executeQuery();
//			 boolean found = false;
//			 String dir_temp = "";
//			 while (rs.next()){
//				 dir_temp = rs.getString(1);
//				 Utiles.enviarArchivoViaSFTP2(file, file.getName(),RUTA_PREVIA+rs.getString(1));
//				 found = true;
//			 }
//			 
//			 if (found){
//				 i++;
//				 System.out.println("Se guardo el file :"+ file.getName() + " en el dir " + dir_temp);
//			 }else{
//				 System.out.println("Error al guardar el file : " + file.getName());
//				 break;
//			 }
//			
//				
//			 
//		 }
//		 System.out.println(i + " Files guardados");
//		 
//		 
//
//		
//	}
//	
//	
//	//este metodo es para poner el orden a los archivos para luego cargarlas en la tabla t_expediente_documento
//	public void updateOrdenFile() throws Exception {
//		
//		 Connection con = null;
//		 Statement st = null;
//		 PreparedStatement pst = null;
//		 con = Conexion.getConnection();
//		 st = con.createStatement();
//		 int contador;
//		 String sql = " select  distinct expediente_id from temp_carga_datos order by expediente_id ";
//		 ResultSet rs = st.executeQuery(sql);
//		 while (rs.next()){
//			 String sql2 = " select expediente_id, nombre_pdf from temp_carga_datos where expediente_id = ? order by nombre_pdf ";
//			 pst = con.prepareStatement(sql2);
//			 pst.setInt(1, rs.getInt(1));
//			 ResultSet r = pst.executeQuery();
//			 PreparedStatement p = null;
//			 contador = 0;
//			 while (r.next()){
//				 contador++;
//				 String sql3 = " update temp_carga_datos set orden_expediente = ? where expediente_id = ? and nombre_pdf = ? ";
//				 p = con.prepareStatement(sql3);
//				 p.setInt(1, contador);
//				 p.setInt(2, r.getInt(1));
//				 p.setString(3, r.getString(2));
//				 p.executeUpdate();
//				 
//			 }
//			 
//			 System.out.println("expediente_id : " + rs.getInt(1) + " se actualizo " + contador + " files" );
//			 
//		 }
//		 
//		
//	}
//	
//	
//	
//	//este metodo es para cargar las tablas y actualizar la tabla expediente documento
//
//	@Test
//	public void updateExpedienteDocumento() throws Exception{
//		
//
//		 Connection con = null;
//		 PreparedStatement pst = null;
//		 CallableStatement sp = null;
//		 con = Conexion.getConnection();
//		 TestUploadFile t = new TestUploadFile();
//		 File [] files =  t.listFilesAndFolders(dir_files);
//		 
//		 ExpedienteDocumento expDocumento;
//		 
//		 //Integer nro_documento = 123516;
//		 
//		 for (File file : files){
//			 
//			 expDocumento = new ExpedienteDocumento();
//			 expDocumento.setNombre_archivo(file.getName());
//			 expDocumento.setTexto(file.getName());
//			 expDocumento.setFecha_subida(new Date());
//			 expDocumento.setId_usuario_creacion(522);
//			 expDocumento.setPeso(file.length());
//			 expDocumento.setDescripcion_peso(Utils.convertirLongBytes(file.length(), false));
//			
//			 expDocumento.setEstado_accion("D");
//			 expDocumento.setEstado(Boolean.TRUE);
//			 //expDocumento.setExpediente_id();
//			 //expDocumento.setNro_archivo(nro_documento++);
//			 expDocumento.setMedio(Boolean.FALSE);
//			
//			 String extension = expDocumento.getTexto().substring(expDocumento.getTexto().length()-4, expDocumento.getTexto().length());
//				
//				System.out.println("EXTENSIÓN: "+extension);
//				
//				if (extension.equals(".pdf")){
//					expDocumento.setTipo_archivo("PDF");
//					expDocumento.setGrupo_formato("PDF");
//				}else if(extension.equals(".zip")){
//					expDocumento.setTipo_archivo("ZIP");
//					expDocumento.setGrupo_formato("ZIPRAR");
//				}else if(extension.equals(".rar")){
//					expDocumento.setTipo_archivo("RAR");
//					expDocumento.setGrupo_formato("ZIPRAR");
//				}
//				
//		           if(extension.equals(".pdf")){
//		           
//		            PdfReader pf = new PdfReader(new FileInputStream(file));
//		            for(int i = 0; i < pf.getNumberOfPages(); i++) {
//		            	System.out.println("" + pf.getPageContent(i+1).length + " - " +Utils.convertirLongBytes(pf.getPageContent(i+1).length, false));
//		            	System.out.println("subrep " +pf.getPageContent(i+1).length);
//		            	DocumentoPagina  dp = new DocumentoPagina();
//		            	dp.setNro_pagina(i+1);
//		            	dp.setPeso(Long.valueOf(pf.getPageContent(i+1).length));
//		            	dp.setDescripcion_peso(Utils.convertirLongBytes(Long.valueOf(pf.getPageContent(i+1).length), false));
//		           
//		            	expDocumento.getListaPaginas().add(dp);
//		            	dp.setFlag(Boolean.TRUE);
//		            	
//		            }
//		            
//		          expDocumento.setNro_paginas(pf.getNumberOfPages());
//		          pf.close();
//		          
//		          }
//		     
//		           
//			 String query = " select  dir, expediente_id, orden_expediente from temp_carga_datos where nombre_pdf = ? ";
//			 pst = con.prepareStatement(query);
//			 pst.setString(1, file.getName());
//			 
//			 ResultSet rs = pst.executeQuery();
//			 
//	
//			 String dir_temp = "";
//			 while (rs.next()){
//				 dir_temp = rs.getString(1)+"/";
//				 expDocumento.setRuta(RUTA_PREVIA+dir_temp+file.getName());
//				 expDocumento.setExpediente_id(rs.getInt(2));
//				 expDocumento.setOrden_expediente(rs.getInt(3));
//				 System.out.println("La ruta es : " + RUTA_PREVIA+dir_temp+file.getName());
//			 }
//			 
//			 
//			 //expDocumento.setOrden_expediente(null);
//			 listaDocumentos.add(expDocumento);
//			 
//			 //llamo al procedure para el insert
//			 
//		 }
//		 
//	
//		 
//		  for (ExpedienteDocumento ex : listaDocumentos){
//			  
//			    sp = con.prepareCall("{call insert_expediente_documento(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
//			    
//			    
//				sp.setInt(1,ex.getExpediente_id());
//				sp.setString(2, ex.getNombre_archivo());
//				sp.setString(3, ex.getRuta());
//				sp.setInt(4, ex.getId_usuario_creacion());
//				sp.setLong(5, ex.getPeso());
//				sp.setString(6, ex.getDescripcion_peso());
//				sp.setInt(7, ex.getNro_paginas());
//				sp.setString(8, ex.getEstado_accion());
//				sp.setBoolean(9, ex.isEstado());
//				sp.setString(10, ex.getTipo_archivo());
//				sp.setString(11, ex.getGrupo_formato());
//				sp.setBoolean(12, ex.isMedio());
//				sp.setInt(13, ex.getOrden_expediente());
//				
//				
//				ResultSet rs = sp.executeQuery();
//			
//			
//		  }
//		 
//		 
//		
//		
//		
//	}
//	
//
//	// clase para leer y listar los archivos de un directorio
//	 public File[] listFilesAndFolders(String directoryName){
//	        File directory = new File(directoryName);
//	        //get all the files from a directory
//	        File[] fList = directory.listFiles();
////	        Integer i = 0;
////	        for (File file : fList){
////	            System.out.println(file.getName());
////	            i++;
////	        }
//	        //System.out.println(i + " Files encontrados");
//	        
//	        return fList;
//	        
//	  }
	 

	 


}
