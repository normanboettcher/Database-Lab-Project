package test.model.data.zahlmethoden;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * Diese Testsuite fuehrt alle Testfaelle des package test.model.data.zahlmethoden
 * @author Norman Böttcher
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ KreditkarteTest.class, KlarnaTest.class, PayPalTest.class })
public class ZahlmethodenAllTestSuite {

}
