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

import ptidej.ui.kernel.Entity;
import ptidej.ui.layout.repository.sugiyama.Children;
import ptidej.ui.layout.repository.sugiyama.Parents;

/**
 * @author kahlamoh
 *
 */
public interface INode {
	public void addChild(final INode aChild);
	public void addParent(final INode aParent);
	public boolean removeChild(final INode aChild);
	public void removeParent(final INode aParent);
	public Entity getEntity();
	public INode[] getTabChildren();
	public INode[] getTabParents();
	public Children getChildren();
	public Parents getParents();
	public int getLevel();
	public void setPosition(int x, int y);
	public int getX();
	public int getY();
}
