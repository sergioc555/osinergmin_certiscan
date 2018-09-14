package com.certicom.certiscan.commons;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JExcelApiExporter;
import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRCsvExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPdfExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;


public class ExportarArchivo {

	 public static String getPath(String ruta){
	        ServletContext servletContext = (ServletContext) (FacesContext.getCurrentInstance().getExternalContext().getContext());
	        String path = servletContext.getRealPath(ruta);
	        return path;
	    }

	    /**
	     * Exportar archivos a pdf.
	     * @param [jasperFile] [Nombre del archivo compilado .jaspe], tipo [String].
	     * @param [parameters] [Ingreso de parametros que pueden ser utilizados en el jasper], tipo [Map<>].
	     * @return [dataList] [Arreglo que contiene los registros que deberá pintar el jasperreport], tipo [List<?>].
	     * @throws [nombre de excepción] [explicación].
	     */
	    public static byte[] exportPdf(String jasperFile, Map<String, Object> parameters, List<?> dataList) throws Exception {
	        System.out.println("exportPdf ==>");
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFile, parameters, new JRBeanCollectionDataSource(dataList));
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	        JRPdfExporter jRPdfExporter = new JRPdfExporter();
	        jRPdfExporter.setParameter(JRPdfExporterParameter.JASPER_PRINT, jasperPrint);
	        jRPdfExporter.setParameter(JRPdfExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
	        jRPdfExporter.exportReport();
	        byte[] bytes = byteArrayOutputStream.toByteArray();
	        jRPdfExporter = null;
	        return bytes;
	    }
	    
	    
	    
	    /**
	     * Exportar archivos a pdf.
	     * @param [jasperFile] [Nombre del archivo compilado .jaspe], tipo [String].
	     * @param [parameters] [Ingreso de parametros que pueden ser utilizados en el jasper], tipo [Map<>].
	     * @return [dataList] [Arreglo que contiene los registros que deberá pintar el jasperreport], tipo [List<?>].
	     * @throws [nombre de excepción] [explicación].
	     */
	    public static byte[] exportXlsX(String jasperFile, Map<String, Object> parameters, List<?> dataList, boolean isOnePagePerSheet) throws Exception {
	    	 parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
		        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFile, parameters, new JRBeanCollectionDataSource(dataList));
		        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		        
		        JRXlsxExporter exporter = new JRXlsxExporter();
		        exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
		        exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, new Boolean(isOnePagePerSheet));
		        exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		     //   exporter.setParameter(JRXlsExporterParameter.CREATE_CUSTOM_PALETTE, Boolean.TRUE);
		        exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
		        exporter.setParameter(JExcelApiExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		        exporter.exportReport();
		        byte[] bytes = byteArrayOutputStream.toByteArray();
		        byteArrayOutputStream.flush();
		        byteArrayOutputStream.close();
		        exporter = null;
		        return bytes;
	    }
	    
	    
	    
	    
	    
	    public static byte[] exportXls(String jasperFile, Map<String, Object> parameters, List<?> dataList, boolean isOnePagePerSheet) throws Exception {
	        parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFile, parameters, new JRBeanCollectionDataSource(dataList));
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	        JRXlsExporter exporter = new JRXlsExporter();
	        exporter.setParameter(JExcelApiExporterParameter.JASPER_PRINT, jasperPrint);
	        exporter.setParameter(JExcelApiExporterParameter.IS_ONE_PAGE_PER_SHEET, new Boolean(isOnePagePerSheet));
	        exporter.setParameter(JExcelApiExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
	    //    exporter.setParameter(JExcelApiExporterParameter.CREATE_CUSTOM_PALETTE, Boolean.TRUE);
	        exporter.setParameter(JExcelApiExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
	        exporter.setParameter(JExcelApiExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
	        exporter.exportReport();
	        byte[] bytes = byteArrayOutputStream.toByteArray();
	        byteArrayOutputStream.flush();
	        byteArrayOutputStream.close();
	        exporter = null;
	        return bytes;
	    }
	    
	    
	    public static byte[] exportMultipleSheetXls(List<JasperPrint> jaspers ) throws Exception {
	        
	    	
	    	//parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
	        
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

	    //    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFile, parameters, new JRBeanCollectionDataSource(dataList));
	        
	        
	        JRXlsExporter exporter = new JRXlsExporter();
	      
	        //  exporter.setParameter(JExcelApiExporterParameter.JASPER_PRINT, jasperPrint);
	        exporter.setParameter(JExcelApiExporterParameter.JASPER_PRINT_LIST, jaspers);
	        exporter.setParameter(JExcelApiExporterParameter.IS_ONE_PAGE_PER_SHEET, new Boolean(false));
	        exporter.setParameter(JExcelApiExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
	        exporter.setParameter(JExcelApiExporterParameter.OUTPUT_STREAM, byteArrayOutputStream);
	        exporter.setParameter(JExcelApiExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
	        exporter.exportReport();
	        
	        byte[] bytes = byteArrayOutputStream.toByteArray();
	        byteArrayOutputStream.flush();
	        byteArrayOutputStream.close();
	        exporter = null;
	        
	        return bytes;
	    }
	    
	    public static byte[] exportWorkbook(HSSFWorkbook wb) throws Exception {
	    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
			try {
				wb.write(bos);
			} finally {
			    bos.close();
			}
			//wb.getb
			byte[] bytes = bos.toByteArray();
			bos.flush();
			bos.close();
			
			return bytes;
			
	    }
	    
	    
	    
	    public static byte[] exportCSV(String jasperFile, Map<String, Object> parameters, List<?> dataList, boolean isOnePagePerSheet) throws Exception {
	        parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
	        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperFile, parameters, new JRBeanCollectionDataSource(dataList));
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
	       
	        JRCsvExporter csvExporter = new JRCsvExporter();
	            csvExporter.setParameter(JRCsvExporterParameter.FIELD_DELIMITER, "&quot;,&quot;");
	            csvExporter.setParameter(JRCsvExporterParameter.RECORD_DELIMITER, "&quot;n&quot;");
	            csvExporter.setParameter(JRCsvExporterParameter.JASPER_PRINT, jasperPrint);
	            csvExporter.setParameter(JRCsvExporterParameter.IGNORE_PAGE_MARGINS, true);
	            csvExporter.setParameter(JRCsvExporterParameter.OUTPUT_FILE_NAME, jasperFile);
	            csvExporter.exportReport();

	        byte[] bytes = byteArrayOutputStream.toByteArray();
	        byteArrayOutputStream.flush();
	        byteArrayOutputStream.close();
	        csvExporter = null;
	        return bytes;
	    }
	    
	    

	    public static void executePdf(byte[] bytes, String name){
	        try {

	            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	            response.reset();
	            response.setContentType("application/octet-stream");
	            response.setContentLength(bytes.length);
	            response.setHeader("Content-disposition","attachment; filename=\""+name+"\"");
	            response.setHeader("Pragma", "no-cache");
	            response.setDateHeader("Expires", 0);

	            ServletOutputStream ouputStream = response.getOutputStream();
	            ouputStream.write(bytes, 0, bytes.length);
	            ouputStream.flush();
	            ouputStream.close();

	        } catch (Exception e) {
	            System.out.println("ERROR JASPER ==>"+e);
	        }
	    }

	    public static void executeExccel(byte[] bytes, String name){
	        try {

	        	HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	            response.reset();
	            response.setContentType("application/octet-stream");
	            response.setContentLength(bytes.length);
	            response.setHeader("Content-disposition","attachment; filename=\"'"+name+"\"");
	            response.setHeader("Pragma", "no-cache");
	            response.setDateHeader("Expires", 0);
	            
	            ServletOutputStream ouputStream = response.getOutputStream();
	            ouputStream.write(bytes, 0, bytes.length);
	            ouputStream.flush();
	            ouputStream.close();
	            
	        } catch (Exception e) {
	            System.out.println("ERROR JASPER ==>"+e);
	        }
	    }
	    
	    public static void executeFile(byte[] bytes, String name){
	        try {

	        	HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
	            response.reset();
	            response.setContentType("application/octet-stream");
	            response.setContentLength(bytes.length);
	            //response.setHeader("Content-disposition","attachment; filename=\"'"+name+"\"");
	            //response.setHeader("Content-disposition","attachment; filename="+name);
	            response.setHeader("Content-disposition","attachment; filename=\""+name+ "\"");
	            response.setHeader("Pragma", "no-cache");
	            response.setDateHeader("Expires", 0);
	            
	            ServletOutputStream ouputStream = response.getOutputStream();
	            ouputStream.write(bytes, 0, bytes.length);
	            ouputStream.flush();
	            ouputStream.close();
	            
	        } catch (Exception e) {
	            System.out.println("ERROR JASPER ==>"+e);
	        }
	    }

	
}
