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
package epi.test.java;

import java.util.Hashtable;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.analysis.UnsupportedSourceModelException;
import padl.analysis.repository.AACRelationshipsAnalysis;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IIdiomLevelModel;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import util.io.ProxyConsole;
import util.io.ProxyDisk;
import epi.solver.Approximation;
import epi.solver.EPISolver;
import epi.solver.OptimisedBitVectorSolver;
import epi.solver.Solution;
import epi.solver.StringBuilder;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/01/24
 */

public class JavaAWTTest extends TestCase {
	private static int NumberOfExpectedSolutions;
	private static Solution[] FoundSolutions;

	public JavaAWTTest(final String name) {
		super(name);
	}
	protected void setUp() throws IllegalAccessException,
			InstantiationException {

		if (JavaAWTTest.FoundSolutions == null) {
			try {
				final long startTime = System.currentTimeMillis();

				final ICodeLevelModel codeLevelModel =
					Factory.getInstance().createCodeLevelModel(
						"Java AWT v1.4.2_04");
				codeLevelModel.create(new CompleteClassFileCreator(
					new String[] { "../EPI Tests/rsc/Java AWT v1.4.2_04/" },
					true));
				final IIdiomLevelModel idiomLevelModel =
					(IIdiomLevelModel) new AACRelationshipsAnalysis()
						.invoke(codeLevelModel);
				final String programString =
					StringBuilder.buildModelString(idiomLevelModel);
				final String motifString =
					StringBuilder.buildPatternString("Composite");
				final Hashtable domains = null;
				final EPISolver solver =
					new OptimisedBitVectorSolver(
						programString,
						motifString,
						"Composite",
						new Approximation(),
						domains);
				JavaAWTTest.FoundSolutions = solver.computeSolutions();

				ProxyConsole
					.getInstance()
					.debugOutput()
					.print(JavaAWTTest.FoundSolutions.length);
				ProxyConsole
					.getInstance()
					.debugOutput()
					.print(" solutions in ");
				ProxyConsole
					.getInstance()
					.debugOutput()
					.print(System.currentTimeMillis() - startTime);
				ProxyConsole.getInstance().debugOutput().println(" ms.");
				Solution.print(JavaAWTTest.FoundSolutions, ProxyDisk
					.getInstance()
					.fileTempOutput("rsc/Java AWT v1.4.2_04.solutions.txt"));
			}
			catch (final CreationException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
			catch (final UnsupportedSourceModelException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
		}
	}
	public void testNumberOfSolutions() {
		Assert.assertEquals(
			"Number of solutions",
			JavaAWTTest.NumberOfExpectedSolutions,
			JavaAWTTest.FoundSolutions.length);
	}
}
