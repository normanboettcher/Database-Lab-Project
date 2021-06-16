package model.web.servlets.bestellung;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.data.users.Kunde;
import model.data.zahlmethoden.Klarna;
import model.data.zahlmethoden.Kreditkarte;
import model.data.zahlmethoden.PayPal;
import model.database.DatabaseConnection;
import model.database.transfer.HoleObjektAusDatenbank;

/**
 * Servlet implementation class BezahlenServlet
 */
@WebServlet("/Bezahlen")
public class BezahlenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BezahlenServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Kunde kunde = (Kunde)request.getSession().getAttribute("kunde");
		int id = Integer.parseInt(request.getParameter("rechnungs_id"));
		
		if(holeZahlmethodeDerRechnung(id).equals("PayPal")) {
			kunde.setZahlmethode(new PayPal());
		}
		
		if(holeZahlmethodeDerRechnung(id).equals("Kreditkarte")) {
			kunde.setZahlmethode(new Kreditkarte());
		}
		
		if(holeZahlmethodeDerRechnung(id).equals("Klarna")) {
			kunde.setZahlmethode(new Klarna());
		}
		
		kunde.getZahlmethode().zahlungAusfuehren(id);
		
		request.setAttribute("rechnungen", HoleObjektAusDatenbank.holeRechnungenVonKunde(kunde));
		request.getRequestDispatcher("kundenkonto.jsp").forward(request, response);
	}
	
	/**
	 * Metode zum holen der Zahlmethode fuer eine spezielle Rechnung.
	 * 
	 * @param id die id der Rechnung.
	 * @return str die Bezeichnung der Zahlmethode als String.
	 */
	private String holeZahlmethodeDerRechnung(int id) {
		String str = "";
		Connection con = DatabaseConnection.getConnection();
		try {
			PreparedStatement stmt = con.prepareStatement("select zahlmethode from rechnungen where rechnungs_id = ?");
			stmt.setInt(1, id);
			ResultSet r = stmt.executeQuery();
			
			while(r.next()) {
				str = r.getString("zahlmethode");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return str;
	}
}
