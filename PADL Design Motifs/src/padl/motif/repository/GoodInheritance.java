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
package padl.motif.repository;

import padl.kernel.IClass;
import padl.motif.models.ArchitecturalQualityMotifModel;
import util.multilingual.MultilingualManager;

public final class GoodInheritance extends ArchitecturalQualityMotifModel {
	private static final char[] GOOD_INHERITANCE = "GoodInheritance"
		.toCharArray();
	private static final long serialVersionUID = -53928198062516626L;
	private static final char[] SUBCLASS = "Subclass".toCharArray();
	private static final char[] SUPERCLASS = "Superclass".toCharArray();

	public GoodInheritance() {
		super(GoodInheritance.GOOD_INHERITANCE);

		final IClass superclass =
			this.getFactory().createClass(
				GoodInheritance.SUPERCLASS,
				GoodInheritance.SUPERCLASS);
		final IClass subclass =
			this.getFactory().createClass(
				GoodInheritance.SUBCLASS,
				GoodInheritance.SUBCLASS);
		subclass.addInheritedEntity(superclass);
		this.addConstituent(superclass);
		this.addConstituent(subclass);
	}

	public String getIntent() {
		return MultilingualManager.getString("INTENT", GoodInheritance.class);
	}

	public char[] getName() {
		return GoodInheritance.GOOD_INHERITANCE;
	}
}
