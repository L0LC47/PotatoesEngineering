package it.univr.is.observer.services;

/*
 * The login servlet instantiates a Bean that is of type "UserBean", 
 * and then calls the DAO named "UserDAO".
 * 1. UserBean is a class representing the User table in our Database 
 * (where each column in the user table has a corresponding instance variable 
 * with a setter and a getter method).
 * 2. DAO(data access object) contains methods needed to communicate with 
 * the data source. The only needed method is the login method that checks 
 * if the username and password inputted by the user are valid or not.
 * */

import it.univr.is.observer.persistenza.Usr;
import it.univr.is.database.MioDriver;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {

	private static int LOW_PRIVILEGE = 2;
	private static int HIGH_PRIVILEGE = 0;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		try {
			Usr user = Usr.getUserData(request.getParameter("user"));

			if (user != null
					&& user.getPassword().equals(request.getParameter("pw"))) {
				// TODO: MD5 password
				HttpSession session = request.getSession(true);
				session.setAttribute("currentSessionUser", user.getEmail());
				session.setAttribute("currentUserPrivileges", user.getGestore());

				// setting session to expiry in 30 mins
				session.setMaxInactiveInterval(30 * 60);

				if (user.getGestore() <= LOW_PRIVILEGE
						&& user.getGestore() >= HIGH_PRIVILEGE)
					response.sendRedirect("userLogged.jsp");
				else if (request.getParameter("user").contains("rick")
						|| request.getParameter("user").contains("astley"))
					response.sendRedirect("https://youtu.be/dQw4w9WgXcQ");
				else
					System.out.println("Not role user registered!");

			} else
				response.sendRedirect("invalidLogin.jsp"); // error page
		}

		catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}
