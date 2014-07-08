/*
 * (c) Copyright 2003-2006 Jean-Yves Guyomarc'h,
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
package padl.creator.aspectj.test;

import java.io.File;
import java.util.HashMap;
import junit.framework.Assert;
import padl.creator.aspectjlst.AspectCreator;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IPackage;
import padl.kernel.exception.CreationException;

public class TestAO extends AspectJPrimitive {

	private HashMap importCreation = null;

	private final String lstAO =
		"../PADL Creator AspectJ Tests/rsc/FigureElement/srcAO/files.lst";

	public TestAO(String aName) {
		super(aName);
	}

	public String getLstPath() {
		return this.lstAO;
	}

	public void setUp() throws CreationException {
		super.setUp();

		// Add AO model

		final AspectCreator ac = new AspectCreator(new String[] { this.lstAO });
		this.getCodeLevelModel().create(ac);
		this.importCreation = ac.getImportMap();
	}

	public void testConstituentCreation() {
		//On package exemple.figureElement.aspect
		Assert
			.assertTrue(
				"Test creation of exemple.figureElement.aspect.DisplayAspect",
				((IPackage) this.getCodeLevelModel().getConstituentFromID(
					AspectCreator.ASPECT_PACKAGE_ID))
					.getConstituentFromID("exemple.figureElement.aspect.DisplayAspect") != null);

		//False test
		Assert
			.assertTrue(
				"Test non creation of exemple.figureElement.aspect.NonExistentAspect",
				((IPackage) this.getCodeLevelModel().getConstituentFromID(
					AspectCreator.ASPECT_PACKAGE_ID))
					.getConstituentFromID("exemple.figureElement.aspect.NonExistentAspect") == null);

	}
	public void testImportCreation() {
		//		import exemple.figureElement.core.Point;
		//		import exemple.figureElement.core.Line;
		//		import exemple.figureElement.core.FigureElement;
		//		import exemple.figureElement.display.DisplayManager;
		//		import exemple.figureElement.display.DisplayableFigure;
		Assert.assertTrue(
			"Test creation of import exemple.figureElement.core.Point",
			(this.importCreation.get("Point") != null));

		Assert.assertTrue(
			"Test mapping of import exemple.figureElement.core.Point",
			(((IFirstClassEntity) (this.importCreation.get("Point")))
				.getDisplayID().equals("exemple.figureElement.core.Point")));

		Assert.assertTrue(
			"Test creation of import exemple.figureElement.core.Line",
			(this.importCreation.get("Line") != null));

		Assert.assertTrue(
			"Test mapping of import exemple.figureElement.core.Line",
			(((IFirstClassEntity) (this.importCreation.get("Line")))
				.getDisplayID().equals("exemple.figureElement.core.Line")));

		Assert.assertTrue(
			"Test creation of import exemple.figureElement.core.FigureElement",
			(this.importCreation.get("FigureElement") != null));

		Assert.assertTrue(
			"Test mapping of import exemple.figureElement.core.FigureElement",
			(((IFirstClassEntity) (this.importCreation.get("FigureElement")))
				.getDisplayID()
				.equals("exemple.figureElement.core.FigureElement")));

		Assert
			.assertTrue(
				"Test creation of import exemple.figureElement.display.DisplayManager",
				(this.importCreation.get("DisplayManager") != null));

		Assert
			.assertTrue(
				"Test mapping of import exemple.figureElement.display.DisplayManager",
				(((IFirstClassEntity) (this.importCreation
					.get("DisplayManager"))).getDisplayID()
					.equals("exemple.figureElement.display.DisplayManager")));

		Assert
			.assertTrue(
				"Test creation of import exemple.figureElement.display.DisplayableFigure",
				(this.importCreation.get("DisplayableFigure") != null));

		Assert
			.assertTrue(
				"Test mapping of import exemple.figureElement.display.DisplayableFigure",
				(((IFirstClassEntity) (this.importCreation
					.get("DisplayableFigure"))).getDisplayID()
					.equals("exemple.figureElement.display.DisplayableFigure")));

		Assert.assertTrue(
			"Test creation of import java.awt.Color",
			(this.importCreation.get("Color") != null));

		Assert.assertTrue(
			"Test mapping of import java.awt.Color",
			(((IFirstClassEntity) (this.importCreation.get("Color")))
				.getDisplayID().equals("java.awt.Color")));

	}
	public void testLstExist() {
		File lst = new File(this.getLstPath());
		Assert.assertEquals("Existence of the lst file", true, lst.exists());
	}
}
