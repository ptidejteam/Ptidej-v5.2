/*
 * (c) Copyright 2001-2004 Jean-Yves Guyomarc'h,
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
package ptidej.solver.test.java.fingerprint.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import padl.creator.CompleteClassFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IEntity;
import padl.kernel.IGhost;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import padl.util.repository.constituent.ConstituentRepository;
import padl.util.repository.file.DefaultFileRepository;
import ptidej.solver.Occurrence;
import ptidej.solver.OccurrenceBuilder;
import ptidej.solver.OccurrenceComparator;
import ptidej.solver.OccurrenceComponent;
import ptidej.solver.Problem;
import ptidej.solver.domain.Manager;
import ptidej.solver.fingerprint.ReducedDomainBuilder;
import util.io.ReaderInputStream;
import choco.integer.IntVar;
import choco.integer.var.IntDomain;
import choco.util.IntIterator;

/**
 * @author Jean-Yves Guyomarc'h
 */
public abstract class TestSolver {
	protected static final String FingerprintPackageName =
		"ptidej.solver.fingerprint.problem.";
	protected static final String NoFingerprintPackageName =
		"ptidej.solver.problem.";

	private List allEntities;
	private ICodeLevelModel codeLevelModel;
	private Logger logger;
	private String motifName;
	private String[] packageNames;
	private String path;
	private ReducedDomainBuilder rdb;
	private String testName;

	public TestSolver(
		final String path,
		final String[] packageNames,
		final String testName,
		final String motifName) {

		this.path = path;
		this.packageNames = packageNames;
		this.testName = testName;
		this.motifName = motifName;
		this.logger = new Logger(this.testName);
	}
	private void checkVariables(final IntVar[] vars, final Problem problem) {
		for (int i = 0; i < vars.length; i++) {
			logger.addVar(
				vars[i].toString(),
				vars[i].getDomainSize(),
				(((double) (problem.getAllEntities().size() - (double) vars[i]
					.getDomainSize()) / (double) problem
					.getAllEntities()
					.size()) * 100.0));
		}
	}
	public void computeDomainReduction(final PrintWriter output) {
		try {
			this.createModel();

			// Get Problem
			output.print("Domain reduction for ");
			output.println(this.testName);
			final Problem problem =
				(Problem) this
					.getMotif(Logger.WITH_RULES)
					.getMethod(
						"getProblem",
						new Class[] { List.class, ReducedDomainBuilder.class })
					.invoke(null, new Object[] { this.allEntities, this.rdb });

			output.print("Original domain size: ");
			output.println(problem.getAllEntities().size());

			final IntVar[] vars = problem.getVars();
			for (int i = 0; i < vars.length; i++) {
				output.print("Reduced domain size for ");
				output.print(vars[i].toString());
				output.print(": ");
				output.print(vars[i].getDomainSize());
				output.print(" (");
				output
					.print((((double) (problem.getAllEntities().size() - (double) vars[i]
						.getDomainSize()) / (double) problem
						.getAllEntities()
						.size()) * 100.0));
				output.println("% of reduction)");

				if (vars[i].getDomainSize() > 0
						&& vars[i].getDomainSize() < problem
							.getAllEntities()
							.size()) {

					final IntDomain domain = vars[i].getDomain();
					final IntIterator iterator = domain.getIterator();
					final List allEntities = problem.getAllEntities();
					while (iterator.hasNext()) {
						output.print("\tEntity: ");
						output.println(allEntities.get(iterator.next()));
					}
				}
			}
		}
		catch (final InvocationTargetException e) {
			e.printStackTrace(System.err);
		}
		catch (final IllegalAccessException e) {
			e.printStackTrace(System.err);
		}
		catch (final NoSuchMethodException e) {
			e.printStackTrace(System.err);
		}
	}
	public void computeSolutions() {
		final Occurrence[] solutionsWithRules = computeSolutionsWithRules();
		final Occurrence[] solutionWithoutRules =
			computeSolutionsWithoutRules();

		this.logger.setIncluded(this.numIncluded(
			solutionsWithRules,
			solutionWithoutRules));

		final Occurrence[] excludedSolutions =
			this.excluded(solutionsWithRules, solutionWithoutRules);
		System.out.println("Number of excluded solutions: "
				+ excludedSolutions.length);
		this.printSolution(excludedSolutions, this.testName
				+ " (excluded solutions)");
		this.logger.print();
	}
	public Occurrence[] computeSolutions(
		final int loggerMode,
		final Class[] problemArgsTypes,
		final Object[] problemArgs) {

		Occurrence[] nonGhostSol = null;
		try {
			//this.createModel();

			// Set Logger
			logger.setMode(loggerMode);
			logger.setStart(System.currentTimeMillis());

			// Get Problem
			final Problem problem =
				(Problem) this.getMotif(loggerMode).getMethod(
					"getProblem",
					problemArgsTypes).invoke(null, problemArgs);
			logger.setProblemCreation(System.currentTimeMillis());

			// Check Vars
			this.checkVariables(problem.getVars(), problem);

			// GC
			for (int i = 0; i < 5; i++) {
				System.gc();
			}

			// Solve
			final Occurrence[] solutions = this.solve(problem);
			logger.setEndProcess(System.currentTimeMillis());
			logger.setNumSolutions(solutions.length);
			nonGhostSol = this.removeGhostSolution(solutions);
			logger.setGhostedSolutions(solutions.length - nonGhostSol.length);

			if (loggerMode == Logger.WITHOUT_RULE) {
				this.printSolution(nonGhostSol, this.testName
						+ " (solutions without rules)");
				this.printSolution(solutions, this.testName
						+ " (all solutions without rules)");
			}
			else {
				this.printSolution(nonGhostSol, this.testName
						+ " (solutions with rules)");
				this.printSolution(solutions, this.testName
						+ " (all solutions with rules)");
			}
		}
		catch (final InvocationTargetException e) {
			e.printStackTrace();
		}
		catch (final IllegalAccessException e) {
			e.printStackTrace();
		}
		catch (final NoSuchMethodException e) {
			e.printStackTrace();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}
		catch (final Throwable t) {
			t.printStackTrace();
		}
		finally {
			return nonGhostSol;
		}
	}
	public Occurrence[] computeSolutionsWithoutRules() {
		this.createModel();
		return this.computeSolutions(
			Logger.WITHOUT_RULE,
			new Class[] { List.class },
			new Object[] { this.allEntities });
	}
	public Occurrence[] computeSolutionsWithRules() {
		this.createModel();
		return this.computeSolutions(Logger.WITH_RULES, new Class[] {
				List.class, ReducedDomainBuilder.class }, new Object[] {
				this.allEntities, this.rdb });
	}
	private void createModel() {
		String[] computePaths = null;

		if (this.packageNames != null) {
			computePaths = new String[packageNames.length];
			for (int i = 0; i < packageNames.length; i++)
				computePaths[i] = this.path + packageNames[i].replace('.', '/');
		}
		else {
			computePaths = new String[1];
			computePaths[0] = this.path;
		}

		this.codeLevelModel =
			Factory.getInstance().createCodeLevelModel(this.testName);

		try {
			this.codeLevelModel.create(new CompleteClassFileCreator(
				ConstituentRepository.getInstance(DefaultFileRepository
					.getInstance(ConstituentRepository.class)),
				computePaths,
				true));
		}
		catch (final CreationException e) {
			e.printStackTrace();
		}

		if (this.packageNames != null) {
			// Yann 2005/10/07: Packages!
			// A model may now contrain entities and packages.
			// Yann 2005/10/12: Iterator!
			// I have now an iterator able to iterate over a
			// specified type of constituent of a list.
			final Iterator entities =
				this.codeLevelModel.getIteratorOnConstituents(IEntity.class);
			while (entities.hasNext()) {
				final IEntity entity = (IEntity) entities.next();
				final String entityName = entity.getName();
				final String packageName =
					entityName.substring(0, entityName.lastIndexOf('.'));
				boolean toBeRemoved = true;
				for (int i = 0; i < this.packageNames.length && toBeRemoved; i++) {
					if (packageName.equals(this.packageNames[i])) {
						toBeRemoved = false;
					}
				}
				if (toBeRemoved) {
					this.codeLevelModel.removeConstituentFromID(entityName);
				}
			}
		}
		this.rdb = new ReducedDomainBuilder(this.codeLevelModel);
		this.allEntities = Manager.build(this.codeLevelModel);
	}
	private Occurrence[] excluded(
		final Occurrence[] included,
		final Occurrence[] includer) {
		final ArrayList excluded = new ArrayList();
		for (int i = 0; i < includer.length; i++) {
			if (!OccurrenceComparator.getInstance().belongsTo(
				includer[i],
				included)) {
				excluded.add(includer[i]);
			}
		}
		final Occurrence[] tmp = new Occurrence[excluded.size()];
		for (int i = 0; i < excluded.size(); i++) {
			tmp[i] = (Occurrence) excluded.get(i);
		}
		return tmp;
	}
	public abstract Class getMotif(final int mode);
	public String getMotifName() {
		return this.motifName;
	}
	public String getProgramName() {
		return this.testName;
	}
	private int numIncluded(
		final Occurrence[] included,
		final Occurrence[] includer) {
		int cpt_included = 0;
		for (int i = 0; i < included.length; i++) {
			if (OccurrenceComparator.getInstance().belongsTo(
				included[i],
				includer)) {
				cpt_included++;
			}
		}
		return cpt_included;
	}
	private void printSolution(final Occurrence[] sol, final String testName) {
		try {
			//	final PrintWriter out =
			//		new PrintWriter(
			//			new BufferedWriter(
			//				new FileWriter("rsc/" + testName + ".txt")));
			//	for (int i = 0; i < sol.length; i++) {
			//		out.println(sol[i]);
			//	}
			//	out.flush();
			//	out.close();
			final PrintWriter out =
				new PrintWriter(new BufferedWriter(new FileWriter("rsc/"
						+ testName + ".txt")));
			Occurrence.print(sol, out);
		}
		catch (final IOException e) {
			e.printStackTrace();
		}
	}
	private Occurrence[] removeGhostSolution(final Occurrence[] solutions) {
		final ArrayList sol = new ArrayList();
		for (int i = 0; i < solutions.length; i++) {
			boolean ghosted = false;
			final Iterator iter = solutions[i].getComponents().iterator();
			while (iter.hasNext()) {
				// TEST IF INSTANCE OF GHOST
				OccurrenceComponent sc = (OccurrenceComponent) iter.next();
				if (this.codeLevelModel.getConstituentFromName(sc.getValue()) instanceof IGhost) {
					ghosted = true;
				}
			}
			if (!ghosted) {
				sol.add(solutions[i]);
			}
		}
		Occurrence[] solTmp = new Occurrence[sol.size()];
		for (int i = 0; i < sol.size(); i++) {
			solTmp[i] = (Occurrence) sol.get(i);
		}

		return solTmp;
	}
	private Occurrence[] solve(final Problem problem) throws IOException {
		// Solve
		final StringWriter writer = new StringWriter();
		problem.setWriter(new PrintWriter(writer));
		problem.automaticSolve(true);
		// problem.combinatorialAutomaticSolve(true);

		final Properties properties = new Properties();
		properties.load(new ReaderInputStream(new StringReader(writer
			.getBuffer()
			.toString())));

		final OccurrenceBuilder solutionBuilder =
			OccurrenceBuilder.getInstance();

		return solutionBuilder.getCanonicalOccurrences(properties);
	}
}
