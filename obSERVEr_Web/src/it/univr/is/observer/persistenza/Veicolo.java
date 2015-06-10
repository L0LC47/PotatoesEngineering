package it.univr.is.observer.persistenza;

import it.univr.is.database.MioDriver;

import java.sql.ResultSet;
import java.sql.SQLException;

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
			if(rs.next())
				res = new Veicolo(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
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
