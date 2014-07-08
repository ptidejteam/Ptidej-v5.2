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

package ptidej.ui.layout.repository.sugiyama;

import ptidej.ui.layout.repository.sugiyama.graph.INode;

/**
 * @author: Yann
 * @since: 2013/06/04
 * 
 */
// TODO: What is this class for?
// TODO: Should this class really be here?
public class NodeCouple implements Comparable {
	private final INode node;
	private final double value;

	public NodeCouple(final INode aNode, final double aValue) {
		this.node = aNode;
		this.value = aValue;
	}
	public INode getNode() {
		return this.node;
	}
	public double getValue() {
		return this.value;
	}
	public int compareTo(final Object o) {
		// Yann 2014/04/11: New better implementation!
		//	if (this.value < ((NodeCouple) o).getValue())
		//		return -1;
		//	if (this.value > ((NodeCouple) o).getValue())
		//		return 1;
		//	return 0; // they are equal
		return Double.compare(this.value, ((NodeCouple) o).getValue());
	}
}