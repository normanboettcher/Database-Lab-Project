package model.data.zahlmethoden;

/**
 * Diese Klasse definiert die Zahlweise 'Auf Rechnung'.
 * 
 * @author Norman Böttcher
 *
 */
public class Kreditkarte extends AbstractClassZahlmethode {
	//Konstruktor
	/**
	 * Konstruktor der Klasse Kreditkarte. Die Zahlmethode wird automatisch auf
	 * 'Kreditkarte' gesetzt. Die Zahlungsfrist betraegt 14 Tage.
	 */
	public Kreditkarte() {
		this.zahlmethode = "Kreditkarte";
		this.zahlungsfrist = 14;
	}
}
