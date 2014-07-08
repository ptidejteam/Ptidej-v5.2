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

import java.io.Writer;
import java.util.List;

import JIP.engine.JIPEngine;

import com.sun.jdi.VMDisconnectedException;
import com.sun.jdi.VirtualMachine;

/**
 * This class represents the main and only access point
 * to Caffeine. It is a singleton, because the communication
 * between the program under analysis and the underlying Prolog
 * engine is performed through static methods.
 * 
 * @author	Yann-Gaël Guéhéneuc
 * @version 0.3
 */
public final class Engine extends JIPEngine {
	private static Engine UNIQUE_INSTANCE;

	public static Engine getUniqueInstance(
		final Writer traceWriter,
		final String[] classNameFilter,
		final int requiredEventMask,
		final String[][] fieldAccesses,
		final VirtualMachine jvm,
		final List caffeineListeners) {

		if (Engine.UNIQUE_INSTANCE == null) {
			Engine.UNIQUE_INSTANCE =
				new Engine(
					traceWriter,
					classNameFilter,
					requiredEventMask,
					fieldAccesses,
					jvm,
					caffeineListeners);
		}
		else {
			System.err.println(
				"Warning! One and only one engine can exist at a give time!");
			throw new RuntimeException("Reset the current engine first, using the resetUniqueInstance() static method");
		}
		return Engine.UNIQUE_INSTANCE;
	}
	public static void resetUniqueInstance() {
		if (Engine.UNIQUE_INSTANCE != null
			&& Engine.UNIQUE_INSTANCE.jvm != null) {

			try {
				Engine.UNIQUE_INSTANCE.jvm.exit(0);
			}
			catch (final VMDisconnectedException vmde) {
			}
		}
		EventManager.reset();
		Engine.UNIQUE_INSTANCE = null;
	}

	// I add these fields to "my" JIPEngine to pass
	// them along to the EventManager.
	private final List caffeineListeners;
	private final String[] classNameFilter;
	private final String[][] fieldAccesses;
	private final VirtualMachine jvm;
	private final int requiredEventMask;
	private final Writer traceWriter;

	private Engine(
		final Writer traceWriter,
		final String[] classNameFilter,
		final int requiredEventMask,
		final String[][] fieldAccesses,
		final VirtualMachine jvm,
		final List caffeineListeners) {

		this.traceWriter = traceWriter;
		this.classNameFilter = classNameFilter;
		this.requiredEventMask = requiredEventMask;
		this.fieldAccesses = fieldAccesses;
		this.jvm = jvm;
		this.caffeineListeners = caffeineListeners;
	}

	public List getCaffeineListeners() {
		return this.caffeineListeners;
	}
	public String[] getClassNameFilter() {
		return this.classNameFilter;
	}
	public String[][] getFieldAccesses() {
		return this.fieldAccesses;
	}
	public int getRequiredEventMask() {
		return this.requiredEventMask;
	}
	public Writer getTraceWriter() {
		return this.traceWriter;
	}
	public VirtualMachine getVirtualMachine() {
		return this.jvm;
	}
}