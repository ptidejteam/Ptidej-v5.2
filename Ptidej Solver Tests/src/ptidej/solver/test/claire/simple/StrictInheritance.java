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
package ptidej.solver.test.claire.simple;

import junit.framework.Assert;
import padl.motif.IDesignMotifModel;
import ptidej.solver.Occurrence;
import ptidej.solver.OccurrenceGenerator;
import ptidej.solver.test.claire.Primitive;
import ptidej.solver.test.data.pattern.StrictInheritanceTest;
import ptidej.solver.test.data.source.InheritanceTest;

public final class StrictInheritance extends Primitive {
	private static Occurrence[] BuiltSolutions;

	public StrictInheritance(final String name) {
		super(name);
	}
	protected void setUp() throws IllegalAccessException,
			InstantiationException {

		if (StrictInheritance.BuiltSolutions == null) {
			StrictInheritance.BuiltSolutions =
				this.testDesignPattern(
					StrictInheritance.class,
					Primitive.ALL_SOLUTIONS,
					((IDesignMotifModel) StrictInheritanceTest.class
						.newInstance()).getName(),
					InheritanceTest.class,
					OccurrenceGenerator.SOLVER_AUTOMATIC,
					OccurrenceGenerator.PROBLEM_AC4);
		}
	}
	public void testNumberOfSolutions() {
		Assert.assertEquals(
			"Number of solutions",
			6,
			StrictInheritance.BuiltSolutions.length);
	}
	public void testSolution1() {
		Assert.assertEquals(
			"A -|>- java.lang.Object",
			"java.lang.Object",
			StrictInheritance.BuiltSolutions[0].getComponent(
				StrictInheritanceTest.SUPER_ENTITY).getDisplayValue());
		Assert.assertEquals(
			"A -|>- java.lang.Object",
			"A",
			StrictInheritance.BuiltSolutions[0].getComponent(
				StrictInheritanceTest.SUB_ENTITY).getDisplayValue());
	}
	public void testSolution2() {
		Assert.assertEquals(
			"B -|>- A",
			"A",
			StrictInheritance.BuiltSolutions[1].getComponent(
				StrictInheritanceTest.SUPER_ENTITY).getDisplayValue());
		Assert.assertEquals(
			"B -|>- A",
			"B",
			StrictInheritance.BuiltSolutions[1].getComponent(
				StrictInheritanceTest.SUB_ENTITY).getDisplayValue());
	}
	public void testSolution3() {
		Assert.assertEquals(
			"C -|>- B",
			"B",
			StrictInheritance.BuiltSolutions[2].getComponent(
				StrictInheritanceTest.SUPER_ENTITY).getDisplayValue());
		Assert.assertEquals(
			"C -|>- B",
			"C",
			StrictInheritance.BuiltSolutions[2].getComponent(
				StrictInheritanceTest.SUB_ENTITY).getDisplayValue());
	}
	public void testSolution4() {
		Assert.assertEquals(
			"D -|>- java.lang.Object",
			"java.lang.Object",
			StrictInheritance.BuiltSolutions[3].getComponent(
				StrictInheritanceTest.SUPER_ENTITY).getDisplayValue());
		Assert.assertEquals(
			"D -|>- java.lang.Object",
			"D",
			StrictInheritance.BuiltSolutions[3].getComponent(
				StrictInheritanceTest.SUB_ENTITY).getDisplayValue());
	}
	public void testSolution5() {
		Assert.assertEquals(
			"E -|>- D",
			"D",
			StrictInheritance.BuiltSolutions[4].getComponent(
				StrictInheritanceTest.SUPER_ENTITY).getDisplayValue());
		Assert.assertEquals(
			"E -|>- D",
			"E",
			StrictInheritance.BuiltSolutions[4].getComponent(
				StrictInheritanceTest.SUB_ENTITY).getDisplayValue());
	}
	public void testSolution6() {
		Assert.assertEquals(
			"F -|>- java.lang.Object",
			"java.lang.Object",
			StrictInheritance.BuiltSolutions[5].getComponent(
				StrictInheritanceTest.SUPER_ENTITY).getDisplayValue());
		Assert.assertEquals(
			"F -|>- java.lang.Object",
			"F",
			StrictInheritance.BuiltSolutions[5].getComponent(
				StrictInheritanceTest.SUB_ENTITY).getDisplayValue());
	}
	public void testSolutionPercentage() {
		for (int i = 0; i < StrictInheritance.BuiltSolutions.length; i++) {
			Assert.assertEquals(
				"Solution with all constraints",
				100,
				StrictInheritance.BuiltSolutions[i].getConfidence());
		}
	}
}
