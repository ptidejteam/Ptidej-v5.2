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
import ptidej.ui.RGB;
import ptidej.ui.kernel.IntermediaryPoint;
import ptidej.ui.primitive.IPlainDoubleSquareLine;

/**
 * @author Mohamed Kahla
 * @since  2006/05/16
 *
 */
public final class PlainDoubleSquareLine extends DoubleSquareLine implements
		IPlainDoubleSquareLine {

	public PlainDoubleSquareLine(
		final PrimitiveFactory primitiveFactory,
		final Point origin,
		final Dimension dimension,
		final RGB color) {
		super(primitiveFactory, origin, dimension, color);

	}
	// 18-05-2006
	// Mohamed Kahla
	public void paint(final int xOffset, final int yOffset) {
		if (this.intermediaryPoints.length == 0) {
			this.getGraphics().setColor(this.getAWTColor());
			this.getGraphics().drawLine(
				this.getPosition().x + xOffset,
				this.getPosition().y + yOffset,
				this.getDestination().x + xOffset,
				this.getDestination().y + yOffset);
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

		this.getGraphics().drawLine(
			this.getPosition().x + xOffset,
			this.getPosition().y + yOffset,
			xDummyPosition + xOffset,
			yDummyPosition + yOffset);

		IntermediaryPoint lastDummy = first;

		for (int i = 1; i < this.intermediaryPoints.length; i++) {
			final IntermediaryPoint dummy = this.intermediaryPoints[i];
			xDummyPosition = dummy.getX();
			yDummyPosition = dummy.getY();

			this.getGraphics().drawLine(
				lastDummy.getX() + xOffset,
				lastDummy.getY() + yOffset,
				xDummyPosition + xOffset,
				yDummyPosition + yOffset);

			lastDummy = dummy;
		}

		this.getGraphics().drawLine(
			lastDummy.getX() + xOffset,
			lastDummy.getY() + yOffset,
			this.getDestination().x + xOffset,
			this.getDestination().y + yOffset);
	}
}
