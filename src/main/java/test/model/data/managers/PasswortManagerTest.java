package test.model.data.managers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import model.data.managers.PasswortManager;
/**
 * Diese Klasse definiert Testfaelle fuer die Klasse PasswortManager.
 * @author Norman Böttcher
 *
 */
public class PasswortManagerTest {
	/**
	 * Diese Metode prueft, ob er uebergebees Passwort als String korrekt gehasht wird.
	 */
	@Test
	public void generateHashTest() {
		String passwort = "AdministratorPasswort1";
		/*
		 * Es wird das passwort gehasht.
		 */
		try {
			String pw_hash = PasswortManager.generateHash(passwort);
			//System.out.println(pw_hash);
			
			/*
			 *Simuliere LogIn. 
			 */
			String pw_login_richtig = PasswortManager.generateHash("AdministratorPasswort1");
			String pw_login_falsch = PasswortManager.generateHash("AdministratorPasswort");
			
			assertTrue(pw_login_richtig.equals(pw_hash));
			assertFalse(pw_login_falsch.equals(pw_hash));
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
