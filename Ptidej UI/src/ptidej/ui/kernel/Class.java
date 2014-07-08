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
import padl.kernel.IClass;
import padl.kernel.IFirstClassEntity;
import ptidej.ui.kernel.builder.Builder;
import ptidej.ui.primitive.IPrimitiveFactory;

public class Class extends Entity {
	public Class(
		final IPrimitiveFactory aPrimitiveFactory,
		final Builder aBuilder,
		final IClass aClass) {

		super(aPrimitiveFactory, aBuilder, aClass);
	}
	protected void computeHierarchies() {
		super.computeHierarchies();

		Entity entity;
		Iterator iterator;

		iterator =
			((IClass) this.getFirstClassEntity())
				.getIteratorOnInheritedEntities();
		while (iterator.hasNext()) {
			final IFirstClassEntity e =
				(padl.kernel.IFirstClassEntity) iterator.next();
			entity = this.getBuilder().getEntity(e);

			// Yann 2004/12/16: Clean!
			// Ghosts are taken care of when doint the layout and painting
			// only, this removes many ugly checks like the following:
			//	if (!(entity instanceof Ghost
			//		&& (this.getVisibleElements()
			//			& IVisibility.GHOST_ENTITIES_DISPLAY)
			//			== 0)) {

			this.addHierarchy(new Specialisation(
				this.getPrimitiveFactory(),
				this,
				entity));
		}

		iterator =
			((IClass) this.getFirstClassEntity())
				.getIteratorOnImplementedInterfaces();
		while (iterator.hasNext()) {
			entity =
				this.getBuilder().getEntity(
					(padl.kernel.IFirstClassEntity) iterator.next());

			// Yann 2004/12/16: Clean!
			// Ghosts are taken care of when doing the layout and painting
			// only, this removes many ugly checks like the following:
			//	if (!(entity instanceof Ghost
			//		&& (this.getVisibleElements()
			//			& IVisibility.GHOST_ENTITIES_DISPLAY)
			//			== 0)) {

			this.addHierarchy(new Implementation(
				this.getPrimitiveFactory(),
				this,
				entity));
		}
	}
	protected String getStereotype() {
		return "";
	}
}
