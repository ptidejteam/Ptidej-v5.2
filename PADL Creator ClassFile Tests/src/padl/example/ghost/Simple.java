/*
 * (c) Copyright 2000-2002 Yann-Gaël Guéhéneuc,
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
package padl.example.ghost;

/**
 * @version	0.1
 * @author 	Yann-Gaël Guéhéneuc
 * @since		2002/10/10
 */
public class Simple {
	private A anA;
	private A[] listOfAs;
	private int anInt;
	private int[] listOfInts;

	public static void main(final String[] args) {
		final Simple simple = new Simple();
		simple.foo(new B());
		simple.foo(new B[0]);
		simple.foo(0);
		simple.foo(new float[0]);
		simple.foo1();
		simple.foo2();
		simple.foo3();
		simple.foo4();
	}
	private void foo(final B anA) {
		this.anA = null;
		this.listOfAs = null;
		this.anInt = 0;
		this.listOfInts = null;
	}
	private void foo(final B[] aListOfBs) {
	}
	private void foo(final float anInt) {
	}
	private void foo(final float[] aListOfInts) {
	}
	private void foo1() {
		final C c = new C();
		c.getClass();
	}
	private void foo2() {
		final C[] aListOfCs = new C[0];
		aListOfCs.getClass();
	}
	private void foo3() {
		final double aDouble;
		aDouble = 0;
		System.out.println(aDouble);
	}
	private void foo4() {
		final double[] aListOfDoubles = new double[0];
		aListOfDoubles.getClass();
	}
}
