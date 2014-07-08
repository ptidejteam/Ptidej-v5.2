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
package ptidej.solver;

import java.util.ArrayList;

import choco.AbstractConstraint;
import choco.palm.explain.Explanation;
import choco.palm.search.PalmRepair;


public abstract class Repair extends PalmRepair {
	private Problem problem;

	public Repair(final Problem aProblem) {
		this.problem = aProblem;
	}

	public abstract void remove(final AbstractConstraint aConstraint);
	public abstract ArrayList[] ptidejSelectDecisionToUndo(final Explanation anExplanation);
	public Problem getProblem() {
		return this.problem;
	}
}
