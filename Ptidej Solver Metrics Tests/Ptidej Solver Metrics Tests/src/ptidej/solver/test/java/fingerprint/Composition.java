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

public final class Composition extends Primitive {
	private static Occurrence[] BuiltSolutions;

	public Composition(final String name) {
		super(name);
	}
	protected void setUp()
		throws
			CloneNotSupportedException,
			IllegalAccessException,
			InstantiationException,
			ModelDeclarationException {

		if (Composition.BuiltSolutions == null) {
			Composition.BuiltSolutions =
				Composition.automaticSolve(
					ptidej.solver.test.data.problem.fingerprint.CompositionTest.class,
					ptidej.solver.test.data.source.CompositionTest.class);
		}
	}
	public void testNumberOfSolutions() {
		Composition.assertEquals(
			"Number of solutions",
			2,
			Composition.BuiltSolutions.length);
	}
	public void testSolution1() {
		Composition.assertEquals(
			"AggregateClass1 == AggregateClass1",
			"AggregateClass1",
			Composition
				.BuiltSolutions[0]
				.getComponent("Aggregate")
				.getValue());
		Composition.assertEquals(
			"AggregatedClass1 == AggregatedClass1",
			"AggregatedClass1",
			Composition
				.BuiltSolutions[0]
				.getComponent("Aggregated")
				.getValue());
	}
	public void testSolution2() {
		Composition.assertEquals(
			"AggregateClass2 == AggregateClass2",
			"AggregateClass2",
			Composition
				.BuiltSolutions[1]
				.getComponent("Aggregate")
				.getValue());
		Composition.assertEquals(
			"AggregatedClass2 == AggregatedClass2",
			"AggregatedClass2",
			Composition
				.BuiltSolutions[1]
				.getComponent("Aggregated")
				.getValue());
	}
	public void testSolutionPercentage() {
		for (int i = 0; i < Composition.BuiltSolutions.length; i++) {
			Composition.assertEquals(
				"Solution with all constraints",
				100,
				Composition.BuiltSolutions[i].getPercentage());
		}
	}
}
