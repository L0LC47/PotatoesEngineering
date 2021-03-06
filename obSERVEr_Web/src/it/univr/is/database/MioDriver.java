package it.univr.is.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class MioDriver {
	private String sUrlDB;
	private String sUserNameDB;
	private String sPasswordDB;
	private Connection connection;
	/*
	 * private String sDriver; private Properties myProperties = new
	 * Properties(); // Per massimizzare // portabilitÓ, // mantenibilitÓ, riuso
	 * // ...
	 */private static MioDriver instance;

	// Singleton
	private MioDriver() {
		// Caricamento driver
		// TODO Sviluppi futuri, usare file di property
		/*
		 * sDriver = myProperties.getProperty("dbmanager.driver"); try {
		 * InputStream is = new FileInputStream("jdbc.properties");
		 * myProperties.load(is); } catch (Exception e) { e.printStackTrace();
		 * 
		 * } try { Class.forName(sDriver); } catch (ClassNotFoundException e) {
		 * System.out.println("DatabaseManager unable to find Driver class");
		 * System.exit(-1003); }
		 * 
		 * // Creazione connessione
		 * 
		 * sUrlDB = myProperties.getProperty("dbmanager.url"); sUserNameDB =
		 * myProperties.getProperty("dbmanager.user"); sPasswordDB =
		 * myProperties.getProperty("dbmanager.password");
		 */

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		sUrlDB = "jdbc:postgresql://dbserver.scienze.univr.it/dblab73";
		sUserNameDB = "userlab73";
		sPasswordDB = "sessantatreQE";
		try {
			connection = DriverManager.getConnection(sUrlDB, sUserNameDB,
					sPasswordDB);
		} catch (SQLException e) {
			System.out.println("Non riesco a connettermi...");
		}
	}

	public static MioDriver getInstance() {
		return instance == null ? instance = new MioDriver() : instance;
	}

	public ResultSet execute(String query, Object[] params) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(query);

		if (params != null) {
			if (params.length > 0) {
				for (int i = 0; i < (params.length); i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
		}
		ResultSet result = execute(ps);
		// ps.close(); // GUAI, il resultset risulterebbe chiuso
		return result;
	}

	public int update(String query, Object[] params) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(query);
		if (params != null) {
			if (params.length > 0) {
				for (int i = 0; i < (params.length); i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
		}
		int result = update(ps);
		ps.close();
		return result;
	}

	private int update(PreparedStatement ps) throws SQLException {
		int result = -1;
		result = ps.executeUpdate();
		return result;
	}

	private ResultSet execute(PreparedStatement ps) throws SQLException {
		ResultSet result = null;
		result = ps.executeQuery();
		return result;
	}

	public void close() throws SQLException {
		connection.close();
	}
}
