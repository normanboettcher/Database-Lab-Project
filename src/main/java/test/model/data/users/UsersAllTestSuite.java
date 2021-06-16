package test.model.data.users;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * Diese Testsuite fuehrt alle Testfaelle des package test.model.data.users aus.
 * @author Norman Böttcher
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ AdministratorTest.class, KundeTest.class })
public class UsersAllTestSuite {

}
