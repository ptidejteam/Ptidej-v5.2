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

import padl.kernel.IClass;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IPackage;
import padl.kernel.impl.Factory;

public final class InheritanceTest extends TestAbstractLevelModel {
	private static final long serialVersionUID = 2188891814109630235L;

	public InheritanceTest() {
		this.setFactory(Factory.getInstance());

		// java.lang.Object -<|- A -<|- B -<|- C
		// java.lang.Object -<|- D -<|- E
		// java.lang.Object -<|- F

		final IFirstClassEntity Object =
			this.getFactory().createHierarchyRoot();
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

		A.addInheritedEntity(Object);
		D.addInheritedEntity(Object);
		F.addInheritedEntity(Object);
		B.addInheritedEntity(A);
		C.addInheritedEntity(B);
		E.addInheritedEntity(D);

		final IPackage enclosingPackage =
			this.getFactory().createPackage("InheritanceTest".toCharArray());
		enclosingPackage.addConstituent(Object);
		enclosingPackage.addConstituent(A);
		enclosingPackage.addConstituent(B);
		enclosingPackage.addConstituent(C);
		enclosingPackage.addConstituent(D);
		enclosingPackage.addConstituent(E);
		enclosingPackage.addConstituent(F);

		this.addConstituents(enclosingPackage);
	}
}
