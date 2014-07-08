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
import padl.creator.classfile.CompleteClassFileCreator;
import padl.creator.classfile.test.ClassFilePrimitive;
import padl.kernel.Constants;
import padl.kernel.IAssociation;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IElement;
import padl.kernel.IFirstClassEntity;
import padl.kernel.exception.CreationException;
import padl.util.Util;
import util.lang.Modifier;

/**
 * @author 	Yann-Gaël Guéhéneuc
 * @since	2002/08/15
 */
public class Aggregation14 extends ClassFilePrimitive {
	private static IElement[] Elements = null;
	private static IFirstClassEntity[] FirstClassEntities = null;

	public Aggregation14(final String name) {
		super(name);
	}
	protected void setUp() throws CreationException {
		if (Aggregation14.FirstClassEntities == null
				|| Aggregation14.Elements == null) {

			final ICodeLevelModel codeLevelModel =
				ClassFilePrimitive.getFactory().createCodeLevelModel(
					"ptidej.example.aggregations");
			codeLevelModel
				.create(new CompleteClassFileCreator(
					new String[] { "../PADL Creator ClassFile Tests/bin/padl/example/aggregation/Aggregation14.class" }));

			Aggregation14.FirstClassEntities =
				Util.getArrayOfTopLevelEntities(codeLevelModel);

			Aggregation14.Elements =
				Util.getArrayOfElements(Aggregation14.FirstClassEntities[2]);
		}
	}
	public void testConstructor() {
		Assert.assertEquals(
			"Constructor",
			"Aggregation14",
			Aggregation14.Elements[0].getName());
	}
	public void testConstructorAssociation() {
		ClassFilePrimitive.assertAssigable(
			"Association type",
			IAssociation.class,
			Aggregation14.Elements[1].getClass());
		Assert.assertEquals(
			"Association visibility",
			Modifier.toString(Modifier.PUBLIC),
			Modifier.toString(Aggregation14.Elements[1].getVisibility()));
		Assert.assertEquals(
			"Association cardinality",
			Constants.CARDINALITY_ONE,
			((IAssociation) Aggregation14.Elements[1]).getCardinality());
		Assert.assertEquals(
			"Association name",
			"padl.kernel.impl.Association:java.lang.Object:1",
			Aggregation14.Elements[1].getName());
		Assert.assertEquals(
			"Association target",
			"java.lang.Object",
			((IAssociation) Aggregation14.Elements[1])
				.getTargetEntity()
				.getName());
	}
	public void testGetAMethod() {
		Assert.assertEquals(
			"Method getA()",
			"getA",
			Aggregation14.Elements[2].getName());
	}
	public void testMainEntityName() {
		Assert.assertEquals(
			"Entity name",
			"padl.example.aggregation.Aggregation14",
			Aggregation14.FirstClassEntities[2].getName());
	}
	public void testNumberOfElements() {
		Assert.assertEquals(
			"Number of elements",
			4,
			Aggregation14.Elements.length);
	}
	public void testNumberOfEntities() {
		Assert.assertEquals(
			"Number of entities",
			3,
			Aggregation14.FirstClassEntities.length);
	}
	public void testGetAAssociation() {
		ClassFilePrimitive.assertAssigable(
			"Association type",
			IAssociation.class,
			Aggregation14.Elements[3].getClass());
		Assert.assertEquals(
			"Association visibility",
			Modifier.toString(Modifier.DEFAULT),
			Modifier.toString(Aggregation14.Elements[3].getVisibility()));
		Assert.assertEquals(
			"Association cardinality",
			Constants.CARDINALITY_ONE,
			((IAssociation) Aggregation14.Elements[3]).getCardinality());
		Assert.assertEquals(
			"Association name",
			"padl.kernel.impl.Association:padl.example.aggregation.A:1",
			Aggregation14.Elements[3].getName());
		Assert.assertEquals(
			"Association target",
			"padl.example.aggregation.A",
			((IAssociation) Aggregation14.Elements[3])
				.getTargetEntity()
				.getName());
	}
}
