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

public final class Composition4 extends Primitive {
	private static Occurrence[] BuiltSolutions;

	public Composition4(final String name) {
		super(name);
	}
	protected void setUp()
		throws
			CloneNotSupportedException,
			IllegalAccessException,
			InstantiationException,
			ModelDeclarationException {

		if (Composition4.BuiltSolutions == null) {
			Composition4.BuiltSolutions =
				Composition4.automaticSolve(
					ptidej.solver.test.data.problem.fingerprint.CompositionTest4.class,
					ptidej.solver.test.data.source.CompositionTest.class);
		}
	}
	public void testNumberOfSolutions() {
		Composition4.assertEquals(
			"Number of solutions",
			2,
			Composition4.BuiltSolutions.length);
	}
	public void testSolution1() {
		Composition4.assertEquals(
			"AggregateClass1 == AggregateClass1",
			"AggregateClass1",
			Composition4
				.BuiltSolutions[0]
				.getComponent("Aggregate")
				.getValue());
		Composition4.assertEquals(
			"AggregatedClass1 == AggregatedClass1",
			"AggregatedClass1",
			Composition4
				.BuiltSolutions[0]
				.getComponent("Aggregated")
				.getValue());
	}
	public void testSolution2() {
		Composition4.assertEquals(
			"AggregateClass2 == AggregateClass2",
			"AggregateClass2",
			Composition4
				.BuiltSolutions[1]
				.getComponent("Aggregate")
				.getValue());
		Composition4.assertEquals(
			"AggregatedClass2 == AggregatedClass2",
			"AggregatedClass2",
			Composition4
				.BuiltSolutions[1]
				.getComponent("Aggregated")
				.getValue());
	}
	public void testSolutionPercentage() {
		for (int i = 0; i < Composition4.BuiltSolutions.length; i++) {
			Composition4.assertEquals(
				"Solution with all constraints",
				100,
				Composition4.BuiltSolutions[i].getPercentage());
		}
	}
}
