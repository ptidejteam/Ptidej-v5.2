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
import ptidej.ui.primitive.IAssociationSymbol;

public final class AssociationSymbol extends Symbol implements
		IAssociationSymbol {

	AssociationSymbol(
		final PrimitiveFactory primitiveFactory,
		final Point origin,
		final Dimension dimension,
		final RGB color) {

		super(primitiveFactory, origin, dimension, color);
	}
	public void paint(final int xOffset, final int yOffset) {
		// Yann 2007/04/01: April's fool's fix!
		// I pretend the direction of the arrow is upsidedown
		// for symbols at the origin of an arrow.
		final Dimension dimension = this.getDimension();
		this.setDimension(new Dimension(-dimension.width, -dimension.height));

		final int[][] coordinates =
			this.computeCoordinates(
				Constants.AAC_SYMBOL_DIMENSION.width,
				Constants.AAC_SYMBOL_DIMENSION.height);

		this.setDimension(new Dimension(dimension.width, dimension.height));

		final int[] x = coordinates[0];
		final int[] y = coordinates[1];

		this.getGraphics().translate(xOffset, yOffset);
		this.getGraphics().setColor(
			Primitive.convertColor(Constants.BACKGROUND_COLOR));
		this.getGraphics().fillPolygon(x, y, 4);
		this.getGraphics().translate(-xOffset, -yOffset);

		for (int i = 0; i < 4; i++) {
			new DottedLine(
				this.getPrimitiveFactory(),
				new Point(x[i], y[i]),
				new Point(x[i == 3 ? 0 : i + 1], y[i == 3 ? 0 : i + 1]),
				this.getRGBColor()).paint(0, 0);
		}
	}
}
