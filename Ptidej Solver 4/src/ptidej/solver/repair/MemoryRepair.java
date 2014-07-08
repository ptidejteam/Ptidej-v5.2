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

import java.util.ArrayList;

import ptidej.solver.Problem;
import ptidej.solver.Repair;
import choco.AbstractConstraint;

public abstract class MemoryRepair extends Repair {
	private ArrayList userRelaxedConstraints;

	public MemoryRepair(final Problem problem) {
		super(problem);
		this.userRelaxedConstraints = new ArrayList();
	}
	public final void remove(final AbstractConstraint constraint) {
		this.userRelaxedConstraints.add(constraint);
	}
	public ArrayList getRemovedConstraints() {
		return this.userRelaxedConstraints;
	}
	public void setRemoveConstraint(final ArrayList removedList) {
		this.userRelaxedConstraints = removedList;
	}
}
