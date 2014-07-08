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
package padl.refactoring.test;

import padl.refactoring.test.method.RefactoringMoveMethodTest;
import padl.refactoring.test.method.RefactoringPullUpMethodTest;
import padl.refactoring.test.method.RefactoringPushDownMethodTest;
import padl.refactoring.test.method.RefactoringRenameMethodTest;
import junit.framework.Test;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2006/08/08
 */
public final class TestRefactorings extends junit.framework.TestSuite {
	public TestRefactorings() {
	}
	public TestRefactorings(final Class theClass) {
		super(theClass);
	}
	public TestRefactorings(final String name) {
		super(name);
	}
	public static Test suite() {
		final TestRefactorings suite = new TestRefactorings();
		suite.addTestSuite(RefactoringMoveMethodTest.class);
		suite.addTestSuite(RefactoringPullUpMethodTest.class);
		suite.addTestSuite(RefactoringPushDownMethodTest.class);
		suite.addTestSuite(RefactoringRenameMethodTest.class);
		return suite;
	}
}
