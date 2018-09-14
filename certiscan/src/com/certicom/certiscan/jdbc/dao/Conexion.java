package com.certicom.certiscan.jdbc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Conexion {

	public static Connection getConnection() throws Exception {
		String PATH = "/src/com/certicom/certiscan/propiedades/database.properties";
		Properties properties = new Properties();
		properties.load(Conexion.class.getResourceAsStream(PATH));

		Connection conn = null;

		String url = properties.getProperty("jdbc.url");
		String username = properties.getProperty("jdbc.username");
		String password = properties.getProperty("jdbc.password");
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	public static Connection getConnectionSBS() throws Exception {
		String PATH = "/src/com/certicom/certiscan/propiedades/database.properties";
		Properties properties = new Properties();
		properties.load(Conexion.class.getResourceAsStream(PATH));

		Connection conn = null;

		String url = properties.getProperty("jdbc.urlSBS");
		String username = properties.getProperty("jdbc.usernameSBS");
		String password = properties.getProperty("jdbc.passwordSBS");
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

	public static Connection getConnectionRENIEC() throws Exception {
		String PATH = "/src/com/certicom/certiscan/propiedades/database.properties";
		Properties properties = new Properties();
		properties.load(Conexion.class.getResourceAsStream(PATH));

		Connection conn = null;

		String url = properties.getProperty("jdbc.urlRENIEC");
		String username = properties.getProperty("jdbc.usernameRENIEC");
		String password = properties.getProperty("jdbc.passwordRENIEC");
		Class.forName("org.postgresql.Driver");
		conn = DriverManager.getConnection(url, username, password);
		return conn;
	}

}