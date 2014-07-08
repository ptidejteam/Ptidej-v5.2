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
package ptidej.solver.test.claire.approximate.combinatorial;

import junit.framework.Assert;
import padl.motif.IDesignMotifModel;
import ptidej.solver.Occurrence;
import ptidej.solver.OccurrenceGenerator;
import ptidej.solver.test.claire.Primitive;
import ptidej.solver.test.data.pattern.BadCompositionTest;
import ptidej.solver.test.data.source.BadCompositionTest2;

public final class BadComposition2 extends Primitive {
	private Occurrence[] builtSolutions;

	public BadComposition2(final String name) {
		super(name);
	}
	protected void setUp() throws IllegalAccessException,
			InstantiationException {

		this.builtSolutions =
			this.testDesignPattern(
				BadComposition2.class,
				Primitive.ALL_SOLUTIONS,
				((IDesignMotifModel) BadCompositionTest.class.newInstance())
					.getName(),
				BadCompositionTest2.class,
				OccurrenceGenerator.SOLVER_COMBINATORIAL_AUTOMATIC,
				OccurrenceGenerator.PROBLEM_AC4);
	}
	public void testNumberOfSolutions() {
		Assert.assertEquals(
			"Number of solutions",
			2,
			this.builtSolutions.length);
	}
	public void testSolution1() {
		Assert.assertEquals(
			"AggregateClass1 == AggregateClass1",
			"AggregateClass1",
			this.builtSolutions[0]
				.getComponent(BadCompositionTest.AGGREGATE)
				.getDisplayValue());
		Assert.assertEquals(
			"AggregatedClass1 == AggregatedClass1",
			"AggregatedClass1",
			this.builtSolutions[0]
				.getComponent(BadCompositionTest.AGGREGATED)
				.getDisplayValue());
	}
	public void testSolution2() {
		Assert.assertEquals(
			"AggregateClass2 == AggregateClass2",
			"AggregateClass2",
			this.builtSolutions[1]
				.getComponent(BadCompositionTest.AGGREGATE)
				.getDisplayValue());
		Assert.assertEquals(
			"AggregatedClass2 == AggregatedClass2",
			"AggregatedClass2",
			this.builtSolutions[1]
				.getComponent(BadCompositionTest.AGGREGATED)
				.getDisplayValue());
	}
	public void testSolutionPercentage() {
		Assert.assertEquals(
			"Solution with all constraints",
			100,
			this.builtSolutions[0].getConfidence());
		Assert.assertEquals(
			"Solution with all constraints",
			100,
			this.builtSolutions[1].getConfidence());
	}
}
