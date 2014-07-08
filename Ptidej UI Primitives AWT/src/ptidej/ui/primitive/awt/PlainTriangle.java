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

import java.awt.Point;
import ptidej.ui.Constants;
import ptidej.ui.RGB;
import ptidej.ui.primitive.ITriangle;

public final class PlainTriangle extends Triangle implements
		ptidej.ui.primitive.IPlainTriangle {

	private double rotationAngle = 0;
	PlainTriangle(
		final PrimitiveFactory primitiveFactory,
		final Point origin,
		final int direction,
		final RGB color) {

		super(primitiveFactory, origin, direction, color);
	}
	public void paint(final int xOffset, final int yOffset) {
		final int originX = this.getPosition().x + xOffset;
		final int originY = this.getPosition().y + yOffset;

		// the center of the origin of the arraw
		//		int originEntityX = this.originEntityPoint.x;
		//		int originEntityY = this.originEntityPoint.y;

		// 15-08-2006
		// Mohamed Kahla
		// This two tab contains the 3 points forming 
		// a triangle
		// and to close the first and last point are equals!

		int[] x;
		int[] y;
		// Yann 2007/04/03: Upupup!
		// I force any triangle to be drawn upward
		// TODO: Find out when it is set downward!?
		if (this.getDirection() == ITriangle.DIRECTIONTUP || true) {
			x =
				new int[] {
						originX,
						originX - Constants.INHERITANCE_SYMBOL_DIMENSION.width
								/ 2,
						originX + Constants.INHERITANCE_SYMBOL_DIMENSION.width
								/ 2, originX };
			y =
				new int[] {
						originY - Constants.INHERITANCE_SYMBOL_DIMENSION.height,
						originY, originY,
						originY - Constants.INHERITANCE_SYMBOL_DIMENSION.height };
		}
		//	else {
		//		x =
		//			new int[] {
		//				originX,
		//				originX
		//					- Constants.INHERITANCE_SYMBOL_DIMENSION.width / 2,
		//				originX
		//					+ Constants.INHERITANCE_SYMBOL_DIMENSION.width / 2,
		//				originX };
		//		y =
		//			new int[] {
		//				originY + Constants.INHERITANCE_SYMBOL_DIMENSION.height,
		//				originY,
		//				originY,
		//				originY + Constants.INHERITANCE_SYMBOL_DIMENSION.height };
		//	}

		// now that we have the coordonates of the origin point in 
		// the usuel base E = {i, j}
		// and let § = the rotation angle
		// 1- we determine this coordonates in the base S = {k, l}
		// where 	k = cos § i + sin § j
		// and 		l = cos (§+PI/2) i + sin (§+PI/2) j

		// let 	 P = passage matrix from S to E
		// and 	 Q =Passage matrix from E to S
		// as P * Q = I : the identy matrix
		// 				  _										     _
		// so P = K x  |   -sin (§+PI/2)	 cos (§+PI/2)    |
		//					 |_  sin §			 cos §			 _|

		// K = 1/ (cos (§+PI/2) * sin § - sin (§+PI/2) * cos § )

		// 				  _										_
		// and Q =	     |   cos §	 	cos (§+PI/2)    |
		//					 |_  sin §		sin (§+PI/2)	_|

		// test
		//		this.rotationAngle = Math.PI / 4;
		final double originXInEBase = originX;
		final double originYInEBase = originY;

		final double k =
			1 / (Math.cos(this.rotationAngle + Math.PI / 2)
					* Math.sin(this.rotationAngle) - Math
				.sin(this.rotationAngle + Math.PI / 2)
					* Math.cos(this.rotationAngle));

		final double originXInSBase =
			k
					* (-Math.sin(this.rotationAngle + Math.PI / 2)
							* originXInEBase + Math.cos(this.rotationAngle
							+ Math.PI / 2)
							* originYInEBase);

		final double originYInSBase =
			k
					* (Math.sin(this.rotationAngle) * originXInEBase - Math
						.cos(this.rotationAngle) * originYInEBase);

		//		System.out.println(" originXInSBase : " + originXInSBase
		//			+ ", originYInSBase : " + originYInSBase);

		// now we set the coordinates  of the triangles' in the S base
		final double xP1InS = originXInSBase;
		final double yP1InS =
			originYInSBase - Constants.INHERITANCE_SYMBOL_DIMENSION.height;
		final double xP2InS =
			originXInSBase - Constants.INHERITANCE_SYMBOL_DIMENSION.width / 2;
		final double yP2InS = originYInSBase;
		final double xP3InS =
			originXInSBase + Constants.INHERITANCE_SYMBOL_DIMENSION.width / 2;
		final double yP3InS = originYInSBase;

		// now we transforme them to E base
		// we use Q MATRIX
		final double xp1InE =
			Math.cos(this.rotationAngle) * xP1InS
					+ Math.cos(this.rotationAngle + Math.PI / 2) * yP1InS;
		final double yp1InE =
			Math.sin(this.rotationAngle) * xP1InS
					+ Math.sin(this.rotationAngle + Math.PI / 2) * yP1InS;

		final double xp2InE =
			Math.cos(this.rotationAngle) * xP2InS
					+ Math.cos(this.rotationAngle + Math.PI / 2) * yP2InS;
		final double yp2InE =
			Math.sin(this.rotationAngle) * xP2InS
					+ Math.sin(this.rotationAngle + Math.PI / 2) * yP2InS;

		final double xp3InE =
			Math.cos(this.rotationAngle) * xP3InS
					+ Math.cos(this.rotationAngle + Math.PI / 2) * yP3InS;
		final double yp3InE =
			Math.sin(this.rotationAngle) * xP3InS
					+ Math.sin(this.rotationAngle + Math.PI / 2) * yP3InS;

		x =
			new int[] { (int) xp1InE, (int) xp2InE, (int) xp3InE, (int) xp1InE };
		y =
			new int[] { (int) yp1InE, (int) yp2InE, (int) yp3InE, (int) yp1InE };

		this.getGraphics().setColor(
			Primitive.convertColor(Constants.BACKGROUND_COLOR));
		this.getGraphics().fillPolygon(x, y, 4);
		this.getGraphics().setColor(this.getAWTColor());
		this.getGraphics().drawPolyline(x, y, 4);
	}

	/**
	 * @author Mohamed Kahla
	 * @since  19/08/2006
	 * @param  the rotation make from the vertical default 
	 */
	public void setRotationAngle(final double aRotationAngle) {
		this.rotationAngle = aRotationAngle;
	}
}
