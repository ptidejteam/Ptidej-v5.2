/* (c) Copyright 2011 and following years, Aminata SABANÉ,
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
package padl.creator.javafile.eclipse.test.comparator;

import java.io.File;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.creator.javafile.eclipse.test.util.Utils;
import padl.kernel.IClass;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IInterface;
import padl.kernel.IMethod;
import padl.kernel.IParameter;
import util.io.ProxyConsole;

public class ClassAndFileConventionTest extends TestCase {
	public ClassAndFileConventionTest(final String aName) {
		super(aName);
	}

	public void testUseOfFileClassNameConvention() {

		final String javaFilesFolderPath = "../PADL Creator JavaFile (Eclipse) Tests/rsc/";
		final String classPathEntry = "";
		String rootPath = "../PADL Creator JavaFile (Eclipse) Tests/rsc/ptidej/example/FileAndClassNames/";

		String someFilesPaths[] =
			new File("../PADL Creator JavaFile (Eclipse) Tests/rsc/ptidej/example/FileAndClassNames/").list();
		for (int i = 0; i < someFilesPaths.length; i++) {
			someFilesPaths[i] =
				new StringBuffer()
					.append(rootPath)
					.append(someFilesPaths[i])
					.toString();
		}

		// Model from source code

		final ICodeLevelModel padlModelFromJavaFiles =
			Utils.createLightJavaFilesPadlModel(
				"",
				javaFilesFolderPath,
				classPathEntry,
				someFilesPaths);

		IInterface interface1 =
			(IInterface) padlModelFromJavaFiles
				.getTopLevelEntityFromID("ptidej.example.FileAndClassNames.Element");
		IClass class1 =
			(IClass) padlModelFromJavaFiles
				.getTopLevelEntityFromID("ptidej.example.FileAndClassNames.Document");
		IMethod method =
			(IMethod) class1.getConstituentFromName("removeComponent");
		IParameter param = (IParameter) method.getConstituentFromName("e");
		ProxyConsole
			.getInstance()
			.debugOutput()
			.println(param.getDisplayTypeName());

		Assert.assertNotNull("entity is null", interface1);
		Assert.assertEquals(
			"ptidej.example.FileAndClassNames.Element",
			param.getDisplayTypeName());

	}

	public void testUseOfFileClassNameConvention2() {

		final String javaFilesFolderPath = "../PADL Creator JavaFile (Eclipse) Tests/rsc/";
		final String classPathEntry = "";
		String rootPath = new StringBuffer()

		.append("../PADL Creator JavaFile (Eclipse) Tests/rsc/ptidej/example/FileAndClassNames2/").toString();

		String someFilesPaths[] =
			new File("../PADL Creator JavaFile (Eclipse) Tests/rsc/ptidej/example/FileAndClassNames2/").list();
		for (int i = 0; i < someFilesPaths.length; i++) {
			someFilesPaths[i] =
				new StringBuffer()
					.append(rootPath)
					.append(someFilesPaths[i])
					.toString();
		}

		// Model from source code

		final ICodeLevelModel padlModelFromJavaFiles =
			Utils.createLightJavaFilesPadlModel(
				"",
				javaFilesFolderPath,
				classPathEntry,
				someFilesPaths);

		IInterface interface1 =
			(IInterface) padlModelFromJavaFiles
				.getTopLevelEntityFromID("ptidej.example.FileAndClassNames2.Element");
		IClass class1 =
			(IClass) padlModelFromJavaFiles
				.getTopLevelEntityFromID("ptidej.example.FileAndClassNames2.Document");
		IMethod method =
			(IMethod) class1.getConstituentFromName("removeComponent");
		IParameter param = (IParameter) method.getConstituentFromName("e");
		ProxyConsole
			.getInstance()
			.debugOutput()
			.println(param.getDisplayTypeName());

		Assert.assertNotNull("entity is not null", interface1);
		//the type is not equal to what is expected
		Assert.assertTrue(!("ptidej.example.FileAndClassNames.Element"
			.equals(param.getDisplayTypeName())));

	}

}
