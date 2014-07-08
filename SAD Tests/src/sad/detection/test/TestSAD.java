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
package sad.detection.test;

import junit.framework.Test;
import sad.detection.test.classfile.swt.DetectionAntipatternSWTTest;
import sad.detection.test.classfile.xerces.SpaghettiCodeTest;
import sad.detection.test.classfile.xerces.SpaghettiCodeTest2;
import sad.detection.test.classfile.xerces.VariousSmellsTest;
import sad.detection.test.comparison.xerces.BlobTest;
import sad.detection.test.cppfile.qmake.FewSmellsTest;
import sad.detection.test.generic.BoxPlotTest;
import sad.detection.test.javafile.ideasimsyn.SomeSmellsTest;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/01/25
 */
// TODO: Implement and test the tests!
// Non-fuzzy first, then fuzzy...
public final class TestSAD extends junit.framework.TestSuite {
	public static Test suite() {
		final TestSAD suite = new TestSAD();
		suite.addTestSuite(DetectionAntipatternSWTTest.class);

		suite.addTestSuite(SpaghettiCodeTest.class);
		suite.addTestSuite(SpaghettiCodeTest2.class);
		suite.addTestSuite(VariousSmellsTest.class);
		suite.addTestSuite(BlobTest.class);

		suite.addTestSuite(BoxPlotTest.class);

		suite.addTestSuite(SomeSmellsTest.class);

		suite.addTestSuite(FewSmellsTest.class);

		return suite;
	}
	public TestSAD() {
		// Empty block.
	}
	public TestSAD(final Class<?> theClass) {
		super(theClass);
	}
	public TestSAD(final String name) {
		super(name);
	}
}
