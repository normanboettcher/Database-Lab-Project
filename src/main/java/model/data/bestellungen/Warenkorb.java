package model.data.bestellungen;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.data.filme.Film;
import model.data.filme.Produzent;
import model.data.managers.DoubleManager;
import model.database.DatabaseConnection;

/**
 * Diese Klasse definiert die Eigenschaften eines Warenkorbs.
 * @author Norman Böttcher
 *
 */
public class Warenkorb {
	//Attribute
	private ArrayList<Film> bestellungen;
	private double gesamtpreis;
	
	//Konstruktor
	/**
	 * Konstruktor der Klasse Warenkorb.
	 */
	public Warenkorb() {
		this.bestellungen = new ArrayList<Film>();
	}
	
	/**
	 * Methode, um eine Bestellung zum Kunden hinzuzufuegen. Es kann nur ein Film
	 * zur Bestellung hinzugefuegt werden, der in der Datenbank filme abgespeichert
	 * ist. Der Film wird aus der Datenbank geholt. Ein FilmObjekt wird erzeugt und
	 * dem Warenkorb hinzugefuegt.
	 * 
	 * @param id die Id des Films der zur Bestellung hinzugefuegt wird.
	 */
	public void addBestellung(int id) {
		Connection con = DatabaseConnection.getConnection();
		Film f; //Film, der aus der Datenbank geholt und zur Bestellung hinzugefuegt werden soll.
		try {
			int film_id = 0;
			String filmtitel = "";
			String genre = "";
			String beschreibung = "";
			double bewertung = 0.0;
			double preis = 0.0;
			Date veroeffentlicht = null;
			int altersbeschraenkung = 0;
			int produzent_id = 0;
			Produzent produzent = null;
			
			PreparedStatement stmt_film = con.prepareStatement("select * from filme where film_id = ?");
			stmt_film.setInt(1, id);
			ResultSet r = stmt_film.executeQuery();
			
			while(r.next()) {
				film_id = r.getInt("film_id");
				filmtitel = r.getString("filmtitel");
				genre = r.getString("genre");
				beschreibung = r.getString("beschreibung");
				bewertung = r.getDouble("bewertung");
				preis = r.getDouble("preis");
				veroeffentlicht = r.getDate("veroeffentlicht");
				altersbeschraenkung = r.getInt("altersbeschraenkung");
				produzent_id = r.getInt("produzent_id");
			}
			
			PreparedStatement stmt_prod = con.prepareStatement("select * from produzenten where produzent_id = ?");
			stmt_prod.setInt(1, produzent_id);
			ResultSet r_prod = stmt_prod.executeQuery();
			
			while(r_prod.next()) {
				produzent = new Produzent(r_prod.getInt("produzent_id"), r_prod.getString("vorname"), r_prod.getString("nachname"));
			}
			
			f = new Film(film_id, filmtitel, preis, genre, produzent, veroeffentlicht);
			f.holeBeschreibungAusDB(beschreibung);
			f.holeAltersbeschraenkungAusDB(altersbeschraenkung);
			f.holeBewertungAusDB((bewertung));
			zeigeVollenWarenkorb().add(f);
			
			double betrag = 0;
			for(int i = 0; i < zeigeVollenWarenkorb().size(); i++) {
				betrag += zeigeVollenWarenkorb().get(i).getPreis();
			}
			
			setGesamtpreis(betrag);
			r_prod.close();
			stmt_prod.close();
			stmt_film.close();
			r.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Methode um den gesamtpreis eines Wartenkorbs zu speichern.
	 * 
	 * @param gesamtpreis der errechnete Gesamtpreis.
	 */
	private void setGesamtpreis(double gesamtpreis) {
		this.gesamtpreis = gesamtpreis;
	}

	/**
	 * Methode um den Gesamtpreis des Warenkorbs zu berechnen auf Basis der Liste
	 * mit den Bestellungen.
	 * 
	 * @return gesamtpreis der Gesamtpreis des Warenkorbs.
	 */
	public double getGesamtpreis() {
		return DoubleManager.round(gesamtpreis, 2);
	}

	/**
	 * Methode fuer den Zugriff auf die Liste der Bestellungen eines Warenkorbs.
	 * 
	 * @return bestellungen die Liste mit den bestellungen.
	 */
	public ArrayList<Film> zeigeVollenWarenkorb() {
		return bestellungen;
	}
}
