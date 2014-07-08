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
package ptidej.solver.test.java;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Properties;
import junit.framework.TestCase;
import padl.kernel.IAbstractLevelModel;
import ptidej.solver.Occurrence;
import ptidej.solver.OccurrenceBuilder;
import ptidej.solver.Problem;
import ptidej.solver.domain.Manager;
import util.io.ReaderInputStream;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/09/19
 */
public abstract class Primitive extends TestCase {
	protected static Occurrence[] automaticSolve(
		final Class testClass,
		final Class sourceClass) {

		Occurrence[] solutions = new Occurrence[0];

		try {
			final IAbstractLevelModel abstractLevelModel =
				(IAbstractLevelModel) sourceClass.newInstance();

			final Problem problem =
				(Problem) testClass.getMethod(
					"getProblem",
					new Class[] { List.class }).invoke(
					null,
					new Object[] { Manager.build(abstractLevelModel) });

			final StringWriter writer = new StringWriter();
			problem.setWriter(new PrintWriter(writer));
			problem.automaticSolve(true);

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

			solutions = solutionBuilder.getAllOccurrences(properties);
		}
		catch (final IllegalArgumentException e) {
			e.printStackTrace();
		}
		catch (final SecurityException e) {
			e.printStackTrace();
		}
		catch (final InstantiationException e) {
			e.printStackTrace();
		}
		catch (final IllegalAccessException e) {
			e.printStackTrace();
		}
		catch (final InvocationTargetException e) {
			e.printStackTrace();
		}
		catch (final NoSuchMethodException e) {
			e.printStackTrace();
		}

		return solutions;
	}
	// JYves 6/10/2004
	// Added for Fingerprinting testing
	protected static Occurrence[] automaticSolve(
		final Problem problem,
		final Class sourceClass) {

		Occurrence[] solutions = new Occurrence[0];

		try {
			//	final IAbstractLevelModel abstractLevelModel =
			//		(IAbstractLevelModel) sourceClass.newInstance();

			final StringWriter writer = new StringWriter();
			problem.setWriter(new PrintWriter(writer));
			problem.automaticSolve(true);

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

			solutions = solutionBuilder.getAllOccurrences(properties);
		}
		catch (final IllegalArgumentException e) {
			e.printStackTrace();
		}
		//	catch (final IllegalAccessException e) {
		//		e.printStackTrace();
		//	}
		//	catch (final InstantiationException e) {
		//		e.printStackTrace();
		//	}

		return solutions;
	}

	protected static Occurrence[] combinatorialAutomaticSolve(
		final Class testClass,
		final Class sourceClass) {

		// Yann 2004/09/20: Duplicate!
		// This method is an exact duplicate of method
		// automaticSolve() but for the call to the solver.

		Occurrence[] solutions = new Occurrence[0];

		try {
			final IAbstractLevelModel abstractLevelModel =
				(IAbstractLevelModel) sourceClass.newInstance();

			final Problem problem =
				(Problem) testClass.getMethod(
					"getProblem",
					new Class[] { List.class }).invoke(
					null,
					new Object[] { Manager.build(abstractLevelModel) });

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

			solutions = solutionBuilder.getCanonicalOccurrences(properties);
		}
		catch (final IllegalArgumentException e) {
			e.printStackTrace();
		}
		catch (final SecurityException e) {
			e.printStackTrace();
		}
		catch (final InstantiationException e) {
			e.printStackTrace();
		}
		catch (final IllegalAccessException e) {
			e.printStackTrace();
		}
		catch (final InvocationTargetException e) {
			e.printStackTrace();
		}
		catch (final NoSuchMethodException e) {
			e.printStackTrace();
		}

		return solutions;
	}

	// JYves 6/10/2004
	// Added for Fingerprinting testing	
	//	protected static Solution[] combinatorialAutomaticSolve(
	//		final Problem problem,
	//		final Class sourceClass) {
	//
	//		// Yann 2004/09/20: Duplicate!
	//		// This method is an exact duplicate of method
	//		// automaticSolve() but for the call to the solver.
	//
	//		Solution[] solutions = new Solution[0];
	//
	//		try {
	//			final IAbstractLevelModel abstractLevelModel =
	//				(IAbstractLevelModel) sourceClass.newInstance();
	//
	//			final StringWriter writer = new StringWriter();
	//			problem.setWriter(new PrintWriter(writer));
	//			problem.combinatorialAutomaticSolve(true);
	//
	//			final Properties properties = new Properties();
	//			try {
	//				properties.load(
	//					new ReaderInputStream(
	//						new StringReader(writer.getBuffer().toString())));
	//			}
	//			catch (final IOException e) {
	//				e.printStackTrace();
	//			}
	//			final SolutionBuilder solutionBuilder =
	//				SolutionBuilder.getCurrentSolutionBuilder();
	//
	//			solutions =
	//				solutionBuilder.getCanonicalSolutions(
	//					properties,
	//					abstractLevelModel);
	//		}
	//		catch (final IllegalArgumentException e) {
	//			e.printStackTrace();
	//		}
	//		catch (final IllegalAccessException e) {
	//			e.printStackTrace();
	//		}
	//		catch (final InstantiationException e) {
	//			e.printStackTrace();
	//		}
	//
	//		return solutions;
	//	}
	public Primitive(final String name) {
		super(name);
	}
}
