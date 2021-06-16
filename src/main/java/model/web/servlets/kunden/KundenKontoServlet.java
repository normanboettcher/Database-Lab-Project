package model.web.servlets.kunden;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.data.users.Kunde;
import model.database.transfer.HoleObjektAusDatenbank;

/**
 * Servlet implementation class KundenKontoServlet
 */
@WebServlet("/Kundenkonto")
public class KundenKontoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KundenKontoServlet() {
        super();
      
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Kunde kunde = (Kunde)request.getSession().getAttribute("kunde");
		
		request.setAttribute("rechnungen", HoleObjektAusDatenbank.holeRechnungenVonKunde(kunde));
		request.setAttribute("name", kunde.getVorname() + " " + kunde.getNachname());
		
		request.getRequestDispatcher("kundenkonto.jsp").forward(request, response);
	}
}
