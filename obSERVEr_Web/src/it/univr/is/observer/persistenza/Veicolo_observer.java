package it.univr.is.observer.persistenza;

import it.univr.is.database.MioDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 serial char(10),
 targa char(7),
 inizio DATE,
 fine DATE,
 */

public class Veicolo_observer {

	// ==== Field
	// ========================================================================

	private String serial;
	private String targa;
	private Date inizio;
	private Date fine;

	// ==== Constructor
	// ========================================================================

	public Veicolo_observer(String serial, String targa, Date inizio, Date fine) {
		this.serial = serial;
		this.targa = targa;
		this.inizio = inizio;
		this.fine = fine;
	}

	public Veicolo_observer() {

	}

	// ==== Methods
	// ========================================================================

	public Veicolo_observer(ResultSet rs) throws SQLException {
		this.serial = rs.getString("serial");
		this.targa = rs.getString("targa");
		this.inizio = rs.getDate("inizio");
		this.fine = rs.getDate("fine");
	}

	public static List<Veicolo_observer> getAssociazioniCorrenti() {
		List<Veicolo_observer> res = new ArrayList<>();
		try {
			MioDriver driver = MioDriver.getInstance();
			String query = "select * from veicolo_observer where fine is null order by serial";
			ResultSet rs = driver.execute(query, null);
			while (rs.next())
				res.add(new Veicolo_observer(rs));
		} catch (SQLException e) {
			System.out
					.println("Select failed: An Exception has occurred! " + e);
		}
		return res;
	}

	public static Veicolo_observer getAssociazioneCorrente(String targa) {
		Veicolo_observer res = null;
		try {
			MioDriver driver = MioDriver.getInstance();
			String query = "select * from veicolo_observer where fine is null and targa = ?";
			Object[] values = new Object[1];
			values[0] = targa;
			ResultSet rs = driver.execute(query, values);
			if (rs.next())
				res = new Veicolo_observer(rs);
		} catch (SQLException e) {
			System.out
					.println("Select failed: An Exception has occurred! " + e);
		}
		return res;
	}

	public static List<Observer> getObserverLiberi() {
		List<Observer> res = new ArrayList<>();
		try {
			MioDriver driver = MioDriver.getInstance();

			String query = "select * from observer o where o.serial not in "
					+ "(select vo.serial from veicolo_observer vo where fine is null)";
			ResultSet rs = driver.execute(query, null);
			while (rs.next())
				res.add(new Observer(rs.getString(1)));
		} catch (SQLException e) {
			System.out
					.println("Select failed: An Exception has occurred! " + e);
		}
		return res;
	}

	public static boolean nuovaAssociazione(String seriale, String targa) {
		boolean res = false;
		try {
			MioDriver driver = MioDriver.getInstance();
			String query = "INSERT INTO veicolo_observer (serial, targa, inizio)"
					+ " VALUES ( ?, ?, ?)";
			Object[] params = new Object[3];
			params[0] = seriale;
			params[1] = targa;
			params[2] = new java.sql.Date(System.currentTimeMillis());;
			// Se modifica 1 riga allora è andato a buon fine
			if (driver.update(query, params) == 1)
				res = true;
		} catch (SQLException e) {
			res = false;
			System.out
					.println("Select failed: An Exception has occurred! " + e);
		}
		return res;
	}

	// ==== Getter & Setter
	// ========================================================================

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public Date getInizio() {
		return inizio;
	}

	public void setInizio(Date inizio) {
		this.inizio = inizio;
	}

	public Date getFine() {
		return fine;
	}

	public void setFine(Date fine) {
		this.fine = fine;
	}

}
