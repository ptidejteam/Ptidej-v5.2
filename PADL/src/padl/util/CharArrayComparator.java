/* (c) Copyright 2001 and following years, Yann-Gaël Guéhéneuc,
 * University of Montreal.
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
package padl.util;

import java.io.Serializable;
import java.util.Comparator;

/**
 * 
 * @author Yann
 * @date   2010/06/21 
 *
 */
public class CharArrayComparator implements Comparator, Serializable {
	private static final long serialVersionUID = -3215432399567382906L;
	private static CharArrayComparator UniqueInstance;
	public static CharArrayComparator getInstance() {
		if (CharArrayComparator.UniqueInstance == null) {
			CharArrayComparator.UniqueInstance = new CharArrayComparator();
		}
		return CharArrayComparator.UniqueInstance;
	}

	private CharArrayComparator() {
	}
	public int compare(final Object o1, final Object o2) {
		final char[] s1 = (char[]) o1;
		final char[] s2 = (char[]) o2;
		final int n1 = s1.length, n2 = s2.length;
		for (int i1 = 0, i2 = 0; i1 < n1 && i2 < n2; i1++, i2++) {
			final char c1 = s1[i1];
			final char c2 = s2[i2];
			if (c1 != c2) {
				return c1 - c2;
			}
		}
		return n1 - n2;
	}
}
