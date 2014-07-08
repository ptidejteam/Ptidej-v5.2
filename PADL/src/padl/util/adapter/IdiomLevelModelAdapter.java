/* (c) Copyright 2001 and following years, Yann-Gaël Guéhéneuc,
 * University of Montreal.
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
import padl.kernel.IAbstractModel;
import padl.kernel.ICodeLevelModel;
import padl.kernel.IConstituent;
import padl.kernel.IConstituentOfModel;
import padl.kernel.IFactory;
import padl.kernel.IFilter;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IIdiomLevelModel;
import padl.kernel.exception.ModelDeclarationException;
import padl.visitor.IGenerator;
import padl.visitor.IWalker;

/**
 * 
 * @author Yann
 * @since  2009/03/07
 *
 */

public class IdiomLevelModelAdapter implements IIdiomLevelModel {
	private static final long serialVersionUID = 7176911949482060583L;

	private final ICodeLevelModel wrappedCodeLevelModel;

	public IdiomLevelModelAdapter(final ICodeLevelModel aCodeLevelModel) {
		this.wrappedCodeLevelModel = aCodeLevelModel;
	}

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
		this.wrappedCodeLevelModel.addConstituent(aConstituent);
	}

	public void addModelListener(final IModelListener aModelListener) {
		this.wrappedCodeLevelModel.addModelListener(aModelListener);
	}

	public void addModelListeners(final List aListOfModelListeners) {
		this.wrappedCodeLevelModel.addModelListeners(aListOfModelListeners);
	}

	public Object clone() throws CloneNotSupportedException {
		return this.wrappedCodeLevelModel.clone();
	}

	public void moveIn(final IAbstractModel aDestinationModel) {
		this.wrappedCodeLevelModel.moveIn(aDestinationModel);
	}

	public boolean doesContainConstituentWithID(final char[] anID) {
		return this.wrappedCodeLevelModel.doesContainConstituentWithID(anID);
	}

	public boolean doesContainConstituentWithName(final char[] aName) {
		return this.wrappedCodeLevelModel.doesContainConstituentWithName(aName);
	}

	public boolean doesContainTopLevelEntityWithID(final char[] anID) {
		return this.wrappedCodeLevelModel.doesContainTopLevelEntityWithID(anID);
	}

	public void fireModelChange(final String anEventType, final IEvent anEvent) {
		this.wrappedCodeLevelModel.fireModelChange(anEventType, anEvent);
	}

	public String generate(final IGenerator aBuilder) {
		return this.wrappedCodeLevelModel.generate(aBuilder);
	}

	public Iterator getConcurrentIteratorOnConstituents() {
		return this.wrappedCodeLevelModel.getConcurrentIteratorOnConstituents();
	}

	public Iterator getConcurrentIteratorOnConstituents(
		final Class aConstituentType) {
		return this.wrappedCodeLevelModel
			.getConcurrentIteratorOnConstituents(aConstituentType);
	}

	public Iterator getConcurrentIteratorOnConstituents(final IFilter aFilter) {
		return this.wrappedCodeLevelModel
			.getConcurrentIteratorOnConstituents(aFilter);
	}

	public IConstituent getConstituentFromID(final char[] anID) {
		return this.wrappedCodeLevelModel.getConstituentFromID(anID);
	}

	public IConstituent getConstituentFromID(final String anID) {
		return this.wrappedCodeLevelModel.getConstituentFromID(anID);
	}

	public IConstituent getConstituentFromName(final char[] aName) {
		return this.wrappedCodeLevelModel.getConstituentFromName(aName);
	}

	public IConstituent getConstituentFromName(final String aName) {
		return this.wrappedCodeLevelModel.getConstituentFromName(aName
			.toCharArray());
	}

	public String getDisplayName() {
		return this.wrappedCodeLevelModel.getDisplayName();
	}

	public String getDisplayPath() {
		return this.wrappedCodeLevelModel.getDisplayPath();
	}

	public IFactory getFactory() {
		return this.wrappedCodeLevelModel.getFactory();
	}

	public Iterator getIteratorOnConstituents() {
		return this.wrappedCodeLevelModel.getIteratorOnConstituents();
	}

	public Iterator getIteratorOnConstituents(final Class aConstituentType) {
		return this.wrappedCodeLevelModel
			.getIteratorOnConstituents(aConstituentType);
	}

	public Iterator getIteratorOnConstituents(final IFilter aFilter) {
		return this.wrappedCodeLevelModel.getIteratorOnConstituents(aFilter);
	}

	public Iterator getIteratorOnModelListeners() {
		return this.wrappedCodeLevelModel.getIteratorOnModelListeners();
	}

	public Iterator getIteratorOnTopLevelEntities() {
		return this.wrappedCodeLevelModel.getIteratorOnTopLevelEntities();
	}

	public char[] getName() {
		return this.wrappedCodeLevelModel.getName();
	}

	public int getNumberOfConstituents() {
		return this.wrappedCodeLevelModel.getNumberOfConstituents();
	}

	public int getNumberOfConstituents(final Class aConstituentType) {
		return this.wrappedCodeLevelModel
			.getNumberOfConstituents(aConstituentType);
	}

	public int getNumberOfTopLevelEntities() {
		return this.wrappedCodeLevelModel.getNumberOfTopLevelEntities();
	}

	public int getNumberOfTopLevelEntities(final Class aClass) {
		return this.wrappedCodeLevelModel.getNumberOfTopLevelEntities(aClass);
	}

	public char[] getPath() {
		return this.wrappedCodeLevelModel.getPath();
	}

	public IFirstClassEntity getTopLevelEntityFromID(final char[] anID) {
		return this.wrappedCodeLevelModel.getTopLevelEntityFromID(anID);
	}

	public IFirstClassEntity getTopLevelEntityFromID(final String anID) {
		return this.wrappedCodeLevelModel.getTopLevelEntityFromID(anID);
	}

	public void removeAllConstituent() {
		this.wrappedCodeLevelModel.removeAllConstituent();
	}

	public void removeConstituentFromID(final char[] anID) {
		this.wrappedCodeLevelModel.removeConstituentFromID(anID);
	}

	public void removeModelListener(final IModelListener aModelListener) {
		this.wrappedCodeLevelModel.removeModelListener(aModelListener);
	}

	public void removeModelListeners(final List aListOfModelListeners) {
		this.wrappedCodeLevelModel.removeModelListeners(aListOfModelListeners);
	}

	public void removeTopLevelEntityFromID(final char[] anID) {
		this.wrappedCodeLevelModel.removeTopLevelEntityFromID(anID);
	}

	public Object walk(final IWalker aWalker) {
		return this.wrappedCodeLevelModel.walk(aWalker);
	}
}
