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
import ptidej.ui.IVisibility;
import ptidej.ui.RGB;
import ptidej.ui.kernel.ModelGraph;
import ptidej.ui.occurrence.IGroupOccurrenceModel;

public final class GroupOccurrenceModel extends GroupOccurrenceTip implements
		IVisibility, IGroupOccurrenceModel {

	private final ModelGraph modelGraph;
	private final int offsetY;
	private final int baseHeight;
	private final Dimension originalDimension;

	protected GroupOccurrenceModel(
		final PrimitiveFactory primitiveFactory,
		final Point position,
		final ModelGraph modelGraph,
		final String tip,
		final RGB color) {

		super(primitiveFactory, position, tip, color);

		this.modelGraph = modelGraph;
		this.offsetY = this.getDimension().height;
		this.baseHeight = this.getDimension().height;
		this.setDimension();
		this.originalDimension = this.getDimension();
	}
	public int getVisibleElements() {
		return this.modelGraph.getVisibleElements();
	}
	public void paint(final int xOffset, final int yOffset) {
		super.paint(xOffset, yOffset);
		this.modelGraph.paint(this.getPosition().x, this.getPosition().y
				+ this.offsetY);
	}
	private void setDimension() {
		this.setDimension(new Dimension(Math.max(
			this.getDimension().width,
			this.modelGraph.getDimension().width), this.baseHeight
				+ this.modelGraph.getDimension().height));
	}
	public void setVisibleElements(final int visibility) {
		// Yann 2002/08/06: Visible elements
		// When I change the visibility of elements, I must
		// rebuild the sub-pattern and recompute the
		// dimension of the solution pattern tip.

		// Yann 2007/04/02: Ghosts again...
		// I never want to show ghosts in group occurrences.
		final int noGhostVisibility =
			visibility & (IVisibility.GHOST_ENTITIES_DISPLAY ^ -1);

		this.modelGraph.setVisibleElements(noGhostVisibility);
		this.modelGraph.build();

		this.setDimension(this.originalDimension);
		this.setDimension();
	}
	public String toString() {
		return super.toString();
	}
}
