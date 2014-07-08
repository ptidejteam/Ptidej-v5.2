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
package caffeine.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import util.io.OutputMonitor;
import caffeine.logic.PrologListener;

import com.sun.jdi.VMDisconnectedException;
import com.sun.jdi.VirtualMachine;

/**
 * @author	Yann-Gaël Guéhéneuc
 * @version 0.1
 */
public final class Simple extends JFrame {
	private static final long serialVersionUID = -8592609882923126128L;

	private final VirtualMachine jvm;
	private final OutputMonitor streamOutputMonitor;
	private final PrologListener prologListener;

	public Simple(
		final VirtualMachine jvm,
		final OutputMonitor streamOutputMonitor,
		final PrologListener prologListener) {

		this.jvm = jvm;
		this.streamOutputMonitor = streamOutputMonitor;
		this.prologListener = prologListener;

		this.setTitle("Caffeine");
		this.setLocation(100, 100);
		this.setSize(300, 100);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.getContentPane().setLayout(new BorderLayout());

		final JButton suspendResumeButton = new JButton("Resume");
		suspendResumeButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				try {
					final JButton button = (JButton) e.getSource();
					if (button.getText().equals("Suspend")) {
						if (Simple.this.prologListener.suspend()) {
							button.setText("Resume");
						}
					}
					else {
						if (Simple.this.prologListener.resume()) {
							button.setText("Suspend");
						}
					}
				}
				catch (final VMDisconnectedException vmde) {
				}
			}
		});
		this.getContentPane().add(suspendResumeButton, BorderLayout.WEST);

		final JButton verboseButton = new JButton("Quiet");
		verboseButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				final JButton button = (JButton) e.getSource();
				if (button.getText().equals("Quiet")) {
					button.setText("Verbose");
					Simple.this.streamOutputMonitor.setVerbose(false);
				}
				else {
					button.setText("Quiet");
					Simple.this.streamOutputMonitor.setVerbose(true);
				}
			}
		});
		this.getContentPane().add(verboseButton, BorderLayout.CENTER);

		final JButton stopButton = new JButton("Stop");
		stopButton.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				try {
					Simple.this.jvm.exit(0);
				}
				catch (final VMDisconnectedException vmde) {
				}

			}
		});
		this.getContentPane().add(stopButton, BorderLayout.EAST);
		this.setVisible(true);
	}
}
