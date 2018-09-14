package com.certicom.certiscan.test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.junit.Test;

import src.com.certicom.certiscan.utils.CargaDatos;
import src.com.certicom.certiscan.utils.Utils;

import com.certicom.certiscan.commons.Constante;
import com.certicom.certiscan.commons.Utiles;
import com.certicom.certiscan.domain.DocumentoPagina;
import com.certicom.certiscan.domain.Expediente;
import com.certicom.certiscan.domain.ExpedienteDocumento;
import com.certicom.certiscan.domain.Tarifa;
import com.certicom.certiscan.jdbc.dao.Conexion;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.lowagie.text.pdf.PdfReader;

//clase creada para crear las carpetas desde archivos en  base de datos

public class TestCreateExpedienteFileMe {

	final static Integer ID_USURIO = 522;

	private List<ExpedienteDocumento> listaDocumentos = new ArrayList<>();
	private List<CargaDatos> listaCargaDatos = new ArrayList<>();
	private List<CargaDatos> listaCargaDatosTemp = new ArrayList<>();
	private List<Expediente> listaExpediente = new ArrayList<>();

	@Test
	public void cargaExpedientes() throws Exception {

		System.out.println("inicio colocarExpedienteId/Expediente");
		colocarExpedienteId();
		System.out.println("fin colocarExpedienteId/Expediente");

		System.out.println("inicio create carpetas");
		create();
		System.out.println("fin create carpetas");

		System.out.println("inicio generarExpedienteDocumento");
		generarExpedienteDocumento();
		System.out.println("fin generarExpedienteDocumento");
		//generarExpedienteDocumentoRAR();

		limpiar_tabla_procesados();

		System.out.println("inicio upload");
		upload();
		//uploadRAR();
		System.out.println("fin upload");
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
					+ " " + Constante.RUTA_DIGITILZACION);
			
			canalExec.connect();
			System.out.println("RESULTADO: " + canalExec.getExitStatus());

			// Mostrar resultado del comando
			InputStream outputstream_from_the_channel = canalExec.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(outputstream_from_the_channel));
			String line = null;
			StringBuilder sb = new StringBuilder();
			System.out.println("por terminar");
			while ((line = br.readLine()) != null) {
				sb.append(line.trim());
			}
			System.out.println("Result =" + sb.toString());

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

		System.out.println("cantidad de archivos: " + files.length);

		int cantidad = 0;

		for (File file : files) {

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

		System.out.println("recibiendo carga datos");

		String query = " select  nombre_pdf, dir, expediente_id, orden_expediente from temp_carga_datos ";
		pst = con.prepareStatement(query);

		ResultSet rst = pst.executeQuery();

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
			sp.executeQuery();
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

		for (File file : files) {

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

			// llamo al procedure para el insert
			cantidad++;
			System.out.println("Nro: " + cantidad);
		}

		System.out.println("recibiendo carga datos");

		String query = " select  nombre_pdf, dir, expediente_id, orden_expediente from temp_carga_datos ";
		pst = con.prepareStatement(query);

		ResultSet rst = pst.executeQuery();

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

		for (ExpedienteDocumento ex : listaDocumentos) {

			System.out.println("Nombre archivo REAL: " + ex.getNombre_archivo());

			sp = con.prepareCall("{call insert_expediente_documento(?,?,?,?,?,?,?,?,?,?,?,?,?)}");

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

			sp.executeQuery();

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

	public void upload() throws Exception {

		Connection con = null;
		PreparedStatement pst = null;
		con = Conexion.getConnection();

		File t = new File(Constante.DIR_FILES);
		File[] files = t.listFiles();

		System.out.println(files.length + " Files encontrados");

		Integer i = 0;

		System.out.println("Inicio enviar archivo");
		String HOST = Constante.HOST_SCPF;
		Integer PUERTO = Constante.PUERTO_SCPF;
		String USUARIO = Constante.USUARIO_SCPF;
		String PASSWORD = Constante.PASSWORD_SCPF;

		Session miSesion = null;
		Channel miCanal = null;
		ChannelSftp canalSFTP = null;

		try {
			JSch jsch = new JSch(); // instancia de jsch
			miSesion = jsch.getSession(USUARIO, HOST, PUERTO);// establezco la sesion
			miSesion.setPassword(PASSWORD); // le doy la clave
			Properties configuracion = new Properties(); // nueva instancia de arch de config.
			configuracion.put("StrictHostKeyChecking", "no"); // seteo una propiedad
			miSesion.setConfig(configuracion);// se la paso a la sesion
			System.out.println("iniciando conexion...");
			miSesion.connect();// intento la conexion
			miCanal = miSesion.openChannel("sftp"); // abro un canal sftp
			miCanal.connect();// me conecto
			canalSFTP = (ChannelSftp) miCanal;// casteo

			for (File file : files) {

				i++;
				String query = " select distinct dir from temp_carga_datos where nombre_pdf = ? ";
				pst = con.prepareStatement(query);
				pst.setString(1, file.getName());

				ResultSet rs = pst.executeQuery();
				boolean found = false;
				String dir_temp = "";
				System.out.println("i: " + i);
				System.out.println("Se va a guardar el file :" + file.getName() + " en el dir " + dir_temp);
				while (rs.next()) {

					dir_temp = rs.getString(1);
					System.out.println("RUTA: " + Constante.DIR_FILES + rs.getString(1));
					if (buscar_procesados(file.getName()).equals(0)) {
						Utiles.copiarArchivo(file, file.getName(), Constante.RUTA_DIGITILZACION + rs.getString(1),
								canalSFTP);
					}
					found = true;
				}

				if (found) {
					System.out.println("Se guardo el file :" + file.getName() + " en el dir " + dir_temp);

					if (buscar_procesados(file.getName()).equals(0)) {

						Connection con1 = null;
						PreparedStatement pst1 = null;
						con1 = Conexion.getConnection();

						String query1 = " insert into t_procesados values (?) ";
						pst1 = con1.prepareStatement(query1);
						pst1.setString(1, file.getName());
						pst1.executeUpdate();
						pst.close();
						con1.close();
					}
				} else {
					System.out.println("no se guardo el file : " + file.getName());
					break;
				}

			}
			System.out.println(i + " Files guardados");

		} catch (Exception g) {
			System.out.println("Transferencia Fallida");
			g.printStackTrace();
		} finally {

			// Cerramos el canal y session
			if (canalSFTP.isConnected())
				canalSFTP.disconnect();
			if (miSesion.isConnected())
				miSesion.disconnect();
		}
		System.out.println("Fin enviar archivo");

	}

	public void uploadRAR() throws Exception {

		Connection con = null;
		PreparedStatement pst = null;
		con = Conexion.getConnection();

		File t = new File(Constante.DIR_FILES_RAR);
		File[] files = t.listFiles();

		System.out.println(files.length + " Files encontrados");

		Integer i = 0;

		System.out.println("Inicio enviar archivo");
		String HOST = Constante.HOST_SCPF;
		Integer PUERTO = Constante.PUERTO_SCPF;
		String USUARIO = Constante.USUARIO_SCPF;
		String PASSWORD = Constante.PASSWORD_SCPF;

		Session miSesion = null;
		Channel miCanal = null;
		ChannelSftp canalSFTP = null;

		try {

			JSch jsch = new JSch(); // instancia de jsch
			miSesion = jsch.getSession(USUARIO, HOST, PUERTO);// establezco la sesion
			miSesion.setPassword(PASSWORD); // le doy la clave
			Properties configuracion = new Properties(); // nueva instancia de arch de config.
			configuracion.put("StrictHostKeyChecking", "no"); // seteo una propiedad
			miSesion.setConfig(configuracion);// se la paso a la sesion
			System.out.println("iniciando conexion...");
			miSesion.connect();// intento la conexion
			miCanal = miSesion.openChannel("sftp"); // abro un canal sftp
			miCanal.connect();// me conecto
			canalSFTP = (ChannelSftp) miCanal;// casteo

			for (File file : files) {

				String query = " select distinct dir from temp_carga_datos where nombre_pdf = ? ";
				pst = con.prepareStatement(query);
				pst.setString(1, file.getName());

				ResultSet rs = pst.executeQuery();
				boolean found = false;
				boolean procesado = false;
				String dir_temp = "";
				while (rs.next()) {
					dir_temp = rs.getString(1);
					System.out.println("RUTA: " + Constante.RUTA_DIGITILZACION + rs.getString(1));
					if (buscar_procesados(file.getName()).equals(0)) {

						Utiles.copiarArchivo(file, file.getName(), Constante.RUTA_DIGITILZACION + rs.getString(1),
								canalSFTP);
						procesado = true;
					}
					found = true;
				}
				rs.close();

				if (found) {
					i++;

					System.out.println("Archivos guardados: " + i);
					System.out.println("Se guardo el file :" + file.getName() + " en el dir " + dir_temp);

					// if(buscar_procesados(file.getName()).equals(0)){
					if (procesado) {
						String query1 = " insert into t_procesados values (?) ";
						pst = con.prepareStatement(query1);
						pst.setString(1, file.getName());
						pst.executeUpdate();
					}
				} else {
					System.out.println("Error al guardar el file : " + file.getName());
					break;
				}
				pst.close();
			}
			System.out.println(i + " Files guardados");

		} catch (Exception g) {
			System.out.println("Transferencia Fallida");
			g.printStackTrace();
		} finally {
			// Cerramos el canal y session
			if (canalSFTP.isConnected())
				canalSFTP.disconnect();
			if (miSesion.isConnected())
				miSesion.disconnect();
		}
		con.close();
		pst.close();
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

}
