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
import ptidej.ui.RGB;
import ptidej.ui.kernel.ModelGraph;
import ptidej.ui.occurrence.IGroupOccurrenceModel;
import ptidej.ui.occurrence.IGroupOccurrenceTip;
import ptidej.ui.occurrence.IGroupRectangleButton;
import ptidej.ui.primitive.IPrimitiveFactory;

public final class PrimitiveFactory extends
		ptidej.ui.primitive.awt.PrimitiveFactory implements
		ptidej.ui.occurrence.IOccurrencePrimitiveFactory {

	public static IPrimitiveFactory getInstance() {
		if (ptidej.ui.primitive.awt.PrimitiveFactory.UniqueInstance == null) {
			ptidej.ui.primitive.awt.PrimitiveFactory.UniqueInstance =
				new PrimitiveFactory();
		}

		return ptidej.ui.primitive.awt.PrimitiveFactory.UniqueInstance;
	}
	public IGroupOccurrenceModel createGroupOccurrenceModel(
		final Point position,
		final ModelGraph pattern,
		final String tip,
		final RGB color) {

		return new GroupOccurrenceModel(this, position, pattern, tip, color);
	}
	public IGroupOccurrenceTip createGroupOccurrenceTip(
		final Point position,
		final String tip,
		final RGB color) {

		return new GroupOccurrenceTip(this, position, tip, color);
	}
	public IGroupRectangleButton createGroupRectangleButton(
		final int percentageOfGray,
		final Point position,
		final Dimension dimension,
		final RGB color) {

		return new GroupRectangleButton(
			this,
			percentageOfGray,
			position,
			dimension,
			color);
	}
}
