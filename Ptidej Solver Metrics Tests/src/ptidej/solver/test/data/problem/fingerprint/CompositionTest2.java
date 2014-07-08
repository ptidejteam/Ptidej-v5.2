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
package ptidej.solver.test.data.problem.fingerprint;

import java.util.List;
import ptidej.solver.Problem;
import ptidej.solver.Variable;
import ptidej.solver.approximation.DefaultNoApproximations;
import ptidej.solver.constraint.repository.CompositionConstraint;
import ptidej.solver.constraint.repository.NotEqualConstraint;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/09/05 
 */
public final class CompositionTest2 {
	public static Problem getProblem(final List allEntities) {
		final Problem pb = new Problem(90, "Composition Test", allEntities);

		//	final ArrayList aggregateList = new ArrayList();
		//	aggregateList.add(allEntities.get(0));
		//	aggregateList.add(allEntities.get(3));

		//	final ArrayList aggregatedList = new ArrayList();
		//	aggregatedList.add(allEntities.get(2));
		//	aggregatedList.add(allEntities.get(5));

		final Variable aggregate = new Variable(pb, "Aggregate", true);
		final Variable aggregated = new Variable(pb, "Aggregated", true);

		pb.addVar(aggregate);
		pb.addVar(aggregated);

		final CompositionConstraint c1 =
			new CompositionConstraint(
				"Aggregate <>--> Aggregated",
				"throw new RuntimeException(\"Composite <>--> Component\");",
				aggregate,
				aggregated,
				100,
				DefaultNoApproximations.getDefaultApproximations());
		final NotEqualConstraint c2 =
			new NotEqualConstraint(
				"Composite <> leaf",
				"throw new RuntimeException(\"Aggregate <> Aggregated\");",
				aggregate,
				aggregated,
				100,
				DefaultNoApproximations.getDefaultApproximations());

		pb.post(c1);
		pb.post(c2);

		return pb;
	}
}
