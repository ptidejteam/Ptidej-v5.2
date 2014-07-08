package padl.creator.test.csharpfile.v2;

import junit.framework.TestCase;
import padl.creator.csharpfile.v2.CSharpCreator;
import padl.kernel.IClass;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IMethod;
import padl.kernel.exception.CreationException;

/**
 * TestCase for our CSharp to PADL parser/converter
 */
public class TestCreatorCSharpv2 extends TestCase {
	public TestCreatorCSharpv2(final String aName) {
		super(aName);
	}

	public void testParser() throws CreationException {
		final ICodeLevelModel model =
			CSharpCreator
				.parse("../PADL Creator C# v2 Tests/rsc/parser_oracles");

		// make sure we got our right number of classes
		assertEquals(11, model.getNumberOfConstituents());

		// make sure we got the 'Line' class
		assertNotNull(model.getConstituentFromName("Line"));
		// make sure the superclass was found
		assertNotNull(((IClass) model.getConstituentFromName("Line"))
			.getInheritedEntityFromName("DrawingObject".toCharArray()));

		// make sure we got the interface
		assertNotNull(model.getConstituentFromName("IMyInterface"));
		// make sure the implementation was found
		assertNotNull(((IClass) model
			.getConstituentFromName("InterfaceImplementer"))
			.getImplementedInterface("IMyInterface".toCharArray()));

		// make sure we got the class member of Outputclass
		assertTrue(((IClass) model.getConstituentFromName("OutputClass"))
			.doesContainConstituentWithName("myString".toCharArray()));

		// make sure we got the method and parameter 'myChoice' of method makeDecision
		assertTrue(((IClass) model.getConstituentFromName("MethodParams"))
			.doesContainConstituentWithName("makedecision".toCharArray()));
		assertNotNull(((IMethod) ((IClass) model
			.getConstituentFromName("MethodParams"))
			.getConstituentFromName("makedecision"))
			.getConstituentFromName("myChoice"));

	}

}
