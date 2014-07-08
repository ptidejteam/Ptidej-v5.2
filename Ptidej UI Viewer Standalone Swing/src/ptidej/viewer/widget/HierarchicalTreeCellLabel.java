/*
 * (c) Copyright 2001-2004 Yann-Gaël Guéhéneuc,
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
import java.awt.Graphics;
import java.awt.SystemColor;

import javax.swing.JLabel;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2007/09/01
 */
public class HierarchicalTreeCellLabel extends JLabel {
	private static final long serialVersionUID = 1L;
	private boolean isSelected;

	public HierarchicalTreeCellLabel() {
		this.setBackground(Color.WHITE);
	}
	public boolean isSelected() {
		return this.isSelected;
	}
	public void paint(final Graphics g) {
		// This is a hack to paint the background. Normally a JLabel 
		// can paint its own background, but due to an apparent bug 
		// or limitation in the TreeCellRenderer, the paint method 
		// is required to handle this.
		// Set the correct background colour
		final Color color =
			this.isSelected ? SystemColor.textHighlight : Color.WHITE;
		g.setColor(color);

		// Draw a rectangle in the background of the cell
		g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

		super.paint(g);
	}
	public void setSelected(final boolean isSelected) {
		this.isSelected = isSelected;
		if (!this.isSelected) {
			this.setForeground(Color.BLACK);
		}
		else {
			this.setForeground(Color.WHITE);
		}
	}
}
