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
package sad.detection.test.comparison.xerces;

import java.io.PrintWriter;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.generator.helper.ModelGenerator;
import padl.kernel.IIdiomLevelModel;
import sad.designsmell.detection.IDesignSmellDetection;
import sad.designsmell.detection.repository.Blob.BlobDetection;
import util.io.ProxyDisk;

/**
 * @author Yann-Gaël Guéhéneuc
 * @author Naouel Moha
 * @author Aminata Sabané
 * @since  2005/12/04
 */
public final class BlobTest extends TestCase {
	private static IIdiomLevelModel IdiomLevelModelFromJavaFiles;
	private static IIdiomLevelModel IdiomLevelModelFromClassFiles;

	public BlobTest(final String name) {
		super(name);
	}

	//	public void testCompareModels() {
	//		BlobTest.IdiomLevelModelFromClassFiles.walk(new ModelComparator(
	//			BlobTest.IdiomLevelModelFromJavaFiles));
	//	}
	private void blobDetection(
		final IIdiomLevelModel anIdiomLevelModel,
		final int theExpectedNumberOfBlobs) {

		final IDesignSmellDetection ad = new BlobDetection();
		// TODO Necessary?
		//	ad.setMetricsFileRepository(ClassFileRepository
		//		.getInstance(MetricRepository.class));
		ad.detect(anIdiomLevelModel);
		((BlobDetection) ad).output(new PrintWriter(ProxyDisk
			.getInstance()
			.fileTempOutput("Xerces v1.0.1_Blob.ini")));
		Assert.assertEquals("Number of Blobs", theExpectedNumberOfBlobs, ad
			.getDesignSmells()
			.size());
	}

	protected void setUp() throws Exception {
		if (BlobTest.IdiomLevelModelFromClassFiles == null
				|| BlobTest.IdiomLevelModelFromJavaFiles == null) {

			BlobTest.IdiomLevelModelFromClassFiles =
				ModelGenerator
					.generateModelFromClassFilesDirectories(new String[] { "../SAD Tests/data/Xercesv1.0.1.jar" });
			BlobTest.IdiomLevelModelFromJavaFiles =
				ModelGenerator
					.generateModelFromJavaFilesDirectoriesUsingEclipse("../SAD Tests/data/Xercesv1.0.1/src/");
		}
	}
	public void testBlobDetectionInClassFiles() {
		this.blobDetection(BlobTest.IdiomLevelModelFromClassFiles, 9);
	}
	public void testBlobDetectionInJavaFiles() {
		this.blobDetection(BlobTest.IdiomLevelModelFromJavaFiles, 6);
	}
}