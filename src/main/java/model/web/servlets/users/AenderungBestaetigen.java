package model.web.servlets.users;

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

import model.data.managers.PasswortManager;
import model.data.users.Administrator;
import model.data.users.Kunde;
import model.database.DatabaseConnection;
import model.database.transfer.HoleObjektAusDatenbank;

/**
 * Servlet implementation class AenderungBestaetigen
 */
@WebServlet("/AenderungBestaetigen")
public class AenderungBestaetigen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AenderungBestaetigen() {
        super();
        
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Kunde k = (Kunde) request.getSession().getAttribute("kunde");
		Administrator a = (Administrator) request.getSession().getAttribute("admin");
		
		String vorname = request.getParameter("vorname_neu");
		String nachname = request.getParameter("nachname_neu");
		String strasse = request.getParameter("strasse_neu");
		String hausnummer = request.getParameter("hausnummer_neu");
		String plz = request.getParameter("plz_neu");
		String ort = request.getParameter("ort_neu");
		String pw_alt = null; 
		String pw1_neu = null;
		String pw2_neu = null; 
		
		try {
			pw_alt = PasswortManager.generateHash(request.getParameter("pw_alt"));
			pw1_neu = PasswortManager.generateHash(request.getParameter("pw1_neu"));
			pw2_neu = PasswortManager.generateHash(request.getParameter("pw2_neu"));
		} catch(NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		}
		
		//Attribut zur kontrolle, ob der Admin den User bearbeitet, oder der User sich selbst.
		String userBearbeitet = request.getParameter("userBearbeitet");
		
		//Es bearbeitet ein Kunde sich selbst.
		if(userBearbeitet != null && k != null) {
			try {
				kundeBeareitetSichSelbst(request, k, vorname, nachname, strasse, hausnummer, plz, ort, pw_alt, pw1_neu, pw2_neu);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			request.setAttribute("user", HoleObjektAusDatenbank.holeUserMitEmail(true, k.getEmail()));
			request.setAttribute("alsAdminBearbeiten", "nein");
			request.getRequestDispatcher("kundenkontoBearbeiten.jsp").forward(request, response);
		}
		
		//Es bearbeitet ein Admin sich selbst.
		if(userBearbeitet != null && a != null) {
			try {
				adminBearbeitetSichSelbst(request, a, vorname, nachname, strasse, hausnummer, plz, ort, pw_alt, pw1_neu, pw2_neu);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			request.setAttribute("user", HoleObjektAusDatenbank.holeUserMitEmail(false, a.getEmail()));
			request.setAttribute("alsAdminBearbeiten", "nein");
			request.getRequestDispatcher("kundenkontoBearbeiten.jsp").forward(request, response);
		} 
		
		//Ein Admin bearbeitet einen User.
		if(userBearbeitet == null && a != null ) {
			adminBearbeitetKunde(request, a, vorname, nachname, strasse, hausnummer, plz, ort);
			request.setAttribute("user", HoleObjektAusDatenbank.holeUserMitEmail(true, request.getParameter("kunden_email")));
			request.setAttribute("alsAdminBearbeiten", "ja");
			request.getRequestDispatcher("kundenkontoBearbeiten.jsp").forward(request, response);
		}
		
	}
	/**
	 * Mit dieser Methode kann ein Kunde sich selbst bearbeiten.
	 * 
	 * @param request    die request.
	 * @param k          der Kunde, der sich bearbeitet.
	 * @param vorname    der Vorname aus der Formularabfrage.
	 * @param nachname   der Nachname aus der Formularabfrage.
	 * @param strasse    die Strasse aus der Formularabfrage.
	 * @param hausnummer die Hausnummer aus der Formularabfrage.
	 * @param plz        die Postleitzahl aus der Formularabfrage.
	 * @param ort        der Ort aus der Formularabfrage.
	 * @param pw_alt     das alte Passwort des Kunden.
	 * @param pw1_neu    die erste Eingabe des neuen Passwortes des Kunden.
	 * @param pw2_neu    die zweite Eingabe des neuen Passwortes des Kunden.
	 */
	private void kundeBeareitetSichSelbst(HttpServletRequest request, Kunde k, String vorname, String nachname, String strasse, String hausnummer, String plz, 
			String ort, String pw_alt, String pw1_neu, String pw2_neu) throws NoSuchAlgorithmException {
		//Pruefe welche Aederungen vorgenommen werden soll.
		if(!vorname.equals("")) {
			k.bearbeiteEigenesKonto(vorname, "vorname");
			request.setAttribute("vorname_geaendert", "Der Vorname wurde geaendert");
		}
		
		if(!nachname.equals("")) {
			k.bearbeiteEigenesKonto(nachname, "nachname");
			request.setAttribute("nachname_geaendert", "Der Nachname wurde geaendert");
		}
		
		if(!strasse.equals("")) {
			k.bearbeiteEigenesKonto(strasse, "strasse");
			request.setAttribute("strasse_geaendert", "Die Strasse wurde geaendert");
		}
		
		if(!hausnummer.equals("")) {
			k.bearbeiteEigenesKonto(hausnummer, "hausnummer");
			request.setAttribute("hausnummer_geaendert", "Die Hausnummer wurde geaendert");
		}
		
		if(!plz.equals("")) {
			k.bearbeiteEigenesKonto(plz, "plz");
			request.setAttribute("plz_geaendert", "Die Postleitzahl wurde geaendert");
		}
		
		if(!ort.equals("")) {
			k.bearbeiteEigenesKonto(ort, "ort");
			request.setAttribute("ort_geaendert", "Der Ort wurde geaendert");
		}
		
		if(pw_alt.equals(holePasswortAlt(k.getEmail())) && (pw1_neu.equals(pw2_neu))) {
			k.updatePasswort(pw1_neu);
			request.setAttribute("pw_geaendert", "Passwort erfolgreich geaendert");
		} else {
			request.setAttribute("pw_geaendert", "Passwort konnte nicht geaendert werden.");
		}	
	}

	/**
	 * Mit dieser Methode kann ein Admin sich selbst bearbeiten.
	 * 
	 * @param request    die request.
	 * @param a          der Administrator, der sich bearbeitet.
	 * @param vorname    der Vorname aus der Formularabfrage.
	 * @param nachname   der Nachname aus der Formularabfrage.
	 * @param strasse    die Strasse aus der Formularabfrage.
	 * @param hausnummer die Hausnummer aus der Formularabfrage.
	 * @param plz        die Postleitzahl aus der Formularabfrage.
	 * @param ort        der Ort aus der Formularabfrage.
	 * @param pw_alt     das alte Passwort des Admins.
	 * @param pw1_neu    die erste Eingabe des neuen Passwortes des Admins.
	 * @param pw2_neu    die zweite Eingabe des neuen Passwortes des Admins.
	 */
	private void adminBearbeitetSichSelbst(HttpServletRequest request, Administrator a, String vorname, String nachname, String strasse, String hausnummer, String plz, 
			String ort, String pw_alt, String pw1_neu, String pw2_neu) throws NoSuchAlgorithmException {
		//Pruefe welche Aederungen vorgenommen werden soll.
		if(!vorname.equals("")) {
			a.bearbeiteEigenesKonto(vorname, "vorname");
			request.setAttribute("vorname_geaendert", "Der Vorname wurde geaendert");
		}
		
		if(!nachname.equals("")) {
			a.bearbeiteEigenesKonto(nachname, "nachname");
			request.setAttribute("nachname_geaendert", "Der Nachname wurde geaendert");
		}
		
		if(!strasse.equals("")) {
			a.bearbeiteEigenesKonto(strasse, "strasse");
			request.setAttribute("strasse_geaendert", "Die Strasse wurde geaendert");
		}
		
		if(!hausnummer.equals("")) {
			a.bearbeiteEigenesKonto(hausnummer, "hausnummer");
			request.setAttribute("hausnummer_geaendert", "Die Hausnummer wurde geaendert");
		}
		
		if(!plz.equals("")) {
			a.bearbeiteEigenesKonto(plz, "plz");
			request.setAttribute("plz_geaendert", "Die Postleitzahl wurde geaendert");
		}
		
		if(!ort.equals("")) {
			a.bearbeiteEigenesKonto(ort, "ort");
			request.setAttribute("ort_geaendert", "Der Ort wurde geaendert");
		}
		
		if(pw_alt.equals(holePasswortAlt(a.getEmail())) && (pw1_neu.equals(pw2_neu))) {
			a.updatePasswort(pw1_neu);
			request.setAttribute("pw_geaendert", "Passwort erfolgreich geaendert");
		} else {
			request.setAttribute("pw_geaendert", "Passwort konnte nicht geaendert werden. Oder die zu prüfenden Felder sind leer.");
		}
	}

	/**
	 * Mit dieser Methode kann ein Admin einen Kunden bearbeiten.
	 * 
	 * @param request    die request.
	 * @param a          der Administrator, der den Kunden bearbeiten soll.
	 * @param vorname    der Vorname aus der Formularabfrage.
	 * @param nachname   der Nachname aus der Formularabfrage.
	 * @param strasse    die Strasse aus der Formularabfrage.
	 * @param hausnummer die Hausnummer aus der Formularabfrage.
	 * @param plz        die Postleitzahl aus der Formularabfrage.
	 * @param ort        der Ort aus der Formularabfrage.
	 */
	private void adminBearbeitetKunde(HttpServletRequest request, Administrator a, String vorname, String nachname, String strasse, String hausnummer, String plz, 
			String ort) {
		//Pruefe welche Aederungen vorgenommen werden soll.
		String kundeZuBearbeiten = request.getParameter("kunden_email");
		if(!vorname.equals("")) {
			a.bearbeiteKunde(vorname, "vorname", HoleObjektAusDatenbank.holeUserMitEmail(true, kundeZuBearbeiten));
			request.setAttribute("vorname_geaendert", "Der Vorname wurde geaendert");
		}
		
		if(!nachname.equals("")) {
			a.bearbeiteKunde(nachname, "nachname", HoleObjektAusDatenbank.holeUserMitEmail(true, kundeZuBearbeiten));
			request.setAttribute("nachname_geaendert", "Der Nachname wurde geaendert");
		}
		
		if(!strasse.equals("")) {
			a.bearbeiteKunde(strasse, "strasse", HoleObjektAusDatenbank.holeUserMitEmail(true, kundeZuBearbeiten));
			request.setAttribute("strasse_geaendert", "Die Strasse wurde geaendert");
		}
		
		if(!hausnummer.equals("")) {
			a.bearbeiteKunde(hausnummer, "hausnummer", HoleObjektAusDatenbank.holeUserMitEmail(true, kundeZuBearbeiten));
			request.setAttribute("hausnummer_geaendert", "Die Hausnummer wurde geaendert");
		}
		
		if(!plz.equals("")) {
			a.bearbeiteKunde(plz, "plz", HoleObjektAusDatenbank.holeUserMitEmail(true, kundeZuBearbeiten));
			request.setAttribute("plz_geaendert", "Die Postleitzahl wurde geaendert");
		}
		
		if(!ort.equals("")) {
			a.bearbeiteKunde(ort, "ort", HoleObjektAusDatenbank.holeUserMitEmail(true, kundeZuBearbeiten));
			request.setAttribute("ort_geaendert", "Der Ort wurde geaendert");
		}
	}
	
	private String holePasswortAlt(String email) {
		String str = "";
		Connection con = DatabaseConnection.getConnection();
		
		try {
			PreparedStatement stmt = con.prepareStatement("select passwort from users where email = ?");
			stmt.setString(1, email);
			ResultSet r = stmt.executeQuery();
			
			while(r.next()) {
				str = r.getString("passwort");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return str;
	}
}
