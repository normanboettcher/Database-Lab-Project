package model.web.servlets.film;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.data.filme.Film;
import model.data.users.Administrator;
import model.database.transfer.HoleObjektAusDatenbank;

/**
 * Servlet implementation class FilmAendernServlet
 */
@WebServlet("/FilmAendern")
public class FilmAendernServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilmAendernServlet() {
        super();
        
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Administrator admin = (Administrator) request.getSession().getAttribute("admin");
		Film film = (Film) request.getSession().getAttribute("f");
		
		int counter_aenderungen = 0;
		
		if(!request.getParameter("titel_neu").equals(""))  {
			counter_aenderungen++;
			admin.bearbeiteFilm(request.getParameter("titel_neu"), "filmtitel", film);
			request.setAttribute("titel_veraendert", "Filmtitel erfolgreich ge�ndert");
			System.out.println(request.getParameter("titel_neu"));
		}
		
		
		if(request.getParameter("genre_neu") != null) {
			if(!request.getParameter("genre_neu").equals("")) {
				counter_aenderungen++;
				admin.bearbeiteFilm(request.getParameter("genre_neu"), "genre", film);
				request.setAttribute("genre_veraendert", "Genre erolgreich ge�ndert");
			}
		}
		if(!request.getParameter("preis_neu").equals("") && !request.getParameter("preis_neu").equals(null)) {
			counter_aenderungen++;
			admin.bearbeiteFilm(request.getParameter("preis_neu"), "preis", film);
			request.setAttribute("preis_veraendert",  "Preis wurde erfolgreich geaendert");
		}
		
			
		if(!request.getParameter("alter_neu").equals(null) && !request.getParameter("alter_neu").equals("")) {
			counter_aenderungen++;
			admin.bearbeiteFilm(request.getParameter("alter_neu"), "altersbeschraenkung", film);
			request.setAttribute("alter_veraendert", "Die Altersbeschr�nkung wurde erfolgreich ge�ndert");
		}
		
		if(!request.getParameter("produzent_neu").equals("")) {
			counter_aenderungen++;
			admin.bearbeiteFilm(request.getParameter("produzent_neu"), "produzent", film);
			request.setAttribute("produzent_veraendert", "Der Produzent wurde erfolgreich ge�ndert.");
		}
		
		
		/*
		 * if(!request.getParameter("yt_link_neu").equals("")) { counter_aenderungen++;
		 * admin.bearbeiteFilm(request.getParameter("yt_link_neu"), "quelle", film);
		 * request.setAttribute("yt_link_veraendert",
		 * "Die Quelle des Films wurde erfolgreich ge�ndert"); }
		 */

			
		if(!request.getParameter("beschr_neu").equals("")) {
			counter_aenderungen++;
			admin.bearbeiteFilm(request.getParameter("beschr_neu"), "beschreibung", film);
			request.setAttribute("beschr_veraendert", "Beschreibung erfolgreich ge�ndert");
		}
		
		
		if(!request.getParameter("datum_neu").equals("")) {
			counter_aenderungen++;
			admin.bearbeiteFilm(request.getParameter("datum_neu"), "veroeffentlicht", film);
			request.setAttribute("datum_veraendert", "Datum der Ver�ffentlichung wurde erfolgreich ge�ndert");
		}
		
		if(counter_aenderungen > 0) {
			String aenderung_admin = admin.getVorname() + " " + admin.getNachname();
			request.setAttribute("aenderung_admin", aenderung_admin);
		}
		
		request.setAttribute("f", HoleObjektAusDatenbank.filmMitIdAusDB(film.getId()));
		request.setAttribute("counter_aenderungen", counter_aenderungen);
		request.getRequestDispatcher("filmBearbeiten.jsp").forward(request, response);
	}
}
