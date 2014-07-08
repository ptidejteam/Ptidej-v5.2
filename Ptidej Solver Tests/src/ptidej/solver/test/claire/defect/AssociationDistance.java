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
package ptidej.solver.test.claire.defect;

import junit.framework.Assert;
import ptidej.solver.Occurrence;
import ptidej.solver.OccurrenceGenerator;
import ptidej.solver.test.claire.Primitive;
import ptidej.solver.test.claire.example.Composite2;
import ptidej.solver.test.data.source.AssociationTest;

public final class AssociationDistance extends Primitive {
	private static final char[] ASSOCIATED_ENTITY = "AssociatedEntity"
		.toCharArray();
	private static final char[] ASSOCIATION_DISTANCE = "AssociationDistance"
		.toCharArray();
	private static final char[] ENTITY = "Entity".toCharArray();
	private static Occurrence[] Solutions;

	public AssociationDistance(final String name) {
		super(name);
	}
	protected void setUp() throws IllegalAccessException,
			InstantiationException {

		if (AssociationDistance.Solutions == null) {
			//	final IIdiomLevelModel idiomLevelModel =
			//		Factory.getUniqueInstance().createIdiomLevelModel(
			//			"ptidej.example.composite2");
			//	idiomLevelModel.create(
			//		new LightClassFileCreator(
			//			DefaultFileRepository.getDefaultFileRepository(),
			//			new String[] { PropertyManager.getExamplesDirectory()+"ptidej/example/composite2/" }));

			AssociationDistance.Solutions =
				this.testDesignPattern(
					Composite2.class,
					Primitive.ALL_SOLUTIONS,
					"AssociationDistanceTest".toCharArray(),
					AssociationTest.class,
					OccurrenceGenerator.SOLVER_AUTOMATIC,
					OccurrenceGenerator.PROBLEM_CUSTOM);
		}
	}
	public void testNumberOfSolutions() {
		Assert.assertEquals(
			"Number of solutions",
			4,
			AssociationDistance.Solutions.length);
	}
	public void testSolution1() {
		//	1.100.Name = AssociationDistance Pattern Problem
		//	1.100.XCommand = System.out.println("No transformation required.");
		//	1.100.Entity = AggregateClass1
		//	1.100.AssociatedEntity = AggregatedClass1
		//	1.100.AssociationDistance = 1
		try {
			Assert.assertEquals(
				"Solution 1",
				"AggregateClass1",
				AssociationDistance.Solutions[0].getComponent(
					AssociationDistance.ENTITY).getDisplayValue());
			Assert.assertEquals(
				"Solution 1",
				"AggregatedClass1",
				AssociationDistance.Solutions[0].getComponent(
					AssociationDistance.ASSOCIATED_ENTITY).getDisplayValue());
			Assert
				.assertEquals(
					"Solution 1",
					"1",
					AssociationDistance.Solutions[0]
						.getComponent(AssociationDistance.ASSOCIATION_DISTANCE)
						.getDisplayValue());
		}
		catch (final Exception e) {
			e.printStackTrace();
		}
	}
	public void testSolution2() {
		//	2.100.Name = AssociationDistance Pattern Problem
		//	2.100.XCommand = System.out.println("No transformation required.");
		//	2.100.Entity = AggregateClass2
		//	2.100.AssociatedEntity = AggregatedClass2
		//	2.100.AssociationDistance = 1
		Assert.assertEquals(
			"Solution 2",
			"AggregateClass2",
			AssociationDistance.Solutions[1].getComponent(
				AssociationDistance.ENTITY).getDisplayValue());
		Assert.assertEquals(
			"Solution 2",
			"AggregatedClass2",
			AssociationDistance.Solutions[1].getComponent(
				AssociationDistance.ASSOCIATED_ENTITY).getDisplayValue());
		Assert.assertEquals("Solution 2", "1", AssociationDistance.Solutions[1]
			.getComponent(AssociationDistance.ASSOCIATION_DISTANCE)
			.getDisplayValue());
	}
	public void testSolution3() {
		//	3.100.Name = AssociationDistance Pattern Problem
		//	3.100.XCommand = System.out.println("No transformation required.");
		//	3.100.Entity = AggregatedClass1
		//	3.100.AssociatedEntity = AssociatedClass1
		//	3.100.AssociationDistance = 1
		Assert.assertEquals(
			"Solution 3",
			"AggregatedClass1",
			AssociationDistance.Solutions[2].getComponent(
				AssociationDistance.ENTITY).getDisplayValue());
		Assert.assertEquals(
			"Solution 3",
			"AssociatedClass1",
			AssociationDistance.Solutions[2].getComponent(
				AssociationDistance.ASSOCIATED_ENTITY).getDisplayValue());
		Assert.assertEquals("Solution 3", "1", AssociationDistance.Solutions[2]
			.getComponent(AssociationDistance.ASSOCIATION_DISTANCE)
			.getDisplayValue());
	}
	public void testSolution4() {
		//	4.100.Name = AssociationDistance Pattern Problem
		//	4.100.XCommand = System.out.println("No transformation required.");
		//	4.100.Entity = AggregateClass1
		//	4.100.AssociatedEntity = AssociatedClass1
		//	4.100.AssociationDistance = 2
		Assert.assertEquals(
			"Solution 4",
			"AggregateClass1",
			AssociationDistance.Solutions[3].getComponent(
				AssociationDistance.ENTITY).getDisplayValue());
		Assert.assertEquals(
			"Solution 4",
			"AssociatedClass1",
			AssociationDistance.Solutions[3].getComponent(
				AssociationDistance.ASSOCIATED_ENTITY).getDisplayValue());
		Assert.assertEquals("Solution 4", "2", AssociationDistance.Solutions[3]
			.getComponent(AssociationDistance.ASSOCIATION_DISTANCE)
			.getDisplayValue());
	}
}
