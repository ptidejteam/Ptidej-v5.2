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
package ptidej.ui.primitive.awt;

import java.awt.Dimension;
import java.awt.Point;
import ptidej.ui.RGB;

public abstract class Symbol extends Primitive {
	Symbol(
		final PrimitiveFactory primitiveFactory,
		final Point origin,
		final Dimension dimension,
		final RGB color) {

		super(primitiveFactory, origin, dimension, color);
	}
	int[][] computeCoordinates(final int symbolWidth, final int symbolHeight) {
		final int xOrg = this.getPosition().x;
		final int yOrg = this.getPosition().y;
		int xDest = 0;
		int yDest = 0;
		int xMid1 = 0;
		int yMid1 = 0;
		int xMid2 = 0;
		int yMid2 = 0;

		if (this.getDimension().width == 0 && this.getDimension().height > 0) {
			xDest = xOrg;
			yDest = yOrg + -symbolHeight;

			final int xMid = (xOrg + xDest) / 2;
			final int yMid = (yOrg + yDest) / 2;
			xMid1 = xMid - symbolWidth / 2;
			yMid1 = yMid;
			xMid2 = xMid + symbolWidth / 2;
			yMid2 = yMid;
		}
		else if (this.getDimension().width == 0
				&& this.getDimension().height < 0) {

			xDest = xOrg;
			yDest = yOrg + symbolHeight;

			final int xMid = (xOrg + xDest) / 2;
			final int yMid = (yOrg + yDest) / 2;
			xMid1 = xMid - symbolWidth / 2;
			yMid1 = yMid;
			xMid2 = xMid + symbolWidth / 2;
			yMid2 = yMid;
		}
		else if (this.getDimension().width > 0
				&& this.getDimension().height == 0) {

			xDest = xOrg - symbolWidth;
			yDest = yOrg;

			final int xMid = (xOrg + xDest) / 2;
			final int yMid = (yOrg + yDest) / 2;
			xMid1 = xMid - symbolWidth / 2;
			yMid1 = yMid - symbolHeight / 4;
			xMid2 = xMid - symbolWidth / 2;
			yMid2 = yMid + symbolHeight / 4;
		}
		else if (this.getDimension().width < 0
				&& this.getDimension().height == 0) {

			xDest = xOrg + symbolWidth;
			yDest = yOrg;

			final int xMid = (xOrg + xDest) / 2;
			final int yMid = (yOrg + yDest) / 2;
			xMid1 = xMid + symbolWidth / 2;
			yMid1 = yMid - symbolHeight / 4;
			xMid2 = xMid + symbolWidth / 2;
			yMid2 = yMid + symbolHeight / 4;
		}
		else if (this.getDimension().width < 0) {
			final double slope =
				(double) this.getDimension().height
						/ (double) this.getDimension().width;
			final double alpha = Math.atan(slope);

			xDest = (int) Math.round(xOrg + symbolHeight * Math.cos(alpha));
			yDest = (int) Math.round(yOrg + symbolHeight * Math.sin(alpha));

			final double xMid = (xOrg + xDest) / 2;
			final double yMid = (yOrg + yDest) / 2;
			xMid1 = (int) Math.round(xMid - symbolWidth * Math.sin(alpha) / 2);
			yMid1 = (int) Math.round(yMid + symbolWidth * Math.cos(alpha) / 2);
			xMid2 = (int) Math.round(xMid + symbolWidth * Math.sin(alpha) / 2);
			yMid2 = (int) Math.round(yMid - symbolWidth * Math.cos(alpha) / 2);
		}
		else if (this.getDimension().width > 0) {
			final double slope =
				(double) this.getDimension().height
						/ (double) this.getDimension().width;
			final double alpha = Math.atan(slope);

			xDest = (int) Math.round(xOrg - symbolHeight * Math.cos(alpha));
			yDest = (int) Math.round(yOrg - symbolHeight * Math.sin(alpha));

			final double xMid = (xOrg + xDest) / 2;
			final double yMid = (yOrg + yDest) / 2;
			xMid1 = (int) Math.round(xMid - symbolWidth * Math.sin(alpha) / 2);
			yMid1 = (int) Math.round(yMid + symbolWidth * Math.cos(alpha) / 2);
			xMid2 = (int) Math.round(xMid + symbolWidth * Math.sin(alpha) / 2);
			yMid2 = (int) Math.round(yMid - symbolWidth * Math.cos(alpha) / 2);
		}

		return new int[][] { { xOrg, xMid1, xDest, xMid2 },
				{ yOrg, yMid1, yDest, yMid2 } };
	}
}
