package it.univr.is.observer.persistenza;

/*
 serial char(10) PRIMARY KEY
 */

public class Observer {

	// ==== Field
	// ========================================================================
	
	private String serial;

	// ==== Constructor
	// ========================================================================

	public Observer(String serial) {
		this.serial = serial;
	}

	public Observer() {

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

}
