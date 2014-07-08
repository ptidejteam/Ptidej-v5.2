/*
 * (c) Copyright Yann-Gaël Guéhéneuc,
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
package epi.test;

import padl.kernel.IClass;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IFactory;
import padl.kernel.impl.Factory;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2005/04/08
 */
public class Test {
	public static ICodeLevelModel getCodeLevelModel() {
		final IFactory factory = Factory.getInstance();
		final ICodeLevelModel codeLevelModel =
			factory.createCodeLevelModel("Test");
		final IClass classA =
			factory.createClass("A".toCharArray(), "A".toCharArray());
		final IClass classB =
			factory.createClass("B".toCharArray(), "B".toCharArray());
		final IClass classC =
			factory.createClass("C".toCharArray(), "C".toCharArray());
		final IClass classD =
			factory.createClass("D".toCharArray(), "D".toCharArray());
		final IClass classE =
			factory.createClass("E".toCharArray(), "E".toCharArray());
		final IClass classF =
			factory.createClass("F".toCharArray(), "F".toCharArray());
		final IClass classG =
			factory.createClass("G".toCharArray(), "G".toCharArray());
		final IClass classH =
			factory.createClass("H".toCharArray(), "H".toCharArray());

		classH.addConstituent(factory.createAssociationRelationship(
			"as1".toCharArray(),
			classG,
			1));
		classH.addConstituent(factory.createCreationRelationship(
			"cr1".toCharArray(),
			classD,
			1));
		classH.addConstituent(factory.createCreationRelationship(
			"cr2".toCharArray(),
			classE,
			1));
		classH.addConstituent(factory.createCreationRelationship(
			"cr3".toCharArray(),
			classF,
			1));
		classG.addConstituent(factory.createAggregationRelationship(
			"ag1".toCharArray(),
			classA,
			1));
		classB.addInheritedEntity(classA);
		classC.addInheritedEntity(classB);
		classD.addInheritedEntity(classC);
		classD.addConstituent(factory.createAssociationRelationship(
			"as2".toCharArray(),
			classC,
			1));
		classE.addInheritedEntity(classC);
		classF.addInheritedEntity(classC);
		classF.addConstituent(
		// TODO: containerComposition or Composition?!		
		//factory.createCompositionRelationship("co1", classB, 1));
			factory.createContainerCompositionRelationship(
				"co1".toCharArray(),
				classB,
				1));

		codeLevelModel.addConstituent(classA);
		codeLevelModel.addConstituent(classB);
		codeLevelModel.addConstituent(classC);
		codeLevelModel.addConstituent(classD);
		codeLevelModel.addConstituent(classE);
		codeLevelModel.addConstituent(classF);
		codeLevelModel.addConstituent(classG);
		codeLevelModel.addConstituent(classH);

		return codeLevelModel;
	}
}
