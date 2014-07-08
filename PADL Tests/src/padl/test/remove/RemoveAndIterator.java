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
package padl.test.remove;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import junit.framework.Assert;
import junit.framework.TestCase;
import padl.kernel.IConstituent;
import padl.motif.IDesignMotifModel;
import padl.motif.repository.Composite;

public final class RemoveAndIterator extends TestCase {
	private IDesignMotifModel compositePattern;
	private IDesignMotifModel clonedCompositePattern1;
	private IDesignMotifModel clonedCompositePattern2;
	public RemoveAndIterator(String name) {
		super(name);
	}
	protected void setUp() throws CloneNotSupportedException {
		this.compositePattern = new Composite();

		this.clonedCompositePattern1 =
			(IDesignMotifModel) this.compositePattern.clone();

		this.clonedCompositePattern2 =
			(IDesignMotifModel) this.compositePattern.clone();
	}
	public void testNotConcurrent() {
		try {
			final Iterator iterator =
				this.clonedCompositePattern1.getIteratorOnConstituents();
			while (iterator.hasNext()) {
				final IConstituent constituent = (IConstituent) iterator.next();
				this.clonedCompositePattern1
					.removeConstituentFromID(constituent.getID());
			}
		}
		catch (final ConcurrentModificationException e) {
			return;
		}
		Assert.fail("Modifying concurrently the list of constituents!");
	}
	public void testEmbeddedNotConcurrent1() {
		int count = 0;
		final Iterator iterator1 =
			this.compositePattern.getIteratorOnConstituents();
		while (iterator1.hasNext()) {
			iterator1.next();

			final Iterator iterator2 =
				this.compositePattern.getIteratorOnConstituents();
			while (iterator2.hasNext()) {
				iterator2.next();

				count++;
			}
		}
		Assert.assertEquals(9, count);
	}
	public void testEmbeddedNotConcurrent2() {
		int count = 0;
		final Iterator iterator1 =
			this.compositePattern.getIteratorOnConstituents();
		while (iterator1.hasNext() && count < 5) {
			//	final IConstituent constituent1 = (IConstituent) iterator1.next();

			final Iterator iterator2 =
				this.compositePattern.getIteratorOnConstituents();
			while (iterator2.hasNext() && count < 5) {
				//	final IConstituent constituent2 = (IConstituent) iterator2.next();

				count++;
			}
		}

		count = 0;
		final Iterator iterator =
			this.compositePattern.getIteratorOnConstituents();
		while (iterator.hasNext()) {
			iterator.next();
			count++;
		}
		Assert.assertEquals(3, count);
	}
	public void testConcurrent() {
		final Iterator iterator =
			this.clonedCompositePattern2.getConcurrentIteratorOnConstituents();
		while (iterator.hasNext()) {
			final IConstituent constituent = (IConstituent) iterator.next();
			this.clonedCompositePattern2.removeConstituentFromID(constituent
				.getID());
		}
		Assert.assertEquals(
			"Number of entities",
			0,
			this.clonedCompositePattern2.getNumberOfConstituents());
	}
}
