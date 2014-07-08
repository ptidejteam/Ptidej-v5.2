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

import padl.kernel.IOperation;
import sad.codesmell.property.ICodeSmellProperty;

/**
 * Hold information about a method
 *
 * @author Pierre Leduc
 * @version 1.0
 * @since 2006/05/29
 */
public class MethodProperty extends PropertyContainer
	implements
		ICodeSmellProperty {

	final private IOperation iMethod;

	public MethodProperty(final IOperation method) {
		this.iMethod = method;
	}

	public IOperation getIMethod() {
		return this.iMethod;
	}

	public String getIDMethod() {
		return this.iMethod.getDisplayID();
	}

	public String toString(int count, final int propertyCount, final String codesmellName) {
		final StringBuffer buffer = new StringBuffer();
		buffer.append("\n" + count + ".100." + codesmellName + ".MethodName-" + propertyCount + " = ");
		buffer.append(this.getIDMethod());

		// Add properties informations
		buffer.append(super.toString(count, codesmellName));

		return buffer.toString();
	}

}
