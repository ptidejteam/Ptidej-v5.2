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

package sad.codesmell.property.impl;

import padl.kernel.IField;
import sad.codesmell.property.ICodeSmellProperty;

/**
 * Hold information about a field
 *
 * @author Pierre Leduc
 * @version 1.0
 * @since 2006/05/29
 */
public class FieldProperty implements ICodeSmellProperty {

	final private IField iField;

	public FieldProperty(IField field) {
		this.iField = field;
	}

	public IField getIField() {
		return this.iField;
	}

	public String getIDField() {
		return this.iField.getDisplayID();
	}

	public String toString(final int count, final int propertyCount, final String codesmellName) {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("\n" + count + ".100." + codesmellName + ".FieldName-" + propertyCount + " = ");
		buffer.append(this.getIDField());

		return buffer.toString();
	}

}
