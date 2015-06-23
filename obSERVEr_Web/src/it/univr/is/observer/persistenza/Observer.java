package it.univr.is.observer.persistenza;

import it.univr.is.database.MioDriver;

import java.sql.ResultSet;
import java.sql.SQLException;

/*
 serial char(10) PRIMARY KEY
 sms int
 */

public class Observer {

	// ==== Field
	// ========================================================================

	private String serial;

	// ==== Constructor
	// ========================================================================

	public Observer(String serial) {
		this.serial = serial;
	}

	public Observer() {

	}

	// ==== Methods
	// ========================================================================

	/**
	 * Ritorna l'impostazione selezionata per gli sms del dispositivo avente
	 * seriale = seriale
	 * 
	 * @param seriale
	 * @return
	 */
	public static int getSMS(String seriale) {
		int result = -1;
		try {
			MioDriver driver = MioDriver.getInstance();
			String query = "select sms from observer where serial = ?";
			Object[] params = new Object[1];
			params[0] = seriale;
			ResultSet rs = driver.execute(query, params);
			if (rs != null) {
				rs.next();
				result = rs.getInt("sms");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Setta l'intervallo per l'invio di SMS del dispositivo avente seriale = seriale
	 * 
	 * @param sms
	 * @param seriale
	 */
	public static boolean setSMS(int sms, String seriale) {
		try {

			MioDriver driver = MioDriver.getInstance();
			String query = "update observer set sms = ? where serial = ?";
			Object[] params = new Object[2];
			params[0] = sms;//Integer.valueOf(sms);
			params[1] = seriale;
			if (driver.update(query, params) != 1) {
				System.err.println("Errore inserimento");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	// ==== Getter & Setter
	// ========================================================================

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}
}
