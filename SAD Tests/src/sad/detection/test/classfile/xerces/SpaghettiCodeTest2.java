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
package sad.detection.test.classfile.xerces;

import java.io.IOException;
import java.io.PrintWriter;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.generator.helper.ModelGenerator;
import padl.kernel.IIdiomLevelModel;
import sad.designsmell.detection.IDesignSmellDetection;
import sad.designsmell.detection.repository.SpaghettiCode.SpaghettiCodeDetection;
import util.io.ProxyDisk;

/**
 * @author Yann
 * @since 2010/02/10
 */
public final class SpaghettiCodeTest2 extends TestCase {
	private static IIdiomLevelModel IdiomLevelModel;
	private static final String NAME = "Xercesv2.7.0.jar";
	private static final String PATH = "../SAD Tests/data/Xercesv2.7.0.jar";

	public SpaghettiCodeTest2(final String name) {
		super(name);
	}
	protected void setUp() throws Exception {
		if (SpaghettiCodeTest2.IdiomLevelModel == null) {
			SpaghettiCodeTest2.IdiomLevelModel =
				ModelGenerator
					.generateModelFromClassFilesDirectories(new String[] { SpaghettiCodeTest2.PATH });
		}
	}
	public void testSpaghettiCode() throws IOException {
		final IDesignSmellDetection ad = new SpaghettiCodeDetection();
		ad.detect(SpaghettiCodeTest2.IdiomLevelModel);
		ad.output(new PrintWriter(ProxyDisk.getInstance().fileTempOutput(
			SpaghettiCodeTest2.NAME + "_SpaghettiCode.ini")));
		Assert.assertEquals("Incorrect number of spaghetti code found", 1, ad
			.getDesignSmells()
			.size());
	}
}