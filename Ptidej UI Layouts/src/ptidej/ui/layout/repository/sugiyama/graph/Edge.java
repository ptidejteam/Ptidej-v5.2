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

import java.util.ArrayList;
import java.util.List;

/**
 * @author kahlamoh
 * this class are added to bypass the problem of the 
 * 1- the direction of an edge (because of the Sugiyama's algorithme, 
 * we must have an acyclic Digraph, so some edges are inversed for this perpose)
 * 2- some dummy nodes are added sometime and it's one part of an edge
 * so we could find them easly dy calling det dummiesNodes()
 *
 */
// TODO: What is the difference between an Edge and a Link?
public class Edge {
	// 1 for not inversed, -1 for inversed, 0 for not defined or applicable
	private int direction;
	private INode parent;
	private INode child;
	private List dummies;

	public Edge() {
		this.direction = 0;
		this.dummies = new ArrayList();
	}
	public Edge(int aDirection) {
		this.direction = aDirection;
	}
	public Edge(
		final int aDirection,
		final List aDumyList,
		final INode anOrigin,
		final INode aTarget) {

		this.direction = aDirection;
		this.dummies = aDumyList;
		this.parent = anOrigin;
		this.child = aTarget;
	}
	public void setDirection(final int aDirection) {
		this.direction = aDirection;
	}
	public int getDirection() {
		return this.direction;
	}
	public INode getOrigin() {
		return this.parent;
	}
	public INode getTArget() {
		return this.child;
	}
	public void setDummyList(final List aList) {
		this.dummies = aList;
	}
	public List getDummiesNodes() {
		return this.dummies;
	}
}
