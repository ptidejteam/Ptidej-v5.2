/*
 * (c) Copyright 2001, 2002 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes and Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * Yann-Gaël Guéhéneuc, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR B PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.test.clone;

import java.util.Iterator;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.kernel.IElement;
import padl.kernel.IFirstClassEntity;
import padl.motif.IDesignMotifModel;
import padl.motif.repository.Composite;
import padl.util.Util;

public final class Clone extends TestCase {
	private IDesignMotifModel compositePattern;
	private IDesignMotifModel clonedCompositePattern;
	public Clone(String name) {
		super(name);
	}
	protected void setUp() throws CloneNotSupportedException {
		this.compositePattern = new Composite();
		this.clonedCompositePattern =
			(IDesignMotifModel) this.compositePattern.clone();
	}
	public void testElements() {
		final IFirstClassEntity[] originalEntities =
			Util.getArrayOfTopLevelEntities(this.compositePattern);
		final IFirstClassEntity[] clonedEntities =
			Util.getArrayOfTopLevelEntities(this.clonedCompositePattern);

		Assert.assertEquals(
			"Number of entities",
			originalEntities.length,
			clonedEntities.length);

		for (int i = 0; i < originalEntities.length; i++) {
			Assert.assertTrue(
				"Number of elements per entities",
				((IFirstClassEntity) originalEntities[i])
					.getNumberOfConstituents() != 0);
			Assert.assertTrue(
				"Number of elements per entities",
				((IFirstClassEntity) clonedEntities[i])
					.getNumberOfConstituents() != 0);

			Assert.assertEquals(
				"Number of elements per entities",
				((IFirstClassEntity) originalEntities[i])
					.getNumberOfConstituents(),
				((IFirstClassEntity) clonedEntities[i])
					.getNumberOfConstituents());

			final Iterator originalEntityElements =
				((IFirstClassEntity) originalEntities[i])
					.getIteratorOnConstituents();
			final Iterator clonedEntityElements =
				((IFirstClassEntity) clonedEntities[i])
					.getIteratorOnConstituents();
			while (originalEntityElements.hasNext()) {
				final IElement originalEntityElement =
					(IElement) originalEntityElements.next();
				final IElement clonedEntityElement =
					(IElement) clonedEntityElements.next();
				Assert.assertEquals(originalEntityElement, clonedEntityElement);
				Assert.assertTrue(
					"Identity hashcodes of the elements are different",
					System.identityHashCode(originalEntityElement) != System
						.identityHashCode(clonedEntityElement));
			}
		}
	}
	public void testEntities() {
		final IFirstClassEntity[] originalEntities =
			Util.getArrayOfTopLevelEntities(this.compositePattern);
		final IFirstClassEntity[] clonedEntities =
			Util.getArrayOfTopLevelEntities(this.clonedCompositePattern);

		Assert.assertEquals("Number of entities", 3, originalEntities.length);
		Assert.assertEquals("Number of entities", 3, clonedEntities.length);

		Assert.assertEquals(
			"Number of entities",
			originalEntities.length,
			clonedEntities.length);

		for (int i = 0; i < originalEntities.length; i++) {
			// Yann 2006/02/21: Member entities...
			// Two member entities may have indentical names,
			// as well as identical other attributes but for
			// their JVM-based object id, which I now use for
			// equals(). In the case of clones, I test on their
			// hash codes...
			Assert.assertEquals(
				"Entities are equal",
				originalEntities[i].hashCode(),
				clonedEntities[i].hashCode());
			Assert.assertTrue(
				"Identity hashcodes of the entities are different",
				System.identityHashCode(originalEntities[i]) != System
					.identityHashCode(clonedEntities[i]));
		}
	}
	public void testInheritance() {
		final IFirstClassEntity[] originalEntities =
			Util.getArrayOfTopLevelEntities(this.compositePattern);
		final IFirstClassEntity[] clonedEntities =
			Util.getArrayOfTopLevelEntities(this.clonedCompositePattern);

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

			final Iterator originalEntityInherits =
				((IFirstClassEntity) originalEntities[i])
					.getIteratorOnInheritedEntities();
			final Iterator clonedEntityInherits =
				((IFirstClassEntity) clonedEntities[i])
					.getIteratorOnInheritedEntities();

			while (originalEntityInherits.hasNext()) {
				final IFirstClassEntity originalInheritedEntity =
					(IFirstClassEntity) originalEntityInherits.next();
				final IFirstClassEntity clonedInheritedEntity =
					(IFirstClassEntity) clonedEntityInherits.next();
				Assert.assertEquals(
					originalInheritedEntity,
					clonedInheritedEntity);
				Assert.assertTrue(
					"Identity hashcodes of the entities are different",
					System.identityHashCode(originalInheritedEntity) != System
						.identityHashCode(clonedInheritedEntity));
			}
		}
	}
	public void testPatterns() {
		Assert.assertEquals(
			"Pattern names",
			this.compositePattern.getName(),
			this.clonedCompositePattern.getName());
		Assert.assertTrue(
			"Identity hashcodes of the patterns are different",
			System.identityHashCode(this.compositePattern) != System
				.identityHashCode(this.clonedCompositePattern));
	}
}
