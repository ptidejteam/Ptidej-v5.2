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

import java.util.Set;

import ptidej.solver.approximation.IApproximations;

import choco.ContradictionException;

public interface Constraint extends choco.Constraint {
	public void awakeOnRem(final int idx, final int index_e);
	public void awakeOnRestoreVal(final int idx, final int val)
		throws ContradictionException;
	public void doAwake() throws ContradictionException;
	// public void setListOfNextOrder(final String[] listOfNextOrder);
	public IApproximations getApproximations();
	public String getName();
	public String getNextConstraint();
	public Class getNextConstraintConstructor(final String nextConstraint);
	public String getSymbol();
	public int getWeight();
	public String getXCommand();
	public boolean isSatisfied();
	public void propagate();
	public Set whyIsFalse();
	public Set whyIsTrue();
}
