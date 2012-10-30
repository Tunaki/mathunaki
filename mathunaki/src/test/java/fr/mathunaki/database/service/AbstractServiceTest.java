package fr.mathunaki.database.service;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = { "classpath:applicationContext-test.xml" })
@Transactional
public abstract class AbstractServiceTest extends
		AbstractTestNGSpringContextTests {

	/**
	 * Asserts that the actual object and expected object are both not null and
	 * equal or that they are both null.
	 * 
	 * @param actual Actual object.
	 * @param expected Expected object.
	 */
	protected static void assertEqualsOrBothNull(Object actual, Object expected) {
		if (expected == null) {
			assertNull(actual);
		} else {
			assertEquals(actual, expected);
		}
	}

	protected static void assertEquals(Object expected, Object actual) {
		Assert.assertEquals(expected, actual);
	}

	protected static void assertTrue(boolean condition) {
		Assert.assertTrue(condition);
	}

	protected static void assertFalse(boolean condition) {
		Assert.assertFalse(condition);
	}

	protected static void assertNull(Object object) {
		Assert.assertNull(object);
	}

	protected static void assertNotSame(Object expected, Object actual) {
		Assert.assertNotSame(expected, actual);
	}

	protected Date getDate(int year, int month, int day, int hour, int minute) {
		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(year, month - 1, day, hour, minute, 0);
		return calendar.getTime();
	}

	protected Date getDateWithoutMilliseconds(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
}
