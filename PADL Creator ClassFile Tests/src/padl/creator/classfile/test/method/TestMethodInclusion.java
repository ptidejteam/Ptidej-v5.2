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
import padl.creator.classfile.LightClassFileCreator;
import padl.kernel.IClass;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IOperation;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;

/**
 * Test for methods wrongly included in an IClass
 * after being parsed by the PADL Creator ClassFile.
 * 
 * @author Stephane Vaucher
 * @author Yann-Gaël Guéhéneuc
 * @since  2005/11/11
 */
public class TestMethodInclusion extends TestCase {
	private static final char[] CLASS_ID = "padl.test.method.B".toCharArray();
	private static final char[] METHOD_ID = "foo".toCharArray();

	public TestMethodInclusion(final String aName) {
		super(aName);
	}
	public void setUp() throws Exception {
	}
	public void testInclusionCompiledIn1p3() throws CreationException {
		final ICodeLevelModel codeLevelModel =
			Factory.getInstance().createCodeLevelModel("");
		codeLevelModel
			.create(new LightClassFileCreator(
				new String[] { "../PADL Creator ClassFile Tests/rsc/CompiledIn1.4with1.1classfile/padl/test/method/B.class" }));

		final boolean elementsExist =
			((IClass) codeLevelModel
				.getTopLevelEntityFromID(TestMethodInclusion.CLASS_ID))
				.getNumberOfConstituents() > 0;
		Assert.assertTrue("Elements exist", elementsExist);

		final IOperation method =
			(IOperation) ((IClass) codeLevelModel
				.getTopLevelEntityFromID(TestMethodInclusion.CLASS_ID))
				.getConstituentFromName(TestMethodInclusion.METHOD_ID);

		Assert
			.assertNull(
				"Method '"
						+ String.valueOf(METHOD_ID)
						+ "' of class  '"
						+ String.valueOf(CLASS_ID)
						+ "' is included through the inheritance mecanism wrongly.",
				method);
	}
	public void testInclusionCompiledIn1p4() throws CreationException {
		final ICodeLevelModel codeLevelModel =
			Factory.getInstance().createCodeLevelModel("");
		codeLevelModel
			.create(new LightClassFileCreator(
				new String[] { "../PADL Creator ClassFile Tests/rsc/CompiledIn1.4/padl/test/method/B.class" }));

		final boolean elementsExist =
			((IClass) codeLevelModel
				.getTopLevelEntityFromID(TestMethodInclusion.CLASS_ID))
				.getNumberOfConstituents() > 0;
		Assert.assertTrue("Elements exist", elementsExist);

		final IOperation method =
			(IOperation) ((IClass) codeLevelModel
				.getTopLevelEntityFromID(TestMethodInclusion.CLASS_ID))
				.getConstituentFromName(TestMethodInclusion.METHOD_ID);

		Assert
			.assertNull(
				"Method '"
						+ String.valueOf(METHOD_ID)
						+ "' of class  '"
						+ String.valueOf(CLASS_ID)
						+ "' is included through the inheritance mecanism wrongly.",
				method);
	}
	public void testInclusionCompiledIn1p4with1p1classfile()
			throws CreationException {

		final ICodeLevelModel codeLevelModel =
			Factory.getInstance().createCodeLevelModel("");
		codeLevelModel
			.create(new LightClassFileCreator(
				new String[] { "../PADL Creator ClassFile Tests/rsc/CompiledIn1.4with1.1classfile/padl/test/method/B.class" }));

		final boolean elementsExist =
			((IClass) codeLevelModel
				.getTopLevelEntityFromID(TestMethodInclusion.CLASS_ID))
				.getNumberOfConstituents() > 0;
		Assert.assertTrue("Elements exist", elementsExist);

		final IOperation method =
			(IOperation) ((IClass) codeLevelModel
				.getTopLevelEntityFromID(TestMethodInclusion.CLASS_ID))
				.getConstituentFromName(TestMethodInclusion.METHOD_ID);

		Assert
			.assertNull(
				"Method '"
						+ String.valueOf(METHOD_ID)
						+ "' of class  '"
						+ String.valueOf(CLASS_ID)
						+ "' is included through the inheritance mecanism wrongly.",
				method);
	}
	public void testInclusionCompiledIn1p4with1p2classfile()
			throws CreationException {

		final ICodeLevelModel codeLevelModel =
			Factory.getInstance().createCodeLevelModel("");
		codeLevelModel
			.create(new LightClassFileCreator(
				new String[] { "../PADL Creator ClassFile Tests/rsc/CompiledIn1.4with1.2classfile/padl/test/method/B.class" }));

		final boolean elementsExist =
			((IClass) codeLevelModel
				.getTopLevelEntityFromID(TestMethodInclusion.CLASS_ID))
				.getNumberOfConstituents() > 0;
		Assert.assertTrue("Elements exist", elementsExist);

		final IOperation method =
			(IOperation) ((IClass) codeLevelModel
				.getTopLevelEntityFromID(TestMethodInclusion.CLASS_ID))
				.getConstituentFromName(TestMethodInclusion.METHOD_ID);

		Assert
			.assertNull(
				"Method '"
						+ String.valueOf(METHOD_ID)
						+ "' of class  '"
						+ String.valueOf(CLASS_ID)
						+ "' is included through the inheritance mecanism wrongly.",
				method);
	}
	public void testInclusionCompiledIn1p4with1p3classfile()
			throws CreationException {

		final ICodeLevelModel codeLevelModel =
			Factory.getInstance().createCodeLevelModel("");
		codeLevelModel
			.create(new LightClassFileCreator(
				new String[] { "../PADL Creator ClassFile Tests/rsc/CompiledIn1.4with1.3classfile/padl/test/method/B.class" }));

		final boolean elementsExist =
			((IClass) codeLevelModel
				.getTopLevelEntityFromID(TestMethodInclusion.CLASS_ID))
				.getNumberOfConstituents() > 0;
		Assert.assertTrue("Elements exist", elementsExist);

		final IOperation method =
			(IOperation) ((IClass) codeLevelModel
				.getTopLevelEntityFromID(TestMethodInclusion.CLASS_ID))
				.getConstituentFromName(TestMethodInclusion.METHOD_ID);

		Assert
			.assertNull(
				"Method '"
						+ String.valueOf(METHOD_ID)
						+ "' of class  '"
						+ String.valueOf(CLASS_ID)
						+ "' is included through the inheritance mecanism wrongly.",
				method);
	}
}
