package it.univr.is.observer.persistenza;

import it.univr.is.database.MioDriver;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 observer char(10) PRIMARY KEY,
 velocita SMALLINT NOT NULL DEFAULT 130,
 */

public class Allarme {

	// ==== Field
	// ========================================================================

	private String observer;
	private int velocita;

	// ==== Constructor
	// ========================================================================

	public Allarme(String observer, int velocita) {
		this.observer = observer;
		this.velocita = velocita;
	}

	public Allarme() {

	}

	// ==== Methods
	// ========================================================================

	/**
	 * Ritorna la velocità a cui è impostato l'allarme del dispositivo avente
	 * seriale = seriale
	 * 
	 * @param seriale
	 * @return
	 */
	public int getAllarme(String seriale) {
		int result = -1;
		try {
			MioDriver driver = MioDriver.getInstance();
			String query = "select velocita from allarme where observer=?";
			Object[] params = new Object[1];
			params[0] = seriale;
			ResultSet rs = driver.execute(query, params);
			rs.next();
			result = Integer.parseInt(rs.getString(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Setta la velocità dell'allarme del dispositivo avente seriale = seriale
	 * 
	 * @param velocita
	 * @param seriale
	 */
	public void setAllarme(int velocita, String seriale) {
		try {
			MioDriver driver = MioDriver.getInstance();
			String query = "update allarme set velocita = ? where observer=?";
			Object[] params = new Object[2];
			params[0] = velocita;
			params[1] = seriale;
			if (driver.update(query, params) != 1)
				// TODO: Gestione errore
				System.err.println("Errore inserimento");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Ritorna la lista degli allarmi corrispondenti all'utente corrente e i
	 * dati relativi al veicolo
	 * 
	 * @param seriale
	 * @return
	 */

	public ResultSet getAllarme(String email) {
		/**
		 * da email ottengo gestore e quindi privilegi
		 * 
		 * 0 admin 1 gestore 2 user
		 * 
		 * se 0 listo all
		 * 
		 * se 1 listo solo quelle dove gestore = email
		 * 
		 * se 2 join con usr-veicolo e listo solo quelle dove data antecedente a
		 * oggi, fine nulla o > oggi email = mia email
		 */

		ResultSet rs = null;
		try {
			MioDriver driver = MioDriver.getInstance();
			String query = "select velocita from allarme where observer=?";
			Object[] params = new Object[1];
			params[0] = seriale;
			ResultSet rs = driver.execute(query, params);
			rs.next();
			result = Integer.parseInt(rs.getString(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	// ==== Getter & Setter
	// ========================================================================

	public String getObserver() {
		return observer;
	}

	public void setObserver(String observer) {
		this.observer = observer;
	}

	public int getVelocita() {
		return velocita;
	}

	public void setVelocita(int velocita) {
		this.velocita = velocita;
	}

}
