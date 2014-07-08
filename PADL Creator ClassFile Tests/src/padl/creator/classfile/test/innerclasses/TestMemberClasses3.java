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
package padl.creator.classfile.test.innerclasses;

import junit.framework.Assert;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.creator.classfile.test.ClassFilePrimitive;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IGhost;
import padl.kernel.exception.CreationException;
import padl.util.Util;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2006/02/08
 */
public class TestMemberClasses3 extends ClassFilePrimitive {
	private static IFirstClassEntity[] FirstClassEntities = null;

	public TestMemberClasses3(final String aName) {
		super(aName);
	}
	protected void setUp() throws CreationException {
		if (TestMemberClasses3.FirstClassEntities == null) {
			final ICodeLevelModel codeLevelModel =
				ClassFilePrimitive.getFactory().createCodeLevelModel(
					"ptidej.example.innerclasses");
			codeLevelModel
				.create(new CompleteClassFileCreator(
					new String[] { "../PADL Creator ClassFile Tests/rsc/Multiple Same-name Member Classes/" }));

			TestMemberClasses3.FirstClassEntities =
				Util.getArrayOfTopLevelEntities(codeLevelModel);
		}
	}
	public void testMemberEntities() {
		for (int i = 0; i < TestMemberClasses3.FirstClassEntities.length; i++) {
			Assert.assertTrue(
				"Member entity "
						+ TestMemberClasses3.FirstClassEntities[i]
							.getDisplayName() + " (" + i
						+ ") outside of its enclosing entity!",
				TestMemberClasses3.FirstClassEntities[i]
					.getDisplayName()
					.indexOf('$') == -1);
		}
	}
	public void testMemberEntitiesInheritance() {
		for (int i = 0; i < TestMemberClasses3.FirstClassEntities.length; i++) {
			if (TestMemberClasses3.FirstClassEntities[i].getDisplayID().equals(
				"java.util.Iterator")) {

				Assert.assertEquals(
					"Member entity "
							+ TestMemberClasses3.FirstClassEntities[i]
								.getDisplayName() + " (" + i
							+ ") inherits from the proper classes/interfaces!",
					2,
					((IGhost) TestMemberClasses3.FirstClassEntities[i])
						.getNumberOfInheritingEntities());
			}
		}
	}
}
