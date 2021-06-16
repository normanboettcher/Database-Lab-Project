package test.model.data.users;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import model.data.general.Adresse;
import model.data.users.Kunde;
import model.data.zahlmethoden.PayPal;
/**
 * Diese Klasse definiert Testfaelle fuer die Klasse Kunde.
 * @author Norman Böttcher
 *
 */
public class KundeTest {
	
	//Attribute
	/**
	 * Testobjekt Kunde.
	 */
	private Kunde kunde;
	
	/**
	 * Diese Methode definiert alle Eigenschaften eines Testobjekts Kunde.
	 */
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		kunde = new Kunde("Tim", "Tesla", "kunde@web.de", new Date(1900, 1, 1),
				new Adresse("Am Scheunentor", "27", "11111", "Dorfküche"));
	}
	/**
	 * Diese Methode prueft die korrekte Ausgabe des Geurtsdatums eines Kunden.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void getGeburtsdatumTest() {
		assertNotNull(kunde.getGeburtsdatum());
		
		System.out.println("Geburtstag des Kunden: " + kunde.getGeburtsdatum().getDay() 
				+ "." + kunde.getGeburtsdatum().getMonth()
				+ "." + kunde.getGeburtsdatum().getYear());
		System.out.println("------------------------------");
	}
	/**
	 * Diese Metode prueft die korrekte Ausgabe der Adresse eies Kunden.
	 */
	@Test
	public void getAdresseTest() {
		assertNotNull(kunde.getAdresse());
		
		System.out.println("Adresse des Kunden");
		System.out.println(kunde.getAdresse().getStrasse() + " " + kunde.getAdresse().getHausnummer()
				+ "\n" + kunde.getAdresse().getPLZ() + " "
				+ kunde.getAdresse().getOrt());
	}
	/**
	 * Diese Methode prueft die korrekte Zahlmethode eines Kunden.
	 */
	@Test
	public void getZahlmethodeBezeichnungTest() {
		kunde.setZahlmethode(new PayPal());
		
		assertTrue(kunde.getZahlmethode().getZahlmethodeBezeichnung().equals("PayPal"));
		assertTrue(kunde.getZahlmethode().getZahlungsfrist() == 14);
	}
	/**
	 * Korrekte Ausgabe der Email eines Kunden wird geprueft.
	 */
	@Test
	public void getEmailTest() {
		assertTrue(kunde.getEmail().equals("kunde@web.de"));
	}
}
