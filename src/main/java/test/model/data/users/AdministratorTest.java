package test.model.data.users;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;

import model.data.general.Adresse;
import model.data.users.Administrator;
/**
 * Diese Klasse definiert Testfaelle fuer die Klasse Administrator.
 */
public class AdministratorTest {
	
	//Attribute
	/**
	 * Testobjekt Administrator.
	 */
	private Administrator admin;
	
	/**
	 * Diese Methode defniert alle Eigenschaften eines Administrator-Objektes.
	 */
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() {
		admin = new Administrator("Test", "Admin", "email@beispiel.de", new Date(1920, 5, 1),
				new Adresse("Strassengraben", "1a", "12345", "Dorf"));
	}
	/**
	 * Diese Methode prueft die korrekte Ausgabe des Geburtsdatums.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void getGeburtsdatumTest() {
		assertNotNull(admin.getGeburtsdatum());
		System.out.println("Geburtsdatum des Admins");
		System.out.println("-----------------------------------");
		System.out.println("Geburtsdatum: " + admin.getGeburtsdatum().getDay() + "." + admin.getGeburtsdatum().getMonth()
				+ "." + admin.getGeburtsdatum().getYear());
		
		System.out.println("Test von getGeburtsdatum Ende \n");
	}
	/**
	 * Diese Methode prueft, ob die Adresse eines Administrators die korrekte Ausgaben liefert.
	 */
	@Test
	public void getAdresseTest() {
		assertNotNull(admin.getAdresse());
		System.out.println("Adresse des Admins");
		System.out.println("--------------------------------");
		System.out.println(admin.getAdresse().getStrasse() + " "
				+ admin.getAdresse().getHausnummer() + "\n"
				+ admin.getAdresse().getPLZ() + " "
				+ admin.getAdresse().getOrt());
		
	}
	/**
	 * Metode zur Pruefung, ob die korrekte Email des Administrators ausgegeben wird.
	 */
	@Test
	public void getEmailTest() {
		assertTrue(admin.getEmail().equals("email@beispiel.de"));
	}
}
