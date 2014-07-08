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
package caffeine.example.gcd.ui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;

import caffeine.Constants;

public final class GCD {
	public GCD() {
		super();
	}
	public int computeGCD(int a, int b) {
		if (b == 0) {
			return a;
		}
		else {
			return this.computeGCD(b, a % b);
		}
	}
	public static void main(final String[] args) {
		if (Constants.DEBUG) {
			System.out.println("GCD: 1");
		}

		final JFrame frame = new JFrame("Euclid's GCD algorithm");
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.setLocation(400, 100);
		frame.setSize(300, 100);
		final JTextField text = new JTextField("Results");
		frame.getContentPane().add(text);
		frame.setVisible(true);

		if (Constants.DEBUG) {
			System.out.println("GCD: 2");
		}

		int a = 6;
		int b = 18;
		final StringBuffer buffer = new StringBuffer();
		final GCD gcd = new GCD();

		if (Constants.DEBUG) {
			System.out.println("GCD: 3");
		}

		while (true) {
			buffer.setLength(0);
			buffer.append(text.getText());
			buffer.append("\n\rGCD(");
			buffer.append(a);
			buffer.append(", ");
			buffer.append(b);
			buffer.append(") = ");
			buffer.append(gcd.computeGCD(a, b));
			text.setText(buffer.toString());
			a = a + 1;
			b = b + 3;
		}
	}
}