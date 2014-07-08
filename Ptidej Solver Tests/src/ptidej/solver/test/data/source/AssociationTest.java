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

import padl.kernel.IAssociation;
import padl.kernel.IClass;
import padl.kernel.IPackage;
import padl.kernel.impl.Factory;

public final class AssociationTest extends TestAbstractLevelModel {
	private static final long serialVersionUID = -2125455711226326226L;

	public AssociationTest() {
		this.setFactory(Factory.getInstance());

		// Aggregate1 ----> Aggregated1
		// Aggregated1 ----> Associated
		// Aggregate2 ----> Aggregated2
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
		final IAssociation anAssociation1 =
			this.getFactory().createAssociationRelationship(
				"association".toCharArray(),
				anAggregatedClass1,
				1);
		anAggregateClass1.addConstituent(anAssociation1);

		final IClass anAssociatedClass =
			this.getFactory().createClass(
				"AssociatedClass1".toCharArray(),
				"AssociatedClass1".toCharArray());
		final IAssociation anAssociation2 =
			this.getFactory().createAssociationRelationship(
				"association".toCharArray(),
				anAssociatedClass,
				1);
		anAggregatedClass1.addConstituent(anAssociation2);

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
		final IAssociation anAssociation3 =
			this.getFactory().createAssociationRelationship(
				"association".toCharArray(),
				anAggregatedClass2,
				1);
		anAggregateClass2.addConstituent(anAssociation3);

		final IPackage enclosingPackage =
			this.getFactory().createPackage("AssociationTest".toCharArray());
		enclosingPackage.addConstituent(anAggregateClass1);
		enclosingPackage.addConstituent(anAggregatedClass1);
		enclosingPackage.addConstituent(anAssociatedClass);
		enclosingPackage.addConstituent(anAggregateClass2);
		enclosingPackage.addConstituent(anAggregatedClass2);
		enclosingPackage.addConstituent(subclass1);
		enclosingPackage.addConstituent(subclass2);
		enclosingPackage.addConstituent(subclass3);

		this.addConstituents(enclosingPackage);
	}
}
