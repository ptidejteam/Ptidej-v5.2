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
package padl.creator.msefile.misc;


/**
 * @author guehene
 */
public class Attribute {
	private final String name;
	private Value[] values;

	public Attribute(final String aName) {
		this.name = aName;
		this.setValues(new Value[0]);
	}
	public Attribute(final String aName, final Value[] someValues) {
		this.name = aName;
		this.setValues(someValues);
	}
	public String getName() {
		return this.name;
	}
	public Value getValue(final String name) {
		// Yann 2007/06/10: Cache!
		// I could cache results of this call for 
		// better performance
		final Value[] values = this.getValues();
		for (int i = 0; i < values.length; i++) {
			final Value value = values[i];
			if (value.getName().equals(name)) {
				return value;
			}
		}
		return null;
	}
	public Value[] getValues() {

		return this.values;
	}
	public void setValues(final Value[] values) {
		this.values = values;
	}
	public String toString() {

		final StringBuffer buffer = new StringBuffer();
		buffer.append("attribute ");
		buffer.append(this.getName());
		buffer.append('\n');
		for (int i = 0; i < this.getValues().length; i++) {
			final Value value = this.getValues()[i];
			buffer.append("\t\t");
			buffer.append(value);
			if (i < this.getValues().length - 1) {
				buffer.append('\n');
			}
		}

		return buffer.toString();
	}

}
