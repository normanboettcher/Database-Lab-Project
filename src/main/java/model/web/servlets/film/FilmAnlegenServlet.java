package model.web.servlets.film;

import java.io.IOException;
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

import model.data.filme.Film;
import model.data.filme.Produzent;
import model.data.managers.DatumsManager;
import model.data.managers.IdGenerator;
import model.data.users.Administrator;
import model.database.DatabaseConnection;

/**
 * Servlet implementation class FilmAnlegenServlet
 */
@WebServlet("/FilmAnlegen")
public class FilmAnlegenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilmAnlegenServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Administrator admin = (Administrator) request.getSession().getAttribute("admin");
		
		String filmtitel = request.getParameter("titel");
		String genre = request.getParameter("genre");
		String beschreibung = request.getParameter("beschreibung"); 
		String film_preis = request.getParameter("preis");
		String produzent = request.getParameter("produzent");
		String veroeffentlicht_string = request.getParameter("datum");
		String altersbeschr_string =request.getParameter("alter");
		String yt_link = request.getParameter("yt_link");
		String bild = request.getParameter("bild");
		
		Date date = DatumsManager.stringDatumZuDate(veroeffentlicht_string);
		double preis = Double.parseDouble(film_preis);
	
		int altersbeschr = Integer.parseInt(altersbeschr_string);
		
		Film f = new Film(IdGenerator.generiereFilmId(), filmtitel, preis, genre, erstelleProduzent(produzent), date);
		
		 f.setAltersbeschraenkung(altersbeschr);  
		 f.speicherBeschreibung(beschreibung);
		 admin.fuegeFilmHinzu(f);
		 f.setTitelbildQuelle(aufbereitungTitelbildPfad(bild));
		 /*
		  * Der Youtube link wird aktiviert, NACHDEM der Film in der Datenbank abeglegt wurde.
		  */
		 f.aktiviereYoutubeLink(yt_link);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}
	
	/**
	 * Diese Methode erstellt einen Produzenten auf Basis eines uebergebenen Namens als String.
	 * 
	 * @param name der name des Produzenten.
	 * @return p der neue Produzent.
	 */
	private Produzent erstelleProduzent(String name) {
		Connection con = DatabaseConnection.getConnection();
		int id = 0;
		String[] name_p = name.split("\\s");
		try {
			PreparedStatement stmt = con.prepareStatement("select max(produzent_id) as max_id from produzenten");
			ResultSet r = stmt.executeQuery();
			while(r.next()) {
				id = r.getInt("max_id");
			}
			r.close();
			stmt.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} 
		return new Produzent(id + 1, name_p[0], name_p[1]);
	}
	
	/**
	 * Mit dieser Methode wird der Pfad des Titelbildes entsprechend der
	 * Anforderungen aufbereitet. Es wird dabei vorausgesetzt, dass alle (!)
	 * Titelbilder aus dem Verzeichnis WebContent/bilder stammen.
	 * 
	 * @param pfad der aufzuberietende pfad.
	 * @return der aufberietete pfad.
	 */
	private String aufbereitungTitelbildPfad(String pfad) {
		return "bilder/" + pfad;
	}
	
}
