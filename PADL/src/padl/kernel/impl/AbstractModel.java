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

import java.util.Iterator;
import java.util.List;
import padl.event.IEvent;
import padl.event.IModelListener;
import padl.kernel.Constants;
import padl.kernel.IAbstractModel;
import padl.kernel.IConstituent;
import padl.kernel.IConstituentOfModel;
import padl.kernel.IContainer;
import padl.kernel.IFactory;
import padl.kernel.IFilter;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IPackage;
import padl.kernel.exception.ModelDeclarationException;
import padl.path.Finder;
import padl.path.FormatException;
import padl.path.IConstants;
import padl.visitor.IGenerator;
import padl.visitor.IWalker;
import util.io.ProxyConsole;

// This class is public only because DesignMotifModel should extend it.
public abstract class AbstractModel implements IAbstractModel {
	private static final long serialVersionUID = -3222006532486393116L;
	private AbstractGenericContainerOfConstituents container =
		new GenericContainerOfNaturallyOrderedConstituents(this);
	private IWalker eventGenerator;

	private IFactory factory;
	// Yann 2010/10/10: DB4O
	// Used to be:
	//	private final char[] name;
	// I removed the final to make DB4O works...
	// TODO: Understand how to keep it final with DB4O!
	private char[] name;

	// Yann 2010/10/10: DB4O
	// Used to be:
	//	private final char[] path;
	// I removed the final to make DB4O works...
	// TODO: Understand how to keep it final with DB4O!
	private final char[] path;

	private GenericContainerOfTopLevelEntities topLevelEntitiesContainer =
		new GenericContainerOfTopLevelEntities(this);
	public AbstractModel(final char[] aName) {
		if (aName.length == 0) {
			// Yann 2011/06/18: Empty name for path...
			// I must deal with the case of a model
			// with an empty ("") name... yes, it does
			// happen more often than not and breaks
			// the Finder for path...
			this.name = Constants.NONAME;
		}
		else {
			this.name = aName;
		}
		this.path = new char[1 + this.name.length];
		this.path[0] = IConstants.ABSTRACT_MODEL_SYMBOL;
		System.arraycopy(this.name, 0, this.path, 1, this.name.length);

		// Yann 2013/05/22: Top-level entities!
		// See GenericContainerOfTopLevelEntities.TopLevelEntityManager
		this.addModelListener(this.topLevelEntitiesContainer.getListener());
	}
	public void addConstituent(final IConstituent aConstituent) {
		if (aConstituent instanceof IConstituentOfModel) {
			this.addConstituent((IConstituentOfModel) aConstituent);
		}
		else {
			throw new ModelDeclarationException(this.getClass().getName()
					+ " can only add IConstituentOfModel, not "
					+ aConstituent.getClass().getName());
		}
	}
	public void addConstituent(final IConstituentOfModel aConstituent) {
		this.container.addConstituent(aConstituent);

		// Yann 2013/05/22: Duplicate job!
		// I already copy the "topLevelEntitiesContainer" of this model
		// into the constituent when adding it, so no need to do it again!
		//	aConstituent.addModelListener(this.topLevelEntitiesContainer
		//		.getListener());
	}
	public void addModelListener(final IModelListener aModelListener) {
		this.container.addModelListener(aModelListener);
	}
	public void addModelListeners(final List aListOfModelListeners) {
		this.container.addModelListeners(aListOfModelListeners);
	}
	public Object clone() throws CloneNotSupportedException {
		// Yann: Here is the new clone protocole. This is subject
		// to discussion!! The idea is that a model may be cloned,
		// but a constituent of a model (Element or Entity) may
		// not be cloned individually, it does not make sense to clone
		// a Method if the rest of the model is not cloned to. I
		// believe this is actually a risk: to be able to clone a single
		// constituent on an individual basis may prove to create
		// duplicates with references on current objects...
		// Thus, only the AbstractLevelModel class implements the Cloneable interface.
		// The constituents of the model implements the
		// ICloneable interface. This model provides
		// three methods:
		// * startCloneSession() is somewhat equivallent to a shallow
		//   copy of the constituent. After this protocol is executed,
		//   it is guaranteed that all constituents have been
		//   shallow-copied. No assumption is made about the link among
		//   constituents.
		// * performCloneSession() updates the links among constituents,
		//   using the isCloned() and getClone() methods. After this
		//   protocol is executed, it is guarenteed that all the links
		//   have been updated, somewhat like a deep copy;
		// * endCloneSession() finished the updates and cleans the possible
		//   temporary values, mainly it sets to null all the "clone"
		//   instance variable.

		final AbstractModel clonedModel = (AbstractModel) super.clone();

		// Yann 2002/04/08: Clone!
		// The cloned model must have its own instance
		// of class ArrayList for the list or Entities.
		//	clonedModel.container.resetListOfConstituents();
		// Yann 2010/10/03: Objects!
		// The "container" is now an instance of a class
		// and must be assigned a new instance independently.
		clonedModel.container =
			new GenericContainerOfNaturallyOrderedConstituents(clonedModel);
		clonedModel.topLevelEntitiesContainer =
			new GenericContainerOfTopLevelEntities(clonedModel);
		// Yann 2013/05/22: Top-level entities!
		// See GenericContainerOfTopLevelEntities.TopLevelEntityManager
		clonedModel.addModelListener(clonedModel.topLevelEntitiesContainer
			.getListener());

		this.clone(clonedModel);

		// Yann 2011/06/20: Top-level entities...
		this.topLevelEntitiesContainer
			.createMapOfIDsEntities(Constants.FORBIDDEN_ID);

		return clonedModel;
	}
	protected void clone(final IAbstractModel anAbstractModel) {
		// First, I make a shallow copy of all the entities.
		Iterator iterator = this.getIteratorOnTopLevelEntities();
		while (iterator.hasNext()) {
			final IConstituentOfModel constituent =
				(IConstituentOfModel) iterator.next();
			constituent.startCloneSession();
			anAbstractModel.addConstituent((IConstituentOfModel) constituent
				.getClone());
		}

		// Second, I update the links among entities (deep copy).
		iterator = this.getIteratorOnTopLevelEntities();
		while (iterator.hasNext()) {
			((IConstituent) iterator.next()).performCloneSession();
		}

		// Finally, I clean up all temporary instance variables.
		iterator = this.getIteratorOnTopLevelEntities();
		while (iterator.hasNext()) {
			((IConstituent) iterator.next()).endCloneSession();
		}
	}
	public boolean doesContainConstituentWithID(final char[] anID) {
		return this.container.doesContainConstituentWithID(anID);
	}
	public boolean doesContainConstituentWithName(final char[] aName) {
		return this.container.doesContainConstituentWithName(aName);
	}
	public boolean doesContainTopLevelEntityWithID(final char[] anID) {
		return this.topLevelEntitiesContainer
			.doesContainTopLevelEntityWithID(anID);
	}
	public void fireModelChange(final String anEventType, final IEvent anEvent) {
		this.container.fireModelChange(anEventType, anEvent);
	}
	public String generate(final IGenerator aGenerator) {
		aGenerator.open(this);

		final Iterator iterator = this.getIteratorOnConstituents();
		while (iterator.hasNext()) {
			((IConstituent) iterator.next()).accept(aGenerator);
		}

		aGenerator.close(this);
		return aGenerator.getCode();
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
	public String getDisplayName() {
		return String.valueOf(this.name);
	}
	public String getDisplayPath() {
		return String.valueOf(this.path);
	}
	IWalker getEventGenerator() {
		return this.eventGenerator;
	}
	public IFactory getFactory() {
		return this.factory;
	}
	public Iterator getIteratorOnConstituents() {
		return this.container.getIteratorOnConstituents();
	}
	public Iterator getIteratorOnConstituents(final IFilter aFilter) {
		return this.container.getIteratorOnConstituents(aFilter);
	}
	public Iterator getIteratorOnConstituents(
		final java.lang.Class aConstituentType) {
		return this.container.getIteratorOnConstituents(aConstituentType);
	}
	public Iterator getIteratorOnModelListeners() {
		return this.container.getIteratorOnModelListeners();
	}
	public Iterator getIteratorOnTopLevelEntities() {
		return this.topLevelEntitiesContainer.getIteratorOnTopLevelEntities();
	}
	public char[] getName() {
		return this.name;
	}
	public int getNumberOfConstituents() {
		return this.container.getNumberOfConstituents();
	}
	public int getNumberOfConstituents(final java.lang.Class aConstituentType) {
		return this.container.getNumberOfConstituents(aConstituentType);
	}
	public int getNumberOfTopLevelEntities() {
		return this.topLevelEntitiesContainer.getNumberOfTopLevelEntities();
	}
	public int getNumberOfTopLevelEntities(final java.lang.Class anEntityType) {
		return this.topLevelEntitiesContainer
			.getNumberOfTopLevelEntities(anEntityType);
	}
	public char[] getPath() {
		return this.path;
	}
	public IFirstClassEntity getTopLevelEntityFromID(final char[] anID) {
		return this.topLevelEntitiesContainer.getTopLevelEntityFromID(anID);
	}
	public IFirstClassEntity getTopLevelEntityFromID(final String anID) {
		return this.getTopLevelEntityFromID(anID.toCharArray());
	}
	public void moveIn(final IAbstractModel aDestinationModel) {
		// TODO This should be this.getConcurrentIteratorOnConstituents()
		// must implement fail-fast for Iterator... see modCount in AbstractList in JDK.
		final Iterator iterator = this.getConcurrentIteratorOnConstituents();
		while (iterator.hasNext()) {
			final IConstituentOfModel constituent =
				(IConstituentOfModel) iterator.next();
			this.removeConstituentFromID(constituent.getID());
			aDestinationModel.addConstituent(constituent);
		}

		final AbstractModel destinationModel =
			(AbstractModel) aDestinationModel;
		destinationModel.setEventGenerator(this.getEventGenerator());
		destinationModel.setFactory(this.getFactory());
		aDestinationModel.walk(destinationModel.getEventGenerator());
	}
	public void removeAllConstituent() {
		this.container.removeAllConstituent();
	}
	public void removeConstituentFromID(final char[] anID) {
		// Yann 2013/05/22: Top-level entities
		// I don't forget to remove any top-level entities that could
		// belong to the constituent being removed... for consistency!
		final IConstituent constituent = this.getConstituentFromID(anID);
		if (constituent instanceof IPackage) {
			final Iterator iterator =
				((IPackage) constituent).getIteratorOnConstituents();
			while (iterator.hasNext()) {
				final IConstituent constituentOfConstituent =
					(IConstituent) iterator.next();
				this.topLevelEntitiesContainer
					.removeTopLevelEntityFromID(constituentOfConstituent
						.getID());
			}
		}

		this.container.removeConstituentFromID(anID);
	}
	public void removeModelListener(final IModelListener aModelListener) {
		this.container.removeModelListener(aModelListener);
	}
	public void removeModelListeners(final List aListOfModelListeners) {
		this.container.removeModelListeners(aListOfModelListeners);
	}
	public void removeTopLevelEntityFromID(final char[] anID) {
		// Yann 2013/05/22: Top-level entities
		// I didn't forget to remove this top-level entity from
		// wherever constituent it actually concretely belong...
		// This removal is taken care for by 
		//	GenericContainerOfTopLevelEntities.TopLevelEntityManager
		// which is interesting use of the Observer design pattern!
		//	final IConstituent constituent =
		//		this.topLevelEntitiesContainer.getTopLevelEntityFromID(anID);
		//	final IContainer container =
		//		Finder.findContainer(constituent.getDisplayPath(), this);
		//	container.removeConstituentFromID(anID);
		// Actually, it was wrong conceptually of me to do: 
		//	this.topLevelEntitiesContainer.removeTopLevelEntityFromID(anID);
		// because it is the listener that should take care of 
		// removing a top-level entities from its container. But,
		// because I want to keep the possibility to remove a
		// top-level entitiy directly from an abstract-level model,
		// I must do something a bit more clever: remove the top-level
		// entity from its true container and let the listener take
		// care of the rest...
		try {
			final IConstituent constituent =
				this.topLevelEntitiesContainer.getTopLevelEntityFromID(anID);
			final IContainer container =
				Finder.findContainer(constituent.getDisplayPath(), this);
			container.removeConstituentFromID(anID);
		}
		catch (final FormatException e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
	}
	public void setEventGenerator(final IWalker anEventGenerator) {
		this.eventGenerator = anEventGenerator;
	}
	public void setFactory(final IFactory aFactory) {
		this.factory = aFactory;
	}
	public String toString() {
		final StringBuffer buffer = new StringBuffer();
		final Iterator iterator = this.getIteratorOnConstituents();
		while (iterator.hasNext()) {
			buffer.append(iterator.next().toString());
			if (iterator.hasNext()) {
				buffer.append('\n');
			}
		}
		return buffer.toString();
	}
	public Object walk(final IWalker aWalker) {
		aWalker.open(this);

		// Yann 2004/12/19: Comodification again...
		// I prevent comodification exceptions by iterating
		// "by hand" over the list of entities.
		//	final Iterator iterator = this.listOfConstituents().iterator();
		//	while (iterator.hasNext()) {
		//		((IConstituent) iterator.next()).accept(walker);
		//	}
		// Yann 2005/10/12: Iterator!
		// I now provide iterators to go through a list of actors
		// rather than using the depracated (and soon to disappear)
		// listOfConstituents() method. So, I also provide an iterator
		// iterating over a copy of the list, thus allowing concurrent
		// modifications.
		//	final List listOfConstituents = this.listOfConstituents();
		//	for (int i = 0; i < listOfConstituents.size(); i++) {
		//		((IConstituent) listOfConstituents.get(i)).accept(walker);
		//	}
		final Iterator iterator = this.getConcurrentIteratorOnConstituents();
		while (iterator.hasNext()) {
			((IConstituent) iterator.next()).accept(aWalker);
		}

		aWalker.close(this);
		return aWalker.getResult();
	}
}
