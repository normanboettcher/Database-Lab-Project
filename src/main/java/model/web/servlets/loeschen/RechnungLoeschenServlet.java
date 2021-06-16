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
 * Servlet implementation class RechnungLoeschenServlet
 */
@WebServlet("/RechnungLoeschen")
public class RechnungLoeschenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RechnungLoeschenServlet() {
        super();
       
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Administrator admin = (Administrator)request.getSession().getAttribute("admin");
		admin.loescheRechnung(Integer.parseInt(request.getParameter("rechnung")));
		request.setAttribute("meldung", "Die Rechnung mit der ID "+ Integer.parseInt(request.getParameter("rechnung")) + " wurde erfolgreich gelöscht");
		//Die Rechnungen müssen aus der aktualisierten Tabelle nach der Loeschung erneut aus der DB geholt werden.
		request.setAttribute("rechnungen", HoleObjektAusDatenbank.holeRechnungenAusDB());
		
		request.getRequestDispatcher("rechnungenAnzeigen.jsp").forward(request, response);
	}	 
}
