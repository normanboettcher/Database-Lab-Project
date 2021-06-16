package test.all;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * In dieser Testsuite werden alle Tests des Projektes ausgefuehrt.
 * @author Norman Böttcher
 *
 */
@RunWith(Suite.class)
@SuiteClasses({test.model.data.film.FilmAllTestSuite.class, test.model.data.general.GeneralAllTestSuite.class,
	test.model.data.zahlmethoden.ZahlmethodenAllTestSuite.class, test.model.data.users.UsersAllTestSuite.class})
public class AllTestSuite {

}
