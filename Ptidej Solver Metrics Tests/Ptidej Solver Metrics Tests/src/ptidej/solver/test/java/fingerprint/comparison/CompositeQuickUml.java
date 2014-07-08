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

public final class CompositeQuickUml extends Primitive {
	private static Occurrence[] BuiltSolutions;
	private static Occurrence[] BuiltSolutionsNoRule;

	public CompositeQuickUml(final String name) {
		super(name);
	}
	protected void setUp()
		throws
			CloneNotSupportedException,
			IllegalAccessException,
			InstantiationException,
			ModelDeclarationException {

		Primitive.initialize("QuickUML");

		if (CompositeQuickUml.BuiltSolutions == null) {
			CompositeQuickUml.BuiltSolutions =
				CompositeQuickUml.automaticSolve(
					ptidej.solver.fingerprint.problem.CompositeMotif.class,
					"../QuickUML 2001/bin/",
					"QuickUML",
					Rule.C_LEAF_ROLE_1);
		}
		if (CompositeQuickUml.BuiltSolutionsNoRule == null) {
			CompositeQuickUml.BuiltSolutionsNoRule =
				CompositeQuickUml.automaticSolve(
					ptidej.solver.problem.CompositeMotif.class,
					"../QuickUML 2001/bin/",
					"QuickUML");
		}
	}
	public void testNumberSolution() {
		CompositeQuickUml.assertTrue(
			"Less solution with rules.",
			CompositeQuickUml.BuiltSolutions.length
				< CompositeQuickUml.BuiltSolutionsNoRule.length);
	}
}
