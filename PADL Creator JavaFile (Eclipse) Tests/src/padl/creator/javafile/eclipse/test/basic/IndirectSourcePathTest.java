/* (c) Copyright 2001 and following years,  Aminata SABANÉ,
 * ÉCole Polytechnique de Montréal.
 * 
 * @author: Aminata SABANÉ
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

package padl.creator.javafile.eclipse.test.basic;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.creator.javafile.eclipse.test.util.Utils;
import padl.creator.javafile.eclipse.util.PadlParserUtil;
import padl.creator.javafile.eclipse.visitor.PADLPrinterVisitor;
import padl.kernel.Constants;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IPackage;
import padl.kernel.impl.Factory;

public class IndirectSourcePathTest extends TestCase {

	public IndirectSourcePathTest(final String aName) {
		super(aName);
	}

	public void testSourcePathIndirect() {
		final String javaFilesFolderPath = "../Java Parser/";
		final String javaFilesFolderPath1 = "../Java Parser/src/";
		final String classPathEntry = "";

		//Model from source code

		final ICodeLevelModel padlModelFromJavaFiles =
			Utils.createLightJavaFilesPadlModel(
				"",
				javaFilesFolderPath,
				classPathEntry);

		final ICodeLevelModel padlModelFromJavaFiles1 =
			Utils.createLightJavaFilesPadlModel(
				"",
				javaFilesFolderPath1,
				classPathEntry);

		//	padlModelFromJavaFiles.walk(new PadlPrinterVisitor());

		//	padlModelFromJavaFiles.walk(new ModelComparator(padlModelFromJavaFiles));

		Assert.assertNotNull(padlModelFromJavaFiles);
		Assert.assertNotNull(padlModelFromJavaFiles1);
	}

	public void testSourcePathIndirect1() {
		final String sourcePath = "WHATEVER";
		final String[] javaFiles =
			new String[] {
					"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/packaje/",
					"../PADL Creator JavaFile (Eclipse) Tests/rsc/Aminata testdata/MyDefaultClass.java" };
		final String classPathEntry = "";

		ICodeLevelModel model = Factory.getInstance().createCodeLevelModel("");
		model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourcePath,
				classPathEntry,
				javaFiles);

		final IPackage packaje1 = PadlParserUtil.getPackage("padl", model);
		final IPackage packaje2 =
			PadlParserUtil.getPackage("padl.example", model);
		final IPackage packaje3 =
			PadlParserUtil.getPackage("padl.example.packaje", model);
		final IPackage packaje4 =
			PadlParserUtil.getPackage("padl.example.packaje.toto", model);
		final IPackage packajeDefault = PadlParserUtil.getPackage("", model);

		model.walk(new PADLPrinterVisitor());

		Assert.assertNotNull(packaje1);
		Assert.assertNotNull(packaje2);
		Assert.assertNotNull(packaje3);
		Assert.assertNull(packaje4);
		Assert.assertNotNull(packajeDefault);
		Assert.assertEquals(
			packajeDefault.getID(),
			Constants.DEFAULT_PACKAGE_ID);
	}

}
