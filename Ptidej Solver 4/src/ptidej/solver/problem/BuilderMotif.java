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
import ptidej.solver.approximation.TSE07AggregationApproximations;
import ptidej.solver.approximation.TSE07ExtensibleStrictInheritanceApproximations;
import ptidej.solver.constraint.repository.AbstractEntityConstraint;
import ptidej.solver.constraint.repository.AggregationConstraint;
import ptidej.solver.constraint.repository.CreationConstraint;
import ptidej.solver.constraint.repository.NoGhostEntityConstraint;
import ptidej.solver.constraint.repository.NoObjectEntityConstraint;
import ptidej.solver.constraint.repository.StrictInheritanceConstraint;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2008/12/13 
 */
public final class BuilderMotif {
	public static Problem getProblem(final List allEntities) {
		final Problem pb =
			new Problem(90, "Builder Design Motif", allEntities);

		final Variable director = new Variable(pb, "director", false);
		final Variable builder = new Variable(pb, "builder", true);
		final Variable concretebuilder =
			new Variable(pb, "concretebuilder", false);
		final Variable product =
			new Variable(pb, "product", false);

		pb.addVar(director);
		pb.addVar(builder);
		pb.addVar(concretebuilder);
		pb.addVar(product);

		pb.post(
			new NoGhostEntityConstraint(
				"Builder <> ?",
				"",
				builder,
				100,
				DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
			new NoGhostEntityConstraint(
				"ConcreteBuilder <> ?",
				"",
				concretebuilder,
				100,
				DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
			new NoGhostEntityConstraint(
				"Product <> ?",
				"",
				product,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		pb.post(
			new NoObjectEntityConstraint(
				"Director <> java.lang.Object",
				"",
				director,
				100,
				DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
			new NoObjectEntityConstraint(
				"Product <> java.lang.Object",
				"",
				product,
				100,
				DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
			new AbstractEntityConstraint(
				"Builder must be abstract",
				"",
				builder,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		pb.post(
			new AggregationConstraint(
				"Director <>--> Builder",
				"",
				director,
				builder,
				50,
				TSE07AggregationApproximations.getDefaultApproximations()));
		pb.post(
			new StrictInheritanceConstraint(
				"ConcreteBuilder -|>- Builder",
				"",
				concretebuilder,
				builder,
				50,
				TSE07ExtensibleStrictInheritanceApproximations
					.getDefaultApproximations()));
		pb.post(
			new CreationConstraint(
				"ConcreteBuilder -*--> Product",
				"",
				concretebuilder,
				product,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		return pb;
	}
}
