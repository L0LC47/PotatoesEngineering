package it.univr.is.observer.persistenza;

import it.univr.is.database.MioDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 observer char(10) PRIMARY KEY,
 usr character varying(50),
 targa char(7),
 velocita SMALLINT,
 posizione char(19),
 instante timestamp,
 */
public class Storico {

	// ==== Field
	// ========================================================================

	private String observer;
	private String usr;
	private String targa;
	private int velocita;
	private String posizione;
	private Timestamp istante;

	// ==== Constructor
	// ========================================================================

	public Storico(String observer, String usr, String targa, int velocita,
			String posizione, Timestamp istante) {
		this.observer = observer;
		this.usr = usr;
		this.targa = targa;
		this.velocita = velocita;
		this.posizione = posizione;
		this.istante = istante;
	}

	public Storico(ResultSet rs) throws SQLException {
		this.observer = rs.getString(1);
		this.usr = rs.getString(2);
		this.targa = rs.getString(3);
		this.velocita = rs.getInt(4);
		this.posizione = rs.getString(5);
		this.istante = rs.getTimestamp(6);
	}

	public Storico() {

	}

	// ==== Methods
	// ========================================================================

	public String toString(ResultSet rs) throws SQLException {
		String res = "";
		res += rs.getString(1);
		res += rs.getString(2);
		res += rs.getString(3);
		res += rs.getInt(4);
		res += rs.getString(5);
		res += rs.getTimestamp(6) + "\n";
		return res;
	}

	/**
	 * Ritorno un vettore di stringhe contenente le righe rappresentanti lo
	 * storico del veicolo "targa" nel giorno selezionato
	 *
	 * @param targa
	 * @param giorno
	 * @return
	 */
	public static List<String> getStorico(String targa, Date data) {
		System.out.println(data);
		List<String> res = new ArrayList<String>();
		try {
			MioDriver driver = MioDriver.getInstance();
			String query = "select posizione from storico where targa = ? and date(istante) = ?";
			Object[] params = new Object[2];
			params[0] = targa;
			params[1] = new java.sql.Date(data.getTime());
			ResultSet rs = driver.execute(query, params);
			while (rs.next())
				res.add(rs.getString(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static List<Storico> getAllarmi(String targa) {
		List<Storico> res = new ArrayList<Storico>();
		try {
			MioDriver driver = MioDriver.getInstance();
			String query = "SELECT * FROM storico s, allarme a WHERE targa = ? "
					+ "AND s.observer = a.observer AND s.velocita > a.velocita";
			Object[] params = new Object[1];
			params[0] = targa;
			ResultSet rs = driver.execute(query, params);
			while (rs.next())
				res.add(new Storico(rs));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * Ritorno un vettore di stringhe contenente le righe rappresentanti lo
	 * storico del veicolo "targa"
	 *
	 * @param targa
	 * @param giorno
	 * @return
	 */

	public static List<Date> getUserVeicoloDate(String targa) {
		List<Date> res = new ArrayList<Date>();
		try {
			MioDriver driver = MioDriver.getInstance();
			String query = "select distinct date(istante) from storico where targa = ?";
			Object[] params = new Object[1];
			params[0] = targa;
			ResultSet rs = driver.execute(query, params);
			while (rs.next()) 
				res.add(rs.getDate(1));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	// ==== Getter & Setter
	// ========================================================================

	public String getObserver() {
		return observer;
	}

	public void setObserver(String observer) {
		this.observer = observer;
	}

	public String getUsr() {
		return usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public String getTarga() {
		return targa;
	}

	public void setTarga(String targa) {
		this.targa = targa;
	}

	public int getVelocita() {
		return velocita;
	}

	public void setVelocita(int velocita) {
		this.velocita = velocita;
	}

	public String getPosizione() {
		return posizione;
	}

	public void setPosizione(String posizione) {
		this.posizione = posizione;
	}

	public Timestamp getIstante() {
		return istante;
	}

	public void setIstante(Timestamp istante) {
		this.istante = istante;
	}

}
