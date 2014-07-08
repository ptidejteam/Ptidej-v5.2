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
public class Value {
	private String name;
	private String value;

	public Value() {
		this.name = "<no name>";
		this.value = "<no value>";
	}
	public Value(final String aName) {
		this();
		this.name = aName;
	}
	public Value(final String aName, final String aValue) {
		this(aName);
		this.value = aValue;
	}
	public String getName() {
		return this.name;
	}
	public String getValue() {
		return this.value;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public void setValue(final String value) {
		this.value = value;
	}
	public String toString() {

		final StringBuffer buffer = new StringBuffer();
		buffer.append('(');
		buffer.append(this.name);
		buffer.append(',');
		buffer.append(this.value);
		buffer.append(')');
		return buffer.toString();
	}
}
