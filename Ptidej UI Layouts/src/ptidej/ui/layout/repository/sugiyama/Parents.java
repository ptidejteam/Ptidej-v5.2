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
 * * This is like a list but it adds
 * more functions and flexibilities!
 * 
 *  It's homogene too!
 *
 */
// TODO: Is this class really needed, with respect to Couple, Node, and Level?
public class Parents {
	private List parents;
	// Channels!
	private ArrayList channels;

	public Parents() {
		this.parents = new ArrayList();
		this.channels = new ArrayList();
	}
	public void add(final INode aParent) {
		this.parents.add(aParent);
		// -1 for not set yet
		this.channels.add(new Integer(-1));
	}
	public boolean remove(final INode aParent) {
		// here a special attention to keep informations
		// coherents
		int parentIndex = this.parents.indexOf(aParent);
		if (parentIndex >= 0) {
			this.channels.remove(parentIndex);
			return this.parents.remove(aParent);
		}
		return false;
	}
	public void clear() {
		// Consistency!
		this.channels.clear();
		this.parents.clear();
	}
	public boolean contains(final INode aParent) {
		return this.parents.contains(aParent);
	}
	public boolean isEmpty() {
		return this.parents.isEmpty();
	}
	public INode[] getParents() {
		// TODO Use proper method provided by class library
		final INode[] tabParent = new INode[this.parents.size()];
		for (int i = 0; i < tabParent.length; i++) {
			tabParent[i] = (INode) this.parents.get(i);
		}

		return tabParent;
	}
	public int getNbParents() {
		return this.parents.size();
	}
	public void setChannel(final INode aParent, int aChannel) {
		int parentIndex = this.parents.indexOf(aParent);
		// if parent not found! 
		// return -1
		this.channels.set(parentIndex, new Integer(aChannel));
	}
	public int getChannel(final INode aParent) {
		int parentIndex = this.parents.indexOf(aParent);
		if (parentIndex >= 0) {
			Integer channel = (Integer) this.channels.get(parentIndex);
			return channel.intValue();
		}
		return -1;
	}
}