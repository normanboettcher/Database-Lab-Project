package test.model.data.zahlmethoden;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.data.zahlmethoden.Kreditkarte;

/**
 * Diese Klasse definiert Testfaelle der Klasse Kreditkarte.
 * @author Norman Böttcher
 *
 */
public class KreditkarteTest {

	
	//Attribute
	/**
	 * Testobjekt Kreditkarte.
	 */
	private Kreditkarte kredit;
	
	/**
	 * Erzeugen des Testojektes Kreditkarte.
	 */
	@Before
	public void setUp() {
		kredit = new Kreditkarte();
	}
	/**
	 * Pruefung der korrekten Bezeichnung der Zahlmethode.
	 */
	@Test
	public void getZahlmethodeTest() {
		assertTrue(kredit.getZahlmethodeBezeichnung().equals("Kreditkarte"));
	}
	/**
	 * Pruefung der korrekte Zahlungsfrist der Zahlmethode.
	 */
	@Test
	public void getZahlungsfristTest() {
		assertTrue(kredit.getZahlungsfrist() == 14);
	}
		
		
}
