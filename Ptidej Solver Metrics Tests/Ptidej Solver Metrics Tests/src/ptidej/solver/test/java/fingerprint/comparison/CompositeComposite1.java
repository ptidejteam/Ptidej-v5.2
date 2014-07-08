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

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Properties;
import junit.framework.TestCase;
import padl.creator.CompleteClassFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import padl.util.repository.constituent.ConstituentRepository;
import padl.util.repository.file.DefaultFileRepository;
import ptidej.solver.Occurrence;
import ptidej.solver.OccurrenceBuilder;
import ptidej.solver.Problem;
import ptidej.solver.domain.Manager;
import ptidej.solver.fingerprint.ReducedDomainBuilder;
import ptidej.solver.fingerprint.problem.CompositeMotif;
import ptidej.solver.test.java.example.SolutionReader;
import util.PropertyManager;
import util.io.ReaderInputStream;

public class CompositeComposite1 extends TestCase {
	private static int NumberOfExpectedSolutions;
	private static Occurrence[] FoundSolutions;
	private static Occurrence[] ExpectedSolutions;

	public CompositeComposite1(final String name) {
		super(name);
	}
	protected void setUp() throws IllegalAccessException,
			InstantiationException {

		if (CompositeComposite1.FoundSolutions == null) {
			final ICodeLevelModel codeLevelModel =
				Factory.getInstance().createCodeLevelModel(
					"ptidej.example.composite1");
			try {
				codeLevelModel.create(new CompleteClassFileCreator(
					ConstituentRepository.getInstance(DefaultFileRepository
						.getInstance(ConstituentRepository.class)),
					new String[] { PropertyManager.getExamplesDirectory()
							+ "bin/ptidej/example/composite1/" }));
			}
			catch (final CreationException e) {
				e.printStackTrace();
			}

			// Expected solutions.
			CompositeComposite1.ExpectedSolutions =
				SolutionReader.getExpectedSolutions(
					"Composite1",
					codeLevelModel);
			CompositeComposite1.NumberOfExpectedSolutions =
				SolutionReader.getExpectedNumberOfSolutions(
					"Composite1",
					codeLevelModel);

			final ReducedDomainBuilder rdg =
				new ReducedDomainBuilder(codeLevelModel);

			// Solutions found.
			final Problem problem =
				CompositeMotif.getProblem(Manager.build(codeLevelModel), rdg);

			final StringWriter writer = new StringWriter();
			problem.setWriter(new PrintWriter(writer));
			problem.combinatorialAutomaticSolve(true);

			final Properties properties = new Properties();
			try {
				properties.load(new ReaderInputStream(new StringReader(writer
					.getBuffer()
					.toString())));
			}
			catch (final IOException e) {
				e.printStackTrace();
			}
			final OccurrenceBuilder solutionBuilder =
				OccurrenceBuilder.getInstance();
			CompositeComposite1.FoundSolutions =
				solutionBuilder.getCanonicalOccurrences(properties);
		}
	}
	public void testNumberOfSolutions() {
		CompositeComposite1.assertEquals(
			"Number of solutions",
			CompositeComposite1.NumberOfExpectedSolutions,
			CompositeComposite1.FoundSolutions.length);
	}
	public void testSolutions() {
		for (int i = 0; i < CompositeComposite1.NumberOfExpectedSolutions; i++) {
			CompositeComposite1.assertEquals(
				"",
				CompositeComposite1.ExpectedSolutions[i],
				CompositeComposite1.FoundSolutions[i]);
		}
	}
	//	public void testComponentSolutionComponent() {
	//		final int[] solutionWithAbstractDocument = new int[] { 0, 3, 6, 9 };
	//		for (int i = 0; i < solutionWithAbstractDocument.length; i++) {
	//			this.testSolutionComponent(
	//				Composite1.FoundSolutions[solutionWithAbstractDocument[i]],
	//				"Component",
	//				"ptidej.example.composite1.AbstractDocument");
	//		}
	//
	//		final int[] solutionWithElement =
	//			new int[] { 1, 2, 4, 5, 7, 8, 10, 11 };
	//		for (int i = 0; i < solutionWithAbstractDocument.length; i++) {
	//			this.testSolutionComponent(
	//				Composite1.FoundSolutions[solutionWithElement[i]],
	//				"Component",
	//				"ptidej.example.composite1.Element");
	//		}
	//	}
	//	public void testCompositeSolutionComponent() {
	//		for (int i = 0; i < Composite1.NUMBER_OF_EXPECTED_SOLUTIONS; i++) {
	//			this.testSolutionComponent(
	//				Composite1.FoundSolutions[i],
	//				"Composite",
	//				"ptidej.example.composite1.Document");
	//		}
	//	}
	//	public void testLeaf1SolutionComponent() {
	//		for (int i = 0; i < Composite1.NUMBER_OF_EXPECTED_SOLUTIONS; i++) {
	//			this.testSolutionComponent(
	//				Composite1.FoundSolutions[i],
	//				"Leaf-1",
	//				"ptidej.example.composite1.IndentedParagraph");
	//		}
	//	}
	//	public void testLeaf2SolutionComponent() {
	//		for (int i = 0; i < Composite1.NUMBER_OF_EXPECTED_SOLUTIONS; i++) {
	//			this.testSolutionComponent(
	//				Composite1.FoundSolutions[i],
	//				"Leaf-2",
	//				"ptidej.example.composite1.Paragraph");
	//		}
	//	}
	//	public void testLeaf3SolutionComponent() {
	//		for (int i = 0; i < Composite1.NUMBER_OF_EXPECTED_SOLUTIONS; i++) {
	//			this.testSolutionComponent(
	//				Composite1.FoundSolutions[i],
	//				"Leaf-3",
	//				"ptidej.example.composite1.Title");
	//		}
	//	}
	//	public void testLeaf4SolutionComponent() {
	//		final int[] solutionWithAbstractDocument = new int[] { 0, 3, 6, 9 };
	//		for (int i = 0; i < solutionWithAbstractDocument.length; i++) {
	//			this.testSolutionComponent(
	//				Composite1.FoundSolutions[solutionWithAbstractDocument[i]],
	//				"Leaf-4",
	//				"ptidej.example.composite1.Element");
	//		}
	//	}
	//	public void testSolutionPercentage() {
	//		Composite1.assertEquals(
	//			"Distorted solution percentage",
	//			12,
	//			Composite1.FoundSolutions[0].getPercentage());
	//		Composite1.assertEquals(
	//			"Distorted solution percentage",
	//			12,
	//			Composite1.FoundSolutions[1].getPercentage());
	//		Composite1.assertEquals(
	//			"Distorted solution percentage",
	//			6,
	//			Composite1.FoundSolutions[2].getPercentage());
	//		Composite1.assertEquals(
	//			"Distorted solution percentage",
	//			3,
	//			Composite1.FoundSolutions[3].getPercentage());
	//		Composite1.assertEquals(
	//			"Distorted solution percentage",
	//			3,
	//			Composite1.FoundSolutions[4].getPercentage());
	//		for (int i = 5; i < 11; i++) {
	//			Composite1.assertEquals(
	//				"Distorted solution percentage",
	//				1,
	//				Composite1.FoundSolutions[i].getPercentage());
	//		}
	//	}
}
