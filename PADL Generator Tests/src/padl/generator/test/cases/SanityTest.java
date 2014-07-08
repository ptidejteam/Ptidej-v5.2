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
package padl.generator.test.cases;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.generator.helper.ModelGenerator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IIdiomLevelModel;

public class SanityTest extends TestCase {
	public SanityTest(final String aName) {
		super(aName);
	}
	public void testGenerateModelFromAOLCodeFiles() {
		final ICodeLevelModel codeLevelModel =
			ModelGenerator
				.generateModelFromAOLCodeFiles(
					"Model",
					new String[] { "../PADL Generator Tests/rsc/test/AOL/Test1.aol" });
		Assert.assertEquals(
			"Number of classes",
			18,
			codeLevelModel.getNumberOfTopLevelEntities());
	}
	public void testGenerateModelFromCppFilesUsingEclipse() {
		final IIdiomLevelModel idiomLevelModel =
			ModelGenerator.generateModelFromCppFilesUsingEclipse(
				"Model",
				new String[] { "../PADL Generator Tests/rsc/test/C++/" });
		Assert.assertEquals(
			"Number of classes",
			9,
			idiomLevelModel.getNumberOfTopLevelEntities());
	}
}
