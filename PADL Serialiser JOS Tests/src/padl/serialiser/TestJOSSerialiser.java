package padl.serialiser;

import junit.framework.Test;
import junit.framework.TestSuite;
import padl.serialiser.test.TestArgoUML0198;
import padl.serialiser.test.TestArgoUML020;
import padl.serialiser.test.TestComposite;
import padl.serialiser.test.TestJHotDraw;

/**
 * @author Yann 
 * since   2009/03/21
 */
public class TestJOSSerialiser extends TestSuite {
	public TestJOSSerialiser() {
		super();
	}
	public TestJOSSerialiser(final Class theClass) {
		super(theClass);
	}
	public TestJOSSerialiser(final String name) {
		super(name);
	}
	public static Test suite() {
		final TestJOSSerialiser suite = new TestJOSSerialiser();

		suite.addTestSuite(TestComposite.class);
		suite.addTestSuite(TestJHotDraw.class);
		suite.addTestSuite(TestArgoUML0198.class);
		suite.addTestSuite(TestArgoUML020.class);

		return suite;
	}
}
