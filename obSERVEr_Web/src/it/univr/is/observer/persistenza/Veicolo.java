package it.univr.is.observer.persistenza;

import it.univr.is.database.MioDriver;
import it.univr.is.observer.logica.Statistica;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 targa char(7) PRIMARY KEY,
 marca varchar(20),
 modello varchar(20),
 gestore varchar(50),
 guidatore varchar(50),
 */

public class Veicolo {

	// ==== Field
	// ========================================================================

	private String targa;
	private String marca;
	private String modello;
	private String gestore;
	private String guidatore;

	// ==== Constructor
	// ========================================================================

	public Veicolo(String targa, String marca, String modello, String gestore,
			String guidatore) {

		this.targa = targa;
		this.marca = marca;
		this.modello = modello;
		this.gestore = gestore;
		this.guidatore = guidatore;
	}

	public Veicolo(ResultSet rs) throws SQLException {
		this.targa = rs.getString("targa");
		this.marca = rs.getString("marca");
		this.modello = rs.getString("modello");
		this.gestore = rs.getString("gestore");
		this.guidatore = rs.getString("email");
	}

	public Veicolo() {

	}

	// ==== Methods
	// ========================================================================

	/**
	 * Ritorno i dati del veicolo con targa "targa"
	 * 
	 * @param targa
	 * @return
	 */
	public Veicolo getVeicoloData(String targa) {
		Veicolo res = null;
		try {
			MioDriver driver = MioDriver.getInstance();
			String query = "select * from veicolo where targa = ?";
			Object[] params = new Object[1];
			params[0] = targa;
			ResultSet rs = driver.execute(query, params);
			if (rs.next())
				res = new Veicolo(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Ritorno i dati di tutti veicoli
	 * 
	 * @return
	 */
	public static List<Veicolo> getVeicoli() {
		List<Veicolo> res = new ArrayList<>();
		try {
			MioDriver driver = MioDriver.getInstance();
			String query = "select * from veicolo";
			ResultSet rs = driver.execute(query, null);
			while (rs.next())
				res.add(new Veicolo(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Ritorno una matrice contenente le statistiche dei veicoli visibili
	 * dall'utente email
	 *
	 * @param targa
	 * @param inzio
	 * @param fine
	 * @return
	 */
	public static List<Integer> getStatisticheVeicolo(String targa,
			String inizio, String fine) {

		java.sql.Date dataInizio = null;
		java.sql.Date dataFine = null;

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			dataInizio = new java.sql.Date(formatter.parse(inizio).getTime());
			dataFine = new java.sql.Date(formatter.parse(fine).getTime());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		String query = "";
		Object[] params = null;
		MioDriver driver;
		ResultSet rs = null;
		List<Integer> velocita = new ArrayList<>();

		try {
			driver = MioDriver.getInstance();
			query = "select s.velocita from "
					+ "storico s where date(s.istante) >= ? "
					+ "AND date(s.istante) <= ? AND s.targa = ?";
			params = new Object[3];
			params[0] = dataInizio;
			params[1] = dataFine;
			params[2] = targa;

			rs = driver.execute(query, params);
			while (rs.next())
				// Popolo lista velocita
				velocita.add(rs.getInt(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return velocita;
	}

	public static int getStatistica(List<Integer> velocita, Statistica s) {
		return s.calcola(velocita);
	}

	// ==== Getter & Setter
	// ========================================================================

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModello() {
		return modello;
	}

	public void setModello(String modello) {
		this.modello = modello;
	}

	public String getGestore() {
		return gestore;
	}

	public void setGestore(String gestore) {
		this.gestore = gestore;
	}

	public String getGuidatore() {
		return guidatore;
	}

	public void setGuidatore(String guidatore) {
		this.guidatore = guidatore;
	}

}