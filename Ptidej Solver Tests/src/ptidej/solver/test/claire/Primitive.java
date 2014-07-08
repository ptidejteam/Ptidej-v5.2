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
package ptidej.solver.test.claire;

import java.util.Properties;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.kernel.IAbstractLevelModel;
import padl.kernel.IAbstractModel;
import padl.motif.IDesignMotifModel;
import ptidej.solver.Occurrence;
import ptidej.solver.OccurrenceBuilder;
import ptidej.solver.OccurrenceGenerator;

public abstract class Primitive extends TestCase {
	protected final static int CANONICAL_SOLUTIONS = 1;
	protected final static int ALL_SOLUTIONS = 2;
	protected final static char[] SUPER_ENTITY = "SuperEntity".toCharArray();
	public static final char[] SUB_ENTITY = "SubEntity".toCharArray();

	private static Class previousTestCase;
	private static int previousSolutionType;
	private static char[] previousPatternName;
	private static IAbstractModel previousSource;
	private static int previousAlgorithm;
	private static Occurrence[] solutions;

	public Primitive(final String name) {
		super(name);
	}
	protected Occurrence[] testDesignPattern(
		final Class testCase,
		final int solutionType,
		final char[] patternName,
		final Class sourceClass,
		final int solver,
		final int algorithm) throws IllegalAccessException,
			InstantiationException {

		// I build an instance of the pattern and of
		// the source code.
		// For the moment, this means that I build
		// instances of subclasses of class AbstractLevelModel
		// that represent a pattern and the  source code.
		return this.testDesignPattern(
			testCase,
			solutionType,
			patternName,
			null,
			(IAbstractModel) sourceClass.newInstance(),
			solver,
			algorithm);
	}
	protected Occurrence[] testDesignPattern(
		final Class testCase,
		final int solutionType,
		final char[] patternName,
		final IAbstractLevelModel source,
		final int solver,
		final int algorithm) {

		return this.testDesignPattern(
			testCase,
			solutionType,
			patternName,
			null,
			source,
			solver,
			algorithm);
	}
	private Occurrence[] testDesignPattern(
		final Class testCase,
		final int solutionType,
		final char[] patternName,
		final IDesignMotifModel aMotif,
		final IAbstractModel source,
		final int solver,
		final int algorithm) {

		// Yann 2002/08/11: Memory!
		// For a given test case, I call once and only once the constraint
		// solver by recording the current test case and the constraint
		// solutions. (This avoids calling over again and again the
		// constraint solver.)

		if (Primitive.previousTestCase != testCase
				|| Primitive.previousSolutionType != solutionType
				|| !Primitive.previousPatternName.equals(patternName)
				|| Primitive.previousSource == null
				|| !Primitive.previousSource.getName().equals(source.getName())
				|| Primitive.previousAlgorithm != algorithm) {

			Primitive.previousTestCase = testCase;
			Primitive.previousSolutionType = solutionType;
			Primitive.previousPatternName = patternName;
			Primitive.previousSource = source;
			Primitive.previousAlgorithm = algorithm;

			// I call the constraint solver.
			final OccurrenceGenerator solutionGenerator =
				OccurrenceGenerator.getInstance();
			final Properties generatedSolutions;
			if (aMotif == null) {
				generatedSolutions =
					solutionGenerator.getOccurrences(
						patternName,
						source,
						OccurrenceGenerator.VERSION_PTIDEJSOLVER3,
						solver,
						algorithm);
			}
			else {
				generatedSolutions =
					solutionGenerator.getOccurrences(
						aMotif,
						source,
						OccurrenceGenerator.VERSION_PTIDEJSOLVER3,
						solver,
						algorithm);
			}

			// I build the solutions from the solutions given by the constraint
			// solver and the instance of class AbstractLevelModel representing the source code.
			final OccurrenceBuilder solutionBuilder =
				OccurrenceBuilder.getInstance();
			if (solutionType == Primitive.ALL_SOLUTIONS) {
				Primitive.solutions =
					solutionBuilder.getAllOccurrences(generatedSolutions);
			}
			else if (solutionType == Primitive.CANONICAL_SOLUTIONS) {
				Primitive.solutions =
					solutionBuilder.getCanonicalOccurrences(generatedSolutions);
			}
			else {
				Assert
					.fail("Unrecognized type of solution for Primitive.testDesignPattern(...).");
				return null;
			}
		}

		return Primitive.solutions;
	}
	protected Occurrence[] testDesignPattern(
		final Class testCase,
		final int solutionType,
		final Class patternClass,
		final Class sourceClass,
		final int solver,
		final int algorithm) throws IllegalAccessException,
			InstantiationException {

		// I build an instance of the pattern and of
		// the source code.
		// For the moment, this means that I build
		// instances of subclasses of class AbstractLevelModel
		// that represent a pattern and the  source code.
		final IDesignMotifModel pattern =
			(IDesignMotifModel) patternClass.newInstance();

		return this.testDesignPattern(
			testCase,
			solutionType,
			pattern.getName(),
			pattern,
			(IAbstractModel) sourceClass.newInstance(),
			solver,
			algorithm);
	}
	protected Occurrence[] testDesignPattern(
		final Class testCase,
		final int solutionType,
		final IDesignMotifModel aMotif,
		final IAbstractLevelModel source,
		final int solver,
		final int algorithm) {

		return this.testDesignPattern(
			testCase,
			solutionType,
			aMotif.getName(),
			aMotif,
			source,
			solver,
			algorithm);
	}
	protected void testSolutionComponent(
		final Occurrence solution,
		final char[] solutionComponentName,
		final String expectedValue) {

		Assert.assertEquals(String.valueOf(solutionComponentName)
				+ " solution component value", expectedValue, solution
			.getComponent(solutionComponentName)
			.getValue());
	}
}
