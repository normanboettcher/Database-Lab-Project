package test.model.data.bestellungen;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import model.data.bestellungen.Rechnung;

import model.data.general.Adresse;
import model.data.users.Kunde;
import model.data.zahlmethoden.PayPal;
/**
 * Diese Testklasse definiert Testfaelle fuer die Klasse Rechnung.
 * @author Norman Böttcher
 *
 */
public class RechnungTest {
	/**
	 * Testobjekt Rechnung
	 * Testobjekt Kunde(als Empfaenger)
	 */
	private Rechnung r;
	private Kunde k;
	
	/**
	 * Diese Methode definiert alle Eigenschaften eines Kunden und Rechnungsobjektes.
	 */
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		k = new Kunde("Test", "Kunde", "eineMail@mail.com", new Date(1999, 3, 4), 
				new Adresse("Teststrasse", "77", "99872", "Testort"));
		k.setZahlmethode(new PayPal());
		r = new Rechnung(100, k, new Date(2020, 6,7));
		r.setPfad("einPfad/zurRechnung.pdf");
	}
	/**
	 * Test, ob richtiger Empfaenger ausgegeben wird.
	 */
	@Test
	public void getEmpfaengerTest() {
		assertTrue(r.getEmpfaenger() != null);
		assertTrue(r.getEmpfaenger().getEmail().equals("eineMail@mail.com"));
	}
	/**
	 * Prueft, ob Rechnung die richtige ID ausgibt.
	 */
	@Test
	public void getRechnungsIDTest() {
		assertTrue(r.getRechnungsID() == 100);
	}
	/**
	 * Prueft das korrekte Rechnungsdatum einer Rechnung.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void getRechnungsdatumTest() {
		assertTrue(r.getRechnungsdatum().getDate() == 7);
		assertTrue(r.getRechnungsdatum().getMonth() == 6);
		assertTrue(r.getRechnungsdatum().getYear() == 2020);
	}
	/**
	 * Prueft die korrekte Zahlugsfrist der Zahlmethode eines Empfaengers ausgegeben wird.
	 */
	@Test
	public void getZahlungsfristTest() {
		assertTrue(r.getZahlungsfrist() == 14);
	}
	/**
	 * Prueft den korrekten Pfad einer Rechnung.
	 */
	@Test
	public void getPfadTest() {
		assertTrue(r.getPfad().equals("einPfad/zurRechnung.pdf"));
	}
	
	//====================================================ANMERKUNG===========================================================
		/*
		 *Die Nachfolgenden Methoden weisen jeweils die uebergebenen Werte einem Objekt zu.
		 *Das Objekt sollte anschliessend in der Lage sein, die uebergebenen Parameter ueber seine getter-Methoden auszugeben.
		 *Im Programm finden diese Methoden Anwendung, um Parameter aus der Datenbank einem lokalen Objekt innerhalb von Java
		 *zuweisen zu koennen.   
		 */
	/**
	 * Test, ob einer Rechnung die korrekte Zahlungsfrist zugewiesen wird.
	 */
	@Test
	public void holeZahlungsfristTest() {
		r.holeZahlungsfrist(14);
		assertTrue(r.getZahlungsfrist() == 14);
	}
	/**
	 * Test, ob einer Rechnung der korrekte Bezahlstatus zugewiesen wird.
	 */
	@Test
	public void holeZahlstatusTest() {
		r.holeZahlstatus("offen");
		assertTrue(r.getBezahlstatus().equals("offen"));
	}
	/**
	 * Test, ob einer Rechnung die korrekte Zahlmethode zugewiesen wird.
	 */
	@Test
	public void holeZahlmethodeTest() {
		r.holeZahlungsmethode("PayPal");
	}
	/**
	 * Test, ob einer Rechnung der korrekte Betrag zugewiesen wird.
	 */
	@Test
	public void holeBetragTest() {
		r.holeBetrag(55.88);
		assertTrue(r.getGesamtBetragRechnung() == 55.88);
	}
	/**
	 * Test, ob einer Rechnung die korrekten Produkten zugewiesen wird.
	 */
	@Test
	public void holeProdukteTest() {
		r.holeProdukte("Film1, Film2,");
		assertTrue(r.getProdukte().equals("Film1, Film2"));
	}
}
