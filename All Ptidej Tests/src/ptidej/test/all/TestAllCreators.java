package ptidej.test.all;

import junit.framework.Test;
import junit.framework.TestSuite;
import padl.creator.aolfile.test.TestCreatorAOL;
import padl.creator.aspectj.test.TestCreatorAspectJ;
import padl.creator.classfile.test.TestCreatorClassFile;
import padl.creator.cppfile.eclipse.test.TestCreatorCPPFileUsingEclipse;
import padl.creator.javafile.eclipse.test.TestCreatorJavaFileUsingEclipse;
import padl.creator.javafile.javac.test.TestCreatorJavaFileUsingJavaC;
import padl.creator.test.TestCreatorJavaFilevsClassFile;
import padl.creator.test.csharpfile.v1.TestCreatorCSharpV1;
import padl.creator.test.csharpfile.v2.TestCreatorCSharpv2;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2013/05/10
 */
public final class TestAllCreators extends TestSuite {
	public static Test suite() {
		final TestAllCreators suite = new TestAllCreators();

		suite.addTest(TestCreatorAOL.suite());
		suite.addTest(TestCreatorAspectJ.suite());
		suite.addTestSuite(TestCreatorCSharpV1.class);
		suite.addTestSuite(TestCreatorCSharpv2.class);
		suite.addTest(TestCreatorCPPFileUsingEclipse.suite());
		// TODO Add test suite
		//	suite.addTest(TestCreatorCPPFileUsingANTLR.suite());
		suite.addTest(TestCreatorClassFile.suite());
		suite.addTest(TestCreatorJavaFileUsingEclipse.suite());
		suite.addTest(TestCreatorJavaFileUsingJavaC.suite());
		suite.addTest(TestCreatorJavaFilevsClassFile.suite());

		return suite;
	}
}
