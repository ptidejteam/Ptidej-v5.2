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
package ptidej.solver;

import java.util.Arrays;
import org.apache.commons.lang.ArrayUtils;

/**
 * The SolutionComponent represents a part of a solution
 * to the problem of finding pattern in source code.
 * A SolutionComponent is of the form:
 *     <name> = <value>
 * where <name> is the name of the part and <value> the
 * name of the pattern constituent fullfilling the <name>.
 * 
 * @see ptidej.solver.Solution
 */
public final class OccurrenceComponent {
	public static String computeSimpleName(final String aName) {
		int indexOfMinusSign = aName.indexOf('-');
		if (indexOfMinusSign > 0) {
			return aName.substring(0, indexOfMinusSign);
		}
		else {
			return aName;
		}
	}
	public static char[] computeSimpleName(final char[] aName) {
		int indexOfMinusSign = ArrayUtils.indexOf(aName, '-');
		if (indexOfMinusSign > 0) {
			return ArrayUtils.subarray(aName, 0, indexOfMinusSign);
		}
		else {
			return aName;
		}
	}
	private final String displayName;
	private final String displayValue;
	private final char[] name;
	private final char[] value;

	/**
	 * Creates a part of a solution. A part has a name, a value,
	 * and a reference on the Entity related by this part.
	 */
	public OccurrenceComponent(final char[] name, final char[] value) {
		this.displayName = String.valueOf(name);
		this.displayValue = String.valueOf(value);
		this.name = name;
		this.value = value;
	}
	public boolean equals(final Object object) {
		if (object instanceof OccurrenceComponent) {
			final OccurrenceComponent other = (OccurrenceComponent) object;

			return Arrays.equals(this.getName(), other.getName())
					&& Arrays.equals(this.getValue(), other.getValue());
		}
		return false;
	}
	public String getDisplayName() {
		return this.displayName;
	}
	public String getDisplayValue() {
		return this.displayValue;
	}
	public char[] getName() {
		return this.name;
	}
	public char[] getValue() {
		return this.value;
	}
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append(this.name);
		buffer.append(" = ");
		buffer.append(this.value);
		return buffer.toString();
	}
}
