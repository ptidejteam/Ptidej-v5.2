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
import ptidej.solver.approximation.TSE07CompositionApproximations;
import ptidej.solver.approximation.TSE07ExtensibleStrictInheritanceApproximations;
import ptidej.solver.approximation.TSE07ExtensibleInheritanceOrNoneApproximations;
import ptidej.solver.constraint.repository.CompositionConstraint;
import ptidej.solver.constraint.repository.NoGhostEntityConstraint;
import ptidej.solver.constraint.repository.NotEqualConstraint;
import ptidej.solver.constraint.repository.StrictInheritanceConstraint;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/09/05 
 */
public final class DecoratorMotif {
	public static Problem getProblem(final List allEntities) {
		final Problem pb =
			new Problem(90, "Decorator Design Motif", allEntities);

		final Variable component = new Variable(pb, "component", true);
		final Variable concreteComponent =
			new Variable(pb, "concretecomponent", false);
		final Variable decorator = new Variable(pb, "decorator", true);
		final Variable concreteDecorator =
			new Variable(pb, "concretedecorator", false);

		pb.addVar(component);
		pb.addVar(concreteComponent);
		pb.addVar(decorator);
		pb.addVar(concreteDecorator);

		pb.post(
			new NoGhostEntityConstraint(
				"Component <> ?",
				"",
				component,
				100,
				DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
			new StrictInheritanceConstraint(
				"Decorator -|>- Component",
				"",
				decorator,
				component,
				50,
				TSE07ExtensibleInheritanceOrNoneApproximations
					.getDefaultApproximations()));
		pb.post(
			new CompositionConstraint(
				"Decorator <>--> Component",
				"",
				decorator,
				component,
				50,
				TSE07CompositionApproximations.getDefaultApproximations()));
		//	pb.post(
		//		new AbstractEntityConstraint(
		//			"Decorator",
		//			"",
		//			decorator,
		//			100,
		//			DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
			new StrictInheritanceConstraint(
				"ConcreteDecorator -|>- Decorator",
				"",
				concreteDecorator,
				decorator,
				100,
				DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
			new StrictInheritanceConstraint(
				"ConcreteComponent -|>- Component",
				"",
				concreteComponent,
				component,
				50,
				TSE07ExtensibleStrictInheritanceApproximations
					.getDefaultApproximations()));

		pb.post(
			new NotEqualConstraint(
				"Component <> Decorator",
				"",
				component,
				decorator,
				100,
				DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
			new NotEqualConstraint(
				"Component <> ConcreteComponent",
				"",
				component,
				concreteComponent,
				100,
				DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
			new NotEqualConstraint(
				"Decorator <> ConcreteComponent",
				"",
				decorator,
				concreteComponent,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		return pb;
	}
}
