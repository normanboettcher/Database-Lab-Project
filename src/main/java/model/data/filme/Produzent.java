package model.data.filme;

import model.data.general.AbstractClassPerson;

/**
 * Diese Klasse definiert einen Produzenten.
 * 
 * @author Norman Böttcher
 *
 */
public class Produzent extends AbstractClassPerson {
	
	//Attribute
	private int id;
	
	//Konstruktor
	/**
	 * Konsturktor der Klasse Produzent.
	 * 
	 * @param id       die ID des Produzenten.
	 * @param vorname  der Vorname des Produzenten.
	 * @param nachname der nachname des Produzenten.
	 */
	public Produzent(int id, String vorname, String nachname) {
		super(vorname, nachname);
		this.id = id;
	}
	
	//public- Methoden
	/**
	 * Methode fuer den zugriff auf die ID des Produzenten.
	 * 
	 * @return id die ID des Produzenten.
	 */
	public int getID() {
		return id;
	}

	/**
	 * Methode um den Namen des Produzenten als einheitlichen String auszugeben im
	 * Format Vorname + Nachname.
	 * 
	 * @return der ganze Name des Produzenten.
	 */
	public String ganzerNameProduzent() {
		return getVorname() + " " + getNachname();
	}
}
