/* (c) Copyright 2001 and following years, Yann-Ga�l Gu�h�neuc,
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

package ptidej.ui.primitive.awt;

import java.awt.Dimension;
import java.awt.Point;
import ptidej.ui.Constants;
import ptidej.ui.RGB;
import ptidej.ui.kernel.IntermediaryPoint;
import ptidej.ui.primitive.IDottedDoubleSquareLine;

/**
 * @author Mohamed Kahla
 * @since  2006/05/16
 */
public final class DottedDoubleSquareLine extends DoubleSquareLine implements
		IDottedDoubleSquareLine {

	public DottedDoubleSquareLine(
		final PrimitiveFactory primitiveFactory,
		final Point origin,
		final Dimension dimension,
		final RGB color) {

		super(primitiveFactory, origin, dimension, color);
	}
	private void drawDottedLine(
		final int xOffset,
		final int yOffset,
		final int xFrom,
		final int yFrom,
		final int xTo,
		final int yTo) {

		this.getGraphics().setColor(this.getAWTColor());

		final int x1 = xFrom + xOffset;
		final int y1 = yFrom + yOffset;
		final int x2 = xTo + xOffset;
		final int y2 = yTo + yOffset;

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
	// 06-07-2006
	// Mohamed Kahla
	public void paint(final int xOffset, final int yOffset) {
		if (this.intermediaryPoints.length == 0) {
			this.getGraphics().setColor(this.getAWTColor());
			this.drawDottedLine(
				xOffset,
				yOffset,
				this.getPosition().x,
				this.getPosition().y,
				this.getDestination().x,
				this.getDestination().y);
		}
		else {
			this.paintSugiyamaLine(xOffset, yOffset);
		}
	}
	// 07-07-2006
	// Mohamed Kahla
	private void paintSugiyamaLine(final int xOffset, final int yOffset) {
		this.getGraphics().setColor(this.getAWTColor());

		final IntermediaryPoint first = this.intermediaryPoints[0];
		int xDummyPosition = first.getX();
		int yDummyPosition = first.getY();

		this.drawDottedLine(
			xOffset,
			yOffset,
			this.getPosition().x,
			this.getPosition().y,
			xDummyPosition,
			yDummyPosition);

		IntermediaryPoint lastDummy = first;

		for (int i = 1; i < this.intermediaryPoints.length; i++) {
			final IntermediaryPoint dummy = this.intermediaryPoints[i];
			xDummyPosition = dummy.getX();
			yDummyPosition = dummy.getY();

			this.drawDottedLine(
				xOffset,
				yOffset,
				lastDummy.getX(),
				lastDummy.getY(),
				xDummyPosition,
				yDummyPosition);

			lastDummy = dummy;
		}

		this.drawDottedLine(
			xOffset,
			yOffset,
			lastDummy.getX(),
			lastDummy.getY(),
			this.getDestination().x,
			this.getDestination().y);
	}
}
