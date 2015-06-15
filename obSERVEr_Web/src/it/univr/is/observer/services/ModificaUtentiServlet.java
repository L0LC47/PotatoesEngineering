package it.univr.is.observer.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.univr.is.observer.persistenza.Usr;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModificaUtentiServlet extends HttpServlet {

	private static int LOW_PRIVILEGE = 2;
	private static int HIGH_PRIVILEGE = 0;
	private final Pattern patternEmail = Pattern
			.compile("(?i)[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}");

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		// valutazione se si deve andare in uno dei modi DML
		String parametroSceltaMode = request.getSession()
				.getAttribute("modalita").toString();

		if (parametroSceltaMode.equalsIgnoreCase("Inserisci")) {
			// I dati nella form sono corretti?
			if (checkData(request))
				// Esiste già?
				if (this.checkUser(request))
					// Password corrispondono?
					if (this.checkPassword(request))
						// Inserimento
						this.InsertUser(request);
		}
		if (parametroSceltaMode.equalsIgnoreCase("Modifica")) {
			// I dati nella form sono corretti?
			if (checkData(request))
				// Password corrispondono?
				if (this.checkPassword(request))
					// Modifica
					this.UpdateUser(request);
		}

		response.sendRedirect("adminModificaEffettuata.jsp");
	}

	private boolean UpdateUser(HttpServletRequest request) {
		if (Usr.modificaUtente(
				request.getSession().getAttribute("utenteSelezionato")
						.toString(),
				request.getParameter("txtNome").toString(), request
						.getParameter("txtCognome").toString(), request
						.getParameter("txtPassword").toString(), request
						.getParameter("txtGestore").toString())) {
			// Modifica effettuata con successo
			request.getSession().setAttribute("messaggio",
					"Modifica effettuata con successo!");
			return true;
		} else {
			// Inserimento non andato a buon fine
			request.getSession().setAttribute("messaggio",
					"Inserimento non effettuato a causa di un errore!");
			return false;
		}

	}

	private boolean InsertUser(HttpServletRequest request) {
		if (Usr.inserisciUtente(request.getParameter("txtEmail").toString(),
				request.getParameter("txtNome").toString(), request
						.getParameter("txtCognome").toString(), request
						.getParameter("txtPassword").toString(), request
						.getParameter("txtGestore").toString())) {
			// Inserimento effettuato con successo
			request.getSession().setAttribute("messaggio",
					"Inserimento effettuato con successo!");
			return true;
		} else {
			// Inserimento non andato a buon fine
			request.getSession().setAttribute("messaggio",
					"Inserimento non effettuato a causa di un errore!");
			return false;
		}
	}

	private boolean checkPassword(HttpServletRequest request) {
		if (!request.getParameter("txtPassword").equals(
				request.getParameter("txtCPassword"))) {
			// Passoword non corrispondono
			request.getSession().setAttribute("messaggio",
					"Le password non corrispondono!");
			return false;
		}
		return true;
	}

	private boolean checkUser(HttpServletRequest request) {
		if (Usr.checkInserimento(request.getParameter("txtEmail").toString())) {
			// Utente esiste già
			request.getSession().setAttribute(
					"messaggio",
					"Utente con email " + request.getParameter("txtEmail")
							+ " già esistente!");
			return false;
		}
		return true;

	}

	private boolean checkData(HttpServletRequest request) {
		String messaggio = "";
		// Controllo dati
		// Se uno fallisce ret false

		String email = request.getSession().getAttribute("modalita").toString()
				.equalsIgnoreCase("Inserisci") ? request.getParameter(
				"txtEmail").toString() : request.getSession()
				.getAttribute("utenteSelezionato").toString();

		// Deve essere una e-mail valida
		Matcher matcher = patternEmail.matcher(email);

		// Se non trovo match --> Email non corretta
		if (!matcher.find())
			messaggio += "Email non valida</br>";

		// Se la lunghezza del match è divarsa dalla lunghezza della stringa
		// -->
		// Email non corretta
		else if (matcher.group().length() != email.length())
			messaggio += "Email non valida</br>";

		// La password eve contenere almeno 6 caratteri
		if (request.getParameter("txtPassword").toString().length() < 6)
			messaggio += "La password deve contenere almeno 6 caratteri</br>";

		// Gestore deve essere compreso tra 0 e 2
		int value = Integer.parseInt(request.getParameter("txtGestore")
				.toString());
		if (value < HIGH_PRIVILEGE || value > LOW_PRIVILEGE)
			messaggio += "Il Livello di Privilegi deve essere compreso tra "
					+ HIGH_PRIVILEGE + " e " + LOW_PRIVILEGE + "</br>";

		if (!messaggio.isEmpty()) {
			request.getSession().setAttribute("messaggio", messaggio);
			return false;
		}
		return true;
	}
}
