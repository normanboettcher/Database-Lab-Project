package model.data.zahlmethoden;

/**
 * Diese Klasse definiert die Zahlweise Klarna.
 * 
 * @author Norman Böttcher
 *
 */
public class Klarna extends AbstractClassZahlmethode {
	//Konstruktor
	/**
	 * Konstruktor der Klasse Klarna. Zahlmethode wird automatisch auf Klarna
	 * gesetzt. Die Zahlungsfrist betraegt 14 Tage.
	 */
	public Klarna() {
		super();
		this.zahlmethode = "Klarna";
		this.zahlungsfrist = 14;
	}
}
