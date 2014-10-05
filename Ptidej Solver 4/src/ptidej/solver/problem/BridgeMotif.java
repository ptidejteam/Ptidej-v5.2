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
import ptidej.solver.approximation.TSE07AbstractnessApproximations;
import ptidej.solver.approximation.TSE07AggregationApproximations;
import ptidej.solver.approximation.TSE07ExtensibleInheritanceOrNoneApproximations;
import ptidej.solver.constraint.repository.AbstractEntityConstraint;
import ptidej.solver.constraint.repository.AggregationConstraint;
import ptidej.solver.constraint.repository.InterfaceEntityConstraint;
import ptidej.solver.constraint.repository.NoGhostEntityConstraint;
import ptidej.solver.constraint.repository.NotEqualConstraint;
import ptidej.solver.constraint.repository.StrictInheritanceConstraint;

/**
 * @author Lucas Nelaupe, Ferrand Anthony, Tran Quang Dung, Verdier Frédéric 
 * @since  2014/06/01 
 */
public final class BridgeMotif {
	public static Problem getProblem(final List allEntities) 
	{
		//Creation du probleme
		final Problem pb = new Problem(90, "Bridge Design Motif", allEntities);
		//Creation des entites
		final Variable abstraction = new Variable(pb, "Abstraction", true);
		final Variable refinedAbstraction = new Variable(pb, "RefinedAbstraction", false);
		final Variable implementor = new Variable(pb, "Implementor", true);
		final Variable concreteImplementor = new Variable(pb, "ConcreteImplementor", false);
		
		//Ajout des entites au probleme.
		pb.addVar(abstraction);
		pb.addVar(refinedAbstraction);
		pb.addVar(implementor);
		pb.addVar(concreteImplementor);
		//Pas de detection sur les entites fantomes.
		pb.post(
				new NoGhostEntityConstraint(
					"Abstraction <> ?",
					"",
					abstraction,
					100,
					DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
				new NoGhostEntityConstraint(
					"RefinedAbstraction <> ?",
					"",
					refinedAbstraction,
					100,
					DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
				new NoGhostEntityConstraint(
						"Implementor <> ?",
						"",
						implementor, 
						100, 
						DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
				new NoGhostEntityConstraint(
						"ConcreteImplementor <> ?",
						"",
						concreteImplementor, 
						100, 
						DefaultNoApproximations.getDefaultApproximations()));
		//Heritage
		pb.post(
				new StrictInheritanceConstraint(
					"RefinedAbstraction -|>- Abstraction",
					"",
					refinedAbstraction,
					abstraction,
					100,
					TSE07ExtensibleInheritanceOrNoneApproximations
						.getDefaultApproximations()));
		pb.post(
				new StrictInheritanceConstraint(
					"ConcreteImplementor -|>- Implementor",
					"",
					concreteImplementor,
					implementor,
					100,
					TSE07ExtensibleInheritanceOrNoneApproximations
						.getDefaultApproximations()));
		//Les entites doivent etre distinctes
		pb.post(
				new NotEqualConstraint(
					"Abstraction <> RefinedAbstraction",
					"",
					abstraction,
					refinedAbstraction,
					100,
					DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
				new NotEqualConstraint(
					"Implementor <> ConcreteImplementor",
					"",
					implementor,
					concreteImplementor,
					100,
					DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
				new NotEqualConstraint(
					"Implementor <> Abstraction",
					"",
					implementor,
					abstraction,
					100,
					DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
				new NotEqualConstraint(
					"RefinedAbstraction <> ConcreteImplementor",
					"",
					refinedAbstraction,
					concreteImplementor,
					100,
					DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
				new NotEqualConstraint(
					"Implementor <> RefinedAbstraction",
					"",
					implementor,
					refinedAbstraction,
					100,
					DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
				new NotEqualConstraint(
					"Abstraction <> ConcreteImplementor",
					"",
					abstraction,
					concreteImplementor,
					100,
					DefaultNoApproximations.getDefaultApproximations()));
		//Implementor est une interface
		pb.post(
				new InterfaceEntityConstraint(
						"Implementor <<interface>>", 
						"", 
						implementor, 
						100, 
						DefaultNoApproximations.getDefaultApproximations()));
		//Abstraction est une classe abstraite
		pb.post(
				new AbstractEntityConstraint(
						"Abstraction <<abstract>>", 
						"", 
						abstraction, 
						100, 
						TSE07AbstractnessApproximations.getDefaultApproximations()));
		pb.post(
				new AggregationConstraint(
						"Abstraction <>-> Implementor",
						"",
						refinedAbstraction,
						implementor,
						100,
						TSE07AggregationApproximations.getDefaultApproximations()));
		return pb;
	}
}
