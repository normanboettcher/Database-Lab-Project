package model.web.servlets.film;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

import model.data.filme.Film;
import model.database.transfer.HoleObjektAusDatenbank;

/**
 * Servlet implementation class FilmDetailsServlet
 */
@WebServlet("/FilmDetails")
public class FilmDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilmDetailsServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String film_id = request.getParameter("film_id");
		int id = Integer.parseInt(film_id);
		
		Film film = HoleObjektAusDatenbank.filmMitIdAusDB(id);
		request.getSession().setAttribute("film", film);
		
		request.getRequestDispatcher("filmDetails.jsp").forward(request, response);
	}
}
