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
package ptidej.ui.kernel;

import java.awt.Dimension;
import java.awt.Point;
import ptidej.ui.IVisibility;
import ptidej.ui.primitive.IPrimitiveFactory;
import ptidej.ui.primitive.ISymbol;

// Yann 2014/04/09: Spring cleaning!
// I cleaned up a heavy mess by making inheritance
// relationships similar to other relationships...
// Factor lots of code, deleted lots of code, happpy :-)
public abstract class AbstractInheritance extends Relationship {
	// The list of dummy nodes 
	protected IntermediaryPoint[] intermediaryPoints;
	protected Dimension dimensionWithoutIntermediaryPoints;

	// The splitter for the line
	protected int splitter = 45;

	protected AbstractInheritance(
		IPrimitiveFactory primitiveFactory,
		Entity origin,
		Entity target) {

		super(primitiveFactory, 0, origin, target);
		this.intermediaryPoints = new IntermediaryPoint[0];
	}
	protected ISymbol getOriginSymbol() {
		return null;
	}
	protected String getSymbol() {
		return "---|>";
	}
	protected ISymbol getTargetSymbol() {
		// Yann 2014/04/09: Position vs. Origin
		// The position should be the position of the last
		// intermediary point, to draw nice symbols aligned
		// with this last point while the origin is that of
		// the origin entity.
		if (this.intermediaryPoints.length == 0) {
			return this.getPrimitiveFactory().createInheritanceSymbol(
				this.findIntersectionPointWithTarget(),
				this.dimensionWithoutIntermediaryPoints,
				this.getColor());
		}
		else {
			final IntermediaryPoint lastIntermediaryPoint =
				this.intermediaryPoints[this.intermediaryPoints.length - 1];

			final Point newOrigin =
				new Point(
					lastIntermediaryPoint.getX(),
					lastIntermediaryPoint.getY());

			final Dimension newDimension =
				new Dimension(
					this.getDestination().x - newOrigin.x,
					this.getDestination().y - newOrigin.y);

			return this.getPrimitiveFactory().createInheritanceSymbol(
				this.findIntersectionPointWithTarget(newOrigin, newDimension),
				newDimension,
				this.getColor());
		}
	}
	public int getVisibilityDisplay() {
		return IVisibility.HIERARCHY_DISPLAY_ELEMENTS;
	}
	public int getVisibilityName() {
		return IVisibility.HIERARCHY_NAMES;
	}
	/**
	 * @author Mohamed Kahla
	 * @since 06/07/2006
	 * @version 06072006H????
	 * 
	 * This method sets the the list of dummy nodes  
	 * in this edge from the Sugiyama's metods (DrawLines)
	 */
	public final void setEdgesList(
		final IntermediaryPoint[] someIntermediaryPoints) {

		this.intermediaryPoints = someIntermediaryPoints;
	}
	/**
	 * @author Mohamed Kahla
	 * @since 23/05/2006
	 * @version 23052006H1330
	 * 
	 * This method sets the correct Splitter Value for 
	 * this link from the Sugiyama's metods (DrawLines)
	 */
	public final void setSplitter(final int aSplit) {
		this.splitter = aSplit;
	}
	protected void setDimensionSpecifics(final Dimension dimension) {
		this.dimensionWithoutIntermediaryPoints = new Dimension(dimension);

		// Yann 2014/04/28: Dimensions!
		// It is possible that one or several intermediate points
		// are way beyong the box created by the entities at the 
		// origin and destination of the relationship. Therefore,
		// these points could disappear "outside" of the visible
		// canvas if not taken into account when computing the
		// dimensions of the relationship.
		// See also ptidej.ui.kernel.ModelGraph.computeDimension()
		// I assume that the intermediary points can only be to the
		// right of the line existing between origin and target.
		int minX = this.getPosition().x;
		int maxX = this.getPosition().x;

		for (int i = 0; i < this.intermediaryPoints.length; i++) {
			final IntermediaryPoint intermediaryPoint =
				this.intermediaryPoints[i];

			minX = Math.min(minX, intermediaryPoint.getX());
			maxX = Math.max(maxX, intermediaryPoint.getX());
		}
		dimension.setSize(
			maxX - minX,
			this.dimensionWithoutIntermediaryPoints.height);
	}
}
