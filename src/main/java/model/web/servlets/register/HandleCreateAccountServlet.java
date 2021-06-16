package model.web.servlets.register;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.data.general.Adresse;
import model.data.managers.DatumsManager;
import model.data.managers.PasswortManager;
import model.data.users.Kunde;
import model.database.DatabaseConnection;
import model.database.transfer.SpeicherInDatenbank;

/**
 * Dieses Servlet behandelt das Register Formular, falls sich ein neuer User
 * registrieren will.
 * 
 * @author Norman Böttcher
 *
 */
@WebServlet("/HandleCreateAccountServlet")
public class HandleCreateAccountServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * doPost Methode zur Abwicklung der Formularbearbeitung.
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
	
		boolean fehler_aufgetreten = false;
		String fehlermeldung_email = "";
		String fehlermeldung_passwort = "";
		
		String email = req.getParameter("email");
		String vorname = req.getParameter("vor_name");
		String nachname = req.getParameter("nach_name");
		String strasse = req.getParameter("strasse");
		String hausnummer = req.getParameter("nr");
		String plz = req.getParameter("plz");
		String ort = req.getParameter("stadt");
		String passwort = null;
		String passwort2 = null;
		try {
			passwort = PasswortManager.generateHash(req.getParameter("passwort"));
			passwort2 = PasswortManager.generateHash(req.getParameter("passwort2"));
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		Date geburtsdatum = DatumsManager.stringDatumZuDate(req.getParameter("geb_tag"));
		
		if(pruefeObEmailVorhanden(email)) {
			fehlermeldung_email = "Die Email-Adresse: '"+ email +"' ist bereits vergeben.";
			fehler_aufgetreten = true;
		}
		
		if(!(passwort.equals(passwort2))) {
			fehlermeldung_passwort = "Die Passwörter stimmen nicht überein";
			fehler_aufgetreten = true;
		}
		
		
		if(fehler_aufgetreten) {
			req.setAttribute("fehlermeldung_email", fehlermeldung_email);
			req.setAttribute("fehlermeldung_passwort", fehlermeldung_passwort);
			
			req.getRequestDispatcher("registerFail.jsp").forward(req, res);
			
		} else {
			Kunde k = new Kunde(vorname, nachname, email, geburtsdatum, new Adresse(strasse, hausnummer, plz, ort));
			k.speicherPasswortFuerUserLokal(passwort);

			SpeicherInDatenbank.speicherKundeInDatenbank(k);
			
			req.getRequestDispatcher("login.jsp").forward(req, res);
		}
	}

	/**
	 * Methode um die Korrektheit der eingegebenen Email zu pruefen. Sofern diese
	 * Email bereits vergeben ist, ist eine Registrierung nicht moeglich.
	 * 
	 * @param email die email, die geprueft wird.
	 * @return true or false.
	 */
	private boolean pruefeObEmailVorhanden(String email) {
		boolean email_vorhanden = false;
		Connection con = DatabaseConnection.getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("select email from users");
			ResultSet r = stmt.executeQuery();
			
			while(r.next() && !email_vorhanden) {
				if(r.getString("email").equals(email)) {
					email_vorhanden = true;
				}
			}
			stmt.close();
			r.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
		return email_vorhanden;
	}
}
