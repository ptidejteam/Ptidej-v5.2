/*
 * (c) Copyright 2001-2004 Yann-Ga? Gu??euc,
 * University of Montr?l.
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
package padl.kernel.impl;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import padl.kernel.IConstituent;
import padl.kernel.INavigable;
import padl.util.CharArrayComparator;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/04/09
 */
public class GenericContainerOfNaturallyOrderedConstituents extends
		AbstractGenericContainerOfConstituents {

	private static class NaturalOrderComparator implements Comparator {
		private static NaturalOrderComparator UniqueInstance;
		private static NaturalOrderComparator getInstance() {
			if (NaturalOrderComparator.UniqueInstance == null) {
				NaturalOrderComparator.UniqueInstance =
					new NaturalOrderComparator();
			}
			return NaturalOrderComparator.UniqueInstance;
		}

		public int compare(final Object o1, final Object o2) {
			if (o1 == null || o2 == null) {
				return 0;
			}
			return CharArrayComparator.getInstance().compare(
				((IConstituent) o1).getID(),
				((IConstituent) o2).getID());
		}
	}
	private static final long serialVersionUID = -3180931385964182545L;

	private boolean dirty;
	public GenericContainerOfNaturallyOrderedConstituents(
		final INavigable aContainerConstituent) {

		super(aContainerConstituent);
	}
	public GenericContainerOfNaturallyOrderedConstituents(
		final INavigable aContainerConstituent,
		final int anInitialCapacity) {

		super(aContainerConstituent, anInitialCapacity);
	}
	protected void directlyAddConstituentExtra(final IConstituent aConstituent) {
		this.dirty = true;
	}
	public Iterator getIteratorOnConstituents() {
		// Yann 2005/10/12: Iterator!
		// I replace the list with an iterator, but this
		// is a major bottleneck in some specific case!
		// TODO: Implement my own "smart" iterator which
		// could be tightly linked with the AbstractContainer
		// class to prevent too many memory allocation (Singleton).
		if (this.dirty) {
			Arrays
				.sort(this.constituents, NaturalOrderComparator.getInstance());
			this.dirty = false;
		}
		//	this.constituentIterator.reset();
		//	return this.constituentIterator;
		return super.getIteratorOnConstituents();
	}
}
