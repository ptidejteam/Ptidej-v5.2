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
import ptidej.solver.approximation.TSE07AggregationApproximations;
import ptidej.solver.approximation.TSE07ExtensibleInheritanceOrNoneApproximations;
import ptidej.solver.constraint.repository.AbstractEntityConstraint;
import ptidej.solver.constraint.repository.AggregationConstraint;
import ptidej.solver.constraint.repository.AssociationConstraint;
import ptidej.solver.constraint.repository.NoGhostEntityConstraint;
import ptidej.solver.constraint.repository.NoObjectEntityConstraint;
import ptidej.solver.constraint.repository.NotEqualConstraint;
import ptidej.solver.constraint.repository.StrictInheritanceConstraint;
import ptidej.solver.constraint.repository.UseConstraint;

/**
 * @author Lucas Nelaupe, Ferrand Anthony, Tran Quang Dung, Verdier Frédéric 
 * @since  2014/06/01 
 */
public class InterpreterMotif {
	public static Problem getProblem(final List allEntities) 
	{
		final Problem pb = new Problem(90, "Interpreter Design Motif", allEntities);
		//Creation des entites
		final Variable abstractExpression = new Variable(pb, "AbstractExpression", true);
		final Variable nonTerminalExpression = new Variable(pb, "NonTerminalExpression", false);
		final Variable terminalExpression = new Variable(pb, "TerminalExpression", false);
		final Variable context = new Variable(pb, "Context", true);
		final Variable client = new Variable(pb,"Client", true);
		//Ajout des entites au probleme.
		pb.addVar(abstractExpression);
		pb.addVar(nonTerminalExpression);
		pb.addVar(terminalExpression);
		pb.addVar(context);
		pb.addVar(client);
		//Pas de detection sur les entites fantomes.
		pb.post(
				new NoGhostEntityConstraint(
					"AbstractExpression <> ?",
					"",
					abstractExpression,
					100,
					DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
				new NoGhostEntityConstraint(
					"TerminalExpression <> ?",
					"",
					terminalExpression,
					100,
					DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
				new NoGhostEntityConstraint(
						"NonTerminalExpression <> ?",
						"",
						nonTerminalExpression,
						100,
						DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
				new NoGhostEntityConstraint(
						"Client <> ?",
						"",
						client,
						100,
						DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
				new NoObjectEntityConstraint(
						"Context <> Object", 
						"", 
						context, 
						100, 
						DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
				new StrictInheritanceConstraint(
						"TerminalExpression -|>- AbstractExpression", 
						"", 
						terminalExpression, 
						abstractExpression, 
						100, 
						TSE07ExtensibleInheritanceOrNoneApproximations
						.getDefaultApproximations()));
		pb.post(
				new StrictInheritanceConstraint(
						"NonTerminalExpression -|>- AbstractExpression", 
						"", 
						nonTerminalExpression, 
						abstractExpression, 
						100, 
						TSE07ExtensibleInheritanceOrNoneApproximations
						.getDefaultApproximations()));
		pb.post(
				new AggregationConstraint(
						"NonTerminalExpression <>-> AbstractExpression", 
						"", 
						nonTerminalExpression, 
						abstractExpression, 
						100, 
						TSE07AggregationApproximations.getDefaultApproximations()));
		pb.post(
				new AbstractEntityConstraint(
						"AbstractExpression <<Abstract>>", 
						"", 
						abstractExpression, 
						100, 
						DefaultNoApproximations.getDefaultApproximations()));
		//Empecher context redondant
		pb.post(
				new NotEqualConstraint(
						"Context <> AbstractExpression", 
						"", 
						context, 
						abstractExpression, 
						100, 
						DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
				new NotEqualConstraint(
						"Context <> TerminalExpression", 
						"", 
						context, 
						terminalExpression, 
						100, 
						DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
				new NotEqualConstraint(
						"Context <> NonTerminalExpression", 
						"", 
						context, 
						nonTerminalExpression, 
						100, 
						DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
				new NotEqualConstraint(
						"Context <> NonTerminalExpression", 
				"", 
				terminalExpression, 
				nonTerminalExpression, 
				100, 
				DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
				new UseConstraint(
						"AbstractExpression ----> Context", 
						"", 
						abstractExpression, 
						context, 
						100, 
						DefaultNoApproximations.getDefaultApproximations()));
		//Determination du role de point d'entree du client.
		pb.post(
				new AssociationConstraint(
						"Client ----> Context", 
						"", 
						client, 
						context, 
						100, 
						DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
				new AssociationConstraint(
						"Client ----> TerminalExpression", 
						"", 
						client, 
						terminalExpression, 
						100, 
						DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
				new AssociationConstraint(
						"Client ----> NonTerminalExpression", 
						"", 
						client, 
						nonTerminalExpression, 
						100, 
						DefaultNoApproximations.getDefaultApproximations()));
		return pb;
	}
}