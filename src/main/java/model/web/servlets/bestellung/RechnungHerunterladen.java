package model.web.servlets.bestellung;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.data.users.Kunde;
import model.database.transfer.HoleObjektAusDatenbank;

/**
 * Servlet implementation class RechnungHerunterladen
 */
@WebServlet("/Herunterladen")
public class RechnungHerunterladen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RechnungHerunterladen() {
        super();
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Kunde kunde = (Kunde)request.getSession().getAttribute("kunde");
		
		ServletContext cntxt = getServletContext();
		
		kunde.rechnungAlsPDFHerunterladen(Integer.parseInt(request.getParameter("rechnung")), cntxt, response);
		
		request.setAttribute("rechnungen", HoleObjektAusDatenbank.holeRechnungenVonKunde(kunde));
		
		request.getRequestDispatcher("kundenkonto.jsp").forward(request, response);
	}

}
