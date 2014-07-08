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
package ptidej.solver.constraint.repository;

import ptidej.solver.Variable;
import ptidej.solver.constraint.BinaryCounterConstraint;
import choco.palm.integer.PalmIntVar;

public class AssociationDistanceConstraint extends BinaryCounterConstraint {
	public AssociationDistanceConstraint(
		final int n,
		final String name,
		final String command,
		final Variable v0,
		final Variable v1,
		final PalmIntVar counter,
		final int weight) {

		super(n, name, command, v0, v1, counter, weight);
		this.setFieldName("associatedEntities");
		this.setSymbol("--...-->");
	}
}
