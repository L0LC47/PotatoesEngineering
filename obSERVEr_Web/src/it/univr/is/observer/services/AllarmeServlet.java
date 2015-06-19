package it.univr.is.observer.services;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class AllarmeServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		response.setContentType("text/html");
		String targa;
		targa = request.getParameter("targa");
		request.getSession().setAttribute("veicoloSelezionato", targa);
		
		response.sendRedirect("userAllarmiVisualizza.jsp");
	}
}