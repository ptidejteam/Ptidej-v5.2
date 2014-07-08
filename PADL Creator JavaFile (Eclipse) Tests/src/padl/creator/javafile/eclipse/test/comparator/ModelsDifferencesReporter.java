package padl.creator.javafile.eclipse.test.comparator;

import junit.framework.TestCase;
import padl.creator.javafile.eclipse.test.util.RelaxedModelComparatorReporter;
import padl.creator.javafile.eclipse.test.util.Utils;
import padl.kernel.ICodeLevelModel;

public class ModelsDifferencesReporter extends TestCase {
	public ModelsDifferencesReporter(final String aName) {
		super(aName);
	}
	public void testComparison() {
		/**
		 * Compare two models created source code or binary classes based on all
		 * the source The comparison is done by ModelComparator
		 * 
		 */
		System.out.println("Models differences reporter");
		final String javaFilesFolderPath = "../Java Parser/src/";
		final String classPathEntry = "";
		//	final String classFilesFolderPath = "../Java Parser/bin/";

		// Model from source code

		final ICodeLevelModel padlModelFromJavaFiles =
			Utils.createLightJavaFilesPadlModel(
				"",
				javaFilesFolderPath,
				classPathEntry);

		// Model from .class
		//	final ICodeLevelModel padlModelFromClassFiles =
		//		Utils.createLightJavaClassesPadlModel("", classFilesFolderPath);

		padlModelFromJavaFiles.walk(new RelaxedModelComparatorReporter(
			padlModelFromJavaFiles));
	}
}
