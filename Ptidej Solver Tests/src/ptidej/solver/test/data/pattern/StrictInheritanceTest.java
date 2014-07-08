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

import padl.kernel.IClass;
import padl.motif.models.TestMotifModel;

public final class StrictInheritanceTest extends TestMotifModel {
	private static final long serialVersionUID = -5989354895228483331L;
	private static final char[] STRICT_INHERITANCE_TEST =
		"StrictInheritanceTest".toCharArray();
	public static final char[] SUB_ENTITY = "SubEntity".toCharArray();
	public static final char[] SUPER_ENTITY = "SuperEntity".toCharArray();

	public StrictInheritanceTest() {
		final IClass superclass =
			this.getFactory().createClass(
				StrictInheritanceTest.SUPER_ENTITY,
				StrictInheritanceTest.SUPER_ENTITY);
		final IClass subclass =
			this.getFactory().createClass(
				StrictInheritanceTest.SUB_ENTITY,
				StrictInheritanceTest.SUB_ENTITY);
		subclass.addInheritedEntity(superclass);
		this.addConstituent(superclass);
		this.addConstituent(subclass);
	}
	public char[] getName() {
		return StrictInheritanceTest.STRICT_INHERITANCE_TEST;
	}
}
