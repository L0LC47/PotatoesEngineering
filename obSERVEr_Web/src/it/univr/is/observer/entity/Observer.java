package it.univr.is.observer.entity;

public interface Observer {
	
	// Comunicazione diretta tra PC e dispositivo
	public Posizione getPosizione();
	public int getVelocita();

}