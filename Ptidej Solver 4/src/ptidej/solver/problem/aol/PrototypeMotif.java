/*
 * (c) Copyright 2001-2004 Yann-Gaël Guéhéneuc,
 * University of Montréal.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the author, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN,
 * ANY LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHOR IS ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package ptidej.solver.problem.aol;

import java.util.List;

import ptidej.solver.Problem;
import ptidej.solver.Variable;
import ptidej.solver.approximation.DefaultNoApproximations;
import ptidej.solver.approximation.TSE07ExtensibleStrictInheritanceApproximations;
import ptidej.solver.constraint.repository.AggregationConstraint;
import ptidej.solver.constraint.repository.HasMethodConstraint;
import ptidej.solver.constraint.repository.IgnoranceConstraint;
import ptidej.solver.constraint.repository.NoGhostEntityConstraint;
import ptidej.solver.constraint.repository.StrictInheritanceConstraint;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/02/18 
 */
public final class PrototypeMotif {
	public static Problem getProblem(final List allEntities) {
		final Problem pb =
			new Problem(90, "Singleton Design Motif", allEntities);

		final Variable client = new Variable(pb, "client", false);
		final Variable prototype = new Variable(pb, "prototype", true);
		final Variable concreteprototype =
			new Variable(pb, "concreteprototype", false);

		pb.addVar(client);
		pb.addVar(prototype);
		pb.addVar(concreteprototype);

		// Constraints
		pb.post(
			new AggregationConstraint(
				"Client <>--> Prototype",
				"",
				client,
				prototype,
				100,
				DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
			new HasMethodConstraint(
				"Prototype",
				"",
				prototype,
				"clone",
				100,
				DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
			new NoGhostEntityConstraint(
				"Prototype",
				"",
				prototype,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		pb.post(
			new StrictInheritanceConstraint(
				"ConcretePrototype -|>- Prototype",
				"",
				concreteprototype,
				prototype,
				50,
				TSE07ExtensibleStrictInheritanceApproximations
					.getDefaultApproximations()));

		pb.post(
			new IgnoranceConstraint(
				"Prototype -/--> Client",
				"",
				prototype,
				client,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		pb.post(
			new IgnoranceConstraint(
				"Client -/--> ConcretePrototype",
				"",
				client,
				concreteprototype,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		pb.post(
			new IgnoranceConstraint(
				"ConcretePrototype -/--> Client",
				"",
				concreteprototype,
				client,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		return pb;
	}
}
