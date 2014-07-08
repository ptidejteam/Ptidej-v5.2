/*
 * (c) Copyright 2001 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes and Object Technology Internationl, Inc.
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
package caffeine.remote;
/**
 * @version 	0.1
 * @author		Yann-Gaël Guéhéneuc
 */
public final class CaffeineWrapper {
	public static double caffeineReturnedValueWrapper(final double value) {
		return value;
	}
	public static float caffeineReturnedValueWrapper(final float value) {
		return value;
	}
	public static int caffeineReturnedValueWrapper(final int value) {
		return value;
	}
	public static long caffeineReturnedValueWrapper(final long value) {
		return value;
	}
	public static Object caffeineReturnedValueWrapper(final Object value) {
		return value;
	}
	public static void caffeineUniqueExit(final int code) {
		System.exit(code);
	}
}