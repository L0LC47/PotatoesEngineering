package it.univr.is.observer.persistenza;

import it.univr.is.database.MioDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 email character varying(50),
 targa char(7),
 inizio DATE,
 fine DATE,
 */

public class Usr_veicolo {

	// ==== Field
	// ========================================================================

	private String email;
	private String targa;
	private Date inizio;
	private Date fine;

	// ==== Constructor
	// ========================================================================

	public Usr_veicolo(String email, String targa, Date inizio, Date fine) {

		this.email = email;
		this.targa = targa;
		this.inizio = inizio;
		this.fine = fine;
	}

	public Usr_veicolo() {

	}

	public Usr_veicolo(ResultSet rs) throws SQLException {
		this.email = rs.getString("email");
		this.targa = rs.getString("targa");
		this.inizio = rs.getDate("inizio");
		this.fine = rs.getDate("fine");
	}
	// ==== Methods
	// ========================================================================

	/**
	 * Ritorno i dati di tutti i veicoli con il guidatore attuale
	 * 
	 * @param
	 * @return
	 */
	public static List<Veicolo> getVeicoliConGuidatore() {
		List<Veicolo> res = new ArrayList<>();
		try {
			MioDriver driver = MioDriver.getInstance();
			String query = "select v.targa, v.marca, v.modello, v.gestore, x.email "
					+ "from veicolo v left join "
					+ "(select uv.email, uv.targa from usr_veicolo uv "
					+ "where (uv.fine > current_date OR uv.fine is null)) "
					+ "as x	on v.targa = x.targa";
			ResultSet rs = driver.execute(query, null);
			while (rs.next())
				res.add(new Veicolo(rs));
		} catch (SQLException e) {
			System.out
					.println("Select failed: An Exception has occurred! " + e);
		}
		return res;
	}

	public static List<Usr_veicolo> getAssociazioniCorrenti() {
		List<Usr_veicolo> res = new ArrayList<>();
		try {
			MioDriver driver = MioDriver.getInstance();
			String query = "select * from usr_veicolo where fine is null order by email";
			ResultSet rs = driver.execute(query, null);
			while (rs.next())
				res.add(new Usr_veicolo(rs));
		} catch (SQLException e) {
			System.out
					.println("Select failed: An Exception has occurred! " + e);
		}
		return res;
	}
	public static boolean associa(String email, String targa) {
		int res = -1;
		try {
			MioDriver driver = MioDriver.getInstance();
			String insert = "INSERT INTO usr_veicolo (email, targa, inizio) "
					+ "VALUES(?, ?, ?)";
			Object[] values = new Object[3];
			values[0] = email;
			values[1] = targa;
			values[2] = new java.sql.Date(System.currentTimeMillis());
			res = driver.update(insert, values);
		} catch (SQLException e) {
			System.out
					.println("Select failed: An Exception has occurred! " + e);
		}
		return res == 1;
	}
	public static boolean termina(/*String email, */String targa) {
		int res = -1;
		try {
			MioDriver driver = MioDriver.getInstance();
			String insert = "UPDATE usr_veicolo "
					+ "SET fine=? WHERE targa=? AND fine IS NULL";
			Object[] values = new Object[2];
			//values[2] = email; // AND email=? 
			values[1] = targa;
			values[0] = new java.sql.Date(System.currentTimeMillis());
			res = driver.update(insert, values);
		} catch (SQLException e) {
			System.out
					.println("Select failed: An Exception has occurred! " + e);
		}
		return res == 1;
	}

	// ==== Getter & Setter
	// ========================================================================

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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
