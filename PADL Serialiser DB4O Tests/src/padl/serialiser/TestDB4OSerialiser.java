package padl.serialiser;

import junit.framework.Test;
import junit.framework.TestSuite;
import padl.serialiser.test.TestDB4OSerialiserArgoUML020;
import padl.serialiser.test.TestDB4OSerialiserJHotDraw51;
import padl.serialiser.test.TestDB4OSerialiserSanity;

/**
 * @author Yann 
 * since   2010/04/11
 */
public class TestDB4OSerialiser extends TestSuite {
	public TestDB4OSerialiser() {
	}
	public TestDB4OSerialiser(final Class theClass) {
		super(theClass);
	}
	public TestDB4OSerialiser(final String name) {
		super(name);
	}
	public static Test suite() {
		final TestDB4OSerialiser suite = new TestDB4OSerialiser();
		suite.addTestSuite(TestDB4OSerialiserSanity.class);
		suite.addTestSuite(TestDB4OSerialiserJHotDraw51.class);
		suite.addTestSuite(TestDB4OSerialiserArgoUML020.class);
		return suite;
	}
}
