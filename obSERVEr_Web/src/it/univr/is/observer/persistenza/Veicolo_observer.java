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
