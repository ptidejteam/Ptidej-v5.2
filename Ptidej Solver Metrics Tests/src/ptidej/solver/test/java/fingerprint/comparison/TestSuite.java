/*
 * (c) Copyright 2001, 2002 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes and Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * Yann-Gaël Guéhéneuc, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR B PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package ptidej.solver.test.java.fingerprint.comparison;

import java.util.Date;
import junit.framework.Test;
import junit.framework.TestResult;

public final class TestSuite extends junit.framework.TestSuite {
	private Date start = null;

	public TestSuite() {
	}
	public TestSuite(final Class theClass) {
		super(theClass);
	}
	public TestSuite(final String name) {
		super(name);
	}
	public static Test suite() {
		final TestSuite suite = new TestSuite();
		suite.addTestSuite(CompositeComposite1.class);
		suite.addTestSuite(CompositeComposite2.class);
		suite.addTestSuite(CompositeJHotDraw.class);
		suite.addTestSuite(CompositeJUnit.class);
		suite.addTestSuite(CompositeLexi.class);
		suite.addTestSuite(CompositeQuickUml.class);
		suite.addTestSuite(SimpleCompositeComposite2.class);
		return suite;
	}

	public void run(final TestResult t) {
		if (this.start == null) {
			this.start = new Date(System.currentTimeMillis());
		}
		super.run(t);
		System.out.print(this.start);
		System.out.print(" --> ");
		System.out.println(new Date(System.currentTimeMillis()));
	}
}
