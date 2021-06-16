package test.model.data.film;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.data.filme.Produzent;
/**
 * Diese Testklasse definiert Testfaelle fuer einen Produzenten.
 * @author Norman Böttcher
 *
 */
public class ProduzentTest {
	/**
	 * Testobjekt Produzent.
	 */
	private Produzent prod;
	
	/**
	 * In dieser Methode werden die Eigenschaften eines Produzent- Objektes definiert.
	 */
	@Before
	public void setUp() {
		prod = new Produzent(100, "Hans", "Hansson");
	}
	/**
	 * Test, ob aus den Funktionen getVorname() und getNachname() der korrekte ganze Name als Strig gebildet wird.
	 */
	@Test
	public void ganzerNameProduzentTest() {
		assertTrue(prod.ganzerNameProduzent().equals("Hans Hansson"));
	}
	/**
	 * Test, ob die korrekte ID eines Produzenten ausgegeben wird.
	 */
	@Test
	public void getIdTest() {
		assertTrue(prod.getID() == 100);
	}

}
