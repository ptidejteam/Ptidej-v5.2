/*
 * (c) Copyright 2001-2004 Yann-Gaël Guéhéneuc,
 * University of Montréal.
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
package ptidej.ui.analysis.repository.comparator.ui;

import java.awt.Dimension;
import java.awt.Point;

import padl.kernel.IInterface;
import padl.kernel.IConstituentOfModel;
import ptidej.ui.RGB;
import ptidej.ui.kernel.Interface;
import ptidej.ui.kernel.builder.Builder;
import ptidej.ui.primitive.IPrimitiveFactory;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/12/17
 */
public class AddedInterface extends Interface {
	public AddedInterface(
		final IPrimitiveFactory aPrimitiveFactory,
		final Builder aBuilder,
		final IConstituentOfModel anEntity) {

		super(aPrimitiveFactory, aBuilder, (IInterface) anEntity);
	}
	protected String getStereotype() {
		return "<<interface>>";
	}
	public void paint(final int xOffset, final int yOffset) {
		super.paint(xOffset, yOffset);

		// Yann 2005/12/06: Emphasisis!
		// I create a thiker rectangle for better
		// output on paper :-)
		final int thickness = 5;
		for (int i = 0; i < thickness; i++) {
			final Point position =
				new Point(
					(int) this.getPosition().getX() - i,
					(int) this.getPosition().getY() - i);
			final Dimension dimension =
				new Dimension(
					(int) this.getDimension().getWidth() + 2 * i,
					(int) this.getDimension().getHeight() + 2 * i);
			this
				.getPrimitiveFactory()
				.createRectangle(position, dimension, new RGB(0, 255, 0))
				.paint(xOffset, yOffset);
		}
	}
}
