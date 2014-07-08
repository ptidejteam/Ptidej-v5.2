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

import java.util.Iterator;
import java.util.List;
import ptidej.solver.domain.Entity;
import choco.Problem;
import choco.integer.IntVar;
import choco.integer.var.IntDomain;
import choco.palm.integer.PalmIntVar;

public class Variable extends PalmIntVar {
	private boolean toBeEnumerated;
	private final List listOfEntities;

	public Variable(
		final Problem pb,
		final char[] name,
		final boolean toBeEnumerated) {

		this(pb, String.valueOf(name), toBeEnumerated);
	}
	public Variable(
		final Problem pb,
		final String name,
		final boolean toBeEnumerated) {

		super(pb, name, IntVar.LIST, 0, ((ptidej.solver.Problem) pb)
			.getAllEntities()
			.size() - 1);
		this.domain = new Domain(this, this.getInf(), this.getSup());
		this.toBeEnumerated = toBeEnumerated;
		this.listOfEntities = null;
	}
	public Variable(
		final Problem pb,
		final String name,
		final boolean toBeEnumerated,
		final int minDomainSize) {

		super(pb, name, IntVar.LIST, 0, ((ptidej.solver.Problem) pb)
			.getAllEntities()
			.size() - 1);
		this.domain = new Domain(this, this.getInf(), this.getSup());
		this.toBeEnumerated = toBeEnumerated;
		this.listOfEntities = null;
	}

	// JYves 13/10/04:
	// Constructor added to restrict domain
	public Variable(
		final Problem pb,
		final char[] name,
		final boolean toBeEnumerated,
		final List entities) {

		this(pb, String.valueOf(name), toBeEnumerated, entities);
	}
	public Variable(
		final Problem pb,
		final String name,
		final boolean toBeEnumerated,
		final List entities) {

		super(pb, name, IntVar.LIST, 0, ((ptidej.solver.Problem) pb)
			.getAllEntities()
			.size() - 1);
		this.domain = new Domain(this, this.getInf(), this.getSup());
		this.toBeEnumerated = toBeEnumerated;
		this.listOfEntities = entities;

		// Cleaning Domain
		if (entities != null) {
			final List allEntities =
				((ptidej.solver.Problem) pb).getAllEntities();
			final boolean[] tab = new boolean[allEntities.size()];
			final IntDomain domain = this.getDomain();

			final Iterator it = entities.iterator();
			while (it.hasNext()) {
				// int i = allEntities.indexOf(it.next());
				final int i = this.getIndexOf(allEntities, (Entity) it.next());
				tab[i] = true;
			}

			for (int cpt = 0; cpt < allEntities.size(); cpt++) {
				if (!tab[cpt]) {
					domain.remove(cpt);
				}
			}
		}
	}
	public List getEntities() {
		return this.listOfEntities;
	}
	private int getIndexOf(final List allEntities, final Entity entity) {
		final Iterator it = allEntities.iterator();

		while (it.hasNext()) {
			Entity e = (Entity) it.next();
			if (e.getName().equalsIgnoreCase(entity.getName()))
				return allEntities.indexOf(e);
		}
		return -1;
	}
	public boolean isEnumerated() {
		return this.toBeEnumerated;
	}
	//	public boolean removeVal(
	//		int value,
	//		int idx,
	//		choco.palm.explain.Explanation e)
	//		throws ContradictionException {
	//
	//		// Yann 2007/02/26: Size matters.
	//		// I enforce a minimum size for the domain
	//		// of the variable to force "more-than-one"
	//		// when not enumerating the variable.
	//		if (!this.toBeEnumerated
	//			&& this.getDomainSize() <= this.minDomainSize) {
	//
	//			throw new ContradictionException(this);
	//		}
	//
	//		return super.removeVal(value, idx, e);
	//	}
}
