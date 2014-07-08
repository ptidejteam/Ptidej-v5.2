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

import java.awt.Point;
import ptidej.ui.kernel.Entity;
import ptidej.ui.layout.repository.sugiyama.Children;
import ptidej.ui.layout.repository.sugiyama.Parents;

/**
 *  date : 22/05/2006
 * @Author : Mohamed Kahla  
 */
// TODO: If this class exists to "replace" the Entity, then, once it is
// built, it should be impossible to access to the underlying entity
// directly but only through delegation, if needed.
public class Node implements INode {
	// Modifications done 
	// 24-07-2006
	private Parents parents; // contains the parents and there channel to draw
	private Children children;
	private Entity entity; // the entity that this node represents
	private int level; // the level of this node

	public Node(final Entity anEntity, final int aLevel) {
		this.entity = anEntity;
		this.level = aLevel;
		this.parents = new Parents(); // associate parents and channels 
		this.children = new Children();
	}

	public void addChild(final INode aChild) {
		// if the child doesn't exist in this list
		// 12-07-2006
		if (!this.children.contains(aChild)) {
			this.children.add(aChild);
		}
	}

	public void addParent(final INode aParent) {
		// if the parent dosen't exist in this Map
		// 12-07-2006
		if (!this.parents.contains(aParent)) {
			this.parents.add(aParent); // the channel not set
		}
	}

	public boolean removeChild(final INode aChild) {
		return this.children.remove(aChild);
	}

	public void removeParent(final INode aParent) {
		this.parents.remove(aParent);
	}

	public Entity getEntity() {
		return this.entity;
	}

	public INode[] getTabChildren() {
		return this.children.getChildren();
	}

	public INode[] getTabParents() {
		return this.parents.getParents();
	}

	public Children getChildren() {
		return this.children;
	}

	public Parents getParents() {
		return this.parents;
	}

	public int getLevel() {
		return this.level;
	}

	public void setParentChannel(final INode aParent, int aChannel) {
		this.parents.setChannel(aParent, aChannel);
	}

	public void setPosition(final int x, final int y) {
		this.entity.setPosition(new Point(x, y));
	}

	public int getX() {
		return this.entity.getPosition().x;
	}

	public int getY() {
		return this.entity.getPosition().y;
	}

	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append(this.entity);
		buffer.append(" at level ");
		buffer.append(this.level);
		return buffer.toString();
	}
}