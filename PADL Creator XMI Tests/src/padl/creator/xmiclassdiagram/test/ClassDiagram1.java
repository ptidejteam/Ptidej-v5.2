package padl.creator.xmiclassdiagram.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.creator.xmiclassdiagram.XMICreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;

public class ClassDiagram1 extends TestCase {
	public void testCreator() {
		final ICodeLevelModel model =
			Factory.getInstance().createCodeLevelModel(new char[0]);
		try {
			model.create(new XMICreator(
				"../PADL Creator XMI Tests/data/ClassDiagram1.xmi"));
			Assert.assertEquals(19, model.getNumberOfTopLevelEntities());
		}
		catch (final CreationException e) {
			throw new RuntimeException(e);
		}
	}
}
