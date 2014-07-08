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
import ptidej.solver.constraint.repository.AssociationConstraint;
import ptidej.solver.constraint.repository.IgnoranceConstraint;
import ptidej.solver.constraint.repository.NoGhostEntityConstraint;
import ptidej.solver.constraint.repository.NoInheritancePathConstraint;
import ptidej.solver.constraint.repository.NotEqualConstraint;
import ptidej.solver.constraint.repository.StrictInheritanceConstraint;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/09/05 
 */
public final class AdapterMotif {
	public static Problem getProblem(final List allEntities) {
		final Problem pb =
			new Problem(90, "Adapter Design Motif", allEntities);

		final Variable client = new Variable(pb, "client", false);
		final Variable target = new Variable(pb, "target", true);
		final Variable adapter = new Variable(pb, "adapter", false);
		final Variable adaptee = new Variable(pb, "adpatee", false);

		pb.addVar(target);
		pb.addVar(adapter);
		pb.addVar(adaptee);
		pb.addVar(client);

		// Constraints
		pb.post(
			new AssociationConstraint(
				"Client ---­> Target",
				"",
				client,
				target,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		//	pb.post(
		//		new AbstractEntityConstraint(
		//			"Target is abstract",
		//			"",
		//			target,
		//			100,
		//			DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
			new NoGhostEntityConstraint(
				"Target <> ?",
				"",
				target,
				100,
				DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
			new IgnoranceConstraint(
				"Target -/--> Adapter",
				"",
				target,
				adapter,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		pb.post(
			new StrictInheritanceConstraint(
				"Adapter -|>- Target",
				"",
				adapter,
				target,
				100,
				TSE07ExtensibleStrictInheritanceApproximations
					.getDefaultApproximations()));
		pb.post(
			new AssociationConstraint(
				"Adapter ----> Adaptee",
				"",
				adapter,
				adaptee,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		pb.post(
			new NoGhostEntityConstraint(
				"Adaptee <> ?",
				"",
				adaptee,
				100,
				DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
			new NotEqualConstraint(
				"Adaptee <> Adapter",
				"",
				adaptee,
				adapter,
				100,
				DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
			new NoInheritancePathConstraint(
				"Adaptee -|>-.../...-|>- Adapter",
				"",
				adaptee,
				adapter,
				100,
				DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
			new NoInheritancePathConstraint(
				"Adapter -|>-.../...-|>- Adaptee",
				"",
				adapter,
				adaptee,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		return pb;
	}
}
