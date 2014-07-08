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
package ptidej.ui.occurrence.swt;

import java.awt.Dimension;
import java.awt.Point;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import ptidej.ui.IVisibility;
import ptidej.ui.RGB;
import ptidej.ui.kernel.ModelGraph;

public final class GroupOccurrenceModel extends GroupOccurrenceTip implements
		IVisibility, ptidej.ui.occurrence.IGroupOccurrenceModel {

	private final ModelGraph pattern;
	private final int offsetY;

	GroupOccurrenceModel(
		final Device device,
		final GC graphics,
		final Point position,
		final ModelGraph pattern,
		final String tip,
		final RGB color) {

		super(device, graphics, position, tip, color);

		this.pattern = pattern;
		this.offsetY = this.getDimension().height;
		this.setDimension(new Dimension(Math.max(
			this.getDimension().width,
			this.pattern.getDimension().width), this.getDimension().height
				+ this.pattern.getDimension().height));
	}
	public int getVisibleElements() {
		return this.pattern.getVisibleElements();
	}
	public void paint(final int xOffset, final int yOffset) {
		super.paint(xOffset, yOffset);
		this.pattern.paint(this.getPosition().x + xOffset, this.getPosition().y
				+ yOffset + this.offsetY);
	}
	public void setVisibleElements(final int visibility) {
		this.pattern.setVisibleElements(visibility);
	}
}
