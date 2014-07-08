/*
 * (c) Copyright 2001, 2002 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes and Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * Yann-Gaël Guéhéneuc, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR B PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.analysis.aac.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.analysis.UnsupportedSourceModelException;
import padl.analysis.repository.AACRelationshipsAnalysis;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.Constants;
import padl.kernel.IAggregation;
import padl.kernel.IAssociation;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IElement;
import padl.kernel.IField;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IIdiomLevelModel;
import padl.kernel.IUseRelationship;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import padl.util.Util;
import util.io.ProxyConsole;
import util.lang.Modifier;

/**
 * @author 	Yann-Gaël Guéhéneuc
 * @since	2005/08/08
 */
public class Aggregation_CLASS_INSTANCE_FROM_FIELD_1 extends TestCase {
	private static IElement[] Elements = null;
	private static IFirstClassEntity[] FirstClassEntities = null;

	public static void assertAssigable(
		final String aMessage,
		final Class anInterface,
		final Class aClass) {

		if (!anInterface.isAssignableFrom(aClass)) {
			Assert.fail(aMessage);
		}
	}

	public Aggregation_CLASS_INSTANCE_FROM_FIELD_1(final String name) {
		super(name);
	}
	protected void setUp() {
		if (Aggregation_CLASS_INSTANCE_FROM_FIELD_1.FirstClassEntities == null
				|| Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements == null) {

			final ICodeLevelModel codeLevelModel =
				Factory.getInstance().createCodeLevelModel(
					"ptidej.example.relationship [code-level]");
			try {
				codeLevelModel
					.create(new CompleteClassFileCreator(
						new String[] { "../PADL Analyses Tests/bin/padl/analysis/aac/data/Aggregation_CLASS_INSTANCE_FROM_FIELD_1.class" }));

				final AACRelationshipsAnalysis analysis =
					new AACRelationshipsAnalysis();
				final IIdiomLevelModel idiomLevelModel =
					(IIdiomLevelModel) analysis.invoke(codeLevelModel);

				Aggregation_CLASS_INSTANCE_FROM_FIELD_1.FirstClassEntities =
					Util.getArrayOfTopLevelEntities(idiomLevelModel);

				Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements =
					Util
						.getArrayOfElements(Aggregation_CLASS_INSTANCE_FROM_FIELD_1.FirstClassEntities[2]);
			}
			catch (final CreationException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
			catch (final UnsupportedSourceModelException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
		}
	}
	public void testAggregation() {
		Aggregation_CLASS_INSTANCE_FROM_FIELD_1.assertAssigable(
			"Aggregation link type",
			IAggregation.class,
			Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[5].getClass());
		Assert.assertEquals("Aggregation link visibility", Modifier
			.toString(Modifier.PUBLIC), Modifier
			.toString(Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[5]
				.getVisibility()));
		Assert
			.assertEquals(
				"Aggregation link cardinality",
				Constants.CARDINALITY_ONE,
				((IAggregation) Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[5])
					.getCardinality());
		Assert.assertEquals(
			"Aggregation name",
			"padl.kernel.impl.Aggregation:padl.analysis.aac.data.A:1",
			Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[5]
				.getDisplayName());
		Assert
			.assertEquals(
				"Aggregation target",
				"padl.analysis.aac.data.A",
				((IAggregation) Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[5])
					.getTargetEntity()
					.getDisplayID());
	}
	public void testAssociation() {
		Aggregation_CLASS_INSTANCE_FROM_FIELD_1.assertAssigable(
			"Association link type",
			IAssociation.class,
			Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[1].getClass());
		Assert.assertEquals("Association link visibility", Modifier
			.toString(Modifier.PUBLIC), Modifier
			.toString(Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[1]
				.getVisibility()));
		Assert
			.assertEquals(
				"Association link cardinality",
				Constants.CARDINALITY_ONE,
				((IAssociation) Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[1])
					.getCardinality());
		Assert.assertEquals(
			"Association name",
			"padl.kernel.impl.Association:java.lang.Object:1",
			Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[1]
				.getDisplayName());
		Assert
			.assertEquals(
				"Association target",
				"java.lang.Object",
				((IAssociation) Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[1])
					.getTargetEntity()
					.getDisplayID());
	}
	public void testConstructor() {
		Assert.assertEquals(
			"Constructor",
			"Aggregation_CLASS_INSTANCE_FROM_FIELD_1",
			Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[0]
				.getDisplayName());
	}
	public void testFooAssociation() {
		Aggregation_CLASS_INSTANCE_FROM_FIELD_1.assertAssigable(
			"Association link type",
			IUseRelationship.class,
			Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[6].getClass());
		Assert.assertEquals("Association link visibility", Modifier
			.toString(Modifier.PUBLIC), Modifier
			.toString(Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[6]
				.getVisibility()));
		Assert
			.assertEquals(
				"Association link cardinality",
				Constants.CARDINALITY_ONE,
				((IUseRelationship) Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[6])
					.getCardinality());
		Assert.assertEquals(
			"Association name",
			"padl.kernel.impl.UseRelationship:padl.analysis.aac.data.A:1",
			Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[6]
				.getDisplayName());
		Assert
			.assertEquals(
				"Association target",
				"padl.analysis.aac.data.A",
				((IUseRelationship) Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[6])
					.getTargetEntity()
					.getDisplayID());
	}
	public void testUseRelationship() {
		Aggregation_CLASS_INSTANCE_FROM_FIELD_1.assertAssigable(
			"Use relationship type",
			IUseRelationship.class,
			Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[4].getClass());
		Assert.assertEquals("Use relationship visibility", Modifier
			.toString(Modifier.PUBLIC), Modifier
			.toString(Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[4]
				.getVisibility()));
		Assert
			.assertEquals(
				"Use relationship cardinality",
				Constants.CARDINALITY_ONE,
				((IUseRelationship) Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[4])
					.getCardinality());
		Assert
			.assertEquals(
				"Use relationship name",
				"padl.kernel.impl.UseRelationship:padl.analysis.aac.data.Aggregation_CLASS_INSTANCE_FROM_FIELD_1:1",
				Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[4]
					.getDisplayName());
		Assert
			.assertEquals(
				"Use relationship target",
				"padl.analysis.aac.data.Aggregation_CLASS_INSTANCE_FROM_FIELD_1",
				((IUseRelationship) Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[4])
					.getTargetEntity()
					.getDisplayID());
	}
	public void testMainEntityName() {
		Assert.assertEquals(
			"Entity name",
			"padl.analysis.aac.data.Aggregation_CLASS_INSTANCE_FROM_FIELD_1",
			Aggregation_CLASS_INSTANCE_FROM_FIELD_1.FirstClassEntities[2]
				.getDisplayID());
	}
	public void testMethod() {
		Assert.assertEquals(
			"Method",
			"foo",
			Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[3]
				.getDisplayName());
	}
	public void testNumberOfElements() {
		Assert.assertEquals(
			"Number of elements",
			7,
			Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements.length);
	}
	public void testNumberOfEntities() {
		Assert.assertEquals(
			"Number of entities",
			3,
			Aggregation_CLASS_INSTANCE_FROM_FIELD_1.FirstClassEntities.length);
	}
	public void testStaticField() {
		Assert.assertEquals("Static field visibility", Modifier
			.toString(Modifier.PRIVATE | Modifier.STATIC), Modifier
			.toString(Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[2]
				.getVisibility()));
		Assert.assertEquals(
			"Static field type",
			"padl.analysis.aac.data.A",
			((IField) Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[2])
				.getDisplayTypeName());
		Assert.assertEquals(
			"Static field name",
			"a",
			Aggregation_CLASS_INSTANCE_FROM_FIELD_1.Elements[2]
				.getDisplayName());
	}
}
