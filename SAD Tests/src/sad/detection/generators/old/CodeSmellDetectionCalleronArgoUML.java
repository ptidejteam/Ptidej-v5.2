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
package sad.detection.generators.old;

import java.io.PrintWriter;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.analysis.repository.AACRelationshipsAnalysis;
import padl.analysis.repository.ModelAnnotatorLOCAnalysis;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IIdiomLevelModel;
import padl.kernel.impl.Factory;
import sad.designsmell.detection.IDesignSmellDetection;
import sad.designsmell.detection.repository.Blob.BlobDetection;
import sad.designsmell.detection.repository.FunctionalDecomposition.FunctionalDecompositionDetection;
import sad.designsmell.detection.repository.SpaghettiCode.SpaghettiCodeDetection;
import sad.designsmell.detection.repository.SwissArmyKnife.SwissArmyKnifeDetection;
import util.io.ProxyDisk;

/**
 * @author Naouel Moha
 * @since  2005/12/04
 */
public final class CodeSmellDetectionCalleronArgoUML extends TestCase {
	private static IIdiomLevelModel IdiomLevelModel;
	private static ModelAnnotatorLOCAnalysis Annotator;
	//	private static MetricRepository Metrics;
	private static final String PATH = "rsc/applications/ArgoUMLv0.18.1.jar";
	private static final String MAIN_PATH = "rsc/applications/";
	private static final String ProgramName =
		CodeSmellDetectionCalleronArgoUML.PATH
			.substring(CodeSmellDetectionCalleronArgoUML.MAIN_PATH.length());

	public CodeSmellDetectionCalleronArgoUML(final String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		if (CodeSmellDetectionCalleronArgoUML.IdiomLevelModel == null) {
			final ICodeLevelModel codeLevelModel =
				Factory.getInstance().createCodeLevelModel(
					CodeSmellDetectionCalleronArgoUML.PATH);
			codeLevelModel.create(new CompleteClassFileCreator(
				new String[] { CodeSmellDetectionCalleronArgoUML.PATH },
				true));
			CodeSmellDetectionCalleronArgoUML.IdiomLevelModel =
				(IIdiomLevelModel) new AACRelationshipsAnalysis()
					.invoke(codeLevelModel);
			//	DetectionAntipatternArgoUML018Test.Metrics = MetricRepository
			//		.getMetricRepository(DetectionAntipatternArgoUML018Test.IdiomLevelModel);
			CodeSmellDetectionCalleronArgoUML.Annotator =
				new ModelAnnotatorLOCAnalysis();
			CodeSmellDetectionCalleronArgoUML.Annotator.annotateFromJARs(
				new String[] { CodeSmellDetectionCalleronArgoUML.PATH },
				CodeSmellDetectionCalleronArgoUML.IdiomLevelModel);
		}
	}

	/** 
	 *  Test of the detection of Blobs
	 */
	public void testDetectBlobs() {
		IDesignSmellDetection ad;

		ad = new BlobDetection();
		((BlobDetection) ad).output(new PrintWriter(ProxyDisk
			.getInstance()
			.fileTempOutput(
				"rsc/060224_Yann/"
						+ CodeSmellDetectionCalleronArgoUML.ProgramName
						+ "_Blob.ini")));

		ad = new FunctionalDecompositionDetection();
		((FunctionalDecompositionDetection) ad).output(new PrintWriter(
			ProxyDisk.getInstance().fileTempOutput(
				"rsc/060224_Yann/"
						+ CodeSmellDetectionCalleronArgoUML.ProgramName
						+ "_FunctionalDecomposition.ini")));

		ad = new SpaghettiCodeDetection();
		((SpaghettiCodeDetection) ad).output(new PrintWriter(ProxyDisk
			.getInstance()
			.fileTempOutput(
				"rsc/060224_Yann/"
						+ CodeSmellDetectionCalleronArgoUML.ProgramName
						+ "_SpaghettiCodeDetection.ini")));

		ad = new SwissArmyKnifeDetection();
		((SwissArmyKnifeDetection) ad).output(new PrintWriter(ProxyDisk
			.getInstance()
			.fileTempOutput(
				"rsc/060224_Yann/"
						+ CodeSmellDetectionCalleronArgoUML.ProgramName
						+ "_SwissArmyKnifeDetection.ini")));
		
		// TODO This test does not do anything!?

		Assert.assertTrue(true);
	}
}