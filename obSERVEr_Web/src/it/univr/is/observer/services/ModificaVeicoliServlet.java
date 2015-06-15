package it.univr.is.observer.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import it.univr.is.observer.persistenza.Usr;
import it.univr.is.observer.persistenza.Veicolo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ModificaVeicoliServlet extends HttpServlet {

	private static int LOW_PRIVILEGE = 2;
	private static int HIGH_PRIVILEGE = 0;
	// //////////
	private final Pattern patternTarga = Pattern
			.compile("(?i)(([A-Z]{2}\\d{3}[A-Z]{2})|(([A-Z]{2}|roma)(\\d{5}|\\d{6})))");

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		// valutazione se si deve andare in uno dei modi DML
		String parametroSceltaMode = request.getSession()
				.getAttribute("modalita").toString();

		if (parametroSceltaMode.equalsIgnoreCase("Inserisci")) {
			// I dati nella form sono corretti?
			if (checkData(request))
				// Esiste già?
				if (this.checkVeicolo(request))
					// Inserimento
					this.InsertVeicolo(request);
		}
		if (parametroSceltaMode.equalsIgnoreCase("Modifica")) {
			// I dati nella form sono corretti?
			if (checkData(request))
				// Modifica
				this.UpdateVeicolo(request);
		}

		response.sendRedirect("adminModificaEffettuata.jsp");
	}

	private boolean UpdateVeicolo(HttpServletRequest request) {
		if (Veicolo.modificaVeicolo(
				request.getSession().getAttribute("veicoloSelezionato")
						.toString(), request.getParameter("txtMarca")
						.toString(), request.getParameter("txtModello")
						.toString())) {
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

	private boolean InsertVeicolo(HttpServletRequest request) {
		if (Veicolo.inserisciVeicolo(request.getParameter("txtTarga")
				.toString(), request.getParameter("txtMarca").toString(),
				request.getParameter("txtModello").toString())) {
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

	private boolean checkVeicolo(HttpServletRequest request) {
		if (Veicolo
				.checkInserimento(request.getParameter("txtTarga").toString())) {
			// Utente esiste già
			request.getSession().setAttribute(
					"messaggio",
					"Il veicolo avente targa "
							+ request.getParameter("txtTarga")
							+ " già esistente!");
			return false;
		}
		return true;
	}

	private boolean checkData(HttpServletRequest request) {
		String messaggio = "";
		// Controllo dati
		// Se uno fallisce ret false

		String targa = request.getSession().getAttribute("modalita").toString()
				.equalsIgnoreCase("Inserisci") ? request.getParameter(
				"txtTarga").toString() : request.getSession()
				.getAttribute("veicoloSelezionato").toString();

		// La targa deve essere nel formato AA000AA
		Matcher matcher = patternTarga.matcher(targa);

		// Se non trovo match --> Targa non corretta
		if (!matcher.find())
			messaggio += "Targa non valida (esempio: AA000BB)</br>";

		// Se la lunghezza del match è divarsa dalla lunghezza della stringa
		// -->
		// Targa non corretta
		else if (matcher.group().length() != targa.length())
			messaggio += "Targa non valida (esempio: AA000BB)</br>";

		if (!messaggio.isEmpty()) {
			request.getSession().setAttribute("messaggio", messaggio);
			return false;
		}
		return true;
	}
}
