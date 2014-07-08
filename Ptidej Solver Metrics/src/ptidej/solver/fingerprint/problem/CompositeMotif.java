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
import ptidej.solver.approximation.DefaultContainerAssociationApproximations;
import ptidej.solver.approximation.DefaultIgnoranceApproximations;
import ptidej.solver.approximation.DefaultInheritanceApproximations;
import ptidej.solver.approximation.DefaultNoApproximations;
import ptidej.solver.constraint.repository.ContainerCompositionConstraint;
import ptidej.solver.constraint.repository.IgnoranceConstraint;
import ptidej.solver.constraint.repository.NotEqualConstraint;
import ptidej.solver.constraint.repository.StrictInheritanceConstraint;
import ptidej.solver.constraint.repository.StrictInheritancePathConstraint;
import ptidej.solver.domain.Manager;
import ptidej.solver.fingerprint.Problem;
import ptidej.solver.fingerprint.ReducedDomainBuilder;
import ptidej.solver.fingerprint.Rule;
import util.multilingual.MultilingualManager;

/**
 * @author Jean-Yves Guyomarc'h
 * @since  2004/10/15 
 */
public final class CompositeMotif {
	public static Problem getProblem(
		final List allEntities,
		final ReducedDomainBuilder rdg) {

		final Problem pb =
			new Problem(90, MultilingualManager.getString(
				"Problem_CDM",
				CompositeMotif.class), allEntities);

		final Variable component = new Variable(pb, "component", true);
		final Variable composite = new Variable(pb, "composite", true);
		final Variable leaf =
			new Variable(pb, "leaf", true, Manager.build(rdg
				.computeReducedDomain(Rule.C_LEAF_ROLE_1)));

		pb.addVar(leaf);
		pb.addVar(composite);
		pb.addVar(component);

		final StrictInheritanceConstraint c1 =
			new StrictInheritanceConstraint(
				"Composite -|>- Component",
				"command",
				composite,
				component,
				50,
				DefaultInheritanceApproximations.getDefaultApproximations());
		final StrictInheritancePathConstraint c2 =
			new StrictInheritancePathConstraint(
				"Leaf -|>- Component",
				"command",
				leaf,
				component,
				100,
				DefaultNoApproximations.getDefaultApproximations());
		final ContainerCompositionConstraint c3 =
			new ContainerCompositionConstraint(
				"Composite <>--> Component",
				"command",
				composite,
				component,
				50,
				DefaultContainerAssociationApproximations
					.getDefaultApproximations());
		final IgnoranceConstraint c4 =
			new IgnoranceConstraint(
				"Component -/--> Leaf",
				"command",
				component,
				leaf,
				10,
				DefaultIgnoranceApproximations.getDefaultApproximations());
		final IgnoranceConstraint c5 =
			new IgnoranceConstraint(
				"Leaf -/--> Composite",
				"command",
				leaf,
				composite,
				30,
				DefaultIgnoranceApproximations.getDefaultApproximations());
		final NotEqualConstraint c6 =
			new NotEqualConstraint(
				"Component <> Composite",
				"command",
				component,
				composite,
				100,
				DefaultNoApproximations.getDefaultApproximations());
		final NotEqualConstraint c7 =
			new NotEqualConstraint(
				"Component <> Leaf",
				"command",
				component,
				leaf,
				100,
				DefaultNoApproximations.getDefaultApproximations());
		final NotEqualConstraint c8 =
			new NotEqualConstraint(
				"Composite <> Leaf",
				"command",
				composite,
				leaf,
				100,
				DefaultNoApproximations.getDefaultApproximations());

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
