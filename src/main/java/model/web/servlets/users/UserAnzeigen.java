package model.web.servlets.users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.database.transfer.HoleObjektAusDatenbank;

/**
 * Servlet implementation class UserAnzeigen
 */
@WebServlet("/UserAnzeigen")
public class UserAnzeigen extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserAnzeigen() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("userliste", HoleObjektAusDatenbank.holeAlleUser());
		request.getRequestDispatcher("userAnzeigen.jsp").forward(request, response);
	}
}
