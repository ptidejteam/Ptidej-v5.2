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
package ptidej.solver.test.java.fingerprint;

import padl.kernel.exception.ModelDeclarationException;
import ptidej.solver.Occurrence;
import ptidej.solver.test.java.Primitive;

public final class Composition3 extends Primitive {
	private static Occurrence[] BuiltSolutions;

	public Composition3(final String name) {
		super(name);
	}
	protected void setUp()
		throws
			CloneNotSupportedException,
			IllegalAccessException,
			InstantiationException,
			ModelDeclarationException {

		if (Composition3.BuiltSolutions == null) {
			Composition3.BuiltSolutions =
				Composition3.automaticSolve(
					ptidej
						.solver
						.test
						.data
						.problem
						.fingerprint
						.CompositionTest3
						.class,
					ptidej.solver.test.data.source.CompositionTest.class);
		}
	}
	public void testNumberOfSolutions() {
		Composition3.assertEquals(
			"Number of solutions",
			1,
			Composition3.BuiltSolutions.length);
	}
	public void testSolution() {
		Composition3.assertEquals(
			"AggregateClass2 == AggregateClass2",
			"AggregateClass2",
			Composition3
				.BuiltSolutions[0]
				.getComponent("Aggregate")
				.getValue());
		Composition3.assertEquals(
			"AggregatedClass2 == AggregatedClass2",
			"AggregatedClass2",
			Composition3
				.BuiltSolutions[0]
				.getComponent("Aggregated")
				.getValue());
	}
	public void testSolutionPercentage() {
		for (int i = 0; i < Composition3.BuiltSolutions.length; i++) {
			Composition3.assertEquals(
				"Solution with all constraints",
				100,
				Composition3.BuiltSolutions[i].getPercentage());
		}
	}
}
