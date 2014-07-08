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
import ptidej.ui.primitive.IAggregationSymbol;

public final class AggregationSymbol extends Symbol implements
		IAggregationSymbol {

	AggregationSymbol(
		final Device device,
		final GC graphics,
		final Point origin,
		final Dimension dimension,
		final RGB color) {

		super(device, graphics, origin, dimension, color);
	}
	public void paint(final int xOffset, final int yOffset) {
		final int[][] coordinates =
			this.computeCoordinates(
				Constants.AAC_SYMBOL_DIMENSION.width,
				Constants.AAC_SYMBOL_DIMENSION.height);

		final int[] x = coordinates[0];
		final int[] y = coordinates[1];

		final int[] vertices = new int[x.length + y.length];
		for (int i = 0, j = 0; i < x.length; i++) {
			vertices[j++] = x[i] + xOffset;
			vertices[j++] = y[i] + yOffset;
		}

		this
			.getGraphics()
			.setForeground(
				Primitive.convertColor(
					this.getDevice(),
					Constants.BACKGROUND_COLOR));
		this.getGraphics().fillPolygon(vertices);
		this.getGraphics().setForeground(this.getSWTColor());
		this.getGraphics().drawPolygon(vertices);
	}
}
