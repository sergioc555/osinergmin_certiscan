package com.certicom.certiscan.commons;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.certicom.certiscan.domain.ArchivoExcel;

public class LeerTxt {

	private static Logger depurador = Logger.getLogger(LeerTxt.class.getName());

	public static ArchivoExcel leer(InputStream archivoDestino) {

		ArchivoExcel archivo = new ArchivoExcel();
		List<String> listaCabecera = new ArrayList<String>();
		List<String> listaData = new ArrayList<String>();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(archivoDestino, "iso-8859-1"));
			String sCurrentLine;

			boolean header = true;
			String[] headerVals;
			List<String[]> rows = new ArrayList<String[]>();
			List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();

			while ((sCurrentLine = br.readLine()) != null) {
				String row[] = sCurrentLine.split("\\|");
				if (header) {
					headerVals = row;
					archivo.setHeaders(headerVals);
					header = false;
				} else {
					rows.add(row);

					Map<String, String> valMap = new HashMap<String, String>();
					int i = 0;
					for (String val : row) {
						valMap.put(archivo.getHeaders()[i], val);
						i++;
					}
					mapList.add(valMap);

				}

			}
			archivo.setRows(rows);
			archivo.setMapList(mapList);

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {

		} catch (Exception ioe) {
			ioe.printStackTrace();
		}

		// archivo.setNroFilas(archivo.getNroFilas()-1);
		// archivo.setListaCabecera(listaCabecera);
		// archivo.setListaData(listaData);
		return archivo;
	}

	public static ArchivoExcel leerNroDoc(InputStream archivoDestino) {

		ArchivoExcel archivo = new ArchivoExcel();
		List<String> listaCabecera = new ArrayList<String>();
		List<String> listaData = new ArrayList<String>();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(archivoDestino, "iso-8859-1"));
			String sCurrentLine;

			String[] headerVals;
			List<String[]> rows = new ArrayList<String[]>();
			List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();

			while ((sCurrentLine = br.readLine()) != null) {
				String row[] = sCurrentLine.split(" ", 2);
				row[0] = row[0].replaceAll("[^0-9]", "");
				// row[0] = row[0].replace("\\D", "");
				System.out.println("AGREGANDO " + row[0]);
				rows.add(row);

			}
			archivo.setRows(rows);
			archivo.setMapList(mapList);

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {

		} catch (Exception ioe) {
			ioe.printStackTrace();
		}

		// archivo.setNroFilas(archivo.getNroFilas()-1);
		// archivo.setListaCabecera(listaCabecera);
		// archivo.setListaData(listaData);
		return archivo;
	}
}
