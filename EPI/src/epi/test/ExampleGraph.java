package epi.test;

import padl.kernel.IClass;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IFactory;
import padl.kernel.impl.Factory;

public class ExampleGraph {

	public static ICodeLevelModel getCodeLevelModel() {
		final IFactory factory = Factory.getInstance();
		final ICodeLevelModel codeLevelModel =
			factory.createCodeLevelModel("Graph");
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

		classB.addConstituent(factory.createAssociationRelationship(
			"as".toCharArray(),
			classA,
			2));
		classB.addConstituent(factory.createCreationRelationship(
			"cr".toCharArray(),
			classE,
			1));
		classB.addConstituent(factory.createCreationRelationship(
			"us".toCharArray(),
			classD,
			1));
		classC.addConstituent(factory.createCreationRelationship(
			"us".toCharArray(),
			classA,
			1));
		classC.addConstituent(factory.createAggregationRelationship(
			"ag".toCharArray(),
			classB,
			1));
		classC.addConstituent(factory.createAggregationRelationship(
			"ag".toCharArray(),
			classD,
			1));
		classD.addConstituent(factory.createAggregationRelationship(
			"as".toCharArray(),
			classE,
			1));

		codeLevelModel.addConstituent(classA);
		codeLevelModel.addConstituent(classB);
		codeLevelModel.addConstituent(classC);
		codeLevelModel.addConstituent(classD);
		codeLevelModel.addConstituent(classE);

		return codeLevelModel;
	}
}
