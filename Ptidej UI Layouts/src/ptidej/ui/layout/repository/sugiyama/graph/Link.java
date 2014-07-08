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
/**
 * 
 */
package ptidej.ui.layout.repository.sugiyama.graph;

/**
 * @author kahlamoh
 * @since 2006/07/21
 *
 */
public class Link {
	private final int linkType;
	private final INode parent;
	private final INode child;
	public Link(final int aLinkType, final INode aParent, final INode aChild) {
		this.linkType = aLinkType;
		this.parent = aParent;
		this.child = aChild;
	}
	public INode getParent() {
		return this.parent;
	}
	public INode getChild() {
		return this.child;
	}
	public int getLinkType() {
		return this.linkType;
	}
}
