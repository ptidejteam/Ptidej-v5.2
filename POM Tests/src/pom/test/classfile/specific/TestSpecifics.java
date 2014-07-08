/** (c) Copyright 2001 and following years, Yann-Gaël Guéhéneuc,
 * University of Montreal.
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
package pom.test.classfile.specific;

import junit.framework.Test;
import junit.framework.TestSuite;

public class TestSpecifics {
	public static Test suite() {
		final TestSuite suite = new TestSuite("Test for pom.test.specific");

		suite.addTestSuite(TestAID.class);
		suite.addTestSuite(TestCBO.class);
		suite.addTestSuite(TestDIT.class);
		suite.addTestSuite(TestNMI.class);
		suite.addTestSuite(TestNOC.class);
		suite.addTestSuite(TestUnaryCBO.class);
		suite.addTestSuite(TestWMC.class);
		suite.addTestSuite(TestWMC1.class);

		return suite;
	}
}
