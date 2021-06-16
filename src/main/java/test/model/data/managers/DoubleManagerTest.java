package test.model.data.managers;

import static org.junit.Assert.*;

import org.junit.Test;

import model.data.managers.DoubleManager;
/**
 * Diese Klasse definiert Testfaelle fuer die Klasse DoubleManager.
 * @author Norman Böttcher
 *
 */
public class DoubleManagerTest {
	/**
	 * Diese Klasse testet, ob ein double-Wert korrekt gerundet wird.
	 */
	@Test
	public void roundTest() {
		double number1 = 2.222222222222222222222;
		double number2 = 1.789264658520;
		double number3 = 1.5;
		double number4 = 7;
		
		assertTrue(DoubleManager.round(number1, 2) == 2.22);
		assertTrue(DoubleManager.round(number2, 2) == 1.79);
		assertTrue(DoubleManager.round(number3, 2) == 1.5);
		assertTrue(DoubleManager.round(number4, 2) == 7);
	}

}
