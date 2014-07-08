/* (c) Copyright 2001 and following years, Yann-Gaël Guéhéneuc,
 * University of Montreal.
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
package padl.pagerank;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import padl.kernel.IIdiomLevelModel;
import padl.pagerank.utils.InputDataGeneratorWithoutMembersAndGhostsAnd3Relations;
import padl.visitor.IGenerator;
import util.io.ProxyConsole;
import util.io.ProxyDisk;
import util.process.CallerHelper;

public class PageRankRankingGenerator {
	private static PageRankRankingGenerator UniqueInstance;
	public static PageRankRankingGenerator getInstance() {
		if (PageRankRankingGenerator.UniqueInstance == null) {
			PageRankRankingGenerator.UniqueInstance =
				new PageRankRankingGenerator();
		}
		return PageRankRankingGenerator.UniqueInstance;
	}
	private PageRankRankingGenerator() {
	}
	public void compareModels(
		final IIdiomLevelModel anIdiomLevelModel1,
		final IIdiomLevelModel anIdiomLevelModel2,
		final IGenerator aGenerator) {

		try {
			aGenerator.reset();
			final String model1 = anIdiomLevelModel1.generate(aGenerator);
			final String outputFileName1 =
			// "C:/Temp/File1";
			// ProxyDisk.getInstance().fileTempString();
				anIdiomLevelModel1.getDisplayName();
			final Writer writer1 =
				ProxyDisk
					.getInstance()
					.fileTempOutput(outputFileName1 + ".txt");
			writer1.write(model1);
			writer1.close();

			aGenerator.reset();
			final String model2 = anIdiomLevelModel2.generate(aGenerator);
			final String outputFileName2 =
			// "C:/Temp/File2";
			// ProxyDisk.getInstance().fileTempString();
				anIdiomLevelModel2.getDisplayName();
			final Writer writer2 =
				ProxyDisk
					.getInstance()
					.fileTempOutput(outputFileName2 + ".txt");
			writer2.write(model2);
			writer2.close();

			final String outputDirectory =
			// "C:/Temp";
				"\""
						+ ProxyDisk
							.getInstance()
							.directoryTempOutput(
								outputFileName1 + '-' + outputFileName2)
							.getAbsolutePath() + '"';

			CallerHelper
				.execute(
					"padl.pagerank.PageRankRankingGenerator.compareModels(IIdiomLevelModel, IIdiomLevelModel, IGenerator)",
					("\"../PADL Generator PageRank/BenchMatch/BenchMatch.exe\" -h 12 -o \""
							+ ProxyDisk.getInstance().fileTempInputString(
								outputFileName2 + ".txt")
							+ "\" -n \""
							+ ProxyDisk.getInstance().fileTempInputString(
								outputFileName2 + ".txt") + "\" -p " + outputDirectory)
						.replace('\\', '/'));
		}
		catch (final IOException e) {
			e.printStackTrace();
		}
	}
	public void computeRanking(
		final IIdiomLevelModel anIdiomLevelModel,
		final String anOutputFileName) {

		try {
			this.generateModel(
				anIdiomLevelModel,
				anOutputFileName,
				new InputDataGeneratorWithoutMembersAndGhostsAnd3Relations());

			final Runtime run = Runtime.getRuntime();
			final Process process =
				run
					.exec("../PADL Generator PageRank/ClassRank/ClassRank.exe -i \""
							+ anOutputFileName
							+ ".txt\" -o \""
							+ anOutputFileName + ".csv\"");

			final InputStream stderr = process.getErrorStream();
			final InputStreamReader isr = new InputStreamReader(stderr);
			final BufferedReader br = new BufferedReader(isr);
			System.out
				.println("\nIf any error occurred, it will be printed out between the two following tags:");
			System.out.println("<ERROR>");
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			System.out.println("</ERROR>");

			final int exitVal = process.waitFor();
			System.out.println("Process exitValue: " + exitVal);
		}
		catch (final IOException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
		catch (final InterruptedException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
	}
	public void computeRanking(
		final IIdiomLevelModel anIdiomLevelModel,
		final String aProgramName,
		final String anOutputDirName) {

		this.computeRanking(anIdiomLevelModel, anOutputDirName + aProgramName);
	}
	public void generateModel(
		final IIdiomLevelModel aModel,
		final String anOutputFileName,
		final IGenerator aGenerator) {

		aModel.generate(aGenerator);
		try {
			final String outputFile = anOutputFileName + ".txt";
			final Writer fw =
				ProxyDisk.getInstance().fileAbsoluteOutput(outputFile);
			final BufferedWriter out = new BufferedWriter(fw);
			out.write(aGenerator.getCode());
			out.close();
		}
		catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
