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
package ptidej.solver.test.claire.example;

import junit.framework.Assert;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import padl.motif.IDesignMotifModel;
import ptidej.solver.Occurrence;
import ptidej.solver.OccurrenceGenerator;
import ptidej.solver.test.claire.Primitive;

public class Composite5 extends Primitive {
	private static int NumberOfExpectedSolutions;
	private static Occurrence[] FoundSolutions;
	private static Occurrence[] ExpectedSolutions;

	public Composite5(final String name) {
		super(name);
	}
	protected void setUp() throws IllegalAccessException,
			InstantiationException {

		if (Composite5.FoundSolutions == null) {
			final IDesignMotifModel pattern =
				(IDesignMotifModel) padl.motif.repository.Composite.class
					.newInstance();

			final ICodeLevelModel codeLevelModel =
				Factory.getInstance().createCodeLevelModel(
					"ptidej.example.composite5");
			try {
				codeLevelModel
					.create(new CompleteClassFileCreator(
						new String[] { "../Ptidej Tests/bin/ptidej/example/composite5/" }));
			}
			catch (final CreationException e) {
				e.printStackTrace();
			}

			// Expected solutions.
			Composite5.ExpectedSolutions =
				SolutionReader.getExpectedSolutions(
					"Composite5",
					codeLevelModel);
			Composite5.NumberOfExpectedSolutions =
				SolutionReader.getExpectedNumberOfSolutions(
					"Composite5",
					codeLevelModel);

			// Solutions found.
			Composite5.FoundSolutions =
				this.testDesignPattern(
					Composite4.class,
					Primitive.ALL_SOLUTIONS,
					pattern.getName(),
					codeLevelModel,
					OccurrenceGenerator.SOLVER_COMBINATORIAL_AUTOMATIC,
					OccurrenceGenerator.PROBLEM_AC4);
		}
	}
	public void testNumberOfSolutions() {
		Assert.assertEquals(
			"Number of solutions",
			Composite5.NumberOfExpectedSolutions,
			Composite5.FoundSolutions.length);
	}
	public void testSolutions() {
		for (int i = 0; i < Composite5.NumberOfExpectedSolutions; i++) {
			Assert.assertEquals(
				"",
				Composite5.ExpectedSolutions[i],
				Composite5.FoundSolutions[i]);
		}
	}
	//	public void testSolution1() {
	//		final int[] solutions = new int[] { 0, 1, 2, 6, 7, 8 };
	//		for (int i = 0; i < solutions.length; i++) {
	//			this.testSolutionComponent(
	//				this.solutions[solutions[i]],
	//				"Component",
	//				"ptidej.example.composite5.Element");
	//			this.testSolutionComponent(
	//				this.solutions[solutions[i]],
	//				"Composite",
	//				"ptidej.example.composite5.Document");
	//			this.testSolutionComponent(
	//				this.solutions[solutions[i]],
	//				"Leaf-1",
	//				"ptidej.example.composite5.IndentedParagraph");
	//			this.testSolutionComponent(
	//				this.solutions[solutions[i]],
	//				"Leaf-2",
	//				"ptidej.example.composite5.Paragraph");
	//			this.testSolutionComponent(
	//				this.solutions[solutions[i]],
	//				"Leaf-3",
	//				"ptidej.example.composite5.Title");
	//		}
	//	}
	//	public void testSolution2() {
	//		final int[] solutions = new int[] { 3, 4, 5, 9, 10, 11 };
	//		for (int i = 0; i < solutions.length; i++) {
	//			this.testSolutionComponent(
	//				this.solutions[solutions[i]],
	//				"Component",
	//				"ptidej.example.composite5.Element");
	//			this.testSolutionComponent(
	//				this.solutions[solutions[i]],
	//				"Composite",
	//				"ptidej.example.composite5.Document");
	//			this.testSolutionComponent(
	//				this.solutions[solutions[i]],
	//				"Leaf-1",
	//				"ptidej.example.composite5.IndentedParagraph");
	//			this.testSolutionComponent(
	//				this.solutions[solutions[i]],
	//				"Leaf-2",
	//				"ptidej.example.composite5.Paragraph");
	//			this.testSolutionComponent(
	//				this.solutions[solutions[i]],
	//				"Leaf-3",
	//				"ptidej.example.composite5.SpecialIndentedParagraph");
	//			this.testSolutionComponent(
	//				this.solutions[solutions[i]],
	//				"Leaf-4",
	//				"ptidej.example.composite5.Title");
	//		}
	//	}
	//	public void testSolutionPercentage() {
	//		Composite5.assertEquals(
	//			"Distorted solution percentage",
	//			25,
	//			this.solutions[0].getPercentage());
	//		Composite5.assertEquals(
	//			"Distorted solution percentage",
	//			12,
	//			this.solutions[1].getPercentage());
	//		Composite5.assertEquals(
	//			"Distorted solution percentage",
	//			6,
	//			this.solutions[2].getPercentage());
	//		Composite5.assertEquals(
	//			"Distorted solution percentage",
	//			7,
	//			this.solutions[3].getPercentage());
	//		Composite5.assertEquals(
	//			"Distorted solution percentage",
	//			3,
	//			this.solutions[4].getPercentage());
	//		Composite5.assertEquals(
	//			"Distorted solution percentage",
	//			1,
	//			this.solutions[5].getPercentage());
	//		Composite5.assertEquals(
	//			"Distorted solution percentage",
	//			2,
	//			this.solutions[6].getPercentage());
	//		Composite5.assertEquals(
	//			"Distorted solution percentage",
	//			1,
	//			this.solutions[7].getPercentage());
	//		Composite5.assertEquals(
	//			"Distorted solution percentage",
	//			1,
	//			this.solutions[8].getPercentage());
	//		Composite5.assertEquals(
	//			"Distorted solution percentage",
	//			1,
	//			this.solutions[9].getPercentage());
	//		Composite5.assertEquals(
	//			"Distorted solution percentage",
	//			1,
	//			this.solutions[10].getPercentage());
	//		Composite5.assertEquals(
	//			"Distorted solution percentage",
	//			1,
	//			this.solutions[11].getPercentage());
	//	}
}
