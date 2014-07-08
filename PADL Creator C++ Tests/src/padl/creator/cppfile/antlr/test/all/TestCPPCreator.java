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
package padl.creator.cppfile.antlr.test.all;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * @author robidose
 */
public class TestCPPCreator extends TestSuite {

	public TestCPPCreator() {
	}
	public TestCPPCreator(final Class theClass) {
		super(theClass);
	}
	public TestCPPCreator(final String name) {
		super(name);
	}
	public static Test suite() {
		final TestCPPCreator suite = new TestCPPCreator();
		suite.addTest(new TestCPPCreator(TestWorld.class));
		suite.addTest(new TestCPPCreator(TestInheritance.class));
		suite.addTest(new TestCPPCreator(TestCpoint.class));
		suite.addTest(new TestCPPCreator(TestCsegment2.class));
		return suite;
	}
}
