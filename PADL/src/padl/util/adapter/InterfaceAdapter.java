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
package padl.util.adapter;

import java.util.Iterator;
import java.util.List;
import padl.event.IEvent;
import padl.event.IModelListener;
import padl.kernel.IConstituent;
import padl.kernel.IConstituentExtension;
import padl.kernel.IConstituentOfEntity;
import padl.kernel.IElement;
import padl.kernel.IFilter;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IInterface;
import padl.kernel.impl.Factory;
import padl.visitor.IVisitor;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/12/17
 */
public abstract class InterfaceAdapter implements IInterface {
	private static final long serialVersionUID = -8302726844488400447L;

	private IInterface wrappedInterface;

	public InterfaceAdapter(final char[] anID, final char[] aName) {
		this.wrappedInterface =
			Factory.getInstance().createInterface(anID, aName);
	}
	public InterfaceAdapter(final IInterface anInterface) {
		this.wrappedInterface = anInterface;
	}
	public void accept(final IVisitor aVisitor) {
		this.wrappedInterface.accept(aVisitor);
	}
	public void addConstituent(final IConstituent aConstituent) {
		this.wrappedInterface.addConstituent(aConstituent);
	}
	public void addConstituent(final IConstituentOfEntity aConstituent) {

		this.wrappedInterface.addConstituent(aConstituent);
	}
	public void addConstituent(final IElement anElement) {
		this.wrappedInterface.addConstituent(anElement);
	}
	public void addExtension(final IConstituentExtension anExtension) {
		this.wrappedInterface.addExtension(anExtension);
	}
	public void addInheritedEntity(final IFirstClassEntity anEntity) {
		this.wrappedInterface.addInheritedEntity(anEntity);
	}
	public void addModelListener(final IModelListener aModelListener) {
		this.wrappedInterface.addModelListener(aModelListener);
	}
	public void addModelListeners(final List aListOfModelListeners) {
		this.wrappedInterface.addModelListeners(aListOfModelListeners);
	}
	public boolean doesContainConstituentWithID(final char[] anID) {
		return this.wrappedInterface.doesContainConstituentWithID(anID);
	}
	public boolean doesContainConstituentWithName(final char[] aName) {
		return this.wrappedInterface.doesContainConstituentWithName(aName);
	}
	public void endCloneSession() {
		this.wrappedInterface.endCloneSession();
	}
	public void fireModelChange(final String anEventType, final IEvent anEvent) {
		this.fireModelChange(anEventType, anEvent);
	}
	public IConstituent getClone() {
		return this.wrappedInterface.getClone();
	}
	public String[] getCodeLines() {
		return this.wrappedInterface.getCodeLines();
	}
	public String getComment() {
		return this.wrappedInterface.getComment();
	}
	public Iterator getConcurrentIteratorOnConstituents() {
		return this.getConcurrentIteratorOnConstituents();
	}
	public Iterator getConcurrentIteratorOnConstituents(
		final Class aConstituentType) {
		return this.getConcurrentIteratorOnConstituents(aConstituentType);
	}
	public Iterator getConcurrentIteratorOnConstituents(final IFilter aFilter) {
		return this.getConcurrentIteratorOnConstituents(aFilter);
	}
	public IConstituent getConstituentFromID(final char[] anID) {
		return this.wrappedInterface.getConstituentFromID(anID);
	}
	public IConstituent getConstituentFromID(final String anID) {
		return this.wrappedInterface.getConstituentFromID(anID);
	}
	public IConstituent getConstituentFromName(final char[] aName) {
		return this.wrappedInterface.getConstituentFromName(aName);
	}
	public IConstituent getConstituentFromName(final String aName) {
		return this.wrappedInterface
			.getConstituentFromName(aName.toCharArray());
	}
	public String getDisplayID() {
		return this.wrappedInterface.getDisplayID();
	}
	public String getDisplayName() {
		return this.wrappedInterface.getDisplayName();
	}
	public String getDisplayPath() {
		return this.wrappedInterface.getDisplayPath();
	}
	public IConstituentExtension getExtension(final char[] anExtensionName) {
		return this.wrappedInterface.getExtension(anExtensionName);
	}
	public char[] getID() {
		return this.wrappedInterface.getID();
	}
	public IFirstClassEntity getInheritedEntityFromID(final char[] anID) {
		return this.wrappedInterface.getInheritedEntityFromID(anID);
	}
	public IFirstClassEntity getInheritedEntityFromName(final char[] aName) {
		return this.wrappedInterface.getInheritedEntityFromName(aName);
	}
	public Iterator getIteratorOnConstituents() {
		return this.wrappedInterface.getIteratorOnConstituents();
	}
	public Iterator getIteratorOnConstituents(final IFilter aFilter) {
		return this.wrappedInterface.getIteratorOnConstituents(aFilter);
	}
	public Iterator getIteratorOnConstituents(
		final java.lang.Class aConstituentType) {
		return this.wrappedInterface
			.getIteratorOnConstituents(aConstituentType);
	}
	public Iterator getIteratorOnImplementingClasses() {
		return this.wrappedInterface.getIteratorOnImplementingClasses();
	}
	public Iterator getIteratorOnInheritedEntities() {
		return this.wrappedInterface.getIteratorOnInheritedEntities();
	}
	public Iterator getIteratorOnInheritedEntities(final IFilter aFilter) {
		return this.wrappedInterface.getIteratorOnInheritedEntities(aFilter);
	}
	public Iterator getIteratorOnInheritingEntities() {
		return this.wrappedInterface.getIteratorOnInheritingEntities();
	}
	public Iterator getIteratorOnInheritingEntities(final IFilter aFilter) {
		return this.wrappedInterface.getIteratorOnInheritedEntities(aFilter);
	}
	public Iterator getIteratorOnModelListeners() {
		return this.wrappedInterface.getIteratorOnModelListeners();
	}
	public char[] getName() {
		return this.wrappedInterface.getName();
	}
	public int getNumberOfConstituents() {
		return this.getNumberOfConstituents();
	}
	public int getNumberOfConstituents(final Class aConstituentType) {
		return this.getNumberOfConstituents(aConstituentType);
	}
	public int getNumberOfInheritedEntities() {
		return this.wrappedInterface.getNumberOfInheritedEntities();
	}
	public int getNumberOfInheritingEntities() {
		return this.wrappedInterface.getNumberOfInheritingEntities();
	}
	public char[] getPath() {
		return this.wrappedInterface.getPath();
	}
	public String getPurpose() {
		return this.wrappedInterface.getPurpose();
	}
	public int getVisibility() {
		return this.wrappedInterface.getVisibility();
	}
	public int getWeight() {
		return this.wrappedInterface.getWeight();
	}
	public boolean isAboveInHierarchy(final IFirstClassEntity anEntity) {
		return this.wrappedInterface.isAboveInHierarchy(anEntity);
	}
	public boolean isAbstract() {
		return this.wrappedInterface.isAbstract();
	}
	public boolean isFinal() {
		return this.wrappedInterface.isFinal();
	}
	public boolean isPrivate() {
		return this.wrappedInterface.isPrivate();
	}
	public boolean isProtected() {
		return this.wrappedInterface.isProtected();
	}
	public boolean isPublic() {
		return this.wrappedInterface.isPublic();
	}
	public boolean isStatic() {
		return this.wrappedInterface.isStatic();
	}
	public void performCloneSession() {
		this.wrappedInterface.performCloneSession();
	}
	public void removeAllConstituent() {
		this.wrappedInterface.removeAllConstituent();
	}
	public void removeConstituentFromID(final char[] anID) {
		this.wrappedInterface.removeConstituentFromID(anID);
	}
	public void removeImplementedEntity(final IFirstClassEntity anEntity) {
		this.removeImplementedEntity(anEntity);
	}
	public void removeInheritedEntity(final IFirstClassEntity anEntity) {
		this.wrappedInterface.removeInheritedEntity(anEntity);
	}
	public void removeModelListener(final IModelListener aModelListener) {
		this.removeModelListener(aModelListener);
	}
	public void removeModelListeners(final List aListOfModelListeners) {
		this.wrappedInterface.removeModelListeners(aListOfModelListeners);
	}
	public void resetCodeLines() {
		this.wrappedInterface.resetCodeLines();
	}
	public void setAbstract(final boolean aBoolean) {
		this.wrappedInterface.setAbstract(aBoolean);
	}
	public void setCodeLines(final String someCode) {

		this.wrappedInterface.setCodeLines(someCode);
	}
	public void setCodeLines(final String[] someCode) {
		this.wrappedInterface.setCodeLines(someCode);
	}
	public void setComment(final String aComment) {
		this.wrappedInterface.setComment(aComment);
	}
	public void setDisplayName(final String aName) {
		this.wrappedInterface.setDisplayName(aName);
	}
	public void setFinal(final boolean aBoolean) {
		this.wrappedInterface.setFinal(aBoolean);
	}
	public void setName(final char[] aName) {
		this.wrappedInterface.setName(aName);
	}
	public void setPrivate(final boolean aBoolean) {
		this.wrappedInterface.setPrivate(aBoolean);
	}
	public void setProtected(final boolean aBoolean) {
		this.wrappedInterface.setProtected(aBoolean);
	}
	public void setPublic(final boolean aBoolean) {
		this.wrappedInterface.setPublic(aBoolean);
	}
	public void setPurpose(final String aPurpose) {
		this.wrappedInterface.setPurpose(aPurpose);
	}
	public void setStatic(final boolean aBoolean) {
		this.wrappedInterface.setStatic(aBoolean);
	}
	public void setVisibility(final int aVisibility) {
		this.wrappedInterface.setVisibility(aVisibility);
	}
	public void setWeight(final int aWeight) {
		this.wrappedInterface.setWeight(aWeight);
	}
	public void startCloneSession() {
		this.wrappedInterface.startCloneSession();
	}
	public String toString() {
		return this.wrappedInterface.toString();
	}
	public String toString(final int aTab) {
		return this.wrappedInterface.toString(aTab);
	}
}
