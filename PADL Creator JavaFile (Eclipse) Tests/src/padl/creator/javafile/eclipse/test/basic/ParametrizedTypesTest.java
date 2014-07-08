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
package padl.creator.javafile.eclipse.test.basic;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.creator.javafile.eclipse.test.util.Utils;
import padl.kernel.IClass;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IConstructor;
import padl.kernel.IPackage;
import padl.kernel.IParameter;
import padl.kernel.impl.Factory;

public class ParametrizedTypesTest extends TestCase {

	public ParametrizedTypesTest(final String name) {
		super(name);

	}

	public void testParametrizedType() {
		final String sourcePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] javaFiles =
			new String[] { "../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/collection/" };
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
				.getTopLevelEntityFromID("padl.example.collection.CollectionExamples");

		Assert.assertNotNull(clazz);
		final IConstructor constructor =
			(IConstructor) clazz.getConstituentFromName("CollectionExamples");
		Assert.assertNotNull(constructor);

		final IParameter p =
			(IParameter) constructor.getConstituentFromName("l");
		Assert.assertNotNull(p);

		final IPackage packaje1 = (IPackage) model.getConstituentFromID("java");
		final IPackage packaje2 =
			(IPackage) packaje1.getConstituentFromID("util");
		final IPackage packaje3 =
			(IPackage) model.getConstituentFromID("java.util");

		Assert.assertNotNull(packaje1);
		Assert.assertNotNull(packaje2);
		Assert.assertNull(packaje3);

		Assert.assertEquals("java.util.List", p.getDisplayTypeName());

	}

}
