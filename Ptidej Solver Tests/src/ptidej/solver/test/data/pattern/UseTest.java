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
package ptidej.solver.test.data.pattern;

import padl.kernel.Constants;
import padl.kernel.IClass;
import padl.kernel.IUseRelationship;
import padl.motif.models.TestMotifModel;

public final class UseTest extends TestMotifModel {
	public static final char[] CALLEE = "Callee".toCharArray();
	public static final char[] CALLER = "Caller".toCharArray();
	private static final char[] MESSAGE = "message".toCharArray();
	private static final long serialVersionUID = -8903957402751001456L;
	private static final char[] USE_TEST = "UseTest".toCharArray();

	public UseTest() {
		final IClass caller =
			this.getFactory().createClass(UseTest.CALLER, UseTest.CALLER);
		final IClass callee =
			this.getFactory().createClass(UseTest.CALLEE, UseTest.CALLEE);
		final IUseRelationship useRelationship =
			this.getFactory().createUseRelationship(
				UseTest.MESSAGE,
				callee,
				Constants.CARDINALITY_ONE);
		caller.addConstituent(useRelationship);
		this.addConstituent(caller);
		this.addConstituent(callee);
	}
	public char[] getName() {
		return UseTest.USE_TEST;
	}
}
