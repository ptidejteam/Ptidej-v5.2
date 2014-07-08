/*
 * (c) Copyright 2001, 2002 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes and Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * Yann-Gaël Guéhéneuc, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR B PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package ptidej.solver.test.data.source;

import padl.kernel.Constants;
import padl.kernel.IClass;
import padl.kernel.IPackage;
import padl.kernel.IUseRelationship;
import padl.kernel.impl.Factory;

public final class BadCompositionTest3 extends TestAbstractLevelModel {
	private static final long serialVersionUID = 6131550238722520788L;

	public BadCompositionTest3() {
		this.setFactory(Factory.getInstance());

		// Aggregate1 -k--> Aggregated1
		// Aggregate2 -k--> Aggregated2
		// Subclass1 -|>- Aggregated2
		// Subclass2 -|>- Aggregated2
		// Subclass3 -|>- Subclass2

		final IClass anAggregateClass1 =
			this.getFactory().createClass(
				"AggregateClass1".toCharArray(),
				"AggregateClass1".toCharArray());
		final IClass anAggregatedClass1 =
			this.getFactory().createClass(
				"AggregatedClass1".toCharArray(),
				"AggregatedClass1".toCharArray());
		final IUseRelationship aUseRelationship1 =
			this.getFactory().createUseRelationship(
				"use".toCharArray(),
				anAggregatedClass1,
				Constants.CARDINALITY_ONE);
		anAggregateClass1.addConstituent(aUseRelationship1);

		final IClass anAggregateClass2 =
			this.getFactory().createClass(
				"AggregateClass2".toCharArray(),
				"AggregateClass2".toCharArray());
		final IClass anAggregatedClass2 =
			this.getFactory().createClass(
				"AggregatedClass2".toCharArray(),
				"AggregatedClass2".toCharArray());
		final IClass subclass1 =
			this.getFactory().createClass(
				"Subclass1".toCharArray(),
				"Subclass1".toCharArray());
		final IClass subclass2 =
			this.getFactory().createClass(
				"Subclass2".toCharArray(),
				"Subclass2".toCharArray());
		final IClass subclass3 =
			this.getFactory().createClass(
				"Subclass3".toCharArray(),
				"Subclass3".toCharArray());
		subclass1.addInheritedEntity(anAggregatedClass2);
		subclass2.addInheritedEntity(anAggregatedClass2);
		subclass3.addInheritedEntity(subclass2);
		final IUseRelationship aUseRelationship2 =
			this.getFactory().createUseRelationship(
				"use".toCharArray(),
				anAggregatedClass2,
				Constants.CARDINALITY_ONE);
		anAggregateClass2.addConstituent(aUseRelationship2);

		final IPackage enclosingPackage =
			this
				.getFactory()
				.createPackage("BadCompositionTest3".toCharArray());
		enclosingPackage.addConstituent(anAggregateClass1);
		enclosingPackage.addConstituent(anAggregatedClass1);
		enclosingPackage.addConstituent(anAggregateClass2);
		enclosingPackage.addConstituent(anAggregatedClass2);
		enclosingPackage.addConstituent(subclass1);
		enclosingPackage.addConstituent(subclass2);
		enclosingPackage.addConstituent(subclass3);

		this.addConstituents(enclosingPackage);
	}
}
