package model.web.servlets.login;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.data.general.Adresse;
import model.data.managers.PasswortManager;
import model.data.users.Administrator;
import model.data.users.Kunde;
import model.database.DatabaseConnection;
/**
 * Dieses Servlet ist fuer die Abwicklung des Log ins verantwortlich.
 * @author Norman Böttcher
 *
 */
@WebServlet ("/LoginServlet")
public class LoginServlet extends HttpServlet {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Post Methode zur Abwicklung der Login Anfrage.
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = null;
		try {
			password = PasswortManager.generateHash(req.getParameter("pw"));
		} catch(NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if(pruefungUsername(username) == false || pruefungPasswort(password, username) == false) {
			
			req.setAttribute("usernameVorhanden", pruefungUsername(username));

			req.setAttribute("passwortKorrekt", pruefungPasswort(password, username));
			req.getRequestDispatcher("login.jsp").forward(req, res);
		} else {
			HttpSession session = req.getSession();
			/*
			 * Wenn Username und Passwort korrekt sind, muss die Rolle dieses Users ueberprueft werden.
			 */
			if(!(userIstAdmin(username))) {
				session.setAttribute("kunde", holeEingeloggtenKunden(username));
				req.getRequestDispatcher("index.jsp").forward(req, res);
			} else {
				session.setAttribute("admin", holeEingeloggtenAdmin(username));
				req.getRequestDispatcher("index.jsp").forward(req, res);
			}
		}
		
	}

	/**
	 * Diese Methode prueft, ob ein korekter username eingegeben wurde.
	 * 
	 * @param username der zu pruefende Username.
	 * @return true or false.
	 */
	private boolean pruefungUsername(String username) {
		boolean usernameVorhanden = false;
		Connection con = DatabaseConnection.getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("select email from users");
			ResultSet r = stmt.executeQuery();
			
			while(r.next() && !(usernameVorhanden)) {
				if(r.getString("email").equals(username)) {
					usernameVorhanden = true;
				}
			}
			r.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return usernameVorhanden;
	}

	/**
	 * Diese Methode prueft das Passwort eines uebergebenen users.
	 * 
	 * @param passwort das zu pruefende Passwort.
	 * @param username der username, fuer den das Passwort geprueft wird.
	 * @return true or false.
	 */
	private boolean pruefungPasswort(String passwort, String username) {
		boolean passwortKorrekt = false;
		Connection con = DatabaseConnection.getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement("select passwort from users where email = ?");
			stmt.setString(1, username);
			ResultSet r = stmt.executeQuery();
			
			while(r.next() && !(passwortKorrekt)) {
				if(r.getString("passwort").equals(passwort)) {
					passwortKorrekt = true;
				}
			}
			stmt.close();
			r.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
		return passwortKorrekt;                                                                                                                              
	}

	/**
	 * Diese Methode holt einen Kunden aus der Datenbank, falls die Anmeldedaten mit
	 * einem Kunden uebereinstimmen.
	 * 
	 * @param username der Username, der sich eingeloggt hat.
	 * @return k der Kunde aus der Datenbank.
	 */
	private Kunde holeEingeloggtenKunden(String username) {
		Kunde k = null;
		Connection con = DatabaseConnection.getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement("select * from kunden where email = ?");
			stmt.setString(1, username);
			ResultSet r = stmt.executeQuery();
			
			while(r.next()) {
				k = new Kunde(r.getString("vorname"), r.getString("nachname"), r.getString("email"), r.getDate("geburtsdatum"), 
						new Adresse(r.getString("strasse"), r.getString("hausnummer"), r.getString("plz"), r.getString("ort")));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} 
		return k;
	}
	/**
	 * Diese Methode holt einen Administrator aus der Datenbank, falls die Anmeldedaten mit einem Admin uebereinstimmen.
	 * 
	 * @param username der Username, der sich eingeloggt hat.
	 * @return a der Admin aus der Datenbank.
	 */
	private Administrator holeEingeloggtenAdmin(String username) {
		Administrator a  = null;
		Connection con = DatabaseConnection.getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement("select * from administratoren where email = ?");
			stmt.setString(1, username);
			ResultSet r = stmt.executeQuery();
			
			while(r.next()) {
				a = new Administrator(r.getString("vorname"), r.getString("nachname"), r.getString("email"), r.getDate("geburtsdatum"), 
						new Adresse(r.getString("strasse"), r.getString("hausnummer"), r.getString("plz"), r.getString("ort")));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} 
		return a;
	}

	/**
	 * Diese Methode prueft, ob die Benutzerdaten des eingeloggten Users mit denen
	 * eines Admins uebereinstimmen oder nicht.
	 * 
	 * @param username der zu pruefende Username.
	 * @return true or false.
	 */
	private boolean userIstAdmin(String username) {
		boolean istAdmin = false;
		Connection con = DatabaseConnection.getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement("select rolle from users where email = ?");
			stmt.setString(1, username);
			ResultSet r = stmt.executeQuery();
			
			while(r.next() && !(istAdmin)) {
				if(r.getString("rolle").equals("Administrator")) {
					istAdmin = true;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} 
		return istAdmin;
	}
}
