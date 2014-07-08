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
import ptidej.solver.test.data.pattern.IgnoranceTest;
import ptidej.solver.test.data.source.UseTest;

public final class Ignorance extends Primitive {
	private static Occurrence[] BuiltSolutions;

	public Ignorance(final String name) {
		super(name);
	}
	protected void setUp() throws IllegalAccessException,
			InstantiationException {

		if (Ignorance.BuiltSolutions == null) {
			Ignorance.BuiltSolutions =
				this.testDesignPattern(
					Ignorance.class,
					Primitive.ALL_SOLUTIONS,
					((IDesignMotifModel) IgnoranceTest.class.newInstance())
						.getName(),
					UseTest.class,
					OccurrenceGenerator.SOLVER_AUTOMATIC,
					OccurrenceGenerator.PROBLEM_AC4);
		}
	}
	public void testNumberOfSolutions() {
		Assert.assertEquals(
			"Number of solutions",
			4,
			Ignorance.BuiltSolutions.length);
	}
	public void testSolution1() {
		Assert.assertEquals(
			"Compiler -/--> ProgramNode",
			"Compiler",
			Ignorance.BuiltSolutions[0]
				.getComponent(IgnoranceTest.A)
				.getDisplayValue());
		Assert.assertEquals(
			"Compiler -/--> ProgramNode",
			"ProgramNode",
			Ignorance.BuiltSolutions[0]
				.getComponent(IgnoranceTest.B)
				.getDisplayValue());
	}
	public void testSolution2() {
		Assert.assertEquals(
			"ProgramNodeBuilder -/--> Compiler",
			"ProgramNodeBuilder",
			Ignorance.BuiltSolutions[1]
				.getComponent(IgnoranceTest.A)
				.getDisplayValue());
		Assert.assertEquals(
			"ProgramNodeBuilder -/--> Compiler",
			"Compiler",
			Ignorance.BuiltSolutions[1]
				.getComponent(IgnoranceTest.B)
				.getDisplayValue());
	}
	public void testSolution3() {
		Assert.assertEquals(
			"ProgramNode -/--> Compiler",
			"ProgramNode",
			Ignorance.BuiltSolutions[2]
				.getComponent(IgnoranceTest.A)
				.getDisplayValue());
		Assert.assertEquals(
			"ProgramNode -/--> Compiler",
			"Compiler",
			Ignorance.BuiltSolutions[2]
				.getComponent(IgnoranceTest.B)
				.getDisplayValue());
	}
	public void testSolution4() {
		Assert.assertEquals(
			"ProgramNode -/--> ProgramNodeBuilder",
			"ProgramNode",
			Ignorance.BuiltSolutions[3]
				.getComponent(IgnoranceTest.A)
				.getDisplayValue());
		Assert.assertEquals(
			"ProgramNode -/--> ProgramNodeBuilder",
			"ProgramNodeBuilder",
			Ignorance.BuiltSolutions[3]
				.getComponent(IgnoranceTest.B)
				.getDisplayValue());
	}
	public void testSolutionPercentage() {
		for (int i = 0; i < Ignorance.BuiltSolutions.length; i++) {
			Assert.assertEquals(
				"Solution with all constraints",
				100,
				Ignorance.BuiltSolutions[i].getConfidence());
		}
	}
}
