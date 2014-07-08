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
package ptidej.solver.combination;

import util.multilingual.MultilingualManager;

public final class BitString {
	private final int numberOfBits;
	private int value;

	public BitString(final int numberOfBits) {
		this.numberOfBits = numberOfBits;
	}
	private static int compose(final boolean[] bits) {
		final int numberOfBits = bits.length;
		int v = 0;

		for (int i = 0; i < numberOfBits; i++) {
			v += ((bits[i] == true) ? Math.pow(2, numberOfBits - 1 - i) : 0);
		}

		return v;
	}
	public void dec() {
		this.value--;
	}
	private static boolean[] decompose(final int v, final int numberOfBits) {
		final boolean bits[] = new boolean[numberOfBits];
		int value = v;
		int i = numberOfBits;

		while (value > 0 && i >= 0) {
			i--;
			if (value % 2 > 0) {
				bits[i] = true;
			}
			else {
				bits[i] = false;
			}
			value = value / 2;
		}

		return bits;
	}
	public int getInteger() {
		return this.value;
	}
	public String getString() {
		final boolean[] bits =
			BitString.decompose(this.value, this.numberOfBits);
		final StringBuffer buffer = new StringBuffer(this.numberOfBits);

		for (int i = 0; i < this.numberOfBits; i++) {
			buffer.append(((bits[i] == true) ? '1' : '0'));
		}

		return buffer.toString();
	}
	public void inc() {
		this.value++;
	}
	public void set(final boolean[] bits) {
		if (bits.length != this.numberOfBits) {
			throw new RuntimeException(
				MultilingualManager.getString(
					"NB_OF_BITS",
					BitString.class,
					new Object[] { new Integer(this.numberOfBits)}));
		}
		this.value = BitString.compose(bits);
	}
	public void set(final int value) {
		this.value = value;
	}
	public void set(final String v) {
		if (v.length() != this.numberOfBits) {
			throw new RuntimeException(
				MultilingualManager.getString(
					"NB_OF_BITS",
					BitString.class,
					new Object[] { new Integer(this.numberOfBits)}));
		}

		final boolean[] bits = new boolean[this.numberOfBits];
		for (int i = 0; i < this.numberOfBits; i++) {
			bits[i] = (v.charAt(i) == '1') ? true : false;
		}

		this.value = BitString.compose(bits);
	}
	public String toString() {
		return this.getString();
	}
}
