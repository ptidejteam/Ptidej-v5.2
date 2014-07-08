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
import padl.kernel.IElement;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IIdiomLevelModel;
import padl.kernel.IUseRelationship;
import padl.kernel.exception.CreationException;
import padl.util.Util;
import util.lang.Modifier;

/**
 * @version	0.2
 * @author 	Yann-Gaël Guéhéneuc
 * @since	2002/08/15
 */
public class Association_INSTANCE_INSTANCE_2 extends ClassFilePrimitive {
	private static IFirstClassEntity[] FirstClassEntities = null;
	private static IElement[] Elements = null;

	public Association_INSTANCE_INSTANCE_2(final String name) {
		super(name);
	}
	protected void setUp() throws CreationException,
			UnsupportedSourceModelException {

		if (Association_INSTANCE_INSTANCE_2.FirstClassEntities == null
				|| Association_INSTANCE_INSTANCE_2.Elements == null) {

			final ICodeLevelModel codeLevelModel =
				ClassFilePrimitive.getFactory().createCodeLevelModel(
					"ptidej.example.relationship");
			codeLevelModel
				.create(new CompleteClassFileCreator(
					new String[] { "../PADL Creator ClassFile Tests/bin/padl/example/relationship/Association_INSTANCE_INSTANCE_2.class" }));

			final IIdiomLevelModel idiomLevelModel =
				(IIdiomLevelModel) new AACRelationshipsAnalysis()
					.invoke(codeLevelModel);

			Association_INSTANCE_INSTANCE_2.FirstClassEntities =
				Util.getArrayOfTopLevelEntities(idiomLevelModel);

			Association_INSTANCE_INSTANCE_2.Elements =
				Util
					.getArrayOfElements(Association_INSTANCE_INSTANCE_2.FirstClassEntities[2]);
		}
	}
	public void testConstructor() {
		Assert.assertEquals(
			"Constructor",
			"Association_INSTANCE_INSTANCE_2",
			Association_INSTANCE_INSTANCE_2.Elements[0].getDisplayName());
	}
	public void testConstructorAssociation() {
		ClassFilePrimitive.assertAssigable(
			"Association link type",
			IAssociation.class,
			Association_INSTANCE_INSTANCE_2.Elements[1].getClass());
		Assert.assertEquals("Association link visibility", Modifier
			.toString(Modifier.PUBLIC), Modifier
			.toString(Association_INSTANCE_INSTANCE_2.Elements[1]
				.getVisibility()));
		Assert.assertEquals(
			"Association link cardinality",
			Constants.CARDINALITY_ONE,
			((IAssociation) Association_INSTANCE_INSTANCE_2.Elements[1])
				.getCardinality());
		Assert.assertEquals(
			"Association link name",
			"padl.kernel.impl.Association:java.lang.Object:1",
			Association_INSTANCE_INSTANCE_2.Elements[1].getDisplayName());
		Assert.assertEquals(
			"Association link target",
			"java.lang.Object",
			((IAssociation) Association_INSTANCE_INSTANCE_2.Elements[1])
				.getTargetEntity()
				.getDisplayID());
	}
	public void testMethod() {
		Assert.assertEquals(
			"Method",
			"foo",
			Association_INSTANCE_INSTANCE_2.Elements[2].getDisplayName());
	}
	public void testAssociation() {
		ClassFilePrimitive.assertAssigable(
			"Association link type",
			IAssociation.class,
			Association_INSTANCE_INSTANCE_2.Elements[3].getClass());
		Assert.assertEquals("Association link visibility", Modifier
			.toString(Modifier.PUBLIC), Modifier
			.toString(Association_INSTANCE_INSTANCE_2.Elements[3]
				.getVisibility()));
		Assert.assertEquals(
			"Association link cardinality",
			Constants.CARDINALITY_MANY,
			((IAssociation) Association_INSTANCE_INSTANCE_2.Elements[3])
				.getCardinality());
		Assert.assertEquals(
			"Association link name",
			"padl.kernel.impl.Association:padl.example.relationship.A:2+padl.kernel.impl.Association:padl.example.relationship.A:2",
			Association_INSTANCE_INSTANCE_2.Elements[3].getDisplayName());
		Assert.assertEquals(
			"Association link target",
			"padl.example.relationship.A",
			((IAssociation) Association_INSTANCE_INSTANCE_2.Elements[3])
				.getTargetEntity()
				.getDisplayID());
	}
	public void testUseRelationship() {
		ClassFilePrimitive.assertAssigable(
			"Use relationship type",
			IUseRelationship.class,
			Association_INSTANCE_INSTANCE_2.Elements[4].getClass());
		Assert.assertEquals("Use relationship visibility", Modifier
			.toString(Modifier.PUBLIC), Modifier
			.toString(Association_INSTANCE_INSTANCE_2.Elements[4]
				.getVisibility()));
		Assert.assertEquals(
			"Use relationship cardinality",
			2,
			((IUseRelationship) Association_INSTANCE_INSTANCE_2.Elements[4])
				.getCardinality());
		Assert.assertEquals(
			"Use relationship name",
			"padl.kernel.impl.UseRelationship:padl.example.relationship.A:2",
			Association_INSTANCE_INSTANCE_2.Elements[4].getDisplayName());
		Assert.assertEquals(
			"Use relationship target",
			"padl.example.relationship.A",
			((IUseRelationship) Association_INSTANCE_INSTANCE_2.Elements[4])
				.getTargetEntity()
				.getDisplayID());
	}
	//	public void testFooAssociation() {
	//		ClassFilePrimitive.assertAssigable(
	//			"Association link type",
	//			IAssociation.class,
	//			Association_INSTANCE_INSTANCE_2.Elements[5].getClass());
	//		Assert.assertEquals("Association link visibility", Modifier
	//			.toString(Modifier.PUBLIC), Modifier
	//			.toString(Association_INSTANCE_INSTANCE_2.Elements[5]
	//				.getVisibility()));
	//		Assert.assertEquals(
	//			"Association link cardinality",
	//			Constants.CARDINALITY_ONE,
	//			((IAssociation) Association_INSTANCE_INSTANCE_2.Elements[5])
	//				.getCardinality());
	//		Assert.assertEquals(
	//			"Association link name",
	//			"padl.kernel.impl.Association:padl.example.relationship.A:1",
	//			Association_INSTANCE_INSTANCE_2.Elements[5].getDisplayName());
	//		Assert.assertEquals(
	//			"Association link target",
	//			"padl.example.relationship.A",
	//			((IAssociation) Association_INSTANCE_INSTANCE_2.Elements[5])
	//				.getTargetEntity()
	//				.getDisplayID());
	//	}
	public void testMainEntityName() {
		Assert.assertEquals(
			"Entity name",
			"padl.example.relationship.Association_INSTANCE_INSTANCE_2",
			Association_INSTANCE_INSTANCE_2.FirstClassEntities[2]
				.getDisplayID());
	}
	public void testNumberOfElements() {
		Assert.assertEquals(
			"Number of elements",
			5,
			Association_INSTANCE_INSTANCE_2.Elements.length);
	}
	public void testNumberOfEntities() {
		Assert.assertEquals(
			"Number of entities",
			3,
			Association_INSTANCE_INSTANCE_2.FirstClassEntities.length);
	}
}
