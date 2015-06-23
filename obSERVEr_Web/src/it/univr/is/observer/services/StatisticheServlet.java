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
import it.univr.is.observer.stat.StatisticaMax;
import it.univr.is.observer.stat.StatisticaMedia;
import it.univr.is.observer.stat.StatisticaMediaMovimento;
import it.univr.is.observer.stat.StatisticaMin;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		int min = 0, med = 0, medMov = 0, max = 0;

		inizio = request.getParameter("dataInizio");
		fine = request.getParameter("dataFine");
		request.getSession().setAttribute("dataInizio", inizio);
		request.getSession().setAttribute("dataFine", fine);
		targa = request.getParameter("targa");
		System.out.println(targa);
		request.getSession().setAttribute("veicoloSelezionato", targa);
		List<Integer> velocita = Veicolo.getStatisticheVeicolo(targa, inizio,
				fine);
		if (velocita.size() > 0) {
			min = Veicolo.getStatistica(velocita, new StatisticaMin());
			max = Veicolo.getStatistica(velocita, new StatisticaMax());
			med = Veicolo.getStatistica(velocita, new StatisticaMedia());
			medMov = Veicolo.getStatistica(velocita,
					new StatisticaMediaMovimento());
		}
		try {
			request.getSession().setAttribute("velMin", min);
			request.getSession().setAttribute("velMax", max);
			request.getSession().setAttribute("velMed", med);
			request.getSession().setAttribute("velMedMov", medMov);
			response.sendRedirect("userStatisticheVisualizza.jsp");
		} catch (Throwable theException) {
			System.out.println(theException);
		}
	}
}