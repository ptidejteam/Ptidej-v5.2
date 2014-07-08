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

import java.awt.Dimension;
import java.awt.Point;
import ptidej.ui.primitive.IPrimitiveFactory;

public abstract class Element extends Constituent {
	public Element(final IPrimitiveFactory primitiveFactory) {
		super(primitiveFactory);
	}
	protected void setDimensionSpecifics(final Dimension dimension) {
		// Nothing to do.
	}
	protected void setPositionSpecifics(final Point position) {
		// Nothing to do.
	}
	public final void setSelectedSpecifics(final boolean isSelected) {
		// Nothing to do.
	}
	protected void setVisibleElementsSpecifics(final int visibility) {
		// Nothing to do.
	}
}
