package model.data.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.database.DatabaseConnection;

/**
 * Diese Klasse definiert einen ID-Geerator, der fuer das erstelle der ID's von
 * Ojekten zustaendig ist, die eine ID benoetigen.
 * 
 * @author Norman Böttcher
 *
 */
public class IdGenerator {
	/**
	 * Diese Methode erstellt eine Rechnungs-Id fuer eine neu erstellte Rechnung.
	 * @return die maximale ID aus der Datebank + 1
	 */
	public static int generiereRechnungsID() {
		int id = 0;
		Connection con = DatabaseConnection.getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement("select max(rechnungs_id) as max from rechnungen");
			ResultSet r = stmt.executeQuery();
			
			while(r.next()) {
				id = r.getInt("max");
			}
			stmt.close();
			r.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return id + 1;
	}
	
	/**
	 * Diese Methode generiert eine FilmId fuer den neu erstellten Film.
	 * 
	 * @return die neue ID des neuen Films.Maximale ID aus der Datebank + 1.
	 */
	public static int generiereFilmId() {
		int id = 0;
		Connection con = DatabaseConnection.getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement("select max(film_id) as max_id from filme");
			ResultSet r = stmt.executeQuery();
			
			while(r.next()) {
				id = r.getInt("max_id");
			}
			stmt.close();
			r.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
		return id + 1;
	}
}
