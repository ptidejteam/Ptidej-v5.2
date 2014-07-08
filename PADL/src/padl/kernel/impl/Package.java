/*
 * (c) Copyright 2001-2004 Yann-Gaël Guéhéneuc,
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
package padl.kernel.impl;

import java.util.Iterator;
import java.util.List;
import padl.event.IEvent;
import padl.event.IModelListener;
import padl.kernel.IConstituent;
import padl.kernel.IConstituentOfEntity;
import padl.kernel.IConstituentOfModel;
import padl.kernel.IFilter;
import padl.kernel.IPackage;
import padl.kernel.exception.ModelDeclarationException;
import padl.path.IConstants;
import padl.visitor.IVisitor;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since 2005/09/30
 */
class Package extends Constituent implements IPackage {
	private static final long serialVersionUID = 2682383413411742951L;
	private AbstractGenericContainerOfConstituents container =
		new GenericContainerOfNaturallyOrderedConstituents(
			this,
			GenericContainerConstants.INITIAL_SIZE_PACKAGES);

	public Package(final char[] anID) {
		super(anID);
	}

	public void accept(final IVisitor visitor) {
		this.accept(visitor, "open");
		final Iterator iterator = this.getIteratorOnConstituents();
		while (iterator.hasNext()) {
			((IConstituent) iterator.next()).accept(visitor);
		}
		this.accept(visitor, "close");
	}

	public void addConstituent(IConstituentOfEntity aConstituent) {
		this.container.addConstituent(aConstituent);
	}

	// public final Object clone() throws CloneNotSupportedException {
	// final Package clonedPackage = (Package) super.clone();
	//
	// clonedPackage.container =
	// new GenericContainerOfNaturallyOrderedConstituents(clonedPackage);
	// clonedPackage.topLevelEntitiesContainer =
	// new GenericContainerOfTopLevelEntities(clonedPackage);
	//
	// return clonedPackage;
	// }
	public void addConstituent(final IConstituent aConstituent) {
		if (aConstituent instanceof IConstituentOfModel) {
			this.addConstituent((IConstituentOfModel) aConstituent);
		}
		else {
			throw new ModelDeclarationException(this.getClass().getName()
					+ " can only add IConstituentOfModel");
		}
	}
	public void addConstituent(final IConstituentOfModel aConstituent) {
		this.container.addConstituent(aConstituent);
	}
	//	public void addConstituent(IConstituentOfOperation aConstituent)
	//			throws ModelDeclarationException {
	//		this.container.addConstituent(aConstituent);
	//	}
	//	public void addConstituent(final IFirstClassEntity anEntity)
	//			throws ModelDeclarationException {
	//
	//		this.container.addConstituent(anEntity);
	//	}
	public void addConstituent(final IPackage aPackage) {
		this.container.addConstituent(aPackage);
	}

	public void addModelListener(final IModelListener aModelListener) {
		this.container.addModelListener(aModelListener);
	}

	public void addModelListeners(final List someModelListeners) {
		this.container.addModelListeners(someModelListeners);
	}

	public boolean doesContainConstituentWithID(final char[] anID) {
		return this.container.doesContainConstituentWithID(anID);
	}

	public boolean doesContainConstituentWithName(final char[] aName) {
		return this.container.doesContainConstituentWithName(aName);
	}

	public void fireModelChange(final String anEventType, final IEvent anEvent) {
		this.container.fireModelChange(anEventType, anEvent);
	}

	public Iterator getConcurrentIteratorOnConstituents() {
		return this.container.getConcurrentIteratorOnConstituents();
	}

	public Iterator getConcurrentIteratorOnConstituents(final IFilter filter) {
		return this.container.getConcurrentIteratorOnConstituents();
	}

	public Iterator getConcurrentIteratorOnConstituents(
		final java.lang.Class aConstituentType) {

		return this.container
			.getConcurrentIteratorOnConstituents(aConstituentType);
	}

	public IConstituent getConstituentFromID(final char[] anID) {
		return this.container.getConstituentFromID(anID);
	}

	public IConstituent getConstituentFromID(final String anID) {
		return this.getConstituentFromID(anID.toCharArray());
	}

	public IConstituent getConstituentFromName(final char[] aName) {
		return this.container.getConstituentFromName(aName);
	}

	public IConstituent getConstituentFromName(final String aName) {
		return this.getConstituentFromName(aName.toCharArray());
	}

	public Iterator getIteratorOnConstituents() {
		return this.container.getIteratorOnConstituents();
	}

	public Iterator getIteratorOnConstituents(final IFilter aFilter) {
		return this.container.getIteratorOnConstituents(aFilter);
	}

	public Iterator getIteratorOnConstituents(java.lang.Class aConstituentType) {
		return this.container.getIteratorOnConstituents(aConstituentType);
	}

	public Iterator getIteratorOnModelListeners() {
		return this.container.getIteratorOnModelListeners();
	}

	public int getNumberOfConstituents() {
		return this.container.getNumberOfConstituents();
	}

	public int getNumberOfConstituents(final java.lang.Class aConstituentType) {
		return this.container.getNumberOfConstituents(aConstituentType);
	}

	protected char getPathSymbol() {
		return IConstants.PACKAGE_SYMBOL;
	}

	public void removeAllConstituent() {
		this.container.removeAllConstituent();
	}

	public void removeConstituentFromID(final char[] anID) {
		this.container.removeConstituentFromID(anID);
	}

	public void removeModelListener(final IModelListener aModelListener) {
		this.container.removeModelListener(aModelListener);
	}

	public void removeModelListeners(final List aListOfModelListeners) {
		this.container.removeModelListeners(aListOfModelListeners);
	}
	/**
	 * This method performs a shallow copy.
	 */
	public void startCloneSession() {
		// The shallow copy must include a shallow copy of
		// member entities, because the clones of methods,
		// fields, and so on, depend on it!
		super.startCloneSession();

		// Yann 2010/10/03: Objects!
		// The "container" is now an instance of a class
		// and must be assigned a new instance independently.
		// ((Package) this.getClone()).container.resetListOfConstituents();
		((Package) this.getClone()).container =
			new GenericContainerOfNaturallyOrderedConstituents(
				((Package) this.getClone()));

		final Iterator iterator = this.getIteratorOnConstituents();
		while (iterator.hasNext()) {
			final IConstituent constituent = (IConstituent) iterator.next();
			constituent.startCloneSession();
			((IPackage) this.getClone())
				.addConstituent((IConstituentOfModel) constituent.getClone());
		}
	}
	public String toString(final int tab) {
		final StringBuffer codeEq = new StringBuffer();
		codeEq.append("package");
		codeEq.append(' ');
		codeEq.append(this.getName());
		return codeEq.toString();
	}
}
