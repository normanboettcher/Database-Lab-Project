package model.data.users;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import model.data.filme.Film;
import model.data.filme.Produzent;
import model.data.general.Adresse;
import model.database.DatabaseConnection;
import model.database.transfer.SpeicherInDatenbank;
/**
 * Diese Klasse definiert einen Administrator.
 * @author Norman Böttcher
 *
 */
public class Administrator extends AbstractClassUser {
	
	//Konstruktor
	/**
	 * Konstruktor der Klasse Administrator.
	 * 
	 * @param vorname       der Vorname des Administrators.
	 * @param nachname      der Nachname des Administrators.
	 * @param email         die Email des Administrators.
	 * @param geburtstdatum das Geburtsdatum des Administrators.
	 * @param adrs          die Adresse des Administrators.
	 */
	public Administrator(String vorname, String nachname,String email, Date geburtstdatum, Adresse adrs) {
		super(vorname, nachname, email, geburtstdatum, adrs);
		this.rolle = "Administrator";
	}
	
	//public Methoden.
	/**
	 * Methode um eine Rechnung aus der Datenbank zu loeschen.
	 * 
	 * @param id die id der Rechnung die geloescht werden soll.
	 */
	public void loescheRechnung(int id) {
		Connection con = DatabaseConnection.getConnection();
		
		try {
			PreparedStatement stmt_loescheRechnung = con.prepareStatement("delete from rechnungen where rechnungs_id = ?");
			stmt_loescheRechnung.setInt(1, id);
			stmt_loescheRechnung.executeUpdate();
			stmt_loescheRechnung.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Methode zum loeschen eines Kunden aus der Datenbank.
	 * 
	 * @param id der zu loeschende Kunde.
	 */
	public void loescheKunde(String id) {
		loescheUsr(id);
		Connection con = DatabaseConnection.getConnection();
		try {
			String loescheKundeQuery = "delete from kunden where email = ?";
			PreparedStatement loescheKunden = con.prepareStatement(loescheKundeQuery);
			
			loescheKunden.setString(1, id);
			loescheKunden.executeUpdate();
			loescheKunden.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Methode, um einen bestehenden Film bearbeiten zu koennen.
	 * 
	 * @param wert   der Wert, der neu in die Datenbank geschrieben werden soll.
	 * @param spalte die spalte, die in der Datenbank aktualisiert wird.
	 * @param f      der Film der bearbeitet wird.
	 */
	public void bearbeiteFilm(String wert, String spalte, Film f) {
		Connection con = DatabaseConnection.getConnection();
		
		String query = "update filme set column = ? where film_id = ?";
		String[] str = query.split("\\s");
		query = query.replace(str[3], spalte);
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			
			if(spalte.equals("altersbeschraenkung")) {
				int alter = Integer.parseInt(wert);
				stmt.setInt(1, alter);
			} else if(spalte.equals("preis")) {
				double preis = Double.parseDouble(wert);
				stmt.setDouble(1, preis);
			} else if(spalte.equals("produzent"))  {
				updateProduzent(f, wert);
			} else if(spalte.equals("veroeffentlicht")) {
				updateDatum(f, wert);
			} else {
				stmt.setString(1, wert);
			}
			
			stmt.setInt(2, f.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode um einen Kunden bearbeiten zu koennen.
	 * 
	 * @param wert   der Wert, der neu in die Datenbank geschrieben werden soll.
	 * @param spalte die spalte, die in der Datenbank aktualisiert wird.
	 * @param k      der Kunde, fuer den die Aenderung durchgefuert wird.
	 */
	public void bearbeiteKunde(String wert, String spalte, AbstractClassUser k) {
		Connection con = DatabaseConnection.getConnection();
		
		String query = "update kunden set column = ? where email = ?";
		String[] str = query.split("\\s");
		query = query.replace(str[3], spalte);
		updateUser(wert, spalte, k.getEmail());
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, wert);
			stmt.setString(2, k.getEmail());
			stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Methode um einen User zu loeschen. Sobald ein Kunde geloescht wird, wird er
	 * ebenfalls aus der Datenbank User geloescht.
	 * 
	 * @param id die id des Users in Form der Email.
	 */
	private void loescheUsr(String id) {
		Connection con = DatabaseConnection.getConnection();
		try {
			String loescheUserQuery = "delete from users where email = ?";
			PreparedStatement loescheUser = con.prepareStatement(loescheUserQuery);
			
			PreparedStatement stmt_rolle = con.prepareStatement("select rolle from users where email = ?");
			stmt_rolle.setString(1, id);
			ResultSet r = stmt_rolle.executeQuery();
			
			String rolle = "";
			while(r.next()) {
				rolle = r.getString("rolle");
			}
			
			if(rolle.equals("Kunde")) {
				PreparedStatement stmt_kunde = con.prepareStatement("delete from kunden where email = ?");
				stmt_kunde.setString(1, id);
				stmt_kunde.executeUpdate();
				stmt_kunde.close();
			} else {
				PreparedStatement stmt_admin = con.prepareStatement("delete from administratoren where email = ?");
				stmt_admin.setString(1, id);
				stmt_admin.executeUpdate();
				stmt_admin.close();
			}
			
			loescheUser.setString(1, id);
			loescheUser.executeUpdate();
			loescheUser.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Methode um die Bewertung eies Films auf 0 zu setzen. Nur Vorübergehend.
	 * 
	 * @param id die id des Films fuer den die Bewertung geloescht wird.
	 */
	public void loescheBewertung(int id) {
		Connection con = DatabaseConnection.getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement("update filme set bewertung = ?, bewertung_punkte_gesamt = ?, "
					+ "anzahl_bewertungen = ? where film_id = ?");
			stmt.setDouble(1, 0);
			stmt.setDouble(2, 0);
			stmt.setInt(3, 0);
			stmt.setInt(4, id);
			
			stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Methode, um Youtubelink aus der Datenbank zu loeschen.
	 * 
	 * @param id die id des Youtube Links der geloescht werden soll.
	 */
	public void loescheYoutubeLink(int id) {
		Connection con = DatabaseConnection.getConnection();
		try {
			String loescheYtLinkQuery= "delete from streaming_links where youtubelink_id = ?";
			PreparedStatement loescheYtLink = con.prepareStatement(loescheYtLinkQuery);
			
			loescheYtLink.setInt(1, id);
			loescheYtLink.executeUpdate();
			
			PreparedStatement stmt_2 = con.prepareStatement("update filme set youtubelink_id = null"
					+ " where youtubelink_id = ?");
			stmt_2.setInt(1, id);
			stmt_2.executeUpdate();
			stmt_2.close();
			loescheYtLink.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Methode zum loeschen eines Films aus der Datenbank.
	 * 
	 * @param f der Film der geloescht werden soll.
	 */
	public void loescheFilm(Film f) {
		Connection con = DatabaseConnection.getConnection();
		try {
			loescheProduzent(f.getProduzent());
			String loescheFilmQuery= "delete from filme where film_id = ?";
			PreparedStatement loescheFilm = con.prepareStatement(loescheFilmQuery);
			loescheFilm.setInt(1, f.getId());
			loescheFilm.executeUpdate();
			loescheFilm.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Methode um einen Produzenten zu loeschen.
	 * 
	 * @param p der Produzent, der geloescht werden soll.
	 */
	public void loescheProduzent(Produzent p) {
		Connection con = DatabaseConnection.getConnection();
		try {
			String loescheProduzentQuery= "delete from produzenten where produzent_id = ?";
			PreparedStatement loescheProduzent = con.prepareStatement(loescheProduzentQuery);
			
			loescheProduzent.setInt(1, p.getID());
			loescheProduzent.executeUpdate();
			loescheProduzent.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
	}

	/**
	 * Methode um einen Kunden in der Liste zu speichern.
	 * 
	 * @param kunde kunde, der gespeichert werden soll.
	 */
	public void fuegeKundeHinzu(Kunde kunde) {
		SpeicherInDatenbank.speicherKundeInDatenbank(kunde);
	}
	
	/**
	 * Methode zum speichern eines neuen Filmes in der Datenbank.
	 * 
	 * @param film der Film der zur Datenbank hinzugefuegt wird.
	 *
	 */
	public void fuegeFilmHinzu(Film film) {
		SpeicherInDatenbank.speicherFilmInDatenbank(film);
		SpeicherInDatenbank.speicherProduzentInDatenbank(film.getProduzent());
	}
	
	/**
	 * Methode um de Produzenten eines Filmes updaten zu koennen.
	 * @param f der Film der geaedert wird.
	 * @param name der name des neuen Produzenten.
	 */
	private void updateProduzent(Film f, String name) {
		Connection con = DatabaseConnection.getConnection();
		String[] p_name = name.split("\\s");
		
		try {
			int prod_id = 0;
			PreparedStatement stmt_p = con.prepareStatement("select max(produzent_id) as prod_id from produzenten");
			ResultSet r = stmt_p.executeQuery();
			
			while(r.next()) {
				prod_id = r.getInt("prod_id") + 1;
			}
			
			SpeicherInDatenbank.speicherProduzentInDatenbank(new Produzent(prod_id, p_name[0], p_name[1]));
			
			PreparedStatement stmt = con.prepareStatement("update filme set produzent = ?, produzent_id = ? where film_id = ?");
			
			stmt.setString(1, name);
			stmt.setInt(2, prod_id);
			stmt.setInt(3, f.getId());
			stmt.executeUpdate();
			stmt.close();
			stmt_p.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Methode um das Veroeffentlichungsdatum eines Films aender zu koennen.
	 * 
	 * @param f    der Film der geaedert wird.
	 * @param date das neue Datum.
	 */
	@SuppressWarnings("deprecation")
	private void updateDatum(Film f, String date) {
		Connection con = DatabaseConnection.getConnection();
		String[] datum = date.split("\\.");
		Date date_neu = new Date(Integer.parseInt(datum[2]) - 1900, Integer.parseInt(datum[1]), Integer.parseInt(datum[0]));
		try {
			PreparedStatement stmt = con.prepareStatement("update filme set veroeffentlicht = ? where film_id = ?");
			stmt.setDate(1, date_neu);
			stmt.setInt(2, f.getId());
			stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void bearbeiteEigenesKonto(String wert, String spalte) {
		Connection con = DatabaseConnection.getConnection();
		String query = "update administratoren set column = ? where email = ?";
		
		String[] str = query.split("\\s");
		query = query.replace(str[3], spalte);
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			updateUser(wert, spalte, getEmail());
			stmt.setString(1, wert);
			stmt.setString(2, getEmail());
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
