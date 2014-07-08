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
 * PURPOSE, AND NOT WITHSTANDING ANY OTHER PROVISION CONTAINED HEREIN,
 * ANY LIABILITY FOR DAMAGES RESULTING FROM THE SOFTWARE OR ITS USE IS
 * EXPRESSLY DISCLAIMED, WHETHER ARISING IN CONTRACT, TORT (INCLUDING
 * NEGLIGENCE) OR STRICT LIABILITY, EVEN IF YANN-GAEL GUEHENEUC IS
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * 
 * All Rights Reserved.
 */
package util.awt;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import util.multilingual.MultilingualManager;

public class NameDialog extends Dialog {
	private static final long serialVersionUID = 3685715927255933454L;
	private static final String BUTTON_LABEL = MultilingualManager.getString(
		"BUTTON_LABEL",
		NameDialog.class);
	private final TextField textField;

	public NameDialog(final Frame owner, final String question) {
		super(
			owner,
			MultilingualManager.getString("TITLE", NameDialog.class),
			true);

		new WindowCloser(this);

		this.textField = new TextField("");
		final Label label = new Label(question);
		final Button button = new Button(NameDialog.BUTTON_LABEL);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				NameDialog.this.dispose();
			}
		});

		this.textField.addKeyListener(new KeyListener() {
			public void keyPressed(final KeyEvent e) {
			}
			public void keyReleased(final KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					button.dispatchEvent(new ActionEvent(
						NameDialog.this.textField,
						ActionEvent.ACTION_PERFORMED,
						NameDialog.BUTTON_LABEL));
				}
				else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					NameDialog.this.textField.setText("");
					button.dispatchEvent(new ActionEvent(
						NameDialog.this.textField,
						ActionEvent.ACTION_PERFORMED,
						NameDialog.BUTTON_LABEL));
				}
			}
			public void keyTyped(final KeyEvent e) {
			}
		});

		this.setLayout(new BorderLayout());
		this.add(label, BorderLayout.NORTH);
		this.add(this.textField, BorderLayout.CENTER);
		this.add(button, BorderLayout.SOUTH);
		this.pack();

		final Dimension screenDimension =
			Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(
			(screenDimension.width - this.getWidth()) / 2,
			(screenDimension.height - this.getHeight()) / 2);

		this.setVisible(true);
	}
	public String getName() {
		return this.textField.getText().trim();
	}
}