package model.web.servlets.film;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.data.filme.Film;
import model.database.transfer.HoleObjektAusDatenbank;
/**
 * Dieses Servlet ist fuer den Transfer der Filme aus der Datenbank zur filme.jsp notwendig.
 * @author Norman Böttcher
 *
 */
@WebServlet("/FilmServlet")
public class HoleFilmeAusDatenbankServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Film> filme = HoleObjektAusDatenbank.holeAlleFilmeAusDB();
		request.getSession().setAttribute("filmliste", filme);
		request.getRequestDispatcher("filme.jsp").forward(request, response);
	}
}
