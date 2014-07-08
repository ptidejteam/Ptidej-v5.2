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
package padl.serialiser.test;

import java.io.File;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.analysis.UnsupportedSourceModelException;
import padl.analysis.repository.AACRelationshipsAnalysis;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.IAbstractLevelModel;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IIdiomLevelModel;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import padl.serialiser.DB4OSerialiser;
import padl.test.helper.ModelComparator;
import padl.util.ModelStatistics;

public class TestDB4OSerialiserArgoUML020 extends TestCase {
	private static IAbstractLevelModel AbstractLevelModel;
	private static IAbstractLevelModel SerialisedAbstractLevelModel;
	private static String SerialisedFileName;

	public TestDB4OSerialiserArgoUML020(String name) {
		super(name);
	}
	protected void setUp() throws CreationException,
			UnsupportedSourceModelException {

		if (TestDB4OSerialiserArgoUML020.AbstractLevelModel == null) {
			System.out.println("Creating model...");
			long beginning = System.currentTimeMillis();

			final ModelStatistics statistics = new ModelStatistics();
			final ICodeLevelModel codeLevelModel =
				Factory.getInstance().createCodeLevelModel("ArgoUML v0.20");
			codeLevelModel.addModelListener(statistics);
			codeLevelModel.create(new CompleteClassFileCreator(
				new String[] { "../../P-MARt Workspace/ArgoUML v0.20/bin/" },
				true));

			TestDB4OSerialiserArgoUML020.AbstractLevelModel =
				(IIdiomLevelModel) new AACRelationshipsAnalysis()
					.invoke(codeLevelModel);

			long end = System.currentTimeMillis();
			System.out.println(statistics);
			System.out.print("Model created in ");
			System.out.print(end - beginning);
			System.out.println(" ms.");
			beginning = System.currentTimeMillis();

			TestDB4OSerialiserArgoUML020.SerialisedFileName =
				DB4OSerialiser.getInstance().serialiseWithAutomaticNaming(
					TestDB4OSerialiserArgoUML020.AbstractLevelModel);

			end = System.currentTimeMillis();
			System.out.print("Model serialised in ");
			System.out.print(end - beginning);
			System.out.println(" ms.");
			beginning = System.currentTimeMillis();

			TestDB4OSerialiserArgoUML020.SerialisedAbstractLevelModel =
				(IAbstractLevelModel) DB4OSerialiser.getInstance().deserialise(
					TestDB4OSerialiserArgoUML020.SerialisedFileName);

			end = System.currentTimeMillis();
			System.out.print("Model deserialised in ");
			System.out.print(end - beginning);
			System.out.println(" ms.");
		}
	}
	protected void tearDown() {
		final File serialisedFile =
			new File(TestDB4OSerialiserArgoUML020.SerialisedFileName);
		serialisedFile.delete();
	}
	public void testNames() {
		Assert.assertEquals(
			TestDB4OSerialiserArgoUML020.AbstractLevelModel.getDisplayName(),
			TestDB4OSerialiserArgoUML020.SerialisedAbstractLevelModel
				.getDisplayName());
	}
	public void testComparator() {
		TestDB4OSerialiserArgoUML020.AbstractLevelModel
			.walk(new ModelComparator(
				TestDB4OSerialiserArgoUML020.SerialisedAbstractLevelModel));
	}
}
