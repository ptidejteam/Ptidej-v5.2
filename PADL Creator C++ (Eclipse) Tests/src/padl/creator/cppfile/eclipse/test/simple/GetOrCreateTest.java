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
import padl.generator.helper.ModelGenerator;
import padl.kernel.IIdiomLevelModel;

public class GetOrCreateTest extends TestCase {
	private static IIdiomLevelModel IdiomLevelModel;
	public GetOrCreateTest(String name) {
		super(name);
	}
	protected void setUp() throws Exception {
		if (GetOrCreateTest.IdiomLevelModel == null) {
			GetOrCreateTest.IdiomLevelModel =
				ModelGenerator
					.generateModelFromCppFilesUsingEclipse(
						"Funny",
						new String[] { "../PADL Creator C++ (Eclipse) Tests/data/getOrCreate/" });
		}
	}
	public void testNumberOfTopLevelEntities() {
		Assert.assertNotNull(
			"The idiom-level model is null!",
			GetOrCreateTest.IdiomLevelModel);
		Assert.assertEquals(
			25,
			GetOrCreateTest.IdiomLevelModel.getNumberOfTopLevelEntities());
	}
}
