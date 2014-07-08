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
package ptidej.solver.test.claire.approximate;

import junit.framework.Assert;
import padl.motif.IDesignMotifModel;
import ptidej.solver.Occurrence;
import ptidej.solver.OccurrenceGenerator;
import ptidej.solver.test.claire.Primitive;

public final class Mediator extends Primitive {
	public Mediator(final String name) {
		super(name);
	}

	/*
	 * Mediator.
	 */
	private void testAC4MediatorDesignPattern(final Occurrence[] builtSolutions) {
		Assert.assertEquals("Number of solutions", 2, builtSolutions.length);

		Assert.assertEquals(
			"Solution with all constraints",
			100,
			builtSolutions[0].getConfidence());
		Assert.assertEquals(
			"Solution with all constraints",
			100,
			builtSolutions[1].getConfidence());

		Assert.assertEquals(
			"Mediator is the mediator",
			"Mediator",
			builtSolutions[0].getComponent(
				padl.motif.repository.Mediator.MEDIATOR).getDisplayValue());
		Assert.assertEquals("Client1 is a client", "Client1", builtSolutions[0]
			.getComponent(padl.motif.repository.Mediator.CLIENT1)
			.getDisplayValue());
		Assert.assertEquals("Client2 is a client", "Client2", builtSolutions[0]
			.getComponent(padl.motif.repository.Mediator.CLIENT2)
			.getDisplayValue());

		Assert.assertEquals(
			"Mediator is the mediator",
			"Mediator",
			builtSolutions[1].getComponent(
				padl.motif.repository.Mediator.MEDIATOR).getDisplayValue());
		Assert.assertEquals("Client2 is a client", "Client2", builtSolutions[1]
			.getComponent(padl.motif.repository.Mediator.CLIENT1)
			.getDisplayValue());
		Assert.assertEquals("Client1 is a client", "Client1", builtSolutions[1]
			.getComponent(padl.motif.repository.Mediator.CLIENT2)
			.getDisplayValue());
	}
	private void testCustomMediatorDesignPattern(
		final Occurrence[] builtSolutions) {

		Assert.assertEquals("Number of solutions", 2, builtSolutions.length);

		Assert.assertEquals(
			"Solution with all constraints",
			100,
			builtSolutions[0].getConfidence());
		Assert.assertEquals(
			"Solution with all constraints",
			100,
			builtSolutions[1].getConfidence());

		Assert.assertEquals(
			"Mediator is the mediator",
			"Mediator",
			builtSolutions[0].getComponent(
				padl.motif.repository.Mediator.MEDIATOR).getDisplayValue());
		Assert.assertEquals("Client1 is a client", "Client1", builtSolutions[0]
			.getComponent(padl.motif.repository.Mediator.CLIENT1)
			.getDisplayValue());
		Assert.assertEquals("Client2 is a client", "Client2", builtSolutions[0]
			.getComponent(padl.motif.repository.Mediator.CLIENT2)
			.getDisplayValue());

		Assert.assertEquals(
			"Mediator is the mediator",
			"Mediator",
			builtSolutions[1].getComponent(
				padl.motif.repository.Mediator.MEDIATOR).getDisplayValue());
		Assert.assertEquals("Client2 is a client", "Client2", builtSolutions[1]
			.getComponent(padl.motif.repository.Mediator.CLIENT1)
			.getDisplayValue());
		Assert.assertEquals("Client1 is a client", "Client1", builtSolutions[1]
			.getComponent(padl.motif.repository.Mediator.CLIENT2)
			.getDisplayValue());
	}
	public void testMediatorDesignPattern1() throws IllegalAccessException,
			InstantiationException {

		final Occurrence[] builtSolutions =
			this.testDesignPattern(
				Mediator.class,
				Primitive.ALL_SOLUTIONS,
				((IDesignMotifModel) padl.motif.repository.Mediator.class
					.newInstance()).getName(),
				padl.motif.repository.Mediator.class,
				OccurrenceGenerator.SOLVER_AUTOMATIC,
				OccurrenceGenerator.PROBLEM_AC4);

		this.testAC4MediatorDesignPattern(builtSolutions);
	}
	public void testMediatorDesignPattern2() throws IllegalAccessException,
			InstantiationException {

		final Occurrence[] builtSolutions =
			this.testDesignPattern(
				Mediator.class,
				Primitive.ALL_SOLUTIONS,
				((IDesignMotifModel) padl.motif.repository.Mediator.class
					.newInstance()).getName(),
				padl.motif.repository.Mediator.class,
				OccurrenceGenerator.SOLVER_AUTOMATIC,
				OccurrenceGenerator.PROBLEM_CUSTOM);

		this.testCustomMediatorDesignPattern(builtSolutions);
	}
}
