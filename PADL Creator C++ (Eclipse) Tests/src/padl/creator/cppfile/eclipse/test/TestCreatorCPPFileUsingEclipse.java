/* (c) Copyright 2008 and following years, Yann-Gaël Guéhéneuc,
 * École Polytechnique de Montréal.
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
package padl.creator.cppfile.eclipse.test;

import junit.framework.Test;
import junit.framework.TestSuite;
import padl.creator.cppfile.eclipse.test.big.ChromeTest;
import padl.creator.cppfile.eclipse.test.big.QMakeTest;
import padl.creator.cppfile.eclipse.test.simple.ClassesTest;
import padl.creator.cppfile.eclipse.test.simple.FieldAccessTest;
import padl.creator.cppfile.eclipse.test.simple.GetOrCreateTest;
import padl.creator.cppfile.eclipse.test.simple.SimpleTest;
import padl.creator.cppfile.eclipse.test.simple.Simple2Test;
import padl.creator.cppfile.eclipse.test.simple.Simple3Test;
import padl.creator.cppfile.eclipse.test.simple.StructuresTest;

/**
 * 
 * @author Yann
 * @since  2013/05/04
 *
 */
public final class TestCreatorCPPFileUsingEclipse extends TestSuite {
	public TestCreatorCPPFileUsingEclipse() {
	}
	public TestCreatorCPPFileUsingEclipse(final Class theClass) {
		super(theClass);
	}
	public TestCreatorCPPFileUsingEclipse(final String name) {
		super(name);
	}
	public static Test suite() {
		final TestCreatorCPPFileUsingEclipse suite =
			new TestCreatorCPPFileUsingEclipse();

		suite.addTestSuite(ClassesTest.class);
		suite.addTestSuite(FieldAccessTest.class);
		suite.addTestSuite(SimpleTest.class);
		suite.addTestSuite(GetOrCreateTest.class);
		suite.addTestSuite(Simple2Test.class);
		suite.addTestSuite(Simple3Test.class);
		suite.addTestSuite(StructuresTest.class);

		suite.addTestSuite(QMakeTest.class);
		suite.addTestSuite(ChromeTest.class);

		return suite;
	}
}
