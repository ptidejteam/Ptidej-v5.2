/*
 * (c) Copyright 2004 Sébastien Robidoux, Ward Flores,
 * Université de Montréal
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
 * Created 2004/08/19
 */
package ptidej.ui.kernel;

import padl.cpp.kernel.IGlobalFunction;
import ptidej.ui.kernel.builder.Builder;
import ptidej.ui.primitive.IPrimitiveFactory;

/**
 * @author Yann
 * @since  2013/05/23
 */

// Ward 2004/08/19: Hierarchy level
// For now GlobalField is created at the same level as Class.
// So, it has all the inheritance properties.
// Implementation to be checked ...
public final class GlobalFunction extends Entity {
	public GlobalFunction(
		final IPrimitiveFactory aPrimitiveFactory,
		final Builder aBuilder,
		final IGlobalFunction aGlobalFunction) {

		super(aPrimitiveFactory, aBuilder, aGlobalFunction);
	}
	protected String getStereotype() {
		return "<<global function>>\n";
	}
}
