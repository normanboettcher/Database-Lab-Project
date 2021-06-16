package test.model.data.zahlmethoden;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.data.zahlmethoden.PayPal;

/**
 * Diese Klasse definiert Testfaelle der Klasse PayPal.
 * @author Norman Böttcher
 *
 */
public class PayPalTest {
	//Attribute
	/**
	 * Testobjekt PayPal.
	 */
	private PayPal paypal;
	
	/**
	 * Erzeugen des Testojektes PayPal.
	 */
	@Before
	public void setUp() {
		paypal = new PayPal();
	}
	/**
	 * Pruefung der korrekten Bezeichnung der Zahlmethode.
	 */
	@Test
	public void getZahlmethodeTest() {
		assertTrue(paypal.getZahlmethodeBezeichnung().equals("PayPal"));
	}
	/**
	 * Pruefung der korrekte Zahlungsfrist der Zahlmethode.
	 */
	@Test
	public void getZahlungsfristTest() {
		assertTrue(paypal.getZahlungsfrist() == 14);
	}


}
