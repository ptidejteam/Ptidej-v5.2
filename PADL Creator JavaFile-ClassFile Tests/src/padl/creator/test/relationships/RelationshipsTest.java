package padl.creator.test.relationships;

import java.io.File;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.creator.javafile.eclipse.CompleteJavaFileCreator;
import padl.creator.javafile.eclipse.test.methodinvocation.MethodInvocationComparator;
import padl.creator.test.relationships.providers.ITestProvider;
import padl.kernel.IClass;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IMethod;
import padl.kernel.IMethodInvocation;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;

/* (c) Copyright 2008 and following years, Yann-Gaël Guéhéneuc,
 * École Polytechnique de Montréal.
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

public class RelationshipsTest extends TestCase {
	public RelationshipsTest(final String aName) {
		super(aName);
	}

	/**
	 * Test the existence of the same MI in the JavaFile model as in the ClassFile model.
	 */
	private void testInclusionOfMIsFromJavaFilesModelInClassFilesModel(
		final ITestProvider aProvider,
		final IMethod aMethodFromJavaFilesModel,
		final IMethod aMethodFromClassFilesModel) {

		Assert.assertTrue(
			"Method invocations built for " + aProvider.getTestClassName()
					+ " are different!",
			MethodInvocationComparator
				.isMIofJavaModelMethodIncludedInMIofClassModelMethod(
					aMethodFromJavaFilesModel,
					aMethodFromClassFilesModel));
	}

	/**
	 * Tests methods have been separated - see also the method callMIinclusionTest
	 * @param aProvider
	 */
	private void testExpectedMIs(
		final ITestProvider aProvider,
		final IMethod theTestMethodFromJavaFilesModel) {

		// Get methodFromJavaFileModel's method invocation
		final IMethodInvocation javaMethodInvocation =
			(IMethodInvocation) theTestMethodFromJavaFilesModel
				.getConstituentFromID("Method Invocation_>PADL<_1");
		final IMethodInvocation expectedMethodInvocation =
			aProvider.getExpectedMethodInvocation();

		// Test that the method invocation is which is expected
		Assert.assertEquals(
			" TestClass " + aProvider.getTestClassName()
					+ ": doest not contain the expected method invocation",
			expectedMethodInvocation,
			javaMethodInvocation);
	}
	private ICodeLevelModel createModelFromClassFiles(
		final String[] pathsToFiles) {

		try {
			final ICodeLevelModel codeLevelModel =
				Factory.getInstance().createCodeLevelModel("Test Model");
			codeLevelModel.create(new CompleteClassFileCreator(pathsToFiles));
			return codeLevelModel;
		}
		catch (final CreationException e) {
			throw new RuntimeException(e);
		}
	}
	private ICodeLevelModel createModelFromJavaFiles(final String[] pathsToFiles) {
		try {
			final ICodeLevelModel codeLevelModel =
				Factory.getInstance().createCodeLevelModel("Test Model");
			codeLevelModel.create(new CompleteJavaFileCreator(
				"../PADL Creator JavaFile-ClassFile Tests/src/",
				"",
				pathsToFiles));
			return codeLevelModel;
		}
		catch (final CreationException e) {
			throw new RuntimeException(e);
		}
	}
	private IMethod getTestMethodFromModel(
		final ICodeLevelModel aCodeLevelModel,
		final String aTestClassName) {

		final IClass testClass =
			(IClass) aCodeLevelModel.getTopLevelEntityFromID(aTestClassName);
		Assert.assertNotNull(testClass);

		final IMethod testMethod =
			(IMethod) testClass.getConstituentFromID("foo()");
		Assert.assertNotNull(testMethod);

		return testMethod;
	}
	public void testRelationships() {
		final String testProviderClassName = ITestProvider.class.getName();
		final String testProviderPackageName =
			testProviderClassName.substring(
				0,
				testProviderClassName.lastIndexOf('.'));
		final File pathToTest =
			new File("../PADL Creator JavaFile-ClassFile Tests/bin/"
					+ testProviderPackageName.replace('.', '/'));
		final String[] potentialTests = pathToTest.list();
		for (int i = 0; i < potentialTests.length; i++) {
			final String potentialTest = potentialTests[i];
			if (potentialTest.startsWith(ITestProvider.PROVIDER_CLASS_PREFIX)) {
				try {
					final StringBuffer testClassName = new StringBuffer();
					testClassName.append(testProviderPackageName);
					testClassName.append('.');
					testClassName.append(potentialTest.substring(
						0,
						potentialTest.indexOf('.')));
					final ITestProvider provider =
						(ITestProvider) Class
							.forName(testClassName.toString())
							.newInstance();

					// Create the needed data...
					final String pathToByteCodeOfTestClass =
						provider.getPathToByteCodeOfTestClass();
					final String pathToByteCodeOfHelperClass =
						provider.getPathToByteCodeOfHelperClass();

					final String pathToJavaCodeOfTestClass =
						provider.getPathToJavaCodeOfTestClass();
					final String pathToJavaCodeOfHelperClass =
						provider.getPathToJavaCodeOfHelperClass();

					final ICodeLevelModel classFilesModel =
						this.createModelFromClassFiles(new String[] {
								pathToByteCodeOfTestClass,
								pathToByteCodeOfHelperClass });
					final ICodeLevelModel javaFilesModel =
						this.createModelFromJavaFiles(new String[] {
								pathToJavaCodeOfTestClass,
								pathToJavaCodeOfHelperClass });
					final IMethod methodFromClassFileModel =
						this.getTestMethodFromModel(
							classFilesModel,
							provider.getTestClassName());

					final IMethod methodFromJavaFileModel =
						this.getTestMethodFromModel(
							javaFilesModel,
							provider.getTestClassName());

					// Test code...
					this.testExpectedMIs(provider, methodFromJavaFileModel);
					this.testInclusionOfMIsFromJavaFilesModelInClassFilesModel(
						provider,
						methodFromJavaFileModel,
						methodFromClassFileModel);
				}
				catch (final InstantiationException e) {
					throw new RuntimeException(e);
				}
				catch (final IllegalAccessException e) {
					throw new RuntimeException(e);
				}
				catch (final ClassNotFoundException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}
