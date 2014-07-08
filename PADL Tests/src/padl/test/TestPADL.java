/*
 * (c) Copyright 2001-2004 Yann-Gaël Guéhéneuc,
 * University of Montréal.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the author, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN,
 * ANY LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHOR IS ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.test;

import junit.framework.Test;
import junit.framework.TestSuite;
import padl.kernel.impl.AbstractContainerTest;
import padl.kernel.impl.MethodInvocationTest;
import padl.test.clone.TestCloning;
import padl.test.defaultpackage.TestDefaultPackage;
import padl.test.path.TestPath;
import padl.test.remove.TestRemove;
import padl.test.setter.TestGetterAndSetter;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/01/25
 */
public final class TestPADL extends TestSuite {
	public TestPADL() {
	}
	public TestPADL(final Class theClass) {
		super(theClass);
	}
	public TestPADL(final String name) {
		super(name);
	}
	public static Test suite() {
		final TestPADL suite = new TestPADL();
		suite.addTestSuite(AbstractContainerTest.class);
		suite.addTestSuite(MethodInvocationTest.class);
		suite.addTest(TestCloning.suite());
		suite.addTest(TestRemove.suite());
		suite.addTest(TestGetterAndSetter.suite());
		suite.addTestSuite(TestPath.class);
		suite.addTestSuite(TestDefaultPackage.class); // Added by Mathieu Lemoine, 2009-05-15
		return suite;
	}
}
