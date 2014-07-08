/* (c) Copyright 2001 and following years, Yann-Gaël Guéhéneuc,
 * University of Montreal.
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
package padl.serialiser.test;

import java.io.File;
import java.util.Iterator;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.analysis.UnsupportedSourceModelException;
import padl.analysis.repository.AACRelationshipsAnalysis;
import padl.creator.classfile.CompleteClassFileCreator;
import padl.kernel.IAbstractLevelModel;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IIdiomLevelModel;
import padl.kernel.exception.CreationException;
import padl.kernel.impl.Factory;
import padl.serialiser.DB4OSerialiser;
import padl.util.Util;

public class TestDB4OSerialiserJHotDraw51 extends TestCase {
	private static IAbstractLevelModel AbstractLevelModel;
	private static IAbstractLevelModel SerialisedAbstractLevelModel;
	private static String SerialisedFileName;

	public TestDB4OSerialiserJHotDraw51(String name) {
		super(name);
	}
	protected void setUp() throws UnsupportedSourceModelException,
			CreationException {

		if (TestDB4OSerialiserJHotDraw51.AbstractLevelModel == null) {
			final ICodeLevelModel codeLevelModel =
				Factory.getInstance().createCodeLevelModel("JHotDraw v5.1");
			codeLevelModel.create(new CompleteClassFileCreator(
				new String[] { "rsc/JHotDraw v5.1/bin/" },
				true));

			TestDB4OSerialiserJHotDraw51.AbstractLevelModel =
				(IIdiomLevelModel) new AACRelationshipsAnalysis()
					.invoke(codeLevelModel);

			TestDB4OSerialiserJHotDraw51.SerialisedFileName =
				DB4OSerialiser.getInstance().serialiseWithAutomaticNaming(
					TestDB4OSerialiserJHotDraw51.AbstractLevelModel);
			TestDB4OSerialiserJHotDraw51.SerialisedAbstractLevelModel =
				(IAbstractLevelModel) DB4OSerialiser.getInstance().deserialise(
					TestDB4OSerialiserJHotDraw51.SerialisedFileName);
		}
	}
	protected void tearDown() {
		final File serialisedFile =
			new File(TestDB4OSerialiserJHotDraw51.SerialisedFileName);
		serialisedFile.delete();
	}
	public void testNames() {
		Assert.assertEquals(
			TestDB4OSerialiserJHotDraw51.AbstractLevelModel.getDisplayName(),
			TestDB4OSerialiserJHotDraw51.SerialisedAbstractLevelModel
				.getDisplayName());
	}
	public void testElements() {
		final IFirstClassEntity[] originalEntities =
			Util
				.getArrayOfTopLevelEntities(TestDB4OSerialiserJHotDraw51.AbstractLevelModel);
		final IFirstClassEntity[] clonedEntities =
			Util
				.getArrayOfTopLevelEntities(TestDB4OSerialiserJHotDraw51.SerialisedAbstractLevelModel);

		Assert.assertEquals(
			"Number of entities",
			originalEntities.length,
			clonedEntities.length);

		for (int i = 0; i < originalEntities.length; i++) {
			Assert.assertEquals(
				"Number of inherited or implemented entities",
				((IFirstClassEntity) originalEntities[i])
					.getNumberOfInheritedEntities(),
				((IFirstClassEntity) clonedEntities[i])
					.getNumberOfInheritedEntities());

			final Iterator originalPEntityInherits =
				((IFirstClassEntity) originalEntities[i])
					.getIteratorOnInheritedEntities();
			final Iterator clonedPEntityInherits =
				((IFirstClassEntity) clonedEntities[i])
					.getIteratorOnInheritedEntities();
			while (clonedPEntityInherits.hasNext()) {
				final IFirstClassEntity originalEntity =
					(IFirstClassEntity) originalPEntityInherits.next();
				final IFirstClassEntity clonedEntity =
					(IFirstClassEntity) clonedPEntityInherits.next();

				Assert.assertEquals(
					originalEntity.getDisplayName(),
					clonedEntity.getDisplayName());
				Assert.assertTrue(
					"Identity hashcodes of the entities are different",
					System.identityHashCode(originalEntity) != System
						.identityHashCode(clonedEntity));
			}
		}
	}
	public void testEntities() {
		final IFirstClassEntity[] originalEntities =
			Util
				.getArrayOfTopLevelEntities(TestDB4OSerialiserJHotDraw51.AbstractLevelModel);
		final IFirstClassEntity[] clonedEntities =
			Util
				.getArrayOfTopLevelEntities(TestDB4OSerialiserJHotDraw51.SerialisedAbstractLevelModel);

		Assert.assertEquals(
			"Number of entities",
			originalEntities.length,
			clonedEntities.length);

		for (int i = 0; i < originalEntities.length; i++) {
			// Yann 2006/02/21: Member entities...
			// Two member entities may have identical names,
			// as well as identical other attributes but for
			// their JVM-based object ID, which I now use for
			// equals(). In the case of clones, I test on their
			// hash codes...
			Assert.assertEquals(
				"Entities are equal",
				originalEntities[i].getDisplayID(),
				clonedEntities[i].getDisplayID());
			Assert.assertTrue(
				"Identity hashcodes of the entities are different",
				System.identityHashCode(originalEntities[i]) != System
					.identityHashCode(clonedEntities[i]));
		}
	}
	public void testInheritance() {
		final IFirstClassEntity[] originalEntities =
			Util
				.getArrayOfTopLevelEntities(TestDB4OSerialiserJHotDraw51.AbstractLevelModel);
		final IFirstClassEntity[] clonedEntities =
			Util
				.getArrayOfTopLevelEntities(TestDB4OSerialiserJHotDraw51.SerialisedAbstractLevelModel);

		Assert.assertEquals(
			"Number of entities",
			originalEntities.length,
			clonedEntities.length);

		for (int i = 0; i < originalEntities.length; i++) {
			Assert.assertEquals(
				"Number of inherited or implemented entities",
				((IFirstClassEntity) originalEntities[i])
					.getNumberOfInheritedEntities(),
				((IFirstClassEntity) clonedEntities[i])
					.getNumberOfInheritedEntities());

			final Iterator originalPEntityInherits =
				((IFirstClassEntity) originalEntities[i])
					.getIteratorOnInheritedEntities();
			final Iterator clonedPEntityInherits =
				((IFirstClassEntity) clonedEntities[i])
					.getIteratorOnInheritedEntities();
			while (clonedPEntityInherits.hasNext()) {
				final IFirstClassEntity originalEntity =
					(IFirstClassEntity) originalPEntityInherits.next();
				final IFirstClassEntity clonedEntity =
					(IFirstClassEntity) clonedPEntityInherits.next();

				Assert.assertEquals(
					originalEntity.getDisplayName(),
					clonedEntity.getDisplayName());
				Assert.assertTrue(
					"Identity hashcodes of the entities are different",
					System.identityHashCode(originalEntity) != System
						.identityHashCode(clonedEntity));
			}
		}
	}
}
