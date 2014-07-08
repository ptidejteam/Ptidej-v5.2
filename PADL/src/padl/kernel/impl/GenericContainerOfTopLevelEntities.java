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
package padl.kernel.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import padl.event.EntityEvent;
import padl.event.IModelListener;
import padl.kernel.Constants;
import padl.kernel.IAbstractModel;
import padl.kernel.IConstituent;
import padl.kernel.IConstituentOfModel;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IMemberEntity;
import padl.kernel.IPackage;
import padl.util.CharArrayComparator;
import padl.util.adapter.ModelListenerAdapter;
import util.io.ProxyConsole;

class GenericContainerOfTopLevelEntities implements Serializable {
	// Yann 2011/06/20: Top-level entities...
	// Aminata found an interesting bug with the top level entities:
	// because they used to have this special container, then removing
	// the enclosing package of a top-level entity would not make the
	// entity disappear from the model! I use the model-listener
	// mechanism to fix this bug... Elegant? Performance?
	private class TopLevelEntityManager extends ModelListenerAdapter implements
			IModelListener, Serializable {
		private static final long serialVersionUID = 5491840316982085512L;

		public String toString() {
			return "Instance of TopLevelEntityManager used to handle top-level entities";
		}
		public void entityAdded(final EntityEvent entityEvent) {
			final IConstituentOfModel constituent = entityEvent.getEntity();
			// Aminata 2011/08/23: Member entity are not top!
			// Adding another condition to avoid the member 
			// entities in the top level entities list

			if (constituent instanceof IFirstClassEntity
					&& !(constituent instanceof IMemberEntity)) {
				GenericContainerOfTopLevelEntities.this
					.addTopLevelEntity(constituent);
			}
			else if (constituent instanceof IPackage) {
				GenericContainerOfTopLevelEntities.this
					.createMapOfIDsEntities(Constants.FORBIDDEN_ID);
			}
		}
		public void entityRemoved(final EntityEvent entityEvent) {
			final IConstituentOfModel constituent = entityEvent.getEntity();
			if (constituent instanceof IFirstClassEntity) {
				GenericContainerOfTopLevelEntities.this
					.removeTopLevelEntityFromID(constituent.getID());
			}
			else if (constituent instanceof IPackage) {
				// Nothing to do because it is taken care of
				// by the remove of the IPackage and the listener.
				// throw new RuntimeException("Not yet implemented!");
			}
		}
	}

	private static final long serialVersionUID = -714741910389826912L;
	// Yann 2010/10/10: DB4O
	// Used to be:
	//	private final IAbstractModel containerAbstractModel;
	// I removed the final to make DB4O works...
	// TODO: Understand how to keep it final with DB4O!
	private IAbstractModel containerAbstractModel;

	// Yann 2010/10/10: DB4O
	// Used to be:
	//	private final Map mapOfIDsEntities = new TreeMap();
	// I removed the final to make DB4O works...
	// TODO: Understand how to keep it final with DB4O!
	private Map mapOfIDsEntities = new TreeMap(
		CharArrayComparator.getInstance());
	private final IModelListener topLevelEntityManager =
		new TopLevelEntityManager();

	// Yann 2010/06/21: Debug!
	// I count the number of "faults"...
	//	private static int numberOfCallsTocreateMapOfIDsEntities = 0;
	//	private static int numberOfCallsTogetTopLevelEntityFromID = 0;

	public GenericContainerOfTopLevelEntities(
		final IAbstractModel anAbstractModel) {
		this.containerAbstractModel = anAbstractModel;
	}

	public void addTopLevelEntity(final IConstituentOfModel aConstituentOfModel) {
		this.mapOfIDsEntities.put(
			aConstituentOfModel.getID(),
			aConstituentOfModel);
	}
	// TODO: Remove these methods to fill this.mapOfIDsEntities and use the
	// appropriate "addConstituent" method.
	// Yann 2011/06/20: Top-level entities...
	// Now that the AbstractLevelModel uses a listener to update appropriately
	// this container when constituent are added/removed, I could surely use it
	// to remove the following three methods and use the added/removed methods
	// of the listener to do their jobs only when need, i.e., when adding a
	// non-empty package to the model! This new implementation should improved
	// performances...
	void createMapOfIDsEntities(final char[] anID) {
		if (Arrays.equals(anID, Constants.FORBIDDEN_ID)
				|| !this.mapOfIDsEntities.containsKey(anID)) {

			ProxyConsole
				.getInstance()
				.debugOutput()
				.print("(Re)Creating the maps of IDs and Entities (because of ");
			ProxyConsole.getInstance().debugOutput().print(anID);
			ProxyConsole.getInstance().debugOutput().println(')');
			// Yann 2008/11/17: Walker!
			// I cannot use the visitor to count the number
			// of entities as new entity *not* taken into
			// account could be added to the model, for
			// example IEnum... I must compute the entities
			// recursively "by hand".
			// AbstractLevelModel.UniqueEntityFinder.reset();
			// AbstractLevelModel.UniqueEntityFinder.setID(anID);
			// this.walk(AbstractLevelModel.UniqueEntityFinder);
			// final IEntity entity =
			// (IEntity) AbstractLevelModel.UniqueEntityFinder.getResult();
			// if (entity != null) {
			// this.mapOfIDsEntities.put(anID, entity);
			// }
			this.mapOfIDsEntities.clear();

			final Iterator iterator =
				this.containerAbstractModel.getIteratorOnConstituents();
			while (iterator.hasNext()) {
				final IConstituent constituent = (IConstituent) iterator.next();
				if (constituent instanceof IPackage) {
					this.createMapOfIDsEntities((IPackage) constituent);
				}
				else if (IFirstClassEntity.class.isAssignableFrom(constituent
					.getClass())) {

					this
						.createMapOfIDsEntities((IFirstClassEntity) constituent);
				}
			}
		}
	}
	private void createMapOfIDsEntities(
		final IFirstClassEntity aConstituentOfModel) {
		// Yann 2009/05/03: Comparable!
		// I must use String as keys because
		// char arrays are not Comparable...
		// TODO: Implement my own hashtable!
		// Yann 2010/06/20: Arrays!
		// The mapOfIDsEntities now implements a comparator
		// for char arrays to avoid unnecessary conversion.
		this.mapOfIDsEntities.put(
			aConstituentOfModel.getID(),
			aConstituentOfModel);
	}

	private void createMapOfIDsEntities(final IPackage aPackage) {
		final Iterator iterator = aPackage.getIteratorOnConstituents();
		while (iterator.hasNext()) {
			final IConstituent constituent = (IConstituent) iterator.next();
			if (constituent instanceof IPackage) {
				this.createMapOfIDsEntities((IPackage) constituent);
			}
			else if (IFirstClassEntity.class.isAssignableFrom(constituent
				.getClass())) {

				this.createMapOfIDsEntities((IFirstClassEntity) constituent);
			}
		}
	}
	public boolean doesContainTopLevelEntityWithID(char[] anID) {
		return this.getTopLevelEntityFromID(anID) != null;
	}
	public Iterator getIteratorOnTopLevelEntities() {
		// System.out.println("Iterating on entities");

		// Yann 2008/11/17: Walker!
		// I cannot use the visitor to count the number
		// of entities as new entity *not* taken into
		// account could be added to the model, for
		// example IEnum... I must compute the entities
		// recursively "by hand".
		// AbstractLevelModel.UniqueEntityLister.reset();
		// this.walk(AbstractLevelModel.UniqueEntityLister);
		// final List listOfEntities =
		// (List) AbstractLevelModel.UniqueEntityLister.getResult();
		//	this.createMapOfIDsEntities(Constants.FORBIDDEN_ID);
		final List listOfEntities =
			new ArrayList(this.mapOfIDsEntities.values());

		// Yann 2008/11/05: Sort!
		// I make sure I sort the list of entities according to
		// their ID to preserve the behavior of other code that
		// assumes this order.
		Collections.sort(listOfEntities, new Comparator() {
			public int compare(final Object o1, final Object o2) {
				return CharArrayComparator.getInstance().compare(
					((IFirstClassEntity) o1).getID(),
					((IFirstClassEntity) o2).getID());
			}
		});
		return listOfEntities.iterator();
	}
	public IModelListener getListener() {
		return this.topLevelEntityManager;
	}
	public int getNumberOfTopLevelEntities() {
		// Yann 2008/11/17: Walker!
		// I cannot use the visitor to count the number
		// of entities as new entity *not* taken into
		// account could be added to the model, for
		// example IEnum... I must compute the entities
		// recursively "by hand".
		// AbstractLevelModel.UniqueEntityLister.reset();
		// this.walk(AbstractLevelModel.UniqueEntityLister);
		// return ((List) AbstractLevelModel.UniqueEntityLister.getResult())
		// .size();

		//	this.createMapOfIDsEntities(Constants.FORBIDDEN_ID);
		return this.mapOfIDsEntities.size();
	}
	public int getNumberOfTopLevelEntities(final java.lang.Class aClass) {
		// Yann 2008/11/17: Walker!
		// I cannot use the visitor to count the number
		// of entities as new entity *not* taken into
		// account could be added to the model, for
		// example IEnum... I must compute the entities
		// recursively "by hand".
		// AbstractLevelModel.UniqueEntityLister.reset();
		// this.walk(AbstractLevelModel.UniqueEntityLister);
		// return ((List) AbstractLevelModel.UniqueEntityLister.getResult())
		// .size();
		//	this.createMapOfIDsEntities(Constants.FORBIDDEN_ID);

		int number = 0;
		final Iterator iterator = this.mapOfIDsEntities.values().iterator();
		while (iterator.hasNext()) {
			final IConstituent constituent = (IConstituent) iterator.next();
			if (aClass.isAssignableFrom(constituent.getClass())) {
				number++;
			}
		}
		return number;
	}
	public IFirstClassEntity getTopLevelEntityFromID(final char[] anID) {
		//	GenericContainerOfTopLevelEntities.numberOfCallsTogetTopLevelEntityFromID++;

		// Yann 2008/11/04: Global access!
		// The addition of packages means that we need a global
		// mechanism to access constituent wherever they are...
		//	this.createMapOfIDsEntities(anID);
		//	System.out
		//		.println(GenericContainerOfTopLevelEntities.numberOfCallsTogetTopLevelEntityFromID
		//				+ " "
		//				+ GenericContainerOfTopLevelEntities.numberOfCallsTocreateMapOfIDsEntities
		//				+ " " + String.valueOf(anID));

		return (IFirstClassEntity) this.mapOfIDsEntities.get(anID);
	}
	public void removeTopLevelEntityFromID(final char[] anID) {
		// Yann 2008/11/04: Global access!
		// The addition of packages means that we need a global
		// mechanism to access constituent wherever they are...

		// Yann 2011/06/20: Top-level entities...
		// If a user wants to remove a top-level entity by calling the
		// removeTopLevelEntityFromID(char[]) method on a model, then the
		// removeConstituentFromID(char[]) method is called, to also
		// remove the entity from the appropriate container, which then
		// notifies the GenericContainerOfTopLevelEntity, which finally
		// call again this very same method... Hence the need to test of
		// null entity: the entity has already been removed...
		// Yann 2013/05/22: Again...
		// I do not need to do that "manually" because it is taken care of by
		//	GenericContainerOfTopLevelEntities.TopLevelEntityManager
		// which is interesting use of the Observer design pattern!
		//	final IFirstClassEntity entity = this.getTopLevelEntityFromID(anID);
		//	if (entity != null) {
		//		if (!isMove) {
		//			try {
		//				final IContainer container =
		//					Finder.findContainer(
		//						entity.getDisplayPath(),
		//						this.containerAbstractModel);
		//				container.removeConstituentFromID(anID);
		//			}
		//			catch (final FormatException e) {
		//				e.printStackTrace(Output.getInstance().errorOutput());
		//			}
		//		}
		//	}
		this.mapOfIDsEntities.remove(anID);
	}
}
