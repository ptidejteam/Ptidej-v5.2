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
package epi.test;

import epi.test.java.JavaAWTTest;
import epi.test.java.JavaSwingTest;
import junit.framework.Test;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/01/25
 */
// TODO: Implement and test the tests!
// Non-fuzzy first, then fuzzy...
public final class TestEPI extends junit.framework.TestSuite {
	public TestEPI() {
		//empty block
	}
	public TestEPI(final Class theClass) {
		super(theClass);
	}
	public TestEPI(final String name) {
		super(name);
	}
	public static Test suite() {
		final TestEPI suite = new TestEPI();
		suite.addTestSuite(JavaAWTTest.class);
		suite.addTestSuite(JavaSwingTest.class);
		return suite;
	}
}
