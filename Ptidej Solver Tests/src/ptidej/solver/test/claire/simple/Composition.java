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
import ptidej.solver.test.data.pattern.CompositionTest;

public final class Composition extends Primitive {
	private Occurrence[] builtSolutions;

	// Yann 2001/07/11: Too much!
	// This was too much: The Composition constraint had to many
	// Entity to play with, I only keep the Entity corresponding
	// to the target of the Composition link. The subclasses will
	// be dealt with through the StrictInheritance or
	// InheritancePath constraints.
	/*
	Composition.assertEquals("Number of solutions", 4, builtSolutions.length);
	
	for (int i = 0; i < builtSolutions.length; i++) {
	    Composition.assertEquals(
	        "Solution with all constraints", 
	        100, 
	        builtSolutions[i].getPercentage()); 
	}
	
	Composition.assertEquals(
	    "Aggregate1 == Aggregate1", 
	    "Aggregate1", 
	    builtSolutions[0].getComponent("Aggregate").getDisplayValue()); 
	Composition.assertEquals(
	    "Aggregated1 == Aggregated1", 
	    "Aggregated1", 
	    builtSolutions[0].getComponent("Aggregated").getDisplayValue()); 
	
	Composition.assertEquals(
	    "Aggregate2 == Aggregate2", 
	    "Aggregate2", 
	    builtSolutions[1].getComponent("Aggregate").getDisplayValue()); 
	Composition.assertEquals(
	    "Aggregated2 == Aggregated2", 
	    "Aggregated2", 
	    builtSolutions[1].getComponent("Aggregated").getDisplayValue()); 
	
	Composition.assertEquals(
	    "Aggregate2 == Aggregate2", 
	    "Aggregate2", 
	    builtSolutions[2].getComponent("Aggregate").getDisplayValue()); 
	Composition.assertEquals(
	    "Aggregated2 == Subclass1", 
	    "Subclass1", 
	    builtSolutions[2].getComponent("Aggregated").getDisplayValue()); 
	
	Composition.assertEquals(
	    "Aggregate2 == Aggregate2", 
	    "Aggregate2", 
	    builtSolutions[3].getComponent("Aggregate").getDisplayValue()); 
	Composition.assertEquals(
	    "Aggregated2 == Subclass2", 
	    "Subclass2", 
	    builtSolutions[3].getComponent("Aggregated").getDisplayValue());
	*/

	public Composition(final String name) {
		super(name);
	}
	protected void setUp() throws IllegalAccessException,
			InstantiationException {

		this.builtSolutions =
			this
				.testDesignPattern(
					Composition.class,
					Primitive.ALL_SOLUTIONS,
					((IDesignMotifModel) ptidej.solver.test.data.pattern.CompositionTest.class
						.newInstance()).getName(),
					ptidej.solver.test.data.source.CompositionTest.class,
					OccurrenceGenerator.SOLVER_AUTOMATIC,
					OccurrenceGenerator.PROBLEM_CUSTOM);
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
				.getComponent(CompositionTest.AGGREGATE)
				.getDisplayValue());
		Assert.assertEquals(
			"AggregatedClass1 == AggregatedClass1",
			"AggregatedClass1",
			this.builtSolutions[0]
				.getComponent(CompositionTest.AGGREGATED)
				.getDisplayValue());
	}
	public void testSolution2() {
		Assert.assertEquals(
			"AggregateClass2 == AggregateClass2",
			"AggregateClass2",
			this.builtSolutions[1]
				.getComponent(CompositionTest.AGGREGATE)
				.getDisplayValue());
		Assert.assertEquals(
			"AggregatedClass2 == AggregatedClass2",
			"AggregatedClass2",
			this.builtSolutions[1]
				.getComponent(CompositionTest.AGGREGATED)
				.getDisplayValue());
	}
	public void testSolutionPercentage() {
		for (int i = 0; i < this.builtSolutions.length; i++) {
			Assert.assertEquals(
				"Solution with all constraints",
				100,
				this.builtSolutions[i].getConfidence());
		}
	}
}
