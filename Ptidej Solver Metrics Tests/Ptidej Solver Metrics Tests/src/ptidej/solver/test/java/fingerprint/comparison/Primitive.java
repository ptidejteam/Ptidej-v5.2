/*
 * (c) Copyright 2001-2004 , Jean-Yves Guyomarc'h
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
package ptidej.solver.test.java.fingerprint.comparison;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import junit.framework.TestCase;
import padl.creator.CompleteClassFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IGhost;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import padl.util.repository.constituent.ConstituentRepository;
import padl.util.repository.file.DefaultFileRepository;
import ptidej.solver.Occurrence;
import ptidej.solver.OccurrenceBuilder;
import ptidej.solver.OccurrenceComponent;
import ptidej.solver.Problem;
import ptidej.solver.domain.Manager;
import ptidej.solver.fingerprint.ReducedDomainBuilder;
import ptidej.solver.fingerprint.Rule;
import util.io.ReaderInputStream;
import choco.integer.IntVar;

/**
 * @author Jean-Yves Guyomarc'h
 * @since  2004/09/19
 */
public abstract class Primitive extends TestCase {
	private static Logger Logger = null;
	protected static Occurrence[] automaticSolve(
		final Class testClass,
		final String path,
		final String ilmName) {

		Primitive.Logger
			.setMode(ptidej.solver.test.java.fingerprint.comparison.Logger.WITHOUT_RULE);
		final Occurrence[] sol =
			Primitive.solveAndLog(testClass, path, ilmName, null);
		printSolution(ilmName + "_Sol_NoRule", sol);
		return sol;
	}
	protected static Occurrence[] automaticSolve(
		final Class testClass,
		final String path,
		final String ilmName,
		final Rule role) {

		Primitive.Logger
			.setMode(ptidej.solver.test.java.fingerprint.comparison.Logger.WITH_RULE);
		final Occurrence[] sol =
			Primitive.solveAndLog(testClass, path, ilmName, role);
		printSolution(ilmName + "_Sol", sol);
		return sol;
	}
	private static int countGhostSolution(
		final Occurrence[] someSolutions,
		final ICodeLevelModel aCodeLevelModel) {

		int cpt = 0;
		for (int i = 0; i < someSolutions.length; i++) {
			boolean ghosted = false;
			final Iterator iter = someSolutions[i].getComponents().iterator();
			while (iter.hasNext()) {
				// TEST IF INSTANCE OF GHOST
				OccurrenceComponent sc = (OccurrenceComponent) iter.next();
				if (aCodeLevelModel.getConstituentFromName(sc.getValue()) instanceof IGhost) {
					ghosted = true;
				}
			}
			if (ghosted) {
				cpt++;
			}
		}

		return cpt;
	}
	protected static void initialize(final String testName) {
		Primitive.Logger = new Logger(testName);
	}
	protected static void setLoggerMode(final int loggerMode) {
		Primitive.Logger.setMode(loggerMode);
	}
	private static void printSolution(
		final String testName,
		final Occurrence[] sol) {
		try {
			final PrintWriter out =
				new PrintWriter(new BufferedWriter(new FileWriter("rsc\\"
						+ testName + ".txt")));
			out.println("Solutions for : " + testName + "\n");
			for (int i = 0; i < sol.length; i++) {
				out.println(sol[i]);
			}
			out.flush();
			out.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static Occurrence[] solveAndLog(
		final Class testClass,
		final String path,
		final String ilmName,
		final Rule role) {

		Occurrence[] solutions = new Occurrence[0];
		ICodeLevelModel codeLevelModel = null;
		try {
			Primitive.Logger.setStart(System.currentTimeMillis());

			codeLevelModel =
				Factory.getInstance().createCodeLevelModel(ilmName);
			try {
				codeLevelModel.create(new CompleteClassFileCreator(
					ConstituentRepository.getInstance(DefaultFileRepository
						.getInstance(ConstituentRepository.class)),
					new String[] { path }));
			}
			catch (final CreationException e) {
				e.printStackTrace();
			}
			Primitive.Logger.setILMCreation(System.currentTimeMillis());

			Problem problem = null;
			if (role != null) {
				Primitive.Logger.setRole(role);
				final ReducedDomainBuilder rdg =
					new ReducedDomainBuilder(codeLevelModel);
				problem =
					(Problem) testClass
						.getMethod(
							"getProblem",
							new Class[] { List.class, List.class })
						.invoke(
							null,
							new Object[] {
									Manager.build(codeLevelModel),
									Manager.build(rdg
										.computeReducedDomain(role)) });

			}
			else {
				problem =
					(Problem) testClass.getMethod(
						"getProblem",
						new Class[] { List.class }).invoke(
						null,
						new Object[] { Manager.build(codeLevelModel) });
			}

			Primitive.Logger.setProblemCreation(System.currentTimeMillis());

			IntVar[] vars = problem.getVars();
			Primitive.Logger.setDomainSize(problem.getAllEntities().size());
			for (int i = 0; i < vars.length; i++) {
				Primitive.Logger
					.addVar(
						vars[i].toString(),
						vars[i].getDomainSize(),
						(((double) (problem.getAllEntities().size() - (double) vars[i]
							.getDomainSize()) / (double) problem
							.getAllEntities()
							.size()) * 100.0));
			}

			// GC
			for (int i = 0; i < 5; i++) {
				System.gc();
			}

			final StringWriter writer = new StringWriter();
			problem.setWriter(new PrintWriter(writer));
			problem.combinatorialAutomaticSolve(true);

			final Properties properties = new Properties();

			properties.load(new ReaderInputStream(new StringReader(writer
				.getBuffer()
				.toString())));

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
		catch (final IllegalAccessException e) {
			e.printStackTrace();
		}
		catch (final InvocationTargetException e) {
			e.printStackTrace();
		}
		catch (final NoSuchMethodException e) {
			e.printStackTrace();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}

		Primitive.Logger.setEndProcess(System.currentTimeMillis());
		Primitive.Logger.setNumSolutions(solutions.length);
		Primitive.Logger.setGhostedSolutions(countGhostSolution(
			solutions,
			codeLevelModel));

		if (role == null) {
			Primitive.Logger.print();
		}

		return solutions;
	}
	public Primitive(final String name) {
		super(name);
	}
}
