/*
 * (c) Copyright 2004 Sébastien Robidoux, Ward Flores,
 * Université de Montréal, Inc.
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
 * Created on 2004-08-10
 */
package ptidej.ui.kernel;

/**
 * @author robidose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

import padl.cpp.kernel.IDestructor;
import ptidej.ui.primitive.IPrimitiveFactory;

public final class Destructor extends Element {
	private IDestructor destructor;

	public Destructor(
		final IPrimitiveFactory aPrimitiveFactory,
		final IDestructor aDestructor) {

		super(aPrimitiveFactory);
		this.destructor = aDestructor;
	}
	public void build() {
		// Nothing to do.
	}
	public String getName() {
		final StringBuffer name =
			new StringBuffer(this.destructor.getDisplayName());
		name.append("()");
		return name.toString();
	}
	public void paint(final int xOffset, final int yOffset) {
		// Nothing to do.
	}
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		buffer.append(this.destructor.getDisplayName());
		buffer.append("(...)");
		return buffer.toString();
	}
}