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

import padl.kernel.INavigable;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/04/09
 */
public class GenericContainerOfInsertionOrderedConstituents extends
		AbstractGenericContainerOfConstituents {

	private static final long serialVersionUID = -3180931385964182545L;

	public GenericContainerOfInsertionOrderedConstituents(
		final INavigable aContainerConstituent) {

		super(aContainerConstituent);
	}
	public GenericContainerOfInsertionOrderedConstituents(
		final INavigable aContainerConstituent,
		final int anInitialCapacity) {

		super(aContainerConstituent, anInitialCapacity);
	}
}
