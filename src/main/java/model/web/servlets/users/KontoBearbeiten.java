package model.web.servlets.users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.data.users.Administrator;
import model.data.users.Kunde;
import model.database.transfer.HoleObjektAusDatenbank;

/**
 * Servlet implementation class KontoBearbeiten
 */
@WebServlet("/KontoBearbeiten")
public class KontoBearbeiten extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KontoBearbeiten() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Administrator admin = (Administrator) request.getSession().getAttribute("admin");
		Kunde kunde = (Kunde) request.getSession().getAttribute("kunde");
		
		if(kunde != null) {
			request.setAttribute("user", HoleObjektAusDatenbank.holeUserMitEmail(true, kunde.getEmail()));
			request.setAttribute("alsAdminBearbeiten", "nein");
			request.getRequestDispatcher("kundenkontoBearbeiten.jsp").forward(request, response);
		} 
		
		if(admin != null) {
			if(request.getParameter("alsAdminBearbeiten") != null && request.getParameter("alsAdminBearbeiten").equals("ja")) {
				request.setAttribute("user", HoleObjektAusDatenbank.holeUserMitEmail(true, request.getParameter("kunden_email")));
				request.setAttribute("alsAdminBearbeiten", "ja");
				request.setAttribute("kunden_email", request.getParameter("kunden_email"));
				request.getRequestDispatcher("kundenkontoBearbeiten.jsp").forward(request, response);
			} else {
				request.setAttribute("user", HoleObjektAusDatenbank.holeUserMitEmail(false, admin.getEmail()));
				request.setAttribute("alsAdminBearbeiten", "nein");
				request.getRequestDispatcher("kundenkontoBearbeiten.jsp").forward(request, response);
			}
		}
	}
}
