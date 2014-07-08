/*
 * (c) Copyright 2000-2002 Yann-Gaël Guéhéneuc,
 * Ecole des Mines de Nantes and Object Technology International, Inc.
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
package ptidej.viewer.extension.repository;

import java.io.IOException;
import javax.swing.JFrame;
import ptidej.viewer.IRepresentation;
import util.io.OutputMonitor;
import util.io.ProxyConsole;

public class Dotty extends JFrame {
	private static final long serialVersionUID = 1L;

	protected void callDotty(
		final IRepresentation aRepresentation,
		final String aFilePath) {

		this.setVisible(false);
		this.getContentPane().removeAll();

		try {
			final StringBuffer commandLine = new StringBuffer();
			commandLine.append("..\\DOT\\bin\\dotty ");
			commandLine.append(aFilePath);
			final Process process =
				Runtime.getRuntime().exec(commandLine.toString());
			final OutputMonitor errorStreamMonitor =
				new OutputMonitor(
					"Dotty Stream Monitor",
					"",
					process.getErrorStream(),
					System.out);
			errorStreamMonitor.start();
			final OutputMonitor inputStreamMonitor =
				new OutputMonitor(
					"Dotty Input Stream Monitor",
					"",
					process.getInputStream(),
					System.out);
			inputStreamMonitor.start();

			// I wait for the process to finish
			// in a new thread to allow multi-tasking.
			new Thread(new Runnable() {
				public void run() {
					try {
						process.waitFor();
					}
					catch (final InterruptedException ie) {
						ie.printStackTrace(ProxyConsole
							.getInstance()
							.errorOutput());
					}

					// I check if everything went alright.
					if (process.exitValue() != 0) {
						try {
							while (process.getErrorStream().available() > 0) {
								System.out.print((char) process
									.getErrorStream()
									.read());
							}
						}
						catch (final IOException ioe) {
							ioe.printStackTrace(ProxyConsole
								.getInstance()
								.errorOutput());
						}
					}
				}
			}).start();
		}
		catch (final IOException ioe) {
			ioe.printStackTrace(ProxyConsole.getInstance().errorOutput());
		}
	}
}
