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
import java.util.HashMap;
import java.util.Map;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import ptidej.ui.RGB;
import ptidej.ui.kernel.ModelGraph;

public final class PrimitiveFactory extends
		ptidej.ui.primitive.swt.PrimitiveFactory implements
		ptidej.ui.occurrence.IOccurrencePrimitiveFactory {

	private static Map UniqueInstance = new HashMap(1);

	public static ptidej.ui.primitive.IPrimitiveFactory getInstance(
		final Device device,
		final GC graphics) {

		if (PrimitiveFactory.UniqueInstance.get(graphics) == null) {
			final PrimitiveFactory primitiveFactory = new PrimitiveFactory();
			primitiveFactory.setDevice(device);
			primitiveFactory.setGraphics(graphics);
			PrimitiveFactory.UniqueInstance.put(graphics, primitiveFactory);
		}

		return (ptidej.ui.primitive.IPrimitiveFactory) PrimitiveFactory.UniqueInstance
			.get(graphics);
	}

	public ptidej.ui.occurrence.IGroupOccurrenceModel createGroupOccurrenceModel(
		final Point position,
		final ModelGraph pattern,
		final String tip,
		final RGB color) {

		return new GroupOccurrenceModel(
			this.getDevice(),
			this.getGraphics(),
			position,
			pattern,
			tip,
			color);
	}
	public ptidej.ui.occurrence.IGroupOccurrenceTip createGroupOccurrenceTip(
		final Point position,
		final String tip,
		final RGB color) {

		return new GroupOccurrenceTip(
			this.getDevice(),
			this.getGraphics(),
			position,
			tip,
			color);
	}
	public ptidej.ui.occurrence.IGroupRectangleButton createGroupRectangleButton(
		final int percentageOfGray,
		final Point position,
		final Dimension dimension,
		final RGB color) {

		return new GroupRectangleButton(
			this.getDevice(),
			this.getGraphics(),
			percentageOfGray,
			position,
			dimension,
			color);
	}
}
