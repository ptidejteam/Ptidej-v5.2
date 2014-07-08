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
package ptidej.solver.constraint;

import java.util.Set;
import ptidej.solver.Constraint;
import ptidej.solver.Variable;
import ptidej.solver.approximation.IApproximations;
import choco.ContradictionException;
import choco.palm.explain.PalmConstraintPlugin;
import choco.palm.integer.AbstractPalmUnIntConstraint;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2014/03/31
 */
// TODO Move into this abstract class all the common code among its subclasses!
public abstract class UnaryConstraint extends AbstractPalmUnIntConstraint
		implements Constraint {

	private final IApproximations approximations;
	private final String command;
	private final String name;

	public UnaryConstraint(
		final String name,
		final String command,
		final Variable v0,
		final int weight,
		final IApproximations approximations) {

		this.name = name;
		this.command = command;
		this.v0 = v0;
		this.approximations = approximations;
		this.hook = new PalmConstraintPlugin(this);
		((PalmConstraintPlugin) this.hook).setWeight(weight);
	}
	public abstract void awakeOnRem(int idx, int index_e);
	public abstract void awakeOnRestoreVal(int idx, int val)
			throws ContradictionException;
	public abstract void doAwake() throws ContradictionException;
	public final IApproximations getApproximations() {
		return this.approximations;
	}
	public final String getName() {
		return this.name;
	}
	public abstract String getNextConstraint();
	public abstract Class getNextConstraintConstructor(
		final String nextConstraint);
	public abstract String getSymbol();
	public abstract int getWeight();
	public final String getXCommand() {
		return this.command;
	}
	public abstract boolean isSatisfied();
	public abstract void propagate();
	protected abstract void setStrict(final boolean flag);
	protected abstract String setSymbol(final String symbol);
	public abstract Set whyIsFalse();
	public abstract Set whyIsTrue();
}
