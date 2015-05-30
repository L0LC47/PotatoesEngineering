package it.univr.is.observer.persistenza;

import java.util.Date;

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

	// ==== Methods
	// ========================================================================

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
