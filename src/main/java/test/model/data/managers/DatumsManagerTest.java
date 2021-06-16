package test.model.data.managers;

import static org.junit.Assert.*;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.junit.Test;

import model.data.managers.DatumsManager;
/**
 * Diese Klasse definiert Testfaelle fuer die Klasse DatumsManager.
 * @author Norman Böttcher
 *
 */
public class DatumsManagerTest {
	/**
	 * Diese Methode testet, ob das korrekte aktuelle Datum ausgegeben wird.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void aktuellesDatumTest() {
		Date heute = DatumsManager.aktuellesDatum();
		System.out.println(heute.toString());
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		format.format(heute);
		System.out.println(format.format(heute));
		
		String date = format.format(heute);
		
		Date date_before = new Date(2020-1900, 6, 1);
		Date date_after = new Date(2020 - 1900, 11, 24);
		
		System.out.println(date_before);
		
		assertTrue(heute.after(date_before));
		assertTrue(heute.before(date_after));
		assertTrue(date.equals(format.format(heute)));
		assertTrue(heute.equals(DatumsManager.aktuellesDatum()));
	}
}
