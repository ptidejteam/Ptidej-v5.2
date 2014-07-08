package padl.creator.aolfile.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.analysis.UnsupportedSourceModelException;
import padl.analysis.repository.AACRelationshipsAnalysis;
import padl.creator.aolfile.AOLCreator;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IIdiomLevelModel;
import padl.kernel.impl.Factory;
import util.io.ProxyConsole;

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

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2006/11/28
 */
public class Test1 extends TestCase {
	private static ICodeLevelModel CodeLevelModel;
	private static IIdiomLevelModel IdiomLevelModel;

	public Test1(final String name) {
		super(name);
	}
	public void setUp() {
		if (Test1.CodeLevelModel == null) {
			Test1.CodeLevelModel =
				Factory.getInstance().createCodeLevelModel("Test1");
			final AOLCreator aolCreator =
				new AOLCreator(
					new String[] { "../PADL Creator AOL Tests/rsc/Unit Tests/Test1.aol" });
			aolCreator.create(Test1.CodeLevelModel, true);
			try {
				Test1.IdiomLevelModel =
					(IIdiomLevelModel) new AACRelationshipsAnalysis()
						.invoke(Test1.CodeLevelModel);
			}
			catch (final UnsupportedSourceModelException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
		}
	}
	public void testClasses() {
		Assert.assertEquals("Number of classes", 18, Test1.IdiomLevelModel
			.getNumberOfTopLevelEntities());
	}
}
