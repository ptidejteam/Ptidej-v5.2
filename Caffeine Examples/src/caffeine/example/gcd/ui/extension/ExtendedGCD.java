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
package caffeine.example.gcd.ui.extension;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;

public final class ExtendedGCD {
	public static final int X_ORIGIN = 100;
	public static final int Y_ORIGIN = 100;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 500;

	public ExtendedGCD() {
		super();
	}

	public static void main(final String[] args) {
		final JFrame frame =
			new JFrame("Association, aggregation, and composition relationships demonstrated");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setBounds(
			ExtendedGCD.X_ORIGIN,
			ExtendedGCD.Y_ORIGIN,
			ExtendedGCD.WIDTH,
			ExtendedGCD.HEIGHT);

		final JDesktopPane layeredPane = new JDesktopPane();
		frame.getContentPane().add("Center", layeredPane);

		final GCDController gcdController = new GCDController();
		layeredPane.add(gcdController);

		// Now that all the components belong to a container,
		// all the components know their parents. I can
		// initialize the GCDController instance.
		gcdController.initialize();

		// I show the window.
		frame.setVisible(true);
	}
}