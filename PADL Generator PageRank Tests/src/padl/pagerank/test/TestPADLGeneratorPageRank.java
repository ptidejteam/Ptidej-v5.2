package padl.pagerank.test;

import junit.framework.Test;
import junit.framework.TestSuite;
import padl.pagerank.test.cases.TestCPP;

/**
 * @author yann
 */
public class TestPADLGeneratorPageRank extends TestSuite {
	public TestPADLGeneratorPageRank() {
	}
	public TestPADLGeneratorPageRank(final Class theClass) {
		super(theClass);
	}
	public TestPADLGeneratorPageRank(final String name) {
		super(name);
	}
	public static Test suite() {
		final TestPADLGeneratorPageRank suite = new TestPADLGeneratorPageRank();
		suite.addTestSuite(TestCPP.class);
		return suite;
	}
}