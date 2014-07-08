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
package padl.analysis.packagebuilder.test;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.analysis.UnsupportedSourceModelException;
import padl.analysis.repository.PackageIdentifierAnalysis;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.IAbstractModel;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IPackage;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import padl.util.Util;
import util.io.ProxyConsole;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since 2005/10/07
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
					"PackageBuilder Test 1");
			try {
				Test1.OriginalCodeLevelModel
					.create(new CompleteClassFileCreator(
						new String[] { "../PADL Analyses Tests/bin/padl/analysis/systematicuml/data/" }));
				Test1.ResultingAbstractModel =
					new PackageIdentifierAnalysis().invoke(Test1.OriginalCodeLevelModel);
			}
			catch (final CreationException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
			catch (final UnsupportedSourceModelException e) {
				e.printStackTrace(ProxyConsole.getInstance().errorOutput());
			}
		}
	}
	public void testNumberOfPackages() {
		Assert.assertEquals(
			"Number of packages",
			5,
			Test1.ResultingAbstractModel.getNumberOfConstituents());
	}
	public void testNumberOfJavaLangEntities() {
		Assert.assertEquals("Name of package", "java.lang", Util
			.getArrayOfConstituents(Test1.ResultingAbstractModel)[1]
			.getDisplayName());
		Assert.assertEquals("Number of java.lang entities", 5, ((IPackage) Util
			.getArrayOfConstituents(Test1.ResultingAbstractModel)[1])
			.getNumberOfConstituents());
	}
	public void testNumberOfJavaUtilEntities() {
		Assert.assertEquals("Name of package", "java.util", Util
			.getArrayOfConstituents(Test1.ResultingAbstractModel)[2]
			.getDisplayName());
		Assert.assertEquals("Number of java.util entities", 2, ((IPackage) Util
			.getArrayOfConstituents(Test1.ResultingAbstractModel)[2])
			.getNumberOfConstituents());
	}
	public void testNumberOfDataEntities() {
		Assert.assertEquals(
			"Name of package",
			"padl.analysis.systematicuml.data",
			Util.getArrayOfConstituents(Test1.ResultingAbstractModel)[4]
				.getDisplayName());
		Assert.assertEquals(
			"Number of padl.analysis.systematicuml.data entities",
			14,
			((IPackage) Util
				.getArrayOfConstituents(Test1.ResultingAbstractModel)[4])
				.getNumberOfConstituents());
	}
}
