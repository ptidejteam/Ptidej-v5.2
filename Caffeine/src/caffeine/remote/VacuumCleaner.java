/*
 * (c) Copyright 2001 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes and Object Technology Internationl, Inc.
 * 
 * Use and copying of this software and preparation of derivative works
 * based upon this software are permitted. Any copy of this software or
 * of any derivative work must include the above copyright notice of
 * Yann-Gaël Guéhéneuc, this paragraph and the one after it.
 * 
 * This software is made available AS IS, and THE AUTHOR DISCLAIMS
 * ALL WARRANTIES, EXPRESS OR IMPLIED, INCLUDING WITHOUT LIMITATION THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN, ANY
 * LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS ADVISED
 * OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package caffeine.remote;

import util.io.ProxyConsole;
import caffeine.Constants;

/**
 * @version 	0.2
 * @author		Yann-Gaël Guéhéneuc
 */
public final class VacuumCleaner extends Thread {
	public VacuumCleaner(final String name, final Thread mainThread) {
		super(mainThread.getThreadGroup().getParent(), name);
		this.setPriority(Thread.MIN_PRIORITY);
		// This thread should not be a daemon, because we want
		// it to keep running event after the program thread(s)
		// terminated, to get all finalizer calls and class
		// unload events.
	}
	public void run() {
		try {
			boolean isAnyUserThreadAlive = true;

			while (isAnyUserThreadAlive) {
				VacuumCleaner.clean();

				final int numberOfActiveThreads = Thread.activeCount();
				final Thread[] activeThreads =
					new Thread[numberOfActiveThreads];
				Thread.enumerate(activeThreads);
				isAnyUserThreadAlive = false;
				for (int i = 0; i < numberOfActiveThreads
						&& !isAnyUserThreadAlive; i++) {
					for (int j = 0; j < Constants.NUMBER_OF_USER_SYSTEM_THREAD_NAMES; j++) {
						if (activeThreads[i] != null
								&& activeThreads[i].getName().equals(
									Constants.USER_SYSTEM_THREAD_NAMES[j])) {
							isAnyUserThreadAlive = true;
						}
					}
				}
			}
			//  if (Caffeine.Debug) {
			//  	System.err.println("Vacuum cleaner terminated.");
			//	}
			for (int i = 0; i < Constants.VACUUM_CLEANER_LATENT_PERIOD; i++) {

				VacuumCleaner.clean();
			}
		}
		catch (final Exception e) {
			e.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
	}
	private static void clean() throws InterruptedException {
		Thread.yield();
		Thread.sleep(Constants.VACUUM_CLEANER_WAKE_UP_TIME);
		System.gc();
		System.runFinalization();
	}
}