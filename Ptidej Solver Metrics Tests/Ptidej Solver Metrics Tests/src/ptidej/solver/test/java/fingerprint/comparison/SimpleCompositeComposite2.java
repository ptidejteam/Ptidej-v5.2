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
package ptidej.solver.test.java.fingerprint.comparison;

import padl.kernel.exception.ModelDeclarationException;
import ptidej.solver.Occurrence;
import ptidej.solver.fingerprint.Rule;
import util.PropertyManager;

public final class SimpleCompositeComposite2 extends Primitive {
	private static Occurrence[] BuiltSolutions;
	private static Occurrence[] BuiltSolutionsNoRule;

	public SimpleCompositeComposite2(final String name) {
		super(name);
	}
	protected void setUp() throws CloneNotSupportedException,
			IllegalAccessException, InstantiationException,
			ModelDeclarationException {

		SimpleCompositeComposite2.initialize("Composite2");

		if (SimpleCompositeComposite2.BuiltSolutions == null) {

			SimpleCompositeComposite2.BuiltSolutions =
				SimpleCompositeComposite2.automaticSolve(
					ptidej.solver.fingerprint.problem.CompositeMotif.class,
					PropertyManager.getExamplesDirectory()
							+ "bin/ptidej/example/composite2/",
					"composite2",
					Rule.C_LEAF_TEST);
		}

		if (SimpleCompositeComposite2.BuiltSolutionsNoRule == null) {
			SimpleCompositeComposite2.BuiltSolutionsNoRule =
				SimpleCompositeComposite2.automaticSolve(
					ptidej.solver.problem.CompositeMotif.class,
					PropertyManager.getExamplesDirectory()
							+ "bin/ptidej/example/composite2/",
					"composite2");
		}
	}
	public void test1() {
		SimpleCompositeComposite2.assertEquals(
			"Number of solutions",
			129,
			SimpleCompositeComposite2.BuiltSolutions.length);
	}

}
