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
package padl.creator.classfile.test.path;

import junit.framework.Assert;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.creator.classfile.test.ClassFilePrimitive;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IFirstClassEntity;
import padl.kernel.exception.CreationException;
import padl.path.Finder;
import padl.path.FormatException;
import padl.path.IConstants;
import padl.util.Util;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2006/02/08
 */
public class TestPathArgoUML extends ClassFilePrimitive {
	private static IFirstClassEntity[] FirstClassEntities = null;
	private static ICodeLevelModel Model = null;

	public TestPathArgoUML(final String aName) {
		super(aName);
	}
	protected void setUp() throws CreationException {
		if (TestPathArgoUML.Model == null) {
			TestPathArgoUML.Model =
				ClassFilePrimitive.getFactory().createCodeLevelModel("ArgoUML");
			TestPathArgoUML.Model
				.create(new CompleteClassFileCreator(
					new String[] { "../PADL Creator ClassFile Tests/rsc/ArgoUML-0.15.6.jar" }));

			TestPathArgoUML.FirstClassEntities =
				Util.getArrayOfTopLevelEntities(TestPathArgoUML.Model);
		}
	}
	public void testPath1() {
		for (int i = 0; i < TestPathArgoUML.FirstClassEntities.length; i++) {
			Assert.assertEquals(
				TestPathArgoUML.FirstClassEntities[i].getDisplayID(),
				TestPathArgoUML.FirstClassEntities[i]
					.getDisplayPath()
					.substring(
						TestPathArgoUML.FirstClassEntities[i]
							.getDisplayPath()
							.lastIndexOf(IConstants.ENTITY_SYMBOL) + 1));
		}
	}
	public void testPath2() {
		try {
			Finder.find("/ArgoUML", TestPathArgoUML.Model);
			Assert.fail();
		}
		catch (final FormatException e) {
		}
	}
	public void testPath3() {
		try {
			Assert.assertNull(Finder.find(
				"/ArgoUML|Dummy",
				TestPathArgoUML.Model));
			Assert.fail("Dummy should not exist!");
		}
		catch (final FormatException e) {
		}
	}
	public void testPath4() {
		try {
			Assert
				.assertNotNull(Finder
					.find(
						"/ArgoUML|org|argouml|pattern|cognitive|critics|org.argouml.pattern.cognitive.critics.CrConsiderSingleton",
						TestPathArgoUML.Model));
		}
		catch (final FormatException e) {
			Assert.fail(e.getMessage());
			throw new RuntimeException(e);
		}
	}
	public void testPath5() {
		try {
			Assert.assertNotNull(Finder.find(
				"/ArgoUML|org|argouml|ui|org.argouml.ui.ActionExportXMI",
				TestPathArgoUML.Model));
		}
		catch (final FormatException e) {
			Assert.fail(e.getMessage());
		}
	}
	public void testPath6() {
		try {
			Assert
				.assertNotNull(Finder
					.find(
						"/ArgoUML|org|argouml|ui|org.argouml.ui.ActionExportXMI|actionPerformed(java.awt.event.ActionEvent)",
						TestPathArgoUML.Model));
		}
		catch (final FormatException e) {
			Assert.fail(e.getMessage());
		}
	}
	public void testPath7() {
		try {
			Assert
				.assertNotNull(Finder
					.find(
						"/ArgoUML|org|argouml|ui|org.argouml.ui.DnDNavigatorTree$ArgoDropTargetListener",
						TestPathArgoUML.Model));
		}
		catch (final FormatException e) {
			Assert.fail(e.getMessage());
		}
	}
	public void testPath8() {
		try {
			Assert
				.assertNotNull(Finder
					.find(
						"/ArgoUML|org|argouml|ui|org.argouml.ui.DnDNavigatorTree$ArgoDropTargetListener|isValidDropTarget(javax.swing.tree.TreePath, javax.swing.tree.TreePath)",
						TestPathArgoUML.Model));
		}
		catch (final FormatException e) {
			Assert.fail(e.getMessage());
		}
	}
}
