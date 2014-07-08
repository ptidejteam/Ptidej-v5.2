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
package ptidej.viewer.awt;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

public final class Separator extends Component {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 100;
	private static final int HEIGHT = 12;
	private static final Dimension PREFERRED_SIZE =
		new Dimension(Separator.WIDTH, Separator.HEIGHT);

	public Separator() {
		this.setName("Separator");
	}
	public Dimension getMinimumSize() {
		return this.getPreferredSize();
	}
	public Dimension getPreferredSize() {
		return Separator.PREFERRED_SIZE;
	}
	public void paint(final Graphics g) {
		final int y = this.getHeight() / 2 - 2;
		final int beg = 5;
		final int end = this.getWidth() - beg * 2;
		final int mid = 5 + end / 2;

		g.setColor(Color.BLACK);
		g.fillRect(beg, y + 1, mid - beg - 10, 2);
		g.fillRect(mid + 10, y + 1, end - mid - 10, 2);
		g.fillRect(mid - 2, y + 0, 4, 4);
		g.setColor(Color.WHITE);
		g.drawLine(beg, y + 1, mid - 12, y + 1);
		g.drawLine(beg, y + 2, beg, y + 2);
		g.drawLine(mid + 10, y + 1, end - 2, y + 1);
		g.drawLine(mid + 10, y + 2, mid + 10, y + 2);
		g.drawLine(mid - 2, y + 0, mid, y + 0);
		g.drawLine(mid - 2, y + 0, mid - 2, y + 3);
		g.setColor(this.getBackground());
		g.fillRect(mid - 1, y + 1, 2, 2);
	}
}