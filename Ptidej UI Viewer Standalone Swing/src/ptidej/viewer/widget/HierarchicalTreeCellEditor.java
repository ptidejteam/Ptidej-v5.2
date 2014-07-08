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

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;

import javax.swing.JTree;
import javax.swing.event.CellEditorListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeCellEditor;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/10/03
 */
public class HierarchicalTreeCellEditor implements TreeCellEditor {
	public Component getTreeCellEditorComponent(
		final JTree tree,
		final Object value,
		final boolean isSelected,
		final boolean expanded,
		final boolean leaf,
		final int row) {

		final DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
		return (Component) node.getUserObject();
	}
	public void cancelCellEditing() {
	}
	public boolean stopCellEditing() {
		return false;
	}
	public Object getCellEditorValue() {
		return this;
	}
	public boolean isCellEditable(final EventObject anEvent) {
		if (anEvent instanceof MouseEvent) {
			final MouseEvent mevt = (MouseEvent) anEvent;

			if (mevt.getClickCount() == 1) {
				return true;
			}
		}
		return false;
	}
	public boolean shouldSelectCell(final EventObject anEvent) {
		return false;
	}
	public void addCellEditorListener(final CellEditorListener l) {
	}
	public void removeCellEditorListener(final CellEditorListener l) {
	}
}
