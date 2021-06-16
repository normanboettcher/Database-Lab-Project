package model.web.servlets.logout;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.data.users.Administrator;
import model.data.users.Kunde;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
        super();
        
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Administrator admin = (Administrator)request.getSession().getAttribute("admin");
		Kunde kunde = (Kunde) request.getSession().getAttribute("kunde");
		String name = "";
		System.out.println(name);
		
		if(kunde != null) {
			name = kunde.getVorname() + " " + kunde.getNachname();
		}
		
		if(admin != null) {
			name = admin.getVorname() + " " + admin.getNachname();
		}
		
		request.setAttribute("name", name);
		
		request.getSession().invalidate();
		
		request.getRequestDispatcher("logout.jsp").forward(request, response);
	}

}
