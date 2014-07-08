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
package padl.analysis.systematicuml.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.analysis.UnsupportedSourceModelException;
import padl.analysis.repository.SystematicUMLAnalysis;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.IAbstractModel;
import padl.kernel.ICodeLevelModel;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import util.io.ProxyConsole;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since 2004/11/11
 */
public class Test1 extends TestCase {
	private static ICodeLevelModel OriginalCodeLevelModel;
	private static IAbstractModel ResultingAbstractModel;

	public Test1(final String testName) {
		super(testName);
	}
	protected void setUp() {
		if (Test1.ResultingAbstractModel == null) {
			Test1.OriginalCodeLevelModel =
				Factory.getInstance().createCodeLevelModel(
					"SystematicUML Test 1");
			try {
				Test1.OriginalCodeLevelModel
					.create(new CompleteClassFileCreator(
						new String[] { "../PADL Analyses Tests/bin/padl/analysis/systematicuml/data/" }));
				Test1.ResultingAbstractModel =
					new SystematicUMLAnalysis()
						.invoke(Test1.OriginalCodeLevelModel);
			}
			catch (final CreationException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
			catch (final UnsupportedSourceModelException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
		}
	}
	public void testNumberOfEntities() {
		Assert.assertEquals(
			"Number of entities",
			Test1.OriginalCodeLevelModel.getNumberOfTopLevelEntities(),
			Test1.ResultingAbstractModel.getNumberOfTopLevelEntities());
	}
	public void testEnumeration() {
		Assert
			.assertEquals(
				"ClassH_Enumeration",
				"<<enumeration>>\npadl.analysis.systematicuml.data.ClassH_Enumeration",
				Test1.ResultingAbstractModel
					.getTopLevelEntityFromID(
						"padl.analysis.systematicuml.data.ClassH_Enumeration")
					.getDisplayName());
	}
}
