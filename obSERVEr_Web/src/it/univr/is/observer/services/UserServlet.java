package it.univr.is.observer.services;

import java.util.List;

import it.univr.is.observer.persistenza.*;
import it.univr.is.database.MioDriver;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		try {
			HttpSession session = request.getSession(true);
			String currentUser = session.getAttribute("currentSessionUser")
					.toString();
			System.out.println("Sono la userservlet!");
			List<Veicolo> listaVeicoli = Storico.getUserVeicoliUtente(currentUser);
			System.out.println(listaVeicoli.size());
			request.setAttribute("listaElenco", listaVeicoli);
			System.out.println(listaVeicoli.get(0).getGuidatore());
		}

		catch (Throwable theException) {
			System.out.println(theException);
		}
	}

}
