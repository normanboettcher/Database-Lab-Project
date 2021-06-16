package model.data.zahlmethoden;


/**
 * Diese Klasse definiert die Zahlweise PayPal.
 * 
 * @author Norman Böttcher
 *
 */
public class PayPal extends AbstractClassZahlmethode {
	
	//Konstruktor
	/**
	 * Konstruktor der Klasse PayPal. Zahlmethode wird automatisch auf PayPal
	 * gesetzt. Die Zahlungsfrist betraegt 14 Tage.
	 */
	public PayPal() {
		super();
		this.zahlmethode = "PayPal";
		this.zahlungsfrist = 14;
	}
}
