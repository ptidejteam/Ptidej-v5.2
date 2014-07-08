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
package ptidej.ui.analysis.repository.comparator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import padl.kernel.IAbstractModel;
import padl.kernel.IAggregation;
import padl.kernel.IAssociation;
import padl.kernel.IClass;
import padl.kernel.IComposition;
import padl.kernel.IConstituent;
import padl.kernel.IConstructor;
import padl.kernel.IContainerAggregation;
import padl.kernel.IContainerComposition;
import padl.kernel.ICreation;
import padl.kernel.IDelegatingMethod;
import padl.kernel.IField;
import padl.kernel.IFirstClassEntity;
import padl.kernel.IGetter;
import padl.kernel.IGhost;
import padl.kernel.IInterface;
import padl.kernel.IMemberClass;
import padl.kernel.IMemberGhost;
import padl.kernel.IMemberInterface;
import padl.kernel.IMethod;
import padl.kernel.IMethodInvocation;
import padl.kernel.IPackage;
import padl.kernel.IPackageDefault;
import padl.kernel.IParameter;
import padl.kernel.IPrimitiveEntity;
import padl.kernel.ISetter;
import padl.kernel.IUseRelationship;
import padl.visitor.IWalker;
import util.io.ProxyConsole;
import util.multilingual.MultilingualManager;

/**
 * @author Yann-Gaël Guéhéneuc
 * @since  2004/12/20
 */
public class HighlighterFromMethods implements IWalker {
	private static void addToMap(
		final String aClassName,
		final String aMethodName,
		final Map aMap) {

		final List listOfMethods;
		if (aMap.containsKey(aClassName)) {
			listOfMethods = (List) aMap.get(aClassName);
		}
		else {
			listOfMethods = new ArrayList();
			aMap.put(aClassName, listOfMethods);
		}
		listOfMethods.add(aMethodName);
	}

	private IAbstractModel abstractLevelModel;
	private final Map addedMethods;
	private final Map removedMethods;

	public HighlighterFromMethods(final Properties someDifferences) {
		Manager.getUniqueInstance().reset();

		this.addedMethods = new HashMap(someDifferences.size());
		this.removedMethods = new HashMap(someDifferences.size());

		final Iterator iterator = someDifferences.keySet().iterator();
		while (iterator.hasNext()) {
			final String key = (String) iterator.next();
			final String value = (String) someDifferences.get(key);
			final int pointIndex = value.indexOf('.');
			if (pointIndex > 0) {
				final String className = value.substring(0, pointIndex);
				final String methodName = value.substring(pointIndex + 1);

				if (key.indexOf("Added") > 0) {
					HighlighterFromMethods.addToMap(
						className,
						methodName,
						this.addedMethods);
				}
				else if (key.indexOf("Removed") > 0) {
					HighlighterFromMethods.addToMap(
						className,
						methodName,
						this.removedMethods);
				}
				else {
					System.err.println(MultilingualManager.getString(
						"Err_UNKNOWN_KEY",
						HighlighterFromMethods.class,
						new Object[] { key }));
				}
			}
		}
	}
	public void close(final IAbstractModel anAbstractModel) {
	}
	public void close(final IClass aClass) {
	}
	public void close(final IConstructor aConstructor) {
	}
	public void close(final IDelegatingMethod aDelegatingMethod) {
	}
	public void close(final IGetter aGetter) {
	}
	public void close(final IGhost aGhost) {
	}
	public void close(final IInterface anInterface) {
	}
	public void close(final IMemberClass aMemberClass) {
	}
	public void close(final IMemberGhost aMemberGhost) {
	}
	public void close(final IMemberInterface aMemberInterface) {
	}
	public void close(final IMethod aMethod) {
	}
	public void close(final IPackage aPackage) {
	}
	public void close(final IPackageDefault aPackage) {
	}
	public void close(final ISetter aSetter) {
	}
	public String getName() {
		return "Highligther from methods";
	}
	public Object getResult() {
		return this.abstractLevelModel;
	}
	public void open(final IAbstractModel anAbstractModel) {
		this.abstractLevelModel = anAbstractModel;
	}
	public void open(final IClass aClass) {
		this.open((IFirstClassEntity) aClass);
	}
	public void open(final IConstructor aConstructor) {
	}
	public void open(final IDelegatingMethod aDelegatingMethod) {
	}
	private void open(final IFirstClassEntity anEntity) {
		try {
			final String name = anEntity.getDisplayName();
			final boolean isEntityModified;

			if (this.addedMethods.containsKey(name)) {
				final Iterator iterator =
					((List) this.addedMethods.get(name)).iterator();
				while (iterator.hasNext()) {
					String methodName = (String) iterator.next();
					methodName =
						methodName.substring(0, methodName.indexOf('('));
					Manager.getUniqueInstance().handleAddedMethod(
						this.abstractLevelModel,
						anEntity,
						methodName);
				}

				isEntityModified = true;
			}
			else if (this.removedMethods.containsKey(name)) {
				final Iterator iterator =
					((List) this.removedMethods.get(name)).iterator();
				while (iterator.hasNext()) {
					String methodName = (String) iterator.next();
					methodName =
						methodName.substring(0, methodName.indexOf('('));
					Manager.getUniqueInstance().handleRemovedMethod(
						this.abstractLevelModel,
						anEntity,
						methodName);
				}

				isEntityModified = true;
			}
			else {
				isEntityModified = false;
			}

			if (isEntityModified) {
				Manager.getUniqueInstance().handleModifiedEntity(
					this.abstractLevelModel,
					anEntity);
			}
		}
		catch (final StringIndexOutOfBoundsException e) {
		}
	}
	public void open(final IGetter aGetter) {
	}
	public void open(final IGhost aGhost) {
		this.open((IFirstClassEntity) aGhost);
	}
	public void open(final IInterface anInterface) {
		this.open((IFirstClassEntity) anInterface);
	}
	public void open(final IMemberClass aMemberClass) {
		this.open((IFirstClassEntity) aMemberClass);
	}
	public void open(final IMemberGhost aMemberGhost) {
		this.open((IFirstClassEntity) aMemberGhost);
	}
	public void open(final IMemberInterface aMemberInterface) {
		this.open((IFirstClassEntity) aMemberInterface);
	}
	public void open(final IMethod aMethod) {
	}
	public void open(final IPackage aPackage) {
	}
	public void open(final IPackageDefault aPackage) {
	}
	public void open(final ISetter aSetter) {
	}
	public void reset() {
	}
	public final void unknownConstituentHandler(
		final String aCalledMethodName,
		final IConstituent aConstituent) {

		ProxyConsole
			.getInstance()
			.debugOutput()
			.print(this.getClass().getName());
		ProxyConsole
			.getInstance()
			.debugOutput()
			.print(" does not know what to do for \"");
		ProxyConsole.getInstance().debugOutput().print(aCalledMethodName);
		ProxyConsole.getInstance().debugOutput().print("\" (");
		ProxyConsole
			.getInstance()
			.debugOutput()
			.print(aConstituent.getDisplayID());
		ProxyConsole.getInstance().debugOutput().println(')');
	}
	public void visit(final IAggregation anAggregation) {
	}
	public void visit(final IAssociation anAssociation) {
	}
	public void visit(final IComposition aComposition) {
	}
	public void visit(final IContainerAggregation aContainerAggregation) {
	}
	public void visit(final IContainerComposition aContainerComposition) {
	}
	public void visit(final ICreation aCreation) {
	}
	public void visit(final IField aField) {
	}
	public void visit(final IMethodInvocation aMethodInvocation) {
	}
	public void visit(final IParameter aParameter) {
	}
	public void visit(final IPrimitiveEntity aPrimitiveEntity) {
		// Do nothing for uninteresting primitive types.
	}
	public void visit(final IUseRelationship aUse) {
	}
}
