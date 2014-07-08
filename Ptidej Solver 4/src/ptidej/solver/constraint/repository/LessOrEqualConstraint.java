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

import choco.integer.IntVar;
import choco.palm.explain.PalmConstraintPlugin;
import choco.palm.integer.constraints.PalmLessOrEqualXC;

public class LessOrEqualConstraint extends PalmLessOrEqualXC {
	private String name;
	private String command;

	public LessOrEqualConstraint(
		final String name,
		final String command,
		final IntVar v0,
		final int cste,
		final int weight) {

		super(v0, cste);
		this.name = name;
		this.command = command;
		this.hook = new PalmConstraintPlugin(this);
		((PalmConstraintPlugin) this.hook).setWeight(weight);
	}
	public int getConstant() {
		return this.cste;
	}
	public String getName() {
		return this.name;
	}
	public String getXCommand() {
		return this.command;
	}
}
