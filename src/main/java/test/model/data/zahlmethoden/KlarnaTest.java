package test.model.data.zahlmethoden;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.data.zahlmethoden.Klarna;
/**
 * Diese Klasse definiert Testfaelle der Klasse Klarna.
 * @author Norman Böttcher
 *
 */
public class KlarnaTest {

	//Attribute
	/**
	 * Testobjekt Klarna.
	 */
	private Klarna klarna;
	
	/**
	 * Erzeugen des Testojektes Klarna.
	 */
	@Before
	public void setUp() {
		klarna = new Klarna();
	}
	/**
	 * Pruefung der korrekten Bezeichnung der Zahlmethode.
	 */
	@Test
	public void getZahlmethodeTest() {
		assertTrue(klarna.getZahlmethodeBezeichnung().equals("Klarna"));
	}
	/**
	 * Pruefung der korrekte Zahlugsfrist der Zahlmethode.
	 */
	@Test
	public void getZahlungsfristTest() {
		assertTrue(klarna.getZahlungsfrist() == 14);
	}
		
}
