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
import ptidej.solver.approximation.DefaultAssociationApproximations;
import ptidej.solver.approximation.DefaultNoApproximations;
import ptidej.solver.approximation.TSE07AbstractnessApproximations;
import ptidej.solver.approximation.TSE07ExtensibleInheritanceOrNoneApproximations;
import ptidej.solver.constraint.repository.AbstractEntityConstraint;
import ptidej.solver.constraint.repository.AssociationConstraint;
import ptidej.solver.constraint.repository.NoGhostEntityConstraint;
import ptidej.solver.constraint.repository.StrictInheritanceConstraint;

/**
 * @author Lucas Nelaupe, Ferrand Anthony, Tran Quang Dung, Verdier Frédéric 
 * @since  2014/06/01 
 */
public final class ChainOfResponsibilityMotif {
	public static Problem getProblem(final List allEntities) {
		final Problem pb = new Problem(90,"Chain of Responsibility Design Motif",allEntities);
		final Variable abstractElement = new Variable(pb, "AbstractElement", true);
		final Variable concreteElement = new Variable(pb, "ConcreteElement", false);
		
		pb.addVar(abstractElement);
		pb.addVar(concreteElement);
		
		pb.post(
				new NoGhostEntityConstraint(
					"AbstractElement <> ?",
					"",
					abstractElement,
					100,
					DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
				new NoGhostEntityConstraint(
					"ConcreteElement <> ?",
					"",
					concreteElement,
					100,
					DefaultNoApproximations.getDefaultApproximations()));
		pb.post(
				new AbstractEntityConstraint(
						"AbstractElement <<abstract>>", 
						"", 
						abstractElement, 
						100, 
						TSE07AbstractnessApproximations.getDefaultApproximations()));
		pb.post(
				new StrictInheritanceConstraint(
					"ConcreteElement -|>- AbstractElement",
					"",
					concreteElement,
					abstractElement,
					100,
					TSE07ExtensibleInheritanceOrNoneApproximations
						.getDefaultApproximations()));
		pb.post(
				new AssociationConstraint(
						"AbstractElement ---> AbstractElement", 
						"", 
						abstractElement, 
						abstractElement, 
						100, 
						DefaultAssociationApproximations.getDefaultApproximations()));
		return pb;
	}
}
