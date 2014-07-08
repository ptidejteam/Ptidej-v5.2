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
package ptidej.solver.test.java.combinatorial;

import junit.framework.Assert;
import ptidej.solver.Occurrence;
import ptidej.solver.test.data.problem.CompositionAndInheritanceTest;
import ptidej.solver.test.java.Primitive;

public final class CompositionAndInheritance extends Primitive {
	private static Occurrence[] BuiltSolutions;

	public CompositionAndInheritance(final String name) {
		super(name);
	}
	protected void setUp() {
		if (CompositionAndInheritance.BuiltSolutions == null) {
			CompositionAndInheritance.BuiltSolutions =
				Primitive
					.automaticSolve(
						ptidej.solver.test.data.problem.CompositionAndInheritanceTest.class,
						ptidej.solver.test.data.source.CompositionAndInheritanceTest.class);
		}
	}
	public void testNumberOfSolutions() {
		Assert.assertEquals(
			"Number of solutions",
			1,
			CompositionAndInheritance.BuiltSolutions.length);
	}
	public void testSolution() {
		Assert.assertEquals(
			"SuperEntity",
			"java.lang.Object",
			CompositionAndInheritance.BuiltSolutions[0].getComponent(
				CompositionAndInheritanceTest.SUPER_ENTITY).getDisplayValue());
		Assert.assertEquals(
			"SubEntity",
			"B",
			CompositionAndInheritance.BuiltSolutions[0].getComponent(
				CompositionAndInheritanceTest.SUB_ENTITY).getDisplayValue());
	}
	public void testSolutionPercentage() {
		for (int i = 0; i < CompositionAndInheritance.BuiltSolutions.length; i++) {
			Assert.assertEquals(
				"Percentages of the solutions",
				20,
				CompositionAndInheritance.BuiltSolutions[i].getConfidence());
		}
	}
}
