package model.web.servlets.bestellung;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.data.bestellungen.Rechnung;
import model.data.managers.DatumsManager;
import model.data.managers.IdGenerator;
import model.data.users.Kunde;
import model.data.zahlmethoden.Klarna;
import model.data.zahlmethoden.Kreditkarte;
import model.data.zahlmethoden.PayPal;


/**
 * Servlet implementation class BestellenServlet
 */
@WebServlet("/Bestellen")
public class BestellenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BestellenServlet() {
        super();
    
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Kunde kunde = (Kunde) request.getSession().getAttribute("kunde");
		boolean bestellt = false;
		
		if(request.getParameter("zahlmethode").equals("paypal")) {
			kunde.setZahlmethode(new PayPal());
		}
		
		if(request.getParameter("zahlmethode").equals("kredit")) {
			kunde.setZahlmethode(new Kreditkarte());
		}
		
		if(request.getParameter("zahlmethode").equals("klarna")) {
			kunde.setZahlmethode(new Klarna());
		}
		
		if(request.getParameter("zahlmethode") == null) {
			request.setAttribute("meldung", "Bitte wählen Sie eine Zahlmethode aus");
			request.getRequestDispatcher("warenkorb.jsp").forward(request, response);
		}
		
		if(kunde.getZahlmethode() != null) {
			kunde.erstelleRechnung(new Rechnung(IdGenerator.generiereRechnungsID(), kunde, DatumsManager.aktuellesDatum()));
			bestellt = true;
			request.setAttribute("bestellt", bestellt);
			request.setAttribute("meldung", "Sie haben die Bestellug für " + kunde.getWarenkorb().getGesamtpreis() + " € erolgreich aufgegeben."
					+ "\n" + "Vielen Dank für Ihren Einkauf bei Homestar.");
			request.setAttribute("warenkorb", kunde.getWarenkorb());
			request.getRequestDispatcher("warenkorb.jsp").forward(request, response);
		}
	}
}
