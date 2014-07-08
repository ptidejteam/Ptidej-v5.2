/*
 * Cr le 2006-02-17
 *
 * TODO Pour changer le modle de ce fichier gnr, allez  :
 * Fentre - Prfrences - Java - Style de code - Modles de code
 */
package padl.micropatterns.test.cases;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.analysis.UnsupportedSourceModelException;
import padl.analysis.repository.AACRelationshipsAnalysis;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.IAbstractModel;
import padl.kernel.ICodeLevelModel;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import padl.micropatterns.helper.MicroPatternDetector;
import util.io.ProxyConsole;

/**
 * @author tanterij
 *
 * TODO Pour changer le modle de ce commentaire de type gnr, allez  :
 * Fentre - Prfrences - Java - Style de code - Modles de code
 */
public class TestDataManager extends TestCase {

	private static MicroPatternDetector detector;
	private MicroPatternDetector currentDetector;

	public TestDataManager(String arg0) {
		super(arg0);
	}

	protected void setUp() {
		if (TestDataManager.detector == null) {
			try {
				final ICodeLevelModel codeLevelModel =
					Factory.getInstance().createCodeLevelModel(
						"ptidej.example.innerclasses");
				codeLevelModel
					.create(new CompleteClassFileCreator(
						new String[] { "../PADL Micro-pattern Analysis Tests/bin/padl/micropatterns/examples/DataManager.class", }));

				final AACRelationshipsAnalysis accRel =
					new AACRelationshipsAnalysis();
				IAbstractModel newModel = accRel.invoke(codeLevelModel);
				this.currentDetector = new MicroPatternDetector(newModel);
			}
			catch (final CreationException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
			catch (final UnsupportedSourceModelException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
		}
	}

	public void testDataManager() {
		Assert.assertEquals(
			"Data Manager",
			1,
			this.currentDetector.getNumberOfDataManager());
	}
}
