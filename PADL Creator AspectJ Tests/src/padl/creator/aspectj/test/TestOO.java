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

import junit.framework.Assert;


public class TestOO extends AspectJPrimitive {


	
	public TestOO(String aName) {
		super(aName);
	}
	
	public void testCreation() {
		//---------------------------------------
		Assert.assertEquals(
			"OO CLM Creation",
			true,
			(this.getCodeLevelModel() != null));
	}
	
	public void testConstituentCreation(){
		//On package exemple.figureElement.display
		Assert.assertTrue(
			"Test creation of exemple.figureElement.display.DisplayManager",
			(this.getCodeLevelModel().getTopLevelEntityFromID("exemple.figureElement.display.DisplayManager")) != null);
		Assert.assertTrue(
			"Test creation of exemple.figureElement.display.DisplayableFigure",
			(this.getCodeLevelModel().getTopLevelEntityFromID("exemple.figureElement.display.DisplayableFigure")) != null);
		
		//On package exemple.figureElement.core
		Assert.assertTrue(
			"Test creation of exemple.figureElement.core.Point",
			(this.getCodeLevelModel().getTopLevelEntityFromID("exemple.figureElement.core.Point")) != null);
		Assert.assertTrue(
			"Test creation of exemple.figureElement.core.Line",
			(this.getCodeLevelModel().getTopLevelEntityFromID("exemple.figureElement.core.Line")) != null);
		
		Assert.assertTrue(
			"Test creation of exemple.figureElement.core.FigureElement",
			(this.getCodeLevelModel().getTopLevelEntityFromID("exemple.figureElement.core.FigureElement")) != null);
		
		Assert.assertTrue(
			"Test creation of exemple.figureElement.core.Main",
			(this.getCodeLevelModel().getTopLevelEntityFromID("exemple.figureElement.core.Main")) != null);
		
		//Assert test
		Assert.assertTrue(
			"Test non creation of exemple.figureElement.display.NonExistent",
			(this.getCodeLevelModel().getTopLevelEntityFromID("exemple.figureElement.display.NonExistent")) == null);
		
	}
	
	
}
