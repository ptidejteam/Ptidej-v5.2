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
package ptidej.ui.analysis.repository.comparator.model;

import padl.kernel.IConstituent;
import padl.kernel.IInterface;
import padl.util.adapter.InterfaceAdapter;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/12/20
 */
public class RemovedInterface extends InterfaceAdapter implements IInterface {
	private static final long serialVersionUID = 8431318495759716272L;

	public RemovedInterface(final IInterface anInterface) {
		super(anInterface);
	}
	public IConstituent getClone() {
		return new RemovedInterface((IInterface) super.getClone());
	}
}
