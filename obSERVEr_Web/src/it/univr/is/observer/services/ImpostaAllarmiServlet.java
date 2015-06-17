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

import it.univr.is.observer.persistenza.Allarme;
import it.univr.is.observer.persistenza.Usr_veicolo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
public class ImpostaAllarmiServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		String parametroSceltaMode = request.getParameter("btnMode");
		request.setAttribute("messaggio", "");

		if (parametroSceltaMode.equalsIgnoreCase("Modifica")) {
			String seriale = request.getParameter("rdbSelezione");
			int velocita = Integer.parseInt(request.getParameter("velocita"));

			if (Allarme.setAllarme(velocita, seriale)) {
				request.setAttribute("messaggio", "Allarme Impostato!");
			} else {
				request.setAttribute("messaggio", "Errore!");
			}
			request.getRequestDispatcher("gestoreImpostaAllarmi.jsp").
				forward(request, response);
		} else {
			request.setAttribute("messaggio", "ARGH!");
			request.getRequestDispatcher("gestoreImpostaAllarmi.jsp").
			forward(request, response);
		}
	}
}