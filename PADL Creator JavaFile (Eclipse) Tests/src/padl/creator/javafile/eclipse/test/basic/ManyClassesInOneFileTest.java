/* (c) Copyright 2011 and following years, Aminata SABANÉ,
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
package padl.creator.javafile.eclipse.test.basic;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.creator.javafile.eclipse.test.util.Utils;
import padl.kernel.IClass;
import padl.kernel.ICodeLevelModel;
import padl.kernel.impl.Factory;

public class ManyClassesInOneFileTest extends TestCase {

	public ManyClassesInOneFileTest(final String aName) {
		super(aName);

	}

	/**
	 * One public class in a file and 2 other classes which are package classes, not internal to the first one
	 */
	public void testManyClassesInOneFile() {
		final String sourcePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] javaFiles =
			new String[] { "../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/manyClassesInOneFile/ManyClassesInOneFile.java" };
		final String classPathEntry = "";

		ICodeLevelModel model = Factory.getInstance().createCodeLevelModel("");
		model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourcePath,
				classPathEntry,
				javaFiles);

		final IClass clazz =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.manyClassesInOneFile.ManyClassesInOneFile");

		Assert.assertNotNull(clazz);

		final IClass clazz1 =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.manyClassesInOneFile.SecondClass");

		Assert.assertNotNull(clazz1);

		final IClass clazz2 =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.manyClassesInOneFile.ThirdClass");

		Assert.assertNotNull(clazz2);

		final IClass clazz3 =
			(IClass) model
				.getTopLevelEntityFromID("padl.example.manyClassesInOneFile.FirstClass");

		Assert.assertNull(clazz3);

		//model.walk(new PadlPrinterVisitor());

	}
}
