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
package ptidej.ui.primitive.swt;

import java.awt.Dimension;
import java.awt.Point;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import ptidej.ui.Constants;
import ptidej.ui.RGB;

public final class DottedLine extends Primitive implements
		ptidej.ui.primitive.IDottedLine {

	DottedLine(
		final Device device,
		final GC graphics,
		final Point origin,
		final Dimension dimension,
		final RGB color) {

		super(device, graphics, origin, dimension, color);
	}
	public DottedLine(
		final Device device,
		final GC graphics,
		final Point origin,
		final Point destination,
		final RGB color) {

		this(device, graphics, origin, new Dimension(
			destination.x - origin.x,
			destination.y - origin.y), color);
	}
	public void paint(final int xOffset, final int yOffset) {
		this.getGraphics().setForeground(this.getSWTColor());

		// Here we go again, more maths...
		final int x1 = this.getPosition().x + xOffset;
		final int y1 = this.getPosition().y + yOffset;
		final int x2 = this.getDestination().x + xOffset;
		final int y2 = this.getDestination().y + yOffset;

		/*
		// OLD METHOD, using "high-level" maths, but not very accurate...
		if (x1 > x2 || y1 < y2) {
		    //       + origin
		    //     /
		    //   /
		    // + destination
		    x1 = destination.x;
		    x2 = origin.x;
		    y1 = destination.y;
		    y2 = origin.y;
		}
		
		double alpha = 0;
		if (x2 - x1 != 0) {
		    alpha = Math.atan((double) (y2 - y1) / (double) (x2 - x1));
		}
		else {
		    alpha = Math.PI / 2;
		}
		
		int stepX = (int) (FACTOR * Drawable.DOTLENGTH * Math.cos(alpha));
		int stepY = (int) (FACTOR * Drawable.DOTLENGTH * Math.sin(alpha));
		int factorX = stepX > 0 ? 1 : -1;
		int factorY = stepY > 0 ? 1 : -1;
		
		this.getGraphics().setColor(FOREGROUNDCOLOR);
		int movingX = x1;
		int movingY = y1;
		int nextX;
		int nextY;
		while (factorX * movingX < factorX * x2 || factorY * movingY < factorY * y2) {
		    nextX = (FACTOR * movingX + stepX) / FACTOR;
		    nextY = (FACTOR * movingY + stepY) / FACTOR;
		    this.getGraphics().drawLine(movingX, movingY, nextX, nextY);
		    movingX = (FACTOR * movingX + stepX * 2) / FACTOR;
		    movingY = (FACTOR * movingY + stepY * 2) / FACTOR;
		}
		*/

		int segStartX, segStartY;
		int segEndX, segEndY;

		final int dx = x2 - x1;
		final int dy = y2 - y1;
		final int dxdx = dx * dx;
		final int dydy = dy * dy;

		final int length = (int) Math.sqrt(dxdx + dydy);

		int i = 0;
		while (i < length) {
			segStartX = x1 + dx * i / length;
			segStartY = y1 + dy * i / length;
			i += Constants.DOT_LENGTH;
			if (i >= length) {
				segEndX = x2;
				segEndY = y2;
			}
			else {
				segEndX = x1 + dx * i / length;
				segEndY = y1 + dy * i / length;
			}
			this.getGraphics().drawLine(segStartX, segStartY, segEndX, segEndY);
			i += Constants.DOT_LENGTH;
		}
	}
}
