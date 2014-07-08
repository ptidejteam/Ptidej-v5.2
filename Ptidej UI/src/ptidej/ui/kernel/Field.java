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

import padl.kernel.IField;
import ptidej.ui.primitive.IPrimitiveFactory;
import util.lang.Modifier;

public final class Field extends Element {
	private IField field;

	public Field(final IPrimitiveFactory primitiveFactory, final IField pField) {
		super(primitiveFactory);
		this.field = pField;
	}
	public void build() {
		// Nothing to do.
	}
	public String getName() {
		return this.field.getDisplayName();
	}
	public void paint(int xOffset, int yOffset) {
		// Nothing to do.
	}
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append(Modifier.toString(this.field.getVisibility()));
		buffer.append(' ');
		buffer.append(this.field.getType());
		buffer.append(' ');
		buffer.append(this.field.getDisplayName());
		buffer.append(";");
		return buffer.toString();
	}
}
