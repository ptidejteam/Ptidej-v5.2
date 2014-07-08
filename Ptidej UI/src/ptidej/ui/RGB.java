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
package ptidej.ui;

/**
 * This class represents a graphic-framework independant color.
 * It is ready for storage in Maps and Lists (see methods
 * hashCode() and equals(Object)).
 * 
 * @version	0.2
 * @author 	Yann-Gaël Guéhéneuc
 */
public class RGB {
	public static RGB computePositivePercentagedColor(final int aPercentage) {
		final int red =
			(int) (Constants.BACKGROUND_COLOR.getRed()
				- Constants.BACKGROUND_COLOR.getRed()
					* Math.min(
						Math.max(
							aPercentage / 100d,
							Constants.MINIMUM_PERCENTAGE),
						1.0d));
		final int green = Constants.BACKGROUND_COLOR.getGreen();
		final int blue =
			(int) (Constants.BACKGROUND_COLOR.getBlue()
				- Constants.BACKGROUND_COLOR.getBlue()
					* Math.min(
						Math.max(
							aPercentage / 100d,
							Constants.MINIMUM_PERCENTAGE),
						1.0d));

		return new RGB(red, green, blue);
	}
	public static RGB computeNegativePercentagedColor(final int aPercentage) {
		final int red = Constants.BACKGROUND_COLOR.getRed();
		final int green =
			(int) (Constants.BACKGROUND_COLOR.getGreen()
				- Constants.BACKGROUND_COLOR.getGreen()
					* Math.min(
						Math.max(
							aPercentage / 100d,
							Constants.MINIMUM_PERCENTAGE),
						1.0d));
		final int blue =
			(int) (Constants.BACKGROUND_COLOR.getBlue()
				- Constants.BACKGROUND_COLOR.getBlue()
					* Math.min(
						Math.max(
							aPercentage / 100d,
							Constants.MINIMUM_PERCENTAGE),
						1.0d));

		return new RGB(red, green, blue);
	}

	private final int red, green, blue;
	public RGB(final int red, final int green, final int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}
	public boolean equals(final Object o) {
		if (o instanceof RGB) {
			final RGB other = (RGB) o;
			return this.red == other.red
				&& this.green == other.green
				&& this.blue == other.blue;
		}
		return false;
	}
	public int getBlue() {
		return this.blue;
	}
	public int getGreen() {
		return this.green;
	}
	public int getRed() {
		return this.red;
	}
	public int hashCode() {
		return this.red + this.green + this.blue;
	}
	public String toString() {
		final StringBuffer buffer = new StringBuffer(18);
		buffer.append("RGB(");
		buffer.append(this.red);
		buffer.append(", ");
		buffer.append(this.green);
		buffer.append(", ");
		buffer.append(this.blue);
		buffer.append(')');
		return buffer.toString();
	}
}
