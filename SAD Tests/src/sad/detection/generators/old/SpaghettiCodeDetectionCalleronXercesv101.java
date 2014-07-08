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
package sad.detection.generators.old;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import padl.analysis.UnsupportedSourceModelException;
import padl.analysis.repository.AACRelationshipsAnalysis;
import padl.analysis.repository.ModelAnnotatorLOCAnalysis;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IIdiomLevelModel;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import sad.designsmell.detection.IDesignSmellDetection;
import sad.designsmell.detection.repository.SpaghettiCode.SpaghettiCodeDetection;
import util.io.ProxyConsole;
import util.io.ProxyDisk;

/**
 * @author Naouel Moha
 * @since  2006/02/03
 */
public class SpaghettiCodeDetectionCalleronXercesv101 {
	public static void main(final String[] args) {
		final ICodeLevelModel codeLevelModel =
			Factory
				.getInstance()
				.createCodeLevelModel("Model of Xerces v1.0.1");
		try {
			codeLevelModel
				.create(new CompleteClassFileCreator(
					new String[] { "../SAD Tests/rsc/applications/Xercesv1.0.1.jar" },
					true));
		}
		catch (final CreationException e) {
			e.printStackTrace();
		}

		try {
			final IIdiomLevelModel idiomLevelModel =
				(IIdiomLevelModel) new AACRelationshipsAnalysis()
					.invoke(codeLevelModel);

			final ModelAnnotatorLOCAnalysis annotator =
				new ModelAnnotatorLOCAnalysis();
			annotator.annotateFromJARs(
				new String[] { "rsc/applications/Xercesv1.0.1.jar" },
				idiomLevelModel);

			final IDesignSmellDetection ad1 = new SpaghettiCodeDetection();
			((SpaghettiCodeDetection) ad1)
				.output(new PrintWriter(
					ProxyDisk
						.getInstance()
						.fileTempOutput(
							"rsc/060310 Yann/CandidateSpaghettiCodeClassesXerces1.0.1.ini")));

			final PrintWriter outFile =
				new PrintWriter(
					ProxyDisk
						.getInstance()
						.fileTempOutput(
							"rsc/060310 Yann/CandidateSpaghettiCodeClassesXerces1.0.1.stats.txt"));
			outFile.println();
			outFile.println("###### Statistics #####");
			final Date today = new Date();
			final SimpleDateFormat formatter =
				new SimpleDateFormat("yyyy'/'MM'/'dd' Heure ' hh':'mm':'ss");
			final String timeStamp = formatter.format(today);
			outFile.println(timeStamp);
			//			outFile.print("Number of Poltergeists found: ");
			//			outFile.println(ad3.getSetOfAntiPatterns().size());
			outFile.print("Number of SpaghettiCodes found: ");
			outFile.println(ad1.getDesignSmells().size());
			outFile.close();
			
			// TODO Does this test do anything?!
		}
		catch (final UnsupportedSourceModelException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
	}
}
