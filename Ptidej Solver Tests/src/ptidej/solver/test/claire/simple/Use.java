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
import ptidej.solver.test.data.pattern.UseTest;

public final class Use extends Primitive {
	private static Occurrence[] BuiltSolutions;

	public Use(final String name) {
		super(name);
	}
	protected void setUp() throws IllegalAccessException,
			InstantiationException {

		if (Use.BuiltSolutions == null) {
			Use.BuiltSolutions =
				this
					.testDesignPattern(
						Use.class,
						Primitive.ALL_SOLUTIONS,
						((IDesignMotifModel) ptidej.solver.test.data.pattern.UseTest.class
							.newInstance()).getName(),
						ptidej.solver.test.data.source.UseTest.class,
						OccurrenceGenerator.SOLVER_AUTOMATIC,
						OccurrenceGenerator.PROBLEM_AC4);
		}
	}
	public void testNumberOfSolutions() {
		Assert
			.assertEquals("Number of solutions", 2, Use.BuiltSolutions.length);
	}
	public void testSolution1() {
		Assert.assertEquals(
			"Compiler ----> ProgramNodeBuilder",
			"Compiler",
			Use.BuiltSolutions[0]
				.getComponent(UseTest.CALLER)
				.getDisplayValue());
		Assert.assertEquals(
			"Compiler ----> ProgramNodeBuilder",
			"ProgramNodeBuilder",
			Use.BuiltSolutions[0]
				.getComponent(UseTest.CALLEE)
				.getDisplayValue());
	}
	public void testSolution2() {
		Assert.assertEquals(
			"ProgramNodeBuilder ----> ProgramNode",
			"ProgramNodeBuilder",
			Use.BuiltSolutions[1]
				.getComponent(UseTest.CALLER)
				.getDisplayValue());
		Assert.assertEquals(
			"ProgramNodeBuilder ----> ProgramNode",
			"ProgramNode",
			Use.BuiltSolutions[1]
				.getComponent(UseTest.CALLEE)
				.getDisplayValue());
	}
	public void testSolutionPercentage() {
		for (int i = 0; i < Use.BuiltSolutions.length; i++) {
			Assert.assertEquals(
				"Solution with all constraints",
				100,
				Use.BuiltSolutions[i].getConfidence());
		}
	}
}
