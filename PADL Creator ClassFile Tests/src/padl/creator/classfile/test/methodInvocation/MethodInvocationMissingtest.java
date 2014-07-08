package padl.creator.classfile.test.methodInvocation;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IMethod;
import padl.kernel.IMethodInvocation;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;

public class MethodInvocationMissingtest extends TestCase {

	public MethodInvocationMissingtest(final String name) {
		super(name);

	}

	/**
	 * Client alone
	 */
	public void testMethodInvocationMissingConfig1() {
		final ICodeLevelModel modelClient =
			Factory.getInstance().createCodeLevelModel("");

		try {
			modelClient
				.create(new CompleteClassFileCreator(
					new String[] { "../PADL Creator ClassFile Tests/rsc/MethodInvocation - Missing Method Called/ImmutableCollection.class" }));
		}
		catch (CreationException e) {
			e.printStackTrace();
		}
		IFirstClassEntity entityModelClient =
			modelClient
				.getTopLevelEntityFromID("com.google.common.collect.ImmutableCollection");
		IMethod isEmptyClient =
			(IMethod) entityModelClient.getConstituentFromName("isEmpty");

		final IMethodInvocation sizeClient =
			(IMethodInvocation) isEmptyClient
				.getConstituentFromID("Method Invocation_>PADL<_2");
		Assert.assertNotNull(sizeClient.getCalledMethod());

	}

	/**
	 * Client with supers form original code
	 */
	public void testMethodInvocationMissingConfig2() {
		final ICodeLevelModel modelSystem =
			Factory.getInstance().createCodeLevelModel("");

		try {
			modelSystem
				.create(new CompleteClassFileCreator(
					new String[] {
							"../PADL Creator ClassFile Tests/rsc/MethodInvocation - Missing Method Called/Object.class",
							"../PADL Creator ClassFile Tests/rsc/MethodInvocation - Missing Method Called/Collection.class",
							"../PADL Creator ClassFile Tests/rsc/MethodInvocation - Missing Method Called/Serializable.class",
							"../PADL Creator ClassFile Tests/rsc/MethodInvocation - Missing Method Called/ImmutableCollection.class" }));
		}
		catch (CreationException e) {
			e.printStackTrace();
		}

		final IFirstClassEntity entityModelSystem =
			modelSystem
				.getTopLevelEntityFromID("com.google.common.collect.ImmutableCollection");
		final IMethod isEmptySystem =
			(IMethod) entityModelSystem.getConstituentFromName("isEmpty");

		final IMethodInvocation sizeSystem =
			(IMethodInvocation) isEmptySystem
				.getConstituentFromID("Method Invocation_>PADL<_2");
		Assert.assertNotNull(sizeSystem.getCalledMethod());
	}

	/**
	 * Client with supers (partial simplified configuration - only
	 * ImmutableCollection is changed)
	 */
	public void testMethodInvocationMissingConfig3() {

		final ICodeLevelModel modelSystem =
			Factory.getInstance().createCodeLevelModel("");

		try {
			modelSystem
				.create(new CompleteClassFileCreator(
					new String[] {
							"../PADL Creator ClassFile Tests/rsc/MethodInvocation - Missing Method Called/Object.class",
							"../PADL Creator ClassFile Tests/rsc/MethodInvocation - Missing Method Called/Collection.class",
							"../PADL Creator ClassFile Tests/rsc/MethodInvocation - Missing Method Called/Serializable.class",
							"../PADL Creator ClassFile Tests/bin/padl/example/methodInvocation/ImmutableCollection.class" }));
		}
		catch (CreationException e) {
			e.printStackTrace();
		}

		final IFirstClassEntity entityModelSystem =
			modelSystem
				.getTopLevelEntityFromID("padl.example.methodInvocation.ImmutableCollection");
		final IMethod isEmptySystem =
			(IMethod) entityModelSystem.getConstituentFromName("isEmpty");

		final IMethodInvocation sizeSystem =
			(IMethodInvocation) isEmptySystem
				.getConstituentFromID("Method Invocation_>PADL<_2");
		Assert.assertNotNull(sizeSystem.getCalledMethod());
	}

	/**
	 * Client with supers (complete simplified configuration - all classes are
	 * changed)
	 */
	public void testMethodInvocationMissingConfig4() {

		final ICodeLevelModel modelSystem =
			Factory.getInstance().createCodeLevelModel("");

		try {
			modelSystem
				.create(new CompleteClassFileCreator(
					new String[] {
							"../PADL Creator ClassFile Tests/bin/padl/example/methodInvocation1/B.class",
							"../PADL Creator ClassFile Tests/bin/padl/example/methodInvocation1/C.class",
							"../PADL Creator ClassFile Tests/bin/padl/example/methodInvocation1/D.class",
							"../PADL Creator ClassFile Tests/bin/padl/example/methodInvocation/A.class" }));
		}
		catch (CreationException e) {
			e.printStackTrace();
		}

		final IFirstClassEntity entityModelSystem =
			modelSystem
				.getTopLevelEntityFromID("padl.example.methodInvocation.A");
		final IMethod isEmptySystem =
			(IMethod) entityModelSystem.getConstituentFromName("isEmpty");

		final IMethodInvocation sizeSystem =
			(IMethodInvocation) isEmptySystem
				.getConstituentFromID("Method Invocation_>PADL<_2");
		Assert.assertNotNull(sizeSystem.getCalledMethod());
	}
}
