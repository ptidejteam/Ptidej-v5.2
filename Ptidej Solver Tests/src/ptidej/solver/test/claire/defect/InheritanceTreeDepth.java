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
package ptidej.solver.test.claire.defect;

import junit.framework.Assert;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import ptidej.solver.Occurrence;
import ptidej.solver.OccurrenceGenerator;
import ptidej.solver.test.claire.Primitive;

public final class InheritanceTreeDepth extends Primitive {
	private static final char[] DEPTH = "Depth".toCharArray();
	private static final char[] ENTITY = "SubEntity".toCharArray();
	private static final char[] INHERITANCE_TREE_DEPTH_TEST =
		"InheritanceTreeDepthTest".toCharArray();
	private static Occurrence[] Solutions;
	private static final char[] STRING = "SuperEntity".toCharArray();

	public InheritanceTreeDepth(final String name) {
		super(name);
	}
	protected void setUp() {
		if (InheritanceTreeDepth.Solutions == null) {
			final ICodeLevelModel codeLevelModel =
				Factory.getInstance().createCodeLevelModel(
					"ptidej.example.composite2");
			try {
				codeLevelModel
					.create(new CompleteClassFileCreator(
						new String[] { "../Ptidej Tests/bin/ptidej/example/composite2/" },
						false));
			}
			catch (final CreationException e) {
				e.printStackTrace();
			}

			InheritanceTreeDepth.Solutions =
				this.testDesignPattern(
					InheritanceTreeDepth.class,
					Primitive.ALL_SOLUTIONS,
					InheritanceTreeDepth.INHERITANCE_TREE_DEPTH_TEST,
					codeLevelModel,
					OccurrenceGenerator.SOLVER_AUTOMATIC,
					OccurrenceGenerator.PROBLEM_CUSTOM);
		}
	}
	public void testNumberOfSolutions() {
		Assert.assertEquals(
			"Number of solutions",
			4,
			InheritanceTreeDepth.Solutions.length);
	}
	public void testSolution1() {
		//	3.100.Sub-entity = ptidej.example.composite2.Title
		//	3.100.Super-entity = java.lang.Object
		//	3.100.Depth = 3
		Assert.assertEquals(
			"depth(Sub-entity -|>- Super-entity) >= 3",
			"ptidej.example.composite2.IndentedParagraph",
			InheritanceTreeDepth.Solutions[0].getComponent(
				InheritanceTreeDepth.ENTITY).getDisplayValue());
		Assert.assertEquals(
			"depth(Sub-entity -|>- Super-entity) >= 3",
			"ptidej.example.composite2.AbstractDocument",
			InheritanceTreeDepth.Solutions[0].getComponent(
				InheritanceTreeDepth.STRING).getDisplayValue());
		Assert.assertEquals(
			"depth(Sub-entity -|>- Super-entity) >= 3",
			"3",
			InheritanceTreeDepth.Solutions[0].getComponent(
				InheritanceTreeDepth.DEPTH).getDisplayValue());
	}
	public void testSolution2() {
		//	1.100.Sub-entity = ptidej.example.composite2.IndentedParagraph
		//	1.100.Super-entity = ptidej.example.composite2.AbstractDocument
		//	1.100.Depth = 3
		Assert.assertEquals(
			"depth(Sub-entity -|>- Super-entity) >= 3",
			"ptidej.example.composite2.Paragraph",
			InheritanceTreeDepth.Solutions[1].getComponent(
				InheritanceTreeDepth.ENTITY).getDisplayValue());
		Assert.assertEquals(
			"depth(Sub-entity -|>- Super-entity) >= 3",
			"java.lang.Object",
			InheritanceTreeDepth.Solutions[1].getComponent(
				InheritanceTreeDepth.STRING).getDisplayValue());
		Assert.assertEquals(
			"depth(Sub-entity -|>- Super-entity) >= 3",
			"3",
			InheritanceTreeDepth.Solutions[1].getComponent(
				InheritanceTreeDepth.DEPTH).getDisplayValue());
	}
	public void testSolution3() {
		//	2.100.Sub-entity = ptidej.example.composite2.Paragraph
		//	2.100.Super-entity = java.lang.Object
		//	2.100.Depth = 3
		Assert.assertEquals(
			"depth(Sub-entity -|>- Super-entity) >= 3",
			"ptidej.example.composite2.Title",
			InheritanceTreeDepth.Solutions[2].getComponent(
				InheritanceTreeDepth.ENTITY).getDisplayValue());
		Assert.assertEquals(
			"depth(Sub-entity -|>- Super-entity) >= 3",
			"java.lang.Object",
			InheritanceTreeDepth.Solutions[2].getComponent(
				InheritanceTreeDepth.STRING).getDisplayValue());
		Assert.assertEquals(
			"depth(Sub-entity -|>- Super-entity) >= 3",
			"3",
			InheritanceTreeDepth.Solutions[2].getComponent(
				InheritanceTreeDepth.DEPTH).getDisplayValue());
	}
	public void testSolution4() {
		//	4.100.Sub-entity = ptidej.example.composite2.IndentedParagraph
		//	4.100.Super-entity = java.lang.Object
		//	4.100.Depth = 4
		Assert.assertEquals(
			"depth(Sub-entity -|>- Super-entity) >= 3",
			"ptidej.example.composite2.IndentedParagraph",
			InheritanceTreeDepth.Solutions[3].getComponent(
				InheritanceTreeDepth.ENTITY).getDisplayValue());
		Assert.assertEquals(
			"depth(Sub-entity -|>- Super-entity) >= 3",
			"java.lang.Object",
			InheritanceTreeDepth.Solutions[3].getComponent(
				InheritanceTreeDepth.STRING).getDisplayValue());
		Assert.assertEquals(
			"depth(Sub-entity -|>- Super-entity) >= 3",
			"4",
			InheritanceTreeDepth.Solutions[3].getComponent(
				InheritanceTreeDepth.DEPTH).getDisplayValue());
	}
	public void testSolutionPercentage() {
		for (int i = 0; i < InheritanceTreeDepth.Solutions.length; i++) {
			Assert.assertEquals(
				"Solution with all constraints",
				100,
				InheritanceTreeDepth.Solutions[i].getConfidence());
		}
	}
}
