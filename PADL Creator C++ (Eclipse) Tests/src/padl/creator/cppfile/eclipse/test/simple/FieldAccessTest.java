/* (c) Copyright 2008 and following yearsYann-Gaël Guéhéneuc,
 * École Polytechnique de Montréal.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the authorthis paragraph and the one after it.
 * 
 * This software is made available AS ISand THE AUTHOR DISCLAIMS
 * ALL WARRANTIESEXPRESS OR IMPLIEDINCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSEAND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN,
 * ANY LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMEDWHETHER ARISING IN CONTRACTTORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITYEVEN IF THE AUTHOR IS ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.creator.cppfile.eclipse.test.simple;

import junit.framework.Assert;
import junit.framework.TestCase;
import padl.cpp.kernel.ICPPClass;
import padl.cpp.kernel.IGlobalFunction;
import padl.generator.helper.ModelGenerator;
import padl.kernel.IGetter;
import padl.kernel.IIdiomLevelModel;

public class FieldAccessTest extends TestCase {
	private static IIdiomLevelModel IdiomLevelModel;
	public FieldAccessTest(String name) {
		super(name);
	}
	protected void setUp() throws Exception {
		if (FieldAccessTest.IdiomLevelModel == null) {
			FieldAccessTest.IdiomLevelModel =
				ModelGenerator
					.generateModelFromCppFilesUsingEclipse(
						"Funny",
						new String[] { "../PADL Creator C++ (Eclipse) Tests/data/FieldAccess/" });
		}
	}
	public void testNumberOfTopLevelEntities() {
		Assert.assertNotNull(
			"The idiom-level model is null!",
			FieldAccessTest.IdiomLevelModel);
		Assert.assertEquals(
			10,
			FieldAccessTest.IdiomLevelModel.getNumberOfTopLevelEntities());
	}
	public void testMainFunction() {
		final IGlobalFunction mainFunction =
			(IGlobalFunction) FieldAccessTest.IdiomLevelModel
				.getTopLevelEntityFromID("main()");
		Assert.assertNotNull("No main function!?", mainFunction);
		Assert.assertEquals(13, mainFunction.getNumberOfConstituents());
	}
	public void testGetter() {
		final ICPPClass clazz =
			(ICPPClass) FieldAccessTest.IdiomLevelModel
				.getTopLevelEntityFromID("FieldAccessTest");
		final IGetter getter =
			(IGetter) clazz.getConstituentFromID("getString()");
		Assert.assertNotNull("No getter!?", getter);
		Assert.assertEquals(1, getter.getNumberOfConstituents());
	}
}
