package src.com.certicom.certiscan.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

import com.certicom.certiscan.commons.ExportarArchivo;

public class ExcelCreator {
	
	private CellStyle centerXY;
	private CellStyle h1;
	
	
	public void generarExcel(String ruta) throws IOException{
		String rutaArchivo = ExportarArchivo.getPath(ruta);
        File archivoXLS = new File(rutaArchivo);
        if(archivoXLS.exists()) archivoXLS.delete();
        archivoXLS.createNewFile();
        
        Workbook libro = new HSSFWorkbook();
        libro = loadStyles(libro);
        libro = buildHojaReporteCredito(libro);
       
        
        
        
        
        FileOutputStream archivo = new FileOutputStream(archivoXLS);
        libro.write(archivo);
        archivo.close();
        System.out.println("se genero el archivo ");
	}
	
	private Workbook loadStyles(Workbook libro){
		centerXY=libro.createCellStyle();
		centerXY.setAlignment(CellStyle.ALIGN_CENTER);
		centerXY.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		
		h1=libro.createCellStyle();
		h1.setAlignment(CellStyle.ALIGN_CENTER);
		h1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		
		h1.setFillBackgroundColor(HSSFColor.BLUE_GREY.index);
		h1.setBorderRight(CellStyle.BORDER_THIN);
		h1.setRightBorderColor(HSSFColor.BLACK.index);
		h1.setBorderBottom(CellStyle.BORDER_THIN);
		h1.setBottomBorderColor(HSSFColor.BLACK.index);
		h1.setBorderLeft(CellStyle.BORDER_THIN);
		h1.setLeftBorderColor(HSSFColor.BLACK.index);
		h1.setBorderTop(CellStyle.BORDER_THIN);
		h1.setTopBorderColor(HSSFColor.BLACK.index);
		
		return libro;
	}
	
	private Workbook buildHojaReporteCredito(Workbook libro){
		
		Sheet hoja = libro.createSheet("Reporte de Crédito");
		
		Row a_r1 = hoja.createRow(0);
		Cell a_r1_c1 = a_r1.createCell(0);
		a_r1_c1.setCellValue("Datos de Cliente"); 
		a_r1_c1.setCellStyle(centerXY);
		
		
		Row b_r1 = hoja.createRow(2);
		Cell b_r1_c1 = b_r1.createCell(0);
		b_r1_c1.setCellValue("Garantías"); 
		b_r1_c1.setCellStyle(centerXY);
		
		Row c_r1 = hoja.createRow(5);
		Cell c_r1_c1 = c_r1.createCell(0);
		c_r1_c1.setCellValue("Fortalezas del Titular"); 
		c_r1_c1.setCellStyle(centerXY);
		
		
		hoja.addMergedRegion(CellRangeAddress.valueOf("A1:N1"));
		hoja.addMergedRegion(CellRangeAddress.valueOf("A3:N3"));
		hoja.addMergedRegion(CellRangeAddress.valueOf("A6:N6"));
		
		

		return libro;
	}
}
