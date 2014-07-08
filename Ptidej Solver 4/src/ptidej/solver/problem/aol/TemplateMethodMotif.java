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
import ptidej.solver.constraint.repository.AssociationConstraint;
import ptidej.solver.constraint.repository.StrictInheritanceConstraint;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/03/02 
 */
public final class TemplateMethodMotif {
	public static Problem getProblem(final List allEntities) {
		final Problem pb =
			new Problem(90, "Template Method Design Motif", allEntities);

		final Variable client = new Variable(pb, "client", false);
		final Variable abstractclass =
			new Variable(pb, "abstractclass", true);
		final Variable concreteclass =
			new Variable(pb, "concreteclass", false);

		pb.addVar(client);
		pb.addVar(abstractclass);
		pb.addVar(concreteclass);

		// Constraints
		pb.post(
			new AssociationConstraint(
				"Client <>--> AbstractClass",
				"",
				client,
				abstractclass,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		pb.post(
			new StrictInheritanceConstraint(
				"ConcreteClass -|>- AbstractClass",
				"",
				concreteclass,
				abstractclass,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		//	pb.post(
		//		new AbstractEntityConstraint(
		//			"AbstractClass is abstract",
		//			"",
		//			abstractclass,
		//			100,
		//			DefaultNoApproximations.getDefaultApproximations()));

		return pb;
	}
}
