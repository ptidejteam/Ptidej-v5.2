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
package ptidej.solver.problem;

import java.util.List;

import ptidej.solver.Problem;
import ptidej.solver.Variable;
import ptidej.solver.approximation.DefaultNoApproximations;
import ptidej.solver.approximation.TSE07ContainerCompositionApproximations;
import ptidej.solver.approximation.TSE07ExtensibleStrictInheritanceApproximations;
import ptidej.solver.approximation.TSE07ExtensibleInheritanceOrNoneApproximations;
import ptidej.solver.constraint.repository.ContainerCompositionConstraint;
import ptidej.solver.constraint.repository.NoGhostEntityConstraint;
import ptidej.solver.constraint.repository.NotEqualConstraint;
import ptidej.solver.constraint.repository.StrictInheritanceConstraint;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/09/05 
 */
public final class CompositeMotif {
	public static Problem getProblem(final List allEntities) {
		final Problem pb =
			new Problem(90, "Composite Design Motif", allEntities);

		final Variable component = new Variable(pb, "component", true);
		final Variable composite = new Variable(pb, "composite", true);
		final Variable leaf = new Variable(pb, "leaf", false);

		pb.addVar(leaf);
		pb.addVar(composite);
		pb.addVar(component);

		pb.post(
			new NoGhostEntityConstraint(
				"Component <> ?",
				"",
				component,
				100,
				DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
			new StrictInheritanceConstraint(
				"Composite -|>- Component",
				"",
				composite,
				component,
				50,
				TSE07ExtensibleInheritanceOrNoneApproximations
					.getDefaultApproximations()));
		pb.post(
			new ContainerCompositionConstraint(
				"Composite <#>-> Component",
				"",
				composite,
				component,
				50,
				TSE07ContainerCompositionApproximations
					.getDefaultApproximations()));
		pb.post(
			new StrictInheritanceConstraint(
				"Leaf -|>- Component",
				"",
				leaf,
				component,
				50,
				TSE07ExtensibleStrictInheritanceApproximations
					.getDefaultApproximations()));

		pb.post(
			new NotEqualConstraint(
				"Component <> Composite",
				"",
				component,
				composite,
				100,
				DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
			new NotEqualConstraint(
				"Component <> Leaf",
				"",
				component,
				leaf,
				100,
				DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
			new NotEqualConstraint(
				"Composite <> Leaf",
				"",
				composite,
				leaf,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		return pb;
	}
}
