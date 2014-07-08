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
package ptidej.solver.fingerprint.problem;

import java.util.List;
import ptidej.solver.Variable;
import ptidej.solver.approximation.DefaultAssociationApproximations;
import ptidej.solver.approximation.DefaultInheritanceApproximations;
import ptidej.solver.approximation.DefaultNoApproximations;
import ptidej.solver.constraint.repository.AssociationConstraint;
import ptidej.solver.constraint.repository.CreationConstraint;
import ptidej.solver.constraint.repository.NotEqualConstraint;
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
public final class FactoryMethodMotif {
	public static Problem getProblem(
		final List allEntities,
		final ReducedDomainBuilder rdg) {

		final Problem pb =
			new Problem(90, MultilingualManager.getString(
				"Problem_FMDM",
				FactoryMethodMotif.class), allEntities);

		final Variable creator = new Variable(pb, "creator", true);
		final Variable concreteCreator =
			new Variable(pb, "concreteCreator", true, Manager.build(rdg
				.computeReducedDomain(Rule.FM_CC)));
		final Variable product = new Variable(pb, "product", true);
		final Variable concreteProduct =
			new Variable(pb, "concreteProduct", true, Manager.build(rdg
				.computeReducedDomain(Rule.FM_CP)));

		pb.addVar(creator);
		pb.addVar(concreteCreator);
		pb.addVar(product);
		pb.addVar(concreteProduct);

		//Constraint

		final StrictInheritanceConstraint c1 =
			new StrictInheritanceConstraint(
				"ConcreteCreator -|>- Creator",
				"command",
				concreteCreator,
				creator,
				50,
				DefaultInheritanceApproximations.getDefaultApproximations());

		final StrictInheritanceConstraint c2 =
			new StrictInheritanceConstraint(
				"ConcreteCreator -|>- Product",
				"command",
				concreteProduct,
				product,
				50,
				DefaultInheritanceApproximations.getDefaultApproximations());

		final AssociationConstraint c3 =
			new AssociationConstraint(
				"Creator ----> Product",
				"command",
				creator,
				product,
				80,
				DefaultAssociationApproximations.getDefaultApproximations());

		final CreationConstraint c4 =
			new CreationConstraint(
				"ConcreteCreator -*--> ConcreteProduct",
				"command",
				concreteCreator,
				concreteProduct,
				90,
				DefaultNoApproximations.getDefaultApproximations());

		final NotEqualConstraint c5 =
			new NotEqualConstraint(
				"Creator <> Product",
				"command",
				creator,
				product,
				90,
				DefaultNoApproximations.getDefaultApproximations());

		final NotEqualConstraint c6 =
			new NotEqualConstraint(
				"Creator <> ConcreteCreator",
				"command",
				creator,
				concreteCreator,
				90,
				DefaultNoApproximations.getDefaultApproximations());

		final NotEqualConstraint c7 =
			new NotEqualConstraint(
				"Creator <> ConcreteProduct",
				"command",
				creator,
				concreteProduct,
				90,
				DefaultNoApproximations.getDefaultApproximations());

		final NotEqualConstraint c8 =
			new NotEqualConstraint(
				"Product <> ConcreteProduct",
				"command",
				product,
				concreteProduct,
				90,
				DefaultNoApproximations.getDefaultApproximations());

		final NotEqualConstraint c9 =
			new NotEqualConstraint(
				"ConcreteCreator <> ConcreteProduct",
				"command",
				concreteCreator,
				concreteProduct,
				90,
				DefaultNoApproximations.getDefaultApproximations());

		final NotEqualConstraint c10 =
			new NotEqualConstraint(
				"ConcreteCraetor <> Product",
				"command",
				concreteCreator,
				product,
				90,
				DefaultNoApproximations.getDefaultApproximations());

		pb.post(c1);
		pb.post(c2);
		pb.post(c3);
		pb.post(c4);
		pb.post(c5);
		pb.post(c6);
		pb.post(c7);
		pb.post(c8);
		pb.post(c9);
		pb.post(c10);

		return pb;
	}
}
