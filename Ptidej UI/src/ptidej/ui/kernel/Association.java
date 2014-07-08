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

import ptidej.ui.IVisibility;
import ptidej.ui.primitive.IPrimitive;
import ptidej.ui.primitive.IPrimitiveFactory;
import ptidej.ui.primitive.ISymbol;

public class Association extends Relationship {
	private final String associationFieldName;
	private final String associationTypeName;

	public Association(
		final IPrimitiveFactory primitiveFactory,
		final String name,
		final int cardinality,
		final Entity origin,
		final Entity target) {

		super(primitiveFactory, cardinality, origin, target);

		this.associationFieldName = name;
		this.associationTypeName = target.getName();
	}
	protected IPrimitive getLine() {
		return this.getPrimitiveFactory().createLine(
			this.getPosition(),
			this.getDimension(),
			this.getColor());
	}
	protected ISymbol getOriginSymbol() {
		return null;
	}
	protected String getSymbol() {
		return "----> " + this.associationTypeName + this.associationFieldName;
	}
	protected final ISymbol getTargetSymbol() {
		return this.getPrimitiveFactory().createArrowSymbol(
			this.findIntersectionPointWithTarget(),
			this.getDimension(),
			this.getColor());
	}
	public int getVisibilityDisplay() {
		return IVisibility.ASSOCIATION_DISPLAY_ELEMENTS;
	}
	int getVisibilityName() {
		return IVisibility.ASSOCIATION_NAMES;
	}
}
