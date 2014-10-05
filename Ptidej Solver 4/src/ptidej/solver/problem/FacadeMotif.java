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
import ptidej.solver.constraint.repository.AggregationConstraint;
import ptidej.solver.constraint.repository.AssociationConstraint;
import ptidej.solver.constraint.repository.CreationConstraint;
import ptidej.solver.constraint.repository.IgnoranceConstraint;
import ptidej.solver.constraint.repository.NoGhostEntityConstraint;

/**
 * @author Lucas Nelaupe, Ferrand Anthony, Tran Quang Dung, Verdier Frédéric 
 * @since  2014/06/01 
 */
public final class FacadeMotif {
	public static Problem getProblem(final List allEntities) {
		final Problem pb = new Problem(90, "Facade Design Motif", allEntities);

		final Variable client = new Variable(pb, "client", false);
		final Variable facadedCode = new Variable(pb, "facadedCode", false);
		final Variable facade = new Variable(pb, "facade", true);

		pb.addVar(client);
		pb.addVar(facadedCode);
		pb.addVar(facade);

		// Constraints
		/* --- Facade constraints --- */
		pb.post(
			new AggregationConstraint(
				"facade ---­> facadedCode",
				"",
				facade,
				facadedCode,
				100,
				DefaultNoApproximations.getDefaultApproximations()));
		
		pb.post(
				new CreationConstraint(
					"facade -1--> facadedCode",
					"",
					facade,
					facadedCode,
					100,
					DefaultNoApproximations.getDefaultApproximations()));
		
		pb.post(
				new NoGhostEntityConstraint(
					"facade <> ?",
					"",
					facade,
					100,
					DefaultNoApproximations.getDefaultApproximations()));
		
		/* -------------------------- */
		
		/* --- Client constraints --- */
		pb.post(
			new AssociationConstraint(
				"client ---­> facade",
				"",
				client,
				facade,
				100,
				DefaultNoApproximations.getDefaultApproximations()));
		
		pb.post(
				new IgnoranceConstraint(
					"client -/--> facadedCode",
					"",
					client,
					facadedCode,
					100,
					DefaultNoApproximations.getDefaultApproximations()));
		
		pb.post(
				new NoGhostEntityConstraint(
					"client <> ?",
					"",
					client,
					100,
					DefaultNoApproximations.getDefaultApproximations()));

		
		/* -------------------------- */
		
		return pb;
	}
}
