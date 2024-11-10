package com.accesodatos.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

	private static DBConnection instance;
	private static Connection connection;
	
	private static String JDBC_DRIVER;
	private static String JDBC_URL;
	private static String JDBC_USER;
	private static String JDBC_PASSWORD;
	
	private static final String CONFIG_FILE_PATH = "configs/configuration.properties";
	private static final Properties properties = new Properties();
	
	private void loadProperties() throws IOException {
		try (FileInputStream input = new FileInputStream(CONFIG_FILE_PATH)) {
			
			properties.load(input);
		
			JDBC_DRIVER = properties.getProperty("database.driver", "com.mysql.cj.jdbc.Driver");
			JDBC_URL = properties.getProperty("database.url", "jdbc:mysql://localhost/practicaJDBC?serverTimeZone=UTC");
			JDBC_USER = properties.getProperty("database.user", "usuarioexterno");
			JDBC_PASSWORD = properties.getProperty("database.password", "Toor.toor1");
			
		} catch (IOException e) {
			throw new IOException("Failed to load properties configuration", e);
		}
	}
	
	private DBConnection() throws SQLException {
		try {
			loadProperties();
			Class.forName(JDBC_DRIVER);
			connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
		} catch (ClassNotFoundException e) {
			throw new SQLException("Database driver not found");
		} catch (IOException e) {
		throw new SQLException("Database connection failed");
		}
	}
	
	public static DBConnection getInstance() throws SQLException {
		if (instance == null) {
			instance = new DBConnection();
		}
		return instance;
	}
	
	public Connection getConnection() {
		return connection;
	}	
	
}
