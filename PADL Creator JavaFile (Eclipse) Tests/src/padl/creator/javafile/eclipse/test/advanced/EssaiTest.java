package padl.creator.javafile.eclipse.test.advanced;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.creator.javafile.eclipse.test.util.Utils;
import padl.kernel.ICodeLevelModel;

public class EssaiTest extends TestCase {

	public EssaiTest(final String name) {
		super(name);

	}

	public void testClassesDuplication() {

		final String sourcePath = "";
		final String[] javaFiles =
			new String[] {
					"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/eclipse/duplication/classes/in/A.java",
					"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/eclipse/duplication/classes/out/A.java", };
		final String classPathEntry = "";

		final ICodeLevelModel model =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourcePath,
				classPathEntry,
				javaFiles);
		int nbClasses = 0;
		try {
			nbClasses =
				model.getNumberOfTopLevelEntities(Class
					.forName("padl.kernel.impl.Class"));
		}
		catch (final ClassNotFoundException e) {

			e.printStackTrace();
		}
		Assert.assertEquals(1, nbClasses);

	}

}
