package padl.generator.test;

import junit.framework.Test;
import junit.framework.TestSuite;
import padl.generator.test.cases.SanityTest;

/**
 * @author yann
 */
public class TestPADLGenerator extends TestSuite {
	public TestPADLGenerator() {
	}
	public TestPADLGenerator(final Class theClass) {
		super(theClass);
	}
	public TestPADLGenerator(final String name) {
		super(name);
	}
	public static Test suite() {
		final TestPADLGenerator suite = new TestPADLGenerator();
		suite.addTestSuite(SanityTest.class);
		return suite;
	}
}