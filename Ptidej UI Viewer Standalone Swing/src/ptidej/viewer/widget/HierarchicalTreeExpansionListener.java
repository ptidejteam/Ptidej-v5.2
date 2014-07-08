/*
 * (c) Copyright 2001-2007 Yann-Gaël Guéhéneuc,
 * University of Montréal.
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
package ptidej.viewer.widget;

import java.awt.Dimension;

import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.ExpandVetoException;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/10/01
 */
public class HierarchicalTreeExpansionListener
	implements TreeWillExpandListener, javax.swing.event.TreeExpansionListener {

	private double width;

	public void treeWillCollapse(final TreeExpansionEvent event)
		throws ExpandVetoException {
	}
	public void treeWillExpand(final TreeExpansionEvent event)
		throws ExpandVetoException {

		this.width = ((JTree) event.getSource()).getSize().getWidth();
	}
	public void treeCollapsed(final TreeExpansionEvent event) {
	}
	public void treeExpanded(final TreeExpansionEvent event) {
		final JTree tree = ((JTree) event.getSource());
		tree.setMaximumSize(
			new Dimension(
				(int) this.width,
				(int) tree.getSize().getHeight()));
	}
}
