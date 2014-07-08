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
public class Element {
	private final String type;
	private Attribute[] attributes;
	public Element(final String aType) {
		this.type = aType;
	}
	public Element(final String aType, final Attribute[] someAttributes) {
		this(aType);
		this.attributes = someAttributes;
	}
	public Attribute getAttribute(final String name) {
		// Yann 2007/06/10: Cache!
		// I could cache results of this call for 
		// better performance
		final Attribute[] attributes = this.getAttributes();
		for (int i = 0; i < attributes.length; i++) {
			final Attribute attribute = attributes[i];
			if (attribute.getName().equals(name)) {
				return attribute;
			}
		}
		return null;
	}
	public Attribute[] getAttributes() {
		return this.attributes;
	}
	public String getType() {
		return this.type;
	}
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("element ");
		buffer.append(this.type);
		buffer.append('\n');
		for (int i = 0; i < this.attributes.length; i++) {
			final Attribute attribute = this.attributes[i];
			buffer.append('\t');
			buffer.append(attribute);
			if (i < this.attributes.length - 1) {
				buffer.append('\n');
			}
		}
		return buffer.toString();
	}
}
