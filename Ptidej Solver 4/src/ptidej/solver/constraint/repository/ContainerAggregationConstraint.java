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
import ptidej.solver.approximation.IApproximations;
import ptidej.solver.constraint.AbstractRelationshipConstraint;

public class ContainerAggregationConstraint
	extends AbstractRelationshipConstraint {
	public ContainerAggregationConstraint(
		final String name,
		final String commande,
		final Variable v0,
		final Variable v1,
		final int weight,
		final IApproximations approximations) {

		super(name, commande, v0, v1, weight, approximations);
		this.setSymbol("<>-->");
		this.setStrict(true);
		this.setFieldName("containerAggregatedEntities");
	}
}
