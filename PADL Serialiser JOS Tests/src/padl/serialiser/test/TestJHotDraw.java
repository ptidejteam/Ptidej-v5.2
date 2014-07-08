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
import padl.kernel.IAbstractModel;
import padl.kernel.ICodeLevelModel;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import padl.serialiser.JOSSerialiser;
import padl.serialiser.util.TransientFieldManager;
import padl.test.helper.ModelComparator;

public class TestJHotDraw extends TestCase {
	private static IAbstractModel OriginalModel;
	private static IAbstractModel SerialisedModel;
	private static String SerialisedFileName;

	public TestJHotDraw(final String name) {
		super(name);
	}
	protected void setUp() throws CreationException,
			UnsupportedSourceModelException {

		if (TestJHotDraw.OriginalModel == null) {
			System.out.println("Creating model...");
			final long beginning = System.currentTimeMillis();

			TestJHotDraw.OriginalModel =
				Factory.getInstance().createCodeLevelModel("JHotDraw v5.1");
			((ICodeLevelModel) TestJHotDraw.OriginalModel)
				.create(new CompleteClassFileCreator(
					new String[] { "../../P-MARt Workspace/JHotDraw v5.1/bin/" },
					true));
			TestJHotDraw.OriginalModel =
				new AACRelationshipsAnalysis()
					.invoke(TestJHotDraw.OriginalModel);

			final long end = System.currentTimeMillis();
			System.out.print("Model created in ");
			System.out.print(end - beginning);
			System.out.println(" ms.");
			TestJHotDraw.SerialisedFileName =
				JOSSerialiser.getInstance().serialiseWithAutomaticNaming(
					TestJHotDraw.OriginalModel,
					"../PADL Serialiser JOS Tests/rsc/");
			TestJHotDraw.SerialisedModel =
				JOSSerialiser.getInstance().deserialise(
					TestJHotDraw.SerialisedFileName);
		}
	}
	protected void tearDown() {
		final File serialisedFile = new File(TestJHotDraw.SerialisedFileName);
		serialisedFile.delete();

		final File serialisedHelperFile =
			new File(TestJHotDraw.SerialisedFileName
					+ TransientFieldManager.METHOD_INVOCATION_EXTENSION);
		serialisedHelperFile.delete();
	}
	public void testNames() {
		Assert.assertEquals(
			TestJHotDraw.OriginalModel.getDisplayName(),
			TestJHotDraw.SerialisedModel.getDisplayName());
	}
	public void testComparator() {
		TestJHotDraw.OriginalModel.walk(new ModelComparator(
			TestJHotDraw.SerialisedModel));
	}
}
