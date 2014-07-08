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

import java.util.ArrayList;
import java.util.List;
import ptidej.ui.layout.repository.sugiyama.graph.INode;

/**
 * @author kahlamoh
 * @since 24/07/2006
 * This is like a list but it adds
 * more functions and flexibilities!
 * 
 *  It's homogene too!
 *
 */
// TODO: Is this class really needed, with respect to Couple, Node, and Level?
public class Children {
	private final List children;

	public Children() {
		this.children = new ArrayList();
	}
	public void add(final INode aChild) {
		this.children.add(aChild);
	}
	public boolean remove(final INode aChild) {
		return this.children.remove(aChild);
	}
	public boolean isEmpty() {
		return this.children.isEmpty();
	}
	public void clear() {
		this.children.clear();
	}
	public boolean contains(final INode aChild) {
		return this.children.contains(aChild);
	}
	public INode[] getChildren() {
		// TODO Use proper method provided by class library
		final INode[] tabChildren = new INode[this.children.size()];
		for (int i = 0; i < tabChildren.length; i++) {
			tabChildren[i] = (INode) this.children.get(i);
		}

		return tabChildren;
	}
	public int getNbChildren() {
		return this.children.size();
	}
}
