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

import junit.framework.TestCase;
import padl.aspectj.kernel.impl.AspectJFactory;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.exception.CreationException;

/**
 * @author Jean-Yves Guyomarc'h
 * @author Yann
 * @since 2006/01/23
 */
public abstract class AspectJPrimitive extends TestCase {
	private ICodeLevelModel codelevelmodel = null;
	private final String jarOO =
		"../PADL Creator AspectJ Tests/rsc/FigureElement/FigureElementOO.jar";

	public AspectJPrimitive(final String aName) {
		super(aName);
	}
	public ICodeLevelModel getCodeLevelModel() {
		return this.codelevelmodel;
	}
	protected void setUp() throws CreationException {
		if (this.codelevelmodel == null) {
			this.codelevelmodel =
				AspectJFactory.getInstance().createCodeLevelModel(
					"AspectJ Model");
			this.codelevelmodel.create(new CompleteClassFileCreator(
				new String[] { this.jarOO }));
		}
	}
}
