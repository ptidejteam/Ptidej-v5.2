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
import padl.kernel.IAggregation;
import padl.kernel.IAssociation;
import padl.kernel.IClass;
import padl.kernel.IComposition;
import padl.kernel.IPackage;
import padl.kernel.IUseRelationship;
import padl.kernel.impl.Factory;

public final class BadInheritanceTest extends TestAbstractLevelModel {
	private static final long serialVersionUID = -2800020575042406684L;

	public BadInheritanceTest() {
		this.setFactory(Factory.getInstance());

		// A -<|- B -<|- C
		// D -<|- E
		// F -<|- G
		// A -k--> B
		// B ----> C
		// D <>--> E
		// F <#>-> G

		final IClass A =
			this.getFactory().createClass("A".toCharArray(), "A".toCharArray());
		final IClass B =
			this.getFactory().createClass("B".toCharArray(), "B".toCharArray());
		final IClass C =
			this.getFactory().createClass("C".toCharArray(), "C".toCharArray());
		final IClass D =
			this.getFactory().createClass("D".toCharArray(), "D".toCharArray());
		final IClass E =
			this.getFactory().createClass("E".toCharArray(), "E".toCharArray());
		final IClass F =
			this.getFactory().createClass("F".toCharArray(), "F".toCharArray());
		final IClass G =
			this.getFactory().createClass("G".toCharArray(), "G".toCharArray());

		B.addInheritedEntity(A);
		C.addInheritedEntity(B);
		E.addInheritedEntity(D);
		G.addInheritedEntity(F);

		final IUseRelationship link1 =
			this.getFactory().createUseRelationship(
				"link1".toCharArray(),
				B,
				Constants.CARDINALITY_ONE);
		A.addConstituent(link1);
		final IAssociation link2 =
			this.getFactory().createAssociationRelationship(
				"link2".toCharArray(),
				C,
				Constants.CARDINALITY_ONE);
		B.addConstituent(link2);
		final IAggregation link3 =
			this.getFactory().createAggregationRelationship(
				"link3".toCharArray(),
				E,
				Constants.CARDINALITY_ONE);
		D.addConstituent(link3);
		final IComposition link4 =
			this.getFactory().createCompositionRelationship(
				"link4".toCharArray(),
				G,
				Constants.CARDINALITY_ONE);
		F.addConstituent(link4);

		final IPackage enclosingPackage =
			this.getFactory().createPackage("BadInheritanceTest".toCharArray());
		enclosingPackage.addConstituent(A);
		enclosingPackage.addConstituent(B);
		enclosingPackage.addConstituent(C);
		enclosingPackage.addConstituent(D);
		enclosingPackage.addConstituent(E);
		enclosingPackage.addConstituent(F);
		enclosingPackage.addConstituent(G);

		this.addConstituents(enclosingPackage);
	}
}
