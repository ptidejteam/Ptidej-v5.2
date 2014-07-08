/*
 * (c) Copyright 2001, 2002 Hervé Albin-Amiot and Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes
 * Object Technology International, Inc.
 * Soft-Maint S.A.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * the authors, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF THE AUTHORS ARE ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package padl.kernel.impl;

/*
 * Classe Element used for type checking and the "attached" protocol.
 * In this protocol, delegation is used instead of bound properties in order to
 * retablish element in its previous state, when detachment occurs...
 * (even if the protocol provides events propagation). 
 */

import org.apache.commons.lang.ArrayUtils;
import padl.kernel.Constants;
import padl.kernel.IElement;
import padl.kernel.exception.ModelDeclarationException;
import padl.path.IConstants;
import util.multilingual.MultilingualManager;

public abstract class Element extends Constituent implements IElement {
	private static final long serialVersionUID = -600323363393722203L;
	private IElement attachedElement;

	public Element(final char[] actorID) {
		super(actorID);
	}
	protected char getPathSymbol() {
		return IConstants.ELEMENT_SYMBOL;
	}
	public void attachTo(final IElement anElement) {
		if (anElement != null) {
			if (anElement == this) {
				throw new ModelDeclarationException(
					MultilingualManager.getString("ELEM_ATTACH", Element.class));
			}

			if (!anElement.getClass().isInstance(this)) {
				throw new ModelDeclarationException(
					MultilingualManager.getString(
						"ATTACH",
						Element.class,
						new Object[] { anElement.getClass() }));
			}

			this.detach();
			this.attachedElement = anElement;
		}
	}
	public void detach() {
		final IElement oldAttachedElement = this.getAttachedElement();

		if (oldAttachedElement == null) {
			return;
		}

		this.attachedElement = null;
	}
	public IElement getAttachedElement() {
		return this.attachedElement;
	}
	public char[] getName() {
		// Delegation is used...
		if (this.getAttachedElement() == null) {
			return super.getName();
		}
		return this.getAttachedElement().getName();
	}
	/**
	 * This methods is used by the clone protocol.
	 */
	public void performCloneSession() {
		if (this.getAttachedElement() != null) {
			((Element) this.getClone()).attachedElement =
				(Element) this.getAttachedElement().getClone();
		}

		super.performCloneSession();
	}
	public void startCloneSession() {
		super.startCloneSession();

		// Yann 2010/10/03: Unique ID... again!
		// I used to concatenate a unique number for certain element,
		// for example binary class relationships, because a class
		// could have two with the same ID, name, etc. However, when
		// cloning, this unique number got duplicated unnecessarily,
		// so I must take care of it...
		// TODO: It would be cleaner to have two separate "unique IDs"
		// one containing the invariable ID, the other the variable number.
		// This code is actually dumb! Because the ID of an element
		// can contain legitimate underscores... So, I replace such
		// underscore with something that only PADL would do...
		final int index =
			this.getDisplayID().indexOf(Constants.NUMBER_SEPARATOR);
		if (index > 0) {
			((Element) this.getClone()).setID(ArrayUtils.subarray(
				this.getID(),
				0,
				index));
		}

		((Element) this.getClone()).attachedElement = null;
	}
}
