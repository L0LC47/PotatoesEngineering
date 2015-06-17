package it.univr.is.observer.services;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		try {
			HttpSession session = request.getSession(true);
			
			System.out.println("Sono la userservlet!");

		}

		catch (Throwable theException) {
			System.out.println(theException);
		}
	}

}
