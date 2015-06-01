package it.univr.is.observer.persistenza;

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
	private int istante;

	// ==== Constructor
	// ========================================================================

	public Storico(String observer, String usr, String targa, int velocita,
			String posizione, int istante) {
		this.observer = observer;
		this.usr = usr;
		this.targa = targa;
		this.velocita = velocita;
		this.posizione = posizione;
		this.istante = istante;
	}

	public Storico() {

	}

	// ==== Methods
	// ========================================================================

	/*
	 * Ritorno un vettore di stringhe contenente le posizione memorizzate per la
	 * macchina "targa" nel giorno selezionato
	 */

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

	public int getIstante() {
		return istante;
	}

	public void setIstante(int istante) {
		this.istante = istante;
	}

}
