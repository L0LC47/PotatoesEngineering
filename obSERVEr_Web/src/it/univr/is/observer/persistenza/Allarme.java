package it.univr.is.observer.persistenza;

/*
 observer char(10) PRIMARY KEY,
 velocita SMALLINT NOT NULL DEFAULT 130,
 */

public class Allarme {

	// ==== Field
	// ========================================================================

	private String observer;
	private int velocita;

	// ==== Constructor
	// ========================================================================

	public Allarme(String observer, int velocita) {
		this.observer = observer;
		this.velocita = velocita;
	}

	public Allarme() {

	}

	// ==== Methods
	// ========================================================================

	// ==== Getter & Setter
	// ========================================================================

	public String getObserver() {
		return observer;
	}

	public void setObserver(String observer) {
		this.observer = observer;
	}

	public int getVelocita() {
		return velocita;
	}

	public void setVelocita(int velocita) {
		this.velocita = velocita;
	}

}
