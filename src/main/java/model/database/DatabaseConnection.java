package model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Diese Klasse organisiert die Verbindung zu einer bestehenden Datenbank.
 * 
 * @author Norman B�ttcher
 *
 */
public class DatabaseConnection {
	 
	static Connection instance;
	
	/**
	 * Infos fuer Serververbindung
	 */
	private static final String DB_SERVER ="207.154.234.136:5432";
	private static final String DB_NAME = "1920-Streaming";
	private static final String DB_USER = "1920-Streaming";
	private static final String DB_PASSWORD = "ee5a5e4eb0f415ca6ba417e5e26620ba";
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_URL = "jdbc:postgresql://" + DB_SERVER + "/" + DB_NAME;
	
	/**
	 * Mit dieser Methode wird eine Verindung zur gegebenen Datebank aufgebaut.
	 * 
	 * @return instance die Connection.
	 */
	private static Connection init() {
		try {
			Class.forName(DB_DRIVER);
			
			instance = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			return instance;
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Es ist ein Fehler aufgetreten.");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Methode um eine Verbindung mit der Datenbank herstellen zu koennen.
	 * 
	 * @return instance eine instance des Typs Connection.
	 */
	public static Connection getConnection() {
		try {
			if (instance == null || instance.isClosed()) {
				init();
			}
			return instance;
		} catch(SQLException e) {
			e.printStackTrace();
			return instance;
		}
	}

	/**
	 * Methode, um die Verbindung zu schliessen.
	 */
	public static void closeConnection() {
		try {
			instance.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
