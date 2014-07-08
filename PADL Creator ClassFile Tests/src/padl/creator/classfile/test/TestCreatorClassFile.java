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
package padl.creator.classfile.test;

import junit.framework.Test;
import junit.framework.TestSuite;
import padl.creator.classfile.test.example.TestClassFileCompleteCreator;
import padl.creator.classfile.test.inheritance.TestInheritance;
import padl.creator.classfile.test.innerclasses.TestInnerAndMembreClasses;
import padl.creator.classfile.test.method.TestMethodInclusion;
import padl.creator.classfile.test.method.TestPrivateConstructor;
import padl.creator.classfile.test.methodInvocation.MethodInvocationMissingtest;
import padl.creator.classfile.test.path.TestPath;
import padl.creator.classfile.test.topLevelEntity.TopLevelEntityTest;
import padl.creator.classfile.test.visitor.TestVisitor;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/01/25
 */
public final class TestCreatorClassFile extends TestSuite {
	public TestCreatorClassFile() {
	}
	public TestCreatorClassFile(final Class theClass) {
		super(theClass);
	}
	public TestCreatorClassFile(final String name) {
		super(name);
	}
	public static Test suite() {
		final TestCreatorClassFile suite = new TestCreatorClassFile();
		suite.addTest(TestClassFileCompleteCreator.suite());
		suite.addTest(TestVisitor.suite());
		suite.addTest(TestInnerAndMembreClasses.suite());
		// suite.addTest(TestMemoryFootprint.suite());
		suite.addTestSuite(TestMethodInclusion.class);
		suite.addTest(TestInheritance.suite());
		suite.addTestSuite(TestPrivateConstructor.class);
		suite.addTest(TestPath.suite());
		suite.addTestSuite(MethodInvocationMissingtest.class);
		suite.addTestSuite(TopLevelEntityTest.class);
		return suite;
	}
}
