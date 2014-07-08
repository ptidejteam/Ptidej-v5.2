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
import ptidej.solver.approximation.TSE07CollapsableInheritanceApproximations;
import ptidej.solver.approximation.TSE07ExtensibleStrictInheritanceApproximations;
import ptidej.solver.constraint.repository.AssociationConstraint;
import ptidej.solver.constraint.repository.NoGhostEntityConstraint;
import ptidej.solver.constraint.repository.NoInheritancePathConstraint;
import ptidej.solver.constraint.repository.StrictInheritanceConstraint;

/**
 * @author Jean-Yves Guyomarc'h
 * @author Yann-Gaël Guéhéneuc
 */
public final class AbstractFactoryMotif {
	public static Problem getProblem(final List allEntities) {
		final Problem pb =
			new Problem(90, "Abstract Factory Design Motif", allEntities);

		final Variable abstractfactory =
			new Variable(pb, "abstractfactory", true);
		final Variable concretefactory =
			new Variable(pb, "concretefactory", true);
		final Variable abstractproduct =
			new Variable(pb, "abstractproduct", true);
		final Variable concreteproduct =
			new Variable(pb, "concreteproduct", false, 3);

		pb.addVar(abstractfactory);
		pb.addVar(concretefactory);
		pb.addVar(abstractproduct);
		pb.addVar(concreteproduct);

		// Constraints
		pb.post(
			new StrictInheritanceConstraint(
				"ConcreteFactory -|>- AbstractFactory",
				"",
				concretefactory,
				abstractfactory,
				50,
				TSE07CollapsableInheritanceApproximations
					.getDefaultApproximations()));

		pb.post(
			new StrictInheritanceConstraint(
				"ConcreteProduct -|>- AbstractProduct",
				"",
				concreteproduct,
				abstractproduct,
				50,
				TSE07ExtensibleStrictInheritanceApproximations
					.getDefaultApproximations()));

		pb.post(
			new NoInheritancePathConstraint(
				"ConcreteProduct -|>- .../... -|>- AbstractFactory",
				"",
				concreteproduct,
				abstractfactory,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		pb.post(
			new NoInheritancePathConstraint(
				"ConcreteFactory -|>- .../... -|>- AbstractProduct",
				"",
				concretefactory,
				abstractproduct,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		pb.post(
			new NoGhostEntityConstraint(
				"AbstractProduct <> ?",
				"",
				abstractproduct,
				100,
				DefaultNoApproximations.getDefaultApproximations()));

		pb.post(
			new AssociationConstraint(
				"ConcreteFactory ----> AbstractProduct",
				"",
				concretefactory,
				abstractproduct,
				90,
				DefaultNoApproximations.getDefaultApproximations()));

		//	pb.post(
		//		new CreationConstraint(
		//			"ConcreteFactory -*--> ConcreteProduct",
		//			"",
		//			concretefactory,
		//			concreteproduct,
		//			90,
		//			DefaultNoApproximations.getDefaultApproximations()));

		return pb;
	}
}
