/*
 * (c) Copyright 2001-2005 Yann-Gaël Guéhéneuc,
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
package padl.creator.classfile.test.method;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.analysis.repository.AACRelationshipsAnalysis;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.IClass;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IIdiomLevelModel;
import padl.kernel.impl.Factory;

/**
 * Test for methods wrongly included in an IClass
 * after being parsed by the PADL Creator ClassFile.
 * 
 * @author Stephane Vaucher
 * @author Yann-Gaël Guéhéneuc
 * @since  2005/11/11
 */
public class TestPrivateConstructor extends TestCase {
	private static IIdiomLevelModel IdiomLevelModel;
	public TestPrivateConstructor(final String aName) {
		super(aName);
	}
	public void setUp() throws Exception {
		if (TestPrivateConstructor.IdiomLevelModel == null) {
			final ICodeLevelModel codeLevelModel =
				Factory.getInstance().createCodeLevelModel("");
			codeLevelModel
				.create(new CompleteClassFileCreator(
					new String[] { "../PADL Creator ClassFile Tests/rsc/RestrictedCreation.class" }));

			TestPrivateConstructor.IdiomLevelModel =
				(IIdiomLevelModel) new AACRelationshipsAnalysis()
					.invoke(codeLevelModel);
		}
	}
	public void testNumberOfEntities() {
		Assert.assertEquals(
			"Number of entities",
			2,
			TestPrivateConstructor.IdiomLevelModel
				.getNumberOfTopLevelEntities());
	}
	public void testElementsOfRestrictedCreation() {
		final IClass clazz =
			(IClass) TestPrivateConstructor.IdiomLevelModel
				.getTopLevelEntityFromID("RestrictedCreation");
		Assert.assertEquals(
			"Number of elements",
			5,
			clazz.getNumberOfConstituents());
		Assert.assertNotNull(
			"Constructor",
			clazz.getConstituentFromID("<init>()"));
	}
}
