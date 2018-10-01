package com.certicom.certiscan.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.junit.Test;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.domain.DocumentoPagina;
import com.certicom.certiscan.domain.ExpedienteDocumento;
import com.certicom.certiscan.jdbc.dao.Conexion;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.lowagie.text.pdf.PdfReader;

import src.com.certicom.certiscan.utils.CargaDatos;
import src.com.certicom.certiscan.utils.Utils;

//clase creada para crear las carpetas desde archivos en  base de datos

public class TestCreateExpedienteFileMe {

	final static Integer ID_USURIO = 522;

	private List<ExpedienteDocumento> listaDocumentos = new ArrayList<>();
	private List<CargaDatos> listaCargaDatos = new ArrayList<>();

	public void test() {
		
		List<ExpedienteDocumento> listaDocumentoSede = new ArrayList<>();
		ExpedienteDocumento a = new ExpedienteDocumento();
		a.setNombre_archivo("005_abc");
		a.setSede_oficina("SAN ISIDRO");
		listaDocumentoSede.add(a);
		ExpedienteDocumento b = new ExpedienteDocumento();
		b.setNombre_archivo("003_abc");
		b.setSede_oficina("SAN BORJA");
		listaDocumentoSede.add(b);
		ExpedienteDocumento c = new ExpedienteDocumento();
		c.setNombre_archivo("002_abc");
		c.setSede_oficina("SAN ISIDRO");
		listaDocumentoSede.add(c);
		ExpedienteDocumento d = new ExpedienteDocumento();
		d.setNombre_archivo("004_abc");
		d.setSede_oficina("SAN BORJA");
		listaDocumentoSede.add(d);
		ExpedienteDocumento e = new ExpedienteDocumento();
		e.setNombre_archivo("001_abc");
		e.setSede_oficina("SAN ISIDRO");
		listaDocumentoSede.add(e);

		listaDocumentoSede.sort(Comparator.comparing(ExpedienteDocumento::getSede_oficina).thenComparing(ExpedienteDocumento::getNombre_archivo));

		for (ExpedienteDocumento ds : listaDocumentoSede) {
			System.out.println("ordenado");
			System.out.println(ds.getNombre_archivo());
			System.out.println(ds.getSede_oficina());
		}
		
	}

	@Test
	public void cargaExpedientes() throws Exception {

//		carga_temp(Constante.CSV_DIGITAL);
//		System.out.println("inicio colocarExpedienteId/Expediente DIG");
//		colocarExpedienteId();
//		System.out.println("fin colocarExpedienteId/Expediente DIG");
//		System.out.println("inicio create carpetas DIG");
//		create();
//		System.out.println("fin create carpetas DIG");
//		System.out.println("inicio generarExpedienteDocumento DIG");
//		generarExpedienteDocumentoRAR();
//		System.out.println("fin generarExpedienteDocumento DIG");
//		limpiar_tabla_procesados();
//		System.out.println("inicio upload DIG");
//		upload(Constante.DIR_FILES_RAR);
//		System.out.println("fin upload DIG");

		// correr update temp
//
//		carga_temp(Constante.CSV_PAPEL);
//		System.out.println("inicio colocarExpedienteId/Expediente PAP");
//		colocarExpedienteId();
//		System.out.println("fin colocarExpedienteId/Expediente PAP");
//		System.out.println("inicio create carpetas PAP");
//		create();
//		System.out.println("fin create carpetas PAP");
//		
//
//		limpiar_tabla_procesados();
//		System.out.println("inicio upload PAP");
//		upload(Constante.DIR_FILES);
//		System.out.println("fin upload PAP");
//		System.out.println("inicio generarExpedienteDocumento PAP");
//		generarExpedienteDocumento();
//		System.out.println("fin generarExpedienteDocumento PAP");

	}

	public void carga_temp(String rutaCsv) throws Exception {

		Connection con = null;
		PreparedStatement pst = null;
		con = Conexion.getConnection();

		String query = " delete from temp_carga_datos ";
		pst = con.prepareStatement(query);
		pst.executeUpdate();

		String queryCopy = "COPY temp_carga_datos(nro_expediente,nombre_pdf,dir,sede,fecha_reg) FROM '" + rutaCsv
				+ "' DELIMITER '|' CSV HEADER";
		System.out.println(queryCopy);
		pst = con.prepareStatement(queryCopy);
		pst.executeUpdate();

		pst.close();
		con.close();
	}

	public void colocarExpedienteId() throws Exception {

		String query = "insert into t_expediente (temp_dir, nro_expediente, fecha_reg, idusuario, id_estado, entregable) select distinct dir, nro_expediente, now(), 522, 6, '"
				+ Constante.NUMERO_ENTREGABLE
				+ "' from temp_carga_datos where expediente_id is null group by dir, nro_expediente";

		String query2 = "UPDATE temp_carga_datos a SET expediente_id = b.expediente_id FROM t_expediente b WHERE a.nro_expediente = b.nro_expediente; ";

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

	public void create() throws Exception {

		String HOST = Constante.HOST_SCPF;
		Integer PUERTO = Constante.PUERTO_SCPF;
		String USUARIO = Constante.USUARIO_SCPF;
		String PASSWORD = Constante.PASSWORD_SCPF;

		Session miSesion1 = null;
		Channel miCanal12 = null;
		ChannelExec canalExec = null;

		try {
			// Ejecuta shell
			Properties configuracion1 = new Properties();
			configuracion1.put("StrictHostKeyChecking", "no");
			JSch jsch1 = new JSch();
			miSesion1 = jsch1.getSession(USUARIO, HOST, PUERTO);
			miSesion1.setPassword(PASSWORD);
			miSesion1.setConfig(configuracion1);
			System.out.println("iniciando conexion...");
			miSesion1.connect();
			miCanal12 = miSesion1.openChannel("exec");
			canalExec = (ChannelExec) miCanal12;

			System.out.println("nos conectamos");

			canalExec.setCommand("cd " + Constante.DIR_SH + " ; ./crearcarpetaprueba.sh " + Constante.NUMERO_ENTREGABLE
					+ " \"" + Constante.RUTA_DIGITILZACION + "\"");

			System.out.println("cd " + Constante.DIR_SH + " ; ./crearcarpetaprueba.sh " + Constante.NUMERO_ENTREGABLE
					+ " \"" + Constante.RUTA_DIGITILZACION + "\"");
			canalExec.connect();

			// Mostrar resultado del comando
//			InputStream outputstream_from_the_channel = canalExec.getInputStream();
//			BufferedReader br = new BufferedReader(new InputStreamReader(outputstream_from_the_channel));
//			String line = null;
//			StringBuilder sb = new StringBuilder();
//			System.out.println("por terminar");
//			while ((line = br.readLine()) != null) {
//				sb.append(line.trim());
//			}
//			System.out.println("Result =" + sb.toString());
			canalExec.disconnect();

		} catch (Exception g) {
			System.out.println("Transferencia Fallida");
			g.printStackTrace();
		} finally {
			if (miSesion1.isConnected())
				miSesion1.disconnect();
		}
	}

	public void generarExpedienteDocumento() throws Exception {

		Connection con = null;
		PreparedStatement pst = null;
		CallableStatement sp = null;
		con = Conexion.getConnection();
		ExpedienteDocumento expDocumento;

		File t = new File(Constante.DIR_FILES);
		File[] files = t.listFiles();

		System.out.println("cantidad de directorios: " + files.length);

		int cantidad = 0;

		listaDocumentos = new ArrayList<>(2500);

		for (File fileDir : files) {

			if (fileDir.isDirectory()) {
				File subDir = new File(fileDir.getAbsolutePath());
				File[] filesSub = subDir.listFiles();
				for (File file : filesSub) {

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
						if (expDocumento.getPeso().equals(0L)) {
							System.out.println("CLASIFICAMOS");
							DocumentoPagina dp = new DocumentoPagina();
							dp.setNro_pagina(0);
							dp.setPeso(0L);
							dp.setDescripcion_peso(Utils.convertirLongBytes(0L, false));
							expDocumento.getListaPaginas().add(dp);
							dp.setFlag(Boolean.TRUE);
							expDocumento.setNro_paginas(0);
						} else {
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
					}

					listaDocumentos.add(expDocumento);

					cantidad++;
					System.out.println("Nro: " + cantidad);
				}
			}
		}

		System.out.println("recibiendo carga datos");

		String query = " select  nombre_pdf, dir, expediente_id, orden_expediente from temp_carga_datos ";
		pst = con.prepareStatement(query);

		ResultSet rst = pst.executeQuery();

		listaCargaDatos = new ArrayList<>();

		String dir_temp = "";
		while (rst.next()) {
			CargaDatos cd = new CargaDatos();
			cd.setNombre_archivo(rst.getString(1));
			dir_temp = rst.getString(2) + "/";
			cd.setRuta(Constante.RUTA_DIGITILZACION + dir_temp);
			cd.setExpediente_id(rst.getInt(3));
			cd.setOrden_expediente(rst.getInt(4));
			listaCargaDatos.add(cd);
		}

		HashMap<String, CargaDatos> hm_carga_datos = new HashMap<String, CargaDatos>();

		for (CargaDatos exp : this.listaCargaDatos) {
			hm_carga_datos.put(exp.getNombre_archivo(), exp);
		}

		System.out.println("cruzando datos");

		for (ExpedienteDocumento expd : listaDocumentos) {
			CargaDatos cdt = null;
			cdt = (CargaDatos) hm_carga_datos.get(expd.getNombre_archivo());

			if (!(cdt == null)) {
				expd.setRuta(cdt.getRuta() + expd.getNombre_archivo());
				expd.setExpediente_id(cdt.getExpediente_id());
				expd.setOrden_expediente(cdt.getOrden_expediente());
			}
		}

		System.out.println("vamos a guardar los expedientes documentos");

		System.out.println(listaDocumentos.size());
		for (ExpedienteDocumento ex : listaDocumentos) {

			if (ex.getExpediente_id() == null) {
				System.out.println("Nombre archivo: " + ex.getNombre_archivo());
				System.out.println("RUTA: " + ex.getRuta());
			}

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

		System.out.println("vamos a generar el tracking");

		sp = con.prepareCall("{call insert_tracking_expedientes()}");

		sp.executeQuery();

		sp.close();
		pst.close();
		con.close();
	}

	public void generarExpedienteDocumentoRAR() throws Exception {

		Connection con = null;
		PreparedStatement pst = null;
		CallableStatement sp = null;
		con = Conexion.getConnection();
		ExpedienteDocumento expDocumento;

		File t = new File(Constante.DIR_FILES_RAR);
		File[] files = t.listFiles();

		System.out.println("cantidad de archivos: " + files.length);

		int cantidad = 0;

		listaDocumentos = new ArrayList<>();

		for (File fileDir : files) {

			if (fileDir.isDirectory()) {
				File subDir = new File(fileDir.getAbsolutePath());
				File[] filesSub = subDir.listFiles();
				for (File file : filesSub) {

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

					System.out.println(expDocumento.getTexto());
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

					// llamo al procedure para el insert
					cantidad++;
					System.out.println("Nro: " + cantidad);
				}
			}
		}

		System.out.println("recibiendo carga datos");

		String query = " select  nombre_pdf, dir, expediente_id, orden_expediente from temp_carga_datos ";
		pst = con.prepareStatement(query);

		ResultSet rst = pst.executeQuery();

		listaCargaDatos = new ArrayList<>();

		String dir_temp = "";
		while (rst.next()) {
			CargaDatos cd = new CargaDatos();
			cd.setNombre_archivo(rst.getString(1));
			dir_temp = rst.getString(2) + "/";
			cd.setRuta(Constante.RUTA_DIGITILZACION + dir_temp);
			cd.setExpediente_id(rst.getInt(3));
			cd.setOrden_expediente(rst.getInt(4));
			listaCargaDatos.add(cd);
		}

		HashMap<String, CargaDatos> hm_carga_datos = new HashMap<String, CargaDatos>();

		for (CargaDatos exp : this.listaCargaDatos) {
			hm_carga_datos.put(exp.getNombre_archivo(), exp);
		}

		System.out.println("cruzando datos RAR");

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

		System.out.println(listaDocumentos.size());

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

		System.out.println("vamos a generar el tracking");

		sp = con.prepareCall("{call insert_tracking_expedientes()}");

		sp.executeQuery();

	}

	public void limpiar_tabla_procesados() throws Exception {

		Connection con = null;
		PreparedStatement pst = null;
		con = Conexion.getConnection();

		String query = " delete from t_procesados ";
		pst = con.prepareStatement(query);
		pst.executeUpdate();
		pst.close();
		con.close();
	}

	public void upload(String dirFiles) throws Exception {

//		String HOST = Constante.HOST_SCPF;
//		Integer PUERTO = Constante.PUERTO_SCPF;
//		String USUARIO = Constante.USUARIO_SCPF;
//		String PASSWORD = Constante.PASSWORD_SCPF;

		Connection con = null;
		PreparedStatement pst = null;
		con = Conexion.getConnection();

		File t = new File(dirFiles);
		File[] files = t.listFiles();

		System.out.println(files.length + " Files encontrados");

		Integer i = 0;
//
//		Session miSesion1 = null;
//		Channel miCanal12 = null;
//		ChannelExec canalExec = null;
//		
		try {

			String comando = "";
			Process p;
//test1
			// comando = "sudo cp -p -f \"/media/rbullon/TOSHIBA EXT/17firmado
			// digital/BAS/201800114812_10072018_CD_BAS.rar.esig\" \"/media/rbullon/TOSHIBA
			// EXT/ENTREGABLE17/DIGITALIZACION/201800114812_10072018_2442_BAS\"";
//			comando = "sudo cp -p -f /home/rbullon/Sergio/proyectos/osinergmin/17ENTREGABLE/BASEDIGITAL17.csv '/home/rbullon/Sergio/proyectos/osinergmin/'";
//			System.out.println(comando);
//			p = Runtime.getRuntime().exec(comando);
//			p.waitFor();

			// test2
			// Ejecuta shell
//			Properties configuracion1 = new Properties();
//			configuracion1.put("StrictHostKeyChecking", "no");
//			JSch jsch1 = new JSch();
//			miSesion1 = jsch1.getSession(USUARIO, HOST, PUERTO);
//			miSesion1.setPassword(PASSWORD);
//			miSesion1.setConfig(configuracion1);
//			System.out.println("iniciando conexion...");
//			miSesion1.connect();
//			miCanal12 = miSesion1.openChannel("exec");
//			canalExec = (ChannelExec) miCanal12;
//
//			System.out.println("nos conectamos");
//			
//			canalExec.setCommand("cd " + Constante.DIR_SH + " ; ./copiar.sh \"" + "/home/rbullon/Sergio/proyectos/osinergmin/17ENTREGABLE" + "/" + "BASEDIGITAL17.csv"
//					+ "\" \"" + "/home/rbullon/Sergio/proyectos/osinergmin/" + "\"");
//
//			System.out.println("cd " + Constante.DIR_SH + " ; ./copiar.sh \"" + "/home/rbullon/Sergio/proyectos/osinergmin/17ENTREGABLE" + "/" + "BASEDIGITAL17.csv"
//					+ "\" \"" + "/home/rbullon/Sergio/proyectos/osinergmin/" + "\"");
//			
//			canalExec.setCommand("cd " + Constante.DIR_SH + " ; ./copiar.sh \"" + dirFiles + "/" + "BASEDIGITAL17.csv"
//					+ "\" \"" + Constante.DIR_FILES_RAR + "\"");
//
//			System.out.println("cd " + Constante.DIR_SH + " ; ./copiar.sh \"" + dirFiles + "/" + "BASEDIGITAL17.csv"
//					+ "\" \"" + Constante.DIR_FILES_RAR + "\"");
//			canalExec.connect();
//
//			canalExec.disconnect();
			for (File fileDir : files) {

				if (fileDir.isDirectory()) {
					File subDir = new File(fileDir.getAbsolutePath());
					File[] filesSub = subDir.listFiles();
					for (File file : filesSub) {

						String query = " select distinct dir from temp_carga_datos where nombre_pdf = ? ";
						pst = con.prepareStatement(query);
						pst.setString(1, file.getName());

						ResultSet rs = pst.executeQuery();
//						boolean found = false;
//						boolean procesado = false;
						comando = "";

						while (rs.next()) {
//
//					if (buscar_procesados(file.getName()).equals(0)) {

							comando = "sudo cp -p -f \"" + dirFiles + "/" + fileDir.getName() + "/" + file.getName()
									+ "\" \"" + Constante.RUTA_DIGITILZACION + rs.getString(1) + "\"";

							System.out.println(comando);

							p = Runtime.getRuntime().exec(comando);
							p.waitFor();
//					    
//						procesado = true;
//					}
//					found = true;
						}
						rs.close();
//
//				if (found) {
//					i++;
//					// if(buscar_procesados(file.getName()).equals(0)){
//					if (procesado) {
//						String query1 = " insert into t_procesados values (?) ";
//						pst = con.prepareStatement(query1);
//						pst.setString(1, file.getName());
//						pst.executeUpdate();
//					}
//				} else {
//					System.out.println("Error al guardar el file : " + file.getName());
//					break;
//				}
//				pst.close();
					}
				}
			}
			//System.out.println(i + " Files guardados");
		} catch (Exception g) {
			System.out.println("Transferencia Fallida");
			g.printStackTrace();
		}

		con.close();
	}

	public Integer buscar_procesados(String nombre_pdf) throws Exception {

		Connection con = null;
		PreparedStatement pst = null;
		con = Conexion.getConnection();

		String query = " select nombre_pdf from t_procesados where nombre_pdf = ? ";
		pst = con.prepareStatement(query);
		pst.setString(1, nombre_pdf);
		ResultSet rs = pst.executeQuery();

		Integer rows = 0;

		while (rs.next()) {
			rows++;
		}

		pst.close();
		con.close();

		return rows;
	}
//	
//	update t_expediente_documento
//	set 
//	entregable='DECIMOSEPTIMO_ENTREGABLE',
//	zona=a.zona,
//	ubicacion=split_part(ruta,'/',9),
//	sede_oficina=a.sede,
//	in_process=true,
//    fecha_reg= coalesce(a.fecha_reg,'2020-01-01')
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
//	 END AS zona
//	 from  temp_carga_datos b
//	 ) as a
//	 where  a.expediente_id in (select expediente_id
//				from t_expediente 
//				where entregable='DECIMOSEPTIMO_ENTREGABLE'
//				) 
//	and a.nombre_pdf = nombre_archivo
//
//update t_expediente_documento b set sede_oficina='1 OFICINA REGIONAL LIMA' where trim(b.sede_oficina)='OFICINA REGIONAL LIMA';
//update t_expediente_documento b set sede_oficina='2 OFICINA LIMA NORTE' where trim(b.sede_oficina)='OFICINA LIMA NORTE';
//update t_expediente_documento b set sede_oficina='3 OFICINA LIMA SUR' where trim(b.sede_oficina)='OFICINA LIMA SUR';
//update t_expediente_documento b set sede_oficina='4 OFICINA SAN ISIDRO' where trim(b.sede_oficina)='OFICINA SAN ISIDRO';
//update t_expediente_documento b set sede_oficina='5 TRAMITE DOCUMENTARIO' where trim(b.sede_oficina)='TRAMITE DOCUMENTARIO';

}

//=CONCATENAR("17_IECER_PAP_",CONCATENAR(REPETIR(0,2-LARGO(G2)),G2))

//=SUSTITUIR(SUSTITUIR(SUSTITUIR(SUSTITUIR(L2,"_","1"),".","2"),"-","3")," ","4")

//select replace(replace(replace(replace(nombre_archivo,'_','1'),'.','2'),'-','3'),' ','4') , id_medio
//from t_expediente_documento
// where  expediente_id in (select expediente_id
//			from t_expediente 
//			where entregable='DECIMOSEPTIMO_ENTREGABLE'
//			) 
// and grupo_formato != 'PDF' 
// order by 1

//=TEXTO(FECHA(EXTRAEB(F2,7,4),EXTRAEB(F2,4,2),EXTRAEB(F2,1,2)),"yyyy-MM-DD")