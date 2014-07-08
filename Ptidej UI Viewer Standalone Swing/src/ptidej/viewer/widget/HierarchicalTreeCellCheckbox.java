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

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;
import ptidej.viewer.ui.DesktopFrame;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/10/03
 */
public class HierarchicalTreeCellCheckbox extends JCheckBox {
	private static final long serialVersionUID = 1L;
	private boolean mustCursorChange;
	private final HierarchicalTreeCell treeCell;

	public HierarchicalTreeCellCheckbox(final HierarchicalTreeCell aTreeCell) {
		this.mustCursorChange = true;
		this.treeCell = aTreeCell;
		this.setBackground(Color.WHITE);
	}
	protected void fireItemStateChanged(final ItemEvent anEvent) {
		if (this.mustCursorChange) {
			DesktopFrame.getInstance().setCursor(
				Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		}

		super.fireItemStateChanged(anEvent);

		if (this.mustCursorChange) {
			DesktopFrame.getInstance().setCursor(Cursor.getDefaultCursor());
		}
	}
	public HierarchicalTreeCell getHierarchicalTreeCell() {
		return this.treeCell;
	}
	public void mustChangeCursor(boolean mustChangeCursor) {
		this.mustCursorChange = mustChangeCursor;
	}
}
