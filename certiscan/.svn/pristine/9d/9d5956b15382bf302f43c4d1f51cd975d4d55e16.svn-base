package com.certicom.certiscan.commons;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.biff.CountryCode;

import org.apache.log4j.Logger;

import com.certicom.certiscan.domain.ArchivoExcel;

public class LeerExcel {

	private static Logger depurador = Logger.getLogger(LeerExcel.class.getName());

	public static ArchivoExcel leerExcelBanco(InputStream archivoDestino) {

		ArchivoExcel archivo = new ArchivoExcel();
		List<String> listaCabecera = new ArrayList<String>();
		List<String> listaData = new ArrayList<String>();

		final List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		final List<String[]> rows = new ArrayList<String[]>();

		WorkbookSettings wbSettings = new WorkbookSettings();
		wbSettings.setEncoding("ISO-8859-1");
		wbSettings.setLocale(new Locale("es", "ES"));
		wbSettings.setExcelDisplayLanguage("ES");
		wbSettings.setExcelRegionalSettings("ES");
		wbSettings.setCharacterSet(CountryCode.SPAIN.getValue());

		try {
			Workbook archivoExcel = Workbook.getWorkbook(archivoDestino, wbSettings);
			for (int sheetNo = 0; sheetNo < 1; sheetNo++) {
				Sheet hoja = archivoExcel.getSheet(sheetNo);
				int numColumnas = hoja.getColumns();
				int numFilas = hoja.getRows();
				String data;
				archivo.setNroColumnas(numColumnas);
				archivo.setNroFilas(numFilas);
				for (int fila = 0; fila < numFilas; fila++) {
					if (fila == 0) {
						for (int columna = 0; columna < numColumnas; columna++) {
							data = hoja.getCell(columna, fila).getContents();
							listaCabecera.add(data);
							// System.out.print(data + " ");
						}
					} else {
						for (int columna = 0; columna < numColumnas; columna++) {
							data = hoja.getCell(columna, fila).getContents();
							listaData.add(data);
							// System.out.print(data + " ");
						}
					}
					// System.out.println("\n");
				}
			}

			archivo.setListaCabecera(listaCabecera);
			archivo.setListaData(listaData);

			// BUILD ROWS
			System.out.println("cantidad de cabeceras " + archivo.getNroColumnas());
			String[] row = new String[listaCabecera.size()];
			int i = 0;
			int size = 0;
			for (String val : listaData) {
				size++;
				if (i < listaCabecera.size()) {
					row[i] = val;
					i++;
					if (size == listaData.size()) {
						rows.add(row);
					}
				} else {
					rows.add(row);
					i = 0;
					row = new String[listaCabecera.size()];
					row[i] = val;
					i++;
				}

			}

			// BUILD MAP
			for (String[] rowx : rows) {
				Map<String, String> map = new HashMap<String, String>();
				int j = 0;
				for (String val : rowx) {
					map.put(listaCabecera.get(j), val);
					j++;
				}
				mapList.add(map);
			}

			archivo.setRows(rows);
			archivo.setMapList(mapList);
		} catch (Exception ioe) {
			ioe.printStackTrace();
		}

		archivo.setNroFilas(archivo.getNroFilas() - 1);
		archivo.setListaCabecera(listaCabecera);
		archivo.setListaData(listaData);
		return archivo;
	}

}
