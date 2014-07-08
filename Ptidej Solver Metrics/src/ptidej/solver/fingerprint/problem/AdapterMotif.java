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
package ptidej.solver.fingerprint.problem;

import java.util.List;
import ptidej.solver.Variable;
import ptidej.solver.approximation.DefaultIgnoranceApproximations;
import ptidej.solver.approximation.DefaultNoApproximations;
import ptidej.solver.constraint.repository.AssociationConstraint;
import ptidej.solver.constraint.repository.IgnoranceConstraint;
import ptidej.solver.constraint.repository.StrictInheritanceConstraint;
import ptidej.solver.domain.Manager;
import ptidej.solver.fingerprint.Problem;
import ptidej.solver.fingerprint.ReducedDomainBuilder;
import ptidej.solver.fingerprint.Rule;
import util.multilingual.MultilingualManager;

/**
 * @author Jean-Yves Guyomarc'h
 * @since 2004/10/28
 */
public final class AdapterMotif {
	public static Problem getProblem(
		final List allEntities,
		final ReducedDomainBuilder rdg) {
		final Problem pb =
			new Problem(90, MultilingualManager.getString(
				"Problem_ADM",
				AdapterMotif.class), allEntities);

		final Variable target =
			new Variable(pb, "target", true, Manager.build(rdg
				.computeReducedDomain(Rule.A_T)));
		final Variable adapter = new Variable(pb, "adapter", true);
		final Variable adaptee = new Variable(pb, "adpatee", true);
		final Variable client = new Variable(pb, "client", true);

		pb.addVar(target);
		pb.addVar(adapter);
		pb.addVar(adaptee);
		pb.addVar(client);

		// Constraints
		final StrictInheritanceConstraint c1 =
			new StrictInheritanceConstraint(
				"Adapter -|>- Target",
				"command",
				adapter,
				target,
				90,
				DefaultNoApproximations.getDefaultApproximations());

		final AssociationConstraint c2 =
			new AssociationConstraint(
				"Adapter ---­> Adaptee",
				"command",
				adapter,
				adaptee,
				90,
				DefaultNoApproximations.getDefaultApproximations());
		final AssociationConstraint c3 =
			new AssociationConstraint(
				"Client ---­> Target",
				"command",
				client,
				target,
				90,
				DefaultNoApproximations.getDefaultApproximations());

		final IgnoranceConstraint c4 =
			new IgnoranceConstraint(
				"Adapter -/--> Client",
				"command",
				adapter,
				client,
				70,
				DefaultIgnoranceApproximations.getDefaultApproximations());
		final IgnoranceConstraint c5 =
			new IgnoranceConstraint(
				"Adaptee -/--> Adapter",
				"command",
				adaptee,
				adapter,
				70,
				DefaultIgnoranceApproximations.getDefaultApproximations());
		final IgnoranceConstraint c6 =
			new IgnoranceConstraint(
				"Client -/--> Adaptee",
				"command",
				client,
				adaptee,
				70,
				DefaultIgnoranceApproximations.getDefaultApproximations());
		final IgnoranceConstraint c7 =
			new IgnoranceConstraint(
				"Target -/--> Adaptee",
				"command",
				target,
				adaptee,
				70,
				DefaultIgnoranceApproximations.getDefaultApproximations());
		final IgnoranceConstraint c8 =
			new IgnoranceConstraint(
				"Target -/--> Adapter",
				"command",
				target,
				adapter,
				70,
				DefaultIgnoranceApproximations.getDefaultApproximations());

		pb.post(c1);
		pb.post(c2);
		pb.post(c3);
		pb.post(c4);
		pb.post(c5);
		pb.post(c6);
		pb.post(c7);
		pb.post(c8);

		return pb;
	}
}
