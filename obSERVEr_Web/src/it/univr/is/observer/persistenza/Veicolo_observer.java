package it.univr.is.observer.persistenza;

import java.util.Date;

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
