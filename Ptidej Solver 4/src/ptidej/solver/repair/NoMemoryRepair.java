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
package ptidej.solver.repair;

import ptidej.solver.Problem;
import ptidej.solver.Repair;
import choco.AbstractConstraint;

public abstract class NoMemoryRepair extends Repair {
	public NoMemoryRepair(final Problem problem) {
		super(problem);
	}
	public final void remove(final AbstractConstraint constraint) {
		//we don't use the remove in noMemoryRepair
	}
}
