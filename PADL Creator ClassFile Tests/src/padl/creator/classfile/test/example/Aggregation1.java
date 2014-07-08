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
package padl.creator.classfile.test.example;

import junit.framework.Assert;
import padl.analysis.UnsupportedSourceModelException;
import padl.analysis.repository.AACRelationshipsAnalysis;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.creator.classfile.test.ClassFilePrimitive;
import padl.kernel.Constants;
import padl.kernel.IAssociation;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IContainerAggregation;
import padl.kernel.IElement;
import padl.kernel.IField;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IIdiomLevelModel;
import padl.kernel.exception.CreationException;
import padl.util.Util;
import util.lang.Modifier;

/**
 * @author 	Yann-Gaël Guéhéneuc
 * @since	2002/08/15
 */
public class Aggregation1 extends ClassFilePrimitive {
	private static IElement[] Elements = null;
	private static IFirstClassEntity[] FirstClassEntities = null;

	public Aggregation1(final String name) {
		super(name);
	}
	protected void setUp() throws CreationException,
			UnsupportedSourceModelException {

		if (Aggregation1.FirstClassEntities == null
				|| Aggregation1.Elements == null) {

			final ICodeLevelModel codeLevelModel =
				ClassFilePrimitive.getFactory().createCodeLevelModel(
					"ptidej.example.messagetype");
			codeLevelModel
				.create(new CompleteClassFileCreator(
					new String[] { "../PADL Creator ClassFile Tests/bin/padl/example/aggregation/Aggregation1.class" }));

			final IIdiomLevelModel idiomLevelModel =
				(IIdiomLevelModel) new AACRelationshipsAnalysis()
					.invoke(codeLevelModel);

			Aggregation1.FirstClassEntities =
				Util.getArrayOfTopLevelEntities(idiomLevelModel);

			Aggregation1.Elements =
				Util.getArrayOfElements(Aggregation1.FirstClassEntities[2]);
		}
	}
	public void testAddMethod() {
		Assert.assertEquals(
			"Method",
			"getA",
			Aggregation1.Elements[4].getDisplayName());
	}
	public void testAssociation() {
		ClassFilePrimitive.assertAssigable(
			"Association relationship type",
			IAssociation.class,
			Aggregation1.Elements[1].getClass());
		Assert.assertEquals(
			"Association relationship visibility",
			Modifier.toString(Modifier.PUBLIC),
			Modifier.toString(Aggregation1.Elements[1].getVisibility()));
		Assert.assertEquals(
			"Association relationship cardinality",
			Constants.CARDINALITY_ONE,
			((IAssociation) Aggregation1.Elements[1]).getCardinality());
		Assert.assertEquals(
			"Association relationship name",
			"padl.kernel.impl.Association:java.lang.Object:1",
			Aggregation1.Elements[1].getDisplayName());
		Assert.assertEquals(
			"Association relationship target",
			"java.lang.Object",
			((IAssociation) Aggregation1.Elements[1])
				.getTargetEntity()
				.getDisplayID());
	}
	public void testConstructor() {
		Assert.assertEquals(
			"Constructor",
			"Aggregation1",
			Aggregation1.Elements[0].getDisplayName());
	}
	public void testContainerAggregation() {
		ClassFilePrimitive.assertAssigable(
			"ContainerAggregation relationship type",
			IContainerAggregation.class,
			Aggregation1.Elements[3].getClass());
		Assert.assertEquals(
			"ContainerAggregation relationship visibility",
			Modifier.toString(Modifier.DEFAULT),
			Modifier.toString(Aggregation1.Elements[3].getVisibility()));
		Assert
			.assertEquals(
				"ContainerAggregation relationship cardinality",
				Constants.CARDINALITY_ONE,
				((IContainerAggregation) Aggregation1.Elements[3])
					.getCardinality());
		Assert
			.assertEquals(
				"ContainerAggregation relationship name",
				"padl.kernel.impl.ContainerAggregation:padl.example.aggregation.A:1",
				Aggregation1.Elements[3].getDisplayName());
		Assert.assertEquals(
			"ContainerAggregation relationship target",
			"padl.example.aggregation.A",
			((IContainerAggregation) Aggregation1.Elements[3])
				.getTargetEntity()
				.getDisplayID());
	}
	public void testField() {
		Assert.assertEquals(
			"Field visibility",
			Modifier.toString(Modifier.PRIVATE),
			Modifier.toString(Aggregation1.Elements[2].getVisibility()));
		Assert.assertEquals(
			"Field type",
			"padl.example.aggregation.A",
			((IField) Aggregation1.Elements[2]).getDisplayTypeName());
		Assert.assertEquals(
			"Field name",
			"a",
			Aggregation1.Elements[2].getDisplayName());
	}
	public void testGetUseRelationship() {
		ClassFilePrimitive.assertAssigable(
			"Use relationship type",
			IAssociation.class,
			Aggregation1.Elements[5].getClass());
		Assert.assertEquals(
			"Use relationship visibility",
			Modifier.toString(Modifier.DEFAULT),
			Modifier.toString(Aggregation1.Elements[5].getVisibility()));
		Assert.assertEquals(
			"Use relationship cardinality",
			Constants.CARDINALITY_ONE,
			((IAssociation) Aggregation1.Elements[5]).getCardinality());
		Assert.assertEquals(
			"Use relationship name",
			"padl.kernel.impl.Association:padl.example.aggregation.A:1",
			Aggregation1.Elements[5].getDisplayName());
		Assert.assertEquals(
			"Use relationship target",
			"padl.example.aggregation.A",
			((IAssociation) Aggregation1.Elements[5])
				.getTargetEntity()
				.getDisplayID());
	}
	public void testMainEntityName() {
		Assert.assertEquals(
			"Entity name",
			"padl.example.aggregation.Aggregation1",
			Aggregation1.FirstClassEntities[2].getDisplayID());
	}
	public void testNumberOfElements() {
		Assert.assertEquals(
			"Number of elements",
			8,
			Aggregation1.Elements.length);
	}
	public void testNumberOfEntities() {
		Assert.assertEquals(
			"Number of entities",
			3,
			Aggregation1.FirstClassEntities.length);
	}
	public void testRemoveMethod() {
		Assert.assertEquals(
			"Aggregation",
			"setA",
			Aggregation1.Elements[6].getDisplayName());
	}
	//	public void testSetUseRelationship() {
	//		Assert.assertAssigable(
	//			"Use relationship type",
	//			IUseRelationship.class,
	//			Aggregation1.Elements[7].getClass());
	//		Assert.assertEquals(
	//			"Use relationship visibility",
	//			Modifier.toString(Modifier.DEFAULT),
	//			Modifier.toString(Aggregation1.Elements[7].getVisibility()));
	//		Assert.assertEquals(
	//			"Use relationship cardinality",
	//			1,
	//			((IUseRelationship) Aggregation1.Elements[7]).getCardinality());
	//		Assert.assertEquals(
	//			"Use relationship name",
	//			"setA_2",
	//			Aggregation1.Elements[7].getName());
	//		Assert.assertEquals(
	//			"Use relationship target",
	//			"padl.example.aggregation.A",
	//			((IUseRelationship) Aggregation1.Elements[7])
	//				.getTargetEntity()
	//				.getName());
	//	}
}
