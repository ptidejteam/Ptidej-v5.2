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
import java.awt.event.MouseEvent;
import ptidej.ui.Constants;
import ptidej.ui.RGB;
import ptidej.ui.primitive.awt.Button;
import ptidej.ui.primitive.awt.Primitive;
import ptidej.ui.primitive.awt.PrimitiveFactory;

public final class GroupRectangleButton extends Button implements
		ptidej.ui.occurrence.IGroupRectangleButton {

	private final int percentage;
	private int[][] handlesPositions;

	GroupRectangleButton(
		final PrimitiveFactory primitiveFactory,
		final int percentage,
		final Point position,
		final Dimension dimension,
		final RGB color) {

		super(
			primitiveFactory,
			Integer.toString(percentage),
			position,
			dimension,
			true,
			color);

		this.percentage = percentage;
		this.setPositionAndDimension();
	}
	public void paint(final int xOffset, final int yOffset) {
		this.getGraphics().setColor(
			Primitive.convertColor(RGB
				.computePositivePercentagedColor(this.percentage)));

		this.getGraphics().drawRect(
			this.getPosition().x + xOffset,
			this.getPosition().y + yOffset,
			this.getDimension().width,
			this.getDimension().height);
		this.getGraphics().drawRect(
			this.getPosition().x + xOffset - 1,
			this.getPosition().y + yOffset - 1,
			this.getDimension().width + 2,
			this.getDimension().height + 2);

		if (this.isSelected()) {
			for (int i = 0; i < this.handlesPositions.length; i++) {
				this.getGraphics().fillRect(
					this.handlesPositions[i][0],
					this.handlesPositions[i][1],
					Constants.SOLUTION_HANDLE_DIMENSION.width,
					Constants.SOLUTION_HANDLE_DIMENSION.height);
			}
		}
		else {
			for (int i = 0; i < this.handlesPositions.length; i++) {
				this.getGraphics().drawRect(
					this.handlesPositions[i][0],
					this.handlesPositions[i][1],
					Constants.SOLUTION_HANDLE_DIMENSION.width,
					Constants.SOLUTION_HANDLE_DIMENSION.height);
			}
		}
	}
	public boolean processMouseEvent(final MouseEvent me) {
		if (me.getID() == MouseEvent.MOUSE_CLICKED) {
			boolean isInRange = false;
			for (int i = 0; i < this.handlesPositions.length; i++) {
				isInRange |=
					this.handlesPositions[i][0] <= me.getX()
							&& this.handlesPositions[i][1] <= me.getY()
							&& me.getX() <= this.handlesPositions[i][0]
									+ Constants.SOLUTION_HANDLE_DIMENSION.width
							&& me.getY() <= this.handlesPositions[i][1]
									+ Constants.SOLUTION_HANDLE_DIMENSION.height;
			}

			if (isInRange) {
				if (me.getID() == MouseEvent.MOUSE_CLICKED) {
					this.setSelected(!this.isSelected());
					return true;
				}
			}
		}

		return false;
	}
	public void setDimension(final Dimension dimension) {
		super.setDimension(dimension);
		this.setPositionAndDimension();
	}
	public void setPosition(final Point position) {
		super.setPosition(position);
		this.setPositionAndDimension();
	}
	private void setPositionAndDimension() {
		final int x = this.getPosition().x;
		final int y = this.getPosition().y;
		final int w = this.getDimension().width;
		final int h = this.getDimension().height;

		this.handlesPositions = new int[4][];
		this.handlesPositions[0] = new int[] { x, y };
		this.handlesPositions[1] =
			new int[] { x, y + h - Constants.SOLUTION_HANDLE_DIMENSION.height };
		this.handlesPositions[2] =
			new int[] { x + w - Constants.SOLUTION_HANDLE_DIMENSION.width, y };
		this.handlesPositions[3] =
			new int[] { x + w - Constants.SOLUTION_HANDLE_DIMENSION.width,
					y + h - Constants.SOLUTION_HANDLE_DIMENSION.height };
	}
}
