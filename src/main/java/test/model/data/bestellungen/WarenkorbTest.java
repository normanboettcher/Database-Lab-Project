package test.model.data.bestellungen;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.data.bestellungen.Warenkorb;
import model.data.filme.Film;
import model.data.filme.Produzent;
/**
 * Diese Klasse definert Testfaelle fuer die Klasse Warenkorb.
 * @author Norman Böttcher
 *
 */
public class WarenkorbTest {
	/**
	 * Testobjekt Warenkorb.
	 */
	private Warenkorb wrnkrb;
	/**
	 * Diese Methode erzeugt ein Warekorb-Objekt.
	 */
	@Before
	public void setUp() {
		wrnkrb = new Warenkorb();
	}
	/**
	 * Diese Methode gibt die Liste zurueck, mit der ein Warekorb produkte speichert und ausgibt.
	 */
	@Test
	public void zeigeVollenWarenkorbTest() {
		wrnkrb.zeigeVollenWarenkorb().add(new Film(1, "", 4.99, "", new Produzent(1, "", ""), null));
		assertTrue(wrnkrb.zeigeVollenWarenkorb() != null);
		assertTrue(wrnkrb.zeigeVollenWarenkorb().size() == 1);
	}
}
