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

public final class DottedSquareLine extends SquareLine implements
		ptidej.ui.primitive.IDottedSquareLine {

	DottedSquareLine(
		final Device device,
		final GC graphics,
		final Point origin,
		final Dimension dimension,
		final RGB color) {

		super(device, graphics, origin, dimension, color);
	}
	public void paint(final int xOffset, final int yOffset) {
		final Point origin = this.getPosition();
		final Point destination = this.getDestination();

		this.getGraphics().setForeground(this.getSWTColor());

		if (origin.y < destination.y) {
			for (int movingY = origin.y; movingY < destination.y; movingY +=
				Constants.DOT_LENGTH * 2) {

				this.getGraphics().drawLine(
					origin.x + xOffset,
					movingY + yOffset,
					origin.x + xOffset,
					Math.min(movingY + Constants.DOT_LENGTH, destination.y)
							+ yOffset);
			}
		}
		else {
			for (int movingY = destination.y; movingY < origin.y; movingY +=
				Constants.DOT_LENGTH * 2) {

				this.getGraphics().drawLine(
					origin.x + xOffset,
					movingY + yOffset,
					origin.x + xOffset,
					Math.min(movingY + Constants.DOT_LENGTH, origin.y)
							+ yOffset);
			}
		}

		if (origin.x < destination.x) {
			for (int movingX = origin.x; movingX < destination.x; movingX +=
				Constants.DOT_LENGTH * 2) {

				this.getGraphics().drawLine(
					movingX + xOffset,
					destination.y + yOffset,
					Math.min(movingX + Constants.DOT_LENGTH, destination.x)
							+ xOffset,
					destination.y + yOffset);
			}
		}
		else {
			for (int movingX = destination.x; movingX < origin.x; movingX +=
				Constants.DOT_LENGTH * 2) {

				this.getGraphics().drawLine(
					movingX + xOffset,
					destination.y + yOffset,
					Math.min(movingX + Constants.DOT_LENGTH, origin.x)
							+ xOffset,
					destination.y + yOffset);
			}
		}
	}
}
