package padl.creator.javafile.eclipse.test.advanced;

import junit.framework.TestCase;
import padl.creator.javafile.eclipse.test.util.RelaxedModelComparator;
import padl.creator.javafile.eclipse.test.util.Utils;
import padl.kernel.ICodeLevelModel;

public class FieldsAndReturnTypesTest extends TestCase {

	public FieldsAndReturnTypesTest(final String name) {
		super(name);

	}

	// test fields and return types in comparison with what Padl .class gives
	public void testFieldsAndReturnTypes() {

		final String sourcePath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/";
		final String[] javaFiles =
			new String[] { "../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/fieldsAndReturnTypes/FieldsAndReturnTypes.java" };
		final String classPathEntry = "";

		final String classFilesFolderPath =
			"../PADL Creator JavaFile (Eclipse) Tests/rsc/PADL testdata/padl/example/fieldsAndReturnTypes/FieldsAndReturnTypes.class";
		final ICodeLevelModel padlModelFromJavaFiles =
			Utils.createLightJavaFilesPadlModel(
				"",
				sourcePath,
				classPathEntry,
				javaFiles);
		// Model from .class
		final ICodeLevelModel padlModelFromClassFiles =
			Utils.createLightJavaClassesPadlModel("", classFilesFolderPath);

		padlModelFromJavaFiles.walk(new RelaxedModelComparator(
			padlModelFromClassFiles));
	}
}
