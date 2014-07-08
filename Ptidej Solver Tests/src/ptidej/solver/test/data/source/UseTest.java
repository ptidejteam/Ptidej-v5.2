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

public final class UseTest extends TestAbstractLevelModel {
	private static final long serialVersionUID = -69654226450493126L;

	public UseTest() {
		this.setFactory(Factory.getInstance());

		// Compiler ----> ProgramNodeBuilder ----> ProgramNode

		final IClass compiler =
			this.getFactory().createClass(
				"Compiler".toCharArray(),
				"Compiler".toCharArray());
		final IClass programNodeBuilder =
			this.getFactory().createClass(
				"ProgramNodeBuilder".toCharArray(),
				"ProgramNodeBuilder".toCharArray());
		final IClass programNode =
			this.getFactory().createClass(
				"ProgramNode".toCharArray(),
				"ProgramNode".toCharArray());

		final IUseRelationship link1 =
			this.getFactory().createUseRelationship(
				"message".toCharArray(),
				programNodeBuilder,
				Constants.CARDINALITY_ONE);
		final IUseRelationship link2 =
			this.getFactory().createUseRelationship(
				"message".toCharArray(),
				programNode,
				Constants.CARDINALITY_ONE);

		compiler.addConstituent(link1);
		programNodeBuilder.addConstituent(link2);

		final IPackage enclosingPackage =
			this.getFactory().createPackage("UseTest".toCharArray());
		enclosingPackage.addConstituent(compiler);
		enclosingPackage.addConstituent(programNodeBuilder);
		enclosingPackage.addConstituent(programNode);

		this.addConstituents(enclosingPackage);
	}
}
