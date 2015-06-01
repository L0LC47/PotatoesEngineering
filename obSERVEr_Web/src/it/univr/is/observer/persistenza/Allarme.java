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
	public int getAllarmeDB(String seriale) {
		int result = -1;
		try {
			MioDriver driver = MioDriver.getInstance();
			String query = "select velocita from allarme where observer=?";
			Object[] params = new Object[1];
			params[0] = seriale;
			ResultSet rs = driver.execute(query, params);
			rs.next();
			result = rs.getInt("velocita");
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
	 * @param email
	 * @return
	 */

	public ResultSet getAllarme(String email) {

		int livelloPrivilegi = -1;
		String query = "";
		Object[] params = null;
		MioDriver driver;
		ResultSet rs = null;

		// Ottengo livello di privilegi dell'utente
		try {
			driver = MioDriver.getInstance();
			query = "select veicolo.gestore from veicolo, usr_veicolo "
					+ "where usr_veicolo.targa = veicolo.targa and "
					+ "usr_veicolo = ?";
			params = new Object[1];
			params[0] = email;
			rs = driver.execute(query, params);
			rs.next();
			livelloPrivilegi = rs.getInt("gestore");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			driver = MioDriver.getInstance();
			switch (livelloPrivilegi) {
			// se livelloPrivilegi = 0 -> Admin -> visualizzo tutti gli allarmi
			case 0:
				query = "select uv.email, uv.targa, a.observer, "
						+ "a.velocita from allarme a, usr_veicolo uv, "
						+ "veicolo_observer vo where vo.serial = a.observer "
						+ "and uv.targa = vo.targa and uv.inizio < current_date"
						+ " and (uv.fine >= current_date OR uv.fine is null)";
				break;
			// se livelloPrivilegi = 1 -> GestoreFlotta
			case 1:
				query = "select uv.email, uv.targa, a.observer, "
						+ "a.velocita from allarme a, usr_veicolo uv, "
						+ "veicolo_observer vo, veicolo v where vo.serial = "
						+ "a.observer and uv.targa = vo.targa and v.targa = "
						+ "vo.targa and v.gestore = ? uv.inizio < current_date "
						+ "and (uv.fine >= current_date OR uv.fine is null)";
				params = new Object[1];
				params[0] = email;
				break;
			// se livelloPrivilegi = 2 -> Utente
			case 2:
				query = "select uv.email, uv.targa, a.observer, "
						+ "a.velocita from allarme a, usr_veicolo uv, "
						+ "veicolo_observer vo where vo.serial = a.observer and"
						+ " uv.email = ? and uv.targa = vo.targa and uv.inizio "
						+ "< current_date and (uv.fine >= current_date OR "
						+ "uv.fine is null)";
				params = new Object[1];
				params[0] = email;
				break;
			default:
				// TODO: Gestione errore
				System.err.println("Errore inserimento");
			}
			rs = driver.execute(query, params);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
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
