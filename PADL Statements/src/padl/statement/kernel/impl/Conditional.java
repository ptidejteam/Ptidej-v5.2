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
package padl.statement.kernel.impl;

import padl.kernel.IMethod;
import padl.kernel.exception.ModelDeclarationException;
import padl.kernel.impl.Statement;
import padl.statement.kernel.IConditional;

abstract class Conditional extends Statement implements IConditional {
	private static final long serialVersionUID = 2905665981477351455L;

	private IMethod condition;
	public Conditional(final char[] anID) {
		super(anID);
	}
	public void setCondition(final IMethod aBooleanMethod) {
		if (aBooleanMethod.getReturnType().equals("boolean")) {
			this.condition = aBooleanMethod;
		}
		else {
			throw new ModelDeclarationException(
				"Condition method must return a boolean value");
		}
	}
	public IMethod getCondition() {
		return this.condition;
	}
}
