package test.model.data.managers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * Diese Testsuite fuehrt alle Testfaelle des package test.model.data.managers aus.
 * @author Norman Böttcher
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ DatumsManagerTest.class, DoubleManagerTest.class, PasswortManagerTest.class })
public class ManagersAllTestSuite {

}
