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
import java.util.Iterator;
import java.util.List;
import ptidej.ui.layout.repository.sugiyama.Parents;

/**
 * @author kahlamoh
 * @since 20/07/2006
 * contains all the nodes at this level
 */
// TODO: What are levels for? If it is to hold information
// about the level in which nodes belong to, why put them
// first in List of List (HierarchiesBuilder) then put them
// in levels?
public class Level {
	private final int levelIndex;
	private final List nodes;

	public Level(final List someNodes, int index) {
		this.nodes = someNodes;
		this.levelIndex = index;
	}
	public Level(final int index) {
		this.nodes = new ArrayList();
		this.levelIndex = index;
	}
	public void addNode(final INode aNode) {
		this.nodes.add(aNode);
	}
	public void removeNode(final INode aNode) {
		this.nodes.remove(aNode);
	}
	public List getChildren(final INode aParent) {
		final List children = new ArrayList();
		final Iterator itr = this.nodes.iterator();
		while (itr.hasNext()) {
			final INode next = (Node) itr.next();
			final Parents parents = next.getParents();
			if (parents.contains(aParent)) {
				children.add(next);
			}
		}
		return children;
	}
	public INode[] getTabChildren(final INode aParent) {
		final List children = this.getChildren(aParent);
		final INode[] tabChildren = new Node[children.size()];
		for (int i = 0; i < tabChildren.length; i++) {
			tabChildren[i] = (INode) children.get(i);
		}

		return tabChildren;
	}
	public List getNodes() {
		return this.nodes;
	}
	public INode[] getTabNodes() {
		final INode[] tabChildren = new Node[this.nodes.size()];
		for (int i = 0; i < tabChildren.length; i++) {
			tabChildren[i] = (INode) this.nodes.get(i);
		}

		return tabChildren;
	}
	public int getLevelIndex() {
		return this.levelIndex;
	}
}
