package model.data.users;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.data.general.AbstractClassPerson;
import model.data.general.Adresse;
import model.database.DatabaseConnection;

/**
 * Diese Klasse definiert einen abstrakten User. Ein User des Systems kann
 * entweder ein Kunde oder ein Admin sein.
 * 
 * @author Norman Böttcher
 *
 */
public abstract class AbstractClassUser extends AbstractClassPerson {
	//Attribute	
	protected String rolle;
	
	private String email;
	
	private String passwort;
	
	//Konstruktor
	/**
	 * Kosntruktor der Klasse User.
	 * 
	 * @param vorname      der Vorname des Users.
	 * @param nachname     der Nachname eines Users.
	 * @param email        die Email eines Users. Ist gleichzeitig der einmalige
	 *                     Username.
	 * @param geburtsdatum das Geburtsdatum eines Users.
	 * @param adrs         die Adresse eines Users.
	 */
	protected AbstractClassUser(String vorname, String nachname, String email, Date geburtsdatum, Adresse adrs ) {
		super(vorname, nachname, geburtsdatum, adrs);
		this.email = email;
	}
	
	/**
	 * Methode um die Email eines Users auszugeben.
	 * 
	 * @return email die Email des Users.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Methode um das Passwort fuer einen User zu speichern.
	 * Fuer lokale Verwendung in Java Logik.
	 * 
	 * @param passwort das Passwort des Users.
	 */
	public void speicherPasswortFuerUserLokal(String passwort) {
		this.passwort = passwort;
		
	}

	/**
	 * Methode um auf die Rolle eines Users zugfreifen zu koennen.
	 * 
	 * @return rolle die Rolle des Users.
	 */
	public String getRolle() {
		return rolle;
	}

	/**
	 * Methode um auf das Passwort des Users zugreifen zu koennen.
	 * 
	 * @return passwort das Passwort des Users.
	 */
	public String getPasswort() {
		return passwort;
	}

	/**
	 * Diese Methode speichert das Passwort des Users in der Datenbank.
	 * 
	 * @param pw das Passwort, das gespeichert wird.
	 */
	public void updatePasswort(String pw) {
		Connection con = DatabaseConnection.getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement("update users set passwort = ? where email = ?");
			stmt.setString(1, pw);
			stmt.setString(2, getEmail());
			stmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Mit dieser Methode kann ein User sein eigenes Konto bearbeiten. 
	 * Ein Kunde und ein Admin implementieren die Methode dabei jeweils anders.
	 * 
	 * @param wert   der Wert, der neu in die Datenbank gegeben wird.
	 * @param spalte die spalte, die in der Datenbank aktualisiert wird.
	 */
	protected abstract void bearbeiteEigenesKonto(String wert, String spalte); 
	 /**
	  * Mit dieser Methode wird ein User aus der User-Datenbank geupdatet.
	  * Diese Methode wird automatisch ausgefuehrt, sollten aenderungen an einem
	  * Kunden oder Admin vorgenommen werden.
	  * Verantwortlich fuer Synchronisation der User-Tabelle nach Aenderung eines 
	  * Kunde oder Admins.
	  * 
	  * @param value der Wert, der neu in die Datenbank geschrieben wird.
	  * @param column die spalte, die in der Datenbank geaendert wird.
	  * @param id die id des Users, der geaendert wird (email aus Methode {@link #getEmail()})
	  */
	protected void updateUser(String value, String column, String id) {
		Connection con = DatabaseConnection.getConnection();
		
		String query = "update users set column = ? where email = ?";
		String[] str = query.split("\\s");
		query = query.replace(str[3], column);
		try {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, value);
			stmt.setString(2, id);
			stmt.executeUpdate();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
