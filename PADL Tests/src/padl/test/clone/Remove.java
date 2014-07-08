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
package padl.test.clone;

import java.util.Iterator;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.analysis.UnsupportedSourceModelException;
import padl.analysis.repository.AACRelationshipsAnalysis;
import padl.kernel.Constants;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IField;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IIdiomLevelModel;
import padl.kernel.IMethod;
import padl.kernel.IPackage;
import padl.kernel.IParameter;
import padl.kernel.impl.Factory;

public final class Remove extends TestCase {
	private static IIdiomLevelModel OriginalIdiomLevelModel;

	public Remove(String name) {
		super(name);
	}
	protected void setUp() throws UnsupportedSourceModelException {
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

		Remove.OriginalIdiomLevelModel =
			(IIdiomLevelModel) new AACRelationshipsAnalysis()
				.invoke(aCodeLevelModel);
	}
	public void test1() {
		final Iterator iteratorOnTopLevelEntities =
			Remove.OriginalIdiomLevelModel.getIteratorOnTopLevelEntities();
		while (iteratorOnTopLevelEntities.hasNext()) {
			final IFirstClassEntity firstClassEntity =
				(IFirstClassEntity) iteratorOnTopLevelEntities.next();
			Remove.OriginalIdiomLevelModel
				.removeTopLevelEntityFromID(firstClassEntity.getID());
		}

		final Iterator iteratorOnConstituents =
			Remove.OriginalIdiomLevelModel
				.getConcurrentIteratorOnConstituents(IPackage.class);
		while (iteratorOnConstituents.hasNext()) {
			final IPackage packaje = (IPackage) iteratorOnConstituents.next();
			Assert.assertEquals(0, packaje.getNumberOfConstituents());
		}
	}
}
