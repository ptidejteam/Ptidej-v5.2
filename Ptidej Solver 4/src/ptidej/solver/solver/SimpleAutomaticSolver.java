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
import ptidej.solver.branching.SaveAllSolutions;
import ptidej.solver.repair.SimpleAutomaticRepair;

public class SimpleAutomaticSolver extends Solver {
	public SimpleAutomaticSolver(final Problem pb) {
		super(pb);
		final ArrayList list = new ArrayList();
		list.add(new AssignVariable());
		list.add(new SaveAllSolutions());
		this.attachPalmRepair(new SimpleAutomaticRepair(pb));
		this.attachPalmBranchings(list);
	}
}
