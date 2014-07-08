package ptidej.ui.primitive.swt;

import java.awt.Dimension;
import java.awt.Point;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.GC;
import ptidej.ui.RGB;

public final class Rectangle extends Primitive implements
		ptidej.ui.primitive.IRectangle {

	Rectangle(
		final Device device,
		final GC graphics,
		final int percentageofGray,
		final Point origin,
		final Dimension dimension,
		final RGB color) {

		super(device, graphics, origin, dimension, color);
	}
	Rectangle(
		final Device device,
		final GC graphics,
		final Point origin,
		final Dimension dimension,
		final RGB color) {

		this(device, graphics, 0, origin, dimension, color);
	}
	public void paint(final int xOffset, final int yOffset) {
		this.getGraphics().setForeground(this.getSWTColor());
		this.getGraphics().drawRectangle(
			this.getPosition().x + xOffset,
			this.getPosition().y + yOffset,
			this.getDimension().width,
			this.getDimension().height);
		this.getGraphics().drawRectangle(
			this.getPosition().x + xOffset - 1,
			this.getPosition().y + yOffset - 1,
			this.getDimension().width + 2,
			this.getDimension().height + 2);
	}
}
