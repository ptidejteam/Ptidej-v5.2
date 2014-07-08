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
import padl.creator.classfile.LightClassFileCreator;
import padl.kernel.IAbstractModel;
import padl.kernel.ICodeLevelModel;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import padl.serialiser.JOSSerialiser;
import padl.serialiser.util.TransientFieldManager;
import padl.test.helper.ModelComparator;
import util.io.ProxyConsole;

public class TestArgoUML020 extends TestCase {
	private static IAbstractModel OriginalModel;
	private static IAbstractModel SerialisedModel;
	private static String SerialisedFileName;

	public TestArgoUML020(String name) {
		super(name);
	}
	protected void setUp() {
		if (TestArgoUML020.OriginalModel == null) {
			try {
				System.out.println("Creating model...");
				final long beginning = System.currentTimeMillis();

				TestArgoUML020.OriginalModel =
					Factory.getInstance().createCodeLevelModel("ArgoUML v0.20");
				((ICodeLevelModel) TestArgoUML020.OriginalModel)
					.create(new LightClassFileCreator(
						new String[] { "../../P-MARt Workspace/ArgoUML v0.20/bin/" },
						true));
				TestArgoUML020.OriginalModel =
					new AACRelationshipsAnalysis()
						.invoke(TestArgoUML020.OriginalModel);

				final long end = System.currentTimeMillis();
				System.out.print("Model created in ");
				System.out.print(end - beginning);
				System.out.println(" ms.");
			}
			catch (final CreationException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
			catch (final UnsupportedSourceModelException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}

			TestArgoUML020.SerialisedFileName =
				JOSSerialiser.getInstance().serialiseWithAutomaticNaming(
					TestArgoUML020.OriginalModel,
					"../PADL Serialiser JOS Tests/rsc/");
			TestArgoUML020.SerialisedModel =
				JOSSerialiser.getInstance().deserialise(
					TestArgoUML020.SerialisedFileName);
		}
	}
	protected void tearDown() {
		final File serialisedFile = new File(TestArgoUML020.SerialisedFileName);
		serialisedFile.delete();

		final File serialisedHelperFile =
			new File(TestArgoUML020.SerialisedFileName
					+ TransientFieldManager.METHOD_INVOCATION_EXTENSION);
		serialisedHelperFile.delete();
	}
	public void testNames() {
		Assert.assertEquals(
			TestArgoUML020.OriginalModel.getDisplayName(),
			TestArgoUML020.SerialisedModel.getDisplayName());
	}
	public void testComparator() {
		TestArgoUML020.OriginalModel.walk(new ModelComparator(
			TestArgoUML020.SerialisedModel));
	}
}
