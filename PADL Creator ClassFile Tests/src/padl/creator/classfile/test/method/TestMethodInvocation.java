/*
 * (c) Copyright 2001-2005 Yann-Gaël Guéhéneuc,
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
package padl.creator.classfile.test.method;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.IClass;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IMethod;
import padl.kernel.IMethodInvocation;
import padl.kernel.impl.Factory;

/**
 * Test for methods wrongly included in an IClass
 * after being parsed by the PADL Creator ClassFile.
 * 
 * @author Stephane Vaucher
 * @author Yann-Gaël Guéhéneuc
 * @since  2005/11/11
 */
public class TestMethodInvocation extends TestCase {
	private static ICodeLevelModel CodeLevelModel;
	public TestMethodInvocation(final String aName) {
		super(aName);
	}
	public void setUp() throws Exception {
		if (TestMethodInvocation.CodeLevelModel == null) {
			TestMethodInvocation.CodeLevelModel =
				Factory.getInstance().createCodeLevelModel("");
			TestMethodInvocation.CodeLevelModel
				.create(new CompleteClassFileCreator(
					new String[] { "../PADL Creator ClassFile Tests/bin/padl/example/method/MethodInvocation.class" }));
		}
	}
	public void testMethodInvocation1() {
		Assert.assertEquals(
			"Number of entities",
			2,
			TestMethodInvocation.CodeLevelModel.getNumberOfConstituents());
	}
	public void testMethodInvocation2() {
		final IClass clazz =
			(IClass) TestMethodInvocation.CodeLevelModel
				.getConstituentFromID("padl.example.method.MethodInvocation");
		Assert.assertEquals(
			"Number of elemets",
			6,
			clazz.getNumberOfConstituents());
	}
	public void testMethodInvocation3() {
		final IClass clazz =
			(IClass) TestMethodInvocation.CodeLevelModel
				.getConstituentFromID("padl.example.method.MethodInvocation");
		final IMethod method =
			(IMethod) clazz.getConstituentFromName("SomeMethod".toCharArray());
		Assert.assertEquals(
			"Number of elemets",
			2,
			method.getNumberOfConstituents());
		Assert.assertEquals("Assignation", "=", ((IMethodInvocation) method
			.getConstituentFromID("Method Invocation 2"))
			.getCalledMethod()
			.getName());
		Assert.assertEquals("Assignation", "=", ((IMethodInvocation) method
			.getConstituentFromID("Method Invocation 3"))
			.getCalledMethod()
			.getName());
	}
}
