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
import ptidej.solver.approximation.DefaultNoApproximations;
import ptidej.solver.constraint.repository.InheritancePathConstraint;
import ptidej.solver.domain.Manager;
import ptidej.solver.fingerprint.Problem;
import ptidej.solver.fingerprint.ReducedDomainBuilder;
import ptidej.solver.fingerprint.Rule;
import util.multilingual.MultilingualManager;

/**
 * @author Jean-Yves Guyomarc'h
 * @since  2004/09/05 
 */
public final class CommandMotif {
	public static Problem getProblem(
		final List allEntities,
		final ReducedDomainBuilder rdg) {
		final Problem pb =
			new Problem(90, MultilingualManager.getString(
				"Problem_CDM",
				CommandMotif.class), allEntities);

		final Variable concretecommand =
			new Variable(pb, "concretecommand", true, Manager.build(rdg
				.computeReducedDomain(Rule.CM_CC)));
		final Variable invoker =
			new Variable(pb, "invoker", true, Manager.build(rdg
				.computeReducedDomain(Rule.CM_I)));
		final Variable receiver =
			new Variable(pb, "receiver", false, Manager.build(rdg
				.computeReducedDomain(Rule.CM_R)));
		final Variable client =
			new Variable(pb, "client", false, Manager.build(rdg
				.computeReducedDomain(Rule.CM_C)));
		final Variable command = new Variable(pb, "command", false);

		pb.addVar(concretecommand);
		pb.addVar(invoker);
		pb.addVar(receiver);
		pb.addVar(client);
		pb.addVar(command);

		final InheritancePathConstraint c1 =
			new InheritancePathConstraint(
				"ConcreteCommand -|>- ... -|>- Command",
				"command",
				concretecommand,
				command,
				90,
				DefaultNoApproximations.getDefaultApproximations());

		pb.post(c1);

		return pb;
	}
}
