/*
 * Writen in CLAIRE by
 * @author Yann-Gaël Guéhéneuc
 * Translated and adapted from CLAIRE version to JAVA by
 * @author Iyadh Sidhom
 * @author Salim Bensemmane
 * @author Fayçal Skhiri
 *
 * (c) Copyright 2000-2004 Yann-Gaël Guéhéneuc,
 */
package ptidej.solver.solver;

import java.util.ArrayList;

import ptidej.solver.AssignVariable;
import ptidej.solver.Problem;
import ptidej.solver.Solver;
import ptidej.solver.branching.InteractiveBranching;
import ptidej.solver.repair.InteractiveRepair;

public class InteractiveSolver extends Solver {
	public InteractiveSolver(final Problem pb) {
		super(pb);

		// Attach the repair to solver
		this.attachPalmRepair(new InteractiveRepair(pb));

		final ArrayList list = new ArrayList();
		list.add(new AssignVariable());
		list.add(new InteractiveBranching());

		// Attach branching to solver
		this.attachPalmBranchings(list);
	}

}
