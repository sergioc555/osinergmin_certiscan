package com.certicom.certiscan.commons;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.text.StrSubstitutor;
//import org.apache.commons.mail.EmailAttachment;
//import org.apache.commons.mail.EmailException;
//import org.apache.commons.mail.HtmlEmail;




import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;

import com.certicom.certiscan.obj.Periodo;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Logger;
import com.jcraft.jsch.Session;
public class Utils {
	private ChannelSftp sftpChannel;
	private Session session;

	public ChannelSftp crearConexionSFTP(){
		JSch jsch = new JSch();
        session = null;
        Channel channel =null;
        try 
        {

            session = jsch.getSession(Constante.USER_SERVER_IMG, Constante.HOST_SERVER_IMG);
            java.util.Properties config = new java.util.Properties(); 
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);  
            session.setPassword(Constante.PASS_SERVER_IMG);                
            session.connect();              
            channel = session.openChannel("sftp");
            channel.connect();
            System.out.println("Is connected to IP:"+ channel.isConnected());
            }
            catch (JSchException e) {
                e.printStackTrace();
            }
        
        sftpChannel = (ChannelSftp) channel;
        return sftpChannel;
		
	}
	
	public void cerrarConexionSFTP(){
		sftpChannel.exit();             
        session.disconnect();
        	
	}
	
	public void createZip(String filename, String carpeta, int inicioSubLote,int finalSubLote) {

		try {
			int BUFFER = 2048;
			BufferedInputStream origin = null;
			FileOutputStream dest = new FileOutputStream(filename);
			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
			out.setMethod(ZipOutputStream.DEFLATED);
			byte data[] = new byte[BUFFER];

			for (int i = inicioSubLote; i <= finalSubLote; i++) {
				String filename1 = Constante.PREFIJO_IMG + autocompletar(i) + Constante.EXTENSION_IMG; 
				System.out.println("Agregando al ZIP: " + filename1);
				FileInputStream fi = new FileInputStream(carpeta + filename1);
				origin = new BufferedInputStream(fi, BUFFER);
				ZipEntry entry = new ZipEntry(filename1);

				// Guardamos el objeto en el ZIP
				out.putNextEntry(entry);
				int count;
				// Escribimos el objeto en el ZIP
				while ((count = origin.read(data, 0, BUFFER)) != -1) {
					out.write(data, 0, count);
					
				}
				out.flush();
				origin.close();
			}
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void UnZip(String dirzipOriginal, String dirDest) throws Exception {
		try {
			int BUFFER = 2048;
			File dirDestino = new File(dirDest);
			BufferedOutputStream dest = null;
			FileInputStream fis = new FileInputStream(dirzipOriginal);
			ZipInputStream zis = new ZipInputStream(
					new BufferedInputStream(fis));
			FileOutputStream fos = null;
			ZipEntry entry;
			int count;
			int index = 0;
			byte data[] = new byte[BUFFER];
			String rutaarchivo = null;
			while ((entry = zis.getNextEntry()) != null) {
				System.out.println("Extracting: " + entry);
				rutaarchivo = entry.getName();
				// tenemos que quitar el primer directorio
				index = rutaarchivo.indexOf("/");
				rutaarchivo = rutaarchivo.substring(index + 1);
				if (rutaarchivo.trim().length() > 0) {
					// write the files to the disk
					try {
						dest = null;
						File fileDest = new File(dirDestino.getAbsolutePath() + "/" + rutaarchivo);
						if (entry.isDirectory()) {
							fileDest.mkdirs();
						} else {
							if (fileDest.getParentFile().exists() == false)
								fileDest.getParentFile().mkdirs();

							fos = new FileOutputStream(fileDest);
							dest = new BufferedOutputStream(fos, BUFFER);
							while ((count = zis.read(data, 0, BUFFER)) != -1) {
								dest.write(data, 0, count);
							}
							dest.flush();
						}
					} finally {
						try {
							if (dest != null)
								dest.close();
						} catch (Exception e) {
							;
						}
					}
				}
			}
			zis.close();
			File zip = new File(dirzipOriginal); 
		    zip.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	static class MyJschLogger implements Logger {
		static java.util.Hashtable name = new java.util.Hashtable();
		static {
			name.put(new Integer(DEBUG), "DEBUG: ");
			name.put(new Integer(INFO), "INFO: ");
			name.put(new Integer(WARN), "WARN: ");
			name.put(new Integer(ERROR), "ERROR: ");
			name.put(new Integer(FATAL), "FATAL: ");
		}

		public boolean isEnabled(int level) {
			return true;
		}

		public void log(int level, String message) {
			System.err.print(name.get(new Integer(level)));
			System.err.println(message);
		}

	}
	
	
	
	public String autocompletar(int num){
		String num1 = String.valueOf(num);
		String cero = "0";
		
		for(int i=0;i<Constante.NRO_DIG_NUMERACION_SCANNER - num1.length()-1;i++){
			cero = 0 + cero;
		}
		return cero+num1;
	}

	public Date sumarRestarDiasFecha(Date fecha, int dias){
		      Calendar calendar = Calendar.getInstance();	
		      calendar.setTime(fecha); // Configuramos la fecha que se recibe
		      calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de d�as a a�adir, o restar en caso de d�as<0
		      return calendar.getTime(); // Devuelve el objeto Date con los nuevos d�as a�adidos
		 }
	
	public int getDiasHabiles(Calendar fechaInicial, Calendar fechaFinal) {

		int diffDays= 0;

		//mientras la fecha inicial sea menor o igual que la fecha final se cuentan los dias

		while (fechaInicial.before(fechaFinal) /*|| fechaInicial.equals(fechaFinal)*/) {

		//si el dia de la semana de la fecha minima es diferente de sabado o domingo
		if (fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY && fechaInicial.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
		//se aumentan los dias de diferencia entre min y max
		diffDays++;
		}
		//se suma 1 dia para hacer la validacion del siguiente dia.
		fechaInicial.add(Calendar.DATE, 1);

		}return diffDays;

		}
	
	public int getDiasCalendario(Calendar fechaInicial, Calendar fechaFinal) {

		int diffDays= 0;

		//mientras la fecha inicial sea menor o igual que la fecha final se cuentan los dias

		while (fechaInicial.before(fechaFinal) /*|| fechaInicial.equals(fechaFinal)*/) {

		//se aumentan los dias de diferencia entre min y max
		diffDays++;
		
		//se suma 1 dia para hacer la validacion del siguiente dia.
		fechaInicial.add(Calendar.DATE, 1);

		}return diffDays;

		}
	
	public int getDiferenciaDias(Calendar fechaInicial, Calendar fechaFinal) {

		int diffDays= 0;

		//mientras la fecha inicial sea menor o igual que la fecha final se cuentan los dias
		if (fechaInicial.before(fechaFinal)){
			while (fechaInicial.before(fechaFinal) /*|| fechaInicial.equals(fechaFinal)*/) {
	
			//se aumentan los dias de diferencia entre min y max
			diffDays++;
			
			//se suma 1 dia para hacer la validacion del siguiente dia.
			fechaInicial.add(Calendar.DATE, 1);
	
			}
		
		}else if(fechaInicial.after(fechaFinal)){
			while (fechaInicial.after(fechaFinal) /*|| fechaInicial.equals(fechaFinal)*/) {
				
				//se restan los dias de diferencia entre min y max
				diffDays--;
				
				//se suma 1 dia para hacer la validacion del siguiente dia.
				fechaInicial.add(Calendar.DATE, -1);
				
		
				}
		}
		
		return diffDays;

		}
	
	public Calendar DateToCalendar(Date date ) 
	{ 
		Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  return cal;
	 }
	
	public Calendar StringToCalendar(String fecha){
		//String dateStr = "04/05/2010"; 
		Calendar calendar = Calendar.getInstance();
		try {
		SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd"); 
		Date dateObj = curFormater.parse(fecha);
		
		calendar.setTime(dateObj);
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return calendar; 
		
	}
	
	public String DateToString(Date fecha){
		
		DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		String sfecha=formatoFecha.format(fecha);
		return sfecha;
		
	}
	
	public Date sumarDiasHabiles(Date fecha, int cantidad){

		int i=0;
		while (i<cantidad) {
			fecha.setTime(fecha.getTime()+24*60*60*1000); 
			
			Calendar fechac= DateToCalendar(fecha);
			int dia = fechac.get(Calendar.DAY_OF_WEEK);
		if (dia>=2 && dia<=6){
		i++; 
		}else{
			if(dia==7){
				fecha.setTime(fecha.getTime()+24*60*60*1000*2); 
			}else{
				fecha.setTime(fecha.getTime()+24*60*60*1000); 
			}
		}
		
		}
		
		//fecha = sumarRestarDiasFecha(fecha, i);
		
		
		return fecha;
		
	}
	
	public List<Periodo> armar_mes(){
		Periodo operiodo = null;
		
		List<Periodo> periodo = new ArrayList<Periodo>();
		
		operiodo = new Periodo("01", "Enero");
		
		periodo.add(operiodo);
				
		operiodo = new Periodo("02", "Febrero");
				
				periodo.add(operiodo);
				
		operiodo = new Periodo("03", "Marzo");
				
				periodo.add(operiodo);
				
		operiodo = new Periodo("04", "Abril");
				
				periodo.add(operiodo);
				
		operiodo = new Periodo("05", "Mayo");
				
				periodo.add(operiodo);
				
		operiodo = new Periodo("06", "Junio");
				
				periodo.add(operiodo);
				
		operiodo = new Periodo("07", "Julio");
				
				periodo.add(operiodo);
				
		operiodo = new Periodo("08", "Agosto");
				
				periodo.add(operiodo);
				
		operiodo = new Periodo("09", "Septiembre");
				
				periodo.add(operiodo);
				
		operiodo = new Periodo("10", "Octubre");
				
				periodo.add(operiodo);
				
		operiodo = new Periodo("11", "Noviembre");
				
				periodo.add(operiodo);
				
		operiodo = new Periodo("12", "Diciembre");
				
				periodo.add(operiodo);
		
		
		
		return periodo;
	}
	
	public String convertirANombre(String mes){
		
			String nmes="";
		
			switch(mes){
			
			case "01":
				nmes = "Enero";
				break;
			case "02":
				nmes = "Febrero";
				break;
			case "03":
				nmes = "Marzo";
				break;
			case "04":
				nmes = "Abril";
				break;
			case "05":
				nmes = "Mayo";
				break;
			case "06":
				nmes = "Junio";
				break;
			case "07":
				nmes = "Julio";
				break;
			case "08":
				nmes = "Agosto";
				break;
			case "09":
				nmes = "Septiembre";
				break;
			case "10":
				nmes = "Octubre";
				break;
			case "11":
				nmes = "Noviembre";
				break;
			default:
				nmes = "Diciembre";
				break;
			}
		
		  return nmes;
	}
	
	/* JDELGADO - RQ003 - Inicio */
	public byte[] generarArchivo(List list){
        JasperPrint jasperPrint = null;
        byte[] results = null;
        try {
            jasperPrint = cargarJasperPrint(list);
            if (jasperPrint != null) {

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                JRCsvExporter exporterCsv = new JRCsvExporter();
                exporterCsv.setParameter(JRCsvExporterParameter.JASPER_PRINT, jasperPrint);
                exporterCsv.setParameter(JRCsvExporterParameter.OUTPUT_STREAM, outputStream);
                exporterCsv.exportReport();

                results = outputStream.toByteArray();
                
                exporterCsv=null;
            }
        } catch (JRException jrexcepcion) {
            jrexcepcion.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return results;

    }
    
    public JasperPrint cargarJasperPrint(List list){
        JasperPrint jasperPrint = null;
        InputStream plantillaReporte = null;
        InputStream imagenLogo = null;
        String rutaReporte = "";
        try {
        	rutaReporte = "/com/pe/certicom/nextel/commons/reporteAutomatizacionPO.jasper";
            //String nomCabecera = "/pe/com/certicom/vacaciones/util/001.jpg";
            
            plantillaReporte = this.getClass().getClassLoader().getResourceAsStream(rutaReporte);
            //imagenLogo = this.getClass().getClassLoader().getResourceAsStream(nomCabecera);
            
            // Mapa de Par�metros
            HashMap parametros = new HashMap();
            parametros.put("P_NO_REPORTE", "AUTOMATIZACI�N REPORTE PRODUCCI�N OPERATIVA");
            //parametros.put("P_IMAGEN_LOGO", imagenLogo);
            //parametros.put("P_IP", obtenerIp());
            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);

            jasperPrint = JasperFillManager.fillReport(plantillaReporte,parametros,beanColDataSource);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        
        return jasperPrint;
    }
    
    //INICIO JPISCOYA
    public byte[] generarArchivo_Incidencias(List list){
        JasperPrint jasperPrint = null;
        byte[] results = null;
        try {
            jasperPrint = cargarJasperPrint_Incidencias(list);
            if (jasperPrint != null) {

                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                JRCsvExporter exporterCsv = new JRCsvExporter();
                exporterCsv.setParameter(JRCsvExporterParameter.JASPER_PRINT, jasperPrint);
                exporterCsv.setParameter(JRCsvExporterParameter.OUTPUT_STREAM, outputStream);
                exporterCsv.exportReport();

                results = outputStream.toByteArray();
                
                exporterCsv=null;
            }
        } catch (JRException jrexcepcion) {
            jrexcepcion.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return results;

    }
    
    
    public JasperPrint cargarJasperPrint_Incidencias(List list){
        JasperPrint jasperPrint = null;
        InputStream plantillaReporte = null;
        InputStream imagenLogo = null;
        String rutaReporte = "";
        try {
        	rutaReporte = "/com/pe/certicom/nextel/commons/reporteAutomatizacionPO_Incidencias.jasper";
            //String nomCabecera = "/pe/com/certicom/vacaciones/util/001.jpg";
            
            plantillaReporte = this.getClass().getClassLoader().getResourceAsStream(rutaReporte);
            //imagenLogo = this.getClass().getClassLoader().getResourceAsStream(nomCabecera);
            
            // Mapa de Par�metros
            HashMap parametros = new HashMap();
            parametros.put("P_NO_REPORTE", "AUTOMATIZACI�N REPORTE PRODUCCI�N OPERATIVA INCIDENCIAS");
            //parametros.put("P_IMAGEN_LOGO", imagenLogo);
            //parametros.put("P_IP", obtenerIp());
            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(list);

            jasperPrint = JasperFillManager.fillReport(plantillaReporte,parametros,beanColDataSource);

        } catch (Exception exception) {
            exception.printStackTrace();
        }
        
        return jasperPrint;
    }
    
    //FIN JPISCOYA
    public File bytesToFile(String dir, String fileName, byte[] bytes) throws IOException {
    	
        //Verificando si existe directorio
        boolean exists = (new File(dir)).exists();
        if (!exists)
            (new File(dir)).mkdirs();

        File file = new File(dir+"/"+fileName);
        if(file.exists()){
            file.delete();
        }
        
        //Obteniendo archivo temporal unico
        File f = uniqueFile(new File(dir), fileName);
        String i = f.getAbsolutePath();
        OutputStream out = new FileOutputStream(i);
        out.write(bytes);
        out.close();
        
        return f;
    }
    
    public File uniqueFile(File filePath, String fileName) throws IOException {
    	
        File file = new File(filePath, fileName);
        if (file.exists()) {
            // Split filename and add braces, e.g. "name.ext" --> "name[", "].ext".
            String prefix;
            String suffix;
            int dotIndex = fileName.lastIndexOf(".");
            if (dotIndex > -1) {
                prefix = fileName.substring(0, dotIndex) + "[";
                suffix = "]" + fileName.substring(dotIndex);
            } else {
                prefix = fileName + "[";
                suffix = "]";
            }
            int count = 0;
            // Add counter to filename as long as file exists.
            while (file.exists()) {
                if (count < 0) { // int++ restarts at -2147483648 after 2147483647.
                    throw new IOException("No unique filename available for " + fileName + " in path " + filePath.getPath() + ".");
                }
                // Glue counter between prefix and suffix, e.g. "name[" + count + "].ext".
                file = new File(filePath, prefix + (count++) + suffix);
            }
        }
        
        return file;
    }
    
    
    public int enviarCorreoElectronico(String asunto, String direccionOrigen, String direccionDestino, Map datos, String formatoHtml,
			List<String> pathAdjuntos, String idTipoFormatoDocumento) {
       
        return 1;
    }

    
    public String obtenerEstilosCorreoHtml(){
        String estiloUsado = "";

        /* Estilos para correos con colores */
        StringBuffer estilosColor = new StringBuffer();
        estilosColor.append("body{\n")
        .append("	font-family: Verdana, Arial, Helvetica, sans-serif;\n")
        .append("	background-color: #FAFAFA;\n")
        .append("	font-size:12px;\n")
        .append("	color: #000000;\n")
        .append("}\n")
        .append(".contenedorPrincipal{\n")
        .append("	border: 1px #DDDDDD solid;\n")
        .append("	width: 722px;\n")
        .append("	background-color:#FFFFFF;\n")
        .append("	padding: 1px;\n")
        .append("	height: 300px;\n")
        .append("}\n")
        .append(".polig{\n")
        .append("	width:60px;\n")
        .append("	height:10px;\n")
        .append("	font-family:arial;\n")
        .append("	font-weight:bold;\n")
        .append("	color:#EEEEEE;\n")
        .append("}\n")
        .append("div#titulo{\n")
        .append("	width: 100%;\n")
        .append("	height: 50px;\n")
        .append("	clear:both;\n")
        .append("	text-align:center;\n")
        .append("	color:#FFFFFF;\n")
        .append("	font-size:25px;\n")
        .append("	padding-top: 10px;\n")
        .append("	background-color: #8b0000;\n")
        .append("}\n")
        .append("div#mensaje{\n")
        .append("	padding: 10px;\n")
        .append("	float: left;\n")
        .append("}\n")
        .append("div#tituloLeyenda{\n")
        .append("	width:100%;\n")
        .append("	height:auto;\n")
        .append("	font-weight: bold;\n")
        .append("	text-decoration:underline;\n")
        .append("	margin-bottom:12px;\n")
        .append("	clear:both;\n")
        .append("	float: left;\n")
        .append("}\n")
        .append(".verde {\n")
        .append("	color: green;\n")
        .append("	font-weight: bold;\n")
        .append("}\n")
        .append(".ambar {\n")
        .append("	color: yellow;\n")
        .append("	font-weight: bold;\n")
        .append("}\n")
        .append(".rojo {\n")
        .append("	color: red;\n")
        .append("	font-weight: bold;\n")
        .append("}\n")
        .append(".iconomuestra{\n")
        .append("	width:10px;\n")
        .append("	height:10px;\n")
        .append("	float:left;\n")
        .append("	margin:2px 2px 2px 2px;\n")
        .append("	border:solid 1px;\n")
        .append("}\n");

        estiloUsado += estilosColor.toString();
        return estiloUsado;
    }
    
    public String toASCII(String html) {
        String output = "";
        try {
            html = html.replaceAll("�", "&aacute;");
            html = html.replaceAll("�", "&eacute;");
            html = html.replaceAll("�", "&iacute;");
            html = html.replaceAll("�", "&oacute;");
            html = html.replaceAll("�", "&uacute;");
            html = html.replaceAll("�", "&Aacute;");
            html = html.replaceAll("�", "&Eacute;");
            html = html.replaceAll("�", "&Iacute;");
            html = html.replaceAll("�", "&Oacute;");
            html = html.replaceAll("�", "&Uacute;");
            html = html.replaceAll("�", "&ntilde;");
            html = html.replaceAll("�", "&Ntilde;");
            html = html.replaceAll("�", "&Uuml;");
            html = html.replaceAll("�", "&uuml;");
            html = html.replaceAll("�", "");
            html = html.replaceAll("�", "");
            html = html.replaceAll("�", "&deg;");
            html = html.replaceAll("�", "&ordm;");
            html = html.replaceAll("�", "&iquest;");
            html = html.replaceAll("á", "&aacute;");
            html = html.replaceAll("é", "&eacute;");
            html = html.replaceAll("®", "&reg;");
            html = html.replaceAll("ì", "&iacute;");
            html = html.replaceAll("�", "&iacute;");
            html = html.replaceAll("ó", "&oacute;");
            html = html.replaceAll("ú", "&uacute;");
            html = html.replaceAll("n~", "&ntilde;");
            html = html.replaceAll("º", "&ordm;");
            html = html.replaceAll("ª", "&ordf;");
            html = html.replaceAll("Ã¡", "&aacute;");
            html = html.replaceAll("ñ", "&ntilde;");
            html = html.replaceAll("Ñ", "&Ntilde;");
            html = html.replaceAll("Ã±", "&ntilde;");
            html = html.replaceAll("n~", "&ntilde;");
            html = html.replaceAll("Ú", "&Uacute;");
            html = html.replaceAll("�", "");
            html = html.replaceAll("�", "");
            html = html.replaceAll("&Acirc;", "");
            html = html.replaceAll("&acirc;", "");
        } catch (Exception e) {
            e.printStackTrace();
            output = "";
        }
        return html;
    }
    /* JDELGADO - RQ003 - Fin */
	
	
	
	
	
	
}
