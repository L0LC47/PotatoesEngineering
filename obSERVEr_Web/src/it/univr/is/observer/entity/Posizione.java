package it.univr.is.observer.entity;

//-90.123456,-180.123456 (nord-est +)

public class Posizione {

	// ==== Field
	// ========================================================================

	private int latitudine;
	private int longitudine;
	private static final int precisione = 1000000;

	// ==== Constructor
	// ========================================================================

	public Posizione(int latitudine, int longitudine) {
		this.latitudine = latitudine;
		this.longitudine = longitudine;
	}

	// TODO test
	public Posizione(String position) {
		String temp[] = position.split(",");
		this.latitudine = (int) (Float.parseFloat(temp[0]) * precisione);
		this.longitudine = (int) (Float.parseFloat(temp[1]) * precisione);
	}

	public Posizione() {

	}

	// ==== Methods
	// ========================================================================

	// TODO test
	@Override
	public String toString() {
		return (this.latitudine / precisione) + "."
				+ (this.latitudine - this.latitudine / precisione) + ","
				+ (this.longitudine / precisione) + "."
				+ (this.longitudine - this.longitudine / precisione);
	}

	// ==== Getter & Setter
	// ========================================================================

	public int getLatitudine() {
		return latitudine;
	}

	public void setLatitudine(int latitudine) {
		this.latitudine = latitudine;
	}

	public int getLongitudine() {
		return longitudine;
	}

	public void setLongitudine(int longitudine) {
		this.longitudine = longitudine;
	}

}
