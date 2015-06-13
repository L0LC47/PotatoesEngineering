package it.univr.is.observer.services;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GestioneUtentiServlet extends HttpServlet {

	private static int LOW_PRIVILEGE = 2;
	private static int HIGH_PRIVILEGE = 0;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		// valutazione se si deve andare in uno dei modi DML
		String parametroSceltaMode = request.getParameter("btnMode");
		String parametroIdRecord = request.getParameter("rdbSelezione");

		if (parametroSceltaMode.equalsIgnoreCase("Insert")) {

		}
		if (parametroSceltaMode.equalsIgnoreCase("Update")) {

		}
		if (parametroSceltaMode.equalsIgnoreCase("Delete")) {

		}

	}
} 

