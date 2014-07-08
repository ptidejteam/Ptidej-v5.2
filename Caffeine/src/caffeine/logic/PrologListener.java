/*
 * (c) Copyright 2002-2003 Yann-Gaël Guéhéneuc,
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
package caffeine.logic;

import java.util.Iterator;

import JIP.engine.JIPErrorEvent;
import JIP.engine.JIPErrorListener;
import JIP.engine.JIPEvent;
import JIP.engine.JIPEventListener;
import JIP.engine.JIPTraceEvent;
import JIP.engine.JIPTraceListener;

/**
 * @author	Yann-Gaël Guéhéneuc
 * @version 0.1
 */
public final class PrologListener
	implements JIPErrorListener, JIPEventListener, JIPTraceListener {

	private boolean keepOn = true;
	private JIPTraceEvent traceEvent;

	public void callNotified(final JIPTraceEvent traceEvent) {
		// System.out.println("callNotified");
		this.keepOn(traceEvent);
	}
	public void closeNotified(final JIPEvent jipEvent) {
		// System.out.println("closeNotified");
	}
	private void display(final JIPTraceEvent traceEvent) {
		System.err.println(traceEvent.getTerm());
		System.err.println(traceEvent.getHeight());
	}
	public void endNotified(final JIPEvent jipEvent) {
		// System.out.println("endNotified");
	}
	public void errorNotified(final JIPErrorEvent errorEvent) {
		System.err.println("An error occured: " + errorEvent);
		errorEvent.consume();
	}
	public void exitNotified(final JIPTraceEvent traceEvent) {
		// System.out.println("exitNotified");
		this.keepOn(traceEvent);
	}
	public void failNotified(final JIPTraceEvent traceEvent) {
		// System.out.println("failNotified");
		this.keepOn(traceEvent);
	}
	public void foundNotified(final JIPTraceEvent traceEvent) {
		// System.out.println("foundNotified");
		this.display(traceEvent);
		this.keepOn(traceEvent);
	}
	private void keepOn(final JIPTraceEvent traceEvent) {
		if (this.keepOn) {
			traceEvent.step();
		}
		else {
			this.traceEvent = traceEvent;
		}
	}
	public void moreNotified(final JIPEvent jipEvent) {
		// System.out.println("moreNotified");
	}
	public void openNotified(final JIPEvent jipEvent) {
		// System.out.println("openNotified");
	}
	public void redoNotified(final JIPTraceEvent traceEvent) {
		// System.out.println("redoNotified");
		this.keepOn(traceEvent);
	}
	public boolean resume() {
		if (this.traceEvent != null) {
			this.keepOn = true;
			this.traceEvent.step();
			return true;
		}
		return false;
	}
	public void solutionNotified(final JIPEvent jipEvent) {
		final StringBuffer buffer = new StringBuffer(25);

		buffer.append("Solution: ");
		buffer.append(jipEvent.getTerm());
		System.out.println(buffer.toString());
		buffer.setLength(0);

		final long time =
			EventManager.getVMDeathTimeStamp()
				- EventManager.getVMStartTimeStamp();
		final long steps = EventManager.getNumberOfSteps();
		buffer.append("JVM running time: ");
		buffer.append(time);
		buffer.append(" ms.");
		if (steps > 0) {
			buffer.append('(');
			buffer.append(time / steps);
			buffer.append(" ms. per step for the ");
			buffer.append(steps);
			buffer.append(" steps)");
		}
		System.out.println(buffer.toString());

		// Yann 2003/07/31: Listener.
		// I notify all the listerners that a new event
		// has been built and is sent to the Prolog engine.
		final Engine engine = (Engine) jipEvent.getSource();
		final Iterator iterator = engine.getCaffeineListeners().iterator();
		while (iterator.hasNext()) {
			((EngineListener) iterator.next()).engineTerminated(
				jipEvent.getTerm(),
				engine,
				time,
				steps);
		}
	}
	public boolean suspend() {
		if (this.traceEvent != null) {
			this.keepOn = false;
			return true;
		}
		return false;
	}
	public void termNotified(final JIPEvent jipEvent) {
		// System.out.print("termNotified");
	}
}