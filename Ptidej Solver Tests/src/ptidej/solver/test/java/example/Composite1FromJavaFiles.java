/*******************************************************************************
 * Copyright (c) 2001-2014 Yann-Gaël Guéhéneuc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Yann-Gaël Guéhéneuc and others, see in file; API and its implementation
 ******************************************************************************/
package ptidej.solver.test.java.example;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Properties;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.generator.helper.ModelGenerator;
import padl.kernel.IIdiomLevelModel;
import ptidej.solver.Occurrence;
import ptidej.solver.OccurrenceBuilder;
import ptidej.solver.Problem;
import ptidej.solver.domain.Manager;
import ptidej.solver.problem.CompositeMotif;
import util.io.ProxyConsole;
import util.io.ReaderInputStream;

public class Composite1FromJavaFiles extends TestCase {
	private static int NumberOfExpectedSolutions;
	private static Occurrence[] FoundSolutions;
	private static Occurrence[] ExpectedSolutions;

	public Composite1FromJavaFiles(final String name) {
		super(name);
	}
	protected void setUp() throws IllegalAccessException,
			InstantiationException {

		if (Composite1FromJavaFiles.FoundSolutions == null) {
			//	final PatternModel pattern =
			//		(PatternModel) padl
			//			.pattern
			//			.repository
			//			.Composite
			//			.class
			//			.newInstance();

			try {
				//	final ICodeLevelModel codeLevelModel =
				//		Factory.getInstance().createCodeLevelModel(
				//			"ptidej.example.composite1");
				//	codeLevelModel.create(new CompleteClassFileCreator(
				//		ConstituentRepository.getInstance(ClassFileRepository
				//			.getInstance(ConstituentRepository.class)),
				//		new String[] { "../Ptidej Tests/bin/ptidej/example/composite1/" }));
				//
				//	final IIdiomLevelModel idiomLevelModel =
				//		(IIdiomLevelModel) new AACRelationshipsAnalysis()
				//			.invoke(codeLevelModel);

				final String rootPath =
					"../Ptidej Tests/src/ptidej/example/composite1/";
				final String someFilesPaths[] = new File(rootPath).list();
				for (int i = 0; i < someFilesPaths.length; i++) {
					someFilesPaths[i] =
						new StringBuffer()
							.append(rootPath)
							.append(someFilesPaths[i])
							.toString();
				}
				final IIdiomLevelModel idiomLevelModel =
					ModelGenerator
						.generateModelFromJavaFilesDirectoriesUsingEclipse(
							"",
							"../Ptidej Tests/src/",
							someFilesPaths,
							null);

				// Expected solutions.
				Composite1FromJavaFiles.ExpectedSolutions =
					SolutionReader.getExpectedSolutions(
						"Composite1",
						idiomLevelModel);
				Composite1FromJavaFiles.NumberOfExpectedSolutions =
					SolutionReader.getExpectedNumberOfSolutions(
						"Composite1",
						idiomLevelModel);

				// Solutions found.
				final Problem problem =
					CompositeMotif.getProblem(Manager.build(idiomLevelModel));

				final StringWriter writer = new StringWriter();
				problem.setWriter(new PrintWriter(writer));
				problem.automaticSolve(true);

				final Properties properties = new Properties();
				properties.load(new ReaderInputStream(new StringReader(writer
					.getBuffer()
					.toString())));
				final OccurrenceBuilder solutionBuilder =
					OccurrenceBuilder.getInstance();
				Composite1FromJavaFiles.FoundSolutions =
					solutionBuilder.getCanonicalOccurrences(properties);
			}
			//	catch (final CreationException e) {
			//		e.printStackTrace(Output.getInstance().errorOutput());
			//	}
			//	catch (final UnsupportedSourceModelException e) {
			//		e.printStackTrace(Output.getInstance().errorOutput());
			//	}
			catch (final IOException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
		}
	}
	public void testNumberOfSolutions() {
		Assert.assertEquals(
			"Number of solutions",
			Composite1FromJavaFiles.NumberOfExpectedSolutions,
			Composite1FromJavaFiles.FoundSolutions.length);
	}
	public void testSolutions() {
		for (int i = 0; i < Composite1FromJavaFiles.NumberOfExpectedSolutions; i++) {
			Assert.assertEquals(
				"",
				Composite1FromJavaFiles.ExpectedSolutions[i],
				Composite1FromJavaFiles.FoundSolutions[i]);
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
