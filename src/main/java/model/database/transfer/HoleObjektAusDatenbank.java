package model.database.transfer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.data.bestellungen.Rechnung;
import model.data.filme.Film;
import model.data.filme.Produzent;
import model.data.general.Adresse;
import model.data.users.AbstractClassUser;
import model.data.users.Administrator;
import model.data.users.Kunde;
import model.database.DatabaseConnection;

/**
 * Diese Klasse definiert verschiedene Methoden, um Objekte aus der Datenbank zu
 * holen und java-intern zu speichern.
 * 
 * @author Norman Böttcher
 *
 */
public class HoleObjektAusDatenbank {
	/**
	 * Metode, um alle Rechnung aus der Datenbank zu holen.
	 * 
	 * @return rechnungen Alle Rechnungen aus der Datenbank gespeichert in einer
	 *         Liste.
	 */
	public static ArrayList<Rechnung> holeRechnungenAusDB() {
		ArrayList<Rechnung> rechnungen = new ArrayList<Rechnung>();
		Connection con = DatabaseConnection.getConnection();
		Rechnung rechnung = null;
		try {
			PreparedStatement stmt = con.prepareStatement("select * from rechnungen");
			ResultSet r = stmt.executeQuery();
			
			while(r.next()) {
				rechnung = new Rechnung(r.getInt("rechnungs_id"), new Kunde(r.getString("vorname"), r.getString("nachname"), 
						r.getString("email"), r.getDate("geburtsdatum"), new Adresse(r.getString("strasse"), r.getString("hausnummer"), r.getString("plz"), 
								r.getString("ort"))), r.getDate("rechnungsdatum"));
				rechnung.holeBetrag(r.getDouble("betrag"));
				rechnung.holeZahlungsfrist(r.getInt("zahlungsfrist"));
				rechnung.holeZahlungsmethode(r.getString("zahlmethode"));
				rechnung.holeZahlstatus(r.getString("bezahlstatus"));
				rechnung.setPfad(r.getString("pfad"));
				if(r.getString("gekaufte_filme") != null) {
					rechnung.holeProdukte(r.getString("gekaufte_filme"));
				}
				rechnungen.add(rechnung);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return rechnungen;
	}

	/**
	 * Diese Methode holt alle bestehenden Kunden aus der Datenbank ud speichert
	 * diese in einer Liste.
	 * 
	 * @return kunden alle Kundenobjekte aus der Datenbank.
	 */
	public static ArrayList<Kunde> holeAlleKundenAusDB() {
		ArrayList<Kunde> kunden = new ArrayList<Kunde>();
		Kunde k = null;
		Connection con = DatabaseConnection.getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement("select * from kunden");
			ResultSet r = stmt.executeQuery();
			
			while(r.next()) {
				k = new Kunde(r.getString("vorname"), r.getString("nachname"), r.getString("email"), 
						r.getDate("geburtsdatum"), new Adresse(r.getString("strasse"), r.getString("hausnummer"), r.getString("plz"), r.getString("ort")));
				kunden.add(k);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return kunden;
	}

	/**
	 * Diese Methode holt alle vorhandenen Filme aus der Datenbank und speichert
	 * diese in einer Liste.
	 * 
	 * @return filme die Liste mit den gefundenen Filmen aus der Datenbank.
	 */
	public static ArrayList<Film> holeAlleFilmeAusDB() {
		ArrayList<Film> filme = new ArrayList<Film>();
		
		Connection con = DatabaseConnection.getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement("select * from filme inner join produzenten on filme.produzent_id = produzenten.produzent_id");
			ResultSet r = stmt.executeQuery();
			
			while(r.next()) {
				Film f = new Film(r.getInt("film_id"), r.getString("filmtitel"), r.getDouble("preis"), 
						r.getString("genre"), new Produzent(r.getInt("produzent_id"), r.getString("vorname"), r.getString("nachname")), 
						r.getDate("veroeffentlicht"));
				f.holeTitelbildQuelleAusDB(r.getString("titelbild_quelle"));
				f.holeAltersbeschraenkungAusDB(r.getInt("altersbeschraenkung"));
				f.holeBewertungAusDB(r.getDouble("bewertung"));
				f.holeBeschreibungAusDB(r.getString("beschreibung"));
				filme.add(f);
			}
			r.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
		return filme;
	}

	/**
	 * Methode um einen speziellen Film aus der Datenbank zu holen.
	 * 
	 * @param id die ID des Films der aus der Datenbank geholt wird.
	 * @return f der Film aus der Datenbank.
	 */
	public static Film filmMitIdAusDB(int id) {
		Film f = null;
		Connection con = DatabaseConnection.getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("select * from filme inner join produzenten on "
					+ "filme.produzent_id = produzenten.produzent_id inner join streaming_links on "
					+ "filme.youtubelink_id = streaming_links.youtubelink_id where film_id = ?");
			stmt.setInt(1, id);
			ResultSet r = stmt.executeQuery();
			
			while(r.next()) {
				f = new Film(r.getInt("film_id"), r.getString("filmtitel"), r.getDouble("preis"), r.getString("genre"), 
						new Produzent(r.getInt("produzent_id"), r.getString("vorname"),r.getString("nachname")), r.getDate("veroeffentlicht"));
				f.holeBeschreibungAusDB(r.getString("beschreibung"));
				f.holeYoutubeLinkAusDB(r.getString("quelle"));
				f.holeTitelbildQuelleAusDB(r.getString("titelbild_quelle"));
				f.holeAltersbeschraenkungAusDB(r.getInt("altersbeschraenkung"));
				f.holeBewertungAusDB(r.getDouble("bewertung"));
			}
			r.close();
			stmt.close();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return f;
	}
	
	/**
	 * Diese Methode holt fuer einen bestimmten Kunden die Rechnung aus der
	 * Datenbank.
	 * 
	 * @param k der Kunde fuer den die Rechnung geholt wird.
	 * @return rechnungen eine Liste mit allen Rechnungen des Kunden.
	 */
	public static ArrayList<Rechnung> holeRechnungenVonKunde(Kunde k) {
		ArrayList<Rechnung> rechnungen = new ArrayList<>();
		Connection con = DatabaseConnection.getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement("select * from rechnungen where email = ?");
			stmt.setString(1, k.getEmail());
			ResultSet r = stmt.executeQuery();
			
			while(r.next()) {
				Rechnung re = new Rechnung(r.getInt("rechnungs_id"), k, r.getDate("rechnungsdatum"));
				re.holeBetrag(r.getDouble("betrag"));
				re.holeProdukte(r.getString("gekaufte_filme"));
				re.holeZahlstatus(r.getString("bezahlstatus"));
				re.holeZahlungsfrist(r.getInt("zahlungsfrist"));
				re.holeZahlungsmethode(r.getString("zahlmethode"));
				rechnungen.add(re);
			}
			r.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return rechnungen;
	}

	/**
	 * Mit dieser Methode kann ein User aus der Tabelle users geholt werden,
	 * speziell auf die id, also Email, bezogen. Es wird angegeben, ob es sich bei
	 * dem User um einen Kunden oder Amdinistrator handelt.
	 * 
	 * @param istKunde true or false.
	 * @param email    die email des Users.
	 * @return user als Kunde oder Administrator.
	 */
	public static AbstractClassUser holeUserMitEmail(boolean istKunde, String email) {
		AbstractClassUser user = null;
		Connection con = DatabaseConnection.getConnection();
		try {
			PreparedStatement stmt = null;
			ResultSet r = null;
			if(istKunde) {
				stmt = con.prepareStatement("select * from users where email = ?");
				stmt.setString(1, email);
				r = stmt.executeQuery();
				
				while(r.next()) {
					Kunde k = new Kunde(r.getString("vorname"), r.getString("nachname"), r.getString("email"), 
							r.getDate("geburtsdatum"), new Adresse(r.getString("strasse"), r.getString("hausnummer"), r.getString("plz"),
							r.getString("ort")));
					k.speicherPasswortFuerUserLokal(r.getString("passwort"));
					user = k;
				}
			} else {
				stmt = con.prepareStatement("select * from users where email = ?");
				stmt.setString(1, email);
				r = stmt.executeQuery();
				
				while(r.next()) {
					Administrator a = new Administrator(r.getString("vorname"), r.getString("nachname"), 
							r.getString("email"), r.getDate("geburtsdatum"), new Adresse(r.getString("strasse"), 
									r.getString("hausnummer"), r.getString("plz"), r.getString("ort")));
					a.speicherPasswortFuerUserLokal(r.getString("passwort"));
					user = a;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	/**
	 * Diese Methode holt alle User aus der Datenbank und speichert sie in einer
	 * Liste.
	 * 
	 * @return users die Liste mit allen usern.
	 */
	public static ArrayList<AbstractClassUser> holeAlleUser() {
		ArrayList<AbstractClassUser> users = new ArrayList<AbstractClassUser>();
		Connection con = DatabaseConnection.getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement("select * from users");
			ResultSet r = stmt.executeQuery();
			
		
			while(r.next()) {
				if(r.getString("rolle").equals("Kunde")) {
					Kunde k = new Kunde(r.getString("vorname"), r.getString("nachname"), 
						r.getString("email"), r.getDate("geburtsdatum"), 
						new Adresse(r.getString("strasse"), r.getString("hausnummer"), r.getString("plz"), r.getString("ort")));
					k.speicherPasswortFuerUserLokal(r.getString("passwort"));
					users.add(k);
				} else if(r.getString("rolle").equals("Administrator")) {
					Administrator a = new Administrator(r.getString("vorname"), r.getString("nachname"), 
						r.getString("email"), r.getDate("geburtsdatum"), 
						new Adresse(r.getString("strasse"), r.getString("hausnummer"), r.getString("plz"), r.getString("ort")));
					a.speicherPasswortFuerUserLokal(r.getString("passwort"));
					users.add(a);
					System.out.println(a.getRolle());
				}
			}
			stmt.close();
			r.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return users;
	}
}
