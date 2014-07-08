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
package ptidej.solver.test.java.simple;

import junit.framework.Assert;
import ptidej.solver.Occurrence;
import ptidej.solver.test.data.problem.CreationTest;
import ptidej.solver.test.java.Primitive;

public final class Creation extends Primitive {
	private static Occurrence[] BuiltSolutions;

	public Creation(final String name) {
		super(name);
	}
	protected void setUp() {
		if (Creation.BuiltSolutions == null) {
			Creation.BuiltSolutions =
				Primitive.automaticSolve(
					ptidej.solver.test.data.problem.CreationTest.class,
					ptidej.solver.test.data.source.CreationTest.class);
		}
	}
	public void testNumberOfSolutions() {
		Assert.assertEquals(
			"Number of solutions",
			2,
			Creation.BuiltSolutions.length);
	}
	public void testSolution1() {
		Assert.assertEquals(
			"Compiler -*--> ProgramNodeBuilder",
			"Compiler",
			Creation.BuiltSolutions[0]
				.getComponent(CreationTest.CREATOR)
				.getDisplayValue());
		Assert.assertEquals(
			"Compiler -*--> ProgramNodeBuilder",
			"ProgramNodeBuilder",
			Creation.BuiltSolutions[0]
				.getComponent(CreationTest.CREATED)
				.getDisplayValue());
	}
	public void testSolution2() {
		Assert.assertEquals(
			"ProgramNodeBuilder -*--> ProgramNode",
			"ProgramNodeBuilder",
			Creation.BuiltSolutions[1]
				.getComponent(CreationTest.CREATOR)
				.getDisplayValue());
		Assert.assertEquals(
			"ProgramNodeBuilder -*--> ProgramNode",
			"ProgramNode",
			Creation.BuiltSolutions[1]
				.getComponent(CreationTest.CREATED)
				.getDisplayValue());
	}
	public void testSolutionPercentage() {
		for (int i = 0; i < Creation.BuiltSolutions.length; i++) {
			Assert.assertEquals(
				"Solution with all constraints",
				100,
				Creation.BuiltSolutions[i].getConfidence());
		}
	}
}
