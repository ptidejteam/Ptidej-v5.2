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
import padl.kernel.IContainerComposition;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IPackage;
import padl.kernel.impl.Factory;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/09/20
 */
public final class CompositionAndInheritanceTest extends TestAbstractLevelModel {
	private static final long serialVersionUID = 1272372464314373630L;

	public CompositionAndInheritanceTest() {
		this.setFactory(Factory.getInstance());

		// java.lang.Object -<|- A -<|- B
		// B <#>-> java.lang.Object

		final IFirstClassEntity object =
			this.getFactory().createHierarchyRoot();
		final IClass A =
			this.getFactory().createClass("A".toCharArray(), "A".toCharArray());
		final IClass B =
			this.getFactory().createClass("B".toCharArray(), "B".toCharArray());

		A.addInheritedEntity(object);
		B.addInheritedEntity(A);

		final IContainerComposition containerComposition =
			this.getFactory().createContainerCompositionRelationship(
				"containerComposition".toCharArray(),
				object,
				2);
		B.addConstituent(containerComposition);

		final IPackage enclosingPackage =
			this.getFactory().createPackage(
				"CompositionAndInheritanceTest".toCharArray());
		enclosingPackage.addConstituent(object);
		enclosingPackage.addConstituent(A);
		enclosingPackage.addConstituent(B);

		this.addConstituents(enclosingPackage);
	}
}
