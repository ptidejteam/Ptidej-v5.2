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

import junit.framework.Assert;
import ptidej.solver.Occurrence;
import ptidej.solver.fingerprint.Rule;

public final class CompositeJHotDraw extends Primitive {
	private static Occurrence[] BuiltSolutions;
	private static Occurrence[] BuiltSolutionsNoRule;

	public CompositeJHotDraw(final String name) {
		super(name);
	}
	protected void setUp() {
		Primitive.initialize("JHotDraw");

		if (CompositeJHotDraw.BuiltSolutions == null) {
			CompositeJHotDraw.BuiltSolutions =
				Primitive.automaticSolve(
					ptidej.solver.fingerprint.problem.CompositeMotif.class,
					"../JHotDraw v5.1/bin/",
					"JHotDraw",
					Rule.C_LEAF_ROLE_1);
		}
		if (CompositeJHotDraw.BuiltSolutionsNoRule == null) {
			CompositeJHotDraw.BuiltSolutionsNoRule =
				Primitive.automaticSolve(
					ptidej.solver.problem.CompositeMotif.class,
					"../JHotDraw v5.1/bin/",
					"JHotDraw");
		}
	}
	public void testNumberOfSolutions1() {
		Assert
			.assertTrue(
				"Less solution with rules.",
				CompositeJHotDraw.BuiltSolutions.length < CompositeJHotDraw.BuiltSolutionsNoRule.length);
	}
	public void testNumberOfSolutions2() {
		Assert.assertEquals(
			"Number of solutions",
			2,
			CompositeJHotDraw.BuiltSolutionsNoRule.length);
	}
}
