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

import it.univr.is.observer.persistenza.Observer;
import it.univr.is.observer.persistenza.Usr_veicolo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class SMSServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		String parametroSceltaMode = request.getParameter("btnMode");
		request.setAttribute("messaggio", "");

		if (parametroSceltaMode.equalsIgnoreCase("Modifica")) {
			String seriale = request.getParameter("rdbSelezione");
			int sms = Integer.parseInt(request.getParameter("intervallo"));

			if (sms == 0 || sms == 1 || sms == 3 || sms == 6 || sms == 12
					|| sms == 24) {

				if (Observer.setSMS(sms, seriale)) {
					request.setAttribute("messaggio", "SMS Impostato!");
				} else {
					request.setAttribute("messaggio", "Errore!");
				}
				request.getRequestDispatcher("gestoreImpostaSMS.jsp").forward(
						request, response);
			} else {
				request.setAttribute("messaggio",
						"Il valore dell'intervallo può essere solo 0, 1, 3, 6, 12, 24");
				request.getRequestDispatcher("gestoreImpostaSMS.jsp").forward(
						request, response);
			}
		} else {
			request.setAttribute("messaggio", "ARGH!");
			request.getRequestDispatcher("gestoreImpostaSMS.jsp").forward(
					request, response);
		}
	}
}