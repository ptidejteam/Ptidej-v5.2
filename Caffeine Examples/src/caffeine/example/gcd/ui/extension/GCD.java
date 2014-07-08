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

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public final class GCD extends JInternalFrame {
	private static final long serialVersionUID = -2222451761439949549L;

	public static final int X_ORIGIN = 450;
	public static final int Y_ORIGIN = 10;
	public static final int WIDTH = 300;
	public static final int HEIGHT = 200;

	private static int gcdNumber = -1;

	// This field plays part in a composition relationship:
	// The composition relationship between one instance of
	// class GCD and one instance of class Thread.
	private final Thread thread;
	private final int number;

	public GCD() {
		super("", false, true, true, true);

		this.number = this.hashCode();
		GCD.gcdNumber++;

		this.setTitle(this.getName());
		this.setBounds(
			GCD.X_ORIGIN - 100 * GCD.gcdNumber,
			GCD.Y_ORIGIN + 100 * GCD.gcdNumber,
			GCD.WIDTH,
			GCD.HEIGHT);
		this.addInternalFrameListener(new InternalFrameAdapter() {
			public void internalFrameClosing(InternalFrameEvent e) {
				GCD.this.dispose();
			}
		});
		final JTextField text = new JTextField("");
		this.getContentPane().add(text);
		this.setVisible(true);

		this.thread = new Thread(new Runnable() {
			public void run() {
				int a = GCD.this.number;
				int b = a;
				final StringBuffer buffer = new StringBuffer();
				while (!GCD.this.isClosed()) {
					buffer.setLength(0);
					buffer.append(text.getText());
					buffer.append("\n\rGCD(");
					buffer.append(a);
					buffer.append(", ");
					buffer.append(b);
					buffer.append(") = ");
					buffer.append(GCD.computeGCD(a, b));
					text.setText(buffer.toString());
					a = a + 1;
					b = b + 4;
				}
			}
		});
		this.thread.setName(this.getName());
		this.thread.setPriority(Thread.MIN_PRIORITY);
		this.thread.start();
	}
	public static int computeGCD(int a, int b) {
		if (b == 0) {
			return a;
		}
		else {
			return GCD.computeGCD(b, a % b);
		}
	}
	public String getName() {
		return "GCD: " + this.number;
	}
}