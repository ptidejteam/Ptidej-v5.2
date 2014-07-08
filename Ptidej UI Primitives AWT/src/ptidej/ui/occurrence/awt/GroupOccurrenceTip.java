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
package ptidej.ui.occurrence.awt;

import java.awt.Dimension;
import java.awt.Point;
import java.util.StringTokenizer;
import ptidej.ui.Constants;
import ptidej.ui.RGB;
import ptidej.ui.primitive.awt.Primitive;
import ptidej.ui.primitive.awt.PrimitiveFactory;

public class GroupOccurrenceTip extends Primitive implements
		ptidej.ui.occurrence.IGroupOccurrenceTip {

	private final String tip;
	private final int lineHeight;

	GroupOccurrenceTip(
		final PrimitiveFactory primitiveFactory,
		final Point position,
		final String tip,
		final RGB color) {

		super(primitiveFactory, position, Constants.NULL_DIMENSION, color);

		this.setPosition(new Point(
			position.x + Constants.TIP_GAP.width,
			position.y + Constants.TIP_GAP.height));

		final StringBuffer buffer = new StringBuffer();
		char c;
		for (int i = 0; i < tip.length(); i++) {
			c = tip.charAt(i);
			if (c != '\t') {
				buffer.append(c);
			}
			else {
				for (int j = 0; j < Constants.TAB_LENGTH; j++) {
					buffer.append(' ');
				}
			}
		}

		this.tip = buffer.toString();
		this.lineHeight = this.getGraphics().getFontMetrics().getHeight();

		final StringTokenizer tokenizer = new StringTokenizer(this.tip, "\n");
		final int numberOfLines = tokenizer.countTokens();
		int longestLineWidth = 0;
		while (tokenizer.hasMoreTokens()) {
			longestLineWidth =
				Math.max(longestLineWidth, this
					.getGraphics()
					.getFontMetrics()
					.stringWidth(tokenizer.nextToken()));
		}
		longestLineWidth += Constants.TIP_GAP.width;

		this.setDimension(new Dimension(longestLineWidth, numberOfLines
				* this.lineHeight + Constants.TIP_GAP.height));
	}
	public int getVisibleElements() {
		return 0;
	}
	public void paint(final int xOffset, final int yOffset) {
		this.getGraphics().setColor(
			Primitive.convertColor(Constants.TIP_BACKGROUND_COLOR));
		this.getGraphics().fillRect(
			this.getPosition().x + xOffset,
			this.getPosition().y + yOffset,
			this.getDimension().width,
			this.getDimension().height);
		this.getGraphics().setColor(
			Primitive.convertColor(Constants.FOREGROUND_COLOR));
		this.getGraphics().drawRect(
			this.getPosition().x + xOffset,
			this.getPosition().y + yOffset,
			this.getDimension().width,
			this.getDimension().height);

		final StringTokenizer tokenizer = new StringTokenizer(this.tip, "\n");
		int yPos = this.lineHeight;
		while (tokenizer.hasMoreTokens()) {
			this.getGraphics().drawString(
				tokenizer.nextToken(),
				this.getPosition().x + xOffset + Constants.TIP_GAP.width / 2,
				this.getPosition().y + yOffset + yPos);
			yPos += this.lineHeight;
		}
	}
	public void setVisibleElements(final int visibility) {
	}
	public String toString() {
		return this.tip;
	}
}
