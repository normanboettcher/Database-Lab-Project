package model.web.servlets.loeschen;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.data.users.Administrator;
import model.database.transfer.HoleObjektAusDatenbank;

/**
 * Servlet implementation class FilmLoeschen
 */
@WebServlet("/FilmLoeschen")
public class FilmLoeschen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FilmLoeschen() {
        super();
        
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Administrator admin = (Administrator) request.getSession().getAttribute("admin");
		admin.loescheFilm(HoleObjektAusDatenbank.filmMitIdAusDB(Integer.parseInt(request.getParameter("film_id"))));
		
		request.setAttribute("filmliste", HoleObjektAusDatenbank.holeAlleFilmeAusDB());
		request.getRequestDispatcher("filme.jsp").forward(request, response);
	}
}
