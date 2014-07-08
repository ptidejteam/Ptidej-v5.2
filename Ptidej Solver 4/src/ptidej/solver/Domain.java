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
package ptidej.solver;

import choco.integer.var.IntDomainVar;
import choco.palm.integer.PalmBitSetIntDomain;

/**
 * 
 * @author Yann
 * @since  2010/07/21
 *
 */
public class Domain extends PalmBitSetIntDomain {
	//	private final TIntSet setOfValues;
	public Domain(final IntDomainVar v, final int a, final int b) {
		super(v, a, b);
		//	this.setOfValues = new TIntHashSet(b - a + 1);
		//	for (int i = a; i <= b; i++) {
		//		this.setOfValues.add(i);
		//	}
	}
	protected void addIndex(final int i) {
		super.addIndex(i);
		//	this.setOfValues.add(i);
	}
	protected void removeIndex(final int i) {
		super.removeIndex(i);
		//	this.setOfValues.remove(i);
	}
	//	public TIntSet getSetOfValues() {
	//		return this.setOfValues;
	//	}
}
