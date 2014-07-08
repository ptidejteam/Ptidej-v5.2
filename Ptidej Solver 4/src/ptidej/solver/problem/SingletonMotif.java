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
import ptidej.solver.constraint.repository.CreationConstraint;
import ptidej.solver.constraint.repository.IgnoranceConstraint;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/02/18 
 */
public final class SingletonMotif {
	public static Problem getProblem(final List allEntities) {
		final Problem pb =
			new Problem(90, "Singleton Design Motif", allEntities);

		final Variable singleton = new Variable(pb, "singleton", true);
		final Variable others = new Variable(pb, "others", false);

		pb.addVar(singleton);

		// Constraints
		final CreationConstraint c1 =
			new CreationConstraint(
				"Singleton -*--> Singleton",
				"",
				singleton,
				singleton,
				100,
				DefaultNoApproximations.getDefaultApproximations());

		final IgnoranceConstraint c2 =
			new IgnoranceConstraint(
				"Singleton -/--> Others",
				"",
				singleton,
				others,
				100,
				DefaultNoApproximations.getDefaultApproximations());

		pb.post(c1);
		pb.post(c2);

		return pb;
	}
}
