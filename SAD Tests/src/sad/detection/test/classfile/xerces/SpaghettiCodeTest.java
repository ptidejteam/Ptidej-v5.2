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

import java.io.PrintWriter;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.generator.helper.ModelGenerator;
import padl.kernel.IIdiomLevelModel;
import sad.codesmell.detection.ICodeSmellDetection;
import sad.codesmell.detection.repository.SpaghettiCode.LongMethodDetection;
import sad.codesmell.detection.repository.SpaghettiCode.MethodNoParameterDetection;
import sad.designsmell.detection.IDesignSmellDetection;
import sad.designsmell.detection.repository.SpaghettiCode.SpaghettiCodeDetection;
import util.io.ProxyDisk;

/**
 * @author Naouel Moha
 * @since 2005/12/04
 */
public final class SpaghettiCodeTest extends TestCase {
	private static IIdiomLevelModel IdiomLevelModel;
	private static final String NAME = "Xercesv2.7.0.jar";
	private static final String PATH = "../SAD Tests/data/Xercesv2.7.0.jar";

	public SpaghettiCodeTest(final String name) {
		super(name);
	}
	protected void setUp() throws Exception {
		if (SpaghettiCodeTest.IdiomLevelModel == null) {
			SpaghettiCodeTest.IdiomLevelModel =
				ModelGenerator
					.generateModelFromClassFilesDirectories(new String[] { SpaghettiCodeTest.PATH });
		}
	}
	public void testLongMethod() {
		final ICodeSmellDetection ad = new LongMethodDetection();
		ad.detect(SpaghettiCodeTest.IdiomLevelModel);
		ad.output(new PrintWriter(ProxyDisk.getInstance().fileTempOutput(
			SpaghettiCodeTest.NAME + "_LongMethod.ini")));
		Assert.assertEquals("Incorrect number of long methods found", 67, ad
			.getCodeSmells()
			.size());
	}
	public void testMethodWithNoParameter() {
		final ICodeSmellDetection ad = new MethodNoParameterDetection();
		ad.detect(SpaghettiCodeTest.IdiomLevelModel);
		ad.output(new PrintWriter(ProxyDisk.getInstance().fileTempOutput(
			SpaghettiCodeTest.NAME + "_MethodWithNoParameter.ini")));
		Assert.assertEquals(
			"Incorrect number of methods with no parameter found",
			520,
			ad.getCodeSmells().size());
	}
	public void testSpaghettiCode() {
		final IDesignSmellDetection ad = new SpaghettiCodeDetection();
		ad.detect(SpaghettiCodeTest.IdiomLevelModel);
		ad.output(new PrintWriter(ProxyDisk.getInstance().fileTempOutput(
			SpaghettiCodeTest.NAME + "_SpaghettiCode.ini")));
		Assert.assertEquals("Incorrect number of spaghetti code found", 1, ad
			.getDesignSmells()
			.size());
	}
}