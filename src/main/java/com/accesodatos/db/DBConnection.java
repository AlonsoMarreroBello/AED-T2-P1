package com.accesodatos.db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The `DBConnection` class is a singleton class that manages a database
 * connection using
 * properties loaded from a configuration file.
 */
public class DBConnection {

	private static DBConnection instance;
	private static Connection connection;

	private static String JDBC_DRIVER;
	private static String JDBC_URL;
	private static String JDBC_USER;
	private static String JDBC_PASSWORD;

	private static final String CONFIG_FILE_PATH = "configs/configuration.properties";
	private static final Properties properties = new Properties();

	/**
	 * The `loadProperties` method loads configuration properties from a file and
	 * sets default values for
	 * JDBC driver, URL, username, and password if not specified in the file.
	 */
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

	/**
	 * The function `getInstance` returns a singleton instance of `DBConnection` and
	 * creates a new
	 * instance if the current one is null or closed.
	 * 
	 * @return The `DBConnection` instance is being returned.
	 */
	public static DBConnection getInstance() throws SQLException {
		if (instance == null || instance.getConnection().isClosed()) {
			instance = new DBConnection();
		}
		return instance;
	}

	/**
	 * This function returns a Connection object and may throw a SQLException.
	 * 
	 * @return The method `getConnection()` is returning a `Connection` object.
	 */
	public Connection getConnection() throws SQLException {
		return connection;
	}

}
