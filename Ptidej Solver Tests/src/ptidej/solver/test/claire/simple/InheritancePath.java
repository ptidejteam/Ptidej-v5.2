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
import ptidej.solver.test.data.pattern.InheritancePathTest;
import ptidej.solver.test.data.source.InheritanceTest;

public final class InheritancePath extends Primitive {
	private static Occurrence[] BuiltSolutions = null;

	public InheritancePath(final String name) {
		super(name);
	}
	protected void setUp() throws IllegalAccessException,
			InstantiationException {

		if (InheritancePath.BuiltSolutions == null) {
			InheritancePath.BuiltSolutions =
				this.testDesignPattern(
					InheritancePath.class,
					Primitive.ALL_SOLUTIONS,
					((IDesignMotifModel) InheritancePathTest.class
						.newInstance()).getName(),
					InheritanceTest.class,
					OccurrenceGenerator.SOLVER_AUTOMATIC,
					OccurrenceGenerator.PROBLEM_AC4);
		}
	}
	public void testNumberOfSolutions() {
		Assert.assertEquals(
			"Number of solutions",
			10,
			InheritancePath.BuiltSolutions.length);
	}
	public void testSolution1() {
		Assert.assertEquals(
			"A -|>- java.lang.Object",
			"java.lang.Object",
			InheritancePath.BuiltSolutions[0].getComponent(
				InheritancePathTest.SUPER_ENTITY).getDisplayValue());
		Assert.assertEquals(
			"A -|>- java.lang.Object",
			"A",
			InheritancePath.BuiltSolutions[0].getComponent(
				InheritancePathTest.SUB_ENTITY).getDisplayValue());
	}
	public void testSolution10() {
		Assert.assertEquals(
			"F -|>- java.lang.Object",
			"java.lang.Object",
			InheritancePath.BuiltSolutions[9].getComponent(
				InheritancePathTest.SUPER_ENTITY).getDisplayValue());
		Assert.assertEquals(
			"F -|>- java.lang.Object",
			"F",
			InheritancePath.BuiltSolutions[9].getComponent(
				InheritancePathTest.SUB_ENTITY).getDisplayValue());
	}
	public void testSolution2() {
		Assert.assertEquals("B -|>- A", "A", InheritancePath.BuiltSolutions[1]
			.getComponent(InheritancePathTest.SUPER_ENTITY)
			.getDisplayValue());
		Assert.assertEquals("B -|>- A", "B", InheritancePath.BuiltSolutions[1]
			.getComponent(InheritancePathTest.SUB_ENTITY)
			.getDisplayValue());
	}
	public void testSolution3() {
		Assert.assertEquals(
			"B -|>- java.lang.Object",
			"java.lang.Object",
			InheritancePath.BuiltSolutions[2].getComponent(
				InheritancePathTest.SUPER_ENTITY).getDisplayValue());
		Assert.assertEquals(
			"B -|>- java.lang.Object",
			"B",
			InheritancePath.BuiltSolutions[2].getComponent(
				InheritancePathTest.SUB_ENTITY).getDisplayValue());
	}
	public void testSolution4() {
		Assert.assertEquals("C -|>- A", "A", InheritancePath.BuiltSolutions[3]
			.getComponent(InheritancePathTest.SUPER_ENTITY)
			.getDisplayValue());
		Assert.assertEquals("C -|>- A", "C", InheritancePath.BuiltSolutions[3]
			.getComponent(InheritancePathTest.SUB_ENTITY)
			.getDisplayValue());
	}
	public void testSolution5() {
		Assert.assertEquals("C -|>- B", "B", InheritancePath.BuiltSolutions[4]
			.getComponent(InheritancePathTest.SUPER_ENTITY)
			.getDisplayValue());
		Assert.assertEquals("C -|>- B", "C", InheritancePath.BuiltSolutions[4]
			.getComponent(InheritancePathTest.SUB_ENTITY)
			.getDisplayValue());
	}
	public void testSolution6() {
		Assert.assertEquals(
			"C -|>- java.lang.Object",
			"java.lang.Object",
			InheritancePath.BuiltSolutions[5].getComponent(
				InheritancePathTest.SUPER_ENTITY).getDisplayValue());
		Assert.assertEquals(
			"C -|>- java.lang.Object",
			"C",
			InheritancePath.BuiltSolutions[5].getComponent(
				InheritancePathTest.SUB_ENTITY).getDisplayValue());
	}
	public void testSolution7() {
		Assert.assertEquals(
			"D -|>- java.lang.Object",
			"java.lang.Object",
			InheritancePath.BuiltSolutions[6].getComponent(
				InheritancePathTest.SUPER_ENTITY).getDisplayValue());
		Assert.assertEquals(
			"D -|>- java.lang.Object",
			"D",
			InheritancePath.BuiltSolutions[6].getComponent(
				InheritancePathTest.SUB_ENTITY).getDisplayValue());
	}
	public void testSolution8() {
		Assert.assertEquals("E -|>- D", "D", InheritancePath.BuiltSolutions[7]
			.getComponent(InheritancePathTest.SUPER_ENTITY)
			.getDisplayValue());
		Assert.assertEquals("E -|>- D", "E", InheritancePath.BuiltSolutions[7]
			.getComponent(InheritancePathTest.SUB_ENTITY)
			.getDisplayValue());
	}
	public void testSolution9() {
		Assert.assertEquals(
			"E -|>- java.lang.Object",
			"java.lang.Object",
			InheritancePath.BuiltSolutions[8].getComponent(
				InheritancePathTest.SUPER_ENTITY).getDisplayValue());
		Assert.assertEquals(
			"E -|>- java.lang.Object",
			"E",
			InheritancePath.BuiltSolutions[8].getComponent(
				InheritancePathTest.SUB_ENTITY).getDisplayValue());
	}
	public void testSolutionPercentage() {
		for (int i = 0; i < InheritancePath.BuiltSolutions.length; i++) {
			Assert.assertEquals(
				"Solution with all constraints",
				100,
				InheritancePath.BuiltSolutions[i].getConfidence());
		}
	}
}
