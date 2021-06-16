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
 * Servlet implementation class KundenLoeschenServlet
 */
@WebServlet("/KundenLoeschen")
public class KundenLoeschenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KundenLoeschenServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Administrator admin = (Administrator) request.getSession().getAttribute("admin");
		
		admin.loescheKunde(request.getParameter("email"));
		
		request.setAttribute("kundenliste", HoleObjektAusDatenbank.holeAlleKundenAusDB());
		
		request.getRequestDispatcher("kundenAnzeigen.jsp").forward(request, response);
	}

}
