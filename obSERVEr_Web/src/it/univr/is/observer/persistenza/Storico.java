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
		this.observer = rs.getString(0);
		this.usr = rs.getString(1);
		this.targa = rs.getString(2);
		this.velocita = rs.getInt(3);
		this.posizione = rs.getString(4);
		this.istante = rs.getTimestamp(5);
	}

	public Storico() {

	}

	// ==== Methods
	// ========================================================================

	public String toString(ResultSet rs) throws SQLException {
		String res = "";
		res += rs.getString(0);
		res += rs.getString(1);
		res += rs.getString(2);
		res += rs.getInt(3);
		res += rs.getString(4);
		res += rs.getTimestamp(5)+"\n";
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
		List<String> res = new ArrayList<String>();
		try {
			MioDriver driver = MioDriver.getInstance();
			// TODO: test date(timestamp)
			String query = "select posizione from storico where targa = ? and date(istante) = ?";
			Object[] params = new Object[2];
			params[0] = targa;
			params[1] = data;
			ResultSet rs = driver.execute(query, params);
			// TODO: to string oppure new Storico()?
			while (rs.next())
				res.add(rs.getString(1));
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
			while (rs.next()){
				res.add(rs.getDate(1));
			System.out.println(rs.getDate(1));}
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
