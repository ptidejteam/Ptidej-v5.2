/* (c) Copyright 2008 and following years, Yann-Gaël Guéhéneuc,
 * École Polytechnique de Montréal.
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
package padl.creator.cppfile.eclipse.misc;

import com.sun.jdi.VMDisconnectedException;
import com.sun.jdi.VirtualMachine;
import com.sun.jdi.event.ClassPrepareEvent;
import com.sun.jdi.event.Event;
import com.sun.jdi.event.EventIterator;
import com.sun.jdi.event.EventQueue;
import com.sun.jdi.event.EventSet;
import com.sun.jdi.event.ExceptionEvent;
import com.sun.jdi.event.MethodEntryEvent;
import com.sun.jdi.event.MethodExitEvent;
import com.sun.jdi.event.ModificationWatchpointEvent;
import com.sun.jdi.event.StepEvent;
import com.sun.jdi.event.ThreadDeathEvent;
import com.sun.jdi.event.VMDeathEvent;
import com.sun.jdi.event.VMDisconnectEvent;
import com.sun.jdi.event.VMStartEvent;

public class EventMonitor extends Thread {
	private boolean connected = true;
	private final VirtualMachine vm;

	public EventMonitor(final VirtualMachine aVirtualMachine) {
		this.vm = aVirtualMachine;
	}
	public void run() {
		final EventQueue queue = this.vm.eventQueue();
		while (this.connected) {
			try {
				final EventSet eventSet = queue.remove();
				final EventIterator it = eventSet.eventIterator();
				while (it.hasNext()) {
					this.handleEvent(it.nextEvent());
				}
				eventSet.resume();
			}
			catch (final InterruptedException exc) {
				// Ignore
			}
			catch (final VMDisconnectedException discExc) {
				this.handleDisconnectedException();
				break;
			}
		}
	}
	/***
	 * A VMDisconnectedException has happened while dealing with
	 * another event. We need to flush the event queue, dealing only
	 * with exit events (VMDeath, VMDisconnect) so that we terminate
	 * correctly.
	 */
	private synchronized void handleDisconnectedException() {
		final EventQueue queue = this.vm.eventQueue();
		while (this.connected) {
			try {
				final EventSet eventSet = queue.remove();
				final EventIterator iter = eventSet.eventIterator();
				while (iter.hasNext()) {
					final Event event = iter.nextEvent();
					if (event instanceof VMDeathEvent) {
						// Nothing to do.
					}
					else if (event instanceof VMDisconnectEvent) {
						this.connected = false;
					}
				}
				eventSet.resume();
			}
			catch (final InterruptedException exc) {
				// ignore
			}
		}
	}
	/**
	 * Dispatch incoming events
	 */
	private void handleEvent(final Event event) {
		if (event instanceof ExceptionEvent) {
		}
		else if (event instanceof ModificationWatchpointEvent) {
		}
		else if (event instanceof MethodEntryEvent) {
		}
		else if (event instanceof MethodExitEvent) {
		}
		else if (event instanceof StepEvent) {
		}
		else if (event instanceof ThreadDeathEvent) {
		}
		else if (event instanceof ClassPrepareEvent) {
		}
		else if (event instanceof VMStartEvent) {
		}
		else if (event instanceof VMDeathEvent) {
			// Nothing to do.
		}
		else if (event instanceof VMDisconnectEvent) {
			this.connected = false;
		}
		else {
			throw new Error("Unexpected event type");
		}
	}
}
