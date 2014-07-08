/* (c) Copyright 2001 and following years, Yann-Gaël Guéhéneuc,
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
package padl.creator.javafile.eclipse.test.methodinvocation;

import junit.framework.Test;
import junit.framework.TestSuite;
import padl.creator.javafile.eclipse.test.others.CompleteCreatorTest;
import padl.kernel.impl.MethodInvocationTest;

public class TestMethodInvocations extends TestSuite {
	public static Test suite() {
		final TestMethodInvocations suite = new TestMethodInvocations();
		suite.addTestSuite(JavaFilesModelMethodInvocationExplorer.class);
		suite.addTestSuite(MethodInvocationExplorer.class);
		// normal failure, it is to show the difference
		suite.addTestSuite(MethodInvocationTest.class);
		suite.addTestSuite(CompleteCreatorTest.class);
		suite.addTestSuite(MI_ChainOfMessagesTest.class);
		suite.addTestSuite(MI_ClassClassTest1.class);
		suite.addTestSuite(MI_ClassClassTest.class);
		suite.addTestSuite(MI_ClassInstanceFieldTest.class);
		suite.addTestSuite(MI_ClassInstanceTest.class);
		suite.addTestSuite(MI_Composite4AbstractDocumentTest.class);
		suite.addTestSuite(MI_FieldAccessTest.class);
		suite.addTestSuite(MI_InstanceClassTest2.class);
		suite.addTestSuite(MI_InstanceClassTest.class);
		suite.addTestSuite(MI_InstanceCreationTest.class);
		suite.addTestSuite(MI_InstanceInstanceFieldTest.class);
		suite.addTestSuite(MI_InstanceInstanceTest.class);
		return suite;
	}
	public TestMethodInvocations() {
	}
	public TestMethodInvocations(final Class theClass) {
		super(theClass);
	}
	public TestMethodInvocations(final String name) {
		super(name);
	}
}