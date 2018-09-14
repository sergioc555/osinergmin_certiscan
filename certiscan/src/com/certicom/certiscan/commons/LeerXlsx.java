package com.certicom.certiscan.commons;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;

import com.certicom.certiscan.domain.ArchivoExcel;
import com.myjeeva.poi.ExcelReader;
import com.myjeeva.poi.ExcelRowContentCallback;
import com.myjeeva.poi.ExcelSheetCallback;
import com.myjeeva.poi.ExcelWorkSheetRowCallbackHandler;
import com.ocpsoft.shade.org.apache.commons.logging.Log;
import com.ocpsoft.shade.org.apache.commons.logging.LogFactory;
import com.sun.media.sound.InvalidFormatException;

public class LeerXlsx {
	private static final Log LOG = LogFactory.getLog(LeerXlsx.class);

	/**
	 * @param args
	 * @throws Exception
	 */
	final List<String> lista = new ArrayList<String>();

	public static ArchivoExcel leerExcelBanco(InputStream inputStream) throws Exception {
		OPCPackage pkg = null;
		final ArchivoExcel archivo = new ArchivoExcel();
		final List<String> listaCabecera = new ArrayList<String>();
		final List<String> listaData = new ArrayList<String>();
		final List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();
		final List<String[]> rows = new ArrayList<String[]>();

		try {
			ExcelWorkSheetRowCallbackHandler sheetRowCallbackHandler = new ExcelWorkSheetRowCallbackHandler(new ExcelRowContentCallback() {
				int j = 0;

				@Override
				public void processRow(int rowNum, Map<String, String> map) {

					j++;
					Iterator it = map.entrySet().iterator();
					int i = 0;
					while (it.hasNext()) {

						Map.Entry e = (Map.Entry) it.next();
						if (e.getKey() != null && e.getKey().toString().trim() != null) {
							if (rowNum == 1) {
								i++;
								listaCabecera.add(e.getKey().toString());
								archivo.setNroColumnas(i);
							}
							// System.out.println(j+"==>"+
							// i+": "+e.getKey() + " " + e.getValue());
							archivo.setNroFilas(rowNum);
							listaData.add(e.getValue().toString());
						}

					}

				}

			});

			pkg = OPCPackage.open(inputStream);

			ExcelSheetCallback sheetCallback = new ExcelSheetCallback() {
				private int sheetNumber = 0;

				@Override
				public void startSheet(int sheetNum) {
					this.sheetNumber = sheetNum;
					System.out.println("Started processing sheet number=" + sheetNumber);
				}

				@Override
				public void endSheet() {
					System.out.println("Processing completed for sheet number=" + sheetNumber);
				}
			};

			System.out.println("Constructor: pkg, sheetRowCallbackHandler, sheetCallback");
			ExcelReader example1 = new ExcelReader(pkg, sheetRowCallbackHandler, sheetCallback);
			example1.process();

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

		} catch (RuntimeException are) {
			LOG.error(are.getMessage(), are.getCause());
		} catch (InvalidFormatException ife) {
			LOG.error(ife.getMessage(), ife.getCause());
		} catch (IOException ioe) {
			LOG.error(ioe.getMessage(), ioe.getCause());
		} finally {
			IOUtils.closeQuietly(inputStream);
			try {
				if (pkg != null) {
					pkg.close();
				}
			} catch (IOException e) {
				// just ignore IO exception
			}
		}

		return archivo;
	}

}
