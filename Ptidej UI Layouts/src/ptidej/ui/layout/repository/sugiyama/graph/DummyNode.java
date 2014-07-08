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

package ptidej.ui.layout.repository.sugiyama.graph;

import ptidej.ui.kernel.IntermediaryPoint;

/**
 * @author Mohamed Kahla  
 * @since  29/05/2006
 * 
 * It's a dummy node needed for splitting long span adges
 * a dummy node is used to split a long span edge 
 * connecting two node seperated by a level
 * n dummies nodes are inserted between two nodes
 * if they are separated by n level
 */
public class DummyNode extends Node {
	private final IntermediaryPoint node;

	/**
	 * @param : the level's number
	 * @param : the nodes at the upper level
	 * @param : the nodes at the level under the first one
	 */
	public DummyNode(final int aLevel, final INode up, final INode down) {
		super(null, aLevel);
		this.addParent(up);
		this.addChild(down);
		this.node = new IntermediaryPoint();
	}
	public void setPosition(final int x, final int y) {
		this.node.setPosition(x, y);
	}
	public int getX() {
		return this.node.getX();
	}
	public int getY() {
		return this.node.getY();
	}
}