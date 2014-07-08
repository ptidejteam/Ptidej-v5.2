/*
 * (c) Copyright 2001-2006 Yann-Gaël Guéhéneuc,
 * University of Montréal.
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
package padl.test.path;

import java.util.Iterator;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.analysis.UnsupportedSourceModelException;
import padl.analysis.repository.AACRelationshipsAnalysis;
import padl.kernel.Constants;
import padl.kernel.IClass;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IConstituent;
import padl.kernel.IField;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IIdiomLevelModel;
import padl.kernel.IMethod;
import padl.kernel.IPackage;
import padl.kernel.IParameter;
import padl.kernel.IRelationship;
import padl.kernel.impl.Factory;
import util.io.ProxyConsole;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2006/11/28
 */
public class TestPath extends TestCase {
	private static IIdiomLevelModel IdiomLevelModel;
	public TestPath(final String name) {
		super(name);
	}
	public void setUp() {
		try {
			final char[] entityName = "A".toCharArray();
			final IFirstClassEntity entity =
				Factory.getInstance().createClass(entityName, entityName);

			final IMethod aGetter =
				Factory.getInstance().createMethod(
					"get".toCharArray(),
					"get".toCharArray());
			aGetter.setReturnType(entityName);
			// aGetter.addConstituent(aParameter1);

			final IParameter aParameter2 =
				Factory.getInstance().createParameter(
					entity,
					"a".toCharArray(),
					Constants.CARDINALITY_ONE);
			final IMethod aSetter =
				Factory.getInstance().createMethod(
					"set".toCharArray(),
					"set".toCharArray());
			aSetter.addConstituent(aParameter2);

			final IField aField =
				Factory.getInstance().createField(
					"a".toCharArray(),
					"a".toCharArray(),
					entityName,
					2);
			aField.setPrivate(true);

			entity.addConstituent(aGetter);
			entity.addConstituent(aSetter);
			entity.addConstituent(aField);

			final IPackage aPackage =
				Factory.getInstance().createPackage("p".toCharArray());
			aPackage.addConstituent(entity);

			final ICodeLevelModel aCodeLevelModel =
				Factory.getInstance().createCodeLevelModel("Model");
			aCodeLevelModel.addConstituent(aPackage);

			TestPath.IdiomLevelModel =
				(IIdiomLevelModel) new AACRelationshipsAnalysis()
					.invoke(aCodeLevelModel);
		}
		catch (final UnsupportedSourceModelException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
	}
	public void testClass() {
		Assert.assertEquals(
			"Class A exists",
			1,
			TestPath.IdiomLevelModel.getNumberOfTopLevelEntities());
	}
	public void testPathOfMethods() {
		final IClass clazz =
			(IClass) TestPath.IdiomLevelModel.getTopLevelEntityFromID("A");
		final Iterator iterator = clazz.getConcurrentIteratorOnConstituents();
		while (iterator.hasNext()) {
			final IConstituent constituent = (IConstituent) iterator.next();
			Assert.assertTrue("Path is right", constituent
				.getDisplayPath()
				.startsWith("/Model|p|A"));
		}
	}
	public void testMethods() {
		final IFirstClassEntity firstClassEntity =
			TestPath.IdiomLevelModel.getTopLevelEntityFromID("A");
		Assert.assertEquals(
			"Getter and setter exist",
			4,
			firstClassEntity.getNumberOfConstituents());
	}
	public void testParameters() {
		final IFirstClassEntity firstClassEntity =
			TestPath.IdiomLevelModel.getTopLevelEntityFromID("A");
		final IMethod getter =
			(IMethod) firstClassEntity.getConstituentFromID("set");
		Assert.assertEquals(
			"Parameter exists",
			1,
			getter.getNumberOfConstituents());
	}
	public void testPath() {
		final IFirstClassEntity firstClassEntity =
			TestPath.IdiomLevelModel.getTopLevelEntityFromID("A");
		final IRelationship relationship =
			(IRelationship) firstClassEntity
				.getConstituentFromID("a_ContainerAggregation_>PADL<_1");
		Assert.assertEquals(
			"Path is right",
			"/Model|p|A|a_ContainerAggregation_>PADL<_1",
			relationship.getDisplayPath());
	}
	public void testRemoveAddContainerAggregation() {
		final IFirstClassEntity firstClassEntity =
			TestPath.IdiomLevelModel.getTopLevelEntityFromID("A");
		final IRelationship relationship =
			(IRelationship) firstClassEntity
				.getConstituentFromID("a_ContainerAggregation_>PADL<_1");
		firstClassEntity.removeConstituentFromID("a_ContainerAggregation_>PADL<_1"
			.toCharArray());
		firstClassEntity.addConstituent(relationship);
		Assert.assertEquals(
			"Path is right",
			"/Model|p|A|a_ContainerAggregation_>PADL<_2",
			relationship.getDisplayPath());
	}
	public void testRemoveAddMethod() {
		final IFirstClassEntity firstClassEntity =
			TestPath.IdiomLevelModel.getTopLevelEntityFromID("A");
		final IMethod getter =
			(IMethod) firstClassEntity.getConstituentFromID("get");
		firstClassEntity.removeConstituentFromID("get".toCharArray());
		firstClassEntity.addConstituent(getter);
		Assert.assertEquals(
			"Path is right",
			"/Model|p|A|get()",
			getter.getDisplayPath());
	}
	public void testRemoveAddParameter() {
		final IFirstClassEntity firstClassEntity =
			TestPath.IdiomLevelModel.getTopLevelEntityFromID("A");
		final IMethod getter =
			(IMethod) firstClassEntity.getConstituentFromID("set");
		final IParameter parameter =
			Factory.getInstance().createParameter(firstClassEntity, 2);
		getter.addConstituent(parameter);
		Assert.assertEquals("Method ID", "set", getter.getDisplayID());
		Assert.assertEquals("Method Name", "set", getter.getDisplayName());
		Assert.assertEquals(
			"Method Path",
			"/Model|p|A|set(A)",
			getter.getDisplayPath());

		// Yann 2013/05/14: Remove what I added
		// When running the tests in the complete test suite
		// tests may be run in different order, so I make sure
		// that I leave the getter "as it was".
		getter.removeConstituentFromID(parameter.getID());
	}
	public void testAddRemoveClass1() {
		final IFirstClassEntity firstClassEntity =
			Factory.getInstance().createClass(
				"ClassToRemoveForTest".toCharArray(),
				"ClassToRemoveForTest".toCharArray());
		TestPath.IdiomLevelModel.addConstituent(firstClassEntity);
		TestPath.IdiomLevelModel.removeTopLevelEntityFromID(firstClassEntity
			.getID());
		Assert.assertEquals(
			"Class name",
			"ClassToRemoveForTest",
			firstClassEntity.getDisplayPath());
	}
	public void testAddRemoveClass2() {
		final IFirstClassEntity firstClassEntity =
			Factory.getInstance().createClass(
				"ClassToRemoveForTest".toCharArray(),
				"ClassToRemoveForTest".toCharArray());
		((IPackage) TestPath.IdiomLevelModel.getConstituentFromID("p"))
			.addConstituent(firstClassEntity);
		TestPath.IdiomLevelModel.removeTopLevelEntityFromID(firstClassEntity
			.getID());
		Assert.assertEquals(
			"Class name",
			"ClassToRemoveForTest",
			firstClassEntity.getDisplayPath());
	}
}
