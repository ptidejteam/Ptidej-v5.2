/*
 * (c) Copyright 2003-2004 Jean-Yves Guyomarc'h,
 * University of Montréal.
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
package padl.aspectj.kernel.impl;

import java.util.Iterator;
import padl.aspectj.kernel.IAspect;
import padl.aspectj.kernel.IAspectElement;
import padl.kernel.IConstituentOfEntity;
import padl.kernel.IElement;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IInterfaceActor;
import padl.kernel.exception.ModelDeclarationException;
import padl.kernel.impl.Class;

/**
 * @author Jean-Yves Guyomarc'h
 */
class Aspect extends Class implements IAspect {
	private static final long serialVersionUID = 769206959474845212L;

	public Aspect(final char[] anID) {
		super(anID, anID);
	}
	public Aspect(final char[] anID, final IFirstClassEntity inheritedEntity) {
		super(anID, anID);
		this.addInheritedEntity(inheritedEntity);
	}
	public void addConstituent(final IAspectElement anAspectElement) {

		super.addConstituent(anAspectElement);
	}
	public void addConstituent(final IConstituentOfEntity aConstituent) {
		if (aConstituent instanceof IElement
				|| aConstituent instanceof IAspectElement) {
			super.addConstituent(aConstituent);
		}
		else {
			throw new ModelDeclarationException(
				"Only an element can be added to an entity.");
		}
	}
	public void addImplementedInterface(final IFirstClassEntity anEntity) {
	}
	public void addInheritedEntity(final IAspect anAspect) {
	}
	public IInterfaceActor getImplementedInterface(final String aName) {
		return null;
	}
	public Iterator getIteratorOnImplementedInterfaces() {
		return null;
	}
	public void removeImplementedInterface(final IFirstClassEntity anEntity) {
	}
	public void removeInheritingEntity(final IFirstClassEntity anEntity) {
	}
}
