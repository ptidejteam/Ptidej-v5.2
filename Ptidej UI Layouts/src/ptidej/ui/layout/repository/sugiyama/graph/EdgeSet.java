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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author kahlamoh
 * this class map the edge whith the REAL origin node!
 * what I mean is that no matter if the edge is inverted or not
 * we are sur to get all the edges really emerging fron a Node 
 *  THIS node is used like a key!
 *
 */
public class EdgeSet {
	// key : a real origin node
	// value : a List containing the different edges associated to that origin node
	private final Map edgesMap;

	public EdgeSet() {
		this.edgesMap = new HashMap();
	}

	/**
	 * The real direction of the edge!
	 */
	public void addEdge(final Edge anEdge) {
		final INode originNode;
		if (anEdge.getDirection() > 0) {
			originNode = anEdge.getOrigin();
		}
		else if (anEdge.getDirection() < 0) {
			originNode = anEdge.getTArget();
		}
		else {
			// if no direction it may be at the same level
			// TODO later
			return;
		}
		// Node aParent, Node aChild, int aDirection
		// TODO
		// if not setted yet
		if (!this.edgesMap.containsKey(originNode)) {
			final List l = new ArrayList();
			l.add(anEdge);
			this.edgesMap.put(originNode, l);
		}
		else {
			final List l = (List) this.edgesMap.get(originNode);
			// TODO this test should not be necessary
			if (l != null) {
				l.add(anEdge);
			}
		}
	}

	/**
	 * this method are called after building the dummy nodes 
	 * for each edges!
	 */
	public void setDummyNodes(
		final INode aParent,
		final INode aChild,
		final List dummyList) {

		// for all the edges!
		// we add dummy nodes!

		// the problem is here that we don't new witch is the real origin
		final INode origin;

		if (this.edgesMap.containsKey(aParent)
				&& !this.edgesMap.containsKey(aChild)) {
			origin = aParent;
		}
		else if (!this.edgesMap.containsKey(aParent)
				&& this.edgesMap.containsKey(aChild)) {
			origin = aChild;
		}
		else if (!this.edgesMap.containsKey(aParent)
				&& !this.edgesMap.containsKey(aChild)) {
			// TODO Should never happen
		}
		else {
			boolean inversedEdge = false;

			if (aParent.getLevel() - aChild.getLevel() < 0)
				//				origin = aParent;
				origin = aChild;

			else if (aParent.getLevel() - aChild.getLevel() > 0) {
				//				origin = aChild;
				origin = aParent;
				inversedEdge = true;
			}
			else {
				// TODO
				return;
			}

			final List edges = (List) this.edgesMap.get(origin);
			// this test should not exist!
			if (edges != null) {
				final Iterator itr = edges.iterator();
				// all this edges that share the same real origin
				while (itr.hasNext()) {
					Edge next = (Edge) itr.next();
					// if a long edge
					if (next.getOrigin().getLevel()
							- next.getTArget().getLevel() > 2) {
						if (!inversedEdge) {
							if (next.getOrigin() == origin) {
								next.setDummyList(dummyList);
							}
						}
						// if inversed!
						else {
							if (next.getTArget() == origin) {
								next.setDummyList(dummyList);
							}
						}
					}
					else {
						System.err.println("Edge dummy adding problems!");
					}
				}
			}
		}
	}
}
