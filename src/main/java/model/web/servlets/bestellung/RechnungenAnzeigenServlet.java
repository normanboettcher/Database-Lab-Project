package model.web.servlets.bestellung;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.data.bestellungen.Rechnung;
import model.database.transfer.HoleObjektAusDatenbank;

/**
 * Servlet implementation class RechnungenAnzeigenServlet
 */
@WebServlet("/RechnungenAnzeigen")
public class RechnungenAnzeigenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RechnungenAnzeigenServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Rechnung> rechnungen = HoleObjektAusDatenbank.holeRechnungenAusDB();

		request.setAttribute("rechnungen", rechnungen);
		
		request.getRequestDispatcher("rechnungenAnzeigen.jsp").forward(request, response);
	}
}
