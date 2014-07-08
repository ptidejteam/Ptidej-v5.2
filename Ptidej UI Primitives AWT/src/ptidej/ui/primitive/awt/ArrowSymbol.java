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
import ptidej.ui.Constants;
import ptidej.ui.RGB;
import ptidej.ui.primitive.IArrowSymbol;

public final class ArrowSymbol extends Symbol implements IArrowSymbol {
	ArrowSymbol(
		final PrimitiveFactory primitiveFactory,
		final Point origin,
		final Dimension dimension,
		final RGB color) {

		super(primitiveFactory, origin, dimension, color);
	}
	public void paint(final int xOffset, final int yOffset) {
		final int[][] coordinates =
			this.computeCoordinates(
				Constants.ARROW_SYMBOL_DIMENSION.width,
				Constants.ARROW_SYMBOL_DIMENSION.height);

		int[] x = coordinates[0];
		int[] y = coordinates[1];
		x = new int[] { x[1], x[0], x[3] };
		y = new int[] { y[1], y[0], y[3] };

		this.getGraphics().translate(xOffset, yOffset);
		this.getGraphics().setColor(this.getAWTColor());
		this.getGraphics().drawPolyline(x, y, 3);
		this.getGraphics().translate(-xOffset, -yOffset);
	}
}
