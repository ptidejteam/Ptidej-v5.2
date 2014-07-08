/* (c) Copyright 2008 and following years, Yann-Gaël Guéhéneuc,
 * École Polytechnique de Montréal.
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
package ptidej.solver.helper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;
import java.util.Properties;
import padl.generator.helper.ModelGenerator;
import padl.kernel.IIdiomLevelModel;
import padl.visitor.IWalker;
import ptidej.solver.Occurrence;
import ptidej.solver.OccurrenceBuilder;
import ptidej.solver.Problem;
import ptidej.solver.domain.Generator;
import ptidej.solver.domain.Manager;
import ptidej.solver.problem.CompositeMotif;
import util.io.ProxyDisk;
import util.io.ReaderInputStream;

/**
 * @author yann
 * @since  2013/09/25
 */
public class DesignMotifIdentificationCallerSimple {
	public static void main(final String[] args) throws FileNotFoundException,
			IOException {

		final String path = "../Ptidej Solver Tests/rsc/JHotDraw v5.2.jar";
		final String name = "JHotDraw v5.2.ini";

		final IIdiomLevelModel idiomLevelModel =
			ModelGenerator.generateModelFromJAR(path);
		final IWalker constraintModelBuilder = new Generator();
		final List listOfModelEntities =
			Manager.build(idiomLevelModel, constraintModelBuilder);
		final Problem constraintProblem =
			CompositeMotif.getProblem(listOfModelEntities);

		final Writer writer = ProxyDisk.getInstance().fileTempOutput(name);
		constraintProblem.setWriter(new PrintWriter(writer));
		constraintProblem.automaticSolve(true);

		final Reader reader = ProxyDisk.getInstance().fileTempInput(name);
		final Properties properties = new Properties();
		properties.load(new ReaderInputStream(reader));
		final OccurrenceBuilder solutionBuilder =
			OccurrenceBuilder.getInstance();
		final Occurrence[] solutions =
			solutionBuilder.getCanonicalOccurrences(properties);

		System.out.print("Found ");
		System.out.print(solutions.length);
		System.out.println(" solutions.");
	}
}
