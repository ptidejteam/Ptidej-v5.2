/* (c) Copyright 2001 and following years, Yann-Ga�l Gu�h�neuc,
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

import padl.kernel.IClass;
import sad.codesmell.property.ICodeSmellProperty;

/**
 * 
 */

public class ClassProperty extends PropertyContainer
	implements
		ICodeSmellProperty {

	final private IClass iClass;

	public ClassProperty(final IClass iclass) {
		this.iClass = iclass;
	}

	public IClass getIClass() {
		return this.iClass;
	}

	public String getIDClass() {
		return this.iClass.getDisplayID();
	}

	public String toString(final int count, final int propertyCount, final String codesmellName) {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("\n" + count + ".100." + codesmellName + "-" + propertyCount + " = ");
		buffer.append(this.getIDClass());

		// Add properties informations
		buffer.append(super.toString(count, codesmellName + "-" + propertyCount));

		return buffer.toString();
	}

}
