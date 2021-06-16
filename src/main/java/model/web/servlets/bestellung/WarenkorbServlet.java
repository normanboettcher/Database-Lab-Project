package model.web.servlets.bestellung;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.data.users.Kunde;
import model.database.transfer.HoleObjektAusDatenbank;

/**
 * Servlet implementation class WarenkorbServlet
 */
@WebServlet("/WarenkorbServlet")
public class WarenkorbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WarenkorbServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Kunde kunde = (Kunde) request.getSession().getAttribute("kunde");
		
		int id = Integer.parseInt(request.getParameter("film_id"));
		
		kunde.getWarenkorb().addBestellung(id);

		request.setAttribute("filmliste", HoleObjektAusDatenbank.holeAlleFilmeAusDB());
		
		request.getRequestDispatcher("filme.jsp").forward(request, response);
	}
}
