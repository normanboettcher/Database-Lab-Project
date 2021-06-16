package model.database.transfer;


import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.data.bestellungen.Rechnung;
import model.data.filme.Film;
import model.data.filme.Produzent;
import model.data.managers.PDFManager;
import model.data.users.AbstractClassUser;
import model.data.users.Administrator;
import model.data.users.Kunde;

import model.database.DatabaseConnection;

/**
 * Diese Klasse definiert Methoden, um Objekte in einer Datenbank ablegen zu
 * koennen.
 * 
 * @author Norman Böttcher
 *
 */
public class SpeicherInDatenbank {
	
	/**
	 * Methode um einen komplett neuen Kunden in der Datenbank abzulegen.
	 * 
	 * @param k der neue Kunde.
	 */
	public static void speicherKundeInDatenbank(Kunde k) {
		speicherUserInDatenbank(k);
		Connection con = DatabaseConnection.getConnection();
		
		try {
			String speicherKundenQuery = "INSERT INTO kunden (email, vorname, nachname,"
					+ "geburtsdatum, strasse, hausnummer, plz, ort, offene_rechnungen, rolle)"
					+ "VALUES "
					+ "(?, ?, ?, ?, ?, ?, ?, ? ,?,?)";
		
			PreparedStatement schreibeKundenInDatenbank = con.prepareStatement(speicherKundenQuery);	
			
			schreibeKundenInDatenbank.setString(1, k.getEmail());
			schreibeKundenInDatenbank.setString(2, k.getVorname());
			schreibeKundenInDatenbank.setString(3, k.getNachname());
			schreibeKundenInDatenbank.setDate(4, k.getGeburtsdatum());
			schreibeKundenInDatenbank.setString(5, k.getAdresse().getStrasse());
			schreibeKundenInDatenbank.setString(6, k.getAdresse().getHausnummer());
			schreibeKundenInDatenbank.setString(7, k.getAdresse().getPLZ());
			schreibeKundenInDatenbank.setString(8, k.getAdresse().getOrt());
			if(k.getRechnung() != null) {
				schreibeKundenInDatenbank.setDouble(9, k.getRechnung().getGesamtBetragRechnung());
			} else {
				schreibeKundenInDatenbank.setDouble(9, 0.0); //Wenn noch keine Rechnung existiert ist Betrag 0.0.
			}
			schreibeKundenInDatenbank.setString(10, k.getRolle());
			schreibeKundenInDatenbank.executeUpdate();
			schreibeKundenInDatenbank.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Methode um einen neuen Film in der Datenbank abzulegen.
	 * 
	 * @param f der neue Film.
	 */
	public static void speicherFilmInDatenbank(Film f) {
		Connection con = DatabaseConnection.getConnection();
		
		try {
			String speicherFilmQuery = "INSERT INTO filme (film_id, filmtitel, genre, beschreibung, produzent_id, produzent, bewertung, preis, "
					+ "altersbeschraenkung, veroeffentlicht)"
					+ "VALUES"
					+ "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement schreibeFilmInDatenbank = con.prepareStatement(speicherFilmQuery);
			schreibeFilmInDatenbank.setInt(1, f.getId());
			schreibeFilmInDatenbank.setString(2, f.filmtitel());
			schreibeFilmInDatenbank.setString(3, f.getGenre());
			schreibeFilmInDatenbank.setString(4, f.printBeschreibung());
			schreibeFilmInDatenbank.setInt(5, f.getProduzent().getID());
			schreibeFilmInDatenbank.setString(6, f.getProduzent().ganzerNameProduzent());
			schreibeFilmInDatenbank.setDouble(7, 0.0); //Jeder Film der NEU hinzu kommt, hat vorerst eine Bewertung von 0.0.
			schreibeFilmInDatenbank.setDouble(8, f.getPreis());
			schreibeFilmInDatenbank.setInt(9, f.getAltersbeschraenkung());
			schreibeFilmInDatenbank.setDate(10, f.veroeffentlichtAm());
			
			schreibeFilmInDatenbank.executeUpdate();
			schreibeFilmInDatenbank.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Methode um eine neue Rechung in der Datenbank abzulegen.
	 * 
	 * @param r die abzulegende Rechnung.
	 */
	public static void speicherRechnungInDatenbank(Rechnung r) {
		PDFManager.speicherRechnungAlsPDF(r);
		Connection con = DatabaseConnection.getConnection();
		try {
			String speicherRechnungQuery = "INSERT INTO rechnungen (rechnungs_id, email, vorname, nachname, geburtsdatum, strasse, hausnummer, plz, ort,"
					+ "betrag, bezahlstatus, zahlmethode, zahlungsfrist, rechnungsdatum, gekaufte_filme, pfad)"
					+ "VALUES"
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			
			PreparedStatement speicherRechnungStmt = con.prepareStatement(speicherRechnungQuery);
			
			speicherRechnungStmt.setInt(1, r.getRechnungsID());
			speicherRechnungStmt.setString(2, r.getEmpfaenger().getEmail());
			speicherRechnungStmt.setString(3, r.getEmpfaenger().getVorname());
			speicherRechnungStmt.setString(4, r.getEmpfaenger().getNachname());
			speicherRechnungStmt.setDate(5, r.getEmpfaenger().getGeburtsdatum());
			speicherRechnungStmt.setString(6, r.getEmpfaenger().getAdresse().getStrasse());
			speicherRechnungStmt.setString(7, r.getEmpfaenger().getAdresse().getHausnummer());
			speicherRechnungStmt.setString(8, r.getEmpfaenger().getAdresse().getPLZ());
			speicherRechnungStmt.setString(9, r.getEmpfaenger().getAdresse().getOrt());
			speicherRechnungStmt.setDouble(10, r.getGesamtBetragRechnung());
			speicherRechnungStmt.setString(11, r.getBezahlstatus());
			speicherRechnungStmt.setString(12, r.getEmpfaenger().getZahlmethode().getZahlmethodeBezeichnung());
			speicherRechnungStmt.setInt(13, r.getZahlungsfrist());
			speicherRechnungStmt.setDate(14, r.getRechnungsdatum());
			speicherRechnungStmt.setString(15, r.getProdukte());
			speicherRechnungStmt.setString(16, r.getPfad());
			
			speicherRechnungStmt.executeUpdate();
			speicherRechnungStmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Methode um einen Produzenten der Datenbank hinzufuegen zu koennen.
	 * 
	 * @param p der Produzent, der hinzugefuegt werden soll.
	 */
	public static void speicherProduzentInDatenbank(Produzent p) {
		Connection con = DatabaseConnection.getConnection();
		try {
			String speicherProduzentQuery = "insert into produzenten (produzent_id, vorname, nachname)"
					+ "values"
					+ "(?,?,?)";
			
			PreparedStatement speicherProduzentStmt = con.prepareStatement(speicherProduzentQuery);
			speicherProduzentStmt.setInt(1, p.getID());
			speicherProduzentStmt.setString(2, p.getVorname());
			speicherProduzentStmt.setString(3, p.getNachname());
			
			speicherProduzentStmt.executeUpdate();
			speicherProduzentStmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Methode um einen admin in der Datenbank zu speichern.
	 * 
	 * @param a der uebergebene Admin, der in die Datenbank eingefuegt wird.
	 */
	public static void speicherAdminInDatenbank(Administrator a) {
		speicherUserInDatenbank(a);
		Connection con = DatabaseConnection.getConnection();
		
		try {
			PreparedStatement stmt_speicherAdmin = con.prepareStatement("insert into administratoren (email, vorname, nachname, geburtsdatum, "
					+ "strasse, hausnummer, plz, ort, rolle)"
					+ "values"
					+ "(?,?,?,?,?,?,?,?,?)");
			stmt_speicherAdmin.setString(1, a.getEmail());
			stmt_speicherAdmin.setString(2, a.getVorname());
			stmt_speicherAdmin.setString(3, a.getNachname());
			stmt_speicherAdmin.setDate(4, a.getGeburtsdatum());
			stmt_speicherAdmin.setString(5, a.getAdresse().getStrasse());
			stmt_speicherAdmin.setString(6, a.getAdresse().getHausnummer());
			stmt_speicherAdmin.setString(7, a.getAdresse().getPLZ());
			stmt_speicherAdmin.setString(8, a.getAdresse().getOrt());
			stmt_speicherAdmin.setString(9, a.getRolle());
			
			stmt_speicherAdmin.executeUpdate();
			stmt_speicherAdmin.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Methode um einen Youtube Link in der Datenbank abzulegen.
	 * 
	 * @param id     jeder Youtube Link bekommt eine ID.
	 * @param ytLink der youtube Link als String.
	 */
	public static void speicherYouTubeLinkInDatenbank(int id, String ytLink) {
		Connection con = DatabaseConnection.getConnection();
		try {
			PreparedStatement stmt_ytLink = con.prepareStatement("insert into streaming_links (youtubelink_id, quelle)"
					+ "values"
					+ "(?,?)");
			stmt_ytLink.setInt(1, id);
			stmt_ytLink.setString(2, ytLink);
			stmt_ytLink.executeUpdate();
			stmt_ytLink.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Methode um einen User in der Datenbank abzulegen. Wird automatisch
	 * ausgefuehrt, falls ein neuer Kunde oder Admin hinzugefuegt wird.
	 * 
	 * @param usr der neue User.
	 */
	private static void speicherUserInDatenbank(AbstractClassUser usr) {
		Connection con = DatabaseConnection.getConnection();
		
		try {
			PreparedStatement stmt_speicherUser = con.prepareStatement("insert into users (email, passwort, vorname, nachname, geburtsdatum, strasse,"
					+ "hausnummer, plz, ort, rolle)"
					+ "values"
					+ "(?,?,?,?,?,?,?,?,?,?)");
			stmt_speicherUser.setString(1, usr.getEmail());
			stmt_speicherUser.setString(2, usr.getPasswort());
			stmt_speicherUser.setString(3, usr.getVorname());
			stmt_speicherUser.setString(4, usr.getNachname());
			stmt_speicherUser.setDate(5, usr.getGeburtsdatum());
			stmt_speicherUser.setString(6, usr.getAdresse().getStrasse());
			stmt_speicherUser.setString(7, usr.getAdresse().getHausnummer());
			stmt_speicherUser.setString(8, usr.getAdresse().getPLZ());
			stmt_speicherUser.setString(9, usr.getAdresse().getOrt());
			stmt_speicherUser.setString(10, usr.getRolle());
			
			stmt_speicherUser.executeUpdate();
			stmt_speicherUser.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
	}
}
