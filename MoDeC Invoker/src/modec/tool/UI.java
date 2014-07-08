/* (c) Copyright 2001 and following years, Yann-Gaël Guéhéneuc,
 * University of Montreal.
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
package modec.tool;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.Writer;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import util.io.ProxyDisk;

public class UI extends JFrame {
	private static final long serialVersionUID = -2356097739670395566L;
	public UI(final String aTraceFileName) {
		this.addWindowListener(new WindowListener() {
			public void windowOpened(final WindowEvent e) {
			}
			public void windowIconified(final WindowEvent e) {
			}
			public void windowDeiconified(final WindowEvent e) {
			}
			public void windowDeactivated(final WindowEvent e) {
			}
			public void windowClosing(final WindowEvent e) {
			}
			public void windowClosed(final WindowEvent e) {
			}
			public void windowActivated(final WindowEvent e) {
			}
		});
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setTitle("MoDeC Marker");

		final JTextField textField = new JTextField();
		final JButton buttonStart = new JButton("Insert Start Marker");
		buttonStart.setBackground(Color.GREEN);
		final JButton buttonStop = new JButton("Insert Stop Marker");
		buttonStop.setEnabled(false);

		buttonStart.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent anActionEvent) {
				try {
					final Writer writer =
						ProxyDisk.getInstance().fileAbsoluteOutput(
							aTraceFileName,
							true);
					writer.write("# MARKER START: ");
					writer.write(textField.getText());
					writer.write('\n');
					writer.close();

					buttonStop.setBackground(Color.GREEN);
					buttonStop.setEnabled(true);
					buttonStart.setBackground(Color.LIGHT_GRAY);
					buttonStart.setEnabled(false);
				}
				catch (final IOException e) {
					e.printStackTrace();
				}
			}
		});
		buttonStop.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent anActionEvent) {
				try {
					final Writer writer =
						ProxyDisk.getInstance().fileAbsoluteOutput(
							aTraceFileName,
							true);
					writer.write("# MARKER STOP: ");
					writer.write(textField.getText());
					writer.write('\n');
					writer.close();

					buttonStart.setBackground(Color.GREEN);
					buttonStart.setEnabled(true);
					buttonStop.setBackground(Color.LIGHT_GRAY);
					buttonStop.setEnabled(false);
				}
				catch (final IOException e) {
					e.printStackTrace();
				}
			}
		});

		this.getContentPane().setLayout(
			new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		this.getContentPane().add(textField);
		this.getContentPane().add(buttonStart);
		this.getContentPane().add(buttonStop);
		this.pack();

		final Dimension uiDimension = this.getSize();
		final Dimension screenDimension =
			Toolkit.getDefaultToolkit().getScreenSize();
		//	this.setLocation((int) (screenDimension.getWidth() - uiDimension
		//		.getWidth()) / 2, (int) (screenDimension.getHeight() - uiDimension
		//		.getHeight()) / 2);
		this.setLocation(
			(int) (screenDimension.getWidth() - uiDimension.getWidth()),
			(int) (screenDimension.getHeight() - uiDimension.getHeight()));
	}
}
