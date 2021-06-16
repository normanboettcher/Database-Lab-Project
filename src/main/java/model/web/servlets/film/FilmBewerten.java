package model.web.servlets.film;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.data.filme.Film;
import model.data.users.Kunde;

/**
 * Servlet implementation class FilmBewerten
 */
@WebServlet("/FilmBewerten")
public class FilmBewerten extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilmBewerten() {
        super();
        
    }
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Kunde k = (Kunde)request.getSession().getAttribute("kunde");
		Film film = (Film)(request.getSession().getAttribute("film"));
	
		String bewertung = request.getParameter("bewertung_abgabe");
		double neueBewertung = Double.parseDouble(bewertung);
		
		if(k != null && film != null) {
			k.bewerteFilm(film, neueBewertung);
			request.setAttribute("film", film);
			request.getRequestDispatcher("filmDetails.jsp").forward(request, response);
		} else {
			request.setAttribute("fehlermeldung", "Sie müssen sich erst einloggen, um diese Aktion durchzuführen");
			request.getRequestDispatcher("aktionVerweigert.jsp").forward(request, response);;
		}
	}
}
