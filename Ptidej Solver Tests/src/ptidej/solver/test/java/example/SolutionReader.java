/*
 * (c) Copyright 2001-2004 Yann-Gaël Guéhéneuc,
 * University of Montréal.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the author, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN,
 * ANY LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHOR IS ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package ptidej.solver.test.java.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import padl.kernel.IAbstractLevelModel;
import ptidej.solver.Occurrence;
import ptidej.solver.OccurrenceBuilder;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/08/16
 */
public class SolutionReader {
	private static String CurrentTestName;
	private static Occurrence[] ExpectedSolutions;
	private static int NumberOfExpectedSolutions;

	public static int getExpectedNumberOfSolutions(
		final String testName,
		final IAbstractLevelModel anAbstractLevelModel) {

		SolutionReader.readExpectedSolutions(testName, anAbstractLevelModel);
		return SolutionReader.NumberOfExpectedSolutions;
	}
	public static Occurrence[] getExpectedSolutions(
		final String testName,
		final IAbstractLevelModel anAbstractLevelModel) {

		SolutionReader.readExpectedSolutions(testName, anAbstractLevelModel);
		return SolutionReader.ExpectedSolutions;
	}
	private static void readExpectedSolutions(
		final String testName,
		final IAbstractLevelModel anAbstractLevelModel) {

		if (SolutionReader.ExpectedSolutions == null
				|| SolutionReader.CurrentTestName == null
				|| SolutionReader.CurrentTestName != null
				&& !SolutionReader.CurrentTestName.equals(testName)) {

			SolutionReader.CurrentTestName = testName;

			final OccurrenceBuilder solutionBuilder =
				OccurrenceBuilder.getInstance();
			final Properties properties = new Properties();
			try {
				properties
					.load(new FileInputStream(
						"../Ptidej Solver Tests/bin/ptidej/solver/test/java/example/ConstraintResultsFor"
								+ testName + ".ini"));
			}
			catch (final FileNotFoundException fnfe) {
				fnfe.printStackTrace();
			}
			catch (final IOException ioe) {
				ioe.printStackTrace();
			}
			SolutionReader.ExpectedSolutions =
				solutionBuilder.getCanonicalOccurrences(properties);
			SolutionReader.NumberOfExpectedSolutions =
				SolutionReader.ExpectedSolutions.length;
		}
	}
	private SolutionReader() {
	}
}
