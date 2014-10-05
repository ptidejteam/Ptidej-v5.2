/*
 * (c) Copyright 2001-2004 Yann-Gael Gueheneuc,
 * University of Montreal.
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
import ptidej.solver.constraint.repository.AggregationConstraint;
import ptidej.solver.constraint.repository.AssociationConstraint;
import ptidej.solver.constraint.repository.IgnoranceConstraint;
import ptidej.solver.constraint.repository.InterfaceEntityConstraint;
import ptidej.solver.constraint.repository.NoGhostEntityConstraint;
import ptidej.solver.constraint.repository.NotEqualConstraint;
import ptidej.solver.constraint.repository.StrictInheritanceConstraint;

/**
 * @author Lucas Nelaupe, Ferrand Anthony, Tran Quang Dung, Verdier Frédéric 
 * @since  2014/06/01 
 */
public final class MediatorMotif {
	public static Problem getProblem(final List allEntities) {
		final Problem pb = new Problem(90, "Meditor Design Motif", allEntities);
		final Variable interfacemediator =
			new Variable(pb, "interfacemediator", true);
		final Variable concretemediator =
			new Variable(pb, "concretemediator", true);
		final Variable interfacecolleague =
			new Variable(pb, "interfacecolleague", true);
		final Variable concretecolleague =
			new Variable(pb, "concretecolleague", false);

		pb.addVar(interfacemediator);
		pb.addVar(concretemediator);
		pb.addVar(interfacecolleague);
		pb.addVar(concretecolleague);

		// Constraints
		pb.post(new NoGhostEntityConstraint(
			"ConcreteColleague <>?",
			"",
			concretecolleague,
			100,
			DefaultNoApproximations.getDefaultApproximations()));
		pb.post(new NoGhostEntityConstraint(
			"ConcreteMediator <>?",
			"",
			concretemediator,
			100,
			DefaultNoApproximations.getDefaultApproximations()));

		pb.post(new NoGhostEntityConstraint(
			"InterfaceColleague <>?",
			"",
			interfacecolleague,
			100,
			DefaultNoApproximations.getDefaultApproximations()));

		pb.post(new InterfaceEntityConstraint(
			"InterfaceColleague is an interface",
			"",
			interfacecolleague,
			100,
			DefaultNoApproximations.getDefaultApproximations()));
		pb.post(new NoGhostEntityConstraint(
			"InterfaceMediator <>?",
			"",
			interfacemediator,
			100,
			DefaultNoApproximations.getDefaultApproximations()));
		pb.post(new InterfaceEntityConstraint(
			"InterfaceMediator is an interface",
			"",
			interfacemediator,
			100,
			DefaultNoApproximations.getDefaultApproximations()));
		pb.post(new StrictInheritanceConstraint(
			"ConcreteColleague -|>- InterfaceColleague",
			"",
			concretecolleague,
			interfacecolleague,
			100,
			DefaultNoApproximations.getDefaultApproximations()));
		pb.post(new StrictInheritanceConstraint(
			"ConcreteMediator -|>- InterfaceMediator",
			"",
			concretemediator,
			interfacemediator,
			100,
			DefaultNoApproximations.getDefaultApproximations()));
		pb.post(new NotEqualConstraint(
			"InterfaceMediator <> InterfaceColleague",
			"",
			interfacemediator,
			interfacecolleague,
			100,
			DefaultNoApproximations.getDefaultApproximations()));
		pb.post(new AggregationConstraint(
			"ConcreteMediator ---ｭ> ConcreteColleague",
			"",
			concretemediator,
			concretecolleague,
			100,
			DefaultNoApproximations.getDefaultApproximations()));
		pb.post(new AssociationConstraint(
			"ConcreteColleague ---ｭ> InterfaceMediator",
			"",
			concretecolleague,
			interfacemediator,
			100,
			DefaultNoApproximations.getDefaultApproximations()));
		pb.post(new IgnoranceConstraint(
			"ConcreteColleague ---ｭ> InterfaceMediator",
			"",
			concretecolleague,
			concretecolleague,
			100,
			DefaultNoApproximations.getDefaultApproximations()));

		return pb;
	}
}
