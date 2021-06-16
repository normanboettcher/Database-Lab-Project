package model.web.servlets.bestellung;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.data.users.Kunde;

/**
 * Servlet implementation class WarenkorbAnzeigenServlet
 */
@WebServlet("/WarenkorbAnzeigen")
public class WarenkorbAnzeigenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WarenkorbAnzeigenServlet() {
        super();
        
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Kunde kunde = (Kunde) request.getSession().getAttribute("kunde");
		
		request.setAttribute("warenkorb", kunde.getWarenkorb());
		
		request.getRequestDispatcher("warenkorb.jsp").forward(request, response);
	}

}
