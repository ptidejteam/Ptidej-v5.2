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
package ptidej.ui.kernel;

import java.util.Iterator;
import padl.kernel.IConstructor;
import padl.kernel.IParameter;
import ptidej.ui.primitive.IPrimitiveFactory;
import util.lang.Modifier;

public final class Constructor extends Element {
	private IConstructor constructor;

	public Constructor(
		final IPrimitiveFactory aPrimitiveFactory,
		final IConstructor aConstructor) {

		super(aPrimitiveFactory);
		this.constructor = aConstructor;
	}
	public void build() {
		// Nothing to do.
	}
	public String getName() {
		final StringBuffer name =
			new StringBuffer(this.constructor.getDisplayName());
		name.append("()");
		return name.toString();
	}
	public void paint(final int xOffset, final int yOffset) {
		// Nothing to do.
	}
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append(Modifier.toString(this.constructor.getVisibility()));
		buffer.append(' ');
		buffer.append(this.constructor.getDisplayName());
		// buffer.append("(...)");
		buffer.append('(');
		final Iterator iterator =
			this.constructor.getIteratorOnConstituents(IParameter.class);
		while (iterator.hasNext()) {
			final IParameter parameter = (IParameter) iterator.next();
			buffer.append(parameter.getTypeName());
			if (iterator.hasNext()) {
				buffer.append(", ");
			}
		}
		buffer.append(')');
		return buffer.toString();
	}
}
