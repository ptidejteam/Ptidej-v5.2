/*
 * (c) Copyright 2001-2003 Yann-Gaël Guéhéneuc,
 * École des Mines de Nantes and Object Technology International, Inc.
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
