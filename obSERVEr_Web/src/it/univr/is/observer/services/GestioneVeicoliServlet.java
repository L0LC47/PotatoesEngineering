package it.univr.is.observer.services;

import it.univr.is.observer.persistenza.Usr;
import it.univr.is.observer.persistenza.Veicolo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GestioneVeicoliServlet extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {

		// valutazione se si deve andare in uno dei modi DML
		String parametroSceltaMode = request.getParameter("btnMode");
		String parametroIdRecord = request.getParameter("rdbSelezione");
		// Metto in sessione la modalità per la pagina dei risultati
		request.getSession().setAttribute("modalita", parametroSceltaMode);

		if (parametroSceltaMode.equalsIgnoreCase("Inserisci"))
			response.sendRedirect("adminModificaVeicolo.jsp");

		if (parametroSceltaMode.equalsIgnoreCase("Modifica")) {
			request.getSession().setAttribute("veicoloSelezionato",
					parametroIdRecord);
			response.sendRedirect("adminModificaVeicolo.jsp");
		}
		if (parametroSceltaMode.equalsIgnoreCase("Elimina")) {
			// Sei sicuro? --> Eliminazione effettuata con successo
			// Elimina
			if (Veicolo.eliminaVeicolo(parametroIdRecord)) {
				if (parametroIdRecord.equals(request.getSession()
						.getAttribute("currentSessionUser").toString()))
					request.getSession().invalidate();
					request.getSession().setAttribute("messaggio",
							"Eliminazione effettuata con successo!");
			} else {
				// Inserimento non andato a buon fine
				request.getSession().setAttribute("messaggio",
						"Inserimento non effettuato a causa di un errore!");
			}
			response.sendRedirect("adminModificaEffettuata.jsp");
		}
	}
}