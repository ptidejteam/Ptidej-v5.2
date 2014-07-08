/* (c) Copyright 2001 and following years, Yann-Gaël Guéhéneuc,
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
/**
 * 
 */
package ptidej.ui.primitive.swt;

import java.awt.Dimension;
import java.awt.Point;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import ptidej.ui.RGB;
import ptidej.ui.kernel.IntermediaryPoint;
import ptidej.ui.primitive.IPlainDoubleSquareLine;

/**
 * @author mohamedkahla
 * @date 	16-05-2006
 *
 */
public final class PlainDoubleSquareLine extends DoubleSquareLine implements
		IPlainDoubleSquareLine {

	// 23-05-2006
	// Mohamed Kahla
	private int splitter = 45;

	/**
	 * @param primitiveFactory
	 * @param origin
	 * @param dimension
	 * @param color
	 */
	PlainDoubleSquareLine(
		final Device device,
		final GC graphics,
		final Point origin,
		final Dimension dimension,
		final RGB color) {

		super(device, graphics, origin, dimension, color);
	}

	public void paint(final int xOffset, final int yOffset) {
		this.getGraphics().setForeground(this.getSWTColor());

		// draw the first line
		this.getGraphics().drawLine(
			this.getPosition().x + xOffset,
			this.getPosition().y + yOffset,
			this.getPosition().x + xOffset,
			this.getPosition().y + this.splitter + yOffset);

		// draw the second line
		this.getGraphics().drawLine(
			this.getPosition().x + xOffset,
			this.getPosition().y + this.splitter + yOffset,
			this.getDestination().x + xOffset,
			this.getPosition().y + this.splitter + yOffset);

		//		 draw the third line
		this.getGraphics().drawLine(
			this.getDestination().x + xOffset,
			this.getPosition().y + this.splitter + yOffset,
			this.getDestination().x + xOffset,
			this.getDestination().y + yOffset);

	}

	public void setEdgeList(final IntermediaryPoint[] someIntermediaryPoints) {
		// TODO Auto-generated method stub
	}

	public void setSplitter(final int split) {
		this.splitter = split;
	}
}
