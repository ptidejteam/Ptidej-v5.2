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
import ptidej.solver.approximation.TSE07ExtensibleStrictInheritanceApproximations;
import ptidej.solver.constraint.repository.IgnoranceConstraint;
import ptidej.solver.constraint.repository.NotEqualConstraint;
import ptidej.solver.constraint.repository.StrictInheritanceConstraint;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/02/24 
 */
public final class GoodInheritanceMotif {
	public static Problem getProblem(final List allEntities) {
		final Problem pb =
			new Problem(90, "Good Inheritance Design Motif", allEntities);

		final Variable superclass = new Variable(pb, "superclass", true);
		final Variable subclass = new Variable(pb, "subclass", false);

		pb.addVar(superclass);
		pb.addVar(subclass);

		pb.post(new StrictInheritanceConstraint(
			"SubClass -|>- SuperClass",
			"",
			subclass,
			superclass,
			100,
			TSE07ExtensibleStrictInheritanceApproximations
				.getDefaultApproximations()));

		pb.post(new IgnoranceConstraint(
			"SuperClass -/--> SubClass",
			"",
			superclass,
			subclass,
			100,
			DefaultNoApproximations.getDefaultApproximations()));

		pb.post(new NotEqualConstraint(
			"SuperClass <> SubClass",
			"",
			superclass,
			subclass,
			100,
			DefaultNoApproximations.getDefaultApproximations()));

		return pb;
	}
}
