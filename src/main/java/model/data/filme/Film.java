package model.data.filme;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.data.managers.DoubleManager;
import model.database.DatabaseConnection;
import model.database.transfer.SpeicherInDatenbank;

/**
 * Diese Klasse definiert die Eigenschaften eines Films.
 * 
 * @author Norman Böttcher
 *
 */
public class Film implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Attribute
	private int id;
	private String titel;
	private double preis;
	private String genre;
	private String beschreibung;
	private Date veroeffentlicht;
	private Produzent produzent;
	private String youtubeLinkTrailer;
	private int altersbeschraenkung;
	private String titelbild_quelle;

	private double bewertung; // Rating 1-5.

	

	// Konstruktor
	/**
	 * Konstruktor der Klasse Film.
	 * 
	 * @param id              id des Films.
	 * @param titel           der Filmtitel.
	 * @param preis           der Preis des Films.
	 * @param genre           das Genre des Films.
	 * @param produzent       der Produzent eines Films.
	 * @param veroeffentlicht das Datum, an dem der Film erschien.
	 */
	public Film(int id, String titel, double preis, String genre, Produzent produzent, Date veroeffentlicht) {
		this.id = id;
		this.titel = titel;
		this.preis = preis;
		this.genre = genre;
		this.produzent = produzent;
		this.veroeffentlicht = veroeffentlicht;
	}

	// public Methoden
	/**
	 * Methode zum Ausgeben des Filmtitels.
	 * 
	 * @return titel der Filmtitel.
	 */
	public String filmtitel() {
		return titel;
	}

	/**
	 * Methode um eine Altersbeschraenkung festzulegen.
	 * 
	 * @param beschraenkung die Altersbeschraenkung.
	 */
	public void setAltersbeschraenkung(int beschraenkung) {
		this.altersbeschraenkung = beschraenkung;

		Connection con = DatabaseConnection.getConnection();
		try {
			String setAlterQuery = "update filme set altersbeschraenkung = ? where film_id = ?";
			PreparedStatement setAlterStmt = con.prepareStatement(setAlterQuery);

			setAlterStmt.setInt(1, getAltersbeschraenkung());
			setAlterStmt.setInt(2, getId());

			setAlterStmt.executeUpdate();
			setAlterStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Methode um den Pfad des Titelbildes in der Dateank zu speichern.
	 * 
	 * @param quelle der Pfad des Titelbildes.
	 */
	public void setTitelbildQuelle(String quelle) {

		Connection con = DatabaseConnection.getConnection();

		try {
			PreparedStatement stmt = con.prepareStatement("update filme set titelbild_quelle = ? where film_id = ?");
			stmt.setString(1, quelle);
			stmt.setInt(2, getId());
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Diese Methode holt den Pfad des Titelbildes aus der Datenbank und weist diese
	 * einem Filmobjekt zu.
	 * 
	 * @param quelle der Pfad des Titelbildes.
	 */
	public void holeTitelbildQuelleAusDB(String quelle) {
		this.titelbild_quelle = quelle;
	}

	/**
	 * Diese Methode holt die Altersbeschraenkung aus der Datenbank ud weist diese
	 * einem Filmojekt zu.
	 * 
	 * @param alter die altersbeschraenkung aus der Datenbank.
	 */
	public void holeAltersbeschraenkungAusDB(int alter) {
		this.altersbeschraenkung = alter;
	}
	
	/**
	 * Methode fuer de Zugriff auf den Pfad des Titelbildes.
	 * 
	 * @return titelbild_quelle der Pfad des Titelbildes.
	 */
	public String getTitelbildQuelle() {
		return titelbild_quelle;
	}

	/**
	 * Methode fuer den Zugriff auf die Altersbeschraenkung des Films.
	 * 
	 * @return altersbeschraenkung die Altersbeschraenkung eines Films.
	 */
	public int getAltersbeschraenkung() {
		return altersbeschraenkung;
	}

	/**
	 * Methode zum Ausgeben des PReises eines Films.
	 * 
	 * @return preis der Preis des Films. Angabe bis auf zwei Nachkommastellen
	 */
	public double getPreis() {
		return preis;
	}

	/**
	 * Methode um die Beschreibung des Films auszugeben.
	 * 
	 * @return beschreibung die Beschreibung des Films.
	 */
	public String printBeschreibung() {
		return beschreibung;
	}

	/**
	 * Methode fuer den Zugriff auf die ID des Films.
	 * 
	 * @return id die ID des Films.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Methode fuer den Zugriff auf das Genre eines Films.
	 * 
	 * @return genre das Genre des Films.
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Methode fuer Zugriff auf den Produzenten des Films.
	 * 
	 * @return produzent der Produzent.
	 */
	public Produzent getProduzent() {
		return produzent;
	}

	/**
	 * Methode fuer Zugriff auf das Datum der Veroeffentlichung des Films.
	 * 
	 * @return veroeffentlicht das Datum des Films.
	 */
	public Date veroeffentlichtAm() {
		return veroeffentlicht;
	}

	/**
	 * Methode fuer den Zugriff auf die Bewertung eines Films.
	 * 
	 * @return bewertung die Bewertung des Films.
	 */
	public double getBewertung() {
		return bewertung;
	}

	/**
	 * Methode fuer den Zugriff auf den Youtube Link eines Films.
	 * 
	 * @return youtubeLinkTrailer der Trailer des Films ueber die Plattform youtube.
	 */
	public String getYoutubeLink() {
		return youtubeLinkTrailer;
	}

	/**
	 * Methode um den YoutTube Link fuer einen Film zu aktivieren.
	 * 
	 * @param link der youtube link des filmes.
	 */
	public void aktiviereYoutubeLink(String link) {
		this.youtubeLinkTrailer = link;
		Connection con = DatabaseConnection.getConnection();
		try {

			PreparedStatement stmt_holeMaxId = con.prepareStatement("select max(youtubelink_id) as maximaleID from streaming_links");
			ResultSet r_maxId = stmt_holeMaxId.executeQuery();
			int id = 0;

			while (r_maxId.next()) {
				id = r_maxId.getInt("maximaleID") + 1; // Zur aktuellen maximalen ID wird eine id drauf gerechnet.
			}
			SpeicherInDatenbank.speicherYouTubeLinkInDatenbank(id, getYoutubeLink());

			PreparedStatement stmt = con.prepareStatement("update filme set youtubelink_id = ? where film_id = ?");
			stmt.setInt(1, id);
			stmt.setInt(2, getId());
			stmt.executeUpdate();
			stmt.close();
			r_maxId.close();
			stmt_holeMaxId.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Methode zum Speichern der Bewertung eines Films.
	 * 
	 * @param bewertung die Bewertung.
	 */
	public void speicherBewertung(double bewertung) {

		/*
		 * Wenn eine Bewertung abgegeben wurde und die neue durchscnnittliche Bewertung
		 * berechnet ist, dann wird diese sofort in der Datenbank fuer den jeweiligen
		 * Film abgelegt.
		 */
		Connection con = DatabaseConnection.getConnection();

		try {
			PreparedStatement stmt = con.prepareStatement("select bewertung_punkte_gesamt, anzahl_bewertungen from filme where film_id = ?");
			stmt.setInt(1, getId());
			ResultSet r = stmt.executeQuery();

			double bewertung_alt = 0;
			int anzahl_bewertungen_alt = 0;
			/*
			 * Es wird zuerst der alte Wert der Gesamtpunkte aus der Datebank geholt.
			 * Auf diesen Wert wird die neue Bewertung aufaddiert.
			 * Nach dieser Addition wird die Gesamtpunktzahl aktualisiert.
			 * Die aktualisierte Punktzahl wird ausgelesen sowie die neue Anzahl der Bewertungen.
			 */
			while (r.next()) {
				bewertung_alt = r.getDouble("bewertung_punkte_gesamt");
				anzahl_bewertungen_alt = r.getInt("anzahl_bewertungen");
			}

			String speicherBewertungQuery = "update filme set bewertung_punkte_gesamt = ?, anzahl_bewertungen = ? where film_id = ?";
			PreparedStatement updateFilmBewertungPunktzahl = con.prepareStatement(speicherBewertungQuery);

			updateFilmBewertungPunktzahl.setDouble(1, bewertung_alt + bewertung);
			updateFilmBewertungPunktzahl.setInt(2, anzahl_bewertungen_alt + 1);
			updateFilmBewertungPunktzahl.setInt(3, getId());
			updateFilmBewertungPunktzahl.executeUpdate();
			
			PreparedStatement stmt_bewertung = con.prepareStatement("select bewertung_punkte_gesamt, anzahl_bewertungen from filme where film_id = ?");
			stmt_bewertung.setInt(1, getId());
			ResultSet rset = stmt_bewertung.executeQuery();
			
			while(rset.next()) {
				this.bewertung = DoubleManager.round((rset.getDouble("bewertung_punkte_gesamt")/rset.getDouble("anzahl_bewertungen")), 2);
			}
			
			PreparedStatement stmt_update = con.prepareStatement("update filme set bewertung = ? where film_id = ?");
			stmt_update.setDouble(1, getBewertung());
			stmt_update.setInt(2, getId());
			stmt_update.executeUpdate();
			stmt_update.close();
			rset.close();
			updateFilmBewertungPunktzahl.close();
			stmt_bewertung.close();
			r.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Diese Methode ist fuer die Java-interne verarbeitung der Bewertung
	 * zustaendig. Es wird fuer ein Film-Objekt die aktuelle Bewertung aus der
	 * Datenbank gespeichert, um diese ueber die getBewertung() Methode zugreifbar
	 * zu machen.
	 * 
	 * @param bew die bewertung aus der Datenbank.
	 */
	public void holeBewertungAusDB(double bew) {
		this.bewertung = bew;
	}

	/**
	 * Diese Methode wird verwendet, um einen link aus der Datenbank auf der Java
	 * Ebene einem Film zuordnen zu koennen, ohne dabei auf die Datenbank zugreifen
	 * zu müsse wie in 'aktiviereYoutubeLink' der Fall.
	 * 
	 * @param link der youtube-link des Filmes für java-interne Verarbeitung.
	 */
	public void holeYoutubeLinkAusDB(String link) {
		this.youtubeLinkTrailer = link;
	}

	/**
	 * Diese Methode wird verwendet, um eine Beschreibung aus der Datenbank auf der
	 * Java Ebene einem Film zuordnen zu koennen, ohne dabei auf die Datenbank
	 * zugreifen zu müsse wie in 'speicherBeschreibung' der Fall.
	 * 
	 * @param b die beschriebung des Filmes für java-interne Verarbeitung.
	 */
	public void holeBeschreibungAusDB(String b) {
		this.beschreibung = b;
	}

	/**
	 * Methode um die Beschriebung fuer einen Film zu speichern.
	 * 
	 * @param beschreibung die Beschreibung des Films.
	 */
	public void speicherBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;

		Connection con = DatabaseConnection.getConnection();

		try {
			String updateBeschreibungQuery = "UPDATE filme SET beschreibung = ? WHERE film_id = ?";
			PreparedStatement updateBeschreibung = con.prepareStatement(updateBeschreibungQuery);

			updateBeschreibung.setString(1, printBeschreibung());
			updateBeschreibung.setInt(2, getId());

			updateBeschreibung.executeUpdate();
			updateBeschreibung.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
