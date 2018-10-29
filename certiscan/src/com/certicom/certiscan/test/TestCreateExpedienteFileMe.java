package com.certicom.certiscan.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.domain.DocumentoPagina;
import com.certicom.certiscan.domain.ExpedienteDocumento;
import com.certicom.certiscan.jdbc.dao.Conexion;
import com.lowagie.text.pdf.PdfReader;

import src.com.certicom.certiscan.utils.CargaDatos;
import src.com.certicom.certiscan.utils.Utils;

//clase creada para crear las carpetas desde archivos en  base de datos

public class TestCreateExpedienteFileMe {

	final static Integer ID_USURIO = 522;

	@Test
	public void cargaExpedientes() throws Exception {

//		cargar_csv_temp(Constante.CSV_DIGITAL);
//		System.out.println("inicio colocarExpedienteId/Expediente DIG");
//		actualizar_temp();
//		System.out.println("fin colocarExpedienteId/Expediente DIG");
//		System.out.println("inicio upload DIG");
//		System.out.println("inicio generarExpedienteDocumento DIG");
//		generarExpedienteDocumentoRAR(Coh's success in this respect in turn owes to its much smaller file sizes, as well as benefiting from the Sun vs. Microsoft suit that resulted in Microsoft removing the MSJVM frnstante.DIR_FILES_RAR, obtenerHmCargaDatos(Constante.RUTA_DIGITILZACION_DIG));
//		System.out.println("inicio create carpetas DIG");
//		crear_carpetas(Constante.RUTA_DIGITILZACION_DIG);
//		System.out.println("fin create carpetas DIG");
//		System.out.println("fin generarExpedienteDocumento DIG");
//		clasificar_archivos(Constante.DIR_FILES_RAR, Constante.RUTA_DIGITILZACION_DIG);
//		System.out.println("fin upload DIG");

//		cargar_csv_temp(Constante.CSV_PAPEL);
//		System.out.println("inicio colocarExpedienteId/Expediente PAP");
//		actualizar_temp();
//		System.out.println("fin colocarExpedienteId/Expediente PAP");
//		System.out.println("inicio generarExpedienteDocumento PAP");
//		generarExpedienteDocumento(Constante.DIR_FILES, obtenerHmCargaDatos(Constante.RUTA_DIGITILZACION_PAP));
//		System.out.println("fin generarExpedienteDocumento PAP");
//		System.out.println("inicio create carpetas PAP");
//		crear_carpetas(Constante.RUTA_DIGITILZACION_PAP);
//		System.out.println("fin create carpetas PAP");
//		System.out.println("inicio upload PAP");
//		clasificar_archivos(Constante.DIR_FILES, Constante.RUTA_DIGITILZACION_PAP);
//		System.out.println("fin upload PAP");

		generarMedios(Constante.ETIQUETA_ENTREGABLE_DIG);
//		generarMedios(Constante.ETIQUETA_ENTREGABLE_PAP);
	}

	public void cargar_csv_temp(String rutaCsv) throws Exception {

		String queryCopy = "";
		String query = " delete from temp_carga_datos ";

		if (rutaCsv.equals(Constante.CSV_DIGITAL)) {
			queryCopy = "COPY temp_carga_datos(nro_expediente,fecha_reg,sede,nombre_pdf,dir) FROM '" + rutaCsv
					+ "' DELIMITER '$' CSV HEADER";
		} else {
			queryCopy = "COPY temp_carga_datos(dir,fecha_reg,nombre_pdf,nro_expediente,tipo_documento,numero_documento,fecha_del_documento,numero_de_imagenes,asunto,datos_del_cliente,sede,firma_del_fedatario,fecha_aprobacion_fedatario,observacion) FROM '"
					+ rutaCsv + "' DELIMITER '$' CSV HEADER";
		}

		try (Connection con = Conexion.getConnection();) {

			try (PreparedStatement pst = con.prepareStatement(query);) {
				pst.executeUpdate();
			} catch (Exception e) {
				System.out.println("ocurrio un error " + e.getMessage());
				throw new Exception(e.getMessage());
			}

			try (PreparedStatement pstCopy = con.prepareStatement(queryCopy);) {
				pstCopy.executeUpdate();
			} catch (Exception e) {
				System.out.println("ocurrio un error " + e.getMessage());
				throw new Exception(e.getMessage());
			}
		}
	}

	public void actualizar_temp() throws Exception {

		String query = "insert into t_expediente (temp_dir, nro_expediente, fecha_reg, idusuario, id_estado, entregable) select distinct dir, nro_expediente, now(), 522, 6, '"
				+ Constante.NUMERO_ENTREGABLE
				+ "' from temp_carga_datos where expediente_id is null group by dir, nro_expediente";

		String query2 = "UPDATE temp_carga_datos a SET expediente_id = b.expediente_id FROM t_expediente b WHERE a.nro_expediente = b.nro_expediente and b.entregable='"
				+ Constante.NUMERO_ENTREGABLE + "' and b.id_estado=6; ";

		try (Connection con = Conexion.getConnection();
				PreparedStatement pst = con.prepareStatement(query);
				PreparedStatement pstUpd = con.prepareStatement(query2);) {

			pst.executeUpdate();
			pstUpd.executeUpdate();

		} catch (Exception e) {
			System.out.println("ocurrio un error " + e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	public void crear_carpetas(String rutaDigitalizacion) throws Exception {

		String query = "select temp_dir from t_expediente where entregable = '" + Constante.NUMERO_ENTREGABLE + "'";

		try (Connection con = Conexion.getConnection();
				PreparedStatement pst = con.prepareStatement(query);
				ResultSet rstRutas = pst.executeQuery();) {

			while (rstRutas.next()) {

				File dir = new File(rutaDigitalizacion + rstRutas.getString(1));

				boolean isDirectoryCreated = dir.mkdir();

				if (isDirectoryCreated) {
					System.out.println("Directory created successfully");
				} else {
					System.out.println("Directory was not created successfully");
				}
			}
		} catch (Exception e) {
			System.out.println("ocurrio un error " + e.getMessage());
			throw new Exception(e.getMessage());
		}
//
//		String HOST = Constante.HOST_SCPF;
//		Integer PUERTO = Constante.PUERTO_SCPF;
//		String USUARIO = Constante.USUARIO_SCPF;
//		String PASSWORD = Constante.PASSWORD_SCPF;
//
//		Session miSesion1 = null;
//		Channel miCanal12 = null;
//		ChannelExec canalExec = null;
//
//		try {
//			Properties configuracion1 = new Properties();
//			configuracion1.put("StrictHostKeyChecking", "no");
//			JSch jsch1 = new JSch();
//			miSesion1 = jsch1.getSession(USUARIO, HOST, PUERTO);
//			miSesion1.setPassword(PASSWORD);
//			miSesion1.setConfig(configuracion1);
//			miSesion1.connect();
//			miCanal12 = miSesion1.openChannel("exec");
//			canalExec = (ChannelExec) miCanal12;
//
//			System.out.println("nos conectamos");
//
//			canalExec.setCommand("cd " + Constante.DIR_SH + " ; ./crearcarpetaprueba.sh " + Constante.NUMERO_ENTREGABLE
//					+ " \"" + rutaDigitalizacion + "\"");
//
//			System.out.println("cd " + Constante.DIR_SH + " ; ./crearcarpetaprueba.sh " + Constante.NUMERO_ENTREGABLE
//					+ " \"" + rutaDigitalizacion + "\"");
//
//			canalExec.connect();
//
//			while (!canalExec.isClosed()) {
//				System.out.println("esperando termino de ejecucion...");
//				Thread.sleep(5000);
//			}
//
//			// Mostrar resultado del comando
////			InputStream outputstream_from_the_channel = canalExec.getInputStream();
////			BufferedReader br = new BufferedReader(new InputStreamReader(outputstream_from_the_channel));
////			String line = null;
////			StringBuilder sb = new StringBuilder();
////			System.out.println("por terminar");
////			while ((line = br.readLine()) != null) {
////				sb.append(line.trim());
////			}
////			System.out.println("Result =" + sb.toString());
//			canalExec.disconnect();
//
//		} catch (Exception g) {
//			System.out.println("Transferencia Fallida");
//			g.printStackTrace();
//		} finally {
//			if (miSesion1.isConnected())
//				miSesion1.disconnect();
//		}
	}

	public HashMap<String, CargaDatos> obtenerHmCargaDatos(String rutaDigitalizacion) throws Exception {

		String query = "select nombre_pdf, dir, expediente_id, orden_expediente from temp_carga_datos";
		List<CargaDatos> listaCargaDatos = new ArrayList<>();
		CargaDatos cd;

		try (Connection con = Conexion.getConnection();
				PreparedStatement pst = con.prepareStatement(query);
				ResultSet rst = pst.executeQuery();) {

			while (rst.next()) {
				cd = new CargaDatos();
				cd.setNombre_archivo(rst.getString(1));
				cd.setRuta(rutaDigitalizacion + rst.getString(2) + "/");
				cd.setExpediente_id(rst.getInt(3));
				cd.setOrden_expediente(rst.getInt(4));
				listaCargaDatos.add(cd);
			}
		} catch (Exception e) {
			System.out.println("ocurrio un error " + e.getMessage());
			throw new Exception(e.getMessage());
		}

		HashMap<String, CargaDatos> hm_carga_datos = new HashMap<String, CargaDatos>();

		for (CargaDatos exp : listaCargaDatos) {
			hm_carga_datos.put(exp.getNombre_archivo(), exp);
		}

		return hm_carga_datos;
	}

	public void generarExpedienteDocumento(String dirFile, HashMap<String, CargaDatos> hm_carga_datos)
			throws Exception {

		Connection con = null;
		CallableStatement sp = null;
		con = Conexion.getConnection();
		ExpedienteDocumento expDocumento;
		int cantidad = 0;

		File t = new File(dirFile);
		File[] files = t.listFiles();

		System.out.println("cantidad de directorios: " + files.length);

		List<ExpedienteDocumento> listaDocumentos = new ArrayList<>(1000);

		for (File file : files) {

			if (file.isDirectory()) {
				generarExpedienteDocumento(file.getAbsolutePath(), hm_carga_datos);
			} else {
				expDocumento = new ExpedienteDocumento();
				expDocumento.setNombre_archivo(file.getName());
				expDocumento.setTexto(file.getName());
				expDocumento.setFecha_subida(new Date());
				expDocumento.setId_usuario_creacion(522);
				expDocumento.setPeso(file.length());
				expDocumento.setDescripcion_peso(Utils.convertirLongBytes(file.length(), false));
				expDocumento.setEstado_accion("D");
				expDocumento.setEstado(Boolean.TRUE);
				expDocumento.setMedio(Boolean.FALSE);

				String extension = expDocumento.getTexto().substring(expDocumento.getTexto().length() - 4,
						expDocumento.getTexto().length());

				if (extension.equals(".pdf")) {
					expDocumento.setTipo_archivo("PDF");
					expDocumento.setGrupo_formato("PDF");
				} else if (extension.equals(".zip")) {
					expDocumento.setTipo_archivo("ZIP");
					expDocumento.setGrupo_formato("ZIPRAR");
				} else if (extension.equals(".rar")) {
					expDocumento.setTipo_archivo("RAR");
					expDocumento.setGrupo_formato("ZIPRAR");
				}

				if (extension.equals(".pdf") || extension.equals(".PDF")) {
//					if (expDocumento.getPeso().equals(0L)) {
//					System.out.println("CLASIFICAMOS");
					DocumentoPagina dp = new DocumentoPagina();
					dp.setNro_pagina(0);
					dp.setPeso(0L);
					dp.setDescripcion_peso(Utils.convertirLongBytes(0L, false));
					expDocumento.getListaPaginas().add(dp);
					dp.setFlag(Boolean.TRUE);
					expDocumento.setNro_paginas(0);
//					} else {
//						PdfReader pf = new PdfReader(new FileInputStream(file));
//						for (int i = 0; i < pf.getNumberOfPages(); i++) {
//							DocumentoPagina dp = new DocumentoPagina();
//							dp.setNro_pagina(i + 1);
//							dp.setPeso(Long.valueOf(pf.getPageContent(i + 1).length));
//							dp.setDescripcion_peso(
//									Utils.convertirLongBytes(Long.valueOf(pf.getPageContent(i + 1).length), false));
//
//							expDocumento.getListaPaginas().add(dp);
//							dp.setFlag(Boolean.TRUE);
//						}
//
//						expDocumento.setNro_paginas(pf.getNumberOfPages());
//						pf.close();
//					}
				}

				listaDocumentos.add(expDocumento);

				cantidad++;
			}
		}

		for (ExpedienteDocumento expd : listaDocumentos) {
			CargaDatos cdt = null;
			cdt = (CargaDatos) hm_carga_datos.get(expd.getNombre_archivo());

			if (!(cdt == null)) {
				expd.setRuta(cdt.getRuta() + expd.getNombre_archivo());
				expd.setExpediente_id(cdt.getExpediente_id());
				expd.setOrden_expediente(cdt.getOrden_expediente());
			}
		}

		System.out.println("vamos a guardar los expedientes documentos RAR");
		System.out.println(dirFile);

		for (ExpedienteDocumento ex : listaDocumentos) {

			if (ex.getExpediente_id() == null) {
				System.out.println("Nombre archivo: " + ex.getNombre_archivo());
				System.out.println("RUTA: " + ex.getRuta());
			} else {

				System.out.println("Nombre archivo REAL: " + ex.getNombre_archivo());

				sp = con.prepareCall("{call insert_expediente_documento(?,?,?,?,?,?,?,?,?,?,?,?,?)}");

				sp.setInt(1, ex.getExpediente_id());
				sp.setString(2, ex.getNombre_archivo());
				sp.setString(3, ex.getRuta());
				sp.setInt(4, ex.getId_usuario_creacion());
				sp.setLong(5, ex.getPeso());
				sp.setString(6, ex.getDescripcion_peso());
				sp.setInt(7, ex.getNro_paginas());
				sp.setString(8, ex.getEstado_accion());
				sp.setBoolean(9, ex.isEstado());
				sp.setString(10, ex.getTipo_archivo());
				sp.setString(11, ex.getGrupo_formato());
				sp.setBoolean(12, ex.isMedio());
				sp.setInt(13, ex.getOrden_expediente());

				if (!sp.execute()) {
					throw new Exception("Error en el registro" + ex.getExpediente_id());
				}
			}
		}

		System.out.println("vamos a generar el tracking actualizar t_expediente_documento");

		sp = con.prepareCall("{call insert_tracking_expedientes(?)}");

		sp.setString(1, Constante.NUMERO_ENTREGABLE);

		sp.executeQuery();

		sp.close();
		con.close();
	}

	public void generarExpedienteDocumentoRAR(String dirFile, HashMap<String, CargaDatos> hm_carga_datos)
			throws Exception {

		Connection con = null;
		CallableStatement sp = null;
		con = Conexion.getConnection();
		ExpedienteDocumento expDocumento;

		File t = new File(dirFile);
		File[] files = t.listFiles();

		System.out.println("cantidad de archivos: " + files.length);

		int cantidad = 0;

		List<ExpedienteDocumento> listaDocumentos = new ArrayList<>(1000);

		for (File file : files) {

			if (file.isDirectory()) {
				generarExpedienteDocumentoRAR(file.getAbsolutePath(), hm_carga_datos);
			} else {
				expDocumento = new ExpedienteDocumento();
				expDocumento.setNombre_archivo(file.getName());
				expDocumento.setTexto(file.getName());
				expDocumento.setFecha_subida(new Date());
				expDocumento.setId_usuario_creacion(522);
				expDocumento.setPeso(file.length());
				expDocumento.setDescripcion_peso(Utils.convertirLongBytes(file.length(), false));
				expDocumento.setEstado_accion("D");
				expDocumento.setEstado(Boolean.TRUE);
				expDocumento.setMedio(Boolean.FALSE);

				String extension = expDocumento.getTexto().substring(expDocumento.getTexto().length() - 4,
						expDocumento.getTexto().length());

				if (extension.equals(".pdf")) {
					expDocumento.setTipo_archivo("PDF");
					expDocumento.setGrupo_formato("PDF");
				} else if (extension.equals(".PDF")) {
					expDocumento.setTipo_archivo("PDF");
					expDocumento.setGrupo_formato("PDF");
				} else if (extension.equals(".zip")) {
					expDocumento.setTipo_archivo("ZIP");
					expDocumento.setGrupo_formato("ZIPRAR");
				} else if (extension.equals(".rar")) {
					expDocumento.setTipo_archivo("RAR");
					expDocumento.setGrupo_formato("ZIPRAR");
				} else {
					expDocumento.setTipo_archivo("RAR");
					expDocumento.setGrupo_formato("ZIPRAR");
				}

				if (extension.equals(".pdf")) {

					PdfReader pf = new PdfReader(new FileInputStream(file));
					for (int i = 0; i < pf.getNumberOfPages(); i++) {
						DocumentoPagina dp = new DocumentoPagina();
						dp.setNro_pagina(i + 1);
						dp.setPeso(Long.valueOf(pf.getPageContent(i + 1).length));
						dp.setDescripcion_peso(
								Utils.convertirLongBytes(Long.valueOf(pf.getPageContent(i + 1).length), false));
						expDocumento.getListaPaginas().add(dp);
						dp.setFlag(Boolean.TRUE);
					}

					expDocumento.setNro_paginas(pf.getNumberOfPages());
					pf.close();
				}

				listaDocumentos.add(expDocumento);

				cantidad++;
			}
		}

		for (ExpedienteDocumento expd : listaDocumentos) {
			CargaDatos cdt = null;
			cdt = (CargaDatos) hm_carga_datos.get(expd.getNombre_archivo());

			if (!(cdt == null)) {
				expd.setRuta(cdt.getRuta() + expd.getNombre_archivo());
				expd.setExpediente_id(cdt.getExpediente_id());
				expd.setOrden_expediente(cdt.getOrden_expediente());
			}
		}

		System.out.println("vamos a guardar los expedientes documentos RAR");
		System.out.println(dirFile);

		for (ExpedienteDocumento ex : listaDocumentos) {

			sp = con.prepareCall("{call insert_expediente_documento(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			try {
				sp.setInt(1, ex.getExpediente_id());
				sp.setString(2, ex.getNombre_archivo());
				sp.setString(3, ex.getRuta());
				sp.setInt(4, ex.getId_usuario_creacion());
				sp.setLong(5, ex.getPeso());
				sp.setString(6, ex.getDescripcion_peso());
				sp.setInt(7, 0);
				sp.setString(8, ex.getEstado_accion());
				sp.setBoolean(9, ex.isEstado());
				sp.setString(10, ex.getTipo_archivo());
				sp.setString(11, ex.getGrupo_formato());
				sp.setBoolean(12, ex.isMedio());
				sp.setInt(13, ex.getOrden_expediente());

				if (!sp.execute()) {
					throw new Exception("Error en el registro" + ex.getExpediente_id());
				} else {
					System.out.println("Nombre archivo REAL: " + ex.getNombre_archivo());
				}

			} catch (Exception e) {
				System.out.println("fallo carga del " + ex.getNombre_archivo());
			}

		}

		System.out.println("vamos a generar el tracking actualizar t_expediente_documento");

		sp = con.prepareCall("{call insert_tracking_expedientes(?)}");

		sp.setString(1, Constante.NUMERO_ENTREGABLE);

		sp.executeQuery();

		sp.close();
		con.close();
	}

	public void clasificar_archivos(String dirFiles, String rutaDigitalizacion) throws Exception {

		System.out.println("ini clasificar_archivos");
		System.out.println(dirFiles);
		Path origenPath;
		Path destinoPath;
		int cont = 0;
		String query = "";
		ResultSet rs;
		ExpedienteDocumento expdoc;

		File t = new File(dirFiles);
		File[] files = t.listFiles();

		ArrayList<ExpedienteDocumento> listaExpdoc = new ArrayList<ExpedienteDocumento>();

		for (File file : files) {
			cont++;

			if (file.isDirectory()) {
				clasificar_archivos(file.getAbsolutePath(), rutaDigitalizacion);
			} else {
				query = "select ubicacion from t_expediente_documento a" + "  where a.entregable='"
						+ Constante.NUMERO_ENTREGABLE + "' and a.nombre_archivo=? ";

				try (Connection con = Conexion.getConnection(); PreparedStatement pst = con.prepareStatement(query);) {

					pst.setString(1, file.getName());
					rs = pst.executeQuery();

					while (rs.next()) {
						expdoc = new ExpedienteDocumento();
						expdoc.setCarpeta(rs.getString(1));
						expdoc.setNombre_archivo(file.getName());
						expdoc.setPath(file.getPath());
						listaExpdoc.add(expdoc);
					}
					rs.close();

				} catch (Exception e) {
					System.out.println("ocurrio un error " + e.getMessage());
					throw new Exception(e.getMessage());
				}

			}
		}

//		listaExpdoc.parallelStream().forEach((expDocActual) -> {
//			Path origenPath = Paths.get(expDocActual.getPath());
//			Path destinoPath = Paths
//					.get(rutaDigitalizacion + expDocActual.getCarpeta() + "/" + expDocActual.getNombre_archivo());
//		    try {
//		        Files.copy(origenPath, destinoPath, StandardCopyOption.COPY_ATTRIBUTES);
//				System.out.println("fin destino: " + destinoPath);
//		    } catch (IOException e) {
//		        e.printStackTrace();
//		    }
//		});

		for (ExpedienteDocumento expDocActual : listaExpdoc) {
			origenPath = Paths.get(expDocActual.getPath());
			destinoPath = Paths.get(rutaDigitalizacion + expDocActual.getCarpeta() + "/" + expDocActual.getNombre_archivo());
//			System.out.println("origen: " + origenPath);
//			System.out.println("destino: " + destinoPath);
			try {
				Files.copy(origenPath, destinoPath, StandardCopyOption.COPY_ATTRIBUTES);
				System.out.println("fin destino: " + destinoPath);
			} catch (FileAlreadyExistsException e) {
				System.out.println("archivo ya existe");
			}
		}

		System.out.println("fin clasificar_archivos");
	}

	public static void cambiarFecha(Path patch) throws IOException {
		Calendar cal = Calendar.getInstance();

		Map f = Files.readAttributes(patch, "*");
		System.out.println(f);

		Map a = Files.readAttributes(patch, "basic:lastModifiedTime", java.nio.file.LinkOption.NOFOLLOW_LINKS);
		System.out.println(a.get("lastModifiedTime"));

		Files.setAttribute(patch, "basic:creationTime", a.get("lastModifiedTime"),
				java.nio.file.LinkOption.NOFOLLOW_LINKS);
//		Files.setAttribute(patch, "basic:lastModifiedTime", a.get("lastModifiedTime"),
//				java.nio.file.LinkOption.NOFOLLOW_LINKS );
		Files.setAttribute(patch, "basic:lastAccessTime", a.get("lastModifiedTime"),
				java.nio.file.LinkOption.NOFOLLOW_LINKS);

		Map g = Files.readAttributes(patch, "*");
		System.out.println(g);
	}

	public void generarMedios(String formatoEtiqueta) throws ClassNotFoundException {
		int i = 0;
//		Path origenPath;
//		Path destinoPath;
		String etiqueta;
		String grupoFormato = (formatoEtiqueta.equals(Constante.ETIQUETA_ENTREGABLE_PAP)) ? "PDF" : "ZIPRAR";
		String selectIdMedios = "select id_medio from t_expediente_documento where entregable='"
				+ Constante.NUMERO_ENTREGABLE + "' and grupo_formato='" + grupoFormato + "' "
				+ "group by entregable,id_medio order by 1";
		ArrayList<ExpedienteDocumento> medios = new ArrayList<ExpedienteDocumento>();
		ArrayList<ExpedienteDocumento> listaExpdoc = new ArrayList<ExpedienteDocumento>();
		ExpedienteDocumento expDoc;

		try (Connection connPostgres = Conexion.getConnection();
				PreparedStatement pstIdMedios = connPostgres.prepareStatement(selectIdMedios);
				ResultSet rstIdMedios = pstIdMedios.executeQuery();) {

			while (rstIdMedios.next()) {
				i++;
				etiqueta = formatoEtiqueta + String.format("%02d", i);

				expDoc = new ExpedienteDocumento();
				expDoc.setId_medio(rstIdMedios.getInt(1));
				expDoc.setEtiqueta(etiqueta);

				medios.add(expDoc);
			}
		} catch (Exception e) {
			System.err.println(e);
		}

		for (ExpedienteDocumento medio : medios) {
			listaExpdoc = new ArrayList<ExpedienteDocumento>();

			if (formatoEtiqueta.equals(Constante.ETIQUETA_ENTREGABLE_PAP)) {
				crearSQLite(medio.getId_medio(), medio.getEtiqueta());
				copiarEstructura(medio.getEtiqueta(), Constante.RUTA_MEDIOS_PAP, Constante.RUTA_ESTRUCTURA_PAP);
			} else {
				copiarEstructura(medio.getEtiqueta(), Constante.RUTA_MEDIOS_DIG, Constante.RUTA_ESTRUCTURA_DIG);
			}

			String selectDocs = "select ruta,nombre_archivo from t_expediente_documento where id_medio ="
					+ medio.getId_medio();

			try (Connection connPostgres = Conexion.getConnection();
					PreparedStatement pstDocs = connPostgres.prepareStatement(selectDocs);
					ResultSet rstDocs = pstDocs.executeQuery();) {

				while (rstDocs.next()) {

					expDoc = new ExpedienteDocumento();
					expDoc.setPath(rstDocs.getString(1));
					expDoc.setNombre_archivo(rstDocs.getString(2));

					listaExpdoc.add(expDoc);
				}
			} catch (Exception e) {
				System.err.println(e);
			}

			listaExpdoc.parallelStream().forEach((expDocActual) -> {
				Path origenPath = Paths.get(expDocActual.getPath());
				Path destinoPath;
				if (formatoEtiqueta.equals(Constante.ETIQUETA_ENTREGABLE_PAP)) {
					destinoPath = Paths
							.get(Constante.RUTA_MEDIOS_PAP + medio.getEtiqueta() + "/web/" + expDocActual.getNombre_archivo());
				} else {
					destinoPath = Paths
							.get(Constante.RUTA_MEDIOS_DIG + medio.getEtiqueta() + "/web/" + expDocActual.getNombre_archivo());
				}
			    try {
			        Files.copy(origenPath, destinoPath, StandardCopyOption.COPY_ATTRIBUTES);
					System.out.println("fin destino: " + destinoPath);
			    } catch (IOException e) {
			        e.printStackTrace();
			    }
			});
			

//			for (ExpedienteDocumento expDocActual: listaExpdoc)
//			{
//				origenPath = Paths.get(expDocActual.getPath());
//
//				if (formatoEtiqueta.equals(Constante.ETIQUETA_ENTREGABLE_PAP)) {
//					destinoPath = Paths
//							.get(Constante.RUTA_MEDIOS_PAP + medio.getEtiqueta() + "/web/" + expDocActual.getNombre_archivo());
//				} else {
//					destinoPath = Paths
//							.get(Constante.RUTA_MEDIOS_DIG + medio.getEtiqueta() + "/web/" + expDocActual.getNombre_archivo());
//				}
//				
//			    try {
//					System.out.println(destinoPath);
//					Files.copy(origenPath, destinoPath, StandardCopyOption.COPY_ATTRIBUTES);
//			    } catch (IOException e) {
//			        e.printStackTrace();
//			    }
//			}

		}
	}

	public void copiarEstructura(String nombreMedio, String medios, String estructura) {

		String dirPath = medios + nombreMedio;
		File origDir = new File(estructura);
		File destDir = new File(dirPath);

		try {
			Path dirPathObj = Paths.get(dirPath);
			boolean dirExists = Files.exists(dirPathObj);
			if (!dirExists) {
				try {
					Files.createDirectories(dirPathObj);
				} catch (IOException ioExceptionObj) {
					System.out.println(
							"Problem Occured While Creating The Directory Structure= " + ioExceptionObj.getMessage());
				}
			}

			FileUtils.copyDirectory(origDir, destDir);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void crearSQLite(Integer idMedio, String nombreMedio) throws ClassNotFoundException {

		String url = "jdbc:sqlite:" + Constante.RUTA_SQLITE;
		Class.forName("org.sqlite.JDBC");
		List<ExpedienteDocumento> listaDocumentos = new ArrayList<>();

		String selectPostgres = "select  nro_expediente,tipo_de_documento,numero_del_documento, "
				+ "fecha_del_documento,numero_de_imagenes,asunto, "
				+ "datos_del_cliente,sede_oficina,firma_del_fedatario, "
				+ "fecha_aprobacion_fedatario,observacion,nombre_archivo, "
				+ "ruta,ubicacion from t_expediente_documento where id_medio=" + idMedio;

		try (Connection connPostgres = Conexion.getConnection();
				PreparedStatement pstPostgres = connPostgres.prepareStatement(selectPostgres);
				ResultSet rstPostgres = pstPostgres.executeQuery();) {

			while (rstPostgres.next()) {
				ExpedienteDocumento expDocumento = new ExpedienteDocumento();

				expDocumento.setNro_expediente(rstPostgres.getString(1));
				expDocumento.setTipo_documento(rstPostgres.getString(2));
				expDocumento.setNumero_documento(rstPostgres.getString(3));

				expDocumento.setFecha_del_documento(rstPostgres.getString(4));
				expDocumento.setNumero_de_imagenes(rstPostgres.getInt(5));
				expDocumento.setAsunto(rstPostgres.getString(6));

				expDocumento.setDatos_del_cliente(rstPostgres.getString(7));
				expDocumento.setSede_oficina(rstPostgres.getString(8));
				expDocumento.setFirma_del_fedatario(rstPostgres.getString(9));

				expDocumento.setFecha_aprobacion_fedatario(rstPostgres.getString(10));
				expDocumento.setObservacion(rstPostgres.getString(11));
				expDocumento.setNombre_archivo(rstPostgres.getString(12));

				expDocumento.setRuta(nombreMedio);
				expDocumento.setUbicacion(nombreMedio);

				listaDocumentos.add(expDocumento);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		try (Connection connSqlite = DriverManager.getConnection(url); Statement stat = connSqlite.createStatement();) {
			stat.executeUpdate("DELETE FROM t_expediente_documento");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		try (Connection connSqlite = DriverManager.getConnection(url); Statement stat = connSqlite.createStatement();) {
			if (connSqlite != null) {

				String sql = "INSERT INTO t_expediente_documento (nro_expediente,tipo_de_documento,numero_del_documento,"
						+ "fecha_del_documento,numero_de_imagenes,asunto,"
						+ "datos_del_cliente,sede_oficina,firma_del_fedatario,"
						+ "fecha_aprobacion_fedatario,observacion,nombre_archivo,"
						+ "ruta,ubicacion) values (?,?,?, ?,?,?, ?,?,?, ?,?,?, ?,?)";

				connSqlite.setAutoCommit(false);

				PreparedStatement ps = connSqlite.prepareStatement(sql);

				for (ExpedienteDocumento doc : listaDocumentos) {

					ps.clearParameters();

					ps.setString(1, doc.getNro_expediente());
					ps.setString(2, doc.getTipo_documento());
					ps.setString(3, doc.getNumero_documento());

					ps.setString(4, doc.getFecha_del_documento());
					ps.setInt(5, doc.getNumero_de_imagenes());
					ps.setString(6, doc.getAsunto());

					ps.setString(7, doc.getDatos_del_cliente());
					ps.setString(8, doc.getSede_oficina());
					ps.setString(9, doc.getFirma_del_fedatario());

					ps.setString(10, doc.getFecha_aprobacion_fedatario());
					ps.setString(11, doc.getObservacion());
					ps.setString(12, doc.getNombre_archivo());

					ps.setString(13, doc.getRuta());
					ps.setString(14, doc.getUbicacion());
					ps.addBatch();
				}

				ps.executeBatch();
				connSqlite.commit();

				System.out.println("Datos insertados con exito...");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

//	public static void exportarExcel() throws Exception {
//
//		Connection con = null;
//		PreparedStatement pst = null;
//		con = Conexion.getConnection();
//
//		String query = "select * from t_expediente_documento where entregable=? and grupo_formato='PDF'";
//
//		pst = con.prepareStatement(query);
//		pst.setString(1, Constante.NUMERO_ENTREGABLE);
//		ResultSet rs = pst.executeQuery();
//
//		String file_name = "/media/rbullon/DATA/MyFirstExcel.xlsx";
//
//		XSSFWorkbook workbook = new XSSFWorkbook();
//		XSSFSheet sheet = workbook.createSheet("Datatypes in Java");
//		Object[] datatypes = { "Datatype", "Type", "Size(in bytes)" };
//
//		int rowNum = 0;
//		int colNum = 0;
//
//		Row row = sheet.createRow(rowNum++);
//		for (Object field : datatypes) {
//			Cell cell = row.createCell(colNum++);
//			if (field instanceof String) {
//				cell.setCellValue((String) field);
//			} else if (field instanceof Integer) {
//				cell.setCellValue((Integer) field);
//			}
//		}
//
//		while (rs.next()) {
//
//			colNum = 0;
//			row = sheet.createRow(rowNum++);
//
//			for (int x = 1; x <= rs.getMetaData().getColumnCount(); x++) {
//				if (rs.getObject(x) instanceof Integer) {
//					row.createCell(colNum++).setCellValue((Integer) rs.getInt(x));
//				} else if (rs.getObject(x) instanceof String) {
//					row.createCell(colNum++).setCellValue((String) rs.getString(x));
//				} else if (rs.getObject(x) instanceof Date) {
//					row.createCell(colNum++).setCellValue((String) rs.getDate(x).toLocaleString());
//				} else {
//					row.createCell(colNum++).setCellValue((String) rs.getString(x));
//				}
//			}
//		}
//
//		try {
//			FileOutputStream outputStream = new FileOutputStream(file_name);
//			workbook.write(outputStream);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//
//		pst.close();
//		con.close();
//
//	}

//	update t_expediente_documento
//	set 
//	entregable='DECIMOSEPTIMO_ENTREGABLE',
//	zona=a.zona,
//	ubicacion=a.dir,
//	sede_oficina=a.sede,
//	in_process=true,
	// fecha_reg= coalesce(a.fecha_reg,'2020-01-01'),
	// tipo_de_documento=a.tipo_documento,
	// numero_del_documento=a.numero_documento,
	// fecha_del_documento=a.fecha_del_documento,
	// numero_de_imagenes=a.numero_de_imagenes,
	// asunto=a.asunto,
	// datos_del_cliente=a.datos_del_cliente,
	// firma_del_fedatario=a.firma_del_fedatario,
	// fecha_aprobacion_fedatario=a.fecha_aprobacion_fedatario,
	// observacion=a.observacion,
	// nro_expediente=a.nro_expediente
//	from 
//	(
//	select b.*,
//	CASE
//	  WHEN trim(b.sede)='OFICINA REGIONAL LIMA' THEN 'ZONA LIMA'
//	  WHEN trim(b.sede)='OFICINA LIMA NORTE' THEN 'ZONA LIMA'
//	  WHEN trim(b.sede)='OFICINA LIMA SUR' THEN 'ZONA LIMA'
//	  WHEN trim(b.sede)='OFICINA REGIONAL AREQUIPA' THEN 'ZONA SUR'
//	  WHEN trim(b.sede)='OFICINA REGIONAL CUSCO' THEN 'ZONA SUR'
//	  WHEN trim(b.sede)='OFICINA REGIONAL ICA' THEN 'ZONA SUR'
//	  WHEN trim(b.sede)='OFICINA REGIONAL JUNIN' THEN 'ZONA SUR'
//	  WHEN trim(b.sede)='OFICINA REGIONAL LA LIBERTAD' THEN 'ZONA NORTE'
//	  WHEN trim(b.sede)='OFICINA REGIONAL LAMBAYEQUE' THEN 'ZONA NORTE'
//	  WHEN trim(b.sede)='OFICINA REGIONAL LORETO' THEN 'ZONA NORTE'
//	  WHEN trim(b.sede)='OFICINA REGIONAL PIURA' THEN 'ZONA NORTE'
//	  WHEN trim(b.sede)='OFICINA REGIONAL PUNO' THEN 'ZONA SUR'
//	  WHEN trim(b.sede)='OFICINA REGIONAL TACNA' THEN 'ZONA SUR'
//	  WHEN trim(b.sede)='OFICINA REGIONAL UCAYALI' THEN 'ZONA NORTE'
//	  WHEN trim(b.sede)='OFICINA SAN ISIDRO' THEN 'ZONA LIMA'
//	  WHEN trim(b.sede)='TRAMITE DOCUMENTARIO' THEN 'ZONA LIMA'
//	  WHEN trim(b.sede)='DIGITALIZACION LIMA' THEN 'ZONA LIMA'
//	  WHEN trim(b.sede)='DIVISION DE SUPERVISION REGIONAL' THEN 'ZONA LIMA'
//	 END AS zona
//	 from  temp_carga_datos b
//	 ) as a
//
//update t_expediente_documento b set sede_oficina='1 OFICINA REGIONAL LIMA' where trim(b.sede_oficina)='OFICINA REGIONAL LIMA' and entregable='CUARTO_ENTREGABLE';
//update t_expediente_documento b set sede_oficina='2 OFICINA LIMA NORTE' where trim(b.sede_oficina)='OFICINA LIMA NORTE' and entregable='CUARTO_ENTREGABLE';
//update t_expediente_documento b set sede_oficina='3 OFICINA LIMA SUR' where trim(b.sede_oficina)='OFICINA LIMA SUR' and entregable='CUARTO_ENTREGABLE';
//update t_expediente_documento b set sede_oficina='4 OFICINA SAN ISIDRO' where trim(b.sede_oficina)='OFICINA SAN ISIDRO' and entregable='CUARTO_ENTREGABLE';
//update t_expediente_documento b set sede_oficina='5 TRAMITE DOCUMENTARIO' where trim(b.sede_oficina)='TRAMITE DOCUMENTARIO' and entregable='CUARTO_ENTREGABLE';
//update t_expediente_documento b set sede_oficina='5 TRAMITE DOCUMENTARIO' where trim(b.sede_oficina)='DIGITALIZACION LIMA' and entregable='CUARTO_ENTREGABLE';
//update t_expediente_documento b set sede_oficina='1 OFICINA REGIONAL LIMA' where trim(b.sede_oficina)='DIVISION DE SUPERVISION REGIONAL' and entregable='CUARTO_ENTREGABLE';

}

//Para cargar el csv formatear la fecha del csv
//=TEXTO(FECHA(EXTRAEB(E2,7,4),EXTRAEB(E2,4,2),EXTRAEB(E2,1,2)),"yyyy-MM-DD")
//=TEXTO(E2,"yyyy-MM-DD")

//Copiar y Pegar formula para obtener nombre de archivo formateado
//=SUSTITUIR(SUSTITUIR(SUSTITUIR(SUSTITUIR(L2,"_","1"),".","2"),"-","3")," ","4")
//Ordenar el excel mediante este campo

////Ejecutar query y copiar columnas a excel en el mismo orden
//select replace(replace(replace(replace(nombre_archivo,'_','1'),'.','2'),'-','3'),' ','4') , id_medio
//from t_expediente_documento
// where  expediente_id in (select expediente_id
//			from t_expediente 
//			where entregable='DECIMOSEPTIMO_ENTREGABLE'
//			) 
// and grupo_formato != 'PDF' 
// order by 1

//verificar que los nombres de archivo correspondan para ambas columnas generadas ENCONTRAR(A2,D2)

//copiar la columna del medio desde el postgres al excel en el mismo orden 
//ordenar el excel por item para regresar al orden inicial , eliminar columnas auxiliares 

//formatear el medio
//=CONCATENAR("02_IECER_DIG_",CONCATENAR(REPETIR(0,2-LARGO(G2)),G2))
