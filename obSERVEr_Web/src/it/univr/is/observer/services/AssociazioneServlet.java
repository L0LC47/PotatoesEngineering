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
import it.univr.is.observer.persistenza.Usr_veicolo;
import it.univr.is.observer.persistenza.Veicolo;
import it.univr.is.observer.logica.*;
import it.univr.is.database.MioDriver;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.*;
import java.util.List;

/**
 * Servlet implementation class LoginServlet
 */
public class AssociazioneServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		// valutazione se si deve andare in uno dei modi DML
		String parametroSceltaMode = request.getParameter("btnMode");
		request.setAttribute("messaggio", "");

		if (parametroSceltaMode.equalsIgnoreCase("Inserisci")) {
			request.getRequestDispatcher("gestoreNuovaAssociaUtentiVeicoli.jsp")
			.forward(request, response);
		} else if (parametroSceltaMode.equalsIgnoreCase("Elimina")) {
			// Sei sicuro? --> Eliminazione effettuata con successo
			// Elimina
			String parametroIdRecord = request.getParameter("rdbSelezione");
			if (Usr_veicolo.termina(parametroIdRecord)) {
				request.setAttribute("messaggio",
						"Eliminazione effettuata con successo!");
				request.getRequestDispatcher("gestoreAssociaUtentiVeicoli.jsp")
						.forward(request, response);
			} else {
				// Inserimento non andato a buon fine
				request.getSession().setAttribute("messaggio", "Errore!");
			}
		} else if (parametroSceltaMode.equalsIgnoreCase("Associa")) {
			String email = request.getParameter("utente");
			String targa = request.getParameter("veicolo");

			if (Usr_veicolo.associa(email, targa)) {
				request.setAttribute("messaggio",
						"Associazione effettuata con successo!");
				request.getRequestDispatcher("gestoreAssociaUtentiVeicoli.jsp")
						.forward(request, response);
			} else {
				// Inserimento non andato a buon fine
				request.getSession().setAttribute("messaggio", "Errore!");
			}
		} else {
			request.getRequestDispatcher("gestoreNuovaAssociaUtentiVeicoli.jsp")
			.forward(request, response);
		}
	}
}