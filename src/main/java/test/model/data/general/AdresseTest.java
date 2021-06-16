package test.model.data.general;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import model.data.general.Adresse;
/**
 * Diese Klasse definiert Testfaelle fuer die Klasse Adresse.
 * @author Norman Böttcher
 *
 */
public class AdresseTest {
	
	//Attribute
	/**
	 * Testobjekt Adresse.
	 */
	private Adresse adrs;
	
	/**
	 * In dieser Methode werden die Eigenschaften einer Testadresse definiert.
	 */
	@Before
	public void setUp() {
		adrs = new Adresse("Musterstrasse", "12", "56421", "Dorf am Rand");
	}
	/**
	 * Diese Testklasse prueft die korrekte Ausgabe der Strasse einer Adresse.
	 */
	@Test
	public void getStrasseTest() {
		assertTrue(adrs.getStrasse().equals("Musterstrasse"));
	}
	/**
	 * Diese Testklasse prueft die korrekte Ausgabe der Hausnummer einer Adresse.
	 */
	@Test
	public void getHausnummerTest() {
		assertTrue(adrs.getHausnummer().equals("12"));
	}
	/**
	 * Diese Testklasse prueft die korrekte Ausgabe der Postleitzahl einer Adresse.
	 */
	@Test
	public void getPLZTest() {
		assertTrue(adrs.getPLZ().equals("56421"));
	}
	/**
	 * Diese Testklasse prueft die korrekte Ausgabe des Ortes einer Adresse.
	 */
	@Test
	public void getOrtTest() {
		assertTrue(adrs.getOrt().equals("Dorf am Rand"));
	}
}
