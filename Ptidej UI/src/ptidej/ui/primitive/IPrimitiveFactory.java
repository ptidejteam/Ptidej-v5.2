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
package ptidej.ui.primitive;

import java.awt.Dimension;
import java.awt.Point;
import ptidej.ui.RGB;

public interface IPrimitiveFactory {
	IAggregationSymbol createAggregationSymbol(
		final Point origin,
		final Dimension dimension,
		final RGB color);
	IArrowSymbol createArrowSymbol(
		final Point origin,
		final Dimension dimension,
		final RGB color);
	IAssociationSymbol createAssociationSymbol(
		final Point origin,
		final Dimension dimension,
		final RGB color);
	IButton createButton(
		final String label,
		final Point position,
		final boolean centerText,
		final RGB color);
	IButton createButton(
		final String label,
		final Point position,
		final Dimension dimension,
		final boolean centerText,
		final RGB color);
	ICompositionSymbol createCompositionSymbol(
		final Point origin,
		final Dimension dimension,
		final RGB color);
	// Mohamed Kahla 06/05/17
	IDoubleSquareLine createDottedDoubleSquareLine(
		final Point origin,
		final Dimension dimension,
		final RGB color);
	IDottedLine createDottedLine(
		final Point origin,
		final Dimension dimension,
		final RGB color);
	IDottedSquareLine createDottedSquareLine(
		final Point origin,
		final Dimension dimension,
		final RGB color);
	IDottedTriangle createDottedTriangle(
		final Point origin,
		final int direction,
		final RGB color);
	IInheritanceSymbol createInheritanceSymbol(
		final Point origin,
		final Dimension dimension,
		final RGB color);
	ILabel createLabel(
		final String label,
		final Point position,
		final Dimension dimension,
		final RGB color);
	ILine createLine(
		final Point origin,
		final Dimension dimension,
		final RGB color);
	// Mohamed Kahla 06/05/17
	IDoubleSquareLine createPlainDoubleSquareLine(
		final Point origin,
		final Dimension dimension,
		final RGB color);
	IPlainSquareLine createPlainSquareLine(
		final Point origin,
		final Dimension dimension,
		final RGB color);
	IPlainTriangle createPlainTriangle(
		final Point origin,
		final int direction,
		final RGB color);
	IRectangle createRectangle(
		final Point origin,
		final Dimension dimension,
		final RGB color);
}
