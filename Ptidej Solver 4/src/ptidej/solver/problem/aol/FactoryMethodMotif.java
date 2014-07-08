/*
 * (c) Copyright 2001-2004 Jean-Yves Guyomarc'h,
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
import ptidej.solver.approximation.TSE07ExtendedAssociationApproximations;
import ptidej.solver.approximation.TSE07ExtensibleStrictInheritanceApproximations;
import ptidej.solver.constraint.repository.AbstractEntityConstraint;
import ptidej.solver.constraint.repository.AssociationConstraint;
import ptidej.solver.constraint.repository.NotEqualConstraint;
import ptidej.solver.constraint.repository.StrictInheritanceConstraint;

/**
 * @author Jean-Yves Guyomarc'h
 * @author Yann-Gaël Guéhéneuc
 */
public final class FactoryMethodMotif {
	public static Problem getProblem(final List allEntities) {
		final Problem pb =
			new Problem(90, "Factory Method Design Motif", allEntities);

		final Variable creator = new Variable(pb, "creator", true);
		final Variable concreteCreator =
			new Variable(pb, "concreteCreator", false);
		final Variable product = new Variable(pb, "product", true);
		final Variable concreteProduct =
			new Variable(pb, "concreteProduct", false);

		pb.addVar(creator);
		pb.addVar(concreteCreator);
		pb.addVar(product);
		pb.addVar(concreteProduct);

		// Constraints
		pb.post(
			new StrictInheritanceConstraint(
				"ConcreteCreator -|>- Creator",
				"",
				concreteCreator,
				creator,
				50,
				TSE07ExtensibleStrictInheritanceApproximations
					.getDefaultApproximations()));

		pb.post(
			new StrictInheritanceConstraint(
				"ConcreteProdcut -|>- Product",
				"",
				concreteProduct,
				product,
				50,
				TSE07ExtensibleStrictInheritanceApproximations
					.getDefaultApproximations()));

		pb.post(
			new AssociationConstraint(
				"Creator ----> Product",
				"",
				creator,
				product,
				80,
				TSE07ExtendedAssociationApproximations
					.getDefaultApproximations()));

		pb.post(
			new AssociationConstraint(
				"ConcreteCreator ----> ConcreteProduct",
				"",
				concreteCreator,
				concreteProduct,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		pb.post(
			new NotEqualConstraint(
				"Creator <> Product",
				"",
				creator,
				product,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		pb.post(
			new NotEqualConstraint(
				"Creator <> ConcreteCreator",
				"",
				creator,
				concreteCreator,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		pb.post(
			new NotEqualConstraint(
				"Creator <> ConcreteProduct",
				"",
				creator,
				concreteProduct,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		pb.post(
			new NotEqualConstraint(
				"Product <> ConcreteProduct",
				"",
				product,
				concreteProduct,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		pb.post(
			new NotEqualConstraint(
				"ConcreteCreator <> ConcreteProduct",
				"",
				concreteCreator,
				concreteProduct,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		pb.post(
			new NotEqualConstraint(
				"ConcreteCreator <> Product",
				"",
				concreteCreator,
				product,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		pb.post(
			new AbstractEntityConstraint(
				"Product is abstract",
				"",
				product,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		pb.post(
			new AbstractEntityConstraint(
				"Creator is abstract",
				"",
				creator,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		return pb;
	}
}
