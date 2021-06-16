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
 * Servlet implementation class FilmBearbeitenServlet
 */
@WebServlet("/FilmBearbeiten")
public class FilmBearbeitenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilmBearbeitenServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Film f = HoleObjektAusDatenbank.filmMitIdAusDB(Integer.parseInt(request.getParameter("film_id")));
		
		request.getSession().setAttribute("f", f);
		
		request.getRequestDispatcher("filmBearbeiten.jsp").forward(request, response);
		
	}
}
