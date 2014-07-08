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
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Point;
import ptidej.ui.Constants;
import ptidej.ui.RGB;

public final class Label extends Primitive implements
		ptidej.ui.primitive.ILabel {
	private static FontMetrics cacheFontMetrics;
	private static FontMetrics computeFontMetrics() {
		if (Label.cacheFontMetrics == null) {
			Frame frame = new Frame();
			frame.setVisible(true);
			Label.cacheFontMetrics = frame.getGraphics().getFontMetrics();
			frame.dispose();
			frame = null;
		}
		return Label.cacheFontMetrics;
	}
	public static int getHeight() {
		return Label.computeFontMetrics().getHeight() / 2;
	}
	public static int getWidth(final String someText) {
		return Label.computeFontMetrics().stringWidth(someText);
	}

	private String label;
	private int minH;
	private int minW;
	Label(
		final PrimitiveFactory primitiveFactory,
		final String s,
		final Point position,
		final Dimension dimension,
		final RGB color) {

		super(primitiveFactory, position, dimension, color);
		this.setLabel(s);
	}
	public void paint(final int xOffset, final int yOffset) {
		final int x = this.getPosition().x + xOffset;
		final int y = this.getPosition().y + yOffset;
		final int h = this.getDimension().height;
		final int w = this.getDimension().width;

		this.getGraphics().setColor(
			Primitive.convertColor(Constants.BACKGROUND_COLOR));
		this.getGraphics().fillRect(x, y, w, h);
		this.getGraphics().setColor(this.getAWTColor());
		this.getGraphics().drawString(
			this.label,
			x + (w - this.minW) / 2,
			y + (h - this.minH) / 2 + this.minH);
	}
	private void setLabel(final String s) {
		this.label = s;
		this.minW = Label.getWidth(s);
		this.minH = Label.getHeight();
	}
}
