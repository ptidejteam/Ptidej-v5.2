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

import java.util.Vector;

public final class Combination {
	public static Vector C(int n, int p) {
		/*
		 * The number of combinations of p elements within n is equal to:
		 *        p     n!
		 *      C  = --------
		 *        n  p!(n-p)!
		 */
		final int numberOfPermutations =
			factorial(n) / (factorial(p) * factorial(n - p));

		/*
		 * (From Canada's SchoolNet website:
		 *  www.schoolnet.ca/vp-pv/ECOS/index.html)
		 * The computation of the combination of p elements within n
		 * is easily done using bitstring.
		 *
		 * The idea is that for two elements within four, the results
		 * are (in bit strings and sub-sets):
		 *
		 *     +------+--------+
		 *     | 0011 | {1, 2} |
		 *     | 0101 | {1, 3} |
		 *     | 0110 | {2, 3} |
		 *     | 1001 | {1, 4} |
		 *     | 1010 | {2, 4} |
		 *     | 1100 | {3, 4} |
		 *     +------+--------+
		 */

		final BitString bitString = new BitString(n);
		final Vector solutions = new Vector(numberOfPermutations);

		/*
		 * I compute the solutions by counting the number of
		 * ones in the bit string: If the number of ones is equal
		 * to p, then the current value of the bit string represents
		 * a solution. I obtain a solution from the bit string:
		 * The indexes of the ones compose the solution.
		 */
		while (solutions.size() != numberOfPermutations) {
			if (Combination.getNumberOfOnes(bitString.getString()) == p) {
				solutions.add(
					0,
					Combination.getIndexesOfOnes(bitString.getString()));
			}
			bitString.inc();
		}

		return solutions;
	}
	private static int factorial(final int n) {
		int factorial = 1;
		for (int i = 1; i <= n; i++) {
			factorial *= i;
		}
		return factorial;
	}
	private static Vector getIndexesOfOnes(final String bitString) {
		final Vector solution = new Vector();
		for (int i = 0; i < bitString.length(); i++) {
			if (bitString.charAt(i) == '1') {
				solution.add(new Integer(i + 1));
			}
		}
		return solution;
	}
	private static int getNumberOfOnes(final String bitString) {
		int result = 0;
		for (int i = 0; i < bitString.length(); i++) {
			if (bitString.charAt(i) == '1') {
				result++;
			}
		}
		return result;
	}
	public static void main(final String[] args) {
		final int NB_CONSTRAINTS = 4;
		for (int i = 1; i <= NB_CONSTRAINTS; i++) {
			System.out.println(Combination.C(NB_CONSTRAINTS, i));
		}
	}
}
