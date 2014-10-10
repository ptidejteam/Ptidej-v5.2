/*******************************************************************************
 * Copyright (c) 2001-2014 Yann-Gaël Guéhéneuc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/old-licenses/gpl-2.0.html
 * 
 * Contributors:
 *     Yann-Gaël Guéhéneuc and others, see in file; API and its implementation
 ******************************************************************************/
package padl.kernel.impl;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import padl.event.IEvent;
import padl.event.IModelListener;
import util.io.ProxyConsole;

class GenericObservable implements Serializable {
	private static final long serialVersionUID = 198074990982735832L;
	// Yann 2010/10/10: DB4O
	// Used to be:
	//	private final List listOfModelListeners =
	// I removed the final to make DB4O works...
	// TODO: Understand how to keep it final with DB4O!
	private List listOfModelListeners = new ArrayList(
		GenericContainerConstants.INITIAL_SIZE_GENERIC_OBSERVABLE);

	public final void addModelListener(final IModelListener aModelListener) {
		this.listOfModelListeners.add(aModelListener);
	}
	public final void addModelListeners(final List aListOfModelListeners) {
		final Iterator iterator = aListOfModelListeners.iterator();
		while (iterator.hasNext()) {
			this.addModelListener((IModelListener) iterator.next());
		}
	}
	public final void fireModelChange(
		final String eventType,
		final IEvent modelEvent) {

		if (this.listOfModelListeners.size() > 0) {
			// First, I look for the method corresponding to the event type.
			final Method[] methods = IModelListener.class.getDeclaredMethods();
			Method eventMethod = null;
			for (int i = 0; i < methods.length && eventMethod == null; i++) {
				if (methods[i].getName().equals(eventType)) {
					eventMethod = methods[i];
				}
			}

			// Second, I notify the listeners with the appropriate event.
			if (eventMethod != null) {
				final Iterator iterator = this.listOfModelListeners.iterator();
				while (iterator.hasNext()) {
					final IModelListener listener =
						(IModelListener) iterator.next();
					if (listener != null) {
						try {
							eventMethod.invoke(
								listener,
								new Object[] { modelEvent });
						}
						catch (final IllegalAccessException iae) {
							iae.printStackTrace(ProxyConsole
								.getInstance()
								.errorOutput());
						}
						catch (final InvocationTargetException ite) {
							ite.printStackTrace(ProxyConsole
								.getInstance()
								.errorOutput());
						}
					}
				}
			}
		}
	}
	public Iterator getIteratorOnModelListeners() {
		return this.listOfModelListeners.iterator();
	}
	protected List getModelListeners() {
		return this.listOfModelListeners;
	}
	public final void removeModelListener(final IModelListener aModelListener) {
		this.listOfModelListeners.remove(aModelListener);
	}
	public final void removeModelListeners(final List aListOfModelListeners) {
		final Iterator iterator = aListOfModelListeners.iterator();
		while (iterator.hasNext()) {
			this.removeModelListener((IModelListener) iterator.next());
		}
	}
}
