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
import ptidej.solver.test.data.pattern.InheritanceTest;

public final class GoodInheritance extends Primitive {
	private static Occurrence[] BuiltSolutions;

	public GoodInheritance(final String name) {
		super(name);
	}
	protected void setUp() throws IllegalAccessException,
			InstantiationException {

		if (GoodInheritance.BuiltSolutions == null) {
			GoodInheritance.BuiltSolutions =
				this
					.testDesignPattern(
						GoodInheritance.class,
						Primitive.ALL_SOLUTIONS,
						((IDesignMotifModel) ptidej.solver.test.data.pattern.InheritanceTest.class
							.newInstance()).getName(),
						ptidej.solver.test.data.source.InheritanceTest.class,
						OccurrenceGenerator.SOLVER_AUTOMATIC,
						OccurrenceGenerator.PROBLEM_CUSTOM);
		}
	}
	public void testNumberOfSolutions() {
		Assert.assertEquals(
			"Number of solutions",
			13,
			GoodInheritance.BuiltSolutions.length);
	}
	public void testSolution1() {
		Assert.assertEquals("A == A", "A", GoodInheritance.BuiltSolutions[0]
			.getComponent(InheritanceTest.SUPER_ENTITY)
			.getDisplayValue());
		Assert.assertEquals("A == A", "A", GoodInheritance.BuiltSolutions[0]
			.getComponent(InheritanceTest.SUB_ENTITY)
			.getDisplayValue());
	}
	public void testSolution10() {
		Assert.assertEquals("E == E", "E", GoodInheritance.BuiltSolutions[9]
			.getComponent(InheritanceTest.SUPER_ENTITY)
			.getDisplayValue());
		Assert.assertEquals("E == E", "E", GoodInheritance.BuiltSolutions[9]
			.getComponent(InheritanceTest.SUB_ENTITY)
			.getDisplayValue());
	}
	public void testSolution11() {
		Assert.assertEquals("F == F", "F", GoodInheritance.BuiltSolutions[10]
			.getComponent(InheritanceTest.SUPER_ENTITY)
			.getDisplayValue());
		Assert.assertEquals("F == F", "F", GoodInheritance.BuiltSolutions[10]
			.getComponent(InheritanceTest.SUB_ENTITY)
			.getDisplayValue());
	}
	public void testSolution12() {
		Assert.assertEquals(
			"F -|>- java.lang.Object",
			"java.lang.Object",
			GoodInheritance.BuiltSolutions[11].getComponent(
				InheritanceTest.SUPER_ENTITY).getDisplayValue());
		Assert.assertEquals(
			"F -|>- java.lang.Object",
			"F",
			GoodInheritance.BuiltSolutions[11].getComponent(
				InheritanceTest.SUB_ENTITY).getDisplayValue());
	}
	public void testSolution13() {
		Assert.assertEquals(
			"java.lang.Object == java.lang.Object",
			"java.lang.Object",
			GoodInheritance.BuiltSolutions[12].getComponent(
				InheritanceTest.SUPER_ENTITY).getDisplayValue());
		Assert.assertEquals(
			"java.lang.Object == java.lang.Object",
			"java.lang.Object",
			GoodInheritance.BuiltSolutions[12].getComponent(
				InheritanceTest.SUB_ENTITY).getDisplayValue());
	}
	public void testSolution2() {
		Assert.assertEquals(
			"A -|>- java.lang.Object",
			"java.lang.Object",
			GoodInheritance.BuiltSolutions[1].getComponent(
				InheritanceTest.SUPER_ENTITY).getDisplayValue());
		Assert.assertEquals(
			"A -|>- java.lang.Object",
			"A",
			GoodInheritance.BuiltSolutions[1].getComponent(
				InheritanceTest.SUB_ENTITY).getDisplayValue());
	}
	public void testSolution3() {
		Assert.assertEquals("B -|>- A", "A", GoodInheritance.BuiltSolutions[2]
			.getComponent(InheritanceTest.SUPER_ENTITY)
			.getDisplayValue());
		Assert.assertEquals("B -|>- A", "B", GoodInheritance.BuiltSolutions[2]
			.getComponent(InheritanceTest.SUB_ENTITY)
			.getDisplayValue());
	}
	public void testSolution4() {
		Assert.assertEquals("B == B", "B", GoodInheritance.BuiltSolutions[3]
			.getComponent(InheritanceTest.SUPER_ENTITY)
			.getDisplayValue());
		Assert.assertEquals("B == B", "B", GoodInheritance.BuiltSolutions[3]
			.getComponent(InheritanceTest.SUB_ENTITY)
			.getDisplayValue());
	}
	public void testSolution5() {
		Assert.assertEquals("C -|>- B", "B", GoodInheritance.BuiltSolutions[4]
			.getComponent(InheritanceTest.SUPER_ENTITY)
			.getDisplayValue());
		Assert.assertEquals("C -|>- B", "C", GoodInheritance.BuiltSolutions[4]
			.getComponent(InheritanceTest.SUB_ENTITY)
			.getDisplayValue());
	}
	public void testSolution6() {
		Assert.assertEquals("C == C", "C", GoodInheritance.BuiltSolutions[5]
			.getComponent(InheritanceTest.SUPER_ENTITY)
			.getDisplayValue());
		Assert.assertEquals("C == C", "C", GoodInheritance.BuiltSolutions[5]
			.getComponent(InheritanceTest.SUB_ENTITY)
			.getDisplayValue());
	}
	public void testSolution7() {
		Assert.assertEquals("D == D", "D", GoodInheritance.BuiltSolutions[6]
			.getComponent(InheritanceTest.SUPER_ENTITY)
			.getDisplayValue());
		Assert.assertEquals("D == D", "D", GoodInheritance.BuiltSolutions[6]
			.getComponent(InheritanceTest.SUB_ENTITY)
			.getDisplayValue());
	}
	public void testSolution8() {
		Assert.assertEquals(
			"D -|>- java.lang.Object",
			"java.lang.Object",
			GoodInheritance.BuiltSolutions[7].getComponent(
				InheritanceTest.SUPER_ENTITY).getDisplayValue());
		Assert.assertEquals(
			"D -|>- java.lang.Object",
			"D",
			GoodInheritance.BuiltSolutions[7].getComponent(
				InheritanceTest.SUB_ENTITY).getDisplayValue());
	}
	public void testSolution9() {
		Assert.assertEquals("E -|>- D", "D", GoodInheritance.BuiltSolutions[8]
			.getComponent(InheritanceTest.SUPER_ENTITY)
			.getDisplayValue());
		Assert.assertEquals("E -|>- D", "E", GoodInheritance.BuiltSolutions[8]
			.getComponent(InheritanceTest.SUB_ENTITY)
			.getDisplayValue());
	}
	public void testSolutionPercentage() {
		for (int i = 0; i < GoodInheritance.BuiltSolutions.length; i++) {
			Assert.assertEquals(
				"Solution with all constraints",
				100,
				GoodInheritance.BuiltSolutions[i].getConfidence());
		}
	}
}
