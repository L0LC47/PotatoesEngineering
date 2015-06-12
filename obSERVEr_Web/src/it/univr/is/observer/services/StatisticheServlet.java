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
public class StatisticheServlet extends HttpServlet {

	private static int LOW_PRIVILEGE = 2;
	private static int HIGH_PRIVILEGE = 0;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		response.setContentType("text/html");
		String inizio, fine, targa;
		int min, med, medMov, max;

		inizio = request.getParameter("dataInizio");
		fine = request.getParameter("dataFine");
		request.getSession().setAttribute("dataInizio", inizio);
		request.getSession().setAttribute("dataFine", fine);
		targa = request.getSession().getAttribute("veicoloSelezionato")
				.toString();
		List<Integer> velocita = Veicolo.getStatisticheVeicolo(targa, inizio,
				fine);
		min = Veicolo.getStatistica(velocita, new StatisticaMin());
		max = Veicolo.getStatistica(velocita, new StatisticaMax());
		med = Veicolo.getStatistica(velocita, new StatisticaMedia());
		medMov = Veicolo
				.getStatistica(velocita, new StatisticaMediaMovimento());
		try {
			request.getSession().setAttribute("velMin", min);
			request.getSession().setAttribute("velMax", max);
			request.getSession().setAttribute("velMed", med);
			request.getSession().setAttribute("velMedMov", medMov);
			response.sendRedirect("userStatisticheDate.jsp");
		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}