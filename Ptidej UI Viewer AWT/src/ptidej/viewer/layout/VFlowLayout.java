/*
 * (c) Copyright 2001, 2002 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes and Object Technology International, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * Yann-Gaël Guéhéneuc, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package ptidej.viewer.layout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;



/**
 * VFlow layout is used to layout components in a panel. It will arrange
 * components top to bottom until no more buttons fit in the same column.
 * The constructor has full control over alignment and packing within the column.
 */
public class VFlowLayout
	extends TFlowLayout {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a new VFlow Layout with a LEFT, SPREAD alignment and a
	 * default 5-unit horizontal and vertical gap.
	 */
	public VFlowLayout() {
		super(SPREAD, 5, 5);
	}

	/**
	 * Constructs a new VFlow Layout with the specified alignment and a
	 * default 5-unit horizontal and vertical gap.
	 * @param align the alignment value
	 */
	public VFlowLayout(int align) {
		super(align, 5, 5);
	}

	/**
	 * Constructs a new VFlow Layout with the specified alignment and gap
	 * values.
	 * @param align the alignment value
	 * @param hgap the horizontal gap variable
	 * @param vgap the vertical gap variable
	 */
	public VFlowLayout(int align, int hgap, int vgap) {
		super(align, hgap, vgap);
	}

	/**
	 * Lays out the container. This method will actually reshape the
	 * components in the target in order to satisfy the constraints of
	 * the VFlowLayout object. 
	 * @param target the specified component being laid out.
	 * @see IContainer
	 */
	public void layoutContainer(Container target) {
		Insets insets = target.getInsets();
		Dimension t   = target.getSize();
		int maxheight = t.height
						- (insets.top + insets.bottom + this.vgap * 2);
		int roww      = t.width
						- (insets.left + insets.right + this.hgap * 2);
		int nmembers  = target.getComponentCount();
		int x         = insets.left + this.hgap;
		int y         = 0;
		int start     = 0;
		for (int i = 0; i < nmembers; i++) {
			Component m = target.getComponent(i);
			if (m.isVisible()) {
				Dimension d = m.getPreferredSize();
				m.setSize(d.width, d.height);
				if ((y == 0) || ((y + d.height) <= maxheight)) {
					if (y > 0) {
						y += this.vgap;
					}

					y += d.height;
					roww = Math.max(roww, d.width);
				}
				else {
					moveComponents(target, x, insets.top + this.vgap, roww, 
								   maxheight - y, start, i);
					y = d.height;
					x += this.hgap + roww;
					roww  = d.width;
					start = i;
				}
			}
		}

		moveComponents(target, x, insets.top + this.vgap, roww, 
					   maxheight - y, start, nmembers);
	}

	/**
	 * Returns the minimum dimensions needed to layout the components
	 * contained in the specified target container.
	 * @param target the component which needs to be laid out 
	 * @see #preferredLayoutSize
	 */
	public Dimension minimumLayoutSize(Container target) {
		Dimension dim = new Dimension(0, 0);
		int nmembers  = target.getComponentCount();
		for (int i = 0; i < nmembers; i++) {
			Component m = target.getComponent(i);
			if (m.isVisible()) {
				Dimension d = m.getMinimumSize();
				dim.width = Math.max(dim.width, d.width);
				if (i > 0) {
					dim.height += this.vgap;
				}

				dim.height += d.height;
			}
		}

		Insets insets = target.getInsets();
		dim.width += insets.left + insets.right + this.hgap * 2;
		dim.height += insets.top + insets.bottom + this.vgap * 2;
		return dim;
	}

	/**
	 * Returns the preferred dimensions for this layout given the components
	 * in the specified target container.
	 * @param target the component which needs to be laid out
	 * @see IContainer
	 * @see #minimumLayoutSize
	 */
	public Dimension preferredLayoutSize(Container target) {
		Dimension dim = new Dimension(0, 0);
		int nmembers  = target.getComponentCount();
		for (int i = 0; i < nmembers; i++) {
			Component m = target.getComponent(i);
			if (m.isVisible()) {
				Dimension d = m.getPreferredSize();
				dim.width = Math.max(dim.width, d.width);
				if (i > 0) {
					dim.height += this.vgap;
				}

				dim.height += d.height;
			}
		}

		Insets insets = target.getInsets();
		dim.width += insets.left + insets.right + this.hgap * 2;
		dim.height += insets.top + insets.bottom + this.vgap * 2;
		return dim;
	}

	/** 
	 * Centers the elements in the specified column, if there is any slack.
	 * @param target the component which needs to be moved
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @param width the width of the column
	 * @param height the spare height left over
	 * @param colStart the beginning of the column
	 * @param colEnd the the ending of the column
	 */
	private void moveComponents(Container target, int x, int y, 
								int width, int height, int colStart, 
								int colEnd) {
		int extra = this.vgap;
		switch (this.align & SPREAD) {
			case TOP:
				break;
			case VCENTER:
				y += height / 2;
				break;
			case BOTTOM:
				y += height;
				break;
			case SPREAD:

				int h = height / (colEnd - colStart + 1);
				extra += h;
				y += h;
				break;
		}

		for (int i = colStart; i < colEnd; i++) {
			Component m = target.getComponent(i);
			if (m.isVisible()) {
				Dimension d = m.getSize();
				int nx      = x;
				switch (this.align & EXPAND) {
					case LEFT:
						nx = 0;
						break;
					case HCENTER:
						nx += (width - d.width) / 2;
						break;
					case RIGHT:
						nx += (width - d.width);
						break;
					case EXPAND:
						m.setSize(width, d.height);
						break;
				}

				m.setBounds(nx, y, width, d.height);
				y += extra + d.height;
			}
		}
	}
}