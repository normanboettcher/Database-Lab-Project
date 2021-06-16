package model.web.servlets.kunden;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.data.users.Kunde;
import model.database.transfer.HoleObjektAusDatenbank;

/**
 * Servlet implementation class KundenAnzeigenServlet
 */
@WebServlet("/KundenAnzeigen")
public class KundenAnzeigenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KundenAnzeigenServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Kunde> kunden = HoleObjektAusDatenbank.holeAlleKundenAusDB();
		
		request.setAttribute("kundenliste", kunden);
		request.getRequestDispatcher("kundenAnzeigen.jsp").forward(request, response);
	}
}
