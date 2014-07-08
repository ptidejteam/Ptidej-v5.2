/*
 * Cr le 2006-02-17
 *
 * TODO Pour changer le modle de ce fichier gnr, allez  :
 * Fentre - Prfrences - Java - Style de code - Modles de code
 */
package padl.micropatterns.test.cases;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.impl.Factory;
import padl.micropatterns.helper.MicroPatternDetector;

/**
 * @author tanterij
 *
 * TODO Pour changer le modle de ce commentaire de type gnr, allez  :
 * Fentre - Prfrences - Java - Style de code - Modles de code
 */
public class TestOverrider extends TestCase {

	private static MicroPatternDetector detector;
	private MicroPatternDetector currentDetector;

	public TestOverrider(String arg0) {
		super(arg0);
	}

	protected void setUp() {

		if (TestOverrider.detector == null) {
			final ICodeLevelModel codeLevelModel =
				Factory.getInstance().createCodeLevelModel(
					"ptidej.example.innerclasses");
			try {
				codeLevelModel
					.create(new CompleteClassFileCreator(
						new String[] {
								"../PADL Micro-pattern Analysis Tests/bin/padl/micropatterns/examples/Overrider.class",
								"../PADL Micro-pattern Analysis Tests/bin/padl/micropatterns/examples/OverriderBase.class", }));
			}
			catch (Exception e) {
				// TODO: handle exception
			}

			this.currentDetector = new MicroPatternDetector(codeLevelModel);
		}
	}

	public void testOverrider() {
		Assert.assertEquals(
			"Overrider",
			1,
			this.currentDetector.getNumberOfOverrider());
	}
}
